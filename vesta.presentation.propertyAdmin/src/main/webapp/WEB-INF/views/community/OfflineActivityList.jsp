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
        $("#005400050000").addClass("active");
        $("#005400050000").parent().parent().addClass("in");
        $("#005400050000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005400050000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../offlineActivity/getOfflineActivityList.html">
                    <input type="hidden" id="menuId" name="menuId" value="005400050000"/>
                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>
                        <div class="col-sm-8">
                            <select id="scopeId" name="cityId" class="form-control">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq offlineActivityDTO.cityId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 活动标题 --%>
                    <div class="form-group  col-xs-4">
                        <label for="activityTitle" class="col-sm-4 control-label">活动标题</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="activityTitle" name="activityTitle" value="${offlineActivityDTO.activityTitle}">
                        </div>
                    </div>
                    <%-- 活动状态 --%>
                    <div class="form-group  col-xs-4">
                        <label for="activityState" class="col-sm-4 control-label">活动状态</label>
                        <div class="col-sm-8">
                            <select id="activityState" name="activityState" class="form-control">
                                <option value="">--请选择状态--</option>
                                <option value="0" <c:if test="${'0' eq offlineActivityDTO.activityState}">selected</c:if>>未开始</option>
                                <option value="1" <c:if test="${'1' eq offlineActivityDTO.activityState}">selected</c:if>>进行中</option>
                                <option value="2" <c:if test="${'2' eq offlineActivityDTO.activityState}">selected</c:if>>已结束</option>
                            </select>
                        </div>
                    </div>
                    <!-- 活动开始日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="activityStartTimeStr" class="col-sm-4 control-label">活动时间</label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="activityStartTimeStr"
                                   value="${offlineActivityDTO.activityStartTimeStr}" name="activityStartTimeStr" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <!-- 活动结束日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="activityEndTimeStr" class="col-sm-4 control-label"><spring:message
                                code="HousePayBatch.to"/></label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="activityEndTimeStr"
                                   value="${offlineActivityDTO.activityEndTimeStr}" name="activityEndTimeStr" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
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
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                <th>活动标题</th>
                <th>活动时间</th>
                <th>城市</th>
                <th>地址</th>
                <th>签到人数</th>
                <th>参与人数</th>
                <th>活动状态</th>
                <th>是否抽奖</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${offlineActivityList}" var="offlineActivity" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${offlineActivity.activityTitle}</td>
                    <td>
                        <fmt:formatDate type="time" value="${offlineActivity.activityStartTime}" pattern="yyyy-MM-dd HH:mm" />
                    </td>
                    <td>${offlineActivity.cityName}</td>
                    <td>${offlineActivity.activityPlace}</td>
                    <td>${offlineActivity.signNumber}</td>
                    <td>${offlineActivity.partakeNumber}</td>
                    <td>
                        <c:if test="${'0' eq offlineActivity.activityState}">未开始</c:if>
                        <c:if test="${'1' eq offlineActivity.activityState}">进行中</c:if>
                        <c:if test="${'2' eq offlineActivity.activityState}">已结束</c:if>
                    </td>
                    <td>
                        <c:if test="${'1' eq offlineActivity.isLuckDraw}">是</c:if>
                        <c:if test="${'0' eq offlineActivity.isLuckDraw}">否</c:if>
                    </td>
                    <td class="last">
                        <a href="javascript:void(0);" onclick="toEdit('${offlineActivity.activityId}')" id="toEdit" class="a3">编辑</a>
                        <a href="javascript:void(0);" onclick="toSignInfo('${offlineActivity.activityId}')" id="toSignInfo" class="a3">签到详情</a>
                        <a href="javascript:void(0);" onclick="toLuckDraw('${offlineActivity.activityId}','${offlineActivity.isLuckDraw}')" id="toLuckDraw" class="a3">去抽奖</a>
                        <a href="javascript:void(0);" onclick="toDelete('${offlineActivity.activityId}')" id="toDelete" class="a3">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/offlineActivity/getOfflineActivityList.html?menuId=005400050000&pageIndex={0}&cityId=${offlineActivityDTO.cityId}&activityTitle=${offlineActivityDTO.activityTitle}&activityState=${offlineActivityDTO.activityState}&activityStartTime=${offlineActivityDTO.activityStartTime}&activityEndTime=${offlineActivityDTO.activityEndTime}"/>
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
    //编辑
    function toEdit(id){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../offlineActivity/toEditOfflineActivity.html?menuId="+$("#menuId").val()+"&activityId="+id;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //删除
    function toDelete(activityId){
        if (confirm("删除会使该活动签到记录同步删除，确定要删除么？")) {
            $.ajax({
                type: "GET",
                url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
                cache: false,
                async:false,
                dataType:"json",
                success: function (data) {
                    if (data.error == 1) {
                        $.ajax({
                            url:"../offlineActivity/toDeleteOfflineActivity",
                            type:"POST",
                            cache:false,
                            async:false,
                            data:{
                                activityId:activityId
                            },
                            dataType:"JSON",
                            error:function(){
                                alert("网络异常，可能服务器忙，请刷新重试");
                            },
                            success:function(data){
                                if(data.error == "0"){
                                    alert("删除成功！");
                                    location.reload();
                                }else if(data.error == "-1"){
                                    alert("删除失败，请联系管理员！");
                                }
                            }
                        });
                    }else if(data.error == 0) {
                        alert("对不起，您无权限执行此操作！");
                    }else{
                        alert("对不起，操作失败！");
                    }
                }
            });
        }
    }
    //签到详情
    function toSignInfo(activityId){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../offlineActivity/getOfflineActivitySignList.html?menuId="+$("#menuId").val()+"&activityId="+activityId;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //去抽奖
    function toLuckDraw(activityId,isLuckDraw){
        if (isLuckDraw == 1){
            $.ajax({
                type: "GET",
                url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
                cache: false,
                async:false,
                dataType:"json",
                success: function (data) {
                    if (data.error == 1) {
                        window.open("../offlineActivity/toGoLuckDraw.html?menuId="+$("#menuId").val()+"&activityId="+activityId);
                    }else if(data.error == 0) {
                        alert("对不起，您无权限执行此操作！");
                    }else{
                        alert("对不起，操作失败！");
                    }
                }
            });
        }else{
            alert("该活动不能抽奖！");
        }
    }
</script>
</body>
</html>