<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="ctx" value="<%= request.getContextPath() %>"/>
<html>
<head template="twobyfour">
    <title>W-8BEN</title>
    <meta name="activeMenu" content="w8Menu"/>
</head>
<body>

<h3 class="text-center">Certificate of Foreign Status of Beneficial Owner<br/>for United States Tax Withholding</h3>

<p>
    blah
    Vivamus malesuada vitae sapien vel placerat. Phasellus facilisis mauris ac felis sodales convallis. Duis non augue
    laoreet ante tristique blandit vel vel ligula. Morbi et varius lacus, pretium vestibulum dolor. Phasellus at mi
    suscipit, tincidunt justo ac, molestie metus.
</p>

<p class="lead">
    <b>W-8BEN Form</b>
</p>

<form:form method="post" commandName="w8Model" action="${ctx}/w8" accept-charset="UTF-8">
    <div class="form-fields">
        <div class="row">
            <div class="span7">
                <form:label path="partOne.beneficialOwnerName" cssErrorClass="field-error"
                        >1 Name of individual or organization that is the beneficial owner</form:label>
            </div>
            <div class="span5">
                <form:label path="partOne.incorporationCountry" cssErrorClass="field-error"
                        >2 Country of incorporation or organization</form:label>
            </div>
            <div class="span7">
                <form:input path="partOne.beneficialOwnerName" cssErrorClass="field-error"
                            style="width: 100%;" placeholder="My Corporation LLC" autocomplete="false"/>
            </div>
            <div class="span5">
                <form:input path="partOne.incorporationCountry" cssErrorClass="field-error"
                            style="width: 100%;" placeholder="Finland" autocomplete="false"/>
            </div>
        </div>

        <div class="row">
            <div class="span12">
                <form:label path="partOne.permanentResidenceAddress.streetAddress" cssErrorClass="field-error"
                        >4 Permanent residence address (street, apt. or suite no., or rural route). <b>Do not use a P.O.
                    box or in-care-of address.</b></form:label>
            </div>
            <div class="span12">
                <form:input path="partOne.permanentResidenceAddress.streetAddress" cssErrorClass="field-error"
                            style="width: 100%;" placeholder="100 Main Street" autocomplete="false"/>
            </div>
        </div>

            <%--<div class="row">--%>
            <%--<div class="span1">firstName:</div>--%>
            <%--<div class="span3"><input type="text" name="r['resource.firstName']" value="First Name狐"/></div>--%>
            <%--</div>--%>
            <%--<div class="row">--%>
            <%--<div class="span1">lastName:</div>--%>
            <%--<div class="span3"><input type="text" name="r['resource.lastName']" value="Last 狐Name"/></div>--%>
            <%--</div>--%>
            <%--<div class="row">--%>
            <%--<div class="span1">balance:</div>--%>
            <%--<div class="span3"><input type="text" name="r['resource.balance']" value="Balance狐"/></div>--%>
            <%--</div>--%>
            <%--<div class="row">--%>
            <%--<div class="span1">date:</div>--%>
            <%--<div class="span3"><input type="text" name="r['resource.date']" value="Date狐"/></div>--%>
            <%--</div>--%>
            <%--<div class="row">--%>
            <%--<div class="span1">phone:</div>--%>
            <%--<div class="span3"><input type="text" name="r['resource.phone']" value="Phone狐"/></div>--%>
            <%--</div>--%>
            <%--<div class="row">--%>
            <%--<div class="span1">notes:</div>--%>
            <%--<div class="span3"><input type="text" name="r['resource.notes']" value="Notes狐"/></div>--%>
            <%--</div>--%>
    </div>

    <button type="submit" class="locale-button btn btn-primary wide">PDF</button>
</form:form>

</body>
</html>
