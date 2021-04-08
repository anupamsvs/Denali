package com.pwc.denali2.estimator.dao;

import java.util.List;

import com.pwc.denali2.estimator.model.BusinessProcess;

public interface BusinessProcessDao {

	List<BusinessProcess> findAll();

}
