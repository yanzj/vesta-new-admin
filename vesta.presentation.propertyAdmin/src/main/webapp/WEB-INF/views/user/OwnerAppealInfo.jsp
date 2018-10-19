<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/5/21
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%response.setHeader("cache-control","public"); %>
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
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        $(function(){
            console.log("sqq")
            $("#005200010000").addClass("active");
            $("#005200010000").parent().parent().addClass("in");
            $("#005200010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005200010000" username="${propertystaff.staffName}" />
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <%-- 姓名 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label"><spring:message code="ownerManage.Name" />：</label>
                    <label class="control-label">${ownerAppealInfo.ownerName}</label>
                </div>
                <%-- 证件类型 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">证件类型：</label>
                    <label class="control-label">
                        <c:if test="${'100000000' eq ownerAppealInfo.idType}">身份证</c:if>
                        <c:if test="${'100000001' eq ownerAppealInfo.idType}">军官证</c:if>
                        <c:if test="${'100000002' eq ownerAppealInfo.idType}">警官证</c:if>
                        <c:if test="${'100000003' eq ownerAppealInfo.idType}">护照</c:if>
                        <c:if test="${'100000004' eq ownerAppealInfo.idType}">营业执照</c:if>
                    </label>
                </div>
                <%-- 证件号码 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">证件号码：</label>
                    <label class="control-label">${ownerAppealInfo.idCard}</label>
                </div>
                <%-- 房产地址 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">房产编码：</label>
                    <label class="control-label">${ownerAppealInfo.houseNum}</label>
                </div>
                <%-- 手机号 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">手机号：</label>
                    <label class="control-label">${ownerAppealInfo.mobile}</label>
                </div>
                <%-- 申诉时间 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">申诉时间：</label>
                    <label class="control-label"><fmt:formatDate type="time" value="${ownerAppealInfo.createOn}" pattern="yyyy-MM-dd hh:mm:ss" /></label>
                </div>
                <%-- 操作人 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">操作人：</label>
                    <label class="control-label">${ownerAppealInfo.modifyBy}</label>
                </div>
                <%-- 客户端来源 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">客户端来源：</label>
                    <label class="control-label">${ownerAppealInfo.clientName}</label>
                </div>
                <%-- 处理状态 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">处理状态：</label>
                    <label class="control-label">
                        <c:if test="${'100' eq ownerAppealInfo.handleState}">未处理</c:if>
                        <c:if test="${'101' eq ownerAppealInfo.handleState}">申诉通过</c:if>
                        <c:if test="${'102' eq ownerAppealInfo.handleState}">申诉失败</c:if>
                    </label>
                </div>
                <%-- 合同封面 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">合同封面：</label>
                    <div class="col-sm-5">
                        <img src="${ownerAppealInfo.contractCoverUrl}" alt="合同封面" class="img-thumbnail" style="width: auto">
                    </div>
                </div>
                <%-- 证件照(正面) --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">证件照（正面）：</label>
                    <div class="col-sm-5">
                        <img src="${ownerAppealInfo.idPhotoFrontUrl}" alt="证件照(正面)" class="img-thumbnail" style="width: auto">
                    </div>
                </div>
                <%-- 证件照(反面) --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">证件照（反面）：</label>
                    <div class="col-sm-5">
                        <img src="${ownerAppealInfo.idPhotoBackUrl}" alt="证件照(反面)" class="img-thumbnail" style="width: auto">
                    </div>
                </div>
                <div class="text-center form-group  col-lg-12" style="padding:0;">
                    <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);"><spring:message code="common_cancel"/></button>
                </div>
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
