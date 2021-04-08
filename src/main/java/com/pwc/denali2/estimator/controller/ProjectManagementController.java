package com.pwc.denali2.estimator.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pwc.denali2.estimator.configuration.LdapConfig;
import com.pwc.denali2.estimator.configuration.UserEngagement;
import com.pwc.denali2.estimator.dao.ConversionsDao;
import com.pwc.denali2.estimator.dao.DetailedSummaryDao;
import com.pwc.denali2.estimator.dao.EnagagementProgressDao;
import com.pwc.denali2.estimator.dao.MasterAdminUtilityDao;
import com.pwc.denali2.estimator.dao.ProjectUnitsDaoImpl;
import com.pwc.denali2.estimator.dao.SummaryEffortsDao;
import com.pwc.denali2.estimator.dao.SummaryEffortsDaoImpl;
import com.pwc.denali2.estimator.mail.SendMail;
import com.pwc.denali2.estimator.model.DetailedSummary;
import com.pwc.denali2.estimator.model.EnagagementProgress;
import com.pwc.denali2.estimator.model.Engagement;
import com.pwc.denali2.estimator.model.EngagementAttachment;
import com.pwc.denali2.estimator.model.EngagementShare;
import com.pwc.denali2.estimator.model.Engagement_share_permission;
import com.pwc.denali2.estimator.model.User;
import com.pwc.denali2.estimator.service.AccountOpsService;
import com.pwc.denali2.estimator.service.DetailedSummaryService;
import com.pwc.denali2.estimator.service.DetailedSummaryServiceImpl;
import com.pwc.denali2.estimator.service.EngagementShareService;
import com.pwc.denali2.estimator.service.ModuleTransactionDataService;
import com.pwc.denali2.estimator.service.OffshoreCSPRateTransactionService;
import com.pwc.denali2.estimator.service.OnsiteCSPRateTransactionService;
import com.pwc.denali2.estimator.service.ProjectManagementService;
import com.pwc.denali2.estimator.service.staffDriverService;
import com.pwc.denali2.estimator.util.GetPrincipalUtil;
import com.pwc.denali2.estimator.view.summaryExcel;



@Controller
@RequestMapping(value="/user/project")
@PropertySource(value = { "classpath:defaultSettings.properties" })
public class ProjectManagementController {
	
	@Autowired
	private ProjectManagementService projectManagementService;
	@Autowired
	private EngagementShareService engagementShareService;
	@Autowired
	private ModuleTransactionDataService moduleTransactionDataService;
	@Autowired
	private OnsiteCSPRateTransactionService onsiteCSPRateTransactionService;
	@Autowired
	private OffshoreCSPRateTransactionService offshoreCSPRateTransactionService;
	
	@Autowired
	private AccountOpsService accountOpsService;
	
	@Autowired
	private ConversionsDao conversionsDao;
	
	@Autowired
	private DetailedSummaryService detailedSummaryService;
	
	@Autowired
	private EnagagementProgressDao enagagementProgressDao;
	
	@Autowired
	private staffDriverService StaffDriverService;
	
	@Autowired
	private ProjectUnitsDaoImpl projectUnitsDaoImpl;
	
	@Autowired
	private DetailedSummaryDao detailedSummaryDao;
	
	@Autowired
	private SummaryEffortsDao summaryEffortsDao;
	
	@Autowired
	private MasterAdminUtilityDao masterAdminUtilityDao;
	
	private SendMail sendMail = new SendMail();
	
	@Autowired
	private Environment environment;
	
	private String msg = "";
	
	/**
	 * Save or Update a project
	 * @param engagement
	 * @return
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/saveProject", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void saveProject(Engagement engagement,HttpServletResponse response,@RequestParam("enterprise_application") String enterPriseApplicationList) throws UnsupportedEncodingException, MessagingException{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String currentUserGuid = ((UserDetails)principal).getUsername();
		
		if(StringUtils.isEmpty(engagement.getId())){
			if(projectManagementService.checkIfProjectNoExist(engagement.getId(), engagement.getProject_no())){
				writeJson(false,response);
			}
			else{
			
			//save a new project
			engagement.setGuid(currentUserGuid);
			engagement.setCreate_date(new Date());
			engagement.setProject_no(engagement.getProject_no());
			engagement.setUpdate_date(new Date());
			engagement.setEngagement_status("New");
			engagement.setVersion(1);
			
			projectManagementService.saveProject(engagement);
			/*projectManagementService.saveEnterPriseApplication(engagement.getId(),enterPriseApplicationList) ;*/
			
			engagement.setParent(engagement.getId());
			
			projectManagementService.updateProject(engagement);
			
			//onsiteCSPRateTransactionService.copyOnsiteCSPRateMetaData(String.valueOf(engagement.getId()));
		//	offshoreCSPRateTransactionService.copyOffshoreCSPRateMetaData(String.valueOf(engagement.getId()));
			moduleTransactionDataService.copyMetaDataForEngagement(String.valueOf(engagement.getId()));
			StaffDriverService.copyStaffingDrivers(engagement.getId()+"");
			conversionsDao.initComplexity(engagement.getId().toString());
			projectUnitsDaoImpl.listUnit(engagement.getId().toString());
			writeJson(engagement,response);
			}

		}else{
			//send email when status changed
			List<String> engagementStatusList = projectManagementService.getEngagementStatusById(engagement.getId());
			if(engagementStatusList.size()!=0){
				if(!engagementStatusList.get(0).equals(engagement.getEngagement_status())){
					if(engagement.getEstimation_requestor_email()!=null){
						/*sendMail.sendTest(engagement.getEngagement_status(), engagement.getEstimation_requestor_email());*/
					}
				}
			}
			//update a project
			Engagement copyEng = projectManagementService.getEngagementById(engagement.getId());
			String prevCloudOffering = copyEng.getOffering();
			String currentCloudOffering = engagement.getOffering();
			engagement.setUpdate_date(new Date());
			engagement.setVersion(copyEng.getVersion());
			engagement.setParent(copyEng.getParent());
			engagement.setProject_no(copyEng.getProject_no());
			engagement.setVersion(copyEng.getVersion());
			projectManagementService.checkForCloudOffering(engagement,prevCloudOffering,currentCloudOffering);
			projectManagementService.updateProject(engagement);
			projectManagementService.updateEnterPriseApplication(engagement.getId(),enterPriseApplicationList) ;
		}
		
	}
	
	/**
	 * Copy project
	 * @param engagement
	 * @return
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/copy-engagement", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void saveCopyProject(HttpServletResponse response,@RequestParam("engagement_id") Integer engagement_id,@RequestParam("project_no") String project_no,@RequestParam("enterprise_application") String enterPriseApplicationList,@RequestParam("copyList") String copyList) throws UnsupportedEncodingException, MessagingException{
		System.out.println(copyList);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String currentUserGuid = ((UserDetails)principal).getUsername();
		Engagement engagement = projectManagementService.getEngagementById(engagement_id);
		if(projectManagementService.checkIfProjectNoExist(engagement_id, project_no)){
			writeJson(false,response);
		}
		else{
		
			engagement.setId(null);
			//save a new project
			engagement.setProject_no(project_no);
			engagement.setGuid(currentUserGuid);
			engagement.setCreate_date(new Date());
			engagement.setUpdate_date(new Date());
			engagement.setEngagement_status("New");
			engagement.setVersion(1);
			
			projectManagementService.saveProject(engagement);
			
			engagement.setParent(engagement.getId());
			
			projectManagementService.updateProject(engagement);
			projectManagementService.copyProject(copyList,engagement.getId());
			
//			onsiteCSPRateTransactionService.copyOnsiteCSPRateMetaData(String.valueOf(engagement.getId()));
//			offshoreCSPRateTransactionService.copyOffshoreCSPRateMetaData(String.valueOf(engagement.getId()));
//			moduleTransactionDataService.copyMetaDataForEngagement(String.valueOf(engagement.getId()));
//			conversionsDao.initComplexity(engagement.getId().toString());
			writeJson(engagement,response);
		}
	}
	
	/**
	 * Release a project
	 * In engagement version parameter will be used to check if new version is required
	 * @param engagement,versionChange
	 * @return
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/release-project", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void saveProjectRelease(HttpServletResponse response,@RequestParam("enterprise_application") String enterPriseApplicationList,@RequestParam("engagement_id") Integer engagement_id, @RequestParam("version_change") Boolean versionChange) throws UnsupportedEncodingException, MessagingException{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String currentUserGuid = ((UserDetails)principal).getUsername();
		Engagement engagement = projectManagementService.getEngagementById(engagement_id);
		
		Integer eng_id = engagement.getId();
		Integer parent = engagement.getParent();
		Integer oldVersion = engagement.getVersion();
		
		if(!StringUtils.isEmpty(engagement.getId())){
			//Change Status to released and change released by to Current user Id
			
			if(versionChange == true){
				//if version needs to be changed create a new project 
				//with different id and and parent id remaining of previous
				Engagement newCopy = engagement;
				Integer newVersion = newCopy.getVersion() + 1;
				String newProjName = engagement.getProject_no();
				//Code to copy new set of project
				newCopy.setId(null);
				newCopy.setCreate_date(new Date());
				newCopy.setUpdate_date(new Date());
				newCopy.setEngagement_status("New");
				newCopy.setProject_no(newProjName+"-"+newVersion);
				newCopy.setVersion(newVersion);
				newCopy.setParent(parent);
				projectManagementService.saveProject(newCopy);
				
//				projectManagementService.saveEnterPriseApplication(engagement.getId(),enterPriseApplicationList);
				projectManagementService.copyProjectFrRelease(newCopy.getId(),engagement.getId());
				engagement.setProject_no(newProjName);
//				onsiteCSPRateTransactionService.copyOnsiteCSPRateMetaData(String.valueOf(newCopy.getId()));
//				offshoreCSPRateTransactionService.copyOffshoreCSPRateMetaData(String.valueOf(newCopy.getId()));
//				moduleTransactionDataService.copyMetaDataForEngagement(String.valueOf(newCopy.getId()));
				//conversionsDao.initComplexity(engagement.getId().toString());
			}
			engagement.setId(eng_id);
			//engagement.setProject_no(project_no);
			engagement.setVersion(oldVersion);
			engagement.setEngagement_status("Released");
			engagement.setReleasedBy(currentUserGuid);
			//send email when status changed
			List<String> engagementStatusList = projectManagementService.getEngagementStatusById(engagement.getId());
			if(engagementStatusList.size()!=0){
				if(!engagementStatusList.get(0).equals(engagement.getEngagement_status())){
					if(engagement.getEstimation_requestor_email()!=null){
						/*sendMail.sendTest(engagement.getEngagement_status(), engagement.getEstimation_requestor_email());*/
					}
				}
			}
			System.out.println(engagement.getProject_no());
			//update a project
			engagement.setUpdate_date(new Date());
			projectManagementService.updateProject(engagement);
			projectManagementService.updateEnterPriseApplication(engagement.getId(),enterPriseApplicationList) ;
		}
		
	}
	
	@RequestMapping(value = "/download")
	@ResponseBody
	public  void downLoadFile(String filepath,HttpServletResponse response) {
		File file=new File(filepath);
		if (file == null || !file.exists()) {
			writeJson("File not found!",response);
		}
		OutputStream out = null;
		String f= Pattern.compile("\\(\\([\\d]+\\)\\)").matcher(file.getName()).replaceAll("");
		try {
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + f);
			out = response.getOutputStream();
			out.write(FileUtils.readFileToByteArray(file));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping(value = "/upload")
	@ResponseBody
	public void fileupload(@RequestParam MultipartFile file,Integer id,HttpServletResponse response){
		InputStream in=null;
		OutputStream out=null;
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd");
		try{
			String savePath = environment.getRequiredProperty("upload.path");
			savePath+=formatter.format(new Date())+"\\"+id;
			File createfile = new File(savePath);
			if (!createfile.exists() && !createfile.isDirectory()) {
				createfile.mkdirs();
			}
			int num=createfile.list().length+1;
			in = file.getInputStream();
			String filepath=savePath + "\\" + "(("+num+"))"+file.getOriginalFilename();
			out = new FileOutputStream(filepath);
			 byte buffer[] = new byte[1024];
			 int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.flush();
			EngagementAttachment ea=new EngagementAttachment();
			ea.setFile_name_original(file.getOriginalFilename());
			ea.setFile_name_on_server("(("+num+"))"+file.getOriginalFilename());
			ea.setFile_path(filepath);
			ea.setEngagement_id(id);
			ea.setUpload_time(new Date());
			projectManagementService.saveEngagementAttach(ea);
			
			writeJson("success",response);
		}catch (Exception e) {
			writeJson("fail",response);
			e.printStackTrace();
		}finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
		} 
		
	}
	@RequestMapping(value = "/findAllAttachAttachment")
	@ResponseBody
	public void findAllAttachAttachmentByEngagementId(Integer id,HttpServletResponse response){
		List<EngagementAttachment> ls=projectManagementService.findAllAttachAttachmentByEngagementId(id);
		try {
			String json = JSON.toJSONStringWithDateFormat(ls, "MMM-dd-yyyy HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * List available project to current login user
	 * @param response
	 */
	@RequestMapping(value="/listEnterpriseApplicationsByEngagementId",method = RequestMethod.GET)
	@ResponseBody
	public void listEnterpriseApplicationsByEngagementId(HttpServletResponse response,@RequestParam("id") int engagementId){
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> engagementEnterpriseApplicationList = this.projectManagementService.getEnterpriseApplicationListByEngagementId(engagementId);
		map.put("engagementEnterpriseApplicationList", engagementEnterpriseApplicationList);
		writeJson(map,response);
		
	}
	
	/**
	 * List available project to current login user
	 * @param response
	 */
	@RequestMapping(value={"/listAvailableProject","/listAvailableProject/{id}"},method = RequestMethod.GET)
	@ResponseBody
	public void listAvailableProject(Engagement engagement,EngagementShare engagementShare,
			HttpServletResponse response,@PathVariable Optional<Integer> id){
		Map<String,Object> map = new HashMap<String,Object>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String currentUserGuid = ((UserDetails)principal).getUsername();
		User user = accountOpsService.findUserBySsoId(currentUserGuid);
		if(id.isPresent()){
			Engagement engagementList = this.projectManagementService.getEngagementById(id.get());
			map.put("engagementList", engagementList);
		}
		else{
		List<Engagement_share_permission> engagementList = this.projectManagementService.listAvailableProject(currentUserGuid,engagement);
		map.put("engagementList", engagementList);
		}
		List<String> enterpriseApplicationList = this.projectManagementService.getEnterpriseApplicationList();
		List<String> engagementStatusList = this.projectManagementService.getEngagementStatusList();
		List<Map<String, Object>> offeringList = this.projectManagementService.getOfferingList();
		List<String> clientList =this.projectManagementService.getClientList();
		
		map.put("currentGUID", currentUserGuid);
		map.put("offering", offeringList);
		map.put("engagementStatusList", engagementStatusList);
		map.put("enterpriseApplicationList", enterpriseApplicationList);
		map.put("username",user.getFirstName()+" "+user.getLastName());
		map.put("email",user.getEmail());
		map.put("clientList",clientList);
		//map.put("permission", permission);
		
		writeJson(map,response);
		
	}
	
	//listAvailablePermission
	@RequestMapping(value="/listAvailablePermission",method = RequestMethod.GET)
	@ResponseBody
	public void listAvailablePermission(Engagement engagement,EngagementShare engagementShare,
			HttpServletResponse response,@RequestParam("id") int engagementId){
		Map<String,Object> map = new HashMap<String,Object>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String currentUserGuid = ((UserDetails)principal).getUsername();
	
		String permission = this.engagementShareService.getPermissionofUser(currentUserGuid,engagement,engagementId);
		System.out.println(permission+"*************888");
	
		map.put("permission", permission);
		
		writeJson(map,response);
		
	}
	
	@RequestMapping(value="/searchProject",method = RequestMethod.GET)
	@ResponseBody
	public void searchProject(Engagement engagement, HttpServletResponse response){
		
	}
	/**
	 * Share a project with guid
	 * @param engagementShare
	 */
	@RequestMapping(value="/shareProject", method = RequestMethod.GET)
	@ResponseBody
	public void shareProject(EngagementShare engagementShare,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> listSharedNames = this.engagementShareService.shareProject(engagementShare);
		map.put("listSharedNames", listSharedNames);
		writeJson(map,response);
	}
	
	// sharedUsers
	@RequestMapping(value="/sharedUsers", method = RequestMethod.GET)
	@ResponseBody
	public void sharedUsers(EngagementShare engagementShare,HttpServletResponse response){
		System.out.println("into shared");
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> listSharedNames = this.engagementShareService.getSharedNames(engagementShare);
		map.put("listSharedNames", listSharedNames);
		writeJson(map,response);
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
	

	@RequestMapping(value="/getUserInfoByName",method = RequestMethod.POST)
	@ResponseBody
	public void getUserInfoByName(HttpServletRequest request, HttpServletResponse response){
		String currentUserGuid = GetPrincipalUtil.getPrincipal();
		String userName = request.getParameter("userName");
		LdapConfig lc = new LdapConfig();
		String password = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		lc.connetLDAP(currentUserGuid,password);
		List<UserEngagement> ueList = new ArrayList<UserEngagement>();
		ueList = lc.GetADInfo(userName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ueList", ueList);
		writeJson(map, response);
	}
	
	@RequestMapping(value="/enagagement/getProgress/{enagagement_id}",method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public EnagagementProgress getProjectProgress(@PathVariable("enagagement_id") int engagement_id, HttpServletRequest request, HttpServletResponse response){
	
		return enagagementProgressDao.getProgress(engagement_id);
	}
	
	
	
	@RequestMapping(value="/enagagement/saveProgress/{enagagement_id}",method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public void saveProjectProgress(@PathVariable("enagagement_id") String engagement_id,@RequestBody EnagagementProgress data, HttpServletRequest request, HttpServletResponse response){
//		data.setEngagement_id(Integer.parseInt(engagement_id));
		
		enagagementProgressDao.saveProgress(data);
	}
	
	@RequestMapping(value="/enagagement/calculate/{enagagement_id}",method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public boolean saveCalculate(@PathVariable("enagagement_id") String engagement_id,@RequestBody EnagagementProgress data, HttpServletRequest request, HttpServletResponse response){
		projectUnitsDaoImpl.listUnit(engagement_id);
		boolean result  = StaffDriverService.calculateEffort(engagement_id+"");
		enagagementProgressDao.saveProgress(data);
		detailedSummaryService.calculateEffort(Integer.parseInt(engagement_id));
		return result;
	}
	
	@RequestMapping(value = "/summary/export/{enagagement_id}", method = RequestMethod.GET)
	       public ModelAndView getExcel(@PathVariable("enagagement_id") int engagement_id){
				  Map<String, Object> model = new HashMap<String, Object>();
				  	model.put("detailed", detailedSummaryDao.list(engagement_id));
					Map<String, List<List>> prototype = masterAdminUtilityDao.listByTypeTag(Arrays.asList("prototype"), "");
					Map<String, List<List>> workstream = masterAdminUtilityDao.listByTypeTag(Arrays.asList("workstream"), "");
					
					model.put("sumeffort", summaryEffortsDao.getSummaryEffortForEngId(engagement_id));
					
					model.put("effort", detailedSummaryDao.listDetailedEffortExport(engagement_id));
					model.put("effortGroup", detailedSummaryDao.listDetailedEffortGroups(engagement_id));
					
					model.put("workstream", workstream.get("workstream"));
					model.put("prototype", prototype.get("prototype"));
					
	              return new ModelAndView(new summaryExcel(), "DetailedSummary",model );
	       }
	
}
 