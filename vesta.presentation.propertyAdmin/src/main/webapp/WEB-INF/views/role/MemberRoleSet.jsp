<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <script src="../static/property/js/propertyHousPay.js"></script>
  <script type="text/javascript" src="../static/js/roleSetScript.js"></script>
  <style type="text/css">
    *{
      padding: 0;
      list-style: none;
      margin: 0;
    }
    .wrap{
      width: 800px;
      margin:20px auto;
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
  <input type="hidden" id="setId" value="${setId}">
  <input type="hidden" id="menuId" value="002300010000"/>
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
                <span class="z_check1"><input type="checkbox" id="${menuTwo['menuId']}"/></span>
                <span class="z_check2"><input type="checkbox" id="${menuTwo['menuId']}"/></span>
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
    <%--<input type="button" value="提交" id="btn" />--%>
    <br/>
    <div class="form-group  col-lg-5">
      <label class="col-sm-3 control-label"></label>
      <div class="col-sm-7">
        <%--<a href="../memberAuthority/roleList.html" class="btn btn-primary" for="">取消</a>--%>
        <a href="javascript:void(0);" class="btn btn-primary" onclick="cancel('${setId}')">取消</a>
        <button type="button" id="btn" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>

</div>

</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<script>
  function cancel(setId){
    window.location.href = "../memberAuthority/toCreateRole.html?menuId="+$("#menuId").val()+"&setId="+setId;
  }
</script>

</body>
</html>