package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.PwcResponsibilityTransactionData;
import com.pwc.denali2.estimator.model.PwcResponsibilityVo;
import com.pwc.denali2.estimator.model.Pwc_Responsibility_Vo;
import com.pwc.denali2.estimator.model.Pwc_responsibilityData;

public interface PwcResponsibilityDao {

	List<Pwc_Responsibility_Vo> findAllByEngagementIdForOther(String id, Pwc_responsibilityData pwcResponsibility);
	List<Pwc_Responsibility_Vo> findAllByEngagementIdForPMO(String id);
	Map<String, Object> update(JSONArray jsonarray, String idparam, String checkstatusY_N);
//	void deleteByID(Integer id);

	Pwc_Responsibility_Vo findResponsibilityByEngagement(String engagement_id,
			String activity_id);
}
