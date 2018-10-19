<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/16
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/16
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
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
  <script src="../static/property/js/checkNullAllJsp.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
</head>
<script type="text/javascript">
  function goSubmit(id){
    document.getElementById("rolesetId").value = id;
    document.getElementById("search_form").submit();
  }
</script>
<script type="text/javascript">
  function testSection(){
    var sectionName = $("#sectionName").val();
    if(sectionName==""){
      alert($("#sectionNameNull").val())
      return false;
    }
    if(!CheckNull($("#sectionName").val(),$("#sectionNameNull").val())){
      return false;
    }
  }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001000020000" username="${propertystaff.staffName}" />
  <input type="hidden" id="sectionNameNull" value="<spring:message code="SECTION_NAMENULL" />">


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../system/sectionUpdate">
          <%--部门Id--%>
          <input type="hidden" name="sectionId" id="sectionId" value="${houseSectionAdminDTO.sectionId}">
          <%--部门--%>
          <div class="form-group  col-lg-6">
            <label for="sectionName" class="col-sm-3 control-label"><spring:message code="SECTION_NAME" />：</label>

            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="sectionName"
                     name="sectionName" value="${houseSectionAdminDTO.sectionName}">
            </div>
          </div>
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

          <button type="submit" class="btn btn-primary" for="propertySearch"  onclick="return testSection()"><spring:message code="SECTION_UPDATE"/></button>

          <a  href="../system/sectionManage.html" class="btn btn-primary" for="rolesetAdd" ><spring:message code="SECTION_BACK"/></a>

        </form>
      </div>
      <%--搜索条件结束--%>
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

