<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
