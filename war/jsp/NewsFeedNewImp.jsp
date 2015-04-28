<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.FCI.SWE.Controller.NewsFeedController"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>your timeline</title>



</head>
<body>
	<p>${it.timelineList }</p>



	<%
		out.println("the number of posts is :" + NewsFeedController.counter);
		int counter = 0;
	%>
	<c:forEach items="${it.timeline}" var="timeline">
		<%
			counter++;
		%>
	
		<p>
			type of post:
			<c:out value="${timeline.where}"></c:out>
		</p>
		<p>
			place:
			<c:out value="${timeline.postPlace}"></c:out>
		</p>
		<p>
			writer:
			<c:out value="${timeline.writerEmail}"></c:out>
		</p>
		<p>
			content:
			<c:out value="${timeline.postContent}"></c:out>
		</p>
		<p>
			feelings:
			<c:out value="${timeline.feeling}"></c:out>
		</p>
		<p>
			privacy:
			<c:out value="${timeline.privacy}"></c:out>
		</p>
		<p>
			likes:
			<c:out value="${timeline.numOfLikes}"></c:out>
		</p>
		<p>
			time:
			<c:out value="${timeline.postTimestamp}"></c:out>
		</p>
		
		
		<form action="/social/postLike" method="post">
			<input type="hidden" value=" <%=counter%>" name="counter">
			
			<input type="submit" value="like post">
			
		</form>
		
		<form action="/social/sharePost" method="post">
			<input type="hidden" value=" <%=counter %>" name="counter">
			
			<input type="submit" value="like post">
			<p>__________________________________________________________</p>
		</form>
		<br>
		<br>
	</c:forEach>




	<%
		
	%>
</body>
</html>