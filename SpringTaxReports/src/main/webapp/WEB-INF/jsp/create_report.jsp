<%@ taglib prefix="locale" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <c:if test="${command == 'correct' }">
	    <title><locale:message code="correct_report.title"/></title>
    </c:if>
    <c:if test="${command != 'correct' }">
	    <title><locale:message code="create_report.title"/></title>
	</c:if>
</head>
<body>
<div>
	<form:form method="POST" modelAttribute="userForm" action="change-locale"
	          class="box login">	 
		<fieldset class="boxBody">	 
		<span style="float: right">
		<!--
			<a href="?lang=en">en</a>
			<a href="?lang=ua">ua</a>
		-->
		    <c:if test="${command == 'correct' }">
			    <a href="?lang=en&reportId=${id}">en</a>
				<a href="?lang=ua&reportId=${id}">ua</a>
		    </c:if>
		    <c:if test="${command != 'correct' }">
			    <a href="?lang=en">en</a>
				<a href="?lang=ua">ua</a>
			</c:if>
	    </span>
	</form:form>
	<form:form method="POST"  modelAttribute="taxReportForm" >
	    <c:if test="${command == 'correct' }">
			<h2><locale:message code="correct_report.title"/><br></h2>
	    </c:if>
	    <c:if test="${command != 'correct' }">
			<h2><locale:message code="create_report.title"/><br></h2>
		</c:if>
		
		<div>
			<form:input type="hidden" path="id" value="${id}"></form:input><br>
			
			<label><locale:message code="create_report.pib"/></label><br>
			<form:input type="text" path="pib" autofocus="true"></form:input><br>
<!--
			<form:errors path="pib"></form:errors>
		    ${pibError}
-->
		
			<label><locale:message code="create_report.identnumber"/></label><br>
			<form:input type="text" path="identnumber" value="${identnumber}"></form:input><br>
			
     		<label><locale:message code="create_report.taxdepartment"/></label><br>
			<form:input type="text" path="taxdepartment" value="${taxdepartment}"></form:input><br>

     		<label><locale:message code="create_report.adress"/></label><br>
			<form:input type="text" path="adress" value="${adress}"></form:input><br>

     		<label><locale:message code="create_report.workers"/></label><br>
			<form:input type="text" path="workers" value="${workers}"></form:input><br>

     		<label><locale:message code="create_report.taxgroup"/></label><br>
			<form:input type="text" path="taxgroup" value="${taxgroup}"></form:input><br>

     		<label><locale:message code="create_report.taxperiod"/></label><br>
			<form:input type="text" path="taxperiod" value="${taxperiod}"></form:input><br>

     		<label><locale:message code="create_report.taxdate"/></label><br>
			<form:input type="text" path="taxdate" value="${taxdate}"></form:input><br>

     		<label><locale:message code="create_report.taxcode"/></label><br>
			<form:input type="text" path="taxcode" value="${taxcode}"></form:input><br>

     		<label><locale:message code="create_report.taxincome"/></label><br>
			<form:input type="text" path="taxincome" value="${taxincome}"></form:input><br>

			<form:input type="hidden" path="controller" value="${controller}"></form:input><br>
			<form:input type="hidden" path="owner" value="${owner}"></form:input><br>

			<form method ="POST" action="save_report">
				<input type="hidden" name="action" value="save_report"/>
				<c:if test="${command == 'correct' }">
					<button type="submit"><locale:message code="correct_report.button" /></button>
				</c:if>			
				<c:if test="${command != 'correct' }">
					<button type="submit"><locale:message code="create_report.button" /></button>
				</c:if>			
			</form>
		</div>
	</form:form>
    
    <a href="/"><locale:message code="index.title"/></a>
</div>
</body>
</html>