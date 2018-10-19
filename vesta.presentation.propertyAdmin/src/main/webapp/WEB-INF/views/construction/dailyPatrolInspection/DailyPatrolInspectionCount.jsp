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
    <%--tabulous--%>
    <link href="../../../../static/css/tabulous.css" rel="stylesheet">
    <script src="../../../../static/js/tabulous.js"></script>
    <!-- Custom CSS -->
    <link href="../../../../static/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="../../../../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <script>
        $(function () {
            var tabIndex = ${tabIndex};
            $("#002500040000").addClass("active");
            $("#002500040000").parent().parent().addClass("in");
            $("#002500040000").parent().parent().parent().parent().addClass("active");
            $('.tabs').tabulous({
                effect: 'flip'
            });
            if(tabIndex == 1){
                $('#tabsarea').addClass('tabulous_active');
                $('#tabsproject').removeClass('tabulous_active');
                $('#tabsbuild').removeClass('tabulous_active');
            }else if(tabIndex == 2){
                $('#tabsproject').addClass('tabulous_active');
                $('#tabsbuild').removeClass('tabulous_active');
                $('#tabsarea').removeClass('tabulous_active');
                $('#tabs-project').removeClass('hideflip');
                $('#tabs-area').addClass('hideflip');
                $('#tabs-build').addClass('hideflip');
            }else if(tabIndex == 3){
                $('#tabsbuild').addClass('tabulous_active');
                $('#tabsarea').removeClass('tabulous_active');
                $('#tabsproject').removeClass('tabulous_active');
                $('#tabs-build').removeClass('hideflip');
                $('#tabs-project').addClass('hideflip');
                $('#tabs-area').addClass('hideflip');
            }
            $('#tabsarea').click(function () {
                location.href = '../DailyPatrolInspection/dailyPatrolInspectionAreaCount.html';
            });
            $('#tabsproject').click(function () {
                location.href = '../DailyPatrolInspection/dailyPatrolInspectionProejctCount.html';
            });
            $('#tabsbuild').click(function () {
                location.href = '../DailyPatrolInspection/dailyPatrolInspectionCount.html';
            });
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

        .mytime {
            padding-left: 6px;
            padding-right: 6px;
        }

        .tabs {
            width: auto;
            margin: auto;
            margin-bottom: 0;
        }
    </style>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="002500040000" username="${authPropertystaff.staffName}"/>
    <div class="tabs">
        <ul class="tab-button">
            <li><a href="#tabs-area" id="tabsarea" class="cleanClass" title="区域">区域</a></li>
            <li><a href="#tabs-project" id="tabsproject" class="cleanClass" title="项目">项目</a></li>
            <li><a href="#tabs-build" id="tabsbuild" class="cleanClass" title="楼栋">楼栋</a></li>
        </ul>
        <div id="tabs_container">
            <%--区域--%>
            <div id="tabs-area">
                <div class="forms">
                    <div class="widget-shadow" data-example-id="basic-forms">
                        <%--搜索条件开始--%>
                        <div class="form-body">
                            <div class="form-marginL">
                                <form class="form-horizontal" name="search"
                                      action="../DailyPatrolInspection/dailyPatrolInspectionAreaCount.html"
                                      method="GET">
                                    <%-- 集团名称 --%>
                                    <div class="form-group col-lg-4">
                                        <label for="groupId" class="col-sm-4 control-label">集团名称：</label>

                                        <div class="col-sm-8">
                                            <select class="form-control" placeholder="" id="groupId" name="groupId">
                                                <option value="0" selected>中国金茂</option>
                                            </select>
                                        </div>
                                    </div>
                                    <%-- 区域名称 --%>
                                    <div class="form-group  col-lg-4">
                                        <label for="areaId" class="col-sm-4 control-label">区域名称：</label>

                                        <div class="col-sm-8">
                                            <select class="form-control" placeholder="" id="areaId" name="operationId">
                                                <option value="">请选择经营单位</option>
                                                <c:forEach items="${operationList}" var="item">
                                                    <option value="${item.key }"
                                                            <c:if test="${item.key eq DailyPatrolInspection.operationId}">selected</c:if>>${item.value }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group  col-lg-4 pull-right">
                                        <div class="col-sm-4"></div>
                                        <button type="submit" class="btn btn-primary" for="propertySearch">搜索</button>
                                        <a href="javascript:void(0)" onclick="isExcel()" value=""
                                           class="btn btn-primary" style="color:#fff">导出Excel</a>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                        <%--搜索条件结束--%>
                    </div>
                </div>
                <div class="table-responsive bs-example widget-shadow" style="margin-left: -40px">
                    <form>
                        <%--<button type="button" class="btn btn-primary" for="propertySearch" onclick="batchCommit()">批量提交草稿</button>--%>
                        <table width="100%" class="tableStyle" style="table-layout: fixed;">
                            <thead>
                            <tr>
                                <th width="3%">序号</th>
                                <th width="8%">集团名称</th>
                                <th width="8%">区域名称</th>
                                <th width="5%">全部</th>
                                <th width="5%">已完成</th>
                                <th width="5%">整改中</th>
                                <th width="5%">非正常关闭</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${projectDailyPatrolInspectionList}" var="item" varStatus="status">
                                <tr>
                                    <td height="40px" style="text-align: center">
                                        <b>${(webPage.pageIndex-1)*20+status.index+1}</b>
                                    </td>

                                    <td height="40px">
                                            ${item.groupName}
                                    </td>
                                    <td height="40px">
                                            ${item.operationName}
                                    </td>
                                    <td height="40px">${item.total}</td>
                                    <td height="40px">${item.qualifiedToatl}</td>
                                    <td height="40px">${item.unqualifiedToatl}</td>
                                    <td height="40px">${item.closses}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                    <m:pager pageIndex="${webPage.pageIndex}"
                             pageSize="${webPage.pageSize}"
                             recordCount="${webPage.recordCount}"
                             submitUrl="${pageContext.request.contextPath }/DailyPatrolInspection/dailyPatrolInspectionAreaCount.html?pageIndex={0}&operationId=${DailyPatrolInspection.operationId}"/>
                </div>
            </div>
            <%--项目--%>
            <div id="tabs-project">
                <div class="forms">
                    <div class="widget-shadow " data-example-id="basic-forms">
                        <%--搜索条件开始--%>
                        <div class="form-body">
                            <div class="form-marginL">
                                <form class="form-horizontal" name="search"
                                      action="../DailyPatrolInspection/dailyPatrolInspectionProejctCount.html"
                                      method="GET">
                                    <%-- 区域名称 --%>
                                    <div class="form-group  col-lg-4">
                                        <label for="businessUnitId" class="col-sm-4 control-label">区域名称：</label>

                                        <div class="col-sm-8">
                                            <select class="form-control" placeholder="" id="businessUnitId"
                                                    name="operationId">
                                                <option value="">请选择经营单位</option>
                                                <c:forEach items="${operationList}" var="item">
                                                    <option value="${item.key }"
                                                            <c:if test="${item.key eq DailyPatrolInspection.operationId}">selected</c:if>>${item.value }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <%-- 项目名称 --%>
                                    <div class="form-group  col-lg-4">
                                        <label for="itemId" class="col-sm-4 control-label">项目名称：</label>

                                        <div class="col-sm-8">
                                            <select class="form-control" placeholder="" id="itemId" name="projectId">
                                                <c:forEach items="${projectMap}" var="item">
                                                    <option value="${item.key }"
                                                            <c:if test="${item.key eq DailyPatrolInspection.projectId}">selected</c:if>>${item.value }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group  col-lg-4 pull-right">
                                        <div class="col-sm-4"></div>
                                        <button type="submit" class="btn btn-primary" for="propertySearch">搜索</button>
                                        <a href="javascript:void(0)" onclick="isExcel()" value=""
                                           class="btn btn-primary" style="color:#fff">导出Excel</a>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                        <%--搜索条件结束--%>
                    </div>
                </div>
                <div class="table-responsive bs-example widget-shadow" style="margin-left: -40px">
                    <form>
                        <%--<button type="button" class="btn btn-primary" for="propertySearch" onclick="batchCommit()">批量提交草稿</button>--%>
                        <table width="100%" class="tableStyle" style="table-layout: fixed;">
                            <thead>
                            <tr>
                                <th width="3%">序号</th>
                                <th width="8%">区域名称</th>
                                <th width="8%">项目名称</th>
                                <th width="5%">全部</th>
                                <th width="5%">已完成</th>
                                <th width="5%">整改中</th>
                                <th width="5%">非正常关闭</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${projectDailyPatrolInspectionList}" var="item" varStatus="status">
                                <tr>
                                    <td height="40px" style="text-align: center">
                                        <b>${(webPage.pageIndex-1)*20+status.index+1}</b>
                                    </td>

                                    <td height="40px">
                                            ${item.operationName}
                                    </td>
                                    <td height="40px">
                                            ${item.projectName}
                                    </td>
                                    <td height="40px">${item.total}</td>
                                    <td height="40px">${item.qualifiedToatl}</td>
                                    <td height="40px">${item.unqualifiedToatl}</td>
                                    <td height="40px">${item.closses}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                    <m:pager pageIndex="${webPage.pageIndex}"
                             pageSize="${webPage.pageSize}"
                             recordCount="${webPage.recordCount}"
                             submitUrl="${pageContext.request.contextPath }/DailyPatrolInspection/dailyPatrolInspectionProejctCount.html?pageIndex={0}&projectId=${DailyPatrolInspection.projectId}&operationId=${DailyPatrolInspection.operationId}"/>
                </div>
            </div>
            <%--楼栋--%>
            <div id="tabs-build">
                <div class="forms">
                    <div class="widget-shadow " data-example-id="basic-forms">
                        <%--搜索条件开始--%>
                        <div class="form-body">
                            <div class="form-marginL">
                                <form class="form-horizontal" name="search"
                                      action="../DailyPatrolInspection/dailyPatrolInspectionCount.html"
                                      method="GET">
                                    <%-- 项目名称 --%>
                                    <div class="form-group  col-lg-4">
                                        <label for="projectId" class="col-sm-4 control-label">项目名称：</label>

                                        <div class="col-sm-8">
                                            <select class="form-control" placeholder="" id="projectId" name="projectId">
                                                <c:forEach items="${typeMaps.projects}" var="item">
                                                    <option value="${item.key }"
                                                            <c:if test="${item.key eq DailyPatrolInspection.projectId}">selected</c:if>>${item.value }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <%-- 选择楼栋 --%>
                                    <div class="form-group  col-lg-4">
                                        <label for="buildingId" class="col-sm-4 control-label">楼栋名称：</label>

                                        <div class="col-sm-8">
                                            <select class="form-control" placeholder="" id="buildingId"
                                                    name="buildingId">
                                                <c:forEach items="${builds}" var="item">
                                                    <option value="${item.key }"
                                                            <c:if test="${item.key eq DailyPatrolInspection.buildingId}">selected</c:if>>${item.value }</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group  col-lg-4 pull-right">
                                        <div class="col-sm-4"></div>
                                        <button type="submit" class="btn btn-primary" for="propertySearch">搜索</button>
                                        <a href="javascript:void(0)" onclick="isExcel()" value=""
                                           class="btn btn-primary" style="color:#fff">导出Excel</a>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                        <%--搜索条件结束--%>
                    </div>
                </div>
                <div class="table-responsive bs-example widget-shadow" style="margin-left: -40px">
                    <form>
                        <%--<button type="button" class="btn btn-primary" for="propertySearch" onclick="batchCommit()">批量提交草稿</button>--%>
                        <table width="100%" class="tableStyle" style="table-layout: fixed;">
                            <thead>
                            <tr>
                                <th width="3%">序号</th>
                                <th width="8%">项目名称</th>
                                <th width="8%">楼栋名称</th>
                                <th width="5%">全部</th>
                                <th width="5%">已完成</th>
                                <th width="5%">整改中</th>
                                <th width="5%">非正常关闭</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${projectDailyPatrolInspectionList}" var="item" varStatus="status">
                                <tr>
                                    <td height="40px" style="text-align: center">
                                            <%--<c:if test="${problem.caseState eq '草稿'}">--%>
                                            <%--<input name="ids" type="checkbox" value="${problem.caseId}"/>--%>
                                            <%--<b></c:if>--%>
                                        <b>${(webPage.pageIndex-1)*20+status.index+1}</b>
                                    </td>

                                    <td height="40px">
                                            ${item.projectName}
                                    </td>
                                        <%--<td height="40px">--%>
                                        <%--${item.tenderName}--%>
                                        <%--</td>--%>
                                    <td height="40px">
                                            ${item.buildingName}
                                    </td>
                                    <td height="40px">${item.total}</td>
                                    <td height="40px">${item.qualifiedToatl}</td>
                                    <td height="40px">${item.unqualifiedToatl}</td>
                                    <td height="40px">${item.closses}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                    <m:pager pageIndex="${webPage.pageIndex}"
                             pageSize="${webPage.pageSize}"
                             recordCount="${webPage.recordCount}"
                             submitUrl="${pageContext.request.contextPath }/DailyPatrolInspection/dailyPatrolInspectionCount.html?pageIndex={0}&projectId=${DailyPatrolInspection.projectId}&buildingId=${DailyPatrolInspection.buildingId}"/>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>

<%@ include file="../../main/foolter.jsp" %>
</div>
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
<script>

    function isExcel() {
        var size = ${fn:length(projectDailyPatrolInspectionList)};
        if (size > 0) {
            var href = "../DailyPatrolInspection/exportCountExcels?projectId=${DailyPatrolInspection.projectId}&operationId=${DailyPatrolInspection.operationId}&buildingId=${DailyPatrolInspection.buildingId}&tabIndex=${tabIndex}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }
    $("#businessUnitId").change(function () {
        var businessUnitId = $("#businessUnitId").val();
        $.ajax({
            url: "../DailyPatrolInspection/getProjects",
            type: "get",
            async: "false",
            data: {"optId": businessUnitId},
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
                        document.getElementById("itemId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#itemId").append(option);
                    }
                }
            }
        });
    });
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
    //    $("#projectId").change(function () {
    //        var projectId = $("#projectId").val();
    //        $.ajax({
    //            url: "../projectKeyProcesses/getTendersByProjectId",
    //            type: "get",
    //            async: "false",
    //            data: {"projectId": projectId},
    //            dataType: "json",
    //            error: function () {
    //                alert("网络异常，可能服务器忙，请刷新重试");
    //            },
    //            success: function (json) {
    //
    <!-- 获取返回代码 -->
    //                var code = json.code;
    //                if (code != 0) {
    //                    var errorMessage = json.msg;
    //                    alert(errorMessage);
    //                } else {
    //
    <!-- 获取返回数据 -->
    //                    var data = json.data;
    //                    var option = "";
    //                    if (data != null) {
    //                        document.getElementById("tenderId").innerHTML = "";
    //                        for (var prop in data) {
    //                            if (data.hasOwnProperty(prop)) {
    //                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
    //                            }
    //                        }
    //                        $("#tenderId").append(option);
    //                    }
    //                }
    //            }
    //        });
    //    });
    //    $("#tenderId").change(function () {
    //        var tenderId = $("#tenderId").val();
    //        $.ajax({
    //            url: "../projectKeyProcesses/getBuildingByTenderId",
    //            type: "get",
    //            async: "false",
    //            data: {"tenderId": tenderId},
    //            dataType: "json",
    //            error: function () {
    //                alert("网络异常，可能服务器忙，请刷新重试");
    //            },
    //            success: function (json) {
    //
    <!-- 获取返回代码 -->
    //                var code = json.code;
    //                if (code != 0) {
    //                    var errorMessage = json.msg;
    //                    alert(errorMessage);
    //                } else {
    //
    <!-- 获取返回数据 -->
    //                    var data = json.data;
    //                    var option = "";
    //                    if (data != null) {
    //                        document.getElementById("buildingId").innerHTML = "";
    //                        for (var prop in data) {
    //                            if (data.hasOwnProperty(prop)) {
    //                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
    //                            }
    //                        }
    //                        $("#buildingId").append(option);
    //                    }
    //                }
    //            }
    //        });
    //    });

</script>
</body>
</html>
