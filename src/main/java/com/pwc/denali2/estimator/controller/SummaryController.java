package com.pwc.denali2.estimator.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pwc.denali2.estimator.dao.DetailedSummaryDao;
import com.pwc.denali2.estimator.dao.MasterAdminUtilityDao;
import com.pwc.denali2.estimator.dao.MasterAdminUtilityDaoImpl;
import com.pwc.denali2.estimator.model.DetailedSummary;
import com.pwc.denali2.estimator.service.DetailedSummaryService;
import com.pwc.denali2.estimator.service.SummaryEffortsService;

@Controller
@RequestMapping("/user")
public class SummaryController {
	
	@Autowired
	private DetailedSummaryService detailedSummaryService;
	
	@Autowired
	private DetailedSummaryDao detailedSummaryDao;
	
	@Autowired
	private MasterAdminUtilityDao masterAdminUtilityDao;
	
	
	@Autowired
	SummaryEffortsService summaryEffortsService;
	
	
	@RequestMapping(value = "/summary/detailed/{engagement_id}", method=RequestMethod.GET)
	public @ResponseBody Map<String, List> main(@PathVariable int engagement_id ,HttpServletResponse response){
		boolean result =  this.detailedSummaryService.calculateEffort(engagement_id);
		if(result){
			return this.detailedSummaryService.listAllEfforts(engagement_id);
		}
		 return null;
	}
	
	@RequestMapping(value = "/summary/detailed/{engagement_id}/save", method=RequestMethod.POST)
	public @ResponseBody Map<String, List> override(@PathVariable int engagement_id ,@RequestBody Map<String,Map<String,String>> data,HttpServletResponse response){
		System.out.print(data);
		boolean result = this.detailedSummaryService.overrideEfforts(engagement_id, data);
		return this.detailedSummaryService.listAllEfforts(engagement_id);
	}
	
	
	
	@RequestMapping(value = "/SummaryEfforts/findSummaryEffortsData")
	public void getSummaryEffortForEngId(Integer engId,HttpServletResponse response){
		
		Map<String, Object> mapSummaryEffort = summaryEffortsService.getSummaryEffortForEngId(engId);
		writeJson(mapSummaryEffort,response);
	}
	

					
	
	@RequestMapping(value = "/summary/detailed/effort/{engagement_id}", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> effort(@PathVariable int engagement_id ,HttpServletResponse response){
		Map<String,Object> mappedData = new HashMap<String, Object>();
		Map<String,List> result = this.detailedSummaryService.detailedEffortSummary(engagement_id);
		Map<String,List> resultGrp  = detailedSummaryDao.listDetailedEffortGroups(engagement_id);
	
		Map<String, List<List>> prototype = masterAdminUtilityDao.listByTypeTag(Arrays.asList("prototype"), "");
		Map<String, List<List>> workstream = masterAdminUtilityDao.listByTypeTag(Arrays.asList("workstream"), "");
		mappedData.put("prototype", prototype.get("prototype"));
		mappedData.put("groups",resultGrp);
		mappedData.put("list", result);
		mappedData.put("workstream", workstream.get("workstream"));
		return mappedData;
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