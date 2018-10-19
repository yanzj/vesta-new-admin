<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2017/12/21
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%response.setHeader("cache-control", "public"); %>
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
        <c:if test="${adminDTO.category eq '3'}">
            $(function(){
                console.log("sqq")
                $("#007000040000").addClass("active");
                $("#007000040000").parent().parent().addClass("in");
                $("#007000040000").parent().parent().parent().parent().addClass("active");
            })
            new WOW().init();
        </c:if>
        <c:if test="${adminDTO.category eq '2'}">
            $(function(){
                console.log("sqq")
                $("#007100040000").addClass("active");
                $("#007100040000").parent().parent().addClass("in");
                $("#007100040000").parent().parent().parent().parent().addClass("active");
            })
            new WOW().init();
        </c:if>
        <c:if test="${adminDTO.category eq '1'}">
            $(function(){
                console.log("sqq")
                $("#007300040000").addClass("active");
                $("#007300040000").parent().parent().addClass("in");
                $("#007300040000").parent().parent().parent().parent().addClass("active");
            })
            new WOW().init();
        </c:if>

    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <style>
        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 100px;
            height: 120px;
        }

        .sidebar ul li {
            border-bottom: 0;
        }
        .addPeople{
            position: absolute;
            margin-left: 0;
            top: 6.3rem;
            left: 1.2rem;
        }
        .addPeople input{
            width: 15rem;
        }
        .role_select{
            float: inherit;
        }
    </style>
    <%-- zTree --%>
    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exhide-3.5.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <c:if test="${adminDTO.category eq '3'}">
        <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007000040000" username="${authPropertystaff.staffName}"/>
    </c:if>
    <c:if test="${adminDTO.category eq '2'}">
        <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007100040000" username="${authPropertystaff.staffName}"/>
    </c:if>
    <c:if test="${adminDTO.category eq '1'}">
        <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007300040000" username="${authPropertystaff.staffName}"/>
    </c:if>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <%--搜索条件结束--%>
        </div>
    </div>

    <div class="table-responsive bs-example widget-shadow">
        <div class="row">
            <div class="content_wrap col-md-3">
                <div class="zTreeDemoBackground left">
                    <ul id="treeDemo11" class="ztree"></ul>
                </div>
            </div>
            <input type="hidden" name="authStaffId" id="authStaffs" value="">
            <input type="hidden" name="deleAgencyId" id="deleAgencyId" value="">
            <input type="hidden" name="authRoleIds" id="authRoles" value=""><!--角色-->
            <div class="table-responsive  widget-shadow col-md-9" style="float:left;margin-top:0">
                <div>机构名称：${agencyList.authAgencyName}</div>
                <div>机构级别：${agencyList.agencyType}</div>
                <div>备注：</div>
                <table class="tableStyle  col-md-12">
                    <thead>
                    <tr>
                        <th>排序号</th>
                        <th>角色名称</th>
                        <th style="width: 350px">授权人员</th>
                        <th>最后修改时间</th>
                        <th>授权操作</th>
                    </tr>
                    </thead>
                    <tbody class="publicTbody">
                    <c:forEach items="${agencyList.list}" var="list" varStatus="as">
                        <tr>
                            <td><b>${(webPage.pageIndex-1)*20+as.index + 1}</b></td>
                            <td>${list.authRoleName}</td>
                            <td>${list.userListName}</td>
                            <td>${list.updaTime}</td>
                            <td>
                                <c:if test="${function.sth40020028 eq 'Y' ||function.esh40020090 eq 'Y' || function.qch40010121 eq 'Y'}">
                                    <span class="role_select" data-toggle="modal" data-target="#myModal1" onclick="toCheckRole('${agencyAdminDTO.agencyId}','${list.authRoleId}','${list.authRoleName}')">授权</span>
                                    <%--<a style="cursor: pointer" href="../authAgency/AuthAgencyRole.html?agencyId=${agencyAdminDTO.agencyId}&authRoleId=${list.authRoleId}&authRoleName=${list.authRoleName}" class="a3"><span class="span1">授权</span></a>--%>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <m:pager pageIndex="${webPage.pageIndex}"
                         pageSize="${webPage.pageSize}"
                         recordCount="${webPage.recordCount}"
                         submitUrl="${pageContext.request.contextPath }../authAgency/AuthAgencyProject.html?pageIndex={0}&agencyId=${agencyAdminDTO.agencyId}&category=${agencyAdminDTO.category}"/>
            </div>
        </div>
    </div>

</div>
</div>
</div>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>


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
                                <div class="btnB" style="border: none;background: none"></div>
                                <div class=" col-md-offset-5" style="display: block;margin-top: 30px;width: 40rem;">
                                    <button type="submit" class="confirm" class="close" data-dismiss="modal" onclick="toSave()"
                                            aria-hidden="true">确认
                                    </button>
                                    <button class="cancel" data-dismiss="modal" onclick="toDelete()">取消</button>
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




<script type="text/javascript">

    var ckeckCategory=${adminDTO.category}
    // zTree 的参数配置,深入使用请参考 API 文档(setting 配置详解)
    var setting = {
        data: {
            simpleData: {
                enable: true  //开启简单数据模式
            }
        },
        callback: {
            onClick: onClick
        }
    };
    var code;
    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>"+str+"</li>");
    }

    if(ckeckCategory=='2'){
        $(document).ready(function () {
            $.ajax({
                url: "../authAgency/fullAgencyES",
                dataType: "json",
                success: function (result) {
                    $.fn.zTree.init($("#treeDemo11"), setting, result);
                    var cId = "${agencyAdminDTO.agencyId}";
                    var treeO = $.fn.zTree.getZTreeObj("treeDemo11");
                    var nodes = treeO.getNodeByParam('id', cId, null);
                    treeO.selectNode(nodes);
                    treeO.expandNode(nodes, true, false, true);
                }
            });
            showCode();
        });
    }
    if(ckeckCategory=='3'){
        $(document).ready(function(){
            $.ajax({
                url: "../authAgency/authFullAgency",
                dataType: "json",
                success: function (result) {
                    $.fn.zTree.init($("#treeDemo11"), setting, result);
                    var cId = "${agencyAdminDTO.agencyId}";
                    var treeO = $.fn.zTree.getZTreeObj("treeDemo11");
                    var nodes = treeO.getNodeByParam('id', cId, null);
                    treeO.selectNode(nodes);
                    treeO.expandNode(nodes, true, false, true);
                }
            });
            showCode();
        });
    }
    if(ckeckCategory=='1'){
        $(document).ready(function(){
            $.ajax({
                url: "../authAgency/fullAgencyQC",
                dataType: "json",
                success: function (result) {
                    $.fn.zTree.init($("#treeDemo11"), setting, result);
                    var cId = "${agencyAdminDTO.agencyId}";
                    var treeO = $.fn.zTree.getZTreeObj("treeDemo11");
                    var nodes = treeO.getNodeByParam('id', cId, null);
                    treeO.selectNode(nodes);
                    treeO.expandNode(nodes, true, false, true);
                }
            });
            showCode();
        });
    }




    function onClick(event, treeId, treeNode, clickFlag) {
        if(ckeckCategory=='3'){
            window.location.href = "../authAgency/AuthAgencyProject.html?agencyId=" + treeNode.id +"&category=3";
        }
        if(ckeckCategory=='2'){
            window.location.href = "../authAgency/AuthAgencyProject.html?agencyId=" + treeNode.id +"&category=2";
        }
        if(ckeckCategory=='1'){
            window.location.href = "../authAgency/AuthAgencyProject.html?agencyId=" + treeNode.id +"&category=1";
        }
    }

    function toCheckRole(agencyId,authRoleId,authRoleName){
        var agencyId=agencyId;
        var authRoleId=authRoleId;
        var authRoleName=authRoleName;
        $("#right").empty();
        $("#authRoles").val(authRoleId+'|'+agencyId);
        $("#deleAgencyId").val(agencyId);
        if('3'==ckeckCategory){
            $.ajax({
                url: "../authAgency/getAuthAgencyRole",
                type: 'post',
                async: false,
                dataType: "json",
                data: {
                    "agencyId":agencyId,
                    "authRoleId": authRoleId
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
                        console.log(data);
//                    var option = "";
                        if (data != null) {
                            $("#right").empty();
                            var htmlPersonNum = 0;
                            var htmlPerson = ')" id="htmlPerson">' + '</optgroup>';

                            for (var prop in data) {
                                if (!isNaN(data[prop])) {
                                } else {
                                    if (data.hasOwnProperty(prop)) {
                                        htmlPersonNum++;
                                        htmlPerson = htmlPerson + '<option class="htmlPerson"  value="' + prop + '" data-id="' + prop + '">' + data[prop] + '</option>'
                                    }
                                }
                            }
                            htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
                            $("#right").append(htmlPerson);
                        }
                    }
                }
            });
        }
        if('2'==ckeckCategory){
            $.ajax({
                url: "../authAgency/getESAuthAgencyRole",
                type: 'post',
                async: false,
                dataType: "json",
                data: {
                    "agencyId":agencyId,
                    "authRoleId": authRoleId,
                    "category":ckeckCategory
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
                        console.log(data);
//                    var option = "";
                        if (data != null) {
                            $("#right").empty();
                            var htmlPersonNum = 0;
                            var htmlPerson = ')" id="htmlPerson">' + '</optgroup>';

                            for (var prop in data) {
                                if (!isNaN(data[prop])) {
                                } else {
                                    if (data.hasOwnProperty(prop)) {
                                        htmlPersonNum++;
                                        htmlPerson = htmlPerson + '<option class="htmlPerson"  value="' + prop + '" data-id="' + prop + '">' + data[prop] + '</option>'
                                    }
                                }
                            }
                            htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
                            $("#right").append(htmlPerson);
                        }
                    }
                }
            });
        }
        if('1'==ckeckCategory){
            $.ajax({
                url: "../authAgency/getQCAuthAgencyRole",
                type: 'post',
                async: false,
                dataType: "json",
                data: {
                    "agencyId":agencyId,
                    "authRoleId": authRoleId,
                    "category":ckeckCategory
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
                        console.log(data);
//                    var option = "";
                        if (data != null) {
                            $("#right").empty();
                            var htmlPersonNum = 0;
                            var htmlPerson = ')" id="htmlPerson">' + '</optgroup>';

                            for (var prop in data) {
                                if (!isNaN(data[prop])) {
                                } else {
                                    if (data.hasOwnProperty(prop)) {
                                        htmlPersonNum++;
                                        htmlPerson = htmlPerson + '<option class="htmlPerson"  value="' + prop + '" data-id="' + prop + '">' + data[prop] + '</option>'
                                    }
                                }
                            }
                            htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
                            $("#right").append(htmlPerson);
                        }
                    }
                }
            });
        }

    }

</script>

<script>
    //从后台拿到右边的数据
    var selectedData = [];
    var sview = [];
    $('#myModal1').on('shown.bs.modal', function (e) {

        $("#right option.htmlPerson").each(function (i) {
            if (this.value != "") {
                selectedData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
                sview[i] = this.value;
            }
        });
        arrData1select(selectedData, '#right');
        $('#authStaffs').val(sview.join());
    })



    // 点击部门
    var data2 = [];
    $('.typeList_department').bind('click', function () {
        $('.group_list').css('display', 'none');
        $('.zTreeDemoBackground').css('display', 'block');
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
        $('.zTreeDemoBackground').css('display', 'none');
        $('.moveBtn').css('margin-top', '-1.54%');
        $('.typeList_employees').css({'background':'#0D165E','color':'#fff'});
        $('.typeList_department').css({'background':'#fff','color':'#0D165E'});
        $('#left').html('');
    });

    //点击角色
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
    if('2'==ckeckCategory){
        $('.serach_people').bind('click', function () {
            var serachPeople = $('.serach_people_key').val();
            $.ajax({
                url: '/projectAccredit/getOwnerUserByName',
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
    }
    // 查询人员
    if('3'==ckeckCategory){
        $('.serach_people').bind('click', function () {
            var serachPeople = $('.serach_people_key').val();
            $.ajax({
                url: '/userAccredit/getAllUserByName',
                data: {
                    staffName: serachPeople,
                    category:ckeckCategory
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
    }
    if('1'==ckeckCategory){
        $('.serach_people').bind('click', function () {
            var serachPeople = $('.serach_people_key').val();
            $.ajax({
                url: '/userAccredit/getAllUserByName',
                data: {
                    staffName: serachPeople,
                    category:ckeckCategory
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
    }


//    //查询角色
//    $('.serach_project').bind('click', function () {
//        var serachProject = $('.serach_project_key').val();
//        $.ajax({
//            url: '/userAccredit/getRoleByNameAndProjectId',
//            data: {
//                roleName: serachProject
//            },
//            type: 'get',
//            dataType: 'json',
//            success: function (result) {
//                if (result.code != 0) {
//                    alert(result.msg)
//                } else {
//                    var data = result.data.map(function (val) {
//                        return selectFilter1(val);
//                    })
//                    console.log(data);
//                    arrData2select(data, '#left2');
//                }
//            }
//        });
//
//    });


    function selectFilter(obj) {
        return {
            agencyType: "1",
            icon: null,
            id: obj.outerStaffId,
            isParent: false,
            name: obj.outerStaffName,
            pId: null,
            type: "3"
        }
    }
    function selectFilter1(obj) {
        return {
            agencyType: "1",
            icon: null,
            id: obj.outerStaffId,
            isParent: false,
            name: obj.outerStaffName,
            pId: null,
            type: "2"
        }
    }

    var waitLists = {};
    var waitLists2 = {};
    var settingUser = {
        data: {
            simpleData: {
                enable: true
            }
        },
        async: {
            enable: true,
            dataFilter: filter,
        },
        callback: {
            onClick: userOnClick
        }
    };

    function userOnClick(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left'
        //是最后一个  找后台要下一级
        $.getJSON('/userAccredit/getAllUserByAgencyId?category='+ckeckCategory+'&id=' + treeNode.id , function (res) {
            childrenArr = res.data;
            arrData1select(childrenArr, leftId);
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
//        $("#right option").each(function(){  //遍历所有option
//            var user = $(this).attr("data-id");   //获取option值
//            alert(user);
//        })
        var htmlPersonNum = 0;
        var htmlPerson = ')" id="htmlPerson">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left') {
                continue;
            }
            ;
            if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        $(id).html('');
        $(id).append(htmlPerson);
    }

    function select() {
        $('#btnR').bind('click', function () {
            wait1selected();
        });
        $('#btnD').bind('click', function () {
            selected1wait();
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
        selectedData = arrSubtraction(selectedData, arr)
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

    select();
    $(document).ready(function () {
        if(ckeckCategory == 1){
            $.ajax({
                url: "../clientAccredit/getOAAgencyMessage",
                dataType: "json",
                async: false,
                cache: false,
                data: {
                    "category":ckeckCategory
                },
                success: function (result) {
                    $.fn.zTree.init($("#treeDemo"), settingUser, result);
                }
            });
        }else{
            $.ajax({
                url: "../userAccredit/getAllOAAgencyMessage",
                dataType: "json",
                async: false,
                cache: false,
                data: {
                    "category":ckeckCategory
                },
                success: function (result) {
                    $.fn.zTree.init($("#treeDemo"), settingUser, result);
                }
            });
        }
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

    //保存
    function toSave(){
        var authStaffId='';
        $("#right option").each(function(){  //遍历所有option
            var user = $(this).attr("data-id");   //获取option值
            if(user!=''){
                authStaffId+=user+',';
            }
        })
        authStaffId=authStaffId.substring(0,authStaffId.lastIndexOf(","));
        var authRoleIds=$("#authRoles").val();
        if(ckeckCategory=='3'){
            $.ajax({
                url: "/authAgency/saveUserRoleRelation",
                type: 'post',
                async: false,
                dataType: "json",
                data: {
                    "authRoleIds": authRoleIds,
                    "authStaffId":authStaffId,
                    "category":ckeckCategory
                },
                success: function (data) {
                    if (data.check == 1) {
                        var agencyId= $(" #deleAgencyId ").val();
                        window.location.href = "../authAgency/AuthAgencyProject.html?agencyId=" + agencyId +"&category=3";
                    }else if(data.check == 2){
                        alert("对不起，该角色下有关联的授权人员，请解除关联后执行该操作！");
                    }else{
                        alert("对不起，操作失败！");
                    }
                }
            });
        }
        if(ckeckCategory=='2'){
            $.ajax({
                url: "/authAgency/saveUserRoleRelation",
                type: 'post',
                async: false,
                dataType: "json",
                data: {
                    "authRoleIds": authRoleIds,
                    "authStaffId":authStaffId,
                    "category":ckeckCategory
                },
                success: function (data) {
                    if (data.check == 1) {
                        var agencyId= $(" #deleAgencyId ").val();
                        window.location.href = "../authAgency/AuthAgencyProject.html?agencyId=" + agencyId +"&category=2";
                    }else if(data.check == 2){
                        alert("对不起，该角色下有关联的授权人员，请解除关联后执行该操作！");
                    }else{
                        alert("对不起，操作失败！");
                    }
                }
            });
        }
        if(ckeckCategory=='1'){
            $.ajax({
                url: "/authAgency/saveUserRoleRelation",
                type: 'post',
                async: false,
                dataType: "json",
                data: {
                    "authRoleIds": authRoleIds,
                    "authStaffId":authStaffId,
                    "category":ckeckCategory
                },
                success: function (data) {
                    if (data.check == 1) {
                        var agencyId= $(" #deleAgencyId ").val();
                        window.location.href = "../authAgency/AuthAgencyProject.html?agencyId=" + agencyId +"&category=1";
                    }else if(data.check == 2){
                        alert("对不起，该角色下有关联的授权人员，请解除关联后执行该操作！");
                    }else{
                        alert("对不起，操作失败！");
                    }
                }
            });
        }
    }


    //保存
    function toDelete(){
        var agencyId= $(" #deleAgencyId ").val();
        if(ckeckCategory=='2'){
            window.location.href = "../authAgency/AuthAgencyProject.html?agencyId=" + agencyId +"&category=2";
        }
        if(ckeckCategory=='3'){
            window.location.href = "../authAgency/AuthAgencyProject.html?agencyId=" + agencyId +"&category=3";
        }
        if(ckeckCategory=='1'){
            window.location.href = "../authAgency/AuthAgencyProject.html?agencyId=" + agencyId +"&category=1";
        }
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>
