<%--
  Created by IntelliJ IDEA.
  User: lpc
  Date: 2016/6/5
  Time: 10:53
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <!--下拉框可模糊筛选-->
    <link href="../static/css/chosen.css" rel='stylesheet' type='text/css'/>
    <script src="../static/js/chosen.jquery.min.js"></script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <%--<link rel="stylesheet" href="../static/css/jquery-ui.min.css" />
    <script src="../static/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>--%>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
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
        td {
            overflow:hidden;text-overflow:ellipsis;/*display:-webkit-box;*/-webkit-box-orient:vertical;-webkit-line-clamp:2;
            /*white-space:nowrap;overflow:hidden;text-overflow: ellipsis;*/
        }
    </style>
    <style type="text/css">#dialog{display:none;}</style>
    <script type="application/javascript">
        $(function(){
            var answer = $("#planType").val();
            if(answer=='11'){
                $("#001000030000").addClass("active");
                $("#001000030000").parent().parent().addClass("in");
            }
            if(answer=='12'){
                $("#001100020000").addClass("active");
                $("#001100020000").parent().parent().addClass("in");
            }
            if(answer=='13'){
                $("#001200020000").addClass("active");
                $("#001200020000").parent().parent().addClass("in");
            }
        })
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <c:if test="${'11' eq problem.planType}">
        <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001000030000" username="${authPropertystaff.staffName}" />
    </c:if>
    <c:if test="${'12' eq problem.planType}">
        <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001100020000" username="${authPropertystaff.staffName}" />
    </c:if>
    <c:if test="${'13' eq problem.planType}">
        <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001200020000" username="${authPropertystaff.staffName}" />
    </c:if>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">

                <form class="form-horizontal" id="search" name="search" action="../problem/problemPrintNew.html" method="get">
                    <div style="margin-left: -5%;">
                        <%--集团--%>
                        <div class="form-group  col-lg-4">
                            <label for="groupId" class="col-sm-4 control-label">集团：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="groupId" name="groupId">
                                    <c:forEach items="${groups}" var="group">
                                        <option value="${group.key}" <c:if test="${group.key eq problem.groupId}">selected</c:if>>${group.value}</option>
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
                                        <option value="${region.key}" <c:if test="${region.key eq problem.regionId}">selected</c:if>>${region.value}</option>
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
                                        <option value="${city.key}" <c:if test="${city.key eq problem.cityId}">selected</c:if>>${city.value}</option>
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
                                        <option value="${project.key}" <c:if test="${project.key eq problem.projectId}">selected</c:if>>${project.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <%--计划下拉框 --%>
                        <div class="form-group  col-lg-4">
                            <label for="planNum" class="col-sm-4 control-label"><spring:message code="problem.plan"/>：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="planNum" name="planNum">
                                    <c:forEach items="${typeMaps.planList}" var="item">
                                        <option value="${item.key}" <c:if test="${item.key eq problem.planNum}">selected</c:if>>${item.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                            <%--房间--%>
                            <div class="form-group  col-lg-4">
                                <label for="roomNum" class="col-sm-4 control-label"><spring:message code="problem.room"/>：</label>
                                <div class="col-sm-8">
                                    <select class="form-control roomNum_select" placeholder="" id="roomNum" name="roomNum"  data-placeholder="">
                                        <c:forEach items="${typeMaps.roomList}" var="item">
                                            <option value="${item.key }" <c:if test="${item.key eq problem.roomNum}">selected</c:if>>${item.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        <%-- 状态 --%>
                        <div class="form-group  col-lg-4">
                            <label for="state" class="col-sm-4 control-label">验房状态：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="state" name="state">
                                    <option value="" selected>请选择</option>
                                    <c:if test="${'11' eq problem.planType}">
                                        <option value="start" <c:if test="${problem.state eq 'start'}">selected</c:if>>验房开始</option>
                                        <option value="finish" <c:if test="${problem.state eq 'finish'}">selected</c:if>>验房完成</option>
                                    </c:if>
                                    <c:if test="${'12' eq problem.planType}">
                                        <option value="start" <c:if test="${problem.state eq 'start'}">selected</c:if>>业主未到访</option>
                                        <option value="finish" <c:if test="${problem.state eq 'finish'}">selected</c:if>>业主已到访</option>
                                        <%--<option value="inspectionNot" <c:if test="${problem.state eq 'inspectionNot'}">selected</c:if>>验房未通过</option>--%>
                                    </c:if>
                                    <c:if test="${'13' eq problem.planType}">
                                        <option value="preservation" <c:if test="${problem.state eq 'preservation'}">selected</c:if>>数据保存</option>
                                        <option value="acceptanceBy" <c:if test="${problem.state eq 'acceptanceBy'}">selected</c:if>>验收通过</option>
                                        <option value="acceptanceNotThrough" <c:if test="${problem.state eq 'acceptanceNotThrough'}">selected</c:if>>验收未通过</option>
                                        <option value="absence" <c:if test="${problem.state eq 'absence'}">selected</c:if>>业主未到访</option>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <%-- 调用crm是否成功 --%>
                        <div class="form-group  col-lg-4">
                            <label for="successOrFailure" class="col-sm-4 control-label"><spring:message code="problem.successOrFailure"/>：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="successOrFailure" name="successOrFailure">
                                    <option value="0" <c:if test="${problem.successOrFailure eq '0'}">selected</c:if>>成功</option>
                                    <option value="1" <c:if test="${problem.successOrFailure eq '1'}">selected</c:if>>失败</option>
                                </select>
                            </div>
                        </div>
                        <%-- 状态 --%>
                        <div class="form-group  col-lg-4" style="display: none">
                            <label for="caseState" class="col-sm-4 control-label"><spring:message
                                    code="problem.status"/>：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="caseState" name="caseState">
                                    <option value="有效问题" <c:if test="${problem.caseState eq '有效问题'}"></c:if>selected>有效问题</option>
                                    <option value="草稿" <c:if test="${problem.caseState eq '草稿'}">selected</c:if>>草稿</option>
                                    <option value="待接单" <c:if test="${problem.caseState eq '待接单'}">selected</c:if>>待接单</option>
                                    <option value="处理中" <c:if test="${problem.caseState eq '处理中'}">selected</c:if>>处理中</option>
                                    <option value="已完成" <c:if test="${problem.caseState eq '已完成'}">selected</c:if>>已完成</option>
                                    <option value="已废弃" <c:if test="${problem.caseState eq '已废弃'}">selected</c:if>>已废弃</option>
                                    <option value="强制关闭" <c:if test="${problem.caseState eq '强制关闭'}">selected</c:if>>强制关闭</option>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" id="planType" name="planType" value="${problem.planType}" />
                        <div class="form-group  col-lg-4">
                            <label for="startDate" class="col-sm-4 control-label"><spring:message code="workOrders.startDate"/></label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入开始时间" id="startDate" path="startDate" name="startDate" value="${problem.startDate}" readonly="true" />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <%--<div class="form-group  col-lg-4">--%>
                        <%--<input type=radio id=detype name=detype value="0">当天--%>
                        <%--<input type=radio id=detype name=detype value="1">所有--%>
                        <%--</div>--%>
                            <input type="hidden" id="signaType" name="signaType" value="0">
                        <div class="form-group  col-lg-4">
                            <label for="endDate" class="col-sm-4 control-label"><spring:message code="workOrders.endDate"/></label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入结束时间"  id="endDate" path="endDate" name="endDate" value="${problem.endDate}" readonly="true"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <c:if test="${function.qch40010014 eq 'Y' || function.qch40010031 eq 'Y' || function.qch40010048 eq 'Y'}">
                        <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="problem.search"/></button>
                    </c:if>
                    <c:if test="${function.qch40010015 eq 'Y' || function.qch40010032 eq 'Y' || function.qch40010049 eq 'Y'}">
                        <a href="#" onclick="isExcel()"  value="" class="btn btn-primary" style="color:#fff">导出Excel</a>
                    </c:if>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle" style="table-layout: fixed;">
            <thead>
            <tr>
                <%--<th width="4%"><input type="checkbox" name="answer" onclick="checkAllBox(this)">序号</th>--%>
                <th width="4%">序号</th>
                <th width="8%">项目名称</th>
                <th width="20%" >房间信息</th>
                <th width="8%">客户姓名</th>
                <th width="10%">办理状态</th>
                <th width="5%">问题数量统计</th>
                <th width="15%">签字时间</th>
                <th width="5%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${problems}" var="problem" varStatus="row">
                <tr>
                    <td height="40px" style="text-align: center;"><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>
                    <td>${problem.projectName}</td>
                    <td>${problem.roomAddress}</td>
                    <td>${problem.userName}</td>
                    <td>
                        <c:if test="${problem.planType eq '11'}">
                            <c:if test="${problem.state eq 'start'}">验房开始</c:if>
                            <c:if test="${problem.state eq 'finish'}">验房结束</c:if>
                        </c:if>
                        <c:if test="${problem.planType eq '12'}">
                            <c:if test="${problem.state eq 'start'}">业主未到访</c:if>
                            <c:if test="${problem.state eq 'finish'}">业主已到访</c:if>
                            <%--<c:if test="${problem.state eq 'inspectionNot'}">验房未通过</c:if>--%>
                        </c:if>
                        <c:if test="${problem.planType eq '13'}">
                            <c:if test="${problem.state eq 'preservation'}">数据保存</c:if>
                            <c:if test="${problem.state eq 'acceptanceBy'}">验收通过</c:if>
                            <c:if test="${problem.state eq 'acceptanceNotThrough'}">验收未通过</c:if>
                            <c:if test="${problem.state eq 'absence'}">业主未到访</c:if>
                        </c:if>
                    </td>
                    <td>${problem.count}</td>
                    <td><fmt:formatDate  type="String" value="${problem.userTime}" dateStyle="default" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td>
                        <c:if test="${problem.planType eq '11'}">
                            <c:if test="${function.qch40010016 eq 'Y'}">
                                <a href="javascript:void(0);" onclick="isProblemRoomPrint('${problem.caseState}','${problem.projectNum}','${problem.roomNum}','${problem.planType}','${problem.planNum}','${problem.successOrFailure}')" >
                                    <spring:message code="problem.detail" />
                                </a>
                            </c:if>
                        </c:if>
                        <c:if test="${problem.planType eq '12'}">
                            <c:if test="${function.qch40010033 eq 'Y'}">
                                <a href="javascript:void(0);" onclick="isProblemRoomPrint('${problem.caseState}','${problem.projectNum}','${problem.roomNum}','${problem.planType}','${problem.planNum}','${problem.successOrFailure}')" >
                                    <spring:message code="problem.detail" />
                                </a>
                            </c:if>
                        </c:if>
                        <c:if test="${problem.planType eq '13'}">
                            <c:if test="${function.qch40010050 eq 'Y'}">
                                <a href="javascript:void(0);" onclick="isProblemRoomPrint('${problem.caseState}','${problem.projectNum}','${problem.roomNum}','${problem.planType}','${problem.planNum}','${problem.successOrFailure}')" >
                                    <spring:message code="problem.detail" />
                                </a>
                            </c:if>
                        </c:if>
                            <%--房间编码type--%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/problem/problemPrintNew.html?pageIndex={0}&successOrFailure=${problem.successOrFailure}&cityNum=${problem.cityNum}&projectNum=${problem.projectNum}&planNum=${problem.planNum}&roomNum=${problem.roomNum}&state=${problem.state}&userName=${problem.userName}&startDate=${problem.startDate}&endDate=${problem.endDate}&detype=${problem.detype}&planType=${problem.planType}&groupId=${problem.groupId}&regionId=${problem.regionId}&cityId=${problem.cityId}&projectId=${problem.projectId}"/>
    </div>


</div>
</div>
</div>
</div>

<!-- main content end-->
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
    var cityNum = "${problem.cityNum}";
    var projectNum = "${problem.projectNum}";
    var planNum = "${problem.planNum}";
    var roomNum = "${problem.roomNum}";
    var state = "${problem.state}";
    var userName = "${problem.userName}";
    var startDate = "${problem.startDate}";
    var endDate = "${problem.endDate}";
    var detype = "${problem.detype}";
    var successOrFailure = "${problem.successOrFailure}";
    var planType = "${problem.planType}";
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

    function isExcel(){
        var size = ${fn:length(problems)};
        if(size>0){
            var href = "../problem/printExportExcel?cityNum=${problem.cityNum}&projectNum=${problem.projectNum}&planNum=${problem.planNum}&roomNum=${problem.roomNum}&state=${problem.state}&userName=${problem.userName}&startDate=${problem.startDate}&endDate=${problem.endDate}&detype=${problem.detype}&planType=${problem.planType}";
            window.location.href = href;
        }else{
            alert("没有可以导出的数据");
        }
    }
    function isProblemRoomPrint(caseState,projectNum,roomNum,planType,planNum,successOrFailure){
        var room=escape(roomNum)
        var href = encodeURI("../problem/problemRoomPrint.html?caseState="+caseState+"&projectId="+projectNum+"&roomId="+room+"&proType="+planType+"&planNum="+planNum+"&successOrFailure="+successOrFailure+"");
        window.location.href = href;
    }
    /*$("#cityId").change(function(){
        var cityNum = $("#cityNum").val();
        document.getElementById("projectId").innerHTML = "";
        document.getElementById("planNum").innerHTML = "";
        document.getElementById("roomNum").innerHTML = "";
        $.ajax({
            url:"../problem/getPrintProjectList",
            type:"get",
            async:"false",
            data:{ "cityId":cityId},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    console.log(data);
                    var option ="";
                    if(data != null){
                        document.getElementById("projectNum").innerHTML = "";
                        for(var prop in data) {
                            if(!isNaN(data[prop])){
                            }else{
                                if (data.hasOwnProperty(prop)) {
                                    option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                                }
                            }
                        }
                        $("#projectNum").append(option);
                    }
                }
            }
        });
    });*/



    $("#projectId").change(function(){
        var projectId = $("#projectId").val();
        var planType = $("#planType").val();
        document.getElementById("planNum").innerHTML = "";
        document.getElementById("roomNum").innerHTML = "";
        $.ajax({
            url:"../problem/getPrintPlanList",
            type:"get",
            async:"false",
            data:{ "projectId":projectId,"planType":planType},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    if(data != null){
                        document.getElementById("planNum").innerHTML = "";
                        for(var prop in data) {
                            if(!isNaN(data[prop])){
                            }else{
                                if (data.hasOwnProperty(prop)) {
                                    option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                                }
                            }
                        }
                        $("#planNum").append(option);
                    }
                }
            }
        });
    });

    $("#planNum").change(function(){
        var planNum = $("#planNum").val();
        $.ajax({
            url:"../problem/getPrintPlanRoomList",
            type:"get",
            async:"false",
            data:{ "planNum":planNum},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    if(data != null){
                        document.getElementById("roomNum").innerHTML = "";
                        for(var prop in data) {
                            if(!isNaN(data[prop])){
                            }else{
                                if (data.hasOwnProperty(prop)) {
                                    option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                                }
                            }
                        }
                        $("#roomNum").append(option);
                        $('.roomNum_select').trigger('chosen:updated');
                        $('.roomNum_select').chosen();
                    }
                }
            }
        });
    });

    $(function(){
        var roomNum = $("#roomNum").val();
        if(roomNum != null){
            $('.roomNum_select').chosen();
        }
    });

</script>
</body>

</html>