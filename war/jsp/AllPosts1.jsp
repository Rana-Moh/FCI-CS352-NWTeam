<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ page import="com.FCI.SWE.Controller.PostController"%>
<%@ page import ="java.util.ArrayList" %>
<%@ taglib prefix="c" 
		uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http`://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
				for (int i = 0; i < PostController.posts.size(); i++){ %>
					
					<% out.println(PostController.posts.get(i));%>
					<% out.println(PostController.likes.get(i));%>
					 <br/><br/> <br/><br/> <br/><br/> <br/><br/>
				<%}%>
			
</body>
</html>