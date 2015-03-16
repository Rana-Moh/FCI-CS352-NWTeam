<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<form action="/social/doSearch" method="post">
  Username : <input type="text" name="uname" /> <br>
  <input type="submit" value="Search">
  
	</form>
<br><br>


	<form action="/social/RequestAlreadySent" method="post">
  Enter Friend Email : <input type="text" name="friendEmail" /> <br>
  
  <input type="submit" value="SendFriendRequest">
  
  </form>
  <br>
  
	<a href="/social/logout/">Log out</a> <br>
</body>
</html>