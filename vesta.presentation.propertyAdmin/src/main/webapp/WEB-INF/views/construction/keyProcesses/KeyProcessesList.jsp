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
            console.log("sqq")
            $("#002800010000").addClass("active");
            $("#002800010000").parent().parent().addClass("in");
            $("#002800010000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="002800010000" username="${authPropertystaff.staffName}"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body" style="padding-bottom: 0;">
                <div class="form-marginL">
                    <form class="form-horizontal" name="search" action="../projectKeyProcesses/keyProcesses.html"
                      method="GET">
                        <%--集团--%>
                        <div class="form-group  col-lg-4">
                            <label for="groupId" class="col-sm-4 control-label"><spring:message
                                    code="project.groupName"/>:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="groupId" name="groupId">
                                    <c:forEach items="${goups}" var="group">
                                        <option value="${group.key}"
                                                <c:if test="${group.key eq keyProcesses.groupId}">selected</c:if>>${group.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%--区域--%>
                        <div class="form-group  col-lg-4">
                            <label for="regionId" class="col-sm-4 control-label"><spring:message
                                    code="project.areaName"/>:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="regionId" name="regionId">
                                    <c:forEach items="${regions}" var="region">
                                        <option value="${region.key}"
                                                <c:if test="${region.key eq keyProcesses.regionId}">selected</c:if>>${region.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%--城市--%>
                        <div class="form-group  col-lg-4">
                            <label for="cityId" class="col-sm-4 control-label"><spring:message
                                    code="announcementDTO.cityName"/>:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="cityId" name="cityId">
                                    <c:forEach items="${citys}" var="city">
                                        <option value="${city.key}"
                                                <c:if test="${city.key eq keyProcesses.cityId}">selected</c:if>>${city.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    <%-- 项目名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="projectId" class="col-sm-4 control-label">项目名称:</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="projectId" name="projectId">
                                <c:forEach items="${projects}" var="item">
                                    <option value="${item.key }"
                                            <c:if test="${item.key eq keyProcesses.projectId}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 选择楼栋 --%>
                    <div class="form-group  col-lg-4">
                        <label for="buildingId" class="col-sm-4 control-label">楼栋:</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="buildingId" name="buildingId">
                                <c:forEach items="${typeMaps.buildings}" var="item">
                                    <option value="${item.key }"
                                            <c:if test="${item.key eq keyProcesses.buildingId}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--问题状态 --%>
                    <div class="form-group  col-lg-4">
                        <label for="state" class="col-sm-4 control-label">问题状态:</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="state" name="state">
                                <option value="" selected>请选择</option>
                                <option value="合格" <c:if test="${keyProcesses.state eq '合格'}">selected</c:if>>合格
                                </option>
                                <option value="整改中" <c:if test="${keyProcesses.state eq '整改中'}">selected</c:if>>整改中
                                </option>
                                <option value="AbnormalShutdown" <c:if test="${keyProcesses.state eq 'AbnormalShutdown'}">selected</c:if>>非正常关闭
                                </option>
                            </select>
                        </div>
                    </div>

                    <%-- 一级分类 --%>
                    <div class="form-group  col-lg-4">
                        <label for="firstSort" class="col-sm-4 control-label">一级分类:</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="firstSort"
                                    name="firstSort">
                                <c:forEach items="${typeMaps.firstLevels}" var="item">
                                    <option value="${item.key }"
                                            <c:if test="${item.key eq keyProcesses.firstSort}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 二级分类 --%>
                    <div class="form-group  col-lg-4">
                        <label for="secondSort" class="col-sm-4 control-label">二级分类:</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="secondSort"
                                    name="secondSort">
                                <c:forEach items="${typeMaps.secondLevels}" var="item">
                                    <option value="${item.key }"
                                            <c:if test="${item.key eq keyProcesses.secondSort}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 三级分类 --%>
                    <div class="form-group  col-lg-4">
                        <label for="threeSort" class="col-sm-4 control-label">三级分类:</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="threeSort"
                                    name="threeSort">
                                <c:forEach items="${typeMaps.thirdLevels}" var="item">
                                    <option value="${item.key }"
                                            <c:if test="${item.key eq keyProcesses.threeSort}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 甲方负责人 --%>
                    <div class="form-group  col-lg-4">
                        <label for="partyPrincipalName" class="col-sm-4 control-label">甲方负责人:</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="partyPrincipalName"
                                   name="partyPrincipalName" value="${keyProcesses.partyPrincipalName}">
                        </div>
                    </div>
                    <%-- 第三方监理 --%>
                    <div class="form-group  col-lg-4">
                        <label for="supervisorName" class="col-sm-4 control-label">第三方监理:</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="supervisorName"
                                   name="supervisorName" value="${keyProcesses.supervisorName}">
                        </div>
                    </div>
                    <%-- 责任单位 --%>
                    <div class="form-group  col-lg-4">
                        <label for="supplierName" class="col-sm-4 control-label">责任单位:</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="supplierName"
                                   name="supplierName" value="${keyProcesses.supplierName}">
                        </div>
                    </div>
                    <%-- 整改人 --%>
                    <div class="form-group  col-lg-4">
                        <label for="assignName" class="col-sm-4 control-label">整改人:</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="assignName" name="assignName"
                                   value="${keyProcesses.assignName}">
                        </div>
                    </div>
                    <%--严重等级 --%>
                    <div class="form-group  col-lg-4">
                        <label for="severityRating" class="col-sm-4 control-label">严重等级:</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="severityRating" name="severityRating">
                                <option value="" selected>请选择</option>
                                <option value="紧急"
                                        <c:if test="${keyProcesses.severityRating eq '紧急'}">selected</c:if>>紧急
                                </option>
                                <option value="严重"
                                        <c:if test="${keyProcesses.severityRating eq '严重'}">selected</c:if>>严重
                                </option>
                                <option value="一般"
                                        <c:if test="${keyProcesses.severityRating eq '一般'}">selected</c:if>>一般
                                </option>
                            </select>
                        </div>
                    </div>
                    <%-- 开始时间--%>
                    <div class="form-group  col-lg-4">
                        <label for="startDate" class="col-sm-4 control-label">开始时间:</label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="请输入开始时间" path="startDate"
                                   name="startDate" value="${keyProcesses.startDate}" readonly="true"/>
                            <span class="input-group-addon mytime"><span
                            class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon mytime"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <%-- 结束时间--%>
                    <div class="form-group  col-lg-4">
                        <label for="endDate" class="col-sm-4 control-label">结束时间:</label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="请输入结束时间" path="endDate" name="endDate"
                                   value="${keyProcesses.endDate}" readonly="true"/>
                            <span class="input-group-addon mytime"><span
                            class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon mytime"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <div class="clearfix"></div>
                    <div style="text-align: center; margin-top: 8px;">
                        <c:if test="${function.esh40020017 eq 'Y'}">
                            <button  type="submit" class="btn btn-primary" for="propertySearch">搜索</button>
                        </c:if>
                        <c:if test="${function.esh40020018 eq 'Y'}">
                            <a  href="#" onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>
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
                    <%--<th width="3%">超期</th>--%>
                    <th width="8%">批次名称</th>
                    <th width="8%">项目名称</th>
                    <th width="7%">楼栋名称</th>
                    <th width="7%">检查项</th>
                    <th width="7%">甲方负责人</th>
                    <th width="7%">第三方监理</th>
                    <th width="7%">责任单位</th>
                    <th width="5%">整改人</th>
                    <th width="9%">登记时间</th>
                    <th width="5%">严重等级</th>
                    <th width="5%">状态</th>
                    <%--<th width="7%">非正常关闭</th>--%>
                    <th width="5%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${keyProcessesList}" var="processes" varStatus="status">
                    <tr>
                        <td height="40px" style="text-align: center">
                            <b>${(webPage.pageIndex-1)*20+status.index+1}</b>
                        </td>
                        <td height="40px" class="ellipsis message-title" title="${processes.processName}">
                                ${processes.processName}
                        </td>
                        <td height="40px" class="ellipsis message-title" title="${processes.projectName}">
                                ${processes.projectName}
                        </td>
                        <td height="40px" class="ellipsis message-title" title="${processes.buildingName}">
                                ${processes.buildingName}
                        </td>
                        <td height="40px" class="ellipsis message-title"
                            title="${processes.fourSortName}">${processes.fourSortName}</td>
                        <td height="40px" class="ellipsis message-title"
                            title="${processes.partyPrincipalName}">${processes.partyPrincipalName}</td>
                        <td height="40px" class="ellipsis message-title"
                            title="${processes.supervisorName}">${processes.supervisorName}</td>
                        <td height="40px" class="ellipsis message-title"
                            title="${processes.supplierName}">${processes.supplierName}</td>
                        <td height="40px" class="ellipsis message-title"
                            title="${processes.assignName}">${processes.assignName}</td>
                        <td height="40px" class="ellipsis message-title" title="${processes.createDate}">
                                ${processes.createDate}
                                <%--<fmt:formatDate type="date" value="${processes.completeOn}" dateStyle="default"--%>
                                <%--pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                        </td>
                        <td height="40px" class="ellipsis message-title" title="${processes.severityRating}">
                            <c:if test="${processes.severityRating eq '严重'}">严重</c:if>
                            <c:if test="${processes.severityRating eq '一般'}">一般</c:if>
                            <c:if test="${processes.severityRating eq '紧急'}">紧急</c:if>
                        </td>
                        <td height="40px" class="ellipsis message-title" title="${processes.state}">
                            <c:if test="${processes.state eq '合格'}">合格</c:if>
                            <c:if test="${processes.state eq '整改中'}">整改中</c:if>
                            <c:if test="${processes.state eq '非正常关闭'}">非正常关闭</c:if>
                        </td>

                        <td height="40px">
                            <c:if test="${function.esh40020019 eq 'Y'}">
                            <a href="../projectKeyProcesses/searchKeyProcessesDetail?processesId=${processes.processId}&projectId=${processes.projectId}">详情</a>
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
                 submitUrl="${pageContext.request.contextPath }/projectKeyProcesses/keyProcesses.html?pageIndex={0}&groupId=${keyProcesses.groupId}&regionId=${keyProcesses.regionId}&cityId=${keyProcesses.cityId}&projectId=${keyProcesses.projectId}&buildingId=${keyProcesses.buildingId}&state=${keyProcesses.state}&firstSort=${keyProcesses.firstSort}&secondSort=${keyProcesses.secondSort}&threeSort=${keyProcesses.threeSort}&partyPrincipalName=${keyProcesses.partyPrincipalName}&supplierName=${keyProcesses.supplierName}&startDate=${keyProcesses.startDate}&endDate=${keyProcesses.endDate}&severityRating=${keyProcesses.severityRating}&supervisorName=${keyProcesses.supervisorName}&assignName=${keyProcesses.assignName}"/>
    </div>
</div>
</div>
</div>
</div>

<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
</script>

<script type="text/javascript" src="../../../../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../../../../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">

    function batchCommit() {
        if (window.confirm("确定提交选中的草稿吗？")) {
            document.forms[1].submit();
        }
    }

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
<%@ include file="../../main/foolter.jsp" %>
</div>

<script>
    $("#groupId").change(function () {
        var groupId = $("#groupId").val();
        $.ajax({
            url: "../DailyPatrolInspection/getESAuthAgency",
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
            url: "../DailyPatrolInspection/getESAuthAgency",
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
            url: "../DailyPatrolInspection/getESAuthAgency",
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
        var size = ${fn:length(keyProcessesList)};
        if (size > 0) {
            var href = "../projectKeyProcesses/exportExcels?groupId=${keyProcesses.groupId}&regionId=${keyProcesses.regionId}&cityId=${keyProcesses.cityId}&projectId=${keyProcesses.projectId}&buildingId=${keyProcesses.buildingId}&state=${keyProcesses.state}&firstSort=${keyProcesses.firstSort}&secondSort=${keyProcesses.secondSort}&threeSort=${keyProcesses.threeSort}&partyPrincipalName=${keyProcesses.partyPrincipalName}&supplierName=${keyProcesses.supplierName}&startDate=${keyProcesses.startDate}&endDate=${keyProcesses.endDate}&severityRating=${keyProcesses.severityRating}&supervisorName=${keyProcesses.supervisorName}&assignName=${keyProcesses.assignName}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }

    $("#firstSort").change(function () {
        var oneType = $("#firstSort").val();
        $.ajax({
            url: "../projectKeyProcesses/getSecondLevel",
            type: "get",
            async: "false",
            data: {"classifyOne": oneType},
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
                        document.getElementById("secondSort").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (prop == '') {
                                    option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                } else {
                                    option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                }
                            }
                        }
                        $("#secondSort").append(option);
                    }
                }
            }
        });
    });


    $("#secondSort").change(function () {
        var twoType = $("#secondSort").val();
        $.ajax({
            url: "../projectKeyProcesses/getThirdLevel",
            type: "get",
            async: "false",
            data: {"classifyTwo": twoType},
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
                        document.getElementById("threeSort").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (prop == '') {
                                    option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                } else {
                                    option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                }
                            }
                        }
                        $("#threeSort").append(option);
                    }
                }
            }
        });
    });

    $("#projectId").change(function () {
        var projectId = $("#projectId").val();
        $.ajax({
            url: "../BaseData/getBuildingListByProject",
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

</script>
</body>
</html>
