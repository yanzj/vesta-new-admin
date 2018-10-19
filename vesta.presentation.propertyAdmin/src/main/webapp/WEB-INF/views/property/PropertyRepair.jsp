<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <script src="../static/property/js/propertyRepair.js"></script>
  <style>
    .tableStyle thead td,.tableStyle thead th{
      padding-left: 0;
      text-align: center;
    }
    .search_button{
      text-align: center;
    }
    .control_btn{
      padding: 0 0 1rem 0;
      background-color: #fbfbfb;
    }
    .control_btn button,.control_btn a{
      margin-right: 1rem;
    }
  </style>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005700030000" username="${propertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form:form class="form-horizontal" action="../property/workOrderManagement.html" modelAttribute="workOrderSearch" method="get">
          <input type="hidden" id="menuId" name="menuId" value="005700030000"/>
          <!-- 城市 -->
          <div class="form-group  col-xs-4">
            <label for="scopeId" class="col-sm-4 control-label">区域</label>
            <div class="col-sm-8">
              <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                <option value="">请选择</option>
                <c:forEach items="${city}" var="item" >
                  <option value="${item.cityId }"
                          <c:if test="${item.cityId eq workOrderSearch.scopeId}">selected</c:if>>${item.cityName }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 项目 -->
          <div class="form-group  col-xs-4">
            <label for="projectCode" class="col-sm-4 control-label">项目</label>
            <div class="col-sm-8">
              <select id="projectCode" name="projectCode" class="form-control">
                <c:forEach items="${projectList}" var="item" >
                  <option value="${item[0] }"
                          <c:if test="${item[0] eq workOrderSearch.projectCode}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="id" class="col-sm-4 control-label"><spring:message code="workOrders.repairId" /></label>
            <div class="col-sm-8">
              <form:input type="text" class="form-control" placeholder="请输入单号" value="${workOrderSearch.id}" path="id"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="userName" class="col-sm-4 control-label"><spring:message code="workOrders.userName"/></label>
            <div class="col-sm-8">
              <form:input type="text" class="form-control" placeholder="请输入姓名" value="${workOrderSearch.userName}" path="userName"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="userPhone" class="col-sm-4 control-label"><spring:message code="workOrders.userPhone"/></label>
            <div class="col-sm-8">
              <form:input type="text" class="form-control" placeholder="请输入电话" value="${workOrderSearch.userPhone}" path="userPhone"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="repairWay" class="col-sm-4 control-label"><spring:message code="workOrders.resource" /></label>
            <div class="col-sm-8">
              <form:select items="${stateMaps.orderResource}" path="repairWay" class="form-control"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="status" class="col-sm-4 control-label"><spring:message code="workOrders.orderState"/></label>
            <div class="col-sm-8">
              <form:select items="${stateMaps.orderState}" path="status" class="form-control"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="startDate" class="col-sm-4 control-label"><spring:message code="workOrders.startDate"/></label>
            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <form:input type="text" class="form-control" placeholder="请输入开始时间" path="startDate" value="${workOrderSearch.startDate}" readonly="true" />
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="endDate" class="col-sm-4 control-label"><spring:message code="workOrders.endDate"/></label>
            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <form:input type="text" class="form-control" placeholder="请输入结束时间" path="endDate" value="${workOrderSearch.endDate}" readonly="true"/>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="type" class="col-sm-4 control-label"><spring:message code="workOrders.orderType"/></label>
            <div class="col-sm-8">
              <form:select items="${typeMaps.orderType}" path="type" class="form-control"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="completeStartDate" class="col-sm-4 control-label">完成<spring:message code="workOrders.startDate"/></label>
            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <form:input type="text" class="form-control" placeholder="请输入完成开始时间" path="completeStartDate" value="${workOrderSearch.completeStartDate}" readonly="true" />
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="completeEndDate" class="col-sm-4 control-label">完成<spring:message code="workOrders.endDate"/></label>
            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <form:input type="text" class="form-control" placeholder="请输入完成结束时间" path="completeEndDate" value="${workOrderSearch.completeEndDate}" readonly="true"/>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="form-group  col-xs-12 search_button">
            <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_search"/></button>
            <!--集合长度(取决Excel是否可以导出)-->
            <input type="hidden" id="size" value="${isExcel}">
            <a href="javascript:void(0);"  onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>
            <a href="javascript:void(0);"  onclick="exportExcelServiceQuestion()" value="" class="btn btn-primary" style="color:#fff">导出报修评价Excel</a>
          </div>
          <div class="clearfix"></div>
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
        <td style="width:6%;text-align:center"><spring:message code="common_sort" /></td>
        <th><spring:message code="workOrders.id" /></th>
        <th><spring:message code="workOrders.area" /></th>
        <th><spring:message code="workOrders.address" /></th>
        <th><spring:message code="workOrders.name" /></th>
        <th><spring:message code="workOrders.phone" /></th>
        <th><spring:message code="workOrders.createDate" /></th>
        <th><spring:message code="workOrders.repairWay" /></th>
        <th><spring:message code="workOrders.status" /></th>
        <th style="width:6%;"><spring:message code="workOrders.type" /></th>
        <th><spring:message code="common_operate" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${workOrders}" var="workOrder" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
          <td>${workOrder.id}</td>
          <td>${workOrder.project}</td>
          <td>${workOrder.userAddress}</td>
          <td>${workOrder.userName}</td>
          <td>${workOrder.userPhone}</td>
          <td>${workOrder.createDate}</td>
          <td>${workOrder.repairWay}</td>
            <%--<td>
              <c:choose>
                <c:when test="${fn:length(workOrder.id) > 5}">
                  <c:out value="${fn:substring(workOrder.id, 0, 5)}..." />
                </c:when>
                <c:otherwise>
                  <c:out value="${workOrder.id}" />
                </c:otherwise>
              </c:choose>
            </td>--%>
          <td>
              ${workOrder.status}
          </td>
          <td>${workOrder.memo}</td>
          <td class="last">
            <a href="../property/repairDetail.html?id=${workOrder.id}"><spring:message code="workOrders.detail" /></a><!--详情-->
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/property/workOrderManagement.html?menuId=005700030000&pageIndex={0}&scopeId=${workOrderSearch.scopeId}&projectCode=${workOrderSearch.projectCode}&repairWay=${workOrderSearch.repairWay}&id=${workOrderSearch.id}&userName=${workOrderSearch.userName}&userPhone=${workOrderSearch.userPhone}&startDate=${workOrderSearch.startDate}&endDate=${workOrderSearch.endDate}&status=${workOrderSearch.status}&type=${workOrderSearch.type}"/>
  </div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
  var pages = "${webPage.pageIndex}";
  var project = "${workOrderSearch.project}";
  var repairWay = "${workOrderSearch.repairWay}";
  var id = "${workOrderSearch.id}";
  var userName = "${workOrderSearch.userName}";
  var userPhone = "${workOrderSearch.userPhone}";
  var type = "${workOrderSearch.type}";
  var startDate = "${workOrderSearch.startDate}";
  var endDate = "${workOrderSearch.endDate}";
  var status = "${workOrderSearch.status}";
</script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
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
    $("#projectCode").find("option").remove();
    /* -------------------- */
    var projectId = $("#scopeId").val();
    if(projectId == '-1'){
      $("#projectCode").empty();
      return ;
    }
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
      var arr = data.data;
      $("#projectCode").empty();
      $("#projectCode").append('<option value="">请选择</option>');
      for (var k in arr) {
        if(arr[k][0] != '0'){
          $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
        }
      }
    });
  }
  function isExcel(){
    var size = $("#size").val();
    if(size>0){
      var href = "../property/exportExcel?type=1&pageIndex={0}&scopeId=${workOrderSearch.scopeId}&projectCode=${workOrderSearch.projectCode}&repairWay=${workOrderSearch.repairWay}&id=${workOrderSearch.id}&userName=${workOrderSearch.userName}&userPhone=${workOrderSearch.userPhone}&startDate=${workOrderSearch.startDate}&endDate=${workOrderSearch.endDate}&status=${workOrderSearch.status}&type=${workOrderSearch.type}";
      window.location.href = href;
    }else{
      alert("没有可以导出的数据");
    }
  }
  function exportExcelServiceQuestion(){
    var msg = "导出每条报修单的服务评价内容可能耗时过长，建议选择搜索条件（如选择状态为已评价），当前确认导出么？";
    if (confirm(msg)==true){
        var size = $("#size").val();
        if(size>0){
            var href = "../property/exportExcelServiceQuestion?type=1&pageIndex={0}&scopeId=${workOrderSearch.scopeId}&projectCode=${workOrderSearch.projectCode}&repairWay=${workOrderSearch.repairWay}&id=${workOrderSearch.id}&userName=${workOrderSearch.userName}&userPhone=${workOrderSearch.userPhone}&startDate=${workOrderSearch.startDate}&endDate=${workOrderSearch.endDate}&status=${workOrderSearch.status}&type=${workOrderSearch.type}";
            window.location.href = href;
        }else{
            alert("没有可以导出的数据");
        }
    }else{
        return false;
    }
  }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>