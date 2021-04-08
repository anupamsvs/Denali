package com.pwc.denali2.estimator.dao;

import com.pwc.denali2.estimator.model.LockUser;
import com.pwc.denali2.estimator.model.User;

public interface AccountOpsDao {
	User findUserBySsoId(String sso);
	
	boolean checkLock(String userId, String engagementId);

	boolean removeLock(String userId);

	LockUser getLockUser(String engagementId);
}