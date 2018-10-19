<%--
  Describe:数据更新管理
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/5/24
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="keywords" content=""/>
  <script type="application/x-javascript">
    addEventListener("load",
            function () {
              setTimeout(hideURLbar, 0);
            }, false);
    function hideURLbar() {
      window.scrollTo(0, 1);
    }
  </script>
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
  <!--//Metis Menu -->
  <style type="text/css">
    .btn-primary {
      background-color: #337ab7;
      border-color: #2e6da4;
      color: #fff;
      margin-bottom: 10px;
    }
    .forms label {
      font-size: 1.1em;
      font-weight: 400;
      position: relative;
      top: 10px;
    }
  </style>
  <style type="text/css">
    #dialog-overlay {
      width:100%;
      height:100%;
      filter:alpha(opacity=50);
      -moz-opacity:0.5;
      -khtml-opacity: 0.5;
      opacity: 0.5;
      position:absolute;
      background:#000;
      top:0; left:0;
      z-index:3000;
      display:none;
    }
    #dialog-box {
      -webkit-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
      -moz-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
      -moz-border-radius: 5px;
      -webkit-border-radius: 5px;
      background:#eee;
      width:328px;
      position:absolute;
      z-index:5000;
      display:none;
    }
    #dialog-box .dialog-content {
      text-align:left;
      padding:10px;
      margin:13px;
      color:#666;
      font-family:arial;
      font-size:13px;
    }
    a.button {
      margin:10px auto 0 auto;
      text-align:center;
      background-color: #337ab7;
      display: block;
      width:50px;
      padding: 5px 10px 6px;
      color: #fff;
      text-decoration: none;
      font-weight: bold;
      line-height: 1;
      -moz-border-radius: 5px;
      -webkit-border-radius: 5px;
      -moz-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
      -webkit-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
      text-shadow: 0 -1px 1px rgba(0,0,0,0.25);
      border-bottom: 1px solid rgba(0,0,0,0.25);
      position: relative;
      cursor: pointer;
    }
    a.button:hover {
      background-color: #337ab7;
    }
    #dialog-box .dialog-content p {
      font-weight:700; margin:0;
    }
    #dialog-box .dialog-content ul {
      margin:10px 0 10px 20px;
      padding:0;
      height:50px;
    }
  </style>
  <script type="application/javascript">
    function saveHouse() {
      if(!CheckNull($("#modifyDate").val(),"请选择修改时间！")){
        return false;
      }
      if ("" == $("#modifyDate").val()) {
        alert("请选择修改时间！");
        return;
      }
      popup('提醒：数据更新时间较长，请稍候...');
      $.ajax({
        url:"/property/querySubmit",
        type:"POST",
        async:"false",
        dataType:"json",
        data:{
          "project":$("#project").val(),
          "modifyDate":$("#modifyDate").val(),
        },
        success:function(json){
          <!-- 获取返回代码 -->
          var code = json.code;
          if(code == 0){
            alert("更新成功！");
            window.parent.location.href="/property/basicData.html";
          }else if(code == 1){
            alert("更新失败！");
            window.parent.location.href="/property/basicData.html";
          }
        }
      });
    }
    function saveLevel() {
      popup('提醒：数据更新时间较长，请稍候...');
      $.ajax({
        url:"/property/querySubmit",
        type:"POST",
        async:"false",
        dataType:"json",
        data:{
          "thirdLevel":2,
        },
        success:function(json){
          <!-- 获取返回代码 -->
          var code = json.code;
          if(code == 0){
            alert("更新成功！");
            window.parent.location.href="/property/basicData.html";
          }else if(code == 1){
            alert("更新失败！");
            window.parent.location.href="/property/basicData.html";
          }
        }
      });
    }
    function saveRoom() {
      popup('提醒：数据更新时间较长，请稍候...');
      $.ajax({
        url:"/property/querySubmit",
        type:"POST",
        async:"false",
        dataType:"json",
        data:{
          "roomId":3,
        },
        success:function(json){
          <!-- 获取返回代码 -->
          var code = json.code;
          if(code == 0){
            alert("更新成功！");
            window.parent.location.href="/property/basicData.html";
          }else if(code == 1){
            alert("更新失败！");
            window.parent.location.href="/property/basicData.html";
          }
        }
      });
    }
    function saveSupplier() {
      /*if ("" == $("#projectNum").val()) {
        alert("请选择项目！");
        return;
      }*/
      popup('提醒：数据更新时间较长，请稍候...');
      $.ajax({
        url:"/property/querySubmit",
        type:"POST",
        async:"false",
        dataType:"json",
        data:{
          "projectNum":$("#projectNum").val(),
        },
        success:function(json){
          <!-- 获取返回代码 -->
          var code = json.code;
          if(code == 0){
            alert("更新成功！");
            window.parent.location.href="/property/basicData.html";
          }else if(code == 1){
            alert("更新失败！");
            window.parent.location.href="/property/basicData.html";
          }
        }
      });
    }
  </script>
  <script type="application/javascript">
    $(document).ready(function () {
      $('a.btn-ok, #dialog-overlay, #dialog-box').click(function () {
        $('#dialog-overlay, #dialog-box').hide();
        return false;
      });
      $(window).resize(function () {
        if (!$('#dialog-box').is(':hidden')) popup();
      });
    });
    //Popup dialog
    function popup(message) {
      var maskHeight = $(document).height();
      var maskWidth = $(window).width();
      //var dialogTop =  (maskHeight/3) - ($('#dialog-box').height());
      var dialogTop = 200;
      //var dialogLeft = (maskWidth/2) - ($('#dialog-box').width()/2);
      var dialogLeft = 600;
      $('#dialog-overlay').css({height:maskHeight, width:maskWidth}).show();
      $('#dialog-box').css({top:dialogTop, left:dialogLeft}).show();
      if(message != ""){
        $('#dialog-message').html(message);
      }
      $("#update1").attr("disabled", true);
      $("#update2").attr("disabled", true);
      $("#update3").attr("disabled", true);
      $("#update4").attr("disabled", true);
    }
  </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001800010000" username="${authPropertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <%--<form:form class="form-horizontal" action="../property/querySubmit" modelAttribute="dataSearch" method="post">--%>
        <div class="form-group  col-lg-12">
          <div class="form-group  col-lg-2">
            <label class="col-sm-12 control-label"><spring:message code="data.houseInfo" /></label>
          </div>
          <div class="form-group  col-lg-4">
            <label for="project" class="col-sm-3 control-label"><spring:message code="propertyCompany.propertyProject" />：</label>
            <div class="col-sm-8">
              <%--<form:select items="${data.propertyProjectMap}" path="project" class="form-control"/>--%>
              <select id="project" name="project"  class="form-control">
                <c:forEach items="${data.propertyProjectMap}" var="item">
                  <option value="${item.key }">${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group  col-lg-4">
            <label for="modifyDate" class="col-sm-3 control-label"><spring:message code="operationLog.time"/>：</label>
            <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd HH:mm:ss"
                 data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="请输入修改时间" id="modifyDate" name="modifyDate" readonly="true" />
              <%--value="${dataSearch.modifyDate}"--%>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="text-right col-lg-2">
          <c:if test="${function.qch40010066 eq 'Y'}">
            <button type="button" class="btn btn-primary" onclick="saveHouse()" id="update1"><spring:message code="common_renewal"/></button>
          </c:if>
        </div>
        </div>

        <div class="form-group  col-lg-12">
          <div class="form-group  col-lg-3">
            <label class="col-sm-7 control-label"><spring:message code="data.threeLevel" /></label>
          </div>
          <div class="text-right col-lg-9">
          <c:if test="${function.qch40010067 eq 'Y'}">
            <button type="button" class="btn btn-primary" onclick="saveLevel()" id="update2"><spring:message code="common_renewal"/></button>
          </c:if>
        </div>
        </div>

        <div class="form-group  col-lg-12">
          <div class="form-group  col-lg-3">
            <label class="col-sm-12 control-label"><spring:message code="data.roomLocation" /></label>
          </div>
          <div class="text-right col-lg-9">
          <c:if test="${function.qch40010068 eq 'Y'}">
            <button type="button" class="btn btn-primary" onclick="saveRoom()" id="update3"><spring:message code="common_renewal"/></button>
          </c:if>
        </div>
        </div>

        <div class="form-group  col-lg-12">
          <div class="col-lg-2" style="margin-bottom: 10px">
            <label class="col-sm-12 control-label"><spring:message code="data.supplier" /></label>
          </div>
          <div class="form-group  col-lg-4">
            <label for="projectNum" class="col-sm-3 control-label"><spring:message code="propertyCompany.propertyProject" />：</label>
            <div class="col-sm-8">
              <%--<form:select items="${data.propertyProjectMap}" path="projectNum" class="form-control"/>--%>
              <select id="projectNum" name="projectNum"  class="form-control">
                <c:forEach items="${data.propertyProjectMap}" var="item">
                  <option value="${item.key }">${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="text-right" style="margin-right: 15px;">
            <c:if test="${function.qch40010069 eq 'Y'}">
              <button type="button" class="btn btn-primary" onclick="saveSupplier()" id="update4"><spring:message code="common_renewal"/></button>
            </c:if>
          </div>
          <div id="dialog-overlay"></div>
          <div id="dialog-box">
          <div class="dialog-content">
          <div id="dialog-message"></div>
            <a href="#" class="button"><spring:message code="common_close"/></a>
          </div>
        </div>
        </div>
        <%--</form:form>--%>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
</div>
</div>
</div>
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
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>
