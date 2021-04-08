package com.pwc.denali2.estimator.util;

import com.pwc.denali2.estimator.model.User;
import com.pwc.denali2.estimator.service.AccountOpsService;

public class GetUserInfoUtil {

	public static User getUserInfo(AccountOpsService accountOpsService) {
		return accountOpsService.findUserBySsoId(GetPrincipalUtil.getPrincipal());
	}
}
