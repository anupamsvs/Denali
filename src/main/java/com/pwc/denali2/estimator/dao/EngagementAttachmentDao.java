package com.pwc.denali2.estimator.dao;

import java.util.List;

import com.pwc.denali2.estimator.model.EngagementAttachment;

public interface EngagementAttachmentDao {

	void save(EngagementAttachment eongagementAttachment);
	List<EngagementAttachment> findAllByEngagementId(Integer id);
}
