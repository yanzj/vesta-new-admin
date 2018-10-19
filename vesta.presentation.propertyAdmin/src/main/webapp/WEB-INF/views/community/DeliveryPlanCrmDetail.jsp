<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2016/2/20
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
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
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <style>
        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 70px;
        }
        .labels{
            font-weight: bold;
            font-size: 16px;
            text-align: right;
        }
        .property{
            font-weight: 400;
            font-size: 14px;
        }
    </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005400010000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                    <div class="row">
                        <input type="hidden" id="id" name="id" value="${deliveryPlanCrmEntity.id}">
                        <%--项目--%>
                        <div class="form-group col-lg-6">
                            <%--<label class="col-sm-3 control-label" for="projectName"><spring:message--%>
                            <%--code="HousePayBatch.projectName"/></label>--%>

                            <div class="col-sm-5 labels">项目名称:</div>
                            <div class="col-sm-7 property">
                                ${deliveryPlanCrmDto.projectName}
                            </div>
                        </div>
                        <%--计划名称--%>
                        <div class="form-group col-lg-6">
                            <div class="col-sm-5 labels">计划名称:</div>
                            <div class="col-sm-7 property">
                                ${deliveryPlanCrmEntity.planName}
                            </div>
                        </div>
                        <%--计划状态--%>
                        <div class="form-group col-lg-6">
                            <div class="col-sm-5 labels">计划状态:</div>
                            <div class="col-sm-7 property">
                                ${deliveryPlanCrmEntity.state}
                            </div>
                        </div>
                        <%--计划时间--%>
                        <div class="form-group col-lg-6">
                            <div class="col-sm-5 labels">计划时间:</div>
                            <div class="col-sm-7 property">
                                ${fn:substring(deliveryPlanCrmEntity.planStart, 0, 10)}至${fn:substring(deliveryPlanCrmEntity.planEnd, 0, 10)}
                            </div>
                        </div>
                        <%--集中交付地点--%>
                        <div class="form-group col-lg-6">
                            <div class="col-sm-5 labels">集中交付地点:</div>
                            <div class="col-sm-7 property">
                                ${deliveryPlanCrmEntity.focusAddress}
                            </div>
                        </div>
                        <%--计划描述--%>
                        <div class="form-group col-lg-6">
                            <div class="col-sm-5 labels">计划描述:</div>
                            <div class="col-sm-7 property">
                                ${deliveryPlanCrmEntity.description}
                            </div>
                        </div>

                        <%--没时间段接待人次-上午--%>
                        <div class="form-group col-lg-6">
                            <div class="col-sm-5 labels">上午接待人数:</div>
                            <div class="col-sm-7 property">
                                ${deliveryPlanCrmEntity.maxUserAm}
                            </div>
                        </div>
                        <%--没时间段接待人次-下午--%>
                        <div class="form-group col-lg-6">
                            <div class="col-sm-5 labels">下午接待人数:</div>
                            <div class="col-sm-7 property">
                                ${deliveryPlanCrmEntity.maxUserPm}
                            </div>
                        </div>

                        <div class="pull-right">
                            <button type="button" class="btn btn-primary" onclick="javascript:history.back(-1);">返回</button>
                            <!--集合长度(取决Excel是否可以导出)-->
                            <input type="hidden" id="size" value="${isExcel}">
                            <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary"
                               style="color:#fff">导出Excel</a>
                        </div>
                    </div>
            </div>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                <th>房间地址</th>
                <th>姓名</th>
                <th>手机号</th>
                <th>身份证号</th>
                <th>业主预约时间</th>
                <th>时间段</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reservationList}" var="reservation" varStatus="row">
                <tr>

                    <td><b>${row.index + 1}</b></td>
                    <td>${reservation.roomAddress}</td>
                    <td>${reservation.userName}</td>
                    <td>${reservation.userMobile}</td>
                    <td>${reservation.idCard}</td>
                    <td>${reservation.appointUserTime}</td>
                    <td>${reservation.amOrPm}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/deliveryPlan/deliveryPlanCrmDetail?pageIndex={0}&id=${deliveryPlanCrmEntity.id}&projectName=${deliveryPlanCrmDto.projectName}"/>
    </div>
</div>
</div>
</div>
</div>
<div class='clearfix'></div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
    $(function(){
        console.log("sqq")
        $("#005400010000").addClass("active");
        $("#005400010000").parent().parent().addClass("in");
        $("#005400010000").parent().parent().parent().parent().addClass("active");
    })
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

    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
            var href = "../deliveryPlan/exportExcel?type=3&pageIndex={0}&id=${deliveryPlanCrmEntity.id}&projectName=${deliveryPlanCrmDto.projectName}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }
</script>
</body>
</html>
