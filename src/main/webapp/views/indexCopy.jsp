<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>图片上传本地预览</title>
    <script type="text/javascript" src="jquery-1.11.0.js"></script>




    <script type="text/javascript">
        function fun() {
            getEle('show').innerHTML = getEle('text').value;
        }

        function getEle(id) {
            return document.getElementById(id);
        }

        <!--button click 触发-->
        function batch() {



// 批量维护行业洞察中的实体标签为决策属性或者标准场景
            successCount = 0; // 成功数
            errorCount = 0; // 失败数
            errorArray = []; // 失败数据的结果数组
            startDate = new Date(); // 开始时间
            var a = getEle('text').value.split(',');
            a.forEach(item => {
                // item.operate = 'reimport'; // 如果要重新导入，则使用 reimport


                jQuery.ajax({
                url: "http://rsp-pre.alibaba-inc.com/portrait/national/nationalTagAction/cleanData.json",
                contentType: "application/json",
                dataType: "json",
                type: "GET",
                async: false,
                data: {tagIdent: item},
                success: function(data) {
                    if (data.success != true ) {
                        errorCount += 1;
                        errorArray.push(item);
                    } else {
                        successCount += 1;
                    }
                    console.log(item)
                    console.log(data)
                },
                error: function(data) {
                    errorCount += 1;
                    errorArray.push(item);
                    console.log('error')
                    console.log(item)
                    console.log(data)
                }
            });
            console.log("StartDate:" + startDate + "Date:" + new Date() + " successCount: " + successCount + ", errorCount: " + errorCount + ", allCount: " + (successCount + errorCount));
        });

        }
    </script>

</head>
<body>
<div id="gallery">
    <form name="form" method="post" action="#">
        <input type="input" id="text" width="400" name="text" value="">
        <br>
        <input type="button" name="first" onclick="batch();" value="第一步">
        <input type="button" name="query" onclick="query();" value="第二步">
        <input type="button" name="update" onclick="update();" value="回滚">
        <input type='button' onclick='fun();' value="button"/>

        <br/>
        <input type="text" id="show"/>
    </form>
</div>
</body>
</html>