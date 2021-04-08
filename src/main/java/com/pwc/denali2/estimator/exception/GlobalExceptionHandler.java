package com.pwc.denali2.estimator.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.http.HttpStatus;

@Controller
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final int ERROR_403_CODE = 403;
	private static final int ERROR_500_CODE = 500;
	private static final int GENERIC_ERROR_CODE = 9999;

	static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Handle request not valid
	 * 
	 * @param request
	 * @param response
	 * @param ex
	 * @return 404 page
	 */
	@ExceptionHandler({ NoHandlerFoundException.class })
	public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, HttpServletResponse response,
			Exception ex) {
		logger.error("Requested URL=" + request.getRequestURL());
		logger.error("Exception Raised=" + ex);

		clearUserAuthentication(request, response);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", request.getRequestURL());

		modelAndView.setViewName("404");
		return modelAndView;
	}

	/**
	 * Handle server internal error
	 * 
	 * @param request
	 * @param response
	 * @param ex
	 * @return
	 */

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGlobalException() {

		return "redirect:/server_internal_error";
	}

	@RequestMapping(value = "/server_internal_error", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> handleGlobalError(HttpServletRequest request) {
		logger.error("Requested URL=" + request.getRequestURL());

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("CODE", ERROR_500_CODE);

		return returnMap;
	}

	@RequestMapping(value = "/error_500", method = RequestMethod.GET)
	public String getGlobalError(HttpServletRequest request, HttpServletResponse response) {
		clearUserAuthentication(request, response);
		return "500";
	}

	/**
	 * Handle AccessDenied error
	 * 
	 * @param request
	 * @param response
	 * @param ex
	 * @return
	 */

	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDeniedException() {

		return "redirect:/access_denied";
	}

	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> handleAccessDeniedException(HttpServletRequest request) {
		logger.error("Requested URL=" + request.getRequestURL());

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("CODE", ERROR_403_CODE);

		return returnMap;
	}

	@RequestMapping(value = "/error_403", method = RequestMethod.GET)
	public String getAccessDeniedException(HttpServletRequest request, HttpServletResponse response) {
		clearUserAuthentication(request, response);
		return "403";
	}

	/**
	 * Handle business exception
	 * 
	 * @param request
	 * @param response
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	public String handleBusinessException() {
		return "redirect:/generic_error";
	}

	@RequestMapping(value = "/generic_error", method = RequestMethod.GET)
	public String handleGenericError(HttpServletRequest request, HttpServletResponse response) {
		logger.error("Requested URL=" + request.getRequestURL());
		clearUserAuthentication(request, response);
		return "redirect:/login?autherror";
	}

	@RequestMapping(value = "/error_9999", method = RequestMethod.GET)
	public String getGenericError(HttpServletRequest request, HttpServletResponse response) {
		clearUserAuthentication(request, response);
		return "redirect:/login?autherror";
	}

	/**
	 * clear user authentication information while exceptions occured
	 * 
	 * @param request
	 * @param response
	 */
	private void clearUserAuthentication(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
	}
}