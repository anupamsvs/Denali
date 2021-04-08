package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.ClientComplexity;

@Repository("complexityDaoDao")
public class ComplexityDaoImpl extends AbstractDao<Integer, ClientComplexity> implements ComplexityDao {
	@SuppressWarnings("unchecked")
	public List<ClientComplexity> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<ClientComplexity>) crit.list();
	}
}
