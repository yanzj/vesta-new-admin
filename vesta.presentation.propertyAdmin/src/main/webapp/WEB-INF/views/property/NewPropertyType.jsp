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
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001000000000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--公告类型添加开始--%>
      <div class="form-body">
        <h3 class="text-center">新增公告类型 </h3>
        <div class="form-body">
          <form class="form-horizontal" id="frm" action="../property/AddPropertyType.html">
            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="type"><spring:message code="propertyAnnouncement.type" /></label>
              <div  class="col-sm-9">
                <input type="text" class="form-control" placeholder="" id="type" name="type"  value="${PropertyType.type}">
              </div>
            </div>
            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="propertyRange"><spring:message code="propertyType.Range" /></label>
              <div  class="col-sm-9">
                <input type="text" class="form-control" placeholder="" id="propertyRange" name="propertyRange"  value="${PropertyType.propertyRange}">
              </div>
            </div>
            <input type="hidden" class="form-control" id="typeId" name="typeId"  value="${PropertyType.typeId}">

                <button type="button" class="btn btn-primary" onclick="validate()" ><spring:message code="propertyServices.servicePreservation"/></button>

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
<script type="text/javascript">
  function validate() {
    var type = $("#type").val();
    var propertyRange=$("#propertyRange").val();

    if(!CheckNull($("#type").val(),"请输入公告类型！")){
      return false;
    }
    if(type==''){
      alert("请输入公告类型");
      return;
    }
    if(!CheckNull($("#propertyRange").val(),"请输入范围！")){
      return false;
    }
    if(propertyRange==''){
      alert("请输入范围");
      return;
    }
        document.getElementById("frm").submit();
  }

</script>

</body>
</html>