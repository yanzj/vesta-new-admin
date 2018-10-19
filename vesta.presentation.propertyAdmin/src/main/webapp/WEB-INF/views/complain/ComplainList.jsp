<%--
  Created by IntelliJ IDEA.
  User: JIAZEFENG
  Date: 2016/10/17
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    List<Viewmodel> menulist = (List<Viewmodel>) session.getAttribute("menulist");
    List<Viewmodel> secanViewlist = (List<Viewmodel>) session.getAttribute("secanViewlist");

%>
<html>
<head>
    <title><spring:message code="sys.tital"/></title>
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>

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
    <!--animate-->
    <link href="../../../../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../../../../static/js/wow.min.js"></script>
    <script>
        $(function () {
            $("#006800010000").addClass("active");
            $("#006800010000").parent().parent().addClass("in");
            $("#006800010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../../../../static/js/metisMenu.min.js"></script>
    <script src="../../../../static/js/custom.js"></script>
    <link href="../../../../static/css/custom.css" rel="stylesheet">
    <script type="text/javascript" src="../../../../static/plus/js/jquery.validate.js"></script>

    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);
        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <style>
        label.error {
            /*position: absolute;*/
            /*margin-top: -74px;*/
            /*display: block;*/
            margin-left: 1%;
            color: red;
        }
    </style>
    <style>
        .tableStyle tbody td {
            padding-left: 0px;
            text-align: center;
        }

        td {
            white-space: nowrap;
            padding-right: 15px;

            overflow: hidden;
            text-overflow: ellipsis;
            /* display:-webkit-box;*/
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 2;
            /*white-space:nowrap;overflow:hidden;text-overflow: ellipsis;*/
        }

        /*td:hover { /!* 鼠标滑过 *!/*/
        /*overflow:visible;*/
        /*width:auto;*/
        /*}*/
        .mytime {
            padding-left: 6px;
            padding-right: 6px;
        }

    </style>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006800010000" username="${authPropertystaff.staffName}"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body" style="padding-bottom: 0;">
                <div class="form-marginL">
                    <form class="form-horizontal" name="search" action="../complain/complain.html"
                          method="GET">
                        <%--集团--%>
                        <div class="form-group  col-lg-4">
                            <label for="groupId" class="col-sm-4 control-label">集团：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="groupId" name="groupId">
                                    <c:forEach items="${groups}" var="group">
                                        <option value="${group.key}" <c:if test="${group.key eq complain.groupId}">selected</c:if>>${group.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%--区域--%>
                        <div class="form-group  col-lg-4">
                            <label for="regionId" class="col-sm-4 control-label">区域：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="regionId" name="regionId">
                                    <c:forEach items="${regions}" var="region">
                                        <option value="${region.key}" <c:if test="${region.key eq complain.regionId}">selected</c:if>>${region.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%-- 城市下拉框 --%>
                        <div class="form-group  col-lg-4">
                            <label for="cityId" class="col-sm-4 control-label">城市：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="cityId" name="cityId">
                                    <c:forEach items="${citys}" var="city">
                                        <option value="${city.key}" <c:if test="${city.key eq complain.cityId}">selected</c:if>>${city.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <%-- 项目名称 --%>
                        <div class="form-group  col-lg-4">
                            <label for="projectId" class="col-sm-4 control-label"><spring:message code="problem.project"/>：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="projectId" name="projectId">
                                    <c:forEach items="${projects}" var="project">
                                        <option value="${project.key}" <c:if test="${project.key eq complain.projectId}">selected</c:if>>${project.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%-- 地块名称 --%>
                        <div class="form-group  col-lg-4">
                            <label for="area" class="col-sm-4 control-label"><spring:message
                                    code="problem.area"/>：</label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="area" name="area">
                                    <c:forEach items="${typeMaps.areas}" var="item">
                                        <option value="${item.key }"
                                                <c:if test="${item.key eq complain.area}">selected</c:if>>${item.value }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%-- 楼栋 --%>
                        <div class="form-group  col-lg-4">
                            <label for="buildingId" class="col-sm-4 control-label"><spring:message code="problem.building"/>：</label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="buildingId" name="buildingId">
                                    <c:forEach items="${typeMaps.buildings}" var="item">
                                        <option value="${item.key }"
                                                <c:if test="${item.key eq complain.buildingId}">selected</c:if>>${item.value }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <%--单元 --%>
                        <div class="form-group  col-lg-4">
                            <label for="unitId" class="col-sm-4 control-label"><spring:message
                                    code="problem.unit"/>：</label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="unitId" name="unitId">
                                    <c:forEach items="${typeMaps.units}" var="item">
                                        <option value="${item.key }"
                                                <c:if test="${item.key eq complain.unitId}">selected</c:if>>${item.value }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <%-- 楼层 --%>
                        <div class="form-group  col-lg-4">
                            <label for="floorId" class="col-sm-4 control-label"><spring:message
                                    code="problem.floor"/>：</label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="floorId" name="floorId">
                                    <c:forEach items="${typeMaps.floors}" var="item">
                                        <option value="${item.key }"
                                                <c:if test="${item.key eq complain.floorId}">selected</c:if>>${item.value }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <%-- 选择房间 --%>
                        <div class="form-group  col-lg-4">
                            <label for="houseCode" class="col-sm-4 control-label">房间号:</label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="houseCode" name="houseCode">
                                    <c:forEach items="${typeMaps.rooms}" var="item">
                                        <option value="${item.key }"
                                                <c:if test="${item.key eq complain.houseCode}">selected</c:if>>${item.value }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%-- 投诉人 --%>
                        <div class="form-group  col-lg-4">
                            <label for="complaintPersonName" class="col-sm-4 control-label">投诉人:</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="complaintPersonName"
                                       name="complaintPersonName" value="${complain.complaintPersonName}">
                            </div>
                        </div>
                        <%-- 投诉人电话 --%>
                        <div class="form-group  col-lg-4">
                            <label for="complaintPhone" class="col-sm-4 control-label">投诉人电话:</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="complaintPhone"
                                       name="complaintPhone" value="${complain.complaintPhone}">
                            </div>
                        </div>
                        <%-- 投诉单号 --%>
                        <div class="form-group  col-lg-4">
                            <label for="complainName" class="col-sm-4 control-label">投诉单号:</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="complainName"
                                       name="complainName" value="${complain.complainName}">
                            </div>
                        </div>
                        <%-- 处理人 --%>
                        <div class="form-group  col-lg-4">
                            <label for="disposalName" class="col-sm-4 control-label">处理人:</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="disposalName"
                                       name="disposalName"
                                       value="${complain.disposalName}">
                            </div>
                        </div>
                        <%-- 创建人 --%>
                        <div class="form-group  col-lg-4">
                            <label for="createByName" class="col-sm-4 control-label">创建人:</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="createByName"
                                       name="createByName"
                                       value="${complain.createByName}">
                            </div>
                        </div>
                        <%-- 开始时间--%>
                        <div class="form-group  col-lg-4">
                            <label for="startDate" class="col-sm-4 control-label">投诉开始时间:</label>

                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入开始时间" path="startDate"
                                       name="startDate" value="${complain.startDate}" readonly="true"/>
                                <span class="input-group-addon mytime">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </span>
                                <span class="input-group-addon mytime"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>

                        <%-- 结束时间--%>
                        <div class="form-group  col-lg-4">
                            <label for="endDate" class="col-sm-4 control-label">结束时间:</label>

                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入结束时间" path="endDate"
                                       name="endDate"
                                       value="${complain.endDate}" readonly="true"/>
                                <span class="input-group-addon mytime"><span
                                        class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon mytime"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>

                        <div class="clearfix"></div>
                        <div style="text-align: center; margin-top: 8px;">
                            <c:if test="${function.qch40010090 eq 'Y'}">
                                <button type="submit" class="btn btn-primary" for="propertySearch">搜索</button>
                            </c:if>
                            <c:if test="${function.qch40010091 eq 'Y'}">
                                <a href="#" onclick="isExcel()" value="" class="btn btn-primary"
                                   style="color:#fff">导出Excel</a>
                            </c:if>
                        </div>
                    </form>
                </div>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow" style="border-top: 1px dashed #ccc;
    padding-top: 16px;">
        <form>
            <%--<button type="button" class="btn btn-primary" for="propertySearch" onclick="batchCommit()">批量提交草稿</button>--%>
            <table width="100%" class="tableStyle" style="table-layout: fixed;">
                <thead>
                <tr>
                    <th width="3%">序号</th>
                    <th width="8%">项目名称</th>
                    <th width="10%">房间信息</th>
                    <th width="10%">投诉描述</th>
                    <th width="5%">单据状态</th>
                    <th width="5%">创建人</th>
                    <th width="5%">投诉人</th>
                    <th width="10%">限时答复时间</th>
                    <th width="10%">投诉时间</th>
                    <th width="5%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${complainList}" var="com" varStatus="status">
                    <tr>
                        <td height="40px" style="text-align: center">
                            <b>${(webPage.pageIndex-1)*20+status.index+1}</b>
                        </td>
                        <td height="40px" class="ellipsis message-title" title="${com.projectName}">
                                ${com.projectName}
                        </td>
                        <td height="40px" class="ellipsis message-title" title="${com.houseName}">
                                ${com.houseName}
                        </td>
                        <td height="40px" class="ellipsis message-title"
                            title="${com.complaintsDescribes}">${com.complaintsDescribes}</td>
                        <td height="40px" class="ellipsis message-title"
                            title="${com.documentsState}">${com.documentsState}</td>
                        <td height="40px" class="ellipsis message-title"
                            title="${com.createByName}">${com.createByName}</td>
                        <td height="40px" class="ellipsis message-title"
                            title="${com.complaintPersonName}">${com.complaintPersonName}</td>
                        <td height="40px" class="ellipsis message-title"
                            title="${com.limitedReplyTime}">${com.limitedReplyTime}</td>
                        <td height="40px" class="ellipsis message-title" title="${com.submitTime}">
                                ${com.submitTime}
                        </td>
                        <td height="40px">
                            <c:if test="${function.qch40010092 eq 'Y'}">
                                <a href="../complain/getComplainInfoById?complainId=${com.complainId}">详情</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>

        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/complain/complain.html?pageIndex={0}&city=${complain.city}&projectNum=${complain.projectNum}&area=${complain.area}&buildingId=${complain.buildingId}&unitId=${complain.unitId}&floorId=${complain.floorId}&houseCode=${complain.houseCode}&complaintPersonName=${complain.complaintPersonName}&complaintPhone=${complain.complaintPhone}&disposalName=${complain.disposalName}&complainName=${complain.complainName}&createByName=${complain.createByName}&startDate=${complain.startDate}&endDate=${complain.endDate}&groupId=${complain.groupId}&regionId=${complain.regionId}&cityId=${complain.cityId}&projectId=${complain.projectId}"/>
    </div>
</div>
</div>
</div>
</div>

<script type="text/javascript" src="../../../../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../../../../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
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
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    $("#groupId").change(function () {
        var groupId = $("#groupId").val();
        $.ajax({
            url: "../problem/getQCAuthAgency",
            type: "get",
            async: "false",
            data: {"type": '100000001',
                "groupId":groupId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("regionId").innerHTML = "";
                        document.getElementById("cityId").innerHTML = "";
                        document.getElementById("projectId").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }

                                }
                            }
                        }
                        $("#regionId").append(option);
                    }
                }
            }
        });
    });

    $("#regionId").change(function () {
        var regionId = $("#regionId").val();
        $.ajax({
            url: "../problem/getQCAuthAgency",
            type: "get",
            async: "false",
            data: {"type": '100000003',
                "regionId":regionId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("cityId").innerHTML = "";
                        document.getElementById("projectId").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }

                                }
                            }
                        }
                        $("#cityId").append(option);
                    }
                }
            }
        });
    });



    $("#cityId").change(function () {
        var cityId = $("#cityId").val();
        $.ajax({
            url: "../problem/getQCAuthAgency",
            type: "get",
            async: "false",
            data: {"type": '100000002',
                "cityId":cityId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("projectId").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }

                                }
                            }
                        }
                        $("#projectId").append(option);
                    }
                }
            }
        });
    });
</script>
<script>

    function isExcel() {
        var size = ${fn:length(complainList)};
        if (size > 0) {
            var href = "../complain/exportExcels?city=${complain.city}&projectNum=${complain.projectNum}&houseCode=${complain.houseCode}&complaintPersonName=${complain.complaintPersonName}&complaintPhone=${complain.complaintPhone}&disposalName=${complain.disposalName}&complainName=${complain.complainName}&createByName=${complain.createByName}&startDate=${complain.startDate}&endDate=${complain.endDate}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }
    /*//获取项目
    $("#city").change(function () {
        var cityNum = $("#city").val();
        $.ajax({
            url: "../complain/getProjectListByCityNum",
            type: "get",
            async: "false",
            data: {"city": cityNum},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("projectNum").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                }
                            }
                        }
                        $("#projectNum").append(option);
                    }
                }
            }
        });
    });*/
    //获取地块

    $("#projectId").change(function () {
        var projectId = $("#projectId").val();
        $.ajax({
            url: "../houseroomtype/getAreaListByProjectId",
            type: "get",
            async: "false",
            data: {"projectId": projectId},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("area").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#area").append(option);
                    }
                }
            }
        });
    });

    $("#area").change(function () {
        var area = $("#area").val();
        $.ajax({
            url: "../houseroomtype/getBuildingListByArea",
            type: "get",
            async: "false",
            data: {"areaId": area},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("buildingId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#buildingId").append(option);
                    }
                }
            }
        });
    });

    $("#buildingId").change(function () {
        var buildingId = $("#buildingId").val();
        $.ajax({
            url: "../houseroomtype/getUnitList",
            type: "get",
            async: "false",
            data: {"buildingId": buildingId},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("unitId").innerHTML = "";
                        for (var prop in data) {
                            if (data[prop] == '无单元') {
                                option = "<option value=''>请选择单元</option>";
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            } else {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#unitId").append(option);
                    }
                }
            }
        });
    });

    $("#unitId").change(function () {
        var unitId = $("#unitId").val();
        $.ajax({
            url: "../houseroomtype/getFloorListByNum",
            type: "get",
            async: "false",
            data: {"unitId": unitId},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("floorId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#floorId").append(option);
                    }
                }
            }
        });
    });

    $("#floorId").change(function () {
        var floor = $("#floorId").val();
        $.ajax({
            url: "../houseroomtype/getRoomListByNum",
            type: "get",
            async: "false",
            data: {"floor": floor},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("houseCode").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#houseCode").append(option);
                    }
                }
            }
        });
    });
</script>
</body>
</html>
