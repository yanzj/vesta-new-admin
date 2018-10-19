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
            console.log("sqq")
            $("#005900010000").addClass("active");
            $("#005900010000").parent().parent().addClass("in");
            $("#005900010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../../../../static/js/metisMenu.min.js"></script>
    <script src="../../../../static/js/custom.js"></script>
    <link href="../../../../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <link href="../../../../static/css/target.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005900010000" username="${authPropertystaff.staffName}"/>
    <div class="newDetail__">
        <div class="nav">
            <div class="rightButton">
                <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
                <%--<c:choose>--%>
                    <%--<c:when test="${authority == false || leaderCheck.state eq '非正常关闭' || leaderCheck.state eq '已完成'}">--%>
                        <%--<button class="btn btn-primary" style="display: none">非正常关闭--%>
                        <%--</button>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<button data-toggle="modal1" class="btn btn-primary" onclick="closeQuestionModel()">非正常关闭--%>
                        <%--</button>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
            </div>
        </div>
        <div class="notice_head" style="height: 100px;width: 90%;border-bottom: 1px dashed #ccc">
            <h2 style="font-size: 18px; text-align: left;height: 40px;font-weight: bold;">${leaderCheck.title}</h2>
            <span class="notice_time">创建时间：${leaderCheck.createDate}</span>
            <span class="ques_sta">状态：${leaderCheck.state}</span>
            <span class="key1 notice_time " style="width: 290px;margin-left: 60px;">检查人：${leaderCheck.createName}</span>
        </div>

        <%--<c:if test="${leaderCheck.state eq '非正常关闭'}">--%>
            <%--<div class="border_" style="border: none;"></div>--%>
            <%--<div class="notice_inner" style="width: auto;border-bottom: 1px dashed #ccc">--%>
                <%--<div class="room">--%>
                    <%--<span class="key1">关单时间：</span>--%>
                    <%--<span class="value_">${leaderCheck.shutDownOn}</span>--%>
                <%--</div>--%>

                <%--<div class="room">--%>
                    <%--<span class="key1">关单人：</span>--%>
                    <%--<span class="value_">${leaderCheck.shutDownBy}</span>--%>
                <%--</div>--%>
                <%--<div class="room">--%>
                    <%--<span class="key1">关单描述：</span>--%>
                    <%--<span class="value_">${leaderCheck.shutDown}</span>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</c:if>--%>
        <div class="border_" style="border: none;"></div>
        <div class="notice_inner" style="padding-bottom: 40px;width: auto;margin-left: -16px;">
            <div class="room">
                <span class="key1">项目名称：</span>
                <span class="value_">${leaderCheck.projectName}</span>
            </div>
            <div class="room">
                <span class="key1">整改人：</span>
                <span class="value_">${leaderCheck.assignName}</span>
            </div>

            <c:if test="${leaderCheck.leaderList != null && fn:length(leaderCheck.leaderList)>0}">
                <c:forEach items="${leaderCheck.leaderList}" var="item" varStatus="status">
                    <c:if test="${fn:length(item.description) >= 20}">
                        <div class="description" style="height: 55px;">
                            <span class="key1">问题描述：</span>

                            <div class="col-sm-8">
                                    <textarea class="form-control"
                                              disabled="disabled" style="resize: none;">${item.description}</textarea>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${fn:length(item.description) < 20}">
                        <div class="description">
                            <span class="key1">问题描述：</span>
                            <span class="value_">${item.description}</span>
                        </div>
                    </c:if>
                    <div class="mun_pic">
                        <ul class="gallery">
                            <span class="key1">图片：</span>
                            <li>
                                <c:if test="${fn:length(item.imageList)==0}">
                                    <label class="control-label imgList"><img
                                            src="../../../../static/images/logo.png"
                                            style="width: 90px;height: 60px;margin-right: 4px;"></label>
                                </c:if>
                                <c:forEach items="${item.imageList}" var="image">
                                    <a href="${image.imageUrl}">
                                        <label class="control-label imgList"><img src="${image.imageUrl}"
                                                                                  style="width: 90px;height: 60px;margin-right: 4px;"></label>
                                    </a>
                                </c:forEach>
                            </li>
                        </ul>
                    </div>
                </c:forEach>
            </c:if>

            <div class="borderDay"></div>
            <%--整改记录开始--%>
            <c:if test="${leaderCheck.managerList != null && fn:length(leaderCheck.managerList)>0}">
                <c:forEach items="${leaderCheck.managerList}" var="managerList" varStatus="status">
                    <div class="room">
                        <span class="key1">记录：</span>
                        <span class="value_">整改</span>
                    </div>
                    <div class="room">
                        <span class="key1">创建时间：</span>
                        <span class="value_">${managerList.createOn}</span>
                    </div>
                    <c:if test="${fn:length(managerList.description) >= 20}">
                        <div class="description" style="height: 55px;">
                            <span class="key1">问题描述：</span>

                            <div class="col-sm-8">
                                    <textarea class="form-control"
                                              disabled="disabled"
                                              style="resize: none;">${managerList.description}</textarea>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${fn:length(managerList.description) < 20}">
                        <div class="description">
                            <span class="key1">问题描述：</span>
                            <span class="value_">${managerList.description}</span>
                        </div>
                    </c:if>
                    <div class="mun_pic">
                        <ul class="gallery">
                            <span class="key1">图片：</span>
                            <li>
                                <c:if test="${fn:length(managerList.imageList)==0}">
                                    <label class="control-label imgList"><img
                                            src="../../../../static/images/logo.png"
                                            style="width: 90px;height: 60px;margin-right: 4px;"></label>
                                </c:if>
                                <c:forEach items="${managerList.imageList}" var="image">
                                    <a href="${image.imageUrl}">
                                        <label class="control-label imgList"><img src="${image.imageUrl}"
                                                                                  style="width: 90px;height: 60px;margin-right: 4px;"></label>
                                    </a>
                                </c:forEach>
                            </li>
                        </ul>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
    <div class="clearfix"></div>
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
                        onclick="closeQuestion('${leaderCheck.checkId}')">
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
<!-- main content end-->
<%--<%@ include file="../../main/foolter.jsp" %>--%>
<!-- 校验 -->

<script>
    function closeQuestionModel() {
        $("#myModal1").modal({
            backdrop: false,
            show: true
        });
    }
    function closeQuestion(checkId) {
        var closes = $("#closes").val();
        if (closes != null && closes != "") {
            window.location.href = "../projectLeaderCheck/executeAbnormalOffState?checkId=" + checkId + "&shutDown=" + closes;
        } else {
            alert("关单原因不能为空！");
            return false;
        }
    }
</script>
</body>
</html>