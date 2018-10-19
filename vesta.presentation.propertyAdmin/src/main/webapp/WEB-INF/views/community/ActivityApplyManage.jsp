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
<style>
    .form-horizontal .form-group {
        float: left;
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
                 crunMenu="005400040000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../community/showActivityApply.html">
                    <input type="hidden" id="menuId" name="menuId" value="005400040000"/>
                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-3 control-label">区域</label>
                        <div class="col-sm-9">
                            <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq activityApplyAdminDto.scopeId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group  col-xs-4">
                        <label for="projectCode" class="col-sm-3 control-label">项目</label>
                        <div class="col-sm-9">
                            <select id="projectCode" name="projectCode" class="form-control">
                                <c:forEach items="${projectList}" var="item" >
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq activityApplyAdminDto.projectCode}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--身份--%>
                    <div class="form-group  col-xs-4">
                        <label for="userType" class="col-sm-3 control-label"><spring:message code="activityManage.applyUserType"/></label>
                        <div class="col-sm-9">
                            <select class="form-control" id="userType" name="userType" >
                                <option value="" > --请选择身份--</option>
                                <option value="2" <c:if test="${activityApplyAdminDto.userType eq '2'}">selected</c:if>> 普通用户</option>
                                <option value="3" <c:if test="${activityApplyAdminDto.userType eq '3'}">selected</c:if>> 业主</option>
                            </select>
                        </div>
                    </div>
                    <%--活动主题--%>
                    <div class="form-group  col-xs-4">
                        <label for="title" class="col-sm-3 control-label"><spring:message
                                code="activityManage.activityTitle"/></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="" id="title"
                                   name="title" value="${activityApplyAdminDto.title}">
                        </div>
                    </div>
                    <%--姓名--%>
                    <div class="form-group  col-xs-4">
                        <label for="userName" class="col-sm-3 control-label">姓名</label>

                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="" id="userName"
                                   name="userName" value="${activityApplyAdminDto.userName}">
                        </div>
                    </div>
                    <%--手机号--%>
                    <div class="form-group  col-xs-4">
                        <label for="userMobile" class="col-sm-3 control-label"><spring:message
                                code="activityManage.applyUserMobile"/></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="" id="userMobile"
                                   name="userMobile" value="${activityApplyAdminDto.userMobile}">
                        </div>
                    </div>
                    <%--活动时间--%>
                    <div class="form-group  col-xs-4">
                        <label for="activityDate" class="col-sm-3 control-label">活动时间</label>

                        <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="activityDate"
                                   value="${activityApplyAdminDto.activityDate}" name="activityDate" readonly>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span
                                    class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <%--报名时间--%>
                    <div class="form-group  col-xs-4">
                        <label for="applyDate" class="col-sm-3 control-label">报名时间</label>

                        <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="applyDate"
                                   value="${activityApplyAdminDto.applyDate}" name="applyDate" readonly>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span
                                    class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon" style="padding-right: 6px;padding-left: 6px;"><span
                                    class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="form-group col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for=""><spring:message
                                code="common_search"/></button>
                        <input type="hidden" id="size" value="${isExcel}"/>
                        <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary"
                           style="color:#fff">导出Excel</a>

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
                <td><spring:message code="activityManage.applySort"/></td>
                <th>姓名</th>
                <th><spring:message code="activityManage.applyUserMobile"/></th>
                <th><spring:message code="activityManage.activityProjectName"/></th>
                <th>房产信息</th>
                <th><spring:message code="activityManage.activityTitle"/></th>
                <th><spring:message code="activityManage.activityDate"/></th>
                <th>报名时间</th>
                <th>报名人数</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${applyList}" var="apply" varStatus="vs">
                <tr>
                    <td><b>${vs.count}</b></td>
                    <td>
                        <c:if test="${empty apply.realName or apply.realName eq ''}">${apply.userName}</c:if>
                        <c:if test="${!empty apply.realName or apply.realName ne ''}">${apply.realName}</c:if>
                    </td>
                    <td>${apply.applyPhone}</td>
                    <td>${apply.projectName}</td>
                    <td>${apply.address}</td>
                    <td>${apply.title}</td>
                    <td>${apply.activityDate}</td>
                    <td>${apply.makeDate}</td>
                    <td>${apply.applyCount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/community/showActivityApply.html?menuId=005400040000&pageIndex={0}&scopeId=${activityApplyAdminDto.scopeId}&projectCode=${activityApplyAdminDto.projectCode}&userName=${activityApplyAdminDto.userName}&userType=${activityApplyAdminDto.userType}&title=${activityApplyAdminDto.title}&userMobile=${activityApplyAdminDto.userMobile}"/>
    </div>

</div>
</div>
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
    //通过城市获取项目列表
    function queryProjectNameById() {
        $("#projectCode").find("option").remove();
        /* -------------------- */
        var projectId = $("#scopeId").val();
        if(projectId == '-1'){
            $("#projectCode").empty();
            return ;
        }
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectCode").empty();
            $("#projectCode").append('<option value="">请选择</option>');
            for (var k in arr) {
                if(arr[k][0] != '0'){
                    $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
                }
            }
        });
    }
    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
            var href = "../community/applyExportExcel?menuId=005400040000&scopeId=${activityApplyAdminDto.scopeId}&projectCode=${activityApplyAdminDto.projectCode}&userName=${activityApplyAdminDto.userName}&userType=${activityApplyAdminDto.userType}&title=${activityApplyAdminDto.title}&userMobile=${activityApplyAdminDto.userMobile}";
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