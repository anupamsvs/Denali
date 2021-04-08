package com.pwc.denali2.estimator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.ProjectUnit;
import com.pwc.denali2.estimator.model.PwcResponsibilityTransactionData;
import com.pwc.denali2.estimator.model.PwcResponsibilityVo;
import com.pwc.denali2.estimator.model.Pwc_Responsibility_Vo;
import com.pwc.denali2.estimator.model.Pwc_responsibilityData;
import com.pwc.denali2.estimator.model.Unit;
import com.pwc.denali2.estimator.service.ProjectUnitsServices;
import com.pwc.denali2.estimator.service.PwcResponsibilityService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/user/ProjectUnits")
public class ProjectUnitsController {
	
	@Autowired
	ProjectUnitsServices unitservices;
	
	@RequestMapping(value = "/saveUnits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void saveProject(Unit unit,
			HttpServletResponse response,@RequestParam("idparam") String idparam){
			
			String msg ="";
			System.out.println("***********");
			System.out.println(unit.getId());
			 
			if (StringUtils.isEmpty(unit.getId())) {
			System.out.println("create");
			msg = unitservices.saveUnit(unit,idparam);
			} else {
			System.out.println("update");
			msg = unitservices.updateUnit(unit,idparam);
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("msg", msg);
			writeJson(map, response);
	}

	

	@RequestMapping(value = "/loadUnit", method = RequestMethod.GET)
	@ResponseBody
	public void listMasterAdminLovs(Unit unit,
			HttpServletResponse response,@RequestParam("idparam") String idparam) {

		System.out.println("list controller");
		
		Map<String, Object> unitDetailsDta = this.unitservices.listUnit(idparam);
		//List<Unit> unitListTables = this.unitservices.listUnitTables();

		
		writeJson(unitDetailsDta, response);

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
