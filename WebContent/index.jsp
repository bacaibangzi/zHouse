<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" class="js flexbox canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="format-detection" content="telephone=no"/>
    <meta name="renderer" content="webkit">
    <meta name="baidu-site-verification" content="HuKZbEtsCC" />
    <title>宜居宝宝</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/theme.css">
    <link rel="stylesheet" href="css/main.min.css" />
    <link rel="stylesheet" href="css/gainseestyle.css" />
    <script src="js/main.min.js"></script>
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="js/webplate.min.js"></script>
    <script src="js/script.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/headroom.min.js"></script>
    <script src="js/script.js"></script>
    <script async src="js/analytics.js"></script>

</head>


<body ryt12934="1">
<script>
    $(document).ready(function(){
        $("button").click(function(){
            $(".navbar-inverse").toggleClass("navbar-bg");
        });
        $(".news-item").click(function(){
            window.location=$(this).find("a").attr("href");
            return false;
        });
    });
</script>

<nav class="absolute">
    <div >
        <nav class="navbar navbar-inverse navbar-static-top ">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div id="navbar" class="navbar-collapse collapse daohang">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href='index.jsp'>首页</a></li> 
                        <li><a href='about.jsp' ><span>关于</span></a></li>
                        <li><a href='contact.jsp' ><span>联系</span></a></li>
                    </ul>
                </div>
                <div class="navbar-iphone">
                    <span>025</span>-8888888
                </div>
            </div>
        </nav>
    </div></nav>
<header class="stripe full">

    <div class="contain">

        <div  class="text-center-tight">
	        <div class="inner">
	            <h2>Welcome to 宜居宝宝 ...</h2>
	            <h6>随时随地！想住就住！。</h6>
	        </div>
	        
        </div>

        <a href="javascript:;" class="anchor scroll-down fadeInDown"></a>

    </div>


    <div class="bg-image" style="background-image:url(img/index/1-160519102031223.jpg)"></div>

</header>
<div class="stripe">
    <div class="container1 anchor-scroll">
        <div class="row featurette">
            <div class="col-xs-6"" data-show="on-scroll">
                <h2>宜居宝宝，最优秀的房屋租赁APP</h2>
                <p class="lead">真实 及时 专业 自由</p>

            </div>
            <div class="col-xs-6" data-show="on-scroll" style="padding-left:120px;">
                <img alt="" src="common/images/android_ew.png"></img>
                <h3>扫二维码下载宜居宝宝APP</h3>

            </div>
        </div>
    </div>

    <div class="container-fluid">


        <section class="work-list" >
            <div class="inner" id="work-feed">

                <article class="work-item " data-show="on-scroll" >
                    <a href="">
                        <picture>
                            <img src='img/index/welcome_1.png' border='0' width='120' height='120' alt=''>
                        </picture>
                        <div class="content">
                            <div>
                                <div>
                                    <h1>真实</h1>
                                    <h2>高清图片，真实成交，真实信息展现真实二手房</h2>
                                </div>
                            </div>
                        </div></a>
                </article>

            </div>
        </section>
        <section class="work-list" >
            <div class="inner" id="work-feed">

                <article class="work-item " data-show="on-scroll" >
                    <a href="">
                        <picture>
                            <img src='img/index/welcome_2.png' border='0' width='120' height='120' alt=''>
                        </picture>
                        <div class="content">
                            <div>
                                <div>
                                    <h1>及时</h1>
                                    <h2>即时在线咨询直联房产顾问个人手机，沟通快，无骚扰</h2>
                                </div>
                            </div>
                        </div></a>
                </article>

            </div>
        </section>
        <section class="work-list" >
            <div class="inner" id="work-feed">

                <article class="work-item " data-show="on-scroll" >
                    <a href="">
                        <picture>
                            <img src='img/index/welcome_3.png' border='0' width='120' height='120' alt=''>
                        </picture>
                        <div class="content">
                            <div>
                                <div>
                                    <h1>专业</h1>
                                    <h2>十万名考核认证社区专家您的专职房产顾问</h2>
                                </div>
                            </div>
                        </div></a>
                </article>

            </div>
        </section>
        <section class="work-list" >
            <div class="inner" id="work-feed">

                <article class="work-item " data-show="on-scroll" >
                    <a href="">
                        <picture>
                            <img src='img/index/welcome_4.png' border='0' width='120' height='120' alt=''>
                        </picture>
                        <div class="content">
                            <div>
                                <div>
                                    <h1>自由</h1>
                                    <h2>关注，收藏，转发，信息分享随心所欲</h2>
                                </div>
                            </div>
                        </div></a>
                </article>

            </div>
        </section>
    </div>
    
    <!-- 
    <a class="full-btn btn-outline" id="show-more" href="#" data-show="on-scroll">查看更多案例</a>
    <div class="container-fluid">
        <div class="row featurette qrcode">
            <div class="col-lg-12" data-show="on-scroll">
                <h2>品牌策略 × 品牌设计 × 品牌传播 </h2><br>
                <h4>和果实一起播下品牌的种子</h4><br>
                <h5><a href="#" class="btn-outline">查看更多服务</a></h5>
            </div>
        </div>
    </div>
     -->
</div>
<footer class="footer">

    <div class="nav">
        <ul id="menu">
            <li>品牌垂询电话: 025-8888888</li>
            <li>MAIL: xxxxxxx@xxxx.com  </li>
        </ul>
    </div>
    <div class="info">
        <ul id="social-menu">


            <li ><a href="#"  target="_blank" rel="external">f</a></li>
            <li ><a href="#" target="_blank" rel="external">g</a></li>
            <li ><a href="#" target="_blank" rel="external">h</a></li>
            <li ><a href="#"  rel="external">U</a></li>


        </ul>
        <p class="copyright"><a href="#">Copyright © </a><br />xxxxxxxxx Branding design Co,.Ltd.<br>
            All rights reserved. <br>
             </p>
</footer>



</body></html>