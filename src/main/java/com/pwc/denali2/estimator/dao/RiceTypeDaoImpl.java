package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.RiceType;

@Repository("riceTypeDao")
public class RiceTypeDaoImpl extends AbstractDao<Integer, RiceType> implements RiceTypeDao {
	@SuppressWarnings("unchecked")
	public List<RiceType> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<RiceType>) crit.list();
	}
	

}
