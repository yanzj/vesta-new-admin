<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/2/18
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
  <script type="text/javascript">
  function chooseDpt(){
    $("#userId").empty();
    $("#userName").empty();
    var departmentsId=$("#departmentId").val();
    var usersId="";
    $.ajax({
      url: "../property/showWorker",
      type: "post",
      async: "false",
      dataType: "json",
      data: {
        "departmentId": departmentsId,
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
          $("#userId").append("<option id='0' value='0'>"+"---请选择姓名---"+"</option>");
          for(var n=0;n<data.length;n++){
            var ids=data[n].userId;
            var names=data[n].userName;
            $("#userId").append("<option id='"+ids+"' value='"+ids+"'>"+names+"</option>");
          }
          usersId = $("#userId").val();
        }
      },
    });
  }
  function saveUser() {
    if("0" == $("#departmentId").val()){
      alert("请选择部门！");
      return;
    }
    if("0" == $("#userId").val()){
      alert("请选择人员！");
      return;
    }
    if("10"==$("#taskStatus").val()){
      var myTime=new Date().getTime();
//      alert("myTime:"+myTime);
      var completeDate=$("#completeDate").val();
      /*var toTime=(myTime-completeDate)/1000/60/60;
      //alert("toTime:"+parseInt(toTime));
      if(toTime<24){
        alert("距离待回访时间还有："+parseInt(24-toTime)+"小时,请稍后再试！");
        return;
      }*/
      var toTime=(myTime-completeDate)/1000/60;
      //alert("toTime:"+parseInt(toTime));
      if(toTime<3){
        alert("距离待回访时间还有："+parseInt(3-toTime)+"分钟,请稍后再试！");
        return;
      }
    }
    $.ajax({
      url:"/property/saveAssign",
      type:"POST",
      async:"false",
      dataType:"json",
      data:{
        "id":$("#id").val(),
        "userId":$("#userId").val(),
      },
      success:function(json){
        <!-- 获取返回代码 -->
        var code = json.code;
        if(code == 0){
          alert("分配成功！");
          window.parent.location.href="/property/workOrderManagement.html";
        }else if(code == 1){
          alert("分配失败！");
          return;
        }else if(code==2){
          alert("工单不存在，请刷新重试！");
          window.parent.location.href="/property/workOrderManagement.html";
          //return;
        }
      }
    });
  }
  </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020008" username="${propertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--工单分配开始--%>
      <div class="form-body">
        <%--<form class="form-horizontal" action="../property/assign.html">--%>
          <input type="hidden" id="id" name="id" value="${workerInfo.id}" />
          <input type="hidden" id="taskStatus" name="taskStatus" value="${worker.taskStatus}" />
          <input type="hidden" id="completeDate" name="completeDate" value="${worker.completeDate}" />
          <div class="form-group  col-lg-6">
            <label for="departmentId" class="col-sm-3 control-label"><spring:message code="workOrders.department" /></label>
            <div class="col-sm-6">
              <input type="hidden" id="departmentName" name="departmentName">
              <select id="departmentId" name="departmentId"  class="form-control" onchange="chooseDpt()">
                <c:forEach items="${worker.departmentMap}" var="item">
                  <option value="${item.key }">${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group  col-lg-6">
            <label for="userId" class="col-sm-3 control-label"><spring:message code="workOrders.worker" /></label>
            <div class="col-sm-6">
              <input type="hidden" id="userName" name="userName">
              <select id="userId" name="userId"  class="form-control">
                <c:forEach items="${worker.workerMap}" var="item">
                  <option value="${item.key }">${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="text-center">
            <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);"><spring:message code="common_cancel"/></button>
            <button type="button" class="btn btn-primary" onclick="saveUser()"><spring:message code="common_confirm"/></button>
          </div>
        <%--</form>--%>
      </div>
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