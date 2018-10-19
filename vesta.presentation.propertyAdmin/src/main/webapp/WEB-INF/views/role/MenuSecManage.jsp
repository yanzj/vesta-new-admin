<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/4/18
  Time: 14:46
  To change this template use File | Settings | File Templates.
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
</script>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000500030000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../role/addSecMenu">
          <input type="hidden" name="roleMenuParId" value="${parId}">
          <div class="form-group  col-lg-6">
            <label for="roleMenuName" class="col-sm-3 control-label">菜单名称</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="roleMenuName"
                     name="roleMenuName">
            </div>
          </div>
          <div class="form-group  col-lg-6">
            <label for="roleMenuDesc" class="col-sm-3 control-label">菜单描述</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="roleMenuDesc"
                     name="roleMenuDesc">
            </div>
          </div>
          <button type="submit" class="btn btn-primary" for="propertySearch" >添加二级菜单</button>
          <a  href="../role/menuManage.html" class="btn btn-primary" for="rolesetAdd" >返回一级菜单</a>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">

    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <th>排序</th>
        <th>菜单Id</th>
        <th>菜单名称</th>
        <th>菜单描述</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${roleMenuDTOs}" var="menu" varStatus="vs">
        <tr>
          <td><b>${vs.count}</b></td>
          <td>${menu.roleMenuId}</td>
          <td>${menu.roleMenuName}</td>
          <td>${menu.roleMenuDesc}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/role/menuSecManage?pageIndex={0}"/>
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
