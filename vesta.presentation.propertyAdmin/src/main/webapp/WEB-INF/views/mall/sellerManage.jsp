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

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005800050000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../seller/SellerManage.html">
                    <div class="form-group  col-lg-3">
                        <label for="sellerName" class="col-sm-5 control-label"><spring:message code="mall.sellerName" /></label>
                        <div class="col-sm-7">
                            <input type="text" id="sellerName" name="sellerName" value="${seller.sellerName}" class="form-control">
                        </div>
                    </div>
                    <div class="form-group  col-lg-3">
                        <label for="sellerPeople" class="col-sm-5 control-label"><spring:message code="mall.people" /></label>
                        <div class="col-sm-7">
                            <input type="text" id="sellerPeople" name="sellerDirector" value="${seller.sellerDirector}" class="form-control">
                        </div>
                    </div>
                    <div class="form-group  col-lg-3">
                        <label for="sellerPeopleTel" class="col-sm-5 control-label"><spring:message code="mall.peopleTel" /></label>
                        <div class="col-sm-7">
                            <input type="text" id="sellerPeopleTel" name="sellerDirectorTel" value="${seller.sellerDirectorTel}" class="form-control">
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_search"/></button>
                    <a href="../seller/addSeller.html" type="button" class="btn btn-primary" for="propertyAdd" ><spring:message code="common_add"/></a>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="mall.serialNumber" /></td>
                <th><spring:message code="mall.sellerName" /></th>
                <th><spring:message code="mall.sellerAddress" /></th>
                <th><spring:message code="mall.director" /></th>
                <th><spring:message code="mall.directorTel" /></th>
                <th><spring:message code="mall.goodLevel" /></th>
                <th><spring:message code="mall.badLevel" /></th>
                <th><spring:message code="mall.collect" /></th>
                <th><spring:message code="mall.operate" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sellerList}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index+1}</b></td>
                    <td>${list.sellerName}</td>
                    <td>${list.sellerAddress}</td>
                    <td>${list.sellerDirector}</td>
                    <td>${list.sellerDirectorTel}</td>
                    <td>${list.goodNum}</td>
                    <td>${list.badNum}</td>
                    <td>${list.collect}</td>
                    <td><a href="../seller/AlterSeller.html?sellerId=${list.sellerId}&status=2&pageIndex=${webPage.pageIndex}" style="margin:0;color: #000000"><span class="glyphicon glyphicon-arrow-up" aria-hidden="true" style="color: #008000"></span><spring:message code="common_up" /></a>
                        <a href="../seller/AlterSeller.html?sellerId=${list.sellerId}&status=1&pageIndex=${webPage.pageIndex}" style="margin:0;color: #000000"><span class="glyphicon glyphicon-arrow-down"style="color: #ff0000"></span><spring:message code="common_down" /></a>
                        <a href="../seller/addSeller.html?sellerId=${list.sellerId}" style="margin:0;color: #000000"><span class="glyphicon glyphicon-edit"><spring:message code="common_update" /></span></a>
                        <a onClick="javascript:if (confirm('确定删除吗？')) location.href='../seller/delete.html?sellerId=${list.sellerId}';else return;" style="margin:0;color: #000000;cursor: pointer"><span class="glyphicon glyphicon-remove"><spring:message code="common_delete" /></span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../seller/SellerManage.html?pageIndex={0}"/>
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