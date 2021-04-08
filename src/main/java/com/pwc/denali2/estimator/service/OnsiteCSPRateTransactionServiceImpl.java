package com.pwc.denali2.estimator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.OnsiteCSPRateTransactionDao;
import com.pwc.denali2.estimator.model.OnSiteCspRateTransactiondata;
import com.pwc.denali2.estimator.model.OnsiteCspRateTransactionDataVO;

@Service("onsiteCSPRateTransactionService")
@Transactional
public class OnsiteCSPRateTransactionServiceImpl implements OnsiteCSPRateTransactionService{

	@Autowired
	private OnsiteCSPRateTransactionDao onsiteCSPRateTransactionDao;
	
	@Override
	public void copyOnsiteCSPRateMetaData(String engagementId) {
		
		this.onsiteCSPRateTransactionDao.copyOnsiteCSPRateMetaData(engagementId);
	}

	@Override
	public List<OnsiteCspRateTransactionDataVO> findEngagementTransactionData(String engagementId) {
		
		return onsiteCSPRateTransactionDao.findEngagementTransactionData(engagementId);
	}

	@Override
	public void saveOnsiteCspRateTransactionData(OnSiteCspRateTransactiondata data) {
		
		this.onsiteCSPRateTransactionDao.saveOnsiteCspRateTransactionData(data);
	}
	
}
