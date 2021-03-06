admin_<%@ taglib prefix="locale" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><locale:message code="statistic_reports.title"/></title>
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
	<h2><locale:message code="statistic_reports.title"/></h2>
	<c:if test="${number_reports > 0}">
		<table cellspacing="1" border="1" cellpadding="5"
				bgcolor="#c0c0c0" text-align="center">
		    <thead>
			    <th><locale:message code="statistic_reports.id"/></th>
			    <th><locale:message code="statistic_reports.pib"/></th>
			    <th><locale:message code="statistic_reports.identnumber"/></th>
			    <th><locale:message code="statistic_reports.taxdepartment"/></th>
			    <th><locale:message code="statistic_reports.adress"/></th>
			    <th><locale:message code="statistic_reports.workers"/></th>
			    <th><locale:message code="statistic_reports.taxgroup"/></th>
			    <th><locale:message code="statistic_reports.taxperiod"/></th>
			    <th><locale:message code="statistic_reports.taxdate"/></th>
			    <th><locale:message code="statistic_reports.taxcode"/></th>
			    <th><locale:message code="statistic_reports.taxincome"/></th>
			    <th><locale:message code="statistic_reports.status"/></th>
			    <th><locale:message code="statistic_reports.controller"/></th>
		    </thead>
		    <c:forEach items="${allTaxReports}" var="taxreport">
		    	<tr>
			        <td>${taxreport.id}</td>
			        <td>${taxreport.pib}</td>
			        <td>${taxreport.identnumber}</td>
			        <td>${taxreport.taxdepartment}</td>
			        <td>${taxreport.adress}</td>
			        <td>${taxreport.workers}</td>
			        <td>${taxreport.taxgroup}</td>
			        <td>${taxreport.taxperiod}</td>
			        <td>${taxreport.taxdate}</td>
			        <td>${taxreport.taxcode}</td>
			        <td>${taxreport.taxincome}</td>
			        <td><locale:message code="create_report.status.${taxreport.status}"/>
			        	<c:if test="${taxreport.status == 'correct'}">
			        		<br><label>(${taxreport.comment})</label>
			        	</c:if>
			        	<c:if test="${taxreport.status == 'reject'}">
			        		<br><label>(${taxreport.comment})</label>
			        	</c:if>
			        </td>
			        <td>${taxreport.controller}</td>
			        <td>
						<form action="statistic_reports" method="post">
							<c:if test="${taxreport.status == 'correct'}">					        
				        		<input type="hidden" name="command" value="correct">
				        		<input type="hidden" name="reportId" value="${taxreport.id}">
				        		<button type="submit">
				        			<locale:message code="statistic_reports.button.correct"/>
								</button>
				        	</c:if>
				        	
							<c:if test="${taxreport.status == 'reject'}">					        
				        		<input type="hidden" name="command" value="change_controller">
				        		<input type="hidden" name="reportId" value="${taxreport.id}">
				        		<button type="submit">
				        			<locale:message code="statistic_reports.button.change_controller"/>
								</button>
				        	</c:if>
			        	</form>
			        </td>
		    	</tr>
		    </c:forEach>
		</table>
	</c:if>
	
	<c:if test="${number_reports == 0}">
		<label><locale:message code="statistic_reports.label.empty_list"/></label><br>
	</c:if>
	
    <input type="button" value=<locale:message code="index.title"/>
		onClick='location.href="/"'/><br>
		 
	</div>
</body>
</html>