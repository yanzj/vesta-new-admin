<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/6/2
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        $(function () {
            $("#addOrganize").validate({
                rules: {
                    organizeName: {
                        required: true,
                        minlength: 1,
                        maxlength: 15
                    },
                    memo: {
                        required: false,
                        maxlength: 50
                    }
                },
                messages: {
                    organizeName: {
                        required: "请输入组名称！",
                        minlength: "不能少于1个字符！",
                        maxlength: "请勿超过15个字！"
                    },
                    memo: {
                        maxlength: "请勿超过50字符！"
                    }
                }
            })
        });
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="001300030000" username="${propertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <div class="form-horizontal">
                    <form name="" action="../organize/organizeList.html">
                        <div class="form-group  col-lg-4">
                            <label for="organizeName" class="col-sm-4 control-label">组名：</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="请输入组名搜索" value="${organize}"
                                       id="organizeName" name="organizeName">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyConsult.search"/></button>
                        <a href="../organize/goAltOrganize.html" class="btn btn-primary">新建组</a>
                    </form>
                </div>
                <%--搜索条件结束--%>
            </div>
        </div>
        <div class="table-responsive bs-example widget-shadow">
            <table width="100%" class="tableStyle">
                <thead>
                <tr>
                    <td><spring:message code="common_sort"/></td>
                    <th>组名称</th>
                    <th>备注</th>
                    <th>最后修改时间</th>
                    <th>组内成员数</th>
                    <th width="194"><spring:message code="common_operate"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${organizeList}" var="list" varStatus="row">
                    <tr>
                        <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
                        <td>${list.organizeName}</td>
                        <td>${list.memo}</td>
                        <td>${list.modifyTime}</td>
                        <td>${list.staffNum}</td>
                        <td>
                                <%--<a href="../organize/goAltOrganize.html?organizeId=${list.organizeId}">编辑</a>--%>
                            <a href="../organize/OrganizeDetail.html?organizeId=${list.organizeId}">详情</a>
                            <a style="cursor: pointer"
                               onclick="javascript:if(confirm('确定置为无效吗！'))location.href='../organize/delOrganize.html?organizeId=${list.organizeId}';else return">置为无效</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <m:pager pageIndex="${webPage.pageIndex}"
                     pageSize="${webPage.pageSize}"
                     recordCount="${webPage.recordCount}"
                     submitUrl="${pageContext.request.contextPath }/organize/organizeList.html?pageIndex={0}"/>
        </div>

    </div>
</div>
</div>
</div>
<script type="text/javascript">
    <!--  -->
    <!-- 当前页面 -->
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
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>


<%--新增公司组部分 模态框--%>
<%--<div class="modal fade" id="myModal">--%>
<%--<div class="modal-dialog modal-lg">--%>
<%--<form action="../organize/addOrganize.html" name="addOrganize" id="addOrganize" class="form-inline">--%>
<%--<div class="modal-content">--%>
<%--<div class="modal-header">--%>
<%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
<%--<h4 class="modal-title">新建公司组</h4>--%>
<%--</div>--%>
<%--<div class="modal-body">--%>
<%--<div class="highlight">--%>
<%--<div class="form-group mt10 col-lg-5">--%>
<%--<label for="organName" class="col-sm-4 control-label">组名称：</label>--%>
<%--<div class="col-sm-8">--%>
<%--<input type="text" id="organName" name="organizeName" class="form-control">--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group mt10">--%>
<%--<label for="memo" class="col-sm-3 control-label">备注：</label>--%>
<%--<div class="col-sm-9">--%>
<%--<input type="text" id="memo" name="memo" class="form-control">--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="modal-footer">--%>
<%--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>--%>
<%--<button type="submit" class="btn btn-primary">保存</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
</body>
</html>
