package com.pwc.denali2.estimator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.pwc.denali2.estimator.model.BusinessProcess;
import com.pwc.denali2.estimator.model.ConversionDataType;
import com.pwc.denali2.estimator.model.ConversionTransactionData;
import com.pwc.denali2.estimator.model.ConversionVo;
import com.pwc.denali2.estimator.model.ModuleMetaData;
import com.pwc.denali2.estimator.service.ConversionsService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/user/Conversions")
public class ConversionsController {
	@Autowired
	ConversionsService conversionsService;

	@RequestMapping(value = "/update")
	public void update(Integer id,String data,String adminDta,HttpServletResponse response) {
		
		JSONArray jsonarray = JSONArray.fromObject(data);
		
		System.out.println(data);
		List list = (List) JSONArray.toCollection(jsonarray, ConversionTransactionData.class);
		Iterator it = list.iterator();
		List<ConversionTransactionData> ls = new ArrayList<ConversionTransactionData>();
		while (it.hasNext()) {
			ConversionTransactionData pt = (ConversionTransactionData) it.next();
			ls.add(pt);
		}
		 String dupStatus = conversionsService.update(ls,id);
		 System.out.println(dupStatus);
		if(!StringUtils.isEmpty(dupStatus)){
			
			writeJson("Duplicates", response);
		}else{writeJson("success", response);}
		
	}
	
//	@RequestMapping(value = "/saveComplexityMultiplier")
//	public void saveComplexityMultiplier(@RequestParam("data") String data,@RequestParam("id") String id,HttpServletResponse response) {
//		
//
//		conversionsService.saveComplexityMultiplier(data,id);
//		
//		
//	}
	
	
	@RequestMapping(value = "/saveComplexityMultiplier", method=RequestMethod.POST)
	public void saveComplexityMultiplier(@RequestBody Map<String, Map<String, String>> data,HttpServletResponse response) {
		System.out.println(data);
		conversionsService.saveComplexityMultiplier(data,"conversion");
		
		writeJson("success", response);
		
		
		
	}


	@RequestMapping(value = "/findconversions")
	public void findAllByEngagementId(Integer id,String type,String userflag,HttpServletResponse response) {
		List<ConversionVo> ls = conversionsService.findAllByEngagementId(id,type,userflag);
		writeJson(ls, response);
	}
	
	@RequestMapping(value = "/findconversionsforusercreate")
	public void findAllByEngagementIdForUserCreate(Integer id,HttpServletResponse response) {
		List<ConversionVo> ls = conversionsService.findAllByEngagementIdForUserCreate(id);
		writeJson(ls, response);
	}
	
	@RequestMapping(value = "/loadAllConversion")
	public void loadAllRice(HttpServletResponse response,@RequestParam("idparam") String idparam) {
		System.out.println(idparam);
		Map<Object, Object> ls = conversionsService.loadAllConversion(idparam);

		
		writeJson(ls, response);
	}
	
	
	
//	@RequestMapping(value = "/findallbusinessprocess")
//	public void findAllBusinessProcess(HttpServletResponse response) {
//		List<BusinessProcess> ls = conversionsService.findAllBusinessProcess();
//		writeJson(ls, response);
//	}
//
//	@RequestMapping(value = "/findallconversiondatatype")
//	public void findAllConversionDataType(HttpServletResponse response) {
//		List<ConversionDataType> ls = conversionsService.findAllConversionDataType();
//		writeJson(ls, response);
//	}
//	
	@RequestMapping(value = "/findmodulemetadatabybusinessid")
	public void findModuleMetaDataByBusinessId(Integer businessid,HttpServletResponse response) {
		List<ModuleMetaData> ls = conversionsService.findModuleMetaDataByBusinessId(businessid);
		writeJson(ls, response);
	}
//	@RequestMapping(value = "/findallmodulemetadata")
//	public void findAllModuleMetaData(HttpServletResponse response) {
//		List<ModuleMetaData> ls = conversionsService.findAllModuleMetaData();
//		writeJson(ls, response);
//	}

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
