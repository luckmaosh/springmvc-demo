<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>图片上传本地预览</title>
    <script type="text/javascript" src="static/js/jquery.js"></script>
    <script type="text/javascript" src="static/js/jquery.lightbox-0.5.js"></script>
    <link rel="stylesheet" type="text/css" href="static/css/jquery.lightbox-0.5.css" media="screen" />


    <script type="text/javascript">
        $(function() {
            $('#gallery a').lightBox({});
        });
    </script>
    
</head>
<body>
<div id="gallery">
    <ul>
        <li>
            <a href="static/images/image-1.jpg" title="Utilize a flexibilidade dos seletores da jQuery e crie um grupo de imagens como desejar. $('#gallery').lightBox();">
                <img src="static/images/thumb-1.jpg" width="72" height="72" alt="" />
            </a>
        </li>
        <li>
            <a href="static/images/image-2.jpg" title="Utilize a flexibilidade dos seletores da jQuery e crie um grupo de imagens como desejar. $('#gallery a').lightBox();">
                <img src="static/images/thumb-2.jpg" width="72" height="72" alt="" />
            </a>
        </li>
        <li>
            <a href="static/images/image-3.jpg" title="Utilize a flexibilidade dos seletores da jQuery e crie um grupo de imagens como desejar. $('#gallery a').lightBox();">
                <img src="static/images/thumb-3.jpg" width="72" height="72" alt="" />
            </a>
        </li>
        <li>
            <a href="static/images/image-4.jpg" title="Utilize a flexibilidade dos seletores da jQuery e crie um grupo de imagens como desejar. $('#gallery a').lightBox();">
                <img src="static/images/thumb-4.jpg" width="72" height="72" alt="" />
            </a>
        </li>
        <li>
            <a href="static/images/image-5.jpg" title="Utilize a flexibilidade dos seletores da jQuery e crie um grupo de imagens como desejar. $('#gallery a').lightBox();">
                <img src="static/images/thumb-5.jpg" width="72" height="72" alt="" />
            </a>
        </li>
    </ul>
</div>
</body>
</html>