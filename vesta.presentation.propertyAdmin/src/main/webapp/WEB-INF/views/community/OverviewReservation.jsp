<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="../static/property/js/propertyHousPay.js"></script>
</head>
<style type="text/css">
    .search_button{
        text-align: center;
    }
    .form_b{
        height: 10.5rem;
    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#005300030000").addClass("active");
        $("#005300030000").parent().parent().addClass("in");
        $("#005300030000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005300030000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../overview/overviewReservation.html">
                    <input type="hidden" id="menuId" name="menuId" value="005300030000"/>
                    <input type="hidden" id="id" name="id" value="${communityOverviewDto.id}"/>
                    <%-- 楼盘名称 --%>
                    <div class="form-group  col-xs-4">
                        <label for="overviewName" class="col-sm-4 control-label">楼盘名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="overviewName" name="overviewName" value="${communityOverviewDto.overviewName}">
                        </div>
                    </div>
                    <%-- 预约人姓名 --%>
                    <div class="form-group  col-xs-4">
                        <label for="reservationPer" class="col-sm-4 control-label">预约人姓名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="reservationPer" name="reservationPer" value="${communityOverviewDto.reservationPer}">
                        </div>
                    </div>
                    <%-- 联系方式 --%>
                    <div class="form-group  col-xs-4">
                        <label for="reservationTel" class="col-sm-4 control-label">联系方式</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="reservationTel" name="reservationTel" value="${communityOverviewDto.reservationTel}">
                        </div>
                    </div>

                    <div class="col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                    </div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                <th>项目名称</th>
                <th>预约人</th>
                <th>联系方式</th>
                <th>预约日期</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${overviewReservationList}" var="overviewReservation" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${overviewReservation.overviewName}</td>
                    <td>${overviewReservation.reservationPer}</td>
                    <td>${overviewReservation.reservationTel}</td>
                    <td>${overviewReservation.reservationDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/overview/overviewReservation.html?menuId=005300030000&pageIndex={0}&id=${communityOverviewDto.id}&overviewName=${communityOverviewDto.overviewName}&reservationPer=${communityOverviewDto.reservationPer}&reservationTel=${communityOverviewDto.reservationTel}"/>
    </div>

</div>
</div>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>