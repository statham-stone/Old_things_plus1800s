<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

    
    <%
	String column_name_string=request.getAttribute("column_names").toString();
    String column_name[]=column_name_string.split("~");
	for(int i=0;i<column_name.length;i++)
	{
		out.print(" <p>"+column_name[i]+"<input type=\"text\" id=\"column_name"+ i+ "\" " +"name=\""+" column_name"+i+"\"required=\"required\" /></input> </p>");
	}
	
	out.print("	<input type=\"text\" id=\"int_column_number\"  value=\""+column_name.length+"\"></input>");// 
	%>
    
	<script>
	function show_details()
	{		
		var big_string="";
		var number=document.getElementById("int_column_number").value;
		//big_string=document.getElementById("user_id").value;
		//big_string=big_string+"~"+document.getElementById("table_name").value;
		//big_string=big_string+"~"+document.getElementById("int_column_number").value;
		for(var i=0;i<number;i=i+1)
		{		
			big_string=big_string+"~"+document.getElementById("column_name"+i).value;
		}
		big_string=big_string.substring(1, big_string.length);//delete the first"~"
		
		document.write("<a href=\"create_little_thing_java?information="+big_string+"\"> Submit (you will not see this next version)<br><br></a>");
	};
	</script>
	
	
	
	
	
	<button onclick="show_details()"> click_here </button>	
	
	
	
	



</body>
</html>