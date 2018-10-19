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
            $("#001400010000").addClass("active");
            $("#001400010000").parent().parent().addClass("in");
            $("#001400010000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="001400010000" username="${propertystaff.staffName}"/>
    <div class="container1 userStaffManage">
        <form class="form-horizontal" name="projectInfo" id="projectInfo" action="../project/altProject.html">
            <div class="row">
                <div class="col-md-10 role_new_submit">
                    <div class="newRoleSubmit">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <a href="" onclick="javaScript:history.go(-1)" class="btn btn-primary" for="">关闭</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10 role_new_submit2">
                    <table class="table table-bordered">
                        <caption class="role_table_title">项目编辑</caption>
                        <input type="hidden" name="id" value="${project.id}">
                        <tbody>
                        <tr>
                            <td class="role_table_roleName">项目别名</td>
                            <td class="role_table_fillCont">
                                <input type="text" class="roleNameText" name="originName" value="${project.originName}">
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_titleable_roleName">CRM项目名</td>
                            <td class="role_table_fillCont">
                                ${project.name}
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName">备注</td>
                            <td class="role_table_fillCont">
                                <input type="text" class="roleNameText" id="memo" name="memo" value="${project.memo}">
                            </td>
                        </tr>
                        <tr style="background-color: #F2F2F2">
                            <td colspan="2">质量管理功能模块相关信息</td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>数据查看</span></td>
                            <td>
                                <input type="hidden" name="viewAgencys" id="viewAgencys">
                                <input type="hidden" name="viewStaffs" id="viewStaffs">
                                <input type="hidden" name="viewOrganizes" id="viewOrganizes">
                                <!-- <span class="existence" ></span> -->
                                <div class="existence">
                                  <span style="display:inline-block" class="selectResult" id="role_linkRole">
                                    <c:forEach items="${views}" var="view">
                                        ${view.agencyName};
                                    </c:forEach>
                                  </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal1">选择</span>
                            </td>
                        </tr>
                        <%--业务变更暂时隐藏派单权限--%>
                        <%-- <tr>
              <td class="role_table_roleName"><span>派单权限</span></td>
              <td>
                <input type="hidden" name="dispatchAgencys" id="dispatchAgencys">
                <input type="hidden" name="dispatchStaffs" id="dispatchStaffs">
                <input type="hidden" name="dispatchOrganizes" id="dispatchOrganizes">
                <!-- <span class="existence" ></span> -->
                <div class="existence">
                  <span style="display:inline-block" class="selectResult5" id="role_linkDispatch">
                    <c:forEach items="${dispatches}" var="dispatch">
                      ${dispatch.agencyName};
                    </c:forEach>
                  </span>
                </div>
                <span class="role_select" data-toggle="modal" data-target="#myModal5" >选择</span>
              </td>
            </tr>--%>
                        <tr>
                            <td class="role_table_roleName"><span>工程接单</span></td>
                            <td>
                                <input type="hidden" name="plumbingAgency" id="plumbingAgency">
                                <input type="hidden" name="plumbingStaffs" id="plumbingStaffs">
                                <input type="hidden" name="plumbingOrganizes" id="plumbingOrganizes">
                                <!-- <span class="existence" ></span> -->
                                <div class="existence">
                                  <span style="display:inline-block" class="selectResult2" id="role_linkPlumb">
                                    <c:forEach items="${plumbs}" var="plumb">
                                        ${plumb.agencyName};
                                    </c:forEach>
                                  </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal2">选择</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>报修:物业接单</span></td>
                            <td>
                                <input type="hidden" name="propertyAgency" id="propertyAgency">
                                <input type="hidden" name="propertyStaffs" id="propertyStaffs">
                                <input type="hidden" name="propertyOrganizes" id="propertyOrganizes">
                                <!-- <span class="existence" ></span> -->
                                <div class="existence">
                                  <span style="display:inline-block" class="selectResult3" id="role_linkProperty">
                                    <c:forEach items="${properties}" var="property">
                                        ${property.agencyName};
                                    </c:forEach>
                                  </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal3">选择</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>报修:开发接单</span></td>
                            <td>
                                <input type="hidden" name="makesAgencys" id="makesAgencys">
                                <input type="hidden" name="makesStaffs" id="makesStaffs">
                                <input type="hidden" name="makesOrganizes" id="makesOrganizes">
                                <!-- <span class="existence" ></span> -->
                                <div class="existence">
                                  <span style="display:inline-block" class="selectResult4" id="role_linkMake">
                                    <c:forEach items="${makes}" var="make">
                                        ${make.agencyName};
                                    </c:forEach>
                                  </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal4">选择</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>关单权限</span></td>
                            <td>
                                <input type="hidden" name="closeAgencys" id="closeAgencys">
                                <input type="hidden" name="closeStaffs" id="closeStaffs">
                                <input type="hidden" name="closeOrganizes" id="closeOrganizes">
                                <!-- <span class="existence" ></span> -->
                                <div class="existence">
                                  <span style="display:inline-block" class="selectResult6" id="role_linkClose">
                                    <c:forEach items="${closeBills}" var="bills">
                                        ${bills.agencyName};
                                    </c:forEach>
                                  </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal6">选择</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>验房工程师</span></td>
                            <td>
                                <input type="hidden" name="checkAgencys" id="checkAgencys">
                                <input type="hidden" name="checkStaffs" id="checkStaffs">
                                <input type="hidden" name="checkOrganizes" id="checkOrganizes">
                                <!-- <span class="existence" ></span> -->
                                <div class="existence">
                                  <span style="display:inline-block" class="selectResult7" id="role_linkCheck">
                                    <c:forEach items="${checkInspect}" var="inspect">
                                        ${inspect.agencyName};
                                    </c:forEach>
                                  </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal7">选择</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>质量经理</span></td>
                            <td>
                                <input type="hidden" name="qualityAgencys" id="qualityAgencys">
                                <input type="hidden" name="qualityStaffs" id="qualityStaffs">
                                <input type="hidden" name="qualityOrganizes" id="qualityOrganizes">
                                <!-- <span class="existence" ></span> -->
                                <div class="existence">
                                  <span style="display:inline-block" class="selectResult8" id="role_linkQuality">
                                    <c:forEach items="${qualityManager}" var="inspect">
                                        ${inspect.agencyName};
                                    </c:forEach>
                                  </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal8">选择</span>
                            </td>
                        </tr>
                        <%--具有报事派单权限，投诉单新增权限，目前叫客观信息员--%>
                        <tr>
                            <td class="role_table_roleName"><span>项目前台</span></td>
                            <td>
                                <input type="hidden" name="dispatchSheetStaffs" id="dispatchSheetStaffs">
                                <input type="hidden" name="dispatchSheetAgencys" id="dispatchSheetAgencys">
                                <input type="hidden" name="dispatchSheetOrganizes" id="dispatchSheetOrganizes">
                                <!-- <span class="existence" ></span> -->
                                <div class="existence">
                                  <span style="display:inline-block" class="selectResultDispatch"
                                        id="role_linkDispatch">
                                    <c:forEach items="${dispatchSheet}" var="dispatch">
                                        ${dispatch.agencyName};
                                    </c:forEach>
                                  </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#dispatchSheet">选择</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>供应商负责人</span></td>
                            <td>
                                <input type="hidden" name="secondAgencys" id="secondAgencys">
                                <input type="hidden" name="secondStaffs" id="secondStaffs">
                                <input type="hidden" name="secondOrganizes" id="secondOrganizes">
                                <!-- <span class="existence" ></span> -->
                                <div class="existence">
                                      <span style="display:inline-block" class="selectResult9" id="role_linkSecond">
                                        <c:forEach items="${secondParty}" var="inspect">
                                            ${inspect.agencyName};
                                        </c:forEach>
                                      </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal9">选择</span>
                            </td>
                        </tr>
                        <%--总部客观具有废弃投诉单权限--%>
                        <tr>
                            <td class="role_table_roleName"><span>总部客关</span></td>
                            <td>
                                <input type="hidden" name="HQObjectiveStaffs" id="HQObjectiveStaffs">
                                <input type="hidden" name="HQObjectiveAgencys" id="HQObjectiveAgencys">
                                <input type="hidden" name="HQObjectiveOrganizes" id="HQObjectiveOrganizes">
                                <!-- <span class="existence" ></span> -->
                                <div class="existence">
                                  <span style="display:inline-block" class="selectResultHQ"
                                        id="role_linkHQ">
                                    <c:forEach items="${HQObjective}" var="objective">
                                        ${objective.agencyName};
                                    </c:forEach>
                                  </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#HQ">选择</span>
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
                name: {
                    required: true,
                    minlength: 1,
                    maxlength: 20
                }
            },
            messages: {
                name: {
                    required: "请输入项目名称！",
                    minlength: "不能少于1个字符！",
                    maxlength: "请勿超过20个字！"
                },
            }
        })
    })
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
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
                            <div class="col-md-4 typeList" style="padding-left: 0;">
                                <div class="typeList_department" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees">员工</div>
                                <div class="typeList_group">群组</div>
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
                                <div class="zTreeDemoBackground left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo" class="ztree"></ul>
                                </div>
                                <div class="people_list"></div>
                                <div class="group_list"></div>
                            </div>
                            <div class="col-md-5 addPeople" style="left: 7%;">
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
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnR" style="background: #0D165E">添加></button>
                                </div>

                            </div>

                            <div class="col-md-3 department ">
                                <h5 class="h52">已选列表</h5>
                                <form class="submitForm" id="form1" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <select id="right" multiple="multiple">
                                            <c:forEach items="${views}" var="view">
                                                <c:if test="${view.agencyType eq 1}">
                                                    <option class="htmlDepartment"
                                                            value="${view.agencyId}">${view.agencyName}</option>
                                                </c:if>
                                                <c:if test="${view.agencyType eq 2}">
                                                    <option class="htmlGroup"
                                                            value="${view.agencyId}">${view.agencyName}</option>
                                                </c:if>
                                                <c:if test="${view.agencyType eq 3}">
                                                    <option class="htmlPerson"
                                                            value="${view.agencyId}">${view.agencyName}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnD">
                                        <移除
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button type="submit" class="confirm" class="close" data-dismiss="modal" aria-hidden="true">
                                确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
                            <div class="col-md-4 typeList" style="padding-left: 0;">
                                <div class="typeList_department2" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees2">员工</div>
                                <div class="typeList_group2">群组</div>
                            </div>

                        </div>
                        <!-- <div class="row"> -->
                        <!-- <div class="col-md-5 addPeople">
             <input type="text"  class="serach_people_key"placeholder="关键字添加员工" value="">
             <button type="submit" class="serach_people">搜索</button>
         </div> -->
                        <!--  </div> -->
                        <div class="row ">
                            <div class="content_wrap2 col-md-3">
                                <div class="zTreeDemoBackground2 left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo2" class="ztree"></ul>
                                </div>
                                <div class="people_list2"></div>
                                <div class="group_list2"></div>
                            </div>
                            <div class="col-md-5 addPeople2" style="left: 7%;">
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
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
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
                                                <c:forEach items="${plumbs}" var="plumb">
                                                    <c:if test="${plumb.agencyType eq 1}">
                                                        <option class="htmlDepartment2"
                                                                value="${plumb.agencyId}">${plumb.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${plumb.agencyType eq 2}">
                                                        <option class="htmlGroup2"
                                                                value="${plumb.agencyId}">${plumb.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${plumb.agencyType eq 3}">
                                                        <option class="htmlPerson2"
                                                                value="${plumb.agencyId}">${plumb.agencyName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnD2" style="background: #0D165E">
                                        <移除
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button type="submit" class="confirm2" class="close" data-dismiss="modal"
                                    aria-hidden="true">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
                            <div class="col-md-4 typeList" style="padding-left: 0;">
                                <div class="typeList_department3" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees3">员工</div>
                                <div class="typeList_group3">群组</div>
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
                                <div class="zTreeDemoBackground3 left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo3" class="ztree"></ul>
                                </div>
                                <div class="people_list3"></div>
                                <div class="group_list3"></div>
                            </div>
                            <div class="col-md-5 addPeople3" style="left: 7%;">
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
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
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
                                                <c:forEach items="${properties}" var="property">
                                                    <c:if test="${property.agencyType eq 1}">
                                                        <option class="htmlDepartment3"
                                                                value="${property.agencyId}">${property.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${property.agencyType eq 2}">
                                                        <option class="htmlGroup3"
                                                                value="${property.agencyId}">${property.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${property.agencyType eq 3}">
                                                        <option class="htmlPerson3"
                                                                value="${property.agencyId}">${property.agencyName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnD3" style="background: #0D165E">
                                        <移除>
                                    </button>
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
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
                            <div class="col-md-4 typeList" style="padding-left: 0;">
                                <div class="typeList_department4" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees4">员工</div>
                                <div class="typeList_group4">群组</div>
                            </div>

                        </div>
                        <!-- <div class="row"> -->
                        <!-- <div class="col-md-5 addPeople">
             <input type="text"  class="serach_people_key"placeholder="关键字添加员工" value="">
             <button type="submit" class="serach_people">搜索</button>
         </div> -->
                        <!--  </div> -->
                        <div class="row ">
                            <div class="content_wrap4 col-md-3">
                                <div class="zTreeDemoBackground4 left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo4" class="ztree"></ul>
                                </div>
                                <div class="people_list4"></div>
                                <div class="group_list4"></div>
                            </div>
                            <div class="col-md-5 addPeople4" style="left: 7%;">
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
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnR4">添加></button>
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
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnD4">
                                        <移除
                                    </button>
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
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
                            <div class="col-md-4 typeList" style="padding-left: 0;">
                                <div class="typeList_department5" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees5">员工</div>
                                <div class="typeList_group5">群组</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap5 col-md-3">
                                <div class="zTreeDemoBackground5 left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo5" class="ztree"></ul>
                                </div>
                                <div class="people_list5"></div>
                                <div class="group_list5"></div>
                            </div>
                            <div class="col-md-5 addPeople5" style="left: 7%;">
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
                                    <button id="btnR5">添加></button>
                                </div>
                            </div>


                            <div class="col-md-3 department5">
                                <h5 class="h52">已选列表</h5>
                                <form class="submitForm" id="form5" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right5" multiple="multiple">
                                                <c:forEach items="${dispatches}" var="dispatch">
                                                    <c:if test="${dispatch.agencyType eq 1}">
                                                        <option class="htmlDepartment5"
                                                                value="${dispatch.agencyId}">${dispatch.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${dispatch.agencyType eq 2}">
                                                        <option class="htmlGroup5"
                                                                value="${dispatch.agencyId}">${dispatch.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${dispatch.agencyType eq 3}">
                                                        <option class="htmlPerson5"
                                                                value="${dispatch.agencyId}">${dispatch.agencyName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnD5">
                                        <移除
                                    </button>
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
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
                                <div class="typeList_department6" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees6">员工</div>
                                <div class="typeList_group6">群组</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap6 col-md-3">
                                <div class="zTreeDemoBackground6 left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo6" class="ztree"></ul>
                                </div>
                                <div class="people_list6"></div>
                                <div class="group_list6"></div>
                            </div>
                            <div class="col-md-5 addPeople6" style="left: 7%;">
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
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnR6">添加></button>
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
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnD6">
                                        <移除
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button type="submit" class="confirm6" class="close" data-dismiss="modal"
                                    aria-hidden="true">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
                            <div class="col-md-4 typeList" style="padding-left: 0;">
                                <div class="typeList_department7" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees7">员工</div>
                                <div class="typeList_group7">群组</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap6 col-md-3">
                                <div class="zTreeDemoBackground7 left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo7" class="ztree"></ul>
                                </div>
                                <div class="people_list7"></div>
                                <div class="group_list7"></div>
                            </div>
                            <div class="col-md-5 addPeople7" style="left: 7%;">
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
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnR7">添加<</button>
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
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnD7">
                                        <移除
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button type="submit" class="confirm7" class="close" data-dismiss="modal"
                                    aria-hidden="true">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%--模态框8 <Model8>--%>
<div class="modal fade" id="myModal8" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0;">
                                <div class="typeList_department8" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees8">员工</div>
                                <div class="typeList_group8">群组</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap6 col-md-3">
                                <div class="zTreeDemoBackground8 left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo8" class="ztree"></ul>
                                </div>
                                <div class="people_list8"></div>
                                <div class="group_list8"></div>
                            </div>
                            <div class="col-md-5 addPeople8" style="left: 7%;">
                                <input type="text" class="serach_people_key8" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people8">搜索</button>
                            </div>
                            <div class="col-md-3 department8 waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="left8" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnR8">添加<</button>
                                </div>
                            </div>
                            <div class="col-md-3 department8">
                                <h5 class="h52">已选列表</h5>
                                <form class="submitForm" id="form8" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right8" multiple="multiple">
                                                <c:forEach items="${qualityManager}" var="inspect">
                                                    <c:if test="${inspect.agencyType eq 1}">
                                                        <option class="htmlDepartment8"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${inspect.agencyType eq 2}">
                                                        <option class="htmlGroup8"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${inspect.agencyType eq 3}">
                                                        <option class="htmlPerson8"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnD8">
                                        <移除
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button type="submit" class="confirm8" class="close" data-dismiss="modal"
                                    aria-hidden="true">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%--客观信息员（报事派单）模态框 <dispatchSheet>--%>
<div class="modal fade" id="dispatchSheet" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0;">
                                <div class="typeList_departmentDispatch" style="color: #fff;background: #0D165E">部门
                                </div>
                                <div class="typeList_employeesDispatch">员工</div>
                                <div class="typeList_groupDispatch">群组</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap6 col-md-3">
                                <div class="zTreeDemoBackgroundDispatch left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemoDispatch" class="ztree"></ul>
                                </div>
                                <div class="people_listDispatch"></div>
                                <div class="group_listDispatch"></div>
                            </div>
                            <div class="col-md-5 addPeopleDispatch" style="left: 7%;">
                                <input type="text" class="serach_people_keyDispatch" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_peopleDispatch">搜索</button>
                            </div>
                            <div class="col-md-3 departmentDispatch waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="leftDispatch" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnRDispatch">添加<</button>
                                </div>
                            </div>
                            <div class="col-md-3 departmentDispatch">
                                <h5 class="h52">已选列表</h5>
                                <form class="submitForm" id="formDispatch" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="rightDispatch" multiple="multiple">
                                                <c:forEach items="${dispatchSheet}" var="inspect">
                                                    <c:if test="${inspect.agencyType eq 1}">
                                                        <option class="htmlDepartmentDispatch"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${inspect.agencyType eq 2}">
                                                        <option class="htmlGroupDispatch"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${inspect.agencyType eq 3}">
                                                        <option class="htmlPersonDispatch"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnDDispatch">移除</button>
                                </div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button type="submit" class="confirmDispatch" class="close" data-dismiss="modal"
                                    aria-hidden="true">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%--模态框9 <Model9>--%>
<div class="modal fade" id="myModal9" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0;">
                                <div class="typeList_department9" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees9">员工</div>
                                <div class="typeList_group9">群组</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap6 col-md-3">
                                <div class="zTreeDemoBackground9 left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo9" class="ztree"></ul>
                                </div>
                                <div class="people_list9"></div>
                                <div class="group_list9"></div>
                            </div>
                            <div class="col-md-5 addPeople9" style="left: 7%;">
                                <input type="text" class="serach_people_key9" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people9">搜索</button>
                            </div>
                            <div class="col-md-3 department9 waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="left9" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnR9">添加<</button>
                                </div>
                            </div>
                            <div class="col-md-3 department9">
                                <h5 class="h52">已选列表</h5>
                                <form class="submitForm" id="form9" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right9" multiple="multiple">
                                                <c:forEach items="${secondParty}" var="inspect">
                                                    <c:if test="${inspect.agencyType eq 1}">
                                                        <option class="htmlDepartment9"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${inspect.agencyType eq 2}">
                                                        <option class="htmlGroup9"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${inspect.agencyType eq 3}">
                                                        <option class="htmlPerson9"
                                                                value="${inspect.agencyId}">${inspect.agencyName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnD9">
                                        移除
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button type="submit" class="confirm9" class="close" data-dismiss="modal"
                                    aria-hidden="true">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%--总部客观   模态框--%>
<div class="modal fade" id="HQ" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0;">
                                <div class="typeList_departmentHQ" style="color: #fff;background: #0D165E">部门
                                </div>
                                <div class="typeList_employeesHQ">员工</div>
                                <div class="typeList_groupHQ">群组</div>
                            </div>

                        </div>
                        <div class="row ">
                            <div class="content_wrap6 col-md-3">
                                <div class="zTreeDemoBackgroundHQ left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemoHQ" class="ztree"></ul>
                                </div>
                                <div class="people_listHQ"></div>
                                <div class="group_listHQ"></div>
                            </div>
                            <div class="col-md-5 addPeopleHQ" style="left: 7%;">
                                <input type="text" class="serach_people_keyHQ" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_peopleHQ">搜索</button>
                            </div>
                            <div class="col-md-3 departmentHQ waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="leftHQ" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnRHQ">添加<</button>
                                </div>
                            </div>
                            <div class="col-md-3 departmentHQ">
                                <h5 class="h52">已选列表</h5>
                                <form class="submitForm" id="formHQ" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="rightHQ" multiple="multiple">
                                                <c:forEach items="${HQObjective}" var="objective">
                                                    <c:if test="${objective.agencyType eq 1}">
                                                        <option class="htmlDepartmentHQ"
                                                                value="${objective.agencyId}">${objective.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${objective.agencyType eq 2}">
                                                        <option class="htmlGroupHQ"
                                                                value="${objective.agencyId}">${objective.agencyName}</option>
                                                    </c:if>
                                                    <c:if test="${objective.agencyType eq 3}">
                                                        <option class="htmlPersonHQ"
                                                                value="${objective.agencyId}">${objective.agencyName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnDHQ">移除</button>
                                </div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button type="submit" class="confirmHQ" class="close" data-dismiss="modal"
                                    aria-hidden="true">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script>
    //从后台拿到右边的数据
    var selectedData = [], selectedData2 = [], selectedData3 = [], selectedData4 = [], selectedData5 = [],
        selectedData6 = [], selectedData7 = [], selectedData8 = [], selectedData9 = [], selecteDispatchData = [],
        selecteHQ = [];
    var sview = [], aview = [], oview = [], splum = [], aplum = [], opluum = [], spro = [], apro = [], opro = [],
        smake = [], amake = [], omake = [], sdisp = [], adisp = [], odisp = [], sclose = [], aclose = [], oclose = [],
        scheck = [], acheck = [], ocheck = [], squality = [], aquality = [], oquality = [],
        ssecond = [], asecond = [], osecond = [], sdispatch = [], adispatch = [], odispatch = [], sHQ = [], aHQ = [],
        oHQ = [];
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
            selectedData2.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'))
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
    $("#right8 option.htmlDepartment8").each(function (i) {
        if (this.value != "") {
            selectedData8.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            aquality[i] = this.value;
        }
    });
    $("#right8 option.htmlPerson8").each(function (i) {
        if (this.value != "") {
            selectedData8.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            squality[i] = this.value;
        }
    });
    $("#right8 option.htmlGroup8").each(function (i) {
        if (this.value != "") {
            selectedData8.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            oquality[i] = this.value;
        }
    });
    // 开始(客观信心员)报事派单 Dispatch
    $("#rightDispatch option.htmlDepartmentDispatch").each(function (i) {
        if (this.value != "") {
            selecteDispatchData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            adispatch[i] = this.value;
        }
    });
    $("#rightDispatch option.htmlPersonDispatch").each(function (i) {
        if (this.value != "") {
            selecteDispatchData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            sdispatch[i] = this.value;
        }
    });
    $("#rightDispatch option.htmlGroupDispatch").each(function (i) {
        if (this.value != "") {
            selecteDispatchData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            odispatch[i] = this.value;
        }
    });
    //结束

    $("#right9 option.htmlDepartment9").each(function (i) {
        if (this.value != "") {
            selectedData9.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            asecond[i] = this.value;
        }
    });
    $("#right9 option.htmlPerson9").each(function (i) {
        if (this.value != "") {
            selectedData9.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            ssecond[i] = this.value;
        }
    });
    $("#right9 option.htmlGroup9").each(function (i) {
        if (this.value != "") {
            selectedData9.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            osecond[i] = this.value;
        }
    });

    // 开始(客观信心员)报事派单 Dispatch
    $("#rightHQ option.htmlDepartmentHQ").each(function (i) {
        if (this.value != "") {
            selecteHQ.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            aHQ[i] = this.value;
        }
    });
    $("#rightHQ option.htmlPersonHQ").each(function (i) {
        if (this.value != "") {
            selecteHQ.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            sHQ[i] = this.value;
        }
    });
    $("#rightHQ option.htmlGroupHQ").each(function (i) {
        if (this.value != "") {
            selecteHQ.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            oHQ[i] = this.value;
        }
    });
    //结束

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
    arrData7select(selectedData7, '#right7')
    $('#checkStaffs').val(scheck.join());
    $('#checkAgencys').val(acheck.join());
    $('#checkOrganizes').val(ocheck.join());
    arrData8select(selectedData8, '#right8')
    $('#qualityStaffs').val(squality.join());
    $('#qualityAgencys').val(aquality.join());
    $('#qualityOrganizes').val(oquality.join());
    //报事派单
    arrDataDispatchselect(selecteDispatchData, '#rightDispatch')
    $('#dispatchSheetStaffs').val(sdispatch.join());
    $('#dispatchSheetAgencys').val(adispatch.join());
    $('#dispatchSheetOrganizes').val(odispatch.join());
    //
    arrData9select(selectedData9, '#right9')
    $('#secondStaffs').val(ssecond.join());
    $('#secondAgencys').val(asecond.join());
    $('#secondOrganizes').val(osecond.join());

    //总部客观
    arrDataHQselect(selecteHQ, '#rightHQ')
    $('#HQObjectiveStaffs').val(sHQ.join());
    $('#HQObjectiveAgencys').val(aHQ.join());
    $('#HQObjectiveOrganizes').val(oHQ.join());

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
        $('.typeList_department').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group').css({'background': '#fff', 'color': '#0D165E'});
        $('#left').html('');
    });
    $('.typeList_department2').bind('click', function () {
        $('.group_list2').css('display', 'none');
        $('.zTreeDemoBackground2').css('display', 'block');
        $('#treeDemo2').css('display', 'block');
        $('.department2').css('margin-top', '-2.95%');
        $('.addPeople2').css('display', 'none');
        $('.moveBtn2').css('margin-top', '3.1%');
        $('.content_wrap2 .resultPeople2').css('display', 'none');
        $('.typeList_department2').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees2').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group2').css({'background': '#fff', 'color': '#0D165E'});
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
        $('.typeList_group3').css({'background': '#fff', 'color': '#0D165E'});
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
        $('.typeList_group4').css({'background': '#fff', 'color': '#0D165E'});
        $('#left4').html('');
    });
    $('.typeList_department5').bind('click', function () {
        $('.group_list5').css('display', 'none');
        $('.zTreeDemoBackground5').css('display', 'block');
        $('#treeDemo5').css('display', 'block');
        $('.department5').css('margin-top', '-2.95%');
        $('.addPeople5').css('display', 'none');
        $('.moveBtn5').css('margin-top', '3.1%');
        $('.content_wrap5 .resultPeople5').css('display', 'none');
        $('.typeList_department5').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees5').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group5').css({'background': '#fff', 'color': '#0D165E'});
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
        $('.typeList_group6').css({'background': '#fff', 'color': '#0D165E'});
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
        $('.typeList_group7').css({'background': '#fff', 'color': '#0D165E'});
        $('#left7').html('');
    });
    $('.typeList_department8').bind('click', function () {
        $('.group_list8').css('display', 'none');
        $('.zTreeDemoBackground8').css('display', 'block');
        $('#treeDemo8').css('display', 'block');
        $('.department8').css('margin-top', '-2.95%');
        $('.addPeople8').css('display', 'none');
        $('.moveBtn8').css('margin-top', '3.1%');
        $('.content_wrap8 .resultPeople8').css('display', 'none');
        $('.typeList_department8').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees8').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group8').css({'background': '#fff', 'color': '#0D165E'});
        $('#left8').html('');
    });
    //报事派单
    $('.typeList_departmentDispatch').bind('click', function () {
        $('.group_listDispatch').css('display', 'none');
        $('.zTreeDemoBackgroundDispatch').css('display', 'block');
        $('#treeDemoDispatch').css('display', 'block');
        $('.departmentDispatch').css('margin-top', '-2.95%');
        $('.addPeopleDispatch').css('display', 'none');
        $('.moveBtnDispatch').css('margin-top', '3.1%');
        $('.content_wrapDispatch .resultPeopleDispatch').css('display', 'none');
        $('.typeList_departmentDispatch').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employeesDispatch').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_groupDispatch').css({'background': '#fff', 'color': '#0D165E'});
        $('#leftDispatch').html('');
    });
    $('.typeList_department9').bind('click', function () {
        $('.group_list9').css('display', 'none');
        $('.zTreeDemoBackground9').css('display', 'block');
        $('#treeDemo9').css('display', 'block');
        $('.department9').css('margin-top', '-2.95%');
        $('.addPeople9').css('display', 'none');
        $('.moveBtn9').css('margin-top', '3.1%');
        $('.content_wrap9 .resultPeople9').css('display', 'none');
        $('.typeList_department9').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees9').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group9').css({'background': '#fff', 'color': '#0D165E'});
        $('#left9').html('');
    });
    //总部客观
    $('.typeList_departmentHQ').bind('click', function () {
        $('.group_listHQ').css('display', 'none');
        $('.zTreeDemoBackgroundHQ').css('display', 'block');
        $('#treeDemoHQ').css('display', 'block');
        $('.departmentHQ').css('margin-top', '-2.95%');
        $('.addPeopleHQ').css('display', 'none');
        $('.moveBtnHQ').css('margin-top', '3.1%');
        $('.content_wrapHQ .resultPeopleHQ').css('display', 'none');
        $('.typeList_departmentHQ').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employeesHQ').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_groupHQ').css({'background': '#fff', 'color': '#0D165E'});
        $('#leftHQ').html('');
    });
    //点击人员
    $('.typeList_employees').bind('click', function () {
        $('.group_list').css('display', 'none');
        $('.addPeople').css('display', 'block');
        $('.department').css('margin-top', '-2.95%');
        // $('.waitSelect').css('margin-left','4%');
        $('.zTreeDemoBackground').css('display', 'none');
        $('.moveBtn').css('margin-top', '-1.54%');
        $('.typeList_employees').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group').css({'background': '#fff', 'color': '#0D165E'});
        $('#left').html('');
    });
    $('.typeList_employees2').bind('click', function () {
        $('.group_list2').css('display', 'none');
        $('.addPeople2').css('display', 'block');
        $('.department2').css('margin-top', '-2.95%');
        $('.zTreeDemoBackground2').css('display', 'none');
        $('.moveBtn2').css('margin-top', '-1.54%');
        $('.typeList_employees2').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department2').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group2').css({'background': '#fff', 'color': '#0D165E'});
        $('#left2').html('');
    });
    $('.typeList_employees3').bind('click', function () {
        $('.group_list3').css('display', 'none');
        $('.addPeople3').css('display', 'block');
        $('.department3').css('margin-top', '-2.95%');
        $('.zTreeDemoBackground3').css('display', 'none');
        $('.moveBtn3').css('margin-top', '-1.54%');
        $('.typeList_employees3').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department3').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group3').css({'background': '#fff', 'color': '#0D165E'});
        $('#left3').html('');
    });
    $('.typeList_employees4').bind('click', function () {
        $('.group_list4').css('display', 'none');
        $('.addPeople4').css('display', 'block');
        $('.department4').css('margin-top', '-2.95%');
        $('.zTreeDemoBackground4').css('display', 'none');
        $('.moveBtn4').css('margin-top', '-1.54%');
        $('.typeList_employees4').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department4').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group4').css({'background': '#fff', 'color': '#0D165E'});
        $('#left4').html('');
    });
    $('.typeList_employees5').bind('click', function () {
        $('.group_list5').css('display', 'none');
        $('.addPeople5').css('display', 'block');
        $('.department5').css('margin-top', '-2.95%');
        $('.zTreeDemoBackground5').css('display', 'none');
        $('.moveBtn5').css('margin-top', '-1.54%');
        $('.typeList_employees5').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department5').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group5').css({'background': '#fff', 'color': '#0D165E'});
        $('#left5').html('');
    });
    $('.typeList_employees6').bind('click', function () {
        $('.group_list6').css('display', 'none');
        $('.addPeople6').css('display', 'block');
        $('.department6').css('margin-top', '-2.95%');
        $('.zTreeDemoBackground6').css('display', 'none');
        $('.moveBtn6').css('margin-top', '-1.54%');
        $('.typeList_employees6').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department6').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group6').css({'background': '#fff', 'color': '#0D165E'});
        $('#left6').html('');
    });
    $('.typeList_employees7').bind('click', function () {
        $('.group_list7').css('display', 'none');
        $('.addPeople7').css('display', 'block');
        $('.department7').css('margin-top', '-2.95%');
        $('.zTreeDemoBackground7').css('display', 'none');
        $('.moveBtn7').css('margin-top', '-1.54%');
        $('.typeList_employees7').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department7').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group7').css({'background': '#fff', 'color': '#0D165E'});
        $('#left7').html('');
    });
    $('.typeList_employees8').bind('click', function () {
        $('.group_list8').css('display', 'none');
        $('.addPeople8').css('display', 'block');
        $('.department8').css('margin-top', '-2.95%');
        $('.zTreeDemoBackground8').css('display', 'none');
        $('.moveBtn8').css('margin-top', '-1.54%');
        $('.typeList_employees8').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department8').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group8').css({'background': '#fff', 'color': '#0D165E'});
        $('#left8').html('');
    });
    //报事派单
    $('.typeList_employeesDispatch').bind('click', function () {
        $('.group_listDispatch').css('display', 'none');
        $('.addPeopleDispatch').css('display', 'block');
        $('.departmentDispatch').css('margin-top', '-2.95%');
        $('.zTreeDemoBackgroundDispatch').css('display', 'none');
        $('.moveBtnDispatch').css('margin-top', '-1.54%');
        $('.typeList_employeesDispatch').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_departmentDispatch').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_groupDispatch').css({'background': '#fff', 'color': '#0D165E'});
        $('#leftDispatch').html('');
    });
    $('.typeList_employees9').bind('click', function () {
        $('.group_list9').css('display', 'none');
        $('.addPeople9').css('display', 'block');
        $('.department9').css('margin-top', '-2.95%');
        $('.zTreeDemoBackground9').css('display', 'none');
        $('.moveBtn9').css('margin-top', '-1.54%');
        $('.typeList_employees9').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_department9').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_group9').css({'background': '#fff', 'color': '#0D165E'});
        $('#left9').html('');
    });
//总部客观
    $('.typeList_employeesHQ').bind('click', function () {
        $('.group_listHQ').css('display', 'none');
        $('.addPeopleHQ').css('display', 'block');
        $('.departmentHQ').css('margin-top', '-2.95%');
        $('.zTreeDemoBackgroundHQ').css('display', 'none');
        $('.moveBtnHQ').css('margin-top', '-1.54%');
        $('.typeList_employeesHQ').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_departmentHQ').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_groupHQ').css({'background': '#fff', 'color': '#0D165E'});
        $('#leftHQ').html('');
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
        $('.typeList_group').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_department').css({'background': '#fff', 'color': '#0D165E'});
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
        $('.zTreeDemoBackground2').css('display', 'none');
        $('#treeDemo2').css('display', 'none');
        $('.department2').css('margin-top', '-2.95%');
        $('.addPeople2').css('display', 'none');
        $('.moveBtn2').css('margin-top', '3.1%');
        $('.content_wrap2 .resultPeople2').css('display', 'none');
        $('.typeList_group2').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees2').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_department2').css({'background': '#fff', 'color': '#0D165E'});
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
        $('.typeList_group3').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees3').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_department3').css({'background': '#fff', 'color': '#0D165E'});
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
        $('.typeList_group4').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees4').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_department4').css({'background': '#fff', 'color': '#0D165E'});
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
        $('.zTreeDemoBackground5').css('display', 'none');
        $('#treeDemo5').css('display', 'none');
        $('.department5').css('margin-top', '-2.95%');
        $('.addPeople5').css('display', 'none');
        $('.moveBtn5').css('margin-top', '3.1%');
        $('.content_wrap5 .resultPeople5').css('display', 'none');
        $('.typeList_group5').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees5').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_department5').css({'background': '#fff', 'color': '#0D165E'});
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
        $('.typeList_group6').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees6').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_department6').css({'background': '#fff', 'color': '#0D165E'});
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
        $('.typeList_group7').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees7').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_department7').css({'background': '#fff', 'color': '#0D165E'});
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

    $('.typeList_group8').bind('click', function () {
        var groupHtml = arr2Html(groupData1);
        $('.group_list8').html(groupHtml);
        $('.group_list8').css('display', 'block');
        $('.zTreeDemoBackground8').css('display', 'none');
        $('#treeDemo8').css('display', 'none');
        $('.department8').css('margin-top', '-2.95%');
        $('.addPeople8').css('display', 'none');
        $('.moveBtn8').css('margin-top', '3.1%');
        $('.content_wrap8 .resultPeople8').css('display', 'none');
        $('.typeList_group8').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees8').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_department8').css({'background': '#fff', 'color': '#0D165E'});
        arrData8select(groupData1, '#left8');
    });
    // 查询8
    $('.serach_people8').bind('click', function () {
        var serachPeople = $('.serach_people_key8').val();
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
                    arrData8select(data, '#left8');
                }
            }
        });
    });

    //报事派单
    $('.typeList_groupDispatch').bind('click', function () {
        var groupHtml = arr2Html(groupData1);
        $('.group_listDispatch').html(groupHtml);
        $('.group_listDispatch').css('display', 'block');
        $('.zTreeDemoBackgroundDispatch').css('display', 'none');
        $('#treeDemoDispatch').css('display', 'none');
        $('.departmentDispatch').css('margin-top', '-2.95%');
        $('.addPeopleDispatch').css('display', 'none');
        $('.moveBtnDispatch').css('margin-top', '3.1%');
        $('.content_wrapDispatch .resultPeopleDispatch').css('display', 'none');
        $('.typeList_groupDispatch').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employeesDispatch').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_departmentDispatch').css({'background': '#fff', 'color': '#0D165E'});
        arrDataDispatchselect(groupData1, '#leftDispatch');
    });
    // 查询8
    $('.serach_peopleDispatch').bind('click', function () {
        var serachPeople = $('.serach_people_keyDispatch').val();
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
                    arrDataDispatchselect(data, '#leftDispatch');
                }
            }
        });
    });

    $('.typeList_group9').bind('click', function () {
        var groupHtml = arr2Html(groupData1);
        $('.group_list9').html(groupHtml);
        $('.group_list9').css('display', 'block');
        $('.zTreeDemoBackground9').css('display', 'none');
        $('#treeDemo9').css('display', 'none');
        $('.department9').css('margin-top', '-2.95%');
        $('.addPeople9').css('display', 'none');
        $('.moveBtn9').css('margin-top', '3.1%');
        $('.content_wrap9 .resultPeople9').css('display', 'none');
        $('.typeList_group9').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employees9').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_department9').css({'background': '#fff', 'color': '#0D165E'});
        arrData9select(groupData1, '#left9');
    });
    // 查询8
    $('.serach_people9').bind('click', function () {
        var serachPeople = $('.serach_people_key9').val();
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
                    arrData9select(data, '#left9');
                }
            }
        });
    });
    //总部客观
    $('.typeList_groupHQ').bind('click', function () {
        var groupHtml = arr2Html(groupData1);
        $('.group_listHQ').html(groupHtml);
        $('.group_listHQ').css('display', 'block');
        $('.zTreeDemoBackgroundHQ').css('display', 'none');
        $('#treeDemoHQ').css('display', 'none');
        $('.departmentHQ').css('margin-top', '-2.95%');
        $('.addPeopleHQ').css('display', 'none');
        $('.moveBtnHQ').css('margin-top', '3.1%');
        $('.content_wrapHQ .resultPeopleHQ').css('display', 'none');
        $('.typeList_groupHQ').css({'background': '#0D165E', 'color': '#fff'});
        $('.typeList_employeesHQ').css({'background': '#fff', 'color': '#0D165E'});
        $('.typeList_departmentHQ').css({'background': '#fff', 'color': '#0D165E'});
        arrDataHQselect(groupData1, '#leftDispatch');
    });
    // 总部客观
    $('.serach_peopleHQ').bind('click', function () {
        var serachPeople = $('.serach_people_keyHQ').val();
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
                    arrDataHQselect(data, '#leftHQ');
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
    var waitLists8 = {};
    var waitListsDispatch = {};
    var waitLists9 = {};
    var waitListsHQ = {};
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
    var setting8 = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter8,
        },
        callback: {
            onClick: onClick8
        }
    };
    //报事派单
    var settingDispatch = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filterDispatch,
        },
        callback: {
            onClick: onClickDispatch
        }
    };

    var setting9 = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter9,
        },
        callback: {
            onClick: onClick9
        }
    };
    //总部客观
    var settingHQ = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filterHQ,
        },
        callback: {
            onClick: onClickHQ
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

    function onClick8(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left8'
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrData8select(childrenArr, leftId);
        })
    }

    //报事派单
    function onClickDispatch(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#leftDispatch'
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrDataDispatchselect(childrenArr, leftId);
        })
    }

    //
    function onClick9(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left9'
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrData9select(childrenArr, leftId);
        })
    }
    //总部客观
    function onClickHQ(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#leftHQ'
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrDataHQselect(childrenArr, leftId);
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
    function filter8(treeId, parentNode, childNodes) {
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
            waitLists8['id' + parentNode.id] = arr;
        } else {
            waitLists8['idRoot'] = arr;
        }
        return childNodes;
    }

    //报事派单
    function filterDispatch(treeId, parentNode, childNodes) {
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
            waitListsDispatch['id' + parentNode.id] = arr;
        } else {
            waitListsDispatch['idRoot'] = arr;
        }
        return childNodes;
    }

    function filter9(treeId, parentNode, childNodes) {
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
            waitLists9['id' + parentNode.id] = arr;
        } else {
            waitLists9['idRoot'] = arr;
        }
        return childNodes;
    }
//总部客观
    function filterHQ(treeId, parentNode, childNodes) {
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
            waitListsHQ['id' + parentNode.id] = arr;
        } else {
            waitListsHQ['idRoot'] = arr;
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
        $(id).append(htmlGroup).append(htmlPerson).append(htmlDepartment);
    }

    //方法
    function arrData2select(arr, id) {
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
        $(id).append(htmlGroup).append(htmlPerson).append(htmlDepartment);
    }
    //方法
    function arrData3select(arr, id) {
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
        $(id).append(htmlGroup).append(htmlPerson).append(htmlDepartment);
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
        $(id).append(htmlGroup).append(htmlPerson).append(htmlDepartment);
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

    function arrData8select(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right8' && arr != selectedData8) {
            arr = selectedData8.concat(arr)
            selectedData8 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson8" ">' + '</optgroup>';
        var htmlGroup = ')"  id="htmlGroup8" ">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment8" ">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData8.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left8') {
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

    //报事派单
    function arrDataDispatchselect(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#rightDispatch' && arr != selecteDispatchData) {
            arr = selecteDispatchData.concat(arr)
            selecteDispatchData = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPersonDispatch" ">' + '</optgroup>';
        var htmlGroup = ')"  id="htmlGroupDispatch" ">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartmentDispatch" ">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selecteDispatchData.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#leftDispatch') {
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

    function arrData9select(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right9' && arr != selectedData9) {
            arr = selectedData9.concat(arr)
            selectedData9 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson9" ">' + '</optgroup>';
        var htmlGroup = ')"  id="htmlGroup9" ">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment9" ">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData9.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left9') {
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
//总部客观
    function arrDataHQselect(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#rightHQ' && arr != selecteHQ) {
            arr = selecteHQ.concat(arr)
            selecteHQ = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPersonHQ" ">' + '</optgroup>';
        var htmlGroup = ')"  id="htmlGroupHQ" ">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartmentHQ" ">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selecteHQ.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#leftHQ') {
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
        $('#btnR8').bind('click', function () {
            // alert(111);
            wait8selected();
        });
        $('#btnD8').bind('click', function () {
            selected8wait();
        });
        //报事派单
        $('#btnRDispatch').bind('click', function () {
            waitDispatchSelected();
        });
        $('#btnDDispatch').bind('click', function () {
            selectedDispatchWait();
        });

        $('#btnR9').bind('click', function () {
            // alert(111);
            wait9selected();
        });
        $('#btnD9').bind('click', function () {
            selected9wait();
        });
        //总部客观
        $('#btnRHQ').bind('click', function () {
            waitHQSelected();
        });
        $('#btnDHQ').bind('click', function () {
            selectedHQWait();
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
        selectedData = arrSubtraction(selectedData, arr)
        $('#right #htmlDepartment')[0].label = "部门(" + $('#right').find('.htmlDepartment').length + ")";
        $('#right #htmlPerson')[0].label = "员工(" + $('#right').find('.htmlPerson').length + ")";
        $('#right #htmlGroup')[0].label = "群组(" + $('#right').find('.htmlGroup').length + ")";
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
        arrData2select(arr, '#right2');
    }
    //左移方法2
    function selected2wait() {
        var optionArr = $('#right2 option:selected');
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
        selectedData2 = arrSubtraction(selectedData2, arr)
        $('#right2 #htmlPerson2')[0].label = "员工(" + $('#right2').find('.htmlPerson').length + ")";
        $('#right2 #htmlDepartment2')[0].label = "部门(" + $('#right2').find('.htmlDepartment').length + ")";
        $('#right2 #htmlGroup2')[0].label = "群组(" + $('#right2').find('.htmlGroup').length + ")";
        var optionArrLeft = $('#left2 option');
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
            arrLeft[i] = {name: optionArrLeft[i].value, id:id, type: className}
        }
        ;
        arr = arrLeft.concat(arr)
        arrData2select(arr, '#left2')
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
        selectedData3 = arrSubtraction(selectedData3, arr)
        $('#right3 #htmlPerson3')[0].label = "员工(" + $('#right3').find('.htmlPerson').length + ")";
        $('#right3 #htmlDepartment3')[0].label = "部门(" + $('#right3').find('.htmlDepartment').length + ")";
        $('#right3 #htmlGroup3')[0].label = "群组(" + $('#right3').find('.htmlGroup').length + ")";
        var optionArrLeft = $('#left3 option');
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
        selectedData5 = arrSubtraction(selectedData5, arr)
        $('#right5 #htmlPerson5')[0].label = "员工(" + $('#right5').find('.htmlPerson').length + ")";
        $('#right5 #htmlDepartment5')[0].label = "部门(" + $('#right5').find('.htmlDepartment').length + ")";
        $('#right5 #htmlGroup5')[0].label = "群组(" + $('#right5').find('.htmlGroup').length + ")";
        var optionArrLeft = $('#left5 option');
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
    //右移方法8
    function wait8selected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left8 option:selected');
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
        arrData8select(arr, '#right8');
    }
    //左移方法8
    function selected8wait() {
        var optionArr = $('#right8 option:selected');
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
        selectedData8 = arrSubtraction(selectedData8, arr)
        $('#right8 #htmlPerson8')[0].label = "员工(" + $('#right8').find('.htmlPerson').length + ")";
        $('#right8 #htmlDepartment8')[0].label = "部门(" + $('#right8').find('.htmlDepartment').length + ")";
        $('#right8 #htmlGroup8')[0].label = "群组(" + $('#right8').find('.htmlGroup').length + ")";
        var optionArrLeft = $('#left8 option');
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
        arrData8select(arr, '#left8')
    }
    //报事派单右移方法
    function waitDispatchSelected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#leftDispatch option:selected');
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
        arrDataDispatchselect(arr, '#rightDispatch');
    }
    //报事派单左移方法
    function selectedDispatchWait() {
        var optionArr = $('#rightDispatch option:selected');
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
        selecteDispatchData = arrSubtraction(selecteDispatchData, arr)
        $('#rightDispatch #htmlPersonDispatch')[0].label = "员工(" + $('#rightDispatch').find('.htmlPerson').length + ")";
        $('#rightDispatch #htmlDepartmentDispatch')[0].label = "部门(" + $('#rightDispatch').find('.htmlDepartment').length + ")";
        $('#rightDispatch #htmlGroupDispatch')[0].label = "群组(" + $('#rightDispatch').find('.htmlGroup').length + ")";
        var optionArrLeft = $('#leftDispatch option');
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
        arrDataDispatchselect(arr, '#leftDispatch')
    }
    //右移方法9
    function wait9selected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left9 option:selected');
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
        arrData9select(arr, '#right9');
    }
    //左移方法9
    function selected9wait() {
        var optionArr = $('#right9 option:selected');
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
        selectedData9 = arrSubtraction(selectedData9, arr)
        $('#right9 #htmlPerson9')[0].label = "员工(" + $('#right9').find('.htmlPerson').length + ")";
        $('#right9 #htmlDepartment9')[0].label = "部门(" + $('#right9').find('.htmlDepartment').length + ")";
        $('#right9 #htmlGroup9')[0].label = "群组(" + $('#right9').find('.htmlGroup').length + ")";
        var optionArrLeft = $('#left9 option');
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
        arrData9select(arr, '#left9')
    }
    //总部客关右移方法
    function waitHQSelected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#leftHQ option:selected');
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
        arrDataHQselect(arr, '#rightHQ');
    }
    //总部客关左移方法
    function selectedHQWait() {
        var optionArr = $('#rightHQ option:selected');
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
        selecteHQ = arrSubtraction(selecteHQ, arr)
        $('#rightHQ #htmlPersonHQ')[0].label = "员工(" + $('#rightHQ').find('.htmlPerson').length + ")";
        $('#rightHQ #htmlDepartmentHQ')[0].label = "部门(" + $('#rightHQ').find('.htmlDepartment').length + ")";
        $('#rightHQ #htmlGroupHQ')[0].label = "群组(" + $('#rightHQ').find('.htmlGroup').length + ")";
        var optionArrLeft = $('#leftHQ option');
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
        arrDataHQselect(arr, '#leftHQ')
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
        $.fn.zTree.init($("#treeDemo8"), setting8);
        $.fn.zTree.init($("#treeDemoDispatch"), settingDispatch);
        $.fn.zTree.init($("#treeDemo9"), setting9);
        $.fn.zTree.init($("#treeDemoHQ"), settingHQ);
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
    };

    // 提交选择1
    $('.confirm').bind('click', function () {
        // alert(111);
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
        var liShow = $('#right2').html();
        $('.selectResult2').html(liShow);
        $('.selectResult2').find('option').addClass('floatSelectResult');
        $('#htmlGroup2').css('display', 'none');
        $('#htmlPerson2').css('display', 'none');
        $('#htmlDepartment2').css('display', 'none');
        var tpstaff = [], tpagency = [], tporganize = [];
        var i = j = k = 0;
        $("#right2 option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tpagency[i] = $(this).attr("data-id");
                i++;
            } else if ($(this).attr("class") == 'htmlGroup') {
                tporganize[j] = $(this).attr("data-id");
                j++;
            } else {
                tpstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#plumbingStaffs').val(tpstaff.join());
        $('#plumbingAgency').val(tpagency.join());
        $('#plumbingOrganizes').val(tporganize.join());
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
        // alert(111);
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
        // alert(111);
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
        var tstaff = [], tagency = [], torganize = [];
        var i = j = k = 0;
        $("#right6 option").each(function () {
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
        $('#closeStaffs').val(tstaff.join());
        $('#closeAgencys').val(tagency.join());
        $('#closeOrganizes').val(torganize.join());
    });
    // 提交选择7
    $('.confirm7').bind('click', function () {
        var liShow = $('#right7').html();
        $('.selectResult7').html(liShow);
        $('.selectResult7').find('option').addClass('floatSelectResult');
        $('#htmlGroup7').css('display', 'none');
        $('#htmlPerson7').css('display', 'none');
        $('#htmlDepartment7').css('display', 'none');
        var tstaff = [], tagency = [], torganize = [];
        var i = j = k = 0;
        $("#right7 option").each(function () {
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
        $('#checkStaffs').val(tstaff.join());
        $('#checkAgencys').val(tagency.join());
        $('#checkOrganizes').val(torganize.join());
    });
    // 提交选择8
    $('.confirm8').bind('click', function () {
        var liShow = $('#right8').html();
        $('.selectResult8').html(liShow);
        $('.selectResult8').find('option').addClass('floatSelectResult');
        $('#htmlGroup8').css('display', 'none');
        $('#htmlPerson8').css('display', 'none');
        $('#htmlDepartment8').css('display', 'none');
        var tstaff = [], tagency = [], torganize = [];
        var i = j = k = 0;
        $("#right8 option").each(function () {
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
        $('#qualityStaffs').val(tstaff.join());
        $('#qualityAgencys').val(tagency.join());
        $('#qualityOrganizes').val(torganize.join());
    });
    //报事派单 提交选择
    $('.confirmDispatch').bind('click', function () {
        var liShow = $('#rightDispatch').html();
        $('.selectResultDispatch').html(liShow);
        $('.selectResultDispatch').find('option').addClass('floatSelectResult');
        $('#htmlGroupDispatch').css('display', 'none');
        $('#htmlPersonDispatch').css('display', 'none');
        $('#htmlDepartmentDispatch').css('display', 'none');
        var tstaff = [], tagency = [], torganize = [];
        var i = j = k = 0;
        $("#rightDispatch option").each(function () {
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
        $('#dispatchSheetStaffs').val(tstaff.join());
        $('#dispatchSheetAgencys').val(tagency.join());
        $('#dispatchSheetOrganizes').val(torganize.join());
    });
    // 提交选择9
    $('.confirm9').bind('click', function () {
        var liShow = $('#right9').html();
        $('.selectResult9').html(liShow);
        $('.selectResult9').find('option').addClass('floatSelectResult');
        $('#htmlGroup9').css('display', 'none');
        $('#htmlPerson9').css('display', 'none');
        $('#htmlDepartment9').css('display', 'none');
        var tstaff = [], tagency = [], torganize = [];
        var i = j = k = 0;
        $("#right9 option").each(function () {
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
        $('#secondStaffs').val(tstaff.join());
        $('#secondAgencys').val(tagency.join());
        $('#secondOrganizes').val(torganize.join());
    });
    //总部客观 提交选择
    $('.confirmHQ').bind('click', function () {
        var liShow = $('#rightHQ').html();
        $('.selectResultHQ').html(liShow);
        $('.selectResultHQ').find('option').addClass('floatSelectResult');
        $('#htmlGroupHQ').css('display', 'none');
        $('#htmlPersonHQ').css('display', 'none');
        $('#htmlDepartmentHQ').css('display', 'none');
        var tstaff = [], tagency = [], torganize = [];
        var i = j = k = 0;
        $("#rightHQ option").each(function () {
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
        $('#HQObjectiveStaffs').val(tstaff.join());
        $('#HQObjectiveAgencys').val(tagency.join());
        $('#HQObjectiveOrganizes').val(torganize.join());
    });
</script>
</body>
</html>