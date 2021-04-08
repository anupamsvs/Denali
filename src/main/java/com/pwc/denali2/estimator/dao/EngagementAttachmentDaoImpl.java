package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.EngagementAttachment;

@Repository("engagementAttachmentDao")
public class EngagementAttachmentDaoImpl extends AbstractDao<Integer, EngagementAttachment> implements EngagementAttachmentDao {
	

	@Override
	public void save(EngagementAttachment engagementAttachment) {
		persist(engagementAttachment);
	}

	@Override
	public List<EngagementAttachment> findAllByEngagementId(Integer id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("engagement_id", id));
		crit.addOrder(Order.desc("upload_time"));
		return (List<EngagementAttachment>) crit.list();
	}

	
}