<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/12/7
  Time: 15:39
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
  <script src="../static/js/jquery.metadata.js"></script>
  <script>
    $(function () {
      console.log("sqq")
      $("#003100030000").addClass("active");
      $("#003100030000").parent().parent().addClass("in");
      $("#003100030000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
  <style>
    label,label.error {
      margin-left: 1%;
      color: red;
    }
  </style>
  <script type="text/javascript">
    function alt(optId,id1,id2,id3,id4,id5) {
      var optName=$('#'+optId).val();
      if("" == optName){
        $("#"+optId+8).html("请输入经营单位名称！");
        return;
      }
      if(optName.length>20){
        $("#"+optId+8).html("请勿超过20个字！");
        return;
      }
      $("#"+optId+8).html("");
      var url = "../BaseData/updateOperation";
      $.ajax({
        type: "post",
        async: "false",  //同步请求
        url: url,
        data: {optId: optId, optName: optName},
        dataType: "json",
        timeout: 1000,
        success: function (datas) {
          var code = datas.code;
          if (code != 0) {
            var errorMessage = json.msg;
            alert(errorMessage);
          } else {
            var data1 = datas.data;
            $("#" + id1).html(data1);//要刷新的div
          }
        },
        error: function () {
          alert("修改失败，请稍后再试！");
          return;
        }
      })
      tog(optId, id1, id2, id3, id4, id5);
    }

    function tog(idx,id1,id2,id3,id4,id5){
      $("#"+idx+8).html("");
      $("#"+idx).toggle();
      $("#"+id1).toggle();
      $("#"+id2).toggle();
      $("#"+id3).toggle();
      $("#"+id4).toggle();
      $("#"+id5).toggle();
    }

    $().ready(function() {
      $("#addOperation").validate({
        rules: {
          optName: {
            required: true,
            minlength: 1,
            maxlength: 20
          }
        },
        messages: {
          optName: {
            required: "请输入经营单位名称！",
            minlength: "不能少于1个字符！",
            maxlength: "请勿超过20个字！"
          }
        }
      });
    });
  </script>

</head>
<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="003100030000" username="${authPropertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <form class="form-horizontal" name="form1" id="addOperation" action="../BaseData/addOperation.html">
          <div class="form-group  col-lg-5">
            <div class="col-sm-12">
              <input type="text" name="optName" class="form-control">
            </div>
          </div>
          <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message code="common_add" /></button>
          <a type="button" class="btn btn-primary" href="../BaseData/projectRoleManage.html">返回</a>
        </form>
      </div>
    </div>
  </div>
</div>
<div class="table-responsive bs-example widget-shadow">
  <table width="100%" class="tableStyle">
    <thead>
    <tr>
      <td>序号</td>
      <th>经营单位名称</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${OperationList}" var="list" varStatus="row">
      <tr>
        <form id="${list.optId}9" onkeydown="if(event.keyCode==13){return false}">
          <td><b>${row.index+1}</b></td>
          <td><span id="${list.optId}1">${list.optName}</span>
            <input type="hidden" name="optId" value="${list.optId}">
            <input type="text" name="optName" id="${list.optId}" value="${list.optName}" style="display: none"><label id="${list.optId}8"></label></td>
          <td>
            <a id="${list.optId}2" onClick="tog('${list.optId}','${list.optId}'+1,'${list.optId}'+2,'${list.optId}'+3,'${list.optId}'+4,'${list.optId}'+5)"
               style="margin:0;color: #000000;cursor: pointer"><span class="glyphicon glyphicon-edit"><spring:message code="common_update" /></span></a>
            <a id="${list.optId}3" onClick="javascript:if (confirm('确定删除吗？')) location.href='../BaseData/delOperation.html?optId=${list.optId}';else return;" style="margin:0;color: #000000;cursor: pointer"><span class="glyphicon glyphicon-remove"><spring:message code="common_delete" /></span></a>
            <a type="button" class="btn btn_default" id="${list.optId}4" onClick="alt('${list.optId}','${list.optId}'+1,'${list.optId}'+2,'${list.optId}'+3,'${list.optId}'+4,'${list.optId}'+5)"
               style="display: none;cursor: pointer">确定</a>
            <a type="button" class="btn btn_default" id="${list.optId}5" onClick="tog('${list.optId}','${list.optId}'+1,'${list.optId}'+2,'${list.optId}'+3,'${list.optId}'+4,'${list.optId}'+5)"
               style="display: none;cursor: pointer"><spring:message code="common_cancel" /></a>
          </td>
        </form>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <%--<m:pager pageIndex="${webPage.pageIndex}"--%>
  <%--pageSize="${webPage.pageSize}"--%>
  <%--recordCount="${webPage.recordCount}"--%>
  <%--submitUrl="${pageContext.request.contextPath }../seller/typeList.html?pageIndex={0}"/>--%>
</div>
</diy>
</div>
</div>
<%@ include file="../../main/foolter.jsp" %>
</div>
</body>
</html>
