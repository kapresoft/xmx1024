<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>-Xmx1024</title>
</head>
<body>

<p class="lead">
    Vivamus malesuada vitae sapien vel placerat. Phasellus facilisis mauris ac felis sodales convallis. Duis non augue
    laoreet ante tristique blandit vel vel ligula. Morbi et varius lacus, pretium vestibulum dolor. Phasellus at mi
    suscipit, tincidunt justo ac, molestie metus.
</p>

<p class="lead">
    <b>API Version:</b> ${version}
</p>

<h3>URI Templates</h3>

<b>Create an account to the accounts collection</b>

<div class="api">
    POST /api/accounts
</div>

<b>Get an account</b>

<div class="api">
    GET /api/account/{accountId}
</div>

</body>
</html>
