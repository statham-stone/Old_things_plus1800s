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
	String user_id_string=request.getAttribute("user_id").toString();
	String table_name_string=request.getAttribute("table_name").toString();
	out.print(column_number_string);
	for( int i=1;i<=Integer.parseInt(column_number_string);i++)
	{
		out.print(" <p>Column name:<input type=\"text\" id=\"column_name"+ i+ "\" " +"name=\""+" column_name"+i+"\"required=\"required\" /></input> </p>");
		out.print(" <p>Column length:<input type=\"text\" id=\"column_length"+i+ "\" "+"name=\""+"column_length"+i+"\" required=\"required\" /></input> </p>");
	}
	out.print("	<input type=\"text\" id=\"int_column_number\"  value=\""+column_number_string+"\"></input>");// 
	out.print("	<input type=\"text\" id=\"table_name\"  value=\""+table_name_string+"\"></input>");// 
	out.print("	<input type=\"text\" id=\"user_id\"  value=\""+user_id_string+"\"></input>");// 
	%>
	<script>
	function show_table()
	{		
		var big_string="";
		big_string=document.getElementById("user_id").value;
		big_string=big_string+"~"+document.getElementById("table_name").value;
		big_string=big_string+"~"+document.getElementById("int_column_number").value;
		for(var i=1;i<=document.getElementById("int_column_number").value;i=i+1)
		{		
			big_string=big_string+"~"+document.getElementById("column_name"+i).value;
			big_string=big_string+"~"+document.getElementById("column_length"+i).value;
		}
		document.write("<a href=\"create_table?new_table_information="+big_string+"\"> Submit (you will not see this next version)<br><br></a>");
	};
	</script>
	
	<button onclick="show_table()"> click_here </button>	
</body>
</html>