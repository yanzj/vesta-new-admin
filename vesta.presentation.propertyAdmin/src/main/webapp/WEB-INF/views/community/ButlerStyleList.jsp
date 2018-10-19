<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="../static/property/js/propertyHousPay.js"></script>
</head>
<style type="text/css">
    .search_button{
        text-align: center;
    }
    .form_b{
        height: 12.5rem;
    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#005500050000").addClass("active");
        $("#005500050000").parent().parent().addClass("in");
        $("#005500050000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005500050000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../butlerStyle/getButlerStyleList.html">
                    <input type="hidden" id="menuId" name="menuId" value="005500050000"/>
                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>
                        <div class="col-sm-8">
                            <select id="scopeId" name="cityId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq butlerStyleDto.cityId}">selected</c:if>>${item.cityName }</option>
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
                                            <c:if test="${item[0] eq butlerStyleDto.projectNum}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 服务楼栋 --%>
                    <div class="form-group  col-xs-4">
                        <label for="serviceBuilding" class="col-sm-4 control-label">服务楼栋</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="serviceBuilding" name="serviceBuilding" value="${butlerStyleDto.serviceBuilding}">
                        </div>
                    </div>
                    <%-- 管家名称 --%>
                    <div class="form-group  col-xs-4">
                        <label for="butlerNum" class="col-sm-4 control-label">管家名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="butlerNum" name="butlerNum" value="${butlerStyleDto.butlerNum}">
                        </div>
                    </div>
                    <%-- 联系电话 --%>
                    <div class="form-group  col-xs-4">
                        <label for="telephone" class="col-sm-4 control-label">联系电话</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="telephone" name="telephone" value="${butlerStyleDto.telephone}">
                        </div>
                    </div>
                    <%-- 微信昵称 --%>
                    <div class="form-group  col-xs-4">
                        <label for="wechatNickname" class="col-sm-4 control-label">微信昵称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="wechatNickname" name="wechatNickname" value="${butlerStyleDto.wechatNickname}">
                        </div>
                    </div>
                    <div class="col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <button type="button" class="btn btn-primary" onclick="toEdit('')" id="toAdd">新增</button>
                    </div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <form id="batchForm" class="form-horizontal" action="#">
            <table width="100%" class="tableStyle">
                <thead>
                <tr>
                    <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                    <th>管家名称</th>
                    <th>项目</th>
                    <th>服务楼栋</th>
                    <th>联系电话</th>
                    <th>微信昵称</th>
                    <th>累计评分</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${butlerStyleList}" var="butlerStyle" varStatus="row">
                    <tr>
                        <td><b>${row.index + 1}</b></td>
                        <td>${butlerStyle.butlerNum}</td>
                        <td>${butlerStyle.serviceProjectName}</td>
                        <td>${butlerStyle.serviceBuilding}</td>
                        <td>${butlerStyle.telephone}</td>
                        <td>${butlerStyle.wechatNickname}</td>
                        <td>${butlerStyle.butlerScore}</td>
                        <td class="last">
                            <a href="javascript:void(0);" onclick="toEdit('${butlerStyle.butlerId}')" id="toEdit" class="a3">编辑</a>
                            <a href="javascript:void(0);" onclick="toAssignScope('${butlerStyle.butlerId}')" id="toAssignScope" class="a3">责任范围</a>
                            <a href="javascript:void(0);" onclick="toScoreInfo('${butlerStyle.butlerId}')" id="toScoreInfo" class="a3">查看评分</a>
                            <a href="javascript:void(0);" onclick="toDelete('${butlerStyle.butlerId}')" id="toDelete" class="a3">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/butlerStyle/getButlerStyleList.html?menuId=005500050000&pageIndex={0}&cityId=${butlerStyleDto.cityId}&projectNum=${butlerStyleDto.projectNum}&serviceBuilding=${butlerStyleDto.serviceBuilding}&butlerNum=${butlerStyleDto.butlerNum}&telephone=${butlerStyleDto.telephone}&wechatNickname=${butlerStyleDto.wechatNickname}"/>
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
</script>
<script>
    //通过城市获取项目列表
    function queryProjectNameById() {
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
    //编辑
    function toEdit(butlerId){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../butlerStyle/toEditButlerStyle.html?menuId="+$("#menuId").val()+"&butlerId="+butlerId;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //分配责任范围
    function toAssignScope(butlerId){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../butlerStyle/toAssignButlerScope.html?butlerId="+butlerId;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //查看评分
    function toScoreInfo(butlerId){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../butlerStyle/getButlerScoreLogList.html?butlerId="+butlerId;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //删除
    function toDelete(butlerId){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    if (data.error == 1) {
                        if (confirm("确认要删除？")) {
                            $.ajax({
                                url: "../butlerStyle/deleteButlerStyle",
                                type: "POST",
                                cache: false,
                                async: false,
                                data: {
                                    butlerId: butlerId
                                },
                                dataType: "JSON",
                                error: function () {
                                    alert("网络异常，可能服务器忙，请刷新重试");
                                },
                                success: function (data) {
                                    if (data.error == "0") {
                                        alert("删除成功！");
                                        location.reload();
                                    } else if (data.error == "-1") {
                                        alert("删除失败，请联系管理员！");
                                    }
                                }
                            });
                        }
                    } else if (data.error == 0) {
                        alert("对不起，您无权限执行此操作！");
                    } else {
                        alert("对不起，操作失败！");
                    }
                }
            }
        });
    }
</script>
</body>
</html>