<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/7/27
  Time: 11:16
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
  <style>
    label.error {
      margin-left: 1%;
      color: red;
    }
  </style>
  <!-- Bootstrap Core CSS -->
  <link href="../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
  <!-- Custom CSS -->
  <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>
  <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
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
      $("#001300020000").addClass("active");
      $("#001300020000").parent().parent().addClass("in");
      $("#001300020000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001300020000" username="${propertystaff.staffName}" />
  <div class="container1 userStaffManage">
    <form name="updateStaff" id="updateStaff" method="post">
      <div class="row">
        <div class="col-md-10 role_new_submit">
          <div class="newRoleSubmit">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">修改密码</button>
            <%--<a  href="/user/goAltPassword.html?staffId=${userStaffDTO.cnStaffId}" class="btn btn-primary" for="" >修改密码</a>--%>
            <a  href="/user/gotoUpdateStaff.html?staffIdDto=${userStaffDTO.cnStaffId}" class="btn btn-primary" for="" >编辑</a>
            <%--<a  href="/user/delStaffAgency.html?staffIdDto=${userStaffDTO.cnStaffId}" class="btn btn-primary" for="" >从部门中删除</a>--%>
            <a style="cursor: pointer;width: 9.5rem;" class="btn btn-primary" onclick="javascript:if(confirm('确定删除吗!'))location.href='/user/delStaffAgency.html?staffIdDto=${userStaffDTO.cnStaffId}&agencyId=${agencyId}';else return">从部门中删除</a>
            <a  href="#" onclick="javascript:window.history.back();return false" class="btn btn-primary" for="" >关闭</a>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-10 role_new_submit2">
          <table class="table table-bordered">
            <caption class="role_table_title">员工基础信息</caption>
            <tbody>
            <tr>
              <td class="role_table_roleName">账号(英文)</td>
              <td class="role_table_fillCont">
                ${userStaffDTO.cnUserName}
              </td>
            </tr>
            <tr>
              <td class="role_table_titleable_roleName">姓名(中文)</td>
              <td class="role_table_fillCont">
                ${userStaffDTO.cnStaffName}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">联系方式</td>
              <td class="role_table_fillCont">
                ${userStaffDTO.cnStaffMobile}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">邮箱地址</td>
              <td class="role_table_fillCont">
                ${userStaffDTO.cnStaffEmail}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">所属组织机构</td>
              <td class="role_table_fillCont">
                <span class="role_select_result" id="role_originOnly">
                  <c:forEach items="${userStaffDTO.staffAgency}" var="agencyList">
                    ${agencyList.agencyName}；&nbsp;
                  </c:forEach>
                </span>
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">金茂内部员工</td>
              <td >
                <input type="checkbox" disabled="disabled" value="1" name="jinmaoIs"<c:if test="${userStaffDTO.jinmaoIs eq 1}">checked="checked"</c:if>>（选中表示为是）
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">是否有效</td>
              <td >
                <input type="checkbox" disabled="disabled" value="1" name="status" <c:if test="${userStaffDTO.status eq 1}">checked="checked"</c:if>>（选中表示为是）
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">排序</td>
              <td >
                ${userStaffDTO.orderNum}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">备注</td>
              <td>
                ${userStaffDTO.memo}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">最后修改时间</td>
              <td>
                <span>${userStaffDTO.cnModifyTime}</span>
              </td>
            </tr>
            <tr style="background-color: #F2F2F2"><td colspan="2">质量管理功能模块相关信息</td></tr>
            <tr>
              <td class="role_table_roleName"><span>关联的角色</span></td>
              <td>
                <input type="hidden" name="appRoleSet" id="appRoleSet" value="">
                <!-- <span class="existence" ></span> -->
                <div class="existence">
                  <span style="display:inline-block" id="role_linkRole">
                   <c:forEach items="${roleSetList}" var="roleSet">
                     ${roleSet.appSetName};&nbsp;
                   </c:forEach>
                </span>
                </div>
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName"><span>关联的项目</span></td>
              <td class="roleNameText">
                <div class="existence2">
                  <span style="display:inline-block" id="role_project">
                    <c:forEach items="${projectList}" var="project">
                      ${project.name};&nbsp;
                    </c:forEach>
                  </span>
                </div>
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
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">修改员工密码</h4>
      </div>
      <form id="altPassword" autocomplete="off" class="form-horizontal" method="post">
        <div class="modal-body">
          <div class="form-body">
            <input type="hidden" id="cnStaffId" name="cnStaffId" value="${userStaffDTO.cnStaffId}">
            <%--用户名--%>
            <%--<div class="form-group  col-lg-5">--%>
            <%--<label class="col-sm-3 control-label"><spring:message code="staffManage.staffUserName"/></label>--%>
            <%--<label class="col-sm-9">--%>
            <%--${userStaffDTO.cnUserName}--%>
            <%--</label>--%>
            <%--</div>--%>
            <%--<div class="clearfix"></div>--%>
            <%--新密码--%>
            <div class="form-group  col-lg-6">
              <label for="cnPassword" class="col-sm-5 control-label">新密码</label>
              <div class="col-sm-7">
                <input type="password" style="display:none">
                <input type="password" class="form-control" autocomplete="off" placeholder="" id="cnPassword" name="cnPassword">
              </div>
            </div>
            <%--密码确认--%>
            <div class="clearfix"></div>
            <div class="form-group  col-lg-6">
              <label for="Password" class="col-sm-5 control-label">密码确认</label>
              <div class="col-sm-7">
                <input type="password" class="form-control" autocomplete="off" placeholder="" id="Password" name="rPassword">
              </div>
            </div>
            <div class="clearfix"></div>
            <button type="submit" class="btn btn-primary col-md-offset-2">保存</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<script type="text/javascript">
  $.validator.setDefaults({
    submitHandler: function() {
      $.ajax({
        url: "../user/altPassword",            //目标网址
        type: "post",
        async: "false",
        dataType: "json",
        data: $("#altPassword").serialize(),
        success: function (json) {
          <!-- 获取返回代码 -->
          var code = json.code;
          if (code != 0) {
            var errorMessage = json.msg;
            alert(errorMessage);
          } else {
            <!-- 获取返回数据 -->
            var data = json.data;
            if (data == 'ok') {
              alert("修改成功");
              window.location.href = '../user/staffDetail.html?staffId='+$("#cnStaffId").val()+'&agencyId=${agencyId}';
            } else {
              alert(data);
            }
          }
        }
      });
    }
  });

  $().ready(function() {
    $("#altPassword").validate({
      rules: {
        cnPassword:{
          required:true,
          minlength:6,
          maxlength:20
        },
        rPassword:{
          required:true,
          equalTo:"#cnPassword"
        }
      },
      messages: {
        cnPassword:{
          required:"请输入密码",
          minlength:"密码不能低于6位",
          maxlength:"请保持在20位以内"
        },
        rPassword:{
          required:"请输入确认密码",
          equalTo:"两次密码输入不一致"
        }
      }
    })
  })
</script>
</body>
</html>
