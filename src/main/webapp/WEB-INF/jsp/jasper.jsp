<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Jasper</title>
    <meta name="activeMenu" content="jasperReportsMenu"/>
</head>
<body>

<p class="lead">
    blah
    Vivamus malesuada vitae sapien vel placerat. Phasellus facilisis mauris ac felis sodales convallis. Duis non augue
    laoreet ante tristique blandit vel vel ligula. Morbi et varius lacus, pretium vestibulum dolor. Phasellus at mi
    suscipit, tincidunt justo ac, molestie metus.
</p>

<p class="lead">
    <b>Jasper Reports Library Examples</b>
</p>

<form:form method="post" target="_blank">
    <%--<form:hidden path="pdf"/>--%>

    <button type="submit" data-export-type="pdf" class="locale-button btn btn-primary wide">PDF</button>
    <button type="submit" data-export-type="xls" class="locale-button btn btn-primary wide">Excel</button>
</form:form>


</body>
</html>
