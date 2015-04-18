<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>add member</title>
<style>

.button
{
    width:150px;
    height: 60px;
    color: blue;
    line-height: 20px;
    padding-bottom: 2px;
    vertical-align: middle;
    font-family: font-family:verdana;
    font-size: 40px;
  
}
body 
{
   background-image: url("${pageContext.request.contextPath}/pic2.png");
}
p {border-style: solid;}
p.solid 
{
 border-bottom-style: solid;
 border-width: 120px;
 border-width: 120px;
 border-color: #000000;

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
</head>
<body class="body">
<img src="${pageContext.request.contextPath}/pic.jpg"  style="width:304px;height:228px">
<div class="container"  style="width:400px; margin-right:auto; margin-left:auto; color:blue;">
<form action="/social/addMember" method="get">
    <input type="text"  placeholder="Conversation Name" name="cname" /> <br><br>
    <input type="text" placeholder="Member Email" name="email" /> <br><br> 
<input type="submit" value="send" class="button"> </form> 
	</section><!-- content -->
</div><!-- container -->
</body>
</html>