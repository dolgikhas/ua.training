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
			<a href="?lang=en">en</a>
			<a href="?lang=ua">ua</a>
		<!--
		    <c:if test="${command == 'correct' }">
			    <a href="?lang=en&reportId=${id}">en</a>
				<a href="?lang=ua&reportId=${id}">ua</a>
		    </c:if>
		    <c:if test="${command != 'correct' }">
			    <a href="?lang=en">en</a>
				<a href="?lang=ua">ua</a>
			</c:if>
		-->
	    </span>
	</form:form>
	<form method="POST">
		<h2><locale:message code="comment_report.title"/><br></h2>
		
		<div>
			<input type="hidden" name="id" value="${id}" /> 
		
			<label><locale:message code="comment_report.label"/></label><br>
			<input name="comment" type="text" autofocus="true"/><br>

			<button type="submit"><locale:message code="comment_report.button" /></button>
		</div>
	</form>
    
    <a href="/"><locale:message code="index.title"/></a>
</div>
</body>
</html>