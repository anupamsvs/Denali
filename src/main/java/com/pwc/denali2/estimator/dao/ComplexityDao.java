package com.pwc.denali2.estimator.dao;

import java.util.List;

import com.pwc.denali2.estimator.model.ClientComplexity;

public interface ComplexityDao {

	List<ClientComplexity> findAll();

}
