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

<c:if test="${success != null}">
	<div class="yellowBox">
		<c:choose>		
				<c:when test="${success == true}">
					Your task was recorded!
				</c:when>
				<c:otherwise>
					There was a problem while saving your task!
				</c:otherwise>
		</c:choose>
	</div>
</c:if>

<c:if test="${success == null}">
	<div class="yellowBox">
		<form:form class="form-horizontal" method="post"
		                modelAttribute="status" action="submit">
		   
		   <label>Select a person</label>
		   <form:select path="name">
		   		<form:option value="select" label="--- Select ---"/>
		   		<c:forEach items="${names}" var="name1">
		    		<form:option value="${name1}"/>
		    	</c:forEach>
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
		         
		   <br/>      
		   <button type="submit">Submit</button>
		                
		</form:form>
	</div>
</c:if>

<c:if test="${validationError != null}">
	<div class="yellowBox">
		Validation failed, invalid request was sent to server! Maybe you didn't select any person...
	</div>
</c:if>

</body>
</html>
