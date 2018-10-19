<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/2
  Time: 18:14
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
  function goSubmit(id){
    document.getElementById("rolesetId").value = id;
    document.getElementById("search_form").submit();
  }
</script>

<script type="text/javascript">
  function testCircle(){
    var cName =document.getElementById("circleName").value;// $("#circleName").val;
    if(cName==null||cName==""){
      var nameNull =document.getElementById("circleNameNull").value;
      alert(nameNull);
      return false;
    }
//    $.ajax({
//      url: "../club/testClubName",            //目标网址
//      type: "post",
//      async: "false",
//      dataType: "json",
//      data: {
//        "circleName": cName,            //参数
//      },
//      success: function (json) {
//        <!-- 获取返回代码 -->
//        var code = json.code;
//        if (code != 0) {
//          var errorMessage = json.msg;
//          alert(errorMessage);
//        } else {
//          <!-- 获取返回数据 -->
//          var data = json.data;
//          alert(data);
////          for(var n=0;n<data.length;n++){
////          var projectId=data.projectId;
////          var projectName = $("#projectId"+projectId).innerText
////          var staffName=data.staffName;
////          var mobile=data.mobile;
////          $("#projectId").val(projectId);
////          $("#staffName").val(staffName);
////          $("#mobile").val(mobile);
////          }
//
//        }
//      },
//    });
//    document.getElementById("addClub").submit;
  }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000500030000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
        <input type="hidden" id="circleNameNull" value="<spring:message code="clubManage.CircleNameNull"/>">
        <div class="form-body">
        <form class="form-horizontal" action="../circle/updateClub" enctype="MULTIPART/FORM-DATA" method="post">
          <input type="hidden" value="${circleInfoAdminDTO.circleId}" name="circleId">
          <div class="form-group  col-lg-6">
            <label for="circleName" class="col-sm-3 control-label"><spring:message code="clubManage.SelclubName" /></label>

            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="circleName"
                     name="circleName" value="${circleInfoAdminDTO.circleName}">
            </div>
          </div>
          <div class="form-group  col-lg-6">
            <label for="circleSlogan" class="col-sm-3 control-label"><spring:message code="clubManage.SelclubcircleSlogan"/></label>

            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="circleSlogan"
                     name="circleSlogan" value="${circleInfoAdminDTO.circleSlogan}">
            </div>
          </div>
          <div class="form-group col-lg-6">
            <label class="col-sm-3 control-label" for="circleLogoUrl"><spring:message code="clubManage.SelclubcircleLOGO" /></label>
            <div class="col-sm-9">
              <input type="file" class="form-control" placeholder="" id="circleLogoUrl" name="circleLogoUrl"  style="width: 335px;" onchange="checkClub(this)">
            </div>
          </div>
          <div class="form-group col-lg-6">
            <label class="col-sm-3 control-label" for="circleLogoUrl">图片:</label>
            <div class="col-sm-9">
              <img src="${circleInfoAdminDTO.circleLogo}" style="width: 150px;height: 150px"/>
              <div id="demo_result"></div>
            </div>
          </div>
          <%--<div class="form-group  col-lg-6">--%>
          <%--<label for="propertyProject" class="col-sm-3 control-label"><spring:message code="propertyCompany.propertyProject"/></label>--%>

          <%--<div class="col-sm-9">--%>
          <%--<input type="password" class="form-control" placeholder="" id="propertyProject"--%>
          <%--name="propertyProject">--%>
          <%--</div>--%>
          <%--</div>--%>
          <%--<div class="form-group  col-lg-6">--%>
          <%--<label for="projectAdmin" class="col-sm-3 control-label"><spring:message code="propertyCompany.projectAdmin"/></label>--%>

          <%--<div class="col-sm-9">--%>
          <%--<input type="password" class="form-control" placeholder="" id="projectAdmin"--%>
          <%--name="projectAdmin">--%>
          <%--</div>--%>
          <%--</div>--%>

          <button type="submit" class="btn btn-primary" for="" onclick="return testCircle()"><spring:message code="common_confirm" /></button>

          <a  href="../circle/clubManage.html" class="btn btn-primary" for="" ><spring:message code="common_cancel"/></a>

        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">

  </div>


</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>

  var imgtype=true;
  function checkClub(fnUpload) {
    var filename = fnUpload.value;
    var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
    if (mime != ".jpg" && mime != ".png"&&mime != ".jpeg"&& mime != ".gif") {
      alert("上传图片类型错误");
      imgtype=false;
    }else{
      imgtype=true;
    }

  }

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
  function js_checkMessage(){
    document.getElementById("frm").submit();
  }
  var inputs=$("#circleLogoUrl").get(0);
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
        result.innerHTML= '<img src="'+this.result+'" style="width:100px;padding-top: 15px; " alt=""/>';
      }
    }
  }
</script>

</body>
</html>