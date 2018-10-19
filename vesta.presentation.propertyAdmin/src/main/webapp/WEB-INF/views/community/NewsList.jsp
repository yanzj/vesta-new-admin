<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
  <script src="../static/property/js/propertyHousPay.js"></script>
</head>
<style type="text/css">
  .form_b {
    height: 15.5rem;
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
<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="005300010000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body form_b">
        <form class="form-horizontal" action="../communityNews/NewsList.html">
          <input type="hidden" id="menuId" name="menuId" value="005300010000"/>
          <!-- 城市 -->
          <div class="form-group  col-xs-4">
            <label for="scopeId" class="col-sm-3 control-label">区域</label>

            <div class="col-sm-8">
              <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                <option value="">请选择</option>
                <c:forEach items="${city}" var="item">
                  <option value="${item.cityId }"
                          <c:if test="${item.cityId eq communityNewsDto.scopeId}">selected</c:if>>${item.cityName }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 项目 -->
          <div class="form-group  col-xs-4">
            <label for="projectCode" class="col-sm-3 control-label">项目</label>

            <div class="col-sm-8">
              <select id="projectCode" name="projectCode" class="form-control">
                <c:forEach items="${projectList}" var="item">
                  <option value="${item[0] }"
                          <c:if test="${item[0] eq communityNewsDto.projectCode}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 新闻标题 -->
          <div class="form-group  col-xs-4">
            <label for="title" class="col-sm-4 control-label"><spring:message
                    code="CommunityNews.Title"/></label>

            <div class="col-sm-8">
              <input type="text" class="form-control"
                     placeholder="<spring:message code="Community.pleaseSelect"/><spring:message code="CommunityNews.Title"/>"
                     id="title"
                     name="title" value="${communityNewsDto.title}">
            </div>
          </div>
          <!-- 发布人 -->
          <div class="form-group  col-xs-4">
            <label for="releasePerson" class="col-sm-3 control-label"><spring:message
                    code="CommunityNews.deploy"/></label>

            <div class="col-sm-8">
              <input type="text" class="form-control"
                     placeholder="<spring:message code="Community.pleaseSelect"/><spring:message code="CommunityNews.deploy"/>"
                     id="releasePerson"
                     name="releasePerson" value="${communityNewsDto.releasePerson}">
            </div>
          </div>
          <!-- 类型 -->
          <div class="form-group  col-xs-4">
            <label for="type" class="col-sm-3 control-label"><spring:message
                    code="workOrders.type"/></label>

            <div class="col-sm-8">
              <select id="type" name="type" class="form-control">
                <c:forEach items="${typeMap}" var="item">
                  <option value="${item.id }"
                          <c:if test="${item.id eq communityNewsDto.type}">selected</c:if>>
                      ${item.name }
                  </option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 状态 -->
          <div class="form-group  col-xs-4">
            <label for="releaseStatus" class="col-sm-4 control-label"><spring:message
                    code="advertServices.state"/></label>

            <div class="col-sm-8">
              <select id="releaseStatus" name="releaseStatus" class="form-control">
                <c:forEach items="${statusMap}" var="item">
                  <option value="${item.id }"
                          <c:if test="${item.id eq communityNewsDto.releaseStatus}">selected</c:if>>
                      ${item.name }
                  </option>
                </c:forEach>
              </select>
            </div>
          </div>

          <!-- 发布日期开始日期 -->
          <div class="form-group  col-xs-4">
            <label for="staDate" class="col-sm-3 control-label"><spring:message
                    code="propertyAnnouncement.createTimeAndEnd"/></label>

            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="staDate"
                     value="${communityNewsDto.staDate}" name="staDate" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>

          <!-- 发布日期结束日期 -->
          <div class="form-group  col-xs-4">
            <label for="endDate" class="col-sm-3 control-label"><spring:message
                    code="HousePayBatch.to"/></label>

            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="endDate"
                     value="${communityNewsDto.endDate}" name="endDate" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>

          <div class='clearfix'></div>
          <div class="form-group  col-xs-12 search_button">
            <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                    code="propertyCompany.propertySearch"/></button>
            <!--集合长度(取决Excel是否可以导出)-->
            <input type="hidden" id="size" value="${isExcel}">
            <%--<a  href="../communityNews/transferPage" class="btn btn-primary" for="payBatchAdd" ><spring:message code="common_add"/></a>--%>
            <button type="button" class="btn btn-primary" onclick="transferPage()"><spring:message
                    code="common_add"/></button>
            <a href="#" onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>

          </div>
        </form>
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
        <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
        <th><spring:message code="CommunityNews.Title"/></th>
        <th><spring:message code="CommunityNews.deploy"/></th>
        <th>项目范围</th>
        <th><spring:message code="CommunityNews.type"/></th>
        <th><spring:message code="advertServices.state"/></th>
        <th><spring:message code="advertServices.Time"/></th>
        <th width="174"><spring:message code="billInfo.opera"/></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${communityNewsDtos}" var="communityNewsDtos" varStatus="row">
        <tr>

          <td><b>${row.index + 1}</b></td>
          <td>${communityNewsDtos.title}</td>
          <td>${communityNewsDtos.releasePerson}</td>
          <td>${communityNewsDtos.projects}</td>
          <td>
            <c:if test="${communityNewsDtos.type lt 2}"><spring:message
                    code="CommunityNews.publicity"/></c:if>
            <c:if test="${communityNewsDtos.type eq 2 }"><spring:message
                    code="CommunityNews.newsInformation"/></c:if>
          </td>
          <td>
            <c:if test="${communityNewsDtos.releaseStatus eq 0}"><spring:message
                    code="activityManage.activityStatus_5"/></c:if>
            <c:if test="${communityNewsDtos.releaseStatus eq 1 }"><spring:message
                    code="activityManage.activityStatus_6"/></c:if>
          </td>
          <td>${communityNewsDtos.releaseDate}</td>
          <td class="last">
            <a href="javascript:void(0);" onclick="js_update('${communityNewsDtos.id}')" id="update"
               class="a3"><spring:message code="propertyServices.serviceModify"/></a>
            <a href="javascript:void(0);" onclick="js_del('${communityNewsDtos.id}')" id="del"
               class="a3"><spring:message code="propertyServices.serviceDelete"/></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/communityNews/NewsList.html?menuId=005300010000&pageIndex={0}&scopeId=${communityNewsDto.scopeId}&projectCode=${communityNewsDto.projectCode}&title=${communityNewsDto.title}&releasePerson=${communityNewsDto.releasePerson}&type=${communityNewsDto.type}&releaseStatus=${communityNewsDto.releaseStatus}&staDate=${communityNewsDto.staDate}&endDate=${communityNewsDto.endDate}"/>
  </div>

</div>
</div>
</div>
</div>
<script type="text/javascript">
  var pages = "${webPage.pageIndex}";
  var title = "${communityNewsDto.title}";
  var releasePerson = ${communityNewsDto.releasePerson};
  var type = ${communityNewsDto.type};
  var releaseStatus = "${communityNewsDto.releaseStatus}";

  var staDate = "${communityNewsDto.staDate}";
  var endDate = "${communityNewsDto.endDate}";

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
    var projectId = $("#scopeId").val();
    if (projectId == '-1') {
      $("#projectCode").empty();
      return;
    }
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId + "/" + $("#menuId").val(), function (data) {
      var arr = data.data;
      $("#projectCode").empty();
      $("#projectCode").append('<option value="">请选择</option>');
      for (var k in arr) {
        if (arr[k][0] != '0') {
          $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
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
          window.location.href = "../communityNews/transferPage?menuId=" + $("#menuId").val();
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
      var href = "../communityNews/exportExcel?title=${communityNewsDto.title}&releasePerson=${communityNewsDto.releasePerson}&type=${communityNewsDto.type}&releaseStatus=${communityNewsDto.releaseStatus}&staDate=${communityNewsDto.staDate}&endDate=${communityNewsDto.endDate}";
      window.location.href = href;
    } else {
      alert("没有可以导出的数据");
    }
  }
  function js_update(id) {
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
        url: "../communityNews/checkEdit/" + id + "/" + $("#menuId").val(),
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
      window.location.href = "../communityNews/transferPage?id=" + id + "&menuId=" + $("#menuId").val();
    }
  }
  function js_del(id) {
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
        url: "../communityNews/checkEdit/" + id + "/" + $("#menuId").val(),
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
      myvalue = confirm("确定要删除吗?");
      if (myvalue == true) {
        window.location.href = "../communityNews/deleteNews?id=" + id;
      }
    }
  }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>