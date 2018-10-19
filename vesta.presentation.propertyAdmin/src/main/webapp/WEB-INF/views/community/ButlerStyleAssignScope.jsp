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
            $("#005500050000").addClass("active");
            $("#005500050000").parent().parent().addClass("in");
            $("#005500050000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="005500050000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="005500050000"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../butlerStyle/saveOrUpdateButlerScope" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="butlerId" name="butlerId" value="${butlerStyle.butlerId}">
                    <%-- 区域 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label">区域</label>
                        <div class="col-sm-4">
                            <select id="scopeId" class="form-control" readonly>
                                <option value="${butlerStyle.serviceCityId}">${butlerStyle.serviceCityName}</option>
                            </select>
                        </div>
                    </div>
                    <%-- 项目 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label">项目</label>
                        <div class="col-sm-4">
                            <select id="projectCode" class="form-control" readonly>
                                <option value="${butlerStyle.serviceProjectCode}">${butlerStyle.serviceProjectName}</option>
                            </select>
                        </div>
                    </div>
                    <%-- 责任范围 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label">责任范围</label>
                        <div class="col-sm-4">
                            <ul id="treeDemo" class="ztree"></ul>
                        </div>
                    </div>

                    <div class="text-center form-group  col-lg-6">
                        <button type="button" class="btn btn-primary" onclick="toSave()"><spring:message
                                code="common_save"/></button>
                        <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);">
                            <spring:message code="common_cancel"/></button>
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
    var scopeJson = ${scopeJson};
    // zTree 的参数配置,深入使用请参考 API 文档(setting 配置详解)
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    // zTree 的数据属性,深入使用请参考 API 文档(zTreeNode 节点数据详解)
    var zNodes =[
        {id:"1",pId:"0",name:"地块_西一",value:"GZ-NSJMW-X1",isParent:"true"},
        {id:"11",pId:"1",name:"楼栋_T1",value:"GZ-NSJMW-X1-T1",isParent:"true"},
        {id:"1A",pId:"11",name:"单元_无",value:"GZ-NSJMW-X1-T1-#",isParent:"true"},
        {id:"1111",pId:"1A",name:"南沙金茂湾T1号楼303",value:"024b1dea-be7e-4256-a60d-3dbecda0c3c1",isParent:"false"},
        {id:"1112",pId:"1A",name:"南沙金茂湾T1号楼308",value:"0510bba1-ce77-42d4-a489-cdf1b5df2a0b",isParent:"false"},
        {id:"1113",pId:"1A",name:"南沙金茂湾T1号楼504",value:"053b7d4e-bb23-4407-9070-6c5a8c04dba8",isParent:"false",checked:true},
        {id:"1114",pId:"1A",name:"南沙金茂湾T1号楼116",value:"0667422c-2b1c-4fd4-ad92-50096d772dd2",isParent:"false",checked:true},
        {id:"1115",pId:"1A",name:"南沙金茂湾T1号楼720",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        {id:"1116",pId:"11",name:"南沙金茂湾T1号楼916",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"false"}
    ];

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
//        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        setCheck();
        $("#py").bind("change", setCheck);
        $("#sy").bind("change", setCheck);
        $("#pn").bind("change", setCheck);
        $("#sn").bind("change", setCheck);
    });

    //保存
    function toSave(){
        var resultJson = '[';
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        for (var i = 0,length = nodes.length; i<length; i++){
            var resultObj = '{id:'+nodes[i].id+',name:"'+nodes[i].name+'",value:"'+nodes[i].value+'"},';
            resultJson += resultObj;
        }
        $.ajax({
            url:"../butlerStyle/saveOrUpdateButlerScope",
            type:"POST",
            async:"false",
            data:{
                butlerId:$("#butlerId").val(),
                resultJson:resultJson+"]"
            },
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(data){
                if(data.error == "0"){
                    alert("保存成功！");
                }else if(data.error == "-1"){
                    alert("保存失败，请联系管理员！");
                }
                window.location.href = "../butlerStyle/getButlerStyleList.html?menuId=005500050000";
            }
        });
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>