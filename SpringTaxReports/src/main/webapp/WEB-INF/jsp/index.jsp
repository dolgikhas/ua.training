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
				<input type="button" value="en" onClick='location.href="?lang=en"'/>
				<input type="button" value="ua" onClick='location.href="?lang=ua"'/>			  
	    	</span>
	    </form:form>

		<div>
			<h3>${pageContext.request.userPrincipal.name}</h3>
			<sec:authorize access="!isAuthenticated()">
			    <input type="button" value=<locale:message code="index.login"/>
			    		onClick='location.href="/login"'/><br>    
			    <input type="button" value=<locale:message code="index.registration"/>
			    		onClick='location.href="/registration"'/><br>
			</sec:authorize>			
			
			<sec:authorize access="hasRole('ROLE_USER')">
				<input type="button" value=<locale:message code="index.create_report"/>
			    		onClick='location.href="/create_report"'/><br> 
				<input type="button" value=<locale:message code="index.statistic_reports"/>
			    		onClick='location.href="/statistic_reports_user"'/><br>

			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<input type="button" value=<locale:message code="index.statistic_reports"/>
			    		onClick='location.href="/statistic_reports_admin"'/><br>

			</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
				<input type="button" value=<locale:message code="index.logout"/>
			    		onClick='location.href="/logout"'/><br> 
			</sec:authorize>
			
		</div>
	</body>
</html>