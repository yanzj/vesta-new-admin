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
        $(function(){
            $("#007200010000").addClass("active");
            $("#007200010000").parent().parent().addClass("in");
            $("#007200010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <script src="../static/js/echarts.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
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
        .tableStyle button{
            border: none;
            line-height: 30px;
        }
        .btnDaochu{
            background: #e2a40e;
            border: none;
            color: #fff!important;
        }
        .btn{
            height: 30px;
            line-height: 30px;
            padding: 0 12px;
            color: #003159;
        }
        .btn:hover, .btn:focus, .btn.focus, .btn:active, .btn.active, .open > .dropdown-toggle.btn{
            color: #fff;
            background-color: #003159;
        }
        .btn2{
            color: #06315d;
            font-weight: bold;
        }
        .btn-primary{
            height: 30px;
            color: #fff;
            background-color: #003159;
        }
        .btn-primary:hover, .btn-primary:focus, .btn-primary.focus, .btn-primary:active, .btn-primary.active, .open > .dropdown-toggle.btn-primary{
            background-color: #003159;
        }
        .btn-primary2{
            color: #fff;
            background: #06315d;
        }
        .tableStyle thead{
            border: none;
        }
        .tableStyle thead td, .tableStyle thead th{
            border: none;
            background: none;
        }
        .detail{
            float: left;
            margin-right: 60px;
            margin-top: 10px;
            font-size: 15px;
        }
        .areaDetail{
            float: left;
            margin-left: 60px;
            margin-top: 10px;
            font-size: 15px;
        }
        .btnImg{
            float: right;
            margin-right: 60px;
            margin-top: 10px;
            font-size: 15px;
        }
        .modelDetail{
            float: left;
            margin-left: 60px;
            margin-top: 10px;
            font-size: 15px;
        }
        .echarts{
            float: right;
            margin-right: 60px;
            margin-top: 10px;
        }
        .echarts .btn2{
            background: #e2a40e;
        }
        .echarts .btn3{
            background: #e85a28;
        }
        .echarts button{
            width: 20px;
            height: 15px;
            margin-left: 8px;
        }
        .form-group{
            margin-left: -30px!important;
        }
    </style>

</head>

<body class="cbp-spmenu-push">

<div class="main-content">
        <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                     crunMenu="007200010000" username="${authPropertystaff.staffName}"/>
    <div id = "agencyIdList" style="display:none">${agencyIdList}</div>
    <div class="table-responsive bs-example widget-shadow">
        <div class="row">
            <div class="col-md-3" style="min-height: 500px;">
                <div class="zTreeDemoBackground left">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
            <div class="table-responsive  widget-shadow col-md-9" style="height: 40px;">
                    <form class="form-horizontal" action="../weekly/weekly.html" method="get" style="overflow: hidden;">
                        <input type="hidden" name="agencyId" value="${project.agencyId}" readonly="true"/>
                        <input type="hidden" name="agencyType" value="${agencyType}" readonly="true"/>
                        <%-- 开始时间--%>
                        <div class="form-group  col-lg-5">
                            <label for="startDate" class="col-sm-3 control-label"><spring:message
                                    code="workOrders.startDate"/>:</label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入开始时间" path="startDate"
                                       id="startDate"
                                       name="startDate" value="${startDate}" readonly="true"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <%-- 结束时间--%>
                        <div class="form-group  col-lg-5">
                            <label for="endDate" class="col-sm-3 control-label"><spring:message
                                    code="workOrders.endDate"/>:</label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入结束时间" path="endDate" id="endDate"
                                       name="endDate" value="${endDate}" readonly="true"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div <%--class="col-lg-3"--%> style="height: 40px;margin-right: -5px;">
                            <button type="button" class="btn btn-primary" onclick="search()" for="propertySearch">搜索</button>
                            <button type="button" class="btn  btnDaochu" onclick="exportExcel()">excel导出</button>
                            <a href="../weekly/saveWeeklyByApp.html"  class="btn btn-primary" style="visibility:hidden">获取app端统计数据</a>
                        </div>
                    </form>
                </div>
            <div class="table-responsive  widget-shadow col-md-9" style="float:left;margin-top:0;height:1000px">
                    <table class="tableStyle  col-md-12">
                        <thead>
                        <tr>
                            <th><button type="button" class="btn  btn-js btn-primary" onclick='dataByProjecy("onClickByProject")'>${project.agencyName}</button></th>
                            <th><button type="button" class="btn     btn-js" onclick='dataByProjecy("ProjectInspection")' >日常巡检</button></th>
                            <th><button type="button" class="btn     btn-js" onclick='dataByProjecy("ProjectExamine")' >检查验收</button></th>
                            <th><button type="button" class="btn     btn-js" onclick='dataByProjecy("ProjectMaterial")' >材料验收</button></th>
                            <th><button type="button" class="btn    btn-js" onclick='dataByProjecy("ProjectSampleCheck")' >样板点评</button></th>
                            <th><button type="button" class="btn     btn-js" onclick='dataByProjecy("ProjectKeyProcesses")' >关键工序</button></th>
                            <th><button type="button" class="btn    btn-js" onclick='dataByProjecy("ProjectSideStation")' >旁站</button></th>
                            <th><button type="button" class="btn     btn-js" onclick='dataByProjecy("ProjectLeadersCheck")' >领导检查</button></th>
                        </tr>
                        </thead>
                    </table>
                    <div class="col-md-12">
                        <div class="detail"></div>
                        <div class="echarts">检查次数<button class="btn btn-mini btn-primary" type="button"></button></div>
                    </div>
                    <div class="col-md-12">
                        <div class="echarts">超过两周以上未整改内容<button class="btn btn-mini btn3" type="button"></button></div>
                    </div>
                    <div class="col-md-12">
                        <div class="echarts"><span id="pop">合格率(单位%)</span><button class="btn btn-mini btn2" type="button"></button></div>
                    </div>
                    <div class="col-md-12" style="margin-left: -70px;">
                        <div id="container" ></div>
                        <div class = "areaDetail"></div>
                        <div id="container1" style="height: 100%;margin-top: 35px"></div>
                        <div id="container2" style="height: 100%"></div>
                    </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body" id="modal-body" style="margin-left: -55px">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script>
    var ss ='';
    function agencyType(){
        var type = '${project_type}';
        if(type != "100000000"){
            $("#container1").hide();
            $(".areaDetail").hide();
        }
    }
    function titleDetail(param) {
        var projectName = '${project.agencyName}';
        var projectTypeNum = '${project.agencyType}';
        var projectTypeName = null;
        agencyType();
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        var nowDate = new Date();
        var month = nowDate.getMonth();
        if(param == "ProjectInspection"){
            param = "日常巡检";
        }else if(param == "ProjectExamine"){
            param = "检查验收";
        }else if(param == "ProjectMaterial"){
            param = "材料验收";
        }else if(param == "ProjectSampleCheck"){
            param = "样板点评";
        }else if(param == "ProjectKeyProcesses"){
            param = "关键工序";
        }else if(param == "ProjectSideStation"){
            param = "旁站";
        }else if(param == "ProjectLeadersCheck"){
            param = "领导检查";
        }else{
            param = null;
        }

        if(projectTypeNum == "100000001"){
            projectTypeName = "各城市";
        }else if(projectTypeNum == "100000003"){
            projectTypeName = "各项目";
        }else if(projectTypeNum == "100000002"){
            projectTypeName = projectName;
        }else {
            projectTypeName = "各区域";
        }
        if(startDate != null && startDate != "" && endDate != null && endDate != ""){
            $(".detail").html(projectName+"从"+startDate+"到"+endDate+projectTypeName+"使用情况：");
            $(".areaDetail").html(projectName+"从"+startDate+"到"+endDate+projectTypeName+"APP使用情况："+
                "<button type='button' onclick='detailName()' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal' style='margin-right: 0'>查看</button>");
            if(param != null && param != ""){
                $(".detail").html(projectTypeName+"从"+startDate+"到"+endDate+param+"使用情况："+
                    "<button type='button' onclick='detailName()' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal'>查看</button>");
            }else{
                $(".detail").html(projectName+"从"+startDate+"到"+endDate+"各模块使用情况：");
            }
        }else if(startDate != null && startDate != "" && (endDate == null || endDate == "")){
            $(".detail").html(projectName+"从"+startDate+"到现在各模块使用情况：");
            $(".areaDetail").html(projectName+"从"+startDate+"到现在"+projectTypeName+"APP使用情况："+
                "<button type='button' onclick='detailName()' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal' style='margin-right: 0'>查看</button>");
            if(param != null && param != ""){
                $(".detail").html(projectTypeName+"从"+startDate+"到现在"+param+"使用情况："+
                    "<button type='button' onclick='detailName()' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal'>查看</button>");
            }else{
                $(".detail").html(projectName+"从"+startDate+"到现在各模块使用情况：");
            }
        }else if((startDate == null || startDate == "") && endDate != null && endDate != ""){
            $(".detail").html(projectName+"截止到"+endDate+"各模块使用情况：");
            $(".areaDetail").html(projectName+"截止到"+endDate+projectTypeName+"APP使用情况："+
                "<button type='button' onclick='detailName()' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal' style='margin-right: 0'>查看</button>");
            if(param != null && param != ""){
                $(".detail").html(projectTypeName+"截止到"+endDate+param+"使用情况："+
                    "<button type='button' onclick='detailName()' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal'>查看</button>");
            }else{
                $(".detail").html(projectName+"截止到"+endDate+"各模块使用情况：");
            }
        }else{
            $(".detail").html(projectName+"在"+month+"月份各模块使用情况:");
            $(".areaDetail").html(projectName+"在"+month+"月份"+projectTypeName+"APP使用情况:"+
                "<button type='button' onclick='detailName()' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal' style='margin-right: 0'>查看</button>");
            if(param != null && param != ""){
                $(".detail").html(projectTypeName+"在"+month+"月份"+param+"使用情况："+
                    "<button type='button' onclick='detailName()' class='btn btn-primary btn-lg' data-toggle='modal' data-target='#myModal'>查看</button>");
            }else{
                $(".detail").html(projectName+"在"+month+"月份各模块使用情况:");
            }
        }
    }
    titleDetail();
</script>

<script>
    function search() {
        if(!isDateBetween()){
            return;
        }
        param = ss;
        if(param == "ProjectInspection"){
            dataByProjecy("ProjectInspection");
        }else if(param == "ProjectExamine"){
            dataByProjecy("ProjectExamine");
        }else if(param == "ProjectMaterial"){
            dataByProjecy("ProjectMaterial");
        }else if(param == "ProjectSampleCheck"){
            dataByProjecy("ProjectSampleCheck");
        }else if(param == "ProjectKeyProcesses"){
            dataByProjecy("ProjectKeyProcesses");
        }else if(param == "ProjectSideStation"){
            dataByProjecy("ProjectSideStation");
        }else if(param == "ProjectLeadersCheck"){
            dataByProjecy("ProjectLeadersCheck");
        }else{
            document.forms[0].submit();
        }
    }
    function exportExcel(){
        if(!isDateBetween()){
            return;
        }
        var agencyId = getCookie('agencyId');
        var agencyType = getCookie('agencyType');
        window.location="../weekly/export";
        window.location.href = "../weekly/export?agencyId=" + agencyId + "&agencyType=" + agencyType +"&startDate=" + $("#startDate").val()+"&endDate="+$("#endDate").val();
    }
    function onClickByProject() {
        var agencyId = "${agencyId}";
        var agencyType = "${agencyType}";
        window.location="../weekly/export";
        window.location.href = "../weekly/weekly.html?agencyId=" + agencyId + "&agencyType=" + agencyType +"&startDate=" + $("#startDate").val()+"&endDate="+$("#endDate").val();
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
    var waitLists = {};//waitLists['id'+id]=[{name:name,id:id}]
    var ckeckCategory=2;//1 客关  2.工程  3.安全

    if(ckeckCategory=='2'){
        var setting = {
            async: {
                enable: false,
                url: "/weekly/weeklyAgency",
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
                url: "../weekly/weeklyAgency", dataType: "json", success: function (result) {
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
        if(ckeckCategory == '2'){
            window.location.href = "../weekly/weekly.html?agencyId=" + treeNode.id + "&agencyType=" + treeNode.agencyType +"&startDate=" + $("#startDate").val()+"&endDate="+$("#endDate").val();/*+"&category=2"*/
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
</script>
<script text="text/javascript">
    var oneProjectName = null;
    var oneCheckNum = 0;
    var onePercentOfPass = 0;
    var oneOverTwoWeekNum = 0;
    $("tr .btn-js").click(function() {
        $("tr .btn-js").removeClass("btn-primary")
        $(this).addClass("btn-primary").siblings().removeClass("btn-primary");
    });
    function dataByProjecy(param){
        ss=param;
        $(".areaDetail").hide();
        titleDetail(param);
        if(param == "ProjectInspection"){
            $("#pop").html("整改合格率(单位%)")
        }else{
            $("#pop").html("合格率(单位%)")
        }
        var index=$(this).index();
        //点击标题栏项目
        if(param == "onClickByProject"){
            onClickByProject();
            return false;
        }
        $(this).addClass("btn-primary").siblings().removeClass("btn-primary");

        var agencyId = getCookie('agencyId');
        var agencyType = getCookie('agencyType');

        if(param == "ProjectSideStation" || param == "ProjectLeadersCheck"){
            $.ajax({
                url: "../weekly/dataByProject",
                type: 'post',
                async: false,
                data: {
                    "startDate":$("#startDate").val(),
                    "endDate":$("#endDate").val(),
                    "agencyId":agencyId,
                    "agencyType":agencyType,
                    "requestParam":param,
                },
                success: function (data) {
                    var dataObj=eval(data);
                    var oneProjectNameList = new Array();
                    var oneCheckNumList = new Array();
                    var onePercentOfPassList = new Array();
                    var oneOverTwoWeekNumList = new Array();
                    $.each(dataObj,function(index,item){
                        oneProjectNameList.push(dataObj[index].projectName);
                        oneCheckNumList.push(dataObj[index].projectData.checkNum);
                        onePercentOfPassList.push(dataObj[index].projectData.percentOfPass);
                        oneOverTwoWeekNumList.push(dataObj[index].projectData.overTwoWeekNum);
                    });
                    assignParam(oneProjectNameList,oneCheckNumList,oneOverTwoWeekNumList,onePercentOfPassList);
                    $("#container").hide();
                    $("#container1").hide();
                    var dom2 = document.getElementById("container2");
                    var resizeWorldMapContainer2
                    if(oneProjectNameList.length>8){
                        resizeWorldMapContainer2 = function () {
                            dom2.style.width = window.innerWidth+'px';
                            dom2.style.height = 450+'px';
//                            dom2.style.height = window.innerHeight+'px';
                            dom2.style.marginLeft = '-55px';
                        };
                    }else {
                        resizeWorldMapContainer2 = function () {
                            dom2.style.width = '750px';
                            dom2.style.height = '450px';
                        };
                    }
                    resizeWorldMapContainer2();
                    var myChart2 = echarts.init(dom2);
                    var app = {};
                    var option2 = null;
                    var posList = [
                        'left', 'right', 'top', 'bottom',
                        'inside',
                        'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
                        'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
                    ];
                    app.configParameters = {
                        rotate: {
                            min: -90,
                            max: 90
                        },
                        align: {
                            options: {
                                left: 'left',
                                center: 'center',
                                right: 'right'
                            }
                        },
                        verticalAlign: {
                            options: {
                                top: 'top',
                                middle: 'middle',
                                bottom: 'bottom'
                            }
                        },
                        position: {
                            options: echarts.util.reduce(posList, function (map, pos) {
                                map[pos] = pos;
                                return map;
                            }, {})
                        },
                        distance: {
                            min: 0,
                            max: 100
                        }
                    };
                    app.config = {
                        rotate: 0,
                        align: 'center',
                        verticalAlign: 'middle',
                        position: 'top',
                        distance: 10,
                        onChange: function () {
                            var labelOption = {
                                normal: {
                                    rotate: app.config.rotate,
                                    align: app.config.align,
                                    verticalAlign: app.config.verticalAlign,
                                    position: app.config.position,
                                    distance: app.config.distance
                                }
                            };
                            myChart2.setOption({
                                series: [{
                                    label: labelOption
                                }, {
                                    label: labelOption
                                }, {
                                    label: labelOption
                                }, {
                                    label: labelOption
                                }]
                            });
                        }
                    };
                    var labelOption = {
                        normal: {
                            show: true,
                            position: app.config.position,
                            distance: app.config.distance,
                            align: app.config.align,
                            verticalAlign: app.config.verticalAlign,
                            rotate: app.config.rotate,
                            formatter: '{c}  {name|{a}}',
                            fontSize: 10,
                            rich: {
                                name: {
                                    textBorderColor: '#fff'
                                }
                            }
                        }
                    };
                    option2 = {
                        color: ['#06315d', '#e85a28','#e2a40e'],
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },

                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                data: oneProjectNameList,
                                axisLabel:{
                                    interval:0,//横轴信息全部显示
                                    rotate:-30,//-30度角倾斜显示
                                }
                            }
                        ],
                        yAxis: [
                            {
                                axisTick: {show: false},
                                splitLine:{show:false },
                                axisLabel : {show:false},
                                type: 'value'
                            }
                        ],
                        grid:{
//                        width:600,
                            height:300
                        },
                        series: [
                            {
                                name: '',
                                type: 'bar',
                                barWidth: 20,
                                barCategoryGap: '1%',
                                label: labelOption,
                                data: oneCheckNumList
                            }
                        ]
                    };
                    if (option2 && typeof option2 === "object") {
                        myChart2.setOption(option2, true);
                    }
                },error:function(data){
                    console.log(data);
                }
            });
        }
        else if(param == "ProjectMaterial"){$.ajax({
            url: "../weekly/dataByProject",
            type: 'post',
            async: false,
            data: {
                "startDate":$("#startDate").val(),
                "endDate":$("#endDate").val(),
                "agencyId":agencyId,
                "agencyType":agencyType,
                "requestParam":param,
            },
            success: function (data) {
                var dataObj=eval(data);
                var oneProjectNameList = new Array();
                var oneCheckNumList = new Array();
                var onePercentOfPassList = new Array();
                var oneOverTwoWeekNumList = new Array();
                $.each(dataObj,function(index,item){
                    oneProjectNameList.push(dataObj[index].projectName);
                    oneCheckNumList.push(dataObj[index].projectData.checkNum);
                    onePercentOfPassList.push(dataObj[index].projectData.percentOfPass);
                    oneOverTwoWeekNumList.push(dataObj[index].projectData.overTwoWeekNum);
                });
                //
                assignParam(oneProjectNameList,oneCheckNumList,oneOverTwoWeekNumList,onePercentOfPassList);
                $("#container").hide();
                $("#container1").hide();
                var dom2 = document.getElementById("container2");
                var resizeWorldMapContainer2
                if(oneProjectNameList.length>8){
                    resizeWorldMapContainer2 = function () {
                        dom2.style.width = window.innerWidth+'px';
                        dom2.style.height = 450+'px';
//                        dom2.style.height = window.innerHeight+'px';
                        dom2.style.marginLeft = '-55px';
                    };
                }else {
                    resizeWorldMapContainer2 = function () {
                        dom2.style.width = '750px';
                        dom2.style.height = '450px';
                    };
                }
                resizeWorldMapContainer2();
                var myChart2 = echarts.init(dom2);
                var app = {};
                var option2 = null;

                var posList = [
                    'left', 'right', 'top', 'bottom',
                    'inside',
                    'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
                    'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
                ];

                app.configParameters = {
                    rotate: {
                        min: -90,
                        max: 90
                    },
                    align: {
                        options: {
                            left: 'left',
                            center: 'center',
                            right: 'right'
                        }
                    },
                    verticalAlign: {
                        options: {
                            top: 'top',
                            middle: 'middle',
                            bottom: 'bottom'
                        }
                    },
                    position: {
                        options: echarts.util.reduce(posList, function (map, pos) {
                            map[pos] = pos;
                            return map;
                        }, {})
                    },
                    distance: {
                        min: 0,
                        max: 100
                    }
                };

                app.config = {
                    rotate: 0,
                    align: 'center',
                    verticalAlign: 'middle',
                    position: 'top',
                    distance: 10,
                    onChange: function () {
                        var labelOption = {
                            normal: {
                                rotate: app.config.rotate,
                                align: app.config.align,
                                verticalAlign: app.config.verticalAlign,
                                position: app.config.position,
                                distance: app.config.distance
                            }
                        };

                        myChart2.setOption({
                            series: [{
                                label: labelOption
                            }, {
                                label: labelOption
                            }, {
                                label: labelOption
                            }, {
                                label: labelOption
                            }]
                        });
                    }
                };


                var labelOption = {
                    normal: {
                        show: true,
                        position: app.config.position,
                        distance: app.config.distance,
                        align: app.config.align,
                        verticalAlign: app.config.verticalAlign,
                        rotate: app.config.rotate,
                        formatter: '{c}  {name|{a}}',
                        fontSize: 10,
                        rich: {
                            name: {
                                textBorderColor: '#fff'
                            }
                        }
                    }
                };


                option2 = {
                    color: ['#06315d','#e2a40e'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },

                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            data: oneProjectNameList,
                            axisLabel:{
                                interval:0,//横轴信息全部显示
                                rotate:-30,//-30度角倾斜显示
                            }
                        }
                    ],
                    yAxis: [
                        {
                            axisTick: {show: false},
                            splitLine:{show:false },
                            axisLabel : {show:false},
                            type: 'value'
                        }
                    ],
                    grid:{
//                        width:600,
                        height:300
                    },
                    series: [
                        {
                            name: '',
                            type: 'bar',
                            barWidth: 20,
                            barCategoryGap: '1%',
                            label: labelOption,
                            data: oneCheckNumList
                        },
                        {
                            name: '',
                            type: 'bar',
                            barWidth: 20,
                            barCategoryGap: '1%',
                            label: labelOption1,
                            data: onePercentOfPassList
                        },
                    ]
                };
                if (option2 && typeof option2 === "object") {
                    myChart2.setOption(option2, true);
                }
            },error:function(data){
                console.log(data);
            }
        });}else{
            $.ajax({
                url: "../weekly/dataByProject",
                type: 'post',
                async: false,
                data: {
                    "startDate":$("#startDate").val(),
                    "endDate":$("#endDate").val(),
                    "agencyId":agencyId,
                    "agencyType":agencyType,
                    "requestParam":param,
                },
                success: function (data) {
                    var dataObj=eval(data);
                    var oneProjectNameList = new Array();
                    var oneCheckNumList = new Array();
                    var onePercentOfPassList = new Array();
                    var oneOverTwoWeekNumList = new Array();
                    $.each(dataObj,function(index,item){
                        oneProjectNameList.push(dataObj[index].projectName);
                        oneCheckNumList.push(dataObj[index].projectData.checkNum);
                        onePercentOfPassList.push(dataObj[index].projectData.percentOfPass);
                        oneOverTwoWeekNumList.push(dataObj[index].projectData.overTwoWeekNum);
                    });
                    assignParam(oneProjectNameList,oneCheckNumList,oneOverTwoWeekNumList,onePercentOfPassList);
                    //
                    $("#container").hide();
                    $("#container1").hide();
                    var dom2 = document.getElementById("container2");
                    var resizeWorldMapContainer2
                    if(oneProjectNameList.length>8){
                        resizeWorldMapContainer2 = function () {
                            dom2.style.width = window.innerWidth+'px';
                            dom2.style.height = 450+'px';
//                            dom2.style.height = window.innerHeight+'px';
                            dom2.style.marginLeft = '-55px';
                        };
                    }else {
                        resizeWorldMapContainer2 = function () {
                            dom2.style.width = '750px';
                            dom2.style.height = '450px';
                        };
                    }
                    resizeWorldMapContainer2();
                    var myChart2 = echarts.init(dom2);
                    var app = {};
                    var option2 = null;

                    var posList = [
                        'left', 'right', 'top', 'bottom',
                        'inside',
                        'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
                        'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
                    ];

                    app.configParameters = {
                        rotate: {
                            min: -90,
                            max: 90
                        },
                        align: {
                            options: {
                                left: 'left',
                                center: 'center',
                                right: 'right'
                            }
                        },
                        verticalAlign: {
                            options: {
                                top: 'top',
                                middle: 'middle',
                                bottom: 'bottom'
                            }
                        },
                        position: {
                            options: echarts.util.reduce(posList, function (map, pos) {
                                map[pos] = pos;
                                return map;
                            }, {})
                        },
                        distance: {
                            min: 0,
                            max: 100
                        }
                    };

                    app.config = {
                        rotate: 0,
                        align: 'center',
                        verticalAlign: 'middle',
                        position: 'top',
                        distance: 10,
                        onChange: function () {
                            var labelOption = {
                                normal: {
                                    rotate: app.config.rotate,
                                    align: app.config.align,
                                    verticalAlign: app.config.verticalAlign,
                                    position: app.config.position,
                                    distance: app.config.distance
                                }
                            };

                            myChart2.setOption({
                                series: [{
                                    label: labelOption
                                }, {
                                    label: labelOption
                                }, {
                                    label: labelOption
                                }, {
                                    label: labelOption
                                }]
                            });
                        }
                    };


                    var labelOption = {
                        normal: {
                            show: true,
                            position: app.config.position,
                            distance: app.config.distance,
                            align: app.config.align,
                            verticalAlign: app.config.verticalAlign,
                            rotate: app.config.rotate,
                            formatter: '{c}  {name|{a}}',
                            fontSize: 10,
                            rich: {
                                name: {
                                    textBorderColor: '#fff'
                                }
                            }
                        }
                    };


                    option2 = {
                        color: ['#06315d', '#e85a28','#e2a40e'],
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        /*legend: {
                         orient: 'vertical',
                         x: 'right',
                         data: ['检查次数', '合格率（单位%）', '超过两周以上未整改内容']
                         },*/

                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                data: oneProjectNameList,
                                axisLabel:{
                                    interval:0,//横轴信息全部显示
                                    rotate:-30,//-30度角倾斜显示
                                }
                            }
                        ],
                        yAxis: [
                            {
                                axisTick: {show: false},
                                splitLine:{show:false },
                                axisLabel : {show:false},
                                type: 'value'
                            }
                        ],
                        grid:{
//                        width:600,
                            height:300
                        },
                        series: [
                            {
                                name: '',
                                type: 'bar',
                                barWidth: 20,
                                barCategoryGap: '1%',
                                label: labelOption,
                                data: oneCheckNumList
                            },
                            {
                                name: '',
                                type: 'bar',
                                barWidth: 20,
                                barCategoryGap: '1%',
                                label: labelOption,
                                data: oneOverTwoWeekNumList
                            },
                            {
                                name: '',
                                type: 'bar',
                                barWidth: 20,
                                barCategoryGap: '1%',
                                label: labelOption1,
                                data: onePercentOfPassList
                            },
                        ]
                    };
                    if (option2 && typeof option2 === "object") {
                        myChart2.setOption(option2, true);
                    }
                },error:function(data){
                    console.log(data);
                }
            });
        }
    }
</script>

<script type="text/javascript">
    var allProjectDataJson=${allProjectDataJson};
    var projectName = new Array();
    var CheckNum = new Array();
    var PercentOfPass = new Array();
    var OverTwoWeekNum = new Array();
    var length = ${projectSize};

    for(var i=0;i<allProjectDataJson.length;i++){
        projectName.push(allProjectDataJson[i].name);
        CheckNum.push(allProjectDataJson[i].CheckNum);
        PercentOfPass.push(allProjectDataJson[i].PercentOfPass);
        OverTwoWeekNum.push(allProjectDataJson[i].OverTwoWeekNum);
    }


    var dom = document.getElementById("container");
    var dom1 = document.getElementById("container1");

    //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
    var resizeWorldMapContainer = function () {
//        dom.style.width = window.innerWidth+'px';
//        dom.style.height = window.innerHeight+'px';
        dom.style.width = '700px';
        dom.style.height = '350px';
    };
    var resizeWorldMapContainer1 ;
    if(length>8){
        resizeWorldMapContainer1 = function () {
            dom1.style.width = window.innerWidth+'px';
            /*dom1.style.height = window.innerHeight+'px';*/
            dom1.style.height = 400+'px';
            dom1.style.marginLeft = '-55px';
        };
    }else {
        resizeWorldMapContainer1 = function () {
            dom1.style.width = '750px';
            dom1.style.height = '400px';
        };
    }

    //设置容器高宽
    resizeWorldMapContainer();
    resizeWorldMapContainer1();

    window.onresize = function () {
        //重置容器高宽
        resizeWorldMapContainer();
        resizeWorldMapContainer1();
        myChart.resize();
        myChart1.resize();
    };
    var myChart = echarts.init(dom);
    var myChart1 = echarts.init(dom1);
    var app = {};
    option = null;
    option1 = null;
    var posList = [
        'left', 'right', 'top', 'bottom',
        'inside',
        'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
        'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
    ];
    app.configParameters = {
        rotate: {
            min: -90,
            max: 90
        },
        align: {
            options: {
                left: 'left',
                center: 'center',
                right: 'right'
            }
        },
        verticalAlign: {
            options: {
                top: 'top',
                middle: 'middle',
                bottom: 'bottom'
            }
        },
        position: {
            options: echarts.util.reduce(posList, function (map, pos) {
                map[pos] = pos;
                return map;
            }, {})
        },
        distance: {
            min: 0,
            max: 100
        }
    };
    app.config = {
        rotate: 0,
        align: 'center',
        verticalAlign: 'middle',
        position: 'top',
        distance: 10,
        onChange: function () {
            var labelOption = {
                normal: {
                    rotate: app.config.rotate,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    position: app.config.position,
                    distance: app.config.distance
                }
            };
            myChart.setOption({
                series: [{
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }]
            });
            myChart1.setOption({
                series: [{
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }]
            });
        }
    };
    var labelOption = {
        normal: {
            show: true,
            position: app.config.position,
            distance: app.config.distance,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            rotate: app.config.rotate,
            formatter: '{c}  {name|{a}}',
            fontSize: 10,
            rich: {
                name: {
                    textBorderColor: '#fff'
                }
            }
        }
    };
    var labelOption1 = {
        normal: {
            show: true,
            position: app.config.position,
            distance: app.config.distance,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            rotate: app.config.rotate,
            formatter: '{c}{name|{a}}%',
            fontSize: 10,
            rich: {
                name: {
                    textBorderColor: '#fff'
                }
            }
        }
    };
    option = {
        color: ['#06315d', '#e85a28','#e2a40e'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        /*legend: {
            orient: 'vertical',
            x: 'right',
            data: ['检查次数', '合格率（单位%）', '超过两周以上未整改内容']
        },*/

        calculable: true,
        xAxis: [
            {
                type: 'category',
                data: ['日常巡检', '检查验收', '材料验收', '样板点评', '关键工序','旁站','领导检查']
            }
        ],
        yAxis: [
            {
                axisTick: {show: false},
                splitLine:{show:false },
                axisLabel : {show:false},
                type: 'value'
            }
        ],
        grid:{
            width:600,
            height:250
        },
        series: [
            {
                name: '',
                type: 'bar',
                barWidth: 20,
                barCategoryGap: '3%',
                label: labelOption,
                data: [${count.ProjectInspection.checkNum}, ${count.ProjectExamine.checkNum}, ${count.ProjectMaterial.checkNum}, ${count.ProjectSampleCheck.checkNum}, ${count.ProjectKeyProcesses.checkNum},${count.ProjectSideStation.checkNum},${count.ProjectLeadersCheck.checkNum}]
            },
            {
                name: '',
                type: 'bar',
                barWidth: 20,
                barCategoryGap: '3%',
                label: labelOption,
                data: [${count.ProjectInspection.overTwoWeekNum}, ${count.ProjectExamine.overTwoWeekNum}, ${count.ProjectMaterial.overTwoWeekNum}, ${count.ProjectSampleCheck.overTwoWeekNum}, ${count.ProjectKeyProcesses.overTwoWeekNum},${count.ProjectSideStation.overTwoWeekNum},${count.ProjectLeadersCheck.overTwoWeekNum}]
            },
            {
                name: '',
                type: 'bar',
                barWidth: 20,
                barCategoryGap: '3%',
                label: labelOption1,
                data: [${count.ProjectInspection.percentOfPass}, ${count.ProjectExamine.percentOfPass}, ${count.ProjectMaterial.percentOfPass}, ${count.ProjectSampleCheck.percentOfPass}, ${count.ProjectKeyProcesses.percentOfPass},${count.ProjectSideStation.percentOfPass},${count.ProjectLeadersCheck.percentOfPass}]
            },
        ]
    };
    option1 = {
        color: ['#06315d', '#e85a28','#e2a40e'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        /*legend: {
            orient: 'vertical',
            x: 'right',
            data: ['检查次数', '合格率（单位%）', '超过两周以上未整改内容']
        },*/

        calculable: true,
        xAxis: [
            {
                type: 'category',
                data: projectName ,
                axisLabel:{
                    interval:0,//横轴信息全部显示
                    rotate:-30,//-30度角倾斜显示
                }
            }
        ],
        yAxis: [
            {
                axisTick: {show: false},
                splitLine:{show:false },
                axisLabel : {show:false},
                type: 'value'
            }
        ],
        grid:{
//            width:600,
            height:250
        },
        series: [
            {
                name: '',
                type: 'bar',
                barWidth: 20,
                barCategoryGap: '1%',
                label: labelOption,
                data: CheckNum
            },
            {
                name: '',
                type: 'bar',
                barWidth: 20,
                barCategoryGap: '1%',
                label: labelOption,
                data: OverTwoWeekNum
            },
            {
                name: '',
                type: 'bar',
                barWidth: 20,
                barCategoryGap: '1%',
                label: labelOption1,
                data: PercentOfPass
            },
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
    if (option1 && typeof option1 === "object") {
        myChart1.setOption(option1, true);
    }
</script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
</script>
<script>
    function isDateBetween(){
        var flag = true;
        var startDateString = $("#startDate").val();
        if(startDateString != null && startDateString != ''){
            var startDate=new Date(startDateString.replace("-","/"));
            var date = new Date();
            if(startDate>date){
                alert("开始时间不能大于当前时间");
                flag = false;
            }
            var endDateString = $("#endDate").val();
            if(endDateString != null && endDateString != ''){
                var endDate=new Date(endDateString.replace("-","/"));
                if(endDate<startDate){
                    alert("结束时间不能大于开始时间");
                    flag = false;
                }
            }
        }
        return flag;
    };
</script>
<style>
    .modal-dialog{
        width: 90%;
        height: 30%;
    }
</style>
<script>
    var modelCheckNum = CheckNum;
    var modelOverTwoWeekNum = OverTwoWeekNum;
    var modelPercentOfPass = PercentOfPass;
    var modelProjectName = projectName;
    var barWidthParam = 20;
    function detailName(){
        var text = $(".areaDetail").text();
        $("#myModalLabel").html(text.substr(0,text.length-2));
        model();
    }
    function assignParam(ProjectNameParam,CheckNumParam,OverTwoWeekNumParam,PercentOfPassParam){
        modelProjectName = ProjectNameParam;
        modelCheckNum = CheckNumParam;
        modelOverTwoWeekNum = OverTwoWeekNumParam;
        modelPercentOfPass = PercentOfPassParam;
    }
    function model() {
        if(modelProjectName.length>9){
            barWidthParam = 10;
        }
        var dom3 = document.getElementById("modal-body");

        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer3 = function () {
            dom3.style.width = window.innerWidth+'px'
            dom3.style.height = '450px';
        };

        //设置容器高宽
        resizeWorldMapContainer3();

        window.onresize = function () {
            //重置容器高宽
            resizeWorldMapContainer3
            myChart3.resize();
        };
        var myChart3 = echarts.init(dom3);
        var app = {};
        option3 = null;
        var posList = [
            'left', 'right', 'top', 'bottom',
            'inside',
            'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
            'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
        ];
        app.configParameters = {
            rotate: {
                min: -90,
                max: 90
            },
            align: {
                options: {
                    left: 'left',
                    center: 'center',
                    right: 'right'
                }
            },
            verticalAlign: {
                options: {
                    top: 'top',
                    middle: 'middle',
                    bottom: 'bottom'
                }
            },
            position: {
                options: echarts.util.reduce(posList, function (map, pos) {
                    map[pos] = pos;
                    return map;
                }, {})
            },
            distance: {
                min: 0,
                max: 100
            }
        };
        app.config = {
            rotate: 0,
            align: 'center',
            verticalAlign: 'middle',
            position: 'top',
            distance: 10,
            onChange: function () {
                var labelOption = {
                    normal: {
                        rotate: app.config.rotate,
                        align: app.config.align,
                        verticalAlign: app.config.verticalAlign,
                        position: app.config.position,
                        distance: app.config.distance
                    }
                };
                myChart3.setOption({
                    series: [{
                        label: labelOption
                    }, {
                        label: labelOption
                    }, {
                        label: labelOption
                    }, {
                        label: labelOption
                    }]
                });
            }
        };

        option3 = {
            color: ['#06315d', '#e85a28','#e2a40e'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            /*legend: {
             orient: 'vertical',
             x: 'right',
             data: ['检查次数', '合格率（单位%）', '超过两周以上未整改内容']
             },*/

            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    data: modelProjectName ,
                    axisLabel:{
                        interval:0,//横轴信息全部显示
                        rotate:-30,//-30度角倾斜显示
                    }
                }
            ],
            yAxis: [
                {
                    axisTick: {show: false},
                    splitLine:{show:false },
                    axisLabel : {show:false},
                    type: 'value'
                }
            ],
            grid:{
//            width:600,
                height:250
            },
            series: [
                {
                    name: '',
                    type: 'bar',
                    barWidth: barWidthParam,
                    barCategoryGap: '1%',
                    label: labelOption,
                    data: modelCheckNum
                },
                {
                    name: '',
                    type: 'bar',
                    barWidth: barWidthParam,
                    barCategoryGap: '1%',
                    label: labelOption,
                    data: modelOverTwoWeekNum
                },
                {
                    name: '',
                    type: 'bar',
                    barWidth: barWidthParam,
                    barCategoryGap: '1%',
                    label: labelOption1,
                    data: modelPercentOfPass
                },
            ]
        };
        option4 = {
            color: ['#06315d','#e2a40e'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            /*legend: {
             orient: 'vertical',
             x: 'right',
             data: ['检查次数', '合格率（单位%）', '超过两周以上未整改内容']
             },*/

            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    data: modelProjectName ,
                    axisLabel:{
                        interval:0,//横轴信息全部显示
                        rotate:-30,//-30度角倾斜显示
                    }
                }
            ],
            yAxis: [
                {
                    axisTick: {show: false},
                    splitLine:{show:false },
                    axisLabel : {show:false},
                    type: 'value'
                }
            ],
            grid:{
//            width:600,
                height:250
            },
            series: [
                {
                    name: '',
                    type: 'bar',
                    barWidth: barWidthParam,
                    barCategoryGap: '1%',
                    label: labelOption,
                    data: modelCheckNum
                },
                {
                    name: '',
                    type: 'bar',
                    barWidth: barWidthParam,
                    barCategoryGap: '1%',
                    label: labelOption1,
                    data: modelPercentOfPass
                },
            ]
        };
        option5 = {
            color: ['#06315d', '#e85a28','#e2a40e'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            /*legend: {
             orient: 'vertical',
             x: 'right',
             data: ['检查次数', '合格率（单位%）', '超过两周以上未整改内容']
             },*/

            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    data: modelProjectName ,
                    axisLabel:{
                        interval:0,//横轴信息全部显示
                        rotate:-30,//-30度角倾斜显示
                    }
                }
            ],
            yAxis: [
                {
                    axisTick: {show: false},
                    splitLine:{show:false },
                    axisLabel : {show:false},
                    type: 'value'
                }
            ],
            grid:{
//            width:600,
                height:250
            },
            series: [
                {
                    name: '',
                    type: 'bar',
                    barWidth: barWidthParam,
                    barCategoryGap: '1%',
                    label: labelOption,
                    data: modelCheckNum
                },
            ]
        };
        if (option3 && typeof option3 === "object") {
            if(ss == "ProjectMaterial"){
                myChart3.setOption(option4, true);
            }else if(ss == "ProjectSideStation" || ss == "ProjectLeadersCheck"){
                myChart3.setOption(option5, true);
            }else{
                myChart3.setOption(option3, true);
            }
        }
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>

</body>
</html>