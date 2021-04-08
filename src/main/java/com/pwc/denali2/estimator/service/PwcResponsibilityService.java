package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.PwcResponsibilityTransactionData;
import com.pwc.denali2.estimator.model.PwcResponsibilityVo;
import com.pwc.denali2.estimator.model.Pwc_Responsibility_Vo;
import com.pwc.denali2.estimator.model.Pwc_responsibilityData;

public interface PwcResponsibilityService {
	List<Pwc_Responsibility_Vo> findAllByEngagementIdForPMO(String id);
	Map<String, Object> update(JSONArray jsonarray, String idparam, String checkstatusY_N);
	List<Pwc_Responsibility_Vo> findAllByEngagementIdForOther(String id,
			Pwc_responsibilityData pwcResponsibility);

}