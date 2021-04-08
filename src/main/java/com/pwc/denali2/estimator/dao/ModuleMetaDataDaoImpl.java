package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.ModuleMetaData;

@Repository("moduleMetaDataDao")
public class ModuleMetaDataDaoImpl extends AbstractDao<Integer, ModuleMetaData> implements ModuleMetaDataDao {
	@SuppressWarnings("unchecked")
	public List<ModuleMetaData> findByBusinessId(Integer id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("business_process_id", id));
		crit.addOrder(Order.asc("module_name"));
		return (List<ModuleMetaData>) crit.list();
	}

	@Override
	public List<ModuleMetaData> findAll() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("business_process_id"));
		return (List<ModuleMetaData>) crit.list();
	}
}
