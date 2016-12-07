<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->
    <head>
    
    	<!-- Javascript Libraries -->
        <!-- jQuery -->
        <script src="js/jquery.min.js"></script> 
        
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js"></script>
        
        <!--  Form Related -->
        <script src="js/icheck.js"></script> <!-- Custom Checkbox + Radio -->
        
        <!-- All JS functions -->
        <script src="js/functions.js"></script>
    
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <meta name="format-detection" content="telephone=no">
        <meta charset="UTF-8">

        <meta name="description" content="Violate Responsive Admin Template">
        <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">

        <title>Login</title>
            
        <!-- CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/form.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/generics.css" rel="stylesheet"> 
        
        <!-- Check Cookies for user information -->
        <script>
			function setCookies(c_name,value,expiredays)
			{
				var exdate = new Date()
				exdate.setDate(exdate.getDate()+expiredays)
				document.cookie=c_name+ "=" +escape(value)+
					((expiredays==null) ? "" : "; expires="+exdate.toGMTString())
			}
			
			function getCookie(c_name)
			{
			if (document.cookie.length>0)
			  {
			  c_start=document.cookie.indexOf(c_name + "=")
			  if (c_start!=-1)
			    { 
			    c_start=c_start + c_name.length+1 
			    c_end=document.cookie.indexOf(";",c_start)
			    if (c_end==-1) c_end=document.cookie.length
			    return unescape(document.cookie.substring(c_start,c_end))
			    } 
			  }
			return ""
			}
			
			function checkCookies()
			{
				<!--redirect was tested-->
				uid=getCookie('uid')
				if (uid!=null && uid!="") <!-- should redirect-->
				{window.location.href="./index.jsp"	}
			}
			
			$(function() {
				$("#signBtn").click(function() {
					var params = {
				    	username : $("#signUsername").val(),
				    	password : $("#signPassword").val()
					};
					$.ajax({
				    	type: "POST",
				    	url: "ajaxUserLogin.action",
				    	data: params,
				    	dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
				    	success: function(json){  
				    		var obj = $.parseJSON(json);  //使用这个方法解析json
				            var state_value = obj.result;  //result是和action中定义的result变量的get方法对应的
				    		if (state_value=="-1")
				    		{
				    			alert("login failed");
				    			setCookies('uid',"",4)
				    		} else {
				    			alert("login successful");
				    			setCookies('uid',state_value,4);
				    			window.location.href="./index.jsp";
				    		};
				    	},
				    	error: function(json){
				    		console.log(json);
				     		return false;
				    	}
				    });
				});
			});

			
			$(function() {
				$("#regBtn").click(function() {
					var params = {
				    	username : $("#regUsername").val(),
				    	password : $("#regPassword").val()
					};
					$.ajax({
				    	type: "POST",
				    	url: "ajaxUserRegister.action",
				    	data: params,
				    	dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
				    	success: function(json){  
				    		var obj = $.parseJSON(json);  //使用这个方法解析json
				            var state_value = obj.result;  //result是和action中定义的result变量的get方法对应的
				    		if (state_value=="-1")
				    		{
				    			alert("register failed");
				    			setCookies('uid',"",4)
				    		} else {
				    			alert("register successful");
				    			setCookies('uid',state_value,4);
				    			window.location.href="./index.jsp";
				    		};
				    	},
				    	error: function(json){
				    		console.log(json);
				     		return false;
				    	}
				    });
				});
			});
			
        
        </script>
    </head>
    <!-- add onload="checkCookies()" to tag below to auto login -->
    <body id="skin-blur-violate"  onload="checkCookies()">
        <section id="login">
            <header>
                <h1>Timepluser</h1>
                <p>made by plus 1800s team.</p>
            </header>
        
            <div class="clearfix"></div>
            
            <!-- Login -->
            <form class="box tile animated active" id="box-login" >
                <h2 class="m-t-0 m-b-15">Login</h2>
                <input id="signUsername" type="text" class="login-control m-b-10" placeholder="Username or Email Address">
                <input id="signPassword" type="password" class="login-control" placeholder="Password">
                <div class="checkbox m-b-20">
                    <label>
                        <input type="checkbox">
                        Remember Me
                    </label>
                </div>
                <input class="btn btn-sm m-r-5"  type="button" value="Sign In" id="signBtn">
                
                <small>
                    <a class="box-switcher" data-switch="box-register" href="">Don't have an Account?</a>
<!--                     <a class="box-switcher" data-switch="box-reset" href="">Forgot Password?</a> -->
                </small>
            </form>
            
            <!-- Register -->
            <form class="box animated tile" id="box-register" >
                <h2 class="m-t-0 m-b-15">Register</h2>
                <input id="regUsername" type="text" class="login-control m-b-10" placeholder="Username"> 
                <input id="regPassword" type="password" class="login-control m-b-10" placeholder="Password">

				<input class="btn btn-sm m-r-5"  type="button" value="Register" id="regBtn">
                <small><a class="box-switcher" data-switch="box-login" href="">Already have an Account?</a></small>
            </form>
            
            <!-- Forgot Password -->
            <form class="box animated tile" id="box-reset">
                <h2 class="m-t-0 m-b-15">Reset Password</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla eu risus. Curabitur commodo lorem fringilla enim feugiat commodo sed ac lacus.</p>
                <input type="email" class="login-control m-b-20" placeholder="Email Address">    

                <button class="btn btn-sm m-r-5">Reset Password</button>

                <small><a class="box-switcher" data-switch="box-login" href="">Already have an Account?</a></small>
            </form>
        </section>                      
        

    </body>
</html>
