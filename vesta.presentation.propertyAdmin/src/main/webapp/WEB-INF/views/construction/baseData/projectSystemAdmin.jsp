<%--
  设置人员项目关系
  Created by IntelliJ IDEA.
  User: yuanyn
  Date: 2017/8/28
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
            $("#003100030000").addClass("active");
            $("#003100030000").parent().parent().addClass("in");
            $("#003100030000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="003100030000" username="${authPropertystaff.staffName}"/>
    <div class="container1 userStaffManage">
        <form class="form-horizontal" name="projectInfo" id="projectInfo"
              action="../BaseData/saveAreaRole.html">
            <input type="hidden" name="groupId" value="${groupId}">
            <div class="row">
                <div class="col-md-10 " style=" border-bottom: 1px dashed #ccc;">
                         <div class="newRoleSubmit">
                        <button type="button" class="btn btn-primary" onclick="toSave()">保存</button>
                        <button onclick="history.go(-1)" class="btn btn-primary" for="">关闭</button>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10 role_new_submit2">
                    <table class="table table-bordered">
                        <tbody>
                        <tr style="background-color: #003366;color: #fff;">
                            <td colspan="2">质量管理人员项目相关信息</td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>人员选择</span></td>
                            <td>
                                <input type="hidden" name="securityOfficerDep" id="viewAgencys"><!-- 部门-->
                                <input type="hidden" name="securityOfficerStaff" id="viewStaffs"><!-- 人-->
                                <div class="existence">
                                          <span style="display:inline-block" class="selectResult" id="role_linkRole">
                                            <c:if test="${(projectStaffRelationDTO.groupDepartment) != null && fn:length(projectStaffRelationDTO.groupDepartment)>0}">
                                                <c:forEach items="${projectStaffRelationDTO.groupDepartment}" var="dept">
                                                    ${dept.pRoleName}；
                                                </c:forEach>
                                                <br/>
                                            </c:if>
                                              <c:if test="${projectStaffRelationDTO.groupStaff != null && fn:length(projectStaffRelationDTO.groupStaff)>0}">
                                                  <c:forEach items="${projectStaffRelationDTO.groupStaff}" var="staff">
                                                      ${staff.pRoleName}；
                                                  </c:forEach>
                                              </c:if>
                                          </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal1">选择</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="role_table_projectName" align="center" ><span>项目选择</span></td>
                            <td>
                                <input type="hidden" name="projectBelongArea" id="viewArea">
                                <input type="hidden" name="projectBelongProject" id="viewProjects">
                                <div class="existence">
                                          <span style="display:inline-block" class="selectResult2" id="project_linkProject">
                                            <c:if test="${(projectStaffRelationDTO.groupArea) != null && fn:length(projectStaffRelationDTO.groupArea)>0}">
                                                <c:forEach items="${projectStaffRelationDTO.groupArea}" var="area">
                                                    ${area.pRoleName}；
                                                </c:forEach>
                                                <br/>
                                            </c:if>
                                            <c:if test="${(projectStaffRelationDTO.groupProject) != null && fn:length(projectStaffRelationDTO.groupProject)>0}">
                                                <c:forEach items="${projectStaffRelationDTO.groupProject}" var="project">
                                                    ${project.pRoleName}；
                                                </c:forEach>
                                                <br/>
                                            </c:if>
                                          </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal2">选择</span>
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
                areaName: {
                    required: true,
                    minlength: 1,
                    maxlength: 20
                }
            },
            messages: {
                areaName: {
                    required: "请输入区域名称！",
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
                            <div class="col-md-5 addPeople">
                                <input type="text" class="serach_people_key" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people">搜索</button>
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
                                            <c:forEach items="${projectStaffRelationDTO.groupDepartment}" var="dept">
                                                <option class="htmlDepartment"
                                                        value="${dept.pRoleId}">${dept.pRoleName}</option>
                                            </c:forEach>
                                            <c:forEach items="${projectStaffRelationDTO.groupStaff}" var="staff">
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
                                <div class="typeList_area">区域</div>
                                <div class="typeList_project">项目</div>
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
                                <input type="text" class="serach_project_key" placeholder="关键字添加项目" value="">
                                <button type="submit" class="serach_project">搜索</button>
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
                            <div class=" col-md-2 moveBtn2">
                                <button id="btnR2">添加</button>
                                <button id="btnD2">移除</button>
                            </div>
                            <div class="col-md-3 department2 ">
                                <h5 class="h52">已选列表</h5>
                                <form class="submitForm" id="form2" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <select id="right2" multiple="multiple">
                                            <c:forEach items="${projectStaffRelationDTO.groupArea}" var="area">
                                                <option class="htmlDepartment2"
                                                        value="${area.pRoleId}">${area.pRoleName}</option>
                                            </c:forEach>
                                            <c:forEach items="${projectStaffRelationDTO.groupProject}" var="project">
                                                <option class="htmlPerson2"
                                                        value="${project.pRoleId}">${project.pRoleName}</option>
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
    var selectedData = [] ,selectedData2 = [];
    var sview = [], aview = [] ,sview2 = [] ,aview2 = [];
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
            aview2[i] = this.value;
        }
    });
    $("#right2 option.htmlPerson2").each(function (i) {
        if (this.value != "") {
            selectedData2.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            sview2[i] = this.value;
        }
    });
    arrData1select(selectedData, '#right');
    $('#viewStaffs').val(sview.join());
    $('#viewAgencys').val(aview.join());
    arrData2select(selectedData2, '#right2');
    $('#viewProjects').val(sview2.join());
    $('#viewArea').val(aview2.join());

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

    //点击区域
    $('.typeList_area').bind('click', function () {
        $('.group_list2').css('display', 'none');
        $('.zTreeDemoBackground2').css('display', 'block');
        $('#treeDemo2').css('display', 'block');
        $('.department2').css('margin-top', '-2.95%');
        $('.addPeople2').css('display', 'none');
        $('.moveBtn2').css('margin-top', '3.1%');
        $('.content_wrap2 .resultPeople2').css('display', 'none');
        $('.typeList_area').css({'background':'#0D165E','color':'#fff'});
        $('.typeList_project').css({'background':'#fff','color':'#0D165E'});
        $('#left2').html('');
    });
    //点击项目
    $('.typeList_project').bind('click', function () {
        $('.group_list2').css('display', 'none');
        $('.addPeople2').css('display', 'block');
        $('.department2').css('margin-top', ' -2.95%');
        $('.zTreeDemoBackground2').css('display', 'none');
        $('.moveBtn2').css('margin-top', '-1.54%');
        $('.typeList_project').css({'background':'#0D165E','color':'#fff'});
        $('.typeList_area').css({'background':'#fff','color':'#0D165E'});
        $('#left2').html('');
    });
    // 查询人员
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

    //查询项目
    $('.serach_project').bind('click', function () {
        var serachProject = $('.serach_project_key').val();
        $.ajax({
            url: '/BaseData/getProjectByName',
            data: {
                projectName: serachProject
            },
            type: 'get',
            dataType: 'json',
            success: function (result) {
                if (result.code != 0) {
                    alert(result.msg)
                } else {
                    var data = result.data.map(function (val) {
                        return selectFilter1(val);
                    })
                    console.log(data);
                    arrData2select(data, '#left2');
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
    function selectFilter1(obj) {
        return {
            agencyType: "1",
            icon: null,
            id: obj.id,
            isParent: false,
            name: obj.projectName,
            pId: null,
            type: "2"
        }
    }

    var selectedList = []
    var waitLists = {};
    var waitLists2 = {};
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
            url: "/BaseData/initProject",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter2,
        },
        callback: {
            onClick: onClick2
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
        $.getJSON('/BaseData/getNextProject?id=' + treeNode.id , function (res) {
            childrenArr = res.data;
            arrData2select(childrenArr, leftId);
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
    //方法2
    function arrData2select(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right2' && arr != selectedData2) {
            arr = selectedData2.concat(arr)
            selectedData2 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson2 = ')" id="htmlPerson2">' + '</optgroup>';
        var htmlDepartment2 = ')"  id="htmlDepartment2">' + '</optgroup>';
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
                htmlDepartment2 = htmlDepartment2 + '<option class="htmlDepartment2" data-id="' + arr[i].id + '">' + arr[i].name + '</option>';
            } else if (arr[i].type == '2') {
                htmlPersonNum++;
                htmlPerson2 = htmlPerson2 + '<option class="htmlPerson2" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
            }

        }
        ;
        htmlPerson2 = '<optgroup label="项目(' + htmlPersonNum + htmlPerson2;
        htmlDepartment2 = '<optgroup label="区域(' + htmlDepartmentNum + htmlDepartment2;
        $(id).html('');
        $(id).append(htmlPerson2).append(htmlDepartment2);
    }
    function select() {
        $('#btnR').bind('click', function () {
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
            if (optionArr[i].className == 'htmlPerson2') {
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
            if (optionArr[i].className == 'htmlPerson2') {
                className = '2';
            }
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
        $('#right2 #htmlDepartment2')[0].label = "区域(" + $('#right2').find('.htmlDepartment2').length + ")";
        $('#right2 #htmlPerson2')[0].label = "项目(" + $('#right2').find('.htmlPerson2').length + ")";
        var optionArrLeft2 = $('#left2 option');
        var arrLeft2 = [];
        for (var i = 0; i < optionArrLeft2.length; i++) {
            var className = '1';
            if (optionArrLeft2[i].className == 'htmlPerson2') className = '2';
            var id = '';
            if (optionArrLeft2[i].dataset) {
                id = optionArrLeft2[i].dataset.id;
            } else {
                id =optionArrLeft2[i].attributes[1].value;
            }
            arrLeft2[i] = {name: optionArrLeft2[i].value, id:id, type: className}
        }
        arr = arrLeft2.concat(arr);
        arrData2select(arr, '#left2');
    }
    select();
    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting);
        $.fn.zTree.init($("#treeDemo2"), setting2);
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
        var tstaff = [], tagency = [], torganize = [];
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
        var tstaff = [], tagency = [], torganize = [];
        var i = k = 0;
        $("#right2 option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment2') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else if ($(this).attr("class") == 'htmlPerson2') {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#viewProjects').val(tstaff.join());
        $('#viewArea').val(tagency.join());
    });

    //保存
    function toSave(){
        if($("#viewArea").val().replace(/(^s*)|(s*$)/g, "").length == 0 && $("#viewProjects").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请至少选择一个项目！");
            return;
        }
        $("#projectInfo").submit();
    }
</script>
</body>
</html>