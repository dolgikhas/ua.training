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
			<input type="button" value="en" onClick='location.href="?lang=en"'/>
			<input type="button" value="ua" onClick='location.href="?lang=ua"'/>			  
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
			<form:input type="hidden" path="id" value="${id}"></form:input>
			
			<label><locale:message code="create_report.pib"/></label><br>
			
			<form:input type="text" path="pib"
				pattern="[А-ЩЬЮЯҐІЇЄа-щьюяґіїє'\sА-А-ЩЮЯІЇЄ.А-ЩЮЯІЇЄ.]{1,25}"
				placeholder="Прізвище І.Б."
				autofocus="true"></form:input><br>
		
			<label><locale:message code="create_report.identnumber"/></label><br>
			<form:input type="number" min="10000000" max="9999999999"
				path="identnumber" value="${identnumber}"></form:input><br>
			
     		<label><locale:message code="create_report.taxdepartment"/></label><br>
			<form:input type="number" min="1" max="999" path="taxdepartment"
				value="${taxdepartment}"></form:input><br>

     		<label><locale:message code="create_report.adress"/></label><br>
			<form:input type="text" path="adress"
				pattern="[А-ЩЬЮЯҐІЇЄа-щьюяґіїє'\s.,0-9]{1,25}"			
				value="${adress}"></form:input><br>

     		<label><locale:message code="create_report.workers"/></label><br>
			<form:input type="number" min="1" max="99999" path="workers"
				value="${workers}"></form:input><br>

     		<label><locale:message code="create_report.taxgroup"/></label><br>
			<form:input type="number" min="1" max="3" path="taxgroup"
				value="${taxgroup}"></form:input><br>

     		<label><locale:message code="create_report.taxperiod"/></label><br>
			<form:input type="tel" pattern="\d\d\d\d, \d" placeholder="####, #"
				path="taxperiod" value="${taxperiod}"></form:input><br>

     		<label><locale:message code="create_report.taxdate"/></label><br>
			<c:if test="${taxdate != 0}">
				<form:input type="date" min="2020-01-01" max="2020-12-01"
					path="taxdate" value="${taxdate}"></form:input><br>
			</c:if>
			<c:if test="${taxdate == 0}">
				<form:input type="date" min="2020-01-01" max="2020-12-01"
					path="taxdate" value="2020-01-01"></form:input><br>
			</c:if>
			

     		<label><locale:message code="create_report.taxcode"/></label><br>
			<form:input type="tel" pattern="\d\d.\d\d" placeholder="##.##"
				path="taxcode" value="${taxcode}"></form:input><br>

     		<label><locale:message code="create_report.taxincome"/></label><br>
			<form:input type="number" min="1" max="99999999" path="taxincome" value="${taxincome}"></form:input><br>
			

			<form:input type="hidden" path="controller" value="${controller}"></form:input><br>
			<form:input type="hidden" path="owner" value="${owner}"></form:input>

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
    
     <input type="button" value=<locale:message code="index.title"/>
		onClick='location.href="/"'/><br>    
</div>
</body>
</html>
