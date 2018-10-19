<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/10/25
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        new WOW().init();
        $(function(){
            $("#003100010000").addClass("active");
            $("#003100010000").parent().parent().addClass("in");
            $("#003100010000").parent().parent().parent().parent().addClass("active");
        })
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="003100010000" username="${authPropertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal">
                    <a href="../BaseData/gotoAddTenders.html?projectId=${projectId}" type="button"
                       class="btn btn-primary" for="propertyAdd">新建标段</a>
                    <a type="button" class="btn btn-primary" href="../BaseData/projectManage.html">返回</a>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="mall.serialNumber"/></td>
                <th>标段名称</th>
                <th>所属项目</th>
                <th>总包</th>
                <th>最后修改时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tendersList}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index+1}</b></td>
                    <td>${list.tenderName}</td>
                    <td>${list.projectName}</td>
                    <td>${list.supplierName}</td>
                    <td>${list.modifyOn}</td>
                    <td>
                        <a href="../BaseData/gotoUpdateTender?tenderId=${list.tenderId}">修改</a>
                        <a href="../BaseData/tenderCategorySetting?tenderId=${list.tenderId}&projectId=${projectId}">可选检查项设置</a>
                        <a onClick="javascript:if (confirm('确定删除吗？')) window.location.href='../BaseData/deleteTender.html?tenderId=${list.tenderId}&projectId=${projectId}';else return;"
                           style="cursor: pointer"><spring:message code="common_delete"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../BaseData/tendersManage.html?projectId=${projectId}&pageIndex={0}"/>
    </div>


</div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>

</body>
</html>
