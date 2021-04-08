package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.ModuleTransactionActivity;
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.ModuleTransactionDataVO;
import com.pwc.denali2.estimator.model.ProjectUnit;

public interface ModuleTransactionDataService {
	
	void copyMetaDataForEngagement(String engagementId);
	
	List<ModuleTransactionData> findEngagementTransactionData(String engagementId);
	
	void saveModuleTransactionData(ModuleTransactionData moduleTransactionData);

	List<ActivityManagementData> getActivities();


	Map<String, Object> calculateAct(Map<String,ProjectUnit> AllUnits,ActivityManagementData activity,
			String unit, ModuleTransactionActivity module_trans,
			Engagement engagement, Map<String, Double> dependantData);

}
