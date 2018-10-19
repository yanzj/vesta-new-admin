<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    <link href="../../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="../../../../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- Custom CSS -->
    <link href="../../../../static/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="../../../../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../../../../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="../../../../static/js/jquery-1.11.1.min.js"></script>
    <script src="../../../../static/js/modernizr.custom.js"></script>

    <script type="text/javascript" src="../../../../static/plus/js/jquery.validate.js"></script>
    <link rel="stylesheet" href="../../../../static/css/detailsCss.css"/>
    <!--animate-->
    <link href="../../../../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../../../../static/js/wow.min.js"></script>
    <script>
        $(function () {
            console.log("sqq")
            $("#003000010000").addClass("active");
            $("#003000010000").parent().parent().addClass("in");
            $("#003000010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../../../../static/js/metisMenu.min.js"></script>
    <script src="../../../../static/js/custom.js"></script>
    <link href="../../../../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <link href="../../../../static/css/target.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="003000010000" username="${authPropertystaff.staffName}"/>
    <div class="newDetail__" style="">
        <div class="nav">
            <div class="rightButton">
                <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
            </div>
        </div>
        <div class="notice_inner" style="padding-bottom: 40px;width: auto;padding-left: 0;">
            <h2  class="sideTitle"><span>${projectSideStation.processName}</span></h2>

            <div class="container" style=" width: 100%;padding: 0;margin-top:8px">
                <div class="col-lg-12" style="padding-left: 0;margin-left: -30px;">
                    <div class="repair_per col-sm-4">
                        <span class="key1">项目名称：</span>
                        <span class="value_">${projectSideStation.projectName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">工序名称：</span>
                        <span class="value_">${projectSideStation.processName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">旁站位置：</span>
                        <span class="value_">${projectSideStation.location}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">旁站时间：</span>
                        <span class="value_">${projectSideStation.sideStationTimeSpace}</span>
                        <%--<fmt:formatDate type="date" value="${projectSideStation.sideStationDate}" dateStyle="default"--%>
                        <%--pattern="yyyy-MM-dd"/>--%>
                    </div>

                    <div class="repair_per col-sm-4">
                        <span class="key1">旁站人员：</span>
                        <span class="value_">${projectSideStation.sideStationStaffName}</span>
                    </div>
                </div>
                <%--<ul class="nav nav-list"--%>
                <%--style="margin-left: 20px;width: 870px;padding-top: 150px;border-bottom: 1px solid #cccccc">--%>
                <%--<li class="divider"></li>--%>
                <%--</ul>--%>
                <div class="col-lg-12" style="padding-left: 0;margin-left: -30px;">
                    <div class="repair_per col-sm-4">
                        <span class="key1">开始时间：</span>
                        <span class="value_">${projectSideStation.sideStationStartDate}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">结束时间：</span>
                        <span class="value_">${projectSideStation.sideStationEndDate}</span>
                    </div>
                </div>
                <ul class="gallery">
                    <div class="col-lg-12" style="margin:17px 0 15px 0;border-top: 1px dashed #ccc;padding-top: 10px;padding-left: 0;">
                        <div style="margin-left: -30px;">
                            <c:forEach items="${projectSideStation.projectSideStationDetailsDTOs}" var="item"
                                   varStatus="status">
                            <div class="col-sm-4 annalDes">
                                <div class="team-member detail">
                                    <h4  class="sideTime">${item.recordTime}</h4>

                                    <div class="col-sm-12 detailsList">
                                        <div class="col-sm-2 targetImg">
                                            <c:choose>
                                                <c:when test="${item.imageUrl != null && item.imageUrl != ''}">
                                                    <li>
                                                        <a href="${item.imageUrl}">
                                                            <span class="imgSpan"
                                                                  style="background:url(${item.imageUrl}) no-repeat center center;background-size:cover;"></span>
                                                        </a>
                                                    </li>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="imgSpan"></span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <c:if test="${item.description != null && item.description != ''}">
                                            <div class="col-sm-2  description">
                                                <p title="${item.description}">${fn:substring(item.description,0,40)}</p>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        </div>
                    </div>
                </ul>

            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<script src="../../../../static/js/zoom.min.js"></script>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>

</div>
<script type="text/javascript" src="../../../../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../../../../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
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
<!-- main content end-->
<%--<%@ include file="../../main/foolter.jsp" %>--%>
<!-- 校验 -->

<script>
    function getDetail(titel, description) {
        $("#myModalLabel").html(titel);
        $("#mo-content").html(description);
        $("#myModal").modal({
            backdrop: false,
            show: true
        });
    }
</script>
</body>
</html>