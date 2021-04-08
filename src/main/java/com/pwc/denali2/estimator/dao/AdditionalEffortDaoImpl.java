package com.pwc.denali2.estimator.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.AdditionalService;
import com.pwc.denali2.estimator.model.AdditionalServiceVo;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.model.ProjectRates;

@Repository("AdditionalEffortDao")
public class AdditionalEffortDaoImpl extends AbstractDao<Integer, AdditionalService>implements AdditionalEffortDao{
	
	@Autowired
	MasterAdminUtilityDaoImpl masterAdminUtilityDaoImpl;

	@Override
	public Map<Object, Object> findAdditionalServiceEffortData(Integer id) {
		String sql ="";
		
		Map<Object, Object> mapAddServcEffortDetails = new HashMap<Object, Object>();
		 Map<Object, Object> mapTotal =null;
		
		List<MasterAdminLovs> worstreamList =masterAdminUtilityDaoImpl.getMasterAdminListData("workstream", "");
		List<MasterAdminLovs> prototypList =masterAdminUtilityDaoImpl.getMasterAdminListData("prototype", "");
		
		for(MasterAdminLovs workstream:worstreamList)  {
			
			double totalSumRsrWorkstreamRates =0.0,totalSumCspWorkstreamRates=0.0,totalEffortWorkstreamRates =0.0,totalSumMarginWorkstreamRates	=0.0,totalSumMarginWorkstream=0.0;
			List<Map<Object, Object>> ls =new ArrayList<Map<Object,Object>> ();
			
			for(MasterAdminLovs prototyp:prototypList){
				Map<Object, Object> map = new HashMap<Object, Object>();
				double sumEffrtWrkstram =0;
				double sumRsrRates = 0 ,sumCspRates =0;
				try {
					sql ="SELECT  sum(additional_services_transaction_join_table.value) FROM additional_service,additional_services_transaction_join_table where additional_service.id =additional_services_transaction_join_table.additonal_service_id and   additional_service.engagement_id = "+id+" and additional_service.work_stream_id = "+workstream.getId()+"  and additional_services_transaction_join_table.prototype_id = "+prototyp.getId();
					//sql ="SELECT  sum(value) FROM additional_service where engagement_id = "+id+" and work_stream_id = "+workstream.getId()+" and prototype_id = "+prototyp.getId();
				    sumEffrtWrkstram = jdbcTemplate.queryForObject(sql, Double.class);
				    totalEffortWorkstreamRates +=sumEffrtWrkstram;
				
				} catch (NullPointerException e) {
					sumEffrtWrkstram =0;
					e.printStackTrace();
					
				}
				
			
				sql ="SELECT additional_service .*,additional_services_transaction_join_table.prototype_id,additional_services_transaction_join_table.value FROM additional_service,additional_services_transaction_join_table where additional_service.id=additional_services_transaction_join_table.additonal_service_id and additional_service.engagement_id = "+id+" and additional_service.work_stream_id = "+workstream.getId()+" and additional_services_transaction_join_table.prototype_id = "+prototyp.getId();
	 
				    //sql ="SELECT * FROM additional_service where engagement_id = "+id+" and work_stream_id = "+workstream.getId()+" and prototype_id = "+prototyp.getId();
					List<AdditionalServiceVo> additionalService = jdbcTemplate.query(sql,
							new BeanPropertyRowMapper<AdditionalServiceVo>(
									AdditionalServiceVo.class));
					if(additionalService.size() >0){
						sumCspRates =0.0;
						for(AdditionalServiceVo list :additionalService){
							sumCspRates =sumCspRates +(list.getRate()*list.getValue());
							
						}
						totalSumCspWorkstreamRates +=sumCspRates;
						System.out.println(totalSumCspWorkstreamRates);
					
					
					
					}
					
				
				
				
                    sql ="SELECT *  FROM project_rates where rate_type ='rsr' and worktype='onsite'and engagement_id ='"+id+"' and workstream_id ='"+workstream.getId()+"'";
                    List<ProjectRates> projctrates = jdbcTemplate.query(sql,
							new BeanPropertyRowMapper<ProjectRates>(
									ProjectRates.class));
                    if(projctrates.size() >0){
                    	
                    	for(ProjectRates m:projctrates){
   				    	 Double value =0.0;	
   				    	sql ="SELECT additional_service .*,additional_services_transaction_join_table.prototype_id,additional_services_transaction_join_table.value FROM additional_service,additional_services_transaction_join_table where additional_service.id=additional_services_transaction_join_table.additonal_service_id and additional_service.engagement_id = "+id+"  and additional_service.level_id ="+m.getLevel_id()+" and additional_service.work_stream_id = "+workstream.getId()+" and additional_services_transaction_join_table.prototype_id = "+prototyp.getId();
   					 
   				    	// sql ="SELECT * FROM additional_service where engagement_id = "+id+" and work_stream_id = "+workstream.getId()+" and level_id ="+m.getLevel_id()+" and prototype_id = "+prototyp.getId();
   				    	 List<AdditionalServiceVo> addservc = jdbcTemplate.query(sql,
   								new BeanPropertyRowMapper<AdditionalServiceVo>(
   										AdditionalServiceVo.class));
   				    	
   				    	 if(addservc.size() >0){
   				    		 
   				    		sumRsrRates =sumRsrRates +(m.getRate()*addservc.get(0).getValue());
   				    		totalSumRsrWorkstreamRates +=sumRsrRates;
   				    	 }
   				    	 
   				    	 
   				     }
                    	
                    }
				     
				     
	            double margin =sumCspRates - sumRsrRates;
	            totalSumMarginWorkstream +=margin;	 
			    map.put("workstream",workstream.getCode());
			    map.put("prototype",prototyp.getCode());
			    map.put("sumEffortWorkstream",sumEffrtWrkstram);
			    map.put("sumEffortDDC",0);
			    map.put("sumCSPRates", sumCspRates);
			    map.put("sumCSPRatesDDC",0);
			    map.put("sumRsrRates", sumRsrRates);
			    map.put("sumRsrRatesDDC",0);
			    map.put("marginWorkstream", margin);
			    map.put("marginDDc", 0);
			    
			    
			    
			    
			   
			    ls.add(map);
			    
			    
		   }
			    mapTotal =new HashMap<Object, Object>();
			    mapTotal.put("totalEffortWorkstreamRates", totalEffortWorkstreamRates);
			    mapTotal.put("totalSumCspWorkstreamRates", totalSumCspWorkstreamRates);
			    mapTotal.put("totalSumRsrWorkstreamRates", totalSumRsrWorkstreamRates); 
			    mapTotal.put("totalSumMarginWorkstream", totalSumMarginWorkstream);
			    ls.add(mapTotal);
			mapAddServcEffortDetails.put(workstream.getCode(), ls);
			
			
			mapAddServcEffortDetails.put("workstream", worstreamList);
		}
		
		
		
		return mapAddServcEffortDetails;
		
	}

}
