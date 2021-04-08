package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.BusinessProcess;

@Repository("businessProcessDao")
public class BusinessProcessDaoImpl extends AbstractDao<Integer, BusinessProcess> implements BusinessProcessDao {
	@SuppressWarnings("unchecked")
	public List<BusinessProcess> findAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("description"));
		return (List<BusinessProcess>) crit.list();
	}
}
