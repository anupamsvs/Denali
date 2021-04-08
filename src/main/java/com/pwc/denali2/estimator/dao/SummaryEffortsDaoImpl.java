package com.pwc.denali2.estimator.dao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.AdditionalService;
import com.pwc.denali2.estimator.model.AdditionalServiceVo;
import com.pwc.denali2.estimator.model.DetailSummaryVo;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.model.ProjectRates;
import com.pwc.denali2.estimator.model.StaffDriverBlendedRateTransactionData;

@Repository("SummaryEffortsDao")
public class SummaryEffortsDaoImpl extends AbstractDao<Integer, AdditionalService> implements SummaryEffortsDao {
	
	@Autowired
	MasterAdminUtilityDaoImpl masterAdminUtilityDaoImpl;
	
	@Autowired
	ProjectDurationDaoImpl projectDurationDaoImpl;

	@Override
	public Map<String, Object> getSummaryEffortForEngId(int engId) {
		Object currWorkstream = null ,currPrototyp;
		double totalEffortOnsite = 0.0,totalEffortDDC = 0.0,totalCspRateOnsite = 0.0,totalRSRRateOnsite = 0.0,totalCspRateDDC = 0.0,totalRSRRateDDC = 0.0,totalMarginOnsite = 0.0,totalMarginDDC = 0.0;
		
		 Map<Object, Object> mapAddServcEffortDetails = new HashMap<Object, Object>();
		 Map<Object, Object> mapTotal =null;
		 Map<Object, Object> OverallTotalOfAllWorkstreamMap =null;
		Map<String, Object> SummaryEffortmap = new HashMap<String, Object>();
		List<MasterAdminLovs> worstreamList =masterAdminUtilityDaoImpl.getMasterAdminListData("workstream", "");
		List<MasterAdminLovs> prototypList =masterAdminUtilityDaoImpl.getMasterAdminListData("prototype", "");
		
		Map<Object, Object> mapAdditionalEffortsSummary = getAdditionalEffortsSummary(worstreamList,prototypList,engId);
		Map<Object, Object> mapProjectEffortsSummary =getProjectEffortsSummary(worstreamList,prototypList,engId);

				for(MasterAdminLovs malworkstream:worstreamList){
					
					double totalSumRsrWorkstreamRates =0.0,totalSumCspWorkstreamRates=0.0,totalSumRsrDDCRates =0.0,totalSumCspDDcRates=0.0,totalEffortWorkstreamRates =0.0,totalEffortDDCRates=0.0,totalSumMarginWorkstream=0.0,totalSumMarginDDC=0.0;
					currWorkstream =malworkstream.getCode();
					List<Map<Object, Object>> listofMapAddSevEffort= (List<Map<Object, Object>>) mapAdditionalEffortsSummary.get(currWorkstream);
					List<Map<Object, Object>> listofMapProjectEffort= (List<Map<Object, Object>>) mapProjectEffortsSummary.get(currWorkstream);
					List<Map<Object, Object>> ls =new ArrayList<Map<Object,Object>> ();
					
					
				for(Map<Object, Object> mapAddServ:listofMapAddSevEffort){
					
					
					currPrototyp =mapAddServ.get("prototype");
					for(Map<Object, Object> mapProjctEffrt:listofMapProjectEffort){
						double EffrtWrkstram =0,EffortDDC=0;
						double RsrRatesworkstream = 0 ,CspRatesworkstream =0,rsrRatesDdc = 0 ,cspRatesDdc =0,ratePerHrOnsite=0,ratePerHrDDC =0,ratePerHrTotal =0,marginOnsite=0.0,marginDDC =0.0 ;
						
						if(mapProjctEffrt.containsKey("workstream") &&mapProjctEffrt.containsValue(currWorkstream) && mapProjctEffrt.containsKey("prototype") &&mapProjctEffrt.containsValue(currPrototyp) )
						{
							
							Map<Object, Object> map = new HashMap<Object, Object>();
							EffrtWrkstram =Double.parseDouble(mapAddServ.get("sumEffortWorkstream").toString()) + Double.parseDouble(mapProjctEffrt.get("sumEffortWorkstream").toString());
							EffortDDC =Double.parseDouble(mapAddServ.get("sumEffortDDC").toString()) + Double.parseDouble(mapProjctEffrt.get("sumEffortDDC").toString());
							CspRatesworkstream =Double.parseDouble(mapAddServ.get("sumCSPRates").toString()) + Double.parseDouble(mapProjctEffrt.get("sumCSPRates").toString());
							cspRatesDdc =Double.parseDouble(mapAddServ.get("sumCSPRatesDDC").toString()) + Double.parseDouble(mapProjctEffrt.get("sumCSPRatesDDC").toString());
							RsrRatesworkstream =Double.parseDouble(mapAddServ.get("sumRsrRates").toString()) + Double.parseDouble(mapProjctEffrt.get("sumRsrRates").toString());
							rsrRatesDdc =Double.parseDouble(mapAddServ.get("sumRsrRatesDDC").toString()) + Double.parseDouble(mapProjctEffrt.get("sumRsrRatesDDC").toString());
							marginOnsite =Double.parseDouble(mapAddServ.get("marginWorkstream").toString()) + Double.parseDouble(mapProjctEffrt.get("marginWorkstream").toString());
							marginDDC =Double.parseDouble(mapAddServ.get("marginDDc").toString()) + Double.parseDouble(mapProjctEffrt.get("marginDDc").toString());
							
							
							
							if(EffrtWrkstram !=0){
								ratePerHrOnsite = CspRatesworkstream/EffrtWrkstram;
							}
							if(EffortDDC !=0){
								ratePerHrDDC = cspRatesDdc/EffortDDC;
							}
							
							
							 DecimalFormat oneDigit = new DecimalFormat("#.#");//format to 1 decimal place
							  double roundedvalueDDC = Double.valueOf(oneDigit.format(EffortDDC));
							  double roundedvaluewrkstream = Double.valueOf(oneDigit.format(EffrtWrkstram));
							  
							  double effortTotal = roundedvalueDDC + roundedvaluewrkstream;
							  if(effortTotal==0.0 ||effortTotal==0){
								  ratePerHrTotal =0; 
							  }else{
								  double cspratesTotal = Math.round(CspRatesworkstream) + Math.round(cspRatesDdc);
								  ratePerHrTotal = cspratesTotal/effortTotal;
							  }
							
							
//							 double effortTotal = Math.round(EffrtWrkstram) + Math.round(EffortDDC);
//							  if(effortTotal ==0){
//								  ratePerHrTotal =0; 
//							  }else{
//								  double cspratesTotal = Math.round(CspRatesworkstream) + Math.round(cspRatesDdc);
//								  ratePerHrTotal = cspratesTotal/effortTotal;
//							  }
//							
							
							//ratePerHrTotal =ratePerHrOnsite + ratePerHrDDC;
						     
							
							totalEffortWorkstreamRates += EffrtWrkstram;
							totalEffortDDCRates += EffortDDC;
							totalSumCspWorkstreamRates += CspRatesworkstream;
							totalSumCspDDcRates += cspRatesDdc;
							totalSumRsrWorkstreamRates += RsrRatesworkstream;
							totalSumRsrDDCRates += rsrRatesDdc;
							totalSumMarginWorkstream += marginOnsite;
							totalSumMarginDDC +=marginDDC;
							
							    map.put("workstream",currWorkstream);
							    map.put("prototype",currPrototyp);
							    map.put("sumEffortWorkstream",EffrtWrkstram);
							    map.put("sumEffortDDC",EffortDDC);
							    map.put("sumCSPRates", CspRatesworkstream);
							    map.put("sumCSPRatesDDC",cspRatesDdc);
							    map.put("sumRsrRates", RsrRatesworkstream);
							    map.put("sumRsrRatesDDC",rsrRatesDdc);
							    map.put("marginWorkstream",marginOnsite);
							    map.put("marginDDc", marginDDC);
							    map.put("RateHrOnsite",ratePerHrOnsite);
							    map.put("RateHrDDC", ratePerHrDDC);
							    map.put("RateHrTotal",ratePerHrTotal);
							    ls.add(map);
							
							
						}
						
					}
					
					
				}
				 totalEffortOnsite += totalEffortWorkstreamRates;
				 totalEffortDDC +=totalEffortDDCRates;
				 totalCspRateOnsite +=totalSumCspWorkstreamRates;
				 totalCspRateDDC +=totalSumCspDDcRates;
				 totalMarginOnsite += totalSumMarginWorkstream;
				 totalMarginDDC += totalSumMarginDDC;
		         totalRSRRateOnsite +=totalSumRsrWorkstreamRates;
		         totalRSRRateDDC +=totalSumRsrDDCRates;
				mapTotal =new HashMap<Object, Object>();
			    mapTotal.put("totalEffortWorkstreamRates",totalEffortWorkstreamRates);
			    mapTotal.put("totalEffortDDCRates",totalEffortDDCRates);
			    mapTotal.put("totalSumCspWorkstreamRates",totalSumCspWorkstreamRates);
			    mapTotal.put("totalSumCspDDCRates",totalSumCspDDcRates);
			    mapTotal.put("totalSumRsrWorkstreamRates",totalSumRsrWorkstreamRates); 
			    mapTotal.put("totalSumRsrDDCRates",totalSumRsrDDCRates); 
			    mapTotal.put("totalSumMarginWorkstream",totalSumMarginWorkstream);
			    mapTotal.put("totalSumMarginDDC",totalSumMarginDDC);
			    
			    ls.add(mapTotal);
			    mapAddServcEffortDetails.put(currWorkstream, ls);
				
				}
				
				
				
				double directionalProjectMarginEng =0;
				double directionalProjectMarginDdc =0;
				if(totalCspRateOnsite !=0){
					directionalProjectMarginEng = (totalMarginOnsite/totalCspRateOnsite)*100.0;
				}
				
				if(totalCspRateDDC !=0){
					directionalProjectMarginDdc = (totalMarginDDC/totalCspRateDDC)*100.0;
				}
				
				double cspRateGrandTotal = totalCspRateOnsite + totalCspRateDDC;
				double margingrandTotal = totalMarginDDC +totalMarginOnsite;
				double effortgrandTotal = totalEffortOnsite + totalEffortDDC;
				
				double project_margin = (margingrandTotal/cspRateGrandTotal)*100.0;
				double GrandRatePerHr=(cspRateGrandTotal/effortgrandTotal)*100.0;
				System.out.println(project_margin +"+++++"+GrandRatePerHr);
				
				OverallTotalOfAllWorkstreamMap = new HashMap<Object, Object>();
				OverallTotalOfAllWorkstreamMap.put("OverallTotalEffortsOnsite",totalEffortOnsite);
				OverallTotalOfAllWorkstreamMap.put("OverallCspRateOnsite",totalCspRateOnsite);
				OverallTotalOfAllWorkstreamMap.put("OverallRsrRateOnsite",totalRSRRateOnsite);
				OverallTotalOfAllWorkstreamMap.put("OverallTotalMarginOnsite",totalMarginOnsite);
				
				OverallTotalOfAllWorkstreamMap.put("OverallTotalEffortsDDC",totalEffortDDC );
				OverallTotalOfAllWorkstreamMap.put("OverallCspRateDDC",totalCspRateDDC);
				OverallTotalOfAllWorkstreamMap.put("OverallRsrRateDDC",totalRSRRateDDC);
				OverallTotalOfAllWorkstreamMap.put("OverallTotalMarginDDC",totalMarginDDC);
				
				OverallTotalOfAllWorkstreamMap.put("GrandTotalEfforts", effortgrandTotal);
				OverallTotalOfAllWorkstreamMap.put("GrandTotalCSP",cspRateGrandTotal);
				OverallTotalOfAllWorkstreamMap.put("GrandTotalRSR",totalRSRRateOnsite + totalRSRRateDDC);
				OverallTotalOfAllWorkstreamMap.put("GrandTotalMargin",margingrandTotal);
				OverallTotalOfAllWorkstreamMap.put("GrandRatePerHr",GrandRatePerHr);
				
				
				OverallTotalOfAllWorkstreamMap.put("directionalProjectMarginEng", Math.round(directionalProjectMarginEng * 100.0)/100.0);
				OverallTotalOfAllWorkstreamMap.put("directionalProjectMarginDdc", Math.round(directionalProjectMarginDdc *100.0)/100.0);
				OverallTotalOfAllWorkstreamMap.put("project_margin", Math.round(project_margin *100.0)/100.0);
//				OverallTotalOfAllWorkstreamMap.put("directionalProjectMarginEng",directionalProjectMarginEng);
//				OverallTotalOfAllWorkstreamMap.put("directionalProjectMarginDdc",directionalProjectMarginDdc);
//				OverallTotalOfAllWorkstreamMap.put("project_margin",project_margin);
				OverallTotalOfAllWorkstreamMap.put("projectDurationPrototypSplit", projectDurationDaoImpl.getProtypSplitValue(engId));
				OverallTotalOfAllWorkstreamMap.put("protypeSplitList", prototypList);
				mapAddServcEffortDetails.put("TotalSummaryEfforts", OverallTotalOfAllWorkstreamMap);
		
		
		
		
		
		SummaryEffortmap.put("SummaryEffortForAddServices", mapAdditionalEffortsSummary);
		SummaryEffortmap.put("SummaryProjectsEfforts", mapProjectEffortsSummary);
		SummaryEffortmap.put("TotalSummaryEfforts", mapAddServcEffortDetails);
		
		
		return SummaryEffortmap;
	}

	@Override
	public Map<Object, Object> getAdditionalEffortsSummary(List<MasterAdminLovs> worstreamList, List<MasterAdminLovs> prototypList,int id) {
		String sql ="";
		double totalEffortOnsiteFrAddServices = 0.0,totalCspOnsiteFrAddServices = 0.0,totalRsrOnsiteFrAddServices = 0.0,totalMarginOnsiteFrAddServices = 0.0;
		 Map<Object, Object> mapAddServcEffortDetails = new HashMap<Object, Object>();
		 Map<Object, Object> mapTotal =null;
		 Map<Object, Object> OverallTotalOfAllWorkstreamMap =null;
		 
		
		for(MasterAdminLovs workstream:worstreamList)  {
			
			double totalSumRsrWorkstreamRates =0.0,totalSumCspWorkstreamRates=0.0,totalEffortWorkstreamRates =0.0,totalSumMarginWorkstream=0.0;
			List<Map<Object, Object>> ls =new ArrayList<Map<Object,Object>> ();
			
			for(MasterAdminLovs prototyp:prototypList){
				Map<Object, Object> map = new HashMap<Object, Object>();
				double sumEffrtWrkstram =0;
				double sumRsrRates = 0 ,sumCspRates =0,ratePerHrOnsite=0,ratePerHrDDC =0,ratePerHrTotal =0 ;
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
						
						if(sumEffrtWrkstram ==0){
							ratePerHrOnsite =0;
						}else{
							ratePerHrOnsite =  sumCspRates/sumEffrtWrkstram;
						}
						
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
	              
	               DecimalFormat oneDigit = new DecimalFormat("#.#");//format to 1 decimal place
				   double roundedvalueworkstream = Double.valueOf(oneDigit.format(sumEffrtWrkstram));
	           
				  if(roundedvalueworkstream==0.0|| roundedvalueworkstream==0){
					  ratePerHrTotal =0; 
				  }else{
					   ratePerHrTotal = Math.round(sumCspRates)/roundedvalueworkstream;
					 //ratePerHrTotal = cspratesTotal/effortTotal;
				  }
	           
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
			    map.put("RateHrOnsite", ratePerHrOnsite);
			    map.put("RateHrDDC", 0);
			    map.put("RateHrTotal", ratePerHrTotal);
			    
			
			    
			   
			    ls.add(map);
			    
			    
		   }
			
			 totalEffortOnsiteFrAddServices += totalEffortWorkstreamRates;
			 totalCspOnsiteFrAddServices +=totalSumCspWorkstreamRates;
			 totalMarginOnsiteFrAddServices += totalSumMarginWorkstream;
	         totalRsrOnsiteFrAddServices +=totalSumRsrWorkstreamRates;
			 
			    mapTotal =new HashMap<Object, Object>();
			    mapTotal.put("totalEffortWorkstreamRates",totalEffortWorkstreamRates);
			    mapTotal.put("totalSumCspWorkstreamRates",totalSumCspWorkstreamRates);
			    mapTotal.put("totalSumRsrWorkstreamRates",totalSumRsrWorkstreamRates); 
			    mapTotal.put("totalSumMarginWorkstream",totalSumMarginWorkstream);
			    ls.add(mapTotal);
			mapAddServcEffortDetails.put(workstream.getCode(), ls);
			
			
			mapAddServcEffortDetails.put("workstream", worstreamList);
		}
		OverallTotalOfAllWorkstreamMap = new HashMap<Object, Object>();
		OverallTotalOfAllWorkstreamMap.put("OverallTotalEffortsOnsite",totalEffortOnsiteFrAddServices);
		OverallTotalOfAllWorkstreamMap.put("OverallCspRateOnsite",totalCspOnsiteFrAddServices);
		OverallTotalOfAllWorkstreamMap.put("OverallRsrRateOnsite",totalRsrOnsiteFrAddServices);
		OverallTotalOfAllWorkstreamMap.put("OverallTotalMarginOnsite",totalMarginOnsiteFrAddServices);
		 
		mapAddServcEffortDetails.put("Total", OverallTotalOfAllWorkstreamMap);
		return mapAddServcEffortDetails;
		
	}

	
	
	
	
	
	@Override
	public Map<Object, Object> getProjectEffortsSummary(List<MasterAdminLovs> worstreamList, List<MasterAdminLovs> prototypList,int engId) {

		String sql ="";
		double totalEffortOnsite = 0.0,totalEffortDDC = 0.0,totalCspRateOnsite = 0.0,totalRSRRateOnsite = 0.0,totalCspRateDDC = 0.0,totalRSRRateDDC = 0.0,totalMarginOnsite = 0.0,totalMarginDDC = 0.0;
		 Map<Object, Object> mapAddServcEffortDetails = new HashMap<Object, Object>();
		 Map<Object, Object> mapTotal =null;
		 Map<Object, Object> OverallTotalOfAllWorkstreamMap =null;
		 
		
		for(MasterAdminLovs workstream:worstreamList)  {
			
			double totalSumRsrWorkstreamRates =0.0,totalSumCspWorkstreamRates=0.0,totalSumRsrDDCRates =0.0,totalSumCspDDcRates=0.0,totalEffortWorkstreamRates =0.0,totalEffortDDCRates=0.0,totalSumMarginWorkstream=0.0,totalSumMarginDDC=0.0;
			List<Map<Object, Object>> ls =new ArrayList<Map<Object,Object>> ();
			
			for(MasterAdminLovs prototyp:prototypList){
				Map<Object, Object> map = new HashMap<Object, Object>();
				double EffrtWrkstram =0,EffortDDC=0;
				double RsrRatesworkstream = 0 ,CspRatesworkstream =0,rsrRatesDdc = 0 ,cspRatesDdc =0,ratePerHrOnsite=0,ratePerHrDDC =0,ratePerHrTotal =0,marginOnsite=0.0,marginDDC =0.0 ;
				

				try {
					sql = "SELECT distinct malw.code as workstream ,malp.code as prototyp,sum(effort_onsite) as effort_onsite,sum(effort_ddc) as effort_ddc,"+
				   " sum(round(csp_onsite)) as csp_onsite,sum(round(csp_ddc)) as csp_ddc,sum(round(rsr_onsite)) as rsr_onsite,sum(round(rsr_ddc)) as rsr_ddc,sum(round(margin_onsite)) as margin_onsite,sum(round(margin_ddc)) as margin_ddc "+
							" FROM detailed_summary ds"+
							" JOIN activity_master_data_driver amdd ON ds.activity_id = amdd.id"+
							" JOIN master_admin_lovs malw ON amdd.workstream = malw.id"+
							" JOIN master_admin_lovs malp ON amdd.prototype = malp.id"+ 
							" where engagement_id = "+engId+" and amdd.workstream= "+workstream.getId()+" and amdd.prototype= "+prototyp.getId();
					
					List<DetailSummaryVo> listDetailSummry = jdbcTemplate.query(sql,
   								new BeanPropertyRowMapper<DetailSummaryVo>(
   										DetailSummaryVo.class));
					
					if(listDetailSummry.size()>0){
						EffrtWrkstram = Double.parseDouble(listDetailSummry.get(0).getEffort_onsite());
						EffortDDC = Double.parseDouble(listDetailSummry.get(0).getEffort_ddc());
						
						
						CspRatesworkstream =Double.parseDouble(listDetailSummry.get(0).getCsp_onsite());;
						cspRatesDdc = Double.parseDouble(listDetailSummry.get(0).getCsp_ddc());;
						RsrRatesworkstream = Double.parseDouble(listDetailSummry.get(0).getRsr_onsite());;
						rsrRatesDdc  =Double.parseDouble(listDetailSummry.get(0).getRsr_ddc());;
						
						
						
						 if(EffrtWrkstram == 0){
							  ratePerHrOnsite = 0;
						  }else{
							  ratePerHrOnsite = CspRatesworkstream/EffrtWrkstram;
						  }
						 
						 
						 if(EffortDDC == 0){
							  ratePerHrDDC = 0;
							  System.out.println("if"+EffortDDC+"***"+ratePerHrDDC);
						  }else{
							  
							  ratePerHrDDC = cspRatesDdc/EffortDDC;
							  System.out.println("else"+EffortDDC+"***"+ratePerHrDDC);
						  }
						 
						 
						
						 totalEffortWorkstreamRates += EffrtWrkstram;
						 totalEffortDDCRates += EffortDDC;
						 totalSumCspWorkstreamRates += CspRatesworkstream;
						 totalSumCspDDcRates += cspRatesDdc;
						 totalSumRsrWorkstreamRates += RsrRatesworkstream;
						 totalSumRsrDDCRates += rsrRatesDdc;
						
						
						
						
					}
					
					
				} catch (Exception e) {
					
//					totalEffortWorkstreamRates += EffrtWrkstram;
//					totalEffortDDCRates += EffortDDC;
					
					
				}
					

				  marginOnsite = CspRatesworkstream - RsrRatesworkstream;
				  marginDDC =cspRatesDdc - rsrRatesDdc;
				  
				  totalSumMarginWorkstream += marginOnsite;
				  totalSumMarginDDC +=marginDDC;
				  
				  
				  
				  DecimalFormat oneDigit = new DecimalFormat("#.#");//format to 1 decimal place
				  double roundedvalueDDC = Double.valueOf(oneDigit.format(EffortDDC));
				  double roundedvaluewrkstream = Double.valueOf(oneDigit.format(EffrtWrkstram));
				  System.out.println(roundedvalueDDC+"%%%"+roundedvaluewrkstream);
				  
				  double effortTotal = roundedvalueDDC + roundedvaluewrkstream;
				  if(effortTotal==0.0 ||effortTotal==0){
					  ratePerHrTotal =0; 
				  }else{
					  double cspratesTotal = Math.round(CspRatesworkstream) + Math.round(cspRatesDdc);
					  ratePerHrTotal = cspratesTotal/effortTotal;
				  }
			      //ratePerHrTotal = ratePerHrOnsite + ratePerHrDDC;
				  
			      
			        map.put("workstream",workstream.getCode());
				    map.put("prototype",prototyp.getCode());
				    map.put("sumEffortWorkstream",EffrtWrkstram);
				    map.put("sumEffortDDC",EffortDDC);
				    map.put("sumCSPRates", CspRatesworkstream);
				    map.put("sumCSPRatesDDC",cspRatesDdc);
				    map.put("sumRsrRates", RsrRatesworkstream);
				    map.put("sumRsrRatesDDC",rsrRatesDdc);
				    map.put("marginWorkstream", marginOnsite);
				    map.put("marginDDc", marginDDC);
				    map.put("RateHrOnsite", ratePerHrOnsite);
				    map.put("RateHrDDC", ratePerHrDDC);
				    map.put("RateHrTotal",ratePerHrTotal);
				    
				    
				    System.out.println(workstream.getCode()+"**"+prototyp.getCode()+"**"+EffrtWrkstram+"**"+EffortDDC+"**"+CspRatesworkstream+"**"+cspRatesDdc+"**"+RsrRatesworkstream+"**"+rsrRatesDdc+"**"+marginDDC+"**"+marginOnsite+"**"+ratePerHrOnsite+"**"+ratePerHrDDC+"**"+ratePerHrTotal);
				    System.out.println(map);
				    
				   
				    ls.add(map);
				  
				
				
                    	
                    }
				 totalEffortOnsite += totalEffortWorkstreamRates;
				 totalEffortDDC +=totalEffortDDCRates;
				 totalCspRateOnsite +=totalSumCspWorkstreamRates;
				 totalCspRateDDC +=totalSumCspDDcRates;
				 totalMarginOnsite += totalSumMarginWorkstream;
				 totalMarginDDC += totalSumMarginDDC;
		         totalRSRRateOnsite +=totalSumRsrWorkstreamRates;
		         totalRSRRateDDC +=totalSumRsrDDCRates;
			 
			    mapTotal =new HashMap<Object, Object>();
			    mapTotal.put("totalEffortWorkstreamRates",totalEffortWorkstreamRates);
			    mapTotal.put("totalEffortDDCRates",totalEffortDDCRates);
			    mapTotal.put("totalSumCspWorkstreamRates",totalSumCspWorkstreamRates);
			    mapTotal.put("totalSumCspDDCRates",totalSumCspDDcRates);
			    mapTotal.put("totalSumRsrWorkstreamRates",totalSumRsrWorkstreamRates); 
			    mapTotal.put("totalSumRsrDDCRates",totalSumRsrDDCRates); 
			    mapTotal.put("totalSumMarginWorkstream",totalSumMarginWorkstream);
			    mapTotal.put("totalSumMarginDDC",totalSumMarginDDC);
			    
			    ls.add(mapTotal);
			mapAddServcEffortDetails.put(workstream.getCode(), ls);
			
			
			//mapAddServcEffortDetails.put("workstream", worstreamList);
			
                    
				
		   }
			
			 
		
		OverallTotalOfAllWorkstreamMap = new HashMap<Object, Object>();
		OverallTotalOfAllWorkstreamMap.put("OverallTotalEffortsOnsite",totalEffortOnsite);
		OverallTotalOfAllWorkstreamMap.put("OverallCspRateOnsite",totalCspRateOnsite);
		OverallTotalOfAllWorkstreamMap.put("OverallRsrRateOnsite",totalRSRRateOnsite);
		OverallTotalOfAllWorkstreamMap.put("OverallTotalMarginOnsite",totalMarginOnsite);
		
		OverallTotalOfAllWorkstreamMap.put("OverallTotalEffortsDDC",totalEffortDDC);
		OverallTotalOfAllWorkstreamMap.put("OverallCspRateDDC",totalCspRateDDC);
		OverallTotalOfAllWorkstreamMap.put("OverallRsrRateDDC",totalRSRRateDDC);
		OverallTotalOfAllWorkstreamMap.put("OverallTotalMarginDDC",totalMarginDDC);
		 
		mapAddServcEffortDetails.put("TotalProjectSumEffort", OverallTotalOfAllWorkstreamMap);
		return mapAddServcEffortDetails;
		
	
	}



}