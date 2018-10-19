<%--
  Describe:普通用户管理
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/5/15
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
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
<%
    List<Viewmodel> menulist = (List<Viewmodel>) session.getAttribute("menulist");
    List<Viewmodel> secanViewlist = (List<Viewmodel>) session.getAttribute("secanViewlist");

%>

<!DOCTYPE HTML>
<html>
<head>
    <title><spring:message code="sys.tital"/></title>
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script type="application/x-javascript">
        addEventListener("load",
                function () {
                    setTimeout(hideURLbar, 0);
                }, false);
        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
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
    <script src="../static/property/js/propertyRepair.js"></script>
    <style>
        .tableStyle thead td, .tableStyle thead th {
            padding-left: 0;
            text-align: center;
        }
        .myTime{
            padding-left: 6px;
            padding-right: 6px;
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
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005200050000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form:form class="form-horizontal" action="../user/commonUserManagement.html"
                           modelAttribute="commonUserSearch" method="get">
                    <div class="form-group  col-xs-4">
                        <label for="nickName" class="col-sm-4 control-label">用户名</label>

                        <div class="col-sm-8">
                            <form:input type="text" class="form-control" placeholder="请输入昵称"
                                        value="${commonUserSearch.nickName}" path="nickName"/>
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="mobile" class="col-sm-4 control-label">手机号码</label>

                        <div class="col-sm-8">
                            <form:input type="text" class="form-control" placeholder="请输入手机号"
                                        value="${commonUserSearch.mobile}" path="mobile"/>
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="idCard" class="col-sm-4 control-label"><spring:message
                                code="activityManage.appointIdCard"/></label>

                        <div class="col-sm-8">
                            <form:input type="text" class="form-control" placeholder="请输入身份证号"
                                        value="${commonUserSearch.idCard}" path="idCard"/>
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="beginTime" class="col-sm-4 control-label"><spring:message
                                code="ownerManage.CreateTime"/></label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <form:input type="text" class="form-control" placeholder="请输入开始时间" path="beginTime"
                                        value="${commonUserSearch.beginTime}" readonly="true"/>
                            <span class="input-group-addon myTime"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon myTime"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="endTime" class="col-sm-4 control-label"><spring:message
                                code="HousePayBatch.to"/></label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <form:input type="text" class="form-control" placeholder="请输入结束时间" path="endTime"
                                        value="${commonUserSearch.endTime}" readonly="true"/>
                            <span class="input-group-addon myTime"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon myTime"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="form-group  col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="common_search"/></button>
                        <!--集合长度(取决Excel是否可以导出)-->
                        <input type="hidden" id="size" value="${isExcel}">
                        <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary"
                           style="color:#fff">导出Excel</a>
                    </div>

                    <div class="clearfix"></div>
                </form:form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <div class="">

        </div>
        <table width="100%" class="tableStyle table-striped">
            <thead>
            <tr>
                <td style="width:6%;text-align:center"><spring:message code="common_sort"/></td>
                <th>用户名</th>
                <th>微信昵称</th>
                <th>手机号码</th>
                <th class="col-sm-4"><spring:message code="activityManage.appointIdCard"/></th>
                <th><spring:message code="ownerManage.CreateTime"/></th>
                <%--<th><spring:message code="common_operate" /></th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${commonUsers}" var="user" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${user.nickName}</td>
                    <td>${user.wc_nickName}</td>
                    <td>${user.mobile}</td>
                    <td>${user.idCard}</td>
                    <td>${user.beginTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/user/commonUserManagement.html?pageIndex={0}&nickName=${commonUserSearch.nickName}&mobile=${commonUserSearch.mobile}&idCard=${commonUserSearch.idCard}&beginTime=${commonUserSearch.beginTime}&endTime=${commonUserSearch.endTime}"/>
    </div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
    var nickName = "${commonUserSearch.nickName}";
    var mobile = "${commonUserSearch.mobile}";
    var idCard = "${commonUserSearch.idCard}";
    var beginTime = "${commonUserSearch.beginTime}";
    var endTime = "${commonUserSearch.endTime}";
</script>
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
            var href = "../user/exportExcel?type=1&pageIndex={0}&nickName=${commonUserSearch.nickName}&mobile=${commonUserSearch.mobile}&idCard=${commonUserSearch.idCard}&beginTime=${commonUserSearch.beginTime}&endTime=${commonUserSearch.endTime}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>