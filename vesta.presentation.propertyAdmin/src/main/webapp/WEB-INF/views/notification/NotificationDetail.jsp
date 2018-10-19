<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/5/18
  Time: 15:06
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
</head>
<script type="text/javascript">
  function goSubmit(id){
    document.getElementById("rolesetId").value = id;
    document.getElementById("search_form").submit();
  }
  function goto(){
    window.location.href='../notification/systemMessagelists.html';
  }
  $(function(){
    console.log("sqq")
    $("#000100010000").addClass("active");
    $("#000100010000").parent().parent().addClass("in");
    $("#000100010000").parent().parent().parent().parent().addClass("active");
  })
</script>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100010000" username="${propertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <div class="form-group  col-lg-12">
          <label class="col-sm-2 control-label"><spring:message code="systemnotification.title" /></label>
          <label class="col-sm-10 control-label">${systemNotification.notificationTitle}</label>
        </div>

        <div class="form-group  col-lg-12">
          <label class="col-sm-2 control-label"><spring:message code="systemnotification.content" /></label>
          <div class="col-sm-10">
            <textarea class="form-control" placeholder="" id="notificationContent" name="notificationContent" style= "min-height:300px;background:transparent;border-style:none;" readonly="readonly">
              ${systemNotification.notificationContent}
            </textarea>
          </div>
        </div>
        <div class="text-center form-group  col-lg-12" style="padding:0;">
          <button type="button" class="btn btn-primary" onclick="goto()"><spring:message code="common_cancel"/></button>
        </div>
        <div class="clearfix"></div>
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

</body>
</html>
