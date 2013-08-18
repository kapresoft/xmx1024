<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

<form method="post" action="${ctx}/reports/view" target="_blank" accept-charset="UTF-8">
    <input id="type" name="type" type="hidden"/>

    <div>
        <div class="row">
            <div class="span1">reportTitle:</div>
            <div class="span3"><input type="text" name="resourceMap['resource.reportTitle']"
                                      value="Example 敏捷 Jasper Reports"/></div>
        </div>
        <div class="row">
            <div class="span1">customerId:</div>
            <div class="span3"><input type="text" name="r['resource.customerId']" value="Customer狐 ID"/></div>
        </div>
        <div class="row">
            <div class="span1">firstName:</div>
            <div class="span3"><input type="text" name="r['resource.firstName']" value="First Name狐"/></div>
        </div>
        <div class="row">
            <div class="span1">lastName:</div>
            <div class="span3"><input type="text" name="r['resource.lastName']" value="Last 狐Name"/></div>
        </div>
        <div class="row">
            <div class="span1">balance:</div>
            <div class="span3"><input type="text" name="r['resource.balance']" value="Balance狐"/></div>
        </div>
        <div class="row">
            <div class="span1">date:</div>
            <div class="span3"><input type="text" name="r['resource.date']" value="Date狐"/></div>
        </div>
        <div class="row">
            <div class="span1">phone:</div>
            <div class="span3"><input type="text" name="r['resource.phone']" value="Phone狐"/></div>
        </div>
        <div class="row">
            <div class="span1">notes:</div>
            <div class="span3"><input type="text" name="r['resource.notes']" value="Notes狐"/></div>
        </div>
    </div>

    <button type="submit" data-output-type="pdf" class="locale-button btn btn-primary wide">PDF</button>
    <button type="submit" data-output-type="xls" class="locale-button btn btn-primary wide">Excel</button>
</form>

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
