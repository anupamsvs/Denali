package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.EngagementAttachmentDao;
import com.pwc.denali2.estimator.dao.ProjectDurationDao;
import com.pwc.denali2.estimator.dao.ProjectManagementDao;
import com.pwc.denali2.estimator.model.ProjectDurationData;

@Service("projectDurationService")
@Transactional
public class ProjectDurationServiceImpl implements ProjectDurationService{
	
	@Autowired
	ProjectDurationDao projectDurationDao;
	
	@Override
	public Map<String,Double> getMaxModuleWeightage(String prjId) {
		// TODO Auto-generated method stub
		return projectDurationDao.getMaxModuleWeightage(prjId);
	}

	@Override
	public Map<String,String> getModuleMultiplier(String Id) {
		// TODO Auto-generated method stub
		return projectDurationDao.getModuleMultiplier(Id);
	}

	@Override
	public double getProjectDurationFactor(String id) {
		// TODO Auto-generated method stub
		return projectDurationDao.getProjectDurationFactor(id);
	}
	
/*	@Override
	public int getprojectImplDuration(String id){
		
		return projectDurationDao.getprojectImplDuration(id);
	}
*/
	@Override
	public double getModuleWeeks(Double moduleFactor, String id) {
		// TODO Auto-generated method stub
		return projectDurationDao.getModuleWeeks(moduleFactor, id);
	}

	@Override
	public Map<String, Object> riceEffortInput(Double weeks,
			String id) {
		// TODO Auto-generated method stub
		return projectDurationDao.riceEffortInput(weeks,id);
	}

	

	@Override
	public void dbupdateOperation(String idparam, String complexity_multiplier,
			String planningWeeks, String resourcesForWeeks,
			String resourcesForplusWeeks) {
		
		projectDurationDao.dbupdateOperation(idparam, complexity_multiplier, planningWeeks, resourcesForWeeks, resourcesForplusWeeks);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dbinsertOperation(String project_id,
			Double mod_cplex_multiplier, Object ProjectDurDbMap,
			Map<String, String> moduleMultiplierMap) {
		// TODO Auto-generated method stub
		projectDurationDao.dbinsertOperation(project_id, mod_cplex_multiplier, ProjectDurDbMap,moduleMultiplierMap);
	}
	
	

	
}