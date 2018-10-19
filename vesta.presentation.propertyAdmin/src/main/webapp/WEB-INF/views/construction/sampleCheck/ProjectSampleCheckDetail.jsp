<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

    <script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
    </script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="../../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="../../../../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- Custom CSS -->
    <link href="../../../../static/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="../../../../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../../../../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="../../../../static/js/jquery-1.11.1.min.js"></script>
    <script src="../../../../static/js/modernizr.custom.js"></script>

    <script type="text/javascript" src="../../../../static/plus/js/jquery.validate.js"></script>
    <link rel="stylesheet" href="../../../../static/css/detailsCss.css"/>
    <!--animate-->
    <link href="../../../../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../../../../static/js/wow.min.js"></script>
    <script>
        $(function () {
            $("#002700010000").addClass("active");
            $("#002700010000").parent().parent().addClass("in");
            $("#002700010000").parent().parent().parent().parent().addClass("active");
        });
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../../../../static/js/metisMenu.min.js"></script>
    <script src="../../../../static/js/custom.js"></script>
    <link href="../../../../static/css/custom.css" rel="stylesheet">
    <link href="../../../../static/css/target.css" rel="stylesheet">
    <!--//Metis Menu -->
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="002700010000" username="${authPropertystaff.staffName}"/>
    <div class="newDetail__">
        <div class="nav">
            <div class="rightButton">
                <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
                <c:if test="${sampleCheck.state ne '合格' && sampleCheck.state ne '非正常关闭' && checkAuthFunction.esh40020101 eq 'Y'}">
                    <button data-toggle="modal1" class="btn btn-primary" onclick="closeQuestionModel()">非正常关闭
                </c:if>
                <%--<c:choose>--%>
                    <%--<c:when test="${authority == false || sampleCheck.state eq '非正常关闭' || sampleCheck.state eq '合格'}">--%>
                        <%--<button class="btn btn-primary" style="display: none">非正常关闭--%>
                        <%--</button>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<button data-toggle="modal1" class="btn btn-primary" onclick="closeQuestionModel()">非正常关闭--%>
                        <%--</button>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                <c:choose>
                    <c:when test="${sampleCheck.state eq '非正常关闭'}">
                        <button class="btn btn-primary" style="display: none">导出PPT
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-primary" onclick="exportPPT()">导出PPT
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="notice_inner" style="padding: 0;margin:0;width: auto;">
            <h2 class="keyComTitle" style="width: 90%;">${sampleCheck.title}</h2>

            <div class="container" style="width: 90%;padding: 0;height: 100%;margin: 0;">
                <div class="col-lg-12" style="padding-left:0;margin-left: -30px;">
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">创建人：</span>
                        <span class="col-sm-8">${sampleCheck.createName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">创建时间：</span>
                        <span class="col-sm-8">${sampleCheck.createOn}</span>
                        <%--<fmt:formatDate type="date" value="${keyProcesses.createDate}" dateStyle="default"--%>
                        <%--pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">问题状态：</span>
                        <span class="col-sm-8">${sampleCheck.state}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">所属项目：</span>
                        <span class="col-sm-8">${sampleCheck.projectName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">检查项：</span>
                        <span class="col-sm-8">${sampleCheck.classifyThreeName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">严重等级：</span>
                        <span class="col-sm-8">${sampleCheck.severityLevel}</span>
                    </div>
                    <%--<div class="repair_per col-sm-4">--%>
                    <%--<span class="key1">检查楼栋：</span>--%>
                    <%--<span class="value_">${sampleCheck.buildingName}</span>--%>
                    <%--</div>--%>
                    <%--<div class="repair_per col-sm-4">--%>
                    <%--<span class="key1">楼层区间：</span>--%>
                    <%--<span class="levels">${sampleCheck.floorNum1}~${sampleCheck.floorNum2}</span>--%>
                    <%--</div>--%>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">检查部位：</span>
                        <span class="col-sm-8">${sampleCheck.checkPosition}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">检查时间：</span>
                        <span class="col-sm-8">${sampleCheck.checkDate}</span>
                        <%--<fmt:formatDate type="date" value="${keyProcesses.examinationDate}" dateStyle="default"--%>
                        <%--pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">甲方负责人：</span>
                        <span class="col-sm-8">${sampleCheck.firstPartyName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">第三方监理：</span>
                        <span class="col-sm-8">${sampleCheck.supervisorName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">责任单位：</span>
                        <span class="col-sm-8">
                            ${sampleCheck.supplier}
                        </span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">整改人：</span>
                        <span class="col-sm-8">
                            ${sampleCheck.assignName}
                        </span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">完成期限：</span>
                        <span class="col-sm-8">
                            ${sampleCheck.rectificationPeriod}
                            <%--<fmt:formatDate type="date" value="${keyProcesses.completeOn}" dateStyle="default"--%>
                                            <%--pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                        </span>
                    </div>
                    <c:if test="${sampleCheck.state eq '非正常关闭'}">
                        <div class="repair_per col-sm-4">
                            <span class="key1">关单时间：</span>
                            <span class="value_">${sampleCheck.shutDownOn}</span>
                        </div>

                        <div class="repair_per col-sm-4">
                            <span class="key1">关单人：</span>
                            <span class="value_">${sampleCheck.shutDownBy}</span>
                        </div>
                        <div class="repair_per col-sm-12">
                            <span class="key1">关单描述：</span>
                            <span class="value_">${sampleCheck.shutDown}</span>
                        </div>
                    </c:if>
                    <div class="repair_per col-sm-8">
                        <span class="col-sm-2" style="text-align: right;">抄送人：</span>
                        <%--<span class="value_" id="copyName"></span>--%>
                        <div class="col-sm-10">
                            <c:forEach items="${sampleCheck.copyList}" var="item" varStatus="index">
                            <span class="value_">
                            ${item.name}
                                <c:if test="${index.last==false}">
                                    ,
                                </c:if>
                            </span>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <ul class="gallery">
                    <div class="col-lg-12" style="border-top: 1px dashed #ccc;margin: 30px 0;padding-left: 0;">
                        <c:forEach items="${sampleCheck.sampleCheckDetails}" var="item" varStatus="status">
                            <div class="col-lg-12">
                                <div class="repair_per col-sm-6 targetHeader" style="margin-left: -15px">
                                    <span class="key1 span" style="margin-top: 10px;"><b
                                            style="font-size: 16px;">检查指标</b></span>
                                </div>
                                <c:choose>
                                    <c:when test="${item.targetDTOByPartyBAnnalList.size() > 0 || item.targetDTOBySupervisionAnnalList.size() > 0 || item.targetDTOByPartyAAnnalList.size() > 0}">
                                        <div class="repair_per col-sm-6">
                                            <p class="details" id="${status.index+1}"
                                               onclick="showDiv('targetDes${status.index+1}')" style="font-size: 16px;">
                                                详情</p>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="details none" id="${status.index+1}" onclick=""
                                           style="font-size: 16px;">详情</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-lg-12">
                                <div class="team-member ">
                                    <div style="margin-left: -107px">
                                        <h4 class="keyPress">${item.targetName}</h4>
                                        <input id="des${status.index+1}" type="hidden"
                                               value="${item.guide}">

                                        <p class="pull-right" id="${status.index+1}"
                                           style="cursor: pointer;color: #0044cc;margin-top: -40px;margin-right: 15px;"
                                           onclick="javascript:getDetail('${item.targetName}','des${status.index+1}');"
                                           data-toggle="modal">指引</p>
                                        <c:if test="${item.imageList != null && fn:length(item.imageList)>0}">
                                            <div class="keyPressTarget">
                                                <h4>合格</h4>
                                                <ul>
                                                    <c:forEach items="${item.imageList}" var="imgitem"
                                                               varStatus="imgstatus">
                                                        <c:choose>
                                                            <c:when test="${imgitem.imageUrl != null && imgitem.imageUrl !=''}">

                                                                <li class="">
                                                                    <a href="${imgitem.imageUrl}">
                                                            <span class="imgSpan"
                                                                  style="background:url(${imgitem.imageUrl}) no-repeat center center;background-size:cover;"></span>
                                                                    </a>
                                                                </li>

                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="imgSpan"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </c:if>
                                        <c:if test="${item.image2List != null && fn:length(item.image2List)>0}">
                                            <div class="keyPressTarget2">
                                                <h4>不合格</h4>
                                                <ul>
                                                    <c:forEach items="${item.image2List}" var="img2item"
                                                               varStatus="imgstatus">
                                                        <c:choose>
                                                            <c:when test="${img2item.imageUrl != null && img2item.imageUrl !=''}">
                                                                <li>
                                                                    <a href="${img2item.imageUrl}">
                                                            <span class="imgSpan"
                                                                  style="background:url(${img2item.imageUrl}) no-repeat center center;background-size:cover;"></span>
                                                                    </a>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="imgSpan"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="keypressDesc">
                                        <div class="keyPressTarget2">
                                            <h4>描述</h4>
                                            <p>${item.description}</p>
                                        </div>
                                        <div class="keyPressTarget2">
                                            <h4 style="margin-top: -30px;">状态</h4>
                                            <p style="margin-top: -20px;">${item.state}</p>
                                        </div>
                                            <%--<p class="text-muted"--%>
                                            <%--style="border-bottom: 1px solid #e5e5e5;">${item.state}</p>--%>

                                            <%--<p>${item.description}</p>--%>
                                    </div>
                                </div>
                            </div>
                            <%--整改记录开始--%>
                            <div id="targetDes${status.index+1}" class="col-sm-12 annal none"
                                 style="margin-bottom: 15px">
                                <button type="button" class="close" data-dismiss="modal"
                                        onclick="showDiv('targetDes${status.index+1}')">
                                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <div class="col-sm-12 annalDes">
                                    <c:forEach items="${item.targetDTOByPartyBAnnalList}" var="partyBAnnalItem"
                                               varStatus="partyBAnnalState">
                                        <div class="col-lg-12">
                                            <div class="repair_per col-sm-6 header">
                                                <span class="key1 span"><b>第${partyBAnnalItem.serialNumber}次整改-乙方：</b></span>
                                            </div>
                                        </div>
                                        <div class="team-member">
                                            <div style="margin-left: -107px">
                                                <c:if test="${partyBAnnalItem.imageList != null && fn:length(partyBAnnalItem.imageList)>0}">
                                                    <div class="col-lg-6">
                                                        <h4 style="text-align: center">合格</h4>
                                                        <c:forEach items="${partyBAnnalItem.imageList}"
                                                                   var="partyBimgitem"
                                                                   varStatus="imgstatus">
                                                            <c:choose>
                                                                <c:when test="${partyBimgitem.imageUrl != null && partyBimgitem.imageUrl !=''}">
                                                                    <li class="col-lg-3">
                                                                        <a href="${partyBimgitem.imageUrl}">
                                                                            <span class="imgSpan"
                                                                                  style="background:url(${partyBimgitem.imageUrl}) no-repeat center center;background-size:cover;"></span>
                                                                        </a>
                                                                    </li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="imgSpan"></span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </c:if>
                                                <c:if test="${partyBAnnalItem.image2List != null && fn:length(partyBAnnalItem.image2List)>0}">
                                                    <div class="col-lg-6">
                                                        <h4 style="text-align: center">不合格</h4>
                                                        <c:forEach items="${partyBAnnalItem.image2List}"
                                                                   var="partyBimgitem2"
                                                                   varStatus="imgstatus">
                                                            <c:choose>
                                                                <c:when test="${partyBimgitem2.imageUrl != null && partyBimgitem2.imageUrl !=''}">
                                                                    <li class="col-lg-3">
                                                                        <a href="${partyBimgitem2.imageUrl}">
                                                                            <span class="imgSpan"
                                                                                  style="background:url(${partyBimgitem2.imageUrl}) no-repeat center center;background-size:cover;"></span>
                                                                        </a>
                                                                    </li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="imgSpan"></span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </c:if>
                                                <div class="col-lg-12 des" style="width:100%;">
                                                    <p>${partyBAnnalItem.description}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="col-sm-12 annalDes">
                                    <c:forEach items="${item.targetDTOBySupervisionAnnalList}"
                                               var="supervisionAnnalItem"
                                               varStatus="supervisionAnnalState">
                                        <div class="col-lg-12">
                                            <div class="repair_per col-sm-6 header">
                                                <span class="key1 span"><b>第${supervisionAnnalItem.serialNumber}次审核-监理：</b></span>
                                            </div>
                                        </div>
                                        <div class="team-member">
                                            <div style="margin-left: -107px">
                                                <c:if test="${supervisionAnnalItem.imageList != null && fn:length(supervisionAnnalItem.imageList)>0}">
                                                    <div class="col-lg-6">
                                                        <h4 style="text-align: center">合格</h4>
                                                        <c:forEach items="${supervisionAnnalItem.imageList}"
                                                                   var="supervisionimgitem"
                                                                   varStatus="imgstatus">
                                                            <c:choose>
                                                                <c:when test="${supervisionimgitem.imageUrl != null && supervisionimgitem.imageUrl !=''}">
                                                                    <li class="col-lg-3">
                                                                        <a href="${supervisionimgitem.imageUrl}">
                                                                            <span class="imgSpan"
                                                                                  style="background:url(${supervisionimgitem.imageUrl}) no-repeat center center;background-size:cover;"></span>
                                                                        </a>
                                                                    </li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="imgSpan"></span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </c:if>
                                                <c:if test="${supervisionAnnalItem.image2List != null && fn:length(supervisionAnnalItem.image2List)>0}">
                                                    <div class="col-lg-6">
                                                        <h4 style="text-align: center">不合格</h4>
                                                        <c:forEach items="${supervisionAnnalItem.image2List}"
                                                                   var="supervisionimgitem2"
                                                                   varStatus="imgstatus">
                                                            <c:choose>
                                                                <c:when test="${supervisionimgitem2.imageUrl != null && supervisionimgitem2.imageUrl !=''}">
                                                                    <li class="col-lg-3">
                                                                        <a href="${imgitem.imageUrl}">
                                                                            <span class="imgSpan"
                                                                                  style="background:url(${supervisionimgitem2.imageUrl}) no-repeat center center;background-size:cover;"></span>
                                                                        </a>
                                                                    </li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="imgSpan"></span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </c:if>
                                                <div class="col-lg-12 des" style="width:100%;">
                                                    <p>${supervisionAnnalItem.description}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="col-sm-12 annalDes">
                                    <c:forEach items="${item.targetDTOByPartyAAnnalList}" var="partyAAnnalItem"
                                               varStatus="partyAAnnalState">
                                        <div class="col-lg-12">
                                            <div class="repair_per col-sm-6 header">
                                                <span class="key1 span"><b>第${partyAAnnalItem.serialNumber}次审核-甲方：</b></span>
                                            </div>
                                        </div>
                                        <div class="team-member">
                                            <div style="margin-left: -107px">
                                                <c:if test="${partyAAnnalItem.imageList != null && fn:length(partyAAnnalItem.imageList)>0}">
                                                    <div class="col-lg-6">
                                                        <h4 style="text-align: center">合格</h4>
                                                        <c:forEach items="${partyAAnnalItem.imageList}"
                                                                   var="partyAimgitem" varStatus="imgstatus">
                                                            <c:choose>
                                                                <c:when test="${partyAimgitem.imageUrl != null && partyAimgitem.imageUrl !=''}">
                                                                    <li class="col-lg-3">
                                                                        <a href="${partyAimgitem.imageUrl}">
                                                                            <span class="imgSpan"
                                                                                  style="background:url(${partyAimgitem.imageUrl}) no-repeat center center;background-size:cover;"></span>
                                                                        </a>
                                                                    </li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="imgSpan"></span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </c:if>
                                                <c:if test="${partyAAnnalItem.image2List != null && fn:length(partyAAnnalItem.image2List)>0}">
                                                    <div class="col-lg-6">
                                                        <h4 style="text-align: center">不合格</h4>
                                                        <c:forEach items="${partyAAnnalItem.image2List}"
                                                                   var="partyAimgitem" varStatus="imgstatus">
                                                            <c:choose>
                                                                <c:when test="${partyAimgitem.imageUrl != null && partyAimgitem.imageUrl !=''}">
                                                                    <li class="col-lg-3">
                                                                        <a href="${partyAimgitem.imageUrl}">
                                                                            <span class="imgSpan"
                                                                                  style="background:url(${partyAimgitem.imageUrl}) no-repeat center center;background-size:cover;"></span>
                                                                        </a>
                                                                    </li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="imgSpan"></span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </c:if>
                                                <div class="col-lg-12 des" style="width:100%;">
                                                    <p>${partyAAnnalItem.description}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </ul>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
    <%--指引模态框--%>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="top: 70px">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                </div>
                <div class="modal-body" id="mo-content">

                </div>
            </div>
        </div>
    </div>
    <!-- 非正常关闭模态框（Modal） -->
    <div class="modal fade " id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel1">
                        <spring:message code="problem.forceClosese"/></h4>
                </div>
                <div class="modal-body">
                    <table>
                        <tr>
                            <td>
                                <label class="" for="closes">
                                    <spring:message code="problem.closess"/> ：
                                </label>
                            </td>
                            <td style="width: 88%;">
                                <input type="text" class="form-control" placeholder="" id="closes" name="closes"
                                       value="">
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <%-- 取消 --%>
                    <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message
                            code="project.cancel"/></button>
                    <%-- 确定 --%>
                    <button type="button" class="btn btn-primary"
                            onclick="closeQuestion('${sampleCheck.sampleCheckId}')">
                        <spring:message code="project.confirm"/></button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../../../static/js/zoom.min.js"></script>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script type="text/javascript" src="../../../../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../../../../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script>
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
    function exportPPT() {
        var href = "../SampleCheckAdmin/exportPPT?sampleCheckId=${sampleCheck.sampleCheckId}";
        window.location.href = href;
    }

    function getDetail(titel, tid) {
        var desc = $('#' + tid).val();
        $("#myModalLabel").html(titel);
        $("#mo-content").html(desc);
        $("#myModal").modal({
            backdrop: false,
            show: true
        });
    }
    function closeQuestionModel() {
//        $("#myModalLabel").html(titel);
//        $("#mo-content").html(description);
        $("#myModal1").modal({
            backdrop: false,
            show: true
        });
    }
    function closeQuestion(sampleCheckId) {
        var closes = $("#closes").val();
        if (closes != null && closes != "") {
            window.location.href = "../SampleCheckAdmin/executeAbnormalOffState?sampleCheckId=" + sampleCheckId + "&shutDown=" + closes;
        } else {
            alert("关单原因不能为空！");
            return false;
        }
    }
    function showDiv(targetid) {
        var target = document.getElementById(targetid);
        if (target.style.display == "block") {
            target.style.display = "none";
        } else {
            target.style.display = "block";
        }
    }
</script>
</body>
</html>