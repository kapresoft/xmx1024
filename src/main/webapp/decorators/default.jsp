<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx"><%= request.getContextPath() %>
</c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><sitemesh:write property='title'>Title goes here</sitemesh:write></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Fjalla+One,Archivo+Black|Telex|Questrial|Acme|Archivo+Black|Berkshire+Swash|Ruda|Righteous|Montserrat'
          rel='stylesheet' type='text/css'>

    <link href="${ctx}/css/main.css" rel="stylesheet" media="screen">

    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>

    <sitemesh:write property='head'/>
</head>

<body>

<div class="body-wrap container-fluid">

    <div class="hero-unit">
        <h2><sitemesh:write property='title'>Title goes here</sitemesh:write></h2>
    </div>

    <sitemesh:write property='body'>Body goes here. Blah blah blah.</sitemesh:write>

</div>
<!-- body-wrap -->
<%--<div class="disclamer">Site disclaimer. This is an example.</div>--%>
</body>
</html>