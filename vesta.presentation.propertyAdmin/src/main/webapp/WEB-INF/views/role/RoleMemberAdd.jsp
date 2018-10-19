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
    <script>
    $(function(){
      console.log("sqq")
      $("#002300010000").addClass("active");
      $("#002300010000").parent().parent().addClass("in");
      $("#002300010000").parent().parent().parent().parent().addClass("active");
    })
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

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="002300010000" username="${propertystaff.staffName}"/>

  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form id="form" class="form-horizontal" action="../memberAuthority/toAddRoleMember">
          <input type="hidden" id="menuId" name="menuId" value="002300010000"/>
          <input type="hidden" id="roleSetId" name="roleSetId" value="${userPropertystaffDTO.roleSetId}"/>
          <input type="hidden" id="roleName" name="roleName" value="${userPropertystaffDTO.roleName}"/>
          <!-- 城市 -->
          <div class="form-group  col-lg-4">
            <label for="scopeId" class="col-sm-3 control-label">区域：</label>
            <div class="col-sm-8">
              <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                <option value="">请选择</option>
                <c:forEach items="${city}" var="item" >
                  <option value="${item.cityId }"
                          <c:if test="${item.cityId eq userPropertystaffDTO.scopeId}">selected</c:if>>${item.cityName }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 项目 -->
          <div class="form-group  col-lg-4">
            <label for="projectCode" class="col-sm-3 control-label">项目：</label>
            <div class="col-sm-8">
              <select id="projectCode" name="projectCode" class="form-control">
                <c:forEach items="${projectList}" var="item" >
                  <option value="${item[0] }"
                          <c:if test="${item[0] eq userPropertystaffDTO.projectCode}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 账号名 -->
          <div class="form-group  col-lg-4">
            <label for="userNameDto" class="col-sm-3 control-label">账号名：</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="userNameDto"
                     name="userNameDto" value="${userPropertystaffDTO.userNameDto}">
            </div>
          </div>
          <!-- 联系方式 -->
          <div class="form-group  col-lg-4">
            <label for="mobileDto" class="col-sm-3 control-label">联系方式：</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="mobileDto"
                     name="mobileDto" value="${userPropertystaffDTO.mobileDto}">
            </div>
          </div>
          <!-- 所属公司 -->
          <div class="form-group  col-lg-4">
            <label for="company" class="col-sm-3 control-label">所属公司：</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="company"
                     name="company" value="${userPropertystaffDTO.company}">
            </div>
          </div>
          <!-- 角色 -->
          <div class="form-group  col-lg-4">
            <label for="roledesc" class="col-sm-3 control-label">角色：</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="roledesc"
                     name="roledesc" value="${userPropertystaffDTO.roledesc}">
            </div>
          </div>
          <!-- 创建时间查询_开始日期 -->
          <div class="form-group  col-lg-4">
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
          <div class="form-group  col-lg-4">
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
          <!-- 角色 -->
          <div class="form-group  col-lg-4">
            <label class="col-sm-3 control-label">角色：</label>
            <div class="col-sm-8">
              <h3>${userPropertystaffDTO.roleName}</h3>
            </div>
          </div>
          <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                  code="propertyCompany.propertySearch"/></button>
          <button type="button" class="btn btn-primary" for="propertySearch" onclick="saveMember('${userPropertystaffDTO.roleSetId}')">添加到当前角色</button>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td width="52px">
          <div class="checkbox">
            <label>
              <input type="checkbox" id="checkAll" value="">
            </label>
          </div>
        </td>
        <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
        <th>姓名</th>
        <th>账号名</th>
        <th>联系方式</th>
        <th>公司</th>
        <th>区域</th>
        <th>项目</th>
        <th>角色</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${staffUserList}" var="staffUser" varStatus="row">
        <tr>
          <td>
            <div class="checkbox">
              <label>
                <input type="checkbox" class="staffIds" value="${staffUser.staffId}" <c:if test="${fn:contains(staffUser.roleDesc, userPropertystaffDTO.roleName) }">disabled="disabled"</c:if>/>
              </label>
            </div>
          </td>
          <td><b>${row.index + 1}</b></td>
          <td>${staffUser.staffName}</td>
          <td>${staffUser.userName}</td>
          <td>${staffUser.mobile}</td>
          <td>${staffUser.company}</td>
          <td>${staffUser.scope}</td>
          <td>${staffUser.project}</td>
          <td>${staffUser.roleDesc}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/memberAuthority/toAddRoleMember?pageIndex={0}&userNameDto=${userPropertystaffDTO.userNameDto}&mobileDto=${userPropertystaffDTO.mobileDto}&company=${userPropertystaffDTO.company}&roledesc=${userPropertystaffDTO.roledesc}&beginTimeDto=${userPropertystaffDTO.beginTimeDto}&endTimeDto=${userPropertystaffDTO.endTimeDto}&scope=${userPropertystaffDTO.scope}&roleSetId=${userPropertystaffDTO.roleSetId}&roleName=${userPropertystaffDTO.roleName}"/>
  </div>

</div>


<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
  $(function(){
    $("#002300010000").addClass("active");
    $("#002300010000").parent().parent().addClass("in");
    $("#002300010000").parent().parent().parent().parent().addClass("active");

    //全选
    $("#checkAll").click(function(){
      var isCheck = $(this).is(':checked');
      var arr=$(".staffIds");
      for ( var i = 0; i < arr.length; i++){
        if(isCheck){
          //已选中
          arr[i].checked = true;
        }else{
          //未选中
          arr[i].checked = false;
        }
      }
    });
  })
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
  //通过城市获取项目列表
  function queryProjectNameById() {
    $("#projectCode").find("option").remove();
    /* -------------------- */
    var projectId = $("#scopeId").val();
    if(projectId == '-1'){
      $("#projectCode").empty();
      return ;
    }
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
      var arr = data.data;
      $("#projectCode").empty();
      $("#projectCode").append('<option value="">请选择</option>');
      for (var k in arr) {
        if(arr[k][0] != '0'){
          $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
        }
      }
    });
  }
  //批量添加到当前角色
  function saveMember(roleSetId){
    var staffIds = "";
    $(".staffIds").each(function(){
      if($(this).is(':checked')){
        staffIds += $(this).val()+",";
      }
    });
    if(staffIds != ""){
      myvalue = confirm("确定要添加成员到该角色吗?");
      if (myvalue == true) {
        $.ajax({
          type: "POST",
          url: "../memberAuthority/saveRoleMember",
          data: {
            roleSetId:roleSetId,
            staffIds:staffIds
          },
          dataType: "json",
          success: function (data) {
            if (data.error == '0') {
              alert("批量保存成功！");
              $("#form").submit();
            }else{
              alert("批量保存失败！");
            }
          }
        });
      }
    }else{
      alert("请先勾选成员！");
    }
  }
</script>
</body>
</html>