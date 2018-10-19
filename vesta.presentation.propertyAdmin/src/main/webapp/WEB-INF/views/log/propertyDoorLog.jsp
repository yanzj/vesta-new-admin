<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<style type="text/css">
    .form_b {
        height: 17rem;
    }

    .search_button {
        text-align: center;
    }

    .control_btn {
        padding: 0 0 1rem 0;
        background-color: #fbfbfb;
    }

    .control_btn button, .control_btn a {
        margin-right: 1rem;
    }

    .blank_sty .blank_input {
        border: none;
        box-shadow: none;
    }

    .form-group.last_time {
        /*margin-left: 1.5rem;*/
    }
</style>
<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005600030000" username="${propertystaff.staffName}"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form class="form-horizontal" action="../property/getDoorLogList">
                    <input type="hidden" id="menuId" name="menuId" value="005600030000"/>

                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>

                        <div class="col-sm-8">
                            <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item">
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq propertyDoorLogDTO.scopeId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- 项目 -->
                    <div class="form-group  col-xs-4">
                        <label for="projectCode" class="col-sm-4 control-label">项目</label>

                        <div class="col-sm-8">
                            <select id="projectCode" name="projectCode" class="form-control">
                                <c:forEach items="${projectList}" var="item">
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq propertyDoorLogDTO.projectCode}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%-- 设备描述 --%>
                    <div class="form-group  col-xs-4">
                        <label for="deviceDesc" class="col-sm-4 control-label">设备描述</label>

                        <div class="col-sm-8">
                            <input type="text" id="deviceDesc" name="deviceDesc"
                                   value="${propertyDoorLogDTO.deviceDesc}"
                                   class="form-control">
                        </div>
                    </div>

                    <%-- 用户名称 --%>
                    <div class="form-group  col-xs-4">
                        <label for="userName" class="col-sm-4 control-label">用户</label>

                        <div class="col-sm-8">
                            <input type="text" id="userName" name="userName" value="${propertyDoorLogDTO.userName}"
                                   class="form-control">
                        </div>
                    </div>

                    <%-- 用户电话 --%>
                    <div class="form-group  col-xs-4">
                        <label for="userPhone" class="col-sm-4 control-label">手机号码</label>

                        <div class="col-sm-8">
                            <input type="text" id="userPhone" name="userPhone" value="${propertyDoorLogDTO.userPhone}"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group  col-xs-4 blank_sty">
                        <label for="userPhone" class="col-sm-4 control-label">&nbsp;&nbsp;&nbsp;&nbsp; </label>

                        <div class="col-sm-8">
                            <input type="text" id="" name="" value="" class="form-control blank_input">
                        </div>
                    </div>
                    <%--<div class="form-group  col-xs-4 blank_sty">--%>
                    <%--<div class="col-sm-8"></div>--%>
                    <%--</div>--%>
                    <!-- 日期设定 -->
                    <div class="form-group  col-xs-4">
                        <label for="staDate" class="col-sm-4 control-label">日期设定</label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="staDate"
                                   value="${propertyDoorLogDTO.startDate}" name="startDate" readonly>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span
                                    class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <%--<div class="form-group  col-lg-1">
                        <label for="endDate" class="col-sm-3 control-label">至</label>
                    </div>--%>
                    <div class="form-group  col-xs-4 last_time">
                        <label for="endDate" class="col-sm-4 control-label">至</label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="endDate"
                                   value="${propertyDoorLogDTO.endDate}" name="endDate" readonly>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span
                                    class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <!-- 日期设定 -->
                    <%--<div class="form-group  col-lg-4">
                        <label for="endDate" class="col-sm-3 control-label">至&nbsp;</label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="endDate"
                                   value="${propertyDoorLogDTO.endDate}" name="endDate" readonly>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span
                                    class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>--%>

                    <%-- 操作 --%>
                    <div class="form-group  col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="common_search"/></button>
                        <input type="hidden" id="size" value="${isExcel}"/>
                        <a href="javascript:void(0);" onclick="isExcel()" type="button" class="btn btn-primary"
                           for="propertyAdd">导出Excel</a>
                    </div>
                    <div class="clearfix"></div>

                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <div class="">

        </div>
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td>序号</td>
                <th>项目名称</th>
                <th>用户</th>
                <th>手机号码</th>
                <th>MacAddress</th>
                <th>设备描述</th>
                <th>开门时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="propertyDoorLog" varStatus="row">
                <tr>
                    <td><b>${row.index+1}</b></td>
                    <td>${propertyDoorLog.projectName}</td>
                    <td>${propertyDoorLog.userName}</td>
                    <td>${propertyDoorLog.userPhone}</td>
                    <td>${propertyDoorLog.macAddress}</td>
                    <td>${propertyDoorLog.deviceDesc}</td>
                    <td>${propertyDoorLog.openDateTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/property/getDoorLogList?menuId=005600030000&pageIndex={0}&scopeId=${propertyDoorLogDTO.scopeId}&projectCode=${propertyDoorLogDTO.projectCode}&deviceDesc=${propertyDoorLogDTO.deviceDesc}&userName=${propertyDoorLogDTO.userName}&userPhone=${propertyDoorLogDTO.userPhone}&startDate=${propertyDoorLogDTO.startDate}&endDate=${propertyDoorLogDTO.endDate}"/>
    </div>


</div>
</div>
</div>
</div>

<%--时间搜索插件--%>
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
    //通过城市获取项目列表
    function queryProjectNameById() {
        $("#projectCode").find("option").remove();
        /* -------------------- */
        var projectId = $("#scopeId").val();
        if (projectId == '-1') {
            $("#projectCode").empty();
            return;
        }
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId + "/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectCode").empty();
            $("#projectCode").append('<option value="">请选择</option>');
            for (var k in arr) {
                if (arr[k][0] != '0') {
                    $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
                }
            }
        });
    }

    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
//            document.getElementById("form").action = "../property/exportDoorLogListExcel";
//            document.getElementById("form").submit();
            var href = "../property/exportDoorLogListExcel?menuId=005600030000&scopeId=${propertyDoorLogDTO.scopeId}&projectCode=${propertyDoorLogDTO.projectCode}"
                    + "&deviceDesc=${propertyDoorLogDTO.deviceDesc}&userName=${propertyDoorLogDTO.userName}&userPhone=${propertyDoorLogDTO.userPhone}"
                    + "&startDate=${propertyDoorLogDTO.startDate}&endDate=${propertyDoorLogDTO.endDate}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据！");
        }
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>