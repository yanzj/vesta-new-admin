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


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" id="HroleSetAdd" name="HroleSetAdd" action="../role/AddRoleRoleset" method="post">
          <h3><spring:message code="authotity.rolesetAdd" /></h3>
          <%--角色名称--%>
          <div class="form-group  col-lg-6">
          <label for="roledesc" class="col-sm-3 control-label"><spring:message code="authority.rolesetAddName" /></label>
          <div class="col-sm-9">
          <input type="text" class="form-control" placeholder="" id="roledesc" name="roledesc">
          </div>
          </div>
            <%--&lt;%&ndash;是否允许被分配&ndash;%&gt;--%>
          <%--<div class="form-group  col-lg-6">--%>
          <%--<label for="isallot" class="col-sm-3 control-label"><spring:message code="authority.rolesetAddIsallot"/></label>--%>
          <%--<div class="col-sm-9">--%>
          <%--<select class="form-control" placeholder="" id="isallot" name="isallot">--%>
                      <%--<option value="1"><spring:message code="common_yes"/></option>--%>
                      <%--<option value="0"><spring:message code="common_no"/></option>--%>
          <%--</select>--%>
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

          <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_confirm"/></button>

          <a  href="/role/AuthorityManage.html" class="btn btn-primary" for="rolesetAdd" ><spring:message code="common_cancel"/></a>

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
<script type="text/javascript">
  $(function() {
    $("#HroleSetAdd").validate({
      rules: {
        roledesc: {
          required: true,
          minlength: 1,
          maxlength: 30
        }
      },
      messages: {
        roledesc: {
          required: "请输入名称！",
          minlength: "不能少于1个字符！",
          maxlength: "请勿超过30个字！"
        }
      }
    })
  });
</script>
</body>
</html>