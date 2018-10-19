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
  <script src="../static/property/js/checkNullAllJsp.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
  <script src="../static/property/js/newPropertyServices.js"></script>
</head>
<script>
  function js_validation(){
    if(!CheckNull($("#serviceName").val(),"请填写单位！")){
      return false;
    }
    if("" == $("#serviceName").val()){
      $("#errorMsg").html("请填写单位!");
      return;
    }
    if(!CheckNull($("#servicesPhone").val(),"请填写电话！")){
      return false;
    }
    if("" == $("#servicesPhone").val()){
      $("#errorMsg").html("请填写电话!");
      return;
    }
    if(!CheckNull($("#serviceType").val(),"请选择类型！")){
      return false;
    }
    if("0" == $("#serviceType").val()){
      $("#errorMsg").html("请选择类型!");
      return;
    }
    $("#errorMsg").html("");
    $("#formMessage").submit();
  }
</script>
<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020002" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--服务信息添加开始--%>
      <div class="form-body">
        <h3 class="text-center">新增服务信息 </h3>
        <div class="form-body">
          <form class="form-horizontal" action="../property/addPropertyServices.html" id="formMessage">
            <div class="form-group">
              <label class="col-sm-5 control-label" for="serviceName"><spring:message code="propertyServices.serviceName" /></label>
              <div class="col-sm-3">
                <input type="text" class="form-control" placeholder="" value="${propertyServices.serviceName}" id="serviceName" name="serviceName">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-5 control-label" for="servicesPhone"><spring:message code="propertyServices.servicesPhone" /></label>
              <div class="col-sm-3">
                <input type="text" class="form-control" placeholder="" value="${propertyServices.servicesPhone}" id="servicesPhone" name="servicesPhone">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-5 control-label" for="serviceType"><spring:message code="propertyServices.serviceType" /></label>
              <div class="col-sm-3">
                <select id="serviceType" name="serviceType"  class="form-control">
                  <c:forEach items="${statusMaps.statusMap}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq propertyServices.serviceType}">selected="selected"</c:if> >${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-5 control-label" for="serviceType">&nbsp;</label>
              <div class="col-sm-3">
                <label id="errorMsg" style="color: red;"></label>
              </div>
            </div>
            <input type="hidden" class="form-control" value="${propertyServices.servicesId}" id="servicesId" name="servicesId">
            <div class="form-group">
              <div class="col-sm-offset-6">
                <input type="button" onclick="js_validation();" class="btn btn-primary" value="<spring:message code="propertyServices.servicePreservation" />"/>
              </div>
            </div>
          </form>
        </div>
      </div>
      <%--服务信息添加结束--%>
    </div>
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