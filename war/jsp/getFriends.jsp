<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ page import="com.FCI.SWE.Controller.MsgController"%>
<%@ page import ="java.util.ArrayList" %>
<%@ taglib prefix="c" 
		uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>your Friends</title>


</head>
<body>
	<form action="/social/msg" method="get">
		<table Border="3" BorderColor="black" width="300" cellSpacing="1"
			CellPadding="3">

			<TR>
				<th ColSpan="3"><font size="4"><b>your Friends</b></font>
			</TR>

			<%
				for (int i = 0; i < MsgController.friends.size(); i++) {
			%>
			<tr Align="Center">
				<TD BGColor="white"><Font size="4"><I>
				 <input
							type="checkbox"  name="Email"
							value="<%= MsgController.friends.get(i)  %>"
				 >

					</I></Font></td>

				<td BGColor="white"><Font size="4"><I> <%
 	out.println(MsgController.friends.get(i));
 %>

					</I></Font></TD>
			</tr>
			<%
				}
			%>

		</table>
				<input type="submit" value="send msg">
	</form>




</body>
</html>