<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
   pageContext.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1 , maximum-scale=1.0">
		<title>Login page</title>
		<link rel="icon" type="image/ico" href="${basePath}/static/favicon.ico"/>
		<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
		<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
	</head>

	<body>
	
		<div id="mainWrapper">
			
			<div class="login-container">
				<div class="login-card">
					<div class="login-form">
						<div class='login-logo-group'><img src="${basePath}/static/custom/PwC_logo.png"/><span>Denali Cloud Estimator</span></div>
						<h2 class="login-header">LOGIN</h2>
						<c:url var="loginUrl" value="/login" />
						<form action="${loginUrl}" method="post" class="form-horizontal">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Invalid username and password.</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>You have been logged out successfully.</p>
								</div>
							</c:if>
							<c:if test="${param.autherror != null}">
								<div class="alert alert-danger">
									<p>Unauthorized User.</p>
								</div>
							</c:if>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
								<input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
							</div>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
								<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
							<div class="form-actions">
								<input type="submit"
									class="btn btn-block btn-primary btn-default" value="Submit">
							</div>
						</form>
						
						<a href="${basePath}/saml/login"
									class="btn  btn-block btn-primary" value="SSO"><img class="pull-left" width="30px" src="${basePath}/static/custom/PwC_logo.png"/> Login with PwC 
							</a>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>