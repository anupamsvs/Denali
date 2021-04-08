package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.ModuleMetaData;
import com.pwc.denali2.estimator.model.StaffdriverData;

public interface staffDriverService {
	List<Map<String, List>> findAllEfforts();
	boolean calculateEffort(String engagement_id);
	boolean copyStaffingDrivers(String engagement_id);
}