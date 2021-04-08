package com.pwc.denali2.estimator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.aop.CheckUserValidAction;
import com.pwc.denali2.estimator.dao.AccountOpsDao;
import com.pwc.denali2.estimator.model.LockUser;
import com.pwc.denali2.estimator.model.User;

@Service("accountOpsService")
@Transactional
public class AccountOpsServiceImpl implements AccountOpsService {

	@Autowired
	private AccountOpsDao accountOpsDao;
	
	@CheckUserValidAction
	public User findUserBySsoId(String sso) {
		User user = accountOpsDao.findUserBySsoId(sso);
		return user;
	}
	
	public User findUserBySsoIdWithoutAOP(String sso) {
		User user = accountOpsDao.findUserBySsoId(sso);
		return user;
	}
	
	@Override
	public boolean checkLock(String UserId, String engagementId) {
		return accountOpsDao.checkLock(UserId,engagementId);
	}
	
	@Override
	public boolean removeLock(String userId) {
		return accountOpsDao.removeLock(userId);
	}
	
	@Override
	public LockUser getLockUser(String engagementId) {
		return accountOpsDao.getLockUser(engagementId);
	}
}