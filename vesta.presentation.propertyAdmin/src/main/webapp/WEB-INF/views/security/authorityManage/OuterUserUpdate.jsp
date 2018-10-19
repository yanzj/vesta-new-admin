<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2017/12/19
  Time: 16:46
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
            $("#007000010000").addClass("active");
            $("#007000010000").parent().parent().addClass("in");
            $("#007000010000").parent().parent().parent().parent().addClass("active");
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
    <%-- zTree --%>
    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
</head>

<body class="cbp-spmenu-push">
<div>

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007000010000" username="${authPropertystaff.staffName}" />
    <div class="container1 userStaffManage">
        <form name="updateStaff" id="updateStaff" method="post">
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
                        <caption class="role_table_title">员工基础信息</caption>
                        <input type="hidden" name="staffIdW" id="staffIdW" value="${userManageDTO.staffIdW}">
                        <input type="hidden" name="agencyIdW" id="agencyIdW" value="">
                        <tbody>
                        <tr>
                            <td class="role_table_roleName"><label style="color:red;">*</label> 姓名</td>
                            <td class="role_table_fillCont">
                                <input type="text"  class="roleNameText" id="staffNameW" name="staffNameW" value="${userManageDTO.staffNameW}">
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><label style="color:red;">*</label> 手机号码</td>
                            <td class="role_table_fillCont">
                                <input type="text" class="roleNameText" id="mobileW" name="mobileW" value="${userManageDTO.mobileW}">
                                <%--<label style="color: red"><h4>*</h4></label>--%>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName">邮箱地址</td>
                            <td class="role_table_fillCont">
                                <input type="text" class="roleNameText" id="emailW" name="emailW" value="${userManageDTO.emailW}">
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName">是否启用</td>
                            <td >
                                <input type="checkbox" value="1" name="statusW" <c:if test="${userManageDTO.statusW eq 1}">checked="checked"</c:if>>（选中表示为是）
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName">备注</td>
                            <td>
                                <input type="text"  class="roleNameText" id="memoW" name="memoW" value="${userManageDTO.memoW}">
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><label style="color:red;">*</label> 所属组织机构</td>
                            <td class="role_table_fillCont">
                                <div class="content_wrap col-md-12">
                                    <div class="zTreeDemoBackground left" style="border: 1px solid #ccc;">
                                        <ul id="treeDemo" class="ztree" style="width: 100%;"></ul>
                                    </div>
                                    <div class="people_list"></div>
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
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler: function() {
            var reg = /^1[3|4|5|6|7|8][0-9]{9}$/; //验证手机号码
            var mobile = document.getElementById("mobileW").value.trim();//手机号码

            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
            var result='';
            if(nodes.length==0){
                alert("请选择所属机构！！");
                return false;
            }
            for (var i = 0; i < nodes.length; i++) {
                result += nodes[i].id +',';
            }
            result=result.substring(0,result.lastIndexOf(","));
            document.updateStaff.agencyIdW.value=result;

            if(reg.test(mobile)){
                $.ajax({
                    url: "../authority/addOrUpdateOuterUser",            //目标网址
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
                                alert("保存成功");
                                window.location.href = '../authority/outerUserManage.html?agencyId='+$("#agencyId").val();
                            } else {
                                alert(data);
                            }
                        }
                    }
                });
            }else{
                alert("错误，手机号码格式不正确");
                return false;
            }
        }
    });

    $().ready(function() {
        $("#updateStaff").validate({
            rules: {
                staffNameW: {
                    required: true,
                    minlength: 1,
                    maxlength: 13
                },
                mobileW: {
                    required: true,
                    minlength: 11,
                    maxlength: 13
                }
            },
            messages: {
                staffNameW: {
                    required: "请输入真实姓名！",
                    minlength: "不能少于1个字符！",
                    maxlength: "请勿超过13个字！"
                },
                mobileW: {
                    required: "请输入联系电话!",
                    minlength: "不能少于11位",
                    maxlength: "请勿超过13位"
                }
            },
        })

    })


</script>
<script type="text/javascript">
    // zTree 的参数配置,深入使用请参考 API 文档(setting 配置详解)
    var setting = {
        check: {
            enable: true,
            chkboxType:  { "Y": "", "N": "" }
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    var code;
    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>"+str+"</li>");
    }

    $(document).ready(function(){
        $.ajax({
            url: "../authority/getOuterAgencyByStaffId",
            dataType: "json",
            data:{
                staffIdW:document.getElementById("staffIdW").value
            },
            success: function (result) {
                $.fn.zTree.init($("#treeDemo"), setting, result);
            }
        });
        showCode('setting.check.chkboxType = { "Y" : "", "N" : "" };');
    });
</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
</body>
</html>