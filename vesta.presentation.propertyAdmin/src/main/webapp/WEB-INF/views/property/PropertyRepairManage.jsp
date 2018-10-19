<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/2/15
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
  <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="keywords" content=""/>
  <script type="application/x-javascript">
    addEventListener("load",
            function () {
              setTimeout(hideURLbar, 0);
            }, false);
    function hideURLbar() {
      window.scrollTo(0, 1);
    }
  </script>
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
  <script src="../static/property/js/propertyRepairManage.js"></script>
  <style>
    .tableStyle thead td,.tableStyle thead th{
      padding-left: 0;
      text-align: center;
    }
  </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001900020000" username="${propertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <form class="form-horizontal" action="../property/repairManage.html" method="post">
          <div class="form-group  col-lg-4">
            <label for="project" class="col-sm-4 control-label"><spring:message code="workOrders.project" /></label>
            <div class="col-sm-8">
              <input type="text"  class="form-control" placeholder="请输入项目名称" value="${workOrderSearch.project}" id="project" name="project"/>
            </div>
          </div>
          <div class="form-group  col-lg-4">
            <label class="col-sm-4 control-label"><spring:message code="workOrders.buildingNum" /></label>
            <div class="col-sm-8">
              <select id="buildingNum" name="buildingNum" class="form-control">
                <c:forEach items="${typeMaps.buildingNumMap}" var="item">
                  <option value="${item.key }" >${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group  col-lg-4">
            <label for="buildingFloor" class="col-sm-4 control-label"><spring:message code="workOrders.buildingFloor"/></label>
            <div class="col-sm-8">
              <select id="buildingFloor" name="buildingFloor" class="form-control">
                <c:forEach items="${typeMaps.buildingFloorMap}" var="item">
                  <option value="${item.key }" >${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group  col-lg-4">
            <label for="firstType" class="col-sm-4 control-label"><spring:message code="workOrders.firstType"/></label>
            <div class="col-sm-8">
              <select id="firstType" name="firstType" class="form-control">
                <c:forEach items="${typeMaps.firstTypeMap}" var="item">
                  <option value="${item.key }" >${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group  col-lg-4">
            <label for="secondType" class="col-sm-4 control-label"><spring:message code="workOrders.secondType" /></label>
            <div class="col-sm-8">
              <select id="secondType" name="secondType" class="form-control">
                <c:forEach items="${typeMaps.secondTypeMap}" var="item">
                  <option value="${item.key }" >${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group  col-lg-4">
            <label for="type" class="col-sm-4 control-label"><spring:message code="workOrders.orderType"/></label>
            <div class="col-sm-8">
              <select id="type" name="type" class="form-control">
                <c:forEach items="${typeMaps.orderType}" var="item">
                  <option value="${item.key }" >${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group  col-lg-4">
            <label for="state" class="col-sm-4 control-label"><spring:message code="workOrders.orderState"/></label>
            <div class="col-sm-8">
              <select id="state" name="state" class="form-control">
                <c:forEach items="${typeMaps.orderState}" var="item">
                  <option value="${item.key }" >${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="clearfix"></div>
          <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_search"/></button>
        </form>
      </div>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <table width="100%" class="tableStyle table-striped">
      <thead>
      <tr>
        <td style="width:6%;text-align:center"><spring:message code="common_sort" /></td>
        <th><spring:message code="workOrders.Qcontent" /></th>
        <th><spring:message code="workOrders.roomInfo" /></th>
        <th><spring:message code="workOrders.name" /></th>
        <th><spring:message code="workOrders.phone" /></th>
        <th><spring:message code="workOrders.projectName" /></th>
        <th><spring:message code="workOrders.status" /></th>
        <th><spring:message code="common_operate" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${workOrders}" var="workOrder" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
          <td>
            <c:choose>
              <c:when test="${fn:length(workOrder.content) > 20}">
                <c:out value="${fn:substring(workOrder.content, 0, 20)}..." />
              </c:when>
              <c:otherwise>
                <c:out value="${workOrder.content}" />
              </c:otherwise>
            </c:choose>
          </td>
          <td>${workOrder.userAddress}</td>
          <td>${workOrder.userName}</td>
          <td>${workOrder.userPhone}</td>
          <td>${workOrder.project}</td>
          <td>${workOrder.state}</td>
          <td>
            <a href="../property/repairDetail.html?id=${workOrder.id}"><spring:message code="workOrders.detail" /></a>
            <a href="javascript:void(0);" onclick="deleteRepair('${workOrder.id}')" id="deleteOrder" class="a3"><spring:message code="common_delete" /></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
   <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/property/repairManage.html?pageIndex={0}&project=${workOrderSearch.project}&buildingNum=${workOrderSearch.buildingNum}&buildingFloor=${workOrderSearch.buildingFloor}&firstType=${workOrderSearch.firstType}&secondType=${workOrderSearch.secondType}&type=${workOrderSearch.type}&state=${workOrderSearch.state}"/>
  </div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
  var pages = "${webPage.pageIndex}";
  var project = "${workOrderSearch.project}";
  var id = "${workOrderSearch.id}";
  var type = "${workOrderSearch.type}";
  var state = "${workOrderSearch.state}";
  var firstType = "${workOrderSearch.firstType}";
  var secondType = "${workOrderSearch.secondType}";
  var buildingNum = "${workOrderSearch.buildingNum}";
  var buildingFloor = "${workOrderSearch.buildingFloor}";
  var project = "${workOrderSearch.project}";
</script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>