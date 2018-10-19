<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    List<Viewmodel> menulist = (List<Viewmodel>) session.getAttribute("menulist");
    List<Viewmodel> secanViewlist = (List<Viewmodel>) session.getAttribute("secanViewlist");

%>
<!DOCTYPE HTML>
<html>
<head>
    <title><spring:message code="sys.tital"/></title>
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>

    <script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
    </script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>
    <!-- Bootstrap Core CSS -->
    <link href="../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- Custom CSS -->
    <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <link rel="stylesheet" href="../static/css/detailsCss.css"/>

    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--animate-->
    <%--problemDetail  css style--%>
    <link rel="stylesheet" href="../static/css/detailsCss.css"/>
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        $(function () {
            console.log("sqq")
            $("#006600010000").addClass("active");
            $("#006600010000").parent().parent().addClass("in");
            $("#006600010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <%--图片放大--%>
    <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>
    <%----%>
    <style>
        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 70px;
        }
    </style>
    <STYLE type=text/css>
        .newDetail__ .notice_test > div {
            padding: 10px 0 0;
        }

        .newDetail__ .notice_test > div span {
            font-size: 16px;
        }

        .newDetail__ .notice_test .imgList img {
            width: 90px;
            height: 90px;
            margin-right: 5px;
        }

        input.error {
            border: 1px solid red;
        }

        label.error {
            background: url("./demo/images/unchecked.gif") no-repeat 0px 0px;
            padding-left: 16px;
            padding-bottom: 2px;
            font-weight: bold;
            color: #EA5200;
        }

        label.checked {
        / / background: url("./demo/images/checked.gif") no-repeat 0 px 0 px;
        }

        .newDetail__ .backTo {
            width: 100px;
            height: 40px;
            background: #53b9fc;
            border-radius: 5px;
            font-size: 14px;
            margin-top: 30px;
            margin-left: 150px;
            margin-bottom: 65px;
        }

        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 70px;
        }

        #locationImg {
            width: 500px;
            /*height:300px;*/
            border: none;
        }

        .positionArea {
            position: absolute;
            z-index: 99;
            background: rgba(0, 0, 0, 0.5);
            color: #ffe400;
            font-size: 15px;
        }

        .point {
            position: absolute;
            z-index: 9999;
            width: 15px;
            height: 15px;
            border-radius: 15px;
            background-color: #ffe400;
            /*display:none;*/
        }

        .gallery {
            list-style: none;
            padding: 10px 0 0;
        }

        .gallery span {
            font-size: 16px;
        }
    </STYLE>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006600010000" username="${authPropertystaff.staffName}"/>
    <div class="newDetail__">
        <div class="nav">
            <div class="rightButton">
                <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
            </div>
        </div>
        <div class="notice_head" style="height: 80px;width: 90%;border-bottom: 1px dashed #ccc;">
            <h2 style="font-size: 18px;text-align: left;height: 40px;font-weight: bold;">检查计划详情</h2>
            <span class="notice_time" style="width: 225px;"><spring:message code="problem.createdate"/>：
                <span>${plan.createDate}</span>
            </span>

            <span class="ques_sta " style="width: 225px;"><spring:message code="problem.status"/>：
                <span>
                    <c:if test="${plan.state eq 'release'}">已发布</c:if>
                    <c:if test="${plan.state eq 'unpublished'}">未发布</c:if>
                </span>
            </span>
            <span class="key1 notice_time " style="width: 225px;"><spring:message
                    code="problem.createname"/>：${plan.createName}</span>
        </div>
        <div class="notice_inner notice_test" style="padding-bottom: 40px;">
            <div class="room">
                <span class="key1">创建单位：</span>
                <span class="value_">${plan.createUnitName}，
                    <c:if test="${plan.type eq '1'}">
                    集团创建
                    </c:if>
                    <c:if test="${plan.type eq '2'}">
                        区域创建
                    </c:if>
                    <c:if test="${plan.type eq '3'}">
                        项目自查
                    </c:if>
                </span>
            </div>
            <%--<div class="duration">--%>
                <%--<span class="key1">创建单位：</span>--%>
                <%--<span class="value_">--%>
                    <%--<c:if test="${plan.type eq '1'}">--%>
                        <%--集团创建--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${plan.type eq '2'}">--%>
                        <%--区域创建--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${plan.type eq '3'}">--%>
                        <%--项目自查--%>
                    <%--</c:if>--%>
                <%--</span>--%>
            <%--</div>--%>
            <div class="room">
                <span class="key1">开始时间：</span>
                <span class="value_">${plan.planStart}</span>
            </div>
            <div class="room_position">
                <span class="key1">结束时间：</span>
                <span class="value_">${plan.planEnd}</span>
            </div>
            <div class="duration">
                <span class="key1">参加人员：</span>
                <span class="value_">${plan.participant}</span>
            </div>
            <%--<div class="duration">--%>
                <%--<span class="key1">得&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分：</span>--%>
                <%--<span class="value_">${plan.score}</span>--%>
            <%--</div>--%>
            <%--<div class="duration">--%>
                <%--<span class="key1">总&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分：</span>--%>
                <%--<span class="value_">${plan.fraction}</span>--%>
            <%--</div>--%>
            <div class="mun_pic">
                <span class="key1" style="width: 90px;">签字照片：</span>
                <ul class="gallery">
                    <c:forEach items="${plan.imageList}" var="image">
                        <label class="control-label imgList">
                            <li>
                                <a href="${image.imageUrl}">
                                    <img src="${image.imageUrl}">
                                </a>
                            </li>
                        </label>
                    </c:forEach>
                </ul>
            </div>
            <div class="border1" style="margin-top: 15px;"></div>
            <div class="complete_dur">
                <span class="key1">参检单位：</span>
                <c:forEach items="${plan.inUnitList}" var="inUnit">
                        <span class="value_">
                                ${inUnit.projectName}
                        </span>
                </c:forEach>
            </div>
            <div class="complete_dur">
                <span class="key1">被检单位：</span>
                <c:forEach items="${plan.unitList}" var="unit">
                        <span class="value_">
                                ${unit.projectName}
                        </span>
                </c:forEach>
            </div>
            <div class="mun_pic">
                <span class="key1" style="width: 90px;">签字照片：</span>
                <ul class="gallery">
                    <c:forEach items="${plan.unitList}" var="unit">
                        <c:forEach items="${unit.imageList}" var="image">
                            <label class="control-label imgList">
                            <li>
                                <a href="${image.imageUrl}">
                                    <img src="${image.imageUrl}">
                                </a>
                            </li>
                            </label>
                        </c:forEach>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script src="../../../../static/js/zoom.min.js"></script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script>
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
</script>
<script>

    function delImg(obj, type) {
        if (type == '1') {
            $("#f" + obj).remove();
            $("#i" + obj).remove();
        } else {
            $("#im" + obj).remove();
            $("#ih" + obj).remove();
        }

    }
    var imgtype = true;
    function check(fnUpload) {
        var filename = fnUpload.value;
        var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
        if (mime != ".jpg" && mime != ".png" && mime != ".jpeg" && mime != ".gif") {
            alert("上传图片类型错误");
            imgtype = false;
        } else {
            imgtype = true;
        }

    }
</script>
</body>
</html>