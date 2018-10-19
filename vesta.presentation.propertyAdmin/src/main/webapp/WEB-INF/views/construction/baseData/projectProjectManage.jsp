<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/10/20
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
  <!-- js-->
  <script src="../static/js/jquery-1.11.1.min.js"></script>
  <script src="../static/js/modernizr.custom.js"></script>
  <!--animate-->
  <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
  <script src="../static/js/wow.min.js"></script>
  <script>
    $(function(){
      console.log("sqq")
      $("#003100010000").addClass("active");
      $("#003100010000").parent().parent().addClass("in");
      $("#003100010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
  <script src="../static/js/jquery.ztree.core-3.5.js"></script>
  <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
  <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="003100010000" username="${authPropertystaff.staffName}" />


  <div class="forms">

    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="">
        <%--<div class="form-group  col-lg-4">--%>
        <%--<label class="col-sm-8 control-label">公司项目列表</label>--%>
        <%--</div>--%>
        <%--<div class="form-group  col-lg-4">--%>
        <%--<select class="form-control" id="cityId" name="cityId" onchange="changeProject(this.options[this.options.selectedIndex].value)">--%>
        <%--<c:forEach items="${allCity}" var="city">--%>
        <%--<option value="${city.cityId}" <c:if test="${city.cityId eq cityId}">selected</c:if>>${city.cityName}</option>--%>
        <%--</c:forEach>--%>
        <%--</select>--%>
        <%--</div>--%>
        <%--<div class="form-group  col-lg-4">--%>
        <%--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">--%>
        <%--添加项目--%>
        <%--</button>--%>
        <%--</div>--%>

        <table class="tableStyle" width="100%" style="border: 1px solid #ddd;margin-top: 30px;">
          <c:forEach items="${projectList}" var="list" varStatus="row">
            <tr>
              <td>${list.value}</td>
              <%--<td><a href="../BaseData/editProjectRole.html?projectId=${list.projectId}&flag=2">甲方设置</a></td>--%>
              <td><a href="../BaseData/projectBuilding.html?projectId=${list.key}">楼栋管理</a></td>
              <td><a href="../BaseData/employManage.html?projectId=${list.key}">责任单位及监理设置</a></td>
              <td><a href="../BaseData/tendersManage.html?projectId=${list.key}">标段管理</a></td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>

  </div>

</div>
</div>
</div>
</div>
<!-- main content end-->

<%@ include file="../../main/foolter.jsp" %>
</div>
<%--<script>--%>
<%--function changeProject(cityId){--%>
<%--window.location.href="../BaseData/projectManage.html?cityId="+cityId;--%>
<%--}--%>
<%--</script>--%>
</body>
</html>
