<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
	<title>Delete Activities</title>
	<link href="<c:url value="/resources/css/page.css" />" rel="stylesheet">
</head>
<body>

<jsp:include page='header/adminheader.jsp'>
    <jsp:param name="activeItem" value="activityDel"/>
</jsp:include>

<c:if test="${activities != null && activities.size() > 0}">

	<table class="containerBox">
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
	<div class="containerBox">
		No data
	</div>
</c:if>


</body>
</html>
