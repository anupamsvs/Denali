package com.pwc.denali2.estimator.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.RiceTransactionData;

@Repository("riceTransactionDataDao")
public class RiceTransactionDataDaoImpl extends AbstractDao<Integer, RiceTransactionData> implements RiceTransactionDataDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	public List<RiceTransactionData> findAllByEngagementId(Integer id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("engagement_id", id));
		crit.addOrder(Order.asc("rice_type_id"));
		return (List<RiceTransactionData>) crit.list();
	}

	@Override
	public List<Integer> save(RiceTransactionData riceTransactionData,List<Integer> idList) {
		
		Map<String,String> mapEffortsDta = new HashMap<String, String>();
		String sqlForWorktype ="SELECT * FROM master_admin_lovs where lower(type) ='work type' order by bu_factor";
		//List<MasterAdminLovs> Worktypedata =(List<MasterAdminLovs>) findAll("").get("worktypeLov");
		List<MasterAdminLovs> Worktypedata = jdbcTemplate.query(sqlForWorktype,
				new BeanPropertyRowMapper<MasterAdminLovs>(
						MasterAdminLovs.class));
		String sql ="";
		int id = 0;
	


		if(riceTransactionData.getId() == -1){
			System.out.println("per");
			System.out.println(riceTransactionData.getRice_name());
//			    riceTransactionData.setId(null);
//				persist(riceTransactionData);
			sql ="insert into rice_transaction_data (rice_type_id,module_id,client_complexity_id,engagement_id,rice_name,rice_source_system,rice_target_system,create_date,update_date) values "+
            "("+riceTransactionData.getRice_type_id()+","+riceTransactionData.getModule_id()+","+riceTransactionData.getClient_complexity_id()+","+riceTransactionData.getEngagement_id()+",'"+riceTransactionData.getRice_name()+"','"+riceTransactionData.getRice_source_system()+"','"+riceTransactionData.getRice_target_system()+"',now(),now())";
            System.out.println(sql);
	   	    jdbcTemplate.update(sql);
	   	    
	   	    sql ="SELECT id FROM rice_transaction_data where rice_type_id ='"+riceTransactionData.getRice_type_id()+"' and module_id ='"+riceTransactionData.getModule_id()+"' and client_complexity_id ='"+riceTransactionData.getClient_complexity_id()+"' and engagement_id ='"+riceTransactionData.getEngagement_id()+"' and "
                  +"rice_name ='"+riceTransactionData.getRice_name()+"' and rice_source_system ='"+riceTransactionData.getRice_source_system()+"' and rice_target_system ='"+riceTransactionData.getRice_target_system()+"'";
   	       id =jdbcTemplate.queryForObject(sql, Integer.class);	   	    
	   	    
   	    idList.add(id);
		}else {
			
			
			update(riceTransactionData);
			id = riceTransactionData.getId();
			idList.add(id);
		}
		
		
		
	
		
		for(MasterAdminLovs data:Worktypedata){
			
			sql ="SELECT  distinct CONCAT_WS('_',rice_effort_master.effort,complexity_multiplier.value) FROM rice_effort_master,complexity_multiplier where rice_effort_master.workstream =complexity_multiplier.workstream and complexity_multiplier.effort_type ='rice'  and rice_effort_master.worktype ='"+data.getId()+"' and rice_effort_master.complexity='"+riceTransactionData.getClient_complexity_id()+"' and rice_effort_master.rie_type ='"+riceTransactionData.getRice_type_id()+"' and complexity_multiplier.engagement_id = "+riceTransactionData.getEngagement_id();
			System.out.println(sql);
			String effort =jdbcTemplate.queryForObject(sql, String.class);
			System.out.println(effort);
			
			Double result =(Double.parseDouble(effort.split("_")[0].toString()))*(Double.parseDouble(effort.split("_")[1].toString()));
			
			
	
			sql ="insert into rice_transaction_join_table(worktype,effort,rice_data_id,engagement_id) values('"+data.getId()+"','"+result+"',"+id+",'"+riceTransactionData.getEngagement_id()+"') "+
					" ON DUPLICATE KEY UPDATE effort ='"+result+"'";
			jdbcTemplate.update(sql);
			
		}
		 
		
		return idList;
		
	}

	@Override
	public void deleteByID(List<Integer> idListForDbInsert ,Integer id) {
//		String sql ="DELETE FROM rice_transaction_join_table WHERE rice_data_id IN (SELECT id FROM rice_transaction_data where engagement_id = '"+id+"')";
//		jdbcTemplate.update(sql);
//		jdbcTemplate.update("delete from rice_transaction_data where engagement_id=? ", new Object[] { id });
		
		
		 String sql = "SELECT id FROM rice_transaction_data where engagement_id ="+id;
		 List<Integer> dbListOfId = jdbcTemplate.queryForList(sql, Integer.class);
		 System.out.println(dbListOfId);
		 
		 
		 dbListOfId.removeAll(idListForDbInsert);
		 System.out.println(dbListOfId);
		 if (dbListOfId.size() >0) {
			 
			 String idInForUpdate =org.apache.commons.lang.StringUtils.join(dbListOfId, ',');
			 System.out.println(idInForUpdate);
			 sql ="delete from rice_transaction_data where engagement_id="+id+" and id in("+idInForUpdate+")";
			 System.out.println(sql);
			 jdbcTemplate.update(sql);
		}
		 
		 
		 
		

	}

	@Override
	public Map<Object, Object> findAll(String idparam) {
		 String InvalidRiceStr ="";
		String listInvalidRice="Select id FROM rice_transaction_data where engagement_id='"+idparam+"' and module_id not in(select id from module_transaction_data where engagement_id ='"+idparam+"' and  lower(is_in_scope_flag) ='yes')";
	    List<Integer> listInvalidId=jdbcTemplate.queryForList(listInvalidRice, Integer.class);
	   
	    
	    if(listInvalidId.size()>0){
			if(listInvalidId.size()==1){
				InvalidRiceStr ="'"+listInvalidId.get(0)+"'";
			}else{
				InvalidRiceStr ="'"+org.apache.commons.lang.StringUtils.join(listInvalidId, "','")+"'";
			}
			String SqlDeleteInvalidRecords="delete FROM rice_transaction_data where id in("+InvalidRiceStr+")";
			jdbcTemplate.update(SqlDeleteInvalidRecords);
			
			String SqlDeleteInvalidRecordsJoinTable="delete FROM rice_transaction_join_table  where rice_data_id in("+InvalidRiceStr+")";
			jdbcTemplate.update(SqlDeleteInvalidRecordsJoinTable);
			
		}
	    

		
		
	    Map<Object, Object>mapOfAllRiceDta = new HashMap<Object,Object>();
		
		String sqlForComplexity ="SELECT * FROM master_admin_lovs where lower(type) ='complexity' order by description";
		String sqlForRietype ="SELECT * FROM master_admin_lovs where lower(type) ='rie type'";
		String sqlForWorktype ="SELECT * FROM master_admin_lovs where lower(type) ='work type' order by bu_factor";
		
		String sqlforRiceJoinTable ="select CONCAT_WS('_',rice_data_id,worktype) as data ,effort from rice_transaction_join_table";
		String sqlworkstreammtype ="SELECT * FROM master_admin_lovs where lower(type) ='Workstream'";
		
		
		 String sqlforComplexityMultiplier ="select workstream,value from complexity_multiplier where effort_type ='rice' and engagement_id ="+Integer.parseInt(idparam);
		 
			List<MasterAdminLovs> queryForComplexity = jdbcTemplate.query(sqlForComplexity,
					new BeanPropertyRowMapper<MasterAdminLovs>(
							MasterAdminLovs.class));
			List<MasterAdminLovs> queryForRieType = jdbcTemplate.query(sqlForRietype,
					new BeanPropertyRowMapper<MasterAdminLovs>(
							MasterAdminLovs.class));
			List<MasterAdminLovs> queryForWorktype = jdbcTemplate.query(sqlForWorktype,
					new BeanPropertyRowMapper<MasterAdminLovs>(
							MasterAdminLovs.class));
			
			List<MasterAdminLovs> queryForWorkstream = jdbcTemplate.query(sqlworkstreammtype,
					new BeanPropertyRowMapper<MasterAdminLovs>(
							MasterAdminLovs.class));
		
		 
		 
		
		
		if(!idparam.equals("")){
			int id  = Integer.parseInt(idparam);
			String sqlForRiceDta ="select * from rice_transaction_data where engagement_id = "+id;
			String sqlForModule ="SELECT * FROM module_transaction_data where engagement_id = "+id+" and lower(is_in_scope_flag) ='yes'";
			List<ModuleTransactionData> queryForModule = jdbcTemplate.query(sqlForModule,
					new BeanPropertyRowMapper<ModuleTransactionData>(
							ModuleTransactionData.class));
			List<RiceTransactionData> queryForricedata = jdbcTemplate.query(sqlForRiceDta,
					new BeanPropertyRowMapper<RiceTransactionData>(
							RiceTransactionData.class));
			
			mapOfAllRiceDta.put("moduleNameLov", queryForModule);
			mapOfAllRiceDta.put("riceDta", queryForricedata);
			
		}
		
		
      
	
		
		List<Map<String, Object>>  data =jdbcTemplate.queryForList(sqlforRiceJoinTable);
		
		Map<Object, Object> listWorktypeRiceiddata = new HashMap<Object, Object>();
		for(Map<String, Object> map:data){
			listWorktypeRiceiddata.put(map.get("data"),map.get("effort"));
		}
		
		initRiceClientComplexityMultiplier(queryForWorkstream);
		 List<Map<String, Object>> Complexity_multiplier = jdbcTemplate.queryForList(sqlforComplexityMultiplier);
		 
	
		
		Map<Object, Object> listComplexity_multiplier = new HashMap<Object, Object>();
		if(Complexity_multiplier.size() >0){
		for(Map<String, Object> map:Complexity_multiplier){
			listComplexity_multiplier.put(map.get("workstream"),map.get("value"));
		}
		}
		Map<Object, Object> Allcount_rice_record = getRiePassRecordCount(queryForRieType,queryForComplexity,idparam);
		
		String sql ="SELECT count(*) FROM rice_transaction_data where engagement_id ="+Integer.parseInt(idparam);
		int countRie = jdbcTemplate.queryForObject(sql, Integer.class);
		
		mapOfAllRiceDta.put("complexityLov", queryForComplexity);
		mapOfAllRiceDta.put("rieLov", queryForRieType);
		mapOfAllRiceDta.put("worktypeLov", queryForWorktype);
		mapOfAllRiceDta.put("WorkstreamLov", queryForWorkstream);
		
		mapOfAllRiceDta.put("listWorktypeRiceiddata",listWorktypeRiceiddata);
		mapOfAllRiceDta.put("complexity_multiplier", listComplexity_multiplier);
		mapOfAllRiceDta.put("Allcount_rice_record", Allcount_rice_record);
		mapOfAllRiceDta.put("AllRieTotal",countRie);
		
		return mapOfAllRiceDta;
		
		
	}
	
	private void initRiceClientComplexityMultiplier(List<MasterAdminLovs> queryForWorkstream) {
	  String sql ="SELECT count(*) FROM complexity_multiplier where (effort_type) ='rice'";
	  int countrows =jdbcTemplate.queryForObject(sql,Integer.class);
	  if(countrows ==0){
		  for(MasterAdminLovs data:queryForWorkstream){
			   sql ="insert into complexity_multiplier(workstream,value,effort_type) values('"+data.getId()+"','1','rice')";
					jdbcTemplate.update(sql);
		  } 
	  }
		 
	  
		
	}

	private Map<Object, Object> getRiePassRecordCount(List<MasterAdminLovs> RieTypeAllDta,
			List<MasterAdminLovs> ComplexityAllDat,String id) {
		String sql ="";
		System.out.println(id+"****");
		Map<Object, Object> mapCountRiceRecord = new HashMap<Object, Object>();
		if(!StringUtils.isEmpty(id)){
		for(MasterAdminLovs riedata :RieTypeAllDta){
			for(MasterAdminLovs complexitydata :ComplexityAllDat){
			  sql ="select count(*) from rice_transaction_data where client_complexity_id = "+complexitydata.getId()+" and rice_type_id ="+riedata.getId()+" and engagement_id ="+Integer.parseInt(id);
			  int record_count =jdbcTemplate.queryForObject(sql,Integer.class);
			  mapCountRiceRecord.put(riedata.getId()+"_"+complexitydata.getId(), record_count);
				
			}	
			
		}
		}
		System.out.println(mapCountRiceRecord);
		return mapCountRiceRecord;
	}

	@Override
	public void saveComplexityMultiplier(Map<String, Map<String, String>> data,String type) {
		
		// {data={2=4, 3=5, 4=5}, id={engId=16}}
		String engId =data.get("id").get("engId");
		System.out.println(engId);
		if(!StringUtils.isEmpty(data.get("id").get("engId"))){
			
			Map<String, String> mapComplMultiplier =data.get("data");
			 Iterator it = mapComplMultiplier.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        
			        String sql ="insert into complexity_multiplier(workstream,value,effort_type,engagement_id) values('"+pair.getKey()+"','"+pair.getValue()+"','"+type+"',"+Integer.parseInt(engId)+")"+
							" ON DUPLICATE KEY UPDATE value ='"+pair.getValue()+"'";
					System.out.println(sql);
					jdbcTemplate.update(sql);
			        
			        System.out.println(pair.getKey() + " = " + pair.getValue());
			        //it.remove(); // avoids a ConcurrentModificationException
			    }
			
		
			
		
		}
		
	}
	

	
	

	

	
	
	
	
}