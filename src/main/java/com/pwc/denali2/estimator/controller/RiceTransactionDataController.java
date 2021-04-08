package com.pwc.denali2.estimator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.pwc.denali2.estimator.model.ClientComplexity;
import com.pwc.denali2.estimator.model.ModuleTransactionData;
import com.pwc.denali2.estimator.model.PaaSAppType;
import com.pwc.denali2.estimator.model.RiceTransactionData;
import com.pwc.denali2.estimator.model.RiceType;
import com.pwc.denali2.estimator.service.RiceTransactionDataService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/user/RiceTransactionData")
public class RiceTransactionDataController {
	@Autowired
	RiceTransactionDataService riceTransactionDataService;

	@RequestMapping(value = "/save")
	public void save(String data,HttpServletResponse response) {
		JSONArray jsonarray = JSONArray.fromObject(data);
		System.out.println(jsonarray);
		
		List list = (List) JSONArray.toCollection(jsonarray, RiceTransactionData.class);
		Iterator it = list.iterator();
		List<RiceTransactionData> ls = new ArrayList<RiceTransactionData>();
		while (it.hasNext()) {
			RiceTransactionData rt = (RiceTransactionData) it.next();
			ls.add(rt);
		}
		riceTransactionDataService.save(ls);
		writeJson("success", response);
	}
	
	@RequestMapping(value = "/saveComplexityMultiplier", method=RequestMethod.POST)
	public void saveComplexityMultiplier(@RequestBody Map<String, Map<String, String>> data,HttpServletResponse response) {
		
		riceTransactionDataService.saveComplexityMultiplier(data,"rice");
		
		writeJson("success", response);
		
		
		
	}

	@RequestMapping(value = "/findricetransactiondata")
	public void findRiceTransactionDataByEngagementId(Integer id,HttpServletResponse response) {
		List<RiceTransactionData> ls = riceTransactionDataService.findRiceTransactionDataByEngagementId(id);
		writeJson(ls, response);
	}
	
	@RequestMapping(value = "/loadAllRice")
	public void loadAllRice(HttpServletResponse response,@RequestParam("idparam") String idparam) {
		System.out.println(idparam);
		Map<Object, Object> ls = riceTransactionDataService.loadAllRice(idparam);
		writeJson(ls, response);
	}
	
	@RequestMapping(value = "/findmoduledata")
	public void findModuleDataByEngagementId(Integer id,HttpServletResponse response) {
		List<ModuleTransactionData> ls = riceTransactionDataService.findModuleDataByEngagementId(id);
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
