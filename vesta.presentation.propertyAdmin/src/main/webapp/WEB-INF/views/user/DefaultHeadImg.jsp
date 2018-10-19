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

  <META HTTP-EQUIV="pragma" CONTENT="no-cache">
  <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
  <META HTTP-EQUIV="expires" CONTENT="0">

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
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="000200020000" username="${propertystaff.staffName}"/>
  <input type="hidden" id="menuId" value="000200020000"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>

        <div class="form-body" style="min-height:380px">
          <form class="form-horizontal" id="frm" action="../defaultConfig/saveOrUpdateDefaultHeadImg" method="post"
                enctype="MULTIPART/FORM-DATA">
            <input type="hidden" id="id" name="id" value="${defaultConfig.id}">

            <%--默认头像设置--%>
            <div class="form-group col-lg-7">
              <label class="col-sm-2 control-label" for="multipartFile">默认头像设置</label>

              <div class="col-sm-8">
                <input type="file" class="form-control"
                       placeholder="<spring:message code = "CommunityOverview.imgRemark"/>"
                       id="multipartFile"
                       name="multipartFile" accept="image/*"
                       height="160px" width="280px"
                       style="width: 427px;" onchange="check(this)" >
                <p style="color: red">
                  <span>建议上传尺寸150×150</span>
                </p>
                <!-- 图片-->
                <div class="form-group col-lg-5">
                  <div class="col-sm-9" id="circleLogoUrl" name="circleLogoUrl">
                    <c:if test="${defaultConfig.configValue != null}">
                      <img src="${defaultConfig.configValue}"
                           style="width: 150px;height: 150px"/>
                    </c:if>
                  </div>
                </div>
              </div>
              <div class='clearfix'></div>

              <div id="demo_multipartFile" style="padding-top: 5px;">
              </div>

            </div>


            <div class='clearfix'></div>

            <input type="hidden" name="releaseStatus" id="releaseStatus" value="">
            <button type="button" id="sub" class="btn btn-primary">确定</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<script>

  $("#sub").click(function(){
    $.ajax({
      type: "GET",
      url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
      cache: false,
      async:false,
      dataType:"json",
      success: function (data) {
        if (data.error == 1) {
          $("#frm").submit();
        }else if(data.error == 0) {
          alert("对不起，您无权限执行此操作！");
          return ;
        }else{
          alert("对不起，操作失败！");
          return ;
        }
      }
    });
  });

  var inputs = $("#multipartFile").get(0);
  var result = document.getElementById("circleLogoUrl");
  //var result = document.getElementById("demo_multipartFile");

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
        result.innerHTML = '<img src="' + this.result + '"style="width: 150px;height: 150px" alt=""/>';
        results.innerHTML(result.innerHTML);
      }
      <!--限制图片大小-->
      /*            reader.onloadend = function(e){
       //判断图片
       var img = new Image();//构造JS的Image对象
       img.src = reader.result;//将本地图片赋给image对象

       if (img.width % 700 != 0) {
       result.innerHTML = "";
       //设置图片状态
       document.getElementById("imgStatus").value = 0;
       var obj = document.getElementById('imgStatus') ;
       obj.outerHTML=obj.outerHTML;
       //alert("对不起，图片宽度不符合，当前图片宽度为" + img.width );
       alert("对不起，图片宽度不符合，您的图片宽高比应该为700：400" );
       return;
       }
       if (img.height % 400 !=0) {
       result.innerHTML = "";
       //设置图片状态
       document.getElementById("imgStatus").value = 0;
       var obj = document.getElementById('imgStatus') ;
       obj.outerHTML=obj.outerHTML;
       //alert("对不起，图片宽度不符合，当前图片高度为" + img.height );
       alert("对不起，图片宽度不符合，您的图片宽高比应该为700：400" );
       return;
       }
       }*/
    }
  }

  var input = $("#activityimgpath").get(0);
  var results = document.getElementById("demo_activityimgpath");
  if (typeof FileReader === 'undefined') {
    results.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
    input.setAttribute('disabled', 'disabled');
  } else {
    input.addEventListener('change', readFiles, false);
  }

  function readFiles() {
    for (var i = 0; i < this.files.length; i++) {
      var file = this.files[0];
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function (e) {
        results.innerHTML = '<img src="' + this.result + '" style="width: 500px;height: 180px"/>';
      }
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
</script>
</body>
</html>
