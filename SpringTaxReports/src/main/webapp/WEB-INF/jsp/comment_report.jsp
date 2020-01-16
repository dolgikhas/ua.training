<%@ taglib prefix="locale" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <title><locale:message code="comment_report.title"/></title>
</head>
<body>
<div>
	<form:form method="POST" modelAttribute="userForm" action="change-locale"
	          class="box login">	 
		<fieldset class="boxBody">	 
		<span style="float: right">
			<input type="button" value="en" onClick='location.href="?lang=en"'/>
			<input type="button" value="ua" onClick='location.href="?lang=ua"'/>			  
	    </span>
	</form:form>
	<form method="POST">
		<h2><locale:message code="comment_report.title"/><br></h2>
		<div>
			<input type="hidden" name="id" value="${id}" /> 
		
			<label><locale:message code="comment_report.label"/></label><br>
			<input name="comment" type="text" autofocus="true"/><br>

			<button type="submit"><locale:message code="comment_report.button" /></button><br>
		</div>
	</form>
    
    <!--	<a href="/"><locale:message code="index.title"/></a>	-->
    <input type="button" value=<locale:message code="index.title"/>
		onClick='location.href="/"'/><br>    
</div>
</body>
</html>