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
	String cg=request.getAttribute("table_list").toString();
    String big_string=request.getAttribute("table_list").toString();
    String string_arr[]=big_string.split("~");
    int table_numbers=string_arr.length-1;
    if(table_numbers!=0)
    {
    	out.print(string_arr[0]+"<br>");
        out.print("	<table border=\"1\"> <tr> <th>Isbn</th> <th>Name</th> </tr>");
        
        for(int i=0;i<table_numbers+1;i=i+1)
        {
        	out.print("<tr><td>");	
        	out.print(string_arr[i]);    	
        	out.print("</td><td>");
        	out.print("<a href=\"create_little_thing_detail_java?table_name="+(string_arr[i])+"\">");
        	out.print(string_arr[i]); 
        	out.print("</a>");
        	out.print("</td></tr>");
        }
        out.print("</table>");    	
    }
    else
    {
    	out.print("None");	
    }
    %>
    
	
	
	
	
	



</body>
</html>