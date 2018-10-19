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
    </style>
</head>
<script>
    function amAndPmClick(){
        $("#amAndPmMaxUser").show();
        $("#timeRangeMaxUser").hide();
    }
    function timeRangeClick(){
        $("#amAndPmMaxUser").hide();
        $("#timeRangeMaxUser").show();
    }
    function addTimeRangeMaxUser(){
        var html = '<div class="form-group col-lg-12"><div class="form-group col-lg-6"><label class="col-sm-3 control-label">请选择时间段</label>';
        html += '<div class="col-sm-4"><div class="input-group date form_time col-sm-12">';
        html += '<input type="text" class="form-control" placeholder="" value="${homeLetter.homeLetterDate}" name="startDateList" readonly>';
        html += '<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>';
        html += '</div></div><div class="col-sm-4"><div class="input-group date form_time col-sm-12">';
        html += '<input type="text" class="form-control" placeholder="" value="${homeLetter.homeLetterDate}" name="endDateList" readonly>';
        html += '<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>';
        html += '</div></div></div><div class="cform-group col-lg-6"><label class="col-sm-3 control-label" style="padding-right: 12px">交付人数</label>';
        html += '<div class="col-sm-4"><input type="text" class="form-control maxUser" name="maxUserList"/></div></div></div>';
        $("#timeRange").append(html);

        $('.form_time').datetimepicker({
            language: 'zh-CN',
            format: 'hh:ii',
            autoclose: true,
            startView: 0,
            maxView:'minute'
        });
    }

    function validate(status) {
        var note = $("#note").val();
        if (note.length > 50) {
            alert("补充说明过长！");
            return false;
        }
        if ($("input[name='reservationType']:checked").val() == 'amAndPm'){
            var patrn=/^([+]?)(\d+)$/;
            if(!CheckNull($("#maxUserAm").val(),"请输入上午交付人数！")){
                return false;
            }
            if(patrn.test($("#maxUserAm").val()) == false){
                alert("上午交付人数请填写整形数字");
                return false;
            }
            if(!CheckNull($("#maxUserPm").val(),"请输入下午交付人数！")){
                return false;
            }
            if(patrn.test($("#maxUserPm").val()) == false){
                alert("下午交付人数请填写整形数字");
                return false;
            }
        }
        $("#batchStatus").val(status);
        document.getElementById("frm").submit();
    }

</script>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005400010000" username="${propertystaff.staffName}" />
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body" style="min-height:380px">
                    <form class="form-horizontal" id="frm" action="../overview/updateDeliveryPlan.html"
                          method="post" enctype="MULTIPART/FORM-DATA">
                        <input type="hidden" id="id" name="id" value="${deliveryPlanCrmEntity.id}">
                        <%-- 项目，不更新只展示 --%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="projectName"><spring:message
                                    code="HousePayBatch.projectName"/></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="projectName"
                                       name="projectName" value="${deliveryPlanCrmDto.projectName}" readonly>
                            </div>
                        </div>
                        <%-- 计划名称 --%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="planName"><spring:message
                                    code="DeliveryPlanCrm.plan"/></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="planName"
                                       name="planName" value="${deliveryPlanCrmEntity.planName}" readonly>
                            </div>
                        </div>
                        <%-- 计划名称_别名 --%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="planName_alias"><spring:message
                                    code="DeliveryPlanCrm.plan"/>（别名）</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="planName_alias"
                                       name="planName_alias" value="${deliveryPlanCrmEntity.planName_alias}">
                            </div>
                        </div>
                        <%-- 计划状态 --%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="state"><spring:message
                                    code="DeliveryPlanCrm.state"/></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="state"
                                       name="state" value="${deliveryPlanCrmEntity.state}" readonly>
                            </div>
                        </div>
                        <%-- 计划时间 --%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="planDate"><spring:message
                                    code="DeliveryPlanCrm.time"/></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="planDate"
                                       name="planDate"
                                       value="${fn:substring(deliveryPlanCrmEntity.planStart, 0, 10)}至${fn:substring(deliveryPlanCrmEntity.planEnd, 0, 10)}"
                                       readonly>
                            </div>
                        </div>
                        <%-- 计划时间_别名 --%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="planDate_alias"><spring:message
                                    code="DeliveryPlanCrm.time"/>（别名）</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="planDate_alias"
                                       name="planDate_alias" value="${deliveryPlanCrmEntity.planDate_alias}">
                            </div>
                        </div>
                        <%-- 集中交付地点 --%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="focusAddress"><spring:message
                                    code="DeliveryPlanCrm.distance"/></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="focusAddress"
                                       name="focusAddress" value="${deliveryPlanCrmEntity.focusAddress}" readonly>
                            </div>
                        </div>
                        <%-- 集中交付地点_别名 --%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="focusAddress_alias"><spring:message
                                    code="DeliveryPlanCrm.distance"/>（别名）</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="focusAddress_alias"
                                       name="focusAddress_alias" value="${deliveryPlanCrmEntity.focusAddress_alias}">
                            </div>
                        </div>
                        <%-- 计划描述 --%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="description"><spring:message
                                    code="DeliveryPlanCrm.description"/></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="description"
                                       name="description" value="${deliveryPlanCrmEntity.description}" readonly>
                            </div>
                        </div>
                        <%-- 计划描述_别名 --%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="description_alias"><spring:message
                                    code="DeliveryPlanCrm.description"/>（别名）</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="description_alias"
                                       name="description_alias" value="${deliveryPlanCrmEntity.description_alias}">
                            </div>
                        </div>
                        <%--房屋列表--%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="houseString"
                                   style="min-width:115px;"><spring:message
                                    code="DeliveryPlanCrm.houses"/></label>

                            <div class="col-sm-9">
                                <textarea class="form-control" name="houseString" id="houseString" readonly
                                          style="height: 71px;">${houseString}</textarea>
                            </div>
                        </div>
                        <%--补充说明--%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="note"><spring:message
                                    code="DeliveryPlanCrm.note"/></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="note"
                                       name="note" value="${deliveryPlanCrmEntity.note}">
                            </div>
                        </div>
                        <%--交付预约图片--%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="homePageimgpath"><spring:message
                                    code="activityManage.homePageimgpath"/></label>
                            <div class="col-sm-9">
                                <input type="file" class="form-control" placeholder="" id="homePageimgpath"
                                       name="homePageimgpath" value="" style="width: 418px;" onchange="check(this)">
                                <div id="demo_homePageimgpath" style="padding-top: 15px;">
                                    <c:if test="${deliveryPlanCrmEntity.url != '' && null != deliveryPlanCrmEntity.url}">
                                        <img src="${deliveryPlanCrmEntity.url}" width="100px" height="80px">
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <%--预约形式--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label">预约形式</label>
                            <div class="radio col-sm-3">
                                <label>
                                    <input type="radio" name="reservationType" id="reservationType_amAndPm"
                                           value="amAndPm" onclick="amAndPmClick()" <c:if test="${deliveryPlanCrmEntity.reservationType eq 'amAndPm'}">checked</c:if>>上下午
                                </label>
                            </div>
                            <div class="radio col-sm-3">
                                <label>
                                    <input type="radio" name="reservationType" id="reservationType_timeRange"
                                           value="timeRange" onclick="timeRangeClick()" <c:if test="${deliveryPlanCrmEntity.reservationType eq 'timeRange'}">checked</c:if>>时间段
                                </label>
                            </div>
                        </div>

                        <div class="form-group col-lg-12" id="amAndPmMaxUser" <c:if test="${deliveryPlanCrmEntity.reservationType eq 'timeRange'}">style="display:none;"</c:if>>
                            <%--时间段_上午接待人次配额--%>
                            <div class="form-group col-lg-6">
                                <label class="col-sm-3 control-label" for="maxUserAm"><spring:message
                                        code="DeliveryPlanCrm.am"/></label>
                                <div class="col-sm-9" style="padding-top:7px;">
                                    <input type="text" class="form-control" placeholder="" id="maxUserAm" name="maxUserAm"
                                           value="${deliveryPlanCrmEntity.maxUserAm}">
                                </div>
                            </div>
                            <%--时间段_下午接待人次配额--%>
                            <div class="form-group col-lg-6">
                                <label class="col-sm-3 control-label" for="maxUserPm"><spring:message
                                        code="DeliveryPlanCrm.pm"/></label>
                                <div class="col-sm-9" style="padding-top:7px;">
                                    <input type="text" class="form-control" placeholder="" id="maxUserPm" name="maxUserPm"
                                           value="${deliveryPlanCrmEntity.maxUserPm}">
                                </div>
                            </div>
                        </div>
                        <div id="timeRangeMaxUser" <c:if test="${deliveryPlanCrmEntity.reservationType eq 'amAndPm'}">style="display:none;"</c:if>>
                            <%-- 日期 --%>
                            <div class="form-group col-lg-12">
                                <div class="form-group col-lg-6">
                                    <label class="col-sm-3 control-label" for="reservationDate">请选择日期</label>
                                    <div class="col-sm-4">
                                        <div class="input-group date form_datetime col-sm-12" data-date=""
                                             data-date-format="yyyy-mm-dd"
                                             data-link-field="dtp_input1">
                                            <input type="text" class="form-control " placeholder="" id="reservationDate" name="reservationDate" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="timeRange">
                            </div>
                            <div class="form-group col-lg-2">
                                <button type="button" class=" btn btn-primary" onclick="addTimeRangeMaxUser()">新增</button>
                                <button type="button" class=" btn btn-primary" onclick="saveTimeRange()">保存</button>
                            </div>
                        </div>
                        <%--
                        <div id="timeRangeMaxUser" <c:if test="${deliveryPlanCrmEntity.reservationType eq 'amAndPm'}">style="display:none;"</c:if>>
                            <c:forEach items="${reservationTimeRangeList}" var="reservationTimeRange">
                                <div class="form-group col-lg-12">
                                    <div class="form-group col-lg-6">
                                        <label class="col-sm-3 control-label">请选择时间段</label>
                                        <div class="col-sm-4">
                                            <div class="input-group date form_time col-sm-12">
                                                <input type="text" class="form-control" placeholder=""
                                                       value="${reservationTimeRange.startTime}" name="startDateList" readonly>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="input-group date form_time col-sm-12">
                                                <input type="text" class="form-control" placeholder=""
                                                       value="${reservationTimeRange.endTime}" name="endDateList" readonly>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="cform-group col-lg-6">
                                        <label class="col-sm-3 control-label" style="padding-right: 12px">交付人数</label>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control maxUser" name="maxUserList" value="${reservationTimeRange.maxUser}"/>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="form-group col-lg-2">
                                <button type="button" class=" btn btn-primary" onclick="addTimeRangeMaxUser()">新增</button>
                            </div>
                        </div>
                        --%>
                        <input type="hidden" id="batchStatus" name="batchStatus" value="">

                        <div class="text-center form-group  col-lg-12" style="padding:0;">
                            <button type="button" class="btn btn-primary" onclick="validate('1')"><spring:message
                                    code="activityManage.activityPublish"/></button>
                            <button type="button" class="btn btn-primary" onclick="validate('0')"><spring:message
                                    code="activityManage.activityUnPublish"/></button>
                            <a href="../deliveryPlan/DeliveryPlan.html?menuId=005400010000" class="btn btn-primary" for=""><spring:message
                                    code="common_cancel"/></a>
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
    $(function(){
        $("#005400010000").addClass("active");
        $("#005400010000").parent().parent().addClass("in");
        $("#005400010000").parent().parent().parent().parent().addClass("active");
    })
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

    var inputs = $("#homePageimgpath").get(0);
    var result = document.getElementById("demo_homePageimgpath");
    if (typeof FileReader === 'undefined') {
        result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
        inputs.setAttribute('disabled', 'disabled');
    } else {
        inputs.addEventListener('change', readFile, false);
    }

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

    var input = $("#activityimgpath").get(0);
    var results = document.getElementById("demo_activityimgpath");
    if (typeof FileReader === 'undefined') {
        results.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
        input.setAttribute('disabled', 'disabled');
    } else {
        input.addEventListener('change', readFiles, false);
    }

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
    $('.form_time').datetimepicker({
        language: 'zh-CN',
        format: 'hh:ii',
        autoclose: true,
        startView: 0,
        maxView:'minute'
    });
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        linkField: "mirror_field",
        linkFormat: "yyyy-mm-dd",
        minView:2,
        maxView:3,
        startDate:'${fn:substring(deliveryPlanCrmEntity.planStart, 0, 10)}',
        endDate:'${fn:substring(deliveryPlanCrmEntity.planEnd, 0, 10)}'
    }).on('changeDate',function(){
        $("#timeRange").empty();
        //请求时间段数据
        $.ajax({
            url:"../deliveryPlan/getReservationTimeRangeList",
            type:"POST",
            async:"false",
            data:{
                "id":$("#id").val(),
                "reservationDate":$("#reservationDate").val()
            },
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(data){
                if (data.error == 0){
                    var reservationTimeRangeList = data.reservationTimeRangeList;
                    for (var i=0,length=reservationTimeRangeList.length;i<length;i++){
                        var html = '<div class="form-group col-lg-12"><div class="form-group col-lg-6"><label class="col-sm-3 control-label">请选择时间段</label>';
                        html += '<div class="col-sm-4"><div class="input-group date form_time col-sm-12">';
                        html += '<input type="text" class="form-control" placeholder="" value="'+reservationTimeRangeList[i].startTime+'" name="startDateList" readonly>';
                        html += '<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>';
                        html += '</div></div><div class="col-sm-4"><div class="input-group date form_time col-sm-12">';
                        html += '<input type="text" class="form-control" placeholder="" value="'+reservationTimeRangeList[i].endTime+'" name="endDateList" readonly>';
                        html += '<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>';
                        html += '</div></div></div><div class="cform-group col-lg-6"><label class="col-sm-3 control-label" style="padding-right: 12px">交付人数</label>';
                        html += '<div class="col-sm-4"><input type="text" class="form-control maxUser" name="maxUserList" value="'+reservationTimeRangeList[i].maxUser+'"/></div></div></div>';
                        $("#timeRange").append(html);
                    }
                    $('.form_time').datetimepicker({
                        language: 'zh-CN',
                        format: 'hh:ii',
                        autoclose: true,
                        startView: 0,
                        maxView:'minute'
                    });
                }else if (data.error == -1){
                    alert("时间段配置数据请求失败！");
                }
            }
        });
    });
//    0 or 'hour' for the hour view
//    1 or 'day' for the day view
//    2 or 'month' for month view (the default)
//    3 or 'year' for the 12-month overview
//    4 or 'decade' for the 10-year overview. Useful for date-of-birth datetimepickers.
    //时间段保存
    function saveTimeRange(){
        var startDateList = [];
        var endDateList = [];
        var maxUserList = [];
        if($("input[name='reservationType']:checked").val() == 'timeRange'){
            var flag = 0;
            $("input[name='startDateList']").each(function(i){
                if ($(this).val() == ''){
                    flag = 1;
                    return false;
                }else{
                    startDateList[i] = $(this).val();
                }
            });
            $("input[name='endDateList']").each(function(i){
                if ($(this).val() == ''){
                    flag = 1;
                    return false;
                }else{
                    endDateList[i] = $(this).val();
                }
            });
            $("input[name='maxUserList']").each(function(i){
                if ($(this).val() == ''){
                    flag = 1;
                    return false;
                }else{
                    maxUserList[i] = $(this).val();
                }
            });
            if (flag == 1){
                alert("请完成时间段配置！");
                return false;
            }
        }
        $.ajax({
            url:"../deliveryPlan/saveReservationTimeRange",
            type:"POST",
            async:"false",
            traditional:true,
            data:{
                "id":$("#id").val(),
                "reservationDate":$("#reservationDate").val(),
                "startDateList":startDateList,
                "endDateList":endDateList,
                "maxUserList":maxUserList
            },
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(data){
                if (data.error == 0){
                    alert("时间段保存成功！");
                }else if (data.error == -1){
                    alert("时间段保存失败！");
                }else{
                    alert("时间段保存异常！");
                }
            }
        });
    }
</script>
</body>
</html>
