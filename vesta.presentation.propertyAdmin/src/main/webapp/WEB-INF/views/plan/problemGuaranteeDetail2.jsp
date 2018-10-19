<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
  <link rel="stylesheet" href="../static/css/detailsCss"/>

  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <!--animate-->
  <%--problemDetail  css style--%>
  <link rel="stylesheet" href="../static/css/detailsCss.css"/>
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
  <!--//Metis Menu -->
  <style>
    .flowersList img {
      width: 20px;
    }

    .imgList img {
      width: 70px;
    }
  </style>
  <STYLE type=text/css>
    input.error {
      border: 1px solid red;
    }
    label.error {
      background: url("./demo/images/unchecked.gif") no-repeat 0px 0px;
      padding-left: 16px;
      padding-bottom: 2px;
      font-weight: bold;
      color: #EA5200;
    }
    label.checked {
    / / background : url("./demo/images/checked.gif") no-repeat 0 px 0 px;
    }
    .newDetail__ .backTo {
      min-height:200px;
      width: 100px;
      height: 40px;
      background: #53b9fc;
      border-radius: 5px;
      font-size: 14px;
      margin-top: 30px;
      margin-left: 150px;
      margin-bottom: 65px;
    }
    .flowersList img {
      width: 20px;
    }

    .imgList img {
      width: 70px;
    }
    #locationId{
      height: 450px;
      position: absolute;
      top: -20px;
      width: 500px;
      left: 558px;
    }
    #locationImg{
      height: 450px;
      width:500px;
      /*height:300px;*/
      border:none;
    }
    .positionArea{
      position:absolute;
      z-index: 99;
      background:rgba(0,0,0,0.5);
      color: #ffe400;
      font-size:15px;
    }

    .point{
      position:absolute;
      z-index:9999;
      width:15px;
      height:15px;
      border-radius:15px;
      background-color:#ffe400;
      /*display:none;*/
    }
    .gallery{
      list-style: none;
    }
  </STYLE>
  <script type="application/javascript">
    function deleteQuestion(rectifyId){
      if(window.confirm("确定作废吗？")){
        window.location.href = "../problemGuarantee/deleteRepair?rectifyId="+rectifyId;
      }
    }
  </script>
  <script type="application/javascript">
    function acceptTask(repairId){
      if(window.confirm("确定接受吗？")){
        window.location.href = "../problemGuarante/deleteQeustion?rectifyId="+rectifyId;
      }
    }
  </script>

</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="002000010000" username="${authPropertystaff.staffName}" />
  <div class="newDetail__">
    <div class="nav">
      <div class="rightButton">
        <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
        <td>
            <c:if test="${function.qch40010088 eq 'Y' && problem.caseState ne 'completed' && problem.caseState ne 'closed' && problem.caseState ne 'returnVisit'}">
              <a href="../problemGuarantee/preToModifyProblemGuarantee?rectifyId=${problem.caseId}"
                 target=_blank class="btn btn-primary"><spring:message code="problem.edit" /></a>
            </c:if>
            <c:if test="${function.qch40010087 eq 'Y' && problem.caseState ne 'closed' && problem.caseState ne 'returnVisit'} ">
              <a style="cursor: pointer" onClick="deleteQuestion('${problem.caseId}')" class="btn btn-primary"><spring:message code="problem.del" /></a>
            </c:if>
        </td>
      </div>
    </div>
    <div class="notice_head">
      <h2>报修单</h2>
        <span class="notice_time"><spring:message code="problem.createdate" />：
          <fmt:formatDate  type="date" value="${problem.createDate}" dateStyle="default" pattern="yyyy-MM-dd HH:mm:ss" />
        </span>
        <span class="ques_sta"><spring:message code="problem.status" />：</span>
          <c:if test="${problem.caseState eq 'accepted'}"><span>待接单</span></c:if>
          <c:if test="${problem.caseState eq 'processing'}"><span>处理中</span></c:if>
          <c:if test="${problem.caseState eq 'completed'}"><span>已完成</span></c:if>
          <c:if test="${problem.caseState eq 'closed'}"><span>已废弃</span></c:if>
        <span class="record_num"><spring:message code="problem.createby" />：
            <c:if test="${fn:length(problem.userName) >= 20}" >
              <textarea id="caseDesc" name="shutDown" class="form-control" disabled="disabled" style="resize: none;display: inline-block;width: 73%;">${problem.userName}</textarea>
            </c:if>
            <c:if test="${fn:length(problem.userName) < 20}">
              <span>${problem.userName}</span>
            </c:if>
           </span>
        <%--<span class="record_num"><spring:message code="problem.createby" />：</span><span>${problem.userName}</span>--%>
        <span class="phone"><spring:message code="problem.repairphone" />：</span><span>${problem.userPhone}</span>
    </div>

    <div class="border_"></div>
    <div class="notice_inner">
      <div class="room">
        <span class="key1">房间：</span>
        <span class="value_">${problem.address}</span>
      </div>
      <div class="room_position">
        <span class="key1"><spring:message code="problem.location" />：</span>
        <span class="value_">${problem.casePlace}</span>
      </div>
      <div class="user">
        <span class="key1">报修人：</span>
        <span class="value_">${problem.userName}  ${problem.userPhone}</span>
      </div>
      <c:if test="${fn:length(problem.caseDesc) >= 20}" >
        <div class="repair_per col-sm-4" style="height: 55px;padding-left: 0;">
          <span class="key1" >问题描述：</span>
          <div class="col-sm-8">
            <textarea id="caseDesc" name="shutDown" class="form-control" disabled="disabled" style="resize: none;">${problem.caseDesc}</textarea>
          </div>
        </div>
      </c:if>
      <c:if test="${fn:length(problem.caseDesc) < 20}">
        <div class="repair_per col-sm-4" style="height: 55px;padding-left: 0;">
          <span class="key1">问题描述：</span>
          <span class="value_">${problem.caseDesc}</span>
        </div>
      </c:if>
      <%--<div class="repaired_pic" >--%>
        <%--<span class="key1"><spring:message code="problem.image" />：</span>--%>
        <%--<c:forEach items="${problem.questionImageList}" var="image">--%>
          <%--<label class="control-label imgList"><img src="${image.imageUrl}"></label>--%>
        <%--</c:forEach>--%>
      <%--</div>--%>


      <div class="mun_pic">
        <ul class="gallery" >
          <span class="key1"><spring:message code="problem.image" /></span>
          <li>
            <c:if test="${fn:length(problem.questionImageList)==0}">
              <label class="control-label imgList"><img src="../../../../static/images/logo.png" height="100" width="200"></label>
            </c:if>
            <c:forEach items="${problem.questionImageList}" var="image">
              <a href="${image.imageUrl}">
                <label class="control-label imgList"><img src="${image.imageUrl}"  height="100" width="200"></label>
              </a>
            </c:forEach>
          </li>
        </ul>
      </div>



      <div class="border1"></div>


      <div class="class1">
        <span class="key1"><spring:message code="problem.fristLevel" />：</span>
        <span class="levels">${problem.oneType}</span>
      </div>
      <div class="class2">
        <span class="key1"><spring:message code="problem.secondLevel" />：</span>
        <span class="levels">${problem.twoType}</span>
      </div>
      <div class="class3">
        <span class="key1"><spring:message code="problem.thirdLevel" />：</span>
        <span class="levels">${problem.threeType}</span>
      </div>
      <div class="repair_mode">
        <span class="key1">维修方式：</span>
        <span class="value_">${problem.repairMode}</span>
      </div>
      <div class="duration">
        <span class="key1">标准工时：</span>
        <span class="value_">
          <span class="value_">${problem.standardDate}</span>
        </span>
      </div>

      <div class="border1"></div>
      <br/>
      <div class="deal_mode">
        <span class="key1">处理方式：</span>
        <span class="value_">
          <c:if test="${problem.dealMode eq 'interior'}">物业</c:if>
          <c:if test="${problem.dealMode eq 'accountabilityunit'}">责任单位</c:if>
          <c:if test="${problem.dealMode eq 'thirdParty'}">第三方</c:if>
          <c:if test="${problem.dealMode eq 'mutualProcess'}">责任单位和第三方共同处理</c:if>
        </span>
      </div>
      <c:if test="${problem.dealMode eq 'thirdParty' or problem.dealMode eq 'mutualProcess'}">
        <div class="third_repair">
          <span class="key1">维修第三方：</span>
          <span class="value_">${problem.thirdRepair}</span>
        </div>
      </c:if>
      <c:if test="${problem.dealMode eq 'accountabilityunit' or problem.dealMode eq 'mutualProcess'}">
        <div class="company_one">
          <span class="key1">责任单位1：</span>
          <span class="value_">${problem.companyOne}</span>
        </div>
        <div class="company_two">
          <span class="key1">责任单位2：</span>
          <span class="value_">${problem.companyTwo}</span>
        </div>
        <div class="company_three">
          <span class="key1">责任单位3：</span>
          <span class="value_">${problem.companyThree}</span>
        </div>
      </c:if>
      <div class="repair_manager">
        <span class="key1">维修负责人：</span>
        <span class="value_">${problem.repairManager}</span>
      </div>
      <div class="task_date">
        <span class="key1">接单时间：</span>
        <span class="value_">
          <fmt:formatDate type="date" value="${problem.taskDate}"  dateStyle="default"  pattern="yyyy-MM-dd HH:mm:ss" />
                <%--&lt;%&ndash;${problem.taskDate}&ndash;%&gt;--%>
        </span>
      </div>
      <div class="complete_dur">
        <span class="key1">完工时间：</span>
        <span class="value_">
          <fmt:formatDate type="date" value="${problem.dealCompleteDate}"  dateStyle="default"  pattern="yyyy-MM-dd HH:mm:ss" />
                <%--&lt;%&ndash;${problem.dealCompleteDate}&ndash;%&gt;--%>
        </span>
      </div>
      <%--<div class="deal_way">--%>
        <%--<span class="key1">处理过程：</span>--%>
        <%--<span class="value_">${problem.dealWay}</span>--%>
      <%--</div>--%>

      <c:if test="${fn:length(problem.dealWay) >= 20}" >
        <div class="repair_per col-sm-4" style="height: 55px;padding-left: 0;">
          <span class="key1" >处理过程：</span>
          <div class="col-sm-8">
            <textarea id="shutDown" name="shutDown" class="form-control" disabled="disabled" style="resize: none;">${problem.dealWay}</textarea>
          </div>
        </div>
      </c:if>
      <c:if test="${fn:length(problem.dealWay) < 20}">
        <div class="repair_per col-sm-4" style="padding-left: 0;">
          <span class="key1">处理过程：</span>
          <span class="value_">${problem.dealWay}</span>
        </div>
      </c:if>

      <div class="mun_pic">
        <ul class="gallery" >
          <span class="key1"><spring:message code="problem.image" /></span>
          <li>
            <c:if test="${fn:length(problem.repairImageList)==0}">
              <label class="control-label imgList"><img src="../../../../static/images/logo.png" height="100" width="200"></label>
            </c:if>
            <c:forEach items="${problem.repairImageList}" var="image">
              <a href="${image.imageUrl}">
                <label class="control-label imgList"><img src="${image.imageUrl}"  height="100" width="200"></label>
              </a>
            </c:forEach>
          </li>
        </ul>
      </div>

    </div>
    <br/><br/>
  </div>
</div>

</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<script type="text/javascript" src="../../../../static/js/zoom.min.js"></script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script>
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
</body>
</html>

