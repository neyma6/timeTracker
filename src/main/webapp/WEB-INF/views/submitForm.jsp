<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<title>Submit</title>
	<link href="<c:url value="/resources/css/page.css" />" rel="stylesheet">
</head>
<body>

<%@include file="header/header.jsp" %>

<div class="yellowBox">
	<form:form class="form-horizontal" method="post"
	                modelAttribute="status" action="submit">
	   
	   <label>Choose a person</label>
	   <form:select path="name">
	    	<form:options items="${names}" />
	   </form:select>
	   <br/>
	   <label>How many hours did you work?</label>
	   <form:select path="time">
	    	<form:options items="${time}" />
	   </form:select>
	   
	   <br/>
	   <label>Please give some description about your work (Mingle/Jira links etc...)</label>
	   <form:textarea path="description" rows="5" cols="100" />
	         
	   <br/>      
	   <button type="submit">Submit</button>
	                
	</form:form>
</div>

</body>
</html>
