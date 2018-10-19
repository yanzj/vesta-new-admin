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
      $("#005600020000").addClass("active");
      $("#005600020000").parent().parent().addClass("in");
      $("#005600020000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
</head>
<style type="text/css">
  .form_b{
    height: 12.5rem;
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
               crunMenu="005600020000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body form_b">
        <form id="form" class="form-horizontal" action="../property/toAssignDoor.html" method="post" enctype="multipart/form-data">
          <input type="hidden" id="menuId" name="menuId" value="005600020000"/>
          <!-- 城市 -->
          <div class="form-group  col-xs-4">
            <label for="scopeId" class="col-sm-3 control-label">区域</label>
            <div class="col-sm-7">
              <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                <option value="">请选择</option>
                <c:forEach items="${city}" var="item" >
                  <option value="${item.cityId }"
                          <c:if test="${item.cityId eq propertyDoorDTO.scopeId}">selected</c:if>>${item.cityName }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 项目 -->
          <div class="form-group  col-xs-4">
            <label for="projectCode" class="col-sm-3 control-label">项目</label>
            <div class="col-sm-7">
              <select id="projectCode" name="projectCode" class="form-control">
                <c:forEach items="${projectList}" var="item" >
                  <option value="${item[0] }"
                          <c:if test="${item[0] eq propertyDoorDTO.projectCode}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 房间信息 -->
          <div class="form-group  col-xs-4">
            <label for="addressDTO" class="col-sm-4 control-label">房间信息</label>
            <div class="col-sm-7">
              <input type="text" class="form-control" id="addressDTO" name="addressDTO" value="${propertyDoorDTO.addressDTO}"/>
            </div>
          </div>
          <!-- 姓名 -->
          <div class="form-group  col-xs-4">
            <label for="nameDTO" class="col-sm-3 control-label">姓名</label>
            <div class="col-sm-7">
              <input type="text" class="form-control" id="nameDTO" name="nameDTO" value="${propertyDoorDTO.nameDTO}"/>
            </div>
          </div>
          <!-- 电话 -->
          <div class="form-group  col-xs-4">
            <label for="mobileDTO" class="col-sm-3 control-label">手机号码</label>
            <div class="col-sm-7">
              <input type="text" class="form-control" id="mobileDTO" name="mobileDTO" value="${propertyDoorDTO.mobileDTO}"/>
            </div>
          </div>
          <%-- 用户身份 --%>
          <div class="form-group  col-xs-4">
            <label for="mobileDTO" class="col-sm-4 control-label">用户身份</label>
            <div class="col-sm-7">
              <select id="userTypeDTO" name="userTypeDTO" class="form-control">
                <option value="">请选择</option>
                <option value="2" <c:if test="${propertyDoorDTO.userTypeDTO eq '2'}">selected</c:if>>普通会员</option>
                <option value="3" <c:if test="${propertyDoorDTO.userTypeDTO eq '3'}">selected</c:if>>业主</option>
                <option value="4" <c:if test="${propertyDoorDTO.userTypeDTO eq '4'}">selected</c:if>>同住人</option>
              </select>
            </div>
          </div>
          <div class="form-group  col-xs-12 search_button">
            <button type="submit" class="btn btn-primary"><spring:message
                    code="propertyCompany.propertySearch"/></button>
          </div>

          <%--<button type="button" class="btn btn-primary" id="initialize">初始化用户门禁权限</button>--%>
          <!--集合长度(取决Excel是否可以导出)-->
          <%--
          <input type="hidden" id="size" value="${isExcel}">
          <a href="javascript:void(0);"  onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">批量导出</a>
          --%>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <form id="batchForm" class="form-horizontal" action="../staffUser/toBatchSetRole">
      <table width="100%" class="tableStyle">
        <thead>
        <tr>
          <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
          <th>用户名</th>
          <th>用户类型</th>
          <th>姓名</th>
          <th>用户名</th>
          <th>手机号码</th>
          <th>房间号</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${propertyUserDoorList}" var="propertyUserDoor" varStatus="row">
          <tr>
            <td><b>${row.index + 1}</b></td>
            <td>${propertyUserDoor.userName}</td>
            <td>
              <c:if test="${propertyUserDoor.userType eq '2'}">普通会员</c:if>
              <c:if test="${propertyUserDoor.userType eq '3'}">业主</c:if>
              <c:if test="${propertyUserDoor.userType eq '4'}">关联住户</c:if>
              <c:if test="${propertyUserDoor.userType eq '6'}">虚拟业主</c:if>
            </td>
            <td>${propertyUserDoor.realName}</td>
            <td>${propertyUserDoor.nickName}</td>
            <td>${propertyUserDoor.mobile}</td>
            <td>${propertyUserDoor.address}</td>
            <td class="last">
              <a href="javascript:void(0);" onclick="assign('${propertyUserDoor.userId}')" class="a3">分配</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </form>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/property/toAssignDoor.html?menuId=${propertyDoorDTO.menuId}&pageIndex={0}&projectCode=${propertyDoor.projectCode}&addressDTO=${propertyDoorDTO.addressDTO}&nameDTO=${propertyDoorDTO.nameDTO}&mobileDTO=${propertyDoorDTO.mobileDTO}"/>
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
    //初始化用户门禁权限
    $("#initialize").bind('click',function(){
      var projectCode = $("#projectCode").val();
      if(projectCode != null && projectCode != '0' && projectCode != ''){
        if(window.confirm("确认要执行初始化操作？")){
          alert("初始化将持续数分钟，请耐心等待....");
          $.getJSON("/property/initialize/"+projectCode, function (data) {
            var error = data.error;
            if(error == '-1'){
              alert("初始化失败！");
              return ;
            }
            if(error == '0'){
              alert("初始化完成！");
              return ;
            }
          });
        }
      }else{
        alert("请选择项目！");
      }
    });
  });
  //通过城市获取项目列表
  function queryProjectNameById() {
//    $("#projectCode").find("option").remove();
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

  function assign(userId){
    $.ajax({
      type: "GET",
      url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
      cache: false,
      async:false,
      dataType:"json",
      success: function (data) {
        if (data.error == 1) {
          window.location.href = "../property/toAssignDoorList.html?pageIndex=1&menuId="+$("#menuId").val()+"&userId="+userId;
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