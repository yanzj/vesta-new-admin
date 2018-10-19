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
</head>
<style type="text/css">
  .search_button{
   text-align: center;
  }
  .control_btn{
    padding: 0 0 1rem 0;
    background-color: #fbfbfb;
  }
  .control_btn button,.control_btn a{
    margin-right: 1rem;
  }
  .form_b{
    height: 17rem;
  }
</style>
<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="002300010000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body form_b">
        <form id="form" class="form-horizontal" action="../memberAuthority/roleMemberList">
          <input type="hidden" id="roleSetId" name="roleSetId" value="${userPropertystaffDTO.roleSetId}"/>
          <input type="hidden" id="roleName" name="roleName" value="${userPropertystaffDTO.roleName}"/>
          <!-- 角色 -->
          <div class="form-group  col-xs-6">
            <label for="" class="col-sm-3 control-label">角色：</label>
            <div class="col-sm-8">
              <h3>${userPropertystaffDTO.roleName}</h3>
            </div>
          </div>
          <!-- 账号名 -->
          <div class="form-group  col-xs-6">
            <label for="userNameDto" class="col-sm-3 control-label">账号名：</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="userNameDto"
                     name="userNameDto" value="${userPropertystaffDTO.userNameDto}">
            </div>
          </div>
          <!-- 区域 -->
          <div class="form-group  col-xs-6">
            <label for="scope" class="col-sm-3 control-label">区域：</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="scope"
                     name="scope" value="${userPropertystaffDTO.scope}">
            </div>
          </div>
          <!-- 项目 -->
          <div class="form-group  col-xs-6">
            <label for="projectName" class="col-sm-3 control-label">项目：</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="projectName"
                     name="projectName" value="${userPropertystaffDTO.projectName}">
            </div>
          </div>

          <!-- 联系方式 -->
          <div class="form-group  col-xs-6">
            <label for="mobileDto" class="col-sm-3 control-label">管理员联系方式：</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="mobileDto"
                     name="mobileDto" value="${userPropertystaffDTO.mobileDto}">
            </div>
          </div>
          <!-- 所属公司 -->
          <div class="form-group  col-xs-6">
            <label for="company" class="col-sm-3 control-label">所属公司：</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="company"
                     name="company" value="${userPropertystaffDTO.company}">
            </div>
          </div>

          <%--
          <!-- 创建时间查询_开始日期 -->
          <div class="form-group  col-lg-6">
            <label for="beginTimeDto" class="col-sm-3 control-label">创建时间：</label>
            <div class="input-group date form_date col-sm-9" data-date=""
                 data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="beginTimeDto"
                     value="${userPropertystaffDTO.beginTimeDto}" name="beginTimeDto" readonly/>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <!-- 创建时间查询_结束日期 -->
          <div class="form-group  col-lg-6">
            <label for="endTimeDto" class="col-sm-3 control-label"><spring:message
                    code="HousePayBatch.to"/></label>
            <div class="input-group date form_date col-sm-9" data-date=""
                 data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="endTimeDto"
                     value="${userPropertystaffDTO.endTimeDto}" name="endTimeDto" readonly/>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          --%>
          <div class="form-group  col-xs-12 search_button">
            <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                    code="propertyCompany.propertySearch"/></button>
            <a href="../memberAuthority/toAddRoleMember?roleSetId=${userPropertystaffDTO.roleSetId}&roleName=${userPropertystaffDTO.roleName}" class="btn btn-primary" for="payBatchAdd">增加成员</a>
            <!--集合长度(取决Excel是否可以导出)-->
            <input type="hidden" id="size" value="${isExcel}">
            <a href="javascript:void(0);"  onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>

          </div>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <div class="">
    </div>
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <%--<td width="52px">
          <div class="checkbox">
            <label>
              <input type="checkbox" value="">
            </label>
          </div>
        </td>--%>
        <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
        <th>姓名</th>
        <th>账号名</th>
        <th>联系方式</th>
        <th>公司</th>
        <th>区域</th>
        <th>项目</th>
        <th>角色</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${staffUserList}" var="staffUser" varStatus="row">
        <tr>
            <%--<td>
              <div class="checkbox">
                <label>
                  <input type="checkbox" value="${staffUser.staffId}"/>
                </label>
              </div>
            </td>--%>
          <td><b>${row.index + 1}</b></td>
          <td>${staffUser.staffName}</td>
          <td>${staffUser.userName}</td>
          <td>${staffUser.mobile}</td>
          <td>${staffUser.company}</td>
          <td>${staffUser.scope}</td>
          <td>${staffUser.project}</td>
          <td>${staffUser.roleDesc}</td>
          <td class="last">
            <a href="javascript:void(0);" onclick="delMemberRole('${staffUser.staffId}','${userPropertystaffDTO.roleSetId}')" id="del"
               class="a3">删除</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/staffUser/staffUserList.html?pageIndex={0}&userNameDto=${userPropertystaffDTO.userNameDto}&mobileDto=${userPropertystaffDTO.mobileDto}&company=${userPropertystaffDTO.company}&roledesc=${userPropertystaffDTO.roledesc}&beginTimeDto=${userPropertystaffDTO.beginTimeDto}&endTimeDto=${userPropertystaffDTO.endTimeDto}"/>
  </div>

</div>



</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
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
<script>
  function queryProjectNameById() {
    var projectId = $("#scope").val();
    $("#planName").empty();
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId, function (data) {
      var arr = data.data;
      $("#projectName").empty();
      $("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');
      for (var k in arr) {
        $("#projectName").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
      }
    });
  }
  //删除成员角色
  function delMemberRole(staffIdDto,roleSetId) {
    myvalue = confirm("确定要删除该成员角色吗?");
    if (myvalue == true) {
      $.ajax({
        type: "POST",
        url: "../memberAuthority/delMemberRole",
        data: {
          staffIdDto:staffIdDto,
          roleSetId:roleSetId
        },
        dataType: "json",
        success: function (data) {
          if (data.error == '0') {
            alert("删除成员角色成功！");
            $("#form").submit();
          }else{
            alert("对不起，操作失败！");
          }
        }
      });
    }
  }
</script>
</body>
</html>