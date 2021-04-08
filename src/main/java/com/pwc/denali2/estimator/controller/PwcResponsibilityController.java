package com.pwc.denali2.estimator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.pwc.denali2.estimator.dao.MasterAdminUtilityDao;
import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.model.PwcResponsibilityTransactionData;
import com.pwc.denali2.estimator.model.PwcResponsibilityVo;
import com.pwc.denali2.estimator.model.Pwc_Responsibility_Vo;
import com.pwc.denali2.estimator.model.Pwc_responsibilityData;
import com.pwc.denali2.estimator.service.PwcResponsibilityService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/user/PwcResponsibility")
public class PwcResponsibilityController {
	@Autowired
	PwcResponsibilityService pwcResponsibilityService;
	
	@Autowired
	MasterAdminUtilityDao masterAdminUtilityDao;

	@RequestMapping(value = "/update")
	public void update(String data,String checkstatusY_N,String idparam,HttpServletResponse response) {
		System.out.println(idparam+""+checkstatusY_N);
		JSONArray jsonarray = JSONArray.fromObject(data);
		System.out.println(jsonarray);
		
		

		 Map<String,Object> mapupdata = pwcResponsibilityService.update(jsonarray,idparam,checkstatusY_N);
		 
		writeJson(mapupdata, response);
	}
	
	@RequestMapping(value = "/findListFromMasterAdmin")
	public void findAllByEngagementIdForOther(HttpServletResponse response) {
		Map<String, List<MasterAdminLovs>> adminDataList = new HashMap<String,List<MasterAdminLovs>> ();
		adminDataList.put("workstreamList", masterAdminUtilityDao.getMasterAdminListData("workstream", ""));
		adminDataList.put("prototypeList", masterAdminUtilityDao.getMasterAdminListData("prototype", ""));
		
		
	    
		writeJson(adminDataList, response);
	}
	

	@RequestMapping(value = "/findpwcresponsibilityforother")
	public void findAllByEngagementIdForOther(@RequestParam("idparam") String idparam,HttpServletResponse response,Pwc_responsibilityData pwcResponsibility) {
		System.out.println(idparam);
		
	    List<Pwc_Responsibility_Vo> ls = pwcResponsibilityService.findAllByEngagementIdForOther(idparam, pwcResponsibility);
		writeJson(ls, response);
	}
	@RequestMapping(value = "/findpwcresponsibilityforpmo")
	public void findAllByEngagementIdForPMO(@RequestParam("idparam") String idparam,HttpServletResponse response) {
		System.out.println(idparam);
		List<Pwc_Responsibility_Vo> ls = pwcResponsibilityService.findAllByEngagementIdForPMO(idparam);
		writeJson(ls, response);
	}

	public void writeJson(Object object, HttpServletResponse response) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			// json = StringUtil.replaceBlank(json);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
