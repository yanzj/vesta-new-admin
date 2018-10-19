<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/16
  Time: 10:49
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

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001000020000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../system/gotoSectionAdd">
          <%--<div class="form-group  col-lg-6">--%>
          <%--<label for="propertyArea" class="col-sm-3 control-label"><spring:message code="propertyCompany.propertyArea" /></label>--%>

          <%--<div class="col-sm-9">--%>
          <%--<input type="text" class="form-control" placeholder="" id="propertyArea"--%>
          <%--name="propertyArea">--%>
          <%--</div>--%>
          <%--</div>--%>
          <%--<div class="form-group  col-lg-6">--%>
          <%--<label for="companyName" class="col-sm-3 control-label"><spring:message code="propertyCompany.companyName"/></label>--%>

          <%--<div class="col-sm-9">--%>
          <%--<input type="password" class="form-control" placeholder="" id="companyName"--%>
          <%--name="companyName">--%>
          <%--</div>--%>
          <%--</div>--%>
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

          <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="SECTION_ADD"/></button>

          <%--<a  href="/SectionAdd.jsp" class="btn btn-add" for="rolesetAdd" ><spring:message code="SECTION_ADD"/></a>--%>

        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">

    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="SECTION_SORT" /></td>
        <th><spring:message code="SECTION_NAME" /></th>
        <th><spring:message code="SECTION_OPERATE" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${houseSectionAdminDTOs}" var="section" varStatus="vs">
        <tr>
          <td><b>${vs.count}</b></td>
          <td>${section.sectionName}</td>
          <td class="last">
            <a href="../system/moveLevel.html?sectionId=${section.sectionId}&moveStatus=up&pageIndex=${webPage.pageIndex}" style="margin:0;color: #000000"><span class="glyphicon glyphicon-arrow-up" aria-hidden="true" style="color: #008000"></span><spring:message code="common_up" /></a>
            <a href="../system/moveLevel.html?sectionId=${section.sectionId}&moveStatus=down&pageIndex=${webPage.pageIndex}" style="margin:0;color: #000000"><span class="glyphicon glyphicon-arrow-down"style="color: #ff0000"></span><spring:message code="common_down" /></a>
            <a href="../system/gotoSectionUpdate?sectionId=${section.sectionId}" class="a2"><span class="span1"><spring:message code="SECTION_UPDATE" /></span></a>
            <a href="../system/sectionDelete?sectionId=${section.sectionId}" onclick="return confirm('确定删除?');" class="a3"><span class="span1"><spring:message code="SECTION_DELETE" /></span></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/system/sectionManage.html?pageIndex={0}"/>
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