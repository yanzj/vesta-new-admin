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
            $("#005900010000").addClass("active");
            $("#005900010000").parent().parent().addClass("in");
            $("#005900010000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="005900010000" username="${authPropertystaff.staffName}"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body" style="padding-bottom: 0;">
                <div class="form-marginL">
                    <form class="form-horizontal"  name="search" action="../projectLeaderCheck/leaderCheck.html"
                      method="GET">
                        <%--集团--%>
                        <div class="form-group  col-lg-4">
                            <label for="groupId" class="col-sm-4 control-label"><spring:message
                                    code="project.groupName"/>:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="groupId" name="groupId">
                                    <c:forEach items="${goups}" var="group">
                                        <option value="${group.key}"
                                                <c:if test="${group.key eq leaderCheck.groupId}">selected</c:if>>${group.value}</option>
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
                                                <c:if test="${region.key eq leaderCheck.regionId}">selected</c:if>>${region.value}</option>
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
                                                <c:if test="${city.key eq leaderCheck.cityId}">selected</c:if>>${city.value}</option>
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
                                            <c:if test="${item.key eq leaderCheck.projectId}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 检查人 --%>
                    <div class="form-group  col-lg-4">
                        <label for="assignName" class="col-sm-4 control-label">检查人:</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="createName" name="createName"
                                   value="${leaderCheck.createName}">
                        </div>
                    </div>
                    <%-- 整改人 --%>
                    <div class="form-group  col-lg-4">
                        <label for="assignName" class="col-sm-4 control-label">整改人:</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="assignName" name="assignName"
                                   value="${leaderCheck.assignName}">
                        </div>
                    </div>
                    <%--问题状态 --%>
                    <div class="form-group  col-lg-4">
                        <label for="state" class="col-sm-4 control-label">问题状态:</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="state" name="state">
                                <option value="" selected>请选择</option>
                                <option value="已完成" <c:if test="${leaderCheck.state eq '已完成'}">selected</c:if>>已完成
                                </option>
                                <option value="整改中" <c:if test="${leaderCheck.state eq '整改中'}">selected</c:if>>整改中
                                </option>
                                <%--<option value="AbnormalShutdown"--%>
                                        <%--<c:if test="${leaderCheck.state eq 'AbnormalShutdown'}">selected</c:if>>非正常关闭--%>
                                <%--</option>--%>
                            </select>
                        </div>
                    </div>
                    <%-- 开始时间--%>
                    <div class="form-group  col-lg-4">
                        <label for="startDate" class="col-sm-4 control-label">开始时间:</label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="请输入开始时间" path="startDate"
                                   name="startDate" value="${leaderCheck.startDate}" readonly="true"/>
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
                                   value="${leaderCheck.endDate}" readonly="true"/>
                            <span class="input-group-addon mytime"><span
                                    class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon mytime"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div style="text-align: center; margin-top: 8px;">
                        <c:if test="${function.esh40020059 eq 'Y'}">
                            <button  type="submit" class="btn btn-primary" for="propertySearch">搜索</button>
                        </c:if>
                        <c:if test="${function.esh40020061 eq 'Y'}">
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
                    <th width="8%">项目名称</th>
                    <th width="7%">检查人</th>
                    <%--<th width="7%">描述</th>--%>
                    <th width="7%">开始时间</th>
                    <th width="7%">整改人</th>
                    <th width="5%">状态</th>
                    <th width="5%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${leaderCheckList}" var="leaderCheck" varStatus="status">
                    <tr>
                        <td height="40px" style="text-align: center">
                            <b>${(webPage.pageIndex-1)*20+status.index+1}</b>
                        </td>
                        <td height="40px" class="ellipsis message-title" title="${leaderCheck.projectName}">
                                ${leaderCheck.projectName}
                        </td>
                        <td height="40px" class="ellipsis message-title" title="${leaderCheck.createName}">
                                ${leaderCheck.createName}
                        </td>
                        <td height="40px" class="ellipsis message-title"
                            title="${leaderCheck.createDate}">${leaderCheck.createDate}</td>
                        <td height="40px" class="ellipsis message-title"
                            title="${leaderCheck.assignName}">${leaderCheck.assignName}</td>
                        <td height="40px" class="ellipsis message-title" title="${leaderCheck.state}">
                            <c:if test="${leaderCheck.state eq '已完成'}">已完成</c:if>
                            <c:if test="${leaderCheck.state eq '整改中'}">整改中</c:if>
                            <%--<c:if test="${leaderCheck.state eq '非正常关闭'}">非正常关闭</c:if>--%>
                        </td>
                        <td height="40px">
                            <c:if test="${function.esh40020060 eq 'Y'}">
                                <a href="../projectLeaderCheck/getLeaderCheckDetail?checkId=${leaderCheck.checkId}&projectId=${leaderCheck.projectId}">详情</a>
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
                 submitUrl="${pageContext.request.contextPath }/projectLeaderCheck/leaderCheck.html?pageIndex={0}&groupId=${leaderCheck.groupId}&regionId=${leaderCheck.regionId}&cityId=${leaderCheck.cityId}&projectId=${leaderCheck.projectId}&state=${leaderCheck.state}&startDate=${leaderCheck.startDate}&endDate=${leaderCheck.endDate}&assignName=${leaderCheck.assignName}&createName=${leaderCheck.createName}"/>
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
        var size = ${fn:length(leaderCheckList)};
        if (size > 0) {
            var href = "../projectLeaderCheck/exportExcels?groupId=${leaderCheck.groupId}&regionId=${leaderCheck.regionId}&cityId=${leaderCheck.cityId}&projectId=${leaderCheck.projectId}&state=${leaderCheck.state}&startDate=${leaderCheck.startDate}&endDate=${leaderCheck.endDate}&assignName=${leaderCheck.assignName}&createName=${leaderCheck.createName}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }
</script>
</body>
</html>
