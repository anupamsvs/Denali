package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.ModuleTransactionActivity;
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.ModuleTransactionDataVO;

public interface ModuleTransactionDataDao {

	List<ModuleTransactionData> findByEngagementId(Integer id);
	
	void copyMetaDataForEngagment(String engagementId);
	
	List<ModuleTransactionData> findEngagementTransactionData(String engagementId);

    void saveModuleTransactionData(ModuleTransactionData moduleTransactionData);

	List<ActivityManagementData> getAllActivities();

	ActivityManagementData findDependantActData(String activity_id);

//	void setActValues(Double ddc, Double onsite, String module_transaction_id,
//			String activity_id,String explain);

	List<ModuleTransactionActivity> findModuleTransactionActivityData(
			String engagementId);

	Double getSecondaryDriver(int moduleId,String engagementId, String activity_id,
			String driver);

	List actValues(String engagementId);

	void setActValues(List<Map<String, Object>> list_op);

	void checkForCloudOffering(Engagement engagement,
			String prevCloudOffering, String currentCloudOffering);

	
}
