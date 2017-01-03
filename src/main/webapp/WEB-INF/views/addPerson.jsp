<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<title>Add Person</title>
	<link href="<c:url value="/resources/css/page.css" />" rel="stylesheet">
</head>
<body>

<%@include file="header/adminheader.jsp" %>

<c:if test="${success != null}">
	<div class="yellowBox">
		<c:choose>		
				<c:when test="${success == true}">
					Person was recorded!
				</c:when>
				<c:otherwise>
					There was a problem while saving person!
				</c:otherwise>
		</c:choose>
	</div>
</c:if>

<c:if test="${success == null}">
	<div class="yellowBox">
		<form:form class="form-horizontal" method="post"
		                modelAttribute="person" action="addPerson">
		   
		   <label>Add person</label>
		   <form:input path="name" /> 
		      
		   <button type="submit">Add</button>
		                
		</form:form>
	</div>
</c:if>

<c:if test="${validationError != null}">
	<div class="yellowBox">
		Validation failed, invalid request was sent to server! Maybe you didn't write anything...
	</div>
</c:if>

</body>
</html>
