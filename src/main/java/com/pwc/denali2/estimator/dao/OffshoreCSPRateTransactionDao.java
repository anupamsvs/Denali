package com.pwc.denali2.estimator.dao;

import java.util.List;

import com.pwc.denali2.estimator.model.OffshoreCspRateTransactiondata;
import com.pwc.denali2.estimator.model.OffshoreCspRateTransactiondataVO;

public interface OffshoreCSPRateTransactionDao {
	
	void copyOffshoreCSPRateMetaData(String engagementId);
	
	List<OffshoreCspRateTransactiondataVO> findEngagementTransactionData(String engagementId);
	
	void saveOffshoreCspRateTransactionData(OffshoreCspRateTransactiondata data);
}
