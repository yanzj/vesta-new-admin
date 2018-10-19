<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
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
    <%--<link rel="stylesheet" href="../static/css/jquery-ui.min.css" />
    <script src="../static/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>--%>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->

</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="002500010000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <div class="form-marginL">
                    <form class="form-horizontal" action="../DailyPatrolInspection/dailyPatrolInspection.html" method="get">
                        <%--集团--%>
                        <div class="form-group  col-lg-4">
                            <label for="groupId" class="col-sm-4 control-label"><spring:message
                                    code="project.groupName"/>:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="groupId" name="groupId">
                                    <c:forEach items="${goups}" var="group">
                                        <option value="${group.key}"
                                                <c:if test="${group.key eq problem.groupId}">selected</c:if>>${group.value}</option>
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
                                                <c:if test="${region.key eq problem.regionId}">selected</c:if>>${region.value}</option>
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
                                                <c:if test="${city.key eq problem.cityId}">selected</c:if>>${city.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    <%--项目--%>
                    <div class="form-group  col-lg-4">
                        <label for="projectId" class="col-sm-4 control-label"><spring:message
                                code="project.projectName"/>:</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="projectId" name="projectId">
                                <c:forEach items="${projects}" var="project">
                                    <option value="${project.key}"
                                            <c:if test="${project.key eq problem.projectId}">selected</c:if>>${project.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%--楼栋--%>
                    <div class="form-group  col-lg-4">
                        <label for="buildingId" class="col-sm-4 control-label"><spring:message
                                code="project.buildingName"/>:</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="buildingId" name="buildingId">
                                <c:forEach items="${builds}" var="build">
                                    <option value="${build.key}"
                                            <c:if test="${build.key eq problem.buildingId}">selected</c:if>>${build.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--问题状态 --%>
                    <div class="form-group  col-lg-4">
                        <label for="state" class="col-sm-4 control-label"><spring:message
                                code="problem.status"/>:</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="state" name="state">
                                <option value="" selected>请选择</option>
                                <option value="整改中" <c:if test="${problem.state eq '整改中'}">selected</c:if>>整改中</option>
                                <option value="完成" <c:if test="${problem.state eq '完成'}">selected</c:if>>已完成</option>
                                <option value="草稿" <c:if test="${problem.state eq '草稿'}">selected</c:if>>草稿</option>
                                <option value="非正常关闭" <c:if test="${problem.state eq '非正常关闭'}">selected</c:if>>非正常关闭</option>
                            </select>
                        </div>
                    </div>
                    <%--严重等级 --%>
                    <div class="form-group  col-lg-4">
                        <label for="state" class="col-sm-4 control-label"><spring:message code="problem.severityLevel"/>:</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="severityLevel" name="severityLevel">
                                <option value="" selected>请选择</option>
                                <option value="紧急" <c:if test="${problem.severityLevel eq '紧急'}">selected</c:if>>紧急
                                </option>
                                <option value="严重" <c:if test="${problem.severityLevel eq '严重'}">selected</c:if>>严重
                                </option>
                                <option value="一般" <c:if test="${problem.severityLevel eq '一般'}">selected</c:if>>一般
                                </option>
                            </select>
                        </div>
                    </div>
                    <%-- 一级分类 --%>
                    <div class="form-group  col-lg-4">
                        <label for="classifyOne" class="col-sm-4 control-label"><spring:message
                                code="problem.fristLevel"/>:</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="classifyOne" name="classifyOne">
                                <c:forEach items="${firstLevels}" var="item">
                                    <option value="${item.key }"
                                            <c:if test="${item.key eq problem.classifyOne}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 二级分类 --%>
                    <div class="form-group  col-lg-4">
                        <label for="classifyTwo" class="col-sm-4 control-label"><spring:message
                                code="problem.secondLevel"/>:</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="classifyTwo" name="classifyTwo">
                                <c:forEach items="${secondLevels}" var="item">
                                    <option value="${item.key }"
                                            <c:if test="${item.key eq problem.classifyTwo}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 开始时间--%>
                    <div class="form-group  col-lg-4">
                        <label for="startDate" class="col-sm-4 control-label"><spring:message
                                code="workOrders.startDate"/>:</label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="请输入开始时间" path="startDate"
                                   id="startDate"
                                   name="startDate" value="${problem.startDate}" readonly="true"/>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <%-- 结束时间--%>
                    <div class="form-group  col-lg-4">
                        <label for="endDate" class="col-sm-4 control-label"><spring:message
                                code="workOrders.endDate"/>:</label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="请输入结束时间" path="endDate" id="endDate"
                                   name="endDate" value="${problem.endDate}" readonly="true"/>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <%-- 责任单位 --%>
                    <div class="form-group  col-lg-4">
                        <label for="supplier" class="col-sm-4 control-label"><spring:message
                                code="inspection.supplier"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="supplier" name="supplier"
                                   value="${problem.supplier}">
                        </div>
                    </div>
                    <%-- 整改人 --%>
                    <div class="form-group  col-lg-4">
                        <label for="assignName" class="col-sm-4 control-label"><spring:message
                                code="problem.assignName"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="assignName" name="assignName"
                                   value="${problem.assignName}">
                        </div>
                    </div>
                    <%-- 第三方监理 --%>
                    <div class="form-group  col-lg-4">
                        <label for="supervisorName" class="col-sm-4 control-label"><spring:message
                                code="problem.supervisorName"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="supervisorName"
                                   name="supervisorName" value="${problem.supervisorName}">
                        </div>
                    </div>
                    <%-- 甲方负责人 --%>
                    <div class="form-group  col-lg-4">
                        <label for="supervisorName" class="col-sm-4 control-label"><spring:message
                                code="problem.firstPartyName"/>:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="firstPartyName"
                                   name="firstPartyName" value="${problem.firstPartyName}">
                        </div>
                    </div>
                    <%-- 创建人 --%>
                    <div class="form-group  col-lg-4">
                        <label for="createName" class="col-sm-4 control-label">创建人:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="createName" name="createName" value="${problem.createName}">
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div style="text-align: center; margin-top: 8px;">
                        <c:if test="${function.esh40020001 eq 'Y'}">
                            <button type="button" class="btn btn-primary" onclick="search()" for="propertySearch"><spring:message code="propertyCompany.propertySearch"/></button>
                        </c:if>
                        <c:if test="${function.esh40020002 eq 'Y'}">
                            <a href="javascript:void(0)" onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>
                        </c:if>
                        <c:if test="${function.esh40020003 eq 'Y'}">
                            <c:choose>
                                <c:when test="${problem.projectId == null || problem.projectId == ''}">
                                    <a href="javascript:void(0)" class="btn btn-primary" style="color:#fff" disabled="true">导出Word</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:void(0)" onclick="exportWord()" class="btn btn-primary"
                                       style="color:#fff">导出Word</a>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                </form>
                </div>
            </div>
        </div>
        <div class="table-responsive bs-example widget-shadow" style="border-top: 1px dashed #ccc;
    padding-top: 16px;">

            <table width="100%" class="tableStyle" style="table-layout: fixed;">
                <thead>
                <tr>
                    <th width="8%"><input name="checkall1" type="checkbox" value="0" onClick="checkAllBox(this);"/>序号</th>
                    <%--<th width="4%">超期</th>--%>
                    <th width="15%">项目名称</th>
                    <th width="12%">位置</th>
                    <th width="8%">严重等级</th>
                    <th width="15%">三级分类</th>
                    <th width="9%">创建人</th>
                    <th width="12%">责任单位</th>
                    <th width="9%">整改人</th>
                    <th width="11%">第三方监理</th>
                    <th width="13%">登记时间</th>
                    <th width="9%">状态</th>
                    <th width="9%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${problems}" var="problem" varStatus="row">
                    <tr>
                        <td height="40px" style="text-align: center">
                            <input type="checkbox" name="inspectionId"
                                   value="${problem.inspectionId}"><b>${(webPage.pageIndex-1)*20+row.index+1}</b>
                        </td>
                            <%--<td height="40px">${problem.overdue}</td>--%>
                        <td height="40px">${problem.projectName}</td>
                        <td height="40px">${problem.address}</td>
                        <td height="40px">${problem.severityLevel}</td>
                        <td height="40px">${problem.classifyThree}</td>
                        <td height="40px">${problem.createName}</td>
                        <td height="40px">${problem.supplier}</td>
                        <td height="40px">${problem.assignName}</td>
                        <td height="40px">${problem.supervisorName}</td>
                        <td height="40px">${problem.createOn}</td>
                        <td height="40px">${problem.state}</td>

                        <td height="40px">
                            <c:if test="${function.esh40020004 eq 'Y'}">
                                <c:if test="${problem.state eq '草稿'}">
                                    <a href="../DailyPatrolInspection/getInspectionDraftById?inspectionId=${problem.inspectionId}&projectId=${problem.projectId}"><spring:message
                                            code="problem.detail"/></a>
                                </c:if>
                                <c:if test="${problem.state ne '草稿'}">
                                    <a href="../DailyPatrolInspection/getInspectionByinspectionId?inspectionId=${problem.inspectionId}&projectId=${problem.projectId}"><spring:message
                                            code="problem.detail"/></a>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <m:pager pageIndex="${webPage.pageIndex}"
                     pageSize="${webPage.pageSize}"
                     recordCount="${webPage.recordCount}"
                     submitUrl="${pageContext.request.contextPath }/DailyPatrolInspection/dailyPatrolInspection.html?pageIndex={0}&groupId=${problem.groupId}&regionId=${problem.regionId}&cityId=${problem.cityId}&projectId=${problem.projectId}&buildingId=${problem.buildingId}&state=${problem.state}&classifyOne=${problem.classifyOne}&classifyTwo=${problem.classifyTwo}&supplier=${problem.supplier}&firstPartyName=${problem.firstPartyName}&severityLevel=${problem.severityLevel}&supervisorName=${problem.supervisorName}&assignName=${problem.assignName}&startDate=${problem.startDate}&endDate=${problem.endDate}&createName=${problem.createName}"/>
        </div>


    </div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
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
        var size = ${fn:length(problems)};
        if (size > 0) {
            var href = "../DailyPatrolInspection/exportExcel?groupId=${problem.groupId}&regionId=${problem.regionId}&cityId=${problem.cityId}&projectId=${problem.projectId}&buildingId=${problem.buildingId}&state=${problem.state}&classifyOne=${problem.classifyOne}&classifyTwo=${problem.classifyTwo}&supplier=${problem.supplier}&firstPartyName=${problem.firstPartyName}&severityLevel=${problem.severityLevel}&supervisorName=${problem.supervisorName}&assignName=${problem.assignName}&startDate=${problem.startDate}&endDate=${problem.endDate}&createName=${problem.createName}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }

    }

    $("#projectId").change(function () {
        var projectId = $("#projectId").val();
        $.ajax({
            url: "../DailyPatrolInspection/getBuilds",
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


    $("#classifyOne").change(function () {
        var classifyOne = $("#classifyOne").val();
        $.ajax({
            url: "../DailyPatrolInspection/getSecondLevel",
            type: "get",
            async: "false",
            data: {"classifyOne": classifyOne},
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
                        document.getElementById("classifyTwo").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#classifyTwo").append(option);
                    }
                }
            }
        });
    });
    function search() {
        document.forms[0].submit();
    }

    function checkAllBox(obj){
        var answer= document.getElementsByName("inspectionId");
        if(obj.checked==true){
            for(var i=0;i<answer.length;i++){
                answer[i].checked = true;
            }
        }else{
            for(var i=0;i<answer.length;i++){
                answer[i].checked = false;
            }
        }
    }
    function exportWord() {
        var inspectionId = document.getElementsByName("inspectionId");
        var checkbox = false;
        var inspectionIds = '';
        for (var i = 0; i < inspectionId.length; i++) {
            if (inspectionId[i].checked) {
                checkbox = true;
                inspectionIds += "'" + inspectionId[i].value + "',";
            }
        }
        if (checkbox) {
            if (confirm("您确认要导出此数据?")) {
                var href = "../DailyPatrolInspection/exportWord?inspectionId=" + inspectionIds.substring(0, inspectionIds.length - 1);
                window.location.href = href;
            } else {
                return;
            }
        } else {
            alert("请选择要导出的数据");
            return;
        }
    }
</script>

</body>
</html>