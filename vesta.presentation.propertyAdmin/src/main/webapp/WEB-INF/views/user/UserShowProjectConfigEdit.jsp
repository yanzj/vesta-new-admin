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
<%response.setHeader("cache-control", "public"); %>
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
    $(function(){
      console.log("sqq")
      $("#000200050000").addClass("active");
      $("#000200050000").parent().parent().addClass("in");
      $("#000200050000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <script src="../static/property/js/checkNullAllJsp.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <style>
    .flowersList img {
      width: 20px;
    }

    .imgList img {
      width: 100px;
      height: 120px;
    }

    .sidebar ul li {
      border-bottom: 0;
    }
  </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="000200050000" username="${propertystaff.staffName}"/>
  <input type="hidden" id="menuId" name="menuId" value="000200050000"/>

  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
        <form class="form-horizontal" id="froms" action="../defaultConfig/saveOrUpdateUserShowProjectConfig">
          <input type="hidden" id="projectNum" name="projectNum" value="${projectConfig.projectNum}">
          <!-- 项目 -->
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label">所配置项目：</label>
            <div class="col-sm-6">
              <h2>${projectConfig.projectName}</h2>
            </div>
          </div>
          <!-- 可选用户类型 -->
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label">可选用户类型：</label>
            <div class="col-sm-6">
              <div class="checkbox">
                <label>

                  <input type="checkbox" name="userTypes" value="1" <c:if test="${fn:contains(projectConfig.userTypes,'1')}">checked="checked"</c:if>>游客
                </label>
              </div>
              <div class="checkbox">
                <label>
                  <input type="checkbox" name="userTypes" value="2" <c:if test="${fn:contains(projectConfig.userTypes,'2')}">checked="checked"</c:if>>普通用户
                </label>
              </div>
              <div class="checkbox">
                <label>
                  <input type="checkbox" name="userTypes" value="3" <c:if test="${fn:contains(projectConfig.userTypes,'3')}">checked="checked"</c:if>>业主
                </label>
              </div>
              <div class="checkbox">
                <label>
                  <input type="checkbox" name="userTypes" value="4" <c:if test="${fn:contains(projectConfig.userTypes,'4')}">checked="checked"</c:if>>房屋关系人
                </label>
              </div>
              <div class="checkbox">
                <label>
                  <input type="checkbox" name="userTypes" value="6" <c:if test="${fn:contains(projectConfig.userTypes,'6')}">checked="checked"</c:if>>虚拟用户
                </label>
              </div>
            </div>
          </div>

          <div class="text-center form-group  col-lg-6">
            <button type="button" class="btn btn-primary" onclick="js_save()"><spring:message
                    code="common_save"/></button>
            <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);">
              <spring:message code="common_cancel"/></button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</div>
</div>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">


  function js_save(){
    //获取被选中的用户类型
    var userTypes = "";
    $("input[name='userTypes']:checked").each(function () {
      userTypes += this.value + ",";
    });
    $.ajax({
      url:"../defaultConfig/saveOrUpdateUserShowProjectConfig",
      type:"POST",
      async:"false",
      data:{
        "projectNum":$("#projectNum").val(),
        "userTypes":userTypes
      },
      dataType:"json",
      error:function(){
        alert("网络异常，可能服务器忙，请刷新重试");
      },
      success:function(data){
        if(data.error == "0"){
          alert("保存成功！");
          window.location.href = "../defaultConfig/userShowProjectConfigList.html?menuId=000200050000";
        }else if(data.error == "-1"){
          alert("保存失败，请联系管理员！");
        }
      }
    });
  }

</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>