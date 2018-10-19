<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="aaa">
<head>
    <title>金茂移动应用管理平台</title>
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="">
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- Bootstrap Core CSS -->
    <link href="./static/css/bootstrap.css" rel="stylesheet" type="text/css">
    <!-- Custom CSS -->
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="./static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="./static/js/jquery-1.11.1.min.js"></script>
    <script src="./static/js/modernizr.custom.js"></script>
    <!--animate-->
    <link href="./static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="./static/js/wow.min.js"></script>
    <%--图片放大--%>
    <link rel="stylesheet" href="./static/css/zoom.css" media="all"/>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="./static/js/metisMenu.min.js"></script>
    <%--<script src="./static/js/custom.js"></script>--%>
    <link href="./static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
</head>
<style type="text/css">
    .cbp-spmenu-push div#page-wrapper {
        background-color: #FFFFFF;
    }

    .form-body {
        padding: 0 8%;
        margin-top: 10%;
    }

    .form-group {
        height: 70px;
        margin: 0 !important;
        padding: 0;
    }

    .form-group input {
        height: 60%;
        width: 100%;
        line-height: 40px;
        text-align: center;
        font-size: 20px;
        color: #999;
    }

    /*body{*/
    /*background-size:100%;*/
    /*background: url("./static/images/loginImage/logoBg.png") no-repeat left top;*/
    /*}*/
    .loginContent {
        width: 100%;
        height: 100%;
        background: url("./static/images/loginImage/logoBg.png") no-repeat left top;
        background-size: 100%;
    }

    .leftContent {
        float: left;
        width: 60%;
        position: relative;
        height: 100%;
    }

    .leftContent .logo {
        width: 566px;
        height: 92px;
        background: url("./static/images/loginImage/logo.png") no-repeat left top;
        position: absolute;
        left: 50%;
        margin-left: -283px;
        top: 40%;
    }

    .leftContent .descTxt {
        position: absolute;
        left: 50%;
        top: 50%;
    transform：translate(- 50 %, - 50 %);
        -webkit-transform: translate(-50%, -50%);
        -moz-transform: translate(-50%, -50%);
        color: #fff;
        font-size: 48px;
        margin-top: 8%;

    }

    .rightContent {
        float: left;
        width: 30%;
        background: rgba(255, 255, 255, .8);
        height: 100%;
    }

    .rightContent h2 {
        color: #3a5f7e;
        font-size: 53px;
        text-align: center;
        margin-top: 17%;
    }

    .txt {
        color: #005aa7;
        border: 0px;
        border-bottom: 1px solid #005aa7; /* 下划线 */
        background-color: transparent; /* 背景透明 */
    }

    .btn {
        background-color: transparent; /* 背景透明 */
        border: 0px; /*border:0px solid #005aa7;取消边框 */
        cursor: pointer;
    }

    .rember {
        height: 30px;
        line-height: 30px;
        overflow: hidden;
        position: relative;
    }

    .rember em {
        margin-left: 18px;
        font-style: normal;
    }

    .rember .password {
        position: absolute;
        width: 15px;
        height: 15px;
        top: 50%;
        margin-top: -7.5px;
        margin-right: 10px;
        background: #bcc9d2;
        color: #bcc9d2;
    }

    .rember label {
        color: #000;
        font-weight: normal;
        font-size: 16px;
        float: left;
    }

    .rember span {
        font-size: 16px;
        color: #003159;
        float: right;
    }

    .loginTxt {
        font-size: 14px;
        color: #999999;
        text-align: center;
        overflow: hidden;
        width: 100%;
    }

    .loginTxt hr {
        float: left;
        height: 1px;
        border: none;
        border-top: 1px solid #999;
        width: 18%;
    }

    .loginTxt span {
        float: left;
        /* margin: 10px 6%; */
        /* display: inline-block; */
        width: 64%;
        margin: 10px 0;
        text-align: center;
    }

    .form-group2 {
        line-height: 30px;
        font-size: 14px;
    }

    .form-group2 label {
        padding-left: 0;
        margin-right: 8px;
    }

    .form-group2 input {
        margin-right: 8px;
    }

    .loginBtn {
        width: 90%;
        height: 50px;
        margin-left: 5%;
        text-align: center;
        color: #fff;
        background: #003159;
        border-radius: 10px;
        margin-top: 10%;
    }
    .imgList img {
        width: 140px;
        height: 140px;
        margin-left: 5px;
        margin-top: 50px;
    }
    .codelist {
        padding: 0 4%;
        width: 100%;
        overflow: hidden;
    }
    .cordimg{
        display: inline-block;
        float: left;
        overflow: hidden;
        width: 31.3%;
        margin-right: 3%;
        margin-top: 20px;
    }
    .cordimg:last-child{
        margin-right: 0;
    }
    .cordimg img{
        width: 100%;
        pandding-top:100%
    }
</style>
<body>
<div class="loginContent">
    <div class="leftContent">
        <div class="logo"></div>
        <div class="descTxt">移动综合管理平台</div>
    </div>
    <div class="rightContent">
        <div class="login">
            <h2>welcome</h2>
            <div class="form-body">
                <form class="form-horizontal" id="authLogin" action="./login/authLoginCheck.html" method="post">
                    <div class="form-group">
                        <input type="text" class="txt" name="userId" id="userId" value="请输入用户名称"
                               onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
                               onBlur="if(!value){value=defaultValue;this.style.color='#999'}">
                    </div>
                    <div class="form-group">
                        <input type="password" class="txt" name="password" id="password" placeholder="请输入用户密码"
                               onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
                               onBlur="if(!value){value=defaultValue;this.style.color='#999'}">
                    </div>
                    <div class="rember">
                        <label>
                            <input type="checkbox" name="checkbox" onclick="remerbPwd()" class="password"/>
                            <em>记住密码</em>
                        </label>
                        <span>忘记密码</span>
                    </div>
                    <%--<div class="loginTxt"><hr/><span>请选择您想登陆的后台应用</span><hr/></div>--%>

                    <%--<div class="form-group2">--%>
                    <%--<div class="checkbox">--%>
                    <%--<input type=text style="display:none" name="checkLogin" id="checkLogin" value="0">--%>
                    <%--<label> <input type="radio" name="radio" onclick="checklogin('2')" />金茂荟</label>--%>
                    <%--<label> <input type="radio" name="radio" onclick="checklogin('3')" />金茂质量</label>--%>
                    <%--<label> <input type="radio" name="radio" onclick="checklogin('1')" />平安金茂</label>--%>
                    <%--<label> <input type="radio" name="radio" onclick="checklogin('4')" />金茂客关</label>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <input type="button" class="btn  btn-primary loginBtn" onclick="loginAuth()" value="登录"></input>
                </form>
            </div>
        </div>
        <div >
            <div class="codelist">
                <a href="http://images.chinajinmao.cn/houserental/b96e080bb5a14d409edd1f810cbbe373.png" class="zoom cordimg">
                    <img src="http://images.chinajinmao.cn/houserental/7b1d44cc766a4920a46d8bbdf40989ce.png"></p>
                    <h4 style="    text-align: center;margin-top: 10px">金茂质量</h4>
                </a>
                <a href="http://images.chinajinmao.cn/houserental/94a312928bc04020b277430e1dca89fa.png" class="zoom cordimg">
                    <img src="http://images.chinajinmao.cn/houserental/9d3e4abcb18d43a2b7306ebcb76419a8.png"></p>
                    <h4 style="    text-align: center;margin-top: 10px">金茂服务家</h4>
                </a>
                <a href="http://images.chinajinmao.cn/houserental/c6c8af0e34374a4b94141fff1a951392.png" class="zoom cordimg">
                    <img src="http://images.chinajinmao.cn/houserental/9ba67454901e4cd9812712099eae7b92.png"></p>
                    <h4 style="    text-align: center;margin-top: 10px">平安金茂</h4>
                </a>
            </div>


        </div>
    </div>
</div>

<!--footer-->
<div class="footer navbar-fixed-bottom">
    <div class="container">
        <span class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术支持：迈动互联(北京)信息科技有限公司<b>&nbsp;4006-612-966</b></span>
        <span class="pull-right">JINMAO Group 版权所有</span>
    </div>
</div>
<!--//footer-->
<!-- Classie -->
<script src="./static/js/zoom.min.js"></script>
<script src="./static/js/classie.js"></script>
<script>
    var menuLeft = document.getElementById('cbp-spmenu-s1'),
        showLeftPush = document.getElementById('showLeftPush'),
        body = document.body;

    showLeftPush.onclick = function () {
        classie.toggle(this, 'active');
        classie.toggle(body, 'cbp-spmenu-push-toright');
        classie.toggle(menuLeft, 'cbp-spmenu-open');
        disableOther('showLeftPush');
    };

    function disableOther(button) {
        if (button !== 'showLeftPush') {
            classie.toggle(showLeftPush, 'disabled');
        }
    }

    function checklogin(chek) {
        if ("1" == chek) {
            $("#checkLogin").val("st");
        } else if ("3" == chek) {
            $("#checkLogin").val("es");
        } else if ("4" == chek) {
            $("#checkLogin").val("qc");
        } else {
            //没选中
            $("#checkLogin").val("all");
        }
    }
    function loginAuth() {
        var userId = $("#userId").val();//
        if (userId == null || userId == undefined || userId == "" || userId == "请输入用户名称") {
            alert("请输入用户名称");
        } else {
            var password = $("#password").val();//
            if (password == null || password == undefined || password == "" || password == "请输入用户密码") {
                alert("请输入用户密码");
            } else {
                $("#authLogin").submit();
//                var checkbox =$("#checkLogin").val();//
//                if( checkbox==null||checkbox==undefined||checkbox==""||checkbox=="0"){
//                    alert("请选择您所需登录的后台应用");
//                }else{
//                    $("#authLogin").submit();
//                }
            }
        }
    }


</script>


<!--scrolling js-->
<%--<script src="./static/js/jquery.nicescroll.js"></script>--%>
<script src="./static/js/scripts.js"></script>
<%--<div id="ascrail2000" class="nicescroll-rails" style="width: 6px; z-index: 1000; background-color: rgb(66, 79, 99); cursor: default; position: fixed; top: 0px; height: 100%; right: 0px; display: none; background-position: initial initial; background-repeat: initial initial;"><div style="position: relative; top: 0px; float: right; width: 6px; height: 0px; background-color: rgb(242, 179, 63); border: 0px; background-clip: padding-box; border-top-left-radius: 10px; border-top-right-radius: 10px; border-bottom-right-radius: 10px; border-bottom-left-radius: 10px;"></div></div><div id="ascrail2000-hr" class="nicescroll-rails" style="height: 6px; z-index: 1000; background-color: rgb(66, 79, 99); position: fixed; left: 0px; width: 100%; bottom: 0px; cursor: default; display: none; background-position: initial initial; background-repeat: initial initial;"><div style="position: relative; top: 0px; height: 6px; width: 0px; background-color: rgb(242, 179, 63); border: 0px; background-clip: padding-box; border-top-left-radius: 10px; border-top-right-radius: 10px; border-bottom-right-radius: 10px; border-bottom-left-radius: 10px;"></div></div>--%>
<!--//scrolling js-->
<!-- Bootstrap Core JavaScript -->
<script src="./static/js/bootstrap.js"></script>


</body>

</html>