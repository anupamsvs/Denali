package com.pwc.denali2.estimator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pwc.denali2.estimator.dao.ModuleTransactionDataDao;
import com.pwc.denali2.estimator.model.ActivityManagementData;
import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.ModuleTransactionActivity;
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.ModuleTransactionDataVO;
import com.pwc.denali2.estimator.model.ProjectUnit;
import com.pwc.denali2.estimator.service.ModuleTransactionDataService;
import com.pwc.denali2.estimator.service.ProjectManagementService;
import com.pwc.denali2.estimator.service.ProjectUnitsServices;

@Controller
@RequestMapping(value = "/user/modules")
public class ModuleTransactionController {
	
	@Autowired
	private ModuleTransactionDataService moduleTransactionDataService;
	
	@Autowired
	private ModuleTransactionDataDao moduleTransactionDataDao;
	
	@Autowired
	private ProjectManagementService projectMangementService;
	@Autowired
	private ProjectUnitsServices projectUnitsServices;

	@RequestMapping(value="/listEngagementModules",method=RequestMethod.GET)
	@ResponseBody
	public void listEngagementModules(String engagementId,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		Engagement engagement = this.projectMangementService.getEngagementById(Integer.parseInt(engagementId));
		List<ModuleTransactionActivity> transactionModuleList = this.moduleTransactionDataDao.findModuleTransactionActivityData(engagement.getId().toString());
		Map<String, ProjectUnit> allunits = projectUnitsServices.listByUnit(engagement.getId().toString());
		int totalModulesInScope = 0;
		//Get Related Activities
		List<ActivityManagementData> activities = this.moduleTransactionDataService.getActivities();
		List<Map<String,Object>> calculations = new ArrayList<Map<String,Object>>();
		for(ModuleTransactionActivity vo : transactionModuleList){
//			if("Yes".equalsIgnoreCase(vo.getIs_in_scope_flag())){
				totalModulesInScope++;
				for(ActivityManagementData activitiy : activities){
				calculations.add(this.moduleTransactionDataService.calculateAct(allunits,activitiy,activitiy.getUnit(),vo,engagement,null));
				}
				this.moduleTransactionDataDao.setActValues(calculations);
				calculations.clear();
//			}
		}
		
//		List<ModuleTransactionActivity> transactionModuleListafterCal = this.moduleTransactionDataDao.findModuleTransactionActivityData(engagementId);
		List transactionModuleListafterCal = this.moduleTransactionDataDao.actValues(engagementId);
		
		map.put("transactionModuleList", transactionModuleList);
		map.put("act_values", transactionModuleListafterCal);
		map.put("totalModulesInScope", totalModulesInScope);
		map.put("activities", activities);
		writeJson(map,response);
	}
	
	
	@RequestMapping(value="/saveModules", method = RequestMethod.POST)
	@ResponseBody
	public void saveModules(ModuleTransactionData moduleTransactionData){
		int currentVersion = moduleTransactionData.getVersion();
		moduleTransactionData.setVersion(currentVersion + 1);
		
		this.moduleTransactionDataService.saveModuleTransactionData(moduleTransactionData);
	}
	
	
	@RequestMapping(value="/listActivities", method = RequestMethod.GET)
	@ResponseBody
	public void listActivities(ModuleTransactionData moduleTransactionData,HttpServletResponse response){
//		int currentVersion = moduleTransactionData.getVersion();
		Map<String,Object> map = new HashMap<String,Object>();
		try{
		List<ActivityManagementData> Activities = this.moduleTransactionDataService.getActivities();
		map.put("activityList", Activities);
		writeJson(map,response);
		}catch(Exception e){
			return ;
		}
	}
	
	/**
	 * 
	 * @param object
	 * @param response
	 */
	private void writeJson(Object object,HttpServletResponse response) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "MMM-dd-yyyy");
//			json = StringUtil.replaceBlank(json);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
