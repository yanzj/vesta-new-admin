<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/7/28
  Time: 16:02
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
  <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
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
    $(function(){
      console.log("sqq")
      $("#001300030000").addClass("active");
      $("#001300030000").parent().parent().addClass("in");
      $("#001300030000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001300030000" username="${propertystaff.staffName}" />
  <div class="container1 userStaffManage">
      <div class="row">
        <div class="col-md-10 role_new_submit">
          <div class="newRoleSubmit">
            <a  href="../organize/goAltOrganize.html?organizeId=${organize.organizeId}" class="btn btn-primary" for="" >编辑</a>
            <%--<a  href="/agency/delAgency.html?id=${agency.agencyId}" class="btn btn-primary" for="" >置为无效</a>--%>
            <a  href="../organize/organizeList.html" class="btn btn-primary" for="" >关闭</a>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-10 role_new_submit2">
          <table class="table table-bordered">
            <caption class="role_table_title">群组信息</caption>
            <tbody>
            <tr>
              <td class="role_table_roleName">组名称</td>
              <td class="role_table_fillCont">
                ${organize.organizeName}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">是否有效</td>
              <td class="role_table_fillCont">
                <input type="checkbox" disabled="disabled" name="" <c:if test="${organize.status eq 1}">checked</c:if>>（选中表示为是）
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">排序号</td>
              <td class="role_table_fillCont">
                ${organize.orderNum}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">备注</td>
              <td>
                ${organize.memo}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">最后修改时间</td>
              <td>
                ${organize.modifyTime}
              </td>
            </tr>
            <tr style="background-color: #F2F2F2"><td colspan="2">质量管理功能模块相关信息</td></tr>
            <tr>
              <td class="role_table_roleName">群组成员</td>
              <td class="roleNameText">
                <c:forEach items="${organize.staffDTOList}" var="staff">
                  ${staff.staffName};&nbsp;
                </c:forEach>
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">关联的项目</td>
              <td class="roleNameText">
                <c:forEach items="${organize.projectOrganizeList}" var="project">
                  ${project.projectName};&nbsp;
                </c:forEach>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
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
