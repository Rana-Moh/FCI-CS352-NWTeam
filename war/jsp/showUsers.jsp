<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Found Users</title>

<c:forEach items = "${it.usersList}" var = "user" >

<p>Username: <c:out value = "${user.name}"></c:out> </p>
<p>User Email: <c:out value = "${user.email}"></c:out> </p>

<br><br>
</c:forEach>
</head>
<body>



<%




%>
</body>
</html>