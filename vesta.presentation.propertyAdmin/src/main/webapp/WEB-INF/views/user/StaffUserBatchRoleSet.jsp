<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
  <script src="../static/property/js/propertyHousPay.js"></script>
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
    .form-body{
      /*height: 8rem;*/
      height: 11rem;
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
  <input type="hidden" id="menuId" value="002300020000"/>
  <input type="hidden" id="error" value="${error}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <form id="form" class="form-horizontal" action="../staffUser/batchSetRole" method="post">
          <div class="form-group  col-lg-10">
            <label class="col-sm-3 control-label">批量添加角色：</label>
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
          <div class=" form-group col-lg-10">
            <label class="col-sm-3 control-label"></label>
            <div class="col-sm-7 fr clearfix search">
              <input type="text" placeholder="点我添加角色" id="searchInput" class="searchInput" />
              <%--<input type="button" value="搜索" class="button" disabled/>--%>
              <ul class="searchList" id="searchList">
              </ul>
            </div>
          </div>
          <button type="submit" class="btn btn-primary" for="propertySearch">确定</button>
          <a href="javascript:void(0);" onclick="cancel()" class="btn btn-primary" for="payBatchAdd">取消</a>
      </div>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
      <table width="100%" class="tableStyle">
        <thead>
        <tr>
          <td width="52px">
            <div class="checkbox">
              <label>
                <input type="checkbox" id="checkAll" value="" checked>
              </label>
            </div>
          </td>
          <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
          <th>姓名</th>
          <th>账号名</th>
          <th>联系方式</th>
          <th>公司</th>
          <th>区域</th>
          <th>项目</th>
          <th>角色</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${staffUserList}" var="staffUser" varStatus="row">
          <tr>
            <td>
              <div class="checkbox">
                <label>
                  <input type="checkbox" checked id="staffUserIdList" class="staffUserIdList" name="staffUserIdList" value="${staffUser.staffId}"/>
                </label>
              </div>
            </td>
            <td><b>${row.index + 1}</b></td>
            <td>${staffUser.staffName}</td>
            <td>${staffUser.userName}</td>
            <td>${staffUser.mobile}</td>
            <td>${staffUser.company}</td>
            <td>${staffUser.scope}</td>
            <td>${staffUser.project}</td>
            <td>${staffUser.roleDesc}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </form>
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
</script>
<script>
  $(function() {
    if($("#error").val() == '1'){
      alert("批量设置角色失败！");
      window.location.href = "../staffUser/staffUserList.html";
    }

    //全选
    $("#checkAll").click(function(){
      var isCheck = $(this).is(':checked');
      var arr=$(".staffUserIdList");
      for ( var i = 0; i < arr.length; i++){
        if(isCheck){
          //已选中
          arr[i].checked = true;
        }else{
          //未选中
          arr[i].checked = false;
        }
      }
    });
  });

  function cancel(){
    window.location.href = "../staffUser/staffUserList.html";
  }

  <%--角色查询JS_Start--%>
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