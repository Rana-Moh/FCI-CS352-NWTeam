<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>reply</title>
<style>

.button
{
    width:250px;
    height: 30px;
    color: blue;
    line-height: 20px;
    padding-bottom: 2px;
    vertical-align: middle;
    font-family: font-family:verdana;
    font-size: 15px;
  
}
body 
{
   background-image: url("${pageContext.request.contextPath}/pic2.png");
}
p 
{

    font-size: 1em;
    color:#FF3399;
    font-weight: bold;
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
<img src="${pageContext.request.contextPath}/r.jpg"  style="width:304px;height:228px">
<div class="container"  style="width:400px; margin-right:auto; margin-left:auto; color:blue;">
<form action="/social/replymsg" method="get">

	<input type="text" placeholder="Conversation reply name" name="cname" ><br/><br/>
	
	<input type="submit" value="see if conversation exists"class="button"/>
</form>
	</section><!-- content -->
</div><!-- container -->
</body>
</html>