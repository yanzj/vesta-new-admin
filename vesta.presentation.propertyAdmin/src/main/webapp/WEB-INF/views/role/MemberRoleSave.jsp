<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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

  <META HTTP-EQUIV="pragma" CONTENT="no-cache">
  <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
  <META HTTP-EQUIV="expires" CONTENT="0">

  <meta name="keywords" content=""/>
  <script type="application/x-javascript"> addEventListener("load", function () {
    setTimeout(hideURLbar, 0);
  }, false);
  function hideURLbar() {
    window.scrollTo(0, 1);
  } </script>

  <script type="text/javascript" charset="utf-8">
    window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
  </script>
  <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>

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

  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <!--animate-->
  <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
  <script src="../static/js/wow.min.js"></script>
  <script src="../static/property/js/checkNullAllJsp.js"></script>
  <script>
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
  <style>
    .flowersList img {
      width: 20px;
    }

    .imgList img {
      width: 70px;
    }
  </style>
  <STYLE type=text/css>
    input.error {
      border: 1px solid red;
    }

    label.error {
      background: url("./demo/images/unchecked.gif") no-repeat 0px 0px;
      padding-left: 16px;
      padding-bottom: 2px;
      font-weight: bold;
      color: #EA5200;
    }

    label.checked {
    / / background : url("./demo/images/checked.gif") no-repeat 0 px 0 px;
    }
    .borderLeft{
      width: 12%;
    }
    .borderDotted{
      height: 1px;
      border: 1px dotted #999;
    }
  </STYLE>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="002300010000" username="${propertystaff.staffName}"/>
  <input type="hidden" id="menuId" value="002300010000"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">

      <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>

        <div class="form-body" style="min-height:525px">
          <form class="form-horizontal" id="frm" action="../memberAuthority/toSetRoleMenu.html" method="post"
                enctype="MULTIPART/FORM-DATA">
            <input type="hidden" id="error" value="${error}"/>
            <input type="hidden" id="setId" name="setId" value="${RoleRolesetDTO.setId}"/>
            <div class="form-group  col-lg-10">
              <label for="city" class="col-sm-3 control-label"><h3><font color="blue">数据查看范围</font></h3></label>
            </div>
            <div class="form-group  col-lg-10 ">
              <label class="col-sm-2 control-label borderLeft"></label><div class="col-sm-7 borderDotted"></div>
            </div>
            <%--城市区域--%>
            <div class="form-group  col-lg-10">
              <label for="city" class="col-sm-3 control-label"><spring:message
                      code="announcementDTO.cityName"/></label>
              <div class="col-sm-5">
                <select id="city" name="city" class="form-control" onchange="queryProjectNameById()">
                  <option value="-1">--请选择城市--</option>
                  <c:forEach items="${city}" var="item" >
                    <option value="${item.cityId }"
                            <c:if test="${item.cityId eq '0'}">selected</c:if>>${item.cityName }</option>
                  </c:forEach>
                </select>
              </div>
              <div class="col-sm-2">
                <input type="image" name="addCity" id="addCity"
                       value="<spring:message code="announcementDTO.add"/>"
                       onclick="cityAdd();return false;"
                       src="../static/images/add.ico">
              </div>
            </div>
            <%--城市列表--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label" for="cityList"
                     style="min-width:115px;"><spring:message
                      code="announcementDTO.selectedCity"/> <font color="red">*</font></label>
              <div class="col-sm-5">
                <textarea class="form-control" name="cityList" id="cityList" readonly
                          style="width: 432px; height: 71px;" >${RoleRolesetDTO.cityList}</textarea>
                <input type="hidden" id="cityIds" name="cityIds" value="${RoleRolesetDTO.cityIds}"/>
              </div>
            </div>
            <%--城市下项目--%>
            <div class="form-group  col-lg-10">
              <label for="projectName" class="col-sm-3 control-label"><spring:message
                      code="HousePayBatch.projectName"/></label>
              <div class="col-sm-5">
                <select id="projectName" name="projectName" class="form-control">
                </select>
              </div>
              <div class="col-sm-2">
                <input type="image" name="addProject" id="addProject"
                       value="" onclick="projectAdd();return false;" src="../static/images/add.ico">
              </div>
            </div>
            <%--项目列表--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label" for="projectList"
                     style="min-width:115px;"><spring:message
                      code="announcementDTO.selectedProject"/> <font color="red">*</font></label>
              <div class="col-sm-5">
                <textarea class="form-control" name="projectList" id="projectList" readonly
                          style="width: 432px; height: 71px;">${RoleRolesetDTO.projectList}</textarea>
                <input type="hidden" id="projectIds" name="projectIds"  value="${RoleRolesetDTO.projectIds}">
              </div>
            </div>
            <div class="form-group  col-lg-10">
              <label for="city" class="col-sm-3 control-label"><h3><font color="blue">角色信息</font></h3></label>
            </div>
            <div class="form-group  col-lg-10 ">
              <label class="col-sm-2 control-label borderLeft"></label><div class="col-sm-7 borderDotted"></div>
            </div>
            <%--角色名称--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label" for="roledesc">角色名称 <font color="red">*</font></label>
              <div class="col-sm-5">
                <input type="text" class="form-control" placeholder="" id="roledesc" name="roledesc"
                       onchange="checkVal()"
                       value="${RoleRolesetDTO.roledesc}">
              </div>
            </div>
            <%--备注--%>
            <div class="form-group  col-lg-10">
              <label class="col-sm-3 control-label" for="remarks">备注</label>
              <div class="col-sm-5">
                <input type="text" class="form-control" placeholder="" id="remarks" name="remarks"
                       onchange="checkVal()"
                       value="${RoleRolesetDTO.remarks}">
              </div>
            </div>
            <%--取消,去设置会员操作权限--%>
            <div class="form-group  col-lg-10">
              <label class="col-sm-3 control-label" for="remarks"></label>
              <div class="col-sm-7">
                <a href="javascript:history.go(-1);" class="btn btn-primary" for="">取消</a>
                <button type="button" class="btn btn-primary" onclick="checkSubmit('1')">去设置会员操作权限</button>
              </div>
            </div>
        </div>

        </form>
      </div>
    </div>

  </div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<!-- 校验 -->
<script>
  $(function(){
    var error = $("#error").val();
    if(error == "1"){
      alert("角色新建失败!");
    }
  })
  function checkVal() {
    $("#frm").validate();
  }
  function cityAdd() {
    //#1.获取内容
    var projectName = $("#city").find("option:selected").text();
    //获取隐藏域的值_Wyd_2016.06.03
    var projectId = $("#city").find("option:selected").val();
    if(projectId == '-1'){
      alert("请选择城市，并添加！");
      return;
    }
    if (projectName == "全部城市") {
      //清空textarea
      $("#cityList").val("全部城市");
      $("#cityIds").val('0,');
      //清空项目列表_Wyd_2016.08.08_数据权限
      $("#projectList").val("");
      $("#projectIds").val("");
      $("#projectName").empty();
      $("#projectName").append('<option value="0,">全部项目</option>');
    } else {
      if ($("#cityList").val() == "") {
        //如果textarea中没有元素
        $("#cityList").val($("#cityList").val() + projectName + ',');
        $("#cityIds").val($("#cityIds").val() + projectId + ',');
      } else if ($("#cityList").val() != "") {
        //判断textArea中是否有select的值，如果没有则添加
        //获取textArea数组
        var strArray = $("#cityList").val().toString().split(",");
        //获取select值
        var str = $("#city").find("option:selected").text();
        //判断是否在数组中
        if (strArray.toString().indexOf(str) > -1) {
          return;
        }
        //判断是否="全部城市"
        if ($("#cityList").val() == "全部城市") {
          //清空
          $("#cityList").val("");
          $("#cityList").val($("#cityList").val() + projectName + ',');
          $("#cityIds").val(projectId + ',');
          return;
        }
        //如果textarea中有元素，前置","
        $("#cityList").val($("#cityList").val() + projectName + ',');
        $("#cityIds").val($("#cityIds").val() + projectId + ',');
      }
    }
  }
  function projectAdd() {
    //#1.获取内容
    var projectName = $("#projectName").find("option:selected").text();
    var projectId = $("#projectName").find("option:selected").val();
    if (projectName == "全部项目") {
     //清空textarea
     $("#projectList").val("全部项目");
     $("#projectIds").val("0,");
    }
    if ($("#projectList").val() == "") {
      //如果textarea中没有元素
      $("#projectList").val($("#projectList").val() + projectName + ',');
      $("#projectIds").val($("#projectIds").val() + projectId + ',');
    } else if ($("#projectList").val() != "") {
      //判断textArea中是否有select的值，如果没有则添加
      //获取textArea数组
      var strArray = $("#projectList").val().toString().split(",");
      //获取select值
      var str = $("#projectName").find("option:selected").text();
      //判断是否在数组中
      if (strArray.toString().indexOf(str) > -1) {
        return;
      }
      //判断是否="全部项目"
      if ($("#projectList").val() == "全部项目") {
        //清空
        $("#projectList").val("");
        $("#projectList").val($("#projectList").val() + projectName + ',');
        $("#projectIds").val(projectId + ',');
        return;
      }
      //如果textarea中有元素，前置","
      $("#projectList").val($("#projectList").val() + projectName + ',');
      $("#projectIds").val($("#projectIds").val() + projectId + ',');
    }
  }
  function queryProjectNameById() {
    var projectId = $("#city").val();
    if(projectId == '-1'){
      $("#projectName").empty();
      return ;
    }
    $("#planName").empty();
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
      var arr = data.data;
      $("#projectName").empty();
      <%--$("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');--%>
      for (var k in arr) {
        $("#projectName").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
//                alert(arr[k][0]);
      }

    });
  }
  //提交
  function checkSubmit() {
    if(!CheckNull($("#cityList").val(),"请添加城市！")){
      return false;
    }
    if($("#cityList").val() == ""){
      alert("请添加城市！");
      return ;
    }
    if($("#cityList").val() != "全部城市" ){
      if(!CheckNull($("#projectList").val(),"请添加项目！")){
        return false;
      }
    }
    if($("#cityList").val() != "全部城市" && $.trim($("#projectList").val()) == ""){
      alert("请添加项目！");
      return ;
    }
    if ($("#roledesc").val() == "") {
      alert("请添加角色名称！");
      return;
    }
    //角色名称重复校验
    var setId = $("#setId").val();
    if($.trim(setId) != ""){
      $("#frm").submit();
    }else{
      $.ajax({
        type: "GET",
        url: "../memberAuthority/checkRoledesc/"+$("#roledesc").val(),
        cache: false,
        async:false,
        dataType:"json",
        success: function (data) {
          if (data.error == 0) {
            var count = data.count;
            if(count != 0){
              alert("角色名称已被占用，请更换角色名称！");
            }else{
              $("#frm").submit();
            }
          }else{
            alert("对不起，操作失败！");
            return ;
          }
        }
      });
    }
  }
  $().ready(function () {
    var validator = $("#frm").validate({
      errorElement: "span",
      rules: {
        roledesc: {
          required: true
        }
      },
      messages: {
        roledesc: {
          required: "请输入角色名称!"
        }
      }
    })
  });
</script>
</body>
</html>
