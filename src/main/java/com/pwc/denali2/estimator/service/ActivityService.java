package com.pwc.denali2.estimator.service;

import com.pwc.denali2.estimator.model.User;

public interface ActivityService {
	User findAllByEngagementId(String sso);
}