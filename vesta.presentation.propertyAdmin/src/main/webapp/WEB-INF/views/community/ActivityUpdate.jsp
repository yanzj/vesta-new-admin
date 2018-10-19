<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/3
  Time: 14:40
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
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <script src="../static/property/js/checkNullAllJsp.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
</head>
<script type="text/javascript">
  function goSubmit(id){
    document.getElementById("rolesetId").value = id;
    document.getElementById("search_form").submit();
  }
</script>
<script>
  function testValue() {
    if(!CheckNull($("#title").val(),"活动主题不能为空！")){
      return false;
    }
   var title = $("#title").val();//活动标题
    if(title==""){
      alert("活动主题不能为空");
      return false;
    }
    if(title.length>30){
      alert("活动主题不能大于三十字");
      return false;
    }
    if(!CheckNull($("#activityDate").val(),"活动日期不能为空！")){
      return false;
    }
    var activityDate=$("#activityDate").val();//活动日期
    if(activityDate==""){
      alert("活动日期不能为空");
      return false;
    }
   var  operator=$("#operator").val();//活动发布人
    if(operator.length>15){
      alert("活动发布人不能大于十五字");
      return false;
    }
    if(!CheckNull($("#operator").val(),"活动发布人不能为空！")){
      return false;
    }
    if(operator==""){
      alert("活动发布人不能为空");
      return false;
    }
    if(!CheckNull($("#hotline").val(),"咨询热线不能为空！")){
      return false;
    }
   var hotline=$("#hotline").val();//咨询热线
    if(hotline==""){
      alert("咨询热线不能为空");
      return false;
    }
    if(hotline.length>15){
      alert("咨询热线不能大于十五字");
      return false;
    }
    if(!CheckNull($("#address").val(),"活动地址不能为空！")){
      return false;
    }
   var address=$("#address").val();//活动地点
    if(hotline==""){
      alert("活动地址不能为空");
      return false;
    }
    if(!CheckNull($("#activityDesc").val(),"活动内容不能为空！")){
      return false;
    }
   var activityDesc=$("#activityDesc").val();//活动内容
    if(hotline==""){
      alert("活动内容不能为空");
      return false;
    }
    if(hotline.length>500){
      alert("活动内容不能大于五百字");
      return false;
    }
    if(!CheckNull($("#homePageimgpath").val(),"活动主页图不能为空！")){
      return false;
    }
   var homePageimgpath=$("#homePageimgpath").val();//主页图片
    if(hotline==""){
      alert("活动主页图不能为空");
      return false;
    }
    if(!CheckNull($("#activityimgpath").val(),"活动详情不能为空！")){
      return false;
    }
   var activityimgpath=$("#activityimgpath").val();//详情图片
    if(hotline==""){
      alert("活动详情不能为空");
      return false;
    }


//    if($("#title").val()==)

//    document.getElementById("frm").submit();
  }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005400030000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <%--<div class="form-body">--%>
        <%--<form class="form-horizontal" action="../community/activityUpdate" method="post" enctype="MULTIPART/FORM-DATA" >--%>

          <%--<input type="hidden" name="activityId" value="${activityAdminDto.activityId}">--%>

          <%--&lt;%&ndash;活动主题&ndash;%&gt;--%>
          <%--<div class="form-group  col-lg-6">--%>
            <%--<label for="title" class="col-sm-3 control-label"><spring:message code="activityManage.activityTitle"/>：</label>--%>
            <%--<div class="col-sm-9">--%>
              <%--<input type="text" class="form-control" placeholder="" id="title"--%>
                     <%--name="title" value="${activityAdminDto.title}">--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--&lt;%&ndash;活动时间&ndash;%&gt;--%>
          <%--<div class="form-group  col-lg-6">--%>
            <%--<label for="activityDate" class="col-sm-3 control-label"><spring:message code="activityManage.activityDate"/>：</label>--%>
            <%--<div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd"--%>
                 <%--data-link-field="dtp_input2">--%>
              <%--<input type="text" class="form-control" placeholder="" id="activityDate"--%>
                     <%--name="activityDate"  value="${activityAdminDto.activityDate}" readonly>--%>
              <%--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>--%>
              <%--<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--&lt;%&ndash;发布人&ndash;%&gt;--%>
          <%--<div class="form-group  col-lg-6">--%>
            <%--<label for="operator" class="col-sm-3 control-label"><spring:message code="activityManage.activityOperator"/>：</label>--%>
            <%--<div class="col-sm-9">--%>
              <%--<input type="text" class="form-control" placeholder="" id="operator"--%>
                     <%--name="operator" value="${activityAdminDto.operator}">--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--咨询热线--%>
          <%--<div class="form-group  col-lg-6">
            <label for="hotline" class="col-sm-3 control-label"><spring:message code="activityManage.activityHotLine"/>：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="hotline"
                     name="hotline"  value="${activityAdminDto.hotline}">
            </div>
          </div>--%>
          <%--&lt;%&ndash;项目&ndash;%&gt;--%>
          <%--<div class="form-group  col-lg-6">--%>
            <%--<label for="address" class="col-sm-3 control-label"><spring:message code="activityManage.activityProject"/>：</label>--%>

            <%--<div class="col-sm-9">--%>
              <%--<input type="text" class="form-control" placeholder="" id="address"--%>
                     <%--name="address" value="${activityAdminDto.address}">--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--&lt;%&ndash;活动简介&ndash;%&gt;--%>
          <%--<div class="form-group  col-lg-6">--%>
            <%--<label for="activityDesc" class="col-sm-3 control-label"><spring:message code="activityManage.activityDesc"/>：</label>--%>

            <%--<div class="col-sm-9">--%>
              <%--<input type="text" class="form-control" placeholder="" id="activityDesc"--%>
                     <%--name="activityDesc" value="${activityAdminDto.activityDesc}">--%>
            <%--</div>--%>
          <%--</div>--%>

          <%--<div class="form-group col-lg-6">--%>
            <%--<label class="col-sm-3 control-label" for="homePageimgpath"><spring:message code="activityManage.homePageimgpath" /></label>--%>
            <%--<div class="col-sm-9" >--%>
              <%--<input type="file" class="form-control" placeholder="" id="homePageimgpath" name="homePageimgpath" value="" style="width: 179px;" onchange="check(this)" >--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--<div class="form-group col-lg-6">--%>
            <%--<label class="col-sm-3 control-label" for="activityimgpath"><spring:message code="activityManage.activityimgpath" /></label>--%>
            <%--<div class="col-sm-9">--%>
              <%--<input type="file" class="form-control" placeholder="" id="activityimgpath" name="activityimgpath" value="" style="width: 179px;" onchange="check(this)" >--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--<div class="form-group  col-lg-6">--%>
            <%--<label for="activityDesc" class="col-sm-3 control-label"><spring:message code="activityManage.homePageimgpath"/>：</label>--%>

            <%--<div class="col-sm-9" style="padding-top:15px;">--%>
              <%--<img src="${activityAdminDto.homePage}" style="width: 150px;height: 150px"/>--%>
              <%--<div id="demo_homePageimgpath"></div>--%>
            <%--</div>--%>
          <%--</div>--%>

          <%--<div class="form-group  col-lg-6">--%>
            <%--<label for="activityDesc" class="col-sm-3 control-label"><spring:message code="activityManage.activityimgpath"/>：</label>--%>

            <%--<div class="col-sm-9" style="padding-top:15px;">--%>
              <%--<img src="${activityAdminDto.activity}" style="width: 150px;"/>--%>
              <%--<div id="demo_activityimgpath"></div>--%>
            <%--</div>--%>
          <%--</div>--%>


          <%--<button type="submit" class="btn btn-primary" for="" onclick="return testValue()"><spring:message code="common_update"/></button>--%>

          <%--<a  href="../community/activityManage.html" class="btn btn-primary" for="" ><spring:message code="common_cancel"/></a>--%>

        <%--</form>--%>
      <%--</div>--%>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <%--<form action="../community/activityManage" method="post" id ="search_form">--%>

      <input id = "activityId" type="hidden" name="activityId"  value="${activity.activityId}">
    <%--</form>--%>
    <table width="100%" class="tableStyle">

      <tr>
        <td><spring:message code="activityManage.activityTitle" />：</td>
        <td>${activityAdminDto.title}</td>
      </tr>
      <tr>
        <td><spring:message code="activityManage.activityProject" />：</td>
        <td>${activityAdminDto.address}</td>
      </tr>
      <tr>
        <td><spring:message code="activityManage.activityDate" />：</td>
        <td>${activityAdminDto.activityDate}</td>
      </tr>
      <tr>
        <td><spring:message code="activityManage.activityHeadCountApply" />：</td>
        <td>${activityAdminDto.headCount}</td>
      </tr>
      <tr>
        <td><spring:message code="activityManage.activityProjectName" />：</td>

        <td>${projectNameList}</td>
        <%--<td>${activityAdminDto.scope}</td>--%>
      </tr>
      <tr>
        <td><spring:message code="activityManage.activityTypes" />：</td>
        <td>
          <c:if test="${activityAdminDto.types eq '1'}"><spring:message code="activityManage.activityTypes_pub" /></c:if>
          <c:if test="${activityAdminDto.types eq '2'}"><spring:message code="activityManage.activityTypes_pra" /></c:if>
        </td>
      </tr>
      <tr>
        <td><spring:message code="activityManage.activityDesc" />：</td>
        <td><textarea style="width: 928px; height: 101px;" readonly="readonly">${activityAdminDto.activityDesc}</textarea></td>
      </tr>
      <tr>
        <td><spring:message code="activityManage.activityCountApply" />：</td>
        <td>${activityAdminDto.countApply}</td>
      </tr>
      <tr>
        <td><spring:message code="activityManage.activityOperator" />：</td>
        <td>${activityAdminDto.operator}</td>
      </tr>
      <tr>
        <td><spring:message code="activityManage.activityMakeDate" />：</td>
        <td>${activityAdminDto.makedate}</td>
      </tr>
      <tr>
        <td><spring:message code="activityManage.activityHotLine" />：</td>
        <td>${activityAdminDto.hotline}</td>
      </tr>
      <tr>
        <td><spring:message code="activityManage.activityStatus" />：</td>
        <td>
          <c:if test="${activityAdminDto.status eq '1'}"><spring:message code="activityManage.activityStatus_1" /></c:if>
          <c:if test="${activityAdminDto.status eq '2'}"><spring:message code="activityManage.activityStatus_2" /></c:if>
          <c:if test="${activityAdminDto.status eq '3'}"><spring:message code="activityManage.activityStatus_3" /></c:if>
          <c:if test="${activityAdminDto.status eq '4'}"><spring:message code="activityManage.activityStatus_4" /></c:if>
          <c:if test="${activityAdminDto.status eq '5'}"><spring:message code="activityManage.activityStatus_5" /></c:if>
        </td>
      </tr>
      <tr>
        <td>
          <%--
          <c:if test="${activityAdminDto.pushState eq '0'}"><a href="../community/turnActivityStatus?activityId=${activityAdminDto.activityId}"><spring:message code="activityManage.activityPublish" /></a></c:if>
          <c:if test="${activityAdminDto.pushState eq '1'}"><a href="../community/turnActivityStatus?activityId=${activityAdminDto.activityId}"><spring:message code="activityManage.activityUnPublish" /></a></c:if>
          --%>
        </td>

        <td><a href="../community/activityManage.html"><spring:message code="common_back" /></a></td>
      </tr>
    </table>
  </div>

</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

<script>
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

  var inputs=$("#homePageimgpath").get(0);
  var result = document.getElementById("demo_homePageimgpath");
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
        result.innerHTML= '<img src="'+this.result+'" style="width:100px" alt=""/>';
      }
    }
  }

  var input=$("#activityimgpath").get(0);
  var results = document.getElementById("demo_activityimgpath");
  if(typeof FileReader === 'undefined'){
    results.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
    input.setAttribute('disabled','disabled');
  }else{
    input.addEventListener('change',readFiles,false);
  }

  function readFiles(){

    for(var i=0;i<this.files.length;i++)
    {
      var file = this.files[0];
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function(e){
        results.innerHTML= '<img src="'+this.result+'" style="width:100px" alt=""/>';
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
  function validate() {
    document.getElementById("frm").submit();
  }
</script>

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
</script>

</body>
</html>
