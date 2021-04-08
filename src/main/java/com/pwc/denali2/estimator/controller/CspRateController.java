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
import com.pwc.denali2.estimator.model.OffshoreCspRateTransactiondata;
import com.pwc.denali2.estimator.model.OffshoreCspRateTransactiondataVO;
import com.pwc.denali2.estimator.model.OnSiteCspRateTransactiondata;
import com.pwc.denali2.estimator.model.OnsiteCspRateTransactionDataVO;
import com.pwc.denali2.estimator.service.OffshoreCSPRateTransactionService;
import com.pwc.denali2.estimator.service.OnsiteCSPRateTransactionService;

@Controller
@RequestMapping(value="/user/rate")
public class CspRateController {
	
	@Autowired
	private OnsiteCSPRateTransactionService onsiteCSPRateTransactionService;
	
	@Autowired
	private OffshoreCSPRateTransactionService offshoreCSPRateTransactionService;
	
	@RequestMapping(value="/initialData", method = RequestMethod.GET)
	@ResponseBody
	public void initialCSPRateTransactionData(String engagementId,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<OnsiteCspRateTransactionDataVO> onsiteCspRateTransactionDataList = 
				this.onsiteCSPRateTransactionService.findEngagementTransactionData(engagementId);
		map.put("onsiteCspRateTransactionDataList", onsiteCspRateTransactionDataList);
		
		List<OffshoreCspRateTransactiondataVO> offshoreCspRateTransactiondataList = 
				this.offshoreCSPRateTransactionService.findEngagementTransactionData(engagementId);
		map.put("offshoreCspRateTransactiondataList", offshoreCspRateTransactiondataList);
		
		writeJson(map,response);
		
	}
	
	@RequestMapping(value="/saveOnsiteRate", method = RequestMethod.POST)
	@ResponseBody
	public void saveOnsiteRate(OnSiteCspRateTransactiondata data){
		int currentVersion = data.getVersion();
		data.setVersion(currentVersion + 1);
		this.onsiteCSPRateTransactionService.saveOnsiteCspRateTransactionData(data);
	}
	
	@RequestMapping(value="/saveOffshoreRate", method = RequestMethod.POST)
	@ResponseBody
	public void saveOffshoreRate(OffshoreCspRateTransactiondata data){
		int currentVersion = data.getVersion();
		data.setVersion(currentVersion + 1);
		
		this.offshoreCSPRateTransactionService.saveOffshoreCspRateTransactionData(data);
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
