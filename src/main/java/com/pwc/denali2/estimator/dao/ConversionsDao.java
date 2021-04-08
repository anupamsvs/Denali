package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.ConversionTransactionData;
import com.pwc.denali2.estimator.model.ConversionVo;

public interface ConversionsDao {

	List<ConversionVo> findAllByEngagementId(Integer id,String type,String userflag);
	List<ConversionVo> findAllByEngagementIdForUserCreate(Integer id);
	List<String> update(ConversionTransactionData conversionTransactionData, int id, List<String> dupConversionName);
	void deleteByEngagementId(Integer id);
	Map<Object, Object> loadAllConversion(String idparam);
	void saveComplexityMultiplier(Map<String, Map<String, String>> data, String type);
	void initComplexity(String engagement_id);
	boolean getDuplicateStatus(String strInConversionName);
}
