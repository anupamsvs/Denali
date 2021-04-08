package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.ProjectUnitsDao;
import com.pwc.denali2.estimator.model.ProjectUnit;
import com.pwc.denali2.estimator.model.Unit;


@Service("ProjectUnitsServices")
@Transactional
public class ProjectUnitsServicesImpl implements ProjectUnitsServices{
	
	@Autowired
	private ProjectUnitsDao ProjctUnitsDao;

	@Override
	public String saveUnit(Unit unit, String idparam) {
		// TODO Auto-generated method stub
		return ProjctUnitsDao.saveUnit(unit,idparam);
	}


	@Override
	public Map<String, Object> listUnit(String idparam) {
		// TODO Auto-generated method stub
		return ProjctUnitsDao.listUnit(idparam);
	}


	@Override
	public String updateUnit(Unit unit, String idparam) {
		// TODO Auto-generated method stub
		return ProjctUnitsDao.updateUnit(unit,idparam);
	}


	@Override
	public Map<String, ProjectUnit> listByUnit( String idparam) {
		return ProjctUnitsDao.listByUnit( idparam);
	}


//	@Override
//	public List<Unit> listUnitTables() {
//		// TODO Auto-generated method stub
//		 return ProjctUnitsDao.listUnitTables();
//	}
//
//
//	@Override
//	public List<Unit> listUnitColumn(String table) {
//		// TODO Auto-generated method stub
//		return ProjctUnitsDao.listUnitColumn(table);
//	}

}
