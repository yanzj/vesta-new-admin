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
        })
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
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" method="post" enctype="multipart/form-data">
                    <div class="form-group col-lg-7">
                        <label class="col-sm-2 control-label">知识库类型：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_knowledgeType">${qcKnowledgeDTO.knowledgeType}</span>
                        </div>
                    </div>
                    <div class="form-group col-lg-7" style="margin-top: 7px">
                        <label class="col-sm-2 control-label">内容类型：</label>
                        <div class="col-sm-5">
                            <span id="span_contentType">${qcKnowledgeDTO.contentType}</span>
                        </div>
                    </div>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-2 control-label">标题：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_title">${qcKnowledgeDTO.title}</span>
                        </div>
                    </div>
                    <div id="div_videoUrl" class="form-group col-lg-7">
                        <label class="col-sm-2 control-label">视频链接：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_videoUrl"><a href="${qcKnowledgeDTO.videoUrl}" target="_blank">${qcKnowledgeDTO.videoUrl}</a></span>
                        </div>
                    </div>
                    <div id="div_videoUrl_out" class="form-group col-lg-7">
                        <label class="col-sm-2 control-label">视频外链：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_videoUrl_out"><a href="${qcKnowledgeDTO.outVideoUrl}" target="_blank">${qcKnowledgeDTO.outVideoUrl}</a></span>
                        </div>
                    </div>
                    <div id="div_content" class="form-group col-lg-7">
                        <label class="col-sm-2 control-label">内容：</label>
                        <div class="col-sm-9" style="margin-top: 7px">
                            <span id="span_content">${qcKnowledgeDTO.content}</span>
                        </div>
                    </div>
                    <div id="div_fileUrl" class="form-group col-lg-7">
                        <label class="col-sm-2 control-label">文件预览：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_fileUrl"><a href="${qcKnowledgeDTO.fileUrl}" target="_blank">${qcKnowledgeDTO.fileName}</a></span>
                        </div>
                    </div>
                    <div id="div_fileSize" class="form-group col-lg-7">
                        <label class="col-sm-2 control-label">文件大小：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_fileSize">${qcKnowledgeDTO.fileSize}</span>
                        </div>
                    </div>
                    <div id="div_visits" class="form-group col-lg-7">
                        <label class="col-sm-2 control-label">阅读量：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_visits">${qcKnowledgeDTO.visits}</span>
                        </div>
                    </div>
                </form>
                <div id="div_button" class="form-group col-lg-7">
                    <div class="col-sm-4 rightButton" style="text-align: center">
                        <button class="btn btn-primary" onclick="javaScript:history.go(-1)">确认</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    window.onload=function(){
        var contentType = '${qcKnowledgeDTO.contentType}';
        if(contentType == null || contentType == '') {
            $("#div_knowledgeType").hide();
            $("#div_contentType").hide();
            $("#div_title").hide();
            $("#div_content").hide();
            $("#div_fileUrl").hide();
            $("#div_fileSize").hide();
            $("#div_videoUrl").hide();
            $("#div_visits").hide();
            $("#div_videoUrl_out").hide();
        }else{
            if(contentType == '图文'){
                $("#div_knowledgeType").show();
                $("#div_contentType").show();
                $("#div_title").show();
                $("#div_content").show();
                $("#div_visits").show();
                $("#div_fileUrl").hide();
                $("#div_fileSize").hide();
                $("#div_videoUrl").hide();
                $("#div_error").hide();
                $("#div_videoUrl_out").hide();
            }else if(contentType == 'pdf'){
                $("#div_knowledgeType").show();
                $("#div_contentType").show();
                $("#div_title").show();
                $("#div_fileUrl").show();
                $("#div_fileSize").show();
                $("#div_visits").show();
                $("#div_content").hide();
                $("#div_videoUrl").hide();
                $("#div_error").hide();
                $("#div_videoUrl_out").hide();
            }else if(contentType == '视频'){
                $("#div_knowledgeType").show();
                $("#div_contentType").show();
                $("#div_title").show();
                $("#div_videoUrl").show();
                $("#div_content").show();
                $("#div_visits").show();
                $("#div_fileUrl").hide();
                $("#div_fileSize").hide();
                $("#div_error").hide();
                $("#div_videoUrl_out").hide();
            }else if(contentType == '视频-外链'){
                $("#div_knowledgeType").show();
                $("#div_contentType").show();
                $("#div_title").show();
                $("#div_videoUrl").hide();
                $("#div_content").hide();
                $("#div_visits").show();
                $("#div_fileUrl").hide();
                $("#div_fileSize").hide();
                $("#div_error").hide();
                $("#div_videoUrl_out").show();
            }else{
                $("#div_knowledgeType").hide();
                $("#div_contentType").hide();
                $("#div_title").hide();
                $("#div_content").hide();
                $("#div_visits").hide();
                $("#div_fileSize").hide();
                $("#div_fileUrl").hide();
                $("#div_videoUrl").hide();
                $("#div_videoUrl_out").show();
            }
        }
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