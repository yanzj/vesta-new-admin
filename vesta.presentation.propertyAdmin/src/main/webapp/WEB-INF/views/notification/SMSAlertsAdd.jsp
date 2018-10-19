<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/2/20
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
  <script src="../static/property/js/checkNullAllJsp.js"></script>
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
</head>
<script>
  function validate() {
      var repairCheck = document.getElementById("repairModelNum");
      var appealCheck = document.getElementById("appealModelNum");
      var activityCheck = document.getElementById("activityModelNum");
/*      if(repairCheck.checked) {}
      else{
          if($("#repairContent").val()!=null) {
              alert("请选择对应模块！");
            return false;
          }
      }
      if(appealCheck.checked) {}
      else{
          if($("#appealContent").val()!=null) {
              alert("请选择对应模块！");
            return false;
          }
      }
      if(!activityCheck.checked) {}
      else{
          if($("#activityContent").val()!=null) {
              alert("请选择对应模块！");
            return false;
          }
      }
    document.getElementById("frm").submit();*/
  }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100030000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">

      <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>

        <div class="form-body" style="min-height:1500px">
          <form class="form-horizontal" id="frm" action="../smsMessage/smsMessageAdd" method="post">
            <input type="hidden" id="menuId" value="000100030000">

            <div class="form-group  col-xs-10">
              <label for="cityNum" class="col-sm-3 control-label"><spring:message
                      code="announcementDTO.cityName"/></label>
              <div class="col-sm-5">
                <select id="cityNum" name="cityNum" class="form-control" onchange="queryProjectNameById()">
                  <option value="-1">--请选择城市--</option>
                  <c:forEach items="${city}" var="item" >
                    <option value="${item.cityId }"
                            <c:if test="${item.cityId eq '0'}">selected</c:if>>${item.cityName }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--城市下项目--%>
            <div class="form-group  col-xs-10">
              <label for="projectNum" class="col-sm-3 control-label"><spring:message
                      code="HousePayBatch.projectName"/></label>

              <div class="col-sm-5">
                <select id="projectNum" name="projectNum" class="form-control" onchange="queryContent()">
                  <option value="0">全部项目</option>
                </select>
              </div>
            </div>

            <%--房屋报修管理--%>
            <div class="form-group col-xs-10" style="padding-left: 3px;">
              <div class="col-sm-5" id="repair" style="padding-left: 295px;width:800px;height: 20px;">
                <input type="checkbox" id="repairModelNum" name="repairModelNum" value="005700030000" style="width: 15px;height: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="repairModelNum">房屋报修管理</label><br/>
              </div>
            </div>
            <div class="form-group col-xs-10">

              <div class="col-sm-5" style="padding-left: 323px;width:830px;height: 80px;">
                <textarea id="repairContent" name="repairContent" class="form-control" ></textarea>
              </div>
            </div>
            <%--<div class="form-group col-lg-10">

              <div class="col-sm-5" id="repair" style="padding-left: 323px;width:830px;height: 80px;">
                <input type="checkbox" id="repairModelNum" name="repairModelNum" value="005700030000" style="width: 15px;height: 15px;">
                <label for="repairModelNum">房屋报修管理</label><br/>
                <textarea id="repairContent" name="repairContent" class="form-control" ></textarea>
              </div>
            </div>--%>
            <%--身份申诉管理--%>
            <div class="form-group col-lg-10" style="padding-left: 3px;">
              <div class="col-sm-5" style="padding-left: 295px;width:800px;height: 20px;">
                <input type="checkbox" id="appealModelNum" name="appealModelNum" value="005400030000" style="width: 15px;height: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="appealModelNum">身份申诉管理</label><br/>
              </div>
            </div>
            <div class="form-group col-lg-10">

              <div class="col-sm-5" style="padding-left: 323px;width:830px;height: 80px;">
                <textarea id="appealContent" name="appealContent" class="form-control" ></textarea>
              </div>
            </div>
            <%--<div class="form-group col-lg-10">

              <div class="col-sm-5" style="padding-left: 323px;width:830px;height: 80px;">
                <input type="checkbox" id="appealModelNum" name="appealModelNum" value="005200010000" style="width: 15px;height: 15px;">
                <label for="appealModelNum">身份申诉管理</label>
                <textarea id="appealContent" name="appealContent" class="form-control" ></textarea>
              </div>
            </div>--%>
            <%--<div class="form-group col-lg-10">
              <label class="col-sm-3 control-label" for="appealModelNum">身份申诉管理</label>

              <div class="col-sm-5">
                <input type="checkbox" id="appealModelNum" name="appealModelNum" value="005200010000">
                <textarea id="appealContent" name="appealContent" class="form-control" ></textarea>
              </div>
            </div>--%>
            <%--活动报名管理--%>
            <div class="form-group col-lg-10" style="padding-left: 3px;">
              <div class="col-sm-5" style="padding-left: 295px;width:800px;height: 20px;">
                <input type="checkbox" id="activityModelNum" name="activityModelNum" value="005400030000" style="width: 15px;height: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="activityModelNum">活动报名管理</label><br/>
              </div>
            </div>
            <div class="form-group col-lg-10">

              <div class="col-sm-5" style="padding-left: 323px;width:830px;height: 80px;">
                <textarea id="activityContent" name="activityContent" class="form-control" ></textarea>
              </div>
            </div>

            <%--物业缴费管理--%>
            <div class="form-group col-lg-10" style="padding-left: 3px;">
              <div class="col-sm-5" style="padding-left: 295px;width:800px;height: 20px;">
                <input type="checkbox" id="paymentModelNum" name="paymentModelNum" value="005700010000" style="width: 15px;height: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="paymentModelNum">物业缴费管理</label><br/>
              </div>
            </div>
            <div class="form-group col-lg-10">

              <div class="col-sm-5" style="padding-left: 323px;width:830px;height: 80px;">
                <textarea id="paymentContent" name="paymentContent" class="form-control" ></textarea>
              </div>
            </div>
            <%--<div class="form-group col-lg-10">

            <div class="col-sm-5" style="padding-left: 323px;width:830px;height: 80px;">
              <input type="checkbox" id="activityModelNum" name="activityModelNum" value="005400030000" style="width: 15px;height: 15px;">
              <label for="activityModelNum">活动报名管理</label>
              <textarea id="activityContent" name="activityContent" class="form-control" ></textarea>
            </div>
          </div>--%>

            <%--<div class="form-group col-lg-10">
              <label class="col-sm-3 control-label" for="activityModelNum">活动报名管理</label>

              <div class="col-sm-5">
                <input type="checkbox" id="activityModelNum" name="activityModelNum" value="005400030000">
                <textarea id="activityContent" name="activityContent" class="form-control" ></textarea>
              </div>
            </div>--%>

            <div class="text-center form-group  col-lg-12" style="padding:0;">
              <input type="submit" onclick="validate()" class="btn btn-primary" value="确定">
              <a href="../smsMessage/smsList" class="btn btn-primary" for=""><spring:message
                      code="common_cancel"/></a>
            </div>

          </form>
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
  function queryProjectNameById() {
    var projectId = $("#cityNum").val();
    if(projectId == '-1'){
      $("#projectNum").empty();
      return ;
    }
    $("#planName").empty();
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
      var arr = data.data;
      $("#projectNum").empty();
      <%--$("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');--%>
      for (var k in arr) {
        $("#projectNum").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
//                alert(arr[k][0]);
      }

    });
  }

  function queryContent() {
    var cityNum = $("#cityNum").val();
    var projectNum = $("#projectNum").val();
    $.getJSON("/smsMessage/queryContent/" + cityNum + "/" + projectNum , function (data) {
      var arr = data.data;
      $("#repairContent").empty();
      $("#appealContent").empty();
      $("#activityContent").empty();
      $("#paymentContent").empty();

      var repairContent = arr.repairContent;
      var appealContent = arr.appealContent;
      var activityContent = arr.activityContent;
      var paymentContent = arr.paymentContent;

      $("#repairContent").append(repairContent);
      $("#appealContent").append(appealContent);
      $("#activityContent").append(activityContent);
      $("#paymentContent").append(paymentContent);

      if(repairContent != null) {
        $("#repairModelNum").prop("checked", true);
      }else {
        $("#repairModelNum").prop("checked", false);
      }
      if(appealContent != null) {
        $("#appealModelNum").prop("checked", true);
        //$('input[name=appealModelNum]').attr('checked',true);
      }else {
        $("#appealModelNum").prop("checked", false);
      }
      if(activityContent != null) {
        $("#activityModelNum").prop("checked", true);
      }else {
        $("#activityModelNum").prop("checked", false);
      }
      if(paymentContent != null) {
          $("#paymentModelNum").prop("checked", true);
      }else {
          $("#paymentModelNum").prop("checked", false);
      }
    });
  }

</script>
</body>
</html>