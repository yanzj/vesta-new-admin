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
            $("#007600010000").addClass("active");
            $("#007600010000").parent().parent().addClass("in");
            $("#007600010000").parent().parent().parent().parent().addClass("active");
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
    <STYLE type=text/css>

        .flowersList img {
            width: 20px;
        }

        .gallery {
            list-style: none;
            padding: 10px 0 0;
        }

        .gallery span {
            font-size: 16px;
        }

        .imgList img {
            width: 400px;
            height: 250px;
            margin-right: 5px;
        }
    </STYLE>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007600010000" username="${authPropertystaff.staffName}"/>
    <div class="widget-shadow " data-example-id="basic-forms">
        <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
            <div class="form-group col-lg-7" style="margin-top: 7px">
                <label class="col-sm-2 control-label" style="text-align: right">轮播图类型：</label>
                <div class="col-sm-5">
                        <span>
                            <c:if test="${slideShowDTO.slideShowType eq '1'}">图文</c:if>
                            <c:if test="${slideShowDTO.slideShowType eq '2'}">视频</c:if>
                            <c:if test="${slideShowDTO.slideShowType eq '3'}">链接</c:if>
                        </span>
                </div>
            </div>
            <div class="form-group col-lg-7" style="margin-top: 7px">
                <label class="col-sm-2 control-label" style="text-align: right">标题：</label>
                <div class="col-sm-5">
                    <span>${slideShowDTO.slideShowTitle}</span>
                </div>
            </div>
            <c:if test="${slideShowDTO.slideShowType eq '1'}">
                <div class="form-group col-lg-7" style="margin-top: 7px">
                    <label class="col-sm-2 control-label" style="text-align: right">详情：</label>
                    <div class="col-sm-5">
                        <span>${slideShowDTO.slideShowContent}</span>
                    </div>
                </div>
            </c:if>
            <c:if test="${slideShowDTO.slideShowType eq '2'}">
                <div class="form-group col-lg-7" style="margin-top: 7px">
                    <label class="col-sm-2 control-label" style="text-align: right">视频链接：</label>
                    <div class="col-sm-5">
                        <span class="value_"><a href="${slideShowDTO.videoUrl}"
                                                target="view_window">${slideShowDTO.videoUrl}</a></span>
                    </div>
                </div>
            </c:if>
            <c:if test="${slideShowDTO.slideShowType eq '3'}">
                <div class="form-group col-lg-7" style="margin-top: 7px">
                    <label class="col-sm-2 control-label" style="text-align: right">链接：</label>
                    <div class="col-sm-5">
                        <span class="value_"><a href="${slideShowDTO.outUrl}"
                                                target="view_window">${slideShowDTO.outUrl}</a></span>
                    </div>
                </div>
            </c:if>
            <div class="form-group col-lg-7" style="margin-top: 7px">
                <label class="col-sm-2 control-label" style="text-align: right">轮播图配图：</label>
                <div class="col-sm-9">
                    <span class="value_ imgList">
                        <a href="${slideShowDTO.slideShowImgUrl}" class="zoom">
                             <img src="${slideShowDTO.slideShowImgUrl}">
                        </a>
                    </span>
                </div>
            </div>
            <div class="form-group col-lg-7" style="margin-top: 7px">
                <div class="col-sm-4 rightButton" style="text-align: center">
                    <button class="btn btn-primary" onclick="javaScript:history.go(-1)">确认</button>
                </div>
            </div>
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

</body>
</html>