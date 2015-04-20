<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Found Pages</title>

<c:forEach items = "${it.pagesList}" var = "page" >

  	<p>Page Name: <c:out value = "${page.name}"></c:out> </p>
	<p>Page Type: <c:out value = "${page.type}"></c:out> </p>
	<p>Page Category: <c:out value = "${page.category}"></c:out> </p>
  
<p>__________________________________________________________ </p>

<br><br>
</c:forEach>

  <form action="/social/likePage" method="post">
  
  	<p> Page Name : <input type="text" name="pageName" /> <br> </p>
  	<input type="submit" value="Like Page">
  	
  </form>
  
    <form action="/social/viewPage" method="post">
  
  	<p> Page Name : <input type="text" name="pageNameView" /> <br> </p>
  	<input type="submit" value="View Page">
  	
  </form>

</head>
<body>

</body>
</html>