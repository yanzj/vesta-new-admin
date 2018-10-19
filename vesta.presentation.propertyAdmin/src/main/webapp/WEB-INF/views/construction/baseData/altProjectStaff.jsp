<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/10/31
  Time: 10:12
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
    $(function () {
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
    <form name="updateStaff" id="updateStaff" method="post">
      <input type="hidden" id="projectId" value="${projectId}">
      <div class="row">
        <div class="col-md-10 role_new_submit">
          <div class="newRoleSubmit">
            <button type="submit" class="btn btn-primary"  onclick="return check(this.from)">保存</button>
            <a  href="../BaseData/authProjectStaff.html?supplierId=${agency.supplierId}&agencyId=${agency.agencyId}&agencyType=${agency.agencyType}" class="btn btn-primary" for="" >关闭</a>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-10 role_new_submit2">
          <table class="table table-bordered">
            <caption class="role_table_title">员工基础信息</caption>
            <input type="hidden" id="userId1" name="userId1" value="${agency.userId}">
            <input type="hidden" id="agencyId" name="agencyId" value="${agency.agencyId}">
            <input type="hidden" id="agencyType" name="agencyType" value="${agency.agencyType}">
            <tbody>
            <%--<tr>--%>
              <%--<td class="role_table_roleName">用户名</td>--%>
              <%--<td class="role_table_fillCont">--%>
                <%--<input type="text" name="userNameR" id="userName" class="userName" value="${userStaffDTO.userName}">--%>
              <%--</td>--%>
            <%--</tr>--%>
            <tr>
              <td class="role_table_titleable_roleName">姓名</td>
              <td class="role_table_fillCont">
                <input type="text"   class="roleNameText" id="staffName1" name="staffName1" value="${userStaffDTO.staffName}">
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">联系方式</td>
              <td class="role_table_fillCont">
                <c:if test="${userStaffDTO.phone ne '' && userStaffDTO.phone ne null}">
                  <input type="text"  readonly="readonly"  class="roleNameText" id="phone" name="phone" value="${userStaffDTO.phone}">
                </c:if>
                <c:if test="${userStaffDTO.phone eq '' }">
                  <input type="text"  class="roleNameText" id="phone" name="phone" value="${userStaffDTO.phone}">
                </c:if>
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">所属责任单位</td>
              <td class="role_table_fillCont">
                <input type="hidden" name="supplierId" id="supplierId" value="${supplier.supplierId}">
                ${supplier.supplierName}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">是否启用</td>
              <td >
                <input type="radio" name="status" value="1" <c:if test="${userStaffDTO.status eq '1'}">checked="checked"</c:if>/>是
                <input type="radio" name="status" value="0" <c:if test="${userStaffDTO.status eq '0'}">checked="checked"</c:if>/>否
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">角色</td>
              <td>
                <input type="hidden" id ="roleAgencyId" name="roleAgencyId" value="">
                <c:forEach items="${roleList}" var="list" varStatus="row">
                  <input type="checkbox" name="roleIds" value="${list.roleId}" <c:if test="${list.checked eq '1'}">checked="checked"</c:if>/>${list.roleName}
                </c:forEach>
              </td>
            </tr>
              <td class="role_table_roleName">备注</td>
              <td>
                <input type="text"  class="roleNameText" id="remarks" name="remarks" value="${userStaffDTO.remarks}">
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

<script type="text/javascript">
    function check(form){
        var boxes = document.getElementsByTagName("input");
        var roleId="";
        for(i=0;i<boxes.length;i++){
            if(boxes[i].name=="roleIds" && boxes[i].checked == true){
                roleId+=","+boxes[i].value;
            }
        }
        $('#roleAgencyId').val(roleId)
    }

</script>
<script type="text/javascript">
  $.validator.setDefaults({
    submitHandler: function() {
      $.ajax({
        url: "../BaseData/addUserStaff",            //目标网址
        type: "post",
        async: "false",
        dataType: "json",
        data: $("#updateStaff").serialize(),
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
              alert("保存成功！");
                window.location.href = '../BaseData/authProjectStaff.html?supplierId='+$("#supplierId").val()+"&agencyId="+$('#agencyId').val()+"&agencyType="+$('#agencyType').val();
            } else if(data == 'ok1'){
                alert("保存成功！密码重置！");
                window.location.href = '../BaseData/authProjectStaff.html?supplierId='+$("#supplierId").val()+"&agencyId="+$('#agencyId').val()+"&agencyType="+$('#agencyType').val();

            }else{
                alert("保存失敗！密码重置！");
                alert(data);
            }
          }
        }
      });

    }
  });

  $().ready(function() {
    $("#updateStaff").validate({
      rules: {
          staffName: {
          required: true,
          minlength: 1,
          maxlength: 13
        },
          phone: {
          required: true,
          minlength: 11,
          maxlength: 13
        }
      },
      messages: {
          staffName: {
          required: "请输入真实姓名！",
          minlength: "不能少于1个字符！",
          maxlength: "请勿超过13个字！"
        },
          phone: {
          required: "请输入联系电话",
          minlength: "不能少于11位",
          maxlength: "请勿超过13位"
        }
      },
    })

  })
</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>

</body>
</html>
