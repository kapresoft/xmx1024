<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="ctx" value="<%= request.getContextPath() %>"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <title><sitemesh:write property='title'>Title goes here</sitemesh:write></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <%-- HTML5 Fonts --%>
    <%-- https://developers.google.com/fonts/docs/getting_started#Effects --%>
    <%--<link href='http://fonts.googleapis.com/css?effect=shadow-multiple|stonewash|destruction|emboss|3d|3d-float|wallpaper&family=BenchNine|Fjalla+One,Archivo+Black|Telex|Questrial|Acme|Archivo+Black|Berkshire+Swash|Ruda|Righteous|Montserrat|Brawler|EB+Garamond|Chau+Philomene+One|UnifrakturCook:700|Special+Elite|Mouse+Memoirs|Aclonica' rel='stylesheet' type='text/css'/>--%>
    <link href='http://fonts.googleapis.com/css?effectx=wallpaper&family=BenchNine|Aclonica|Ruda' rel='stylesheet'
          type='text/css'/>

    <link href="${ctx}/bootstrap/less/bootstrap.less" rel="stylesheet/less" media="all"/>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="${ctx}/bootstrap/js/html5shiv.js"></script>
    <![endif]-->

    <script src="${ctx}/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        less = {
            env: "development", // development or production
            async: false,       // load imports async
            fileAsync: false,   // load imports async when in a page under a file protocol
            poll: 1000,         // when in watch mode, time in ms between polls
            functions: {},      // user functions, keyed by name
            dumpLineNumbers: "comments", // or "mediaQuery" or "all"
            relativeUrls: false // whether to adjust url's to be relative if false, url's are already relative to the entry less file
        };
    </script>
    <script src="${ctx}/bootstrap/js/less.min.js"></script>

    <sitemesh:write property='head'/>
</head>

<body>
<!-- Part 1: Wrap all page content here -->
<div id="wrap">
    <!-- Fixed navbar -->
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="brand" href="#">-Xmx1024m</a>

                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li id="homeMenu"><a href="${ctx}/home"><i class="icon-bookmark"></i> Home</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b
                                    class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li class="divider"></li>
                                <li class="nav-header">Nav header</li>
                                <li><a href="#">Separated link</a></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </div>

    <!-- Begin page content -->
    <div class="container">
        <div class="page-header">
            <h1 class="font-effect-wallpaper"><sitemesh:write property='title'>Title goes here</sitemesh:write></h1>
        </div>
        <sitemesh:write property='body'>Body goes here. Blah blah blah.</sitemesh:write>

    </div>

    <div id="push"></div>
</div>

<div id="footer">
    <div class="container">
        <p class="muted credit">Antonio Lagnada <a href="xmx1024.blogspot.com">http://xmx1024.blogspot.com</a>.</p>
    </div>
</div>


<!-- Le javascript
================================================== -->
<script>
    $(document).ready(function () {
        var homeMenuEl = $('#homeMenu');
        var activeMenuEl = $('meta[name=activeMenu]');
        var menuFound = false;
        if (activeMenuEl.length > 0)
        {
            var activeMenu = activeMenuEl.attr('content');
            var activeMenuEl = $('#' + activeMenu);
            if (activeMenuEl.length == 1)
            {
                activeMenuEl.addClass('active');
                menuFound = true;
            }
        }

        if (menuFound)
        {
            homeMenuEl.removeClass('active');
        } else
        {
            homeMenuEl.addClass('active');
        }
    });
</script>

</body>
</html>
