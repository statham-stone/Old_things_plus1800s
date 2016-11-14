<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>测试</title>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script type="text/javascript">
   $(function() {
     $("#tj").click(function() {

    //提交的参数，name和inch是和struts action中对应的接收变量
        var params = {
           name : $("#xm").val(),
           inch : $("#sg").val()
        };
        $.ajax({
    type: "POST",
    url: "jsonAjax.action",
    data: params,
    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
    success: function(json){  
    	console.log(json);
    var obj = $.parseJSON(json);  //使用这个方法解析json
                var state_value = obj.result;  //result是和action中定义的result变量的get方法对应的
    alert(state_value);
    },
    error: function(json){
    	console.log(json);
     alert("json=" + json);
     return false;
    }
    });
     });
   });
</script>
</head>
<body>
  <span>姓名：</span><input id="xm" type="text">
  <br/>
  <span>身高：</span><input id="sg" type="text">
  <br/>
  <input type="button" value="提交" id="tj">
</body>
</html> 