package com.pwc.denali2.estimator.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.alibaba.fastjson.JSON;
import com.pwc.denali2.estimator.dao.MasterAdminUtilityDaoImpl;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.service.ProjectDurationService;

@Controller
@RequestMapping("/user/Project Duration")
public class ProjectDurationController {

		
		@Autowired
		ProjectDurationService projectDurationService;
		
		@Autowired
		MasterAdminUtilityDaoImpl masterAdminLovs ;
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		@RequestMapping(value = "/ProjectDurationMain", method = RequestMethod.GET)
		public void LoadAllDurationUser(@RequestParam("idparam") String idparam,HttpServletResponse response,@RequestParam("post_go_live_support_duration") String post_go_live_support_duration) {
			
			
			
			//Double totalricehoursDummy = 8000.00;
			try {
				
			
			Map<String,Double> maxModWtgAndComplexityFactor= projectDurationService.getMaxModuleWeightage(idparam);
			
			Map<String,String> moduleMultiplierMap =projectDurationService.getModuleMultiplier(idparam);
			
			Double projDurationFactor =projectDurationService.getProjectDurationFactor(idparam);
			System.out.println("proj_dur_factor"+projDurationFactor);
			double moduleWeeks = projectDurationService.getModuleWeeks(projDurationFactor, idparam);
			
			System.out.println("moduleweeks "+moduleWeeks);
			Map<String,Object> riceEffortInputMap = projectDurationService.riceEffortInput(moduleWeeks,idparam);
			
			projectDurationService.dbinsertOperation(idparam,maxModWtgAndComplexityFactor.get("complexity_multiplier"),riceEffortInputMap,moduleMultiplierMap);
			
			System.out.println(riceEffortInputMap.get("prototypSplitValue"));
			
			returnMap.put("max_Mod_Wtg_And_CmplxtyFctr", maxModWtgAndComplexityFactor);
			returnMap.put("module_multiplier_Map", moduleMultiplierMap);
			returnMap.put("proj_Duration_Factor", projDurationFactor);
			returnMap.put("module_Weeks", Math.round(moduleWeeks));
			returnMap.put("rice_Effort_Input_Map",riceEffortInputMap.get("allDtaProjectDuration"));
			returnMap.put("prototypSplitValue",riceEffortInputMap.get("prototypSplitValue"));
			returnMap.put("protypSplit",masterAdminLovs.listAvailableAdminLov(null, "Prototype") );
			
			System.out.println(returnMap);
			
			writeJson(returnMap, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//return returnMap;
			
		}
		
		@RequestMapping(value = "/saveChanges", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		public void saveChanges(@RequestParam("idparam")String idparam, @RequestParam("complexity_multiplier")String complexity_multiplier,@RequestParam("planningWeeks") String planningWeeks
				, @RequestParam("resourcesForWeeks") String resourcesForWeeks,@RequestParam("resourcesForplusWeeks") String resourcesForplusWeeks,HttpServletResponse response){
			
			
			projectDurationService.dbupdateOperation(idparam,complexity_multiplier,planningWeeks,resourcesForWeeks,resourcesForplusWeeks);
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