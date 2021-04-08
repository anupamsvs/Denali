package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.PaaSAppType;

@Repository("paaSAppsTypeDao")
public class PaaSAppsTypeDaoImpl extends AbstractDao<Integer, PaaSAppType> implements PaaSAppsTypeDao {
	@SuppressWarnings("unchecked")
	public List<PaaSAppType> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<PaaSAppType>) crit.list();
	}
}
