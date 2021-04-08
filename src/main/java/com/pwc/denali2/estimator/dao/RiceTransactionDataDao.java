package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.RiceTransactionData;

public interface RiceTransactionDataDao {

	List<RiceTransactionData> findAllByEngagementId(Integer id);
	List<Integer> save(RiceTransactionData riceTransactionData, List<Integer> idList);
	void deleteByID(List<Integer> idList, Integer id);
	Map<Object, Object> findAll(String idparam);

	void saveComplexityMultiplier(Map<String, Map<String, String>> data,String type);

}
