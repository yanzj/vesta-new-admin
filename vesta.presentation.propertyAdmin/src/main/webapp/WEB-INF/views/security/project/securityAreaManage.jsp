<%--
  安全区域管理页面
  Created by IntelliJ IDEA.
  User: Jason
  Date: 2017/6/5
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <script>
      $(function () {
          $("#006300010000").addClass("active");
          $("#006300010000").parent().parent().addClass("in");
          $("#006300010000").parent().parent().parent().parent().addClass("active");
      })
      new WOW().init();
  </script>
  <style>
    label.error {
      margin-left: 1%;
      color: red;
    }
  </style>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="006300010000" username="${authPropertystaff.staffName}"/>


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="/securityProject/securityArea.html">
          <input type="hidden" name="groupId" value="${securityAreaDTO.groupId}">
          <%--<div class="form-group  col-lg-4">--%>
            <%--<label for="groupName" class="col-sm-5 control-label">集团名称：</label>--%>
            <%--<div class="col-sm-7">--%>
              <%--<input type="text" id="groupName" name="groupName" value="${securityAreaDTO.groupName}" class="form-control">--%>
            <%--</div>--%>
          <%--</div>--%>
          <div class="form-group  col-lg-4">
            <label for="areaName" class="col-sm-5 control-label">区域名称：</label>
            <div class="col-sm-7">
              <input type="text" id="areaName" name="areaName" value="${securityAreaDTO.areaName}" class="form-control">
            </div>
          </div>
          <button type="submit" class="btn btn-primary">搜索</button>
          <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">添加区域
          </button>
          <a type="button" class="btn btn-primary" href="../securityProject/securityGroup.html">返回</a>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="mall.serialNumber"/></td>
        <th>集团名称</th>
        <th>区域名称</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${securityAreaDTOList}" var="list" varStatus="row">
        <tr>
          <td><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>
          <td>${list.groupName}</td>
          <td>${list.areaName}</td>
          <td>
            <a href="../securityProject/editAreaRole.html?groupId=${list.groupId}&areaId=${list.areaId}">设置</a>
            <a href="../securityProject/securityProject.html?areaId=${list.areaId}&groupId=${securityAreaDTO.groupId}">项目管理</a>
            <a style="cursor: pointer" onclick="delcfm()" href="../securityProject/deleteSecurityArea.html?areaId=${list.areaId}" class="a3"><span class="span1">删除</span></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }../securityProject/securityArea.html?pageIndex={0}&groupId=${securityAreaDTO.groupId}&areaName=${securityAreaDTO.areaName}"/>
  </div>
</div>
</div>
</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content" name="form" id="form" action="../securityProject/addSecurityArea.html">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel2">添加区域</h4>
      </div>
      <div class="modal-body">
        <input type="hidden" name="groupId" value="${securityAreaDTO.groupId}">
        <div class="form-group  col-lg-12">
          <label class="col-sm-2 control-label">区域名称</label>
          <div class="col-sm-6">
            <input type="text" class="form-control" id="areaNames" name="areaName">
          </div>
        </div>
        <div class="modal-footer" style="border: none">
          <button type="submit" class="btn btn-primary" onclick="return checkAreaName()">保存</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
      </div>
    </form>
  </div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>

</body>
<script>
    function delcfm() {
        if (!confirm("你要关闭该项目吗？关闭之后该项目所关联的所有数据将不可恢复！确定吗？")) {
            window.event.returnValue = false;
        }
    }
    $().ready(function () {
        $("#form").validate({
            rules: {
                areaName: {
                    required: true,
                    minlength: 1,
                    maxlength: 25
                },
            },
            messages: {
                areaName: {
                    required: "请输入区域名称！",
                    minlength: "不能少于1个字符！",
                    maxlength: "请勿超过25个字！"
                },
            },
        })
    })
    function checkAreaName() {
        var state = '0';
        var areaNames = $('#areaNames').val();
        $.ajax({
            url: '/securityProject/chekAreaName',
            data: {
                areaName: areaNames
            },
            type: 'get',
            async: false,
            dataType: 'json',
            success: function (result) {
                state = result.data;
            }
        });
        if (state == '0') {
            alert("该区域名称已存在");
            return false;
        }
    }
</script>
</html>
