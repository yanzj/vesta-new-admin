<%--
  设置项目权限
  Created by IntelliJ IDEA.
  User: Jason
  Date: 2017/6/6
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

        .col-md-offset-5 button {
            height: 2.5rem;
            line-height: 2.5rem;
            padding: 0 12px;
            color: #fff;
            background-color: #286090;
            border-color: #204d74;
        }

        .typeList {
            position: relative;
        }

        div.addPeople {
            position: absolute;
            margin-left: 0;
            top: 6.3rem;
            left: 1.2rem;
        }

        div.addPeople input {
            width: 15rem;
        }

        div.btnB {
            width: 46.4rem;
            height: 1px;
            border-top: 1px dashed #ccc;
            background-color: white;
            margin-top: 30px;
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
            $("#006300010000").addClass("active");
            $("#006300010000").parent().parent().addClass("in");
            $("#006300010000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="006300010000" username="${authPropertystaff.staffName}"/>
    <div class="container1 userStaffManage">
        <form class="form-horizontal" name="projectInfo" id="projectInfo"
              action="../securityProject/saveProjectRole.html">
            <%--<input type="hidden" name="areaId" value="${areaId}">--%>
            <input type="hidden" name="projectId" value="${securityAllRoleDTO.id}">
            <div class="row">
                <div class="col-md-10 " style=" border-bottom: 1px dashed #ccc;">
                    <%--<span style="line-height: 30px;font-size: 15px;">编辑项目</span>--%>

                    <div class="newRoleSubmit">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <button onclick="history.go(-1)" class="btn btn-primary" for="">关闭</button>
                    </div>
                </div>
            </div>
            <div style="line-height: 30px;margin-top: 15px;">
                <span class="role_table_roleName">项目名称：</span>
                <span class="role_table_fillCont">
                    <input type="text" class="roleNameText" name="projectName" autofocus="autofocus"
                           value="${securityAllRoleDTO.name}">
                </span>
            </div>
            <div class="row">
                <div class="col-md-10 role_new_submit2">
                    <table class="table table-bordered">
                        <tbody>
                        <tr style="background-color: #003366;color: #fff;">
                            <td colspan="2">安全项目管理功能权限相关信息</td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>项目全体人员</span></td>
                            <td>
                                <input type="hidden" name="projectOfficerDep" id="viewAgencys">
                                <input type="hidden" name="projectOfficerStaff" id="viewStaffs">
                                <div class="existence">
                                          <span style="display:inline-block" class="selectResult" id="role_linkRole">
                                            <c:if test="${(securityAllRoleDTO.projectOfficerDep) != null && fn:length(securityAllRoleDTO.projectOfficerDep)>0}">
                                                <c:forEach items="${securityAllRoleDTO.projectOfficerDep}" var="dept">
                                                    ${dept.name}；
                                                </c:forEach>
                                                <br/>
                                            </c:if>
                                              <c:if test="${securityAllRoleDTO.projectOfficerStaff != null && fn:length(securityAllRoleDTO.projectOfficerStaff)>0}">
                                                  <c:forEach items="${securityAllRoleDTO.projectOfficerStaff}"
                                                             var="staff">
                                                      ${staff.name}；
                                                  </c:forEach>
                                              </c:if>
                                          </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal1">选择</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>甲方安全员</span></td>
                            <td>
                                <input type="hidden" name="partyASecurityOfficerDep" id="partyASecurityOfficerDep">
                                <input type="hidden" name="partyASecurityOfficerStaff" id="partyASecurityOfficerStaff">
                                <div class="existence">
                                          <span style="display:inline-block" class="selectResult2" id="role_linkRole2">
                                            <c:if test="${(securityAllRoleDTO.partyASecurityOfficerDep) != null && fn:length(securityAllRoleDTO.partyASecurityOfficerDep)>0}">
                                                <c:forEach items="${securityAllRoleDTO.partyASecurityOfficerDep}"
                                                           var="dept">
                                                    ${dept.name}；
                                                </c:forEach>
                                                <br/>
                                            </c:if>
                                              <c:if test="${securityAllRoleDTO.partyASecurityOfficerStaff != null && fn:length(securityAllRoleDTO.partyASecurityOfficerStaff)>0}">
                                                  <c:forEach items="${securityAllRoleDTO.partyASecurityOfficerStaff}"
                                                             var="staff">
                                                      ${staff.name}；
                                                  </c:forEach>
                                              </c:if>
                                          </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal2">选择</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>甲方工程师</span></td>
                            <td>
                                <input type="hidden" name="partyAEngineerDep" id="partyAEngineerDep">
                                <input type="hidden" name="partyAEngineerStaff" id="partyAEngineerStaff">
                                <div class="existence">
                                          <span style="display:inline-block" class="selectResult3" id="role_linkRole3">
                                            <c:if test="${(securityAllRoleDTO.partyAEngineerDep) != null && fn:length(securityAllRoleDTO.partyAEngineerDep)>0}">
                                                <c:forEach items="${securityAllRoleDTO.partyAEngineerDep}" var="dept">
                                                    ${dept.name}；
                                                </c:forEach>
                                                <br/>
                                            </c:if>
                                              <c:if test="${securityAllRoleDTO.partyAEngineerStaff != null && fn:length(securityAllRoleDTO.partyAEngineerStaff)>0}">
                                                  <c:forEach items="${securityAllRoleDTO.partyAEngineerStaff}"
                                                             var="staff">
                                                      ${staff.name}；
                                                  </c:forEach>
                                              </c:if>
                                          </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal3">选择</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>总包</span></td>
                            <td>
                                <input type="hidden" name="contractorDep" id="contractorDep">
                                <input type="hidden" name="contractorStaff" id="contractorStaff">
                                <div class="existence">
                                          <span style="display:inline-block" class="selectResult4" id="role_linkRole4">
                                            <c:if test="${(securityAllRoleDTO.contractorDep) != null && fn:length(securityAllRoleDTO.contractorDep)>0}">
                                                <c:forEach items="${securityAllRoleDTO.contractorDep}" var="dept">
                                                    ${dept.name}；
                                                </c:forEach>
                                                <br/>
                                            </c:if>
                                              <c:if test="${securityAllRoleDTO.contractorStaff != null && fn:length(securityAllRoleDTO.contractorStaff)>0}">
                                                  <c:forEach items="${securityAllRoleDTO.contractorStaff}" var="staff">
                                                      ${staff.name}；
                                                  </c:forEach>
                                              </c:if>
                                          </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal4">选择</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>监理</span></td>
                            <td>
                                <input type="hidden" name="supervisorDep" id="supervisorDep">
                                <input type="hidden" name="supervisortaff" id="supervisortaff">
                                <div class="existence">
                                          <span style="display:inline-block" class="selectResult5" id="role_linkRole5">
                                            <c:if test="${(securityAllRoleDTO.supervisorDep) != null && fn:length(securityAllRoleDTO.supervisorDep)>0}">
                                                <c:forEach items="${securityAllRoleDTO.supervisorDep}" var="dept">
                                                    ${dept.name}；
                                                </c:forEach>
                                                <br/>
                                            </c:if>
                                              <c:if test="${securityAllRoleDTO.supervisortaff != null && fn:length(securityAllRoleDTO.supervisortaff)>0}">
                                                  <c:forEach items="${securityAllRoleDTO.supervisortaff}" var="staff">
                                                      ${staff.name}；
                                                  </c:forEach>
                                              </c:if>
                                          </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal5">选择</span>
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
        $("#projectInfo").validate({
            rules: {
                projectName: {
                    required: true,
                    minlength: 1,
                    maxlength: 20
                }
            },
            messages: {
                projectName: {
                    required: "请输入项目名称！",
                    minlength: "不能少于1个字符！",
                    maxlength: "请勿超过20个字！"
                },
            }
        })
    })
</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>

<!-- 模态框1（Modal）-->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0">
                                <div class="typeList_department" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees">员工</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap col-md-3">
                                <div class="zTreeDemoBackground left">
                                    <ul id="treeDemo" class="ztree"></ul>
                                </div>
                                <div class="people_list"></div>
                                <div class="group_list"></div>
                            </div>
                            <div class="col-md-5 addPeople" >
                                <input type="text" class="serach_people_key" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people">搜索</button>
                            </div>
                            <div class="col-md-3 department waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="left" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="btnB"></div>
                                <div class=" col-md-offset-5" style="display: block;margin-top: 30px;width: 40rem;">
                                    <button type="submit" class="confirm" class="close" data-dismiss="modal"
                                            aria-hidden="true">确认
                                    </button>
                                    <button class="cancel" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                            <div class=" col-md-2 moveBtn">
                                <button id="btnR">添加</button>
                                <button id="btnD">移除</button>
                            </div>
                            <div class="col-md-3 department ">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form1" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <select id="right" multiple="multiple">
                                            <c:forEach items="${securityAllRoleDTO.projectOfficerDep}" var="dept">
                                                <option class="htmlDepartment"
                                                        value="${dept.id}">${dept.name}</option>
                                            </c:forEach>
                                            <c:forEach items="${securityAllRoleDTO.projectOfficerStaff}" var="staff">
                                                <option class="htmlPerson"
                                                        value="${staff.id}">${staff.name}</option>
                                            </c:forEach>
                                        </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<!-- 模态框2（Modal）-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0">
                                <div class="typeList_department2" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees2">员工</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap2 col-md-3">
                                <div class="zTreeDemoBackground2 left">
                                    <ul id="treeDemo2" class="ztree"></ul>
                                </div>
                                <div class="people_list2"></div>
                                <div class="group_list2"></div>
                            </div>
                            <div class="col-md-5 addPeople2" style=" position: absolute;margin-left: 0;top: 6.3rem;left: 1.2rem;width: 30%;">
                                <input type="text" class="serach_people_key2" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people2">搜索</button>
                            </div>
                            <div class="col-md-3 department2 waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="left2" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="btnB"></div>
                                <div class=" col-md-offset-5" style="display: block;margin-top: 30px;width: 40rem;">
                                    <button type="submit" class="confirm2" class="close" data-dismiss="modal"
                                            aria-hidden="true">确认
                                    </button>
                                    <button class="cancel" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                            <div class=" col-md-2 moveBtn">
                                <button id="btnR2">添加</button>
                                <button id="btnD2">移除</button>
                            </div>
                            <div class="col-md-3 department2 ">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form2" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <select id="right2" multiple="multiple">
                                            <c:forEach items="${securityAllRoleDTO.partyASecurityOfficerDep}"
                                                       var="dept">
                                                <option class="htmlDepartment2"
                                                        value="${dept.id}">${dept.name}</option>
                                            </c:forEach>
                                            <c:forEach items="${securityAllRoleDTO.partyASecurityOfficerStaff}"
                                                       var="staff">
                                                <option class="htmlPerson2"
                                                        value="${staff.id}">${staff.name}</option>
                                            </c:forEach>
                                        </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<!-- 模态框3（Modal）-->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0">
                                <div class="typeList_department3" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees3">员工</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap3 col-md-3">
                                <div class="zTreeDemoBackground3 left">
                                    <ul id="treeDemo3" class="ztree"></ul>
                                </div>
                                <div class="people_list3"></div>
                                <div class="group_list3"></div>
                            </div>
                            <div class="col-md-5 addPeople3" style=" position: absolute;margin-left: 0;top: 6.3rem;left: 1.2rem;width: 30%;" >
                                <input type="text" class="serach_people_key3" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people3">搜索</button>
                            </div>
                            <div class="col-md-3 department3 waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="left3" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="btnB"></div>
                                <div class=" col-md-offset-5" style="display: block;margin-top: 30px;width: 40rem;">
                                    <button type="submit" class="confirm3" class="close" data-dismiss="modal"
                                            aria-hidden="true">确认
                                    </button>
                                    <button class="cancel" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                            <div class=" col-md-2 moveBtn">
                                <button id="btnR3">添加</button>
                                <button id="btnD3">移除</button>
                            </div>
                            <div class="col-md-3 department3 ">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form3" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right3" multiple="multiple">
                                                <c:forEach items="${securityAllRoleDTO.partyAEngineerDep}" var="dept">
                                                    <option class="htmlDepartment3"
                                                            value="${dept.id}">${dept.name}</option>
                                                </c:forEach>
                                                <br/>
                                                <c:forEach items="${securityAllRoleDTO.partyAEngineerStaff}"
                                                           var="staff">
                                                    <option class="htmlPerson3"
                                                            value="${staff.id}"> ${staff.name}</option>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<!-- 模态框4（Modal）-->
<div class="modal fade" id="myModal4" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0">
                                <div class="typeList_department4" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees4">员工</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap4 col-md-3">
                                <div class="zTreeDemoBackground4 left">
                                    <ul id="treeDemo4" class="ztree"></ul>
                                </div>
                                <div class="people_list4"></div>
                                <div class="group_list4"></div>
                            </div>
                            <div class="col-md-5 addPeople4"  style=" position: absolute;margin-left: 0;top: 6.3rem;left: 1.2rem;width: 30%;">
                                <input type="text" class="serach_people_key4" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people4">搜索</button>
                            </div>
                            <div class="col-md-3 department4 waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="left4" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="btnB"></div>
                                <div class=" col-md-offset-5" style="display: block;margin-top: 30px;width: 40rem;">
                                    <button type="submit" class="confirm4" class="close" data-dismiss="modal"
                                            aria-hidden="true">确认
                                    </button>
                                    <button class="cancel" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                            <div class=" col-md-2 moveBtn">
                                <button id="btnR4">添加</button>
                                <button id="btnD4">移除</button>
                            </div>
                            <div class="col-md-3 department4 ">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form4" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right4" multiple="multiple">
                                                <c:forEach items="${securityAllRoleDTO.contractorDep}" var="dept">
                                                    <option class="htmlDepartment4"
                                                            value="${dept.id}">${dept.name}</option>
                                                </c:forEach>
                                                <br/>
                                                <c:forEach items="${securityAllRoleDTO.contractorStaff}" var="staff">
                                                    <option class="htmlPerson4"
                                                            value="${staff.id}"> ${staff.name}</option>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<!-- 模态框5（Modal）-->
<div class="modal fade" id="myModal5" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0">
                                <div class="typeList_department5" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees5">员工</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap5 col-md-3">
                                <div class="zTreeDemoBackground5 left">
                                    <ul id="treeDemo5" class="ztree"></ul>
                                </div>
                                <div class="people_list5"></div>
                                <div class="group_list5"></div>
                            </div>
                            <div class="col-md-5 addPeople5" style=" position: absolute;margin-left: 0;top: 6.3rem;left: 1.2rem;width: 30%;">
                                <input type="text" class="serach_people_key5" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people5">搜索</button>
                            </div>
                            <div class="col-md-3 department5 waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="left5" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class="btnB"></div>
                                <div class=" col-md-offset-5" style="display: block;margin-top: 30px;width: 40rem;">
                                    <button type="submit" class="confirm5" class="close" data-dismiss="modal"
                                            aria-hidden="true">确认
                                    </button>
                                    <button class="cancel" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                            <div class=" col-md-2 moveBtn">
                                <button id="btnR5">添加</button>
                                <button id="btnD5">移除</button>
                            </div>
                            <div class="col-md-3 department5 ">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form5" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right5" multiple="multiple">
                                                <c:forEach items="${securityAllRoleDTO.supervisorDep}" var="dept">
                                                    <option class="htmlDepartment5"
                                                            value="${dept.id}">${dept.name}</option>
                                                </c:forEach>
                                                <c:forEach items="${securityAllRoleDTO.supervisortaff}" var="staff">
                                                    <option class="htmlPerson5"
                                                            value="${staff.id}">${staff.name}</option>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<script>
    //从后台拿到右边的数据
    var selectedData = [], selectedData2 = [], selectedData3 = [], selectedData4 = [], selectedData5 = [];
    var sview = [], aview = [], splum = [], aplum = [], spro = [], apro = [], smake = [], amake = [], sdisp = [],
        adisp = [];
    $("#right option.htmlDepartment").each(function (i) {
        if (this.value != "") {
            selectedData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            aview[i] = this.value;
        }
    });
    $("#right option.htmlPerson").each(function (i) {
        if (this.value != "") {
            selectedData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            sview[i] = this.value;
        }
    });
    $("#right2 option.htmlDepartment2").each(function (i) {
        if (this.value != "") {
            selectedData2.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            aplum[i] = this.value;
        }
    });
    $("#right2 option.htmlPerson2").each(function (i) {
        if (this.value != "") {
            selectedData2.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            splum[i] = this.value;
        }
    });
    $("#right3 option.htmlDepartment3").each(function (i) {
        if (this.value != "") {
            selectedData3.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            apro[i] = this.value;
        }
    });
    $("#right3 option.htmlPerson3").each(function (i) {
        if (this.value != "") {
            selectedData3.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            spro[i] = this.value;
        }
    });
    $("#right4 option.htmlDepartment4").each(function (i) {
        if (this.value != "") {
            selectedData4.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            amake[i] = this.value;
        }
    });
    $("#right4 option.htmlPerson4").each(function (i) {
        if (this.value != "") {
            selectedData4.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            smake[i] = this.value;
        }
    });
    $("#right5 option.htmlDepartment5").each(function (i) {
        if (this.value != "") {
            selectedData5.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            adisp[i] = this.value;
        }
    });
    $("#right5 option.htmlPerson5").each(function (i) {
        if (this.value != "") {
            selectedData5.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            sdisp[i] = this.value;
        }
    });
    arrData1select(selectedData, '#right');
    $('#viewStaffs').val(sview.join());
    $('#viewAgencys').val(aview.join());
    arrData2select(selectedData2, '#right2');
    $('#partyASecurityOfficerDep').val(aplum.join());
    $('#partyASecurityOfficerStaff').val(splum.join());
    arrData3select(selectedData3, '#right3');
    $('#partyAEngineerDep').val(apro.join());
    $('#partyAEngineerStaff').val(spro.join());
    arrData4select(selectedData4, '#right4');
    $('#contractorDep').val(amake.join());
    $('#contractorStaff').val(smake.join());
    arrData5select(selectedData5, '#right5');
    $('#supervisorDep').val(adisp.join());
    $('#supervisortaff').val(sdisp.join());
    // 点击部门
    var data2 = [];
    $('.typeList_department').bind('click', function () {
        $('.group_list').css('display', 'none');
        $('.zTreeDemoBackground').css('display', 'block');
        // alert(11);
        $('#treeDemo').css('display', 'block');
        $('.department').css('margin-top', '-2.95%');
        $('.addPeople').css('display', 'none');
        $('.moveBtn').css('margin-top', '3.1%');
        $('.content_wrap .resultPeople').css('display', 'none');
        $('.typeList_department').css({'background':'#0D165E','color':'#fff'});
        $('.typeList_employees').css({'background':'#fff','color':'#0D165E'});
        $('#left').html('');
    });
    $('.typeList_department2').bind('click', function () {
        $('.group_list2').css('display', 'none');
        $('.zTreeDemoBackground2').css({'display': 'block', 'border': '1px solid #ccc'});
        $('#treeDemo2').css('display', 'block');
        $('.department2').css('margin-top', '-2.95%');
        $('.addPeople2').css('display', 'none');
        $('.moveBtn2').css('margin-top', '3.1%');
        $('.content_wrap2 .resultPeople2').css('display', 'none');
        $('.typeList_department2').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees2').css({'background': '#fff', 'color': '#0D165E'});
        $('#left2').html('');
    });
    $('.typeList_department3').bind('click', function () {
        $('.group_list3').css('display', 'none');
        $('.zTreeDemoBackground3').css('display', 'block');
        $('#treeDemo3').css('display', 'block');
        $('.department3').css('margin-top', '-2.95%');
        $('.addPeople3').css('display', 'none');
        $('.moveBtn3').css('margin-top', '3.1%');
        $('.content_wrap3 .resultPeople3').css('display', 'none');
        $('.typeList_department3').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees3').css({'background': '#fff', 'color': '#0D165E'});
        $('#left3').html('');
    });
    $('.typeList_department4').bind('click', function () {
        $('.group_list4').css('display', 'none');
        $('.zTreeDemoBackground4').css('display', 'block');
        $('#treeDemo4').css('display', 'block');
        $('.department4').css('margin-top', '-2.95%');
        $('.addPeople4').css('display', 'none');
        $('.moveBtn4').css('margin-top', '3.1%');
        $('.content_wrap4 .resultPeople4').css('display', 'none');
        $('.typeList_department4').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees4').css({'background': '#fff', 'color': '#0D165E'});
        $('#left4').html('');
    });
    $('.typeList_department5').bind('click', function () {
        $('.group_list5').css('display', 'none');
        $('.zTreeDemoBackground5').css({'display': 'block', 'border': '1px solid #ccc'});
        $('#treeDemo5').css('display', 'block');
        $('.department5').css('margin-top', '-2.95%');
        $('.addPeople5').css('display', 'none');
        $('.moveBtn5').css({'margin-top': '3.1%', "margin-left": "20%"});
        $('.content_wrap5 .resultPeople5').css({"display": "none", "border": "none"});
        $('.typeList_department5').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees5').css({'background': '#fff', 'color': '#0D165E'});
        $('#left5').html('');
    });
    //点击人员
    $('.typeList_employees').bind('click', function () {
        $('.group_list').css('display', 'none');
        $('.addPeople').css('display', 'block');
        $('.department').css('margin-top', ' -2.95%');
        // $('.waitSelect').css('margin-left','4%');
        $('.zTreeDemoBackground').css('display', 'none');
        $('.moveBtn').css('margin-top', '-1.54%');
        $('.typeList_employees').css({'background':'#0D165E','color':'#fff'});
        $('.typeList_department').css({'background':'#fff','color':'#0D165E'});
        $('#left').html('');
    });
    $('.typeList_employees2').bind('click', function () {
        $('.group_list2').css('display', 'none');
        $('.addPeople2').css('display', 'block');
        $('.department2').css('margin-top', ' -2.95%');
        $('.zTreeDemoBackground2').css({'display': 'none', 'border': '1px solid #ccc'});
        $('.moveBtn2').css('margin-top', '-1.54%');
        $('.typeList_employees2').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department2').css({'background': '#fff', 'color': '#0D165E'});
        $('#left2').html('');
    });
    $('.typeList_employees3').bind('click', function () {
        $('.group_list3').css('display', 'none');
        $('.addPeople3').css('display', 'block');
        $('.department3').css('margin-top', ' -2.95%');
        $('.zTreeDemoBackground3').css('display', 'none');
        $('.moveBtn3').css('margin-top', '-1.54%');
        $('.typeList_employees3').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department3').css({'background': '#fff', 'color': '#0D165E'});
        $('#left3').html('');
    });
    $('.typeList_employees4').bind('click', function () {
        $('.group_list4').css('display', 'none');
        $('.addPeople4').css('display', 'block');
        $('.department4').css('margin-top', ' -2.95%');
        $('.zTreeDemoBackground4').css('display', 'none');
        $('.moveBtn4').css('margin-top', '-1.54%');
        $('.typeList_employees4').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department4').css({'background': '#fff', 'color': '#0D165E'});
        $('#left4').html('');
    });
    $('.typeList_employees5').bind('click', function () {
        $('.group_list5').css('display', 'none');
        $('.addPeople5').css('display', 'block');
        $('.department5').css('margin-top', ' -2.95%');
        $('.zTreeDemoBackground5').css({'display': 'none', 'border': '1px solid #ccc'});
        $('.moveBtn5').css('margin-top', '-1.54%');
        $('.typeList_employees5').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department5').css({'background': '#fff', 'color': '#0D165E'});
        $('#left5').html('');
    });

    function arr2Html(arr) {
        var html = '';
        for (var i = 0; i < arr.length; i++) {
            html = html + '<li data-id="' + arr[i].id + '">' + arr[i].name + '</li>';
        }
        return html;
    }
    // 查询
    $('.serach_people').bind('click', function () {
        var serachPeople = $('.serach_people_key').val();
        $.ajax({
            url: '/user/searchStaff',
            data: {
                staffName: serachPeople
            },
            type: 'get',
            dataType: 'json',
            success: function (result) {
                if (result.code != 0) {
                    alert(result.msg)
                } else {
                    var data = result.data.map(function (val) {
                        return selectFilter(val);
                    })
                    console.log(data);
                    arrData1select(data, '#left');
                }
            }
        });

    });
    // 查询2
    $('.serach_people2').bind('click', function () {
        var serachPeople = $('.serach_people_key2').val();
        $.ajax({
            url: '/user/searchStaff',
            data: {
                staffName: serachPeople
            },
            type: 'get',
            dataType: 'json',
            success: function (result) {
                if (result.code != 0) {
                    alert(result.msg)
                } else {
                    var data = result.data.map(function (val) {
                        return selectFilter(val);
                    })
                    arrData2select(data, '#left2');
                }
            }
        });

    });

    // 查询
    $('.serach_people3').bind('click', function () {
        var serachPeople = $('.serach_people_key3').val();
        $.ajax({
            url: '/user/searchStaff',
            data: {
                staffName: serachPeople
            },
            type: 'get',
            dataType: 'json',
            success: function (result) {
                if (result.code != 0) {
                    alert(result.msg)
                } else {
                    var data = result.data.map(function (val) {
                        return selectFilter(val);
                    })
                    arrData3select(data, '#left3');
                }
            }
        });

    });

    // 查询4
    $('.serach_people4').bind('click', function () {
        var serachPeople = $('.serach_people_key4').val();
        $.ajax({
            url: '/user/searchStaff',
            data: {
                staffName: serachPeople
            },
            type: 'get',
            dataType: 'json',
            success: function (result) {
                if (result.code != 0) {
                    alert(result.msg)
                } else {
                    var data = result.data.map(function (val) {
                        return selectFilter(val);
                    })
                    arrData4select(data, '#left4');
                }
            }
        });
    });

    // 查询5
    $('.serach_people5').bind('click', function () {
        var serachPeople = $('.serach_people_key5').val();
        $.ajax({
            url: '/user/searchStaff',
            data: {
                staffName: serachPeople
            },
            type: 'get',
            dataType: 'json',
            success: function (result) {
                if (result.code != 0) {
                    alert(result.msg)
                } else {
                    var data = result.data.map(function (val) {
                        return selectFilter(val);
                    })
                    arrData5select(data, '#left5');
                }
            }
        });
    });
    function selectFilter(obj) {
        return {
            agencyType: "1",
            icon: null,
            id: obj.staffId,
            isParent: false,
            name: obj.staffName,
            pId: null,
            type: "3"
        }
    }

    var selectedList = []
    var waitLists = {};
    var waitLists2 = {};
    var waitLists3 = {};
    var waitLists4 = {};
    var waitLists5 = {};
    var setting = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter,
        },
        callback: {
            onClick: onClick
        }
    };
    var setting2 = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter2,
        },
        callback: {
            onClick: onClick2
        }
    };
    var setting3 = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter3,
        },
        callback: {
            onClick: onClick3
        }
    };
    var setting4 = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter4,
        },
        callback: {
            onClick: onClick4
        }
    };
    var setting5 = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter5,
        },
        callback: {
            onClick: onClick5
        }
    };
    function strToJson(str) {
        var json = (new Function("return " + str))();
        return json;
    }
    function onClick(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left'
        //是最后一个  找后台要下一级
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrData1select(childrenArr, leftId);
        })
    }
    function onClick2(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left2'
        //是最后一个  找后台要下一级
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrData2select(childrenArr, leftId);
        })
    }
    function onClick3(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left3'
        //是最后一个  找后台要下一级
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrData3select(childrenArr, leftId);
        })
    }
    function onClick4(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left4'
        //是最后一个  找后台要下一级
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrData4select(childrenArr, leftId);
        })
    }
    function onClick5(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left5'
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrData5select(childrenArr, leftId);
        })
    }
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) {
            return null;
        }
        var arr = [];
        for (var i = 0, h = childNodes.length; i < h; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            //arr.push  下一级的 name  id
            arr.push({
                name: childNodes[i].name,
                id: childNodes[i].id,
                isParent: childNodes[i].isParent,
                type: childNodes[i].type
            })
        }
        if (parentNode) {
            waitLists['id' + parentNode.id] = arr;
        } else {
            waitLists['idRoot'] = arr;
        }
        return childNodes;
    }
    function filter2(treeId, parentNode, childNodes) {
        if (!childNodes) {
            return null;
        }
        var arr = [];
        for (var i = 0, h = childNodes.length; i < h; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            //arr.push  下一级的 name  id
            arr.push({
                name: childNodes[i].name,
                id: childNodes[i].id,
                isParent: childNodes[i].isParent,
                type: childNodes[i].type
            })
        }
        if (parentNode) {
            waitLists2['id' + parentNode.id] = arr;
        } else {
            waitLists2['idRoot'] = arr;
        }
        return childNodes;
    }
    function filter3(treeId, parentNode, childNodes) {
        if (!childNodes) {
            return null;
        }
        var arr = [];
        for (var i = 0, h = childNodes.length; i < h; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            //arr.push  下一级的 name  id
            arr.push({
                name: childNodes[i].name,
                id: childNodes[i].id,
                isParent: childNodes[i].isParent,
                type: childNodes[i].type
            })
        }
        if (parentNode) {
            waitLists3['id' + parentNode.id] = arr;
        } else {
            waitLists3['idRoot'] = arr;
        }
        return childNodes;
    }
    function filter4(treeId, parentNode, childNodes) {
        if (!childNodes) {
            return null;
        }
        var arr = [];
        for (var i = 0, h = childNodes.length; i < h; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            //arr.push  下一级的 name  id
            arr.push({
                name: childNodes[i].name,
                id: childNodes[i].id,
                isParent: childNodes[i].isParent,
                type: childNodes[i].type
            })
        }
        if (parentNode) {
            waitLists4['id' + parentNode.id] = arr;
        } else {
            waitLists4['idRoot'] = arr;
        }
        return childNodes;
    }
    function filter5(treeId, parentNode, childNodes) {
        if (!childNodes) {
            return null;
        }
        var arr = [];
        for (var i = 0, h = childNodes.length; i < h; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            //arr.push  下一级的 name  id
            arr.push({
                name: childNodes[i].name,
                id: childNodes[i].id,
                isParent: childNodes[i].isParent,
                type: childNodes[i].type
            })
        }
        if (parentNode) {
            waitLists5['id' + parentNode.id] = arr;
        } else {
            waitLists5['idRoot'] = arr;
        }
        return childNodes;
    }
    //方法
    function arrData1select(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right' && arr != selectedData) {
            arr = selectedData.concat(arr)
            selectedData = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left') {
                continue;
            }
            ;
            if (arr[i].type == '1') {
                htmlDepartmentNum++;
                htmlDepartment = htmlDepartment + '<option class="htmlDepartment" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
            } else if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        $(id).html('');
        $(id).append(htmlPerson).append(htmlDepartment);
    }

    //方法
    function arrData2select(arr, id) {
//        console.log(arr)
        //arrData2select(arr,'#right')
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right2' && arr != selectedData2) {
            arr = selectedData2.concat(arr)
            selectedData2 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson2">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment2">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData2.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left2') {
                continue;
            }
            ;
            if (arr[i].type == '1') {
                htmlDepartmentNum++;
                htmlDepartment = htmlDepartment + '<option class="htmlDepartment" data-id="' + arr[i].id + '">' + arr[i].name + '</option>';
            } else if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
            }

        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        $(id).html('');
        $(id).append(htmlPerson).append(htmlDepartment);
    }
    //方法
    function arrData3select(arr, id) {
        console.log(arr)
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right3' && arr != selectedData3) {
            arr = selectedData3.concat(arr)
            selectedData3 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson3" ">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment3" ">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData3.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left3') {
                continue;
            }
            ;
            if (arr[i].type == '1') {
                htmlDepartmentNum++;
                htmlDepartment = htmlDepartment + '<option class="htmlDepartment" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>';
            } else if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>'
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        $(id).html('');
        $(id).append(htmlPerson).append(htmlDepartment);
    }

    //方法
    function arrData4select(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right4' && arr != selectedData4) {
            arr = selectedData4.concat(arr)
            selectedData4 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson4" ">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment4" ">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData4.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left4') {
                continue;
            }
            ;
            if (arr[i].type == '1') {
                htmlDepartmentNum++;
                htmlDepartment = htmlDepartment + '<option class="htmlDepartment" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>';
            } else if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>'
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        $(id).html('');
        $(id).append(htmlDepartment).append(htmlPerson);
    }

    function arrData5select(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right5' && arr != selectedData5) {
            arr = selectedData5.concat(arr)
            selectedData5 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson5" ">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment5" ">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData5.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left5') {
                continue;
            }
            ;
            if (arr[i].type == '1') {
                htmlDepartmentNum++;
                htmlDepartment = htmlDepartment + '<option class="htmlDepartment" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>';
            } else if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>'
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        $(id).html('');
        $(id).append(htmlPerson).append(htmlDepartment);
    }

    function select() {
        $('#btnR').bind('click', function () {
            wait1selected();
        });
        $('#btnD').bind('click', function () {
            selected1wait();
        });
        $('#btnR2').bind('click', function () {
            wait2selected();
        });
        $('#btnD2').bind('click', function () {
            selected2wait();
        });
        $('#btnR3').bind('click', function () {
            wait3selected();
        });
        $('#btnD3').bind('click', function () {
            selected3wait();
        });
        $('#btnR4').bind('click', function () {
            wait4selected();
        });
        $('#btnD4').bind('click', function () {
            selected4wait();
        });
        $('#btnR5').bind('click', function () {
            wait5selected();
        });
        $('#btnD5').bind('click', function () {
            selected5wait();
        });
    }
    //右移方法1
    function wait1selected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') {
                className = '3';
            }
            //将数组中optionArr存放的数据全部遍历出来 添加到另一个空数组中arr

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}

        }
        ;
        //移除之前选中的那个数组
        optionArr.remove();
        arrData1select(arr, '#right');
    }
    //左移方法1
    function selected1wait() {
        var optionArr = $('#right option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}
        }
        ;
        optionArr.remove();
        // selectedData
        selectedData = arrSubtraction(selectedData, arr)
        $('#right #htmlDepartment')[0].label = "部门(" + $('#right').find('.htmlDepartment').length + ")";
        $('#right #htmlPerson')[0].label = "员工(" + $('#right').find('.htmlPerson').length + ")";
        var optionArrLeft = $('#left option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = '1';
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';

            var id = '';
            if (optionArrLeft[i].dataset) {
                id = optionArrLeft[i].dataset.id;
            } else {
                id =optionArrLeft[i].attributes[1].value;
            }
            arrLeft[i] = {name: optionArrLeft[i].value, id: id, type: className}
        }
        ;
        arr = arrLeft.concat(arr)
        arrData1select(arr, '#left')
    }
    //右移方法2
    function wait2selected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left2 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') {
                className = '3';
            }
            if (optionArr[i].className == 'htmlGroup') {
                className = '2';
            }
            //将数组中optionArr存放的数据全部遍历出来 添加到另一个空数组中arr

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}

        }
        //移除之前选中的那个数组
        optionArr.remove();
        arrData2select(arr, '#right2');
    }
    //左移方法2
    function selected2wait() {
        var optionArr = $('#right2 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}
        }
        ;
        optionArr.remove();
        // selectedData
        selectedData2 = arrSubtraction(selectedData2, arr);
        $('#right2 #htmlPerson2')[0].label = "员工(" + $('#right2').find('.htmlPerson').length + ")";
        $('#right2 #htmlDepartment2')[0].label = "部门(" + $('#right2').find('.htmlDepartment').length + ")";
        var optionArrLeft = $('#left2 option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = '1';
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';

            var id = '';
            if (optionArrLeft[i].dataset) {
                id = optionArrLeft[i].dataset.id;
            } else {
                id =optionArrLeft[i].attributes[1].value;
            }
            arrLeft[i] = {name: optionArrLeft[i].value, id:id, type: className}
        }
        arr = arrLeft.concat(arr);
        arrData2select(arr, '#left2');
    }
    //右移方法3
    function wait3selected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left3 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') {
                className = '3';
            }
            if (optionArr[i].className == 'htmlGroup') {
                className = '2';
            }
            //将数组中optionArr存放的数据全部遍历出来 添加到另一个空数组中arr

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}

        }
        ;
        //移除之前选中的那个数组
        optionArr.remove();
        arrData3select(arr, '#right3');
    }
    //左移方法3
    function selected3wait() {
        var optionArr = $('#right3 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}
        }
        ;

        optionArr.remove();
        // selectedData
        selectedData3 = arrSubtraction(selectedData3, arr)
        $('#right3 #htmlPerson3')[0].label = "员工(" + $('#right3').find('.htmlPerson').length + ")";
        $('#right3 #htmlDepartment3')[0].label = "部门(" + $('#right3').find('.htmlDepartment').length + ")";
        var optionArrLeft = $('#left3 option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = '1';
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';

            var id = '';
            if (optionArrLeft[i].dataset) {
                id = optionArrLeft[i].dataset.id;
            } else {
                id =optionArrLeft[i].attributes[1].value;
            }
            arrLeft[i] = {name: optionArrLeft[i].value, id: id, type: className}
        }
        ;
        arr = arrLeft.concat(arr)
        arrData3select(arr, '#left3')
    }
    //右移方法4
    function wait4selected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left4 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') {
                className = '3';
            }
            //将数组中optionArr存放的数据全部遍历出来 添加到另一个空数组中arr

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}

        }
        ;
        //移除之前选中的那个数组
        optionArr.remove();
        arrData4select(arr, '#right4');
    }
    //左移方法4
    function selected4wait() {
        var optionArr = $('#right4 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}
        }
        ;

        optionArr.remove();
        // selectedData
        selectedData4 = arrSubtraction(selectedData4, arr)
        $('#right4 #htmlPerson4')[0].label = "员工(" + $('#right4').find('.htmlPerson').length + ")";
        $('#right4 #htmlDepartment4')[0].label = "部门(" + $('#right4').find('.htmlDepartment').length + ")";
        var optionArrLeft = $('#left4 option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = 1;
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';

            var id = '';
            if (optionArrLeft[i].dataset) {
                id = optionArrLeft[i].dataset.id;
            } else {
                id =optionArrLeft[i].attributes[1].value;
            }
            arrLeft[i] = {name: optionArrLeft[i].value, id: id, typet: className}
        }
        ;
        arr = arrLeft.concat(arr)
        arrData4select(arr, '#left4')
    }
    //右移方法5
    function wait5selected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left5 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') {
                className = '3';
            }
            //将数组中optionArr存放的数据全部遍历出来 添加到另一个空数组中arr

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}

        }
        ;
        //移除之前选中的那个数组
        optionArr.remove();
        arrData5select(arr, '#right5');
    }
    //左移方法5
    function selected5wait() {
        var optionArr = $('#right5 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}
        }
        ;

        optionArr.remove();
        // selectedData
        selectedData5 = arrSubtraction(selectedData5, arr)
        $('#right5 #htmlPerson5')[0].label = "员工(" + $('#right5').find('.htmlPerson').length + ")";
        $('#right5 #htmlDepartment5')[0].label = "部门(" + $('#right5').find('.htmlDepartment').length + ")";
        var optionArrLeft = $('#left5 option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = '1';
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';

            var id = '';
            if (optionArrLeft[i].dataset) {
                id = optionArrLeft[i].dataset.id;
            } else {
                id =optionArrLeft[i].attributes[1].value;
            }
            arrLeft[i] = {name: optionArrLeft[i].value, id: id, type: className}
        }
        ;
        arr = arrLeft.concat(arr)
        arrData5select(arr, '#left5')
    }

    select();
    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting);
        $.fn.zTree.init($("#treeDemo2"), setting2);
        $.fn.zTree.init($("#treeDemo3"), setting3);
        $.fn.zTree.init($("#treeDemo4"), setting4);
        $.fn.zTree.init($("#treeDemo5"), setting5);
    });
    function arrSubtraction(arr1, arr2) {
        var temp = [];
        var num = 0;
        for (var i = 0; i < arr1.length; i++) {
            var isContains = contains(arr2.map(function (res) {
                return res.id;
            }), arr1[i].id)
            //作为对比 如果不含有就把它 赋给ta
            if (!isContains) {
                temp[num] = arr1[i];
                num++;
            }
        }
        ;
        return temp;
    }
    //arr, obj看数组中是否含有对象obj可以理解成去重
    function contains(arr, obj) {
        var i = arr.length;
        while (i--) {
            if (arr[i] === obj) {
                return true;
            }
        }
        return false;
    }
    ;

    // 提交选择1
    $('.confirm').bind('click', function () {
        var liShow = $('#right').html();
        $('.selectResult').html(liShow);
        $('.selectResult').find('option').addClass('floatSelectResult');
        $('#htmlPerson').css('display', 'none');
        $('#htmlDepartment').css('display', 'none');
        var tstaff = [], tagency = [];
        var i = k = 0;
        $("#right option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#viewStaffs').val(tstaff.join());
        $('#viewAgencys').val(tagency.join());
    });
    // 提交选择2
    $('.confirm2').bind('click', function () {
        var liShow = $('#right2').html();
        $('.selectResult2').html(liShow);
        $('.selectResult2').find('option').addClass('floatSelectResult');
        $('#htmlPerson2').css('display', 'none');
        $('#htmlDepartment2').css('display', 'none');
        var tstaff = [], tagency = [];
        var i = k = 0;
        $("#right2 option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#partyASecurityOfficerStaff').val(tstaff.join());
        $('#partyASecurityOfficerDep').val(tagency.join());
    });
    // 提交选择3
    $('.confirm3').bind('click', function () {
        var liShow = $('#right3').html();
        $('.selectResult3').html(liShow);
        $('.selectResult3').find('option').addClass('floatSelectResult');
        $('#htmlPerson3').css('display', 'none');
        $('#htmlDepartment3').css('display', 'none');
        var tstaff = [], tagency = [];
        var i = k = 0;
        $("#right3 option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#partyAEngineerStaff').val(tstaff.join());
        $('#partyAEngineerDep').val(tagency.join());
    });
    // 提交选择4
    $('.confirm4').bind('click', function () {
        var liShow = $('#right4').html();
        $('.selectResult4').html(liShow);
        $('.selectResult4').find('option').addClass('floatSelectResult');
        $('#htmlPerson4').css('display', 'none');
        $('#htmlDepartment4').css('display', 'none');
        var tstaff = [], tagency = [];
        var i = k = 0;
        $("#right4 option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#contractorStaff').val(tstaff.join());
        $('#contractorDep').val(tagency.join());
    });
    // 提交选择5
    $('.confirm5').bind('click', function () {
        var liShow = $('#right5').html();
        $('.selectResult5').html(liShow);
        $('.selectResult5').find('option').addClass('floatSelectResult');
        $('#htmlPerson5').css('display', 'none');
        $('#htmlDepartment5').css('display', 'none');
        var tstaff = [], tagency = [];
        var i = k = 0;
        $("#right5 option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#supervisortaff').val(tstaff.join());
        $('#supervisorDep').val(tagency.join());
    });
</script>
</body>
</html>