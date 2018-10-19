<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    .search_button {
        text-align: center;
    }

    .form_b {
        height: 5.5rem;
    }
</style>
<script>
    $(function () {
        console.log("sqq")
        $("#006500010000").addClass("active");
        $("#006500010000").parent().parent().addClass("in");
        $("#006500010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006500010000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../news/getNewsList.html">
                    <%-- 新闻标题搜索 --%>
                    <div class="form-group  col-lg-12">
                        <label for="newsTitle" class="col-sm-2 control-label">新闻搜索</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" placeholder="" id="newsTitle" name="newsTitle"
                                   value="${newsDTO.newsTitle}">
                        </div>
                        <div class="col-sm-5">
                            <c:if test="${function.sth40020053 eq 'Y'}">
                                <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                        code="propertyCompany.propertySearch"/></button>
                            </c:if>
                            <c:if test="${function.sth40020054 eq 'Y'}">
                                <button type="button" class="btn btn-primary" onclick="toEdit('')" id="toAdd">新增</button>
                            </c:if>
                        </div>
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
                <th>新闻标题</th>
                <th>新闻来源</th>
                <th>发布时间</th>
                <th>发布人</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${newsList}" var="news" varStatus="row">
                <tr>
                    <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
                    <td>${news.newsTitle}</td>
                    <td>${news.newsSource}</td>
                    <td><fmt:formatDate type="time" value="${news.createDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
                    <td>${news.createName}</td>
                    <td class="last">
                        <c:if test="${function.sth40020055 eq 'Y'}">
                            <a href="javascript:void(0);" onclick="toTop('${news.newsId}','${news.slideShow}')" id="toTop"
                               class="a3">
                                <c:if test="${news.slideShow eq '0'}">置顶
                                </c:if>
                                <c:if test="${news.slideShow eq '1'}">取消置顶
                                </c:if></a>
                        </c:if>
                        <c:if test="${function.sth40020056 eq 'Y'}">
                            <a href="javascript:void(0);" onclick="toEdit('${news.newsId}')" id="toEdit" class="a3">编辑</a>
                        </c:if>
                        <c:if test="${function.sth40020057 eq 'Y'}">
                            <a href="javascript:void(0);" onclick="toDelete('${news.newsId}')" id="toDelete"
                               class="a3">删除</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/news/getNewsList.html?pageIndex={0}&newsTitle=${newsDTO.newsTitle}"/>
    </div>

</div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
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
    $(".form_YM").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm',
        autoclose: true,
        todayBtn: true,
        startView: 'year',
        minView: 'year',
        maxView: 'decade'
    });
</script>
<script>
    //新增与修改
    function toEdit(id) {
        window.location.href = "../news/toEditNews.html?newsId=" + id;
    }
    //置顶
    function toTop(id, slideShow) {
        if (slideShow == '0') {
            if (confirm("最多只能置顶4条新闻，确定要置顶此条新闻么？")) {
                $.ajax({
                    type: "GET",
                    url: "../news/toTopNews?newsId=" + id + "&slideShow=" + slideShow,
                    cache: false,
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        if (data.error == 0) {
                            alert("置顶成功！");
                        } else {
                            alert("置顶失败！最多只能置顶4条新闻");
                        }
                    }
                });
                window.location.href = "../news/getNewsList.html";
            } else {
                alert("已取消");
            }
        } else if (slideShow == '1') {
            if (confirm("确定要取消置顶此条新闻么？")) {
                $.ajax({
                    type: "GET",
                    url: "../news/toTopNews?newsId=" + id + "&slideShow=" + slideShow,
                    cache: false,
                    async: false,
                    dataType: "json",
                    success: function () {
                        alert("操作成功！");
                    }
                });
                window.location.href = "../news/getNewsList.html";
            }

        }
    }
    //删除
    function toDelete(id) {
        if (confirm("确定要删除吗?")) {
            $.ajax({
                type: "GET",
                url: "../news/toDeleteNews?newsId=" + id,
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
            window.location.href = "../news/getNewsList.html";
        } else {
            alert("已取消！");
        }

    }


</script>
</body>
</html>