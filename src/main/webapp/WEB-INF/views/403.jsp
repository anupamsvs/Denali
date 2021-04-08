<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Forbidden</title>
		<link href="<c:url value='/static/css/error.css' />" rel="stylesheet"></link>
	</head>

	<body>
	<div class="error-container">
		<div class="error-type">403</div>
		<div class="error-type-discription">Forbidden</div>
		<span class="error-direction">Please try the following pages</span>
		<a class="error-links"  href="<c:url value="/login" />">Back to Login</a>
	</div>
</html>