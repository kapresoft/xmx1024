<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>-Xmx1024</title>
    <style type="text/css">
        body, div, table, p {
            font-family: verdana, sans-serif;
            font-size: 13px;
        }

        div.api {
        }

        table.api, table.api th, table.api td {
            border-collapse: collapse;
            border: 1px solid #000000;
        }

        table.api th {
            background: #efefef;
            padding: 2px;
            text-align: left;
        }

        table.api td {
            padding: 2px;
            text-align: left;
        }

        table.api {
            margin-bottom: 10px;
            width: 500px;
        }

    </style>
</head>
<body>
<h1>Welcome</h1>
<b>API Version:</b> ${version}

<h3>URI Templates</h3>

<div>
    <table class="api" border="1" cellpadding="0" cellspacing="0">
        <tbody>
        <tr>
            <th>Create an account to the accounts collection</th>
        </tr>
        <tr>
            <td>
                <div class="api">
                    POST /api/accounts
                </div>
            </td>
        </tr>
        </tbody>
    </table>

    <table class="api" border="1" cellpadding="0" cellspacing="0">
        <tbody>
        <tr>
            <th>Get an account</th>
        </tr>
        <tr>
            <td>
                <div class="api">GET /api/account/{accountId}</div>
            </td>
        </tr>
        </tbody>
    </table>

</div>

</body>
</html>
