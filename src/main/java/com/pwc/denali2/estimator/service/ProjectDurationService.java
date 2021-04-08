package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

public interface ProjectDurationService {

	public Map<String,Double> getMaxModuleWeightage(String prjId);
	public Map<String,String> getModuleMultiplier(String idparam);
	
	public double getProjectDurationFactor(String idparam);
	//public int getprojectImplDuration(String id);
	public double getModuleWeeks(Double moduleFactor, String id);
	public Map<String,Object> riceEffortInput(Double weeks , String id);
	public void dbinsertOperation(String project_id,Double mod_cplex_multiplier,Object ProjectDurDbMap, Map<String, String> moduleMultiplierMap);
	public void dbupdateOperation(String idparam, String complexity_multiplier,
			String planningWeeks, String resourcesForWeeks,
			String resourcesForplusWeeks);
	
}
