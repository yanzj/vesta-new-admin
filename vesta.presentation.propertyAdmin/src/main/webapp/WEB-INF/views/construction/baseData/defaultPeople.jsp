<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/11/10
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  <style>
    label.error {
      margin-left: 1%;
      color: red;
    }
  </style>

  <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
  <!-- font CSS -->
  <!-- font-awesome icons -->
  <link href="../static/css/font-awesome.css" rel="stylesheet">
  <!-- //font-awesome icons -->
  <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
  <!-- js-->
  <script src="../static/js/jquery-1.11.1.min.js"></script>
  <script src="../static/js/modernizr.custom.js"></script>
  <!--animate-->
  <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
  <script src="../static/js/wow.min.js"></script>
  <script>
    $(function(){
      console.log("sqq")
      $("#001000090000").addClass("active");
      $("#001000090000").parent().parent().addClass("in");
      $("#001000090000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
  <script src="../static/js/jquery.ztree.core-3.5.js"></script>
  <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
  <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001000090000" username="${authPropertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
          <button id="one" type="button" class="btn btn-primary" onclick="tabChange(1)">日常巡检</button>
          <button id="two" type="button" class="btn btn-primary" onclick="tabChange(2)">检查验收</button>
          <button id="three" type="button" class="btn btn-primary" onclick="tabChange(3)">样板点评</button>
          <button id="four" type="button" class="btn btn-primary" onclick="tabChange(6)">关键工序</button>
          <button id="five" type="button" class="btn btn-primary" onclick="tabChange(4)">材料验收</button>
          <button id="six" type="button" class="btn btn-primary" onclick="tabChange(5)">旁站</button>
        <input type="hidden" id="tabNum" value="${domainValue}">
      </div>
    </div>
  </div>

    <div class="table-responsive bs-example widget-shadow" data-example-id="basic-forms">
       <table width="100%" class="tableStyle">
          <thead>
          <tr>
            <th>检查项</th>
            <th>责任单位</th>
            <th>整改人</th>
            <th>第三方监理</th>
            <th>抄送人</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td><input type="checkbox" id="checkall" >全选</td>
            <td></td>
            <td><a href="#" data-toggle="modal" data-target="#myModal">批量设置</a></td>
            <td><a href="#" data-toggle="modal" data-target="#myModal">批量设置</a></td>
            <td><a href="#" data-toggle="modal" data-target="#myModal">批量设置</a></td>
          </tr>
          <c:forEach items="${categoryList}" var="list" varStatus="row">
            <tr>
              <td><input type="checkbox" name="category" value="${list.categoryId}">${list.categoryName}</td>
              <td>${employ.agencyName}</td>
              <td>${list.supplierName}</td>
              <td>${list.defManageName}</td>
              <td>${list.defOwnerName}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
    </div>

</div>
</div>
</div>
</div>
<!-- main content end-->

<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">选择整改人</h4>
      </div>
      <form name="addDefaultPeople" action="../BaseData/addDefaultPeople.html">
      <div class="modal-body">

      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary" data-dismiss="modal">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<%@ include file="../../main/foolter.jsp" %>
</div>
<script>
  $().ready(function() {
    $("#checkall").click(
            function(){
              if(this.checked){
                $("input[name='category']").each(function(){this.checked=true;});
              }else{
                $("input[name='category']").each(function(){this.checked=false;});
              }
            }
    );

    switch($('#tabNum').val())
    {
      case '1':
        $('#one').addClass("btn btn-default");
        break;
      case '2':
        $('#two').addClass("btn btn-default");
        break;
      case '3':
        $('#three').addClass("btn btn-default");
            break;
      case '4':
        $('#five').addClass("btn btn-default");
            break;
      case '5':
        $('#six').addClass("btn btn-default");
            break;
      case '6':
        $('#four').addClass("btn btn-default");
            break;
      default:
        $('#one').addClass("btn btn-default");
    }
  })

  function tabChange(tValue){
    window.location.href="../BaseData/defaultPeople.html?projectId=${projectId}&employId=${employ.agencyId}&domain="+tValue;
  }
</script>
</body>
</html>
