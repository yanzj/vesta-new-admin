<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/5/25
  Time: 10:53
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
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
    <style>
        label.error {
            /*position: absolute;*/
            /*margin-top: -74px;*/
            /*display: block;*/
            margin-left: 1%;
            color: red;
        }
    </style>
    <script type="application/javascript">
        $(function() {
            $("#addStand").validate({
                rules: {
                    caseName: {
                        required: true,
                        minlength: 1,
                        maxlength: 20
                    },
                    standPlace: {
                        required:true,
                        minlength:1
                    },
                    standDesc: {
                        required: true,
                        minlength: 3,
                        maxlength: 500
                    },
                    standTime: {
                        required: true,
                        minlength: 1
                    }
                },
                messages: {
                    caseName: {
                        required: "请输入批次名称！",
                        minlength: "不能少于1个字符！",
                        maxlength: "请勿超过20个字！"
                    },
                    standPlace: {
                        required: "请输入旁站位置",
                        minlength:"不能少于1个字符"
                    },
                    standDesc: {
                        required: "请输入旁站要点！",
                        minlength: "不能少于3个字符！",
                        maxlength: "请勿超过500个字符！"
                    },
                    standTime: {
                        required: "请输入文字说明！",
                        minlength: "不能少于1个字符"
                    }
                }
            })
        });
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005800050000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" name="search" action="../stand/standList.html">
                    <div class="form-group  col-lg-3">
                        <input type="text" id="caseName" name="caseName" placeholder="请输入批次名称" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_search"/></button>
                    <button type="button" class="btn btn-primary" for="propertyAdd" data-toggle="modal" data-target="#myModal">新建批次</button>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td>序号</td>
                <th>批次名称</th>
                <th>所属项目</th>
                <th>旁站位置</th>
                <th>旁站日期</th>
                <th>旁站人员</th>
                <th>旁站要点</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${standList}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index+1}</b></td>
                    <td>${list.caseName}</td>
                    <td>${list.projectName}</td>
                    <td>${list.standPlace}</td>
                    <td>${list.standTime}</td>
                    <td>${list.standUser}</td>
                    <td>${list.standDesc}</td>
                    <td><a href="../stand/altStand.html?standId=${list.standId}&status=2&pageIndex=${webPage.pageIndex}"><c:choose><c:when test="${list.status=='1'}">关闭批次</c:when><c:otherwise>重新打开</c:otherwise></c:choose></a>
                        <c:choose><c:when test="${list.status=='1'}"><a href="../stand/gotoDetail.html?standId=${list.standId}&flag=2"><spring:message code="common_update" /></a></c:when>
                            <c:otherwise><a href="../stand/gotoDetail.html?standId=${list.standId}&flag=1">详情</c:otherwise></c:choose>
                        <a onClick="javascript:if (confirm('确定删除吗？')) location.href='../stand/deleteStand.html?standId=${list.standId}';else return;"><spring:message code="common_delete" /></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../stand/standList.html?pageIndex={0}"/>
    </div>


</div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

 <%--新增旁站部分 模态框--%>
<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-lg">
        <form action="../stand/addStand.html" name="addStand" id="addStand" class="form-inline">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新建旁站批次</h4>
                </div>
                <div class="modal-body">
                    <div class="highlight">
                        <div class="form-group mt10">
                            <label for="caseName" class="col-sm-5 control-label">工序名称</label>
                            <div class="col-sm-7">
                                <input type="text" id="case" name="caseName" class="form-control">
                            </div>
                        </div>
                        <br/>
                        <div class="form-group mt10">
                            <label for="standPlace" class="col-sm-5 control-label">旁站位置</label>
                            <div class="col-sm-7">
                                <input type="text" id="standPlace" name="standPlace" class="form-control">
                            </div>
                        </div>
                        <br/>
                        <div class="form-group mt10">
                            <label for="standTime" class="col-sm-5 control-label">旁站日期</label>
                            <div class="input-group date form_date col-sm-7" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="" id="standTime" value="${rental.beginTime}" name="standTime" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <br/>
                        <div class="form-group mt10">
                            <label for="standUser" class="col-sm-5 control-label">旁站人员</label>
                            <div class="col-sm-7">
                                <input type="text" id="standUser" name="standUser" class="form-control">
                            </div>
                        </div>
                        <br/>
                        <div class="form-group mt10">
                            <label for="standDesc" class="col-sm-5 control-label">旁站要点</label>
                            <div class="col-sm-7">
                                <input type="text" id="standDesc" name="standDesc" class="form-control">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </div>
        </form>
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
</script>
</body>

</html>