<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <script src="../static/js/bootstrap.min.js"></script>
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
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
    <script src="../static/property/js/propertyConsult.js"></script>
    <style>
        label.error {
            /*position: absolute;*/
            /*margin-top: -74px;*/
            /*display: block;*/
            margin-left: 1%;
            color: red;
        }
        tr,td{
            text-align: right;
        }
    </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020009" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <form class="form-horizontal" action="../property/addAnswer.html" id="ansPraise">

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-2 control-label text-right" for="crtTimeMsg"><spring:message code="praise.crtTime"/></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="crtTimeMsg" value="${Panswer.crtTime}" readonly>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-2 control-label text-right" for="userNameMsg"><spring:message code="praise.yezhu" /></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="userNameMsg" value="${Panswer.userName}" readonly>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-2 control-label text-right" for="moblieMsg"><spring:message code="praise.mobile" /></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" value="${Panswer.mobile}" id="moblieMsg" readonly>
                        </div>
                    </div>


                    <div class="form-group  col-lg-12">
                        <label class="col-sm-2 control-label text-right" for="addressMsg"><spring:message code="propertyConsult.address" /></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="addressMsg" value="${Panswer.address}" readonly>
                        </div>
                    </div>

                    <div class="form-group col-lg-12">
                        <label class="col-sm-2 control-label text-right" for="contentMsg"><spring:message code="praise.substance"/></label>
                        <div class="col-sm-8">
                            <textarea class="form-control" placeholder="" id="contentMsg" readonly>${Panswer.content}</textarea>
                        </div>
                    </div>

                    <div class="form-group col-lg-12">
                        <label class="col-sm-2 control-label text-right" for="content"><spring:message code="propertyConsult.replyContent" /></label>
                        <div class="col-sm-8">
                            <textarea class="form-control" placeholder="" name="content" id="content">${Panswer.answerContent}</textarea>
                        </div>
                        <input type="hidden" name="consultId" value="${Panswer.id}" id="pId">
                    </div>

                    <div class="form-group col-lg-12">
                        <label class="col-sm-2 control-label text-right"><spring:message code="propertyReport.releaseImg" /></label>
                        <div class="col-sm-8">
                            <c:forEach items="${Panswer.imgList}" var="is" varStatus="row">
                                <img src="${is.img}" style="width: 100px; height:120px;;">
                            </c:forEach>
                        </div>
                    </div>

                    <div class="clearfix"></div>
                    <div class="text-center">
                    <a type="button" href="../property/Praise.html" class="btn btn-primary" for="propertySearch" ><spring:message code="common_cancel"/></a>
                    <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="propertyConsult.reply"/></button>
                    </div>
                </form>
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

        $("#ansPraise").validate({
            rules: {
                content: {
                    required: true,
                    minlength: 1,
                    maxlength: 500
                }
            },
            messages: {
                content: {
                    required: "请输入回复内容",
                    minlength: "不能少于1个字符！",
                    maxlength: "请勿超过500个字！"
                }
            }
    });
</script>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>