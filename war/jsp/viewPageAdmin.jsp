<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Found Pages</title>

<c:forEach items = "${it.postsList}" var = "post" >

  	<p>Content: <c:out value = "${post.postContent}"></c:out> </p>
	<p>Time: <c:out value = "${post.postTimestamp}"></c:out> </p>
	<p>Privacy: <c:out value = "${post.privacy}"></c:out> </p>
	<p>Seen By: <c:out value = "${post.numOfSeens}"></c:out> </p>
  
<p>__________________________________________________________ </p>

<br><br>
</c:forEach>


</head>
<body>

</body>
</html>