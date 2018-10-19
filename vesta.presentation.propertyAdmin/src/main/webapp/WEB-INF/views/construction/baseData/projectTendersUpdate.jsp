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

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="003100010000" username="${authPropertystaff.staffName}"/>
    <div class="container1 userStaffManage">
        <form name="addEmploy" id="addEmploy" action="../BaseData/addTenders">
            <div class="row">
                <div class="col-md-10 role_new_submit">
                    <div class="newRoleSubmit">
                        <button type="submit" class="btn btn-button save" onclick="fun()">保存</button>
                        <button type="button" class="btn btn-button save" onclick="history.go(-1)">关闭</button>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10 role_new_submit2">
                    <table class="table table-bordered">
                        <caption class="role_table_title">标段信息</caption>
                        <input type="hidden" id="projectId" name="projectId" value="${projectId}">
                        <input type="hidden" id="tenderId" name="tenderId" value="${projectTender.tenderId}">
                        <tbody>
                        <tr>
                            <td class="role_table_roleName">标段名称</td>
                            <td class="role_table_fillCont">
                                <input type="text" class="form-control" placeholder="" id="tenderName" name="tenderName"
                                       value="${projectTender.tenderName}">
                            </td>
                        </tr>
                        <%--<tr>--%>
                        <%--<td class="role_table_roleName">是否有效</td>--%>
                        <%--<td class="role_table_fillCont">--%>
                        <%--<input type="checkbox" id="sStatus" name="sStatus" value="1" <c:if test="${employ.sStatus eq 1}">checked="checked"</c:if>>（选中表示为是）--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <tr>
                            <td class="role_table_roleName">对应总包</td>
                            <td>
                                <select id="supplierId" name="supplierId" class="form-control">
                                    <c:forEach items="${agencyList}" var="agency">
                                        <option value="${agency.supplierId}"
                                                <c:if test="${agency.supplierId eq projectTender.supplierId}">selected</c:if>>${agency.supplierName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr id="buildHide">
                            <td class="role_table_roleName">楼栋范围</td>
                            <td class="role_table_fillCont" id="build">
                                <%--<input type="checkbox" id="checkall">全选&nbsp;--%>
                                <c:forEach items="${supplierBuild}" var="list">
                                    <input type="checkbox" name="buildIds" value="${list.buildId}"
                                    <c:forEach items="${projectTender.buildingIds}" var="id">
                                           <c:if test="${list.buildId eq id.buildingId}">checked="checked"</c:if>
                                    </c:forEach>>${list.buildName}&nbsp;
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName">最后修改时间</td>
                            <td>
                                <span>${projectTender.modifyOn}</span>
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
    $().ready(function () {
        $("#checkall").click(
                function () {
                    if (this.checked) {
                        $("input[name='buildIds']").each(function () {
                            this.checked = true;
                        });
                    } else {
                        $("input[name='buildIds']").each(function () {
                            this.checked = false;
                        });
                    }
                });

        $("#addEmploy").validate({
            rules: {
                tenderName: {
                    required: true,
                    minlength: 1,
                    maxlength: 25
                }
            },
            messages: {
                tenderName: {
                    required: "请输入标段名称！",
                    minlength: "不能少于1个字符！",
                    maxlength: "请勿超过25个字！"
                }
            },
        })
    });

    $("#supplierId").change(function () {
        var supplierId = $("#supplierId").val();
        var projectId = $("#projectId").val();
        $("#build").empty();
        $.ajax({
            url: "../BaseData/getBuildersBySupplierId",
            type: "get",
            async: "false",
            data: {"supplierId": supplierId,
                "projectId": projectId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    if (data != null) {
                        $.each(data, function (i, item) {
                            //添加复选框以及对应的值
                            $("#build").append(
//                                    "<input type='checkbox' id='checkall'>全选&nbsp;"+
                                    "<input type='checkbox' id='" + item.buildId + "'  name='buildIds' value='" + item.buildId + "'>" + item.buildName + "&nbsp"
                            );
                        });
                    }
                }
            }
        });
    });
    function fun() {
        obj = document.getElementsByName("buildIds");
        check_val = [];
        for (k in obj) {
            if (obj[k].checked)
                check_val.push(obj[k].value);
        }
    }
</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
</body>
</html>
