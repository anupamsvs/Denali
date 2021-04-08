package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.ProjectUnit;
import com.pwc.denali2.estimator.model.Unit;

public interface ProjectUnitsServices {

	String saveUnit(Unit unit, String idparam);

	String updateUnit(Unit unit, String idparam);

	Map<String, Object> listUnit(String idparam);

	Map<String, ProjectUnit> listByUnit(String idparam);
//	List<Unit> listUnitTables();
//	List<Unit> listUnitColumn(String tables);

}
