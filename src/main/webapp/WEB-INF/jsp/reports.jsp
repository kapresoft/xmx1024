<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<c:set var="ctx" value="<%= request.getContextPath() %>"/>
<html>
<head>
    <title>Jasper</title>
    <meta name="activeMenu" content="reportsMenu"/>
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

<form:form method="get" action="${ctx}/reports/view" target="_blank">
    <input id="type" name="type" type="hidden"/>

    <button type="submit" data-output-type="pdf" class="locale-button btn btn-primary wide">PDF</button>
    <button type="submit" data-output-type="xls" class="locale-button btn btn-primary wide">Excel</button>
</form:form>

<div>
    <a href="${ctx}/reports/unicode?type=pdf" target="_blank">Unicode</a>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $(':button').click(function () {
            var outputType = $(this).data('output-type');
            $('#type').val(outputType)
        });
    });
</script>

</body>
</html>
