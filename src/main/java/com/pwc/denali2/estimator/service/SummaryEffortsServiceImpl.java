package com.pwc.denali2.estimator.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.SummaryEffortsDao;

@Service("summaryEffortsService")
@Transactional
public class SummaryEffortsServiceImpl implements SummaryEffortsService{

	
	@Autowired
	SummaryEffortsDao summaryEffortsDao;
	
	@Override
	public Map<String, Object> getSummaryEffortForEngId(int engId) {
		// TODO Auto-generated method stub
		return summaryEffortsDao.getSummaryEffortForEngId(engId);
	}

}
