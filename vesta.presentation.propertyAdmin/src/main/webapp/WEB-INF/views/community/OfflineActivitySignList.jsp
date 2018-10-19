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
        $("#005400060000").addClass("active");
        $("#005400060000").parent().parent().addClass("in");
        $("#005400060000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005400060000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../offlineActivity/getOfflineActivitySignList.html">
                    <input type="hidden" id="menuId" name="menuId" value="005400060000"/>
                    <input type="hidden" id="activityId" name="activityId" value="${offlineActivityDTO.activityId}">
                    <%-- 活动标题 --%>
                    <div class="form-group  col-xs-4">
                        <label for="activityTitle" class="col-sm-4 control-label">活动</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="activityTitle" name="activityTitle" value="${offlineActivityDTO.activityTitle}">
                        </div>
                    </div>
                    <%-- 姓名 --%>
                    <div class="form-group  col-xs-4">
                        <label for="ownerName" class="col-sm-4 control-label">姓名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="ownerName" name="ownerName" value="${offlineActivityDTO.ownerName}">
                        </div>
                    </div>
                    <%-- 联系电话 --%>
                    <div class="form-group  col-xs-4">
                        <label for="mobile" class="col-sm-4 control-label">联系电话</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="mobile" name="mobile" value="${offlineActivityDTO.mobile}">
                        </div>
                    </div>
                    <!-- 签到时间 -->
                    <div class="form-group  col-xs-4">
                        <label for="createOnStr" class="col-sm-4 control-label">签到时间</label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="createOnStr"
                                   value="${offlineActivityDTO.createOnStr}" name="createOnStr" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <%-- 中奖情况 --%>
                    <div class="form-group  col-xs-4">
                        <label for="prizeInfo" class="col-sm-4 control-label">活动状态</label>
                        <div class="col-sm-8">
                            <select id="prizeInfo" name="prizeInfo" class="form-control">
                                <option value="">--请选择--</option>
                                <option value="一等奖" <c:if test="${'一等奖' eq offlineActivityDTO.prizeInfo}">selected</c:if>>一等奖</option>
                                <option value="二等奖" <c:if test="${'二等奖' eq offlineActivityDTO.prizeInfo}">selected</c:if>>二等奖</option>
                                <option value="三等奖" <c:if test="${'三等奖' eq offlineActivityDTO.prizeInfo}">selected</c:if>>三等奖</option>
                                <option value="未中奖" <c:if test="${'未中奖' eq offlineActivityDTO.prizeInfo}">selected</c:if>>未中奖</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <!--集合长度(取决Excel是否可以导出)-->
                        <input type="hidden" id="size" value="${fn:length(activitySignList)}"/>
                        <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary"
                           style="color:#fff">导出Excel</a>
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
                <th>姓名</th>
                <th>房产</th>
                <th>联系电话</th>
                <th>参与人数</th>
                <th>签到时间</th>
                <th>签到活动</th>
                <th>中奖情况</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${activitySignList}" var="activitySign" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${activitySign.ownerName}</td>
                    <td>${activitySign.address}</td>
                    <td>${activitySign.mobile}</td>
                    <td>${activitySign.partakeNumber}</td>
                    <td>
                        <fmt:formatDate type="time" value="${activitySign.createOn}" pattern="yyyy-MM-dd HH:mm" />
                    </td>
                    <td>${activitySign.activityTitle}</td>
                    <td>${activitySign.prizeInfo}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/offlineActivity/getOfflineActivitySignList.html?menuId=005400060000&pageIndex={0}&activityId=${offlineActivityDTO.activityId}&activityTitle=${offlineActivityDTO.activityTitle}&ownerName=${offlineActivityDTO.ownerName}&mobile=${offlineActivityDTO.mobile}&createOnStr=${offlineActivityDTO.createOnStr}&prizeInfo=${offlineActivityDTO.prizeInfo}"/>
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

    function isExcel(){
        var size = $("#size").val();
        if(size>0){
            var href = "../offlineActivity/exportActivitySignListExcel?menuId=005400060000&pageIndex={0}&activityId=${offlineActivityDTO.activityId}&activityTitle=${offlineActivityDTO.activityTitle}&ownerName=${offlineActivityDTO.ownerName}&mobile=${offlineActivityDTO.mobile}&createOnStr=${offlineActivityDTO.createOnStr}&prizeInfo=${offlineActivityDTO.prizeInfo}";
            window.location.href = href;
        }else{
            alert("没有可以导出的数据");
        }
    }
</script>
</body>
</html>