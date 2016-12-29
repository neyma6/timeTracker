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
	                modelAttribute="report" action="list">
	   
	   <label>Select a person</label>
	   <form:select path="name">
		   		<form:option value="select" label="--- Select ---"/>
		   		<c:forEach items="${names}" var="name1">
		    		<form:option value="${name1}"/>
		    	</c:forEach>
	   </form:select>	
	   <br/>
	   <label>Select which week would like to query</label>
	 
		<form:select path="week">
		   		<c:forEach items="${weeks}" var="week1" varStatus="loop">
		    		<form:option value="${loop.index + 1}" label="${week1}"/>
		    	</c:forEach>
		</form:select>	 
	         
	   <br/>      
	   <button type="submit">List</button>
	                
	</form:form>
</div>

<c:if test="${validationError != null}">
	<div class="yellowBox">
		Validation failed, invalid request was sent to server! Maybe you didn't select any person...
	</div>
</c:if>

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
	        <th>Modify</th>
	        <th>Delete</th>
	    </tr>
	    <c:forEach items="${statuses}" var="status">
	        <tr>
	            <td>${status.name}</td>
	            <td>${status.time}</td>
	            <td>${status.description}</td>
	            <td>${status.displayDate}</td>
	            <td>
	            	<form action="modify">
	            		<input type="hidden" name="id" value="${status.id}">
	            		<input type="submit" value="Modify">
	            	</form>
	            </td>
	            <td>
	            	<form action="delete">
	            		<input type="hidden" name="id" value="${status.id}">
	            		<input type="submit" value="Delete">
	            	</form>
	            </td>
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
