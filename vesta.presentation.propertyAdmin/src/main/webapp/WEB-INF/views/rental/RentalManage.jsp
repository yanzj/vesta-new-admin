<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/2/25
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <script type="text/javascript">
            $(document).ready(function() {
                if("" != "${rental.project}"){
                    $("#projectId").val("${rental.project}");
                }
            });

        </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005400020000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <div class="form-horizontal" action="../rental/rentalList.html">

                    <div class="form-group  col-lg-4">
                        <label for="projectId" class="col-sm-4 control-label"><spring:message code="propertyConsult.projectId" /></label>

                        <div class="col-sm-8">
                            <select name="project" class="form-control" id="projectId" >
                                <c:forEach items="${projectSelDTOs}" var="project" varStatus="row">
                                    <option value="${project.projectId}" id="project${row.index}" <c:if test="${rental.project==project.projectId}">selected </c:if>>${project.projectName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group  col-lg-4">
                        <label for="userName" class="col-sm-4 control-label"><spring:message code="rental.owerName"/></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" value="${rental.userName}" id="userName" name="userName">
                        </div>
                    </div>

                    <div class="form-group  col-lg-4">
                        <label for="mobile" class="col-sm-5 control-label"><spring:message code="rental.userMobile"/></label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="" value="${rental.mobile}" id="mobile" name="mobile">
                        </div>
                    </div>

                    <div class="form-group  col-lg-6">
                        <label for="beginTime" class="col-sm-3 control-label"><spring:message code="rental.timeview"/></label>

                        <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="beginTime" value="${rental.beginTime}" name="beginTime" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>

</div>
                <div class="form-group  col-lg-6">
                    <%--</div>--%>
                    <%--<div class="form-group  col-lg-5">--%>
                        <label for="endTime" class="col-sm-3 control-label"><spring:message code="rental.tofor"/></label>

                        <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="endTime" value="${rental.endTime}" name="endTime" readonly>
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
                <td><spring:message code="common_sort" /></td>
                <th><spring:message code="rental.projectfor" /></th>
                <th><spring:message code="rental.owerName" /></th>
                <th><spring:message code="rental.mobilephone" /></th>
                <th><spring:message code="rental.buildingNum" /></th>
                <th><spring:message code="rental.unitNum" /></th>
                <th><spring:message code="rental.roomNum" /></th>
                <th><spring:message code="rental.houseType" /></th>
                <th><spring:message code="rental.rentalMoney" /></th>
                <th><spring:message code="rental.rentalDesc" /></th>
                <th><spring:message code="rental.timeview" /></th>
                <th width="174"><spring:message code="common_operate" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="rentalList" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${rentalList.project}</td>
                    <td>${rentalList.userName}</td>
                    <td>${rentalList.mobile}</td>
                    <td>${rentalList.buildingNum}</td>
                    <td>${rentalList.unitNum}</td>
                    <td>${rentalList.roomNum}</td>
                    <td>${rentalList.houseType}</td>
                    <td>
                        <c:choose>
                            <c:when test="${empty rentalList.maxMoney}">
                                ${rentalList.minMoney}
                            </c:when>
                            <c:otherwise>
                                <label>${rentalList.minMoney}-${rentalList.maxMoney}</label>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${fn:length(rentalList.rentalDesc) > 15}">
                                <c:out value="${fn:substring(rentalList.rentalDesc, 0, 15)}......" />
                            </c:when>
                            <c:otherwise>
                                <c:out value="${rentalList.rentalDesc}" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${rentalList.crtTime}</td>
                    <td>
                        <a href="../rental/rentalDetail.html?rentalId=${rentalList.rentalId}"><spring:message code="common_view" /></a>
                        <a style="cursor: pointer" onclick="javascript:if(confirm('确定删除吗！'))location.href='../rental/deleteRental.html?rentalId=${rentalList.rentalId}&type=1';else return"><spring:message code="common_delete" /></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/rental/rentalList.html?pageIndex={0}" />
    </div>


</div>
</div>
</div>
</div>
<script type="text/javascript">
    <!--  -->
    <!-- 当前页面 -->
    var pages = "${webPage.pageIndex}";
    <!-- 项目 -->
    var projectSel = "${rental.project}";

</script>
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