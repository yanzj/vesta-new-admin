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
               crunMenu="005500040000" username="${propertystaff.staffName}"/>
  <div class="forms">

      <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>

        <div class="form-body" style="min-height:380px">
          <form class="form-horizontal" id="form" action="../vote/addOrUpdateVote.html" method="post"
                enctype="MULTIPART/FORM-DATA">
            <!-- 截止时间 -->
            <!--mj-->
            <!-- 截止时间 -->
            <div class="form-group  col-lg-6" style="width: 810px;">
              <label class="col-sm-3 control-label" style="float: left; width: 100px; text-align:right;"><spring:message
                      code="vote.deadline"/> <font color="red">*</font></label>

              <div class="input-group date form_datetime col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" style="float: left;width: 270px;">
                <input type="text" class="form-control" placeholder="" id="endDateStr" value="" name="endDateStr" readonly style="background: none;">
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
              </div>
              <%--
              <div class="col-sm-10" style="float: left; width: 100px;">
                <input type="text" class="form-control" placeholder="" id="hour" name="hour" value="" >
              </div>
              <label class="col-sm-2 control-label" style="float: left;width: 20px;"><spring:message code="vote.hour"/></label>
              <div class="col-sm-10" style="width: 100px;">
                <input type="text" class="form-control" placeholder="" id="minute" name="minute" value="">
              </div>
              <label class="col-sm-2 control-label" style="width: 20px;"><spring:message code="vote.minute"/></label>
              --%>
            </div>

            <div class="form-group  col-lg-6" style=" width:810px;">
              <label for="title" class="col-sm-3 control-label" style=" width: 100px;text-align: right;"><spring:message
                      code="propertyReport.title"/> <font color="red">*</font></label>

              <div class="col-sm-8" class="clearfix">
                <input type="text" class="form-control" placeholder="" id="title" name="title" value="" style="float:left; width:240px;">
                <input type="radio" id="voteType" name="voteType" checked value="0" style="float:left;margin: 9px 0 0 55px;"><span style="float:left; width: 40px;text-align: center;height: 30px;line-height: 31px;"><spring:message code="vote.radio"/></span>
                <input type="radio" id="voteType" name="voteType" checked value="1" style="float:left;margin: 9px 0 0 55px;"><span style="float:left; width: 40px;text-align: center;height: 30px;line-height: 31px;"><spring:message code="vote.multiChoice"/></span>
              </div>
            </div>
            <div class="xxk" style="width:auto; height:auto; position:relative; padding-bottom:20px; float:left; margin-bottom: 20px;">
              <div class="form-group col-lg-7" style="width: 100%; height: 50px;">
                <label class="col-sm-2 control-label" for="multipartFiles" style="width: 100px;text-align: right;"><spring:message
                        code="vote.option"/>1 <font color="red">*</font></label>

                <div class="col-sm-8" style="height: 50px;">
                  <input type="text" id="descriptions" name="descriptions" class="form-control" style="float:left; width:240px;"/>
                  <input type="file" class="form-control" placeholder="" id="multipartFiles" name="multipartFiles" accept="image/*" height="400px" width="700px" style="width:240px; float:left; width:240px; margin-left:30px;" onchange="check(this)">
                  <p style="position: absolute;color: red;bottom: -5px;right:500px;}">
                    <span><spring:message code="vote.imgRemark"/></span>
                  </p>
                </div>
              </div>
              <div class="form-group col-lg-7" style="width: 100%; height: 50px;">
                <label class="col-sm-2 control-label" for="multipartFiles" style="width: 100px;text-align: right;"><spring:message
                        code="vote.option"/>2  <font color="red">*</font></label>

                <div class="col-sm-8" style="height: 50px;">
                  <input type="text" id="descriptions" name="descriptions" class="form-control" style="float:left; width:240px;"/>
                  <input type="file" class="form-control" placeholder="" id="multipartFiles" name="multipartFiles" accept="image/*" height="400px" width="700px" style="width:240px; float:left; width:240px; margin-left:30px;" onchange="check(this)">
                  <p style="position: absolute;color: red;bottom: -5px;right:500px;}">
                    <span><spring:message code="vote.imgRemark"/></span>
                  </p>
                </div>
              </div>
              <div class="form-group col-lg-7" style="width: 100%; height: 50px;">
                <label class="col-sm-2 control-label" for="multipartFiles" style="width: 100px;text-align: right;"><spring:message
                        code="vote.option"/>3 <font color="red">*</font></label>

                <div class="col-sm-8" style="height: 50px;">
                  <input type="text" id="descriptions" name="descriptions" class="form-control" style="float:left; width:240px;"/>
                  <input type="file" class="form-control" placeholder="" id="multipartFiles" name="multipartFiles" accept="image/*" height="400px" width="700px" style="width:240px; float:left; width:240px; margin-left:30px;" onchange="check(this)">
                  <p style="position: absolute;color: red;bottom: -5px;right:500px;}">
                    <span><spring:message code="vote.imgRemark"/></span>
                  </p>
                </div>
              </div>
              <a class="addxxk" style=" position:absolute; bottom:0px; left:32px; width:82px;"><spring:message code="vote.addOption"/></a>
              <a class="removexxk" style="position:absolute; bottom:50px; left:640px; width:82px; z-index:100;"><spring:message code="vote.delOption"/></a>
            </div>
      <!--mj-->

            <div class='clearfix'></div>

            <input type="hidden" name="releaseStatus" id="releaseStatus" value="">
            <button type="button" class="btn btn-primary" onclick="checkSubmit()"><spring:message code="vote.votePublish"/></button>
            <a href="../vote/voteList.html" class="btn btn-primary" for=""><spring:message
                    code="common_cancel"/></a>

        </form>
      </div>
    </div>

  </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
  $(function(){
    console.log("sqq")
    $("#005500040000").addClass("active");
    $("#005500040000").parent().parent().addClass("in");
    $("#005500040000").parent().parent().parent().parent().addClass("active");
  })
  $('.form_datetime').datetimepicker({
    language: 'zh-CN',
    format: 'yyyy-mm-dd hh:ii',
    autoclose: true,
    todayBtn: true,
    linkField: "mirror_field",
    linkFormat: "yyyy-mm-dd hh:ii"
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

  function checkSubmit(){
    //校验
    var endDateStr = $("#endDateStr").val();
    endDateStr = endDateStr.replace(/(^\s*)|(\s*$)/g,""); //去除空格
    if (endDateStr.length == 0){
      alert("请输入截止时间!");
      return false;
    }
    var title = $("#title").val();
    title = title.replace(/(^\s*)|(\s*$)/g,"");
    if(title.length == 0){
      alert("请输入投票标题!");
      return false;
    }
    var descriptions = $("input[name='descriptions']");
    for(var i=0;i<descriptions.length;i++){
      var description = descriptions[i].value;
      description = description.replace(/(^\s*)|(\s*$)/g,"");
      if(description.length == 0){
        alert("请输入选项"+(i+1)+"的内容!");
        return false;
      }
    }
    //提交
    $("#form").submit();
  }


</script>

<!--MJ-->
<script>
  $(function(){
    var tt = $(".xxk .col-lg-7").html();
    var ee ='<div class="form-group col-lg-7 xxknumb" style="width: 100%; height: 50px;"></div>'
    $(".xxk .addxxk").click(function(){
      $(".xxk").append(ee);
      $(".xxk .col-lg-7:last").html(tt);
      $(".xxk").children(".xxknumb").each(function() {
        var rr= $(this).index()-1;
        var num ="选项"+rr+' <font color="red">*</font>';
        $(this).children("label").html(num);

      });
    })
    $(".xxk .removexxk").click(function(){
        var ee = $(".xxk .col-lg-7").size();
       if(ee<=2){
         alert("当前个数不允许删除");
       }
        else{
         $(".xxk .col-lg-7:last").remove();
       }


    })
  })
</script>
<!--MJ-->
</body>
</html>
