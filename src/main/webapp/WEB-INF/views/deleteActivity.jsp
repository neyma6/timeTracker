<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
	<title>List</title>
	<link href="<c:url value="/resources/css/page.css" />" rel="stylesheet">
</head>
<body>

<%@include file="header/adminheader.jsp" %>



<c:if test="${activities != null && activities.size() > 0}">

	<table class="yellowBox">
	    <tr>
	        <th>Name</th>
	        <th>Delete</th>
	    </tr>
	    <c:forEach items="${activities}" var="activity">
	        <tr>
	            <td>${activity}</td>
	            <td>
	            	<form action="deleteActivity" method="post">
	            		<input type="hidden" name="name" value="${activity}">
	            		<input type="submit" value="Delete">
	            	</form>
	            </td>
	        </tr>
	    </c:forEach>
	</table>
</c:if>

<c:if test="${activities != null && activities.size() == 0}">
	<div class="yellowBox">
		No data
	</div>
</c:if>


</body>
</html>
