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
    <link href="../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
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
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exhide-3.5.js"></script>
    <script>
        $(function(){
            console.log("sqq")
            $("#007100010000").addClass("active");
            $("#007100010000").parent().parent().addClass("in");
            $("#007100010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <style>
        .btnDis {
            display: none;
            /*background-color: #eee;*/
            /*border: none;*/
        }

        .btn.btnDis:hover {
            display: none;
            /*background-color: #eee;*/
            /*border: none;*/
        }
        .forms{
            overflow: hidden;
        }
    </style>
</head>

<body class="cbp-spmenu-push">

<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007100010000" username="${authPropertystaff.staffName}"/>
    <div style="text-align: right; margin-top: 8px; margin-right: 25px;">
        <a href="../authorityForProject/initProjectUserManage.html" class="btn btn-primary">内部用户管理</a>
        <c:if test="${function.esh40020069 eq 'Y'}">
            <a href="../authorityForProject/outerProjectUserManage.html" class="btn btn-primary">外部用户管理</a>
        </c:if>
        <c:if test="${function.esh40020079 eq 'Y'}">
            <a href="../authorityForProject/getProjectEnabledUser.html" class="btn btn-primary">系统用户管理</a>
        </c:if>
    </div>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" name="searchByName" id="searchByName"
                      action="../authorityForProject/conditionQueryProjectUser.html">
                    <%-- 所属机构--%>
                        <div class="form-group  col-lg-4">
                            <label for="agencyNameB" class="col-sm-4 control-label">所属机构</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="agencyNameB"
                                       name="agencyNameB" value="${userStaffDTO.agencyNameB}">
                            </div>
                        </div>
                    <%-- 人员--%>
                        <div class="form-group  col-lg-4">
                            <label for="staffNameB" class="col-sm-4 control-label">人员</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="staffNameB"
                                       name="staffNameB" value="${userStaffDTO.staffNameB}">
                            </div>
                        </div>
                        <%--用户名--%>
                        <div class="form-group  col-lg-4">
                            <label for="userNameB" class="col-sm-4 control-label">OA账号</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="userNameB"
                                       name="userNameB" value="${userStaffDTO.userNameB}">
                            </div>
                        </div>
                    <%--启用状态--%>
                        <div class="form-group  col-lg-4">
                            <label for="isEnabledB" class="col-sm-4 control-label">启用状态</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="isEnabledB" name="isEnabledB">
                                    <option value="" selected>请选择</option>
                                    <option value="1" <c:if test="${userStaffDTO.isEnabledB eq '1'}">selected</c:if>>启用</option>
                                    <option value="0" <c:if test="${userStaffDTO.isEnabledB eq '0'}">selected</c:if>>停用</option>
                                </select>
                            </div>
                        </div>
                    <%--电话--%>
                        <div class="form-group  col-lg-4">
                            <label for="mobileB" class="col-sm-4 control-label">手机号码</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="mobileB"
                                       name="mobileB" value="${userStaffDTO.mobileB}">
                            </div>
                        </div>
                    <%--邮箱--%>
                        <div class="form-group  col-lg-4">
                            <label for="emailB" class="col-sm-4 control-label">邮箱</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="emailB"
                                       name="emailB" value="${userStaffDTO.emailB}">
                            </div>
                        </div>
                        <div style="text-align: center; margin-top: 8px;">
                            <c:if test="${function.esh40020063 eq 'Y'}">
                                <button type="submit" id="sbm" class="btn btn-primary" for="propertySearch">查询</button>
                            </c:if>
                            <c:if test="${function.esh40020064 eq 'Y'}">
                                <a href="javascript:void(0)" onclick="sycn()" class="btn btn-primary" >同步OA账户</a>
                            </c:if>
                            <c:if test="${function.esh40020065 eq 'Y'}">
                                <a href="javascript:void(0)" onclick="batchStart()" class="btn btn-primary">批量启用</a>
                            </c:if>
                            <c:if test="${function.esh40020066 eq 'Y'}">
                                <a href="javascript:void(0)" onclick="batchStop()" class="btn btn-primary">批量停用</a>
                            </c:if>
                            <a href="#" onclick="saveOA()" class="btn btn-primary" style="visibility:hidden">同步OA数据到本系统</a>
                        </div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>

    <div class="table-responsive bs-example widget-shadow">
        <div class="row">
            <div class="content_wrap col-md-3" style="height: 400px; overflow: scroll">
                <div class="zTreeDemoBackground left">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
            <div class="table-responsive widget-shadow col-md-9" style="float:left;margin-top:0">
                <table class="tableStyle  col-md-12">
                    <thead>
                    <!-- 点击部门 显示人员的列表 -->
                        <tr>
                            <th><input name="checkall1" type="checkbox" value="0" onClick="checkAllBox(this);"/>序号</th>
                            <th>姓名</th>
                            <th>OA账号</th>
                            <th>系统账号</th>
                            <th>手机号码</th>
                            <th>邮箱地址</th>
                            <th>所属机构</th>
                            <th>状态</th>
                            <th>最后修改时间</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody class="publicTbody">
                    <c:forEach items="${userStaffDTOS}" var="list" varStatus="us">
                        <tr>
                            <td height="40px" style="text-align: center">
                                <input type="checkbox" name="staffIdB"
                                       value="${list.staffIdB}"><b>${(webPage.pageIndex-1)*20+us.index+1}</b>
                            </td>
                            <td>${list.staffNameB}</td>
                            <td>${list.userNameB}</td>
                            <td>${list.sysNameB}</td>
                            <td>${list.mobileB}</td>
                            <td>${list.emailB}</td>
                            <td>${list.agencyNameB}</td>
                            <td>
                                <c:if test="${list.isEnabledB eq '0'}">未启用
                                </c:if>
                                <c:if test="${list.isEnabledB eq '1'}">已启用
                                </c:if>
                            </td>
                            <td>${list.modifyOnB}</td>
                            <td>
                                <a href="javascript:void(0);" onclick="toHandle('${list.staffIdB}','${list.isEnabledB}')" id="toHandle"
                                   class="a3">
                                    <c:if test="${list.isEnabledB eq '0' and function.esh40020067 eq 'Y'}">启用
                                    </c:if>
                                    <c:if test="${list.isEnabledB eq '1' and function.esh40020068 eq 'Y'}">停用
                                    </c:if>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <m:pager pageIndex="${webPage.pageIndex}"
                         pageSize="${webPage.pageSize}"
                         recordCount="${webPage.recordCount}"
                         submitUrl="${pageContext.request.contextPath }../authorityForProject/conditionQueryProjectUser.html?pageIndex={0}&agencyIdB=${userStaffDTO.agencyIdB}&agencyNameB=${userStaffDTO.agencyNameB}&staffNameB=${userStaffDTO.staffNameB}&userNameB=${userStaffDTO.userNameB}&isEnabledB=${userStaffDTO.isEnabledB}&mobileB=${userStaffDTO.mobileB}&emailB=${userStaffDTO.emailB}"/>
            </div>
        </div>
    </div>


</div>
</div>
</div>
</div>

<script>


    //JS操作cookies方法!

    //写cookies
//
//    function setCookie(name, value, options) {
//        options = options || {};
//        if (value === null) {
//            value = '';
//            options.expires = -1;
//        }
//        var expires = '';
//        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
//            var date;
//            if (typeof options.expires == 'number') {
//                date = new Date();
//                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
//            } else {
//                date = options.expires;
//            }
//            expires = '; expires=' + date.toUTCString();
//        }
//        var path = options.path ? '; path=' + (options.path) : '';
//        var domain = options.domain ? '; domain=' + (options.domain) : '';
//        var secure = options.secure ? '; secure' : '';
//        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
//    }
//    ;
//
//    //读取cookies
//    function getCookie(name) {
//        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
//
//        if (arr = document.cookie.match(reg))
//
//            return unescape(arr[2]);
//        else
//            return null;
//    }

    function saveOA() {
        if(confirm('数据更新时间较长,确认更新么？')){
            $.ajax({
                url:"../authorityForProject/getOATestByProject",
                type:"POST",
                async:false,
                dataType:"json",
                data:{
                },
                success:function(json){
                    <!-- 获取返回代码 -->
                    var code = json.data;
                    if(code == 0){
                        alert("更新成功！");
                        window.parent.location.href="/authorityForProject/initProjectUserManage.html";
                    }else if(code == 1){
                        alert("更新失败！");
                        window.parent.location.href="/authorityForProject/initProjectUserManage.html";
                    }
                }
            });
        }else {
            alert('已取消')
        }

    }

    function checkAllBox(obj){
        var answer= document.getElementsByName("staffIdB");
        if(obj.checked==true){
            for(var i=0;i<answer.length;i++){
                answer[i].checked = true;
            }
        }else{
            for(var i=0;i<answer.length;i++){
                answer[i].checked = false;
            }
        }
    }
//
//    //删除cookies
//    function delCookie(name, options) {
//        var path = options.path ? '; path=' + (options.path) : '';
//        var domain = options.domain ? '; domain=' + (options.domain) : '';
//        var exp = new Date();
//        exp.setTime(exp.getTime() - 1);
//        var cval = getCookie(name);
//        if (cval != null)
//            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString() + ";+path=" + path + ";domain=" + domain;
//    }
    var selectedList = []
    var waitLists = {};//waitLists['id'+id]=[{name:name,id:id}]
    var setting = {
        async: {
            enable: false,
            url: "/authorityForProject/getAllAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter,
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: null
            }
        },
        callback: {
            onClick: onClick,
            onAsyncSuccess: onAsyncSuccess,
        },
    };
    var initState = false;
    function onAsyncSuccess(event, treeId, treeNode, msg) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        if (!initState) {
            if (treeNode) {
                var index = initOpenTreeDate.indexOf(treeNode.id);
                if (index == initOpenTreeDate.length - 2) {
                    initState = true;
                    setTimeout(function () {
                        zTree.selectNode(zTree.getNodesByParam('id', initOpenTreeDate[index + 1])[0])
                    }, 0)
                } else {
                    setTimeout(function () {
                        zTree.expandNode(zTree.getNodesByParam('id', initOpenTreeDate[index + 1])[0]);
                    }, 0)
                }
            } else {
                if (initOpenTreeDate.length == 1) {
                    setTimeout(function () {
                        zTree.selectNode(zTree.getNodesByParam('id', initOpenTreeDate[0])[0])
                    }, 0)
                } else {
                    var node = zTree.getNodesByParam('id', initOpenTreeDate[0])[0]
                    zTree.expandNode(node);
                }
            }
        }

    }

//    function saveOpenTreeData(node) {
//        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//        if (!node) return;
//        if (node.parentTId) {
//            var pNode = zTree.getNodeByTId(node.parentTId);
//            setCookie('opentreeData', pNode.id, {domain: document.domain, path: '/'})
//        }
//    }

    //传参type id部门
    //name id type pid
    //查找子集
    //event, treeId, treeNode, clickFlag
    function onClick(event, treeId, treeNode, clickFlag) {
//        saveOpenTreeData(treeNode);
        window.location.href = "../authorityForProject/conditionQueryProjectUser.html?agencyIdB=" + treeNode.id ;
    }
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) {
            return null;
        }
        var arr = [];
        for (var i = 0, h = childNodes.length; i < h; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            //arr.push  下一级的 name  id
            arr.push({name: childNodes[i].name, id: childNodes[i].id, isParent: childNodes[i].isParent})
        }
        if (parentNode) {
            waitLists['id' + parentNode.id] = arr;
        } else {
            waitLists['idRoot'] = arr;
        }
        return childNodes;
    }
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
    function strToJson(str) {
        var json = (new Function("return " + str))();

        return json;
    }
    $(document).ready(function () {
        $.ajax({
            url: "../authorityForProject/getAllAgency", dataType: "json", success: function (result) {
                $.fn.zTree.init($("#treeDemo"), setting, result);
                var cId = "${userStaffDTO.agencyIdB}";
                var treeO = $.fn.zTree.getZTreeObj("treeDemo");
                var nodes = treeO.getNodeByParam('id', cId, null);
                treeO.selectNode(nodes);
                treeO.expandNode(nodes, true, false, true);
            }
        });
    });

    $("#sbm").click(function () {
        $("#searchByName").submit();
    })
    
    function sycn() {
        if(confirm('同步OA数据时间较长,确认么？')){
            $.ajax({
                url:"../authorityForProject/syncOAByProject",
                type:"POST",
                async:false,
                dataType:"json",
                data:{
                },
                success:function(json){
                    <!-- 获取返回代码 -->
                    var code = json.data;
                    if(code == 1){
                        alert("更新成功！");
                        window.parent.location.href="/authorityForProject/initProjectUserManage.html";
                    }else if(code == 0){
                        alert("更新失败！");
                        window.parent.location.href="/authorityForProject/initProjectUserManage.html";
                    }
                }
            });
        }else {
            alert('已取消')
        }
    }

    function batchStart() {
        var staffId = document.getElementsByName("staffIdB");
        var checkbox = false;
        var staffIds = '';
        for (var i = 0; i < staffId.length; i++) {
            if (staffId[i].checked) {
                checkbox = true;
                if(i==staffId.length-1){
                    staffIds += staffId[i].value;
                }else{
                    staffIds += staffId[i].value + ",";
                }
            }
        }
        if (checkbox) {
            if (confirm("您确认要批量启用已选用户?")) {
                $.ajax({
                    url:"../authorityForProject/batchHandleProjectUserState",
                    type:"POST",
                    async:false,
                    data:{
                        state:'1',
                        staffIds:staffIds
                    },
                    error:function(){
                        alert("网络异常，可能服务器忙，请刷新重试");
                    },
                    success:function(data){
                        if(data.error == "0"){
                            alert("操作成功！");
                        }else if(data.error == "-1"){
                            alert("操作失败，请联系管理员！");
                        }
                    }
                });
                window.location.reload();
//                window.location.href = "../authorityForProject/initProjectUserManage.html";
            } else {
                return;
            }
        } else {
            alert("请选择要启用的用户");
            return;
        }
    }

    function batchStop() {
        var staffId = document.getElementsByName("staffIdB");
        var checkbox = false;
        var staffIds = '';
        for (var i = 0; i < staffId.length; i++) {
            if (staffId[i].checked) {
                checkbox = true;
                if(i==staffId.length-1){
                    staffIds += staffId[i].value;
                }else{
                    staffIds += staffId[i].value + ",";
                }
            }
        }
        if (checkbox) {
            if (confirm("您确认要批量停用已选用户?")) {
                $.ajax({
                    url:"../authorityForProject/batchHandleProjectUserState",
                    type:"POST",
                    async:false,
                    data:{
                        state:'0',
//                        staffIds:'12315193219321',
                        staffIds:staffIds
                    },
                    error:function(){
                        alert("网络异常，可能服务器忙，请刷新重试");
                    },
                    success:function(data){
                        if(data.error == "0"){
                            alert("操作成功！");
                        }else if(data.error == "-1"){
                            alert("操作失败，请联系管理员！");
                        }
                    }
                });
                window.location.reload();
//                window.location.href = "../authorityForProject/initProjectUserManage.html";
            } else {
                return;
            }
        } else {
            alert("请选择要停用的用户");
            return;
        }
    }
    function toHandle(staffId,isEnabled) {
        if(isEnabled == "1"){
            if (confirm("您确认要停用该用户?")) {
                $.ajax({
                    url:"../authorityForProject/batchHandleProjectUserState",
                    type:"POST",
                    async:false,
                    data:{
                        state:"0",
                        staffIds:staffId
                    },
                    error:function(){
                        alert("网络异常，可能服务器忙，请刷新重试");
                    },
                    success:function(data){
                        if(data.error == "0"){
                            alert("操作成功！");
                        }else if(data.error == "-1"){
                            alert("操作失败，请联系管理员！");
                        }
                    }
                });
                window.location.reload();
//                window.location.href = "../authorityForProject/initProjectUserManage.html";
            } else {
                return;
            }
        }else {
            if (confirm("您确认要启用该用户?")) {
                $.ajax({
                    url:"../authorityForProject/batchHandleProjectUserState",
                    type:"POST",
                    async:false,
                    data:{
                        state:"1",
                        staffIds:staffId
                    },
                    error:function(){
                        alert("网络异常，可能服务器忙，请刷新重试");
                    },
                    success:function(data){
                        if(data.error == "0"){
                            alert("操作成功！");
                        }else if(data.error == "-1"){
                            alert("操作失败，请联系管理员！");
                        }
                    }
                });
                window.location.reload();
//                window.location.href = "../authorityForProject/initProjectUserManage.html";
            } else {
                return;
            }
        }
    }
</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>

</body>
</html>