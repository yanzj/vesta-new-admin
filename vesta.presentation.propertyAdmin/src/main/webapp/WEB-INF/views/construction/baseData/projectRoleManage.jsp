<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/10/31
  Time: 18:24
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
            console.log("sqq")
            $("#003100030000").addClass("active");
            $("#003100030000").parent().parent().addClass("in");
            $("#003100030000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <style>
        label.error {
            margin-left: 1%;
            color: red;
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
                 crunMenu="003100030000" username="${authPropertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <input type="hidden" id="reOpt" value="${project.optId}">
                <input type="hidden" id="reCity" value="${project.cityId}">
                <form class="form-horizontal" action="/BaseData/projectRoleManage.html">
                    <div class="form-group  col-lg-4">
                        <label for="projectName" class="col-sm-5 control-label">经营单位：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="optsId" name="optId" onchange="changeCity1(this.value)">
                                <option value="">请选择经营单位</option>
                                <c:forEach items="${operationList}" var="list">
                                    <option value="${list.key}"
                                            <c:if test="${list.key eq project.optId}">selected="selected"</c:if>>${list.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="projectName" class="col-sm-5 control-label">所属城市：</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="citysId2" name="cityId">
                                <option value="">请选择城市</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="projectName" class="col-sm-5 control-label">项目名称：</label>
                        <div class="col-sm-7">
                            <input type="text" id="projectName" name="projectName" value="${project.projectName}"
                                   class="form-control">
                        </div>
                    </div>
                    <%--<input type="hidden" name="projectId" value="${project.projectId}">--%>
                    <button type="submit" class="btn btn-primary">搜索</button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">添加项目
                    </button>
                    <a href="/BaseData/projectRoleEdit.html" class="btn btn-primary">系统管理员</a>
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
                <th>项目名称</th>
                <th>所属经营单位</th>
                <th>所属城市</th>
                <th>最后修改时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${projectList}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index+1}</b></td>
                    <td>${list.projectName}</td>
                    <td>${list.optName}</td>
                    <td>${list.cityName}</td>
                    <td>${list.modifyOn}</td>
                    <td>
                        <a href="/BaseData/editProjectRole.html?projectId=${list.projectId}&flag=1">编辑</a>
                        <a href="/BaseData/projectRoleDetail.html?projectId=${list.projectId}">详情</a>
                        <a style="cursor: pointer" onclick="delcfm()"
                           href="../BaseData/delProjectProject.html?projectId=${list.projectId}" class="a3"><span
                                class="span1">关闭项目</span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../BaseData/projectRoleManage.html?pageIndex={0}"/>
    </div>


</div>
</div>
</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form class="modal-content" name="form" id="form" action="../BaseData/addProjectProject.html">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel2">添加项目</h4>
            </div>
            <div class="modal-body">
                <div class="form-group  col-lg-12" style="margin-top: 10px">
                    <label class="col-sm-2 control-label">经营单位</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="optId" name="optId" onchange="changeCity(this.value)">
                            <option value="0">请选择经营单位</option>
                            <c:forEach items="${operationList}" var="list">
                                <option value="${list.key}">${list.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-4">
                        <button type="button" class="btn btn-primary"
                                onclick="javascript:window.location.href='../BaseData/operationManage.html'">管理经营单位
                        </button>
                    </div>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-2 control-label">所属城市</label>
                    <div class="col-sm-6">
                        <select class="form-control" id="cityId2" name="cityId">
                            <option value="">请选择城市</option>
                        </select>
                    </div>
                    <div class="col-sm-4">
                        <button type="button" class="btn btn-primary"
                                onclick="addCity()">管理所属城市
                        </button>
                    </div>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-2 control-label">项目名称</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="projectName">
                    </div>
                </div>
                <div class="modal-footer">
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
    function delcfm() {
        if (!confirm("你要关闭该项目吗？") || !confirm("关闭之后该项目所关联的所有数据将不可恢复！确定吗？") || !confirm("请确认最后一次！")) {
            window.event.returnValue = false;
        }
    }
    function changeCity(optId) {
        $.ajax({
            url: "../BaseData/getThisCity",
            type: 'get',
            async: false,
            dataType: "json",
            data: {
                "optId": optId
            },
            success: function (json) {
                var items = json.data;
                var selectModel = $("#cityId2");
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
    function changeCity1(optId) {
        $.ajax({
            url: "../BaseData/getThisCity",
            type: 'get',
            async: false,
            dataType: "json",
            data: {
                "optId": optId
            },
            success: function (json) {
                var items = json.data;
                var selectModel = $("#citysId2");
                selectModel.empty();
                if (items != null) {
                    var reCity = $('#reCity').val();
                    for (var i in items) {
                        var item = items[i];
                        if (i == reCity) {
                            selectModel.append("<option value = '" + i + "' selected='selected'>" + item + "</option>");
                        } else
                            selectModel.append("<option value = '" + i + "'>" + item + "</option>");
                    }
                }
                else {
                    selectModel.empty();
                }
            }
        });
    }
    $().ready(function () {
        var optId = $('#reOpt').val();
        if (optId != '') {
            changeCity1(optId);
        }
        $("#form").validate({
            rules: {
                projectName: {
                    required: true,
                    minlength: 1,
                    maxlength: 25
                },
                cityId: {
                    required: true,
                },
            },
            messages: {
                projectName: {
                    required: "请输入项目名！",
                    minlength: "不能少于1个字符！",
                    maxlength: "请勿超过25个字！"
                },
                cityId: {
                    required: "请选择所属城市！",
                }
            },
        })
    })

    function addCity() {
        var optId = $('#optId').val();
        window.location.href = '../BaseData/projectCityManage.html?optId=' + optId;
    }
</script>
</html>
