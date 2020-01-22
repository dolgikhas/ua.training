<%@ taglib prefix="locale" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title><locale:message code="login.title"/></title>
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

	<sec:authorize access="isAuthenticated()">
	  <% response.sendRedirect("/"); %>
	</sec:authorize>
	
	<div>
	  <form method="POST" action="/login">
	    <h2><locale:message code="login.title"/></h2>
	    <div>
			<label><locale:message code="login.username"/></label><br>	    
			<input name="username" type="text" placeholder="Username"
				autofocus="true"/><br>	
			<label><locale:message code="login.password"/></label><br>	    
			<input name="password" type="password" placeholder="Password"/><br>
			<button type="submit" class="button">
				<!--<font size="2"><locale:message code="login.button"/></font>	-->
				<locale:message code="login.button"/>
			</button><br>
		
			<button type="submit" onClick='location.href="/registration"'
					class="button">
				<locale:message code="index.registration"/>
			</button><br>				    
	    </div>
	  </form>
	</div>

</body>
</html>