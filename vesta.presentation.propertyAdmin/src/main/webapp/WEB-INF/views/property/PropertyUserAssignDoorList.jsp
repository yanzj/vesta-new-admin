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
  .form_b{
    height: 16rem;
  }
</style>
<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="005600020000" username="${propertystaff.staffName}"/>

  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body " style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
        <form class="form-horizontal">
          <div class="form-group  col-lg-12">
            <h4>房间信息：${userInfo.address}</h4>
          </div>
          <div class="form-group  col-lg-4">
            <h4>业主：<c:if test="${!empty userInfo.realName}">${userInfo.realName}</c:if><c:if test="${empty userInfo.realName}">${userInfo.nickName}</c:if>
            </h4>
          </div>
        </form>
      </div>
    </div>
  </div>

  <div class="forms bs-example">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body form_b">
        <form id="form" class="form-horizontal" action="../property/toAssignDoorList.html" method="post">
          <input type="hidden" id="menuId" name="menuId" value="005600020000"/>
          <input type="hidden" id="userId" name="userId" value="${propertyDoorDTO.userId}"/>
          <!-- 城市 -->
          <div class="form-group  col-lg-4">
            <label for="scopeId" class="col-sm-3 control-label">区域</label>
            <div class="col-sm-8">
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
          <div class="form-group  col-lg-4">
            <label for="projectCode" class="col-sm-3 control-label">项目</label>
            <div class="col-sm-8">
              <select id="projectCode" name="projectCode" class="form-control">
                <c:forEach items="${projectList}" var="item" >
                  <option value="${item[0] }"
                          <c:if test="${item[0] eq propertyDoorDTO.projectCode}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 地块 -->
          <div class="form-group  col-lg-4">
            <label for="blockCode" class="col-sm-3 control-label">地块</label>
            <div class="col-sm-8">
              <select class="form-control" id="blockCode" name="blockCode">
                <c:forEach items="${areaMap}" var="item" >
                  <option value="${item.key }"
                          <c:if test="${item.key eq propertyDoorDTO.blockCode}">selected</c:if>>${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 楼栋 -->
          <div class="form-group  col-lg-4">
            <label for="buildingId" class="col-sm-3 control-label">楼栋</label>
            <div class="col-sm-8">
              <select class="form-control" placeholder="" id="buildingId" name="buildingNum">
                <c:forEach items="${buildMap}" var="building">
                  <option value="${building.key}" <c:if test="${building.key eq propertyDoorDTO.buildingNum}">selected</c:if>>${building.value}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 单元 -->
          <div class="form-group  col-lg-4">
            <label for="unitId" class="col-sm-3 control-label">单元</label>
            <div class="col-sm-8">
              <select class="form-control" placeholder="" id="unitId" name="unitCode">
                <c:forEach items="${unitMap}" var="unit">
                  <option value="${unit.key}" <c:if test="${unit.key eq propertyDoorDTO.unitCode}">selected</c:if>>${unit.value}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 楼层 -->
          <div class="form-group  col-lg-4">
            <label for="floorCode" class="col-sm-3 control-label">楼层</label>
            <div class="col-sm-8">
              <select id="floorCode" name="floorCode" class="form-control">floorMap
                <c:forEach items="${floorMap}" var="floor">
                  <option value="${floor.key}" <c:if test="${floor.key eq propertyDoorDTO.floorCode}">selected</c:if>>${floor.value}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 门禁状态 -->
          <div class="form-group  col-lg-4">
            <label for="assignState" class="col-sm-3 control-label">门禁状态</label>
            <div class="col-sm-8">
              <select id="assignState" name="assignState" class="form-control">
                <option value="">请选择</option>
                <option value="0" <c:if test="${propertyDoorDTO.assignState eq '0'}">selected</c:if>>已分配</option>
                <option value="1" <c:if test="${propertyDoorDTO.assignState eq '1'}">selected</c:if>>未分配</option>
              </select>
            </div>
          </div>
          <div class="form-group  col-lg-12 search_button">
            <button type="submit" class="btn btn-primary"><spring:message code="propertyCompany.propertySearch"/></button>
          </div>
        </form>
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
          <th>项目名称</th>
          <th>地块</th>
          <th>楼号</th>
          <th>单元号</th>
          <th>楼层</th>
          <th>设备Mac地址</th>
          <th>门禁设备描述</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${propertyDoorList}" var="propertyDoor" varStatus="row">
          <tr>
            <td><b>${row.index + 1}</b></td>
            <td>${propertyDoor.projectName}</td>
            <td>${propertyDoor.area}</td>
            <td>
              <c:if test="${!empty propertyDoor.buildingRecord}">${propertyDoor.buildingRecord}</c:if>
              <c:if test="${empty propertyDoor.buildingRecord}">${propertyDoor.buildingSale}</c:if>
            </td>
            <td>${propertyDoor.unit}</td>
            <td>${propertyDoor.floor}</td>
            <td>${propertyDoor.macAddress}</td>
            <td>${propertyDoor.deviceDesc}</td>
            <td class="last">
              <c:if test="${propertyDoor.assignState eq '0'}">
                <a href="javascript:void(0);" onclick="assign('0','${propertyDoor.doorId}')" class="a3">取消分配</a>
              </c:if>
              <c:if test="${propertyDoor.assignState ne '0'}">
                <a href="javascript:void(0);" onclick="assign('1','${propertyDoor.doorId}')" class="a3">分配</a>
              </c:if>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </form>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/property/toAssignDoorList.html?menuId=${propertyDoorDTO.menuId}&pageIndex={0}&userId=${propertyDoorDTO.userId}&scopeId=${propertyDoorDTO.scopeId}&projectCode=${propertyDoorDTO.projectCode}&blockCode=${propertyDoorDTO.blockCode}&buildingNum=${propertyDoorDTO.buildingNum}&unitCode=${propertyDoorDTO.unitCode}&floorCode=${propertyDoorDTO.floorCode}"/>
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
    $("#blockCode").find("option").remove();
    $("#buildingId").find("option").remove();
    $("#unitId").find("option").remove();
    $("#floorCode").find("option").remove();
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
  //通过项目获取地块列表
  $("#projectCode").change(function(){
    $("#blockCode").find("option").remove();
    $("#buildingId").find("option").remove();
    $("#unitId").find("option").remove();
    $("#floorCode").find("option").remove();
    /* -------------------- */
    var projectId = $("#projectCode").val();
    $.ajax({
      url:"../houseroomtype/getAreaListByProject",
      type:"get",
      async:"false",
      data:{ "projectId":projectId},
      dataType:"json",
      error:function(){
        alert("网络异常，可能服务器忙，请刷新重试");
      },
      success:function(json){
        <!-- 获取返回代码 -->
        var code = json.code;
        if(code != 0){
          var errorMessage = json.msg;
          alert(errorMessage);
        }else{
          <!-- 获取返回数据 -->
          var data = json.data;
          var option ="";
          if(data != null){
            document.getElementById("blockCode").innerHTML = "";
            for(var prop in data) {
              if (data.hasOwnProperty(prop)) {
                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
              }
            }
            $("#blockCode").append(option);
          }
        }
      }
    });
  });
  //通过地块获取楼栋列表
  $("#blockCode").change(function(){
    $("#buildingId").find("option").remove();
    $("#unitId").find("option").remove();
    $("#floorCode").find("option").remove();
    /* -------------------- */
    var blockCode = $("#blockCode").val();
    $.ajax({
      url:"../houseroomtype/getBuildingListByArea",
      type:"get",
      async:"false",
      data:{ "areaId":blockCode},
      dataType:"json",
      error:function(){
        alert("网络异常，可能服务器忙，请刷新重试");
      },
      success:function(json){
        <!-- 获取返回代码 -->
        var code = json.code;
        if(code != 0){
          var errorMessage = json.msg;
          alert(errorMessage);
        }else{
          <!-- 获取返回数据 -->
          var data = json.data;
          var option ="";
          if(data != null){
            document.getElementById("buildingId").innerHTML = "";
            for(var prop in data) {
              if (data.hasOwnProperty(prop)) {
                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
              }
            }
            $("#buildingId").append(option);
          }
        }
      }
    });
  });
  //通过楼栋获取单元列表
  $("#buildingId").change(function(){
    $("#unitId").find("option").remove();
    $("#floorCode").find("option").remove();
    /* -------------------- */
    var buildingId = $("#buildingId").val();
    $.ajax({
      url:"../houseroomtype/getUnitList",
      type:"get",
      async:"false",
      data:{ "buildingId":buildingId},
      dataType:"json",
      error:function(){
        alert("网络异常，可能服务器忙，请刷新重试");
      },
      success:function(json){
        <!-- 获取返回代码 -->
        var code = json.code;
        if(code != 0){
          var errorMessage = json.msg;
          alert(errorMessage);
        }else{
          <!-- 获取返回数据 -->
          var data = json.data;
          var option ="";
          if(data != null){
            document.getElementById("unitId").innerHTML = "";
            for(var prop in data) {
              if (data.hasOwnProperty(prop)) {
                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
              }
            }
            $("#unitId").append(option);
          }
        }
      }
    });
  });
  //通过单元获取楼层列表
  $("#unitId").change(function(){
    $("#floorCode").find("option").remove();
    /* -------------------- */
    var unitId = $("#unitId").val();
    $.ajax({
      url:"../houseroomtype/getFloorListByNum",
      type:"get",
      async:"false",
      data:{ "unitId":unitId},
      dataType:"json",
      error:function(){
        alert("网络异常，可能服务器忙，请刷新重试");
      },
      success:function(json){
        <!-- 获取返回代码 -->
        var code = json.code;
        if(code != 0){
          var errorMessage = json.msg;
          alert(errorMessage);
        }else{
          <!-- 获取返回数据 -->
          var data = json.data;
          var option ="";
          if(data != null){
            document.getElementById("floorCode").innerHTML = "";
            for(var prop in data) {
              if (data.hasOwnProperty(prop)) {
                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
              }
            }
            $("#floorCode").append(option);
          }
        }
      }
    });
  });
  function assign(state,doorId){
    $.ajax({
      type: "POST",
      url: "../property/assignUserDoor",
      data:{
        userId:$("#userId").val(),
        doorId:doorId,
        state:state
      },
      cache: false,
      async:false,
      dataType:"json",
      success: function (data) {
        if (data.error == 0) {
          alert("操作成功！");
//          location.reload();
          window.location.href = "../property/toAssignDoorList.html?menuId=${propertyDoorDTO.menuId}&pageIndex=1&userId=${propertyDoorDTO.userId}&scopeId=${propertyDoorDTO.scopeId}&projectCode=${propertyDoorDTO.projectCode}&blockCode=${propertyDoorDTO.blockCode}&buildingNum=${propertyDoorDTO.buildingNum}&unitCode=${propertyDoorDTO.unitCode}&floorCode=${propertyDoorDTO.floorCode}";
        }else if(data.error == -1) {
          alert("操作失败，请联系管理员！");
        }
      }
    });
  }
</script>
</body>
</html>