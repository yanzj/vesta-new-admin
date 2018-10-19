<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <!-- Custom CSS -->
    <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>
    <style>
        label.error {
            margin-left: 1%;
            color: red;
        }
    </style>

    <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <%--<link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">--%>
    <!-- js-->
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        $(function () {
            $("#003100010000").addClass("active");
            $("#003100010000").parent().parent().addClass("in");
            $("#003100010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exhide-3.5.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="001400010000" username="${authPropertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <form action="#">
                    <div class="row pull-right" style="margin-top: -25px">
                        <a href="../BaseData/tendersManage.html?projectId=${projectId}" type="button"
                           class="btn btn-primary">返回</a>
                    </div>
                    <input type="hidden" name="tenderId" id="tenderId" value="${tenderId}">
                    <input type="hidden" name="projectId" value="${projectId}">

                    <div class="row">
                        <div class="col-md-2" style="text-align: right">
                            <label>模块分类:</label>
                        </div>
                        <div class="col-md-9">
                            <!-- Nav tabs -->
                            <ul class="nav nav-pills" role="tablist">
                                <li role="presentation" class="active"><a href="#one" role="tab" data-toggle="tab"
                                                                          class="btn btn-default">日常巡检</a></li>
                                <li role="presentation"><a href="#two" role="tab" data-toggle="tab"
                                                           class="btn btn-default">检查验收</a></li>
                                <%--<li role="presentation"><a href="#three" role="tab" data-toggle="tab"--%>
                                                           <%--class="btn btn-default">样板点评</a></li>--%>
                                <li role="presentation"><a href="#six" role="tab" data-toggle="tab"
                                                           class="btn btn-default">关键工序</a></li>
                                <%--<li role="presentation"><a href="#four" role="tab" data-toggle="tab"--%>
                                                           <%--class="btn btn-default">材料验收</a></li>--%>
                                <%--<li role="presentation"><a href="#five" role="tab" data-toggle="tab"--%>
                                                           <%--class="btn btn-default">旁站</a></li>--%>
                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-9">
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="one">
                                    <label>已选列表</label><br/>

                                    <div class="zTreeDemoBackground left" style="overflow: auto;width: 400px;">
                                        <ul id="treeDemo" class="ztree" style="height: 400px"></ul>
                                    </div>
                                    <a href="/BaseData/tenderCategory?tenderId=${tenderId}&projectId=${projectId}&domain=1"
                                       type="button" class="btn btn-primary">设置</a>
                                </div>
                                <div role="tabpanel" class="tab-pane" id="two">
                                    <label>已选列表</label><br/>

                                    <div class="zTreeDemoBackground left" style="overflow: auto;width: 400px;">
                                        <ul id="treeDemo2" class="ztree" style="height: 400px"></ul>
                                    </div>
                                    <a href="/BaseData/tenderCategory?tenderId=${tenderId}&projectId=${projectId}&domain=2"
                                       type="button" class="btn btn-primary">设置</a>
                                </div>
                                <div role="tabpanel" class="tab-pane" id="three">
                                    <label>已选列表</label><br/>

                                    <div class="zTreeDemoBackground left" style="overflow: auto;width: 400px;">
                                        <ul id="treeDemo3" class="ztree" style="height: 400px"></ul>
                                    </div>
                                    <a href="/BaseData/tenderCategory?tenderId=${tenderId}&projectId=${projectId}&domain=3"
                                       type="button" class="btn btn-primary">设置</a>
                                </div>
                                <%--<div role="tabpanel" class="tab-pane" id="four">--%>
                                    <%--<label>已选列表</label><br/>--%>

                                    <%--<div class="zTreeDemoBackground left" style="overflow: auto;width: 400px;">--%>
                                        <%--<ul id="treeDemo4" class="ztree" style="height: 400px"></ul>--%>
                                    <%--</div>--%>
                                    <%--<a href="/BaseData/tenderCategory?tenderId=${tenderId}&projectId=${projectId}&domain=4"--%>
                                       <%--type="button" class="btn btn-primary">设置</a>--%>
                                <%--</div>--%>
                                <%--<div role="tabpanel" class="tab-pane" id="five">--%>
                                    <%--<label>已选列表</label><br/>--%>

                                    <%--<div class="zTreeDemoBackground left" style="overflow: auto;width: 400px;">--%>
                                        <%--<ul id="treeDemo5" class="ztree" style="height: 400px"></ul>--%>
                                    <%--</div>--%>
                                    <%--<a href="/BaseData/tenderCategory?tenderId=${tenderId}&projectId=${projectId}&domain=5"--%>
                                       <%--type="button" class="btn btn-primary">设置</a>--%>
                                <%--</div>--%>
                                <div role="tabpanel" class="tab-pane" id="six">
                                    <label>已选列表</label><br/>

                                    <div class="zTreeDemoBackground left" style="overflow: auto;width: 400px;">
                                        <ul id="treeDemo6" class="ztree" style="height: 400px"></ul>
                                    </div>
                                    <a href="/BaseData/tenderCategory?tenderId=${tenderId}&projectId=${projectId}&domain=6"
                                       type="button" class="btn btn-primary">设置</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%--<div class="row">--%>
                        <%--<div class="col-md-2" style="text-align: right"><label>最后修改时间:</label></div>--%>
                        <%--<div class="col-md-10"></div>--%>
                    <%--</div>--%>
                </form>
            </div>
        </div>
    </div>

</div>
</div>
</div>
</div>
<!-- main content end-->


<%@ include file="../../main/foolter.jsp" %>
</div>

<script type="text/javascript">
    var setting = {
        async: {
            enable: true,
            url: "/BaseData/initTenderCategory?domain=1",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"tenderId": $('#tenderId').val()},
//      dataFilter: filter,
        }
    };
    var setting2 = {
        async: {
            enable: true,
            url: "/BaseData/initTenderCategory?domain=2",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"tenderId": $('#tenderId').val()},
//      dataFilter: filter,
        }
    };
    var setting3 = {
        async: {
            enable: true,
            url: "/BaseData/initTenderCategory?domain=3",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"tenderId": $('#tenderId').val()},
//      dataFilter: filter,
        }
    };
    var setting4 = {
        async: {
            enable: true,
            url: "/BaseData/initTenderCategory?domain=4",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"tenderId": $('#tenderId').val()},
//      dataFilter: filter,
        }
    };
    var setting5 = {
        async: {
            enable: true,
            url: "/BaseData/initTenderCategory?domain=5",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"tenderId": $('#tenderId').val()},
//      dataFilter: filter,
        }
    };
    var setting6 = {
        async: {
            enable: true,
            url: "/BaseData/initTenderCategory?domain=6",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"tenderId": $('#tenderId').val()},
//      dataFilter: filter,
        }
    };

    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting);
        $.fn.zTree.init($("#treeDemo2"), setting2);
        $.fn.zTree.init($("#treeDemo3"), setting3);
        $.fn.zTree.init($("#treeDemo4"), setting4);
        $.fn.zTree.init($("#treeDemo5"), setting5);
        $.fn.zTree.init($("#treeDemo6"), setting6);
    })
</script>
</body>
</html>
