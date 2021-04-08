package com.pwc.denali2.estimator.service;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.OnSiteCspRateMetadata;

public interface CspRateMetaDataOpsService {

	String updateRates(String onsiteDataCSP, String offsitedataCsp, String onsiteDataRSP, String offsitedataRsp,String idparam, String savetype);
	
	
	Map<Object, Object> findAllOffSiteCspRate(String ver_id,String idparam);
	Map<Object, Object> findAllOnSiteCspRate(String ver_id,String idparam);
	Map<Object, Object> findAllOffSiteRspRate(String ver_id,String idparam);
	Map<Object, Object> findAllOnSiteRspRate(String ver_id,String idparam);


	List<Map<String, Object>> getVersionList(String idparam);


	Map<String, Object> refreshAdminRates();


	String getCreateDate(String idparam, String version);
}
