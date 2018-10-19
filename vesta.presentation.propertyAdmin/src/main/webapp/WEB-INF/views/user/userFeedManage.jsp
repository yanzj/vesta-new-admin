<%--
  Describe:意见反馈
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/5/15
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
  <script src="../static/property/js/propertyRepair.js"></script>
  <style>
    .tableStyle thead td,.tableStyle thead th{
      padding-left: 0;
      text-align: center;
    }
    .form_b{
      height: 20rem;
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
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005700040000" username="${propertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body form_b">
        <form:form class="form-horizontal" action="../userFeed/feedbackManage.html" modelAttribute="feedBackSearch" method="get">
          <input type="hidden" id="menuId" name="menuId" value="005700040000"/>
          <%--
          <div class="form-group  col-lg-4">
            <label for="queryScope" class="col-sm-4 control-label"><spring:message code="ownerManage.Project" /></label>
            <div class="col-sm-8">
              <form:input type="text" class="form-control" placeholder="请输入项目名称" value="${feedBackSearch.queryScope}" path="queryScope"/>
            </div>
          </div>
          --%>
          <!-- 城市 -->
          <div class="form-group  col-xs-4">
            <label for="scopeId" class="col-sm-4 control-label">区域</label>
            <div class="col-sm-8">
              <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                <option value="">请选择</option>
                <c:forEach items="${city}" var="item" >
                  <option value="${item.cityId }"
                          <c:if test="${item.cityId eq feedBackSearch.scopeId}">selected</c:if>>${item.cityName }</option>
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
                          <c:if test="${item[0] eq feedBackSearch.projectCode}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="userType" class="col-sm-4 control-label"><spring:message code="ownerManage.UserType" /></label>
            <div class="col-sm-8">
              <form:select items="${feedbackMaps.userTypeMap}" path="userType" class="form-control"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="status" class="col-sm-4 control-label"><spring:message code="billInfo.processStatus" /></label>
            <div class="col-sm-8">
              <form:select items="${feedbackMaps.stateMap}" path="status" class="form-control"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="memo" class="col-sm-4 control-label"><spring:message code="workOrders.repairWay"/></label>
            <div class="col-sm-8">
              <form:select items="${feedbackMaps.resourceMap}" path="memo" class="form-control"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="phone" class="col-sm-4 control-label">手机号码</label>
            <div class="col-sm-8">
              <form:input type="text" class="form-control" placeholder="请输入手机号" value="${feedBackSearch.phone}" path="phone"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="content" class="col-sm-4 control-label"><spring:message code="operationLog.content" /></label>
            <div class="col-sm-8">
              <form:input type="text" class="form-control" placeholder="请输入内容" value="${feedBackSearch.content}" path="content"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="startDate" class="col-sm-4 control-label"><spring:message code="userFeed.startDate"/></label>
            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <form:input type="text" class="form-control" placeholder="请输入开始时间" path="startDate" value="${feedBackSearch.startDate}" readonly="true" />
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="endDate" class="col-sm-4 control-label"><spring:message code="HousePayBatch.to"/></label>
            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <form:input type="text" class="form-control" placeholder="请输入结束时间" path="endDate" value="${feedBackSearch.endDate}" readonly="true"/>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="fbClassification" class="col-sm-4 control-label">意见反馈来源分类</label>
            <div class="col-sm-8">
              <form:select items="${feedbackMaps.fbMap}" path="fbClassification" class="form-control"/>
            </div>
          </div>
          <div class="clearfix"></div>
          <div class="form-group  col-xs-12 search_button">
            <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_search"/></button>
            <!--集合长度(取决Excel是否可以导出)-->
            <input type="hidden" id="size" value="${isExcel}"/>
            <a href="javascript:void(0);"  onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>

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
        <td style="width:6%;text-align:center"><spring:message code="common_sort" /></td>
        <th>手机号码</th>
        <th><spring:message code="ownerManage.UserType" /></th>
        <th><spring:message code="operationLog.content" /></th>
        <th><spring:message code="userFeed.feedDate" /></th>
        <th><spring:message code="ownerManage.Project" /></th>
        <th><spring:message code="workOrders.repairWay" /></th>
        <th><spring:message code="billInfo.processStatus" /></th>
        <%--&lt;%&ndash;&ndash;%&gt;意见反馈来源分类   1便民信息纠错 2:意见反馈--%>
        <th>意见反馈来源分类</th>
        <th><spring:message code="common_operate" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${feedBacks}" var="feed" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
          <td>${feed.phone}</td>
          <td>${feed.userType}</td>
            <%--<td>${feed.content}</td>--%>
          <td>
            <c:if test="${fn:length(feed.content) > 30}">${fn:substring(feed.content,0,30)}......</c:if>
            <c:if test="${fn:length(feed.content) <= 30}">${feed.content}</c:if>
          </td>
          <td>${feed.startDate}</td>
          <td>${feed.queryScope}</td>
          <td>${feed.feedbackType}</td>
          <td>${feed.status}</td>
          <td>${feed.fbClassification}</td>
          <td>
            <c:choose>
              <c:when test="${feed.state ==1}">
                <%--<a href="../userFeed/feedbackUpdate.html?id=${feed.id}"><spring:message code="userFeed.deal" /></a>--%><!--处理-->
                <a href="javascript:void(0);" onclick="js_handle('${feed.id}')"><spring:message code="userFeed.deal" /></a>
              </c:when>
              <c:otherwise>
                <a href="javascript:void(0);" onclick="javascript:alert('处理状态只能在未处理时可编辑！');"><spring:message code="userFeed.deal" /></a><!--处理-->
              </c:otherwise>
            </c:choose>
            <a href="../userFeed/feedbackDetail.html?id=${feed.id}"><spring:message code="workOrders.detail" /></a><!--详情-->
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/userFeed/feedbackManage.html?menuId=005700040000&pageIndex={0}&scopeId=${feedBackSearch.scopeId}&projectCode=${feedBackSearch.projectCode}&userType=${feedBackSearch.userType}&status=${feedBackSearch.status}&memo=${feedBackSearch.memo}&phone=${feedBackSearch.phone}&content=${feedBackSearch.content}&startDate=${feedBackSearch.startDate}&endDate=${feedBackSearch.endDate}"/>
  </div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
  var pages = "${webPage.pageIndex}";
  var queryScope = "${feedBackSearch.queryScope}";
  var userType = "${feedBackSearch.userType}";
  var status = "${feedBackSearch.status}";
  var memo = "${feedBackSearch.memo}";
  var phone = "${feedBackSearch.phone}";
  var content = "${feedBackSearch.content}";
  var startDate = "${feedBackSearch.startDate}";
  var endDate = "${feedBackSearch.endDate}";
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
  //处理
  function js_handle(id){
    $.ajax({
      type: "GET",
      url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
      cache: false,
      async:false,
      dataType:"json",
      success: function (data) {
        if (data.error == 1) {
          $.ajax({
            type: "GET",
            url: "../userFeed/checkEdit/"+id+"/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
              if (data.error == 1) {
                window.location.href = "../userFeed/feedbackUpdate.html?id=" + id;
              }else if(data.error == 0) {
                alert("对不起，您的权限范围无法执行此操作！");
                return ;
              }else{
                alert("对不起，操作失败！");
                return ;
              }
            }
          });
        }else if(data.error == 0) {
          alert("对不起，您无权限执行此操作！");
          return ;
        }else{
          alert("对不起，操作失败！");
          return ;
        }
      }
    });
  }
  function isExcel(){
    var size = $("#size").val();
    if(size>0){
      var href = "../userFeed/exportExcel?type=1&pageIndex={0}&scopeId=${feedBackSearch.scopeId}&projectCode=${feedBackSearch.projectCode}&userType=${feedBackSearch.userType}&status=${feedBackSearch.status}&memo=${feedBackSearch.memo}&phone=${feedBackSearch.phone}&content=${feedBackSearch.content}&startDate=${feedBackSearch.startDate}&endDate=${feedBackSearch.endDate}&fbClassification=${feedBackSearch.fbClassification}";
      window.location.href = href;
    }else{
      alert("没有可以导出的数据");
    }
  }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>
