package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pwc.denali2.estimator.dao.CspRateMetaDataOpsDao;


@Service("CspRateMetaDataOpsService")
@Transactional
public class CspRateMetaDataOpsServiceImpl implements CspRateMetaDataOpsService {

	@Autowired
	CspRateMetaDataOpsDao CspRateMetaDataOpsDao;

	@Override
	public Map<Object, Object> findAllOffSiteCspRate(String ver_id,String idparam) {
		
		return CspRateMetaDataOpsDao.findAllOffSiteCspRate(ver_id,idparam);
	}

	@Override
	public Map<Object, Object> findAllOnSiteCspRate(String ver_id,String idparam) {
	
		return CspRateMetaDataOpsDao.findAllOnSiteCspRate(ver_id,idparam);
	}

	@Override
	public String updateRates(String onsiteDataCSP, String offsitedataCsp, String onsiteDataRSP, String offsitedataRsp,String idparam,String savetype) {
		// TODO Auto-generated method stub
		return CspRateMetaDataOpsDao.updateRates(onsiteDataCSP,offsitedataCsp,onsiteDataRSP,offsitedataRsp,idparam,savetype);
	}

	@Override
	public Map<Object, Object> findAllOffSiteRspRate(String ver_id,String idparam) {
		// TODO Auto-generated method stub
		return CspRateMetaDataOpsDao.findAllOffSiteRspRate(ver_id,idparam);
	}

	@Override
	public Map<Object, Object> findAllOnSiteRspRate(String ver_id,String idparam) {
		// TODO Auto-generated method stub
		return CspRateMetaDataOpsDao.findAllOnSiteRspRate(ver_id,idparam);
	}

	@Override
	public List<Map<String, Object>> getVersionList(String idparam) {
		// TODO Auto-generated method stub
		return CspRateMetaDataOpsDao.getVersionList(idparam);
	}

	@Override
	public Map<String, Object> refreshAdminRates() {
		// TODO Auto-generated method stub
		return CspRateMetaDataOpsDao.refreshAdminRates();
	}

	@Override
	public String getCreateDate(String idparam,String version) {
		// TODO Auto-generated method stub
		return CspRateMetaDataOpsDao.getCreateDate(idparam,version);
		
	}

	

//	@Override
//	@CheckUserValidAction(description= "fetch all onsite type csp rate")
//	public List<OnSiteCspRateMetadata> findAllOnSiteCspRate() {
//		return onSiteCspRateMetaDataOpsDao.findAllOnSiteCspRate();
//	}
	
//	@Override
//	public void saveAllOnSiteCspRate(OnSiteCspRateMetadata onSite){
//		onSiteCspRateMetaDataOpsDao.saveAllOnSiteCspRate(onSite);
//	}
}
