package com.pwc.denali2.estimator.service;

import java.util.List;

import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.EngagementShare;

public interface EngagementShareService {
	
	List<String> shareProject(EngagementShare engagementShare);
	List<String> getSharedNames(EngagementShare engagementShare);
	String getPermissionofUser(String currentUserGuid,Engagement engagementShare, int engagementId);

	
}
