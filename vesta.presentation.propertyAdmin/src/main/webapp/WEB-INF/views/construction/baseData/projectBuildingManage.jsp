<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/10/21
  Time: 16:54
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
    <style>
        label.error {
            margin-left: 1%;
            color: red;
        }
    </style>
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        new WOW().init();
        $(function () {
            $("#003100010000").addClass("active");
            $("#003100010000").parent().parent().addClass("in");
            $("#003100010000").parent().parent().parent().parent().addClass("active");
        })
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
    <script type="text/javascript">
        $().ready(function() {
            $("#addBuild").validate({
                rules: {
                    buildName: {
                        required: true,
                        minlength: 1,
                        maxlength: 10
                    }
                },
                messages: {
                    buildName: {
                        required: "请输入楼栋名",
                        minlength: "不能少于1位字符",
                        maxlength: "请勿超过10位字符"
                    }
                },
            })

        })
        $.validator.setDefaults({
            submitHandler: function() {
                $.ajax({
                    url: "../BaseData/addBuilding",            //目标网址
                    type: "post",
                    async: "false",
                    dataType: "json",
                    data: $("#addBuild").serialize(),
                    success: function (json) {
                        <!-- 获取返回代码 -->
                        var code = json.code;
                        if (code != 0) {
                            var errorMessage = json.msg;
                            alert(errorMessage);
                        } else {
                            <!-- 获取返回数据 -->
                            var data = json.data;
                            if (data == 'ok') {
                                window.location.href = '../BaseData/projectBuilding.html?projectId=${projectId}';
                                alert("保存成功");
                            } else {
                                alert(data);
                            }
                        }
                    }
                });
            }
        });

        function chuancan(id, name) {
            $("#buildId").val(id);
            $("#buildName").val(name);
        }
        function clearData(){
            $("#buildId").val('');
            $("#buildName").val('');
        }
    </script>
    <script type="text/javascript">
        function isExcel() {
            var size = $("#size").val();
            if (size > 0) {
                var href = "../BaseData/projectBuildingExcel?projectId=" +"${projectId}";
                window.location.href = href;
            } else {
                alert("没有可以导出的数据");
            }
        }

        function downloadModel() {
            if (confirm("是否下载模板？")) {
                var href = "../BaseData/downloadModel";
                window.location.href = href;
            } else {
                return;
            }
        }

        function importExcel() {
            //检验导入的文件是否为Excel文件
            var excelPath = document.getElementById("myfile").value;
            if (excelPath == null || excelPath == '') {
                alert("请选择要上传的Excel文件");
                return;
            } else {
                var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();
                if (fileExtend == '.xls' || fileExtend == '.xlsx') {
                } else {
                    alert("文件格式需为'.xls'格式或者'.xlsx格式'");
                    return;
                }
            }
            document.getElementById("frm").action = "../BaseData/importBuildsExcel";
            document.getElementById("frm").submit();
        }

        function projectBack() {
            window.location.href = "../BaseData/projectManage.html";
        }
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="003100010000" username="${authPropertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form id="frm" class="form-horizontal" name="frm" enctype="multipart/form-data" action="../BaseData/importBuildsExcel" method="post">
                    <%-- 新增楼栋 --%>
                    <button type="button" onclick="clearData()" class="btn btn-primary" for="propertyAdd" data-toggle="modal"
                            data-target="#myModal"><spring:message code="project.buildingSave"/>
                    </button>
                    <input type="hidden" id="projectId"  name="projectId" value="${projectId}"/>
                    <%-- 下载模板 --%>
                    <input type="button" class="btn btn-primary" value="<spring:message code="project.downloadModel"/>"
                           onclick="downloadModel()">
                    <%-- 导入 --%>
                    <button type="button" id="btn" name="btn" class="btn btn-primary" onclick="test()"><spring:message code="project.importExcel"/></button>
                    <%-- 导出 --%>
                    <a href="javascript:isExcel();" type="button" class="btn btn-primary"
                       for="propertyAdd"><spring:message code="project.exportExcel"/></a>
                    <%-- 返回 --%>
                    <input type="hidden" id="size" value="${size}"/>
                    <input style="float: right;" type="button" class="btn btn-primary" onclick="javascript:projectBack();"
                           value="<spring:message code="project.back"/>">
                    <%-- 导入excel 隐藏 --%>
                    <input type="file" id="myfile" name="myfile" style="visibility:hidden;" onchange="importExcel()">
                    <%
                        HttpSession sess = request.getSession();
                        String message = (String) sess.getAttribute("result");

                        if (message == "导入成功！") {
                    %>
                    <script type="text/javascript">
                        alert("<%=message %>");
                    </script>
                    <%
                        sess.setAttribute("result", "");
                    } else if (message == "导入失败！") {
                    %>
                    <script type="text/javascript">
                        alert("<%=message %>");
                    </script>
                    <%
                            sess.setAttribute("result", "");
                        }
                    %>
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
                <%-- 楼栋 --%>
                <th><spring:message code="project.build"/></th>
                <%-- 楼层数 --%>
                <th><spring:message code="project.floorNumber"/></th>
                <%-- 创建时间 --%>
                <th><spring:message code="project.createDate"/></th>
                <%-- 操作 --%>
                <th><spring:message code="project.operate"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${buildingList}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index+1}</b></td>
                    <td>${list.buildName}</td>
                    <td>${list.floorNum}</td>
                    <td>${list.createOn}</td>
                    <td>
                            <%-- 楼层信息 --%>
                        <a href="../BaseData/projectFloorManage.html?buildId=${list.buildId}&projectId=${projectId}"><spring:message
                                code="project.floorDetail"/></a>

                            <%--修改名称--%>
                        <a style="color: #cccc66;" href="#" data-toggle="modal" data-target="#myModal" onclick="chuancan('${list.buildId}', '${list.buildName}')"><spring:message
                                code="project.nameUpdate"/></a>
                            <%-- 删除 --%>
                        <a style="color: #cc6666;" onClick="javascript:if (confirm('确定删除吗？')) location.href='../BaseData/deleteBuild.html?buildId=${list.buildId}&projectId=${projectId}';else return;"
                           style="cursor: pointer"><spring:message code="common_delete"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../BaseData/projectBuilding.html?projectId=${projectId}&pageIndex={0}"/>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <form class="form-horizontal" id="addBuild" name="addBuild" action="../BaseData/addBuilding" method="post">
            <input type="hidden" name="projectId" value="${projectId}">
            <%-- 楼栋id --%>
            <input type="hidden" id="buildId" name="buildId">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span
                                aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel"><spring:message
                                code="project.buildingSave"/></h4>
                    </div>
                    <div class="modal-body">
                        <table>
                            <tr>
                                <td style="width: 100px;">
                                    <%-- 楼栋名称 --%>
                                    <label class="" for="buildName"><spring:message
                                            code="project.buildingName"/> ：</label>
                                </td>
                                <td>
                                    <input type="text" class="form-control" placeholder="" id="buildName"
                                           name="buildName">
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <%-- 取消 --%>
                        <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message
                                code="project.cancel"/></button>
                        <%-- 确定 --%>
                        <button type="submit" class="btn btn-primary">
                            <spring:message code="project.confirm"/></button>
                    </div>
                </div>
            </div>
        </form>
    </div>


</div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script>
    function test() {
        $("#myfile").click();
    }
</script>
</body>
</html>
