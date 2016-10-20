<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>获取表格选中的多行对象</title>
    <script>
        function test() {

            var inputs=document.getElementById("myTable_id").getElementsByTagName("input");
            for(var i=0;i<inputs.length;i++){
                if(inputs[i].type=="checkbox"){
                    if(inputs[i].checked&&inputs[i].name=="chk"){
                        var checkedRow=inputs[i];
                        var tr = checkedRow.parentNode.parentNode;
                        var tds = tr.cells;
                        //循环列

                        for(var j = 1;j < tds.length;j++){
                            var str="";
                            str += "title:"+tds[j].title+ "~ value:"+tds[j].value+"~innerHTML:"+tds[j].innerHTML ;
                            alert(str);
                        }
                    }

                }
            }
        }

    </script>
</head>
<body>
<table id ="myTable_id" width="60%" cellspacing="0" cellpadding="0" align="left" border="1" bordercolordark="#ffffff" bordercolorlight="#B3B5B4" >
    <tbody id="tabstore1">
    <tr>
        <td><input type="checkbox" name="chk"value="123"></td>
        <td title="want u sweet heart!" value="want">b1</td>
        <td>b2</td>
        <td>b3</td>
    </tr>
    <tr>
        <td><input type="checkbox" name="chk"value="456"></td>
        <td title="love u sweet heart!"  value="love">c1</td>
        <td>c2</td>
        <td>c3</td>
    </tr>
    <tr>
        <td><input type="checkbox" name="chk"value="456"></td>
        <td title="miss u sweet heart!" value="miss">d1</td>
        <td>d2</td>
        <td>d3</td>
    </tr>
    </tbody>
</table>
<input type="button" name="my_button"value="取值" onclick="test();"></input>
</body>
</html>