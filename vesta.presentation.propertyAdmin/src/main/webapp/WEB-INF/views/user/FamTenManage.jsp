<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/2
  Time: 10:27
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
        "projectId": projectId
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
      }
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
        "buildingId": buildingId
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
      }
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
        "unitId": unitId
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
      }
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
  }

  function trunPayType(houseuserId){
    var  myvalue = confirm("确定要更改授权吗?");
      if(myvalue==true){
        $.ajax({
          url: "../user/turnPayType",
          type: "get",
          async: "false",
          dataType: "json",
          data: {
            "houseuserid": houseuserId
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
              var type = data;
//              alert(type);
//              alert(houseuserId);
              if(type=="YES"){
//                alert("1");
                document.getElementById(houseuserId).innerHTML="已授权";
//                $("#"+houseuserId).val("已授权");
              }else if(type=="NO"){
//                alert("2");
                document.getElementById(houseuserId).innerHTML="未授权";

//                $("#"+houseuserId).val("未授权");
              }
//              for(var n=0;n<data.length;n++){
//                var ids=data[n].roomId;
//                var names=data[n].roomName;
//                $("#roomId").append("<option id='"+ids+"' value='"+ids+"'>"+names+"</option>");
//              }
            }
          }
        });
      }
  }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005500020000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../user/famtenManage.html">
          <%--项目--%>
          <div class="form-group  col-lg-4">
            <label for="projectId" class="col-sm-4 control-label"><spring:message code="ownerManage.SelProject" /></label>

            <div class="col-sm-8">
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
          <div class="form-group  col-lg-4">
            <label for="buildingId" class="col-sm-4 control-label"><spring:message code="ownerManage.SelBuilding"/></label>
            <div class="col-sm-8">
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
          <div class="form-group  col-lg-4">
            <label for="unitId" class="col-sm-4 control-label"><spring:message code="ownerManage.SelUnit"/></label>
            <div class="col-sm-8">
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
          <div class="form-group  col-lg-4">
            <label for="roomId" class="col-sm-4 control-label"><spring:message code="ownerManage.SelRoom"/></label>
            <div class="col-sm-8">
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
          <div class="form-group  col-lg-4">
            <label for="userName" class="col-sm-4 control-label"><spring:message code="ownerManage.SelUserName"/></label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="userName"
                     name="userNameDto" value="${userOwnerDTO.userNameDto}" >
            </div>
          </div>
          <%--业主姓名--%>
          <div class="form-group  col-lg-4">
            <label for="realName" class="col-sm-4 control-label"><spring:message code="ownerManage.SelName"/></label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="realName"
                     name="realNameDto" value="${userOwnerDTO.realNameDto}">
            </div>
          </div>
          <%--联系方式--%>
          <div class="form-group  col-lg-4">
            <label for="mobile" class="col-sm-4 control-label"><spring:message code="ownerManage.SelMobile"/></label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="mobile"
                     name="mobileDto" value="${userOwnerDTO.mobileDto}">
            </div>
          </div>
            <div class="clearfix"></div>
              <%--开始时间--%>
              <div class="form-group  col-lg-6" >
                  <label for="beginTime" class="col-sm-3 control-label"><spring:message code="ownerManage.SelBeginTime"/></label>
                  <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                      <input type="text" class="form-control" placeholder="" id="beginTime" name="beginTime" value="${userOwnerDTO.beginTime}" readonly>
                      <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                      <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                  </div>
                </div>
            <div class="form-group  col-lg-6" >
                  <%--结束时间--%>
                  <label for="endTime" class="col-sm-3 control-label"><spring:message code="ownerManage.SelEndTime"/></label>
                  <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                      <input type="text" class="form-control" placeholder="" id="endTime" name="endTime" value="${userOwnerDTO.endTime}" readonly>
                      <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                      <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                  </div>
              </div>
            <div class="clearfix"></div>
          <%--搜索--%>
          <button type="submit" class="btn btn-primary" for=""><spring:message code="common_select"/></button>
          <%--新增--%>
          <%--<a  href="../user/gotoAddOwner" class="btn btn-add" for="rolesetAdd" ><spring:message code="common_add"/></a>--%>
            <a href="../user/tenant.html" class="btn btn-primary" for="rolestAdd" style="cursor: pointer"><spring:message code="ownerManage.addTenant" /></a>

        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <form action="#" method="post" id ="search_form">

      <%--<input id = "rolesetId" type="hidden" name="rolesetId"  value="">--%>
    </form>
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="ownerManage.sort" /></td>
        <th><spring:message code="ownerManage.Project" /></th>
        <th><spring:message code="ownerManage.Name" /></th>
        <%--<th><spring:message code="ownerManage.UserName" /></th>--%>
        <th><spring:message code="ownerManage.Mobile" /></th>

        <th><spring:message code="ownerManage.Building" /></th>
        <th><spring:message code="ownerManage.Unit" /></th>
        <th><spring:message code="ownerManage.Room" /></th>
        <th><spring:message code="ownerManage.UserType" /></th>
        <th><spring:message code="ownerManage.OwnerName" /></th>
        <th><spring:message code="ownerManage.OwnerMobile" /></th>
        <%--<th><spring:message code="ownerManage.CreateTime" /></th>--%>
        <th><spring:message code="ownerManage.Ispay" /></th>
        <th><spring:message code="ownerManage.Operation" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${userDTOs}" var="owner" varStatus="vs">
        <tr>
          <td><b>${vs.count}</b></td>
          <td>${owner.projectName}</td>
          <td>${owner.realNameDto}</td>
          <%--<td>${owner.userName}</td>--%>
          <td>${owner.mobileDto}</td>

          <td>${owner.buildingName}</td>
          <td>${owner.unitName}</td>
          <td>${owner.roomName}</td>
          <td>
            <c:if test="${owner.userType eq '4'}"><spring:message code="ownerManage.Family" /></c:if>
            <c:if test="${owner.userType eq '5'}"><spring:message code="ownerManage.Tenant" /></c:if>
          </td>
          <td>${owner.ownerName}</td>
          <td>${owner.ownerMobile}</td>
          <%--<td>${owner.registerDate}</td>--%>
          <td id="${owner.houseUserid}">
            <c:if test="${owner.ispay eq 'YES'}"><spring:message code="ownerManage.IspayYes" /></c:if>
            <c:if test="${owner.ispay eq 'NO'}"><spring:message code="ownerManage.IspayNo" /></c:if>
          </td>
          <td class="last">
              <%--&lt;%&ndash;<a class = "a1" href="javascript:void(0);" onclick="goSubmit('${roleSet.setId}')"><span class="span1"><spring:message code="staffManage.manageRoleset" /></span></a>&ndash;%&gt;--%>
              <%--&lt;%&ndash;<a href="../user/gotoDisRoleset?staffId=${staff.staffId}&roleSetId=${staff.roleSetId}&staffName=${staff.staffName}" class="a1"><span class="span1"><spring:message code="staffManage.manageRoleset" /></span></a>&ndash;%&gt;--%>
              <%--&lt;%&ndash;修改   <a href="" class="a2"><span class="span1"><spring:message code="common_update" /></span></a>&ndash;%&gt;--%>
                <a href="javascript:void(0);" onclick="trunPayType('${owner.houseUserid}')" class="a3"><span class="span1"><spring:message code="ownerManage.turnPayType" /></span></a>
              <a href="../user/delFamTen?houseUserid=${owner.houseUserid}" class="a3"><span class="span1"><spring:message code="common_delete" /></span></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/user/famtenManage.html?pageIndex={0}&projectId=${project.projectId}&buildingId=${building.buildingId}&unitName=${unit.unitId}&roomId=${room.roomId}&userNameDto=${userOwnerDTO.userNameDto}&realNameDto=${userOwnerDTO.realNameDto}&mobileDto=${userOwnerDTO.mobileDto}&beginTime=${userOwnerDTO.beginTime}&endTime=${userOwnerDTO.endTime}"/>
  </div>


</div>
</div>
</div>
</div>

<%--时间搜索插件--%>
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
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
