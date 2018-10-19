<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/23
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020006" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../property/electricManage.html">
          <%--房间号--%>
          <div class="form-group  col-lg-4">
            <label for="houseNum" class="col-sm-4 control-label"><spring:message code="EleManage_HouseNum" />：</label>
            <div class="col-sm-8">
              <input type="text" id="houseNum" name="houseNum" class="form-control" value="${propertyElectricDTO.houseNum}">
            </div>
          </div>
          <%--电量余量--%>
          <div class="form-group  col-lg-5">
            <label for="electricQuantity" class="col-sm-3 control-label"><spring:message code="EleManage_EleQuantity"/>：</label>
            <div class="col-sm-5">
              <select name="eleSign" class="form-control">
                <option value="">-请选择符号-</option>
                <option value="<"><</option>
                <option value="<="><=</option>
                <option value=">">></option>
                <option value=">=">>=</option>
                <option value="=">=</option>
              </select>
            </div>
            <div class="col-sm-4">
                <input type="text" id="electricQuantity" class="form-control" name="electricQuantity" value="${propertyElectricDTO.electricQuantity}">
            </div>
          </div>
          <%--搜索--%>
          <button type="submit" class="btn btn-primary" for=""><spring:message code="common_select"/></button>
          <%--导入--%>
          <%--<a  href="#" class="btn btn-add" for="rolesetAdd" ><spring:message code="EleManage_Lead"/></a>--%>
          <%--导入模板下载--%>
          <a  href="../property/electricUpload" class="btn btn-primary" for="rolesetAdd" type="file"><spring:message code="EleManage_Download"/></a>
            <a  href="../property/gotoImport" class="btn btn-primary" for="rolesetAdd" type="file"><spring:message code="EleManage_Lead"/></a>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <form action="#" method="post" id ="search_form">

      <%--<input id = "rolesetId" type="hidden" name="rolesetId"  value="">--%>
    </form>
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="common_sort" /></td>
        <th><spring:message code="EleManage_ProjectName" /></th>
        <th><spring:message code="EleManage_HouseNum" /></th>
        <th><spring:message code="EleManage_UserName" /></th>
        <%--业主电话<th><spring:message code="EleManage_UserMobile" /></th>--%>
        <th><spring:message code="EleManage_EleQuantity" /></th>
        <th><spring:message code="EleManage_CreateOn" /></th>
        <%--抄表人<th><spring:message code="EleManage_StaffName" /></th>--%>
        <th><spring:message code="common_operate" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${propertyElectricDTOs}" var="ele" varStatus="vs">
        <tr>
          <td><b>${vs.count}</b></td>
          <td>${ele.projectName}</td>
          <td>${ele.houseNum}</td>
          <td>${ele.userName}</td>
          <%--业主电话<td>${ele.userMobile}</td>--%>
          <td>${ele.electricQuantity}</td>
          <td>${ele.createOn}</td>
          <%--抄表人<td>${ele.staffName}</td>--%>
            <td class="last">
             <a href="../property/delElectric?electricId=${ele.electricId}" class="a3"><span class="span1"><spring:message code="common_delete" /></span></a>
            </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/property/electricManage.html?pageIndex={0}"/>
  </div>


</div>
</div>
</div>
</div>

<%--时间搜索插件--%>
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
