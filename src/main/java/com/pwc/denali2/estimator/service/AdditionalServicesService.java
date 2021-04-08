package com.pwc.denali2.estimator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.pwc.denali2.estimator.model.AdditionalService;
import com.pwc.denali2.estimator.model.ServiceOwner;
import com.pwc.denali2.estimator.model.WorkStream;

public interface AdditionalServicesService {

	List<WorkStream> findAllWorkStream();
	List<ServiceOwner> findAllServiceOwner();
	Map<Object, Object> findAdditionalServiceByEngagementId(Integer id);
	
	void save(JSONObject jsonarr, String id);
	Map<Object, Object> findAdditionalServiceEffortData(Integer id);

}