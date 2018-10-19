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
  <script src="../static/property/js/propertyAnywhereReport.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020004" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../property/PropertyAnywhereReport.html">

          <div class="form-group  col-lg-6">
            <label for="houseId" class="col-sm-3 control-label"><spring:message code="propertyReport.projectId" /></label>

            <div class="col-sm-9">
              <select name="houseId" class="form-control" id="houseId" >
                <c:forEach items="${projectSelDTOs}" var="project" varStatus="row">
                  <option value="${project.projectId}" id="project${row.index}" <c:if test="${project.projectId eq anywhereReporPage.houseId}">selected="selected"</c:if>>${project.projectName}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="form-group  col-lg-6">
            <label for="userName" class="col-sm-3 control-label"><spring:message code="propertyReport.releasePerson"/></label>

            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" value="${anywhereReporPage.userName}" id="userName" name="userName">
            </div>
          </div>


          <div class="form-group  col-lg-6">
            <label for="makedateStart" class="col-sm-3 control-label"><spring:message code="propertyReport.releaseTimeStart"/></label>

            <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="makedateStart" value="${anywhereReporPage.makedateStart}" name="makedateStart" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>


          </div>
          <div class="form-group  col-lg-6">
            <label for="makedateEnd" class="col-sm-3 control-label"><spring:message code="propertyReport.releaseTimeEnd"/></label>

            <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="makedateEnd" value="${anywhereReporPage.makedateEnd}" name="makedateEnd" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>

          </div>

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
        <td><spring:message code="propertyReport.reportNumber" /></td>
        <td><spring:message code="propertyReport.projectId" /></td>
        <td><spring:message code="propertyReport.releasePerson" /></td>
        <td><spring:message code="propertyReport.reportMobile" /></td>
        <td><spring:message code="propertyReport.reprotType" /></td>
<%--        <td><spring:message code="propertyReport.releaseContent" /></td>
        <td><spring:message code="propertyReport.releasePosition" /></td>--%>
        <td><spring:message code="propertyReport.releaseTime" /></td>
        <td><spring:message code="advertServices.state" /></td>
        <td><spring:message code="propertyReport.releaseImgCont" /></td>
        <th width="174"><spring:message code="propertyReport.releaseOperation" /></th>
      </tr>
      </thead>
      <tbody>
        <c:forEach items="${anywhereRepor}" var="anywhere" varStatus="row">
          <tr>
            <td><b>${row.index + 1}</b></td>
            <td>${anywhere.houseId}</td>
            <td>${anywhere.userName}</td>
            <td>${anywhere.phoneNO}</td>
            <td>${anywhere.type}</td>
            <%--<td>
              <c:choose>
                <c:when test="${fn:length(anywhere.content) > 15}">
                  <c:out value="${fn:substring(anywhere.content, 0, 15)}......" />
                </c:when>
                <c:otherwise>
                  <c:out value="${anywhere.content}" />
                </c:otherwise>
              </c:choose>
            </td>
            <td>${anywhere.repairAddress}</td>--%>
            <td>${anywhere.makedate}</td>
            <td>
              <c:if test="${anywhere.stateType == -1 }"><spring:message code="propertyReport.wait"/></c:if>
              <c:if test="${anywhere.stateType == 0 }"><spring:message code="propertyReport.is"/></c:if>
              <c:if test="${anywhere.stateType == 1 }"><spring:message code="propertyReport.end"/></c:if>
            </td>
            <td>${anywhere.imgCont}</td>
            <td>
             <%-- <c:if test="${anywhere.workType == 1}"><a href="javascript:void(0);" onclick="js_workMessage('${anywhere.anywherePicId}','${anywhere.workType}','2')">转为工单</a></c:if>
              <c:if test="${anywhere.workType == 2}"><a href="javascript:void(0);" onclick="js_workMessage('${anywhere.anywherePicId}','${anywhere.workType}','2')">已转工单</a></c:if>--%>
              <a href="../property/NewPropertyAnywhereReport?anywherePicId=${anywhere.anywherePicId}"><spring:message code="propertyReport.releaseSee"/></a>
              <a href="javascript:void(0);" onclick="js_delMessage('${anywhere.anywherePicId}')" class="a3"><spring:message code="propertyReport.releaseDelete"/></a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/property/PropertyAnywhereReport.html?pageIndex={0}&houseId=${anywhereReporPage.houseId}&makedateStart=${anywhereReporPage.makedateStart}&makedateEnd=${anywhereReporPage.makedateEnd}&userName=${anywhereReporPage.userName}"/>
  </div>


</div>
</div>
</div>
</div>
<script type="text/javascript">
  <!-- 当前页面 -->
  var pages = "${webPage.pageIndex}";
  var houseId = "${anywhereReporPage.houseId}";
  var makedateStart = "${anywhereReporPage.makedateStart}";
  var makedateEnd = "${anywhereReporPage.makedateEnd}";
  var userName = "${anywhereReporPage.userName}";
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