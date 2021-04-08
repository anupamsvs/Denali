package com.pwc.denali2.estimator.dao;

import java.util.List;

import com.pwc.denali2.estimator.model.PaaSAppType;

public interface PaaSAppsTypeDao {

	List<PaaSAppType> findAll();

}
