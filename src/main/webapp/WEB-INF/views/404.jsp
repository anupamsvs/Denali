<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
   pageContext.setAttribute("basePath", basePath);
%>
<html>
	<head>
		<link rel="icon" type="image/ico" href="${basePath}/static/favicon.ico"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Page Not Found</title>
		<link href="<c:url value='/static/css/error.css' />" rel="stylesheet"></link>
	</head>

	<body>
	<div class="error-container">
		<div class="error-type">404</div>
		<div class="error-type-discription">Page Not Found.</div>
		<span class="error-direction">Please try the following pages</span>
		<a class="error-links"  href="<c:url value="/login" />">Back to Login</a>
	</div>
</html>