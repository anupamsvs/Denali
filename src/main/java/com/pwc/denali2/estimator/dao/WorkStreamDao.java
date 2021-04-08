package com.pwc.denali2.estimator.dao;

import java.util.List;

import com.pwc.denali2.estimator.model.WorkStream;

public interface WorkStreamDao {

	List<WorkStream> findAll();

}
