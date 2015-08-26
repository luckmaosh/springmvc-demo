<%--
  Created by IntelliJ IDEA.
  User: maso
  Date: 15/8/26
  Time: 下午2:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script>
        $("#getOtherDomainThings").click(function () {
            $.get("http://localhost:8080/Scripts/jquery-1.4.4.min.js", function (data) {
                console.log(data)
            })

            $.get("http://localhost:8080/home/index", function (data) {
                console.log(data)
            })
        })
    </script>
</head>
<body>

<input type="button" id="getOtherDomainThings" value="click me"/>
</body>
</html>
