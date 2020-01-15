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
			<a href="?lang=en">en</a>
			<a href="?lang=ua">ua</a>
	    </span>
	</form:form>

	<sec:authorize access="isAuthenticated()">
	  <% response.sendRedirect("/"); %>
	</sec:authorize>
	
	<div>
	  <form method="POST" action="/login">
	    <h2><locale:message code="login.title"/></h2>
	    <div>
	      <input name="username" type="text" placeholder="Username"
	             autofocus="true"/>
	      <input name="password" type="password" placeholder="Password"/>
	      <button type="submit"><locale:message code="login.button"/></button>
	      <h4><a href="/registration"><locale:message code="login.registration"/></a></h4>
	    </div>
	  </form>
	</div>

</body>
</html>