package com.pwc.denali2.estimator.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.pwc.denali2.estimator.model.ConversionDataType;

@Repository("conversionDataTypeDao")
public class ConversionDataTypeDaoImpl extends AbstractDao<Integer, ConversionDataType> implements ConversionDataTypeDao {
	@SuppressWarnings("unchecked")
	public List<ConversionDataType> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<ConversionDataType>) crit.list();
	}
	
	
}
