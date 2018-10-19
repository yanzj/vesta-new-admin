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
  <script src="../static/js/jquery-1.11.1.min.js"></script>
  <script src="../static/advert/js/addAdvert.js"></script>
  <script src="../static/property/js/checkNullAllJsp.js"></script>
  <script type="text/javascript">

    addEventListener("load", function () {
      setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
      window.scrollTo(0, 1);
    }


  </script>
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
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020011" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--服务信息添加开始--%>
      <div class="form-body">
        <h3 class="text-center">新增广告 </h3>
        <div class="form-body">
          <form class="form-horizontal" id="frm" action="../advert/addAdvertManage.html" method="post" enctype="MULTIPART/FORM-DATA">
            <div class="form-group col-lg-6">
              <label  class="col-sm-3 control-label"><spring:message code="advertServices.advertGroup" /></label>
              <div class="col-sm-9">
                <form:select id="adModularId" path="adManageMaps.adModularId" items="${adManageMaps.groupMap}"
                             class="form-control" style="width: 179px;" onchange="hideTile(this)"></form:select>
              </div>
            </div>
            <div class="form-group col-lg-6">
              <label  class="col-sm-3 control-label"><spring:message code="advertServices.project"/></label>
                <div class="col-sm-9">
                  <form:select id="item" path="adManageMaps.item" items="${adManageMaps.projectMap}"
                               class="form-control" style="width: 179px;"></form:select>
                </div>
            </div>
            <div class="form-group col-lg-6" id="title">
              <label class="col-sm-3 control-label" for="title"><spring:message code="advertServices.title" /></label>
              <div class="col-sm-9">
                <input type="text" class="form-control" placeholder=""  name="title" value="${adManageMaps.title}"style="width: 179px;">
              </div>
            </div>
            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="advertisinglinks"><spring:message code="advertServices.link" /></label>
              <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="" id="advertisinglinks" name="advertisinglinks" value="${adManageMaps.advertisinglinks}" style="width: 179px;">
              </div>
            </div>
            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="imgUrl"><spring:message code="advertServices.img" /></label>
              <div class="col-sm-9">
                <input type="file" class="form-control" placeholder="" id="imgUrl" name="imgpath" value="${adManageMaps.imgpath}" style="width: 100px;" onchange="check(this)">
              </div>
            </div>

            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="imgUrl"><spring:message code="propertyReport.releaseImg"/></label>
              <div class="col-sm-9">
                <div id="demo_result"></div>
              </div>
            </div>
                <button type="button" id="abc" class="btn btn-primary" onclick="validate()" ><spring:message code="propertyServices.servicePreservation"/></button>
                <input type="hidden" class="form-control" id="advertId" name="advertId" value="${adManageMaps.advertId}">
            <input type="hidden" class="form-control" id="showImg" name="imgUrl" value="${adManageMaps.imgUrl}">

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
<script>
  var advertId=$('#advertId').val();
  if(''!=advertId){
    hideTile($('#adModularId').get(0));
  }


  var result = document.getElementById("demo_result");
  console.info("123"+$('#showImg').val());
  result.innerHTML+= '<img src="'+$('#showImg').val()+'" style="width:100px;height:120px;" alt=""/>';

  function checkURL(URL){
    var str=URL;
    var Expression= "^((https|http|ftp|rtsp|mms)?://)"
            + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
            + "(([0-9]{1,3}.){3}[0-9]{1,3}"      + "|"
            + "([0-9a-z_!~*'()-]+.)*"
            + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]."
            + "[a-z]{2,6})"
            + "(:[0-9]{1,4})?"
            + "((/?)|"     + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    var objExp=new RegExp(Expression);
    if(objExp.test(str)==true){
      return true;
    }else{
      return false;
    }

  }

  var inputs=$("#imgUrl").get(0);
  var result = document.getElementById("demo_result");
  if(typeof FileReader === 'undefined'){
    result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
    inputs.setAttribute('disabled','disabled');
  }else{
    inputs.addEventListener('change',readFile,false);
  }

  function readFile(){

    for(var i=0;i<this.files.length;i++)
    {
      var file = this.files[0];
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function(e){
        $('#demo_result').html("");
        result.innerHTML+= '<img src="'+this.result+'" style="width:100px;height:120px;" alt=""/>';
      }
    }
  }


  var imgtype=true;
  function check(fnUpload) {
    var filename = fnUpload.value;
    var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
    if (mime != ".jpg" && mime != ".png"&&mime != ".jpeg"&& mime != ".gif") {
      alert("上传图片类型错误");
      imgtype=false;
    }else{
      imgtype=true;
    }

  }

  function hideTile(adModularId) {
    console.info(adModularId);
    var adModularId = adModularId.value;
   if(adModularId==1){
     $("#title").css("display","none");
   }else{
     $("#title").css("display","inline");
   }
  }




  function validate() {
    var str = $("#adModularId").val();
    var advertId=$("#advertId").val();
    var item =$("#item").val();
    var links=$("#advertisinglinks").val();
    var item=$("#item").val();
    console.info("模块："+str);
    var advertId=$("#advertId").val();
    var imgUrl=$("#imgUrl").val();//图片

    if (advertId=="") {
      $.ajax({
        url: "/advert/getAdvertByAdModularId",
        type: "post",
        async: "false",
        dataType: "json",
        data: {
          "adModularId": str,
          "itemId":item
        },
        success: function (data) {
          if (data == 5) {
            alert("同模块下只能推送5个广告");
            return;
          } else {
            if(imgtype){
              if(str==0){
                alert("请选择模块");
                return;
              }
              if(item==0){
                alert("请选择项目");
                return;
              }
              if(!CheckNull(links,"您输入的广告链接不合法！")){
                return;
              }
              if(links!=''){
                if(!checkURL(links)){
                  alert("您输入的广告链接不合法！");
                  return;
                }
              }
              if(!CheckNull(imgUrl,"广告图不能为空！")){
                return;
              }
              if(imgUrl==""){
                alert("广告图不能为空");
                return false;
              }
              document.getElementById("frm").submit();
            }
          }
        },
      });

    }else{
      if(imgtype){
        if(str==0){
          alert("请选择模块");
          return;
        }
        if(item==0){
          alert("请选择项目");
          return;
        }
        document.getElementById("frm").submit();
      }
    }
  }
</script>
</body>
</html>