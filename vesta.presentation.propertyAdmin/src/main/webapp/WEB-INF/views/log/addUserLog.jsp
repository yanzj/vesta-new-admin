<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/7/19
  Time: 18:00
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
<style>
    .form-horizontal .form-group{
        float: left;
    }
    .form_b{
        padding: 1.5em 2rem 0 2rem;
        height: 12.5rem;
    }
    .search_button{
       text-align: center;
    }
    .control_btn{
        padding: 0 0 1rem 0;
        background-color: #fbfbfb;
    }
    .control_btn button,.control_btn a{
        margin-right: 1rem;
    }
</style>
<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005800010000" username="${propertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form class="form-horizontal" action="../log/addUserLog.html">
                    <div class="form-group  col-xs-4">
                        <label for="projectId" class="col-sm-4 control-label">项目</label>

                        <div class="col-sm-8">
                            <select class="form-control" id="projectId" name="projectId" class="">
                                <c:forEach items="${projectList}" var="list">
                                    <%--<option value="${list.key}" <c:if test="${list.key eq sysLog.projectId}">selected="selected"</c:if>>${list.value}</option>--%>
                                    <option value="${list.value}"
                                            <c:if test="${list.value eq systemLogDTO.projectId}">selected</c:if>>${list.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="userType" class="col-sm-4 control-label">用户类型</label>

                        <div class="col-sm-8">
                            <select class="form-control" name="userType" id="userType">
                                <option value="0" <c:if test="${sysLog.userType eq 0}">selected="selected"</c:if>>全部
                                </option>
                                <option value="3" <c:if test="${sysLog.userType eq 3}">selected="selected"</c:if>>业主
                                </option>
                                <option value="4" <c:if test="${sysLog.userType eq 4}">selected="selected"</c:if>>同住人
                                </option>
                                <option value="2" <c:if test="${sysLog.userType eq 2}">selected="selected"</c:if>>普通用户
                                </option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="sourceFrom" class="col-sm-4 control-label">来源</label>

                        <div class="col-sm-8">
                            <select class="form-control" name="sourceFrom" id="sourceFrom">
                                <option value="0" <c:if test="${sysLog.sourceFrom eq 0}">selected="selected"</c:if>>全部
                                </option>
                                <c:forEach items="${clientConfigList}" var="clientConfig">
                                    <option value="${clientConfig.id}" <c:if test="${sysLog.sourceFrom eq clientConfig.id}">selected="selected"</c:if>>
                                        ${clientConfig.clientName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="userMobile" class="col-sm-4 control-label">手机号码</label>

                        <div class="col-sm-8">
                            <input type="text" id="userMobile" name="userMobile" value="${sysLog.userMobile}"
                                   class="form-control">
                        </div>
                    </div>
                    <!-- 发布日期开始日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="staDate" class="col-sm-4 control-label">开始时间</label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="staDate"
                                   value="${sysLog.beginTime}" name="beginTime" readonly>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;" ><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <!-- 发布日期结束日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="endDate" class="col-sm-4 control-label">结束时间</label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="endDate"
                                   value="${sysLog.endTime}" name="endTime" readonly>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="form-group  col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="common_search"/></button>
                        <input type="hidden" id="size" value="${isExcel}"/>
                        <a href="javascript:void(0);" onclick="isExcel()" type="button" class="btn btn-primary"
                           for="propertyAdd">导出Excel</a>
                        <input type="hidden" id="cleanRepeatData"/>
                    </div>
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
                <td>序号</td>
                <th>增加时间</th>
                <th>用户名</th>
                <th>用户类型</th>
                <th>手机号码</th>
                <th>身份证</th>
                <th>来源</th>
                <th>系统版本</th>
                <th>项目</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${logList}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index+1}</b></td>
                    <td>${list.logTime}</td>
                    <td>${list.userName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${list.userType eq 2}">普通用户</c:when>
                            <c:when test="${list.userType eq 3}">业主</c:when>
                            <c:when test="${list.userType eq 4}">关联住户</c:when>
                            <c:otherwise>虚拟业主</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${list.userMobile}</td>
                    <td>${list.idCard}</td>
                    <td>
                        <%--
                        <c:choose>
                            <c:when test="${list.sourceFrom eq 1}">APP</c:when>
                            <c:when test="${list.sourceFrom eq 2}">微信</c:when>
                        </c:choose>
                        --%>
                        <c:forEach items="${clientConfigList}" var="clientConfig">
                            <c:if test="${list.sourceFrom eq clientConfig.id}">${clientConfig.clientName}</c:if>
                        </c:forEach>
                    </td>
                    <td>${list.sysVersion}</td>
                    <td>${list.projectName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../log/addUserLog.html?pageIndex={0}&projectId=${systemLogDTO.projectId}&userType=${systemLogDTO.userType}&sourceFrom=${systemLogDTO.sourceFrom}&userMobile=${systemLogDTO.userMobile}&beginTime=${systemLogDTO.beginTime}&endTime=${systemLogDTO.endTime}"/>
    </div>


</div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
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

    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
            var href = "../log/addUserLogExportExcel?projectId=${systemLogDTO.projectId}&userType=${systemLogDTO.userType}&sourceFrom=${systemLogDTO.sourceFrom}&userMobile=${systemLogDTO.userMobile}&beginTime=${systemLogDTO.beginTime}&endTime=${systemLogDTO.endTime}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }

</script>

<script type="text/javascript">
    $("#cleanRepeatData").click(function(){
        //执行清除重复数据
        $.get("/log/cleanSystemLogRepeatData");
    });
</script>
</body>
</html>
