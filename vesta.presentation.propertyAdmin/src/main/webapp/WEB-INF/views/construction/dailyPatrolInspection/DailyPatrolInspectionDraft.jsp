<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
  <script type="application/x-javascript">
    window.addEventListener("load", function () {
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

  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <!--animate-->
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
    #locationId{
      position: absolute;
      top:433px;
      left:60%;
    }
    #locationImg{
      width:500px;
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
    .col-lg-8 {
      width: 65.3%;
    }
    .col-sm-3 {
      width: 16.8%;
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
  </STYLE>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="002500010000" username="${authPropertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">

      <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>
        <div class="nav">
          <div class="rightButton">
            <button type="button" class="btn btn-primary" onclick="checkSubmit()"><spring:message code="inspection.submit"/></button>
            <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
          </div>
        </div>
        <div class="form-body" style="min-height:880px">
          <form class="form-horizontal" id="frm" action="../DailyPatrolInspection/updateInspectionDraft" method="post"
                enctype="MULTIPART/FORM-DATA">


            <div class="notice_head" style="height: 150px;;">
              <h2>${inspection.title}</h2>

              <div class="form-group col-lg-12">
                <label class="col-sm-2 control-label" ><spring:message code="inspection.createOn"/>：</label>
                <div class="col-sm-4">
                  ${inspection.createOn}
                </div>
              </div>
              <div class="form-group col-lg-12">
                <label class="col-sm-2 control-label"><spring:message code="inspection.state"/>：</label>
                <div class="col-sm-4">
                  ${inspection.state}
                </div>
              </div>
              <div class="form-group col-lg-12">
                <label class="col-sm-2 control-label" ><spring:message code="inspection.createName"/>：</label>
                <div class="col-sm-4">
                  ${inspection.createName}
                </div>
              </div>
            </div>
            <div class="border_"></div>
            <%--所属项目--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label"><spring:message code="problem.project"/>：</label>
              <div class="col-sm-4">
                ${inspection.projectName}
              </div>
            </div>

            <%--详细位置 --%>
            <div class="form-group col-lg-12">
              <label for="point" class="col-sm-2 control-label"><spring:message code="inspection.point"/>：</label>
              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="point" name="point">
                  <option value="" selected>请选择</option>
                  <option value="墙" <c:if test="${inspection.point eq '墙'}">selected</c:if>>墙</option>
                  <option value="地" <c:if test="${inspection.point eq '地'}">selected</c:if>>地</option>
                  <option value="顶" <c:if test="${inspection.point eq '顶'}">selected</c:if>>顶</option>
                  <option value="客厅" <c:if test="${inspection.point eq '客厅'}">selected</c:if>>客厅</option>
                </select>
              </div>
            </div>

            <%--一级分类--%>
            <div class="form-group col-lg-12">
              <label for="classifyOne" class="col-sm-2 control-label"><spring:message code="problem.fristLevel"/>：</label>
              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="classifyOne" name="classifyOne">
                  <c:forEach items="${firstLevels}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq inspection.classifyOne}">selected</c:if>>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--二级分类--%>
            <div class="form-group col-lg-12">
              <label for="classifyTwo" class="col-sm-2 control-label"><spring:message code="problem.secondLevel"/>：</label>
              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="classifyTwo" name="classifyTwo">
                  <c:forEach items="${secondLevels}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq inspection.classifyTwo}">selected</c:if>>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--三级分类--%>
            <div class="form-group col-lg-12">
              <label for="classifyThree" class="col-sm-2 control-label"><spring:message code="problem.thirdLevel"/>：</label>
              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="classifyThree" name="classifyThree">
                  <c:forEach items="${thirdLevel}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq inspection.classifyThree}">selected</c:if>>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--严重等级--%>
            <div class="form-group  col-lg-12">
              <label for="severityLevel" class="col-sm-2 control-label"><spring:message code="problem.severityLevel"/>：</label>
              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="severityLevel" name="severityLevel">
                  <option value="" selected>请选择</option>
                  <option value="严重" <c:if test="${inspection.severityLevel eq '严重'}">selected</c:if>>严重</option>
                  <option value="一般" <c:if test="${inspection.severityLevel eq '一般'}">selected</c:if>>一般</option>
                </select>
              </div>
            </div>

            <%--描述--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label" for="description"><spring:message
                      code="inspection.description"/>：</label>
              <div class="col-sm-4">
                <textarea class="form-control" id="description" name="description">${inspection.description}</textarea>
              </div>
            </div>


            <%--问题图片--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label" for="imgFile"><spring:message code="problem.image"/>：</label>

              <div class="col-sm-4">
                <div id="addfileId">
                  <input type="file" id="f1" name="imgFile" class="form-control"  onchange="check(this)">
                </div>
                <div id="demo_imgFile" style="padding-top: 15px;">
                  <c:forEach items="${inspection.imageList}" var="item" varStatus="vs">
                    <img id="im${vs.index}" onclick="delImg('${vs.index}','0')" src="${item.imageUrl}" style="width:100px" alt=""/>
                    <input id="ih${vs.index}" type="hidden" name="image" value="${item.id}">
                  </c:forEach>
                </div>
              </div>
            </div>

            <%--<div class="notice_inner" style="padding-bottom: 40px;">--%>

            <%--责任单位--%>
            <div class="form-group col-lg-12">
              <label for="supplierId" class="col-sm-2 control-label"><spring:message code="inspection.supplierId"/>：</label>
              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="supplierId" name="supplierId">
                  <c:forEach items="${supplierList}" var="item">
                    <option value="${item.key}" <c:if test="${item.key eq inspection.supplierId}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--整改人--%>
            <div class="form-group col-lg-12">
              <label for="assignId" class="col-sm-2 control-label"><spring:message code="inspection.assignName"/>：</label>
              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="assignId" name="assignId">
                  <c:forEach items="${assignList}" var="item">
                    <option value="${item.key}" <c:if test="${item.key eq inspection.assignId}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--第三方监理--%>
            <div class="form-group col-lg-12">
              <label for="supervisor" class="col-sm-2 control-label"><spring:message code="inspection.supervisorName"/>：</label>
              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="supervisor" name="supervisor">
                  <c:forEach items="${supervisorList}" var="item">
                    <option value="${item.key}" <c:if test="${item.key eq inspection.supervisor}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--整改时限--%>
            <div class="form-group col-lg-12">
              <label for="rectificationPeriod" class="col-sm-2 control-label"><spring:message code="problem.limit"/></label>
              <div class="input-group date form_date col-sm-4" data-date="" data-date-format="yyyy-mm-dd"
                   data-link-field="dtp_input2">
                <input type="text" class="form-control" placeholder="请选择整改时限" id="rectificationPeriod" name="rectificationPeriod" value="${inspection.rectificationPeriod}" readonly="true" />
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
              </div>
            </div>

            <%--原位标注图片--%>
            <div id="locationId">
              <img id="locationImg"/>
              <div class="point"></div>
            </div>
            <input type="hidden" id="inspectionId" name="inspectionId" value="${inspection.inspectionId}">

        </div>

        </form>
      </div>
    </div>

  </div>
</div>
</div>

</div>
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
<%@ include file="../../main/foolter.jsp" %>
<!-- 校验 -->

<script>

  $(function(){
    //户型图片的原位标注
    var houseTypeId = '${inspection.houseTypeId}';
    var imgUrl = '${inspection.houseTyprUrl}';
    $("#locationImg").attr("src",imgUrl);


    var xCoordinates = '${inspection.xCoordinates}';
    var yCoordinates = '${inspection.yCoordinates}';
    $(".point").css({
      "left":xCoordinates+"%",
      top:yCoordinates+"%"
    });
  });




  function checkSubmit() {
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

  $("#classifyOne").change(function(){
    var classifyOne = $("#classifyOne").val();
    $.ajax({
      url:"../DailyPatrolInspection/getSecondLevel",
      type:"get",
      async:"false",
      data:{ "classifyOne":classifyOne},
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
            document.getElementById("classifyTwo").innerHTML = "";
            for(var prop in data) {
              if (data.hasOwnProperty(prop)) {
                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
              }
            }
            $("#classifyTwo").append(option);
          }
        }
      }
    });
  });



  $("#classifyTwo").change(function(){
    var classifyTwo = $("#classifyTwo").val();
    $.ajax({
      url:"../DailyPatrolInspection/getThirdLevel",
      type:"get",
      async:"false",
      data:{ "classifyTwo":classifyTwo},
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
            document.getElementById("classifyThree").innerHTML = "";
            for(var prop in data) {
              if (data.hasOwnProperty(prop)) {
                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
              }
            }
            $("#classifyThree").append(option);
          }
        }
      }
    });
  });

  $("#supplierId").change(function(){
    var supplierId = $("#supplierId").val();
    $.ajax({
      url:"../DailyPatrolInspection/getDutyPeople",
      type:"get",
      async:"false",
      data:{ "supplierId":supplierId},
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
            document.getElementById("assignId").innerHTML = "";
            for(var prop in data) {
              if (data.hasOwnProperty(prop)) {
                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
              }
            }
            $("#assignId").append(option);
          }
        }
      }
    });
  });

</script>



</div>

</body>
</html>