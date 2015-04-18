
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
<style>

.button
{
    width:90px;
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

<body class="body" >
<img src="${pageContext.request.contextPath}/so.jpg"  style="width:304px;height:228px">

<h1 style="color:white" >Social Media</h1>
<p style="font-size :20px;" > Welcome  ${it.name} </p>
<a href="/social/search/" style="color:#0066FF;font-size:20px;">Search for a friend</a> <br><br>
<a href="/social/SendFriendRequest/"  style="color:#0066FF;font-size:20px;">SendFrienRequest</a> <br><br>
<a href="/social/AddFriend/"  style="color:#0066FF;font-size:20px;">Accept Friend Request</a> <br><br>
<a href="/social/Group/group/"  style="color:#0066FF;font-size:20px;">Create a Group</a> <br><br>
<a href="/social/logout/"  style="color:#0066FF;font-size:20px;">Log out</a> <br><br>
<a href="/social/getFriends/"  style="color:#0066FF;font-size:20px;">create new chat</a><br><br>
<a href="/social/reply/"  style="color:#0066FF;font-size:20px;">reply to an existing chat</a><br><br>
<a href="/social/add/"  style="color:#0066FF;font-size:20px;">add a new member to an existing chat</a><br/><br>
<a href="/social/AllNotification/"  style="color:#0066FF;font-size:20px;">view all notifications</a><br/><br>
<a href="/social/postPlace/"  style="color:#0066FF;font-size:20px;">Choose place to post</a><br/><br>
<a href="/social/page/"  style="color:#0066FF;font-size:20px;">Create a Page</a><br/><br>
<a href="/social/pageSearch/"  style="color:#0066FF;font-size:20px;">Search for a Page</a><br/><br>


</body>
</html>