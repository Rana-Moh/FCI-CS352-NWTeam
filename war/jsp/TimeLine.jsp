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


	<form action="/social/AllPosts" method="POST">
		<fieldset style="width: 220" align="center">
			<legend width:200px>My time line</legend>
			Content:<br>
			<!-- <textarea class="FormElement" name="postContent" cols="40" rows="4"></textarea> -->
			<input type="text" name="postContent" cols="40" rows="4"/> <br>
			<br> <input type="submit" value="Post">
	
				<input type="submit" value="Like"> 
				<input type="submit"	value="Share">
			    <INPUT TYPE="radio" NAME="privcy" VALUE="public" > public
			    <INPUT TYPE="radio" NAME="privcy" VALUE="private" >private
		</fieldset>
	</form>



</body>
</html>