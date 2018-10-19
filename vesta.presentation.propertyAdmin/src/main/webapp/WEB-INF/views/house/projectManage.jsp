<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/6/21
  Time: 11:42
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

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001400010000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../project/projectList.html">
          <div class="form-group  col-lg-4">
            <label for="originName" class="col-sm-4 control-label">项目原名</label>

            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="originName"
                     name="name" value="${projectName}" >
            </div>
          </div>
          <div class="form-group  col-lg-4">
            <label for="name" class="col-sm-4 control-label">项目名</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="name"
                     name="originName" value="${originName}">
            </div>
          </div>
          <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="propertyCompany.propertySearch"/></button>
          <%--<a href="../project/updateProject.html" type="button" class="btn btn-primary" for="propertyAdd" ><spring:message code="common_add"/></a>--%>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <form action="../role/listRoleRole" method="post" id ="search_form">

      <input id = "rolesetId" type="hidden" name="rolesetId"  value="">
    </form>
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="staffManage.sort" /></td>
        <th>项目别名</th>
        <th>CRM项目名</th>
        <th>最后修改时间</th>
        <th><spring:message code="staffManage.staffOpteration" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${projectList}" var="list" varStatus="vs">
        <tr>
          <td><b>${(webPage.pageIndex-1)*20+vs.index+1}</b></td>
          <td>${list.originName}</td>
          <td>${list.name}</td>
          <td>${list.modifyOn}</td>
          <td class="last">
            <a class = "a1" href="../project/updateProject.html?projectId=${list.id}"><span class="span1">编辑</span></a>
            <a class = "a1" href="../project/projectDetail.html?projectId=${list.id}"><span class="span1">详情</span></a>
          <%--<a style="cursor: pointer" onclick="javascript:if(confirm('确定删除吗！'))location.href='../user/delStaff.html?staffIdDto=${staff.staffIdDto}';else return" class="a3"><span class="span1"><spring:message code="common_delete" /></span></a>--%>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/project/projectList.html?pageIndex={0}&originName=${originName}&name=${projectName}"/>
  </div>


</div>
</div>
</div>
</div>

<%--时间搜索插件--%>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
  $('.form_datetime').datetimepicker({
    language: 'zh-CN',
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1
  });
  $('.form_date').datetimepicker({
    language: 'zh-CN',
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
  });
  $('.form_time').datetimepicker({
    language: 'zh-CN',
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 1,
    minView: 0,
    maxView: 1,
    forceParse: 0
  });
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>