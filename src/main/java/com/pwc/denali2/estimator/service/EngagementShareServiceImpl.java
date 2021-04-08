package com.pwc.denali2.estimator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.EngagementShareDao;
import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.EngagementShare;

@Service("engagementShareService")
@Transactional
public class EngagementShareServiceImpl implements EngagementShareService{

	@Autowired
	private EngagementShareDao engagementShareDao;
	

	@Override
	public List<String> shareProject(EngagementShare engagementShare) {
		
		return this.engagementShareDao.shareProject(engagementShare);
		
	}


	@Override
	public List<String> getSharedNames(EngagementShare engagementShare) {
		// TODO Auto-generated method stub
		return this.engagementShareDao.getSharedNames(engagementShare);
	}


	@Override
	public String getPermissionofUser(String currentUserGuid,Engagement engagementShare,int id) {
		// TODO Auto-generated method stub
		 return this.engagementShareDao.getPermissionofUser(currentUserGuid,engagementShare,id);
	}

}
