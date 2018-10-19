<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <script>
    $(function(){
      console.log("sqq")
      $("#005500030000").addClass("active");
      $("#005500030000").parent().parent().addClass("in");
      $("#005500030000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
</head>
<style type="text/css">
  .form_b{
    height: 10.5rem;
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
<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="005500030000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body form_b">
        <form id="form" class="form-horizontal" action="../property/propertyHotlineList.html" method="post" enctype="multipart/form-data">
          <input type="hidden" id="menuId" name="menuId" value="005500030000"/>
          <!-- 城市 -->
          <div class="form-group  col-xs-4">
            <label for="scopeId" class="col-sm-3 control-label">区域</label>
            <div class="col-sm-8">
              <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                <option value="">请选择</option>
                <c:forEach items="${city}" var="item" >
                  <option value="${item.cityId }"
                          <c:if test="${item.cityId eq propertyHotlineDTO.scopeId}">selected</c:if>>${item.cityName }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 项目 -->
          <div class="form-group  col-xs-4">
            <label for="projectCode" class="col-sm-3 control-label">项目</label>
            <div class="col-sm-8">
              <select id="projectCode" name="projectCode" class="form-control">
                <c:forEach items="${projectList}" var="item" >
                  <option value="${item[0] }"
                          <c:if test="${item[0] eq propertyHotlineDTO.projectCode}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <!-- 功能模块 -->
          <div class="form-group  col-xs-4">
            <label for="functionModuleCode" class="col-sm-3 control-label">功能模块</label>
            <div class="col-sm-8">
              <select id="functionModuleCode" name="functionModuleCode" class="form-control">
                <option value="0">请选择</option>
                <option value="1001" <c:if test="${propertyHotlineDTO.functionModuleCode eq '1001'}">selected</c:if>>智能门禁</option>
                <option value="1002" <c:if test="${propertyHotlineDTO.functionModuleCode eq '1002'}">selected</c:if>>物业缴费</option>
              </select>
            </div>
          </div>
          <div class="form-group  col-xs-12 search_button">
            <button type="submit" class="btn btn-primary"><spring:message
                    code="propertyCompany.propertySearch"/></button>
            <a href="javascript:void(0);" onclick="toAdd()" class="btn btn-primary"><spring:message code="common_add"/></a>

          </div>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <div class="">

    </div>
    <form id="batchForm" class="form-horizontal" action="../staffUser/toBatchSetRole">
      <table width="100%" class="tableStyle">
        <thead>
        <tr>
          <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
          <th>项目</th>
          <th>功能模块</th>
          <th>手机号码</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${hotlineList}" var="hotline" varStatus="row">
          <tr>
            <td><b>${row.index + 1}</b></td>
            <td>${hotline.projectName}</td>
            <td>${hotline.functionModuleName}</td>
            <td>${hotline.hotline}</td>
            <td class="last">
              <a href="javascript:void(0);" onclick="editDoor('${hotline.id}')" id="update"
                 class="a3">编辑</a>
              <a href="javascript:void(0);" onclick="delHotline('${hotline.id}')" id="del"
                 class="a3">删除</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </form>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/property/propertyHotlineList.html?menuId=${propertyDoorDTO.menuId}&pageIndex={0}&scopeId=${propertyDoorDTO.scopeId}&projectCode=${propertyDoorDTO.projectCode}&blockCode=${propertyDoorDTO.blockCode}&buildingNum=${propertyDoorDTO.buildingNum}&unitCode=${propertyDoorDTO.unitCode}&floorCode=${propertyDoorDTO.floorCode}"/>
  </div>

</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
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
<script>
  $(function() {

  });
  //通过城市获取项目列表
  function queryProjectNameById() {
    $("#projectCode").find("option").remove();
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
  function toAdd(){
    $.ajax({
      type: "GET",
      url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
      cache: false,
      async:false,
      dataType:"json",
      success: function (data) {
        if (data.error == 1) {
          window.location.href = "../property/toEditPropertyHotline.html?menuId=005500030000";
        }else if(data.error == 0) {
          alert("对不起，您无权限执行此操作！");
        }else{
          alert("对不起，操作失败！");
        }
      }
    });
  }

  function editDoor(id){
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
            url: "../property/checkEditPropertyHotline/" + id + "/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
              if (data.error == 1) {
                window.location.href = "../property/toEditPropertyHotline.html?menuId=005500030000&hotlineId="+id;
              } else if (data.error == 0) {
                alert("对不起，您的权限范围无法执行此操作！");
                return;
              } else {
                alert("对不起，操作失败！");
                return;
              }
            }
          });
        }else if(data.error == 0) {
          alert("对不起，您无权限执行此操作！");
        }else{
          alert("对不起，操作失败！");
        }
      }
    });
  }
  function delHotline(id){
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
            url: "../property/checkEditPropertyHotline/" + id + "/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
              if (data.error == 1) {
                myvalue = confirm("确定要删除吗?");
                if (myvalue == true) {
                  $.ajax({
                    url:"../property/deletePropertyHotline",
                    type:"POST",
                    data:{
                      "hotlineId":id
                    },
                    dataType:"json",
                    success:function(data){
                      if(data.error == "0"){
                        alert("删除成功！");
                        window.location.href = "../property/propertyHotlineList.html?menuId=005500030000";
                      }else if(data.error == "-1"){
                        alert("删除失败，请联系管理员！");
                      }
                    }
                  });
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
        }else if(data.error == 0) {
          alert("对不起，您无权限执行此操作！");
        }else{
          alert("对不起，操作失败！");
        }
      }
    });
  }

</script>
</body>
</html>