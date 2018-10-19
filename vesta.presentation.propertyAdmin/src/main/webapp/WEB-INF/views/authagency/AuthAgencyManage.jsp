<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/1/25
  Time: 16:00
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
<%@ page import="com.maxrocky.vesta.utility.StringUtil" %>
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
        <c:if test="${adminDTO.category eq '3'}">
        $(function(){
            console.log("sqq")
            $("#007000020000").addClass("active");
            $("#007000020000").parent().parent().addClass("in");
            $("#007000020000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
        </c:if>
        <c:if test="${adminDTO.category eq '2'}">
        $(function(){
            console.log("sqq")
            $("#007100020000").addClass("active");
            $("#007100020000").parent().parent().addClass("in");
            $("#007100020000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
        </c:if>
        <c:if test="${adminDTO.category eq '1'}">
        $(function(){
            console.log("sqq")
            $("#007300020000").addClass("active");
            $("#007300020000").parent().parent().addClass("in");
            $("#007300020000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
        </c:if>
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
        .modal-dialog{
            width: 600px;
        }
        .modal-footer{
            text-align: center;
            border: none;
        }
        .control-label{
            font-size: 14px;
        }
    </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <c:if test="${adminDTO.category eq '3'}">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007000020000" username="${authPropertystaff.staffName}"/>
    </c:if>
    <c:if test="${adminDTO.category eq '2'}">
        <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                     crunMenu="007100020000" username="${authPropertystaff.staffName}"/>
    </c:if>
    <c:if test="${adminDTO.category eq '1'}">
        <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                     crunMenu="007300020000" username="${authPropertystaff.staffName}"/>
    </c:if>
    <%--<div class="forms">--%>
    <%--<div class="widget-shadow " data-example-id="basic-forms">--%>
    <%--&lt;%&ndash;搜索条件开始&ndash;%&gt;--%>
    <%--<div class="form-body">--%>
    <%--<form class="form-horizontal" name="searchByName" id="searchByName"--%>
    <%--action="../authAgency/AuthAgency.html">--%>
    <%--<div class="form-group  col-lg-4">--%>
    <%--<label for="staffNameDto" class="col-sm-4 control-label">机构名称</label>--%>

    <%--<div class="col-sm-8">--%>
    <%--<input type="text" class="form-control" placeholder="" id="agencyNameDto"--%>
    <%--name="agencyName" value="${adminDTO.agencyName}">--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="form-group  col-lg-4">--%>
    <%--<button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_search"/></button>--%>
    <%--</div>--%>
    <%--</form>--%>
    <%--</div>--%>
    <%--&lt;%&ndash;搜索条件结束&ndash;%&gt;--%>
    <%--</div>--%>
    <%--</div>--%>
    
    <div class="table-responsive bs-example widget-shadow">
        <div class="row">
            <div class="content_wrap col-md-3">
                <div class="zTreeDemoBackground left">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
            <div class="table-responsive  widget-shadow col-md-9" style="float:left;margin-top:0">
                <c:if test="${function.sth40020060 eq 'Y' }">
                    <a data-toggle="modal" data-target="#myModal" class="btn btn-primary col-md-offset-11" >添加项目</a>
                </c:if>
                <c:if test="${function.esh40020096 eq 'Y'}">
                    <a data-toggle="modal" data-target="#myModal" class="btn btn-primary col-md-offset-11" >添加项目</a>
                </c:if>
                <table class="tableStyle  col-md-12">
                    <thead>
                    <tr>
                        <th>排序号</th>
                        <th>项目层级名称</th>
                        <th>上级项目层级名称</th>
                        <th>級別</th>
                        <%--<th>最后修改时间</th>--%>
                    </tr>
                    </thead>
                    <tbody class="publicTbody">
                    <c:forEach items="${agencyList}" var="list" varStatus="as">
                        <tr>
                            <td><b>${(webPage.pageIndex-1)*20+as.index + 1}</b></td>
                            <td>${list.agencyName}</td>
                            <td>${list.parentName}</td>
                            <td>
                                <c:if test="${list.agencyType eq '100000000'}">总部</c:if>
                                <c:if test="${list.agencyType eq '100000001'}">区域</c:if>
                                <c:if test="${list.agencyType eq '100000003'}">城市</c:if>
                                <c:if test="${list.agencyType eq '100000002'}">项目</c:if>
                            </td>
                                <%--<td>${list.modifyTime}</td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%--<m:pager pageIndex="${webPage.pageIndex}"--%>
                <%--pageSize="${webPage.pageSize}"--%>
                <%--recordCount="${webPage.recordCount}"--%>
                <%--submitUrl="${pageContext.request.contextPath }/user/userStaffManage.html?pageIndex={0}&admStaff=${adminDTO.admStaff}&admUser=${adminDTO.admUser}"/>--%>
            </div>
        </div>
    </div>


</div>
</div>
</div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form class="modal-content" name="form" id="form" action="../authAgency/saveProject">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel2">添加项目</h4>
            </div>
            <div class="modal-body">
                <div class="form-group  col-lg-12" style="margin-top: 10px">
                    <label class="col-sm-4 control-label">区域名称</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="areaId" name="areaId" onchange="getCity(this.value)">
                            <option value="0">请选择区域</option>
                            <c:forEach items="${areaList}" var="list">
                                <option value="${list.key}">${list.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-4 control-label">城市名称</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="cityId" name="cityId">
                            <option value="0">请选择城市</option>
                        </select>
                    </div>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-4 control-label">项目名称</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="projectName" name="projectName">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onClick="saveProject()">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- /.modal-content -->
<script type="text/javascript">



    function getCity(areaId) {
        if(ckeckCategory=='3'){
            $.ajax({
                url: "../authAgency/getCityByAreaId",
                type: 'get',
                async: false,
                dataType: "json",
                data: {
                    "areaId": areaId
                },
                success: function (json) {
                    var items = json.data;
                    var selectModel = $("#cityId");
                    selectModel.empty();
                    if (items != null) {
                        for (var i in items) {
                            var item = items[i];
                            selectModel.append("<option value = '" + i + "'>" + item + "</option>");
                        }
                    }
                    else {
                        selectModel.empty();
                    }
                }
            });
        }
        if(ckeckCategory=='2'){
            $.ajax({
                url: "../authAgency/getESCityByAreaId",
                type: 'get',
                async: false,
                dataType: "json",
                data: {
                    "areaId": areaId
                },
                success: function (json) {
                    var items = json.data;
                    var selectModel = $("#cityId");
                    selectModel.empty();
                    if (items != null) {
                        for (var i in items) {
                            var item = items[i];
                            selectModel.append("<option value = '" + i + "'>" + item + "</option>");
                        }
                    }
                    else {
                        selectModel.empty();
                    }
                }
            });
        }
        if(ckeckCategory=='1'){
            $.ajax({
                url: "../authAgency/getQCCityByAreaId",
                type: 'get',
                async: false,
                dataType: "json",
                data: {
                    "areaId": areaId
                },
                success: function (json) {
                    var items = json.data;
                    var selectModel = $("#cityId");
                    selectModel.empty();
                    if (items != null) {
                        for (var i in items) {
                            var item = items[i];
                            selectModel.append("<option value = '" + i + "'>" + item + "</option>");
                        }
                    }
                    else {
                        selectModel.empty();
                    }
                }
            });
        }

    }

    function saveProject() {
        var areaId = $('#areaId option:selected').val();
        var cityId = $('#cityId option:selected').val();
        var projectName = $("#projectName").val()
        if(typeof(areaId)=="undefined" ||areaId==0 ||areaId=="" ||areaId==null){
            alert("请选择区域！！！");
            return false;
        }
        if(typeof(cityId)=="undefined" || cityId==0 ||cityId=="" || cityId ==null){
            alert("请选择城市！！！");
            return false;
        }
        $.ajax({
            url: "../authAgency/saveProject",
            type: 'post',
            async: false,
            dataType: "json",
            data: {
                "projectName":projectName,
                "cityId":cityId,
                "category":ckeckCategory,
            },
            success: function (data) {
                var code = data.check;
                if(code=="1"){
                    alert("保存成功");
                    if(ckeckCategory == '3'){
                        window.location.href = "../authAgency/AuthAgency.html?category=3";
                    }
                    if(ckeckCategory == '2'){
                        window.location.href = "../authAgency/AuthAgency.html?category=2";
                    }
                    if(ckeckCategory == '1'){
                        window.location.href = "../authAgency/AuthAgency.html?category=1";
                    }

                }else if(code=="0"){
                    alert("请输入项目名！！");
                }else {
                    alert("操作失败");
                }
            }
        });
    }

</script>
<script>


    //写cookies
    function setCookie(name, value, options) {
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString();
        }
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    }
    ;

    //读取cookies
    function getCookie(name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

        if (arr = document.cookie.match(reg))

            return unescape(arr[2]);
        else
            return null;
    }

    //删除cookies
    function delCookie(name, options) {
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null)
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString() + ";+path=" + path + ";domain=" + domain;
    }
    var selectedList = []
    var waitLists = {};//waitLists['id'+id]=[{name:name,id:id}]
    var ckeckCategory=${adminDTO.category};//1 客关  2.工程  3.安全


    if(ckeckCategory=='3'){
        var setting = {
            async: {
                enable: false,
                url: "/authAgency/fullAgency",
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

        $(document).ready(function () {
            $.ajax({
                url: "../authAgency/fullAgency", dataType: "json", success: function (result) {
                    $.fn.zTree.init($("#treeDemo"), setting, result);
                    var cId = getCookie('agencyId');
                    var treeO = $.fn.zTree.getZTreeObj("treeDemo");
                    var nodes = treeO.getNodeByParam('id', cId, null);
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
                    var cId = getCookie('agencyId');
                    var treeO = $.fn.zTree.getZTreeObj("treeDemo");
                    var nodes = treeO.getNodeByParam('id', cId, null);
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
                    var cId = getCookie('agencyId');
                    var treeO = $.fn.zTree.getZTreeObj("treeDemo");
                    var nodes = treeO.getNodeByParam('id', cId, null);
                    treeO.selectNode(nodes);
                    treeO.expandNode(nodes, true, false, true);
                }
            });
        });
    }


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
            setCookie('openTreeData', pNode.id, {domain: document.domain, path: '/'})
        }
    }

    //传参type id部门
    //name id type pid
    //查找子集
    //event, treeId, treeNode, clickFlag
    function onClick(event, treeId, treeNode, clickFlag) {
        var openTreeData = [];
        setCookie('agencyId', treeNode.id, {domain: document.domain, path: '/'});
        setCookie('agencyType', treeNode.agencyType, {domain: document.domain, path: '/'});
        setCookie('agencyName', treeNode.agencyName, {domain: document.domain, path: '/'});
        saveOpenTreeData(treeNode);
        if(ckeckCategory == '3'){
            window.location.href = "../authAgency/AuthAgency.html?agencyId=" + treeNode.id + "&agencyType=" + treeNode.agencyType+"&category=3";
        }
        if(ckeckCategory == '2'){
            window.location.href = "../authAgency/AuthAgency.html?agencyId=" + treeNode.id + "&agencyType=" + treeNode.agencyType+"&category=2";
        }
        if(ckeckCategory == '1'){
            window.location.href = "../authAgency/AuthAgency.html?agencyId=" + treeNode.id + "&agencyType=" + treeNode.agencyType+"&category=1";
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
</script>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>