package com.pwc.denali2.estimator.dao;

import java.util.List;

import com.pwc.denali2.estimator.model.ModuleMetaData;

public interface ModuleMetaDataDao {

	List<ModuleMetaData> findByBusinessId(Integer id);
	List<ModuleMetaData> findAll();

}
