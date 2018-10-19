<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/3
  Time: 13:06
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
  .form_b{
    height: 15.5rem;
  }
  .tableStyle .projectName{
    width: 30rem;
  }
  .tableStyle .projectT2{
    width: 15rem;
  }
  .tableStyle .activityState{
    width: 4rem;
  }
  .widC5{
    width: 5rem;
  }

</style>
<script type="text/javascript">
  function goSubmit(id) {
    document.getElementById("rolesetId").value = id;
    document.getElementById("search_form").submit();
  }
  function deleteLine(id) {
    $.ajax({
      type: "GET",
      url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
      cache: false,
      async: false,
      dataType: "json",
      success: function (data) {
        if (data.error == 1) {
          $.ajax({
            type: "GET",
            url: "../community/checkEdit/" + id + "/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
              if (data.error == 1) {
                if (confirm("确定要删除吗?")) {
                  window.location.href = "../community/activityDelete?activityId=" + id;
                }
              } else if (data.error == 0) {
                alert("对不起，您的权限范围无法执行此操作！");
                return;
              } else {
                alert("对不起，操作失败！");
                return;
              }
            }
          });
        } else if (data.error == 0) {
          alert("对不起，您无权限执行此操作！");
        } else {
          alert("对不起，操作失败！");
        }
      }
    });
  }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="005400030000" username="${propertystaff.staffName}"/>
  <input type="hidden" id="menuId" value="005400030000"/>

  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body form_b">
        <form class="form-horizontal" action="../community/activityManage.html">
          <%--城市--%>
          <div class="form-group  col-xs-4">
            <label for="cu_projectId" class="col-sm-4 control-label">城市</label>

            <div class="col-sm-8">
              <select id="city" name="city" class="form-control" onchange="queryProjectNameById()">
                <option value="-1">--请选择城市--</option>
                <c:forEach items="${city}" var="item">
                  <option value="${item.cityId }"
                          <c:if test="${item.cityId eq activityAdminDto.city}">selected</c:if>>${item.cityName }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <%--项目--%>
          <div class="form-group  col-xs-4">
            <label for="cu_projectId" class="col-sm-4 control-label"><spring:message
                    code="activityManage.activityProjectName"/></label>

            <div class="col-sm-8">
              <select class="form-control" placeholder="" id="cu_projectId" name="cu_projectId">
                <c:forEach items="${projectList}" var="project">
                  <option value="${project[1]}"
                          <c:if test="${project[1] eq activityAdminDto.cu_projectId}">selected</c:if>>${project[1]}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <%--报名类型--%>
          <div class="form-group  col-xs-4">
            <label for="types" class="col-sm-4 control-label"><spring:message
                    code="activityManage.activityTypes"/></label>
            <div class="col-sm-8">
              <select class="form-control" placeholder="" id="types" name="types">
                <option value="0" <c:if test="${activityAdminDto.types eq '0'}">selected</c:if>>
                  <spring:message code="activityManage.activityTypes_all"/></option>
                <option value="1" <c:if test="${activityAdminDto.types eq '1'}">selected</c:if>>
                  <spring:message code="activityManage.activityTypes_pub"/></option>
                <option value="2" <c:if test="${activityAdminDto.types eq '2'}">selected</c:if>>
                  <spring:message code="activityManage.activityTypes_pra"/></option>
              </select>
            </div>
          </div>
          <%--活动状态--%>
          <div class="form-group  col-xs-4">
            <label for="status" class="col-sm-4 control-label"><spring:message
                    code="activityManage.activityStatus"/></label>
            <div class="col-sm-8">
              <select class="form-control" placeholder="" id="status" name="status">
                <option value="0"><spring:message code="activityManage.activityStatus_0"/></option>
                <option value="1" <c:if test="${activityAdminDto.status eq '1'}">selected</c:if>>
                  <spring:message code="activityManage.activityStatus_1"/></option>
                <option value="2" <c:if test="${activityAdminDto.status eq '2'}">selected</c:if>>
                  <spring:message code="activityManage.activityStatus_2"/></option>
                <option value="3" <c:if test="${activityAdminDto.status eq '3'}">selected</c:if>>
                  <spring:message code="activityManage.activityStatus_3"/></option>
                <option value="4" <c:if test="${activityAdminDto.status eq '4'}">selected</c:if>>
                  <spring:message code="activityManage.activityStatus_4"/></option>

                <option value="5" <c:if test="${activityAdminDto.status eq '5'}">selected</c:if>>
                  <spring:message code="activityManage.activityStatus_5"/></option>
              </select>
            </div>
          </div>
          <%--活动主题--%>
          <div class="form-group  col-xs-4">
            <label for="title" class="col-sm-4 control-label"><spring:message
                    code="activityManage.activityTitle"/></label>

            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="title"
                     name="title" value="${activityAdminDto.title}">
            </div>
          </div>
          <%--活动范围--%>
          <div class="form-group  col-xs-4">
            <label for="scope" class="col-sm-4 control-label"><spring:message
                    code="activityManage.activityProject"/></label>

            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="scope"
                     name="scope" value="${activityAdminDto.scope}">
            </div>
          </div>
          <%--活动类型--%>
          <div class="form-group  col-xs-4">
            <label for="activityType" class="col-sm-4 control-label">活动类型</label>
            <div class="col-sm-8">
              <select class="form-control" id="activityType" name="activityType">
                <option value="" <c:if test="${activityAdminDto.activityType eq ''}">selected</c:if>>请选择</option>
                <option value="1" <c:if test="${activityAdminDto.activityType eq '1'}">selected</c:if>>普通活动</option>
                <option value="2" <c:if test="${activityAdminDto.activityType eq '2'}">selected</c:if>>营销活动</option>
                <option value="3" <c:if test="${activityAdminDto.activityType eq '3'}">selected</c:if>>商业活动</option>
                <option value="4" <c:if test="${activityAdminDto.activityType eq '4'}">selected</c:if>>总部活动</option>
              </select>
            </div>
          </div>
          <%--是否作为宣传位--%>
          <div class="form-group  col-xs-4">
            <label for="isBanner" class="col-sm-4 control-label">是否作为宣传位</label>
            <div class="col-sm-8">
              <select class="form-control" id="isBanner" name="isBanner">
                <option value="" <c:if test="${activityAdminDto.isBanner eq ''}">selected</c:if>>请选择</option>
                <option value="0" <c:if test="${activityAdminDto.isBanner eq '0'}">selected</c:if>>否</option>
                <option value="1" <c:if test="${activityAdminDto.isBanner eq '1'}">selected</c:if>>是</option>
              </select>
            </div>
          </div>
          <div class="form-group  col-xs-12 search_button">
            <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                    code="propertyCompany.propertySearch"/></button>
            <%--<a  href="../community/gotoActivityAdd" class="btn btn-primary" for="rolesetAdd" ><spring:message code="common_add"/></a>--%>
            <a href="javascript:void(0);" onclick="toAdd()" class="btn btn-primary" for="rolesetAdd"><spring:message
                    code="common_add"/></a>
            <input type="hidden" id="size" value="${isExcel}"/>
            <%--<a href="../community/exportExcelhd"  value="" class="btn btn-primary" style="color:#fff">导出Excel</a>--%>
            <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>

          </div>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <div class="">
  </div>
    <form action="../community/activityManage" method="post" id="search_form">

      <input id="activityId" type="hidden" name="rolesetId" value="">
    </form>
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="activityManage.activitySort"/></td>
        <th class=""><spring:message code="activityManage.activityTitle"/></th>
        <th><spring:message code="activityManage.activityDate"/></th>
        <th><spring:message code="activityManage.activityProject"/></th>
        <th class=""><spring:message code="activityManage.activityProjectName"/></th>
        <th class=""><spring:message code="activityManage.activityTypes"/></th>
        <th>是否作为宣传位</th>
        <th><spring:message code="activityManage.activityCountApply"/></th>
        <th class=""><spring:message code="activityManage.activityOperator"/></th>
        <th>操作时间</th>
        <th class=""><spring:message code="activityManage.activityStatus"/></th>
        <th><spring:message code="common_operate"/></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${activityAdminDtos}" var="activity" varStatus="vs">
        <tr>
          <td><b>${vs.count}</b></td>
          <td style="
    width: 9rem;
">${activity.title}</td>
          <td>${activity.activityDate}</td>
          <td>${activity.address}</td>
          <td style="
    width: 23rem;
">${activity.cu_projectName}</td>
            <%--<td>${activity.scope}</td>--%>
          <td>
            <c:if test="${activity.types eq '1'}"><spring:message code="activityManage.activityTypes_pub"/></c:if>
            <c:if test="${activity.types eq '2'}"><spring:message code="activityManage.activityTypes_pra"/></c:if>
          </td>
          <td>
            <c:if test="${activity.isBanner eq '0'}">否</c:if>
            <c:if test="${activity.isBanner eq '1'}">是</c:if>
          </td>
          <td>${activity.countApply}</td>
          <td>${activity.operator}</td>
          <td>${activity.makedate}</td>
          <td>
              <%-- 0-未发布，--%>
            <c:if test="${activity.pushState eq '0'}"><spring:message
                    code="activityManage.activityStatus_5"/></c:if>

              <%--1-已发布--%>
            <c:if test="${activity.pushState eq '1'}">
              <c:if test="${activity.status eq '1'}"><spring:message
                      code="activityManage.activityStatus_1"/></c:if>
              <c:if test="${activity.status eq '2'}"><spring:message
                      code="activityManage.activityStatus_2"/></c:if>
              <c:if test="${activity.status eq '3'}"><spring:message
                      code="activityManage.activityStatus_3"/></c:if>
              <c:if test="${activity.status eq '4'}"><spring:message
                      code="activityManage.activityStatus_4"/></c:if>
              <c:if test="${activity.status eq '5'}"><spring:message
                      code="activityManage.activityStatus_5"/></c:if>
            </c:if>

          </td>
          <td class="last">
            <a href="../community/ActivityProjectApply.html?menuId=005400030000&activityId=${activity.activityId}"
               class="a2">报名详情</a>
            <a href="javascript:void(0);" onclick="toEdit('${activity.activityId}')" class="a2">活动修改</a>
            <a href="javascript:void(0);" onclick="toHouseScope('${activity.activityId}')" class="a2">房产范围</a>
            <a href="javascript:void(0);" onclick="deleteLine('${activity.activityId}')" class="a3"><span
                    class="span1"><spring:message code="common_delete"/></span></a>
              <%--href="javascript:void(0);" onclick="deleteLine('${line.announcementId}')"--%>

          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath}/community/activityManage.html?pageIndex={0}&types=${activityAdminDto.types}&cu_projectId=${activityAdminDto.cu_projectId}&status=${activityAdminDto.status}&title=${activityAdminDto.title}&scope=${activityAdminDto.scope}&city=${activityAdminDto.city}"/>
  </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
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
  function toAdd() {
    $.ajax({
      type: "GET",
      url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
      cache: false,
      async: false,
      dataType: "json",
      success: function (data) {
        if (data.error == 1) {
          window.location.href = "../community/gotoActivityAdd?menuId=" + $("#menuId").val();
        } else if (data.error == 0) {
          alert("对不起，您无权限执行此操作！");
        } else {
          alert("对不起，操作失败！");
        }
      }
    });
  }

  function toEdit(id) {
    $.ajax({
      type: "GET",
      url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
      cache: false,
      async: false,
      dataType: "json",
      success: function (data) {
        if (data.error == 1) {
          $.ajax({
            type: "GET",
            url: "../community/checkEdit/" + id + "/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
              if (data.error == 1) {
                window.location.href = "../community/gotoActivityEdit?activityId=" + id + "&menuId=" + $("#menuId").val();
              } else if (data.error == 0) {
                alert("对不起，您的权限范围无法执行此操作！");
                return;
              } else {
                alert("对不起，操作失败！");
                return;
              }
            }
          });
        } else if (data.error == 0) {
          alert("对不起，您无权限执行此操作！");
        } else {
          alert("对不起，操作失败！");
        }
      }
    });
  }

  function toHouseScope(id){
      $.ajax({
          type: "GET",
          url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
          cache: false,
          async: false,
          dataType: "json",
          success: function (data) {
              if (data.error == 1) {
                  $.ajax({
                      type: "GET",
                      url: "../community/checkEdit/" + id + "/" + $("#menuId").val(),
                      cache: false,
                      async: false,
                      dataType: "json",
                      success: function (data) {
                          if (data.error == 1) {
                              window.location.href = "../community/toAssignHouseScope.html?activityId=" + id;
                          } else if (data.error == 0) {
                              alert("对不起，您的权限范围无法执行此操作！");
                              return;
                          } else {
                              alert("对不起，操作失败！");
                              return;
                          }
                      }
                  });
              } else if (data.error == 0) {
                  alert("对不起，您无权限执行此操作！");
              } else {
                  alert("对不起，操作失败！");
              }
          }
      });
  }

  function isExcel() {
    var size = $("#size").val();
    if (size > 0) {
      var href = "../community/exportExcelhd?types=${activityAdminDto.types}&cu_projectId=${activityAdminDto.cu_projectId}&status=${activityAdminDto.status}&title=${activityAdminDto.title}&scope=${activityAdminDto.scope}";
      window.location.href = href;
    } else {
      alert("没有可以导出的数据");
    }
  }

  function queryProjectNameById() {
    var projectId = $("#city").val();
    if (projectId == '-1') {
      $("#cu_projectId").empty();
      return;
    }
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId + "/" + $("#menuId").val(), function (data) {
      var arr = data.data;
      $("#cu_projectId").empty();
      for (var k in arr) {
        if (arr[k][1] != '全部项目') {
          $("#cu_projectId").append('<option value=' + arr[k][1] + '>' + arr[k][1] + '</option>');
        }
      }
    });
  }
</script>
</body>
</html>