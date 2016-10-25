<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Table details</title>
</head>
<body>
Input column details:
	<%
	String column_number_string=request.getAttribute("column_number").toString();
	out.print(column_number_string);
	for( int i=1;i<=Integer.parseInt(column_number_string);i++)
	{
		out.print(" <p>Column name:<input type=\"text\" id=\"column_name"+ i+ "\" " +"name=\""+" column_name"+i+"\"required=\"required\" /></input> </p>");
		out.print(" <p>Column length:<input type=\"text\" id=\"column_length"+i+ "\" "+"name=\""+"column_length"+i+"\" required=\"required\" /></input> </p>");
	}
	out.print("	<p id=\"int_column_number\" value=$column_number_string></p>");// 
	%>
	
	<script>
	function show_table()
	{	
		document.print("-------------<br>");
		for(var i=1;i<document.getElementById("int_column_name");i++)
		{
			document.print("+++++++++++++++<br>");
			document.print(document.getElementById("column_name1").innerHTML);	
		}
	};
	</script>
	
	<button onclick="show_table()"> click_here </button>	

	<br><br>
	<a href="create_table?new_table_information=asdf"> Submit<br><br></a>

</body>
</html>