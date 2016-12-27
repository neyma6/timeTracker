<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
	<title>List</title>
	<link href="<c:url value="/resources/css/page.css" />" rel="stylesheet">
</head>
<body>

<%@include file="header/header.jsp" %>

<div class="yellowBox">
	<form:form class="form-horizontal" method="post"
	                modelAttribute="report" action="listTeam">
	 
	   <label>Select which week would like to query. (1 - this week, 2 - prev week...)</label>
	   <form:select path="week">
	    	<form:options items="${weeks}" />
	   </form:select>
	 
	         
	   <br/>      
	   <button type="submit">Submit</button>
	                
	</form:form>
</div>

<c:if test="${start != null}">
	<div class="yellowBox">
		${start} - ${end}
	</div>
</c:if>

<c:if test="${statuses != null && statuses.size() > 0}">
	<div class="yellowBox">
		Working hours in this week: ${workingHours}
	</div>
</c:if>

<c:if test="${statuses != null && statuses.size() > 0}">
	<table class="yellowBox">
	    <tr>
	        <th>Name</th>
	        <th>Time</th>
	        <th>Description</th>
	        <th>Date</th>
	    </tr>
	    <c:forEach items="${statuses}" var="status">
	        <tr>
	            <td>${status.name}</td>
	            <td>${status.time}</td>
	            <td>${status.description}</td>
	            <td>${status.displayDate}</td>
	        </tr>
	    </c:forEach>
	</table>
</c:if>

<c:if test="${statuses != null && statuses.size() == 0}">
	<div class="yellowBox">
		No data for this week
	</div>
</c:if>


</body>
</html>