<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/10/31
  Time: 11:15
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
  <style>
    label.error {
      margin-left: 1%;
      color: red;
    }
  </style>
  <!-- Bootstrap Core CSS -->
  <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
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
  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="003100010000" username="${authPropertystaff.staffName}" />
  <div class="container1 userStaffManage">
    <form name="updateStaff" id="updateStaff" method="post">
      <div class="row">
        <div class="col-md-10 role_new_submit">
          <div class="newRoleSubmit">
            <%--<a  href="/user/goAltPassword.html?staffId=${userStaffDTO.cnStaffId}" class="btn btn-primary" for="" >修改密码</a>--%>
            <%--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">修改密码</button>--%>
            <%--<a  href="/user/goToAltStaff.html?staffId=${userStaffDTO.cnStaffId}&" class="btn btn-primary" for="" >编辑</a>--%>
            <%--<a  href="/user/delStaffAgency.html?staffIdDto=${userStaffDTO.cnStaffId}" class="btn btn-primary" for="" >置为无效</a>--%>
              <a  href="#" onclick="javascript:window.history.back();return false" class="btn btn-primary" for="" >关闭</a>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-10 role_new_submit2">
          <table class="table table-bordered">
            <caption class="role_table_title">员工基础信息</caption>
            <tbody>
            <tr>
              <td class="role_table_titleable_roleName">姓名</td>
              <td class="role_table_fillCont">
                ${userStaffDTO.staffName}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">联系方式</td>
              <td class="role_table_fillCont">
                ${userStaffDTO.phone}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">所属责任单位</td>
              <td class="role_table_fillCont">
                ${supplier.supplierName}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">是否有效</td>
              <td >
                <input type="radio" name="status" value="1" <c:if test="${userStaffDTO.status eq '1'}">checked="checked"</c:if>/>是
                <input type="radio" name="status" value="0" <c:if test="${userStaffDTO.status eq '0'}">checked="checked"</c:if>/>否
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">角色</td>
              <td>
                <input type="hidden" id ="roleAgencyId" name="roleAgencyId" value="">
                <c:forEach items="${roleList}" var="list" varStatus="row">
                  <input type="checkbox" name="roleIds" value="${list.roleId}" <c:if test="${list.checked eq '1'}">checked="checked"</c:if>/>${list.roleName}
                </c:forEach>
              </td>
            </tr>

            </tbody>
          </table>
        </div>
      </div>
    </form>
  </div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>

<!-- Modal -->
<script type="text/javascript">

</script>
</body>
</html>
