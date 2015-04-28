<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ page import="com.FCI.SWE.Controller.PagePostController"%>
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
<<<<<<< HEAD
<title>page list</title>
</head>
<body>
Choose a page to write post

 
<form action="/social/createPostPage" method="get">
		<table Border="3" BorderColor="black" width="300" cellSpacing="1"
			CellPadding="3">

			<TR>
				<th ColSpan="3"><font size="5"><color="white"><b>Your Pages</b></font>
			</TR>

			<%
				for (int i = 0; i < PagePostController.pageName.size(); i++) {
			%>
			<tr Align="Center">
				<TD BGColor="white"><Font size="4"><I>
				 <input
							type="radio"  name="Email"
							value="<%= PagePostController.pageName.get(i)  %>"
				 >

					</I></Font></td>

				<td BGColor="white"><Font-size="4"><I> <%
 	out.println(PagePostController.pageName.get(i));
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
<title>Insert title here</title>
</head>
<body>
this in post in page


<form action="/social/AllPosts" method = "POST">
<fieldset style="width:220" align="center">

<legend width:200px >Page post</legend>
Content:<br>
<!-- <textarea class="FormElement" name="postContent" cols="40" rows="4"></textarea> -->
<input type="text" name="postContent" />

<br><br>
<input type="submit" value="Post" >
<!-- <input type="submit" value="Like"> -->
<!-- <input type="submit" value="Share"> -->


</fieldset>
</form>
>>>>>>> 5f1d5ad63d94a61f202cd273ef05201d2fd41463
</body>
</html>