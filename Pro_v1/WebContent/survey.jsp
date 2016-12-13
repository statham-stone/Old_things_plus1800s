<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no">
    <meta charset="UTF-8">

    <meta name="description" content="Violate Responsive Admin Template">
    <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">

    <title>User Survey</title>

    <!-- CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/form.css" rel="stylesheet">
    <link href="css/calendar.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/icons.css" rel="stylesheet">
    <link href="css/generics.css" rel="stylesheet">
    <script type="text/javascript">
	
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
		var uid=getCookie('uid')
		if (uid==null || uid=="") 
		{
			alert("You are not logged in! please log in first.")
			window.location.href="./login.html"	
		} else {
			document.getElementById("usernametag").innerHTML="uid:"+uid;
		}
	}
	
    function signOut()
    {
    	setCookies("uid","",10);			//erase login information
    	window.location.href="./login.html"	//return to login page
    }
    
    function searchKeyDown()
    {
    	if (event.keyCode==13)
    	{
    		//alert("entered:"+$("#searchbox").val());
    		window.location.href="./searchresult?key="+$("#searchbox").val();
    	}
    }
    
    function showTable(a)
    {
    	var name=document.getElementById("tableSet["+a+"]");
    //	window.location.href="./show_table_h?user_id="+getCookie('uid').toString()+"table_name="+name;
    setCookie("table_name",www,20);
    window.location.href="./show_table_jsp.jsp";
    }
    
    function bingo()
    {
    	//statham
    	window.location.assign("choose_table_java?user_id="+getCookie('uid'));
    }
    
    function showInputRequire()
    {
    	document.getElementById("inputrequire").style.display="inline";
    }
    
    function hideInputRequire()
    {
    	document.getElementById("inputrequire").style.display="none";
    }
    
	function checks(t){
		   szMsg="<javascript>";
		   alertStr="";
		    if(t.indexOf(szMsg)>-1){
		     alertStr="illegal content detected, please retry.";
		    }
		
		   if(alertStr != ""){
		    alert(alertStr);
		    return false;
		   }
		   return true;
		  }    
	
    function submitSurvey()
    {
//      	alert("submit activated");
//     	alert($("input[name='rad_1']:checked").val());
//     	alert($("input[name='rad_2']:checked").val());
//     	alert($("input[name='rad_3']:checked").val());
//     	alert($("input[name='rad_4']:checked").val());
//     	alert($("input[name='rad_5']:checked").val());
//     	alert(document.getElementById('inputrequire').value);
//     	alert(document.getElementById('inputcomment').value);
		var survey="";
		survey+= $("input[name='rad_1']:checked").val()+'~';
		survey+= $("input[name='rad_2']:checked").val()+'~';
		survey+= $("input[name='rad_3']:checked").val()+'~';
		survey+= $("input[name='rad_4']:checked").val()+'~';
		survey+= document.getElementById('inputrequire').value+'~';
		survey+= $("input[name='rad_5']:checked").val()+'~';
		survey+= document.getElementById('inputcomment').value;
		//alert(survey);
		
		var validate=document.getElementById('inputrequire').value+document.getElementById('inputcomment').value;
		if (!checks(validate)) return;
		var params = {
				uid : getCookie("uid"),
		    	survey : survey
			};
			$.ajax({
		    	type: "POST",
		    	url: "userSurvey.action",
		    	data: params,
		    	dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
		    	success: function(json){  
		    		var obj = $.parseJSON(json);  //使用这个方法解析json
		            var state_value = obj.result;  //result是和action中定义的result变量的get方法对应的
		            console.log(json);
		    		//state_value is the returned value
		    		alert(state_value);
		    	},
		    	error: function(json){
		    		console.log(json);
		     		return false;
		    	}
		    });
		
		
		
    }
    
    
    </script>
    
</head>

<body id="skin-blur-violate" onload="checkCookies(); hideInputRequire();">


    <header id="header" class="media">
        <a href="" id="menu-toggle"></a>
        <a class="logo pull-left" href="index.jsp">TIME PLUSER 1.0</a>

        <div class="media-body">
            <div class="media" id="top-menu">
                <div id="time" class="pull-right">
                    <span id="hours"></span> :
                    <span id="min"></span> :
                    <span id="sec"></span>
                </div>

                <div class="media-body">
                    <input type="text" class="main-search" id="searchbox" onkeydown="searchKeyDown()">
                </div>
            </div>
        </div>
    </header>

    <div class="clearfix"></div>

    <section id="main" class="p-relative" role="main">

        <!-- Sidebar -->
        <aside id="sidebar">

            <!-- Sidbar Widgets -->
            <div class="side-widgets overflow">
                <!-- Profile Menu -->
                <div class="text-center s-widget m-b-25 dropdown" id="profile-menu">
                    <br/>
                    <h4 class="m-0" id="usernametag"></h4>
                    <a href="#" id="signoutlink" onclick="signOut()">Sign Out</a>
                </div>

                <!-- Calendar -->
                <div class="s-widget m-b-25">
                    <div id="sidebar-calendar"></div>
                </div>

                <!-- Feeds -->
                <div class="s-widget m-b-25">
                    <h2 class="tile-title">
                        News Feeds
                    </h2>

                    <div class="s-widget-body">
                        <div id="news-feed"></div>
                    </div>
                </div>

                <!-- Projects -->

            </div>

            <!-- Side Menu -->
            <ul class="list-unstyled side-menu">
                <li class="active">
                    <a class="sa-side-home" href="index.jsp">
                        <span class="menu-item">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a class="sa-side-typography" href="new_event.html">
                        <span class="menu-item">Create Event</span>
                    </a>
                </li>
                <li>
                     <a class="sa-side-widget" onclick='javascript:bingo()'>
   <!--                     <a class="sa-side-widget" href="create_little_thing_choose_table.jsp">
          -->             
                        <span class="menu-item">Create Little Thing</span>
                    </a>
                </li>
                <li>
                    <a class="sa-side-table" href="create_new_table.jsp">
                        <span class="menu-item">Create Table</span>
                    </a>
                </li>


                <li>
                    <a class="sa-side-chart" href="statistics.jsp">
                        <span class="menu-item">Statistics</span>
                    </a>
                </li>

            </ul>

        </aside>

        <!-- Content -->
        <section id="content" class="container">

            <!-- Messages Drawer -->


            <!-- Notification Drawer -->

            <!-- Breadcrumb -->


            <h4 class="page-title">SURVEY PAGE</h4>

            <!-- Shortcuts -->


            <!-- Quick Stats -->


            <!-- Main Widgets -->

            <div class="block-area">
                <div class="row">
                    <div class="col-md-8">
                        <!-- Main Chart ###################################-->
						<!-- relations -->
						<h3>1.您与三位开发者(a2clef,statham_stone,leafywang)的关系是?</h3>
						<div class="radio">
                        <label>
                            <input type="radio" name="rad_1" value="a">
                            <h4>a.我是他们的同学/朋友/家人</h4>
                        </label>
                    	</div>
                    	<div class="radio">
                        <label>
                            <input type="radio" name="rad_1" value="b">
                            <h4>b.我是学院的相关教师、博士生、研究生</h4>
                        </label>
                    	</div>
                    	<div class="radio">
                        <label>
                            <input type="radio" name="rad_1" value="c">
                           <h4>c.我是来自互联网的陌生用户</h4> 
                        </label>
                    	</div>
						<!-- other application -->
						<br><h3>2. 您以前是否使用过类似功能的软件或者网站？</h3>
						<div class="radio">
                        <label>
                            <input type="radio" name="rad_2" value="a">
                            <h4>a.是</h4>
                        </label>
                    	</div>
                    	<div class="radio">
                        <label>
                            <input type="radio" name="rad_2" value="b">
                            <h4>b.否</h4>
                        </label>
                    	</div>				
						<!-- UX -->
						<br><h3>3. 您觉得我们的网站界面是否友好美观、操作是否便捷快速？满分为5</h3>
						<div class="radio">
                        <label>
                            <input type="radio" name="rad_3" value="a">
                            <h4>a.5</h4>
                        </label>
                    	</div>
                    	<div class="radio">
                        <label>
                            <input type="radio" name="rad_3" value="b">
                            <h4>b.4</h4>
                        </label>
                    	</div>					
                    	<div class="radio">
                        <label>
                            <input type="radio" name="rad_3" value="c">
                            <h4>c.3</h4>
                        </label>
                    	</div>		
                    	<div class="radio">
                        <label>
                            <input type="radio" name="rad_3" value="d">
                            <h4>d.2</h4>
                        </label>
                    	</div>		
                    	<div class="radio">
                        <label>
                            <input type="radio" name="rad_3" value="e">
                            <h4>e.1</h4>
                        </label>
                    	</div>				
						<!-- require -->
						<br><h3>4. 您觉得网站提供的功能是否满足了您在个人信息管理上的需求?</h3>
						<div class="radio">
                        <label onclick="hideInputRequire()">
                            <input type="radio" name="rad_4" value="a">
                            <h4>a.完全够用</h4>
                        </label>
                    	</div>
                    	<div class="radio">
                        <label onclick="hideInputRequire()">
                            <input type="radio" name="rad_4" value="b">
                            <h4>b.基本满足</h4>
                        </label>
                    	</div>					
                    	<div class="radio">
                        <label onclick="hideInputRequire()">
                            <input type="radio" name="rad_4" value="c">
                            <h4>c.不太够用</h4>
                        </label>
                    	</div>		
                    	<div class="radio">
                        <label onclick="showInputRequire()">
                            <input type="radio" name="rad_4" value="d">
                            <h4>d.完全不够</h4>
                        </label>
                    	</div>			
                    	<textarea class="form-control auto-size m-b-10" placeholder="输入您的需求..." id="inputrequire"></textarea>				
						<!-- robustness -->
						<br><h3>5. 您是否在使用过程中遇到一些404等崩溃界面 ?</h3>
						<div class="radio">
                        <label>
                            <input type="radio" name="rad_5" value="a">
                            <h4>a.否，操作完全正常</h4>
                        </label>
                    	</div>
                    	<div class="radio">
                        <label>
                            <input type="radio" name="rad_5" value="b">
                            <h4>b.是，极少数情况</h4>
                        </label>
                    	</div>					
                    	<div class="radio">
                        <label>
                            <input type="radio" name="rad_5" value="c">
                            <h4>c.是，偶尔会遇到</h4>
                        </label>
                    	</div>		
                    	<div class="radio">
                        <label>
                            <input type="radio" name="rad_5" value="d">
                            <h4>d.是，经常会遇到</h4>
                        </label>
                    	</div>								
						<!-- other comments:string -->
						<br><h3>6. 针对本网站您还有些什么样的改进意见？</h3>
						<textarea class="form-control auto-size m-b-10" placeholder="输入您的意见..." id="inputcomment"></textarea>
						
						
						
                        <div class="clearfix"></div>
                    </div>

                    <div class="col-md-4">
                    <button class="btn btn-lg m-r-5" onclick="submitSurvey()">Submit Survey</button>
                        <!-- USA Map -->

                        <!-- Dynamic Chart -->

                        <!-- Activity -->
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>

            <!-- Chat -->

        </section>


    </section>

    <!-- Javascript Libraries -->
    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    <!-- jQuery Library -->
    <script src="js/jquery-ui.min.js"></script>
    <!-- jQuery UI -->
    <script src="js/jquery.easing.1.3.js"></script>
    <!-- jQuery Easing - Requirred for Lightbox + Pie Charts-->

    <!-- Bootstrap -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Charts -->
    <script src="js/charts/jquery.flot.js"></script>
    <!-- Flot Main -->
    <script src="js/charts/jquery.flot.time.js"></script>
    <!-- Flot sub -->
    <script src="js/charts/jquery.flot.animator.min.js"></script>
    <!-- Flot sub -->
    <script src="js/charts/jquery.flot.resize.min.js"></script>
    <!-- Flot sub - for repaint when resizing the screen -->

    <script src="js/sparkline.min.js"></script>
    <!-- Sparkline - Tiny charts -->
    <script src="js/easypiechart.js"></script>
    <!-- EasyPieChart - Animated Pie Charts -->
    <script src="js/charts.js"></script>
    <!-- All the above chart related functions -->

    <!-- Map -->
    <script src="js/maps/jvectormap.min.js"></script>
    <!-- jVectorMap main library -->
    <script src="js/maps/usa.js"></script>
    <!-- USA Map for jVectorMap -->

    <!--  Form Related -->
    <script src="js/icheck.js"></script>

        <script src="js/icheck.js"></script> <!-- Custom Checkbox + Radio -->
        <script src="js/autosize.min.js"></script> <!-- Textarea autosize -->
    <!-- Custom Checkbox + Radio -->

    <!-- UX -->
    <script src="js/scroll.min.js"></script>
    <!-- Custom Scrollbar -->

    <!-- Other -->
    <script src="js/calendar.min.js"></script>
    <!-- Calendar -->
    <script src="js/feeds.min.js"></script>
    <!-- News Feeds -->


    <!-- All JS functions -->
    <script src="js/functions.js"></script>
</body>

</html>