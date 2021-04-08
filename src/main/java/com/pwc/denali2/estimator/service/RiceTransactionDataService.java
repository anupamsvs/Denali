package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.ClientComplexity;
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.PaaSAppType;
import com.pwc.denali2.estimator.model.RiceTransactionData;
import com.pwc.denali2.estimator.model.RiceType;

public interface RiceTransactionDataService {

	
	List<PaaSAppType> findAllPaaSAppType();
	List<ClientComplexity> findComplexity();
	List<RiceTransactionData> findRiceTransactionDataByEngagementId(Integer id);
	void save(List<RiceTransactionData> as);
	List<ModuleTransactionData> findModuleDataByEngagementId(Integer id);
	
	Map<Object, Object> loadAllRice(String idparam);
	
	void saveComplexityMultiplier(Map<String, Map<String, String>> data, String type);

}