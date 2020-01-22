<%@ taglib prefix="locale" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><locale:message code="registration.title"/></title>
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
		<form:form method="POST" modelAttribute="userForm">
	    <h2><locale:message code="registration.title"/></h2>
		    <div>
		    	<form:input type="text" path="username" placeholder="Username"
		                  autofocus="true"></form:input>
		    	<form:errors path="username"></form:errors>
		        ${usernameError}
		    </div>
		    <div>
		      <form:input type="password" path="password" placeholder="Password"></form:input>
		    </div>
			<div>
				<form:input type="password" path="passwordConfirm"
			                  placeholder="Confirm your password"></form:input>
			   	<form:errors path="password"></form:errors>
			        ${passwordError}
			</div>
	    	<button type="submit">
	    		<font size="2"><locale:message code="registration.button"/></font>
	    	</button>
		</form:form>

	    <!--	<input type="button" value=<locale:message code="index.title"/>
			onClick='location.href="/"'/><br>	-->
		<button type="submit" onClick='location.href="/"'
				class="button">
			<font size="2"><locale:message code="index.title"/></font>
		</button><br>

	</div>
</body>
</html>