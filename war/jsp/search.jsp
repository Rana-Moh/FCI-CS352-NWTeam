
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Search</title>
<style>
.center {
    margin-left: auto;
    margin-right: auto;
    width: 70%;
    hight:30%;
    background-color: #b0e0e6;
}
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

</style>
</head>
<body class="body">

<img src="${pageContext.request.contextPath}/search.jpg"  style="width:304px;height:228px">
<div class="container"  style="width:400px; margin-right:auto; margin-left:auto; color:blue;">

	<section id="content">
<form action="/social/doSearch" method="post">
 <div>
  <input type="text" placeholder="uname" required="" name="uname" /><br/><br/>
  </div>
  <div>
  <input type="submit" value="Search" style="center" class="button">
  </div>
  </form>
  <div class="button">
  </div><!-- button -->
   </section><!-- content -->
</div><!-- container -->
</body>
</html>