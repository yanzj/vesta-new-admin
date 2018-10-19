<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        $(function () {
            console.log("sqq")
            $("#007800010000").addClass("active");
            $("#007800010000").parent().parent().addClass("in");
            $("#007800010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>

    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
    <%--图片放大--%>
    <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>

    <%-- FileInput --%>
    <link href="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/css/fileinput.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/js/fileinput.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.1/js/fileinput_locale_zh.js"></script>

    <style>
        .imgList img {
            width: 90px;
            height: 90px;
            margin-right: 5px;
        }
    </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007800010000" username="${authPropertystaff.staffName}"/>

    <div class="forms" style="overflow: hidden;margin-bottom: 20px;">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../qcNews/getNewsList.html">
                    <div class="col-lg-12">
                        <%-- 新闻标题 --%>
                        <div class="form-group  col-lg-4">
                            <label for="newsTitle" class="col-sm-4 control-label">新闻标题：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="newsTitle"
                                       name="newsTitle"
                                       value="${news.newsTitle}">
                            </div>
                        </div>
                        <%-- 状态 --%>
                        <div class="form-group  col-lg-4">
                            <label for="releaseState" class="col-sm-4 control-label">状态：</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="releaseState" name="releaseState">
                                    <option value="" selected>请选择</option>
                                    <option value="0" <c:if test="${news.releaseState eq '0'}">selected</c:if>>
                                        未发布
                                    </option>
                                    <option value="1" <c:if test="${news.releaseState eq '1'}">selected</c:if>>
                                        已发布
                                    </option>
                                </select>
                            </div>
                        </div>
                        <%-- 创建时间--%>
                        <div class="form-group  col-lg-4">
                            <label for="createDate" class="col-sm-4 control-label">创建时间：</label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入时间" path="createDate"
                                       id="createDate"
                                       name="createDate" value="${news.createDate}" readonly="true"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <%-- 发布时间--%>
                        <div class="form-group  col-lg-4">
                            <label for="releaseDate" class="col-sm-4 control-label">发布时间：</label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入时间" path="releaseDate"
                                       id="releaseDate"
                                       name="releaseDate" value="${news.releaseDate}" readonly="true"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-md-offset-4">
                        <button type="submit" class="btn btn-primary">搜索</button>
                        <button type="button" class="btn btn-primary" onclick="toEdit('')" id="toAdd">创建新闻</button>
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
                <th width="8%">序号</th>
                <th width="8%">新闻类型</th>
                <th width="22%">新闻标题</th>
                <th width="9%">创建人</th>
                <th width="9%">状态</th>
                <th width="12%">创建时间</th>
                <th width="12%">发布时间</th>
                <th width="11%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${newsDTOs}" var="news" varStatus="row">
                <tr>
                    <td height="40px" style="text-align: center;"><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>
                    <td height="40px">
                        <c:if test="${news.newsType eq '1'}">图文</c:if>
                        <c:if test="${news.newsType eq '2'}">视频</c:if>
                        <c:if test="${news.newsType eq '3'}">链接</c:if>
                    </td>
                    <td height="40px">${news.newsTitle}</td>
                    <td height="40px">${news.createName}</td>
                    <td height="40px">
                        <c:if test="${news.releaseState eq '0'}">未发布</c:if>
                        <c:if test="${news.releaseState eq '1'}">已发布</c:if>
                    </td>
                    <td height="40px">${news.createDate}</td>
                    <td height="40px">${news.releaseDate}</td>
                    <td height="40px">
                        <a href="../qcNews/toNewsDetails?newsId=${news.newsId}"><spring:message
                                code="problem.detail"/></a>
                        <a href="javascript:void(0);" onclick="toEdit('${news.newsId}')" id="toEdit"
                           class="a3">编辑</a>
                        <a href="javascript:void(0);" onclick="toDelete('${news.newsId}')" id="toDel"
                           class="a3">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/qcNews/getNewsList.html?pageIndex={0}&newsTitle=${news.newsTitle}&releaseState=${news.releaseState}&releaseDate=${news.releaseDate}&createDate=${news.createDate}"/>
    </div>
</div>

</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script src="../../../../static/js/zoom.min.js"></script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">

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

</script>
<script>
    //编辑
    function toEdit(newsId) {
        window.location.href = "../qcNews/toEditNews.html?newsId=" + newsId;
    }

    //删除
    function toDelete(id) {
        if (confirm("确定要删除吗?")) {
            $.ajax({
                type: "GET",
                url: "../qcNews/delNews?newsId=" + id,
                cache: false,
                async: false,
                dataType: "json",
                success: function (data) {
                    if (data.error == 0) {
                        alert("删除成功！");
                    } else {
                        alert("删除失败！");
                    }
                }
            });
            window.location.href = "../qcNews/getNewsList.html";
        } else {
            alert("已取消！");
        }
    }
</script>
</body>
</html>