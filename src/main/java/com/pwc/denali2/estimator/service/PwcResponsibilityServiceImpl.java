package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.PwcResponsibilityDao;
import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.PwcResponsibilityTransactionData;
import com.pwc.denali2.estimator.model.PwcResponsibilityVo;
import com.pwc.denali2.estimator.model.Pwc_Responsibility_Vo;
import com.pwc.denali2.estimator.model.Pwc_responsibilityData;

@Service("pwcResponsibilityService")
@Transactional
public class PwcResponsibilityServiceImpl implements PwcResponsibilityService {
	@Autowired
	private PwcResponsibilityDao pwcResponsibilitydao;

	@Override
	public List<Pwc_Responsibility_Vo> findAllByEngagementIdForOther(String id,Pwc_responsibilityData pwcResponsibility) {
		return pwcResponsibilitydao.findAllByEngagementIdForOther(id,pwcResponsibility);
	}


	@Override
	public List<Pwc_Responsibility_Vo> findAllByEngagementIdForPMO(String id) {
		return pwcResponsibilitydao.findAllByEngagementIdForPMO(id);
	}


	@Override
	public Map<String,Object> update(JSONArray jsonarray, String idparam,
			String checkstatusY_N) {
		return pwcResponsibilitydao.update(jsonarray, idparam,checkstatusY_N);
		
		
	}

	
}