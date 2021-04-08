package com.pwc.denali2.estimator.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.graphbuilder.struc.LinkedList;
import com.pwc.denali2.estimator.model.AdditionalService;
import com.pwc.denali2.estimator.model.MasterAdminLovs;

@Repository("additionalDao")
public class AdditionalDaoImpl extends AbstractDao<Integer, AdditionalService> implements AdditionalDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MasterAdminUtilityDaoImpl masterAdminUtilityDao;

	@SuppressWarnings("unchecked")
	public Map<Object, Object> findAllByEngagementId(Integer id) {
		String sql ="";
		Map<String, String> protypvalueMap =null;
		Map<Object, Object> allAdditionalServiceInitData = new HashMap<Object, Object>();
		List<MasterAdminLovs> serviceOwnerLov= masterAdminUtilityDao.getMasterAdminListData("service owner","");
		List<MasterAdminLovs> LevelLov= masterAdminUtilityDao.getMasterAdminListData("levels","");
		List<MasterAdminLovs> PrototypeLov= masterAdminUtilityDao.getMasterAdminListData("prototype","");
		List<MasterAdminLovs> workstreamLov= masterAdminUtilityDao.getMasterAdminListData("workstream","");
		
//		sql ="SELECT * from master_admin_lovs where lower(type) ='levels' and bu_factor ='offshore' order by description";
//		List<MasterAdminLovs> LevelLov = jdbcTemplate.query(sql,
//				new BeanPropertyRowMapper<MasterAdminLovs>(
//						MasterAdminLovs.class));
		 sql ="SELECT additional_service .* from additional_service,master_admin_lovs where additional_service.work_stream_id = master_admin_lovs.id and additional_service.engagement_id ="+id+" order by master_admin_lovs.description";
		 //sql="SELECT * FROM additional_service where engagement_id ="+id;
		 List<AdditionalService> additionalServiceDbList = jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<AdditionalService>(
							AdditionalService.class));
		 List<Map<String, Object>> additionalServiceTotalList = new ArrayList<Map<String, Object>>();
		 
		 int count =0;double sum = 0;
		 for(AdditionalService additionalService :additionalServiceDbList){
			 sum =0;
			  //Map<String, Object> additionalServiceTotalDta = new LinkedHashMap<String, Object>();
			 Map<String, Object> eachMap= new LinkedHashMap<String, Object>(); 
			 eachMap.put("id",additionalService.getId()); 
			 eachMap.put("work_stream_id",additionalService.getWork_stream_id() );
			 eachMap.put("service_owner_id",additionalService.getService_owner_id() );
			 eachMap.put("engagement_id", additionalService.getEngagement_id());
			 eachMap.put("activity", additionalService.getActivity());
			 eachMap.put("level_id", additionalService.getLevel_id());
			 eachMap.put("rate", additionalService.getRate());
			 
			 sql ="SELECT prototype_id,value FROM additional_services_transaction_join_table where additonal_service_id = "+additionalService.getId();
			 List<Map<String, Object>> listOfMap = jdbcTemplate.queryForList(sql);
			 protypvalueMap= new HashMap<String, String>();
			 
			 for(Map<String, Object> m:listOfMap){
				  
				 protypvalueMap.put(m.get("prototype_id").toString(), m.get("value").toString());
				 sum = sum + (Double.parseDouble(m.get("value").toString()));
			 }
			 eachMap.put("tatalEffort", sum);
			 eachMap.put("protos", protypvalueMap);

			 //additionalServiceTotalDta.put(count+"", eachMap);
			 additionalServiceTotalList.add(eachMap);
			 count++;
			 
		 }
		 double TotalEffort;
        sql ="SELECT sum(additional_services_transaction_join_table.value) FROM additional_services_transaction_join_table,additional_service where additional_services_transaction_join_table.additonal_service_id =additional_service.id and additional_service.engagement_id = "+id ;
		try {
			 TotalEffort =jdbcTemplate.queryForObject(sql, Double.class);
		} catch (Exception e) {
			TotalEffort =0.0;
		}
		
		allAdditionalServiceInitData.put("serviceOwnerLov", serviceOwnerLov);
		allAdditionalServiceInitData.put("LevelLov", LevelLov);
		allAdditionalServiceInitData.put("PrototypeLov", PrototypeLov);
		allAdditionalServiceInitData.put("workstreamLov", workstreamLov);
		allAdditionalServiceInitData.put("additionalServiceTotalDta", additionalServiceTotalList);
		allAdditionalServiceInitData.put("TotalEffort", TotalEffort);
		
		
		
		return allAdditionalServiceInitData;
	
	}

	
	

	@Override
	public void deleteByID(Integer id) {
		
		String sql ="DELETE FROM additional_services_transaction_join_table WHERE additonal_service_id IN (SELECT id FROM additional_service where engagement_id = "+id+")";
		System.out.println(sql);
		jdbcTemplate.update(sql);
		
		jdbcTemplate.update("delete from additional_service where engagement_id=? ", new Object[] { id });
		System.out.println("deleted");
		

	}



	@Override
	public void save(JSONObject jsonarr, String id) {
		String sql ="",level="",workstream="";
		double rate =0.0;
		Iterator itr = jsonarr.keys();
        Set<String> keyset=jsonarr.keySet();
        for(String i:keyset){
       	 JSONObject obj = jsonarr.getJSONObject(i);
       	 System.out.println(obj+"******");
        
     
  	    
  	   if(!StringUtils.isEmpty(obj.get("work_stream_id")) && !StringUtils.isEmpty(obj.get("service_owner_id")) && !StringUtils.isEmpty(obj.get("activity")) && !StringUtils.isEmpty(obj.get("level_id"))){
  		  
  		 try {
  		    if(obj.has("rate") && (!obj.isNull("rate")) && (!obj.get("rate").equals(0))){
  		    	System.out.println(obj.get("rate")+"***");
          	  rate = Double.parseDouble(obj.get("rate").toString());
            }
            else{
          	 
          	  sql ="SELECT rate FROM project_rates where level_id ='"+obj.get("level_id")+"' and workstream_id ='"+obj.get("work_stream_id")+"' and engagement_id = '"+id+"'and   rate_type ='csp'  order by version_id desc limit 1";
                
             	   rate =Double.parseDouble(jdbcTemplate.queryForObject(sql,String.class));
         	}  
            }
  		catch (Exception e) {
     		rate =0.0;
     	}  
  		   
	    sql ="insert into additional_service (work_stream_id,service_owner_id,engagement_id,activity,level_id,rate,create_date) "+
        "values("+obj.get("work_stream_id")+","+obj.get("service_owner_id")+","+Integer.parseInt(id)+",'"+obj.get("activity")+"',"+obj.get("level_id")+","+rate+",now())";
         System.out.println(sql);
        jdbcTemplate.update(sql);
        
        sql ="select id from additional_service where work_stream_id ="+obj.get("work_stream_id")+" and service_owner_id= "+obj.get("service_owner_id")+" and engagement_id = "+Integer.parseInt(id)+" and "
             + "level_id = "+obj.get("level_id")+"  and activity ='"+obj.get("activity")+"' and rate ="+rate;
        int idd =jdbcTemplate.queryForObject(sql,Integer.class);
       	 Set<String> keys =obj.keySet();
       	 
          	
            	if(keys.contains("protos") && (!StringUtils.isEmpty(obj.get("protos")))){
            		JSONObject prototyp_value_key = (JSONObject) obj.get("protos");
            		//System.out.println(prototyp_value_key);
            		
            		for(String prototypKey:prototyp_value_key.keySet()){
                		//System.out.println(prototypKey+"++++"+prototyp_value_key.get(prototypKey));
                		
                		sql ="insert into additional_services_transaction_join_table (prototype_id,value,additonal_service_id,engagement_id) "+
                				"values("+prototypKey+","+prototyp_value_key.get(prototypKey)+","+idd+",'"+id+"')";
                		System.out.println(sql);
                	    jdbcTemplate.update(sql);
                		
                	}
            	}
            		
            	
            }
       	
        }
		
	}




	@Override
	public void copyAddServices(int engagementId, String status,
			String copyfromengId) {
		String sql ="";
		sql ="SELECT * FROM additional_service where engagement_id ='"+copyfromengId+"'";
		List<AdditionalService> additionalServiceList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<AdditionalService>(
						AdditionalService.class));
		
		for(AdditionalService additionalService:additionalServiceList){
			int id =additionalService.getId();
			
			additionalService.setId(null);
			additionalService.setEngagement_id(engagementId);
			persist(additionalService);
			
			int currid=additionalService.getId();
			 sql ="INSERT INTO `additional_services_transaction_join_table` (`prototype_id`, `value`, `additonal_service_id`, `engagement_id`)"
					+" SELECT prototype_id,value,"+currid+",'"+engagementId+"' FROM additional_services_transaction_join_table where engagement_id='"+copyfromengId+"' and additonal_service_id="+id;
		     System.out.println(sql);
		   jdbcTemplate.execute(sql);
		}
		
	}

	
	


////	@Override
//	public void save(List<Map<Object, Object>> AddservcesMapList) {
//			String sql ="",level="",workstream="";
//			
//		    System.out.println("======");
//		    System.out.println(AddservcesMapList);
//		    Map<Object, Object> Prototypdatamap =AddservcesMapList.get((AddservcesMapList.size()) -1);
//		    System.out.println(Prototypdatamap);
//		    
//		    for(Map<Object, Object> map :AddservcesMapList){
//		    	Set<Object> keys =map.keySet();
//		    	for(Object key:keys){
//		    		double rate = 0.0;
//		    		level = masterAdminUtilityDao.getMasterAdminCode(Integer.parseInt(map.get("level_id").toString().toLowerCase()));
//			    	workstream = masterAdminUtilityDao.getMasterAdminCode(Integer.parseInt(map.get("work_stream_id").toString().toLowerCase()));
//			    	
//			    	 sql ="SELECT rate FROM denali_test.project_rates where lower(level) ='"+level+"' and lower(workstream) ='"+workstream+"' and engagement_id = '"+map.get("engagement_id")+"'and   rate_type ='csp' and worktype ='offsite'";
//			         rate =Double.parseDouble(jdbcTemplate.queryForObject(sql,String.class));
//			         System.out.println(rate);
//		    		if(key.toString().contains(map.get("level_id").toString()+"_")){
//		    			double value =0.0;
//		    			value=Double.parseDouble(map.get(key).toString());
//		    			
//				    	sql ="insert into additional_service (service_owner_id,engagement_id,activity,prototype_id,value,level_id,rate,create_dt) "+
//					              "values("+map.get("service_owner_id")+","+map.get("engagement_id")+",'"+map.get("activity")+"',"+key+","+map.get(key)+","+map.get("level_id")+","+rate+",now())";
//		    			
//		    		  jdbcTemplate.update(sql);
//		    		}
//		    	
//				
//		    	}
//		    	
//		    	
//		    	
//		    	
//		    	
//		    }
//			
//			
//			
////			sql ="SELECT rate FROM denali_test.project_rates where lower(level) ='"+level+"' and lower(workstream) ='"+workstream+"' and engagement_id = '"+additionalService.getEngagement_id()+"'and   rate_type ='csp' and worktype ='offsite'";
////			rate =jdbcTemplate.queryForObject(sql,String.class);
////			additionalService.setRate(Double.parseDouble(rate));
////		    Set<Object> list =prototypValue.keySet();
////		    for(Object obj:list){
////		    	if(obj.equals(additionalService.getId()+"_"+additionalService.getPrototype_id())){
////		    		additionalService.setPrototype_id(additionalService.getPrototype_id());
////		    		additionalService.setValue(Double.parseDouble(prototypValue.get(obj).toString()));
////		    	}
////		    }
////			System.out.println(list);
////			persist(additionalService);
//	
//		
//	}



	
}