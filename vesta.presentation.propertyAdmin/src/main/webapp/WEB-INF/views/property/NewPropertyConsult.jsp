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

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020010" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--服务信息添加开始--%>
      <div class="form-body">
        <h3 class="text-center"><spring:message code="propertyConsult.answerContent" /></h3>
        <div class="form-body">
          <form class="form-horizontal" action="../property/replyPropertyConsult.html">
            <div class="form-group col-lg-6">
              <label class="col-sm-4 control-label text-right" for="crtTime"><spring:message code="propertyConsult.crtTime" /></label>
              <input type="hidden" id="id" name="id" value="${propertyConsult.id}" />
              <div class="col-sm-8">
                <input type="text" class="form-control" placeholder="" id="crtTime" name="crtTime" value="${propertyConsult.crtTime}" readonly>
              </div>
            </div>
            <div class="form-group col-lg-6">
              <label class="col-sm-4 control-label text-right" for="owner"><spring:message code="propertyConsult.owner" /></label>
              <div class="col-sm-8">
                <input type="text" class="form-control" placeholder="" id="owner" name="userName" value="${propertyConsult.userName}" readonly>
              </div>
            </div>
            <div class="form-group col-lg-6">
              <label class="col-sm-4 control-label text-right" for="mobile"><spring:message code="propertyConsult.mobile" /></label>
              <div class="col-sm-8">
                <input type="text" class="form-control" placeholder="" id="mobile" name="mobile" value="${propertyConsult.mobile}" readonly>
              </div>
            </div>

            <div class="form-group col-lg-6">
              <label class="col-sm-4 control-label text-right" for="address"><spring:message code="propertyConsult.address" /></label>
              <div class="col-sm-8">
                <input type="text" class="form-control" placeholder="" id="address" name="address" value="${propertyConsult.address}" readonly>
              </div>
            </div>

            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label text-right"><spring:message code="propertyConsult.content" /></label>
              <div class="col-sm-8">
                <textarea class="form-control" placeholder="最多输入200个字符" name="content" id="content" readonly>${propertyConsult.content}</textarea>
              </div>
            </div>

            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label text-right"><spring:message code="propertyConsult.replyContent" /></label>
              <div class="col-sm-8">
                <textarea class="form-control" placeholder="最多输入200个字符" name="answerContent" id="answerContent" >${propertyConsult.answerContent}</textarea>
              </div>
            </div>

            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label text-right"><spring:message code="propertyReport.releaseImg" /></label>
              <div class="col-sm-8">
                <c:forEach items="${propertyConsult.imgList}" var="is" varStatus="row">
                  <img src="${is.img}" style="width: 100px; height:120px;;">
                </c:forEach>
              </div>
            </div>

              <div class="clearfix"></div>
              <div class="text-center">
            <button type="submit" class="btn btn-primary"><spring:message code="propertyServices.servicePreservation" /></button>
            <button type="button" class="btn btn-primary" onclick="javascript:history.back(-1);"><spring:message code="common_back" /></button>
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
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>