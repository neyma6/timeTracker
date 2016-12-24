<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<title>Home</title>
</head>
<body>

<form:form class="form-horizontal" method="post"
                modelAttribute="status" action="submit">
   
   <label>Resource</label>
   <form:select path="name">
    	<form:options items="${names}" />
   </form:select>
   <br/>
   <label>Working Time</label>
   <form:select path="time">
    	<form:options items="${time}" />
   </form:select>
   
   <br/>
   <label>Description</label>
   <form:input path="description" />
         
   <br/>      
   <button type="submit">Submit</button>
                
</form:form>


</body>
</html>
