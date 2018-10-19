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
            console.log("sqq")
            $("#006000020000").addClass("active");
            $("#006000020000").parent().parent().addClass("in");
            $("#006000020000").parent().parent().parent().parent().addClass("active");
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
    </style>
    <%-- zTree --%>
    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
    <script type="text/javascript">
        //通过城市获取项目列表
        function queryProjectNameById() {
            var projectId = $("#scopeId").val();
            if(projectId == '-1'){
                $("#projectCode").empty();
                return ;
            }
            $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
                var arr = data.data;
                $("#projectCode").empty();
                $("#projectCode").append('<option value="">请选择</option>');
                for (var k in arr) {
                    if(arr[k][0] != '0'){
                        $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
                    }
                }
            });
        }

        function toAddPhone(){
            var html = '<div class="form-group col-lg-8"><label class="col-sm-3 control-label">手机号码</label>' +
                '<div class="col-sm-3"><input type="text" class="form-control" placeholder="" name="phone"></div></div>';
            $("#phoneDiv").prepend(html);
        }

        //保存
        function toSave(status){
            if($("#scopeId").val() == "" || $("#scopeId").val() == "0"){
                alert("请选择发布区域！");
                return;
            }
            if($("#projectCode").val() == "0" || $("#projectCode").val() == ""){
                alert("请选择发布项目！");
                return;
            }
            if ($("#listName").val() == "") {
                alert("请输入名单名称！");
                return;
            }
//        var title = $("#title").val();
//        if (title.length > 100) {
//            alert("名单名称超过最大长度！");
//            return;
//        }
            $("#cityName").val($("#scopeId option:selected").text());
            if ($("#projectCode option:selected").text() != "请选择"){
                $("#projectName").val($("#projectCode option:selected").text());
            }
            //遍历手机号码集合
            if($("input[name='listType']:checked").val() == 1){
                var phoneCollection = "";
                $("input[name='phone']").each(function(){
                    phoneCollection += $(this).val() + ",";
                })
                $("#phoneCollection").val(phoneCollection);
            }
            //遍历房产集合
            if($("input[name='listType']:checked").val() == 2){
                var houseCollection = "";
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                var nodes = treeObj.getCheckedNodes(true);
                for (var i = 0,length = nodes.length; i<length; i++){
                    houseCollection += nodes[i].value + ",";
                }
                $("#houseCollection").val(houseCollection);
            }
            $("#fromAdd").submit();
        }

        function toSave_New(){
            if($("#scopeId").val() == "" || $("#scopeId").val() == "0"){
                alert("请选择发布区域！");
                return;
            }
            if($("#projectCode").val() == "0" || $("#projectCode").val() == ""){
                alert("请选择发布项目！");
                return;
            }
            if ($("#listName").val() == "") {
                alert("请输入名单名称！");
                return;
            }
            $("#cityName").val($("#scopeId option:selected").text());
            if ($("#projectCode option:selected").text() != "请选择"){
                $("#projectName").val($("#projectCode option:selected").text());
            }
            //遍历手机号码集合
            if($("input[name='listType']:checked").val() == 1){
                var phoneCollection = "";
                $("input[name='phone']").each(function(){
                    phoneCollection += $(this).val() + ",";
                })
                $("#phoneCollection").val(phoneCollection);
            }
            //遍历房产集合
            if($("input[name='listType']:checked").val() == 2){
                var houseCollection = "";
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                var nodes = treeObj.getCheckedNodes(true);
                for (var i = 0,length = nodes.length; i<length; i++){
                    houseCollection += nodes[i].value + ",";
                }
                $("#houseCollection").val(houseCollection);
            }
            $.ajax({
                type: 'POST',
                url: "../blacklist/saveOrUpdateBlacklist",
                async:"false",
                data: {
                    id:$("#id").val(),
                    cityId:$("#scopeId").val(),
                    cityName:$("#cityName").val(),
                    projectCode:$("#projectCode").val(),
                    projectName:$("#projectName").val(),
                    listName:$("#listName").val(),
                    promptContent:$("#promptContent").val(),
                    listType:$("input[name='listType']:checked").val(),
                    phoneCollection:$("#phoneCollection").val(),
                    houseCollection:$("#houseCollection").val()
                },
                error:function(){
                    alert("网络异常，可能服务器忙，请刷新重试");
                },
                success: function (data) {
                    if(data.error == 0){
                        alert("保存成功！");
                        window.location.href = "../blacklist/getBlacklistList.html?menuId=006000020000";
                    }else if(data.error == -1){
                        alert("保存失败！");
                        window.location.href = "../blacklist/getBlacklistList.html?menuId=006000020000";
                    }else if(data.error == 1){
                        alert("名单名称已存在！请重新填写保存。");
                    }
                }
            });
        }
    </script>
    <script type="text/javascript">

        // zTree 的数据属性,深入使用请参考 API 文档(zTreeNode 节点数据详解)
        //    var zNodes =[
        //        {id:"1",pId:"0",name:"地块_西一",value:"GZ-NSJMW-X1",isParent:"true"},
        //        {id:"11",pId:"1",name:"楼栋_T1",value:"GZ-NSJMW-X1-T1",isParent:"true"},
        //        {id:"1A",pId:"11",name:"单元_无",value:"GZ-NSJMW-X1-T1-#",isParent:"true"},
        //        {id:"1111",pId:"1A",name:"南沙金茂湾T1号楼303",value:"024b1dea-be7e-4256-a60d-3dbecda0c3c1",isParent:"false"},
        //        {id:"1112",pId:"1A",name:"南沙金茂湾T1号楼308",value:"0510bba1-ce77-42d4-a489-cdf1b5df2a0b",isParent:"false"},
        //        {id:"1113",pId:"1A",name:"南沙金茂湾T1号楼504",value:"053b7d4e-bb23-4407-9070-6c5a8c04dba8",isParent:"false",checked:true},
        //        {id:"1114",pId:"1A",name:"南沙金茂湾T1号楼116",value:"0667422c-2b1c-4fd4-ad92-50096d772dd2",isParent:"false",checked:true},
        //        {id:"1115",pId:"1A",name:"南沙金茂湾T1号楼720",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        //        {id:"1116",pId:"11",name:"南沙金茂湾T1号楼916",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"false"}
        //    ];

        var scopeJson = <c:if test="${empty scopeJson}">''</c:if><c:if test="${!empty scopeJson}">${scopeJson}</c:if>;
        // zTree 的参数配置,深入使用请参考 API 文档(setting 配置详解)
        var setting = {
            check: {
                enable: true
            },
//            data: {
//                simpleData: {
//                    enable: true
//                }
//            }
            data: {
                simpleData: {
                    enable: true, //设置是否启用简单数据格式（zTree支持标准数据格式跟简单数据格式，上面例子中是标准数据格式）
                    idKey: "id", //设置启用简单数据格式时id对应的属性名称
                    pidKey: "pId" //设置启用简单数据格式时parentId对应的属性名称,ztree根据id及pid层级关系构建树结构
                }
            }

        };

        var code;
        function setCheck() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                py = $("#py").attr("checked")? "p":"",
                sy = $("#sy").attr("checked")? "s":"",
                pn = $("#pn").attr("checked")? "p":"",
                sn = $("#sn").attr("checked")? "s":"",
                type = {"Y" : "ps", "N" : "ps"};
            zTree.setting.check.chkboxType = type;
            showCode('setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };');
        }

        function showCode(str) {
            if (!code) code = $("#code");
            code.empty();
            code.append("<li>"+str+"</li>");
        }

        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, scopeJson);
            setCheck();
            $("#py").bind("change", setCheck);
            $("#sy").bind("change", setCheck);
            $("#pn").bind("change", setCheck);
            $("#sn").bind("change", setCheck);
        });

        function listTypeOnChange(value){
            if (value == "1"){
                $("#houseDiv").hide();
                $("#phoneDiv").show();
            }else{
                $("#phoneDiv").hide();
                $("#houseDiv").show();
                if ($("#projectCode").val() != "" && $("#projectCode").val() != "0"){
                    $.ajax({
                        type: "POST",
                        url: "../blacklist/getScopeTreeByHouseCollection",
                        data:{
                            id:$("#id").val(),
                            projectCode:$("#projectCode").val()
                        },
                        dataType:"json",
                        success: function (data) {
                            if (data.error == 0){
                                $.fn.zTree.init($("#treeDemo"), setting,eval('('+ data.scopeTree +')'));
                                setCheck();
                                $("#py").bind("change", setCheck);
                                $("#sy").bind("change", setCheck);
                                $("#pn").bind("change", setCheck);
                                $("#sn").bind("change", setCheck);
                            }
                        },
                        beforeSend: function(){
                            $("#phoneRadio").attr("disabled", true);
                            $("#houseRadio").attr("disabled", true);
                        },
                        complete: function(){
                            $("#phoneRadio").attr("disabled", false);
                            $("#houseRadio").attr("disabled", false);
                        }
                    });
                }
            }
        }

        function changeProject(){
            if($("input[name='listType']:checked").val() == 2 && $("#projectCode").val() != "" && $("#projectCode").val() != "0"){
                $.ajax({
                    type: "POST",
                    url: "../blacklist/getScopeTreeByHouseCollection",
                    data:{
                        id:$("#id").val(),
                        projectCode:$("#projectCode").val()
                    },
                    cache: false,
                    dataType:"json",
                    success: function (data) {
                        if (data.error == 0){
                            $.fn.zTree.init($("#treeDemo"), setting,eval('('+ data.scopeTree +')'));
                            setCheck();
                            $("#py").bind("change", setCheck);
                            $("#sy").bind("change", setCheck);
                            $("#pn").bind("change", setCheck);
                            $("#sn").bind("change", setCheck);
                        }
                    }
                });
            }
        }
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006000020000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="006000020000"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../blacklist/saveOrUpdateBlacklist.html" method="post">
                    <input type="hidden" id="id" name="id" value="${blacklistInfo.id}">
                    <!-- 城市 -->
                    <div class="form-group  col-lg-7">
                        <label for="scopeId" class="col-sm-3 control-label">发布区域</label>
                        <div class="col-sm-5">
                            <select id="scopeId" name="cityId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq blacklistInfo.cityId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" id="cityName" name="cityName" value="${blacklistInfo.cityName}"/>
                    <!-- 项目 -->
                    <div class="form-group  col-lg-7">
                        <label for="projectCode" class="col-sm-3 control-label">发布项目</label>
                        <div class="col-sm-5">
                            <select id="projectCode" name="projectCode" class="form-control" onchange="changeProject()">
                                <c:forEach items="${projectList}" var="item" >
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq blacklistInfo.projectCode}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" id="projectName" name="projectName" value="${blacklistInfo.projectName}"/>
                    <%-- 名单名称 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="listName">名单名称</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="listName" name="listName" value="${blacklistInfo.listName}">
                        </div>
                    </div>
                    <%-- 提示内容 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="promptContent">提示内容</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="promptContent" name="promptContent" value="${blacklistInfo.promptContent}">
                        </div>
                    </div>
                    <%-- 名单类型 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">名单类型</label>
                        <div class="col-sm-5">
                            <input type="radio" id="phoneRadio" name="listType" value="1" onclick="listTypeOnChange(this.value)" <c:if test="${empty blacklistInfo.listType or blacklistInfo.listType eq '1'}">checked</c:if>>
                            <label for="phoneRadio">手机号码集合</label>
                            <input type="radio" id="houseRadio" name="listType" value="2" onclick="listTypeOnChange(this.value)" <c:if test="${blacklistInfo.listType eq '2'}">checked</c:if>>
                            <label for="houseRadio">房产集合</label>
                        </div>
                    </div>
                    <%-- 名单范围 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">名单范围</label>
                        <div class="col-sm-5"></div>
                    </div>
                    <%-- 手机号码集合 --%>
                    <c:if test="${empty blacklistInfo.listType or blacklistInfo.listType eq '1'}"><div id="phoneDiv"></c:if>
                    <c:if test="${blacklistInfo.listType eq '2'}"><div id="phoneDiv" style="display:none;"></c:if>
                        <c:if test="${!empty blacklistInfo.phoneList}">
                            <c:forEach items="${blacklistInfo.phoneList}" var="phoneStr" varStatus="status">
                                <div class="form-group col-lg-8">
                                    <label class="col-sm-3 control-label">手机号码</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" placeholder="" name="phone" value="${phoneStr}">
                                    </div>
                                    <c:if test="${status.index eq fn:length(blacklistInfo.phoneList) - 1}">
                                        <div class="col-sm-2">
                                            <button type="button" class="btn btn-primary" onclick="toAddPhone()">新增</button>
                                        </div>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty blacklistInfo.phoneList}">
                            <div class="form-group col-lg-8">
                                <label class="col-sm-3 control-label">手机号码</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="" name="phone" value="">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-primary" onclick="toAddPhone()">新增</button>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <input type="hidden" id="phoneCollection" name="phoneCollection" value="">

                    <%-- 房产集合 --%>
                    <c:if test="${empty blacklistInfo.listType or blacklistInfo.listType eq '1'}"><div id="houseDiv" style="display:none;"></c:if>
                    <c:if test="${blacklistInfo.listType eq '2'}"><div id="houseDiv"></c:if>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label">房产列表</label>
                            <div class="col-sm-4">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="houseCollection" name="houseCollection" value="">

                    <div class="text-center form-group  col-lg-6">
                        <button type="button" class="btn btn-primary" onclick="toSave_New()">保存</button>
                        <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);">
                            <spring:message code="common_cancel"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</body>
</html>

