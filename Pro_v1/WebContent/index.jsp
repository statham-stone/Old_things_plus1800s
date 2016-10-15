<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>${Prompt}</p><p>${ID}</p>

<form action="ntable" method="get">
<input type="hidden" name="ID" value="${ID}"/>
<input type="submit" value="test new"/>
</form>
</body>
</html>