<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no">
    <meta charset="UTF-8">

    <meta name="description" content="Violate Responsive Admin Template">
    <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">

    <title>Create new table</title>

    <!-- CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/form.css" rel="stylesheet">
    <link href="css/calendar.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/icons.css" rel="stylesheet">
    <link href="css/generics.css" rel="stylesheet">
    
    <script src="js/jquery.min.js"></script>
    <script src="js/ajaxFileUpload.js" type="text/javascript"></script>
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
    
    function bingo_2()
    {
    	//statham
    	window.location.assign("check_table_name?user_id="+getCookie('uid')+"&"+"table_name="+document.getElementById("table_name").value+"&column_number="+document.getElementById("column_number").value);
    }
    
    function ajaxFileUpload()
    {
        
        $("#loading")
        .ajaxStart(function(){
            $(this).show();
        })
        
        .ajaxComplete(function(){
            $(this).hide();
        });
        $.ajaxFileUpload
        (
            {
                url:"fileUpload.action",
                data:{	tableName:$("#tableName").val(),
                		uid:getCookie("uid")
                	},
                secureuri:false,//
                fileElementId:'file',//
                dataType: 'json',//
		    	success: function(json){  
		    		var obj = $.parseJSON(json);  
		            var state_value = obj.result;
		            console.log(json);
		    		alert("SUCCESS");
		    	},
		    	error: function(json){
		    		console.log(json);
		    		//alert("FAIL");
		    		window.location.href="./index.jsp" 
		     		return false;
		    	}
            }
        )
        
        return false;

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
                <li class="active">
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


            <h4 class="page-title">CREATE TABLE</h4>

            <!-- Shortcuts -->


            <!-- Quick Stats -->


            <!-- Main Widgets -->

            <div class="block-area">
                <div class="row">
                    <div class="col-md-8">
                        <!-- Main Chart -->
                        <div class="block-area" id="tableHover">
                            <h3 class="block-title">TABLES</h3>

<!-- 	<form action="check_table_name"> -->
    	<p>Table name: <input class="input-sm validate[required] form-control" id="table_name"  type="text" name="table_name"/></p>
    	<p>Column number: <input  class="input-sm validate[required] form-control" id="column_number"    type="text" name="column_number"/></p>
		<button class="btn m-r-5" onclick='javascript:bingo_2()'  value="submit">Check</button>
<!-- 	</form> -->
	<br><br><br>
	<h3>Or create table with .csv file</h3>
	<form action="fileUpload.action" method="post" enctype="multipart/form-data">
		Table Name: <input class="input-sm validate[required] form-control"type="text" id="tableName"><br>
        File: <input class="input-sm validate[required] form-control"type="file" id="file" name="file"><br>
        <input  class="btn m-r-5" type="button" value="submit File" onclick="ajaxFileUpload();">
        <h4 id="loading" style="visibility:hidden;" >File Uploading...</h4>
    </form>

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