<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
	<title>Delete Persons</title>
	<link href="<c:url value="/resources/css/page.css" />" rel="stylesheet">
</head>
<body>

<jsp:include page='header/adminheader.jsp'>
    <jsp:param name="activeItem" value="personDel"/>
</jsp:include>

<c:if test="${persons != null && persons.size() > 0}">

	<table class="containerBox">
	    <tr>
	        <th>Name</th>
	        <th>Delete</th>
	    </tr>
	    <c:forEach items="${persons}" var="person">
	        <tr>
	            <td>${person}</td>
	            <td>
	            	<form action="deletePerson" method="post">
	            		<input type="hidden" name="name" value="${person}">
	            		<input type="submit" value="Delete">
	            	</form>
	            </td>
	        </tr>
	    </c:forEach>
	</table>
</c:if>

<c:if test="${persons != null && persons.size() == 0}">
	<div class="containerBox">
		No data
	</div>
</c:if>


</body>
</html>
