<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/1/25
  Time: 20:29
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

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005700030000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../user/disRoleset" method="post">
          <%--<h3><spring:message code="staffManage.manageRoleset" /></h3>--%>
          <input type="hidden" name="staffIdDto" value="${userPropertystaffDTO.staffIdDto}">
          <%--角色名称--%>
          <div class="form-group  col-lg-6">
            <label for="staffNameDto" class="col-sm-3 control-label"><spring:message code="staffManage.staffName" /></label>
            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="staffNameDto"
                     name="staffNameDto" value="${userPropertystaffDTO.staffNameDto}" readonly>
              <%--value="${userPropertystaffDTO.staffName}"--%>
            </div>
          </div>
          <%--是否允许被分配--%>
          <div class="form-group  col-lg-6">
            <label for="roleSetIdDto" class="col-sm-3 control-label"><spring:message code="staffManage.staffRoleSet"/></label>
            <div class="col-sm-9">
              <select class="form-control" placeholder="" id="roleSetIdDto" name="roleSetIdDto">
                <c:forEach var="roleSet" items="${roleRolesetDTOs}">
                  <option value="${roleSet.setId}" <c:if test="${roleSet.setId eq userPropertystaffDTO.roleSetIdDto}">selected="selected"</c:if>>${roleSet.roledesc}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="form-group  col-lg-6">
            <label for="appRoleSetIdDto" class="col-sm-3 control-label"><spring:message code="staffManage.staffAppRoleSet"/></label>
            <div class="col-sm-9">
              <select class="form-control" placeholder="" id="appRoleSetIdDto" name="appRoleSetIdDto">
                <c:forEach var="approleSet" items="${appRolesetDTOs}">
                  <option value="${approleSet.appSetId}" <c:if test="${approleSet.appSetId eq userPropertystaffDTO.appRoleSetIdDto}">selected="selected"</c:if>>${approleSet.appSetName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <%--<div class="form-group  col-lg-6">--%>
          <%--<label for="propertyProject" class="col-sm-3 control-label"><spring:message code="propertyCompany.propertyProject"/></label>--%>

          <%--<div class="col-sm-9">--%>
          <%--<input type="password" class="form-control" placeholder="" id="propertyProject"--%>
          <%--name="propertyProject">--%>
          <%--</div>--%>
          <%--</div>--%>
          <%--<div class="form-group  col-lg-6">--%>
          <%--<label for="projectAdmin" class="col-sm-3 control-label"><spring:message code="propertyCompany.projectAdmin"/></label>--%>

          <%--<div class="col-sm-9">--%>
          <%--<input type="password" class="form-control" placeholder="" id="projectAdmin"--%>
          <%--name="projectAdmin">--%>
          <%--</div>--%>
          <%--</div>--%>

          <button type="submit" class="btn btn-primary" for="" ><spring:message code="common_confirm"/></button>

          <a  href="" onclick="javaScript:history.go(-1)" class="btn btn-primary" for="" ><spring:message code="common_cancel"/></a>

        </form>
      </div>
      <%--搜索条件结束--%>


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
