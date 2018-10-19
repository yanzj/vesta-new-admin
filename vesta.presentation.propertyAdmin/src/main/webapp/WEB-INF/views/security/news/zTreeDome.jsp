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
            $("#007000010000").addClass("active");
            $("#007000010000").parent().parent().addClass("in");
            $("#007000010000").parent().parent().parent().parent().addClass("active");
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
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007000010000" username="${authPropertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="007000010000"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../butlerStyle/saveOrUpdateButlerScope" method="post" enctype="multipart/form-data">
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label">测试数据</label>
                        <div class="content_wrap col-md-3">
                            <div class="zTreeDemoBackground left">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    <%--var scopeJson = ${scopeJson};--%>
    // zTree 的参数配置,深入使用请参考 API 文档(setting 配置详解)
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    // zTree 的数据属性,深入使用请参考 API 文档(zTreeNode 节点数据详解)
    var zNodes =[
        {id:"1",pId:"0",name:"爷节点1",value:"GZ-NSJMW-X1",isParent:"true",open:"true"},
        {id:"11",pId:"1",name:"父节点1",value:"GZ-NSJMW-X1-T1",isParent:"true",open:"true"},
        {id:"111",pId:"11",name:"孙节点1",value:"GZ-NSJMW-X1-T1-#",isParent:"true",open:"true"},
        {id:"111",pId:"11",name:"孙节点1",value:"024b1dea-be7e-4256-a60d-3dbecda0c3c1",isParent:"true"},
        {id:"112",pId:"11",name:"孙节点1",value:"0510bba1-ce77-42d4-a489-cdf1b5df2a0b",isParent:"true"},
        {id:"113",pId:"11",name:"孙节点1",value:"053b7d4e-bb23-4407-9070-6c5a8c04dba8",isParent:"true"},
        {id:"114",pId:"11",name:"孙节点1",value:"0667422c-2b1c-4fd4-ad92-50096d772dd2",isParent:"true"},
        {id:"115",pId:"11",name:"孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"true"},
        {id:"116",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"117",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"118",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"119",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"120",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"121",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"122",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"1111",pId:"111",name:"孙孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"false"},
        {id:"1112",pId:"111",name:"孙孙节点1",value:"0510bba1-ce77-42d4-a489-cdf1b5df2a0b",isParent:"false"},
        {id:"1113",pId:"111",name:"孙孙节点1",value:"053b7d4e-bb23-4407-9070-6c5a8c04dba8",isParent:"false"},
        {id:"1114",pId:"111",name:"孙孙节点1",value:"0667422c-2b1c-4fd4-ad92-50096d772dd2",isParent:"false"},
        {id:"1115",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        {id:"1116",pId:"111",name:"孙孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"false"},
        {id:"1117",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        {id:"1118",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        {id:"1119",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        {id:"1120",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        {id:"1121",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"}
    ];
    var code;
    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>"+str+"</li>");
    }

    $(document).ready(function(){
        $.ajax({
            url: "../agency/fullAgency",
            dataType: "json",
            success: function (result) {
                $.fn.zTree.init($("#treeDemo"), setting, result);
//
            }
        });
//        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        showCode();
    });
</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
</body>
</html>