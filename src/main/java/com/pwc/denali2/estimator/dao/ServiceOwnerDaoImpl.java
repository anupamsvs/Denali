package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.ServiceOwner;

@Repository("serviceOwnerDao")
public class ServiceOwnerDaoImpl extends AbstractDao<Integer, ServiceOwner> implements ServiceOwnerDao {
	@SuppressWarnings("unchecked")
	public List<ServiceOwner> findAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("description"));
		return (List<ServiceOwner>) crit.list();
	}
}
