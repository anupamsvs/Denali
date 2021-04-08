package com.pwc.denali2.estimator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.OffshoreCSPRateTransactionDao;
import com.pwc.denali2.estimator.model.OffshoreCspRateTransactiondata;
import com.pwc.denali2.estimator.model.OffshoreCspRateTransactiondataVO;

@Service("offshoreCSPRateTransactionService")
@Transactional
public class OffshoreCSPRateTransactionServiceImpl implements OffshoreCSPRateTransactionService{

	@Autowired
	private OffshoreCSPRateTransactionDao offshoreCSPRateTransactionDao;
	
	
	@Override
	public void copyOffshoreCSPRateMetaData(String engagementId) {
		this.offshoreCSPRateTransactionDao.copyOffshoreCSPRateMetaData(engagementId);
	}


	@Override
	public List<OffshoreCspRateTransactiondataVO> findEngagementTransactionData(String engagementId) {
		
		return offshoreCSPRateTransactionDao.findEngagementTransactionData(engagementId);
	}


	@Override
	public void saveOffshoreCspRateTransactionData(OffshoreCspRateTransactiondata data) {

		this.offshoreCSPRateTransactionDao.saveOffshoreCspRateTransactionData(data);
	}

}
