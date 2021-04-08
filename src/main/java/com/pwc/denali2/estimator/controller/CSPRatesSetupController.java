package com.pwc.denali2.estimator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.OnSiteCspRateMetadata;
import com.pwc.denali2.estimator.model.OffshoreCspRateMetadata;
import com.pwc.denali2.estimator.service.CspRateMetaDataOpsService;

@Controller
@RequestMapping("/admin")
public class CSPRatesSetupController {

	@Autowired
	CspRateMetaDataOpsService CspRateMetaDataOpsService;

	
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/loadOnSite", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> loadOnSite(HttpSession session,@RequestParam("version") String version,@RequestParam("idparam") String idparam) {
       System.out.println("rates controller");
		Map<String, Object> returnmap = new HashMap<String, Object>();

		Map<Object, Object> allOnSite = CspRateMetaDataOpsService
				.findAllOnSiteCspRate(version,idparam);
		Map<Object, Object> allOffSite = CspRateMetaDataOpsService
				.findAllOffSiteCspRate(version,idparam);
		Map<Object, Object> allOnSiteRsp = CspRateMetaDataOpsService
				.findAllOnSiteRspRate(version,idparam);
		Map<Object, Object> allOffSiteRsp = CspRateMetaDataOpsService
				.findAllOffSiteRspRate(version,idparam);
		List<Map<String, Object>> versionList = CspRateMetaDataOpsService.getVersionList(idparam);
		String create_dt =CspRateMetaDataOpsService.getCreateDate(idparam,version);
		
		returnmap.put("allOnSite", allOnSite.get("onsite"));
		returnmap.put("allOffSite", allOffSite.get("offsite"));
		returnmap.put("onsiteRsp", allOnSiteRsp.get("onsite"));
		returnmap.put("offsiteRsp", allOffSiteRsp.get("offsite"));
		returnmap.put("versionlist", versionList);
		returnmap.put("create_dt", create_dt);

		return returnmap;

	}
	
	
	@RequestMapping(value = "/refreshAdminRates", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> refreshAdminRates(HttpSession session) {
       System.out.println("rates controller");
		Map<String, Object> returnmap = CspRateMetaDataOpsService.refreshAdminRates();
        

		return returnmap;

	}
	
	

	@RequestMapping(value = "/saveRate", method = RequestMethod.POST)
	public @ResponseBody String saveRateAdminOnsiteCSP(
			HttpSession session,
			@RequestParam("onsiteDataCSP") String onsiteDataCSP,
			@RequestParam("offsitedataCsp") String offsitedataCsp,
			@RequestParam("onsiteDataRSP") String onsiteDataRSP,@RequestParam("offsitedataRsp") String offsitedataRsp,@RequestParam("idparam") String idparam,@RequestParam("savetype") String savetype) {
		
		
		System.out.println(savetype);
		System.out.println(onsiteDataCSP);
		System.out.println(offsitedataCsp);
		System.out.println(onsiteDataRSP);
		System.out.println(offsitedataRsp); 
		String msg =CspRateMetaDataOpsService.updateRates(onsiteDataCSP,offsitedataCsp,onsiteDataRSP,offsitedataRsp,idparam,savetype);
	
		System.out.println(msg);

		return msg;

	}


}
