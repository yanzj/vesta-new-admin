<%--
  Describe:业主预约管理
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/4/14
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<style type="text/css">
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
  .widthControl4{
    width: 4rem;
  }
</style>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005400020000" username="${propertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form:form class="form-horizontal" action="../userAppoint/userAppointManagement.html" modelAttribute="userAppointDto">
          <input type="hidden" id="menuId" name="menuId" value="005400020000"/>
          <%--
          <div class="form-group  col-lg-4">
            <label for="projectName" class="col-sm-4 control-label"><spring:message code="activityManage.activityProjectName"/></label>
            <div class="col-sm-8">
                &lt;%&ndash;<form:select items="${projectName}" path="projectName" class="form-control"/>&ndash;%&gt;
              <form:input type="text" class="form-control" placeholder="请输入项目名称" value="${userAppointDto.projectName}" path="projectName"/>
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
                          <c:if test="${item.cityId eq userAppointDto.scopeId}">selected</c:if>>${item.cityName }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 项目 -->
          <div class="form-group  col-xs-4">
            <label for="projectName" class="col-sm-4 control-label">项目</label>
            <div class="col-sm-8">
              <select id="projectName" name="projectName" class="form-control">
                <c:forEach items="${projectList}" var="item" >
                  <option value="${item[1] }"
                          <c:if test="${item[1] eq userAppointDto.projectName}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <%--手机号--%>
          <div class="form-group  col-xs-4">
            <label for="userMobile" class="col-sm-4 control-label">手机号码</label>
            <div class="col-sm-8">
              <form:input type="text" class="form-control" placeholder="请输入手机号" value="${userAppointDto.userMobile}" path="userMobile"/>
            </div>
          </div>
          <%--业主姓名--%>
          <div class="form-group  col-xs-4">
            <label for="userName" class="col-sm-4 control-label">业主姓名</label>
            <div class="col-sm-8">
              <form:input type="text" class="form-control" placeholder="请输入业主姓名" value="${userAppointDto.userName}" path="userName"/>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="startTime" class="col-sm-4 control-label"><spring:message code="activityManage.appointappointUserTime"/></label>
            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy/mm/dd"
                 data-link-field="dtp_input2">
              <form:input type="text" class="form-control" placeholder="" path="startTime" value="${userAppointDto.startTime}" readonly="true" />
              <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="form-group  col-xs-4">
            <label for="endTime" class="col-sm-4 control-label"><spring:message code="HousePayBatch.to"/></label>
            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy/mm/dd" data-link-field="dtp_input2">
              <form:input type="text" class="form-control" placeholder="" path="endTime" value="${userAppointDto.endTime}" readonly="true"/>
              <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <%--时间段--%>
          <div class="form-group  col-xs-4">
            <label for="amOrPm" class="col-sm-4 control-label"><spring:message code="activityManage.appointAmOrPm"/></label>
            <div class="col-sm-8">
              <form:select items="${timeName}" path="amOrPm" class="form-control"/>
            </div>
          </div>
          <div class="form-group  col-xs-12 search_button">
            <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_search"/></button>
            <!--集合长度(取决Excel是否可以导出)-->
            <input type="hidden" id="size" value="${isExcel}">
            <a href="javascript:void(0);"  onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>

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
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="activityManage.activitySort" /></td>
        <th><spring:message code="activityManage.appointName" /></th>
        <th>手机号码</th>
        <th><spring:message code="activityManage.activityProjectName" /></th>
        <th><spring:message code="activityManage.activityBuildName" /></th>
        <th><spring:message code="activityManage.activityUnitName" /></th>
        <th><spring:message code="activityManage.activityRoomName" /></th>
        <th><spring:message code="activityManage.appointIdCard" /></th>
        <th><spring:message code="activityManage.appointappointUserTime" /></th>
        <th class="widthControl4"><spring:message code="activityManage.appointAmOrPm" /></th>
        <th><spring:message code="activityManage.appointBatch" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${userAppointDtos}" var="appoint" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
          <td>${appoint.userName}</td>
          <td>${appoint.userMobile}</td>
          <td>${appoint.projectName}</td>
          <td>${appoint.buildName}</td>
          <td>${appoint.unitName}</td>
          <td>${appoint.roomName}</td>
          <td>${appoint.idCard}</td>
          <td>${appoint.appointUserTime}</td>
          <td>${appoint.amOrPm}</td>
          <td>${appoint.planName}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/userAppoint/userAppointManagement.html?menuId=005400020000&pageIndex={0}&scopeId=${userAppointDto.scopeId}&projectName=${userAppointDto.projectName}&buildName=${userAppointDto.buildName}&unitName=${userAppointDto.unitName}&planName=${userAppointDto.planName}&amOrPm=${userAppointDto.amOrPm}&userMobile=${userAppointDto.userMobile}&startTime=${userAppointDto.startTime}&endTime=${userAppointDto.endTime}"/>
  </div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
  var pages = "${webPage.pageIndex}";
  var projectName = "${userAppointDto.projectName}";
  var buildName = "${userAppointDto.buildName}";
  var unitName = "${userAppointDto.unitName}";
  var planName = "${userAppointDto.planName}";
  var amOrPm = "${userAppointDto.amOrPm}";
  var userMobile = "${userAppointDto.userMobile}";
  var startTime = "${userAppointDto.startTime}";
  var endTime = "${userAppointDto.endTime}";
</script>
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
  //通过城市获取项目列表
  function queryProjectNameById() {
    $("#projectName").find("option").remove();
    /* -------------------- */
    var projectId = $("#scopeId").val();
    if(projectId == '-1'){
      $("#projectName").empty();
      return ;
    }
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
      var arr = data.data;
      $("#projectName").empty();
      $("#projectName").append('<option value="">请选择</option>');
      for (var k in arr) {
        if(arr[k][0] != '0'){
          $("#projectName").append('<option value=' + arr[k][1] + '>' + arr[k][1] + '</option>');
        }
      }
    });
  }
  function isExcel(){
    var size = $("#size").val();
    if(size>0){
      var href = "../userAppoint/exportExcel?type=2&pageIndex={0}&scopeId=${userAppointDto.scopeId}&projectName=${userAppointDto.projectName}&buildName=${userAppointDto.buildName}&unitName=${userAppointDto.unitName}&planName=${userAppointDto.planName}&amOrPm=${userAppointDto.amOrPm}&userMobile=${userAppointDto.userMobile}&startTime=${userAppointDto.startTime}&endTime=${userAppointDto.endTime}";
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
