package com.pwc.denali2.estimator.controller;

import java.io.IOException;
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
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.ModuleTransactionDataVO;
import com.pwc.denali2.estimator.service.ModuleTransactionDataService;

@Controller
@RequestMapping(value = "/user/modules")
public class ModuleTransactionController {
	
	@Autowired
	private ModuleTransactionDataService moduleTransactionDataService;
	
	@RequestMapping(value="/listEngagementModules",method=RequestMethod.GET)
	@ResponseBody
	public void listEngagementModules(String engagementId,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<ModuleTransactionDataVO> transactionModuleList = this.moduleTransactionDataService.findEngagementTransactionData(engagementId);
		map.put("transactionModuleList", transactionModuleList);
		
		int totalModulesInScope = 0;
		for(ModuleTransactionDataVO vo : transactionModuleList){
			if("Yes".equalsIgnoreCase(vo.getIs_in_scope_flag())){
				totalModulesInScope++;
			}
		}
		map.put("totalModulesInScope", totalModulesInScope);
		writeJson(map,response);
	}
	
	
	@RequestMapping(value="/saveModules", method = RequestMethod.POST)
	@ResponseBody
	public void saveModules(ModuleTransactionData moduleTransactionData){
		int currentVersion = moduleTransactionData.getVersion();
		moduleTransactionData.setVersion(currentVersion + 1);
		
		this.moduleTransactionDataService.saveModuleTransactionData(moduleTransactionData);
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
