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
            $("#005400030000").addClass("active");
            $("#005400030000").parent().parent().addClass("in");
            $("#005400030000").parent().parent().parent().parent().addClass("active");
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
        //暂存
        function toSave(){
            //遍历房产集合
            var houseCollection = "";
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
            for (var i = 0,length = nodes.length; i<length; i++){
                houseCollection += nodes[i].value + ",";
            }
            $("#houseScope").val($("#houseScope").val() + houseCollection);
            alert("暂存完毕！");
        }

        function toSubmit(){
            $.ajax({
                type: 'POST',
                url: "../community/saveHouseScope",
                async:"false",
                data: {
                    activityId:$("#activityId").val(),
                    houseScope:$("#houseScope").val()
                },
                error:function(){
                    alert("网络异常，可能服务器忙，请刷新重试");
                },
                success: function (data) {
                    if(data.error == 0){
                        alert("保存成功！");
                    }else if(data.error == -1){
                        alert("保存失败！");
                    }
                    window.location.href = "../community/activityManage.html";
                }
            });

        }
    </script>
    <script type="text/javascript">
        var scopeJson = <c:if test="${empty scopeJson}">''</c:if><c:if test="${!empty scopeJson}">${scopeJson}</c:if>;
        // zTree 的参数配置,深入使用请参考 API 文档(setting 配置详解)
        var setting = {
            check: {
                enable: true
            },
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

        function changeProject(){
            if($("#projectCode").val() != "" && $("#projectCode").val() != "0"){
                $("#houseDiv").show();
                $.ajax({
                    type: "POST",
                    url: "../community/getHouseScopeTree",
                    data:{
                        activityId:$("#activityId").val(),
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
            }else{
                $("#houseDiv").hide();
            }
        }
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005400030000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="005400030000"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../community/saveHouseScope" method="post">
                    <input type="hidden" id="activityId" name="activityId" value="${activityAdminDto.activityId}">
                    <!-- 项目 -->
                    <div class="form-group  col-lg-7">
                        <label for="projectCode" class="col-sm-3 control-label">发布项目</label>
                        <div class="col-sm-5">
                            <select id="projectCode" name="projectCode" class="form-control" onchange="changeProject()">
                                <c:if test="${error eq 1}"><option value="">活动覆盖项目过多,不适合进行分配房产范围</option></c:if>
                                <c:if test="${error eq 0}">
                                    <option value="">请选择</option>
                                    <c:forEach items="${activityScopeList}" var="activityScope" >
                                        <option value="${activityScope.projectId}">${activityScope.projectName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <%-- 名单范围 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">房产范围</label>
                        <input type="hidden" id="houseScope" name="houseScope">
                        <div class="col-sm-5"></div>
                    </div>
                    <%-- 房产集合 --%>
                    <div id="houseDiv" style="display:none;">
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label">房产列表</label>
                            <div class="col-sm-4">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label">单个项目选择完毕需暂存</label>
                            <div class="col-sm-4">
                                <button type="button" class="btn btn-primary" onclick="toSave()">暂存</button>
                            </div>
                        </div>
                    </div>
                    <div class="text-center form-group  col-lg-6">
                        <button type="button" class="btn btn-primary" onclick="toSubmit()">提交</button>
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

