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
    <style>
        .detail {
            color: #0f0f0f;
        }

        .detail:hover {
            color: #337ab7;
            background: #33FFFF;
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
    </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005400010000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form id="form" class="form-horizontal" action="../deliveryPlan/DeliveryPlan.html">
                    <input type="hidden" id="menuId" name="menuId" value="005400010000"/>
                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>
                        <div class="col-sm-8">
                            <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq deliveryPlanCrmDto.scopeId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group  col-xs-4">
                        <label for="projectNum" class="col-sm-4 control-label">项目</label>
                        <div class="col-sm-8">
                            <select id="projectNum" name="projectNum" class="form-control">
                                <c:forEach items="${projectList}" var="item" >
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq deliveryPlanCrmDto.projectNum}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 支付批次 -->
                    <div class="form-group  col-xs-4">
                        <label for="planName" class="col-sm-4 control-label"><spring:message
                                code="HousePayBatch.payBatch"/></label>

                        <div class="col-sm-8">
                            <select id="planName" name="planName" class="form-control">
                                <option value="" id="community"><spring:message code="HousePayBatch.ing"/></option>
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
                    <div class="form-group  col-xs-4">
                        <label for="batchStatus" class="col-sm-4 control-label"><spring:message
                                code="HousePayBatch.patStatus"/></label>

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
                    <!-- 集中交付开始时间 -->
                    <div class="form-group  col-xs-4">
                        <div for="planStart" class="col-sm-4 control-label">发布日期</div>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="planStart" name="planStart"
                                   value="${deliveryPlanCrmDto.planStart}" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <!-- 集中交付结束时间 -->
                    <div class="form-group  col-xs-4">
                        <div for="planEnd" class="col-sm-4 control-label">至</div>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="planEnd"
                                   value="${deliveryPlanCrmDto.planEnd}" name="planEnd" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class='clearfix'></div>
                    <div class="search_button col-xs-12">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <!--集合长度(取决Excel是否可以导出)-->
                        <input type="hidden" id="size" value="${isExcel}">
                        <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary"
                           style="color:#fff">导出Excel</a>
                    </div>
                    <%--<a  href="../communityArea/addPage" class="btn btn-primary" for="payBatchAdd" ><spring:message code="propertyCompany.propertyAdd"/></a>--%>
                    <div class='clearfix'></div>
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
                <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                <th><spring:message code="HousePayBatch.payBatch"/></th>
                <th><spring:message code="HousePayBatch.projectName"/></th>
                <th><spring:message code="HousePayBatch.payStaDate"/></th>
                <th><spring:message code="DeliveryPlanCrm.description"/></th>
                <th><spring:message code="DeliveryPlanCrm.publishStatus"/></th>
                <th><spring:message code="propertyCompany.operation"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${deliveryPlanCrmDtos}" var="appointManage" varStatus="row">
                <tr>

                    <td><b>${row.index + 1}</b></td>
                    <td class="col-sm-2">${appointManage.planName}</td>
                    <td class="col-sm-2">${appointManage.projectName}</td>
                    <td class="col-sm-2">${appointManage.planStart} <spring:message
                            code="HousePayBatch.to"/> ${appointManage.planEnd}</td>
                    <td class="col-sm-4">${appointManage.description}</td>
                    <td class="col-sm-1">
                        <c:if test="${appointManage.batchStatus == 0}"><spring:message code="HousePayBatch.unPublish"/>
                        </c:if>
                        <c:if test="${appointManage.batchStatus == 1}"><spring:message
                                code="HousePayBatch.publish"/> </c:if>
                    </td>
                    <td class="last col-sm-3">
                        <a href="javascript:void(0);"
                           onclick="js_update('${appointManage.id}','${appointManage.projectName}')" id="update"
                           class="">计划修改</a>
                        <a href="javascript:void(0);"
                           onclick="js_detail('${appointManage.id}','${appointManage.projectName}')" id="detail"
                           class="a3">预约详情</a>
                        <c:if test="${appointManage.batchStatus == 1}">
                            <a href="javascript:void(0);" onclick="js_unPublish('${appointManage.id}')" id="unPublish"
                               class="a3"><spring:message code="HousePayBatch.cancelPublish"/></a>
                        </c:if>
                        <c:if test="${appointManage.batchStatus == 0}">
                            <a href="javascript:void(0);" onclick="js_publish('${appointManage.id}')" id="publish"
                               class="a3"><spring:message code="activityManage.activityPublish"/></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/deliveryPlan/DeliveryPlan.html?menuId=005400010000&pageIndex={0}&scopeId=${deliveryPlanCrmDto.scopeId}&projectNum=${deliveryPlanCrmDto.projectNum}&planName=${deliveryPlanCrmDto.planName}&batchStatus=${deliveryPlanCrmDto.batchStatus}&description=${deliveryPlanCrmDto.description}&planStart=${deliveryPlanCrmDto.planStart}&planEnd=${deliveryPlanCrmDto.planEnd}"/>
    </div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
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
            $("#planName").append("<option value='0'><spring:message code="HousePayBatch.ing"/></option>");
            for (var k in arr) {
                $("#planName").append('<option value=' + arr[k] + '>' + arr[k] + '</option>');
            }

        });

    }

    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
            var href = "../deliveryPlan/exportExcel?type=1&pageIndex={0}&scopeId=${deliveryPlanCrmDto.scopeId}&projectNum=${deliveryPlanCrmDto.projectNum}&planName=${deliveryPlanCrmDto.planName}&batchStatus=${deliveryPlanCrmDto.batchStatus}&description=${deliveryPlanCrmDto.description}&planStart=${deliveryPlanCrmDto.planStart}&planEnd=${deliveryPlanCrmDto.planEnd}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }
</script>
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
    //计划批次
    var planName = ${deliveryPlanCrmDto.planName};
    var batchStatus = ${deliveryPlanCrmDto.batchStatus};
    var description = ${deliveryPlanCrmDto.description};
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
    //通过城市获取项目列表
    function queryProjectNameById() {
        $("#projectNum").find("option").remove();
        /* -------------------- */
        var projectId = $("#scopeId").val();
        if(projectId == '-1'){
            $("#projectNum").empty();
            return ;
        }
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectNum").empty();
            $("#projectNum").append('<option value="">请选择</option>');
            for (var k in arr) {
                if(arr[k][0] != '0'){
                    $("#projectNum").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
                }
            }
        });
    }
    function js_update(id, projectName) {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    $.ajax({
                        type: "GET",
                        url: "../deliveryPlan/checkEdit/" + id + "/" + $("#menuId").val(),
                        cache: false,
                        async: false,
                        dataType: "json",
                        success: function (data) {
                            if (data.error == 1) {
                                window.location.href = "../deliveryPlan/ueliveryPlanCrmUpdate?id=" + id + "&projectName=" + projectName;
                            } else if (data.error == 0) {
                                alert("对不起，您的权限范围无法执行此操作！");
                                return;
                            } else {
                                alert("对不起，操作失败！");
                                return;
                            }
                        }
                    });
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    function js_publish(id) {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    $.ajax({
                        type: "GET",
                        url: "../deliveryPlan/checkEdit/" + id + "/" + $("#menuId").val(),
                        cache: false,
                        async: false,
                        dataType: "json",
                        success: function (data) {
                            if (data.error == 1) {
                                window.location.href = "../deliveryPlan/updateBatchStatus?id=" + id + "&tempStatus=1";
                            } else if (data.error == 0) {
                                alert("对不起，您的权限范围无法执行此操作！");
                                return;
                            } else {
                                alert("对不起，操作失败！");
                                return;
                            }
                        }
                    });
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });

    }
    function js_unPublish(id) {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    $.ajax({
                        type: "GET",
                        url: "../deliveryPlan/checkEdit/" + id + "/" + $("#menuId").val(),
                        cache: false,
                        async: false,
                        dataType: "json",
                        success: function (data) {
                            if (data.error == 1) {
                                window.location.href = "../deliveryPlan/updateBatchStatus?id=" + id + "&tempStatus=0";
                            } else if (data.error == 0) {
                                alert("对不起，您的权限范围无法执行此操作！");
                                return;
                            } else {
                                alert("对不起，操作失败！");
                                return;
                            }
                        }
                    });
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }

    //查看详情
    function js_detail(id, projectName) {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    $.ajax({
                        type: "GET",
                        url: "../deliveryPlan/checkEdit/" + id + "/" + $("#menuId").val(),
                        cache: false,
                        async: false,
                        dataType: "json",
                        success: function (data) {
                            if (data.error == 1) {
                                window.location.href = "../deliveryPlan/deliveryPlanCrmDetail?id=" + id + "&projectName=" + projectName;
                            } else if (data.error == 0) {
                                alert("对不起，您的权限范围无法执行此操作！");
                                return;
                            } else {
                                alert("对不起，操作失败！");
                                return;
                            }
                        }
                    });
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>