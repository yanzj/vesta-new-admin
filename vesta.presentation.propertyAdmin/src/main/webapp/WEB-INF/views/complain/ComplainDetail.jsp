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
            $("#006800010000").addClass("active");
            $("#006800010000").parent().parent().addClass("in");
            $("#006800010000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="006800010000" username="${authPropertystaff.staffName}"/>
    <div class="newDetail__">
        <div class="nav">
            <div class="rightButton">
                <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
            </div>
        </div>
        <div class="notice_inner" style="padding: 0;margin:0;width: auto;">
            <div class="container" style="width: 100%;padding: 0;height: 100%;margin: 0;">
                <ul class="gallery">
                    <h2 class="keyComTitle" style="width: 90%;"><b>投诉单基本信息</b></h2>
                    <div class="col-lg-12" style="padding-left:0;margin-left: -30px;">
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">单号：</span>
                            <span class="col-sm-8">${complainInfo.complainName}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">关联单号：</span>
                            <span class="col-sm-8">${complainInfo.relatedNumber}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">城市：</span>
                            <span class="col-sm-8">${complainInfo.cityName}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">项目：</span>
                            <span class="col-sm-8">${complainInfo.projectName}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">房间地址：</span>
                            <span class="col-sm-8">${complainInfo.houseName}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">房间描述：</span>
                            <span class="col-sm-8">${complainInfo.houseDes}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">投诉人姓名：</span>
                            <span class="col-sm-8">${complainInfo.complaintPersonName}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">投诉人电话：</span>
                            <span class="col-sm-8">${complainInfo.complaintPhone}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">投诉人情绪：</span>
                            <span class="col-sm-8">${complainInfo.emotion}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">投诉级别：</span>
                            <span class="col-sm-8">${complainInfo.complaintLevel}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">投诉分类：</span>
                            <span class="col-sm-8">${complainInfo.classificationComplaints}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">投诉来源：</span>
                            <span class="col-sm-8">${complainInfo.complainCanal}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">投诉渠道：</span>
                            <span class="col-sm-8">
                                ${complainInfo.complaintSource}
                            </span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">单据状态：</span>
                            <span class="col-sm-8">
                                ${complainInfo.documentsState}
                            </span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">创建人：</span>
                            <span class="col-sm-8">
                                ${complainInfo.createByName}
                            </span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">创建时间：</span>
                            <span class="col-sm-8">
                                ${complainInfo.createOn}
                            </span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-4" style="text-align: right;">业主原话：</span>
                            <span class="col-sm-8">
                                ${complainInfo.ownerVersion}
                            </span>
                        </div>
                        <div class="col-lg-12" style="margin-left: 25px">
                            <div class="team-member ">
                                <div style="margin-left: -107px">
                                    <h4 class="keyPress">投诉描述</h4>
                                    <c:if test="${complainInfo.imageList != null && fn:length(complainInfo.imageList)>0}">
                                        <div class="keyPressTarget">
                                            <h4>图片</h4>
                                            <ul>
                                                <c:forEach items="${complainInfo.imageList}" var="imgitem"
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
                                </div>
                                <div class="keypressDesc">
                                    <div class="keyPressTarget2">
                                        <h4>描述</h4>
                                        <p>${complainInfo.complaintsDescribes}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </ul>
            </div>
            <div class="container" style="width: 100%;padding: 0;height: 100%;margin: 0;">
                <ul class="gallery">
                    <h2 class="keyComTitle" style="width: 90%;"><b>投诉单处理信息</b></h2>
                    <div class="col-lg-12" style="padding-left: 0;margin-left: -30px;">
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-5" style="text-align: right;">处理人：</span>
                            <span class="col-sm-7">${complainInfo.disposalName}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-5" style="text-align: right;">超时答复：</span>
                            <span class="col-sm-7">${complainInfo.timeOut}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-5" style="text-align: right;">退回次数：</span>
                            <span class="col-sm-7">${complainInfo.returnTime}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-5" style="text-align: right;">限时答复时间：</span>
                            <span class="col-sm-7">${complainInfo.limitedReplyTime}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-5" style="text-align: right;">初次答复时间：</span>
                            <span class="col-sm-7">${complainInfo.replyTime}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="col-sm-5" style="text-align: right;">最终完成时间：</span>
                            <span class="col-sm-7">${complainInfo.completeTime}</span>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="team-member ">
                            <div style="margin-left: -107px">
                                <h4 class="keyPress">初次处理方案</h4>
                                <c:if test="${complainInfo.receiveImgList != null && fn:length(complainInfo.receiveImgList)>0}">
                                    <div class="keyPressTarget">
                                        <h4>图片</h4>
                                        <ul>
                                            <c:forEach items="${complainInfo.receiveImgList}" var="imgitem"
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
                            </div>
                            <div class="keypressDesc">
                                <div class="keyPressTarget2">
                                    <h4>方案</h4>
                                    <p>${complainInfo.treatmentPlan}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="team-member ">
                            <div style="margin-left: -107px">
                                <h4 class="keyPress">最终处理结果</h4>
                                <c:if test="${complainInfo.completeImgList != null && fn:length(complainInfo.completeImgList)>0}">
                                    <div class="keyPressTarget">
                                        <h4>图片</h4>
                                        <ul>
                                            <c:forEach items="${complainInfo.completeImgList}" var="imgitem"
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
                            </div>
                            <div class="keypressDesc">
                                <div class="keyPressTarget2" style="height: auto;line-height: 15px">
                                    <h4>结果</h4>
                                    <p>${complainInfo.processingResults}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </ul>
            </div>
            <div class="container" style="width: 100%;padding: 0;height: 100%;margin: 0;">
                <div class="col-lg-12" style="padding-left: 0;margin-left: -30px;">
                    <h2 class="keyComTitle" style="width: 90%;margin-left: 30px;"><b>投诉单回访信息</b></h2>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">回访人姓名：</span>
                        <span class="col-sm-8">${complainInfo.revisit}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">回访满意度：</span>
                        <span class="col-sm-8">${complainInfo.visitSatisfaction}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">回访时间：</span>
                        <span class="col-sm-8">${complainInfo.visitDate}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="col-sm-4" style="text-align: right;">回访意见：</span>
                        <span class="col-sm-8">${complainInfo.visitOpinion}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>

</div>
<script src="../../../../static/js/zoom.min.js"></script>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script type="text/javascript" src="../../../../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../../../../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
</body>
</html>