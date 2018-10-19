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
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001000010000" username="${propertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">

      <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>

        <div class="form-body" style="min-height:880px">
          <form class="form-horizontal" id="frm" action="../problem/ToRePieOrder" method="post"
                enctype="MULTIPART/FORM-DATA">
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label" for="projectNum"><spring:message
                      code="problem.project"/></label>

              <div class="col-sm-4">
                <select class="form-control" disabled = "disabled" placeholder="" id="projectNum" name="projectNum">
                  <c:forEach items="${typeMaps.projects}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq problem.projectNum}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--所属楼栋--%>
            <%--<div class="form-group col-lg-12">--%>
            <%--<label class="col-sm-2 control-label" for="buildingNum"><spring:message--%>
            <%--code="problem.building"/></label>--%>

            <%--<div class="col-sm-4">--%>
            <%--<select class="form-control" placeholder="" id="buildingNum" name="buildingNum">--%>
            <%--<c:forEach items="${typeMaps.buildings}" var="item">--%>
            <%--<option value="${item.key }" <c:if test="${item.key eq problem.buildingNum}">selected</c:if>>${item.value }</option>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--所属单元--%>
            <%--<div class="form-group col-lg-12">--%>
            <%--<label class="col-sm-2  control-label" for="unitNum"><spring:message--%>
            <%--code="problem.unit"/></label>--%>

            <%--<div class="col-sm-4">--%>
            <%--<select class="form-control" placeholder="" id="unitNum" name="unitNum">--%>
            <%--<c:forEach items="${typeMaps.units}" var="item">--%>
            <%--<option value="${item.key }" <c:if test="${item.key eq problem.unitNum}">selected</c:if>>${item.value }</option>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--所属楼层--%>
            <%--<div class="form-group col-lg-12">--%>
            <%--<label class="col-sm-2 control-label" for="floorNum"><spring:message--%>
            <%--code="problem.floor"/></label>--%>

            <%--<div class="col-sm-4">--%>
            <%--<select class="form-control" placeholder="" id="floorNum" name="floorNum">--%>
            <%--<c:forEach items="${typeMaps.floors}" var="item">--%>
            <%--<option value="${item.key }" <c:if test="${item.key eq problem.floorNum}">selected</c:if>>${item.value }</option>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--所属房间--%>
            <%--<div class="form-group col-lg-12">--%>
            <%--<label class="col-sm-2 control-label" for="roomNum"><spring:message--%>
            <%--code="problem.room"/></label>--%>

            <%--<div class="col-sm-4">--%>
            <%--<select class="form-control" placeholder="" id="roomNum" name="roomNum">--%>
            <%--<c:forEach items="${typeMaps.rooms}" var="item">--%>
            <%--<option value="${item.key }" <c:if test="${item.key eq problem.roomNum}">selected</c:if>>${item.value }</option>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--整改时限--%>
            <%--<div class="form-group  col-lg-12">--%>
            <%--<label for="limitDate" class="col-sm-2 control-label"><spring:message code="problem.limit"/></label>--%>
            <%--<div class="input-group date form_date col-sm-4" data-date="" data-date-format="yyyy-mm-dd"--%>
            <%--data-link-field="dtp_input2">--%>
            <%--<input type="text" class="form-control" placeholder="请选择整改时限" id="limitDate" name="limitDate" value="${problem.limitDate}" readonly="true" />--%>
            <%--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>--%>
            <%--<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--一级分类--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label" for="classifyOne"><spring:message
                      code="problem.fristLevel"/></label>

              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="classifyOne" name="classifyOne">
                  <c:forEach items="${typeMaps.firstLevels}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq problem.classifyOne}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>


            <%--二级分类--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label" for="classifyTwo"><spring:message
                      code="problem.secondLevel"/></label>

              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="classifyTwo" name="classifyTwo">
                  <c:forEach items="${typeMaps.secondLevels}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq problem.classifyTwo}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--三级分类--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label" for="classifyThree"><spring:message
                      code="problem.thirdLevel"/></label>

              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="classifyThree" name="classifyThree">
                  <c:forEach items="${typeMaps.thirdLevels}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq problem.classifyThree}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--简要描述--%>
            <%--<div class="form-group col-lg-8">--%>
            <%--<label class="col-sm-2 control-label" for="problemtype"><spring:message--%>
            <%--code="problem.problemtype"/></label>--%>

            <%--<div class="col-sm-13" id="problemtypediv">--%>
            <%--<c:forEach items="${typeMaps.problemtypes}" var="item">--%>
            <%--<input type="checkbox" name="problemtype" value="${item.key}">${item.value}--%>
            <%--</c:forEach>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--内部负责人--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label" for="innerPerson"><spring:message
                      code="problem.innerPerson"/></label>

              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="innerPerson" name="innerPerson">
                  <option value="" >请选择</option>
                  <c:forEach items="${typeMaps.innerPersonList}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq problem.innerPerson}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <%--供应商--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label" for="classifyThree"><spring:message
                      code="problem.supplier"/></label>

              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="supplier" name="supplier">
                  <c:forEach items="${typeMaps.supplierMap}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq problem.supplier}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <%--供应商负责人--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label" for="classifyThree"><spring:message
                      code="problem.supplierResponsible"/></label>

              <div class="col-sm-4">
                <select class="form-control" placeholder="" id="supplierResponsible" name="supplierResponsible">
                  <c:forEach items="${typeMaps.supplierResponsibleList}" var="item">
                    <option value="${item.key }" <c:if test="${item.key eq problem.supplierResponsible}">selected</c:if>>${item.value }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--问题描述--%>
            <%--<div class="form-group col-lg-8">--%>
            <%--<label class="col-sm-2 control-label" for="problemDescription"><spring:message--%>
            <%--code="problem.bewrite"/></label>--%>

            <%--<div class="col-sm-4">--%>
            <%--<textarea class="form-control" id="problemDescription" name="problemDescription">${problem.problemDescription}</textarea>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label" for="problemDescription"><spring:message
                      code="problem.address"/></label>

              <div class="col-sm-4">
                <input type="text" readonly="true" class="form-control" id="locationName" name="locationName">
              </div>
            </div>

            <%--原位标注图片--%>
            <div id="locationId">
              <img id="locationImg"/>
              <div class="point"></div>
            </div>
            <%--问题图片--%>
            <%--<div class="form-group col-lg-8">--%>
            <%--<label class="col-sm-3 control-label" for="imgFile"><spring:message code="problem.image"/></label>--%>

            <%--<div class="col-sm-9">--%>
            <%--<div id="addfileId">--%>
            <%--<input type="file" id="f1" name="imgFile" class="form-control" style="width: 300px;" onchange="check(this)">--%>
            <%--</div>--%>
            <%--<div id="demo_imgFile" style="padding-top: 15px;">--%>
            <%--<c:forEach items="${problem.imageList}" var="item" varStatus="vs">--%>
            <%--<img id="im${vs.index}" onclick="delImg('${vs.index}','0')" src="${item.imageUrl}" style="width:100px" alt=""/>--%>
            <%--<input id="ih${vs.index}" type="hidden" name="image" value="${item.imageId}">--%>
            <%--</c:forEach>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>

            <c:if test="${problem.rectifyState ne '草稿' && problem.rectifyState ne '待接单'}">
              <%--整改描述--%>
              <%--<div class="form-group col-lg-8">--%>
              <%--<label class="col-sm-2 control-label" for="dealResult"><spring:message--%>
              <%--code="problem.rectifyDesc"/></label>--%>

              <%--<div class="col-sm-4">--%>
              <%--<textarea class="form-control" id="dealResult" name="problemDescription">${problem.dealResult}</textarea>--%>
              <%--</div>--%>
              <%--</div>--%>

              <%--整改图片--%>
              <%--<div class="form-group col-lg-8">--%>
              <%--<label class="col-sm-3 control-label" for="reviewImgFile"><spring:message code="problem.image"/></label>--%>

              <%--<div class="col-sm-9">--%>
              <%--<div id="reviewfileId">--%>
              <%--<input type="file" id="reviewf1" name="reviewImgFile" class="form-control" style="width: 300px;" onchange="check(this)">--%>
              <%--</div>--%>
              <%--<div id="reviewDemo_imgFile" style="padding-top: 15px;">--%>
              <%--<c:forEach items="${problem.reviewImgList}" var="item" varStatus="vs">--%>
              <%--<img id="reviewim${vs.index}" onclick="delreviewImg('${vs.index}','0')" src="${item.imageUrl}" style="width:100px" alt=""/>--%>
              <%--<input id="reviewih${vs.index}" type="hidden" name="reviewImage" value="${item.imageId}">--%>
              <%--</c:forEach>--%>
              <%--</div>--%>
              <%--</div>--%>
              <%--</div>--%>
            </c:if>

            <input type="hidden" id="problemType" name="problemType">
            <input type="hidden" id="planType" name="planType" value="11">
            <input type="hidden" id="rectifyState" name="rectifyState">
            <input type="hidden" id="rectifyId" name="rectifyId" value="${problem.rectifyId}">
            <input type="hidden" id="roomLocation" name="roomLocation"  value="${problem.roomLocation}">
            <input type="hidden" id="xCoordinates" name="xCoordinates" value="${problem.xCoordinates}">
            <input type="hidden" id="yCoordinates" name="yCoordinates"  value="${problem.yCoordinates}">
            <div class='clearfix'></div>
            <div class="text-right form-group  col-lg-6" style="padding:0;">
              <%--<c:if test="${problem.rectifyState eq '草稿'}">--%>
              <%--<button type="button" class="btn btn-primary" onclick="checkSubmit('草稿')"><spring:message code="problem.save"/></button>--%>
              <%--<button type="button" class="btn btn-primary" onclick="checkSubmit('待接单')"><spring:message code="problem.submit"/></button>--%>
              <%--</c:if>--%>

              <%--<c:if test="${problem.rectifyState eq '待接单'}">--%>
              <%--<button type="button" class="btn btn-primary" onclick="checkSubmit('处理中')"><spring:message code="problem.order"/></button>--%>
              <%--</c:if>--%>

              <%--<c:if test="${problem.rectifyState eq '处理中'}">--%>
              <button type="button" class="btn btn-primary" onclick="checkSubmit('处理中')"><spring:message code="problem.redirect"/></button>
              <%--<button type="button" class="btn btn-primary" onclick="checkSubmit('已完成')"><spring:message code="problem.submit"/></button>--%>
              <%--</c:if>--%>

            </div>
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
<%@ include file="../main/foolter.jsp" %>
<!-- 校验 -->

<script>
  $(function(){

    //简要描述
    var problemType = '${problem.problemType}';
    if(problemType != null && problemType != 'null' && problemType != ''){
      var typeArr = problemType.split(",");
      $.each(typeArr,function(i,item){
        $("input[name='problemtype'][value="+item+"]").attr("checked","true");
      });
    }

    //户型图片的原位标注
    var houseTypeId = '${problem.houseTypeId}';
    var imgUrl = '${problem.houseTyprUrl}';
    $("#locationImg").attr("src",imgUrl);



    var xCoordinates = '${problem.xCoordinates}';
    var yCoordinates = '${problem.yCoordinates}';
    $(".point").css({
      "left":xCoordinates+"%",
      top:yCoordinates+"%"
    });

    $.ajax({
      type: "GET",
      url: "../housetype/getHouseTypeLabelByTypeId",
      cache: false,
      async: false,
      data: "id=" + houseTypeId,
      success: function (data) {
        if (data.code == '0') {
//          alert(data.data.length);
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
            var pointL=xCoordinates;
            var pointT=yCoordinates;
            for (var i = 0; i < areaArry.length; i++) {
              var leftD = pointL - areaArry[i].left,
                      topD = pointT - areaArry[i].top;
              if (leftD >= 0 && leftD < areaArry[i].width && topD >= 0 && topD < areaArry[i].height) {
                $("#locationName").val(areaArry[i].name);
              }
            }
            /*$(".positionArea").click(function(){
             var click=window.event;
             var bowerWidth=document.body.clientWidth;
             var leftPoint=(click.pageX-7-bowerWidth*0.6)*100/500+"%";
             var topPoint=(click.pageY-7-433)*100/$("#locationImg").height()+"%";
             $(".point").css({
             "left":leftPoint,
             "top":topPoint
             });
             var pointL = parseFloat(leftPoint);
             var pointT = parseFloat(topPoint);
             for (var i = 0; i < areaArry.length; i++) {
             var leftD = pointL - areaArry[i].left,
             topD = pointT - areaArry[i].top;
             if (leftD >= 0 && leftD < areaArry[i].width && topD >= 0 && topD < areaArry[i].height) {
             $("#roomLocation").val(areaArry[i].id);
             $("#locationName").val(areaArry[i].name);
             }
             }


             //    原位标注X、Y、ID值传递

             $("#xCoordinates").val(leftPoint.substring(0,leftPoint.length-1));
             $("#yCoordinates").val(topPoint.substring(0,topPoint.length-1));

             })*/
          }
        } else {
          alert("对不起，操作失败");
        }
      }
    });
  });
  function checkSubmit(rectifyState) {
    if(window.confirm("确定派单吗？")){

    }
//    if($("#projectNum").val()==""){
//      alert("请选择项目");
//      return false;
//    }
    var a = "";
    var problemtypes = $("input[name='problemtype']:checked");
    if(problemtypes != null && problemtypes != ""){
      for(var i=0;i<problemtypes.length;i++){
        if(i==0){
          a += problemtypes[i].value;
        }else{
          a = a+","+problemtypes[i].value;
        }
      }
    }
    $("#problemType").val(a);
    $("#rectifyState").val(rectifyState);
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

  //  var fileCount = 1;
  //  var inputs = $("#f1").get(0);
  //  var result = document.getElementById("demo_imgFile");
  //  if (typeof FileReader === 'undefined') {
  //    result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
  //    inputs.setAttribute('disabled', 'disabled');
  //  } else {
  //    inputs.addEventListener('change', readFile, false);
  //  }

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

  <%--<c:if test="${problem.rectifyState ne '草稿' && problem.rectifyState ne '待接单'}">--%>
  //  var reviewfileCount = 1;
  //  var reviewinputs = $("#reviewf1").get(0);
  //  var reviewresult = document.getElementById("reviewDemo_imgFile");
  //  if (typeof FileReader === 'undefined') {
  //    reviewresult.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
  //    reviewinputs.setAttribute('disabled', 'disabled');
  //  } else {
  //    reviewinputs.addEventListener('change', readReviewFile, false);
  //  }

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
  <%--</c:if>--%>

  $("#classifyOne").change(function(){
    var classifyOne = $("#classifyOne").val();
    $.ajax({
      url:"../problem/getSecondTypeList",
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
      url:"../problem/getThirdTypeList",
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

  $("#classifyThree").change(function(){
    var classifyThree = $("#classifyThree").val();

    var projectNum = $("#projectNum").val();
    if(projectNum != "" && classifyThree!= ""){
      $.ajax({
        url:"../problem/getSupplier",
        type:"get",
        async:"false",
        data:{ "classifyThree":classifyThree,"projectNum":projectNum},
        dataType:"json",
        error:function(){
          alert("网络异常，可能服务器忙，请刷新重试");
        },
        success:function(json){
          <!-- 获取返回代码 -->
          var code = json.code;
          if(code != 0){
            var errorMessage = json.msg;
          }else{
            <!-- 获取返回数据 -->
            var data = json.data;
            var option ="";
            if(data != null){
              document.getElementById("supplier").innerHTML = "";
              for(var prop in data) {
                if (data.hasOwnProperty(prop)) {
                  option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                }
              }
              $("#supplier").append(option);
            }
          }
        }
      });
    }
  });


  //    var projectNum = $("#projectNum").val();
  //    if(projectNum != "" && classifyThree!= ""){
  //      $.ajax({
  //        url:"../problem/getSupplier",
  //        type:"get",
  //        async:"false",
  //        data:{ "classifyThree":classifyThree,"projectNum":projectNum},
  //        dataType:"json",
  //        error:function(){
  //          alert("网络异常，可能服务器忙，请刷新重试");
  //        },
  //        success:function(json){
  //          <!-- 获取返回代码 -->
  //          var code = json.code;
  //          if(code != 0){
  //            var errorMessage = json.msg;
  //          }else{
  //            <!-- 获取返回数据 -->
  //            var data = json.data;
  //            var option ="";
  //            if(data != null){
  //              document.getElementById("supplier").innerHTML = "";
  //              for(var prop in data) {
  //                if (data.hasOwnProperty(prop)) {
  //                  option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
  //                }
  //              }
  //              $("#supplier").append(option);
  //            }
  //          }
  //        }
  //      });
  //    }
  //  });


  //  $("#projectNum").change(function(){
  //    var projectNum = $("#projectNum").val();
  //    $("#locationId").css({"display":"none"});
  //    if($("#locationId div").hasClass("positionArea")){
  //      $(".positionArea").remove();
  //    };
  //    $(".point").css({"display":"none"});
  //    $.ajax({
  //      url:"../houseroomtype/getBuildingListByProject",
  //      type:"get",
  //      async:"false",
  //      data:{ "projectId":projectNum},
  //      dataType:"json",
  //      error:function(){
  //        alert("网络异常，可能服务器忙，请刷新重试");
  //      },
  //      success:function(json){
  //        <!-- 获取返回代码 -->
  //        var code = json.code;
  //        if(code != 0){
  //          var errorMessage = json.msg;
  //          alert(errorMessage);
  //        }else{
  //          <!-- 获取返回数据 -->
  //          var data = json.data;
  //          var option ="";
  //          if(data != null){
  //            document.getElementById("buildingNum").innerHTML = "";
  //            for(var prop in data) {
  //              if (data.hasOwnProperty(prop)) {
  //                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
  //              }
  //            }
  //            $("#buildingNum").append(option);
  //          }
  //        }
  //      }
  //    });
  //  });


  //  $("#buildingNum").change(function(){
  //    var buildingNum = $("#buildingNum").val();
  //    $("#locationId").css({"display":"none"});
  //    if($("#locationId div").hasClass("positionArea")){
  //      $(".positionArea").remove();
  //    };
  //    $(".point").css({"display":"none"});
  //    $.ajax({
  //      url:"../houseroomtype/getUnitList",
  //      type:"get",
  //      async:"false",
  //      data:{ "buildingId":buildingNum},
  //      dataType:"json",
  //      error:function(){
  //        alert("网络异常，可能服务器忙，请刷新重试");
  //      },
  //      success:function(json){
  //        <!-- 获取返回代码 -->
  //        var code = json.code;
  //        if(code != 0){
  //          var errorMessage = json.msg;
  //        }else{
  //          <!-- 获取返回数据 -->
  //          var data = json.data;
  //          var option ="";
  //          if(data != null){
  //            document.getElementById("unitNum").innerHTML = "";
  //            for(var prop in data) {
  //              if(data[prop]=='无单元'){
  //                option="<option value=''>请选择单元</option>";
  //                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
  //              }else{
  //                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
  //              }
  //            }
  //            $("#unitNum").append(option);
  //          }
  //        }
  //      }
  //    });
  //  });

  //  $("#unitNum").change(function(){
  //
  //    var unitNum = $("#unitNum").val();
  //    $("#locationId").css({"display":"none"});
  //    if($("#locationId div").hasClass("positionArea")){
  //      $(".positionArea").remove();
  //    };
  //    $(".point").css({"display":"none"});
  //
  //    $.ajax({
  //      url:"../houseroomtype/getFloorList",
  //      type:"get",
  //      async:"false",
  //      data:{ "unitId":unitNum},
  //      dataType:"json",
  //      error:function(){
  //        alert("网络异常，可能服务器忙，请刷新重试");
  //      },
  //      success:function(json){
  //        <!-- 获取返回代码 -->
  //        var code = json.code;
  //        if(code != 0){
  //          var errorMessage = json.msg;
  //          alert(errorMessage);
  //        }else{
  //          <!-- 获取返回数据 -->
  //          var data = json.data;
  //          var option ="";
  //          if(data != null){
  //            document.getElementById("floorNum").innerHTML = "";
  //            for(var prop in data) {
  //              if (data.hasOwnProperty(prop)) {
  //                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
  //              }
  //            }
  //            $("#floorNum").append(option);
  //          }
  //        }
  //      }
  //    });
  //  });

  //  $("#floorNum").change(function(){
  //    var floor = $("#floorNum").val();
  //    $("#locationId").css({"display":"none"});
  //    if($("#locationId div").hasClass("positionArea")){
  //      $(".positionArea").remove();
  //    };
  //    $(".point").css({"display":"none"});
  //    $.ajax({
  //      url:"../houseroomtype/getRoomList",
  //      type:"get",
  //      async:"false",
  //      data:{ "floor":floor},
  //      dataType:"json",
  //      error:function(){
  //        alert("网络异常，可能服务器忙，请刷新重试");
  //      },
  //      success:function(json){
  //        <!-- 获取返回代码 -->
  //        var code = json.code;
  //        if(code != 0){
  //          var errorMessage = json.msg;
  //          alert(errorMessage);
  //        }else{
  //          <!-- 获取返回数据 -->
  //          var data = json.data;
  //          var option ="";
  //          if(data != null){
  //            document.getElementById("roomNum").innerHTML = "";
  //            for(var prop in data) {
  //              if (data.hasOwnProperty(prop)) {
  //                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
  //              }
  //            }
  //            $("#roomNum").append(option);
  //          }
  //        }
  //      }
  //    });
  //  });
  //获得供应商内部负责人列表
  $("#supplier").change(function() {
    var supplierId = $("#supplier").val();
    $("#supplierResponsible").val("");
    $.ajax({
      url: "../problem/getSupplierResponsibleList",
      type: "get",
      async: "false",
      data: {"supplierId": supplierId},
      dataType: "json",
      error: function () {
        alert("网络异常，可能服务器忙，请刷新重试");
      },
      success: function (json) {
        <!-- 获取返回代码 -->
        var code = json.code;
        if (code != 0) {
          var errorMessage = json.msg;
        } else {
          <!-- 获取返回数据 -->
          var data = json.data;
          var option = "";
          if (data != null) {

            for (var prop in data) {
              if (data.hasOwnProperty(prop)) {
                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
              }
            }
            $("#supplierResponsible").append(option);
          }
        }
      }
    });
  });
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



</div>

</body>
</html>