<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%response.setHeader("cache-control", "public"); %>
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
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        $(function(){
            console.log("sqq");
            $("#005400050000").addClass("active");
            $("#005400050000").parent().parent().addClass("in");
            $("#005400050000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <style>
        .flowersList img {
            width: 20px;
        }
        .imgList img {
            width: 100px;
            height: 120px;
        }
        .sidebar ul li {
            border-bottom: 0;
        }
    </style>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005400050000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="005400050000"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../offlineActivity/saveOrUpdateOfflineActivity.html" method="post" enctype="MULTIPART/FORM-DATA">
                    <input type="hidden" id="id" name="activityId" value="${offlineActivity.activityId}">
                    <%--城市区域--%>
                    <div class="form-group  col-xs-4">
                        <label for="cityId" class="col-sm-3 control-label"><spring:message code="announcementDTO.cityName"/></label>
                        <div class="col-sm-5">
                            <select id="cityId" name="cityId" class="form-control" onchange="changeCityName()">
                                <option value="-1">--请选择城市--</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq offlineActivity.cityId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" id="cityName" name="cityName" value="${offlineActivity.cityName}">
                        </div>
                    </div>
                    <%-- 活动标题 --%>
                    <div class="form-group col-xs-4">
                        <label class="col-sm-3 control-label" for="activityTitle">活动标题</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="activityTitle" name="activityTitle" value="${offlineActivity.activityTitle}">
                        </div>
                    </div>
                    <%-- 活动地点 --%>
                    <div class="form-group col-xs-4">
                        <label class="col-sm-3 control-label" for="activityPlace">活动地点</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="activityPlace" name="activityPlace" value="${offlineActivity.activityPlace}">
                        </div>
                    </div>
                    <%-- 活动开始时间 --%>
                    <div class="form-group col-xs-4">
                        <label class="col-sm-3 control-label" for="activityStartTimeStr">活动时间</label>
                        <div class="input-group date form_datetime col-sm-5" data-date=""
                             data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input1">
                            <input type="text" class="form-control " placeholder="" id="activityStartTimeStr"
                                   value="${offlineActivity.activityStartTimeStr}"
                                   name="activityStartTimeStr" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <%-- 活动结束时间 --%>
                    <div class="form-group col-xs-4">
                        <label class="col-sm-3 control-label" for="activityEndTimeStr">至</label>
                        <div class="input-group date form_datetime col-sm-5" data-date=""
                             data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input1">
                            <input type="text" class="form-control " placeholder="" id="activityEndTimeStr"
                                   value="${offlineActivity.activityEndTimeStr}"
                                   name="activityEndTimeStr" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <%-- 是否抽奖 --%>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-3 control-label">是否抽奖</label>
                        <div class="col-sm-5">
                            <input type="radio" name="isLuckDraw" id="isLuckDraw_1" value="0" <c:if test="${offlineActivity.isLuckDraw ne '0'}">checked</c:if>>否
                            <input type="radio" name="isLuckDraw" id="isLuckDraw_2" value="1" <c:if test="${offlineActivity.isLuckDraw eq '1'}">checked</c:if>>是
                        </div>
                    </div>
                    <%-- 一等奖人数 --%>
                    <div class="form-group col-xs-4">
                        <label class="col-sm-3 control-label" for="onePrizeNumber">一等奖人数</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="onePrizeNumber" name="onePrizeNumber" value="${offlineActivity.onePrizeNumber}">
                        </div>
                    </div>
                    <%-- 二等奖人数 --%>
                    <div class="form-group col-xs-4">
                        <label class="col-sm-3 control-label" for="twoPrizeNumber">二等奖人数</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="twoPrizeNumber" name="twoPrizeNumber" value="${offlineActivity.twoPrizeNumber}">
                        </div>
                    </div>
                    <%-- 三等奖人数 --%>
                    <div class="form-group col-xs-4">
                        <label class="col-sm-3 control-label" for="threePrizeNumber">三等奖人数</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="threePrizeNumber" name="threePrizeNumber" value="${offlineActivity.threePrizeNumber}">
                        </div>
                    </div>
                    <div class="text-center form-group  col-xs-12">
                        <button type="button" class="btn btn-primary" onclick="toSave()">发布</button>
                        <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);">
                            <spring:message code="common_cancel"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    //发布
    function toSave(){
        if($("#cityId").val() == ""){
            alert("请添加城市！");
            return;
        }
        if ($("#activityTitle").val() == "") {
            alert("请输入活动标题！");
            return;
        }
//        var title = $("#title").val();
//        if (title.length > 100) {
//            alert("促销信息超过最大长度！");
//            return;
//        }
        if ($("#activityPlace").val() == "") {
            alert("请输入活动地点！");
            return;
        }
        if ($("#activityStartTime").val() == "" || $("#activityEndTime").val() == "") {
            alert("请选择活动时间！");
            return;
        }
        $("#fromAdd").submit();
    }

    //城市切换改变城市名称
    function changeCityName() {
        $("#cityName").val($("#cityId").find("option:selected").text());
    }
</script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayBtn: true,
        linkField: "mirror_field",
        linkFormat: "yyyy-mm-dd hh:ii"
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
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>