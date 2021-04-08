package com.pwc.denali2.estimator.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.RowSet;

import org.springframework.jdbc.core.BeanPropertyRowMapper;


import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.ProjectDurationData;
import com.pwc.denali2.estimator.model.ProjectDurationData;
import com.pwc.denali2.estimator.dao.ProjectDurationDao;

@Repository
public class ProjectDurationDaoImpl extends AbstractDao<Integer, ProjectDurationData> implements ProjectDurationDao{

	
//	String modproMultiplier="";
//	
//	
	
	@Override	
	public Map<String,Double> getMaxModuleWeightage(String prjId)
	{
		Double maxModuleWeightage=0.0;
		Map<String,Double> MaxModWtgAndCmplxtyFacMap = new HashMap<String, Double>();
		String sqlfetchModuleDta = "SELECT * FROM module_transaction_data where engagement_id = "+prjId +" and is_in_scope_flag = 'Yes'" ;
		
				
		Map<String,String> maxModWtgMap = new HashMap<String, String>(); 
		// moduleTransactionList = new ArrayList<ModuleTransactionData>();
		List<Double> modWeightageList = new ArrayList<Double>();
		double complexityFacDefault,calModuleWeightage =0.0;
		List<Object> modulePropertiesList = new ArrayList<Object>();
		Map<String,List<Object>> moduleModuleMap = new HashMap<String, List<Object>>();
		

		Double complexity_multiplier = 0.10;
		String sql3 = "Select mod_cplex_multiplier from project_duration_transaction_data where engagement_id= "+prjId;
		
		try {
			double mod_cplex_multiplier = jdbcTemplate.queryForObject(sql3, Double.class);
			complexity_multiplier = mod_cplex_multiplier;
		} catch (Exception e) {
			complexity_multiplier =0.10;
		}
		

	
		
		Integer engId;
		Double modDurationFac;
		Float compFactor;
		String modName;
		
		List<ModuleTransactionData> moduleTransactionList = jdbcTemplate.query(sqlfetchModuleDta,
				new BeanPropertyRowMapper<ModuleTransactionData>(
						ModuleTransactionData.class));
		
		for(ModuleTransactionData element : moduleTransactionList){
			
			engId = element.getEngagement_id();
			
			
			modDurationFac = element.getModule_duration_factor();
			compFactor = element.getComplexity_factor();
			
			modName= element.getModule_name();
			
			calModuleWeightage = modDurationFac+((modDurationFac)*(compFactor-1)*complexity_multiplier);
			
			//System.out.println("modDurationFac+((modDurationFac)*(compFactor-1)*complexity_multiplier)= "+ modDurationFac+ "+" + "((" + modDurationFac +")*("+compFactor+"-1)*" +complexity_multiplier+")");
			modWeightageList.add(calModuleWeightage);
			
			//System.out.println(calModuleWeightage);
			
		}
		 System.out.println(modWeightageList);
		 
		maxModuleWeightage =Collections.max(modWeightageList);
		System.out.println("max "+maxModuleWeightage);
		
		MaxModWtgAndCmplxtyFacMap.put("max_Module_Weightage",maxModuleWeightage);
		MaxModWtgAndCmplxtyFacMap.put("complexity_multiplier", complexity_multiplier);
		
		return MaxModWtgAndCmplxtyFacMap;
	}
	
	
	public Map<String,String> getModuleMultiplier(String Id)
	
	{
		Map<String,String> ModuleMultiplierMap = new HashMap<String, String>();
		List<MasterAdminLovs> masterAdminLovsList = new ArrayList<MasterAdminLovs>();
		String sql1;
		double moduleMultiplier = 0.0, processAreaMultiplier =0.0;
		
		
		
		sql1= "select * from master_admin_lovs where type= 'Duration Multipliers'" ;
		
		masterAdminLovsList = jdbcTemplate.query(sql1,
				new BeanPropertyRowMapper<MasterAdminLovs>(
						MasterAdminLovs.class));
		
		//Iterator<MasterAdminLovs> itr = masterAdminLovsList.iterator();
		
		Double MLM = 0.0,MHM=0.0,PALM=0.0,PAHM=0.0,MaxM = 0.0,MaxPA=0.0;
		for(MasterAdminLovs element : masterAdminLovsList)
		{
			if(element.getCode().equalsIgnoreCase("Module Multiplier Lower"))
			{
				MLM = Double.parseDouble(element.getBu_factor());
			}
			if(element.getCode().equalsIgnoreCase("Module Multiplier Upper"))
			{
				MHM = Double.parseDouble(element.getBu_factor());
			}
			if(element.getCode().equalsIgnoreCase("Process Area Lower Multiplier"))
			{
				PALM = Double.parseDouble(element.getBu_factor());
			}
			if(element.getCode().equalsIgnoreCase("Process Area Upper Multiplier"))
			{
				PAHM = Double.parseDouble(element.getBu_factor());
			}
			if(element.getCode().equalsIgnoreCase("Max Modules"))
			{
				MaxM = Double.parseDouble(element.getBu_factor());
				
			}
			if(element.getCode().equalsIgnoreCase("Max Process Areas"))
			{
				MaxPA = Double.parseDouble(element.getBu_factor());
			}
		}
		
		String sql2 = "select count(distinct module_name) from module_transaction_data where engagement_id=" + Id + " and is_in_scope_flag = 'YES'";
		int numOfModules = jdbcTemplate.queryForObject(sql2, Integer.class);
		
		String sql3 = "select count(distinct business_process_id) from module_transaction_data where engagement_id = "+ Id + " and is_in_scope_flag= 'Yes'";
		int numInScopePA = jdbcTemplate.queryForObject(sql3, Integer.class);
		
		moduleMultiplier =  (MLM + ((numOfModules-1)*(MHM - MLM)/(MaxM-1)));

		ModuleMultiplierMap.put("moduleMultiplier", (Math.round(moduleMultiplier *100.0)/100.0)+"");
		ModuleMultiplierMap.put("numOfModules", numOfModules+"");
		
		
		
		processAreaMultiplier = (PALM + ((numInScopePA-1)*(PAHM-PALM)/(MaxPA-1)));
		ModuleMultiplierMap.put("numInScopePA", numInScopePA+"");
		ModuleMultiplierMap.put("processAreaMultiplier", (Math.round(processAreaMultiplier *100.0)/100.0)+"");
		
		
		return ModuleMultiplierMap;
	}
	
	public double getProjectDurationFactor(String engId)
	{
		double projDurationFactor = 0.0;
		
		Map<String, String> ModuleMultiplierMap = getModuleMultiplier(engId);
		Map<String, Double> getmaxModuleWeightagemap = getMaxModuleWeightage(engId);
		
		double MM = Double.parseDouble(ModuleMultiplierMap.get("moduleMultiplier"));
		double PAM = Double.parseDouble(ModuleMultiplierMap.get("processAreaMultiplier"));
		double maxModuleWeightage = getmaxModuleWeightagemap.get("max_Module_Weightage");
		
		projDurationFactor = MM*PAM*maxModuleWeightage;
		
		return Math.round(projDurationFactor*100.0)/100.0;
	}
	

	public double getModuleWeeks(Double moduleFactor, String id)
	{
		double weeks =0.0;
		String sql1 = "SELECT bu_factor FROM master_admin_lovs where lower(type)='duration multipliers' and lower(code) ='standard duration factor'";
		//.queryForRowSet(sql1).getDouble("");
		try {
			 String result = jdbcTemplate.queryForObject(sql1,String.class);
			 double stdDurationFactor= Double.parseDouble(result.split("\\|")[1]);

			 weeks = moduleFactor* stdDurationFactor;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return weeks;
	}
	
	public Map<String,Object> riceEffortInput(Double weeks ,String id){
		double value =0.0;
		Map<String,Object> AllDtaProjectDurationDetails = new HashMap<String, Object>();
		Map<String,Object> riceEffortInputMap = new HashMap<String, Object>();
		String sql1 = "SELECT bu_factor FROM master_admin_lovs where code = 'P2-Design & Config' and type='Prototype Split'";
		String prototype2 = jdbcTemplate.queryForObject(sql1, String.class);
		Double p2val = Double.parseDouble(prototype2);
		
		Double p2PrototypeWeeks = p2val*weeks;
		
		
		//String sql2 = "select planning_weeks,resources_for_planned_weeks,resources_for_plus_weeks from project_duration_transaction_data where engagement_id = " + id;

		String sql2 = "select * from project_duration_transaction_data where engagement_id = " + id;
		
		List<ProjectDurationData>  ProjDurationDbDta = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<ProjectDurationData>(
				ProjectDurationData.class));
		
		//SqlRowSet ricefactorListProjDuration = jdbcTemplate.queryForRowSet(sql2);
		
		Double resourcesFor8Weeks= 0.0;
		Double resourcesFor8plusWeeks= 0.0;
		Double planningWeeks = 0.0;
		
		Double MaxRiceEffortHours= 0.0;
		Double numberOfResources =0.0;
		Double maxRiceEffortHours = 0.0;
		
		if(ProjDurationDbDta.size() ==0)
		{
			String sql3 = "SELECT * FROM master_admin_lovs where lower(type)= 'rie factor'";
			List<MasterAdminLovs>  ricefactorListMaster = jdbcTemplate.query(sql3, new BeanPropertyRowMapper<MasterAdminLovs>(
					MasterAdminLovs.class));
			
			
			
			for(MasterAdminLovs element : ricefactorListMaster)
			{
				if(element.getCode().equalsIgnoreCase("Resource 8 Weeks"))
				{
					 resourcesFor8Weeks = Double.parseDouble(element.getBu_factor());
					 riceEffortInputMap.put("resourcesForWeeks", resourcesFor8Weeks);
				}
				if(element.getCode().equalsIgnoreCase("Resource 8 plus Weeks"))
				{
					resourcesFor8plusWeeks = Double.parseDouble(element.getBu_factor());
					riceEffortInputMap.put("resourcesForplusWeeks", resourcesFor8plusWeeks);
				}
				if(element.getCode().equalsIgnoreCase("Planning Weeks"))
				{
					planningWeeks = Double.parseDouble(element.getBu_factor());
					riceEffortInputMap.put("planningWeeks", planningWeeks);
				}
			}
			
			if(p2PrototypeWeeks<=planningWeeks)
			{
				numberOfResources = resourcesFor8Weeks;
				maxRiceEffortHours = numberOfResources*planningWeeks*40;
				
				//maxRiceEffortHours = (double) Math.round(MaxRiceEffortHours);
			}
			else
			{
				Double riceHoursFor8Weeks = resourcesFor8Weeks*planningWeeks*40;
				Double riceRemainingWeeks = (double) Math.round(p2PrototypeWeeks - planningWeeks);
				Double riceHoursForRemainingWeeks = resourcesFor8plusWeeks * riceRemainingWeeks * 40 ;
				
				maxRiceEffortHours = riceHoursFor8Weeks + riceHoursForRemainingWeeks ;
			}
		}
		
		else
		{
				planningWeeks = (ProjDurationDbDta.get(0).getPlanning_weeks()) ;
				riceEffortInputMap.put("planningWeeks", planningWeeks);
				
				resourcesFor8Weeks = (ProjDurationDbDta.get(0).getResources_for_planned_weeks()) ;
				riceEffortInputMap.put("resourcesForWeeks", resourcesFor8Weeks);
				
				resourcesFor8plusWeeks = (ProjDurationDbDta.get(0).getResources_for_plus_weeks()) ;
				riceEffortInputMap.put("resourcesForplusWeeks", resourcesFor8plusWeeks);
			try {
				if(p2PrototypeWeeks<=planningWeeks)
				{
					numberOfResources = resourcesFor8Weeks;
					maxRiceEffortHours = numberOfResources*planningWeeks*40;
					
					//maxRiceEffortHours = (double) Math.round(MaxRiceEffortHours);
				}
				else
				{
					Double riceHoursFor8Weeks = planningWeeks*resourcesFor8Weeks*40;
					Double riceRemainingWeeks = (double) Math.round(p2PrototypeWeeks - planningWeeks);
					Double riceHoursForRemainingWeeks = resourcesFor8plusWeeks * riceRemainingWeeks * 40 ;
					
					maxRiceEffortHours = riceHoursFor8Weeks + riceHoursForRemainingWeeks;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		
		
		double totalCountRiceConversion =getTotalCountRiceConversion(id);
		Double riceExtraHours = totalCountRiceConversion - maxRiceEffortHours;
		if(riceExtraHours < 0.0)
		{
			riceExtraHours = 0.0;
		}
		
		Double riceExtraWeeks = riceExtraHours/(resourcesFor8plusWeeks*40);
		
		if(riceExtraWeeks < 0.0)
		{
			riceExtraWeeks = 0.0;
		}
		
		Double projectImplDuration = weeks + riceExtraWeeks;
		
		double projectDurationOverride = 0;
		String sql4 = "select * from engagement where id = " + id;
		List<Engagement> engagementList = jdbcTemplate.query(sql4, 
				new BeanPropertyRowMapper<Engagement>(
				Engagement.class));
		try {
			projectDurationOverride = engagementList.get(0).getProject_duration();
		} catch (Exception e) {
			projectDurationOverride =0;
			// TODO: handle exception
		}
		double pgLiveDuration =0;
		try {
			pgLiveDuration = engagementList.get(0).getPost_go_live_support_duration();
		} catch (Exception e) {
			pgLiveDuration =0;
		}
		
		Double durationWeeks = 0.0;
		Double totalDurationWeeks = 0.0;
		
		if(StringUtils.isEmpty(projectDurationOverride) || projectDurationOverride== 0)
		{
			durationWeeks = projectImplDuration;
		}
		else
		{
			durationWeeks = projectDurationOverride;
		}
		
		totalDurationWeeks = (durationWeeks + pgLiveDuration);
		
		
		String sql5 = "select * from master_admin_lovs where type= 'Prototype Split'";
		
		List<MasterAdminLovs> prototypeList = jdbcTemplate.query(sql5,
				new BeanPropertyRowMapper<MasterAdminLovs>(
						MasterAdminLovs.class));
		
		Double prototypSplit=0.0,sumProtypVlue = 0.0;
		
		int p2_Id = 0;
		Map<String, Double> value_prototypId =  new HashMap<String, Double>();
		
		
		System.out.println(durationWeeks);
		for(MasterAdminLovs element : prototypeList)
		{
			 String sql ="SELECT id FROM master_admin_lovs where code ='"+element.getCode()+"' and type='Prototype'";
			 int protypId =jdbcTemplate.queryForObject(sql, Integer.class);
			 
			if(!element.getCode().equalsIgnoreCase("P2-Design & Config"))
			{
				 prototypSplit =Double.parseDouble(element.getBu_factor());
				 
				 value = (double) Math.round(prototypSplit*durationWeeks);
				 sumProtypVlue +=value;
				 
				 value_prototypId.put(protypId+"", value);
				
			}
			
			if(element.getCode().equalsIgnoreCase("P2-Design & Config"))
			{
				p2_Id = protypId;
			}

		}
		

		 value = Math.round(durationWeeks)-(sumProtypVlue);
		 value_prototypId.put(p2_Id+"", value);
		
		
		
		riceEffortInputMap.put("total_rice_hours", Math.round(totalCountRiceConversion * 100.0)/100.0);
		riceEffortInputMap.put("p2_Prototype_Weeks", Math.round(p2PrototypeWeeks *100.0)/100.0);
		riceEffortInputMap.put("max_Rice_Effort_Hours",Math.round(maxRiceEffortHours * 100.0)/100.0);
		riceEffortInputMap.put("rice_Extra_Hours", Math.round(riceExtraHours* 100.0)/100.0);
		riceEffortInputMap.put("rice_Extra_Weeks", Math.round(riceExtraWeeks* 100.0)/100.0);
		riceEffortInputMap.put("project_Impl_Duration", Math.round(projectImplDuration));
		riceEffortInputMap.put("durationWeeks", Math.round(durationWeeks));
		riceEffortInputMap.put("pgLive_Duration", Math.round(pgLiveDuration* 100.0)/100.0);
		riceEffortInputMap.put("total_Duration_Weeks",Math.round(totalDurationWeeks* 100.0)/100.0);
		riceEffortInputMap.put("pgLive_Duration", Math.round(pgLiveDuration* 100.0)/100.0);
		riceEffortInputMap.put("project_Duration_Override",Math.round(projectDurationOverride* 100.0)/100.0);	
		
		
		AllDtaProjectDurationDetails.put("allDtaProjectDuration", riceEffortInputMap);
		AllDtaProjectDurationDetails.put("prototypSplitValue", value_prototypId);
		
		return AllDtaProjectDurationDetails;
	}

	public void dbinsertOperation(String project_id,Double mod_cplex_multiplier,Object ProjectDurDbMap,Map<String, String> moduleMultiplierMap)
	{
		Map<String,Object> map =(Map<String,Object>)ProjectDurDbMap;
		Map<String,Object> projectDurationMapForDb =(Map<String,Object>)map.get("allDtaProjectDuration");
		Map<String,Object> projectDurationProtypSplitDtaForDb=(Map<String,Object>)map.get("prototypSplitValue");
		String sql = "Select * from project_duration_transaction_data where engagement_id = "+project_id;
		
		List<ProjectDurationData>  ProjDurationDbDta = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ProjectDurationData>(
				ProjectDurationData.class));
		
		// moduleMultiplier processAreaMultiplier
		if(ProjDurationDbDta.size() ==0){
			
			 sql = "insert into project_duration_transaction_data (engagement_id,process_area_multiplier,module_multiplier,mod_cplex_multiplier,module_weeks,overide_weeks,rice_extra_weeks,proj_impl_duration,"
					+ "total_proj_duration,planning_weeks,resources_for_planned_weeks,resources_for_plus_weeks) "
					+ "values('"+project_id+"','"+moduleMultiplierMap.get("moduleMultiplier")+"','"+moduleMultiplierMap.get("processAreaMultiplier")+"',"+mod_cplex_multiplier+","+projectDurationMapForDb.get("p2_Prototype_Weeks")+","+projectDurationMapForDb.get("project_Duration_Override")+","+projectDurationMapForDb.get("rice_Extra_Weeks")+","+projectDurationMapForDb.get("project_Impl_Duration")+","+projectDurationMapForDb.get("total_Duration_Weeks")+","+projectDurationMapForDb.get("planningWeeks")+","+projectDurationMapForDb.get("resourcesForWeeks")+","+projectDurationMapForDb.get("resourcesForplusWeeks")+")";
//             System.out.println(sql);
			 jdbcTemplate.update(sql);


	       
		}else{
			
			sql = "UPDATE project_duration_transaction_data SET process_area_multiplier='"+moduleMultiplierMap.get("moduleMultiplier")+"',module_multiplier='"+moduleMultiplierMap.get("processAreaMultiplier")+"',mod_cplex_multiplier= "+mod_cplex_multiplier+", module_weeks= "+projectDurationMapForDb.get("p2_Prototype_Weeks")+" , overide_weeks="+projectDurationMapForDb.get("project_Duration_Override")+", rice_extra_weeks="+projectDurationMapForDb.get("rice_Extra_Weeks")+", proj_impl_duration="+projectDurationMapForDb.get("project_Impl_Duration")+", total_proj_duration="+projectDurationMapForDb.get("total_Duration_Weeks")+", planning_weeks="+projectDurationMapForDb.get("planningWeeks")+", resources_for_planned_weeks="+projectDurationMapForDb.get("resourcesForWeeks")+", resources_for_plus_weeks= "+projectDurationMapForDb.get("resourcesForplusWeeks")+" WHERE engagement_id ="+project_id;        
			jdbcTemplate.update(sql);
		
		}
		
		
		Iterator itr = projectDurationProtypSplitDtaForDb.entrySet().iterator();
	    while (itr.hasNext()) {
	        Map.Entry pair = (Map.Entry)itr.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        
	        sql="INSERT INTO project_duration_transaction_join_table (`prototyp_id`, `engagement_id`, `value`) VALUES ('"+pair.getKey()+"', '"+project_id+"', '"+pair.getValue()+"') "+
	             "ON DUPLICATE KEY UPDATE value ='"+pair.getValue()+"'";
	        System.out.println(sql);
	        jdbcTemplate.update(sql);
	        
	    }
		
		
		
		

	
	}

	public void dbupdateOperation(String idparam, String complexity_multiplier,
			String planningWeeks, String resourcesForWeeks,
			String resourcesForplusWeeks)
	{
		String sqlUpdate = "update project_duration_transaction_data set mod_cplex_multiplier = "+complexity_multiplier+",planning_weeks = "+planningWeeks+ ",resources_for_planned_weeks = "+ resourcesForWeeks + ",resources_for_plus_weeks = "+ resourcesForplusWeeks
				+" where engagement_id = "+ idparam;
		
		jdbcTemplate.update(sqlUpdate);
	}
	
	public Double getTotalCountRiceConversion(String id){
		double sumRiceConversion=0.0,sumRice=0.0,sumConversion=0.0;
		String sql ="";
		
        try {
        	 sql ="SELECT sum(rice_transaction_join_table.effort) FROM rice_transaction_data,rice_transaction_join_table where rice_transaction_data.id = rice_transaction_join_table.rice_data_id and rice_transaction_data.engagement_id ='"+id+"' " ;
    		
        	 sumRice =jdbcTemplate.queryForObject(sql, Double.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        try {
        	sql =" SELECT sum(conversion_transaction_join_table.effort) FROM conversion_transaction_data,conversion_transaction_join_table where conversion_transaction_data.id = conversion_transaction_join_table.conversion_data_id and lower(conversion_transaction_data.final_scope_flag)='yes' and conversion_transaction_data.engagement_id ='"+id+"'";  
        	 sumConversion = jdbcTemplate.queryForObject(sql, Double.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
        sumRiceConversion=sumRice + sumConversion;
		return sumRiceConversion;
	}
	
	public Map<String,Object> getProtypSplitValue(int engId){
		
		Map<String,Object> ProjctDurPrototypSplitDetailMap = new HashMap<String,Object>();
		double sum =0;
		Map<String,String> mapProtypSplit = new HashMap<String, String>();
		
		
		String sql ="select prototyp_id as id ,value from project_duration_transaction_join_table where engagement_id = "+engId;
		  System.out.println(sql);
		try {
			List<Map<String, Object>> listProtypSplit_value = jdbcTemplate.queryForList(sql);
			for(Map<String, Object> map :listProtypSplit_value){
				mapProtypSplit.put(map.get("id").toString(), map.get("value").toString());
				sum += Double.parseDouble(map.get("value").toString());
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		ProjctDurPrototypSplitDetailMap.put("sumProtypSplit", sum+"");
		ProjctDurPrototypSplitDetailMap.put("mapProtypSplit", mapProtypSplit);
		
		return ProjctDurPrototypSplitDetailMap;
	}
	
	
	
	
	
	
	


}