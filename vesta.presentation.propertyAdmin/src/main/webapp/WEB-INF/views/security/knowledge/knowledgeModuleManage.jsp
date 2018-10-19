<%--
  安全知识库管理页面
  Created by IntelliJ IDEA.
  User: yuanyn
  Date: 2017/6/7
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <script>
        $(function () {
            $("#006200010000").addClass("active");
            $("#006200010000").parent().parent().addClass("in");
            $("#006200010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <style>
        label.error {
            margin-left: 1%;
            color: red;
        }
        .form-body{
            height: 66px;
        }
    </style>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006200010000" username="${authPropertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../knowledge/initKnowledgeList.html">
                    <div class="form-group  col-lg-4">
                        <label for="fileName" class="col-sm-5 control-label">模块搜索：</label>
                        <div class="col-sm-7">
                            <input type="text" id="fileName" name="fileName" value="${knowledge.fileName}" class="form-control">
                        </div>
                    </div>
                    <c:if test="${function.sth40020035 eq 'Y'}">
                        <button type="submit" class="btn btn-primary">搜索</button>
                    </c:if>
                    <c:if test="${function.sth40020036 eq 'Y'}">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">添加模块
                        </button>
                    </c:if>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="mall.serialNumber"/></td>
                <th>模块名</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${knowledgeList}" var="list" varStatus="row">
                <tr>
                    <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
                    <td>${list.fileName}</td>
                    <td>${list.createDate}</td>
                    <td>
                        <c:if test="${function.sth40020038 eq 'Y'}">
                            <a href="../knowledge/cataKnowledgeList.html?currentId=${list.currentId}">目录管理</a>
                        </c:if>
                        <c:if test="${function.sth40020037 eq 'Y'}">
                            <a style="cursor: pointer" onclick="delModule()" href="../knowledge/deleteKnowledgeModule.html?currentId=${list.currentId}" class="a3"><span class="span1">删除</span></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../knowledge/initKnowledgeList.html?pageIndex={0}&fileName=${knowledge.fileName}"/>
    </div>
</div>
</div>
</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form class="modal-content" name="form" id="form" action="../knowledge/addKnowledgeModule.html">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel2">添加模块</h4>
            </div>
            <div class="modal-body">
                <div class="form-group  col-lg-12">
                    <label class="col-sm-2 control-label"><h5>模块名称：</h5></label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="fileName">
                    </div>
                </div>
                <div class="modal-footer" style="border: none">
                    <button type="submit" class="btn btn-primary">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>

</body>
<script>
    function delModule() {
        if (!confirm("你要删除该模块吗？删除之后该模块下所有数据将全部删除")) {
            window.event.returnValue = false;
        }
    }
    $().ready(function () {
        $("#form").validate({
            rules: {
                groupName: {
                    required: true,
                    minlength: 1,
                    maxlength: 25
                },
            },
            messages: {
                groupName: {
                    required: "请输入文档名！",
                    minlength: "不能少于1个字符！",
                    maxlength: "请勿超过25个字！"
                },
            },
        })
    })
</script>
</html>
