<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
  <title>修改密码</title>
  <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
  <meta name="keywords" content=""/>
  <script type="application/x-javascript"> addEventListener("load", function () {
//    resizeTo(590,240)
    setTimeout(hideURLbar, 0);
  }, false);
  function hideURLbar() {
    window.scrollTo(0, 1);
  } </script>
  <!-- Bootstrap Core CSS -->
  <link href="../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
  <!-- Custom CSS -->
  <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>
  <style>
    html{
      width: 590px;
      height: 240px;
    }
    label.error {
      margin-left: 1%;
      color: red;
    }
    .submit_button {
      width: 10%;
      margin-left: 2.7%;
    }
  </style>

  <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
  <!-- font CSS -->
  <!-- font-awesome icons -->
  <link href="../static/css/font-awesome.css" rel="stylesheet">
  <!-- //font-awesome icons -->
  <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
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
  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
  <script src="../static/js/jquery.ztree.core-3.5.js"></script>
  <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
  <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
</head>

<body class="cbp-spmenu-push">
<div>
  <div class="row">
  </div>
  <form role="form">
    <div class="form-group col-lg-10">
      <label class="col-sm-3 control-label" for="newPassword_1"><h5>设置新密码 <font color="red">*</font></h5></label>
      <div class="col-sm-7">
        <input type="text" class="form-control" placeholder="" id="newPassword_1" name="newPassword_1">
      </div>
    </div>
    <div class="form-group col-lg-10">
      <label class="col-sm-3 control-label" for="newPassword_2"><h5>确认新密码 <font color="red">*</font></h5></label>
      <div class="col-sm-7">
        <input type="text" class="form-control" placeholder="" id="newPassword_2" name="newPassword_2">
      </div>
    </div>
    <div class="col-sm-7">
      <button type="button" class="btn btn-primary submit_button" onclick="jq_submit()">确定</button>
    </div>
  </form>
</div>

<script>
  function jq_submit(){
    var newPassword_1 = $("#newPassword_1").val();
    var newPassword_2 = $("#newPassword_2").val();
    if($.trim(newPassword_1) == "" || newPassword_1.indexOf(" ")!=-1){
      alert("请输入密码！");
      return ;
    }
    if($.trim(newPassword_2) == "" || newPassword_2.indexOf(" ")!=-1){
      alert("请输入密码！");
      return ;
    }
    if(newPassword_1 == newPassword_2){
      $.ajax({
        type: "POST",
        url: "../staffUser/updateStaffUserPassword",
        cache: false,
        async:false,
        data:{newPassword_1:newPassword_1,newPassword_2:newPassword_2},
        dataType:"json",
        success: function (data) {
          if (data.error == 0) {
            alert("密码修改成功！");
            window.close();
          }else if(data.error == 1){
            alert("两次密码输入不一致！");
            return ;
          }else{
            alert("对不起，操作失败！");
            window.close();
          }
        }
      });
    }else{
      alert("两次密码输入不一致！");
      return ;
    }
  }
</script>

</body>
</html>