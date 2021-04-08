package com.pwc.denali2.estimator.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pwc.denali2.estimator.model.EnagagementProgress;
import com.pwc.denali2.estimator.model.LockUser;
import com.pwc.denali2.estimator.model.User;
import com.pwc.denali2.estimator.service.AccountOpsService;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AccountManagementController {
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
	private AccountOpsService accountOpsService;
	
	@Autowired
	private MetadataManager metadata;
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model, HttpSession session) {
		String userCredential = getPrincipal();
		session.setAttribute("loggedinuser", userCredential);

		if ("anonymousUser".equals(userCredential)) {
			return "redirect:/login";
		} else {
			String userprofile;
			User user = accountOpsService.findUserBySsoId(userCredential);
			if (user != null){
				userprofile = user.getUserProfiles().getType();
				session.setAttribute("userprofile", userprofile);
			}
		}

		return "redirect:/blank";
	}
	
	@RequestMapping(value = "/blank", method = RequestMethod.GET)
	public String blankPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = ((HttpServletRequest) request).getSession();
		String sessionProjId = (String) session.getAttribute("current_proj_id_token");
		if(sessionProjId != null){
			return "redirect:/user/home?engagement="+sessionProjId;
		}
		return "redirect:/user/home";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());

		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage( Model model) {
		if (isCurrentAuthenticationAnonymous()) {
			Set<String> idps = metadata.getIDPEntityNames();
			for (String idp : idps)
			model.addAttribute("idps", idps);
			return "login";
		} else {
			return "redirect:/user/home";
		}
	}
	
	@RequestMapping(value = "/logoutBefore", method = RequestMethod.GET)
	public String logoutBefore( Model model) {
		String UserId = this.getPrincipal();
		if ("anonymousUser".equals(UserId)) {
		}else{
			accountOpsService.removeLock(UserId);
		}
			return "redirect:/logout";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}

		return "redirect:/login?logout";
	}
	
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authenticationTrustResolver.isAnonymous(authentication);
	}
	
	
	@RequestMapping(value="/lockcheck/{engagementId}",method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public Map<String,Object> performLockcheck(@PathVariable("engagementId") String engagementId, HttpServletRequest request, HttpServletResponse response){
		String UserId = this.getPrincipal();
		Map<String,Object> lock = new HashMap<String, Object>();
		if ("anonymousUser".equals(UserId)) {
			return lock;
		}else{
		LockUser user = null;
		boolean result = accountOpsService.checkLock(UserId, engagementId);
		if(!result){
			user = accountOpsService.getLockUser(engagementId);
		}
		lock.put("status", result ? "true" : "false");
		lock.put("user",user );
		return lock;
		}
		
	}
}