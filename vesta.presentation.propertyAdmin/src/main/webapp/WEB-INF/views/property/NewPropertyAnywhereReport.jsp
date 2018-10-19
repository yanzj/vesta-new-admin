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
  <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"> </script>
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

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020004" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <h3 class="text-center"><spring:message code="propertyReport.releaseReport" /></h3>
        <div class="form-body">
          <form class="form-horizontal" action="../property/PropertyAnywhereMessage" id="propertyCompany">
            <input type="hidden" id="anywherePicId" name="anywherePicId" value="${anywhereReport.anywherePicId}" />
            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="makedate"><spring:message code="propertyReport.releaseTime" /></label>
              <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="" id="makedate" name="makedate" value="${anywhereReport.makedate}" readonly>
              </div>
            </div>
            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="userName"><spring:message code="propertyReport.releaseName" /></label>
              <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="" id="userName" name="userName" value="${anywhereReport.userName}" readonly>
              </div>
            </div>

            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="phoneNO"><spring:message code="propertyReport.reportMobile" /></label>
              <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="" id="phoneNO" name="phoneNO" value="${anywhereReport.phoneNO}" readonly>
              </div>
            </div>

            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="type"><spring:message code="propertyReport.reprotType" /></label>
              <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="" id="type" name="type" value="${anywhereReport.type}" readonly>
              </div>
            </div>

            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="repairAddress"><spring:message code="propertyReport.releasePosition" /></label>
              <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="" id="repairAddress" name="repairAddress" value="${anywhereReport.repairAddress}" readonly>
              </div>
            </div>

            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="stateType"><spring:message code="advertServices.state" /></label>
              <div class="col-sm-9">
                <select id="stateType" name="stateType"  class="form-control">
                  <c:forEach items="${anywhereReport.repaidType}" var="item">
                    <option value="${item.key }">${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <div class="form-group col-lg-6"> </div>

            <div class="form-group col-lg-12" style="padding-left: 55px;">
              <label class="col-sm-1 control-label"><spring:message code="propertyReport.releaseContent" /></label>
              <div class="col-sm-9">
                <textarea class="form-control" placeholder="最多输入200个字符" name="content" id="content" readonly>${anywhereReport.content}</textarea>
              </div>
            </div>

            <div class="form-group col-lg-12" style="padding-left: 55px;">
              <label class="col-sm-1 control-label"><spring:message code="propertyReport.releaseImg" /></label>
              <div class="col-sm-9">
                <c:forEach items="${anywhereReport.anywherePicImage}" var="image">
                  <img src="${image.url}">
                </c:forEach>
              </div>
            </div>
            <button type="submit" class="btn btn-primary"  for="propertySearch" ><spring:message code="common_save"/></button>
            <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);" for="propertySearch" ><spring:message code="common_back"/></button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</div>
</div>
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
