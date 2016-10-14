<%--
  Created by IntelliJ IDEA.
  User: maso
  Date: 15/8/26
  Time: 下午2:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <meta name="GENERATOR" content="Microsoft FrontPage 4.0">
    <meta name="ProgId" content="FrontPage.Editor.Document">
    <title>New Page 1</title>
</head>

<body>

<script>
    function getrow(obj) {
        if (event.srcElement.tagName == "TD") {
            curRow = event.srcElement.parentElement;
            alert("这是第" + (curRow.rowIndex + 1) + "行");

        }
    }
</script>

<table border="1" width="100%" onclick=getrow(this)>
    <tr>
        <td width="20%">　</td>
        <td width="20%">　</td>
        <td width="20%">　</td>
        <td width="20%">　</td>
        <td width="20%">　</td>
    </tr>
    <tr>
        <td width="20%">　</td>
        <td width="20%">　</td>
        <td width="20%">　</td>
        <td width="20%">　</td>
        <td width="20%">　</td>
    </tr>
</table>

</body>

</html>