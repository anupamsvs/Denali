package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.ProjectUnit;
import com.pwc.denali2.estimator.model.Unit;

public interface ProjectUnitsDao {

	String saveUnit(Unit unit, String idparam);

	Map<String, Object> listUnit(String idparam);

	String updateUnit(Unit unit,String idparam);

//	Double listByUnit(String unit, String idparam);

	Map<String, ProjectUnit> listByUnit(String idparam);

//	List<Unit> listUnitTables();
//
//	List<Unit> listUnitColumn(String table);

}
