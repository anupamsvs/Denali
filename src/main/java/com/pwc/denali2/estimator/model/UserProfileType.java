package com.pwc.denali2.estimator.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable {
	SUPERUSER("SUPERUSER"), ADMIN("ADMIN");

	String userProfileType;

	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}
}
