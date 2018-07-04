<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>upload image demo</title>
    <script type="text/javascript" src="static/js/jquery-1.11.0.js"></script>

    <script type="text/javascript" src="static/js/prototype.js"></script>

    <script type="text/javascript" src="static/js/scriptaculous.js?load=effects"></script>

    <script type="text/javascript" src="static/js/lightbox.js"></script>
    <link rel="stylesheet" href="static/css/lightbox.css" type="text/css" media="screen"/>


</head>
<body>
<div id="gallery">
    <form action="file/upload"  method="post" enctype="multipart/form-data">
        <input type="file"/>
        <input type="submit" value="submit"/>
    </form>
</div>
</body>
</html>