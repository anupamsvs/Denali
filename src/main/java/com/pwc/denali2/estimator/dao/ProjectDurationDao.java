package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.ProjectDurationData;

public interface ProjectDurationDao {

	public Map<String,Double> getMaxModuleWeightage(String prjId);
	public Map<String,String> getModuleMultiplier(String Id);
	
	public double getProjectDurationFactor(String id);
	//public int getprojectImplDuration(String id);
	public double getModuleWeeks(Double moduleFactor, String id);
	public Map<String,Object> riceEffortInput(Double weeks ,String id);
	
	public void dbinsertOperation(String project_id,Double mod_cplex_multiplier,Object module_weeks, Map<String, String> moduleMultiplierMap);
	
	public void dbupdateOperation(String idparam, String complexity_multiplier,
			String planningWeeks, String resourcesForWeeks,
			String resourcesForplusWeeks);
	
}
