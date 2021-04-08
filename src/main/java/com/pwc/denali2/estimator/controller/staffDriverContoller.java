package com.pwc.denali2.estimator.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.pwc.denali2.estimator.dao.MasterAdminUtilityDao;
import com.pwc.denali2.estimator.dao.StaffDriverDao;
import com.pwc.denali2.estimator.mail.SendMail;
import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.MasterAdminLovs;
import com.pwc.denali2.estimator.model.ModuleMetaData;
import com.pwc.denali2.estimator.model.StaffdriverData;
import com.pwc.denali2.estimator.service.staffDriverService;

@Controller
@RequestMapping("/user")
public class staffDriverContoller {
	
	@Autowired
	private StaffDriverDao staffDriverDao; 
	
	@Autowired
	private MasterAdminUtilityDao masterAdminListDao;
	
	@SuppressWarnings("null")
	@RequestMapping(value = {"/staff/efforts/", "/staff/efforts/{engagement_id}"}, method=RequestMethod.GET)
	public @ResponseBody Map<String, Map<String, List<List>>> main(@PathVariable String engagement_id ,HttpServletResponse response){
		Map<String, List<List>> allEfforts = this.staffDriverDao.getAllEfforts(engagement_id);
		Map<String, List<List>> allTeam = this.staffDriverDao.getAllTeam(engagement_id);
		Map<String, List<List>> allTeamRates = this.staffDriverDao.getAllTeamRates(engagement_id);
		
		List<String> Types = Arrays.asList("workstream","levels");
		
		Map<String, List<List>> extra_onsite = this.masterAdminListDao.listByTypeTag(Types,"onsite");
		Map<String, List<List>> extra_offsite = this.masterAdminListDao.listByTypeTag(Types,"offshore");
		Map<String,Map<String, List<List>>> allData  = new HashedMap<String, Map<String,List<List>>>();
		
		allData.put("team", allTeam);
		allData.put("efforts", allEfforts);
		allData.put("team_rates", allTeamRates);
		
		allData.put("extras_onsite", extra_onsite);
		allData.put("extras_offsite", extra_offsite);
		
		return allData;
	}
	
	@RequestMapping(value="/staff/extras", method=RequestMethod.GET)
	public @ResponseBody Map<String, List<MasterAdminLovs>> getExtras(HttpServletResponse response){

		Map<String,List<MasterAdminLovs>> extras  = new HashedMap<String,List<MasterAdminLovs>>();
		
		List<MasterAdminLovs> allWorkStream = this.masterAdminListDao.listAvailableAdminLov(new MasterAdminLovs(),"workstream");
		List<MasterAdminLovs> allLevels = this.masterAdminListDao.listAvailableAdminLov(new MasterAdminLovs(),"levels");
		
		extras.put("workstream", allWorkStream);
		extras.put("levels", allLevels);
		
		return extras;
	}
	
	@RequestMapping(value="/staff/saveAll/{engagement_id}", method=RequestMethod.POST)
	public @ResponseBody Boolean saveAll(@PathVariable String engagement_id,HttpServletResponse response,@RequestBody Map<String,Map<String,Number>> data){
		
		Map<String,Number> teamData = data.get("team");
		Map<String,Number> effortData = data.get("effort");
		Map<String,Number> blendedRatesData = data.get("blended_rates");
		Boolean teamSave = this.staffDriverDao.saveAllTeam(teamData,engagement_id);
		Boolean effortSave = this.staffDriverDao.saveAllEfforts(effortData,engagement_id);
		Boolean blendedSave = this.staffDriverDao.saveAllBlendedrates(blendedRatesData,engagement_id);
		
		return teamSave && effortSave && blendedSave;
	}
	
	
}


