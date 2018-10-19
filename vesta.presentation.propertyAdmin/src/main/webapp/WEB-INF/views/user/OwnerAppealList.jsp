<%--
  Describe:意见反馈
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/5/15
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        .myTime {
            padding-left: 6px;
            padding-right: 6px;
        }
        .form_b{
            padding: 1.5em 2rem 0 2rem;
            height: 15rem;
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
        #dialog-overlay {
            width:100%;
            height:100%;
            filter:alpha(opacity=50);
            -moz-opacity:0.5;
            -khtml-opacity: 0.5;
            opacity: 0.5;
            position:absolute;
            background:#000;
            top:0; left:0;
            z-index:3000;
            display:none;
        }
        #dialog-box {
            -webkit-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
            -moz-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            background:#eee;
            width:328px;
            position:absolute;
            z-index:5000;
            display:none;
        }
        #dialog-box .dialog-content {
            text-align:left;
            padding:10px;
            margin:13px;
            color:#666;
            font-family:arial;
            font-size:13px;
        }
    </style>
    <script>
        $(function(){
            console.log("sqq")
            $("#005200010000").addClass("active");
            $("#005200010000").parent().parent().addClass("in");
            $("#005200010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005200010000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form:form class="form-horizontal" action="../userFeed/getOwnerAppealList.html" method="post">
                    <input type="hidden" id="menuId" name="menuId" value="005200010000"/>
                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>
                        <div class="col-sm-8">
                            <select id="scopeId" name="cityId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq ownerAuthenticationDTO.cityId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group  col-xs-4">
                        <label for="projectCode" class="col-sm-4 control-label">项目</label>
                        <div class="col-sm-8">
                            <select id="projectCode" name="projectNum" class="form-control">
                                <c:forEach items="${projectList}" var="item" >
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq ownerAuthenticationDTO.projectNum}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 所属客户端 -->
                    <div class="form-group  col-xs-4">
                        <label for="clientId" class="col-sm-4 control-label">所属客户端</label>
                        <div class="col-sm-8">
                            <select id="clientId" name="appId" class="form-control">
                                <option value="">请选择</option>
                                <c:forEach items="${clientConfigList}" var="clientConfig" >
                                    <option value="${clientConfig.weChatAppId }"
                                            <c:if test="${clientConfig.weChatAppId eq ownerAuthenticationDTO.appId}">selected</c:if>>${clientConfig.clientName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 姓名 --%>
                    <div class="form-group  col-xs-4">
                        <label for="ownerName" class="col-sm-4 control-label">姓名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="ownerName" name="ownerName" value="${ownerAuthenticationDTO.ownerName}">
                        </div>
                    </div>
                    <%-- 手机号码 --%>
                    <div class="form-group  col-xs-4">
                        <label for="mobile" class="col-sm-4 control-label">手机号码</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="mobile" name="mobile" value="${ownerAuthenticationDTO.mobile}">
                        </div>
                    </div>
                    <%-- 证件号码 --%>
                    <div class="form-group  col-xs-4">
                        <label for="idCard" class="col-sm-4 control-label">证件号码</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="idCard" name="idCard" value="${ownerAuthenticationDTO.idCard}">
                        </div>
                    </div>
                    <%-- 申诉状态 --%>
                    <div class="form-group  col-xs-4">
                        <label for="handleState" class="col-sm-4 control-label">申诉状态</label>
                        <div class="col-sm-8">
                            <select id="handleState" name="handleState" class="form-control">
                                <option value="">--请选择状态--</option>
                                <option value="100" <c:if test="${'100' eq ownerAuthenticationDTO.handleState}">selected</c:if>>未处理</option>
                                <option value="101" <c:if test="${'101' eq ownerAuthenticationDTO.handleState}">selected</c:if>>申诉通过</option>
                                <option value="102" <c:if test="${'102' eq ownerAuthenticationDTO.handleState}">selected</c:if>>申诉失败</option>
                            </select>
                        </div>
                    </div>
                    <%-- 申诉类型 --%>
                    <div class="form-group  col-xs-4">
                        <label for="appealType" class="col-sm-4 control-label">申诉类型</label>
                        <div class="col-sm-8">
                            <select id="appealType" name="appealType" class="form-control">
                                <option value="">--请选择类型--</option>
                                <option value="0" <c:if test="${'0' eq ownerAuthenticationDTO.appealType}">selected</c:if>>普通申诉单</option>
                                <option value="1" <c:if test="${'1' eq ownerAuthenticationDTO.appealType}">selected</c:if>>特殊申诉单</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="common_search"/></button>
                        <!--集合长度(取决Excel是否可以导出)-->
                        <input type="hidden" id="size" value="${fn:length(ownerAppealList)}"/>
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
                <th>手机号码</th>
                <th>业主姓名</th>
                <th>证件类型</th>
                <th>证件编码</th>
                <th>房间信息</th>
                <th>申诉时间</th>
                <th>项目</th>
                <th>来源</th>
                <th>申诉类型</th>
                <th>处理状态</th>
                <th><spring:message code="common_operate"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ownerAppealList}" var="ownerAppeal" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${ownerAppeal.mobile}</td>
                    <td>${ownerAppeal.ownerName}</td>
                    <td>
                        <c:if test="${'100000000' eq ownerAppeal.idType}">身份证</c:if>
                        <c:if test="${'100000001' eq ownerAppeal.idType}">军官证</c:if>
                        <c:if test="${'100000002' eq ownerAppeal.idType}">警官证</c:if>
                        <c:if test="${'100000003' eq ownerAppeal.idType}">护照</c:if>
                        <c:if test="${'100000004' eq ownerAppeal.idType}">营业执照</c:if>
                    </td>
                    <td>${ownerAppeal.idCard}</td>
                    <td>
                        <c:if test="${empty ownerAppeal.houseAddress}">${ownerAppeal.houseNum}</c:if>
                        <c:if test="${!empty ownerAppeal.houseAddress}">${ownerAppeal.houseAddress}</c:if>
                    </td>
                    <td>
                        <fmt:formatDate type="time" value="${ownerAppeal.createOn}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>
                    <td>${ownerAppeal.projectName}</td>
                    <td>${ownerAppeal.clientName}</td>
                    <td>
                        <c:if test="${'0' eq ownerAppeal.appealType}">普通申诉单</c:if>
                        <c:if test="${'1' eq ownerAppeal.appealType}">特殊申诉单</c:if>
                    </td>
                    <td>
                        <c:if test="${'100' eq ownerAppeal.handleState}">未处理</c:if>
                        <c:if test="${'101' eq ownerAppeal.handleState}">申诉通过</c:if>
                        <c:if test="${'102' eq ownerAppeal.handleState}">申诉失败</c:if>
                    </td>
                    <td>
                        <a href="javascript:void(0);" onclick="js_appeal('${ownerAppeal.id}','101','${ownerAppeal.appealType}')">申诉通过</a>
                        <a href="javascript:void(0);" onclick="js_appeal('${ownerAppeal.id}','102','${ownerAppeal.appealType}')">申诉失败</a>
                        <a href="../userFeed/getOwnerAppealInfo.html?id=${ownerAppeal.id}"><spring:message
                                code="workOrders.detail"/></a><!--详情-->
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/userFeed/getOwnerAppealList.html?menuId=005200010000&pageIndex={0}&cityId=${ownerAuthenticationDTO.cityId}&projectNum=${ownerAuthenticationDTO.projectNum}&appId=${ownerAuthenticationDTO.appId}&ownerName=${ownerAuthenticationDTO.ownerName}&mobile=${ownerAuthenticationDTO.mobile}&idCard=${ownerAuthenticationDTO.idCard}&handleState=${ownerAuthenticationDTO.handleState}"/>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <%--
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                --%>
                <h5 class="modal-title" id="myModalLabel">
                    正在处理中...请不要重复点击！
                </h5>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<script type="text/javascript">

    function isExcel(){
        var size = $("#size").val();
        if(size>0){
            var href = "../userFeed/exportUserAppealListExcel?menuId=005200010000&pageIndex={0}&cityId=${ownerAuthenticationDTO.cityId}&projectNum=${ownerAuthenticationDTO.projectNum}&appId=${ownerAuthenticationDTO.appId}&ownerName=${ownerAuthenticationDTO.ownerName}&mobile=${ownerAuthenticationDTO.mobile}&idCard=${ownerAuthenticationDTO.idCard}&handleState=${ownerAuthenticationDTO.handleState}";
            window.location.href = href;
        }else{
            alert("没有可以导出的数据");
        }
    }

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

    var js_id;
    var js_handleState;
    var js_appealType;
    $('#myModal').on('shown.bs.modal', function (e) {
        js_handle(js_id,js_handleState,js_appealType);
    });
    var resultDate;
    $('#myModal').on('hidden.bs.modal', function (e) {
        if (resultDate.error == 0) {
            if (resultDate.flag == 1012){
                alert("业主申诉已通过！CRM正在创建业主数据；处理过程可能会持续几分钟！");
            }else if(resultDate.flag == 1013){
                alert("CRM业主数据通知失败！请稍后再试！");
            }else if(resultDate.flag == 1011){
                alert("该业主申诉已被处理成功，无需重复点击！");
            }else if(resultDate.flag == 1021){
                alert("该业主申诉已被处理成功，无法执行失败操作！");
            }else{
                alert("操作成功！");
            }
        }else {
            alert("对不起，操作失败！");
        }
        window.location.href = "../userFeed/getOwnerAppealList.html?menuId=" + $("#menuId").val();
    });
    function js_appeal(id,handleState,appealType){
        js_id = id;
        js_handleState = handleState;
        js_appealType = appealType;
        $('#myModal').modal({backdrop: 'static', keyboard: false});
    }
    //处理
    function js_handle(id,handleState,appealType) {
        if (appealType == 1 && handleState == 101){
            if (!confirm("该申诉单为特殊申诉单，申诉证件号码被其他用户使用，若通过该申诉，则其他用户将被解除业主绑定！确认要通过申诉么？")==true){
                return false;
            }
        }
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    $.ajax({
                        type: "GET",
                        url: "../userFeed/checkEdit/" + id + "/" + $("#menuId").val(),
                        cache: false,
                        async: false,
                        dataType: "json",
                        success: function (data) {
                            if (data.error == 1) {
                                $.ajax({
                                    type: "POST",
                                    url: "../userFeed/updateOwnerAppealState",
                                    cache: false,
                                    async: false,
                                    data:{
                                        "id":id,
                                        "handleState":handleState
                                    },
                                    dataType: "json",
                                    success: function (data) {
                                        resultDate = data;
                                        $('#myModal').modal('hide');
//                                        if (data.error == 0) {
//                                            if (data.flag == 1012){
//                                                alert("业主申诉已通过！CRM正在创建业主数据；处理过程可能会持续几分钟！");
//                                            }else if(data.flag == 1013){
//                                                alert("CRM业主数据通知失败！请稍后再试！");
//                                            }else if(data.flag == 1011){
//                                                alert("该业主申诉已被处理成功，无需重复点击！");
//                                            }else if(data.flag == 1021){
//                                                alert("该业主申诉已被处理成功，无法执行失败操作！");
//                                            }else{
//                                                alert("操作成功！");
//                                            }
//                                        }else {
//                                            alert("对不起，操作失败！");
//                                        }
//                                        window.location.href = "../userFeed/getOwnerAppealList.html?menuId=" + $("#menuId").val();
                                    }
                                });
                            } else if (data.error == 0) {
                                alert("对不起，您的权限范围无法执行此操作！");
                                return;
                            } else {
                                alert("对不起，操作失败！");
                                return;
                            }
                        }
                    });
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                    return;
                } else {
                    alert("对不起，操作失败！");
                    return;
                }
            }
        });
    }
</script>
</body>
</html>
