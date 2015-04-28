<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ page import="com.FCI.SWE.Controller.MsgController"%>
<<<<<<< HEAD
<%@ page import ="java.util.ArrayList" %>
<%@ taglib prefix="c" 
		uri="http://java.sun.com/jsp/jstl/core"%>
=======
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>your Friends</title>
<<<<<<< HEAD
<style>

.button
{
    width:120px;
    height: 30px;
    color: blue;
    line-height: 20px;
    padding-bottom: 2px;
    vertical-align: middle;
    font-family: font-family:verdana;
    font-size: 20px;
  
}
body 
{
   background-image: url("${pageContext.request.contextPath}/pic2.png");
}
h1{ 
    display: block;
    font-size: 2em;
    margin-top: 0.67em;
    margin-bottom: 0.67em;
    margin-left: 0;
    margin-right: 0;
    font-weight: bold;
}
</style>

</head>
<body class="body">
<img src="${pageContext.request.contextPath}/ms.jpg"  style="width:304px;height:228px">
<div class="container"  style="width:400px; margin-right:auto; margin-left:auto; color:blue;">

	<section id="content">
=======


</head>
<body>
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
	<form action="/social/msg" method="get">
		<table Border="3" BorderColor="black" width="300" cellSpacing="1"
			CellPadding="3">

			<TR>
<<<<<<< HEAD
				<th ColSpan="3"><font size="5"><color="white"><b>Your Friends</b></font>
=======
				<th ColSpan="3"><font size="4"><b>your Friends</b></font>
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
			</TR>

			<%
				for (int i = 0; i < MsgController.friends.size(); i++) {
			%>
			<tr Align="Center">
<<<<<<< HEAD
				<TD BGColor="white"><Font size="4"><I>
				 <input
							type="checkbox"  name="Email"
							value="<%= MsgController.friends.get(i)  %>"
				 >

					</I></Font></td>

				<td BGColor="white"><Font-size="4"><I> <%
=======
				<TD BGColor="white"><Font size="4"><I> <input
							type="checkbox" name="Email"
							value="<%=MsgController.friends.get(i)%>">

					</I></Font></td>

				<td BGColor="white"><Font size="4"><I> <%
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
 	out.println(MsgController.friends.get(i));
 %>

					</I></Font></TD>
			</tr>
			<%
				}
			%>

		</table>
<<<<<<< HEAD
		 <br/><br/>
				<input type="submit" value="send msg" class="button">
	</form>

</section><!-- content -->
</div><!-- container -->
=======
		<input type="submit" value="send msg">
	</form>




>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
</body>
</html>