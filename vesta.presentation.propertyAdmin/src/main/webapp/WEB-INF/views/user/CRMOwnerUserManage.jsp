<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        .tableStyle thead td,.tableStyle thead th{
            padding-left: 0;
            text-align: center;
        }
        .myTime{
            padding-left: 6px;
            padding-right: 6px;
        }
        .form-horizontal .form-group{
            float: left;
        }
        .form_b{
            padding: 1.5em 2em 0 2rem;
            height: 15.5rem;
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
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005200070000" username="${propertystaff.staffName}" />
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form:form class="form-horizontal" action="../user/crmOwnerUserManagement.html" modelAttribute="commonUserSearch" method="get">
                    <input type="hidden" id="menuId" name="menuId" value="005200070000">
                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>
                        <div class="col-sm-8">
                            <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq commonUserSearch.scopeId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group  col-xs-4">
                        <label for="projectId" class="col-sm-4 control-label">项目</label>
                        <div class="col-sm-8">
                            <select id="projectId" name="projectId" class="form-control">
                                <c:forEach items="${projectList}" var="item" >
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq commonUserSearch.projectId}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 姓名 --%>
                    <div class="form-group  col-xs-4">
                        <label for="userName" class="col-sm-4 control-label"><spring:message code="ownerManage.Name" /></label>
                        <div class="col-sm-8">
                            <form:input type="text" class="form-control" placeholder="请输入姓名" value="${commonUserSearch.userName}" path="userName"/>
                        </div>
                    </div>
                    <%-- 手机号 --%>
                    <div class="form-group  col-xs-4">
                        <label for="mobile" class="col-sm-4 control-label">手机号码</label>
                        <div class="col-sm-8">
                            <form:input type="text" class="form-control" placeholder="请输入手机号" value="${commonUserSearch.mobile}" path="mobile"/>
                        </div>
                    </div>
                    <%-- 身份证号 --%>
                    <div class="form-group  col-xs-4">
                        <label for="idCard" class="col-sm-4 control-label"><spring:message code="activityManage.appointIdCard"/></label>
                        <div class="col-sm-8">
                            <form:input type="text" class="form-control" placeholder="请输入身份证号" value="${commonUserSearch.idCard}" path="idCard"/>
                        </div>
                    </div>
                    <%-- 房号 --%>
                    <div class="form-group  col-xs-4">
                        <label for="address" class="col-sm-4 control-label">房号</label>
                        <div class="col-sm-8">
                            <form:input type="text" class="form-control" placeholder="请输入身份证号" value="${commonUserSearch.address}" path="address"/>
                        </div>
                    </div>
                    <div class="form-group col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_search"/></button>
                        <input type="hidden" class="btn btn-primary" id="houseUserCrmClick" value="HouseUserCRM房产信息手动同步"/>
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
                <td style="width:6%;text-align:center"><spring:message code="common_sort" /></td>
                <th>姓名</th>
                <th>手机号</th>
                <th>身份证号</th>
                <th>项目名称</th>
                <th>地址</th>
                <th>创建时间</th>
                <th>更新时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${crmOwnerUserList}" var="user" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>
                        <c:if test="${empty user.realName or user.realName eq ''}"><p class="text-danger">数据不完整</p></c:if>
                        <c:if test="${!empty user.realName and user.realName ne ''}">${user.realName}</c:if>
                    </td>
                    <td>
                        <c:if test="${empty user.mobile or user.mobile eq ''}"><p class="text-danger">数据不完整</p></c:if>
                        <c:if test="${!empty user.mobile and user.mobile ne ''}">${user.mobile}</c:if>
                    </td>
                    <td>
                        <c:if test="${empty user.idCard or user.idCard eq ''}"><p class="text-danger">数据不完整</p></c:if>
                        <c:if test="${!empty user.idCard and user.idCard ne ''}">${user.idCard}</c:if>
                    </td>
                    <td>
                        <c:if test="${empty user.projectName or user.projectName eq ''}"><p class="text-danger">数据不完整</p></c:if>
                        <c:if test="${!empty user.projectName and user.projectName ne ''}">${user.projectName}</c:if>
                    </td>
                    <td>
                        <c:if test="${empty user.address or user.address eq ''}"><p class="text-danger">数据不完整</p></c:if>
                        <c:if test="${!empty user.address and user.address ne ''}">${user.address}</c:if>
                    </td>
                    <td>
                        <c:if test="${empty user.createOn or user.createOn eq ''}"><p class="text-danger">数据不完整</p></c:if>
                        <c:if test="${!empty user.createOn and user.createOn ne ''}"><fmt:formatDate type="time" value="${user.createOn}" pattern="yyyy-MM-dd hh:mm:ss" /></c:if>
                    </td>
                    <td>
                        <c:if test="${empty user.modifyOn or user.modifyOn eq ''}">未更新</c:if>
                        <c:if test="${!empty user.modifyOn and user.modifyOn ne ''}"><fmt:formatDate type="time" value="${user.modifyOn}" pattern="yyyy-MM-dd hh:mm:ss" /></c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/user/crmOwnerUserManagement.html?menuId=005200070000&pageIndex={0}&scopeId=${commonUserSearch.scopeId}&projectId=${commonUserSearch.projectId}&userName=${commonUserSearch.userName}&mobile=${commonUserSearch.mobile}&idCard=${commonUserSearch.idCard}&address=${commonUserSearch.address}"/>
    </div>
</div>
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
    //通过城市获取项目列表
    function queryProjectNameById() {
        $("#projectId").find("option").remove();
        /* -------------------- */
        var projectId = $("#scopeId").val();
        if(projectId == '-1'){
            $("#projectId").empty();
            return ;
        }
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectId").empty();
            $("#projectId").append('<option value="">请选择</option>');
            for (var k in arr) {
                if(arr[k][0] != '0'){
                    $("#projectId").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
                }
            }
        });
    }

    $("#houseUserCrmClick").click(function(){
        $.ajax({
            type: "GET",
            url: "../user/houseUserCrmSyn",
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 0) {
                    window.location.reload();
                } else {
                    alert("代码报错了！");
                    return;
                }
            }
        });
    });
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>
