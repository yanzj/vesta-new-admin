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
          <input type="hidden" id="doorId" name="doorId" value="${propertyDoor.id}"/>
          <!-- 项目 -->
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label">项目：</label>
            <label class="col-sm-2 control-label"><p class="text-left">${propertyDoor.projectName}</p></label>
          </div>
          <!-- 地块 -->
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label">地块：</label>
            <label class="col-sm-2 control-label"><p class="text-left">${propertyDoor.area}</p></label>
          </div>
          <!-- 楼栋 -->
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label">楼栋：</label>
            <label class="col-sm-2 control-label">
              <p class="text-left">
                <c:if test="${empty propertyDoor.buildingRecord and !empty propertyDoor.buildingSale}">
                  ${propertyDoor.buildingSale}栋
                </c:if>
                <c:if test="${!empty propertyDoor.buildingRecord}">${propertyDoor.buildingRecord}栋</c:if>
              </p>
            </label>
          </div>
          <!-- 单元 -->
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label">单元：</label>
            <label class="col-sm-2 control-label">
              <p class="text-left">
                <c:if test="${!empty propertyDoor.unit}">${propertyDoor.unit}单元</c:if>
              </p>
            </label>
          </div>
          <!-- 楼层 -->
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label">楼层：</label>
            <label class="col-sm-2 control-label">
              <p class="text-left">
                <c:if test="${!empty propertyDoor.floor}">${propertyDoor.floor}层</c:if>
              </p>
            </label>
          </div>
          <%-- 门禁设备类型 --%>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label">门禁设备类型：</label>
            <label class="col-sm-2 control-label">
              <p class="text-left">
                <c:if test="${propertyDoor.deviceType eq '1'}">单元门</c:if>
                <c:if test="${propertyDoor.deviceType eq '2'}">小区大门</c:if>
              </p>
            </label>
          </div>
          <%-- 设备Mac地址 --%>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label" for="macAddress">设备Mac地址：</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" placeholder="请输入设备Mac地址" id="macAddress" name="macAddress" value="${propertyDoor.macAddress}"/>
            </div>
          </div>
          <%-- 门禁设备描述 --%>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label" for="deviceDesc">门禁设备描述：</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" placeholder="请输入门禁设备描述" id="deviceDesc" name="deviceDesc" value="${propertyDoor.deviceDesc}"/>
            </div>
          </div>
          <%-- 门禁设备密码 --%>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label" for="devicePwd">门禁设备密码：</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" placeholder="请输入门禁设备密码" id="devicePwd" name="devicePwd" value="${propertyDoor.devicePwd}"/>
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
  function js_save(){
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
        "doorId":$("#doorId").val(),
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
          alert("该门禁设备已经存在，请核对门禁设备信息！");
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