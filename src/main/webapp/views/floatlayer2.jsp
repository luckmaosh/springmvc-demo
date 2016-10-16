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
<style type="text/css">
    #thelayer {
        width: 300px;
        height: 98px;
        border: #E4F5FD 1px solid;
        z-index: 2;
        position: absolute;
        background: #FFFFFF;
        display: none;
    }
</style>

<script>
    function CPos(x, y) {
        this.x = x;
        this.y = y;
    }

    function GetObjPos(ATarget) {
        var target = ATarget;
        var pos = new CPos(target.offsetLeft, target.offsetTop);

        var target = target.offsetParent;
        while (target) {
            pos.x += target.offsetLeft;
            pos.y += target.offsetTop;

            target = target.offsetParent
        }

        return pos;
    }
    function showlayer(obj) {
        pos = GetObjPos(obj);
        l = document.getElementById("thelayer");
        l.style.left = pos.x + 40;
        l.style.top = pos.y + 40;
        l.style.display = "block";
    }
</script>

<div onclick="showlayer(this)">点我</div>

<div id="thelayer">浮我</div>
</body>
</html>