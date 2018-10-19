<%--
  用户访问日志管理页
  Created by IntelliJ IDEA.
  User: jia
  Date: 2016/11/17
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    .form-horizontal .form-group {
        float: left;
    }
</style>
<style type="text/css">
    #test {
        display: block; /*内联对象需加*/
        width: 450px;
        word-break: keep-all; /* 不换行 */
        white-space: nowrap; /* 不换行 */
        overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
        text-overflow: ellipsis;
    }
</style>
<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005100010000" username="${propertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <%--用户名--%>
                <form class="form-horizontal" action="../qualityLog/qualityAccessLog.html">
                    <div class="form-group  col-lg-4">
                        <label for="userName" class="col-sm-4 control-label">用户名：</label>

                        <div class="col-sm-8">
                            <input type="text" id="userName" name="userName" value="${sysLog.userName}"
                                   class="form-control">
                        </div>
                    </div>
                    <%--手机号--%>
                    <div class="form-group  col-lg-4">
                        <label for="userMobile" class="col-sm-4 control-label">手机号：</label>

                        <div class="col-sm-8">
                            <input type="text" id="userMobile" name="userMobile" value="${sysLog.userMobile}"
                                   class="form-control">
                        </div>
                    </div>
                    <%--来源--%>
                    <div class="form-group  col-lg-4">
                        <label for="sourceFrom" class="col-sm-4 control-label">来源：</label>

                        <div class="col-sm-8">
                            <select class="form-control" name="sourceFrom" id="sourceFrom">
                                <option value="" <c:if test="${sysLog.sourceFrom eq ''}">selected="selected"</c:if>>
                                    全部
                                </option>
                                <option value="APP"
                                        <c:if test="${sysLog.sourceFrom eq 'APP'}">selected="selected"</c:if>>APP
                                </option>
                                <option value="Web"
                                        <c:if test="${sysLog.sourceFrom eq 'Web'}">selected="selected"</c:if>>Web
                                </option>
                            </select>
                        </div>
                    </div>

                    <!-- 访问开始日期 -->
                    <div class="form-group  col-lg-4">
                        <label for="staDate" class="col-sm-4 control-label">开始时间： </label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="staDate"
                                   value="${sysLog.beginTime}" name="beginTime" readonly>
                                <span class="input-group-addon" style="padding-left: 6px;padding-right: 6px;"><span
                                        class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon" style="padding-left: 6px;padding-right: 6px;"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <!-- 访问结束日期 -->
                    <div class="form-group  col-lg-4">
                        <label for="endDate" class="col-sm-4 control-label">结束时间：</label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="endDate"
                                   value="${sysLog.endTime}" name="endTime" readonly>
                                <span class="input-group-addon" style="padding-left: 6px;padding-right: 6px;"><span
                                        class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon" style="padding-left: 6px;padding-right: 6px;"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="form-group  col-lg-4 pull-right">
                        <div class="col-sm-4"></div>
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="common_search"/></button>

                        <a href="javascript:void(0);" onclick="isExcel()" type="button" class="btn btn-primary"
                           for="propertyAdd">导出Excel</a>

                    </div>
                    <div class="clearfix"></div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td>序号</td>
                <th>访问时间</th>
                <th>用户名</th>
                <th>用户昵称</th>
                <th>手机号</th>
                <th>访问来源</th>
                <th>访问内容</th>
                <th>IP地址</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${logList}" var="log" varStatus="row">
                <tr>
                    <td><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>
                    <td>
                        <fmt:formatDate type="date" value="${log.accessDate}" dateStyle="default"
                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${log.userName}</td>
                    <td>${log.staffName}</td>
                    <td>${log.userMobile}</td>
                    <td>${log.sourceFrom}</td>
                    <td>${log.content}</td>
                    <td>${log.ipAddress}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../qualityLog/qualityAccessLog.html?pageIndex={0}&userName=${sysLog.userName}&userMobile=${sysLog.userMobile}&sourceFrom=${sysLog.sourceFrom}&beginTime=${sysLog.beginTime}&endTime=${sysLog.endTime}"/>
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
        var size = ${fn:length(logList)};
        if (size > 0) {
            var href = "../qualityLog/exportAccessExcels?userName=${sysLog.userName}&userMobile=${sysLog.userMobile}&sourceFrom=${sysLog.sourceFrom}&beginTime=${sysLog.beginTime}&endTime=&${sysLog.endTime}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }
</script>
</body>
</html>
