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
	<!--
	<script>
		alert( 'Привет, мир!' );
	</script>

	<!--
	<style>
	.button {
	  display: inline-block;
	  font-size: 18px;
	  cursor: pointer;
	  text-align: center;
	  text-decoration: none;
	  outline: none;
	  color: #fff;
	  background-color: #e7e7e7; color: black; /* Gray */
	  border: 1px solid #555555; /* Black */
	  border-radius: 12px;
	  width: 160px;
	}	
	</style>
	-->
	
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
				<locale:message code="login.button"/></span>
			</button><br>
		
		
		    <!--	<input type="button" value=<locale:message code="index.registration"/>
				onClick='location.href="/registration"'/><br>	-->
			<button type="submit" onClick='location.href="/registration"'
					class="button">
				<locale:message code="index.registration"/>
			</button><br>
				    
	    </div>
	  </form>
	</div>

</body>
</html>