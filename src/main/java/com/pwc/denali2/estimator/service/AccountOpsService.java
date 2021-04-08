package com.pwc.denali2.estimator.service;

import com.pwc.denali2.estimator.model.LockUser;
import com.pwc.denali2.estimator.model.User;

public interface AccountOpsService {
	User findUserBySsoId(String sso);

	User findUserBySsoIdWithoutAOP(String ssoId);
	
	
	boolean checkLock(String UserId,String engagementId);
	boolean removeLock(String userId);

	LockUser getLockUser(String engagementId);
}