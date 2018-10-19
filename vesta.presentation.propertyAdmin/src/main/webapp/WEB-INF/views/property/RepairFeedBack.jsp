<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/2/17
  Time: 11:44
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
  <script src="../static/property/js/checkNullAllJsp.js"></script>
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
    function saveTask(){
      var taskContent=document.getElementById("content").value;
      if(!CheckNull(taskContent,"请输入任务内容！")){
        return false;
      }
      if(taskContent==null || taskContent==""){
        alert("请输入任务内容！");
        return;
      }
      if(taskContent.length>200){
        alert("您输入的任务内容已超过200字符！")
        return;
      }
      $.ajax({
        url:"/property/feedBack",
        type:"POST",
        async:"false",
        dataType:"json",
        data:{
          "id":$("#id").val(),
          "taskContent":$("#content").val(),
        },
        success:function(json){
          <!-- 获取返回代码 -->
          var code = json.code;
          if(code == 0){
            alert("保存成功！");
            window.parent.location.href="/property/repairDetail.html?id="+$("#id").val();
          }else if(code==1){
            alert("保存失败！");
            return;
          }else if(code==2){
            alert("工单不在可编辑状态,请刷新重试！");
            window.parent.location.href="/property/workOrderManagement.html";
          }else if(code==3){
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
      <%--工单信息添加开始--%>
      <div class="form-body">
        <h3 class="text-center"><spring:message code="workOrders.repairFeedBack" /></h3>
        <br/>
          <%--<form class="form-horizontal" action="../property/freeBack.html" id="freeBack">--%>
          <div class="form-horizontal">
            <input type="hidden" id="id" name="id" value="${progressInfo.id}" />
            <div class="form-group">
              <label class="col-sm-5 control-label"><spring:message code="workOrders.progressContent" /></label>
              <div class="col-sm-6">
                <textarea class="form-control" placeholder="最多输入200个字符" name="content" id="content">${progressInfo.taskContent}</textarea>
              </div>
            </div>
            <div class="text-center">
              <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);"><spring:message code="common_cancel"/></button>
              <button type="submit" class="btn btn-primary" onclick="saveTask()"><spring:message code="common_save"/></button>
            </div>
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
