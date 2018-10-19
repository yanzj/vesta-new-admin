<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/1/28
  Time: 10:29
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
            if ($('#tabshow').val() == 1) {
                $("#003100030000").addClass("active");
                $("#003100030000").parent().parent().addClass("in");
                $("#003100030000").parent().parent().parent().parent().addClass("active");
            } else {
                $("#003100010000").addClass("active");
                $("#003100010000").parent().parent().addClass("in");
                $("#003100010000").parent().parent().parent().parent().addClass("active");
            }
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
    <input type="hidden" id="tabshow" value="${flag}">
    <c:if test="${flag eq 2}">
        <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                     crunMenu="003100010000" username="${authPropertystaff.staffName}"/>
    </c:if>
    <c:if test="${flag eq 1}">
        <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                     crunMenu="003100030000" username="${authPropertystaff.staffName}"/>
    </c:if>
    <div class="container1 userStaffManage">
        <form class="form-horizontal" name="projectInfo" id="projectInfo" action="../BaseData/saveProjectRole.html">
            <div class="row">
                <div class="col-md-10 " style=" border-bottom: 1px dashed #ccc;">
                    <span style="line-height: 30px;font-size: 15px;">项目编辑</span>

                    <div class="newRoleSubmit">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
                        <%--<c:if test="${flag eq 1}"><a href="../BaseData/projectRoleManage.html" class="btn btn-primary"--%>
                        <%--for="">关闭</a></c:if>--%>
                        <%--<c:if test="${flag eq 2}">--%>
                        <%----%>
                        <%--<button onclick="history.go(-1)" class="btn btn-primary" for="">关闭</button>--%>
                        <%--</c:if>--%>
                    </div>
                </div>
            </div>
            <div style="line-height: 30px;margin-top: 15px;">
                <span class="role_table_roleName">项目名称：</span>
                <span class="role_table_fillCont">
                <input type="text" class="roleNameText" name="projectName" autofocus="autofocus"
                       value="${projectProject.projectName}"
                       <c:if test="${flag eq '2'}">readonly="readonly"</c:if>>
                </span>
            </div>
            <div class="row">
                <div class="col-md-10 role_new_submit2">
                    <table class="table table-bordered">

                        <input type="hidden" name="projectId" value="${projectProject.projectId}">
                        <input type="hidden" name="flag" value="${flag}">
                        <tbody>
                        <%--<tr>--%>
                        <%--<td class="role_table_roleName">项目名称</td>--%>
                        <%--<td class="role_table_fillCont">--%>
                        <%--<input type="text" class="roleNameText" name="projectName"--%>
                        <%--value="${projectProject.projectName}"--%>
                        <%--<c:if test="${flag eq '2'}">readonly="readonly"</c:if>>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <tr style="background-color: #003366;color: #fff;">
                            <td colspan="2">质量管理功能模块相关信息</td>
                        </tr>
                        <c:if test="${flag eq 2}">
                            <tr>
                                <td class="role_table_roleName"><span>甲方权限</span></td>
                                <td>
                                    <input type="hidden" name="ownerDept" id="viewAgencys">
                                    <input type="hidden" name="ownerStaff" id="viewStaffs">
                                    <input type="hidden" name="" id="viewOrganizes">
                                    <!-- <span class="existence" ></span> -->
                                    <div class="existence">
                                          <span style="display:inline-block" class="selectResult" id="role_linkRole">
                                            <c:if test="${(projectProject.ownerDepts) != null && fn:length(projectProject.ownerDepts)>0}">
                                                <c:forEach items="${projectProject.ownerDepts}" var="dept">
                                                    ${dept.pRoleName}；
                                                </c:forEach>
                                                <br/>
                                            </c:if>
                                              <c:if test="${projectProject.ownerStaffs != null && fn:length(projectProject.ownerStaffs)>0}">
                                                  <c:forEach items="${projectProject.ownerStaffs}" var="staff">
                                                      ${staff.pRoleName}；
                                                  </c:forEach>
                                              </c:if>
                                          </span>
                                    </div>
                                    <span class="role_select" data-toggle="modal" data-target="#myModal1">选择</span>
                                </td>
                            </tr>
                            <%--Jason新加的--%>
                            <tr>
                                <td class="role_table_roleName"><span>领导权限</span></td>
                                <td>
                                    <input type="hidden" name="leaderDept" id="leaderAgencys">
                                    <input type="hidden" name="leaderStaff" id="leaderStaffs">
                                    <!-- <span class="existence" ></span> -->
                                    <div class="existence">
                                          <span style="display:inline-block" class="selectResultLeader"
                                                id="role_leaderRole">
                                              <c:if test="${(projectProject.leaderDepts) != null && fn:length(projectProject.leaderDepts)>0}">
                                                  <c:forEach items="${projectProject.leaderDepts}" var="dept">
                                                      ${dept.pRoleName}；
                                                  </c:forEach>
                                                  <br/>
                                              </c:if>
                                              <c:if test="${projectProject.leaderStaffs != null && fn:length(projectProject.leaderStaffs)>0}">
                                                  <c:forEach items="${projectProject.leaderStaffs}" var="staff">
                                                      ${staff.pRoleName}；
                                                  </c:forEach>
                                              </c:if>
                                          </span>
                                    </div>
                                    <span class="role_select" data-toggle="modal" data-target="#leaderModal">选择</span>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${flag eq 1}">
                            <tr>
                                <td class="role_table_roleName"><span>项目系统管理员</span></td>
                                <td>
                                    <input type="hidden" name="viewDept" id="dispatchAgencys">
                                    <input type="hidden" name="viewStaff" id="dispatchStaffs">
                                    <input type="hidden" name="" id="dispatchOrganizes">
                                    <!-- <span class="existence" ></span> -->
                                    <div class="existence">
                                      <span style="display:inline-block" class="selectResult5" id="role_linkDispatch">
                                          <c:if test="${projectProject.viewDepts != null && fn:length(projectProject.viewDepts)>0}">
                                              <c:forEach items="${projectProject.viewDepts}" var="dept">
                                                  ${dept.pRoleName}；
                                              </c:forEach>
                                              <br/>
                                          </c:if>
                                          <c:if test="${projectProject.viewStaffs != null && fn:length(projectProject.viewStaffs)>0}">
                                              <c:forEach items="${projectProject.viewStaffs}" var="staff">
                                                  ${staff.pRoleName}；
                                              </c:forEach>
                                          </c:if>
                                      </span>
                                    </div>
                                    <span class="role_select" data-toggle="modal" data-target="#myModal5">选择</span>
                                </td>
                            </tr>
                            <tr>
                                <td class="role_table_roleName"><span>工程经理</span></td>
                                <td>
                                    <input type="hidden" name="surveyorDept" id="plumbingAgency">
                                    <input type="hidden" name="surveyorStaff" id="plumbingStaffs">
                                    <input type="hidden" name="" id="plumbingOrganizes">
                                    <!-- <span class="existence" ></span> -->
                                    <div class="existence">
                                          <span style="display:inline-block" class="selectResult2" id="role_linkPlumb">
                                              <c:if test="${projectProject.surveyorDepts != null && fn:length(projectProject.surveyorDepts)>0}">
                                                  <c:forEach items="${projectProject.surveyorDepts}" var="dept">
                                                      ${dept.pRoleName}；
                                                  </c:forEach>
                                                  <br/>
                                              </c:if>
                                              <c:if test="${projectProject.surveyorStaffs != null && fn:length(projectProject.surveyorStaffs)>0}">
                                                  <c:forEach items="${projectProject.surveyorStaffs}" var="staff">
                                                      ${staff.pRoleName}；
                                                  </c:forEach>
                                              </c:if>
                                          </span>
                                    </div>
                                    <span class="role_select" data-toggle="modal" data-target="#myModal2">选择</span>
                                </td>
                            </tr>
                        </c:if>

                        <%--<tr>--%>
                        <%--<td class="role_table_roleName"><span>关单权限</span></td>--%>
                        <%--<td>--%>
                        <%--<input type="hidden" name="closeDept" id="propertyAgency">--%>
                        <%--<input type="hidden" name="closeStaff" id="propertyStaffs">--%>
                        <%--<input type="hidden" name="" id="propertyOrganizes">--%>
                        <%--<!-- <span class="existence" ></span> -->--%>
                        <%--<div class="existence">--%>
                        <%--<span style="display:inline-block" class="selectResult3" id="role_linkProperty">--%>
                        <%--<c:forEach items="${projectProject.closeDepts}" var="dept">--%>
                        <%--${dept.pRoleName}；--%>
                        <%--</c:forEach>--%>
                        <%--<br/>--%>
                        <%--<c:forEach items="${projectProject.closeStaffs}" var="staff">--%>
                        <%--${staff.pRoleName}；--%>
                        <%--</c:forEach>--%>
                        <%--</span>--%>
                        <%--</div>--%>
                        <%--<span class="role_select" data-toggle="modal" data-target="#myModal3" >选择</span>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                        <%--<td class="role_table_roleName"><span>开发接单</span></td>--%>
                        <%--<td>--%>
                        <%--<input type="hidden" name="makesAgencys" id="makesAgencys">--%>
                        <%--<input type="hidden" name="makesStaffs" id="makesStaffs">--%>
                        <%--<input type="hidden" name="makesOrganizes" id="makesOrganizes">--%>
                        <%--<!-- <span class="existence" ></span> -->--%>
                        <%--<div class="existence">--%>
                        <%--<span style="display:inline-block" class="selectResult4" id="role_linkMake">--%>
                        <%--<c:forEach items="${makes}" var="make">--%>
                        <%--${make.agencyName};--%>
                        <%--</c:forEach>--%>
                        <%--</span>--%>
                        <%--</div>--%>
                        <%--<span class="role_select" data-toggle="modal" data-target="#myModal4" >选择</span>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                        <%--<td class="role_table_roleName"><span>关单权限</span></td>--%>
                        <%--<td>--%>
                        <%--<input type="hidden" name="closeAgencys" id="closeAgencys">--%>
                        <%--<input type="hidden" name="closeStaffs" id="closeStaffs">--%>
                        <%--<input type="hidden" name="closeOrganizes" id="closeOrganizes">--%>
                        <%--<!-- <span class="existence" ></span> -->--%>
                        <%--<div class="existence">--%>
                        <%--<span style="display:inline-block" class="selectResult6" id="role_linkClose">--%>
                        <%--<c:forEach items="${closeBills}" var="bills">--%>
                        <%--${bills.agencyName};--%>
                        <%--</c:forEach>--%>
                        <%--</span>--%>
                        <%--</div>--%>
                        <%--<span class="role_select" data-toggle="modal" data-target="#myModal6" >选择</span>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                        <%--<td class="role_table_roleName"><span>验房工程师</span></td>--%>
                        <%--<td>--%>
                        <%--<input type="hidden" name="checkAgencys" id="checkAgencys">--%>
                        <%--<input type="hidden" name="checkStaffs" id="checkStaffs">--%>
                        <%--<input type="hidden" name="checkOrganizes" id="checkOrganizes">--%>
                        <%--<!-- <span class="existence" ></span> -->--%>
                        <%--<div class="existence">--%>
                        <%--<span style="display:inline-block" class="selectResult7" id="role_linkCheck">--%>
                        <%--<c:forEach items="${checkInspect}" var="inspect">--%>
                        <%--${inspect.agencyName};--%>
                        <%--</c:forEach>--%>
                        <%--</span>--%>
                        <%--</div>--%>
                        <%--<span class="role_select" data-toggle="modal" data-target="#myModal7" >选择</span>--%>
                        <%--</td>--%>
                        <%--</tr>--%>
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
                                <div class="typeList_department">部门</div>
                                <div class="typeList_employees">员工</div>
                                <%--<div class="typeList_group">群组</div>--%>
                            </div>
                            <div class="col-md-5 addPeople">
                                <input type="text" class="serach_people_key" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people">搜索</button>
                            </div>
                        </div>
                        <!-- <div class="row"> -->
                        <!-- <div class="col-md-5 addPeople">
             <input type="text"  class="serach_people_key"placeholder="关键字添加员工" value="">
             <button type="submit" class="serach_people">搜索</button>
         </div> -->
                        <!--  </div> -->
                        <div class="row ">
                            <div class="content_wrap col-md-3">
                                <div class="zTreeDemoBackground left">
                                    <ul id="treeDemo" class="ztree"></ul>
                                </div>
                                <div class="people_list"></div>
                                <div class="group_list"></div>
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
                                            <c:forEach items="${projectProject.ownerDepts}" var="dept">
                                                <option class="htmlDepartment"
                                                        value="${dept.pRoleId}">${dept.pRoleName}</option>
                                            </c:forEach>
                                            <c:forEach items="${projectProject.ownerStaffs}" var="staff">
                                                <option class="htmlPerson"
                                                        value="${staff.pRoleId}">${staff.pRoleName}</option>
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
                                <%--<div class="typeList_group2">群组</div>--%>
                            </div>

                        </div>
                        <!-- <div class="row"> -->
                        <!-- <div class="col-md-5 addPeople">
             <input type="text"  class="serach_people_key"placeholder="关键字添加员工" value="">
             <button type="submit" class="serach_people">搜索</button>
         </div> -->
                        <!--  </div> -->
                        <div class="row ">
                            <div class="content_wrap2 col-md-4">
                                <div class="zTreeDemoBackground2 left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo2" class="ztree"></ul>
                                </div>
                                <div class="people_list2"></div>
                                <div class="group_list2"></div>
                            </div>
                            <div class="col-md-5 addPeople2">
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

                                <div class=" col-md-9 2" style="margin-left: 20%;">
                                    <button id="btnR2" style="background: #0D165E">添加></button>
                                </div>
                            </div>

                            <div class="col-md-3 department2 ">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form2" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right2" multiple="multiple">
                                                <c:forEach items="${projectProject.surveyorDepts}" var="dept">
                                                    <option class="htmlDepartment2"
                                                            value="${dept.pRoleId}">${dept.pRoleName}</option>
                                                </c:forEach>
                                                <br/>
                                                <c:forEach items="${projectProject.surveyorStaffs}" var="staff">
                                                    <option class="htmlPerson2"
                                                            value="${staff.pRoleId}">${staff.pRoleName}</option>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 2" style="margin-left: 20%;">
                                    <button id="btnD2">
                                        移除
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btnB"></div>
                    <div class=" col-md-offset-5" style="display: block; margin-top: 30px;">
                        <button type="submit" class="confirm2" class="close" data-dismiss="modal"
                                aria-hidden="true">确认
                        </button>
                        <button class="cancel" data-dismiss="modal">取消</button>
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
                                <div class="typeList_department3">部门</div>
                                <div class="typeList_employees3">员工</div>
                                <%--<div class="typeList_group3">群组</div>--%>
                            </div>
                            <div class="col-md-5 addPeople3">
                                <input type="text" class="serach_people_key3" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people3">搜索</button>
                            </div>
                        </div>
                        <!-- <div class="row"> -->
                        <!-- <div class="col-md-5 addPeople">
             <input type="text"  class="serach_people_key"placeholder="关键字添加员工" value="">
             <button type="submit" class="serach_people">搜索</button>
         </div> -->
                        <!--  </div> -->
                        <div class="row ">
                            <div class="content_wrap3 col-md-3">
                                <div class="zTreeDemoBackground3 left">
                                    <ul id="treeDemo3" class="ztree"></ul>
                                </div>
                                <div class="people_list3"></div>
                                <div class="group_list3"></div>
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

                                <div class=" col-md-2 moveBtn3">
                                    <button id="btnR3" style="background: #0D165E">添加></button>
                                </div>
                            </div>

                            <div class="col-md-3 department3">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form3" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right3" multiple="multiple">
                                                <c:forEach items="${projectProject.closeDepts}" var="dept">
                                                    <option class="htmlDepartment3"
                                                            value="${dept.pRoleId}">${dept.pRoleName}</option>
                                                </c:forEach>
                                                <br/>
                                                <c:forEach items="${projectProject.closeStaffs}" var="staff">
                                                    <option class="htmlPerson3"
                                                            value="${staff.pRoleId}"> ${staff.pRoleName}</option>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-2 moveBtn3">
                                    <button id="btnD3">移除</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btnB"></div>
                    <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                        <button type="submit" class="confirm3" class="close" data-dismiss="modal"
                                aria-hidden="true">确认
                        </button>
                        <button class="cancel" data-dismiss="modal">取消</button>
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
                                <div class="typeList_department4">部门</div>
                                <div class="typeList_employees4">员工</div>
                                <%--<div class="typeList_group4">群组</div>--%>
                            </div>

                        </div>
                        <div class="col-md-5 addPeople4">
                            <input type="text" class="serach_people_key4" placeholder="关键字添加员工" value="">
                            <button type="submit" class="serach_people4">搜索</button>
                        </div>
                        <!-- <div class="row"> -->
                        <!-- <div class="col-md-5 addPeople">
             <input type="text"  class="serach_people_key"placeholder="关键字添加员工" value="">
             <button type="submit" class="serach_people">搜索</button>
         </div> -->
                        <!--  </div> -->
                        <div class="row ">
                            <div class="content_wrap4 col-md-3">
                                <div class="zTreeDemoBackground4 left">
                                    <ul id="treeDemo4" class="ztree"></ul>
                                </div>
                                <div class="people_list4"></div>
                                <div class="group_list4"></div>
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

                                <div class=" col-md-2 moveBtn4">
                                    <button id="btnR4" style="background: #0D165E">添加></button>
                                </div>
                            </div>

                            <div class="col-md-3 department4">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right4" multiple="multiple">
                                                <c:forEach items="${makes}" var="make">
                                                    <c:if test="${make.agencyType eq 1}">
                                                        <option class="htmlDepartment4"
                                                                value="${make.agencyId}">${make.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${make.agencyType eq 2}">
                                                        <option class="htmlGroup4"
                                                                value="${make.agencyId}">${make.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${make.agencyType eq 3}">
                                                        <option class="htmlPerson4"
                                                                value="${make.agencyId}">${make.agencyName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-2 moveBtn4">
                                    <button id="btnD4">移除</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btnB"></div>
                    <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                        <button type="submit" class="confirm4" class="close" data-dismiss="modal"
                                aria-hidden="true">确认
                        </button>
                        <button class="cancel" data-dismiss="modal">取消</button>
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
                                <%--<div class="typeList_group5">群组</div>--%>
                            </div>

                        </div>

                        <div class="row ">
                            <div class="content_wrap5 col-md-4">
                                <div class="zTreeDemoBackground5 left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo5" class="ztree"></ul>
                                </div>
                                <div class="people_list5"></div>
                                <div class="group_list5"></div>
                            </div>
                            <div class="col-md-5 addPeople5">
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

                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnR5" style="background: #0D165E">添加></button>
                                </div>
                            </div>

                            <div class="col-md-3 department5">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form5" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right5" multiple="multiple">
                                                <c:forEach items="${projectProject.viewDepts}" var="dept">
                                                    <option class="htmlDepartment5"
                                                            value="${dept.pRoleId}">${dept.pRoleName}</option>
                                                    <%--<option class="htmlGroup" value="${view.agencyId}">${view.agencyName}</option>--%>
                                                </c:forEach>
                                                <c:forEach items="${projectProject.viewStaffs}" var="staff">
                                                    <option class="htmlPerson5"
                                                            value="${staff.pRoleId}">${staff.pRoleName}</option>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnD5">移除</button>
                                </div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button type="submit" class="confirm5" class="close" data-dismiss="modal"
                                    aria-hidden="true">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<%--模态框6 <Model6>--%>
<div class="modal fade" id="myModal6" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0;">
                                <div class="typeList_department6" style="background: #003366;color: #fff;">部门</div>
                                <div class="typeList_employees6">员工</div>
                                <div class="typeList_group6">群组</div>
                            </div>

                        </div>

                        <div class="row ">
                            <div class="content_wrap6 col-md-3">
                                <div class="zTreeDemoBackground6 left">
                                    <ul id="treeDemo6" class="ztree"></ul>
                                </div>
                                <div class="people_list6"></div>
                                <div class="group_list6"></div>
                            </div>
                            <div class="col-md-5 addPeople6">
                                <input type="text" class="serach_people_key6" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people6">搜索</button>
                            </div>
                            <div class="col-md-3 department6 waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="left6" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>

                                <div class=" col-md-2 moveBtn6">
                                    <button id="btnR6" style="background: #0D165E">添加></button>
                                </div>
                            </div>

                            <div class="col-md-3 department6">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form6" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right6" multiple="multiple">
                                                <c:forEach items="${closeBills}" var="bills">
                                                    <c:if test="${bills.agencyType eq 1}">
                                                        <option class="htmlDepartment6"
                                                                value="${bills.agencyId}">${bills.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${bills.agencyType eq 2}">
                                                        <option class="htmlGroup6"
                                                                value="${bills.agencyId}">${bills.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${bills.agencyType eq 3}">
                                                        <option class="htmlPerson6"
                                                                value="${bills.agencyId}">${bills.agencyName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-2 moveBtn6">
                                    <button id="btnD6">移除</button>
                                </div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-6" style="display: block;margin-top: 30px;">
                            <button type="submit" class="confirm6" class="close" data-dismiss="modal"
                                    aria-hidden="true">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<%--模态框7 <Model7>--%>
<div class="modal fade" id="myModal7" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0">
                                <div class="typeList_department7">部门</div>
                                <div class="typeList_employees7">员工</div>
                                <div class="typeList_group7">群组</div>
                            </div>

                        </div>

                        <div class="row ">
                            <div class="content_wrap6 col-md-3">
                                <div class="zTreeDemoBackground7 left">
                                    <ul id="treeDemo7" class="ztree"></ul>
                                </div>
                                <div class="people_list7"></div>
                                <div class="group_list7"></div>
                            </div>
                            <div class="col-md-5 addPeople7">
                                <input type="text" class="serach_people_key7" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people7">搜索</button>
                            </div>
                            <div class="col-md-3 department7 waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="left7" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>

                                <div class=" col-md-2 moveBtn7">
                                    <button id="btnR7" style="background: #0D165E">添加></button>
                                </div>
                            </div>

                            <div class="col-md-3 department7">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form7" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right7" multiple="multiple">
                                                <c:forEach items="${checkInspect}" var="inspect">
                                                    <c:if test="${inspect.agencyType eq 1}">
                                                        <option class="htmlDepartment7"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${inspect.agencyType eq 2}">
                                                        <option class="htmlGroup7"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${inspect.agencyType eq 3}">
                                                        <option class="htmlPerson7"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-2 moveBtn7">
                                    <button id="btnD7">移除</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btnB"></div>
                    <div class=" col-md-offset-7" style="display: block;margin-top: 30px;">
                        <button type="submit" class="confirm7" class="close" data-dismiss="modal"
                                aria-hidden="true">确认
                        </button>
                        <button class="cancel" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<%--领导权限的模态框（Jason添加）--%>
<div class="modal fade" id="leaderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0">
                                <div class="typeList_departmentLeader" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employeesLeader">员工</div>
                            </div>
                        </div>
                        <div class="row ">
                            <div class="content_wrapLeader col-md-4">
                                <div class="zTreeDemoBackgroundLeader left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemoLeader" class="ztree"></ul>
                                </div>
                                <div class="people_listLeader"></div>
                                <div class="group_listLeader"></div>
                            </div>
                            <div class="col-md-5 addPeopleLeader">
                                <input type="text" class="serach_people_keyLeader" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_peopleLeader">搜索</button>
                            </div>
                            <div class="col-md-3 departmentLeader waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="leaderLeft" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>

                                <div class=" col-md-9 2" style="margin-left: 20%;">
                                    <button id="leaderBtnR">添加></button>
                                </div>
                            </div>

                            <div class="col-md-3 departmentLeader ">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="leaderForm" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="leaderRight" multiple="multiple">
                                                <c:forEach items="${projectProject.leaderDepts}" var="dept">
                                                    <option class="htmlDepartmentLeader"
                                                            value="${dept.pRoleId}">${dept.pRoleName}</option>
                                                </c:forEach>
                                                <br/>
                                                <c:forEach items="${projectProject.leaderStaffs}" var="staff">
                                                    <option class="htmlPersonLeader"
                                                            value="${staff.pRoleId}">${staff.pRoleName}</option>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 2" style="margin-left: 20%;">
                                    <button id="leaderBtnD">
                                        移除
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btnB"></div>
                    <div class=" col-md-offset-5" style="display: block; margin-top: 30px;">
                        <button type="submit" class="confirmLeader" class="close" data-dismiss="modal"
                                aria-hidden="true">确认
                        </button>
                        <button class="cancel" data-dismiss="modal">取消</button>
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
    var selectedData = [], selectedData2 = [], selectedData3 = [], selectedData4 = [], selectedData5 = [],
        selectedData6 = [], selectedData7 = [], selectedDataLeader = [];
    var sview = [], aview = [], oview = [], splum = [], aplum = [], opluum = [], spro = [], apro = [], opro = [],
        smake = [], amake = [], omake = [], sdisp = [], adisp = [], odisp = [], sclose = [],
        aclose = [], oclose = [], scheck = [], acheck = [], ocheck = [], sleader = [], aLeader = [];
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
    $("#right option.htmlGroup").each(function (i) {
        if (this.value != "") {
            selectedData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            oview[i] = this.value;
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
    $("#right2 option.htmlGroup2").each(function (i) {
        if (this.value != "") {
            selectedData2.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            opluum[i] = this.value;
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
    $("#right3 option.htmlGroup3").each(function (i) {
        if (this.value != "") {
            selectedData3.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            opro[i] = this.value;
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
    $("#right4 option.htmlGroup4").each(function (i) {
        if (this.value != "") {
            selectedData4.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            omake[i] = this.value;
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
    $("#right5 option.htmlGroup5").each(function (i) {
        if (this.value != "") {
            selectedData5.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            odisp[i] = this.value;
        }
    });
    $("#right6 option.htmlDepartment6").each(function (i) {
        if (this.value != "") {
            selectedData6.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            aclose[i] = this.value;
        }
    });
    $("#right6 option.htmlPerson6").each(function (i) {
        if (this.value != "") {
            selectedData6.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            sclose[i] = this.value;
        }
    });
    $("#right6 option.htmlGroup6").each(function (i) {
        if (this.value != "") {
            selectedData6.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            oclose[i] = this.value;
        }
    });
    $("#right7 option.htmlDepartment7").each(function (i) {
        if (this.value != "") {
            selectedData7.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            acheck[i] = this.value;
        }
    });
    $("#right7 option.htmlPerson7").each(function (i) {
        if (this.value != "") {
            selectedData7.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            scheck[i] = this.value;
        }
    });
    $("#right7 option.htmlGroup7").each(function (i) {
        if (this.value != "") {
            selectedData7.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            ocheck[i] = this.value;
        }
    });
    $("#leaderRight option.htmlDepartmentLeader").each(function (i) {
        if (this.value != "") {
            selectedDataLeader.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            aLeader[i] = this.value;
        }
    });
    $("#leaderRight option.htmlPersonLeader").each(function (i) {
        if (this.value != "") {
            selectedDataLeader.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            sleader[i] = this.value;
        }
    });
    arrData1select(selectedData, '#right');
    $('#viewStaffs').val(sview.join());
    $('#viewAgencys').val(aview.join());
    $('#viewOrganizes').val(oview.join());
    arrData2select(selectedData2, '#right2');
    $('#plumbingStaffs').val(splum.join());
    $('#plumbingAgency').val(aplum.join());
    $('#plumbingOrganizes').val(opluum.join());
    arrData3select(selectedData3, '#right3');
    $('#propertyStaffs').val(spro.join());
    $('#propertyAgency').val(apro.join());
    $('#propertyOrganizes').val(opro.join());
    arrData4select(selectedData4, '#right4');
    $('#makesStaffs').val(smake.join());
    $('#makesAgencys').val(amake.join());
    $('#makesOrganizes').val(omake.join());
    arrData5select(selectedData5, '#right5');
    $('#dispatchStaffs').val(sdisp.join());
    $('#dispatchAgencys').val(adisp.join());
    $('#dispatchOrganizes').val(odisp.join());
    arrData6select(selectedData6, '#right6');
    $('#closeStaffs').val(sclose.join());
    $('#closeAgencys').val(aclose.join());
    $('#closeOrganizes').val(oclose.join());
    arrData7select(selectedData7, '#right7');
    $('#checkStaffs').val(scheck.join());
    $('#checkAgencys').val(acheck.join());
    $('#checkOrganizes').val(ocheck.join());
    arrDataLeaderSelect(selectedDataLeader, '#leaderRight');
    $('#leaderAgencys').val(aLeader.join());
    $('#leaderStaffs').val(sleader.join());


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
    $('.typeList_department6').bind('click', function () {
        $('.group_list6').css('display', 'none');
        $('.zTreeDemoBackground6').css('display', 'block');
        $('#treeDemo6').css('display', 'block');
        $('.department6').css('margin-top', '-2.95%');
        $('.addPeople6').css('display', 'none');
        $('.moveBtn6').css('margin-top', '3.1%');
        $('.content_wrap6 .resultPeople6').css('display', 'none');
        $('.typeList_department6').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees6').css({'background': '#fff', 'color': '#0D165E'});
        $('#left6').html('');
    });
    $('.typeList_department7').bind('click', function () {
        $('.group_list7').css('display', 'none');
        $('.zTreeDemoBackground7').css('display', 'block');
        $('#treeDemo7').css('display', 'block');
        $('.department7').css('margin-top', '-2.95%');
        $('.addPeople7').css('display', 'none');
        $('.moveBtn7').css('margin-top', '3.1%');
        $('.content_wrap7 .resultPeople7').css('display', 'none');
        $('.typeList_department7').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees7').css({'background': '#fff', 'color': '#0D165E'});
        $('#left7').html('');
    });
    $('.typeList_departmentLeader').bind('click', function () {
        $('.group_listLeader').css('display', 'none');
        $('.zTreeDemoBackgroundLeader').css({'display': 'block', 'border': '1px solid #ccc'});
        $('#treeDemoLeader').css('display', 'block');
        $('.departmentLeader').css('margin-top', '-2.95%');
        $('.addPeopleLeader').css('display', 'none');
        $('.moveBtnLeader').css('margin-top', '3.1%');
        $('.content_wrapLeader .resultPeopleLeader').css('display', 'none');
        $('.typeList_departmentLeader').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employeesLeader').css({'background': '#fff', 'color': '#0D165E'});
        $('#leaderLeft').html('');
    });
    //点击人员
    $('.typeList_employees').bind('click', function () {
        $('.group_list').css('display', 'none');
        $('.addPeople').css('display', 'block');
        $('.department').css('margin-top', ' -2.95%');
        // $('.waitSelect').css('margin-left','4%');
        $('.zTreeDemoBackground').css('display', 'none');
        $('.moveBtn').css('margin-top', '-1.54%');
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
        $('#left3').html('');
    });
    $('.typeList_employees4').bind('click', function () {
        $('.group_list4').css('display', 'none');
        $('.addPeople4').css('display', 'block');
        $('.department4').css('margin-top', ' -2.95%');
        $('.zTreeDemoBackground4').css('display', 'none');
        $('.moveBtn4').css('margin-top', '-1.54%');
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
    $('.typeList_employees6').bind('click', function () {
        $('.group_list6').css('display', 'none');
        $('.addPeople6').css('display', 'block');
        $('.department6').css('margin-top', ' -2.95%');
        $('.zTreeDemoBackground6').css('display', 'none');
        $('.moveBtn6').css('margin-top', '-1.54%');
        $('#left6').html('');
    });
    $('.typeList_employees7').bind('click', function () {
        $('.group_list7').css('display', 'none');
        $('.addPeople7').css('display', 'block');
        $('.department7').css('margin-top', ' -2.95%');
        $('.zTreeDemoBackground7').css('display', 'none');
        $('.moveBtn7').css('margin-top', '-1.54%');
        $('#left7').html('');
    });
    $('.typeList_employeesLeader').bind('click', function () {
        $('.group_listLeader').css('display', 'none');
        $('.addPeopleLeader').css('display', 'block');
        $('.departmentLeader').css('margin-top', ' -2.95%');
        $('.zTreeDemoBackgroundLeader').css({'display': 'none', 'border': '1px solid #ccc'});
        $('.moveBtnLeader').css('margin-top', '-1.54%');
        $('.typeList_employeesLeader').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_departmentLeader').css({'background': '#fff', 'color': '#0D165E'});
        $('#leaderLeft').html('');
    });
    //点击群组
    var groupData1 = [];
    $().ready(function () {
        $.ajax({
            url: "../organize/allOrganizeList",            //群组
            type: "get",
            async: "false",
            dataType: "json",
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data.map(function (val) {
                        return organizeFilter(val);
                    });
                    groupData1 = data;
                }
            }
        })
    })

    function arr2Html(arr) {
        var html = '';
        for (var i = 0; i < arr.length; i++) {
            html = html + '<li data-id="' + arr[i].id + '">' + arr[i].name + '</li>';
        }
        return html;
    }
    function groupSelectDataFilter(arr) {
        var html = '';

        return html;
    }
    $('.typeList_group').bind('click', function () {
        var groupHtml = arr2Html(groupData1);
        $('.group_list').html(groupHtml);
        $('.group_list').css('display', 'block');
        $('.zTreeDemoBackground').css('display', 'none');
        $('#treeDemo').css('display', 'none');
        $('.department').css('margin-top', '-2.95%');
        $('.addPeople').css('display', 'none');
        $('.moveBtn').css('margin-top', '3.1%');
        $('.content_wrap .resultPeople').css('display', 'none');
        arrData1select(groupData1, '#left');
    });
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

    $('.typeList_group2').bind('click', function () {
        var groupHtml = arr2Html(groupData1);
        $('.group_list2').html(groupHtml);
        $('.group_list2').css('display', 'block');
        $('.zTreeDemoBackground2').css({'display': 'none', 'border': '1px solid #ccc'});
        $('#treeDemo2').css('display', 'none');
        $('.department2').css('margin-top', '-2.95%');
        $('.addPeople2').css('display', 'none');
        $('.moveBtn2').css('margin-top', '3.1%');
        $('.content_wrap2 .resultPeople2').css('display', 'none');
        arrData2select(groupData1, '#left2');
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

    $('.typeList_group3').bind('click', function () {
        var groupHtml = arr2Html(groupData1);
        $('.group_list3').html(groupHtml);
        $('.group_list3').css('display', 'block');
        $('.zTreeDemoBackground3').css('display', 'none');
        $('#treeDemo3').css('display', 'none');
        $('.department3').css('margin-top', '-2.95%');
        $('.addPeople3').css('display', 'none');
        $('.moveBtn3').css('margin-top', '3.1%');
        $('.content_wrap3 .resultPeople3').css('display', 'none');
        arrData3select(groupData1, '#left3');
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

    $('.typeList_group4').bind('click', function () {
        var groupHtml = arr2Html(groupData1);
        $('.group_list4').html(groupHtml);
        $('.group_list4').css('display', 'block');
        $('.zTreeDemoBackground4').css('display', 'none');
        $('#treeDemo4').css('display', 'none');
        $('.department4').css('margin-top', '-2.95%');
        $('.addPeople4').css('display', 'none');
        $('.moveBtn4').css('margin-top', '3.1%');
        $('.content_wrap4 .resultPeople4').css('display', 'none');
        arrData4select(groupData1, '#left4');
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

    $('.typeList_group5').bind('click', function () {
        var groupHtml = arr2Html(groupData1);
        $('.group_list5').html(groupHtml);
        $('.group_list5').css('display', 'block');
        $('.zTreeDemoBackground5').css({'display': 'none', 'border': '1px solid #ccc'});
        $('#treeDemo5').css('display', 'none');
        $('.department5').css('margin-top', '-2.95%');
        $('.addPeople5').css('display', 'none');
        $('.moveBtn5').css({'margin-top': '3.1%', "margin-left": "20%"});
        $('.content_wrap5 .resultPeople5').css({"display": "none", "border": "none"});
        arrData5select(groupData1, '#left5');
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

    $('.typeList_group6').bind('click', function () {
        var groupHtml = arr2Html(groupData1);
        $('.group_list6').html(groupHtml);
        $('.group_list6').css('display', 'block');
        $('.zTreeDemoBackground6').css('display', 'none');
        $('#treeDemo6').css('display', 'none');
        $('.department6').css('margin-top', '-2.95%');
        $('.addPeople6').css('display', 'none');
        $('.moveBtn6').css('margin-top', '3.1%');
        $('.content_wrap6 .resultPeople6').css('display', 'none');
        arrData6select(groupData1, '#left6');
    });
    // 查询6
    $('.serach_people6').bind('click', function () {
        var serachPeople = $('.serach_people_key6').val();
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
                    arrData6select(data, '#left6');
                }
            }
        });
    });

    $('.typeList_group7').bind('click', function () {
        var groupHtml = arr2Html(groupData1);
        $('.group_list7').html(groupHtml);
        $('.group_list7').css('display', 'block');
        $('.zTreeDemoBackground7').css('display', 'none');
        $('#treeDemo7').css('display', 'none');
        $('.department7').css('margin-top', '-2.95%');
        $('.addPeople7').css('display', 'none');
        $('.moveBtn7').css('margin-top', '3.1%');
        $('.content_wrap7 .resultPeople7').css('display', 'none');
        arrData7select(groupData1, '#left7');
    });
    // 查询7
    $('.serach_people7').bind('click', function () {
        var serachPeople = $('.serach_people_key7').val();
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
                    arrData7select(data, '#left7');
                }
            }
        });
    });
    // 查询领导人员
    $('.serach_peopleLeader').bind('click', function () {
        var serachPeople = $('.serach_people_keyLeader').val();
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
                    arrDataLeaderSelect(data, '#leaderLeft');
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
    function organizeFilter(obj) {
        return {
            id: obj.organizeId,
            isParent: '3',
            name: obj.organizeName,
            type: "2"
        }
    }

    var selectedList = []
    var waitLists = {};
    var waitLists2 = {};
    var waitLists3 = {};
    var waitLists4 = {};
    var waitLists5 = {};
    var waitLists6 = {};
    var waitListsLeader = {};
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
    var setting6 = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter6,
        },
        callback: {
            onClick: onClick6
        }
    };
    var setting7 = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter7,
        },
        callback: {
            onClick: onClick7
        }
    };
    var settingLeader = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filterLeader
        },
        callback: {
            onClick: onClickLeader
        }
    };
    function strToJson(str) {
        var json = (new Function("return " + str))();
        return json;
    }
    function onClick(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left'
//    if (!waitLists['id'+treeNode.id]) {
        //是最后一个  找后台要下一级
//      if (treeNode.isParent) {
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            // console.log(res)
            childrenArr = res.data;
            arrData1select(childrenArr, leftId);
        })
//      }else{
//        arrData1select([],leftId);
//      }
//    }else{
//      //找下一级
//      childrenArr=waitLists[('id'+treeNode.id)];
//      arrData1select(childrenArr,leftId);
//    }
    }
    function onClick2(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left2'
//    if (!waitLists2['id'+treeNode.id]) {
        //是最后一个  找后台要下一级
//      if (treeNode.isParent) {
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrData2select(childrenArr, leftId);
        })
    }
    function onClick3(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left3'
//    if (!waitLists3['id'+treeNode.id]) {
        //是最后一个  找后台要下一级
//      if (treeNode.isParent) {
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            // console.log(res)
            childrenArr = res.data;
            arrData3select(childrenArr, leftId);
        })
    }
    function onClick4(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left4'
        //是最后一个  找后台要下一级
//      if (treeNode.isParent) {
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            // console.log(res)
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

    function onClick6(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left6'
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrData6select(childrenArr, leftId);
        })
    }

    function onClick7(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left7'
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrData7select(childrenArr, leftId);
        })
    }
    function onClickLeader(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#leaderLeft';
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrDataLeaderSelect(childrenArr, leftId);
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
        // console.log(waitLists)
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
    function filter6(treeId, parentNode, childNodes) {
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
            waitLists6['id' + parentNode.id] = arr;
        } else {
            waitLists6['idRoot'] = arr;
        }
        return childNodes;
    }
    function filter7(treeId, parentNode, childNodes) {
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
            waitLists6['id' + parentNode.id] = arr;
        } else {
            waitLists6['idRoot'] = arr;
        }
        return childNodes;
    }
    function filterLeader(treeId, parentNode, childNodes) {
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
            waitListsLeader['id' + parentNode.id] = arr;
        } else {
            waitListsLeader['idRoot'] = arr;
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
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson">' + '</optgroup>';
        var htmlGroup = ')"  id="htmlGroup">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment">' + '</optgroup>';
//    var temp3="";
//    var temp4="";
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
//        temp4+=arr[i].id+",";
            } else if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
//        temp4+=arr[i].id+",";
            } else {
                htmlGroupNum++;
                htmlGroup = htmlGroup + '<option class="htmlGroup" data-id="' + arr[i].id + '">' + arr[i].name + '</option>';
//        temp3+=arr[i].id+",";
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlGroup = '<optgroup label="群组(' + htmlGroupNum + htmlGroup;
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
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson2">' + '</optgroup>';
        var htmlGroup = ')"  id="htmlGroup2">' + '</optgroup>';
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
//            if (arr[i].type == '3') {
//                htmlPersonNum++;
//                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
//            }
            if (arr[i].type == '1') {
                htmlDepartmentNum++;
                htmlDepartment = htmlDepartment + '<option class="htmlDepartment" data-id="' + arr[i].id + '">' + arr[i].name + '</option>';
            } else if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
            } else {
                htmlGroupNum++;
                htmlGroup = htmlGroup + '<option class="htmlGroup" data-id="' + arr[i].id + '">' + arr[i].name + '</option>';
            }

        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlGroup = '<optgroup label="群组(' + htmlGroupNum + htmlGroup;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        $(id).html('');
//        $(id).append(htmlPerson);
        $(id).append(htmlPerson).append(htmlDepartment);
    }
    //方法
    function arrData3select(arr, id) {
        console.log(arr)
        //arrData2select(arr,'#right')
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right3' && arr != selectedData3) {
            arr = selectedData3.concat(arr)
            selectedData3 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson3" ">' + '</optgroup>';
        var htmlGroup = ')"  id="htmlGroup3" ">' + '</optgroup>';
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
            } else {
                htmlGroupNum++;
                htmlGroup = htmlGroup + '<option class="htmlGroup" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>';
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        htmlGroup = '<optgroup label="群组(' + htmlGroupNum + htmlGroup;
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
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson4" ">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment4" ">' + '</optgroup>';
        var htmlGroup = ')"  id="htmlGroup4" ">' + '</optgroup>';
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
            } else {
                htmlGroupNum++;
                htmlGroup = htmlGroup + '<option class="htmlGroup" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>';
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        htmlGroup = '<optgroup label="群组(' + htmlGroupNum + htmlGroup;
        $(id).html('');
        $(id).append(htmlDepartment).append(htmlGroup).append(htmlPerson);
    }

    function arrData5select(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right5' && arr != selectedData5) {
            arr = selectedData5.concat(arr)
            selectedData5 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson5" ">' + '</optgroup>';
        var htmlGroup = ')"  id="htmlGroup5" ">' + '</optgroup>';
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
            } else {
                htmlGroupNum++;
                htmlGroup = htmlGroup + '<option class="htmlGroup" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>';
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        htmlGroup = '<optgroup label="群组(' + htmlGroupNum + htmlGroup;
        $(id).html('');
        $(id).append(htmlPerson).append(htmlDepartment);
    }

    function arrData6select(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right6' && arr != selectedData6) {
            arr = selectedData6.concat(arr)
            selectedData6 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson6" ">' + '</optgroup>';
        var htmlGroup = ')"  id="htmlGroup6" ">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment6" ">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData6.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left6') {
                continue;
            }
            ;
            if (arr[i].type == '1') {
                htmlDepartmentNum++;
                htmlDepartment = htmlDepartment + '<option class="htmlDepartment" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>';
            } else if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>'
            } else {
                htmlGroupNum++;
                htmlGroup = htmlGroup + '<option class="htmlGroup" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>';
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        htmlGroup = '<optgroup label="群组(' + htmlGroupNum + htmlGroup;
        $(id).html('');
        $(id).append(htmlGroup).append(htmlPerson).append(htmlDepartment);
        ;
    }
    function arrData7select(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right7' && arr != selectedData7) {
            arr = selectedData7.concat(arr)
            selectedData7 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson7" ">' + '</optgroup>';
        var htmlGroup = ')"  id="htmlGroup7" ">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment7" ">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData7.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left7') {
                continue;
            }
            ;
            if (arr[i].type == '1') {
                htmlDepartmentNum++;
                htmlDepartment = htmlDepartment + '<option class="htmlDepartment" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>';
            } else if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>'
            } else {
                htmlGroupNum++;
                htmlGroup = htmlGroup + '<option class="htmlGroup" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>';
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        htmlGroup = '<optgroup label="群组(' + htmlGroupNum + htmlGroup;
        $(id).html('');
        $(id).append(htmlGroup).append(htmlPerson).append(htmlDepartment);
        ;
    }
    function arrDataLeaderSelect(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#leaderRight' && arr != selectedDataLeader) {
            arr = selectedDataLeader.concat(arr);
            selectedDataLeader = arr;
        }
        ;
        var htmlDepartmentNum = 0;
        var htmlPersonNum = 0;
        var htmlPerson = ')" id="htmlPersonLeader">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartmentLeader">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedDataLeader.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#leaderLeft') {
                continue;
            }
            ;
//            if (arr[i].type == '3') {
//                htmlPersonNum++;
//                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
//            }
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
    function select() {
        $('#btnR').bind('click', function () {
            // alert(111);
            wait1selected();
        });
        $('#btnD').bind('click', function () {
            selected1wait();
        });
        $('#btnR2').bind('click', function () {
            // alert(111);
            wait2selected();
        });
        $('#btnD2').bind('click', function () {
            selected2wait();
        });
        $('#btnR3').bind('click', function () {
            // alert(111);
            wait3selected();
        });
        $('#btnD3').bind('click', function () {
            selected3wait();
        });
        $('#btnR4').bind('click', function () {
            // alert(111);
            wait4selected();
        });
        $('#btnD4').bind('click', function () {
            selected4wait();
        });
        $('#btnR5').bind('click', function () {
            // alert(111);
            wait5selected();
        });
        $('#btnD5').bind('click', function () {
            selected5wait();
        });
        $('#btnR6').bind('click', function () {
            // alert(111);
            wait6selected();
        });
        $('#btnD6').bind('click', function () {
            selected6wait();
        });
        $('#btnR7').bind('click', function () {
            // alert(111);
            wait7selected();
        });
        $('#btnD7').bind('click', function () {
            selected7wait();
        });
        $('#leaderBtnR').bind('click', function () {
            // alert(111);
            waitLeaderSelected();
        });
        $('#leaderBtnD').bind('click', function () {
            selectedLeaderWait();
        });
    }
    //右移方法1
    function wait1selected() {
        // alert(222);
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left option:selected');
        // console.log(optionArr);
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
            console.log(id);
            arr[i] = {name: optionArr[i].value, id: id, type: className}

        }
        ;
        //移除之前选中的那个数组
        optionArr.remove();
        // $('#left #htmlPerson')[0].label="htmlPerson("+$('#left').find('.htmlPerson').length+")";
        // $('#left #htmlGroup')[0].label="htmlGroup("+$('#left').find('.htmlGroup').length+")";
        arrData1select(arr, '#right');
    }
    //左移方法1
    function selected1wait() {
        var optionArr = $('#right option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';
//      if (optionArr[i].className=='htmlGroup')className='2';
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
//    $('#right #htmlGroup')[0].label="群组("+$('#right').find('.htmlGroup').length+")";
        var optionArrLeft = $('#left option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = '1';
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';
            if (optionArrLeft[i].className == 'htmlGroup') className = '2';
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
        // alert(222);
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left2 option:selected');
//        if (optionArr.length > 1) {
//            alert("只能添加一个人");
//            return;
//        }
        // console.log(optionArr);
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
//        alert($('#right2').find('.htmlPerson').length);
//        if ($('#right2').find('.htmlPerson').length > 0) {
//            alert("只能添加一个人");
//            return false;
//        }
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
//      if (optionArr[i].className=='htmlGroup')className='2';
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
//        $('#right2 #htmlGroup2')[0].label="群组("+$('#right2').find('.htmlGroup').length+")";
        var optionArrLeft = $('#left2 option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = '1';
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';
//            if (optionArrLeft[i].className=='htmlGroup')className='2';
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
        // alert(222);
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left3 option:selected');
        // console.log(optionArr);
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
        // $('#left #htmlPerson')[0].label="htmlPerson("+$('#left').find('.htmlPerson').length+")";
        // $('#left #htmlGroup')[0].label="htmlGroup("+$('#left').find('.htmlGroup').length+")";
        arrData3select(arr, '#right3');
    }
    //左移方法3
    function selected3wait() {
        var optionArr = $('#right3 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';
//      if (optionArr[i].className=='htmlGroup')className='2';
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
//    $('#right3 #htmlGroup3')[0].label="群组("+$('#right3').find('.htmlGroup').length+")";
        var optionArrLeft = $('#left3 option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = '1';
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';
//      if (optionArrLeft[i].className=='htmlGroup')className='2';
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
        // alert(222);
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left4 option:selected');
        // console.log(optionArr);
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
        arrData4select(arr, '#right4');
    }
    //左移方法4
    function selected4wait() {
        var optionArr = $('#right4 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';
            if (optionArr[i].className == 'htmlGroup') className = '2';
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
        $('#right4 #htmlGroup4')[0].label = "群组(" + $('#right4').find('.htmlGroup').length + ")";
        var optionArrLeft = $('#left4 option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = 1;
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';
            if (optionArrLeft[i].className == 'htmlGroup') className = '2';

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
        // alert(222);
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left5 option:selected');
        // console.log(optionArr);
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
        // $('#left #htmlPerson')[0].label="htmlPerson("+$('#left').find('.htmlPerson').length+")";
        // $('#left #htmlGroup')[0].label="htmlGroup("+$('#left').find('.htmlGroup').length+")";
        arrData5select(arr, '#right5');
    }
    //左移方法5
    function selected5wait() {
        var optionArr = $('#right5 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';
//      if (optionArr[i].className=='htmlGroup')className='2';

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
//    $('#right5 #htmlGroup5')[0].label="群组("+$('#right5').find('.htmlGroup').length+")";
        var optionArrLeft = $('#left5 option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = '1';
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';
//      if (optionArrLeft[i].className=='htmlGroup')className='2';

            var id = '';
            if (optionArrLeft[i].dataset) {
                id = optionArrLeft[i].dataset.id;
            } else {
                id =optionArrLeft[i].attributes[1].value;
            }
            arrLeft[i] = {name: optionArrLeft[i].value, id:id, type: className}
        }
        ;
        arr = arrLeft.concat(arr)
        arrData5select(arr, '#left5')
    }
    //右移方法6
    function wait6selected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left6 option:selected');
        // console.log(optionArr);
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
        arrData6select(arr, '#right6');
    }
    //左移方法6
    function selected6wait() {
        var optionArr = $('#right6 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';
            if (optionArr[i].className == 'htmlGroup') className = '2';

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
        selectedData6 = arrSubtraction(selectedData6, arr)
        $('#right6 #htmlPerson6')[0].label = "员工(" + $('#right6').find('.htmlPerson').length + ")";
        $('#right6 #htmlDepartment6')[0].label = "部门(" + $('#right6').find('.htmlDepartment').length + ")";
        $('#right6 #htmlGroup6')[0].label = "群组(" + $('#right6').find('.htmlGroup').length + ")";
        var optionArrLeft = $('#left6 option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = '1';
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';
            if (optionArrLeft[i].className == 'htmlGroup') className = '2';

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
        arrData6select(arr, '#left6')
    }
    //右移方法7
    function wait7selected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left7 option:selected');
        // console.log(optionArr);
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
        arrData7select(arr, '#right7');
    }
    //左移方法7
    function selected7wait() {
        var optionArr = $('#right7 option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';
            if (optionArr[i].className == 'htmlGroup') className = '2';

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
        selectedData7 = arrSubtraction(selectedData7, arr)
        $('#right7 #htmlPerson7')[0].label = "员工(" + $('#right7').find('.htmlPerson').length + ")";
        $('#right7 #htmlDepartment7')[0].label = "部门(" + $('#right7').find('.htmlDepartment').length + ")";
        $('#right7 #htmlGroup7')[0].label = "群组(" + $('#right7').find('.htmlGroup').length + ")";
        var optionArrLeft = $('#left7 option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = '1';
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';
            if (optionArrLeft[i].className == 'htmlGroup') className = '2';

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
        arrData7select(arr, '#left6')
    }
    //右移方法领导权限
    function waitLeaderSelected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#leaderLeft option:selected');
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
        arrDataLeaderSelect(arr, '#leaderRight');
    }
    //左移方法领导权限
    function selectedLeaderWait() {
        var optionArr = $('#leaderRight option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';
//      if (optionArr[i].className=='htmlGroup')className='2';

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
        selectedDataLeader = arrSubtraction(selectedDataLeader, arr);
        $('#leaderRight #htmlPersonLeader')[0].label = "员工(" + $('#leaderRight').find('.htmlPerson').length + ")";
        $('#leaderRight #htmlDepartmentLeader')[0].label = "部门(" + $('#leaderRight').find('.htmlDepartment').length + ")";
        var optionArrLeft = $('#leaderLeft option');
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
        arr = arrLeft.concat(arr);
        arrDataLeaderSelect(arr, '#leaderLeft');
    }

    select();
    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting);
        $.fn.zTree.init($("#treeDemo2"), setting2);
        $.fn.zTree.init($("#treeDemo3"), setting3);
        $.fn.zTree.init($("#treeDemo4"), setting4);
        $.fn.zTree.init($("#treeDemo5"), setting5);
        $.fn.zTree.init($("#treeDemo6"), setting6);
        $.fn.zTree.init($("#treeDemo7"), setting7);
        $.fn.zTree.init($("#treeDemoLeader"), settingLeader);
    });
    function arrSubtraction(arr1, arr2) {
        var temp = [];
        var num = 0;
        for (var i = 0; i < arr1.length; i++) {
            //arr2.map(function(res){return res.id;}数组列表id 和arr1[i].id进行对比
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
        $('#htmlGroup').css('display', 'none');
        $('#htmlPerson').css('display', 'none');
        $('#htmlDepartment').css('display', 'none');
        var tstaff = [], tagency = [], torganize = [];
        var i = j = k = 0;
        $("#right option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else if ($(this).attr("class") == 'htmlGroup') {
                torganize[j] = $(this).attr("data-id");
                j++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#viewStaffs').val(tstaff.join());
        $('#viewAgencys').val(tagency.join());
        $('#viewOrganizes').val(torganize.join());
    });
    // 提交选择2
    $('.confirm2').bind('click', function () {
        // alert(111);
        var liShow = $('#right2').html();
        $('.selectResult2').html(liShow);
        $('.selectResult2').find('option').addClass('floatSelectResult');
        $('#htmlGroup2').css('display', 'none');
        $('#htmlPerson2').css('display', 'none');
        $('#htmlDepartment2').css('display', 'none');
        var tstaff = [], tagency = [], torganize = [];
        var i = j = k = 0;
        $("#right2 option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else if ($(this).attr("class") == 'htmlGroup') {
                torganize[j] = $(this).attr("data-id");
                j++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#plumbingStaffs').val(tstaff.join());
        $('#plumbingAgency').val(tagency.join());
        $('#plumbingOrganizes').val(torganize.join());
    });
    // 提交选择3
    $('.confirm3').bind('click', function () {
        var liShow = $('#right3').html();
        $('.selectResult3').html(liShow);
        $('.selectResult3').find('option').addClass('floatSelectResult');
        $('#htmlGroup3').css('display', 'none');
        $('#htmlPerson3').css('display', 'none');
        $('#htmlDepartment3').css('display', 'none');
        var tstaff = [], tagency = [], torganize = [];
        var i = j = k = 0;
        $("#right3 option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else if ($(this).attr("class") == 'htmlGroup') {
                torganize[j] = $(this).attr("data-id");
                j++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#propertyStaffs').val(tstaff.join());
        $('#propertyAgency').val(tagency.join());
        $('#propertyOrganizes').val(torganize.join());
    });
    // 提交选择4
    $('.confirm4').bind('click', function () {
        var liShow = $('#right4').html();
        $('.selectResult4').html(liShow);
        $('.selectResult4').find('option').addClass('floatSelectResult');
        $('#htmlGroup4').css('display', 'none');
        $('#htmlPerson4').css('display', 'none');
        $('#htmlDepartment4').css('display', 'none');
        var tstaff = [], tagency = [], torganize = [];
        var i = j = k = 0;
        $("#right4 option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else if ($(this).attr("class") == 'htmlGroup') {
                torganize[j] = $(this).attr("data-id");
                j++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#makesStaffs').val(tstaff.join());
        $('#makesAgencys').val(tagency.join());
        $('#makesOrganizes').val(torganize.join());
    });
    // 提交选择5
    $('.confirm5').bind('click', function () {
        var liShow = $('#right5').html();
        $('.selectResult5').html(liShow);
        $('.selectResult5').find('option').addClass('floatSelectResult');
        $('#htmlGroup5').css('display', 'none');
        $('#htmlPerson5').css('display', 'none');
        $('#htmlDepartment5').css('display', 'none');
        var tstaff = [], tagency = [], torganize = [];
        var i = j = k = 0;
        $("#right5 option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else if ($(this).attr("class") == 'htmlGroup') {
                torganize[j] = $(this).attr("data-id");
                j++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#dispatchStaffs').val(tstaff.join());
        $('#dispatchAgencys').val(tagency.join());
        $('#dispatchOrganizes').val(torganize.join());
    });
    // 提交选择6
    $('.confirm6').bind('click', function () {
        var liShow = $('#right6').html();
        $('.selectResult6').html(liShow);
        $('.selectResult6').find('option').addClass('floatSelectResult');
        $('#htmlGroup6').css('display', 'none');
        $('#htmlPerson6').css('display', 'none');
        $('#htmlDepartment6').css('display', 'none');
    });
    // 提交选择7
    $('.confirm7').bind('click', function () {
        var liShow = $('#right7').html();
        $('.selectResult7').html(liShow);
        $('.selectResult7').find('option').addClass('floatSelectResult');
        $('#htmlGroup7').css('display', 'none');
        $('#htmlPerson7').css('display', 'none');
        $('#htmlDepartment7').css('display', 'none');
    });
    // 提交选择领导权限
    $('.confirmLeader').bind('click', function () {
        var liShow = $('#leaderRight').html();
        $('.selectResultLeader').html(liShow);
        $('.selectResultLeader').find('option').addClass('floatSelectResult');
        $('#htmlPersonLeader').css('display', 'none');
        $('#htmlDepartmentLeader').css('display', 'none');
        var tstaff = [], tagency = [];
        var i = k = 0;
        $("#leaderRight option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#leaderStaffs').val(tstaff.join());
        $('#leaderAgencys').val(tagency.join());
//        var tstaff = [];
//        var k = 0;
//        $("#leaderRight option").each(function () {
//            tstaff[k] = $(this).attr("data-id");
//            k++;
//        });
//        $('#leaderStaffs').val(tstaff.join());

    });
</script>
</body>
</html>