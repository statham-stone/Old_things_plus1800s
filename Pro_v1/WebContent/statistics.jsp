<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no">
    <meta charset="UTF-8">

    <meta name="description" content="Violate Responsive Admin Template">
    <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">

    <title> Statistics</title>

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
        uid=getCookie('uid')
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
        setCookies("uid","",10);            //erase login information
        window.location.href="./login.html" //return to login page
    }
    
    function searchKeyDown()
    {
        if (event.keyCode==13)
        {
            //alert("entered:"+$("#searchbox").val());
            window.location.href="./searchresult?key="+$("#searchbox").val();
        }
    }
    
    function bingo()
    {
        //statham
        window.location.assign("choose_table_java?user_id="+getCookie('uid'));
    }
    
    </script>
    
</head>

<body id="skin-blur-violate" onload="checkCookies()">


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
                <li>
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
                        <span class="menu-item">Create Little Thing</span>
                    </a>
                </li>
                <li>
                    <a class="sa-side-table" href="create_new_table.jsp">
                        <span class="menu-item">Create Table</span>
                    </a>
                </li>


                <li class="active">
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


            <h4 class="page-title">DASHBOARD</h4>

            <!-- Shortcuts -->


            <!-- Quick Stats -->


            <!-- Main Widgets -->

            <div class="block-area">
                <div class="row">
                    <div class="col-md-8">
                        <!-- Main Chart -->
                        <div class="block-area" id="tableHover">

				
						<!-- the chart sqk -->
						<h2 class="tile-title">Line Chart</h2>
						
						<div class="p-10">
				        <div id="line-chart" class="main-chart" style="height: 250px; padding: 0px; position: relative;">
				            <div class="flot-text" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; font-size: smaller; color: rgb(84, 84, 84);">
				                <div class="flot-x-axis flot-x1-axis xAxis x1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;">
				                    <div style="position: absolute; max-width: 96px; top: 236px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 42px; text-align: center;">
				                        2
				                    </div>
				                    <div style="position: absolute; max-width: 96px; top: 236px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 85px; text-align: center;">
				                        4
				                    </div>
				                    <div style="position: absolute; max-width: 96px; top: 236px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 128px; text-align: center;">
				                        6
				                    </div>
				                    <div style="position: absolute; max-width: 96px; top: 236px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 171px; text-align: center;">
				                        8
				                    </div>
				                    <div style="position: absolute; max-width: 96px; top: 236px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 211px; text-align: center;">
				                        10
				                    </div>
				                </div>
				                <div class="flot-y-axis flot-y1-axis yAxis y1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;">
				                    <div style="position: absolute; top: 220px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 1px; text-align: right;">
				                        10
				                    </div>
				                    <div style="position: absolute; top: 183px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 1px; text-align: right;">
				                        12
				                    </div>
				                    <div style="position: absolute; top: 147px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 1px; text-align: right;">
				                        14
				                    </div>
				                    <div style="position: absolute; top: 111px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 1px; text-align: right;">
				                        16
				                    </div>
				                    <div style="position: absolute; top: 74px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 1px; text-align: right;">
				                        18
				                    </div>
				                    <div style="position: absolute; top: 38px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 1px; text-align: right;">
				                        20
				                    </div>
				                    <div style="position: absolute; top: 2px; font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 10px; line-height: 13px; font-family: open-sans-light, sans-serif; color: rgba(255, 255, 255, 0.8); left: 1px; text-align: right;">
				                        22
				                    </div>
				                </div>
				            </div>
				        </div>
						</div>
						
						
						<br><br><br><br>
						
						
						
						
						
						
						
						<h2 class="tile-title">Bar Chart</h2>
						<div class="p-10">
                    
					    <div id="bar-chart" class="main-chart" style="height: 250px; padding: 0px; position: relative;">
			
                        </div>
                        
                        
                        
                        
                        
                              
                        </div>

                        <!-- Pies -->


                        <!--  Recent Postings -->

                        <div class="clearfix"></div>
                    </div>

                    <div class="col-md-4">
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