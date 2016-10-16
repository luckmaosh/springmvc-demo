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
    <title>增加Table行</title>
</head>
<script>
    function addRow(obj) {
        //添加一行
        var newTr = testTbl.insertRow();
        //添加两列
        var newTd0 = newTr.insertCell();
        var newTd1 = newTr.insertCell();
        //设置列内容和属性
        newTd0.innerHTML = '<input type=checkbox id="box4">';
        newTd1.innerHTML = '<select name="collectType" id="collectType">                <option value=""></option>                <option value="image" #if($!collectType=="image") selected #end >image</option>    <option value="text" #if($!collectType=="text") selected #end >text</option> </select>'
    }


    function createRow() {
        var tr = testTbl.insertRow(testTbl.rows.length);
        var td  = tr.insertCell();
        var td2 = tr.insertCell();
        td2.innerHTML = '<input type=checkbox id="box4">'
//     #if($!collectType=="image") selected #end
        var td3 = tr.insertCell();
        td3.innerHTML = '<select name="collectType" id="collectType">                <option value=""></option>                <option value="image" >image</option>    <option value="text" >text</option> </select>'

        var td4 = tr.insertCell()

        var td5 = tr.insertCell();
        td5.innerHTML = '<input name="is_key_info" type="radio"        value="1">是</input>'

        var td6 = tr.insertCell();
        td6.innerHTML = '<input name="is_required" type="radio"        value="1">是</input>'

        var td7 = tr.insertCell();
        td.innerHTML = ''


    }
</script>
<body>
<input type="button" id="add" onclick="createRow();" value="Add Row"/>
<table id="testTbl" border=1>
    <tr id="tr1">
        <td><input type=checkbox id="box1"></td>
        <td id="b">第一行</td>
        <td >第一行</td>
        <td >第一行</td>
        <td >第一行</td>
        <td >第一行</td>
        <td >第一行</td>
        <td >第一行</td>
        <td >第一行</td>
    </tr>
    <tr id="tr2">
        <td><input type=checkbox id="box2"></td>
        <td id="b2">第二行</td>
    </tr>
    <tr id="tr3">
        <td><input type=checkbox id="box3"></td>
        <td>第三行</td>
    </tr>
</table>
<br/>
</body>
</html>