<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/5/21
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%response.setHeader("cache-control","public"); %>
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
    $(function(){
      console.log("sqq")
      $("#005700040000").addClass("active");
      $("#005700040000").parent().parent().addClass("in");
      $("#005700040000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005700040000" username="${propertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
        <form:form class="form-horizontal" action="../userFeed/feedbackDetail.html" modelAttribute="feedBacks" method="get">
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="ownerManage.Name" />：</label>
            <label class="control-label">${feedBacks.userName}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="operationLog.content" />：</label>
            <div class="form-group  col-lg-7"><label class="control-label">${feedBacks.content}</label></div>
          </div>
          <input type="hidden" id="address_b" value="${feedBacks.userType}"/>
          <div class="form-group  col-lg-12" id="address_a">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.address" />：</label>
            <label class="control-label">${feedBacks.userType}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="ownerManage.SelMobile" />：</label>
            <label class="control-label">${feedBacks.phone}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="userFeed.feedDate" />：</label>
            <label class="control-label">${feedBacks.startDate}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="billInfo.processTime" />：</label>
            <label class="control-label">${feedBacks.endDate}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="billInfo.processUser" />：</label>
            <label class="control-label">${feedBacks.memo}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.repairWay" />：</label>
            <label class="control-label">${feedBacks.feedbackType}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="billInfo.processStatus" />：</label>
            <label class="control-label">${feedBacks.status}</label>
          </div>
          <div class="text-center form-group  col-lg-12" style="padding:0;">
            <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);"><spring:message code="common_cancel"/></button>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script type="application/javascript">
  $(document).ready(function(){load()});
  function load(){
    var address=$("#address_b").val();
    //alert(address);
    if(address=="" || address==null){
      document.getElementById("address_a").style.display="none";
    }
  }
</script>
</body>
</html>
