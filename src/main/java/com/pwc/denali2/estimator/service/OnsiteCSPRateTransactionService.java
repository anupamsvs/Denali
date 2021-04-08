package com.pwc.denali2.estimator.service;

import java.util.List;

import com.pwc.denali2.estimator.model.OnSiteCspRateTransactiondata;
import com.pwc.denali2.estimator.model.OnsiteCspRateTransactionDataVO;

public interface OnsiteCSPRateTransactionService {
	
	void copyOnsiteCSPRateMetaData(String engagementId);
	
	List<OnsiteCspRateTransactionDataVO> findEngagementTransactionData(String engagementId);
	
	void saveOnsiteCspRateTransactionData(OnSiteCspRateTransactiondata data);
}
