package com.pwc.denali2.estimator.dao;

import java.util.List;
import java.util.Map;

import com.pwc.denali2.estimator.model.OnSiteCspRateMetadata;

public interface CspRateMetaDataOpsDao {

	//List<OnSiteCspRateMetadata> findAllOnSiteCspRate();
	Map<Object, Object> findAllOffSiteCspRate(String ver_id,String idparam);
	Map<Object, Object> findAllOnSiteCspRate(String ver_id,String idparam);
	Map<Object, Object> findAllOffSiteRspRate(String ver_id,String idparam);
	Map<Object, Object> findAllOnSiteRspRate(String ver_id,String idparam);
	
	//String updateRates(String onsiteDataCSP, String offsitedataCsp, String onsiteDataRSP, String offsitedataRsp, String idparam);
	//void saveAllOnSiteCspRate(OnSiteCspRateMetadata onSite);
	List<Map<String, Object>> getVersionList(String idparam);
	Map<String, Object> refreshAdminRates();
	String getCreateDate(String idparam,String ratesVersion);
	String updateRates(String onsiteDataCSP, String offsitedataCsp,
			String onsiteDataRSP, String offsitedataRsp, String idparam,String savetype);

}
