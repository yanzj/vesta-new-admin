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
    <%-- Bootstrap Tree View --%>
    <link href="../static/css/bootstrap-treeview.css" rel="stylesheet">
    <script src="../static/js/bootstrap-treeview.js"></script>
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
                        <div class="col-sm-4 treeview" id="treeview-checkable">

                        </div>
                    </div>
                    <%--<div id="tree"></div>--%>

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
    var resultMap = {};

    //页面加载时执行
    $(function(){
        /*
        需求:节点树联动勾选(check)
        1.勾选父节点,同时勾选所有后代节点,包含子节点/孙子节点/重孙子节点...直到末级叶子节点.(如果存在后代节点)
        2.勾选子节点,如果所有子节点都被勾选,同时勾选父节点.(如果存在父节点)
        3.取消勾选父节点,同时取消勾选所有后代节点,包含子节点/孙子节点/重孙子节点...直到末级叶子节点.(如果存在子节点)
        4.取消勾选子节点,同时取消勾选父节点.(如果存在父节点)
        */
        /*
        编码:取消勾选子节点,同时取消勾选父节点,此时父节点也会触发取消勾选事件,通过设置变量判断勾选事件是由子节点触发还是父节点本身触发.
        childTrigger:bool类型 true表示由子节点触发.
        */
        //判断勾选是否由子节点勾选触发(最好作为全局变量)
        var childTrigger = false;
        $('#treeview-checkable').treeview({
            data: getTree(),
            showCheckbox: true,
            //勾选事件
            onNodeChecked: function(e, node) {
                //业务逻辑
                resultMap[node.id] = node.value;

                var that = $(this);
                //作为父节点:勾选时,同时勾选所有子节点
                var children = node.nodes;
                if (children != undefined) {
                    $.each(node.nodes, function(i, child) {
                        that.treeview('checkNode', [child.nodeId]
                        );
                    });
                }
                //作为子节点:勾选所有子节点时,同时勾选父节点
                var siblings = that.treeview('getSiblings', node);
                var boolCheckAll = true;
                if (siblings != undefined) {
                    $.each(siblings, function(i, child) {
                        if (!child.state.checked) {
                            boolCheckAll = false;
                        }
                    });
                }
                var parentId = node.parentId;
                if (boolCheckAll && parentId != undefined) {
                    that.treeview('checkNode', [parentId]);
                }
            },
            //取消勾选事件
            onNodeUnchecked: function(e, node) {
                var that = $(this);
                //作为父节点:取消勾选时,同时取消勾选所有子节点
                var children = node.nodes;
                if (children != undefined && !childTrigger) {
                    $.each(children, function(i, child) {
                        that.treeview('uncheckNode', [child.nodeId]);
                    });
                }
                //作为子节点:取消勾选时,同时取消勾选父节点
                var parentId = node.parentId;
                if (parentId != undefined) {
                    childTrigger = true;
                    that.treeview('uncheckNode', [parentId]);
                    childTrigger = false;
                }
                //业务逻辑
                delete resultMap[node.id];
            }
        });
    });

    var tree = [
        {
            text: "地块1",
            id:"dk001",
            value:"地块1",
            nodes: [
                {
                    text: "1栋楼",
                    id:"ld001",
                    value:"1栋楼",
                    nodes: [
                        {
                            text: "1单元",
                            id:"dy001",
                            value: "1单元",
                            state: {
                                checked: true
                            }
                        },
                        {
                            text: "2单元",
                            id:"dy002",
                            value: "2单元",
                        }
                    ]
                },
                {
                    text: "2栋楼",
                    id:"ld002",
                    value:"2栋楼",
                }
            ]
        },
        {
            text: "地块2"
        },
        {
            text: "Parent 3"
        },
        {
            text: "Parent 4"
        },
        {
            text: "Parent 5"
        }
    ];

    function getTree() {
        // Some logic to retrieve, or generate tree structure
//        return data;
        return tree;
    }


    //保存
    function toSave(){
        alert(resultMap);
//        var treeview = $('#treeview-checkable').treeview([emoji:0027]getChecked[emoji:0027]);
//         for (var p in treeview) {
//         nodeId.push(treeview[p].id);
//         }

//        var a = $('#treeview-checkable').treeview('getSelected', '#treeview-checkable');
//        alert(a);
        /*
        $.ajax({
            url:"../butlerStyle/saveOrUpdateButlerScope",
            type:"POST",
            async:"false",
            data:{
                butlerId:$("#butlerId").val()
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
        */
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>