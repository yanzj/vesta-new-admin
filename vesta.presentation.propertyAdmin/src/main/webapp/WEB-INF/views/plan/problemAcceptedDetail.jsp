<%--
  Created by IntelliJ IDEA.
  User: lpc
  Date: 2016/6/5
  Time: 10:53
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    $(function(){
      console.log("sqq")
      $("#002000010000").addClass("active");
      $("#002000010000").parent().parent().addClass("in");
      $("#002000010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <link rel="stylesheet" href="../../../static/css/zoom.css" media="all"/>
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <%--<link rel="stylesheet" href="../static/css/jquery-ui.min.css" />
  <script src="../static/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>--%>
  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <!--//Metis Menu -->
  <style>
    label.error {
      /*position: absolute;*/
      /*margin-top: -74px;*/
      /*display: block;*/
      margin-left: 1%;
      color: red;
    }
    .imgSpan {
      height: 100px;
      width: 100px;
      display: inline-block;
      background: url("/static/images/logo.png") no-repeat center center;
      background-size: contain;
      margin-top: -10px;
      overflow: hidden;
    }

    .imgSpan img {
      width: 100%;
      height: 100%;
    }
  </style>

  <style type="text/css">#dialog{display:none;}</style>
  <script type="application/javascript">

    function checkAllBox(obj){
      var answer= document.getElementsByName("answer");
      if(obj.checked==true){
        for(var i=0;i<answer.length;i++){
          answer[i].checked = true;
        }
      }else{
        for(var i=0;i<answer.length;i++){
          answer[i].checked = false;
        }
      }
    }
    function checkData(){
      var answer= document.getElementsByName("answer");
      var flag = false;
      for(var i=0;i<income.length;i++){
        if(income[i].checked == true){
          flag = true;
          break;
        }
      }
      if(!flag){
        alert("请至少选择一项");
      }
      return flag;
    }

    function deleteQuestion(repairId){
      if(window.confirm("确定作废吗？")){
        window.location.href = "../problemGuarantee/deleteRepair?rectifyId="+repairId;
      }
    }
  </script>
  <script>
    $(function(){
      $("#button1").click(function(){
        if(window.confirm("确定保存吗？")){
          var data = $("#frm").serialize();
          window.location.href = "../problemGuarantee/saveRepair?problem="+data;
        }
      });
    });
  </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="002000010000" username="${authPropertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body" style="font-size:14px;">
        <form class="form-horizontal" id="frm" name="frm" action="../problemGuarantee/acceptTask" method="post">
          <input type="hidden" id="caseState" name="caseState" value="${problem.caseState}">
          <input type="hidden" id="caseId" name="caseId" value="${problem.caseId}">
          <input type="hidden" id="userName" name="userName" value="${problem.userName}">
          <input type="hidden" id="userPhone" name="userPhone" value="${problem.userPhone}">
          <input type="hidden" id="address" name="address" value="${problem.address}">
          <input type="hidden" id="casePlace" name="casePlace" value="${problem.casePlace}">
          <input type="hidden" id="caseDesc" name="caseDesc" value="${problem.caseDesc}">
          <input type="hidden" id="standardDate" name="standardDate" value="${problem.standardDate}">

          <table width="700px">
            <tr valign="middle">
              <td height="30px"><h2 align="center">报修单</h2></td>
              <td height="30px" align="right">
                <div class="clearfix"></div>
                <c:if test="${problem.handleType eq '1'}">
                  <button type="submit" class="btn btn-primary" for="propertyAccept" >接受任务</button>
                </c:if>
                <c:if test="${function.qch40010089 eq 'Y'}">
                  <input type="button" class="btn btn-primary" id="button1" name="button1" value=" 保存 "/>
                </c:if>
              </td>
            </tr>
            <tr valign="middle"><td colspan="2" height="25px"><hr style="background-color: gray;height: 1px;border: none"/></td></tr>
            <tr valign="middle">
              <td height="30px">
                <span class="notice_time"><spring:message code="problem.createdate" />：
                    <fmt:formatDate  type="date" value="${problem.createDate}" dateStyle="default" pattern="yyyy-MM-dd HH:mm:ss" />
                </span>
              </td>

              <td height="30px">
                <span class="ques_sta"><spring:message code="problem.status" />：</span>
                <c:if test="${problem.caseState eq 'accepted'}"><span>待接单</span></c:if>
                <c:if test="${problem.caseState eq 'processing'}"><span>处理中</span></c:if>
                <c:if test="${problem.caseState eq 'completed'}"><span>已完成</span></c:if>
                <c:if test="${problem.caseState eq 'closed'}"><span>已废弃</span></c:if>
              </td>
            </tr>
            <tr valign="middle">
              <td height="30px"><span class="record_num"><spring:message code="problem.createby" />：${problem.userName}</span></td>
              <td height="30px"><span class="phone"><spring:message code="problem.repairphone" />：</span><span>${problem.userPhone}</span></td>
            </tr>
            <tr valign="middle"><td colspan="2" height="25px"><hr style="background-color: gray;height: 1px;border: none"/></td></tr>

            <tr valign="middle">
              <td colspan="2" height="30px"><span class="key1">房间：</span><span class="value_">${problem.address}</span></td>
            </tr>
            <tr valign="middle">
              <td colspan="2" height="30px"><span class="key1"><spring:message code="problem.location" />：</span><span class="value_">${problem.casePlace}</span></td>
            </tr>
            <tr valign="middle">
              <td colspan="2" height="30px"><span class="key1">报修人：</span><span class="value_">${problem.userName}  ${problem.userPhone}</span></td>
            </tr>
            <tr valign="middle">
              <td colspan="2" height="30px"><span class="key1">问题描述：</span><span class="value_">${problem.caseDesc}</span></td>
            </tr>
            <tr valign="middle">
              <td colspan="2" height="30px">
                <div class="repair_per col-sm-4">
                  <ul class="gallery" >
                    <span class="key1"><spring:message code="problem.image" /></span>
                    <li style="    width: 770px;height: 110px;">
                      <c:forEach items="${problem.questionImageList}" var="image">
                        <a href="${image.imageUrl}" style="float: left; width: 100px; margin-right: 7px;">
                          <label class="control-label imgList">
                              <%--<img src="${image.imageUrl}"  height="100" width="200" style="width:100%">--%>
                               <span class="imgSpan"
                                     style="background:url(${image.imageUrl}) no-repeat center center;background-size:cover;"></span>
                          </label>
                        </a>
                      </c:forEach>
                    </li>
                  </ul>
                </div>
              </td>

              <%--<div class="repair_per col-sm-4">--%>
              <%--<ul class="gallery" >--%>
              <%--<span class="key1"><spring:message code="problem.image" /></span>--%>
              <%--<li>--%>
              <%--<c:forEach items="${problem.questionImageList}" var="image">--%>
              <%--<a href="${image.imageUrl}">--%>
              <%--<label class="control-label imgList"><img src="${image.imageUrl}"  height="100" width="200"></label>--%>
              <%--</a>--%>
              <%--</c:forEach>--%>
              <%--</li>--%>
              <%--</ul>--%>
              <%--</div>--%>
              <%--<span class="key1"><spring:message code="problem.image" />：</span>--%>
              <%--<c:forEach items="${problem.questionImageList}" var="image">--%>
              <%--<label class="control-label imgList"><img src="${image.imageUrl}"></label>--%>
              <%--</c:forEach></td>--%>
            </tr>
            <tr valign="middle"><td colspan="2" height="25px"><hr style="background-color: gray;height: 1px;border: none"/></td></tr>
            <%-- 一级分类 --%>
            <tr>
              <td colspan="2" height="30px"><label for="oneType" ><spring:message code="problem.fristLevel"/>：</label>
                <select placeholder="" id="oneType" name="oneType">
                  <c:forEach items="${typeMaps.firstLevels}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq problem.classifyOne}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </td>
            </tr>

            <%-- 二级分类--%>
            <tr valign="middle">
              <td colspan="2" height="30px"><label for="twoType" ><spring:message code="problem.secondLevel"/>：</label>
                <select placeholder="" id="twoType" name="twoType">
                  <c:forEach items="${typeMaps.secondLevels}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq problem.classifyTwo}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </td>
            </tr>

            <%--  三级分类 --%>
            <tr valign="middle">
              <td colspan="2" height="30px"><label for="threeType"><spring:message code="problem.thirdLevel"/>：</label>
                <select placeholder="" id="threeType" name="threeType">
                  <c:forEach items="${typeMaps.thirdLevels}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq problem.classifyThree}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </td>
            </tr>

            <tr valign="middle">
              <td colspan="2" height="30px"><label for="repairMode">维修方式：</label>
                <select placeholder="" id="repairMode" name="repairMode">
                  <c:forEach items="${typeMaps.repairModes}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq problem.repairWay}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr valign="middle">
              <td colspan="2" height="30px"><span class="key1">标准工时：</span><span class="value_" id="standardDa">${problem.standardDate}</span></td>
            </tr>
          </table>

          <%--<div class="clearfix"></div>
          <button type="submit" class="btn btn-primary" for="propertyAccept" >接受任务</button>--%>
          <%-- <button type="button" class="btn btn-primary" for="propertyAdd" data-toggle="modal" data-target="#myModal"><spring:message code="problem.export"/></button>--%>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
</div>

<!-- main content end-->
<script type="text/javascript">
  var pages = "${webPage.pageIndex}";
</script>
<script type="text/javascript" src="../../../static/js/zoom.min.js"></script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

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
<%@ include file="../main/foolter.jsp" %>
</div>

<script>
  $(function() {
    $("#oneType").change(function(){
      var oneType = $("#oneType").val();
      $.ajax({
        url:"../problemGuarantee/getGuaranteeSecondTypeList",
        type:"get",
        async:"false",
        data:{ "oneType":oneType},
        dataType:"json",
        error:function(){
          alert("网络异常，可能服务器忙，请刷新重试");
        },
        success:function(json){
          <!-- 获取返回代码 -->
          var code = json.code;
          if(code != 0){
            var errorMessage = json.msg;
            alert(errorMessage);
          }else{
            <!-- 获取返回数据 -->
            var data = json.data;
            var option ="";
            if(data != null){
              document.getElementById("twoType").innerHTML = "";
              document.getElementById("threeType").innerHTML = "";
              document.getElementById("repairMode").innerHTML = "";
              document.getElementById("standardDa").innerHTML = "";

//              document.getElementById("twoType").innerHTML = "<option value='0000'>请选择二级分类</option>";
              for(var prop in data) {
                if(!isNaN(data[prop])){
                }else{
                  if (data.hasOwnProperty(prop)) {
                    option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                  }
                }
              }
              $("#twoType").append(option);
            }
          }
        }
      });
    });



    $("#twoType").change(function(){
      var twoType = $("#twoType").val();
      $.ajax({
        url:"../problemGuarantee/getGuaranteeThirdTypeList",
        type:"get",
        async:"false",
        data:{ "twoType":twoType},
        dataType:"json",
        error:function(){
          alert("网络异常，可能服务器忙，请刷新重试");
        },
        success:function(json){
          <!-- 获取返回代码 -->
          var code = json.code;
          if(code != 0){
            var errorMessage = json.msg;
            alert(errorMessage);
          }else{
            <!-- 获取返回数据 -->
            var data = json.data;
            var option ="";
            if(data != null){
              document.getElementById("threeType").innerHTML = "";
              document.getElementById("repairMode").innerHTML = "";
              document.getElementById("standardDa").innerHTML = "";
//              document.getElementById("threeType").innerHTML = "<option value='0000'>请选择三级分类</option>";
              for(var prop in data) {
                if(!isNaN(data[prop])){
                }else{
                  if (data.hasOwnProperty(prop)) {
                    option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                  }
                }
              }
              $("#threeType").append(option);
            }
          }
        }
      });
    });

    $("#threeType").change(function(){
      var threeType = $("#threeType").val();
      $.ajax({
        url:"../problemGuarantee/getRepairModeList",
        type:"get",
        async:"false",
        data:{ "threeType":threeType},
        dataType:"json",
        error:function(){
          alert("网络异常，可能服务器忙，请刷新重试");
        },
        success:function(json){
          <!-- 获取返回代码 -->
          var code = json.code;
          if(code != 0){
            var errorMessage = json.msg;
            alert(errorMessage);
          }else{
            <!-- 获取返回数据 -->
            var data = json.data;
            var option ="";
            if(data != null){
              document.getElementById("repairMode").innerHTML = "<option value='0000'>请选择维修方式</option>";
              document.getElementById("standardDa").innerHTML = "";
              for(var prop in data) {
                if(!isNaN(data[prop])){
                }else{
                  if (data.hasOwnProperty(prop)) {
                    option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                  }
                }
              }
              $("#repairMode").append(option);
            }
          }
        }
      });
    });

    $("#repairMode").change(function(){
      var repairMode = $("#repairMode").val();
      $.ajax({
        url:"../problemGuarantee/getStandardDate",
        type:"get",
        async:"false",
        data:{ "repairMode":repairMode},
        dataType:"json",
        error:function(){
          alert("网络异常，可能服务器忙，请刷新重试");
        },
        success:function(json){
          <!-- 获取返回代码 -->
          var code = json.code;
          if(code != 0){
            var errorMessage = json.msg;
            alert(errorMessage);
          }else{
            <!-- 获取返回数据 -->
            var data = json.data;
            if(data != null){
              for(var prop in data) {
                document.getElementById("standardDa").innerHTML = data[prop];
                $("#standardDate").val(prop);
              }
            }
          }
        }
      });
    });



  });
</script>
</body>

</html>