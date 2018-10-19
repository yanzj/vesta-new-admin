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
    </style>
</head>
<script>
    $(function(){
        console.log("sqq")
        $("#002100010000").addClass("active");
        $("#002100010000").parent().parent().addClass("in");
        $("#002100010000").parent().parent().parent().parent().addClass("active");
    })
    function validate(status) {
        document.getElementById("frm").submit();
    }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="002100010000" username="${authPropertystaff.staffName}" />
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body" style="min-height:380px">
                    <form class="form-horizontal" id="frm" action="../houseplan/update"
                          method="post" enctype="MULTIPART/FORM-DATA">
                        <input type="hidden" id="id" name="id" value="${deliveryPlanCrmEntity.id}">
                        <%--项目，不更新只展示--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="projectName"><spring:message
                                    code="HousePayBatch.projectName"/></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="projectName"
                                       name="projectName" value="${deliveryPlanCrmDto.projectName}" readonly>
                            </div>
                        </div>
                        <%--计划名称--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="planName"><spring:message
                                    code="DeliveryPlanCrm.plan"/></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="planName"
                                       name="planName" value="${deliveryPlanCrmEntity.planName}" readonly>
                            </div>
                        </div>
                        <%--计划状态--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="state"><spring:message
                                    code="DeliveryPlanCrm.state"/></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="state"
                                       name="state" value="${deliveryPlanCrmEntity.state}" readonly>
                            </div>
                        </div>
                        <%--计划时间--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="planDate"><spring:message
                                    code="DeliveryPlanCrm.time"/></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="planDate"
                                       name="planDate"
                                       value="${fn:substring(deliveryPlanCrmEntity.planStart, 0, 10)}至${fn:substring(deliveryPlanCrmEntity.planEnd, 0, 10)}"
                                       readonly>
                            </div>
                        </div>
                        <%--集中交付地点--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="focusAddress"><spring:message
                                    code="DeliveryPlanCrm.distance"/></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="focusAddress"
                                       name="focusAddress" value="${deliveryPlanCrmEntity.focusAddress}" readonly>
                            </div>
                        </div>
                        <%--计划描述--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="description"><spring:message
                                    code="DeliveryPlanCrm.description"/></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="description"
                                       name="description" value="${deliveryPlanCrmEntity.description}" readonly>
                            </div>
                        </div>
                        <%--简称--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="shortName"><spring:message
                                    code="HousePayBatch.shortName"/></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="shortName"
                                       name="shortName" value="${deliveryPlanCrmEntity.shortName}">
                            </div>
                        </div>
                        <%--没时间段接待人次-上午--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="maxUserAm"><spring:message
                                    code="DeliveryPlanCrm.am"/></label>

                            <div class="col-sm-9" style="padding-top:7px;">
                                <input type="text" class="form-control" placeholder="" id="maxUserAm" name="maxUserAm"
                                       value="${deliveryPlanCrmEntity.maxUserAm}">
                            </div>
                        </div>
                        <%--没时间段接待人次-下午--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="maxUserPm"><spring:message
                                    code="DeliveryPlanCrm.pm"/></label>

                            <div class="col-sm-9" style="padding-top:7px;">
                                <input type="text" class="form-control" placeholder="" id="maxUserPm" name="maxUserPm"
                                       value="${deliveryPlanCrmEntity.maxUserPm}">
                            </div>
                        </div>

                        <input type="hidden" id="batchStatus" name="batchStatus" value="">

                        <div class="text-center form-group  col-lg-12" style="padding:0;">
                            <c:if test="${function.qch40010071 eq 'Y'}">
                                <button type="button" class="btn btn-primary" onclick="validate('0')"><spring:message
                                        code="problem.save"/></button>
                            </c:if>
                            <button type="button" class="btn btn-primary" onclick="history.back()">取消</button>
                        </div>

                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
</div>
</div>
</div>
<div class='clearfix'> </div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<!--图片上传控件-->
<script>
    function checkURL(URL) {
        var str = URL;
        var Expression = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
                + "(([0-9]{1,3}.){3}[0-9]{1,3}" + "|"
                + "([0-9a-z_!~*'()-]+.)*"
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]."
                + "[a-z]{2,6})"
                + "(:[0-9]{1,4})?"
                + "((/?)|" + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        var objExp = new RegExp(Expression);
        if (objExp.test(str) == true) {
            return true;
        } else {
            return false;
        }

    }

//    var inputs = $("#homePageimgpath").get(0);
//    var result = document.getElementById("demo_homePageimgpath");
//    if (typeof FileReader === 'undefined') {
//        result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
//        inputs.setAttribute('disabled', 'disabled');
//    } else {
//        inputs.addEventListener('change', readFile, false);
//    }

    function readFile() {

        for (var i = 0; i < this.files.length; i++) {
            var file = this.files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                result.innerHTML = '<img src="' + this.result + '" style="width:100px" alt=""/>';
                results.innerHTML(result.innerHTML);
            }
        }
    }

//    var input = $("#activityimgpath").get(0);
//    var results = document.getElementById("demo_activityimgpath");
//    if (typeof FileReader === 'undefined') {
//        results.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
//        input.setAttribute('disabled', 'disabled');
//    } else {
//        input.addEventListener('change', readFiles, false);
//    }

    function readFiles() {

        for (var i = 0; i < this.files.length; i++) {
            var file = this.files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                results.innerHTML = '<img src="' + this.result + '" style="width:100px" alt=""/>';
            }
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

<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
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
</body>
</html>
