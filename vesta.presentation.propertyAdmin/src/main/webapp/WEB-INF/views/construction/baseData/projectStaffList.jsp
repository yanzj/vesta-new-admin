<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/10/28
  Time: 18:11
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
    $(function () {
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
  <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
  <script src="../static/js/jquery.ztree.core-3.5.js"></script>
  <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
  <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
  <style>
    .btnDis{
      background-color: #eee;border: none;
    }
    .btn.btnDis:hover{
      background-color: #eee;border: none;
    }
  </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="003100010000" username="${authPropertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../BaseData/projectStaff.html">
          <div class="form-group  col-lg-12">
            <label for="supplierName" class="col-sm-1 control-label">所属机构名称</label>
            <label class="col-sm-8">
              ${staffList.supplierName}
              <%--<input type="text" class="form-control" placeholder="" id="staffNameDto" name="agencyName" value="" >--%>
            </label>
          </div>
          <%--用户名--%>
          <div class="form-group  col-lg-12">
            <label for="roleAgency" class="col-sm-1 control-label">角色列表</label>
            <label class="col-sm-8">
              ${staffList.roleAgency}
              <%--<input type="text" class="form-control" placeholder="" id="userName" name="userName" value="">--%>
            </label>
          </div>
          <input type="hidden" name="projectId" value="${projectEmploy.projectId}">
          <a href="../BaseData/authGoToAltStaff.html?supplierId=${sId}&agencyId=${projectId}&agencyType=${agencyType}" type="button" class="btn btn-primary" for="propertyAdd" >添加人员</a>
            <%--<a href="#" type="button" class="btn btn-primary" for="propertyAdd" >导入账号</a>--%>
          <a href="../BaseData/employManage.html?projectId=${projectId}" type="button" class="btn btn-primary">返回</a>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="mall.serialNumber" /></td>
        <th>系统账号</th>
        <th>姓名</th>
        <th>手机号</th>
        <th>邮箱地址</th>
        <th>角色</th>
        <th>最后修改时间</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${staffList.userList}" var="list" varStatus="row">
        <tr>
          <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
          <td>${list.userName}</td>
          <td>${list.staffName}</td>
          <td>${list.phone}</td>
          <td>${list.mailbox}</td>
          <td>${list.roleAgency}</td>
          <td>${list.updateTime}</td>
          <td>
            <a href="../BaseData/authGoToAltStaff.html?agencyType=${agencyType}&supplierId=${sId}&userId=${list.userId}&agencyId=${projectId}">修改</a>
            <a href="../BaseData/authPprojectStaffDetail.html?agencyType=${agencyType}&supplierId=${sId}&userId=${list.userId}&agencyId=${projectId}">详情</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }../BaseData/authProjectStaff.html?supplierId=${sId}&agencyId=${projectId}&agencyType=${agencyType}&pageIndex={0}"/>
  </div>


</div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>

</body>
</html>
