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
        height: 15.5rem;
    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#006000010000").addClass("active");
        $("#006000010000").parent().parent().addClass("in");
        $("#006000010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006000010000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../salesPromotionInfo/getSalesPromotionInfoList.html">
                    <input type="hidden" id="menuId" name="menuId" value="006000010000"/>
                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>
                        <div class="col-sm-8">
                            <select id="scopeId" name="cityId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq salesPromotionInfoDTO.cityId}">selected</c:if>>${item.cityName }</option>
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
                                            <c:if test="${item[0] eq salesPromotionInfoDTO.projectNum}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 促销标题 --%>
                    <div class="form-group  col-xs-4">
                        <label for="title" class="col-sm-4 control-label">促销信息</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="title" name="title" value="${salesPromotionInfoDTO.title}">
                        </div>
                    </div>
                    <%-- 发布人 --%>
                    <div class="form-group  col-xs-4">
                        <label for="releasePerson" class="col-sm-4 control-label">发布人</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="releasePerson" name="releasePerson" value="${salesPromotionInfoDTO.releasePerson}">
                        </div>
                    </div>
                    <!-- 发布开始日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="releaseStaDate" class="col-sm-4 control-label"><spring:message
                                code="propertyAnnouncement.createTimeAndEnd"/></label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="releaseStaDate"
                                   value="${salesPromotionInfoDTO.releaseStaDate}" name="releaseStaDate" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <!-- 发布结束日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="releaseEndDate" class="col-sm-4 control-label"><spring:message
                                code="HousePayBatch.to"/></label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="releaseEndDate"
                                   value="${salesPromotionInfoDTO.releaseEndDate}" name="releaseEndDate" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <%-- 发布状态 --%>
                    <div class="form-group  col-xs-4">
                        <label for="releaseStatus" class="col-sm-4 control-label">发布状态</label>
                        <div class="col-sm-8">
                            <select id="releaseStatus" name="releaseStatus" class="form-control">
                                <option value="">--请选择状态--</option>
                                <option value="0" <c:if test="${'0' eq salesPromotionInfoDTO.releaseStatus}">selected</c:if>>未发布</option>
                                <option value="1" <c:if test="${'1' eq salesPromotionInfoDTO.releaseStatus}">selected</c:if>>已发布</option>
                            </select>
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
                    <th>促销信息</th>
                    <th>发布范围</th>
                    <th>是否作为宣传位</th>
                    <th>状态</th>
                    <th>发布人</th>
                    <th>发布日期</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${salesPromotionInfoList}" var="salesPromotionInfo" varStatus="row">
                    <tr>
                        <td><b>${row.index + 1}</b></td>
                        <td>${salesPromotionInfo.title}</td>
                        <td>${salesPromotionInfo.releaseProjectScopes}</td>
                        <td>
                            <c:if test="${'0' eq salesPromotionInfo.isBanner}">否</c:if>
                            <c:if test="${'1' eq salesPromotionInfo.isBanner}">是</c:if>
                        </td>
                        <td>
                            <c:if test="${'0' eq salesPromotionInfo.releaseStatus}">未发布</c:if>
                            <c:if test="${'1' eq salesPromotionInfo.releaseStatus}">已发布</c:if>
                        </td>
                        <td>${salesPromotionInfo.releasePerson}</td>
                        <td>${salesPromotionInfo.releaseDate}</td>
                        <td class="last">
                            <a href="javascript:void(0);" onclick="toEdit('${salesPromotionInfo.id}')" id="toEdit" class="a3">编辑</a>
                            <a href="javascript:void(0);" onclick="toDelete('${salesPromotionInfo.id}')" id="toDelete" class="a3">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/salesPromotionInfo/getSalesPromotionInfoList.html?menuId=006000010000&pageIndex={0}&cityId=${butlerStyleDto.cityId}&projectNum=${butlerStyleDto.projectNum}&title=${salesPromotionInfoDTO.title}&releasePerson=${salesPromotionInfoDTO.releasePerson}&releaseStaDate=${salesPromotionInfoDTO.releaseStaDate}&releaseEndDate=${salesPromotionInfoDTO.releaseEndDate}&releaseStatus=${salesPromotionInfoDTO.releaseStatus}"/>
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
    function toEdit(id){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../salesPromotionInfo/toEditSalesPromotionInfo.html?menuId="+$("#menuId").val()+"&id="+id;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //删除
    function toDelete(id){
        if (confirm("确认要删除？")) {
            $.ajax({
                type: "GET",
                url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
                cache: false,
                async:false,
                dataType:"json",
                success: function (data) {
                    if (data.error == 1) {
                        $.ajax({
                            url:"../salesPromotionInfo/deleteSalesPromotionInfo",
                            type:"POST",
                            cache:false,
                            async:false,
                            data:{
                                id:id
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
</script>
</body>
</html>