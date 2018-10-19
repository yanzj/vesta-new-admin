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
      $("#005500030000").addClass("active");
      $("#005500030000").parent().parent().addClass("in");
      $("#005500030000").parent().parent().parent().parent().addClass("active");
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
               crunMenu="005500030000" username="${propertystaff.staffName}"/>
  <input type="hidden" id="menuId" name="menuId" value="005500030000"/>

  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
        <form class="form-horizontal" id="froms" action="../property/saveOrUpdatePropertyDoor">
          <input type="hidden" id="hotlineId" name="hotlineId" value="${propertyHotline.id}"/>
          <!-- 城市 -->
          <div class="form-group  col-lg-12">
            <label for="scopeId" class="col-sm-3 control-label">区域：</label>
            <div class="col-sm-2">
              <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                <option value="">请选择</option>
                <c:forEach items="${city}" var="item">
                  <option value="${item.cityId }"
                          <c:if test="${item.cityId eq propertyHotline.cityId}">selected</c:if>>${item.cityName }</option>
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
                          <c:if test="${item[0] eq propertyHotline.projectCode}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <%-- 功能模块 --%>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label">功能模块：</label>
            <label class="col-sm-2 control-label">
              <select id="functionModuleCode" name="functionModuleCode" class="form-control">
                <option value="0">请选择</option>
                <option value="1001" <c:if test="${propertyHotline.functionModuleCode eq '1001'}">selected</c:if>>智能门禁</option>
                <option value="1002" <c:if test="${propertyHotline.functionModuleCode eq '1002'}">selected</c:if>>物业缴费</option>
              </select>
            </label>
          </div>
          <%-- 联系方式 --%>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label" for="hotline">联系方式：</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" placeholder="请输入联系方式" id="hotline" name="hotline" value="${propertyHotline.hotline}"/>
            </div>
          </div>
          <div class="text-center form-group  col-lg-6">
            <button type="button" class="btn btn-primary" onclick="js_save()"><spring:message
                    code="common_save"/></button>
            <button type="button" class="btn btn-primary" onclick="javascript:window.location.href = '../property/propertyHotlineList.html?menuId=005500030000';">
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
  function js_save(){
    var scopeId = $("#scopeId").val();
    if(scopeId == null || scopeId == "" || scopeId == "0"){
      alert("请选择城市！");
      return;
    }
    var projectCode = $("#projectCode").val();
    if(projectCode == null || projectCode == "" || projectCode == "0"){
      alert("请选择项目！");
      return;
    }
    var functionModuleCode = $("#functionModuleCode").val();
    if(functionModuleCode == "0"){
      alert("请选择功能模块！");
      return;
    }
    var hotline = $("#hotline").val();
    if(hotline == null || hotline == ""){
      alert("请输入联系方式！");
      return;
    }

    $.ajax({
      url:"../property/saveOrUpdatePropertyHotline",
      type:"POST",
      async:"false",
      data:{
        "hotlineId":$("#hotlineId").val(),
        "cityId":scopeId,
        "cityName":$("#scopeId").find("option:selected").text(),
        "projectCode":projectCode,
        "projectName":$("#projectCode").find("option:selected").text(),
        "functionModuleCode":functionModuleCode,
        "functionModuleName":$("#functionModuleCode").find("option:selected").text(),
        "hotline":hotline
      },
      dataType:"json",
      error:function(){
        alert("网络异常，可能服务器忙，请刷新重试");
      },
      success:function(data){
        if(data.error == "0"){
          alert("保存成功！");
          window.location.href = "../property/propertyHotlineList.html?menuId=005500030000";
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