package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.DetailedSummary;


public interface DetailedSummaryService {
	boolean calculateEffort(int engagement_id);
	Map<String, List> listAllEfforts(int engagement_id);
	boolean overrideEfforts(int engagement_id,Map<String,Map<String,String>> data);
	Map<String,List> detailedEffortSummary(int engagement_id);
}