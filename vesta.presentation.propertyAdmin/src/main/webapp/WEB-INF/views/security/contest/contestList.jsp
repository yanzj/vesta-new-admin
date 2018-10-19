<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
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
        $(function(){
            console.log("sqq")
            $("#006400020000").addClass("active");
            $("#006400020000").parent().parent().addClass("in");
            $("#006400020000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>

    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <%--<link rel="stylesheet" href="../static/css/jquery-ui.min.css" />
    <script src="../static/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>--%>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
    <%--图片放大--%>
    <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>

    <style>
        .imgList img {
            width: 90px;
            height: 90px;
            margin-right: 5px;
        }
    </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006400020000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <div class="form-marginL">
                    <form class="form-horizontal" action="../contest/queryContest.html" method="get">
                        <%--项目--%>
                        <div class="form-group  col-lg-4">
                            <label for="projectId" class="col-sm-4 control-label"><spring:message
                                    code="project.projectName"/>:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="projectId" name="projectId">
                                    <c:forEach items="${projects}" var="project">
                                        <option value="${project.key}"
                                                <c:if test="${project.key eq contest.projectId}">selected</c:if>>${project.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                            <%-- 创建人 --%>
                            <div class="form-group  col-lg-4">
                                <label for="createName" class="col-sm-4 control-label">创&nbsp; 建&nbsp; 人:</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" placeholder="" id="createName" name="createName"
                                           value="${contest.createName}">
                                </div>
                            </div>
                        <%-- 关键字 --%>
                        <div class="form-group  col-lg-4">
                            <label for="describe" class="col-sm-4 control-label"><spring:message
                                    code="problem.hiddenTrouble"/>:</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="describe" name="describe"
                                       value="${contest.describe}">
                            </div>
                        </div>
                        <%-- 开始时间--%>
                        <div class="form-group  col-lg-4">
                            <label for="startDate" class="col-sm-4 control-label"><spring:message
                                    code="workOrders.startDate"/>:</label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入开始时间" path="startDate"
                                       id="startDate"
                                       name="startDate" value="${contest.startDate}" readonly="true"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <%-- 结束时间--%>
                        <div class="form-group  col-lg-4">
                            <label for="endDate" class="col-sm-4 control-label"><spring:message
                                    code="workOrders.endDate"/>:</label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入结束时间" path="endDate" id="endDate"
                                       name="endDate" value="${contest.endDate}" readonly="true"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div style="text-align: center; margin-top: 8px;">
                            <button type="button" class="btn btn-primary" onclick="search()" for="propertySearch">
                                <spring:message code="propertyCompany.propertySearch"/></button>
                            <input type="hidden" id="size" value="${isExcel}"/>
                            <a href="#" onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="table-responsive bs-example widget-shadow" style="border-top: 1px dashed #ccc;
    padding-top: 16px;">

            <table width="100%" class="tableStyle" style="table-layout: fixed;">
                <thead>
                <tr>
                    <th width="8%">序号</th>
                    <th width="15%">项目公司</th>
                    <th width="12%">创建人</th>
                    <th width="8%">创建时间</th>
                    <th width="30%">隐患描述</th>
                    <th width="9%">隐患图片</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${contests}" var="contests" varStatus="row">
                    <tr>
                        <td height="40px" style="text-align: center;"><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>

                        <td height="40px">${contests.projectName}</td>
                        <td height="40px">${contests.creatName}</td>
                        <td height="40px">${contests.creatDate}</td>
                        <td height="40px" class="description">${contests.describe}</td>
                        <td height="40px">
                            <c:forEach items="${contests.image}" var="image">
                                <label class="control-label imgList">
                                    <li style="list-style-type:none">
                                        <a href="${image.imageUrl}" class="zoom">
                                            <img src="${image.imageUrl}">
                                        </a>
                                    </li>
                                </label>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <m:pager pageIndex="${webPage.pageIndex}"
                     pageSize="${webPage.pageSize}"
                     recordCount="${webPage.recordCount}"
                     submitUrl="${pageContext.request.contextPath }/contest/queryContest.html?pageIndex={0}&projectId=${contest.projectId}&describe=${contest.describe}&startDate=${contest.startDate}&endDate=${contest.endDate}&createName=${contest.createName}"/>
        </div>
    </div>

</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script src="../../../../static/js/zoom.min.js"></script>
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

    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
            var href = "../contest/contestExportExcel?&projectId=${contest.projectId}&describe=${contest.describe}&startDate=${contest.startDate}&endDate=${contest.endDate}&createName=${contest.createName}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }

</script>
<script>
    function search() {
        document.forms[0].submit();
    }
</script>
<script>
    function getDetail(titel, description) {
        $("#myModalLabel").html(titel);
        $("#mo-content").html(description);
        $("#myModal").modal({
            backdrop: false,
            show: true
        });
    }
</script>
</body>
</html>