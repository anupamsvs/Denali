package com.pwc.denali2.estimator.dao;

import java.util.List;

import com.pwc.denali2.estimator.model.ActivityService;

public interface ActivityDao {

	List<ActivityService> findByActivityid(Integer id);
}
