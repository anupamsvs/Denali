package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.EnagagementProgress;

@Repository("EnagagementProgressDao")
public class EnagagementProgressDaoImpl extends
		AbstractDao<Integer, EnagagementProgress> implements
		EnagagementProgressDao {

	@Override
	public void saveProgress(EnagagementProgress data) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("engagement_id", data.getEngagement_id()));
		EnagagementProgress result = (EnagagementProgress) crit.uniqueResult();
		if (result == null) {
			persist(data);
		} else if (result.getLevel() < data.getLevel()) {
			result.setLevel(data.getLevel());
			
			update(result);
		}
	}

	@Override
	public EnagagementProgress getProgress(int engagement_id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("engagement_id", engagement_id));
		return (EnagagementProgress) crit.uniqueResult();
	}

}