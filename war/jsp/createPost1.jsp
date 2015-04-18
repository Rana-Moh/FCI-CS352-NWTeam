<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	this is my time line
	<form action="/social/TimeLine1" method="POST">
		<fieldset style="width: 220" align="center">
			<legend width:200px>My time line</legend>
			Content:<br>
			<!-- <textarea class="FormElement" name="postContent" cols="40" rows="4"></textarea> -->
			<input type="text" name="content" cols="40" rows="4"/> <br>
			<br> <input type="submit" value="Post">
			     <select name="Feelings">
			     <option value="empty">Feeling</option>
				<option value="happy">happy :)</option>
				<option value="sad">sad :(</option>
				<option value="exited">exited ^_^</option>
				</select>
							     
		</fieldset>
	</form>



</body>
</html>