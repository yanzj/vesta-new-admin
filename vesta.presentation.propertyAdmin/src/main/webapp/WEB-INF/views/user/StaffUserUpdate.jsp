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
  <script src="../static/property/js/checkNullAllJsp.js"></script>
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
  <%--
  <STYLE type=text/css>
    ul,li{
      list-style: none;
    }
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
    /*-Mj-*/
    *{ margin:0px; padding:0px;}
    ul{ list-style:none;}
    .fl{ float:left}
    /*.fr{ float:right}*/
    .clearfix:after{content:".";display:block;height:0;clear:both;visibility:hidden}
    /*.clearfix{*+height:1%;}*/
    .search input{    height: 36px;width: 100%;outline: none;padding-left: 20px;}
    .search button{width: 9rem; height:36px; text-align:center; line-height:36px;position: absolute;right: 14px;top: 0;}
    .search{float: left;/*margin-left: 323px*/position: relative;}
    .demo{min-height: 40px;/*width: 688px;*/margin: 0 auto;background: #ccc;/*margin-left: 310px;*/}
    .demo .search{ height:36px; width:266px;}
    /*.demo .search input{    height: 36px;width: 230px;outline: none;padding-left: 20px;}
    .demo .search button{ width:36px; height:40px; text-align:center; line-height:36px; }*/
    .demo .text{width: 100%;/*height: 40px;*/line-height: 40px;padding: 0 10px;}
    .demo .text .wenben{padding:0 10px; cursor:pointer;}
    .on_changes{    width: 600px;top: 40px;list-style: none;background: #FFF; display: none; cursor: pointer;margin-top: 35px;/*padding: 3px; border: 1px solid #ccc;*/}
    .on_changes li{margin:0px;padding:6px;font-size: 14px;}
    .on_changes li.active{ background:#CEE7FF;}


    .demo .text .wenben{
      position: relative;
      border: 1px solid #fefe0a;
      margin-right: 5px;
      margin-top: 5px;
      margin-bottom: 10px;
      height: 30px;
      line-height: 30px;
    }
    .deletePic{
      position: absolute;
      top: -6px;
      right: -5px;
      width: 10px;
      height: 10px;
      border-radius: 50%;
      border: 1px solid #ff0000;
      background: url(../static/images/deletePic.png) no-repeat;
      background-size: contain;
    }
    /*----*/
  </STYLE>
  --%>
  <%--角色查询CSS_Start--%>
  <style>
    input{
      outline: none;
    }
    ul  li{
      list-style: none;
    }
    html,body{
      width: 100%;
      height: 100%;
    }
    .search{
      position: relative;
    }
    .search .searchInput{
      width: 100%;
      height: 34px;
      line-height: 34px;
      display: inline-block;
    }
    .search .button{
      position: absolute;
      right: 15px;
      top: 0;
      width: 80px;
      height: 34px;
      background: #169BD5;
      color:#FFFFFF;
    }
    .search .searchList{
      display: none;
      cursor: pointer;
      width: calc(100% - 30px);
      border: 1px solid #424242;
      border-top: none;
      /*margin-top: -1px;*/
      height: 150px;
      overflow-y: auto;
      z-index: 5;
      position: absolute;
      background: #ffffff;
    }
    .search .searchList li{
      padding:5px;
    }
    .search .searchList li:hover{
      background: #ccc;
    }
    .searchResultList{
      /*width: 53%;*/
      min-height: 34px;
      border: 1px solid #000000;
    }

    .searchResultList div{
      margin: 6px;
      display: inline-block;
    }
    .resultList{
      position: relative;
      border: 1px solid #337AB7;
    }

    .deletePic{
      position: absolute;
      top: -14px;
      right: -15px;
      width: 14px;
      height: 14px;
      border-radius: 50%;
    }
    .formsStyle{
      width: 50rem;
    }
   /* .deletePic:hover{
      border: 1px solid #ff0000;
      background: url(../static/images/deletePic.png) no-repeat;
      background-size: contain;
    }*/
  </style>
  <%--角色查询CSS_End--%>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="002300020000" username="${propertystaff.staffName}"/>
  <div class="forms formsStyle">
    <div class="widget-shadow " data-example-id="basic-forms">

      <%--<div class="form-body">--%>
        <h3 class="text-center">&nbsp;</h3>

        <%--<div class="form-body" style="min-height:680px">--%>
          <form class="form-horizontal" id="frm" action="../staffUser/saveOrUpdateStaffUser" method="post"
                enctype="MULTIPART/FORM-DATA">
            <input type="hidden" id="error" value="${error}"/>
            <input type="hidden" id="staffIdDto" name="staffIdDto" value="${staffUser.staffIdDto}"/>
            <%--用户名--%>
            <div class="form-group col-xs-10">
              <label class="col-sm-3 control-label" for="userNameDto">用户名 <font color="red">*</font></label>
              <div class="col-sm-7">
                <input type="text" class="form-control" placeholder="" id="userNameDto" name="userNameDto" onchange="checkVal()" value="${staffUser.userNameDto}">
              </div>
            </div>
            <%--姓名--%>
            <div class="form-group col-xs-10">
              <label class="col-sm-3 control-label" for="staffNameDto">姓名 <font color="red">*</font></label>
              <div class="col-sm-7">
                <input type="text" class="form-control" placeholder="" id="staffNameDto" name="staffNameDto"
                       onchange="checkVal()"
                       value="${staffUser.staffNameDto}">
              </div>
            </div>
            <%--联系方式--%>
            <div class="form-group col-xs-10">
              <label class="col-sm-3 control-label" for="mobileDto">联系方式 <font color="red">*</font></label>
              <div class="col-sm-7">
                <input type="text" class="form-control" placeholder="" id="mobileDto" name="mobileDto" onchange="checkVal()" value="${staffUser.mobileDto}">
              </div>
            </div>
            <%--邮箱地址--%>
            <div class="form-group col-xs-10">
              <label class="col-sm-3 control-label" for="email">邮箱地址 <font color="red">*</font></label>
              <div class="col-sm-7">
                <input type="text" class="form-control" placeholder="" id="email" name="email" onchange="checkVal()" value="${staffUser.email}">
              </div>
            </div>
            <%--查询角色_2016.09.20--%>
            <div class="form-group  col-xs-10">
              <label for="" class="col-sm-3 control-label">选择角色</label>
              <div class="col-sm-7">
                <div class="searchResultList" id="searchResultList">
                  <c:forEach items="${roleList}" var="role">
                    <div class="resultList">
                      ${role.roleDesc}
                      <div class="deletePic"></div>
                      <input type="hidden" id="roledescList" name="roledescList" value="${role.setId}">
                    </div>
                  </c:forEach>
                </div>
              </div>
            </div>
            <div class=" form-group col-xs-10">
              <label for="" class="col-sm-3 control-label"></label>
              <div class="col-sm-7 fr clearfix search">
                <input type="text" placeholder="点我添加角色" id="searchInput" class="searchInput" />
                <%--<input type="button" value="搜索" class="button" disabled/>--%>
                <ul class="searchList" id="searchList">
                </ul>
              </div>
            </div>
            <%--by BaiY--%>
            <%--区域--%>
            <div class="form-group col-xs-10">
              <label class="col-sm-3 control-label" for="scope">区域 </label>
              <div class="col-sm-7">
                <input type="text" class="form-control" placeholder="" id="scope" name="scope" value="${staffUser.scope}">
              </div>
            </div>
            <%--项目--%>
            <div class="form-group col-xs-10">
              <label class="col-sm-3 control-label" for="projectName">项目 </label>
              <div class="col-sm-7">
                <input type="text" class="form-control" placeholder="" id="projectName" name="projectName" value="${staffUser.projectName}">
              </div>
            </div>
            <%--公司--%>
            <div class="form-group col-xs-10">
              <label class="col-sm-3 control-label" for="company">公司 </label>
              <div class="col-sm-7">
                <input type="text" class="form-control" placeholder="" id="company" name="company" value="${staffUser.company}">
              </div>
            </div>
            <%--密码--%>
            <div class="form-group col-xs-10">
              <label class="col-sm-3 control-label" for="passwordDto">密码 </label>
              <div class="col-sm-7">
                <c:if test="${!empty staffUser.passwordDto and staffUser.passwordDto ne ''}">
                  <input type="text" class="form-control" placeholder="" id="passwordDto" name="passwordDto" value="******" readonly>
                </c:if>
                <c:if test="${empty staffUser.passwordDto}">
                  <input type="text" class="form-control" placeholder="" id="passwordDto" name="passwordDto" value="">
                </c:if>
                <p style="color: red">
                  <span>您可以选择不输入密码，系统将设置默认密码为：123456</span>
                </p>
              </div>
              <br/>
            </div>
            <%--角色查询--%>
            <%--
            <div class="form-group  col-lg-10">
              <label for="" class="col-sm-3 control-label">选择角色</label>
              <div class="col-sm-7">
                <div class="demo clearfix">
                  <div class="fl clearfix text">
                    <c:forEach items="${roleList}" var="role">
                      <div class="fl wenben">${role.roleDesc}<input type="hidden" id="roledescList" name="roledescList" value="${role.setId}"/></div>
                    </c:forEach>
                  </div>
                </div>
              </div>
            </div>
            --%>
            <%-- search--%>
            <%--
            <div class="form-group  col-lg-10">
              <label for="" class="col-sm-3 control-label"></label>
              <div class="col-sm-7 fr clearfix search">
                <input  id="customerName" class="fl" name="textfield"type="text"value="点击搜索已有角色"
                        onfocus="if (value =='点击搜索已有角色'){value =''}"onblur="if (value ==''){value='点击搜索已有角色'}"
                        onclick="getConfirm()"/><ul class="on_changes"></ul>
                <button class="fr" type="button" onclick="searchRoles()">搜索</button>
              </div>
            </div>
            --%>

            <%--取消,确定--%>
            <div class="form-group  col-xs-8">
              <label class="col-sm-3 control-label" for=""></label>
              <div class="col-sm-7">
                <button type="button" class="btn btn-primary" onclick="jq_submit()">确定</button>
                <a href="" onclick="javaScript:history.go(-1)" class="btn btn-primary" for="">取消</a>
              </div>
            </div>
          </form>
        <%--</div>--%>
      <%--</div>--%>
    </div>

  </div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<!-- 校验 -->
<script>
  $(function(){
    var error = $("#error").val();
    if(error == "1"){
      alert("员工用户创建/更新失败!");
    }
  })
  function checkVal() {
    $("#frm").validate();
  }
  function queryProjectNameById() {
    var projectId = $("#city").val();
    $("#planName").empty();
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId, function (data) {
      var arr = data.data;
      $("#projectName").empty();
      $("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');
      for (var k in arr) {
        $("#projectName").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
//                alert(arr[k][0]);
      }

    });
  }
  //提交
  function jq_submit() {
    $("#frm").submit();
  }
  $().ready(function () {
    var validator = $("#frm").validate({
      errorElement: "span",
      rules: {
        userNameDto: {
          required: true
        },
        staffNameDto: {
          required: true
        },
        mobileDto: {
          required: true
        },
        email: {
          required: true
        }
      },
      messages: {
        userNameDto: {
          required: "请输入用户名称!"
        },
        staffNameDto: {
          required: "请输入姓名!"
        },
        mobileDto: {
          required: "请输入联系方式!"
        },
        email: {
          required: "请输入邮箱地址!"
        }
      }
    })
  });

  //搜索角色
  function searchRoles(){
    /*  bak
    $(".on_changes").find("li").remove();
    var roledesc = $("#customerName").val();
    $.ajax({
      type: "POST",
      url: "../memberAuthority/searchRoles",
      data: {
        roledesc:roledesc
      },
      dataType: "json",
      success: function (data) {
        var roleList = data.roleList;
        for(var i=0; i<roleList.length; i++){
          var setId = roleList[i].setId;
          var roledesc = roleList[i].roledesc;$(".on_changes").append('<li val="'+setId+'" onclick="cli(this)">'+roledesc+'</li>');}
      }
    });
    */
    $(".on_changes").find("li").remove();
    var roledesc = $("#customerName").val();
    $.ajax({
      type: "POST",
      url: "../memberAuthority/searchRoles",
      data: {
        roledesc:roledesc
      },
      dataType: "json",
      success: function (data) {
        var roleList = data.roleList;
        for(var i=0; i<roleList.length; i++){
          var setId = roleList[i].setId;
          var roledesc = roleList[i].roledesc;$(".on_changes").append('<li val="'+setId+'" onclick="cli(this)">'+roledesc+'</li>');}
      }
    });
  }
  /*
  function cli(li){
    var liObj = $(li);    //DOM元素转JQ对象
    var val = li.getAttribute("val");   //JS获取DOM对象自定义属性值
    if(val == '0'){
      return ;
    }
    var flag = 0;
   $("input[name=roledescList]").each(function(){
   if(val==$(this).val()){
   flag = 1;
   }
   })
    if(flag == 0){
      var txt= liObj.text();
      $("#customerName").val(txt);
      if(txt!='点击添入标题')
      {
        $(".demo .text").prepend('<div class="fl wenben">'+txt+'<div class="deletePic">'+'</div>'+'<input type="hidden" id="roledescList" name="roledescList" value="'+val+'"/></div>');
      }
      $(".demo .wenben").click(function(){
        $(this).remove();
      })
    }
  }
  function getConfirm(){
    // 控制下拉框显示
    var display =$('.on_changes');
    if(display.is(':hidden')){//如果node是隐藏的则显示node元素，否则隐藏
      $(".on_changes").show();
    }else{
      $(".on_changes").hide();
    }
  }
  */
  /*-Mj-*/
//  $(function(){
//    $(".on_changes li").click(function(){
//      var txt= $(this).text();
//      $("input").val(txt);
//      if(txt!='点击添入标题')
//      {
//        $(".demo .text").prepend('<div class="fl wenben">'+txt+'</div>');
//      }
//      $(".demo .wenben").click(function(){
//        $(this).remove();
//      })
//    })
//  })
//  $("#customerName").click(getConfirm);
//
//  function getConfirm(){
//
//    // 控制下拉框显示
//    var display =$('.on_changes');
//    if(display.is(':hidden')){//如果node是隐藏的则显示node元素，否则隐藏
//      $(".on_changes").show();
//    }else{
//      $(".on_changes").hide();
//    }
//  }
  /*----*/
</script>
<%--角色查询JS_Start--%>
<script>
  $(function () {
    var $searchInput = $('#searchInput');
    var $search = $('.search');
    var $button = $('.button');
    var $searchResultList=$('#searchResultList');

    var $searchList = $search.children('.searchList');
    console.log($searchList)
    $searchInput.on('focus keyup', function () {
      var val=$(this).val().replace(/^ +| +$/g,'');
      console.log(val)
     /* if(val.length>=1){
        showList();
        return;
    }*/
      showList();
      $searchList.stop().slideUp(200);
    })
    $searchInput.on('focus mouseup', function () {
      var val=$(this).val().replace(/^ +| +$/g,'');
      console.log(val)
     /* if(val.length>=1){
        showList();
        return;
      }*/
      showList();
      $searchList.stop().slideUp(200);
    })
    $('body').on('click', function (e) {
      e=e||window.event;
      var tar= e.target;
      var $tar=$(tar);
      //重复校验开始
      var val = $tar[0].getAttribute("val");
      if(val == '0'){
        return ;
      }
      var flag = 0;
      $("input[name=roledescList]").each(function(){
        if(val==$(this).val()){
          flag = 1;
        }
      })
      if(flag == 1){
        return ;
      }
      //重复校验结束
      if(tar.tagName.toUpperCase()==="INPUT"&&tar.className=='searchInput'){
        return;
      }
      if(tar.tagName.toUpperCase()==="INPUT"&&tar.className=='button'){

      }
      if(tar.tagName.toUpperCase()==="LI"&&$tar.parent().hasClass('searchList')){
//                window.open('https://www.baidu.com/s?wd=' + encodeURIComponent($tar.html()), '_blank');
//                $searchList.stop().slideUp(200);
        $searchInput.val($tar.html());
        $('#searchResultList').val($tar.html());
        $tar.remove();
//        console.log($searchResultList);

        var hid = '<input type="hidden" id="roledescList" name="roledescList" value="'+$tar[0].getAttribute("val")+'">';
        $('#searchResultList').prepend('<div class="resultList">'+$tar[0].innerText+'<div class="deletePic">'+'</div>'+hid+'</div>');

        $('#searchResultList .resultList').click(function(){
          $(this).remove();
        })
        $("#searchResultList .resultList").mouseover(function () {
          $(this).children(".deletePic")[0].style.cssText="background: url(../static/images/deletePic2.png) no-repeat;background-size: contain;"
        })
        $("#searchResultList .resultList").mouseout(function () {
          $(this).children(".deletePic")[0].style.cssText="";
        })
        return;
      }
      $searchList.stop().slideUp(200);
    })


    $button.on('click',function (){
      var val=$searchInput.val().replace(/^ +| +$/g,'');
      if(val.length==0){
        return;
      }
//            window.open('https://www.baidu.com/s?wd=' + encodeURIComponent($searchInput.val()), '_blank');
    });

    $(".searchInput").on('click',function(){
      $searchList.stop().slideUp(200);
      showList();
    })
    //补充点击事件
    $(".searchInput").click(function(){
      $searchList.stop().slideUp(200);
      showList();
    });
    //结果事件
    resultListOper();
    function resultListOper (){
      $('#searchResultList .resultList').click(function(){
        $(this).remove();
      })
      $("#searchResultList .resultList").mouseover(function () {
        $(this).children(".deletePic")[0].style.cssText="background: url(../static/images/deletePic2.png) no-repeat;background-size: contain;"
      })
      $("#searchResultList .resultList").mouseout(function () {
        $(this).children(".deletePic")[0].style.cssText="";
      })
    }
    //鼠标移出事件
//    $(".searchList").mouseout(function(){
//      $searchList.stop().slideUp(200);
//    })
    //------


    function showList() {
//      var roledesc = $("#customerName").val();
      var val = $searchInput.val().replace(/^ +|  +$/g, '');
//      if (val.length == 0) {
//        return
//      }
      $.ajax({
        url: "../memberAuthority/searchRoles",
        type: 'post',
        data: {
          roledesc:val
        },
        dataType: 'json',
        success: function (data) {
          //<li val="007de8a31d8b4bd290b294f0b0ff880b" onclick="cli(this)">北京全部项目可查看</li>
          var str = '';
          var roleList = data.roleList;
          for(var i=0; i<roleList.length; i++){
            var setId = roleList[i].setId;
            var roledesc = roleList[i].roledesc;
//            $(".on_changes").append('<li val="'+setId+'" onclick="cli(this)">'+roledesc+'</li>');
            str += '<li val="'+setId+'">'+roledesc+'</li>';
          }
//          var str = '';
//          if (data && data['s']) {
//            data = data['s'];
//            $.each(data, function (index, item) {
//              if (index <= 9) {
//                str += "<li>" + item + "</li>"
//              }
//            })
//          }
          if(!str){
            $searchList.css('display','none')
          }
          console.log(str)
          $searchList.html(str).stop().slideDown(200);
        }

      })

    }
  })
</script>
<%--角色查询JS_End--%>
</body>
</html>
