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
            $("#007100090000").addClass("active");
            $("#007100090000").parent().parent().addClass("in");
            $("#007100090000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
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
        .typeList .typeList_project, .typeList .typeList_department, .typeList .typeList_employees, .typeList .typeList_area{
            text-align: center;
            border: 1px solid #ccc;
            border-bottom: none;
            cursor: pointer;
            width: 60px;
            float: left;
        }
        label{
            font-size: 14px;
        }
        .inputname{
            padding-left: 10px;
            width: 100%;
            border: none;
            height: 30px;
            background: none;
            font-size: 14px;
        }
    </style>
    <%-- zTree --%>
    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
    <!--进度条-->
    <script src="../static/js/jquery.step.min.js"></script>
    <link href="../static/css/jquery.step.css" type="text/css" rel="stylesheet">
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007100090000" username="${authPropertystaff.staffName}"/>

    <div class="table-responsive bs-example widget-shadow">
        <div class="row">
            <div class="content_wrap col-md-3" style="height: 400px; overflow: scroll">
                <div class="zTreeDemoBackground1 left">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
            <div class="table-responsive widget-shadow col-md-9" style="float:left;margin-top:0">
                <table class="tableStyle  col-md-12">
                    <thead>
                    <!-- 点击部门 显示人员的列表 -->
                    <tr>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>系统账号</th>
                        <th>所属项目</th>
                        <th>APP端同步项目</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="publicTbody">
                    <c:forEach items="${userSyncProjectDTOS}" var="list" varStatus="as">
                        <tr>
                            <td width="5%" height="40px" style="text-align: center">
                                <b>${(webPage.pageIndex-1)*20+as.index + 1}</b>
                            </td>
                            <td width="10%">${list.peoName}</td>
                            <td width="13%">${list.loginName}</td>
                            <td width="32%">${list.proName}</td>
                            <td width="31%">${list.proNameApp}</td>
                            <td width="9%">
                                <a style="cursor: pointer" onClick="updateProject('${list.peoId}','${list.peoName}');" class="a3">
                                    <span class="span1">修改</span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <m:pager pageIndex="${webPage.pageIndex}"
                         pageSize="${webPage.pageSize}"
                         recordCount="${webPage.recordCount}"
                         submitUrl="${pageContext.request.contextPath }../projectAccredit/initProjectUserAccreditManage.html?pageIndex={0}&agencyIdA=${accreditManageDTO.agencyIdA}&agencyNameA=${accreditManageDTO.agencyNameA}&staffNameA=${accreditManageDTO.staffNameA}&userNameA=${accreditManageDTO.userNameA}&sysNameA=${accreditManageDTO.sysNameA}&roleNameA=${accreditManageDTO.roleNameA}&projectNameA=${accreditManageDTO.projectNameA}"/>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
</div>

<!-- 模态框1（Modal）-->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body" style="width: 898px;">
                <div class="container" style=" width: 100%;">
                    <h2>APP项目同步修改</h2>
                    <div class="containerSelect1">
                        <div class="row ">
                            <div class="col-md-6 roel_wrap ">
                                <div class="form-group  col-lg-12">
                                    <input type="hidden" name="userId" id="userId" value="">
                                    <label class="col-sm-3 control-label" for="userName">姓名：</label>
                                    <div class="col-sm-8">
                                        <span><input type="text" name="userName" id="userName" value="" class="inputname" disabled="disabled"></span>
                                    </div>
                                </div>
                                <div class="form-group  col-lg-12">
                                    <label for="roleId" class="col-sm-3 control-label">角色:</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" placeholder="" id="roleId" name="roleId">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="content_wrap col-md-6">
                                <h5>所属项目</h5>
                                <div class="content_wrap col-md-3" style="height: 300px; width: 300px; overflow: scroll">
                                    <div class="zTreeDemoBackground1 left">
                                        <ul id="treeDemo1" class="ztree"></ul>
                                    </div>
                                </div>
                                <%--<div class="zTreeDemoBackground left" style="border: 1px solid #ccc;">--%>
                                    <%--<ul id="treeDemo1" class="ztree"></ul>--%>
                                <%--</div>--%>
                                <%--<div class="people_list"></div>--%>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button  class="confirm" class="close"
                                     aria-hidden="true" onClick="saveOrUpdate()">确认
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

<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">

    function updateProject(id,name){
        $("#userName").append().val(name);
        $("#userId").append().val(id);
        $('#myModal1').modal('show');
        $('#myModal1').on('shown.bs.modal', function (e) {
            $.ajax({
                url: "../projectSync/getRoleByUserId?peoId="+id+"",
                type: 'get',
                async: false,
                dataType: "json",
                success: function (json) {
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("roleId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#roleId").append(option);
                    }
                    $.ajax({
                            url: "../projectSync/getPorjectByInit?userId="+id+"",
                            dataType: "json",
                            success: function (result) {
                                $.fn.zTree.init($("#treeDemo1"), setting1, result);
                                var tree1 = $.fn.zTree.getZTreeObj("treeDemo1");
                                var nodes = tree1.getNodes();
                                for (var i = 0; i < nodes.length; i++) {
                                    tree1.expandNode(nodes, true, false, true);//展开此节点
                                }
                            }
                        }
                    );
                }
            });
        })
    }
    $("#roleId").change(function () {
        var roleId = $("#roleId").val();
        var userId = $("#userId").val();
        $.ajax({
            url: "../projectSync/getPorjectByCheck?roleId="+roleId+"&userId="+userId,
            type: "get",
            async: "false",
            dataType: "json",
            success: function (json) {
                $.fn.zTree.init($("#treeDemo1"), setting1, json);
            }
        });
    });


    $('#myModal1').on('hidden.bs.modal', function (e) {
        $.fn.zTree.destroy("treeDemo1");
    })
    function  saveOrUpdate(){
        var zTree = $.fn.zTree.getZTreeObj("treeDemo1");
        var nodes2 = zTree.getCheckedNodes(true);
        var result='';
        if(nodes2.length==0){
            alert("请选择项目！！");
            return false;
        }
        for (var i = 0; i < nodes2.length; i++) {
            var halfCheck = nodes2[i].getCheckStatus();
            result += nodes2[i].id +',';
        }
        result=result.substring(0,result.lastIndexOf(","));
        var roleId = $(" #roleId option:selected ").val();
        var userId = $(" #userId ").val();
        $.ajax({
            url: "../projectSync/saveUserRoleProject",
            type: 'post',
            async: false,
            dataType: "json",
            data: {
                "category": result,
                "authStaffId":userId,
                "authRoleIds":roleId
            },
            success: function (data) {
                if (data.msg == "OK") {
                    alert("操作成功！");
                    window.location.href = "../projectSync/initProjectSyncForApp.html?agencyIdPro=${agencyIdPro }";
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }

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

    var setting1 = {
        data: {
            simpleData: {
                enable: true  //开启简单数据模式
            }
        },
        check: {
            enable: true
        },
    };
    setting1.check.chkboxType = { "Y" : "s", "N" : "s" };
    var code;
    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>"+str+"</li>");
    }

    $(document).ready(function(){
        $.ajax({
            url: "../projectSync/getProjectAllAgency",
            dataType: "json",
            success: function (result) {
                $.fn.zTree.init($("#treeDemo"), setting, result);
                var cId = "${agencyIdPro }";//回显项目id
                var treeO = $.fn.zTree.getZTreeObj("treeDemo");
                var nodes = treeO.getNodeByParam('id', cId, null);//通过项目id确定节点（查询 id 为cId 的节点）
                treeO.selectNode(nodes);//选中此节点（回显）
                treeO.expandNode(nodes, true, false, true);//展开此节点
            }
        });
        showCode();
    });

    function onClick(event, treeId, treeNode, clickFlag) {
        window.location.href = "../projectSync/initProjectSyncForApp?agencyIdPro=" + treeNode.id ;
    }

</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>

</body>
</html>
