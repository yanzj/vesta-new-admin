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
        $(function(){
            console.log("sqq")
            $("#006600010000").addClass("active");
            $("#006600010000").parent().parent().addClass("in");
            $("#006600010000").parent().parent().parent().parent().addClass("active");
        })
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
                 crunMenu="006600010000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <div class="form-marginL">
                    <form class="form-horizontal" action="../inspectionPlan/queryInspectionPlan.html" method="get">
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
                            <label for="areaId" class="col-sm-4 control-label"><spring:message
                                        code="project.areaName"/>:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="areaId" name="areaId">
                                   <c:forEach items="${areas}" var="area">
                                       <option value="${area.key}"
                                            <c:if test="${area.key eq problem.areaId}">selected</c:if>>${area.value}</option>
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

                        <%--问题状态 --%>
                        <div class="form-group  col-lg-4">
                            <label for="state" class="col-sm-4 control-label"><spring:message
                                    code="inspection.state"/>:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="state" name="state">
                                    <option value="" selected>请选择</option>
                                    <option value="release" <c:if test="${problem.state eq 'release'}">selected</c:if>>发布</option>
                                    <option value="unpublished" <c:if test="${problem.state eq 'unpublished'}">selected</c:if>>未发布</option>
                                </select>
                            </div>
                        </div>
                        <%-- 检查部位 --%>
                        <div class="form-group  col-lg-4">
                            <label for="planName" class="col-sm-4 control-label"><spring:message
                                        code="DeliveryPlanCrm.plan"/>:</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="planName" name="planName"
                                     value="${problem.planName}">
                            </div>
                        </div>
                        <%-- 开始时间--%>
                        <div class="form-group  col-lg-4">
                            <label for="planStart" class="col-sm-4 control-label"><spring:message
                                    code="workOrders.startDate"/>:</label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入开始时间" path="planStart"
                                       id="planStart"
                                       name="planStart" value="${problem.planStart}" readonly="true"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <%-- 结束时间--%>
                        <div class="form-group  col-lg-4">
                            <label for="planEnd" class="col-sm-4 control-label"><spring:message
                                    code="workOrders.endDate"/>:</label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入结束时间" path="planEnd" id="planEnd"
                                       name="planEnd" value="${problem.planEnd}" readonly="true"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div style="text-align: center; margin-top: 8px;">
                            <c:if test="${function.sth40020058 eq 'Y'}">
                                <button type="button" class="btn btn-primary" onclick="search()" for="propertySearch">
                                    <spring:message code="propertyCompany.propertySearch"/></button>
                            </c:if>
                            <%--<a href="#" onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>--%>
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
                    <th width="8%">序号</th>
                    <th width="15%">计划名称</th>
                    <th width="8%">创建人</th>
                    <th width="15%">创建时间</th>
                    <th width="15%">创建单位</th>
                    <th width="8%">状态</th>
                    <th width="8%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${problems}" var="problem" varStatus="row">
                    <tr>
                        <td height="40px" style="text-align: center;"><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>

                        <td height="40px">${problem.planName}</td>
                        <td height="40px">${problem.createName}</td>
                        <td height="40px">${problem.createDate}</td>
                        <td height="40px">${problem.createUnitName}</td>
                        <c:if test="${problem.state eq 'release'}">
                            <td height="40px">发布</td>
                        </c:if>
                        <c:if test="${problem.state eq 'unpublished'}">
                            <td height="40px">未发布</td>
                        </c:if>
                        <c:if test="${problem.state eq ''}">
                            <td height="40px">未发布</td>
                        </c:if>
                        <td height="40px">
                            <c:if test="${function.sth40020059 eq 'Y'}">
                                <a href="../inspectionPlan/inspectionPlanDetails.html?planId=${problem.planId}"><spring:message
                                        code="problem.detail"/></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <m:pager pageIndex="${webPage.pageIndex}"
                     pageSize="${webPage.pageSize}"
                     recordCount="${webPage.recordCount}"
                     submitUrl="${pageContext.request.contextPath }/inspectionPlan/queryInspectionPlan.html?pageIndex={0}&planName=${problem.planName}&planStart=${problem.planStart}&planEnd=${problem.planEnd}&projectId=${problem.projectId}&groupId=${problem.groupId}&areaId=${problem.areaId}&state=${problem.state}"/>
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
    function search() {
        document.forms[0].submit();
    }

    $("#groupId").change(function () {
        var groupId = $("#groupId").val();
        $.ajax({
            url: "../inspectionPlan/getAreaList",
            type: "get",
            async: false,
            data: {"groupId": groupId},
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
                        document.getElementById("areaId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#areaId").append(option);
                    }
                }
            }
        });
        getProjectList();
    });


    $("#areaId").change(function () {
        getProjectList();
    });
    function getProjectList() {
        var areaId = $("#areaId").val();
        $.ajax({
            url: "../inspectionPlan/getProjectList",
            type: "get",
            async: false,
            data: {"areaId": areaId},
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
                        document.getElementById("projectId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#projectId").append(option);
                    }
                }
            }
        });
    }
</script>

</body>
</html>