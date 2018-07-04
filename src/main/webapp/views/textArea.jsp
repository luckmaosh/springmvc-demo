<%--
  Created by IntelliJ IDEA.
  User: maso
  Date: 2018/3/30
  Time: 下午12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript">
        $('#add').click(function () {
            $("#text").append("the text to append \n");
        });
    </script>

</head>
<body>
<h2>SAMPLE</h2>
<a href="###" id="add">add content</a>
<br/><br/>

<textarea id="text">
Goodbye
</textarea>
</body>
</html>
