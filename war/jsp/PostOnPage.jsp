<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
</body>
</html>