<%@ taglib prefix="locale" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title><locale:message code="index.title"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
	</head>
	<body>
		<form:form method="POST" modelAttribute="userForm" action="change-locale"
	           class="box login">	 
	  		<fieldset class="boxBody">	 
		    <span style="float: right">
			    <a href="?lang=en">en</a>
			    <a href="?lang=ua">ua</a>
	    	</span>
	    </form:form>

		<div>
			<h3>${pageContext.request.userPrincipal.name}</h3>
			<sec:authorize access="!isAuthenticated()">
				<h4><a href="/login"><locale:message code="index.login"/></a></h4>
				<h4><a href="/registration"><locale:message code="index.registration"/></a></h4>
			</sec:authorize>			
			
			<sec:authorize access="hasRole('ROLE_USER')">
				<h4><a href="/create_report"><locale:message code="index.create_report"/></a></h4>
			</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
				<h4><a href="/statistic_reports"><locale:message code="index.statistic_reports"/></a></h4>
				<h4><a href="/logout"><locale:message code="index.logout"/></a></h4>
			</sec:authorize>
			
		</div>
	</body>
</html>