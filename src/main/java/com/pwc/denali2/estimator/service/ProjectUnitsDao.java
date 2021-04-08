package com.pwc.denali2.estimator.service;

import java.util.List;

import com.pwc.denali2.estimator.model.ProjectUnit;
import com.pwc.denali2.estimator.model.Unit;

public interface ProjectUnitsDao {

	String saveUnit(Unit unit, String idparam);

	List<ProjectUnit> listUnit(Unit unit,String idparam);

	String updateUnit(Unit unit,String idparam);

//	List<Unit> listUnitTables();
//
//	List<Unit> listUnitColumn(String table);

}
