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
  <link rel="stylesheet" href="../static/css/detailsCss.css"/>

  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <!--animate-->
  <%--problemDetail  css style--%>
  <link rel="stylesheet" href="../static/css/detailsCss.css"/>
  <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
  <script src="../static/js/wow.min.js"></script>
  <script>
    $(function(){
      console.log("sqq")
      $("#002500010000").addClass("active");
      $("#002500010000").parent().parent().addClass("in");
      $("#002500010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <%--加载图片放大--%>
  <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
  <style>
    .flowersList img {
      width: 20px;
    }

    .imgList img {
      width: 90px;
      height: 60px;
      margin-bottom: 10px;
      margin-top: 10px;
    }
    .rightButton{
      margin-top: 20px;
      width: 100%;
      float: right;
      text-align: right;
      margin-right: 50%;
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
      -ms-transform: scale(.5);
      -ms-transform-origin: top;
      -moz-transform: scale(.5);
      -moz-transform-origin: top;
      -webkit-transform: scale(.5);
      -webkit-transform-origin: top;
      -o-transform: scale(.5);
      -o-transform-origin: top;
      transform: scale(.5);
      transform-origin: top;
      top: 0;
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
    function forceCloseQuestion(inspectionId){
      var str = $("#closes").val();
      if(str){
        window.location.href = "../DailyPatrolInspection/shutDown?inspectionId="+inspectionId+"&shutDown="+str;
      }else{
        alert("关闭原因不能为空！！");
        forCloseQuestion();
      }
    }
    //    function forceCloseQuestion(inspectionId){
    //
    //      var str = $("#closes").val();
    //      if(str){
    //        window.location.href = "../DailyPatrolInspection/shutDown?inspectionId="+inspectionId+"&shutDownState="+str;
    //      }else{
    //        alert("关闭原因不能为空！！");
    //        forCloseQuestion();
    //      }
    //    }

  </script>
  <script>
    function forCloseQuestion(){
      $(function () { $('#myModal').modal({
        keyboard: true
      })});
    }

  </script>
</head>

<body class="cbp-spmenu-push">

<!-- 模态框（Modal） -->
<div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" style="width: 800px;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span
                aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel"><spring:message
                code="problem.forceClosese"/></h4>
      </div>
      <div class="modal-body"   style="width: 500px;">
        <table>
          <tr>
            <td style="width: 200px;">
              <%-- 楼栋名称 --%>
              <label class="" for="closes"><spring:message
                      code="problem.closess"/> ：</label>
            </td>
            <td style="width: 300px;">
              <input type="text" class="form-control col-sm-12" placeholder="" id="closes"
                     name="closes" value="" >
            </td>
          </tr>
        </table>
      </div>
      <div class="modal-footer">
        <%-- 取消 --%>
        <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message
                code="project.cancel"/></button>
        <%-- 确定 --%>
        <button type="button" class="btn btn-primary" onclick="forceCloseQuestion('${inspection.inspectionId}')">
          <spring:message code="project.confirm"/></button>
      </div>
    </div>
  </div>
</div>

<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="002500010000" username="${authPropertystaff.staffName}" />

  <div class="newDetail__">
    <div class="nav">

    </div>
    <div class="notice_head" style="height: 100px;width: 90%;border-bottom: 1px dashed #ccc">
      <h2 style="font-size: 18px; text-align: left;height: 40px;font-weight: bold;">${inspection.title}</h2>
      <span class="notice_time"><spring:message code="inspection.createOn" />：
          ${inspection.createOn}
      </span>

      <span class="ques_sta"><spring:message code="inspection.state" />：
          ${inspection.state}
      </span>
      <span class="key1 notice_time " style="width: 290px;margin-left: 60px;"><spring:message code="inspection.createName" />：
        ${inspection.createName}
      </span>
    </div>
    <div class="border_" style="border: none;"></div>
    <div class="notice_inner" style="padding-bottom: 40px;width: auto">

      <div class="room">
        <span class="key1"><spring:message code="inspection.projectName" />：</span>
        <span class="value_">${inspection.projectName}</span>
      </div>
      <div class="room">
        <span class="key1"><spring:message code="inspection.point" />：</span>
        <span class="value_">${inspection.point}</span>
      </div>

      <div class="room">
        <span class="key1"><spring:message code="inspection.classifyThree" />：</span>
        <span class="value_">${inspection.classifyThree}</span>

      </div>
      <div class="room_position">
        <span class="key1"><spring:message code="inspection.severityLevel" />：</span>
        <span class="value_">${inspection.severityLevel}</span>
      </div>
      <div class="class1">
        <span class="key1"><spring:message code="inspection.rectificationPeriod" />：</span>
        <span class="levels">${inspection.rectificationPeriod}</span>
      </div>
      <%--<div class="class1">--%>
      <%--<span class="key1"><spring:message code="inspection.inspectionId" />：</span>--%>
      <%--<span class="levels">${inspection.inspectionId}</span>--%>
      <%--</div>--%>

      <c:if test="${fn:length(inspection.description) >= 20}" >
        <div class="description" style="height: 55px;">
          <span class="key1" >问题描述：</span>
          <div class="col-sm-8">
            <textarea id="description" name="description" class="form-control" disabled="disabled" style="resize: none;">${inspection.description}</textarea>
          </div>
        </div>
      </c:if>
      <c:if test="${fn:length(inspection.description) < 20}">
        <div class="description">
          <span class="key1">问题描述：</span>
          <span class="value_">${inspection.description}</span>
        </div>
      </c:if>

      <%--<div class="class1">--%>
      <%--<span class="key1"><spring:message code="inspection.description" />：</span>--%>
      <%--<span class="levels">${inspection.description}</span>--%>
      <%--</div>--%>
      <div class="mun_pic" style="overflow: hidden;">
        <ul class="gallery" >
          <span class="key1"><spring:message code="inspection.image" />：</span>
          <li>
            <c:if test="${fn:length(inspection.imageList)==0}">
              <label class="control-label imgList"><img src="../../../../static/images/logo.png" style="width: 90px;height: 60px;margin-right: 4px;"></label>
            </c:if>
            <c:forEach items="${inspection.imageList}" var="image">
              <a href="${image.imageUrl}">
                <label class="control-label imgList"><img src="${image.imageUrl}"  style="width: 90px;height: 60px;margin-right: 4px;"></label>
              </a>
            </c:forEach>
          </li>
        </ul>
      </div>
      <div  class="borderDay"></div>
      <div class="supplier">
        <span class="key1"><spring:message code="inspection.supplier" />：</span>
        <span class="value_">${inspection.supplier}</span>
      </div>
      <div class="assignName">
        <span class="key1"><spring:message code="inspection.assignName" />：</span>
        <span class="value_">${inspection.assignName}</span>
      </div>
      <c:if test="${inspection.firstPartyName ne ''}">
        <div class="firstPartyName">
          <span class="key1"><spring:message code="inspection.firstPartyName" />：</span>
          <span class="value_">${inspection.firstPartyName}</span>
        </div>
      </c:if>
      <div class="supervisorName">
        <span class="key1"><spring:message code="inspection.supervisorName" />：</span>
        <span class="value_">${inspection.supervisorName}</span>
      </div>
      <div class="copyName">
        <span class="key1"><spring:message code="inspection.copyName" />：</span>
        <span class="value_">
          <c:forEach items="${inspection.idList}" var="id">
            <label class="control-label idList">${id.name}</label>
          </c:forEach>
        </span>
      </div>

      <c:if test="${inspection.state eq '非正常关闭'}">
        <div class="border1" style="margin-top: 15px;border: none;"></div>

        <div class="class1">
          <span class="key1">关单人：</span>
          <span class="value_">${inspection.shutDownBy}</span>
        </div>
        <c:if test="${fn:length(inspection.shutDown) >= 20}" >
          <div class="description" style="height: 55px;">
            <span class="key1" >关单原因：</span>
            <div class="col-sm-8">
              <textarea id="shutDown" name="shutDown" class="form-control" disabled="disabled" style="resize: none;">${inspection.shutDown}</textarea>
            </div>
          </div>
        </c:if>
        <c:if test="${fn:length(inspection.shutDown) < 20}">
          <div class="shutDown">
            <span class="key1">关单原因：</span>
            <span class="value_">${inspection.shutDown}</span>
          </div>
        </c:if>
        <div class="class1">
          <span class="key1">关单时间：</span>
          <span class="levels">${inspection.shutDownOn}</span>
        </div>
      </c:if>
      <div class="border1" style="margin-top: 15px;border: none;"></div>
      <div class="inspectionList">
        <c:forEach items="${inspection.inspectionList}" var="inspectionList">
          <div class="border_"></div>
          <div class="Name">
            <span class="key1" style="font-size:15px;font-weight:bold;">记录：</span>
            <span class="levels" style="font-size:15px;font-weight:bold; ">${inspectionList.detailsState}</span>
          </div>
          <div class="class1">
            <span class="key1"><spring:message code="inspection.createOn" />：</span>
            <span class="levels">${inspectionList.createOn}</span>
          </div>

          <c:if test="${fn:length(inspectionList.description) >= 20}" >
            <div class="descriptions" style="height: 55px;">
              <span class="key1" >问题描述：</span>
              <div class="col-sm-8">
                <textarea id="descriptions" name="descriptions" class="form-control" disabled="disabled" style="resize: none;">${inspectionList.description}</textarea>
              </div>
            </div>
          </c:if>
          <c:if test="${fn:length(inspectionList.description) < 20}">
            <div class="descriptions">
              <span class="key1">问题描述：</span>
              <span class="value_">${inspectionList.description}</span>
            </div>
          </c:if>
          <div class="mun_pic" style="overflow: hidden;">
            <ul class="gallery" >
              <span class="key1" style="padding-bottom: 100px;"><spring:message code="inspection.image" />：</span>
              <li>
                <c:if test="${fn:length(inspectionList.imageList)==0}">
                  <label class="control-label imgList"><img src="../../../../static/images/logo.png" height="100" width="200"></label>
                </c:if>
                <c:forEach items="${inspectionList.imageList}" var="image">
                  <a href="${image.imageUrl}">
                    <label class="control-label imgList"><img src="${image.imageUrl}" height="100" width="200"></label>
                  </a>
                </c:forEach>

              </li>
            </ul>
          </div>
        </c:forEach>
      </div>
      <div class="rightButton" style="">
        <%--<button class="save">保存</button>--%>
        <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
        <td>
          <c:if test="${inspection.state ne '完成' &&inspection.state ne '非正常关闭' && checkAuthFunction.esh40020097 eq 'Y'}">
            <a style="cursor: pointer" onClick="forCloseQuestion()" target=_blank class="btn btn-primary"><spring:message code="inspection.close" /></a>
          </c:if>
        </td>

      </div>
      <%--原位标注图片--%>
      <div id="locationId">
        <img id="locationImg"/>
        <div class="point"></div>
      </div>
    </div>
    <div class="clearfix"></div>
    <c:if test="${(inspection.type eq '1' || inspection.type eq '2') && inspection.state eq '整改中' && inspection.dealState eq '1'}">
      <form class="form-horizontal" id="frm" action="../DailyPatrolInspection/checkBeforeAcceptance.html" method="post"
            enctype="MULTIPART/FORM-DATA">
          <%--验收描述--%>
        <div class="form-group col-lg-12">
          <label class="col-sm-2 control-label" for="detailsDescription"><spring:message
                  code="inspection.description"/></label>

          <div class="col-sm-4">
            <textarea class="form-control" id="detailsDescription" name="detailsDescription" style="resize: none">${inspection.detailsDescription}</textarea>
          </div>
        </div>

          <%--整改图片--%>
        <div class="form-group col-lg-12">
          <label class="col-sm-2 control-label" for="imgFile"><spring:message code="inspection.image"/></label>

          <div class="col-sm-4">
            <div id="reviewfileId">
              <input type="file" id="f1" name="imgFile" class="form-control"  onchange="check(this)">
            </div>
          </div>
        </div>
        <input type="hidden" id="type" name="type">
        <input type="hidden" id="checkState" name="checkState">
        <input type="hidden" id="inspectionId" name="inspectionId" value="${inspection.inspectionId}">

        <button type="button" class="btn btn-primary" onclick="checkSubmit(${inspection.type},'合格')"><spring:message code="inspection.qualified"/></button>
        <button type="button" class="btn btn-primary" onclick="checkSubmit(${inspection.type},'不合格')"><spring:message code="inspection.unqualified"/></button>

      </form>
    </c:if>
  </div>
</div>
</div>

</div>
<%@ include file="../../main/foolter.jsp" %>

</div>
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
<!-- main content end-->
<%--<%@ include file="../../main/foolter.jsp" %>--%>
<!-- 校验 -->

<script>

  $(function(){
    //户型图片的原位标注
    var houseTypeId = '${inspection.houseTypeId}';
    var imgUrl = '${inspection.houseTyprUrl}';
    $("#locationImg").attr("src",imgUrl);


    var xCoordinates = '${inspection.xCoordinate}';
    var yCoordinates = '${inspection.yCoordinate}';
    $(".point").css({
      "left":xCoordinates+"%",
      top:yCoordinates+"%"
    });
  });


  function checkSubmit(type,caseState) {
    $("#type").val(type);
    $("#checkState").val(caseState);
    $("#frm").submit();
  }

  function checkURL(URL) {
    var str = URL;
    var Expression = "^((https|http|ftp|rtsp|mms)?://)"
            + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
            + "(([0-9]{1,3}.){3}[0-9]{1,3}" + "|"
            + "([0-9a-z_!~*'()-]+.)*"
            + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]."
            + "[a-z]{2,6})"
            + "(:[0-9]{1,4})?"
            + "((/?)|" + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    var objExp = new RegExp(Expression);
    if (objExp.test(str) == true) {
      return true;
    } else {
      return false;
    }

  }

  var fileCount = 1;
  var inputs = $("#f1").get(0);
  var result = document.getElementById("demo_imgFile");
  if (typeof FileReader === 'undefined') {
    result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
    inputs.setAttribute('disabled', 'disabled');
  } else {
    inputs.addEventListener('change', readFile, false);
  }

  function readFile() {
    var beforAdd = fileCount;
    fileCount++;
    for (var i = 0; i < this.files.length; i++) {
      var file = this.files[0];
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function (e) {
        result.innerHTML += '<img id="i'+beforAdd+'" onclick="delImg('+beforAdd+',"1")" src="' + this.result + '" style="width:100px" alt=""/>';
      }
      file = '<input type="file" id="f'+fileCount+'" name="imgFile" onchange="check(this)" class="form-control" style="width: 300px;">';
      $("#addfileId").append(file);
      var f = $("#f"+fileCount).get(0);
      f.addEventListener('change', readFile, false);
    }
  }
  function delImg(obj,type){
    if(type=='1'){
      $("#f"+obj).remove();
      $("#i"+obj).remove();
    }else{
      $("#im"+obj).remove();
      $("#ih"+obj).remove();
    }

  }

  var imgtype = true;
  function check(fnUpload) {
    var filename = fnUpload.value;
    var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
    if (mime != ".jpg" && mime != ".png" && mime != ".jpeg" && mime != ".gif") {
      alert("上传图片类型错误");
      imgtype = false;
    } else {
      imgtype = true;
    }

  }

  <c:if test="${problem.caseState ne '草稿' && problem.caseState ne '待接单'}">
  var reviewfileCount = 1;
  var reviewinputs = $("#reviewf1").get(0);
  //    alert(reviewinputs);
  var reviewresult = document.getElementById("reviewDemo_imgFile");
  if (typeof FileReader === 'undefined') {
    reviewresult.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
    reviewinputs.setAttribute('disabled', 'disabled');
  } else {
    reviewinputs.addEventListener('change', readReviewFile, false);
  }

  function readReviewFile() {
    var beforAdd = reviewfileCount;
    reviewfileCount++;
    for (var i = 0; i < this.files.length; i++) {
      var file = this.files[0];
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function (e) {
        reviewresult.innerHTML += '<img id="reviewi'+beforAdd+'" onclick="delreviewImg('+beforAdd+',"1")" src="' + this.result + '" style="width:100px" alt=""/>';
      }
      file = '<input type="file" id="reviewf'+reviewfileCount+'" name="reviewImgFile" onchange="check(this)" class="form-control" style="width: 300px;">';
      $("#reviewfileId").append(file);
      var f = $("#reviewf"+reviewfileCount).get(0);
      f.addEventListener('change', readReviewFile, false);
    }
  }
  function delreviewImg(obj,type){
    if(type=="1"){
      $("#reviewf"+obj).remove();
      $("#reviewi"+obj).remove();
    }else{
      $("#reviewim"+obj).remove();
      $("#reviewih"+obj).remove();
    }
  }
  </c:if>


  $("#roomNum").change(function(){
    var roomNum = $("#roomNum").val();
    $("#locationId").css({"display":"none"});
    if($("#locationId div").hasClass("positionArea")){
      $(".positionArea").remove();
    };
    $(".point").css({"display":"none"});
    var imgUrl="";
    var houseTypeId= "";
    $.ajax({
      type: "get",
      url: "../problem/getHouseType",
      async: false,
      data: {"roomNum":roomNum},
      dataType:"json",
      success: function (data) {
        if (data.code == '0') {
          var obj = data.data;
          if(obj != null){
            imgUrl = obj.imgUrl;
            $("#locationImg").attr("src",imgUrl);
            houseTypeId = obj.id;
          }else{
            alert("该房间尚未设置户型");
            return;
          }
        } else {
          alert("对不起，操作失败");
          return;
        }
      }
    });

    //户型图片的原位标注
    $.ajax({
      type: "GET",
      url: "../housetype/getHouseTypeLabelByTypeId",
      cache: false,
      async: false,
      data: "id=" + houseTypeId,
      success: function (data) {
        if (data.code == '0') {
          var objList = data.data;
          if(objList != null && objList.length > 0){
            $("#locationId").css({"display":"block"});
            var areaArry=[];
            for(var i = 0; i < objList.length; i++){
              areaArry[i]={};
              $("#locationId").append("<div class='positionArea'></div>");
              areaArry[i].name = objList[i].name;
              areaArry[i].id = objList[i].locationId;
              areaArry[i].left = parseFloat(objList[i].xNum1);
              areaArry[i].top = parseFloat(objList[i].yNum1);
              areaArry[i].width = parseFloat(objList[i].xNum2) - parseFloat(objList[i].xNum1);
              areaArry[i].height = parseFloat(objList[i].yNum2) - parseFloat(objList[i].yNum1);

            }
            for(var j=0;j<$(".positionArea").length;j++){
              var left=objList[j].xNum1+"%",
                      top=objList[j].yNum1+"%",
                      width=(parseFloat(objList[j].xNum2)-parseFloat(objList[j].xNum1))+"%",
                      height=(parseFloat(objList[j].yNum2)-parseFloat(objList[j].yNum1))+"%";
              var area=document.getElementsByClassName("positionArea");
              area[j].style.width=width;
              area[j].style.height=height;
              area[j].style.left=left;
              area[j].style.top=top;
              area[j].innerHTML=objList[j].name;
            }
//                        $("#locationImg").click(function(){
//                            alert("请在阴影区域点击新增问题！");
//                        })
            $(".positionArea").click(function(){
              var click=window.event;
              var bowerWidth=document.body.clientWidth;
              var scroll=$("#locationImg").offset().top;
              var leftPoint=(click.pageX-7-bowerWidth*0.6)*100/500+"%";
              var topPoint=(click.pageY-7-scroll)*100/$("#locationImg").height()+"%";
              $(".point").css({
                "left":leftPoint,
                "top":topPoint,
                "display":"block"
              });
              var pointL = parseFloat(leftPoint);
              var pointT = parseFloat(topPoint);
              for (var i = 0; i < areaArry.length; i++) {
                var leftD = pointL - areaArry[i].left,
                        topD = pointT - areaArry[i].top;
                if (leftD >= 0 && leftD < areaArry[i].width && topD >= 0 && topD < areaArry[i].height) {
                  $("#roomLocation").val(areaArry[i].id);
                  $("#locationName").val(areaArry[i].name);
                };
              }

              //    原位标注X、Y、ID值传递

              $("#xCoordinates").val(leftPoint.substring(0,leftPoint.length-1));
              $("#yCoordinates").val(topPoint.substring(0,topPoint.length-1));

            })

          }
        } else {
          alert("对不起，操作失败");
        }
      }

    });

  });
</script>
</body>
</html>