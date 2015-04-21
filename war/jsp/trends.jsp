<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
    <%@ page import="com.FCI.SWE.Controller.HashTagTrendsController "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<form action="/social/searchHashtag" method="post">
<%
out.print(HashTagTrendsController.trend.size()-1);
%>
<%
for(int i =0; i<HashTagTrendsController.trend.size(); i++)
{
	
%>
<input type="radio" name="hashtag" value='<%= HashTagTrendsController.trend.get(i)%>'>
<%	
		out.print(HashTagTrendsController.trend.get(HashTagTrendsController.trend.size()-1-i)+ " ");
		out.println("  ------  ");
%>


<%	
		out.println("   ");
%>
<%
}
%>
<input type=submit value="see posts">
</form>
</body>
</html>