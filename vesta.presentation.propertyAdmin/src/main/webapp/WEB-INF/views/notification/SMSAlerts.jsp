<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/3
  Time: 17:09
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
    <script src="../static/js/jquery.ajaxfileupload.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
</head>
<script type="text/javascript">
    function goSubmit(id) {
        document.getElementById("rolesetId").value = id;
        document.getElementById("search_form").submit();
    }
</script>
<script type="application/javascript">
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
        document.getElementById("form1").action = "../smsMessage/uploadExcel";
        document.getElementById("form1").submit();

    }

</script>
<script type="text/javascript">
    function changeAdd() {
        $("#addPeople").show();
    }

    function cancel() {
        $("#addPeople").hide();
    }

    function makeSure() {
        var modelList = $("#modelList").val();
        if (null == modelList || "请选择" == modelList) {
            alert("请选择消息提醒事项！");
            return false;
        }
        var cityId = $("#cityIds").val();
        if (null == cityId || "" == cityId) {
            alert("请选择城市范围！");
            return false;
        }
        var projectId = $("#projectIds").val();
        if (null == projectId || "" == projectId) {
            alert("请选择项目范围！");
            return false;
        }
        var name = $("#personName").val();
        if (null == name || "" == name) {
            alert("请输入姓名！");
            return false;
        }
        var phone = $("#personPhone").val();
        if (null == phone || "" == phone) {
            alert("请输入手机号！");
            return false;
        }
        $.ajax({
            type: "post",
            url: "../smsMessage/smsPeopleAdd?cityIds=" + cityId + "&projectIds=" + projectId + "&modelList=" + modelList + "&name=" + name + "&phone=" + phone,
            async: false,
            success: function (data) {
                data = data.toString();
                alert("添加成功！");
                location.reload();

            }
        });
    }
</script>
<style>
    .contrloButtons {
        float: right;
        text-align: center;
    }

    .form-horizontal .form-group {
        float: left;
    }

    .control_btn {
        padding: 0 0 1rem 0;
        background-color: #fbfbfb;
    }

    .control_btn button, .control_btn a {
        margin-right: 1rem;
    }

    .control_btn a.btn_sty {
        width: 9.5rem;
    }
</style>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="000100030000" username="${propertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form id="form1" name="form1" class="form-horizontal" action="../smsMessage/smsList" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" id="menuId" value="000100030000"/>
                    <%--数据查看范围--%>
                    <div class="form-group  col-xs-12">数据查看范围</div>
                    <div class="form-group  col-xs-4">
                        <label for="cityNum" class="col-sm-6 control-label">城市</label>

                        <div class="col-sm-6">
                            <select id="cityNum" name="cityNum" class="form-control" onchange="queryProjectNameById()">
                                <option value="-1">--请选择城市--</option>
                                <c:forEach items="${city}" var="item">
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq smsAlerts.cityNum}">selected</c:if>
                                            <c:if test="${item.cityId eq '0'}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                            <%--<select class="form-control" placeholder="" id="cityNum" name="cityNum" onchange="queryProjectNameById()">
                              <c:forEach items="${city}" var="item">
                                <option value="${item.sid }"
                                        <c:if test="${item.sid eq '0'}">selected</c:if>>${item.name }
                                </option>
                              </c:forEach>
                            </select>--%>
                        </div>
                    </div>

                    <%--项目--%>
                    <div class="form-group  col-xs-4">
                        <label for="projectNum" class="col-sm-4 control-label"><spring:message
                                code="HousePayBatch.projectName"/></label>

                        <div class="col-sm-7">
                            <select id="projectNum" name="projectNum" class="form-control">
                                <c:forEach items="${projectList}" var="project">
                                    <option value="${project[0]}"
                                            <c:if test="${project[0] eq smsAlerts.projectNum}">selected</c:if>>${project[1]}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--提醒模块--%>
                    <div class="form-group  col-xs-4">
                        <label for="model" class="col-sm-4 control-label">提醒模块</label>

                        <div class="col-sm-7">
                            <%--<input type="text" class="form-control" placeholder="" id="model"--%>
                            <%--name="model" value="${smsAlerts.model}">--%>
                            <select id="model" name="model" class="form-control">
                                <c:forEach items="${alertsModel}" var="model">
                                    <option value="${model.value}"
                                            <c:if test="${model.value eq smsAlerts.model}">selected</c:if>>${model.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--姓名--%>
                    <div class="form-group  col-xs-4">
                        <label for="name" class="col-sm-6 control-label">姓名</label>

                        <div class="col-sm-6">
                            <input type="text" class="form-control" placeholder="" id="name"
                                   name="name" value="${smsAlerts.name}">
                        </div>
                    </div>
                    <%--手机号--%>
                    <div class="form-group  col-xs-4">
                        <label for="phone" class="col-sm-4 control-label">手机号码</label>

                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="" id="phone"
                                   name="phone" value="${smsAlerts.phone}">
                        </div>
                    </div>

                    <%--导入excel--%>
                    <div class="form-group contrloButtons col-xs-12">
                        <button type="submit" class="btn btn-primary" for=""><spring:message
                                code="common_search"/></button>
                        <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary"
                           style="color:#fff">导出Excel</a>
                        <input type="hidden" id="size" value="${isExcel}"/>
                        <a href="../smsMessage/downLoadExcel" value="" class="btn btn-primary"
                           style="color:#fff">下载模板</a>
                        <button type="button" id="btn" name="btn" class="btn btn-primary" onclick="myfile.click()">导入
                        </button>
                        <a href="../smsMessage/gotoSMSPeopleAdd" value="" class="btn btn-primary"
                           style="color:#fff">新建</a>
                        <a href="../smsMessage/gotoSMSAlertsAdd?menuId=000100030000" value=""
                           class="btn btn-primary btn_sty"
                           style="color:#fff">编辑短信内容</a>

                        <div class="col-sm-1">
                            <input type="file" class="form-control" placeholder="" id="myfile" style="visibility:hidden"
                                   name="myfile" onchange="importExcel()">
                        </div>
                        <%--<div class="col-sm-1">
                            <button type="button" id="btn" name="btn" class="btn btn-primary" onclick="myfile.click()">导入
                            </button>
                            <label for="myfile" id="flag" style="font-size: large;color: red;width: 150px;">${flag}</label>
                        </div>--%>
                    </div>
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
                    <div class="clearfix"></div>

                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <div class="">

        </div>
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <th><input type="button" style="color: black;" id="add" value="添加人员" onclick="changeAdd()"></th>
                <th>消息提醒事项</th>
                <th>短信提醒范围</th>
                <th>短信文本</th>
                <th>姓名</th>
                <th>手机号码</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr id="addPeople" style="display:none;">
                <td></td>
                <td>
                    <select id="modelList" name="modelList" class="form-control" onchange="queryProjectNameById2()">
                        <c:forEach items="${alertsModel}" var="item">
                            <option value="${item.value }"
                                    <c:if test="${item.key eq '0'}">selected</c:if>>${item.value }
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select id="cityIds" name="cityIds" class="form-control" onchange="queryProjectNameById2()">
                        <option value="-1">--请选择城市--</option>
                        <c:forEach items="${city}" var="item">
                            <option value="${item.cityId }"
                                    <c:if test="${item.cityId eq '0'}">selected</c:if>>${item.cityName }</option>
                        </c:forEach>
                    </select>
                    <select id="projectIds" name="projectIds" class="form-control">
                        <option value="0">全部项目</option>
                    </select>
                </td>
                <td></td>
                <td><input type="text" placeholder="" id="personName" name="name"></td>
                <td><input type="text" placeholder="" id="personPhone" name="phone"></td>
                <td>
                    <input type="button" class="btn btn-primary" id="cancel" value="取消" onclick="cancel()">
                    <input type="button" class="btn btn-primary" id="confirm" value="确定" onclick="makeSure()">
                </td>
            </tr>
            <c:forEach items="${smsAlertsDTO}" var="sms" varStatus="vs">
                <tr>
                    <td align="center"><b>${vs.count}</b></td>
                    <td>${sms.model}</td>
                    <td>${sms.scope}</td>
                    <td>${sms.content}</td>
                    <td>${sms.name}</td>
                    <td>${sms.phone}</td>
                    <td>
                        <a href="../smsMessage/gotoSMSPeopleUpdate?id=${sms.id}">编辑</a>
                        <a href="javascript:js_Delete('${sms.id}')">删除</a>
                            <%--<a href="../smsMessage/smsPeopleDelete?id=${sms.id}">删除</a>--%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/smsMessage/smsList?pageIndex={0}&name=${smsAlerts.name}&phone=${smsAlerts.phone}&model=${smsAlerts.model}&cityNum=${smsAlerts.cityNum}&projectNum=${smsAlerts.projectNum}"/>
        <%--    <m:pager pageIndex="${webPage.pageIndex}"
                     pageSize="${webPage.pageSize}"
                     recordCount="${webPage.recordCount}"
                     submitUrl="${pageContext.request.contextPath }/community/showActivityApply.html?pageIndex={0}&cu_projectId=${activityApplyAdminDto.cu_projectId}&userType=${activityApplyAdminDto.userType}&title=${activityApplyAdminDto.title}&userName=${activityApplyAdminDto.userName}&userMobile=${activityApplyAdminDto.userMobile}"/>--%>
    </div>


</div>

<%--时间搜索插件--%>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
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
    function queryProjectNameById2() {
        var projectId = $("#cityIds").val();
        if (projectId == '-1') {
            $("#projectIds").empty();
            return;
        }
        $("#planName").empty();
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId + "/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectIds").empty();
            <%--$("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');--%>
            for (var k in arr) {
                $("#projectIds").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
//                alert(arr[k][0]);
            }

        });
    }

    function queryProjectNameById() {
        var projectId = $("#cityNum").val();
        if (projectId == '-1') {
            $("#projectNum").empty();
            return;
        }
        $("#planName").empty();
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId + "/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectNum").empty();
            <%--$("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');--%>
            for (var k in arr) {
                $("#projectNum").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
//                alert(arr[k][0]);
            }

        });
    }

    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
            var href = "../smsMessage/exportExcel?name=${smsAlerts.name}&phone=${smsAlerts.phone}&model=${smsAlerts.model}&cityNum=${smsAlerts.cityNum}&projectNum=${smsAlerts.projectNum}&pageIndex=${webPage.pageIndex}&pageSize=${webPage.pageSize}&recordCount=${webPage.recordCount}&submitUrl=${pageContext.request.contextPath }/smsMessage/smsList?pageIndex={0}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }

    function js_Delete(smsId) {
        if (confirm("是否删除？")) {
            window.location.href = "../smsMessage/smsPeopleDelete?id=" + smsId;
        }
    }
</script>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</body>
</html>