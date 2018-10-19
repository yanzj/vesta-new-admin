<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/5/18
  Time: 15:06
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    .control_btn{
        background-color: #fbfbfb;
        padding-bottom: 1rem;
    }
    .control_btn .newBut{
        margin-right: 1rem;
    }
    .search_button{
        text-align: center;
    }

</style>
<script type="text/javascript">
    function goSubmit(id) {
        document.getElementById("rolesetId").value = id;
        document.getElementById("search_form").submit();
    }
    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
            var href = "../notification/exportExcel?pageIndex={0}&notificationType=${systemNotificationDTO.notificationType}&notificationTopStatus=${systemNotificationDTO.notificationTopStatus}&notificationStatus=${systemNotificationDTO.notificationStatus}&notificationContent=${systemNotificationDTO.notificationContent}&notificationTitle=${systemNotificationDTO.notificationTitle}&notificationCreater=${systemNotificationDTO.notificationCreater}&notificationCreateBeginTime=${systemNotificationDTO.notificationCreateBeginTime}&notificationCreateEndTime=${systemNotificationDTO.notificationCreateEndTime}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="000100020000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" value="000100020000"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../notification/systemMessage.html" method="post">
                    <%--公告类型--%>
                    <div class="form-group  col-xs-4">
                        <label for="notificationType" class="col-sm-4 control-label"><spring:message
                                code="systemnotification.type"/></label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="notificationType" name="notificationType">
                                <option value="0"
                                        <c:if test="${systemNotificationDTO.notificationType eq '0'}">selected</c:if>>
                                    <spring:message code="systemnotification.type_all"/></option>
                                <option value="10001"
                                        <c:if test="${systemNotificationDTO.notificationType eq '10001'}">selected</c:if>>
                                    <spring:message code="systemnotification.message"/></option>
                                <option value="10000"
                                        <c:if test="${systemNotificationDTO.notificationType eq '10000'}">selected</c:if>>
                                    <spring:message code="systemnotification.other"/></option>
                            </select>
                        </div>
                    </div>
                    <%--发布状态--%>
                    <div class="form-group  col-xs-4">
                        <label for="notificationStatus" class="col-sm-4 control-label"><spring:message
                                code="systemnotification.releaseStatus"/></label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="notificationStatus"
                                    name="notificationStatus">
                                <option value="0"
                                        <c:if test="${systemNotificationDTO.notificationStatus eq '0'}">selected</c:if>>
                                    <spring:message code="systemnotification.type_all"/></option>
                                <option value="10001"
                                        <c:if test="${systemNotificationDTO.notificationStatus eq '10001'}">selected</c:if>>
                                    <spring:message code="systemnotification.release"/></option>
                                <option value="10000"
                                        <c:if test="${systemNotificationDTO.notificationStatus eq '10000'}">selected</c:if>>
                                    <spring:message code="systemnotification.nrelease"/></option>
                            </select>
                        </div>
                    </div>
                    <%--置顶状态--%>
                    <div class="form-group  col-xs-4">
                        <label for="notificationTopStatus" class="col-sm-4 control-label"><spring:message
                                code="systemnotification.topState"/></label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="notificationTopStatus"
                                    name="notificationTopStatus">
                                <option value="0"
                                        <c:if test="${systemNotificationDTO.notificationTopStatus eq '0'}">selected</c:if>>
                                    <spring:message code="systemnotification.type_all"/></option>
                                <option value="10001"
                                        <c:if test="${systemNotificationDTO.notificationTopStatus eq '10001'}">selected</c:if>>
                                    <spring:message code="systemnotification.top"/></option>
                                <option value="10000"
                                        <c:if test="${systemNotificationDTO.notificationTopStatus eq '10000'}">selected</c:if>>
                                    <spring:message code="systemnotification.ntop"/></option>
                            </select>
                        </div>
                    </div>
                    <%--标题--%>
                    <div class="form-group  col-xs-4">
                        <label for="notificationTitle" class="col-sm-4 control-label"><spring:message
                                code="systemnotification.title"/></label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="notificationTitle"
                                   name="notificationTitle" value="${systemNotificationDTO.notificationTitle}">
                        </div>
                    </div>
                    <%--内容--%>
                    <div class="form-group  col-xs-4">
                        <label for="notificationContent" class="col-sm-4 control-label"><spring:message
                                code="systemnotification.content"/></label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="notificationContent"
                                   name="notificationContent" value="${systemNotificationDTO.notificationContent}">
                        </div>
                    </div>

                    <%--发布人--%>
                    <div class="form-group  col-xs-4">
                        <label for="notificationCreater" class="col-sm-4 control-label"><spring:message
                                code="systemnotification.releasePerson"/></label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="notificationCreater"
                                   name="notificationCreater" value="${systemNotificationDTO.notificationCreater}">
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="notificationCreateBeginTime" class="col-sm-4 control-label"><spring:message
                                code="systemnotification.createTime"/></label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="notificationCreateBeginTime"
                                   name="notificationCreateBeginTime"
                                   value="${systemNotificationDTO.notificationCreateBeginTime}" readonly>
                            <span class="input-group-addon" style="padding-left: 6px;padding-right: 6px;"><span
                                    class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon" style="padding-left: 6px;padding-right: 6px;"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <div class="form-group  col-xs-4">
                        <label for="notificationCreateEndTime" class="col-sm-4 control-label"><spring:message
                                code="systemnotification.to"/></label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="notificationCreateEndTime"
                                   name="notificationCreateEndTime"
                                   value="${systemNotificationDTO.notificationCreateEndTime}" readonly>
                            <span class="input-group-addon" style="padding-left: 6px;padding-right: 6px;"><span
                                    class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon" style="padding-left: 6px;padding-right: 6px;"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <div class="col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="common_select"/></button>
                        <button type="button" class="btn btn-primary newBut" onclick="transferPage('000100020000')">
                            <spring:message code="common_add"/></button>
                        <%--<a  href="../notification/gotoEditNotification" class="btn btn-primary" for="rolesetAdd" ><spring:message code="common_add"/></a>--%>
                        <!--集合长度(取决Excel是否可以导出)-->
                        <input type="hidden" id="size" value="${isExcel}">
                        <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary"
                           style="color:#fff">导出Excel</a>
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
        <form action="../notification/systemMessage.html" method="post" id="search_form">

            <input id="activityId" type="hidden" name="rolesetId" value="">
        </form>
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="systemnotification.serialNumber"/></td>
                <th><spring:message code="systemnotification.type"/></th>
                <th><spring:message code="systemnotification.title"/></th>
                <th><spring:message code="systemnotification.releasePerson"/></th>
                <th><spring:message code="systemnotification.createTime"/></th>
                <th><spring:message code="systemnotification.releaseStatus"/></th>
                <th><spring:message code="systemnotification.operation"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${systemNotification}" var="systemNotification" varStatus="sn">
                <tr>
                    <td><b>${sn.count}</b></td>
                    <td>
                        <c:if test="${systemNotification.notificationType eq '10001'}"><spring:message
                                code="systemnotification.message"/></c:if>
                        <c:if test="${systemNotification.notificationType eq '10000'}"><spring:message
                                code="systemnotification.other"/></c:if>
                    </td>
                    <td>${systemNotification.notificationTitle}</td>
                    <td>${systemNotification.notificationCreater}</td>
                    <td>${systemNotification.notificationCreateTime}</td>
                    <td>
                        <c:if test="${systemNotification.notificationStatus eq '10001'}"><spring:message
                                code="systemnotification.release"/></c:if>
                        <c:if test="${systemNotification.notificationStatus eq '10000'}"><spring:message
                                code="systemnotification.nrelease"/></c:if>
                    </td>
                    <td class="last">
                        <c:if test="${systemNotification.notificationStatus eq '10001'}">
                            <%--<a href="../notification/editnotificationstatus?notificationId=${systemNotification.notificationId}&notificationStatus=10000" class="a2"><span class="span1"><spring:message code="systemnotification.revoke" /></span></a>--%>
                            <a href="javascript:void(0);"
                               onclick="js_editStatus('${systemNotification.notificationId}','10000')" class="a2"><span
                                    class="span1"><spring:message code="systemnotification.revoke"/></span></a>
                        </c:if>
                        <c:if test="${systemNotification.notificationStatus eq '10000'}">
                            <%--<a href="../notification/editnotificationstatus?notificationId=${systemNotification.notificationId}&notificationStatus=10001" class="a2"><span class="span1"><spring:message code="systemnotification.introduced" /></span></a>--%>
                            <a href="javascript:void(0);"
                               onclick="js_editStatus('${systemNotification.notificationId}','10001')" class="a2"><span
                                    class="span1"><spring:message code="systemnotification.introduced"/></span></a>
                        </c:if>
                            <%--<a href="../notification/gotoEditNotification?notificationId=${systemNotification.notificationId}" class="a2"><span class="span1"><spring:message code="systemnotification.edit" /></span></a>--%>
                        <a href="javascript:void(0);" onclick="js_gotoEdit('${systemNotification.notificationId}')"
                           class="a2"><span class="span1"><spring:message code="systemnotification.edit"/></span></a>
                            <%--<a onClick="javascript:if (confirm('确定删除吗？')) href='../notification/removeNotificationDetail?notificationdetailId=${systemNotification.notificationId}';else return;" class="a3"><span class="span1"><spring:message code="common_delete" /></span></a>--%>
                        <a href="javascript:void(0);" onClick="js_del('${systemNotification.notificationId}')" class="a3"><span
                                class="span1"><spring:message code="common_delete"/></span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/notification/systemMessage.html?pageIndex={0}&notificationType=${systemNotificationDTO.notificationType}&notificationTopStatus=${systemNotificationDTO.notificationTopStatus}&notificationStatus=${systemNotificationDTO.notificationStatus}&notificationContent=${systemNotificationDTO.notificationContent}&notificationTitle=${systemNotificationDTO.notificationTitle}&notificationCreater=${systemNotificationDTO.notificationCreater}&notificationCreateBeginTime=${systemNotificationDTO.notificationCreateBeginTime}&notificationCreateEndTime=${systemNotificationDTO.notificationCreateEndTime}"/>
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
    //去新增
    function transferPage(menuId) {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + menuId,
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../notification/gotoEditNotification?menuId=" + $("#menuId").val();
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //撤销、发布
    function js_editStatus(notificationId, notificationStatus) {
        var flag = 0;   //操作级别标示
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    flag = 1;
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                    return;
                } else {
                    alert("对不起，操作失败！");
                    return;
                }
            }
        });
        if (flag != 0) {
            window.location.href = "/notification/editnotificationstatus?notificationId=" + notificationId + "&notificationStatus=" + notificationStatus;
        }
    }
    //去编辑
    function js_gotoEdit(notificationId) {
        var flag = 0;   //操作级别标示
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    flag = 1;
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                    return;
                } else {
                    alert("对不起，操作失败！");
                    return;
                }
            }
        });
        if (flag != 0) {
            window.location.href = "/notification/gotoEditNotification?notificationId=" + notificationId;
        }
    }
    //删除
    function js_del(notificationId) {
        var flag = 0;   //操作级别标示
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    flag = 1;
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                    return;
                } else {
                    alert("对不起，操作失败！");
                    return;
                }
            }
        });
        if (flag != 0) {
            myvalue = confirm("确定要删除吗?");
            if (myvalue == true) {
                window.location.href = "/notification/removeNotificationDetail?notificationdetailId=" + notificationId;
            }
        }
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
