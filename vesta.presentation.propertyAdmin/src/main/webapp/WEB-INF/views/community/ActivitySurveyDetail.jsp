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
            $("#006900040000").addClass("active");
            $("#006900040000").parent().parent().addClass("in");
            $("#006900040000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <style>
        .flowersList img{
            width: 20px;
        }
        .imgList img{
            width: 100px;
            height: 120px;
        }
        .sidebar ul li{
            border-bottom: 0;
        }
    </style>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="006900040000" username="${propertystaff.staffName}" />
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <%-- 当班时间 --%>
                <div class="form-group col-lg-12">
                    <label class="col-sm-3 control-label text-right">当班时间：</label>
                    <div class="col-sm-9 text-left">
                        <label class="control-label"><fmt:formatDate type="time" value="${activitySurveyDetail.workOn}" pattern="yyyy-MM-dd" /></label>
                    </div>
                </div>
                <%-- 当班项目 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label text-right">当班项目：</label>
                    <div class="col-sm-9 text-left">
                        <label class="control-label">${activitySurveyDetail.projectName}</label>
                    </div>
                </div>
                <%-- 当班内容 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label text-right">当班内容：</label>
                    <div class="col-sm-9 text-left">
                        <label class="control-label">${activitySurveyDetail.workContent}</label>
                    </div>
                </div>
                <%-- 个人感受 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label text-right">个人感受：</label>
                    <div class="col-sm-9 text-left">
                        <label class="control-label">${activitySurveyDetail.feel}</label>
                    </div>
                </div>
                <%-- 提升建议 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label text-right">提升建议：</label>
                    <div class="col-sm-9 text-left">
                        <label class="control-label">${activitySurveyDetail.proposal}</label>
                    </div>
                </div>
                <%-- 现场图片 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label text-right">现场图片：</label>
                    <div class="col-sm-9 text-left">
                        <c:forEach items="${activitySurveyDetail.projectPhotoList}" var="projectPhoto">
                            <img src="${projectPhoto}" alt="现场图片" class="img-thumbnail" style="width: auto">
                        </c:forEach>
                    </div>
                </div>
                <%-- 电子签名 --%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label text-right">电子签名：</label>
                    <div class="col-sm-9 text-left">
                        <img src="${activitySurveyDetail.autograph}" alt="电子签名" class="img-thumbnail" style="width: auto">
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
