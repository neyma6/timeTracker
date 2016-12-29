<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
	<title>Delete</title>
	<link href="<c:url value="/resources/css/page.css" />" rel="stylesheet">
</head>
<body>

<%@include file="header/header.jsp" %>

<c:if test="${nodata != null}">
	<div class="yellowBox">
		You can't delete this item, item doesn't exist.
	</div>
</c:if>

<c:if test="${status != null}">
	<table class="yellowBox">
	    <tr>
	        <th>Name</th>
	        <th>Time</th>
	        <th>Description</th>
	        <th>Date</th>
	    </tr>
	        <tr>
	            <td>${status.name}</td>
	            <td>${status.time}</td>
	            <td>${status.description}</td>
	            <td>${status.displayDate}</td>
	        </tr>
	</table>
	
	<div class="yellowBox">
		Do you really want to delete this item?
		<div style="display: inline-flex">
			<form action="delete" method="post">
			      <input type="hidden" name="id" value="${status.id}">
			      <input type="submit" value="Delete">
			</form>
			<form action="list">
			      <input type="submit" value="Cancel">
			</form>
		</div>
	</div>
</c:if>

<c:if test="${success != null}">
	<div class="yellowBox">
		<c:choose>		
				<c:when test="${success == true}">
					Deleted!
				</c:when>
				<c:otherwise>
					There was a problem while deleting your status!
				</c:otherwise>
		</c:choose>
	</div>
</c:if>


</body>
</html>
