<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ page import="com.FCI.SWE.Controller.PostController"%>
<%@ page import ="java.util.ArrayList" %>
<%@ taglib prefix="c" 
		uri="http://java.sun.com/jsp/jstl/core"%>
=======
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Friend timeline list</title>
</head>
<body>
<<<<<<< HEAD
Choose a friend to write to his/her timeline

 
<form action="/social/createPost1" method="get">
		<table Border="3" BorderColor="black" width="300" cellSpacing="1"
			CellPadding="3">

			<TR>
				<th ColSpan="3"><font size="5"><color="white"><b>Your Friends</b></font>
			</TR>

			<%
				for (int i = 0; i < PostController.friends1.size(); i++) {
			%>
			<tr Align="Center">
				<TD BGColor="white"><Font size="4"><I>
				 <input
							type="radio"  name="Email"
							value="<%= PostController.friends1.get(i) %>"
				 >

					</I></Font></td>

				<td BGColor="white"><Font-size="4"><I> <%
 	out.println(PostController.friends1.get(i));
 %>

					</I></Font></TD>
			</tr>
			<%
				}
			%>

		</table>
		 <br/><br/>
				<input type="submit" value="Creat Post" class="button">
	</form>
=======
Choose a friend to write to his/her timeline 
friend list in radio buttons 
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
</body>
</html>