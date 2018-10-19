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
    <link href="../static/css/jquery-ui.min.css" rel="stylesheet"/>
    <link href="../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <link href="../static/css/font-awesome.css" rel="stylesheet">
    <link href="../static/css/custom.css" rel="stylesheet">
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <script src="../static/js/jquery-ui.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <style>
        label.error {
            margin-left: 1%;
            color: red;
        }
    </style>
    <style type="text/css">#dialog {
        display: none;
    }</style>
    <script type="text/javascript">
        function keleyidialog(caseId) {
            var  obj = document.getElementsByName("answer");
            var flag = false;
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].checked == true) {
                    flag = true;
                }
            }
            if (!flag) {
                alert("请至少选择一项");
            }else{
                $("#dialog").dialog({
                    resizable: false,
                    height: 240,
                    width: 500,
                    modal: true,
                    closeOnEscape: false,
                    open: function(event, ui) {
                        $(".ui-dialog-titlebar-close").hide();
                    },
                    buttons: {
                        "确定": function () {
                            var check_val = [];
                            for(k in obj){
                                if(obj[k].checked)
                                    check_val.push(obj[k].value);
                            }
                            var repairManager=$("#repairManager").val();
                            window.location.href = "../problem/editProblemManagement?caseId=" + check_val+"&repairManager="+repairManager;
                        },
                        "取消": function () {
                            $(this).dialog("close");
                        }
                    }
                });
            }
        }
    </script>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="001600010000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <div class="form-marginL">
                    <form class="form-horizontal" name="search" action="../problem/problemManagements.html" method="post">
                    <div class="form-group  col-lg-4">
                        <label for="proType" class="col-sm-4 control-label">责任人:</label>
                        <div class="col-sm-8">
                            <input class="form-control" id="keyword" name="userName" size="10" class="inputstyle keywords" value="${problem.userName}"
                                   onfocus='if(this.value=="请输入责任人名称"){this.value="";}; '
                                                      onblur='if(this.value==""){this.value="请输入责任人名称";};'>
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="startDate" class="col-sm-4 control-label"><spring:message
                                code="workOrders.startDate"/></label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="请输入开始时间" path="startDate"
                                   name="startDate" value="${problem.startDate}" readonly="true"/>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="endDate" class="col-sm-4 control-label"><spring:message
                                code="workOrders.endDate"/></label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="请输入结束时间" path="endDate" name="endDate"
                                   value="${problem.endDate}" readonly="true"/>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                </form>
                </div>
                <div class="clearfix"></div>
                <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                        code="problem.search"/></button>
                <button type="button" onClick="keleyidialog();" class="btn btn-primary" for="propertyAdd" data-toggle="modal" data-target="#myModal">批量修改</button>
            </div>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <th><input name="checkall1" type="checkbox" value="0" onClick="checkAllBox(this);"/>序号</th>
                <th>超期</th>
                <th>问题描述</th>
                <th>楼栋</th>
                <th>三级分类</th>
                <th>登记时间</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${problems}" var="problem" varStatus="row">
                <tr>
                    <input id="${problem.caseId}" type="hidden" value="${problem.caseId}"/>
                    <td><input name="answer" type="checkbox" value="${problem.caseId}"/><b>${row.index+1}</b></td>
                    <c:choose>
                        <c:when test="${problem.limitTime>problem.realityDate}">
                            <td>超期</td>
                        </c:when>
                        <c:otherwise>
                            <td>进行中</td>
                        </c:otherwise>
                    </c:choose>

                    <td><a href="../problem/getProblemManagementDetail?rectifyId=${problem.caseId}&planType=${problem.planType}">${problem.caseDesc}</a>
                    </td>
                    <td>${problem.address}</td>
                    <td>${problem.thirdTypeName}</td>
                    <td>${problem.createDate}</td>
                    <td>
                        <c:if test="${problem.caseState eq '草稿'}">草稿</c:if>
                        <c:if test="${problem.caseState eq '处理中'}">处理中</c:if>
                        <c:if test="${problem.caseState eq '已完成'}">已完成</c:if>
                        <c:if test="${problem.caseState eq '已废弃'}">已废弃</c:if>
                        <c:if test="${problem.caseState eq '待接单'}">待接单</c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/problem/problemManagement.html?pageIndex={0}&userName=${problem.userName}"/>
    </div>
    <div id="dialog" title="修改负责人">
        <table>
            <tr>
                <td valign="top">
                    <p>责任人:</p>
                </td>
                <td>
                    <input id="repairManager" name="repairManager" type="text"/>
                </td>
            </tr>
        </table>
    </div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
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
</script>
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>