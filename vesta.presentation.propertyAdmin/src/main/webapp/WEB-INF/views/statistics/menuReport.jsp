<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/6/1
  Time: 18:34
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
  <script src="../static/property/js/propertyRepair.js"></script>
  <style>
    .tableStyle thead td, .tableStyle thead th {
      padding-left: 0;
      text-align: center;
    }

    .search_button {
      text-align: center;
    }

    .control_btn {
      padding: 0 0 1rem 0;
      background-color: #fbfbfb;
    }

    .control_btn button, .control_btn a {
      margin-right: 1rem;
    }
  </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="005800070000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form:form class="form-horizontal" action="../click/menuReport.html" modelAttribute="menuSearch"
                   method="get">
          <div class="form-group  col-xs-5">
            <label for="startTime" class="col-sm-4 control-label"><spring:message
                    code="ownerManage.SelBeginTime"/></label>

            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <form:input type="text" class="form-control" placeholder="请输入统计开始时间" path="startTime"
                          value="${menuSearch.startTime}" readonly="true"/>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="form-group  col-xs-5">
            <label for="endTime" class="col-sm-4 control-label"><spring:message
                    code="HousePayBatch.to"/></label>

            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <form:input type="text" class="form-control" placeholder="请输入统计结束时间" path="endTime"
                          value="${menuSearch.endTime}" readonly="true"/>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="search_button  col-xs-12">
            <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                    code="common_search"/></button>
            <!--集合长度(取决Excel是否可以导出)-->
            <input type="hidden" id="size" value="${isExcel}">
            <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary"
               style="color:#fff">导出Excel</a>
          </div>


          <div class="clearfix"></div>
        </form:form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <div class="">
    </div>
    <table width="100%" class="tableStyle table-striped">
      <thead>
      <tr>
        <th><spring:message code="pageProperty.name"/></th>
        <th><spring:message code="clickTimes.clicks"/></th>
        <th><spring:message code="clickTimes.clickUser"/></th>
        <th><spring:message code="clickTimes.clickCapita"/></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${menus}" var="menu" varStatus="row">
        <tr>
          <td>${menu.name}</td>
          <td>${menu.clicks}</td>
          <td>${menu.clickUserNum}</td>
          <td>${menu.total}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <%--<m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/click/menuReport.html?pageIndex={0}&startTime=${menuSearch.startTime}&endTime=${menuSearch.endTime}"/>--%>
  </div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
  var pages = "${webPage.pageIndex}";
  var startTime = "${menuSearch.startTime}";
  var endTime = "${menuSearch.endTime}";
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

  function isExcel() {
    var size = $("#size").val();
    if (size > 0) {
      var href = "../click/exportExcel?type=3&pageIndex={0}&startTime=${menuSearch.startTime}&endTime=${menuSearch.endTime}";
      window.location.href = href;
    } else {
      alert("没有可以导出的数据");
    }
  }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>