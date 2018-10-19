<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2017/12/25
  Time: 20:44
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
            $("#007100010000").addClass("active");
            $("#007100010000").parent().parent().addClass("in");
            $("#007100010000").parent().parent().parent().parent().addClass("active");
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
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007100010000" username="${authPropertystaff.staffName}" />
    <div class="container1 userStaffManage">
        <form name="updateAgency" id="updateAgency" method="post">
            <div class="row">
                <div class="col-md-10 role_new_submit">
                    <div class="newRoleSubmit">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <a  href="#" onclick="javascript:window.history.back();return false" class="btn btn-primary" for="" >关闭</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10 role_new_submit2">
                    <table class="table table-bordered">
                        <caption class="role_table_title">组织机构信息</caption>
                        <tbody>
                        <tr>
                            <td class="role_table_roleName"><label style="color:red;">*</label>组织机构名称</td>
                            <td class="role_table_fillCont">
                                <input type="text"  class="roleNameText" id="agencyName" name="agencyName" value="${outerAgencyDTO.agencyName}">
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><label style="color:red;">*</label>上级部门</td>
                            <td class="role_table_fillCont">
                                <select class="form-control" placeholder="" id="agencyId" name="agencyId">
                                    <c:forEach items="${agencys}" var="agency">
                                        <option value="${agency.key}"
                                                <c:if test="${agency.key eq outerAgencyDTO.agencyId}">selected</c:if>>${agency.value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><label style="color:red;">*</label>是否有效</td>
                            <td>
                                <input type="checkbox" value="1" name="status" <c:if test="${outerAgencyDTO.status eq 1}">checked="checked"</c:if>>（选中表示为是）
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName">备注</td>
                            <td>
                                <input type="text"  class="roleNameText" id="memo" name="memo">
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
<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler: function() {
            $.ajax({
                url: "../authorityForProject/addOrUpdateOuterAgency",            //目标网址
                type: "post",
                async: false,
                dataType: "json",
                data: $("#updateAgency").serialize(),
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
                            alert("保存成功");
                            window.location.href = '../authorityForProject/outerProjectUserManage.html';
                        } else {
                            alert(data);
                        }
                    }
                }
            });

        }
    });

    $().ready(function() {
        $("#updateAgency").validate({
            rules: {
                agencyName: {
                    required: true,
                    minlength: 1,
                    maxlength: 13
                }
            },
            messages: {
                agencyName: {
                    required: "请输入组织机构名！",
                    minlength: "不能少于1个字符！",
                    maxlength: "请勿超过13个字！"
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


