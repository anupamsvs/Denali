package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.WorkStream;

@Repository("workStreamDao")
public class WorkStreamDaoImpl extends AbstractDao<Integer, WorkStream> implements WorkStreamDao {
	@SuppressWarnings("unchecked")
	public List<WorkStream> findAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("description"));
		return (List<WorkStream>) crit.list();
	}
}
