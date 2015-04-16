<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ page import="com.FCI.SWE.Models.NotificationEntity"%>
<%@ page import ="java.util.ArrayList" %>
<%@ taglib prefix="c" 
		uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Notifications</title>
</head>
<body>

<form action="/social/parseNotification" method="get">
<table Border="3" BorderColor="black" width="500" cellSpacing="1"
			CellPadding="3">

			<TR>
				<th ColSpan="3"><font size="4"><b>the Notifications</b></font>
			</TR>

			<%
				for (int i = 0; i < NotificationEntity.type.size(); i++) {
			%>
			<tr Align="Center">
				<TD BGColor="white"><Font size="4"><I>


					</I></Font></td>

				<td BGColor="white"><Font size="4"><I>
				<input
							type="radio"  name="type"
							value="<%= NotificationEntity.type.get(i)%>"
				 >
				
				<input
							type="hidden"  name="paramters"
							value='<%= NotificationEntity.parameters.get(i)%>'>
				
				 <%
 				out.println(NotificationEntity.type.get(i)+"   :    "+NotificationEntity.parameters.get(i) );
                %>

					</I></Font></TD>
			</tr>
			<%
				}
			%>

		</table>
		<br/>
		<input type="submit" value="see notification">
	</form>



</body>
</html>