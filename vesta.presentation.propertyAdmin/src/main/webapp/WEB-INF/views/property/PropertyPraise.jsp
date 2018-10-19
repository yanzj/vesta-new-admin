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
    <script src="../static/js/bootstrap.min.js"></script>
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

    <script type="text/javascript">
        <!--  -->
        <!-- 当前页面 -->
        var pages = "${webPage.pageIndex}";
        <!-- 项目 -->
        var projectSel = "${rental.project}";

        $(document).ready(function() {
            if("" != "${praise.projectIds}"){
                $("#projectIds").val("${praise.projectIds}");
            }
            if("" != "${praise.reply}"){
                $("#reply").val("${praise.reply}");
            }
        });

    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020009" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../property/Praise.html">

                    <div class="form-group  col-lg-4">
                        <label for="projectIds" class="col-sm-4 control-label"><spring:message code="propertyConsult.projectId" /></label>
                        <div class="col-sm-8">
                            <select name="projectIds" class="form-control" id="projectIds" >
                                <c:forEach items="${projectSelDTOs}" var="project" varStatus="row">
                                    <option value="${project.projectId}" id="project${row.index}" <c:if test="${praise.projectIds==project.projectId}">selected </c:if>>${project.projectName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group  col-lg-4">
                        <label for="reply" class="col-sm-4 control-label"><spring:message code="propertyConsult.replyStatus"/></label>
                        <想·div class="col-sm-8">
                            <select id="reply" name="reply"  class="form-control">
                                <option value="0" id="reply0">----------请选择状态----------</option>
                                <option value="1" id="reply1"><spring:message code="propertyConsult.yesReply"/></option>
                                <option value="2" id="reply2"><spring:message code="propertyConsult.noReply"/></option>
                            </select>
                        </想div>
                    </div>

                    <div class="form-group  col-lg-4">
                        <label for="content" class="col-sm-4 control-label"><spring:message code="praise.substance"/></label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" value="${praise.content}" id="content" name="content">
                        </div>
                    </div>


                    <div class="form-group  col-lg-6" >
                        <label for="beginTime" class="col-sm-3 control-label"><spring:message code="praise.crtTime"/></label>
                        <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="beginTime" value="${praise.beginTime}" name="beginTime" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        </div>
                    <div class="form-group  col-lg-6" >
                    <%--</div>--%>

                    <%--<div class="form-group col-lg-4">--%>
                        <label for="endTime" class="col-sm-3 control-label"><spring:message code="praise.reive" /></label>
                        <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="endTime" value="${praise.endTime}" name="endTime" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="propertyConsult.search"/></button>

                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <th><spring:message code="propertyAnnouncement.serialNumber" /></th>
                <th><spring:message code="propertyConsult.projectId" /></th>
                <th><spring:message code="praise.crtTime" /></th>
                <th><spring:message code="propertyConsult.owner" /></th>
                <th><spring:message code="propertyConsult.mobile" /></th>
                <th><spring:message code="propertyConsult.address" /></th>
                <th><spring:message code="praise.substance" /></th>
                <th><spring:message code="propertyConsult.replyStatus" /></th>
                <th><spring:message code="propertyConsult.replyTime" /></th>
                <th><spring:message code="propertyServices.serviceOperation" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="praise" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${praise.projectName}</td>
                    <td>${praise.crtTime}</td>
                    <td>${praise.userName}</td>
                    <td>${praise.mobile}</td>
                    <td>${praise.address}</td>
                    <td>
                        <c:choose>
                            <c:when test="${fn:length(praise.content) > 15}">
                                <c:out value="${fn:substring(praise.content, 0, 15)}......" />
                            </c:when>
                            <c:otherwise>
                                <c:out value="${praise.content}" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${praise.reply == '1'}"><spring:message code="propertyConsult.yesReply"/></c:if>
                        <c:if test="${praise.reply == '2'}"><spring:message code="propertyConsult.noReply"/></c:if>
                    </td>
                    <td>${praise.answerTime}</td>
                    <td>
                        <c:if test="${praise.reply == '1'}">
                            <a href="../property/PraiseAnswer.html?praiseId=${praise.id}"><spring:message code="praise.review" /></a>
                        </c:if>
                        <c:if test="${praise.reply == '2'}">
                            <a href="../property/PraiseAnswer.html?praiseId=${praise.id}"><spring:message code="propertyConsult.reply" /></a>
                        </c:if>
                        <a style="cursor: pointer" onclick="javascript:if (confirm('确定删除吗？'))location.href='../property/delPraise.html?praiseId=${praise.id}';else return;" ><spring:message code="propertyConsult.delete" /></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/property/Praise.html?pageIndex={0}&reply=${praise.reply}&beginTime=${praise.beginTime}&endTime=${praise.endTime}&content=${praise.content}"/>
    </div>


</div>
</div>
</div>
</div>

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

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>