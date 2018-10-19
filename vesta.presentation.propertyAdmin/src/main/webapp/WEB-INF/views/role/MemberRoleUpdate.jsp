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
  </STYLE>
  <script type="text/javascript" src="../static/js/roleSetScript.js"></script>
  <style type="text/css">
    *{
      padding: 0;
      list-style: none;
      margin: 0;
    }
    .wrap{
      width: 800px;
      margin: 2rem 17%;
      display: inline-block;
    }
    .checkboxCont{
      border: 1px solid #e2e2e2;
      margin-top:20px;
      border-bottom: none;
    }
    .checkboxCont li{
      border-bottom: 1px solid #e2e2e2;
      clear: both;
      overflow: hidden;
      min-height: 40px;
      line-height: 40px;
    }
    .z_title{
      float: left;
      width: 160px;
      line-height: 40px;

    }
    .z_title span{
      float: left;
    }
    .z_cont{
      width: 635px;
      line-height: 40px;
      overflow: hidden;
      float: left;
      border-left: 1px solid #e2e2e2;
    }
    .z_cont dl, .z_cont dd,.z_cont dt{
      float: left;
      height: 40px;
      line-height: 40px;
      overflow: hidden;
    }
    .z_cont dd{
      margin-right: 15px;
    }
    input[type="checkbox"]{
      opacity: 0;
      cursor: pointer;
      -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
      filter: alpha(opacity=0);
    }
    .checkAll1,.checkAll2,.clearAll,.z_check1,.z_check2 {
      width: 20px;
      height: 20px;
      display: inline-block;
      cursor: pointer;
      margin: 10px 2px 0 2px;
      text-align: center;
      background: url(../static/images/checkbox_01_new.png) no-repeat 0 0;
      background-position: 0 0;
      background-size: cover;
    }
    .z_check1,.z_check2 {
      float: left;
      background-size: cover;
    }
    .checkAll2,.z_check2{
      background: url(../static/images/checkbox_03_new.png) no-repeat 0 0;
      background-position: 0 0;
      /*background-color: #ffffff;*/
      border-radius: 16%;
      background-size: cover;
    }
    .clearAll{
      background: url(../static/images/checkbox_02.gif) no-repeat 0 0;
      background-position: -25px 0;
      background-size: cover;
    }
    .clearAll.on_check{
      background-position: 0 0;
    }
    .checkAll1.on_check,.z_check1.on_check {
      background-position: 0 -19px;
      background-size: cover;
    }
    .checkAll2.on_check,.z_check2.on_check {
      background-position: 0 -20px;
      background-size: cover;
    }
  </style>
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
        <input type="hidden" id="setId" value="${setId}">
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
        <br/>
        <%--菜单操作级别设置--%>
        <div class="wrap">
          <!--选择类型 start-->
          <p>
            <span class="checkAll1"><input type="checkbox" /></span>统一设为有全部权限
            <span class="checkAll2"><input type="checkbox"/></span>统一设为仅有查看权限
            <span class="clearAll"><input type="checkbox"/></span>全取消
          </p>
          <!--选择类型 end-->
          <ul class="checkboxCont">
            <!--循环项 start-->
            <c:forEach var="memberMenuMap" items="${memberMenuList}">
              <li>
                <p class="z_title">
                  <span class="checkGroup1 z_check1"><input type="checkbox"/></span>
                  <span class="checkGroup2 z_check2"><input type="checkbox"/></span>${memberMenuMap['menuOneName']}
                </p>
                <div class="z_cont">
                  <c:forEach var="menuTwo" items="${memberMenuMap['menuTwoList']}">
                    <dl>
                      <dt>
                        <c:if test="${menuTwo['operationLevel'] eq '1'}">
                          <span class="z_check1 on_check"><input type="checkbox" id="${menuTwo['menuId']}"/></span>
                          <span class="z_check2"><input type="checkbox" id="${menuTwo['menuId']}"/></span>
                        </c:if>
                        <c:if test="${menuTwo['operationLevel'] eq '0'}">
                          <span class="z_check1"><input type="checkbox" id="${menuTwo['menuId']}"/></span>
                          <span class="z_check2 on_check"><input type="checkbox" id="${menuTwo['menuId']}"/></span>
                        </c:if>
                        <c:if test="${menuTwo['operationLevel'] ne '0' and menuTwo['operationLevel'] ne '1'}">
                          <span class="z_check1"><input type="checkbox" id="${menuTwo['menuId']}"/></span>
                          <span class="z_check2"><input type="checkbox" id="${menuTwo['menuId']}"/></span>
                        </c:if>
                      </dt>
                      <dd>${menuTwo['menuName']}</dd>
                    </dl>
                  </c:forEach>
                </div>
              </li>
            </c:forEach>
            <!--循环项 end-->
          </ul>
          <!--提交按钮-->
          <br/>
          <div class="form-group  col-lg-10">
            <label class="col-sm-3 control-label"></label>
            <div class="col-sm-7">
              <a href="javascript:history.go(-1);" class="btn btn-primary" for="">取消</a>
              <button type="button" id="save" class="btn btn-primary" onclick="saveRoleSet('0')">保存</button>
              <button type="button" id="saveAs" class="btn btn-primary" onclick="saveRoleSet('1')">另存为新角色</button>
            </div>
          </div>
        </div>
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
    //TODO
    for(var i = 0; i < $('.checkboxCont li').length;i++){
      var parentBox = $('.checkboxCont li').eq(i).find('.z_cont');

      if(parentBox.find('.z_check2').length == parentBox.find('.z_check2.on_check').length){
        parentBox.siblings('.z_title').find('.z_check2').addClass('on_check').siblings().removeClass('on_check');
      }else if(parentBox.find('.z_check1').length == parentBox.find('.z_check1.on_check').length){
        parentBox.siblings('.z_title').find('.z_check1').addClass('on_check').siblings().removeClass('on_check');
      }else{
        parentBox.siblings('.z_title').find('span').removeClass('on_check').siblings().removeClass('on_check');
      }

      if($('.z_check2').length == $('.z_check2.on_check').length){
        $('.checkAll2').addClass('on_check');
      }else if($('.z_check1').length == $('.z_check1.on_check').length){
        $('.checkAll1').addClass('on_check');
      }else{
        $('.checkAll').removeClass('on_check').siblings().removeClass('on_check');
      }
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
      if ($("#projectList").val().indexOf("全部项目")>=0) {
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

  //提交保存
//  $('#save').on('click',function(){
    function saveRoleSet(saveType) {
      //1.获取角色及角色操作范围数据
      if (!CheckNull($("#cityList").val(), "请添加城市！")) {
        return false;
      }
      if ($("#cityList").val() == "") {
        alert("请添加城市！");
        return;
      }
      if ($("#cityList").val() != "全部城市") {
        if (!CheckNull($("#projectList").val(), "请添加项目！")) {
          return false;
        }
      }
      if ($("#cityList").val() != "全部城市" && $.trim($("#projectList").val()) == "") {
        alert("请添加项目！");
        return;
      }
      if ($("#roledesc").val().length == 0) {
        alert("请输入角色名称！");
      }
      //2.获取角色菜单操作级别数据
      var arr = [];
      for (var i = 0; i < $('.z_check1.on_check').not('.checkGroup1').length; i++) {
        var id = $('.z_check1.on_check').not('.checkGroup1').eq(i).find('input[type=checkbox]').attr('id');
        arr.push({id: id, type: 1});
      }
      for (var j = 0; j < $('.z_check2.on_check').not('.checkGroup2').length; j++) {
        var id = $('.z_check2.on_check').not('.checkGroup2').eq(j).find('input[type=checkbox]').attr('id');
        arr.push({id: id, type: 0});
      }
      console.log(arr);
      //arr转为json
      var jsonStr = "[";
      for (var i = 0; i < arr.length; i++) {
        jsonStr += "{id:'" + arr[i].id + "',type:" + arr[i].type + "}";
        if (i != arr.length - 1) {
          jsonStr += ",";
        }
      }
      jsonStr += "]";
//    alert(jsonStr);
      //saveType(0:保存,1:另存为)
      var setId = $("#setId").val();
      if(saveType == '1'){
        setId = null;
      }
      if(saveType == '1'){
        //角色名称重复校验
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
                /* Ajax提交数据 */
                $.ajax({
                  type: "POST",
                  url: "../memberAuthority/saveEditRoleSet",
                  data: {
                    setId: setId,
                    cityIds: $("#cityIds").val(),
                    cityList: $("#cityList").val(),
                    projectIds: $("#projectIds").val(),
                    projectList: $("#projectList").val(),
                    roledesc: $("#roledesc").val(),
                    remarks: $("#remarks").val(),
                    jsonStr: jsonStr
                  },
                  dataType: "json",
                  success: function (data) {
                    if (data.error == '0') {
                      alert("保存成功");
                      //window.history.back(-2);
                      window.location.href = '../memberAuthority/roleList.html?menuId='+$("#roledesc").val();
                    } else {
                      alert("保存失败");
                    }
                  }
                });
                /* ---------- */
              }
            }else{
              alert("对不起，操作失败！");
              return ;
            }
          }
        });
      }else{
        /* Ajax提交数据 */
        $.ajax({
          type: "POST",
          url: "../memberAuthority/saveEditRoleSet",
          data: {
            setId: setId,
            cityIds: $("#cityIds").val(),
            cityList: $("#cityList").val(),
            projectIds: $("#projectIds").val(),
            projectList: $("#projectList").val(),
            roledesc: $("#roledesc").val(),
            remarks: $("#remarks").val(),
            jsonStr: jsonStr
          },
          dataType: "json",
          success: function (data) {
            if (data.error == '0') {
              alert("保存成功");
              //window.history.back(-2);
              window.location.href = '../memberAuthority/roleList.html?menuId='+$("#roledesc").val();
            } else {
              alert("保存失败");
            }
          }
        });
      }



    }
//  });
</script>
</body>
</html>
