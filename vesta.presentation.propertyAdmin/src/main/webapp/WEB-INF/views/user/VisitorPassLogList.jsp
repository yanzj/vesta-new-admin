<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            $("#005700060000").addClass("active");
            $("#005700060000").parent().parent().addClass("in");
            $("#005700060000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005700060000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../visitor/getVisitorPassLogList.html">
                    <input type="hidden" id="menuId" name="menuId" value="005700060000"/>
                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>
                        <div class="col-sm-8">
                            <select id="scopeId" name="cityId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq visitorPassDTO.cityId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group  col-xs-4">
                        <label for="projectCode" class="col-sm-4 control-label">项目</label>
                        <div class="col-sm-8">
                            <select id="projectCode" name="projectCode" class="form-control">
                                <c:forEach items="${projectList}" var="item" >
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq visitorPassDTO.projectCode}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 访客姓名 --%>
                    <div class="form-group  col-xs-4">
                        <label for="visitorName" class="col-sm-4 control-label">访客姓名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="visitorName" name="visitorName" value="${visitorPassDTO.visitorName}">
                        </div>
                    </div>
                    <%-- 被访人姓名 --%>
                    <div class="form-group  col-xs-4">
                        <label for="ownerName" class="col-sm-4 control-label">被访人</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="ownerName" name="ownerName" value="${visitorPassDTO.ownerName}">
                        </div>
                    </div>
                    <!-- 查询开始日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="createOnStr" class="col-sm-4 control-label">日期设定</label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="createOnStr"
                                   value="${visitorPassDTO.createOnStr}" name="createOnStr" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <!-- 查询结束日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="createOnEnd" class="col-sm-4 control-label">至</label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="createOnEnd"
                                   value="${visitorPassDTO.createOnEnd}" name="createOnEnd" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <%-- 房产地址 --%>
                    <div class="form-group  col-xs-4">
                        <label for="houseAddress" class="col-sm-4 control-label">房产地址</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="houseAddress" name="houseAddress" value="${visitorPassDTO.houseAddress}">
                        </div>
                    </div>
                    <div class="col-xs-12 search_button">
                        <button type="button" class="btn btn-primary" onclick="toSearch()"><spring:message code="propertyCompany.propertySearch"/></button>
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
                <th>项目名称</th>
                <th>房产地址</th>
                <th>被访人</th>
                <th>被访人电话</th>
                <th>访客</th>
                <th>访客电话</th>
                <th>扫码人昵称</th>
                <th>放行时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${visitorPassLogList}" var="visitorPassLog" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${visitorPassLog.projectName}</td>
                    <td>${visitorPassLog.houseAddress}</td>
                    <td>${visitorPassLog.ownerName}</td>
                    <td>${visitorPassLog.ownerPhone}</td>
                    <td>${visitorPassLog.visitorName}</td>
                    <td>${visitorPassLog.visitorPhone}</td>
                    <td>${visitorPassLog.guardName}</td>
                    <td>${visitorPassLog.passDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/visitor/getVisitorPassLogList.html?menuId=005700060000&pageIndex={0}&cityId=${visitorPassDTO.cityId}&projectCode=${visitorPassDTO.projectCode}&visitorName=${visitorPassDTO.visitorName}&ownerName=${visitorPassDTO.ownerName}&createOnStr=${visitorPassDTO.createOnStr}&createOnEnd=${visitorPassDTO.createOnEnd}&houseAddress=${visitorPassDTO.houseAddress}"/>
    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
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
<script type="text/javascript">
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
    function toSearch(){
        if ($("#scopeId").val() != '0' && $("#scopeId").val() != ''){
            if ($("#projectCode").val() == '0' || $("#projectCode").val() == ''){
                alert("请选择项目!");
                return ;
            }
        }
        $("#form").submit();
    }
</script>
</body>
</html>
