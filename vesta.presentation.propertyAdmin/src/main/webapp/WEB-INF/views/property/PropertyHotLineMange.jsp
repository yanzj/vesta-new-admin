<%--
  Describe:便民信息管理
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/5/18
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="keywords" content=""/>
  <script type="application/x-javascript">
    addEventListener("load",
            function () {
              setTimeout(hideURLbar, 0);
            }, false);
    function hideURLbar() {
      window.scrollTo(0, 1);
    }
  </script>
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
  <script src="../static/property/js/propertyHotLine.js"></script>
  <style>
    .tableStyle thead td, .tableStyle thead th {
      padding-left: 0;
      text-align: center;
    }

    .form_b {
      height: 12.5rem;
    }

    .search_button {
      text-align: center;
    }

    .control_btn {
      padding: 0 0 1rem 0;
      background-color: #fbfbfb;
    }

    .control_btn button, .control_btn a {
      margin-right: 1rem;
    }
  </style>
  <script type="application/javascript">
    function editDisplay() {
      var menuId = $("#menuId").val();
      window.location.href = "../property/gotoEditDisplay?menuId=" + menuId;
    }
  </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="005500010000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body form_b">
        <form:form class="form-horizontal" action="../property/hotLineManagement.html"
                   modelAttribute="hotLineSearch" method="get">
          <input type="hidden" id="menuId" name="menuId" value="005500010000"/>
          <!-- 城市 -->
          <div class="form-group  col-xs-4">
            <label for="queryScope" class="col-sm-4 control-label">区域</label>
            <div class="col-sm-8">
              <select id="queryScope" name="queryScope" class="form-control" onchange="queryProjectNameById()">
                <option value="">请选择</option>
                <c:forEach items="${city}" var="item" >
                  <option value="${item.cityId }"
                          <c:if test="${item.cityId eq hotLineSearch.queryScope}">selected</c:if>>${item.cityName }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 项目 -->
          <div class="form-group  col-xs-4">
            <label for="project" class="col-sm-4 control-label">项目</label>
            <div class="col-sm-8">
              <select id="project" name="project" class="form-control">
                <c:forEach items="${projectList}" var="item" >
                  <option value="${item[0] }"
                          <c:if test="${item[0] eq hotLineSearch.project}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="title" class="col-sm-4 control-label"><spring:message code="propertyServices.name"/></label>
            <div class="col-sm-8">
              <form:input type="text" class="form-control" placeholder="请输入名称"
                          value="${hotLineSearch.title}" path="title"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="memo" class="col-sm-4 control-label">手机号码</label>
            <div class="col-sm-8">
              <form:input type="text" class="form-control" placeholder="请输入手机号码"
                          value="${hotLineSearch.memo}" path="memo"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="announcementContent" class="col-sm-4 control-label"><spring:message
                    code="propertyReport.releaseContent"/></label>
            <div class="col-sm-8">
              <form:input type="text" class="form-control" placeholder="请输入内容"
                          value="${hotLineSearch.announcementContent}" path="announcementContent"/>
            </div>
          </div>
          <div class="clearfix"></div>
          <div class="form-group  col-xs-12 search_button">
            <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                    code="common_search"/></button>
            <button type="button" class="btn btn-primary" onclick="editDisplay()"><spring:message
                    code="common_editDisplay"/></button>
            <button type="button" class="btn btn-primary" onclick="transferPage()"><spring:message
                    code="common_add"/></button>
            <input type="hidden" id="size" value="${isExcel}"/>
            <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>
          </div>
        </form:form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <div class="">

    </div>
    <table width="100%" class="tableStyle table-striped">
      <thead>
      <tr>
        <td style="width:6%;text-align:center"><spring:message code="common_sort"/></td>
        <th><spring:message code="propertyServices.name"/></th>
        <th><spring:message code="propertyServices.city"/></th>
        <th><spring:message code="propertyCompany.propertyProject"/></th>
        <th><spring:message code="propertyServices.sortNum"/></th>
        <th>手机号码</th>
        <th><spring:message code="propertyReport.releaseContent"/></th>
        <%--<th><spring:message code="propertyReport.releaseTime" /></th>--%>
        <th><spring:message code="common_operate"/></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${hotLines}" var="line" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
          <td>${line.title}</td>
          <td>${line.queryScope}</td>
          <td>${line.project}</td>
          <td>${line.sortNumber}</td>
          <td>${line.memo}</td>
            <%--<td>${line.announcementContent}</td>--%>
          <c:if test="${fn:length(line.announcementContent)>'13'}">
            <td>${fn:substring(line.announcementContent,0,13)}...</td>
          </c:if>
          <c:if test="${fn:length(line.announcementContent)<='13'}">
            <td>${line.announcementContent}</td>
          </c:if>
            <%--<td>${line.createTime}</td>--%>
          <td>
              <%--<a href="../property/hotLineDetail.html?announcementId=${line.announcementId}"><spring:message code="common_update" /></a>--%>
            <a href="javascript:void(0);" onclick="js_edit('${line.announcementId}')"><spring:message
                    code="common_update"/></a>
            <a href="javascript:void(0);" onclick="deleteLine('${line.announcementId}')" id="deleteLine"
               class="a3"><spring:message code="common_delete"/></a>

          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/property/hotLineManagement.html?menuId=005500010000&pageIndex={0}&queryScope=${hotLineSearch.queryScope}&project=${hotLineSearch.project}&title=${hotLineSearch.title}&memo=${hotLineSearch.memo}&announcementContent=${hotLineSearch.announcementContent}&startTime=${hotLineSearch.startTime}&endTime=${hotLineSearch.endTime}"/>
  </div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
  var pages = "${webPage.pageIndex}";
  var queryScope = "${hotLineSearch.queryScope}";
  var project = "${hotLineSearch.project}";
  var title = "${hotLineSearch.title}";
  var memo = "${hotLineSearch.memo}";
  var announcementContent = "${hotLineSearch.announcementContent}";
  var startTime = "${hotLineSearch.startTime}";
  var endTime = "${hotLineSearch.endTime}";
</script>
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
  //通过城市获取项目列表
  function queryProjectNameById() {
    $("#project").find("option").remove();
    /* -------------------- */
    var projectId = $("#queryScope").val();
    if(projectId == '-1'){
      $("#project").empty();
      return ;
    }
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
      var arr = data.data;
      $("#project").empty();
      $("#project").append('<option value="">请选择</option>');
      for (var k in arr) {
        if(arr[k][0] != '0'){
          $("#project").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
        }
      }
    });
  }
  function transferPage() {
    $.ajax({
      type: "GET",
      url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
      cache: false,
      async: false,
      dataType: "json",
      success: function (data) {
        if (data.error == 1) {
          window.location.href = "../property/hotLineAdd.html?menuId=" + $("#menuId").val();
        } else if (data.error == 0) {
          alert("对不起，您无权限执行此操作！");
        } else {
          alert("对不起，操作失败！");
        }
      }
    });
  }

  function js_edit(id) {
    var flag = 0;   //操作级别标示
    $.ajax({
      type: "GET",
      url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
      cache: false,
      async: false,
      dataType: "json",
      success: function (data) {
        if (data.error == 1) {
          flag = 1;
        } else if (data.error == 0) {
          alert("对不起，您无权限执行此操作！");
          return;
        } else {
          alert("对不起，操作失败！");
          return;
        }
      }
    });
    var flag2 = 0;  //操作范围标示
    if (flag != 0) {
      $.ajax({
        type: "GET",
        url: "../property/checkEdit/" + id + "/" + $("#menuId").val(),
        cache: false,
        async: false,
        dataType: "json",
        success: function (data) {
          if (data.error == 1) {
            flag2 = 1;
          } else if (data.error == 0) {
            alert("对不起，您的权限范围无法执行此操作！");
            return;
          } else {
            alert("对不起，操作失败！");
            return;
          }
        }
      });
    }
    if (flag != 0 && flag2 != 0) {
      window.location.href = "../property/hotLineDetail.html?announcementId=" + id + "&menuId=" + $("#menuId").val();
    }
  }

  function isExcel() {
    var size = $("#size").val();
    if (size > 0) {
      var href = "../property/exportExcelbm?queryScope=${hotLineSearch.queryScope}&project=${hotLineSearch.project}&title=${hotLineSearch.title}&memo=${hotLineSearch.memo}&announcementContent=${hotLineSearch.announcementContent}";
      window.location.href = href;
    } else {
      alert("没有可以导出的数据");
    }
  }

</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>
