<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<title>Home</title>
</head>
<body>

<form:form class="form-horizontal" method="post"
                modelAttribute="report" action="list">
   
   <label>Resource</label>
   <form:select path="name">
    	<form:options items="${names}" />
   </form:select>
   <br/>
   <label>Week before</label>
   <form:select path="week">
    	<form:options items="${weeks}" />
   </form:select>
 
         
   <br/>      
   <button type="submit">Submit</button>
                
</form:form>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Time</th>
        <th>Description</th>
        <th>Date</th>
    </tr>
    <c:forEach items="${statuses}" var="status">
        <tr>
            <td>${status.id}</td>
            <td>${status.name}</td>
            <td>${status.time}</td>
            <td>${status.description}</td>
            <td>${status.timestamp}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
