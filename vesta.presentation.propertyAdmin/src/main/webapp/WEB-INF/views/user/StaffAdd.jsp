<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/1/25
  Time: 19:59
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
  <script>
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
</head>

<script type="text/javascript">


  //当引入和自建选择时，改变输入框状态
  function turnType(){
    var type = $("#typeDto").val();
    if (type == "IN"){
//      $("#projectId").attr({"disabled":true});
      $("#staffNameDto").attr({"readonly":true});
      $("#mobileDto").attr({"readonly":true});
//      $("#selStaffNameDiv").attr({"hidden":false});
    }
    if (type == "OFF"){
//      $("#projectId").attr({"disabled":false});
      $("#staffNameDto").attr({"readonly":false});
      $("#mobileDto").attr({"readonly":false});
//      $("#selStaffNameDiv").attr({"hidden":true});
    }
  }

  //当选定引入人时
  function selStaffName(){
    if($("#typeDto").val()=="OFF"){
      return false;
    }
    $("#userName_0Dto").val("");
    var userName = $("#userNameDto").val();
    var type = $("#typeDto").val();
    if(type == "OFF"){
      return false;
    }
    if(userName ==""){
      return false;
    }
    $.ajax({
      url: "../user/getWandaStaff",            //目标网址
      type: "post",
      async: "false",
      dataType: "json",
      data: {
        "userNameDto": userName            //参数
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
//          for(var n=0;n<data.length;n++){
          var staffName=data.staffName;
          var mobile=data.mobile;
          $("#staffNameDto").val(staffName);
          $("#mobileDto").val(mobile);
          $("#userName_0Dto").val(userName);
//          }

        }
      }
    });

  }
</script>
<script type="text/javascript">
  function testStaff(){
    var userName = $("#userNameDto").val();
    var userName_0 = $("#userName_0Dto").val();
    var projectId = $("#projectIdDto").val();
    var staffName = $("#staffNameDto").val();
    var mobile = $("#mobileDto").val();
    var sectionId = $("sectionIdDto").val();


    if(sectionId==""){
      alert($("#sectionIdNullDto").val());
      return false;
    }
    if(userName==""){
      alert($("#userNameNullDto").val());
      return false;
    }
    var a=new RegExp("^[A-Za-z0-9]+$");
    if(a.test(userName)){

    }
    else{
      alert("用户名只能是字母加数字！");
      return false;
    }
    if(userName.length>20){
      alert("用户名长度不能大于20");
      return false;
    }
    if(projectId==""){
      alert($("#projectIdNullDto").val());
      return false;
    }
    if(staffName==""){
      alert($("#staffNameNullDto").val());
      return false;
    }
    if(mobile==""){
      alert($("#mobileNullDto").val());
      return false;
    }
    if($("#typeDto").val()=="IN"){
      if(userName!=userName_0){
        alert("信息不匹配，请重新确认！");
        return false;
      }
    }

//    alert(mobile);
//    var m = new RegExp("/(^13\d{9}$)|(^14)[5,7]\d{8}$|(^15[0,1,2,3,5,6,7,8,9]\d{8}$)|(^17)[6,7,8]\d{8}$|(^18\d{9}$)/g")
//    if(!m.test(mobile))
//    {
//      alert("请输入正确的手机格式！");
//      return false;
//    }


  }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005700030000" username="${propertystaff.staffName}" />

<input type="hidden" id="userNameNullDto" value="<spring:message code="staffManage.userNameNull" />">
  <input type="hidden" id="projectIdNullDto" value="<spring:message code="staffManage.projectIdNull" />">
  <input type="hidden" id="staffNameNullDto" value="<spring:message code="staffManage.staffNameNull" />">
  <input type="hidden" id="mobileNullDto" value="<spring:message code="staffManage.mobileNull" />">
  <input type="hidden" id="sectionIdNullDto" value="<spring:message code="staffManage.sectionIdNull" />">
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <h3 class="text-center"><spring:message code="staffManage.StaffAdd" /></h3>
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../user/addStaff" method="post">
          <%--账号类型--%>
          <div class="form-group  col-lg-6">
            <label for="typeDto" class="col-sm-3 control-label"><spring:message code="staffManage.staffType" /></label>
            <div class="col-sm-9">
              <select name="typeDto" id="typeDto" onchange="turnType()" class="form-control">
                <option value="OFF"><spring:message code="staffManage.staffTypeOFF" /></option>
                <option value="IN"><spring:message code="staffManage.staffTypeIN" /></option>
              </select>
            </div>
          </div>
          <%--&lt;%&ndash;万达内部员工列表&ndash;%&gt;--%>
          <%--<div class="form-group  col-lg-6" hidden="hidden" id="selStaffNameDiv">--%>
          <%--<label for="type" class="col-sm-3 control-label"><spring:message code="staffManage.staffName" /></label>--%>
          <%--<div class="col-sm-9" >--%>
          <%--<select name="wandastaffId" id="wandastaffId" onchange="selStaffName()">--%>
          <%--<c:forEach var="wandaStaff" items="${userWandaStaffDTOs}">--%>
          <%--<option value="${wandaStaff.wandastaffId}">${wandaStaff.staffName}</option>--%>
          <%--</c:forEach>--%>
          <%--</select>--%>
          <%--</div>--%>
          <%--</div>--%>

          <%--用户名--%>
          <div class="form-group  col-lg-6">
            <label for="userNameDto" class="col-sm-3 control-label"><spring:message code="staffManage.staffUserName"/></label>
            <div class="col-sm-9">
              <input type="hidden" id="userName_0Dto">
              <input type="text" class="form-control" placeholder="" id="userNameDto" name="userNameDto"  onblur="selStaffName()">
            </div>
          </div>
          <%--项目公司--%>
          <div class="form-group  col-lg-6">
            <input type ="hidden" name="projectIdDto" id ="projectIdDto" value="${houseProjectDto.id}">
            <label for="projectNameDto" class="col-sm-3 control-label"><spring:message code="staffManage.staffProject"/></label>
            <div class="col-sm-9">
              <input type="text" readonly value="${houseProjectDto.name}" id="projectNameDto" class="form-control" readonly="readonly">
            </div>
          </div>

          <%--姓名--%>
          <div class="form-group  col-lg-6">
            <label for="staffNameDto" class="col-sm-3 control-label"><spring:message code="staffManage.staffName"/></label>
            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="staffNameDto" name="staffNameDto">
            </div>
          </div>

          <%--手机号--%>
          <div class="form-group  col-lg-6">
            <label for="mobileDto" class="col-sm-3 control-label"><spring:message code="staffManage.staffMobile"/></label>
            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="mobileDto" name="mobileDto" >
            </div>
          </div>
          <%--部门--%>
          <div class="form-group  col-lg-6">
            <label for="sectionIdDto" class="col-sm-3 control-label"><spring:message code="staffManage.staffSection"/></label>
            <div class="col-sm-9">
              <select class="form-control" placeholder="" id="sectionIdDto" name="sectionIdDto">
                <c:forEach items="${houseSectionAdminDTOs}" var="section">
                  <option value="${section.sectionId}">${section.sectionName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <%--&lt;%&ndash;密码&ndash;%&gt;--%>
          <%--<div class="form-group  col-lg-6">--%>
          <%--<label for="password" class="col-sm-3 control-label"><spring:message code="staffManage.staffPassword"/></label>--%>
          <%--<div class="col-sm-9">--%>
          <%--<input type="text" class="form-control" placeholder="" id="password"--%>
          <%--name="password">--%>
          <%--</div>--%>
          <%--</div>--%>
              <div class="clearfix"></div>
              <div class="text-center">
          <button type="submit" class="btn btn-primary" for="" onclick="return testStaff()"><spring:message code="common_confirm"/></button>

          <a  href="" onclick="javaScript:history.go(-1)" class="btn btn-primary" for="" ><spring:message code="common_cancel"/></a><span>${result.staffResult}</span>

</div>
        </form>
      </div>
      <%--搜索条件结束--%>


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