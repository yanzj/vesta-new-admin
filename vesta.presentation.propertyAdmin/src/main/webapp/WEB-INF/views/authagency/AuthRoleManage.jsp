<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/5/10
  Time: 14:22
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <script src="../static/js/underscore-min.js"></script>
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

    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
    <!--//Metis Menu -->
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <c:if test="${typeMaps.category eq '3'}">
        <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007000030000" username="${authPropertystaff.staffName}"/>
    </c:if>
    <c:if test="${typeMaps.category eq '2'}">
        <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007100030000" username="${authPropertystaff.staffName}"/>
    </c:if>
    <c:if test="${typeMaps.category eq '1'}">
        <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007300030000" username="${authPropertystaff.staffName}"/>
    </c:if>



    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../authAgency/authRole.html" method="get">

                    <%-- 城市下拉框 --%>
                    <div class="form-group  col-lg-4">
                        <label for="category" class="col-sm-4 control-label">权限设置：</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="category" name="category">
                                <%--<option value="0" selected="">请选择</option>--%>
                                <c:if test="${typeMaps.category eq '1'}">
                                <option value="1" <c:if test="${typeMaps.category eq '1'}">selected</c:if>>客关APP</option>
                                </c:if>
                                <c:if test="${typeMaps.category eq '2'}">
                                <option value="2" <c:if test="${typeMaps.category eq '2'}">selected</c:if>>工程APP</option>
                                </c:if>
                                <c:if test="${typeMaps.category eq '3'}">
                                <option value="3" <c:if test="${typeMaps.category eq '3'}">selected</c:if>>安全APP</option>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <%--角色名称--%>
                    <div class="form-group  col-lg-6">
                        <label for="roleName" class="col-sm-3 control-label">角色名称：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" value="${typeMaps.roleName}"
                                   placeholder="请输入角色名称" id="roleName" name="roleName">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" for="">搜索</button>
                    <c:if test="${function.sth40020020 eq 'Y'|| function.esh40020086 eq 'Y' || function.qch40010113 eq 'Y'}">
                        <a  data-toggle="modal" data-target="#myModal" class="btn btn-primary" for="">新建角色</a>
                    </c:if>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive  widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <%--<th width="4%"><input type="checkbox" name="answer" onclick="checkAllBox(this)">序号</th>--%>
                <th width="4%">序号</th>
                <th width="15%">角色名称</th>
                <th width="8%" >app</th>
                <th width="8%">性质</th>
                <th width="8%" >级别</th>
                <th width="25%">适用项目层级</th>
                <th width="15%">最后修改时间</th>
                <th width="5%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${problems}" var="problem" varStatus="row">
                <tr>
                    <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
                    <td>${problem.roleName}</td>
                    <td>
                        <c:if test="${problem.category eq '1'}">客关APP</c:if>
                        <c:if test="${problem.category eq '2'}">工程APP</c:if>
                        <c:if test="${problem.category eq '3'}">安全APP</c:if>
                    </td>
                    <td>
                        <c:if test="${problem.roleType eq '0'}">内部</c:if>
                        <c:if test="${problem.roleType eq '1'}">总包</c:if>
                        <c:if test="${problem.roleType eq '2'}">分包</c:if>
                        <c:if test="${problem.roleType eq '3'}">监理</c:if>
                    </td>
                    <td>
                        <c:if test="${problem.roleLevel eq '100000000'}">总部</c:if>
                        <c:if test="${problem.roleLevel eq '100000001'}">区域</c:if>
                        <c:if test="${problem.roleLevel eq '100000003'}">城市</c:if>
                        <c:if test="${problem.roleLevel eq '100000002'}">项目</c:if>
                    </td>
                    <%--<td><fmt:formatDate  type="String" value="${problem.modifyOn}" dateStyle="default" pattern="yyyy-MM-dd HH:mm:ss" /></td>--%>
                    <td>${problem.apply}</td>
                    <td>${problem.modifyOn}</td>
                    <td class="last">
                        <c:if test="${function.sth40020021 eq 'Y' || function.esh40020087 eq 'Y' || function.qch40010115 eq 'Y'}">
                            <a style="cursor: pointer" onClick="queryUpdate('${problem.roleId}');" class="a3">
                                <span class="span1"><spring:message code="auth.update"/></span>
                            </a>
                        </c:if>
                        <c:if test="${function.sth40020022 eq 'Y'|| function.esh40020088 eq 'Y' || function.qch40010115 eq 'Y'}">
                            <a style="cursor: pointer"
                               onclick="javascript:if(confirm('确定删除吗！'))deleteCheck('${problem.roleId}');else return" class="a3">删除</a>

                            <%--onclick="deleteCheck('${problem.roleId}');" class="a3">删除</a>--%>
                        </c:if>
                        <c:if test="${function.sth40020023 eq 'Y'|| function.esh40020089 eq 'Y' || function.qch40010116 eq 'Y'}">
                            <a style="cursor: pointer"
                               onclick="javascript:location.href='../authAgency/authFunctionPoint.html?authRoleId=${problem.roleId}&category=${problem.category}&classification=1'" class="a3">授权</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/authAgency/authRole.html?pageIndex={0}&successOrFailure=${problem.successOrFailure}&roleName=${typeMaps.roleName}&category=${typeMaps.category} "/>
    </div>
</div>
</div>
</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <h2>系统角色</h2>
                    <div class="containerSelect1">
                        <div class="row ">
                            <div class="col-md-8 roel_wrap ">
                                <div class="form-group  col-lg-12">
                                    <label for="roleName1" class="col-sm-3 control-label">角色名称:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control roleInput1" placeholder="" id="prefix" name="prefix" value="" readonly="readonly" >
                                        <input type="text" class="form-control roleInput2" placeholder="" id="roleName1" name="roleName1" value="请输入角色名称！" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:#999999"  >
                                    </div>
                                </div>
                                <div class="form-group  col-lg-9">
                                    <label for="roleLevel" class="col-sm-4 control-label">级别:</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" placeholder="" id="roleLevel" name="roleLevel">
                                            <option value="" selected="">请选择</option>
                                            <option value="100000000">总部</option>
                                            <option value="100000001">区域</option>
                                            <option value="100000003">城市</option>
                                            <option value="100000002">项目</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group  col-lg-9">
                                    <label for="category1" class="col-sm-4 control-label">app:</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" placeholder="" id="category1" name="category1">
                                            <option value="" selected="">请选择</option>
                                            <c:if test="${typeMaps.category eq '1'}">
                                                <option value="1" >客关APP</option>
                                            </c:if>
                                            <c:if test="${typeMaps.category eq '2'}">
                                                <option value="2" >工程APP</option>
                                            </c:if>
                                            <c:if test="${typeMaps.category eq '3'}">
                                                <option value="3" >安全APP</option>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group  col-lg-9">
                                    <label for="roleType" class="col-sm-4 control-label">性质:</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" placeholder="" id="roleType" name="roleType">
                                            <option value="" selected="">请选择</option>
                                            <option value="0">内部</option>
                                            <option value="1">总包</option>
                                            <option value="2">分包</option>
                                            <option value="3">监理</option>
                                        </select>
                                    </div>
                                </div>

                            </div>
                            <div class="content_wrap col-md-3">
                                <h5>适用项目层级</h5>
                                <div class="zTreeDemoBackground left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo" class="ztree"></ul>
                                </div>
                                <div class="people_list"></div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button  class="confirm" class="close"
                                    aria-hidden="true" onClick="saveUpdate();">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!-- /.modal-content -->
</div>


<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <h2>系统角色</h2>
                    <div class="containerSelect1">
                        <div class="row ">
                            <div class="col-md-8 roel_wrap ">
                                <div class="form-group  col-lg-12">
                                    <label for="roleName12" class="col-sm-3 control-label">角色名称:</label>
                                    <div class="col-sm-9">
                                        <input type="hidden" id="roleId2" name="roleId2" value=""/>
                                        <input type="text" class="form-control roleInput1" placeholder="" id="prefix2" name="prefix2" value="" readonly="readonly" >
                                        <input type="text" class="form-control roleInput2" placeholder="" id="roleName12" name="roleName12" value="" >
                                    </div>
                                </div>
                                <div class="form-group  col-lg-9">
                                    <label for="roleLevel2" class="col-sm-4 control-label">级别:</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" placeholder="" id="roleLevel2" name="roleLevel2">
                                            <option value="" selected="">请选择</option>
                                            <option value="100000000">总部</option>
                                            <option value="100000001">区域</option>
                                            <option value="100000003">城市</option>
                                            <option value="100000002">项目</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group  col-lg-9">
                                    <label for="category2" class="col-sm-4 control-label">app:</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" placeholder="" id="category2" name="category2">
                                            <option value="" selected="">请选择</option>
                                            <c:if test="${typeMaps.category eq '1'}">
                                                <option value="1" >客关APP</option>
                                            </c:if>
                                            <c:if test="${typeMaps.category eq '2'}">
                                                <option value="2" >工程APP</option>
                                            </c:if>
                                            <c:if test="${typeMaps.category eq '3'}">
                                                <option value="3" >安全APP</option>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group  col-lg-9">
                                    <label for="roleType2" class="col-sm-4 control-label">性质:</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" placeholder="" id="roleType2" name="roleType2">
                                            <option value="" selected="">请选择</option>
                                            <option value="0">内部</option>
                                            <option value="1">总包</option>
                                            <option value="2">分包</option>
                                            <option value="3">监理</option>
                                        </select>
                                    </div>
                                </div>

                            </div>
                            <div class="content_wrap col-md-3">
                                <h5>适用项目层级</h5>
                                <div class="zTreeDemoBackground left" style="border: 1px solid #ccc;">
                                    <ul id="treeDemo2" class="ztree"></ul>
                                </div>
                                <div class="people_list"></div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button  class="confirm" class="close"
                                     aria-hidden="true" onClick="Update();">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
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
<
<style>
    .container{
        width: 800px;
    }
    .container h2{
        text-align: center;
    }
    .container .roel_wrap{
        margin-top: 25px;
    }
    .container .roel_wrap .control-label{
        font-weight: 400;
        font-size: 1.1em;
    }
    .content_wrap h5{
        margin-bottom: 10px;
    }
    .roleInput1{
        border: none;
        background: none;
        box-shadow: none;
        width: auto;
        float: left;
        padding: 0;
        width: 130px;
        margin: 0;
        text-align: center;
    }
    .roleInput2{
        float: left;
        width: 103px;
    }
</style>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>


    var ckeckCategory=${typeMaps.category};//1 客关  2.工程  3.安全
    if(ckeckCategory=='3'){

        var setting = {
            async: {
                enable: false,
                url: "/authAgency/fullAgency",
                autoParam: ["id", "name=n", "level=lv"],
                otherParam: {"otherParam": "zTreeAsyncTest"},
                dataFilter: filter,
            },
            check: {
                enable: true
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

        $(document).ready(function () {
            $.ajax({
                url: "../authAgency/fullAgency", dataType: "json", success: function (result) {
                    $.fn.zTree.init($("#treeDemo"), setting, result);
                    var treeO = $.fn.zTree.getZTreeObj("treeDemo");
                    var nodes = treeO.getNodeByParam('id', null, null);
                    treeO.selectNode(nodes);
                    treeO.expandNode(nodes, true, false, true);
                }
            });
        });
    }

    if(ckeckCategory=='2'){
        var setting = {
            async: {
                enable: false,
                url: "/authAgency/fullAgencyES",
                autoParam: ["id", "name=n", "level=lv"],
                otherParam: {"otherParam": "zTreeAsyncTest"},
                dataFilter: filter,
            },
            check: {
                enable: true
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

        $(document).ready(function () {
            $.ajax({
                url: "../authAgency/fullAgencyES", dataType: "json", success: function (result) {
                    $.fn.zTree.init($("#treeDemo"), setting, result);
                    var treeO = $.fn.zTree.getZTreeObj("treeDemo");
                    var nodes = treeO.getNodeByParam('id', null, null);
                    treeO.selectNode(nodes);
                    treeO.expandNode(nodes, true, false, true);
                }
            });
        });
    }

    if(ckeckCategory=='1'){
        var setting = {
            async: {
                enable: false,
                url: "/authAgency/fullAgencyQC",
                autoParam: ["id", "name=n", "level=lv"],
                otherParam: {"otherParam": "zTreeAsyncTest"},
                dataFilter: filter,
            },
            check: {
                enable: true
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

        $(document).ready(function () {
            $.ajax({
                url: "../authAgency/fullAgencyQC", dataType: "json", success: function (result) {
                    $.fn.zTree.init($("#treeDemo"), setting, result);
                    var treeO = $.fn.zTree.getZTreeObj("treeDemo");
                    var nodes = treeO.getNodeByParam('id', null, null);
                    treeO.selectNode(nodes);
                    treeO.expandNode(nodes, true, false, true);
                }
            });
        });
    }

    setting.check.chkboxType = { "Y" : "s", "N" : "s" };
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

    function saveOpenTreeData(node) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        if (!node) return;
        if (node.parentTId) {
            var pNode = zTree.getNodeByTId(node.parentTId);
        }
    }


    //查找子集
    //event, treeId, treeNode, clickFlag
    function onClick(event, treeId, treeNode, clickFlag) {
        var openTreeData = [];
        setCookie('agencyId', treeNode.id, {domain: document.domain, path: '/'});
        setCookie('agencyType', treeNode.agencyType, {domain: document.domain, path: '/'});
        setCookie('agencyName', treeNode.agencyName, {domain: document.domain, path: '/'});
        saveOpenTreeData(treeNode);
        if(ckeckCategory == '3'){
            window.location.href = "../authAgency/AuthAgency.html?agencyId=" + treeNode.id + "&agencyType=" + treeNode.agencyType;
        }
        if(ckeckCategory == '2'){
            window.location.href = "../authAgency/AuthAgencyES.html?agencyId=" + treeNode.id + "&agencyType=" + treeNode.agencyType;
        }
        if(ckeckCategory == '1'){
            window.location.href = "../authAgency/AuthAgency.html?agencyId=" + treeNode.id + "&agencyType=" + treeNode.agencyType;
        }
        ;
        if (treeNode.agencyType == 1) {
            $('.pageNumber').css('margin-top', '0%');
        } else {
            $('.pageNumber').css('margin-top', '20%');
            $('.publicTbody').html('');
        }
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


    $("#sbm").click(function () {
        $("#searchByName").submit();
    })



    function  saveUpdate(){
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes=zTree.getChangeCheckedNodes(true);
        var result='';
        if(nodes.length==0){
            alert("请选择项目！！");
            return false;
        }

        for (var i = 0; i < nodes.length; i++) {
            var halfCheck = nodes[i].getCheckStatus();
                result += nodes[i].id +',';
        }
        result=result.substring(0,result.lastIndexOf(","));
        var roleType = $('#roleType option:selected').val();
        var roleLevel = $('#roleLevel option:selected').val();
        var roleName = $(" #roleName1 ").val()
        var prefix = $(" #prefix ").val();
        var category = $(" #category1 ").val();
        if(roleName && typeof(roleName)=="undefined" && roleName==0 ||roleName==""||roleName=="请输入角色名称！"){
            alert("角色名称不能为空！！");
            return false;
        }
        if(roleLevel && typeof(roleLevel)=="undefined" && roleLevel==0 ||roleLevel==""){
            alert("角色级别不能为空！！");
            return false;
        }
        if(category && typeof(category)=="undefined" && category==0 ||category=="" ){
            alert("app不能为空！！");
            return false;
        }
        if(roleType && typeof(roleType)=="undefined" && roleType==0 ||roleType==""){
            alert("角色性质不能为空！！");
            return false;
        }
        $.ajax({
            url: "../authAgency/addAuthRole",
            type: 'post',
            async: false,
            dataType: "json",
            data: {
                "apply": result,
                "roleType" :roleType,
                "roleLevel":roleLevel,
                "roleName":roleName,
                "category":category,
                "prefix":prefix
            },
            success: function (data) {
                if (data.check == 1) {
                    window.location.href = "../authAgency/authRole.html?category="+ckeckCategory+"";
                    $('#myModal').modal('hide')
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }

    function  Update(){
        var zTree = $.fn.zTree.getZTreeObj("treeDemo2");
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
        var roleType = $('#roleType2 option:selected').val();
        var roleLevel = $('#roleLevel2 option:selected').val();
        var roleName = $(" #roleName12 ").val();
        var prefix = $(" #prefix2 ").val();
        var roleId = $(" #roleId2 ").val();
        var category = $(" #category2 ").val();
        if(roleName && typeof(roleName)=="undefined" && roleName==0 ||roleName=="" ){
            alert("角色名称不能为空！！");
            return false;
        }
        if(roleLevel && typeof(roleLevel)=="undefined" && roleLevel==0 ||roleLevel=="" ){
            alert("角色级别不能为空！！");
            return false;
        }
        if(category && typeof(category)=="undefined" && category==0 ||category==""){
            alert("app不能为空！！");
            return false;
        }
        if(roleType && typeof(roleType)=="undefined" && roleType==0 ||roleType==""){
            alert("角色性质不能为空！！");
            return false;
        }
        $.ajax({
            url: "../authAgency/upAuthRole",
            type: 'post',
            async: false,
            dataType: "json",
            data: {
                "apply": result,
                "roleType" :roleType,
                "roleLevel":roleLevel,
                "roleName":roleName,
                "roleId":roleId,
                "category":category,
                "prefix":prefix
            },
            success: function (data) {
                if (data.check == 1) {
                    window.location.href = "../authAgency/authRole.html?category="+ckeckCategory+"";
                    $('#myModal2').modal('hide');
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }


    function  queryUpdate(id){
        document.getElementById("roleId2").value=id;
        $('#myModal2').modal('show');
        $('#myModal2').on('shown.bs.modal', function (e) {
            // do something...
            //安全app
            if(ckeckCategory =='3'){//安全
                $.ajax({
                    url: "../authAgency/queryAuthRole?roleId="+id+"",
                    type: 'get',
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        document.getElementById("roleName12").value=data.problem.roleName;
                        document.getElementById("roleType2").value=data.problem.roleType;
                        document.getElementById("roleLevel2").value=data.problem.roleLevel;
                        document.getElementById("category2").value=data.problem.category;
                        document.getElementById("prefix2").value=data.problem.prefix;
                        var mycars= data.problem.apply;
                        $.ajax(
                            {
                                url: "../authAgency/fullAgencyByID?apply="+mycars+"", dataType: "json", success: function (result) {
                                $.fn.zTree.init($("#treeDemo2"), setting, result);
                                var treeO = $.fn.zTree.getZTreeObj("treeDemo2");
                                var nodes = treeO.getNodes();
                                for (var i = 0; i < nodes.length; i++) {
                                    if (mycars.indexOf(nodes[i].id) != -1) {
                                        treeO.expandNode(nodes[i], true); //展开选中的
                                        treeO.checkNode(nodes[i], true);
                                    }
                                }
                                ztreeUpByType("treeDemo2","2","1");
                            }

                            }
                        );
                    }
                });
            }
            if(ckeckCategory =='2'){//工程
                $.ajax({
                    url: "../authAgency/queryAuthRole?roleId="+id+"",
                    type: 'get',
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        document.getElementById("roleName12").value=data.problem.roleName;
                        document.getElementById("roleType2").value=data.problem.roleType;
                        document.getElementById("roleLevel2").value=data.problem.roleLevel;
                        document.getElementById("category2").value=data.problem.category;
                        document.getElementById("prefix2").value=data.problem.prefix;
                        var mycars= data.problem.apply;
                        $.ajax(
                            {
                                url: "../authAgency/fullAgencyByIDES?apply="+mycars+"", dataType: "json", success: function (result) {
                                $.fn.zTree.init($("#treeDemo2"), setting, result);
                                var treeO = $.fn.zTree.getZTreeObj("treeDemo2");
                                var nodes = treeO.getNodes();
                                for (var i = 0; i < nodes.length; i++) {
                                    if (mycars.indexOf(nodes[i].id) != -1) {
                                        treeO.expandNode(nodes[i], true); //展开选中的
                                        treeO.checkNode(nodes[i], true);
                                    }
                                }
                                ztreeUpByType("treeDemo2","2","1");
                            }

                            }
                        );
                    }
                });
            }
            if(ckeckCategory =='1'){//客观
                $.ajax({
                    url: "../authAgency/queryAuthRole?roleId="+id+"",
                    type: 'get',
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        document.getElementById("roleName12").value=data.problem.roleName;
                        document.getElementById("roleType2").value=data.problem.roleType;
                        document.getElementById("roleLevel2").value=data.problem.roleLevel;
                        document.getElementById("category2").value=data.problem.category;
                        document.getElementById("prefix2").value=data.problem.prefix;
                        var mycars= data.problem.apply;
                        $.ajax(
                            {
                                url: "../authAgency/fullAgencyByIDQC?apply="+mycars+"", dataType: "json", success: function (result) {
                                $.fn.zTree.init($("#treeDemo2"), setting, result);
                                var treeO = $.fn.zTree.getZTreeObj("treeDemo2");
                                var nodes = treeO.getNodes();
                                for (var i = 0; i < nodes.length; i++) {
                                    if (mycars.indexOf(nodes[i].id) != -1) {
                                        treeO.expandNode(nodes[i], true); //展开选中的
                                        treeO.checkNode(nodes[i], true);
                                    }
                                }
                                ztreeUpByType("treeDemo2","2","1");
                            }

                            }
                        );
                    }
                });
            }
        })
    }
    $("#roleLevel").change(function saveZtreeByType(){
        ztreeUpByType("treeDemo","1","0")
    });

    $("#roleLevel2").change(function saveZtreeByType(){
        ztreeUpByType("treeDemo2","2","0");
    });

    function ztreeUpByType(treeName,type,check){
        var roleLevel
        if("1"==type){
            roleLevel = $('#roleLevel option:selected').val();
        }else if("2"==type){
            roleLevel = $('#roleLevel2 option:selected').val();
        }
//        var roleLevel = $('#roleLevel2 option:selected').val();
        var parRoleLevel='';
        var pparRoleLevel='';
        var ppparRoleLevel='';
        if(roleLevel == '100000001'){
            parRoleLevel='100000000';
        }else if(roleLevel == '100000003'){
            parRoleLevel='100000001';
            ppparRoleLevel='100000000';
        }else if(roleLevel == '100000002'){
            parRoleLevel='100000001';
            pparRoleLevel='100000003';
            ppparRoleLevel='100000000';
        }
        var zTree = $.fn.zTree.getZTreeObj(treeName);
        if("1"!=check){
            zTree.checkAllNodes(false);
        }

        var nodes = zTree.getNodes();
        var nod=zTree.transformToArray(nodes);
        for (var i = 0; i < nod.length; i++) {
            var node = nod[i];
            if(roleLevel == node.agencyType){
                node.nocheck = false; //表示显示checkbox
            }else if(parRoleLevel == node.agencyType){
                node.nocheck = false; //表示显示checkbox
            }else if(pparRoleLevel == node.agencyType){
                node.nocheck = false; //表示显示checkbox
            }else if(ppparRoleLevel == node.agencyType){
                node.nocheck = false; //表示显示checkbox
            }else {
                node.nocheck = true; //表示不显示checkbox
            }
            zTree.updateNode(node);
        }
    }

    $("#roleLevel").change(function saveZtreeByType(){
        var name='';
        //名字拼接

        var roleLevel = $('#roleLevel option:selected').val();
        if(roleLevel == '100000000'){
            name=name+'总部';
        }else if(roleLevel == '100000001'){
            name=name+'区域';
        }else if(roleLevel == '100000003'){
            name=name+'城市';
        }else if(roleLevel == '100000002'){
            name=name+'项目';
        }

        var category = $('#category1 option:selected').val();
        if(category == '1'){
            name=name+'_客关APP';
        }else if(category == '2'){
            name=name+'_工程APP';
        }else if(category == '3'){
            name=name+'_安全APP';
        }

        var roleType = $('#roleType option:selected').val();
        if(roleType == '0'){
            name=name+'_内部';
        }else if(roleType == '1'){
            name=name+'_总包';
        }else if(roleType == '2'){
            name=name+'_分包';
        }else if(roleType == '3'){
            name=name+'_监理';
        }
        document.getElementById("prefix").value=name;
    });
    $("#roleLevel2").change(function saveZtreeByType(){
        var name='';
        //名字拼接

        var roleLevel = $('#roleLevel2 option:selected').val();
        if(roleLevel == '100000000'){
            name=name+'总部';
        }else if(roleLevel == '100000001'){
            name=name+'区域';
        }else if(roleLevel == '100000003'){
            name=name+'城市';
        }else if(roleLevel == '100000002'){
            name=name+'项目';
        }

        var category = $('#category2 option:selected').val();
        if(category == '1'){
            name=name+'_客关APP';
        }else if(category == '2'){
            name=name+'_工程APP';
        }else if(category == '3'){
            name=name+'_安全APP';
        }

        var roleType = $('#roleType2 option:selected').val();
        if(roleType == '0'){
            name=name+'_内部';
        }else if(roleType == '1'){
            name=name+'_总包';
        }else if(roleType == '2'){
            name=name+'_分包';
        }else if(roleType == '3'){
            name=name+'_监理';
        }
        document.getElementById("prefix2").value=name;
    });

    $("#category1").change(function saveZtreeByType(){
        var name='';
        //名字拼接

        var roleLevel = $('#roleLevel option:selected').val();
        if(roleLevel == '100000000'){
            name=name+'总部';
        }else if(roleLevel == '100000001'){
            name=name+'区域';
        }else if(roleLevel == '100000003'){
            name=name+'城市';
        }else if(roleLevel == '100000002'){
            name=name+'项目';
        }

        var category = $('#category1 option:selected').val();
        if(category == '1'){
            name=name+'_客关APP';
        }else if(category == '2'){
            name=name+'_工程APP';
        }else if(category == '3'){
            name=name+'_安全APP';
        }

        var roleType = $('#roleType option:selected').val();
        if(roleType == '0'){
            name=name+'_内部';
        }else if(roleType == '1'){
            name=name+'_总包';
        }else if(roleType == '2'){
            name=name+'_分包';
        }else if(roleType == '3'){
            name=name+'_监理';
        }
        document.getElementById("prefix").value=name;
    });
    $("#category2").change(function saveZtreeByType(){
        var name='';
        //名字拼接
        var roleLevel = $('#roleLevel2 option:selected').val();
        if(roleLevel == '100000000'){
            name=name+'总部';
        }else if(roleLevel == '100000001'){
            name=name+'区域';
        }else if(roleLevel == '100000003'){
            name=name+'城市';
        }else if(roleLevel == '100000002'){
            name=name+'项目';
        }

        var category = $('#category2 option:selected').val();
        if(category == '1'){
            name=name+'_客关APP';
        }else if(category == '2'){
            name=name+'_工程APP';
        }else if(category == '3'){
            name=name+'_安全APP';
        }

        var roleType = $('#roleType2 option:selected').val();
        if(roleType == '0'){
            name=name+'_内部';
        }else if(roleType == '1'){
            name=name+'_总包';
        }else if(roleType == '2'){
            name=name+'_分包';
        }else if(roleType == '3'){
            name=name+'_监理';
        }
        document.getElementById("prefix2").value=name;
    });


    $("#roleType").change(function saveZtreeByType(){
        var name='';
        //名字拼接

        var roleLevel = $('#roleLevel option:selected').val();
        if(roleLevel == '100000000'){
            name=name+'总部';
        }else if(roleLevel == '100000001'){
            name=name+'区域';
        }else if(roleLevel == '100000003'){
            name=name+'城市';
        }else if(roleLevel == '100000002'){
            name=name+'项目';
        }

        var category = $('#category1 option:selected').val();
        if(category == '1'){
            name=name+'_客关APP';
        }else if(category == '2'){
            name=name+'_工程APP';
        }else if(category == '3'){
            name=name+'_安全APP';
        }


        var roleType = $('#roleType option:selected').val();
        if(roleType == '0'){
            name=name+'_内部';
        }else if(roleType == '1'){
            name=name+'_总包';
        }else if(roleType == '2'){
            name=name+'_分包';
        }else if(roleType == '3'){
            name=name+'_监理';
        }
        document.getElementById("prefix").value=name;
    });
    $("#roleType2").change(function saveZtreeByType(){
        var name='';
        //名字拼接
        var roleLevel = $('#roleLevel2 option:selected').val();
        if(roleLevel == '100000000'){
            name=name+'总部';
        }else if(roleLevel == '100000001'){
            name=name+'区域';
        }else if(roleLevel == '100000003'){
            name=name+'城市';
        }else if(roleLevel == '100000002'){
            name=name+'项目';
        }

        var category = $('#category2 option:selected').val();
        if(category == '1'){
            name=name+'_客关APP';
        }else if(category == '2'){
            name=name+'_工程APP';
        }else if(category == '3'){
            name=name+'_安全APP';
        }


        var roleType = $('#roleType2 option:selected').val();
        if(roleType == '0'){
            name=name+'_内部';
        }else if(roleType == '1'){
            name=name+'_总包';
        }else if(roleType == '2'){
            name=name+'_分包';
        }else if(roleType == '3'){
            name=name+'_监理';
        }
        document.getElementById("prefix2").value=name;
    });



    function deleteCheck(roleId){
        var roleId1=roleId;
        $.ajax({
            url: "../authAgency/delAuthRole",
            type: 'post',
            async: false,
            dataType: "json",
            data: {
                "roleId": roleId1
            },
            success: function (data) {
                if (data.check == 1) {
                    window.location.href = "../authAgency/authRole.html?category="+ckeckCategory+"";
                }else if(data.check == 2){
                    alert("对不起，该角色下有关联的授权人员，请解除关联后执行该操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }

</script>
</body>
</html>

