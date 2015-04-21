<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
    <%@ page import="com.FCI.SWE.Controller.notificationContoller"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>

<%
	out.println("this person like ur post : "+notificationContoller.who);
	out.println();
%>
<br>
<%	
	out.println("ur post type: "+notificationContoller.type);
%>
<br>
<%
	out.println("ur post content : "+notificationContoller.content);
%>
<br>
<% 	out.println("feeling : "+notificationContoller.feelings);
%>
<br>
<%	
	out.println("ur post time : "+notificationContoller.time);
%>
<br>
<%	
	out.println("ur post place : "+notificationContoller.place);
%>
<br>
<%
	out.println("ur post privacy : "+notificationContoller.privacy);
%>

</body>
</html>