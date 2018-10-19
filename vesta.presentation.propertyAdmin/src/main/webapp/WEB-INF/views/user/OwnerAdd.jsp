<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/1
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript">

  //-----------------↓楼栋下拉框↓-----------------
  function turnBuild(){
    $("#buildingId").empty();//清空
    $("#unitId").empty();//清空
    $("#roomId").empty();//清空
    var projectId = document.getElementById("projectId").value;
    var buildId = "";
    $.ajax({
      url: "../user/listBuild",
      type: "post",
      async: "false",
      dataType: "json",
      data: {
        "projectIdDto": projectId,
      },
      success: function (json) {
        <!-- 获取返回代码 -->
        var code = json.code;
        if (code != 0) {
          var errorMessage = json.msg;
          alert(errorMessage);
        } else {
          <!-- 获取返回数据 -->
          var data = json.data;
          for(var n=0;n<data.length;n++){
            var ids=data[n].buildingId;
            var names=data[n].buildingName;
            $("#buildingId").append("<option id='"+ids+"' value='"+ids+"'>"+names+"</option>");
          }
          buildId = $("#buildingId").val();
        }
      },
    });
    setTimeout("turnUnit()",100);
//                    alert("2"+document.getElementById("buildingId").value);
//                    turnUnit()
  }

  //-----------------↓单元下拉框↓-----------------
  function turnUnit(){
    $("#unitId").empty();//清空
    $("#roomId").empty();//清空
    var buildingId = document.getElementById("buildingId").value;
//                alert("3"+"buildingId="+buildingId);
    $.ajax({
      url: "../user/listUnit",
      type: "post",
      async: "false",
      dataType: "json",
      data: {
        "buildingId": buildingId,
      },
      success: function (json) {
        <!-- 获取返回代码 -->
        var code = json.code;
        if (code != 0) {
          var errorMessage = json.msg;
          alert(errorMessage);
        } else {
          <!-- 获取返回数据 -->
          var data = json.data;
          for(var n=0;n<data.length;n++){
            var ids=data[n].unitId;
            var names=data[n].unitName;
            $("#unitId").append("<option id='"+ids+"' value='"+ids+"'>"+names+"</option>");
          }
        }
      },
    });

//                alert($("#unitId").val());

    setTimeout("turnRoom()",100);
  }
  //-----------------↓房间下拉框↓-----------------
  function turnRoom(){
    $("#roomId").empty();//清空
    var unitId = document.getElementById("unitId").value;
    $.ajax({
      url: "../user/listRoom",
      type: "post",
      async: "false",
      dataType: "json",
      data: {
        "unitId": unitId,
      },
      success: function (json) {
        <!-- 获取返回代码 -->
        var code = json.code;
        if (code != 0) {
          var errorMessage = json.msg;
          alert(errorMessage);
        } else {
          <!-- 获取返回数据 -->
          var data = json.data;
          for(var n=0;n<data.length;n++){
            var ids=data[n].roomId;
            var names=data[n].roomName;
            $("#roomId").append("<option id='"+ids+"' value='"+ids+"'>"+names+"</option>");
          }
        }
      },
    });
  }
</script>
<script type="text/javascript">
  function showOption(){
    //项目
    var projectId = document.getElementById("projectId").value;
    var projectName = document.getElementById("project"+projectId).textContent;
    document.getElementById("projectName").value = projectName;
//          alert("projectName"+projectName);
    //楼号
    var buildingId = document.getElementById("buildingId").value;
    var buildingName = document.getElementById("building"+buildingId).textContent;
    document.getElementById("buildingName").value = buildingName;
//          alert("buildingName"+buildingName);
    //单元
    var unitId = document.getElementById("unitId").value;
    var unitName = document.getElementById("unit"+unitId).textContent;
    document.getElementById("unitName").value = unitName;
//          alert("unitName"+unitName);
    //房间
    var roomId = document.getElementById("roomId").value;
    var roomName = document.getElementById("room"+roomId).textContent;
    document.getElementById("roomName").value = roomName;
//          alert("roomName"+roomName);
    setTimeout("return true",300);
  }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005500040000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../user/addOwner" method="post">
          <%--项目--%>
          <div class="form-group  col-lg-6">
            <label for="projectId" class="col-sm-3 control-label"><spring:message code="ownerManage.SelProject" /></label>

            <div class="col-sm-9">
              <input type="hidden" id="projectName" name="projectName">
              <select name="projectIdDto" class="form-control" id="projectId" onchange="turnBuild()">
                <%--<c:if test="${userOwnerDTO.projectName != null}"><option value="${userOwnerDTO.projectId}">${userOwnerDTO.projectName}</option></c:if>--%>
                <c:forEach items="${projectSelDTOs}" var="project">
                  <option value="${project.projectId}" id="project${project.projectId}">${project.projectName}</option>
                </c:forEach>
              </select>
              <%--<c:if test="${project.projectId eq userOwnerDTO.projectId}"> selected = "selected"</c:if>--%>
            </div>
          </div>
          <%--楼栋--%>
          <div class="form-group  col-lg-6">
            <label for="buildingId" class="col-sm-3 control-label"><spring:message code="ownerManage.SelBuilding"/></label>
            <div class="col-sm-9">
              <input type="hidden" id="buildingName" name="buildingName">
              <select name="buildingId" class="form-control" id="buildingId" onchange="turnUnit()">
                <%--<c:if test="${userOwnerDTO.buildingName != null}"><option value="${userOwnerDTO.buildingId}">${userOwnerDTO.buildingName}</option></c:if>--%>
                <c:forEach items="${buildingSelDTOs}" var="building">
                  <option value="${building.buildingId}" id="building${building.buildingId}">${building.buildingName}</option>
                </c:forEach>
                <%--<c:if test="${building.buildingId eq userOwnerDTO.buildingId}"> selected = "selected"</c:if>              --%>
              </select>
            </div>
          </div>
          <%--单元--%>
          <div class="form-group  col-lg-6">
            <label for="unitId" class="col-sm-3 control-label"><spring:message code="ownerManage.SelUnit"/></label>
            <div class="col-sm-9">
              <input type="hidden" name="unitName" id="unitName">
              <select name="unitId" class="form-control" id="unitId" onchange="turnRoom()">
                <%--<c:if test="${userOwnerDTO.unitName != null}"><option value="${userOwnerDTO.unitId}">${userOwnerDTO.unitName}</option></c:if>--%>
                <c:forEach items="${unitSelDTOs}" var="unit">
                  <option value="${unit.unitId}" id="unit${unit.unitId}">${unit.unitName}</option>
                </c:forEach>
              </select>
              <%--<c:if test="${unit.unitId eq userOwnerDTO.unitId}"> selected = "selected"</c:if>--%>
            </div>
          </div>
          <%--房间--%>
          <div class="form-group  col-lg-6">
            <label for="roomId" class="col-sm-3 control-label"><spring:message code="ownerManage.SelRoom"/></label>
            <div class="col-sm-9">
              <input type="hidden" id="roomName" name="roomName">
              <select name="roomId" class="form-control" id="roomId">
                <%--<c:if test="${userOwnerDTO.roomName != null}"><option value="${userOwnerDTO.roomId}">${userOwnerDTO.roomName}</option></c:if>--%>
                <c:forEach items="${roomDTOs}" var="room">
                  <option value="${room.roomId}" id="room${room.roomId}" >${room.roomName}</option>
                </c:forEach>
              </select>
              <%--<c:if test="${room.roomId eq userOwnerDTO.roomId}"> selected = "selected"</c:if>--%>
            </div>
          </div>

          <%--用户名--%>
          <%--<div class="form-group  col-lg-6">--%>
            <%--<label for="userName" class="col-sm-3 control-label"><spring:message code="ownerManage.SelUserName"/></label>--%>
            <%--<div class="col-sm-9">--%>
              <%--<input type="text" class="form-control" placeholder="" id="userName"--%>
                     <%--name="userName" value="${userOwnerDTO.userName}" >--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--业主姓名--%>
          <div class="form-group  col-lg-6">
            <label for="realName" class="col-sm-3 control-label"><spring:message code="ownerManage.SelName"/></label>
            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="realName"
                     name="realNameDto">
            </div>
          </div>
          <%--联系方式--%>
          <div class="form-group  col-lg-6">
            <label for="mobile" class="col-sm-3 control-label"><spring:message code="ownerManage.SelMobile"/></label>
            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="mobile"
                     name="mobileDto">
            </div>
          </div>
            <%--密码--%>
            <div class="form-group  col-lg-6">
              <label for="password" class="col-sm-3 control-label"><spring:message code="ownerManage.SelPassword"/></label>
              <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="" id="password"
                       name="passwordDto">
              </div>
            </div>

              <div class="clearfix"></div>
              <div class="text-center">
          <button type="submit" class="btn btn-primary" for=""><spring:message code="common_add"/></button>

          <a  href="" onclick="javascript:history.go(-1);" class="btn btn-primary" for="rolesetAdd" ><spring:message code="common_cancel"/></a>
</div>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>



</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
