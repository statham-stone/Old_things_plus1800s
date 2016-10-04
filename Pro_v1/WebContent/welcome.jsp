<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>

<style>
.area {
float: left;
margin: 5px;
padding: 15px;
width: 300px;
height: 400px;
border: 1px solid grey;
} 
</style>
</head>
<body>
<h3>Hello World!</h3>
<h4>${Prompt }</h4>

<div class="area">
<h3>Sign up</h3>
<form action="sign" method="get">
<p>USERNAME:<input type="text" name="Username"/></p>
<p>PASSWORD:<input type="password" name="Password"/></p>
<input align="left" type="submit" value="SIGN UP"/>
</form>
</div>

<div class="area">
<h3>Log in</h3>
<form action="log" method="get">
<p>USERNAME:<input type="text" name="Username"/></p>
<p>PASSWORD:<input type="password" name="Password"/></p>
<input align="left" type="submit" value="LOG IN"/>
</form>
</div>

</body>
</html>