<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/10/31
  Time: 18:25
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
  <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
  <!-- js-->
  <script src="../static/js/jquery-1.11.1.min.js"></script>
  <script src="../static/js/modernizr.custom.js"></script>
  <!--animate-->
  <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
  <script src="../static/js/wow.min.js"></script>
  <script>
    $(function(){
      $("#003100030000").addClass("active");
      $("#003100030000").parent().parent().addClass("in");
      $("#003100030000").parent().parent().parent().parent().addClass("active");
    })
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
<div>

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="003100030000" username="${authPropertystaff.staffName}" />
  <div class="container1 userStaffManage">
    <div class="row">
      <div class="col-md-10 role_new_submit">
        <div class="newRoleSubmit">
          <a  href="../BaseData/editProjectRole.html?projectId=${projectProject.projectId}&flag=1" class="btn btn-primary" for="" >编辑</a>
          <a  href="../BaseData/projectRoleManage.html" class="btn btn-primary" for="" >关闭</a>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-10 role_new_submit2">
        <table class="table table-bordered">
          <caption class="role_table_title">项目详情</caption>
          <input type="hidden" name="projectId" value="${projectProject.projectId}">
          <tbody>
          <tr>
            <td class="role_table_roleName">项目名称</td>
            <td class="role_table_fillCont">
              ${projectProject.projectName}
            </td>
          </tr>
          <tr>
            <td class="role_table_roleName">最后修改时间</td>
            <td class="role_table_fillCont">
              ${projectProject.modifyOn}
            </td>
          </tr>
          <%--<tr style="background-color: #F2F2F2"><td colspan="2">质量管理功能模块相关信息</td></tr>--%>
          <%--<tr>--%>
            <%--<td class="role_table_roleName"><span>甲方权限</span></td>--%>
            <%--<td>--%>
              <%--<c:forEach items="${projectProject.ownerDepts}" var="dept">--%>
                <%--${dept.pRoleName}；--%>
              <%--</c:forEach>--%>
              <%--<br/>--%>
              <%--<c:forEach items="${projectProject.ownerStaffs}" var="staff">--%>
                <%--${staff.pRoleName}；--%>
              <%--</c:forEach>--%>
            <%--</td>--%>
          <%--</tr>--%>
          <tr>
            <td class="role_table_roleName"><span>项目系统管理员</span></td>
            <td>
              <c:forEach items="${projectProject.viewDepts}" var="dept">
                ${dept.pRoleName}；
              </c:forEach>
              <br/>
              <c:forEach items="${projectProject.viewStaffs}" var="staff">
                ${staff.pRoleName}；
              </c:forEach>
            </td>
          </tr>
          <tr>
            <td class="role_table_roleName"><span>工程经理</span></td>
            <td>
              <c:forEach items="${projectProject.surveyorDepts}" var="dept">
                ${dept.pRoleName}；
              </c:forEach>
              <br/>
              <c:forEach items="${projectProject.surveyorStaffs}" var="staff">
                ${staff.pRoleName}；
              </c:forEach>
            </td>
          </tr>
          <%--<tr>--%>
            <%--<td class="role_table_roleName"><span>关单权限</span></td>--%>
            <%--<td>--%>
              <%--<c:forEach items="${projectProject.closeDepts}" var="dept">--%>
                <%--${dept.pRoleName}；--%>
              <%--</c:forEach>--%>
              <%--<br/>--%>
              <%--<c:forEach items="${projectProject.closeStaffs}" var="staff">--%>
                <%--${staff.pRoleName}；--%>
              <%--</c:forEach>--%>
            <%--</td>--%>
          <%--</tr>--%>
          </tbody>
        </table>
      </div>
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
