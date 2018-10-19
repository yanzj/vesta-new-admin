<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <script src="../static/property/js/propertyServices.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020002" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form:form class="form-horizontal" action="../property/PropertyServices.html" modelAttribute="propertyServices">

          <div class="form-group  col-lg-3">
            <label for="serviceType" class="col-sm-3 control-label"><spring:message code="propertyServices.serviceType" /></label>

            <div class="col-sm-9">
              <form:select path="serviceType" items="${statusMaps.statusMap}"
                           class="form-control" >
              </form:select>
            </div>
          </div>
          <div class="form-group col-lg-3">
            <label for="serviceName" class="col-sm-3 control-label"><spring:message code="propertyServices.serviceName" /></label>

            <div class="col-sm-9">
              <form:input type="text" class="form-control" placeholder="" value="${propertyServices.serviceName}"  path="serviceName"/>
            </div>
          </div>
          <div class="form-group  col-lg-3">
            <label for="servicesPhone" class="col-sm-3 control-label"><spring:message code="propertyServices.servicesPhone" /></label>

            <div class="col-sm-9">
              <form:input type="text" class="form-control" placeholder="" value="${propertyServices.servicesPhone}"  path="servicesPhone"/>
            </div>
          </div>

          <button type="submit" class="btn btn-primary" for="serviceSearch"><spring:message code="propertyServices.serviceSearch" /></button>
          <a  href="../property/NewPropertyServices.html?servicesId=" class="btn btn-primary" for="serviceAdd"><spring:message code="propertyServices.serviceAdd" /></a>


          <%--<form:input type="hidden" club_path="number" />--%>
        </form:form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="propertyServices.serviceNumber" /></td>
        <th><spring:message code="propertyServices.serviceName" /></th>
        <th><spring:message code="propertyServices.servicesPhone" /></th>
        <th><spring:message code="propertyServices.serviceMessageTime" /></th>
        <th><spring:message code="propertyServices.serviceType" /></th>
        <th><spring:message code="propertyServices.serviceOperation" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${servicesMessage}" var="service" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
          <td>${service.serviceName}</td>
          <td>${service.servicesPhone}</td>
          <td>${service.addMessageTime}</td>
          <td>${service.serviceType}</td>
          <td class="last">
            <a href="../property/NewPropertyServices.html?servicesId=${service.servicesId}" class="a2" ><spring:message code="propertyServices.serviceModify" /></a>
            <a href="javascript:void(0);" onclick="js_delMessage('${service.servicesId}')" id="delProperty" class="a3"><spring:message code="propertyServices.serviceDelete" /></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/property/PropertyServices.html?pageIndex={0}&serviceType=${propertyServices.serviceType}&serviceName=${propertyServices.serviceName}&servicesPhone=${propertyServices.servicesPhone}"/>
  </div>


</div>
</div>
</div>
</div>
<script type="text/javascript">
  var pages = "${webPage.pageIndex}";
  var serviceType  = "${propertyServices.serviceType}";
  var serviceName = "${propertyServices.serviceName}";
  var servicesPhone = "${propertyServices.servicesPhone}";
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>