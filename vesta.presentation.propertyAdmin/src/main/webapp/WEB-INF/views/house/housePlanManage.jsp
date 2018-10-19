<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script src="../static/property/js/propertyHousPay.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="002100010000" username="${authPropertystaff.staffName}" />
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form id="form" class="form-horizontal" action="../houseplan/housePlanManage.html">
                    <%-- 集团名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="groupId" class="col-sm-4 control-label"><spring:message
                                code="problem.group"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="groupId" name="groupId">
                                <c:forEach items="${groups}" var="group">
                                    <option value="${group.key }"
                                            <c:if test="${group.key eq deliveryPlanCrmDto.groupId}">selected</c:if>>${group.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 区域名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="regionId" class="col-sm-4 control-label"><spring:message
                                code="problem.region"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="regionId" name="regionId">
                                <c:forEach items="${regions}" var="region">
                                    <option value="${region.key }"
                                            <c:if test="${region.key eq deliveryPlanCrmDto.regionId}">selected</c:if>>${region.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 城市名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="cityId" class="col-sm-4 control-label"><spring:message
                                code="problem.city"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="cityId" name="cityId">
                                <c:forEach items="${citys}" var="city">
                                    <option value="${city.key }"
                                            <c:if test="${city.key eq deliveryPlanCrmDto.cityId}">selected</c:if>>${city.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group  col-lg-4">
                        <label for="projectNum" class="col-sm-4 control-label"><spring:message
                                code="problem.project"/>：</label>

                        <div class="col-sm-8">
                            <select id="projectNum" name="projectNum" class="form-control"
                                    onchange="queryBatchByProjectNum()">
                                <c:forEach items="${projects}" var="item">
                                    <option value="${item.key }"
                                            <c:if test="${item.key eq deliveryPlanCrmDto.projectNum}">selected</c:if>>
                                            ${item.value }
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 支付批次 -->
                    <div class="form-group  col-lg-4">
                        <label for="planName" class="col-sm-4 control-label"><spring:message
                                code="HousePayBatch.payBatch"/>：</label>

                        <div class="col-sm-8">
                            <select id="planName" name="planName" class="form-control">
                                <c:forEach items="${batchs}" var="item">
                                    <option value="${item}"
                                            <c:if test="${item eq deliveryPlanCrmDto.planName}">selected</c:if>>
                                            ${item}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 支付状态 -->
                    <div class="form-group  col-lg-4">
                        <label for="batchStatus" class="col-sm-4 control-label"><spring:message
                                code="HousePayBatch.patStatus"/>：</label>

                        <div class="col-sm-8">
                            <select id="batchStatus" name="batchStatus" class="form-control">
                                <c:forEach items="${batchStatusDtoList}" var="item">
                                    <option value="${item.id }"
                                            <c:if test="${item.id eq deliveryPlanCrmDto.batchStatus}">selected</c:if>>
                                        <c:if test="${item.id == 0}"><spring:message
                                                code="HousePayBatch.unPublish"/></c:if>
                                        <c:if test="${item.id == 1}"><spring:message
                                                code="HousePayBatch.publish"/></c:if>
                                        <c:if test="${item.id == 2}"><spring:message
                                                code="HousePayBatch.ing"/></c:if>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class='clearfix'></div>
                    <!-- 集中交付开始时间 -->
                    <div class="form-group  col-lg-6">
                        <label for="planStart" class="col-sm-3 control-label"><spring:message
                                code="propertyAnnouncement.createTimeStart"/>：</label>

                        <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="planStart" name="planStart"
                                   value="${deliveryPlanCrmDto.planStart}" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <!-- 集中交付结束时间 -->
                    <div class="form-group  col-lg-6">
                        <label for="planEnd" class="col-sm-3 control-label"><spring:message
                                code="propertyAnnouncement.createTimeEnd"/>：</label>

                        <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="planEnd"
                                   value="${deliveryPlanCrmDto.planEnd}" name="planEnd" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class='clearfix'></div>
                    <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                            code="propertyCompany.propertySearch"/></button>

                    <%--<a  href="../communityArea/addPage" class="btn btn-primary" for="payBatchAdd" ><spring:message code="propertyCompany.propertyAdd"/></a>--%>

                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                <th><spring:message code="HousePayBatch.payBatch"/></th>
                <th><spring:message code="HousePayBatch.projectName"/></th>
                <%--<th><spring:message code="HousePayBatch.payStaDate"/></th>--%>
                <th>修改时间</th>
                <th><spring:message code="HousePayBatch.shortName"/></th>
                <th><spring:message code="DeliveryPlanCrm.publishStatus"/></th>
                <th><spring:message code="propertyCompany.operation"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${deliveryPlanCrmDtos}" var="appointManage" varStatus="row">
                <tr>

                    <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
                    <td>${appointManage.planName}</td>
                    <td>${appointManage.projectName}</td>
                    <%--<td>${appointManage.planStart} <spring:message--%>
                            <%--code="HousePayBatch.to"/> ${appointManage.planEnd}</td>--%>
                    <td>${appointManage.modifyTime}</td>
                    <td>${appointManage.shortName}</td>
                    <td>
                        <c:if test="${appointManage.batchStatus == 0}"><spring:message code="HousePayBatch.unPublish"/>   </c:if>
                        <c:if test="${appointManage.batchStatus == 1}"><spring:message
                                code="HousePayBatch.publish"/>   </c:if>
                    </td>
                    <td class="last">
                        <c:if test="${function.qch40010070 eq 'Y'}">
                            <a href="javascript:void(0);"
                               onclick="js_update('${appointManage.id}','${appointManage.projectName}')" id="update"
                               class="a3"><spring:message  code="HousePayBatch.setShortName"/></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/houseplan/housePlanManage.html?pageIndex={0}&groupId=${deliveryPlanCrmDto.groupId}&regionId=${deliveryPlanCrmDto.regionId}&cityId=${deliveryPlanCrmDto.cityId}&projectNum=${deliveryPlanCrmDto.projectNum}&planName=${deliveryPlanCrmDto.planName}&batchStatus=${deliveryPlanCrmDto.batchStatus}&planStart=${deliveryPlanCrmDto.planStart}&planEnd=${deliveryPlanCrmDto.planEnd}"/>
    </div>
    `
</div>
</div>
</div>
</div>
<script type="text/javascript">

    //根据集团id查询区域信息
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
                        document.getElementById("projectNum").innerHTML = "";
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
    //根据区域id查询城市信息
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
                        document.getElementById("projectNum").innerHTML = "";
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


    //根据城市Id获取项目信息
    $("#cityId").change(function () {
        var cityId = $("#cityId").val();
        $.ajax({
            url: "../problem/getProjectMun",
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
                        document.getElementById("projectNum").innerHTML = "";
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
                        $("#projectNum").append(option);
                    }
                }
            }
        });
    });

    function gosubmit() {
        alert(1);
        var _form = $('#form').serialize();
        alert(_form);
        $.ajax({
            url: "../deliveryPlan/DeliveryPlan.html",
            type: "GET",
            async: "false",
            dataType: "json",
            data: _form,
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code == 0) {
                    window.parent.location.href = "../deliveryPlan/DeliveryPlan.html";
                }
            }
        });
    }
    function queryBatchByProjectNum() {
        var projectNum = $("#projectNum").val();
        $("#planName").empty();
        $.getJSON("/deliveryPlan/queryBatchByProNum/" + projectNum, function (data) {
            var arr = data.data;
            $("#planName").empty();
            for (var k in arr) {
                $("#planName").append('<option value=' + arr[k] + '>' + arr[k] + '</option>');
            }

        });
    }
</script>
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
    //计划批次
    var planName = "${deliveryPlanCrmDto.planName}";
    var batchStatus = "${deliveryPlanCrmDto.batchStatus}";
    var description = "${deliveryPlanCrmDto.description}";
    var projectName = "${deliveryPlanCrmDto.projectName}";

    var planStart = "${deliveryPlanCrmDto.planStart}";
    var planEnd = "${deliveryPlanCrmDto.planEnd}";
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

    function js_update(id,projectName) {
        window.location.href = "../houseplan/toUpdate?id=" + id + "&projectName=" + projectName;;
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>