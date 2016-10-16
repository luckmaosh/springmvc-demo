<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>js弹出层</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script>
        function generateFloatLayer() {
            floatArea = document.getElementById("popup");
            floatArea.style.display = "none";
            divClose = '<div id="close" style="position:absolute; right:10px; top:10px; left:auto; bottom:auto;"><a href="javascript:closeFloat();">关闭</a></div><br>';
            x = event.clientX + document.body.scrollLeft;
            y = event.clientY + document.body.scrollTop;
            floatArea.innerHTML = divClose + "<div id=\"floatcontent\">浮动层的内容在这里</div>";
            floatArea.style.border = "black 1px solid";
            floatArea.style.left = (document.documentElement.scrollLeft + x - 15) + "px";
            floatArea.style.top = (document.documentElement.scrollTop + y - 10) + "px";
            floatArea.style.width = "300px";
            floatArea.style.height = "200px";
            floatArea.style.display = "";
        }

        function closeFloat() {
            floatArea = document.getElementById("popup");
            floatArea.innerHTML = "";
            floatArea.style.display = "none";
        }
    </script>
</head>
<body>
<a href="#" onclick="javascript:generateFloatLayer()">点此弹出浮动层</a>
<div id="popup" style="position:absolute; z-index:100; display:none; background-color:#eeeeee"></div>
</body>
</html>