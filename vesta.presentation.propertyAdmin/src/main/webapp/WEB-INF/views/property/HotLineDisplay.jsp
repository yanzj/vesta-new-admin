<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
      $("#005500010000").addClass("active");
      $("#005500010000").parent().parent().addClass("in");
      $("#005500010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <script src="../static/property/js/checkNullAllJsp.js"></script>
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
</head>
<script>
  function validate() {
    var cityNum = $("#cityNum").val();
    if ("-1" == cityNum) {
      alert("请选择城市!");
      return;
    }
    if(!CheckNull($("#image").val(),"请上传图片！")){
      return;
    }
    var image = $("#image").val();
    if ("" == image) {
      alert("请上传图片！");
      return;
    }
    document.getElementById("frm").submit();
  }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005500010000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">

      <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>

        <div class="form-body" style="min-height:1500px">
          <form class="form-horizontal" id="frm" action="../property/editDisplay" method="post" enctype="multipart/form-data">
            <input type="hidden" id="menuId" value="005500010000">

            <div class="form-group  col-lg-10">
              <label for="cityNum" class="col-sm-3 control-label"><spring:message
                      code="announcementDTO.cityName"/></label>
              <div class="col-sm-5">
                <select id="cityNum" name="cityNum" class="form-control" onchange="queryProjectNameById()">
                  <option value="-1">--请选择城市--</option>
                  <c:forEach items="${city}" var="item" >
                    <option value="${item.cityId }"
                            <c:if test="${item.cityId eq '0'}">selected</c:if>>${item.cityName }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <%--城市下项目--%>
            <div class="form-group  col-lg-10">
              <label for="projectNum" class="col-sm-3 control-label"><spring:message
                      code="HousePayBatch.projectName"/></label>

              <div class="col-sm-5">
                <select id="projectNum" name="projectNum" class="form-control">
                  <option value="0">全部项目</option>
                </select>
              </div>
            </div>

            <div class="form-group  col-lg-10">
              <label class="col-sm-3 control-label" for="image"><spring:message code="propertyServices.image" />：</label>
              <div class="col-sm-5">
                <input type="file" class="form-control" placeholder="" id="image" name="image" style="width: 179px;" onchange="uploadImg(this)"/>
                <span style="color: red">建议图片宽高比750*420</span>
                <%--<div id="imgPath" style="padding-top: 15px;"></div>--%>
              </div>
            </div>

            <%--图片--%>
            <div class="form-group  col-lg-10">
              <label class="col-sm-3 control-label" for="image">预览图片：</label>
              <div class="col-sm-5">
                <div class="backPic">
                  <div id="imgPath" style="padding-top: 15px;">
                    <img src="" alt="" style="width:750px;height:420px;" id="img" />
                  </div>
                </div>
              </div>
            </div>

            <div class="text-center form-group  col-lg-12" style="padding:0;">
              <input type="button" onclick="validate()" class="btn btn-primary" value="确定">
              <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);"><spring:message code="common_cancel"/></button>
            </div>

          </form>
        </div>
      </div>

    </div>
  </div>
</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
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
  function queryProjectNameById() {
    var projectId = $("#cityNum").val();
    if(projectId == '-1'){
      $("#projectNum").empty();
      $("#projectNum").append('<option value="-1">--请选择项目--</option>');
      return ;
    }
    $("#planName").empty();
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
      var arr = data.data;
      $("#projectNum").empty();
      <%--$("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');--%>
      for (var k in arr) {
        $("#projectNum").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
//                alert(arr[k][0]);
      }

    });
  }
  //上传图片
  var imgType = true;
  function uploadImg(fnUpload) {
    var filename = fnUpload.value;
    var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
    if (mime != ".jpg" && mime != ".png" && mime != ".jpeg" && mime != ".gif") {
      alert("上传图片类型错误");
      imgType = false;
    } else {
      imgType = true;
    }
  }

  var inputs = $("#image").get(0);
  var result = document.getElementById("imgPath");
  if (typeof FileReader === 'undefined') {
    result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
    inputs.setAttribute('disabled', 'disabled');
  } else {
    inputs.addEventListener('change', readFile, false);
  }

  function readFile() {

    for (var i = 0; i < this.files.length; i++) {
      var file = this.files[0];
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function (e) {
        result.innerHTML = '<img src="' + this.result + '" style="width:750px;height:420px;" alt=""/>';
      }
    }
  }

</script>
</body>
</html>