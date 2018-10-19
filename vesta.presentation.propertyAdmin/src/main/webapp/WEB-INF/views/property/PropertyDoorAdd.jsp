<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%response.setHeader("cache-control", "public"); %>
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
    $(function(){
      console.log("sqq")
      $("#005600010000").addClass("active");
      $("#005600010000").parent().parent().addClass("in");
      $("#005600010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <script src="../static/property/js/checkNullAllJsp.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <style>
    .flowersList img {
      width: 20px;
    }

    .imgList img {
      width: 100px;
      height: 120px;
    }

    .sidebar ul li {
      border-bottom: 0;
    }
  </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="005600010000" username="${propertystaff.staffName}"/>
  <input type="hidden" id="menuId" name="menuId" value="005600010000"/>

  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
        <form class="form-horizontal" id="froms" action="../property/saveOrUpdatePropertyDoor">
          <!-- 城市 -->
          <div class="form-group  col-lg-12">
            <label for="scopeId" class="col-sm-3 control-label">区域：</label>
            <div class="col-sm-2">
              <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                <option value="">请选择</option>
                <c:forEach items="${city}" var="item">
                  <option value="${item.cityId }"
                          <c:if test="${item.cityId eq propertyDoorDTO.scopeId}">selected</c:if>>${item.cityName }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 项目 -->
          <div class="form-group  col-lg-12">
            <label for="projectCode" class="col-sm-3 control-label">项目：</label>
            <div class="col-sm-2">
              <select id="projectCode" name="projectCode" class="form-control">
                <c:forEach items="${projectList}" var="item" >
                  <option value="${item[0] }"
                          <c:if test="${item[0] eq propertyDoorDTO.projectCode}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 地块 -->
          <div class="form-group  col-lg-12">
            <label for="blockCode" class="col-sm-3 control-label">地块：</label>
            <div class="col-sm-2">
              <select class="form-control" id="blockCode" name="blockCode">
                <c:forEach items="${areaMap}" var="item" >
                  <option value="${item.key }"
                          <c:if test="${item.key eq propertyDoorDTO.blockCode}">selected</c:if>>${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 楼栋 -->
          <div class="form-group  col-lg-12">
            <label for="buildingId" class="col-sm-3 control-label">楼栋：</label>
            <div class="col-sm-2">
              <select class="form-control" placeholder="" id="buildingId" name="buildingNum">
                <c:forEach items="${buildMap}" var="building">
                  <option value="${building.key}" <c:if test="${building.key eq propertyDoorDTO.buildingNum}">selected</c:if>>${building.value}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 单元 -->
          <div class="form-group  col-lg-12">
            <label for="unitId" class="col-sm-3 control-label">单元：</label>
            <div class="col-sm-2">
              <select class="form-control" placeholder="" id="unitId" name="unitCode">
                <c:forEach items="${unitMap}" var="unit">
                  <option value="${unit.key}" <c:if test="${unit.key eq propertyDoorDTO.unitCode}">selected</c:if>>${unit.value}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 楼层 -->
          <div class="form-group  col-lg-12">
            <label for="floor" class="col-sm-3 control-label">楼层：</label>
            <div class="col-sm-2">
              <input type="text" class="form-control" placeholder="请输入楼层" id="floor" name="floor"/>
            </div>
          </div>
          <%-- 门禁设备类型 --%>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label" for="deviceType">门禁设备类型：</label>
            <div class="col-sm-3">
              <select id="deviceType" name="deviceType" class="form-control">floorMap
                <option value="1">单元门</option>
                <option value="2">小区大门</option>
              </select>
            </div>
          </div>
          <%-- 设备Mac地址 --%>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label" for="macAddress">设备Mac地址：</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" placeholder="请输入设备Mac地址" id="macAddress" name="macAddress"/>
            </div>
          </div>
          <%-- 门禁设备描述 --%>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label" for="deviceDesc">门禁设备描述：</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" placeholder="请输入门禁设备描述" id="deviceDesc" name="deviceDesc"/>
            </div>
          </div>
          <%-- 门禁设备密码 --%>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label" for="devicePwd">门禁设备密码：</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" placeholder="请输入门禁设备密码" id="devicePwd" name="devicePwd"/>
            </div>
          </div>

          <div class="text-center form-group  col-lg-6">
            <button type="button" class="btn btn-primary" onclick="js_save()"><spring:message
                    code="common_save"/></button>
            <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);">
              <spring:message code="common_cancel"/></button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</div>
</div>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
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

  function js_save(){
    var scopeId = $("#scopeId").val();
    if(scopeId == null || scopeId == "" || scopeId == "0"){
      alert("请选择城市！");
      return;
    }
    var projectCode = $("#projectCode").val();
    var projectName = $("#projectCode").find("option:selected").text();
    if(projectCode == null || projectCode == "" || projectCode == "0"){
      alert("请选择项目！");
      return;
    }
    var blockCode = $("#blockCode").val();
    var area = $("#blockCode").find("option:selected").text();
    if(blockCode == null || blockCode == "" || blockCode == "0"){
      alert("请选择地块！");
      return;
    }
    if($("#deviceType").val() == "1"){
      //单元门,进行详细位置校验
      var buildingId = $("#buildingId").val();
      var buildingRecord = $("#buildingId").find("option:selected").text();
      if(buildingId == null || buildingId == "" || buildingId == "0"){
        alert("请选择楼栋！");
        return;
      }
      var unitId = $("#unitId").val();
      var unit = $("#unitId").find("option:selected").text();
      if(unitId == null || unitId == "" || unitId == "0"){
        alert("请选择单元！");
        return;
      }
      var floor = "";
      if($("#unitId").find("option:selected").text() != "公共区域"){
        floor = $("#floor").val();
        if(floor == null || floor == "" || isNaN(floor)){
          alert("请输入正确楼层！");
          return;
        }
      }
    }
    var macAddress = $("#macAddress").val();
    if(macAddress == null || macAddress == ""){
      alert("请输入门禁设备Mac地址！");
      return;
    }
    var deviceDesc = $("#deviceDesc").val();
    if(deviceDesc == null || deviceDesc == ""){
      alert("请输入门禁设备描述信息！");
      return;
    }
    var devicePwd = $("#devicePwd").val();
    if(devicePwd == null || devicePwd == ""){
      alert("请输入门禁设备密码！");
      return;
    }

    $.ajax({
      url:"../property/saveOrUpdatePropertyDoor",
      type:"POST",
      async:"false",
      data:{
        "projectCode":projectCode,
        "projectName":projectName,
        "blockCode":blockCode,
        "area":area,
        "buildingNum":buildingId,
        "buildingRecord":buildingRecord,
        "unitCode":unitId,
        "unit":unit,
        "floor":floor,
        "deviceType":$("#deviceType").val(),
        "macAddress":macAddress,
        "deviceDesc":deviceDesc,
        "devicePwd":devicePwd
      },
      dataType:"json",
      error:function(){
        alert("网络异常，可能服务器忙，请刷新重试");
      },
      success:function(data){
        if(data.error == "0"){
          alert("保存成功！");
          window.location.href = "../property/propertyDoorList.html?menuId=005600010000";
        }else if(data.error == "1"){
          alert("该地理位置已经存在门禁设备，请核对地理位置信息！");
        }else if(data.error == "-1"){
          alert("保存失败，请联系管理员！");
        }
      }
    });
  }

</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>