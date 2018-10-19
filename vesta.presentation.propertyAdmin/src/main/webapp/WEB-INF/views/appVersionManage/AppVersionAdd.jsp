<%--
  Created by IntelliJ IDEA.
  User: jiazefneg
  Date: 2016/11/4
  Time: 15:08
  To change this template use File | Settings | File Templates.
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
    <script src="../static/property/js/checkNullAllJsp.js"></script>
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
<script type="text/javascript">
    $(function () {
        console.log("sqq")
        $("#002400010000").addClass("active");
        $("#002400010000").parent().parent().addClass("in");
        $("#002400010000").parent().parent().parent().parent().addClass("active");
    })
    function testVersionAdd() {
        if ($("#appEdition").val() == "10") {
            alert("APP类型不能为空！");
            return false;
        }
        if ($("#appVersionType").val() == "10") {
            alert("系统类型不能为空！");
            return false;
        }
        if (!CheckNull($("#appVersionNum").val(), "版本号不能为空！")) {
            return false;
        }
        if ($("#appVersionNum").val() == "") {
            alert("版本号不能为空！");
            return false;
        }
        if (!CheckNull($("#appVersionName").val(), "版本名称不能为空！")) {
            return false;
        }
        if ($("#appVersionName").val() == "") {
            alert("版本名称不能为空！");
            return false;
        }
        if (!CheckNull($("#createBy").val(), "发布人不能为空！")) {
            return false;
        }
        if ($("#createBy").val() == "") {
            alert("发布人不能为空！");
            return false;
        }
        if (!CheckNull($("#downUrl").val(), "下载地址不能为空！")) {
            return false;
        }
        if ($("#downUrl").val() == "") {
            alert("下载地址不能为空！");
            return false;
        }
        if (!CheckNull($("#fileSize").val(), "文件大小不能为空！")) {
            return false;
        }
        if ($("#fileSize").val() == "") {
            alert("文件大小不能为空！");
            return false;
        }
        if (!CheckNull($("#appRemark").val(), "更新记录不能为空！")) {
            return false;
        }
        if ($("#appRemark").val() == "") {
            alert("更新记录不能为空！");
            return false;
        }
    }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="002400010000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <form class="form-horizontal" action="../qualityAPPVersionManagement/versionAdd">
                    <%--类型--%>
                    <div class="form-group  col-lg-6">
                        <label for="appVersionType" class="col-sm-4 control-label"><spring:message
                                code="AppVersion_AppType"/>：</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="appEdition" name="appEdition">
                                <option value="10"><spring:message code="systemnotification.type_all"/></option>
                                <option value="1"><spring:message code="AppVersion_Deliver"/></option>
                                <option value="2"><spring:message code="AppVersion_Engineering"/></option>
                                <option value="3"><spring:message code="AppVersion_Security"/></option>
                            </select>
                        </div>
                    </div>
                    <%--类型--%>
                    <div class="form-group  col-lg-6">
                        <label for="appVersionType" class="col-sm-4 control-label"><spring:message
                                code="AppVersion_Type"/>：</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="appVersionType" name="appVersionType">
                                <option value="10"><spring:message code="systemnotification.type_all"/></option>
                                <option value="1"><spring:message code="AppVersion_IOS"/></option>
                                <option value="2"><spring:message code="AppVersion_Android"/></option>
                            </select>
                        </div>
                    </div>
                    <%--版本号--%>
                    <div class="form-group  col-lg-6">
                        <label for="appVersionNum" class="col-sm-4 control-label"><spring:message
                                code="AppVersion_appVersion"/>：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="appVersionNum"
                                   name="appVersionNum" value="${appVersionDTO.appVersionNum}">
                        </div>
                    </div>
                    <%--版本名称--%>
                    <div class="form-group  col-lg-6">
                        <label for="appVersionName" class="col-sm-4 control-label"><spring:message
                                code="AppVersion_Name"/>：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="appVersionName"
                                   name="appVersionName" value="${appVersionDTO.appVersionName}">
                        </div>
                    </div>
                    <%--发布人--%>
                    <div class="form-group  col-lg-6">
                        <label for="createBy" class="col-sm-4 control-label"><spring:message
                                code="AppVersion_createBy"/>：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="createBy" name="createBy"
                                   value="${appVersionDTO.createBy}">
                        </div>
                    </div>
                    <%--发布日期--%>
                    <div class="form-group  col-lg-6">
                        <label for="createOn" class="col-sm-4 control-label"><spring:message
                                code="AppVersion_createOn"/>：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="createOn" name="createOn"
                                   value="${appVersionDTO.createOn}" readonly>
                        </div>
                    </div>
                    <%--下载地址--%>
                    <div class="form-group  col-lg-6">
                        <label for="downUrl" class="col-sm-4 control-label"><spring:message
                                code="AppVersion_DownUrl"/>：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="downUrl" name="downUrl"
                                   value="${appVersionDTO.downUrl}">
                        </div>
                    </div>
                    <%--发布状态--%>
                    <div class="form-group  col-lg-6">
                        <label class="col-sm-4 control-label"><spring:message code="AppVersion_status"/>：</label>
                        <div for="appVersionStatus" class="form-control"
                             style="background-color: inherit;box-shadow: none;border: inherit;padding-top:12px;padding-left:135px">
                            <input type="radio" name="appVersionStatus" id="appVersionStatus_yes"
                                   value="2"><spring:message code="AppVersion_status_YES"/>
                            <input type="radio" name="appVersionStatus" id="appVersionStatus_no" value="1"
                                   checked="checked"><spring:message code="AppVersion_status_NO"/>
                        </div>
                    </div>
                    <%--文件大小--%>
                    <div class="form-group  col-lg-6">
                        <label class="col-sm-4 control-label"><spring:message code="AppVersion_size"/>：</label>
                        <div for="fileSize" class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="fileSize" name="fileSize"
                                   value="${appVersionDTO.fileSize}">
                        </div>
                    </div>
                    <%--功能介绍--%>
                    <div class="form-group  col-lg-12">
                        <label for="appRemark" class="col-sm-2 control-label"><spring:message code="AppVersion_Remark"/>：</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" placeholder="" id="appRemark"
                                      name="appRemark">${appVersionDTO.appRemark}</textarea>
                        </div>
                    </div>
                    <div class="text-center col-lg-12">
                        <c:if test="${function.qch40010079 eq 'Y'}">
                            <button type="submit" class="btn btn-primary" for="propertySearch"
                                    onclick="return testVersionAdd()"><spring:message code="common_confirm"/></button>
                        </c:if>
                        <a href="../qualityAPPVersionManagement/appVersionManage.html" class="btn btn-primary"
                           for="rolesetAdd"><spring:message code="common_back"/></a>
                    </div>
                    <div class="clearfix">

                    </div>
                </form>
            </div>
        </div>
    </div>


</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
