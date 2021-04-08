package com.pwc.denali2.estimator.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pwc.denali2.estimator.exception.BusinessException;
import com.pwc.denali2.estimator.model.User;
import com.pwc.denali2.estimator.service.AccountOpsService;
import com.pwc.denali2.estimator.util.GetPrincipalUtil;

@Aspect
@Component
public class CheckUserCredentialsAspect {

	static final Logger logger = LoggerFactory.getLogger(CheckUserCredentialsAspect.class);

	@Autowired
	private AccountOpsService accountOpsService;

	@Pointcut("@annotation(com.pwc.denali2.estimator.aop.CheckUserValidAction)")
	public void checkUserLoggedInAspect() {
	}

	@Before("execution(* com.pwc.denali2.estimator.service..*(..)) && @annotation(com.pwc.denali2.estimator.aop.CheckUserValidAction)")
	public void doBefore(JoinPoint joinPonit) throws Exception {
		String ssoId = GetPrincipalUtil.getPrincipal();
//		String ss = GetPrincipalUtil.getCredential();
		MethodSignature methodSignature = (MethodSignature) joinPonit.getSignature();
		Method method = methodSignature.getMethod();

		User user = accountOpsService.findUserBySsoIdWithoutAOP(ssoId);
		
		if (user != null ) {
			logger.info("Start executing " + method.getName());
			logger.info(user.toString());
		} else {
			throw new BusinessException("Current loggedin user " + ssoId + "is invalid");
		}
	}
}
