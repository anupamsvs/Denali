package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.MasterAdminLovs;

public interface SummaryEffortsDao {

	Map<String, Object> getSummaryEffortForEngId(int engId);
	Map<Object, Object> getProjectEffortsSummary(List<MasterAdminLovs> worstreamList,
			List<MasterAdminLovs> prototypList,int engId);
	Map<Object, Object> getAdditionalEffortsSummary(
			List<MasterAdminLovs> worstreamList,
			List<MasterAdminLovs> prototypList,int engId);

}
