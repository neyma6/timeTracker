<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
	<title>List Team</title>
	<link href="<c:url value="/resources/css/page.css" />" rel="stylesheet">
</head>
<body>

<jsp:include page='header/header.jsp'>
    <jsp:param name="activeItem" value="listTeam"/>
</jsp:include>

<div class="containerBox">
	<form:form class="form-horizontal" method="post"
	                modelAttribute="report" action="listTeam">
	 
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
	<div class="containerBox">
		Validation failed, invalid request was sent to server! Maybe you didn't select a person...
	</div>
</c:if>

<c:if test="${start != null}">
	<div class="containerBox center">
	<br><hr class="horizontalRule"/></hr></br>
		<label>${start} - ${end}</label>
	</div>
</c:if>

<c:if test="${statuses != null && statuses.size() > 0}">
	<div class="containerBox center">
		Working hours in this week: ${workingHours}
	</div>
</c:if>


<c:if test="${statuses != null && statuses.size() > 0}">

	<div class="containerBox">
	<br><hr class="horizontalRule"/></hr></br>
		<ul>
			<c:forEach items="${activityWithHours}" var="entry">
				<li>${entry.key}: <strong>${entry.value}</strong> hour(s) </li>
			</c:forEach>
		</ul>
	</div>

	<table class="containerBox">
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
	<div class="containerBox">
		No data for this week
	</div>
</c:if>


</body>
</html>
