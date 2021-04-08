package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.pwc.denali2.estimator.model.AdditionalService;

public interface AdditionalDao {

	Map<Object, Object> findAllByEngagementId(Integer id);
	//void save(List<Map<Object, Object>> AddservcesMapList);
	void deleteByID(Integer id);
	void save(JSONObject jsonarr, String id);
	void copyAddServices(int engagementId, String status, String copyfromengId);
	
}
