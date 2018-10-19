<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/10/26
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ page import="com.maxrocky.vesta.utility.StringUtil" %>
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
    $(function () {
      $("#003100010000").addClass("active");
      $("#003100010000").parent().parent().addClass("in");
      $("#003100010000").parent().parent().parent().parent().addClass("active");
    })
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

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="003100010000" username="${authPropertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../BaseData/employManage.html">
          <div class="form-group  col-lg-5">
            <label for="sName" class="col-sm-5 control-label">机构名称：</label>
            <div class="col-sm-7">
              <input type="text" id="sName" name="sName" value="${projectEmploy.sName}" class="form-control">
            </div>
          </div>
          <input type="hidden" name="projectId" value="${projectEmploy.projectId}">
          <button type="submit" class="btn btn-primary">搜索</button>
          <a href="../BaseData/goToUpdateEmploy.html?projectId=${projectEmploy.projectId}" type="button" class="btn btn-primary" for="propertyAdd" >新增机构</a>
          <button type="button" class="btn btn-primary" onclick="downloadModel()">下载数据模板</button>
          <button type="button" class="btn btn-primary" onclick="test()">导入数据</button>
          <a href="../BaseData/projectManage.html?projectId=${projectEmploy.projectId}" type="button" class="btn btn-primary" for="propertyAdd" >返回</a>
        </form>
        <form action="../BaseData/importMechanismPeopleExcel",name="frm" id="frm" method="post" enctype="multipart/form-data">
          <%-- 导入excel 隐藏 --%>
          <input type="file" id="myfile" name="myfile" style="visibility:hidden;" onchange="importExcel()">
            <%
              HttpSession sess = request.getSession();
              String message = (String) sess.getAttribute("result");

              if (message == "ok") {
                sess.setAttribute("result", "");
            %>
            <script type="text/javascript">
              alert("导入成功");
            </script>
            <%

            }else if(!StringUtil.isEmpty(message)){
              sess.setAttribute("result", "");
            %>
            <script type="text/javascript">
              alert("<%=message %>");
              <% }%>
            </script>
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
        <th>机构名称</th>
        <th>所属项目</th>
        <th>性质</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${employs}" var="list" varStatus="row">
        <tr>
          <td><b>${row.index+1}</b></td>
          <td>${list.sName}</td>
          <td>${list.projectName}</td>
          <td>
            <c:choose>
              <c:when test="${list.sNature eq 1}">总包</c:when>
              <c:when test="${list.sNature eq 2}">分包</c:when>
              <c:otherwise>监理</c:otherwise>
            </c:choose>
          </td>
          <td>
            <a href="../BaseData/goToUpdateEmploy.html?projectId=${projectEmploy.projectId}&sId=${list.sId}">修改</a>
            <c:if test="${list.sNature eq 1 or list.sNature eq 2}">
              <a href="../BaseData/categorySetting.html?employId=${list.sId}&projectId=${projectEmploy.projectId}">工程责任设置</a>
            </c:if>
            <%--<a href="../BaseData/projectStaff.html?sId=${list.sId}&projectId=${projectEmploy.projectId}">人员列表</a>--%>
            <a href="../BaseData/authProjectStaff.html?supplierId=${list.sId}&agencyId=${projectEmploy.projectId}&agencyType=${list.sNature}">人员列表</a>

          <%--<a href="../BaseData/defaultPeople.html?employId=${list.sId}&projectId=${projectEmploy.projectId}">对应关系</a>--%>
            <%--<a onClick="javascript:if (confirm('确定删除吗？')) location.href='../BaseData/deleteTender.html?tenderId=${list.tenderId}';else return;" style="cursor: pointer"><spring:message code="common_delete" /></a>--%>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }../BaseData/employManage.html?projectId=${projectEmploy.projectId}&pageIndex={0}"/>
  </div>


</div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script>
  function test() {
      $("#myfile").click();
  }

  function downloadModel() {
    if (confirm("是否下载模板？")) {
      var href = "../BaseData/exportMechanismPeopleModel";
      window.location.href = href;
    } else {
      return;
    }
  }
  function importExcel() {
    //检验导入的文件是否为Excel文件
    var excelPath = document.getElementById("myfile").value;
    if (excelPath == null || excelPath == '') {
      alert("请选择要上传的Excel文件");
      return;
    } else {
      var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();
      if (fileExtend == '.xls' || fileExtend == '.xlsx') {
        if (confirm("您确认要添加'"+excelPath+"'?")) {
          document.getElementById("frm").action = "../BaseData/importMechanismPeopleExcel?projectId=${projectEmploy.projectId}";
          document.getElementById("frm").submit();
        } else {
          return;
        }
      } else {
        alert("文件格式需为'.xls'格式或者'.xlsx格式'");
        return;
      }
    }
  }
</script>
</body>
</html>
