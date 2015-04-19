<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Found HashTags</title>

<c:forEach items = "${it.hashtagsList}" var = "hashtags" >

<p>type of post: <c:out value = "${hashtags.Where}"></c:out> </p>
<p>place: <c:out value = "${hashtags.postPlace}"></c:out> </p>
<p>writer: <c:out value = "${hashtags.writerEmail}"></c:out> </p>
<p>content: <c:out value = "${hashtags.postContent}"></c:out> </p>
<p>feelings: <c:out value = "${hashtags.feeling}"></c:out> </p>
<p>privacy: <c:out value = "${hashtags.privacy}"></c:out> </p>
<p>likes: <c:out value = "${hashtags.numOfLikes}"></c:out> </p>
<p>time: <c:out value = "${hashtags.postTimestamp}"></c:out> </p>

<p>__________________________________________________________ </p>

<br><br>
</c:forEach>
</head>
<body>



<%




%>
</body>
</html>