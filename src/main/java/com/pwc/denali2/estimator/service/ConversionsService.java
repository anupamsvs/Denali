package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.pwc.denali2.estimator.model.BusinessProcess;
import com.pwc.denali2.estimator.model.ConversionDataType;
import com.pwc.denali2.estimator.model.ConversionTransactionData;
import com.pwc.denali2.estimator.model.ConversionVo;
import com.pwc.denali2.estimator.model.ModuleMetaData;

public interface ConversionsService {

	List<ConversionVo> findAllByEngagementId(Integer id,String type,String userflag);
	List<ConversionVo> findAllByEngagementIdForUserCreate(Integer id);
	
	List<BusinessProcess> findAllBusinessProcess();
	List<ModuleMetaData> findModuleMetaDataByBusinessId(Integer id);
	List<ModuleMetaData> findAllModuleMetaData();
	List<ConversionDataType> findAllConversionDataType();
	Map<Object, Object> loadAllConversion(String idparam);
	
	String update(List<ConversionTransactionData> ls, int id);
	void saveComplexityMultiplier(Map<String, Map<String, String>> data, String id);
	
}