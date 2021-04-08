package com.pwc.denali2.estimator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.pwc.denali2.estimator.model.AdditionalService;
import com.pwc.denali2.estimator.model.ServiceOwner;
import com.pwc.denali2.estimator.model.WorkStream;
import com.pwc.denali2.estimator.service.AdditionalServicesService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/user/Additional")
public class AdditionalServicesController {
	@Autowired
	AdditionalServicesService additionalServicesService;

	@RequestMapping(value = "/save")
	public void save(@RequestParam("data") String data , @RequestParam("id") String id ,HttpServletResponse response) {
		
		
//		System.out.println(data);
//		JSONArray jsonarray = JSONArray.fromObject(data);
//		System.out.println(jsonarray);
		
		
		
		
		JSONObject jsonarr = new JSONObject(data);
		System.out.println(jsonarr);
		System.out.println(id);
//		Iterator it = list.iterator();
//		List<AdditionalService> ls = new ArrayList<AdditionalService>();
//		while (it.hasNext()) {
//			AdditionalService as = (AdditionalService) it.next();
//			ls.add(as);
//		}
		additionalServicesService.save(jsonarr,id);
		writeJson("success", response);
	}

	@RequestMapping(value = "/findadditionalserviceInitData")
	public void findAdditionalServiceByEngagementId(Integer id,HttpServletResponse response) {
		Map<Object, Object> allAdditionalServiceInitDta = additionalServicesService.findAdditionalServiceByEngagementId(id);
		writeJson(allAdditionalServiceInitDta, response);
	}
	
	@RequestMapping(value ="/findAdditionalServiceEffortData")
	public void findAdditionalServiceEffortData(Integer id,HttpServletResponse response) {
		Map<Object, Object> mapAddServcEffortDetails = additionalServicesService.findAdditionalServiceEffortData(id);
		writeJson(mapAddServcEffortDetails, response);
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
