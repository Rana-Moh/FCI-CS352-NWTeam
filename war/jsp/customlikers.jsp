<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ page import="com.FCI.SWE.Controller.PostController"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>your Friends</title>


</head>
<body>
	<form action="/social/getCustomFriends" method="get">
		<table Border="3" BorderColor="black" width="300" cellSpacing="1"
			CellPadding="3">

			<TR>
				<th ColSpan="3"><font size="4"><b>the page likers</b></font>
			</TR>

			<%
				for (int i = 0; i < PostController.Pagelikers.size(); i++) {
			%>
			<tr Align="Center">
				<TD BGColor="white"><Font size="4"><I> <input
							type="checkbox" name="Email"
							value="<%=PostController.Pagelikers.get(i)%>">

					</I></Font></td>

				<td BGColor="white"><Font size="4"><I> <%
 	out.println(PostController.Pagelikers.get(i));
 %>

					</I></Font></TD>
			</tr>
			<%
				}
			%>

		</table>
		<input type="submit" value="Finish">
	</form>




</body>
</html>