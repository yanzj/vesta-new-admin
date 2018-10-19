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
  <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">

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
  <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
  <script src="../static/js/jquery.ztree.core-3.5.js"></script>
  <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
  <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
  <script src="../static/js/wow.min.js"></script>
  <script>
    $(function(){
      console.log("sqq")
      $("#001300050000").addClass("active");
      $("#001300050000").parent().parent().addClass("in");
      $("#001300050000").parent().parent().parent().parent().addClass("active");
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
  <!--//Metis Menu -->
  <style>
    label.error {
      margin-left: 1%;
      color: red;
    }
  </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001300050000" username="${propertystaff.staffName}" />


  <div class="container1 userStaffManage">
    <form class="form-horizontal" name="roleSetUpdate" id="roleSetUpdate" action="../role/UpdateRoleRoleset.html">
      <div class="row">
        <div class="col-md-10 role_new_submit">
          <div class="newRoleSubmit">
            <a  href="../role/gotoUpdateRole?rolesetId=${rolesetId}" class="btn btn-primary" for="" >编辑</a>
            <a  href="../role/AuthorityManage.html" class="btn btn-primary" for="" >关闭</a>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-10 role_new_submit2">
          <table class="table table-bordered">
            <caption class="role_table_title">角色详情</caption>
            <%--<input type="hidden" name="staffId" value="${userStaffDTO.cnStaffId}">--%>
            <tbody>
            <tr>
              <td class="role_table_roleName">角色名称</td>
              <td class="role_table_fillCont">
               ${roleRoleSet.roledesc}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">关联的人</td>
              <td class="role_table_fillCont">
                <span class="selectResult">
                  <c:forEach items="${roleRoleSet.agencyList}" var="agencys">
                    ${agencys.agencyName};&nbsp;
                  </c:forEach>
                  <br/>
                  <c:forEach items="${roleRoleSet.staffList}" var="staffs">
                    ${staffs.staffName};&nbsp;
                  </c:forEach>
                </span>
              </td>
            </tr>
            <tr style="background-color: #F2F2F2"><td colspan="2">质量管理功能模块相关信息</td></tr>
            <c:forEach var="roleMould" items="${roleRoleMouldDTOs}">
              <tr>
                <td class="role_table_roleName">${roleMould.roleDescName}</td>
                <td>
                  <c:forEach var="roleRole" items="${roleRoleDTOs}">
                    <c:if test="${roleMould.roleDesc eq roleRole.roledesc}">
                      <input type="checkbox" disabled="disabled" name = "jsonStr" value = "${roleRole.roleId}"<c:if test="${roleRole.checkOut eq '1'}">checked = "checked"</c:if>/>${roleRole.roleName}
                    </c:if>
                  </c:forEach>
                </td>
              </tr>
            </c:forEach>
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
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>