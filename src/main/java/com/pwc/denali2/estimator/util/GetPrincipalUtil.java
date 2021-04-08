package com.pwc.denali2.estimator.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class GetPrincipalUtil {
	public static String getPrincipal() {
		String ssoId = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			ssoId = ((UserDetails) principal).getUsername();
		} else {
			ssoId = principal.toString();
		}
		return ssoId;
	}

	public static String getCredential() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
	}
}
