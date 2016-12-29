<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<title>Modify</title>
	<link href="<c:url value="/resources/css/page.css" />" rel="stylesheet">
</head>
<body>

<%@include file="header/header.jsp" %>

<c:if test="${success != null}">
	<div class="yellowBox">
		<c:choose>		
				<c:when test="${success == true}">
					Your task was updated!
				</c:when>
				<c:otherwise>
					There was a problem while updating your task!
				</c:otherwise>
		</c:choose>
	</div>
</c:if>

<c:if test="${success == null}">
	<div class="yellowBox">
		<form:form class="form-horizontal" method="post"
		                modelAttribute="status" action="modify">
		   
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
		   <label>Select an activity</label>
		   <form:select path="description">
		    	<form:options items="${task}" />
		   </form:select>
		         
		   <form:hidden path="id" />
		   <form:hidden path="timestamp" />      
		         
		   <br/>      
		   <button type="submit">Modify</button>
		                
		</form:form>
	</div>
</c:if>

</body>
</html>
