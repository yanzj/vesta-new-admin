<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            $("#007700010000").addClass("active");
            $("#007700010000").parent().parent().addClass("in");
            $("#007700010000").parent().parent().parent().parent().addClass("active");
        });
        new WOW().init();
    </script>
    <!--下拉框可模糊筛选-->
    <link href="../static/css/chosen.css" rel='stylesheet' type='text/css'/>
    <script src="../static/js/chosen.jquery.min.js"></script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <style type="text/css">#dialog{display:none;}</style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007700010000" username="${authPropertystaff.staffName}" />
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" id="search" name="search" action="../qcKnowledge/getKnowledgeList.html" method="get" style="overflow: hidden;">
                    <div class="col-lg-12">
                        <div class="form-group  col-lg-4">
                            <label for="contentType" class="col-sm-4 control-label">内容类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="contentType" name="contentType">
                                    <option value="" <c:if test="${'' eq qcKnowledgeDTO.contentType}">selected</c:if>>请选择</option>
                                    <option value="1" <c:if test="${1 eq qcKnowledgeDTO.contentType}">selected</c:if>>图文</option>
                                    <option value="2" <c:if test="${2 eq qcKnowledgeDTO.contentType}">selected</c:if>>pdf</option>
                                    <option value="3" <c:if test="${3 eq qcKnowledgeDTO.contentType}">selected</c:if>>视频</option>
                                    <option value="4" <c:if test="${4 eq qcKnowledgeDTO.contentType}">selected</c:if>>视频-外链</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group  col-lg-4">
                            <label for="knowledgeType" class="col-sm-4 control-label">知识库类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="knowledgeType" name="knowledgeType">
                                    <option value="" <c:if test="${empty qcKnowledgeDTO.knowledgeType}">selected</c:if>>请选择</option>
                                    <option value="工作方法" <c:if test="${'工作方法' eq qcKnowledgeDTO.knowledgeType}">selected</c:if>>工作方法</option>
                                    <option value="工作成果" <c:if test="${'工作成果' eq qcKnowledgeDTO.knowledgeType}">selected</c:if>>工作成果</option>
                                    <option value="sb" <c:if test="${'工作方法' ne qcKnowledgeDTO.knowledgeType && '工作成果' ne qcKnowledgeDTO.knowledgeType && not empty qcKnowledgeDTO.knowledgeType}">selected</c:if>>其他</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group  col-lg-4">
                            <label for="title" class="col-sm-4 control-label">标题：</label>
                            <div class="col-sm-8">
                                <input class = "form-control" type="text" value="${qcKnowledgeDTO.title}" name="title" id="title">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="form-group  col-lg-4">
                            <label for="userName" class="col-sm-4 control-label">创建人：</label>
                            <div class="col-sm-8">
                                <input class = "form-control" type="text" value="${qcKnowledgeDTO.userName}" name="userName" id="userName">
                            </div>
                        </div>
                        <div class="form-group  col-lg-4">
                            <label for="createDate" class="col-sm-4 control-label">创建时间：</label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入创建时间" id="createDate" path="createDate" name="createDate" value="${qcKnowledgeDTO.createDate}" readonly="true" />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-md-offset-4">
                        <c:if test="${function.qch40010141 eq 'Y'}">
                            <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="problem.search"/></button>
                        </c:if>
                        <c:if test="${function.qch40010142 eq 'Y'}">
                            <a href="#" onclick="editKnowledge()"  value="" class="btn btn-primary" style="color:#fff">创建知识库</a>
                        </c:if>
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
                <th>内容类型</th>
                <th>知识库类型</th>
                <th>标题</th>
                <th>创建人</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="knowledge" varStatus="row">
                <tr>
                    <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
                    <td>${knowledge.contentType}</td>
                    <td>${knowledge.knowledgeType}</td>
                    <td>${knowledge.title}</td>
                    <td>${knowledge.userName}</td>
                    <td><fmt:formatDate type="time" value="${knowledge.createDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>
                        <c:if test="${function.qch40010143 eq 'Y'}">
                            <a href="javascript:void(0);" data-toggle="modal" onclick="getDetail('${knowledge.id}')" data-target="#myModal"  for="">
                                <spring:message code="problem.detail" />
                            </a>
                        </c:if>
                        <c:if test="${knowledge.userId eq userId}">
                            <a href="javascript:void(0);" data-toggle="modal" onclick="editKnowledge('${knowledge.id}')" data-target="#myModal"  for="">
                                编辑
                            </a>
                        </c:if>
                        <c:if test="${knowledge.userId eq userId}">
                            <a href="javascript:void(0);" data-toggle="modal" onclick="delKnowledge('${knowledge.id}')" data-target="#myModal"  for="">
                                删除
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/qcKnowledge/getKnowledgeList.html?pageIndex={0}&contentType=${qcKnowledgeDTO.contentType}&knowledgeType=${qcKnowledgeDTO.knowledgeType}&title=${qcKnowledgeDTO.title}&userName=${qcKnowledgeDTO.userName}&createDate=${qcKnowledgeDTO.createDate}"/>
        <%----%>
    </div>
</div>
</div>
</div>
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
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    function editKnowledge(param){
        if(param != null && param != ''){
            var href = "../qcKnowledge/editKnowledge.html?id="+param;
        }else {
            var href = "../qcKnowledge/editKnowledge.html";
        }
        window.location.href = href;
    }
    function delKnowledge(param){
        if(confirm("确认删除吗")){
            alert("确认");
        } else{
            alert("取消")
            return;
        }
        var href = "../qcKnowledge/delKnowledge.html?id="+param;
        window.location.href = href;
    }

    function getDetail(param) {
        var href = "../qcKnowledge/detailKnowledge.html?id="+param;
        window.location.href = href;
    }
</script>
<style>
    /*.feedBackImg{
        width: 100px;
    }*/
    .knowledgeDetail{
        font-size: 15px;
        margin-left: 10px;
    }
    .search-form {
        border-radius: inherit;
        height: 30px;
        padding: 0 10px;
    }
</style>
</body>

</html>