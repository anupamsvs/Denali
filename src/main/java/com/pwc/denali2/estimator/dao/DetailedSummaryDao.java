package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.DetailedSummary;



public interface DetailedSummaryDao {
	boolean save(List<DetailedSummary> summaryList);
	
	boolean saveByQuery(String query);
	Map<String,List> listDetailedEffort(int engagement_id);
	Map<String, List> list(int engagement_id);
	boolean overrideEfforts(int engagement_id,Map<String,Map<String,String>> data);

	Map<String,List> listDetailedEffortGroups(int engagement_id);

	Map<String, List<DetailedSummary>> listModel(int engagement_id);

	Map<String, List> listDetailedEffortExport(int engagement_id);
}
