<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/10/28
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
  <!-- Bootstrap Core CSS -->
  <link href="../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
  <!-- Custom CSS -->
  <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>
  <style>
    label.error {
      margin-left: 1%;
      color: red;
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
    $(function(){
      console.log("sqq")
      $("#003100010000").addClass("active");
      $("#003100010000").parent().parent().addClass("in");
      $("#003100010000").parent().parent().parent().parent().addClass("active");
    })
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

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="003100010000" username="${authPropertystaff.staffName}" />
  <div class="container1 userStaffManage">
    <form name="addEmploy" id="addEmploy" action="../BaseData/updateEmploy.html" method="post">
      <div class="row">
        <div class="col-md-10 role_new_submit">
          <div class="newRoleSubmit">
            <button type="submit" class="btn btn-button save">保存</button>
            <button type="button" class="btn btn-button save" onclick="history.go(-1)">关闭</button>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-10 role_new_submit2">
          <table class="table table-bordered">
            <caption class="role_table_title">机构信息</caption>
            <input type="hidden" name="sId" value="${employ.sId}">
            <input type="hidden" name="projectId" value="${projectId}">
            <tbody>
            <tr>
              <td class="role_table_roleName">机构名称</td>
              <td class="role_table_fillCont">
                <div class="nice-select" style="float:left;box-shadow:none">
                  <input type="text" class="roleNameText" id="sName" name="sName" value="${employ.sName}">
                    <div id="div_items">
                      <c:forEach items="${nameList}" var="vo">
                        <div class="div_item">${vo}</div>
                      </c:forEach>
                    </div>
                </div>
                <div style="color: red">请从已有机构中选择，如没有，新增是建议使用合同名称</div>
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">机构简称</td>
              <td class="role_table_fillCont">
                <input type="text" class="roleNameText" id="abbreviationName" name="abbreviationName" value="${employ.abbreviationName}">
              </td>
            </tr>
            <tr>
              <td class="role_table_titleable_roleName">性质</td>
              <td class="role_table_fillCont">
                <input type="radio" name="sNature" value="1"<c:if test="${employ.sNature eq 1}">checked="checked"</c:if>>总包&nbsp;
                <input type="radio" name="sNature" value="2"<c:if test="${employ.sNature eq 2}">checked="checked"</c:if>>分包&nbsp;
                <input type="radio" name="sNature" value="3"<c:if test="${employ.sNature eq 3}">checked="checked"</c:if>>监理
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">是否有效</td>
              <td class="role_table_fillCont">
                <input type="radio" name="sStatus" value="1"<c:if test="${employ.sStatus eq 1}">checked="checked"</c:if>>是&nbsp;
                <input type="radio" name="sStatus" value="0"<c:if test="${employ.sStatus eq 0}">checked="checked"</c:if>>否&nbsp;
                <%--<input type="checkbox" value="1" name="sStatus"<c:if test="${employ.sStatus eq 1}">checked="checked"</c:if>>（选中表示为是）--%>
              </td>
            </tr>

            <%--<tr>--%>
              <%--<td class="role_table_roleName">备注</td>--%>
              <%--<td>--%>
                <%--<input type="text"  class="roleNameText" id="sMemo" name="sMemo" value="${employ.sMemo}">--%>
              <%--</td>--%>
            <%--</tr>--%>
            <tr id="buildHide">
              <td class="role_table_roleName">楼栋范围</td>
              <td class="role_table_fillCont">
                <input type="checkbox" id="checkall" >全选&nbsp;
                <c:forEach items="${buildList}" var="list">
                  <input type="checkbox" name="buildId" value="${list.buildId}"<c:forEach items="${buildIds}" var="id"><c:if test="${list.buildId eq id}">checked="checked"</c:if></c:forEach>>${list.buildName}&nbsp;
                </c:forEach>
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">最后修改时间</td>
              <td>
                <span>${employ.modifyTime}</span>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </form>
  </div>
</div>
</div>
</div>

<style type="text/css">
  #div_main {
    margin: 0 auto;
    width: 300px;
    height: 400px;
    border: 1px solid black;
    margin-top: 50px;
  }

  #div_txt {
    position: relative;
    width: 200px;
    margin: 0 auto;
    margin-top: 40px;
  }

  #txt1 {
    width: 99%;
  }

  #div_items {
    position: relative;
    width: 100%;
    height: 200px;
    border: 1px solid #66afe9;
    border-top: 0px;
    overflow: auto;
    display: none;
  }

  .div_item {
    width: 100%;
    height: 20px;
    margin-top: 1px;
    font-size: 13px;
    line-height: 20px;
  }
</style>

<script type="text/javascript">

    //弹出列表框
    $("#sName").click(function () {
        $("#div_items").css('display', 'block');
        return false;
    });

    //隐藏列表框
    $("body").click(function () {
        $("#div_items").css('display', 'none');
    });

    //移入移出效果
    $(".div_item").hover(function () {
        $(this).css('background-color', '#1C86EE').css('color', 'white');
    }, function () {
        $(this).css('background-color', 'white').css('color', 'black');
    });

    //文本框输入
    $("#sName").keyup(function () {
        $("#div_items").css('display', 'block');//只要输入就显示列表框

        if ($("#sName").val().length <= 0) {
            $(".div_item").css('display', 'block');//如果什么都没填，跳出，保持全部显示状态
            return;
        }

        $(".div_item").css('display', 'none');//如果填了，先将所有的选项隐藏

        for (var i = 0; i < $(".div_item").length; i++) {
            //模糊匹配，将所有匹配项显示
//            if ($(".div_item").eq(i).text().substr(0, $("#sName").val().length) == $("#sName").val()) {
//                $(".div_item").eq(i).css('display', 'block');
//            }
            if ($(".div_item").eq(i).text().indexOf( $("#sName").val()) >=0) {
                $(".div_item").eq(i).css('display', 'block');
            }
        }
    });

    //项点击
    $(".div_item").click(function () {
        $("#sName").val($(this).text());
    });

</script>

<script type="text/javascript">
  $().ready(function() {
    $("#checkall").click(
            function(){
              if(this.checked){
                $("input[name='buildId']").each(function(){this.checked=true;});
              }else{
                $("input[name='buildId']").each(function(){this.checked=false;});
              }
            });

    $("#addEmploy").validate({
      rules: {
        sName: {
          required: true,
          minlength: 1,
          maxlength: 25
        }
      },
      messages: {
        sName: {
          required: "请输入责任单位名称！",
          minlength: "不能少于1个字符！",
          maxlength: "请勿超过25个字！"
        }
      },
        rules: {
            abbreviationName: {
                required: true,
                minlength: 1,
                maxlength: 25
            }
        },
        messages: {
            abbreviationName: {
                required: "请输入责任单位简称！",
                minlength: "不能少于1个字符！",
                maxlength: "请勿超过25个字！"
            }
        },
    })




    var radioValue = $('input:radio:checked').val();
    if(radioValue=='3'){
      $('#buildHide').hide();
    }
    $(":radio[name='sNature']").click(function(){
      var index = $(":radio[name='sNature']").index($(this));
      if(index == 2) //选中第2个时，div显示
        $('#buildHide').hide();
      else //当被选中的不是第2个时，div隐藏
        $('#buildHide').show();
    });

  })
</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
</body>
</html>
