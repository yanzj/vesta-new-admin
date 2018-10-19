<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/2/25
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
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
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
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
    <style>
        label.error {
            margin-left: 1%;
            color: red;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            if($("#flag").val()=="true"){
                alert("保存成功！")
            }
        })

        $(function() {
            $("#addCompanyTel").validate({
                rules: {
                    companyTel:{
                        required:true,
                        minlength:1,
                        maxlength:13
                    }
                },
                messages: {
                    companyTel: {
                        required: "请输入电话号码！",
                        minlength: "不能少于1个字符！",
                        maxlength: "请勿超过13个字！"
                    }
                }
            })
        })

    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000400030000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <form class="form-horizontal" action="../rental/altCompanyTel.html" id="addCompanyTel">

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-5 control-label"><spring:message code="rental.viewUserMobile"/></label>

                        <div class="col-sm-7">
                            <input type="radio" id="viewTel" name="viewTel" <c:if test="${rental.viewTel==true}">checked="checked" </c:if> value="true"><spring:message code="common_yes" />&nbsp;
                            <input type="radio" name="viewTel" <c:if test="${rental.viewTel==false}">checked="checked" </c:if> value="false"><spring:message code="common_no" />
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-5 control-label"><spring:message code="rental.compayTel"/></label>

                        <div class="col-sm-3">
                            <input type="text" class="form-control" placeholder="" value="${rental.companyTel}" id="companyTel" name="companyTel">
                        </div>
                    </div>

                        <div style="text-align: center">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message code="common_save"/></button>
                        <input type="hidden" id="flag" value="${result}">
                        <input type="hidden" name = "id" value="${rental.id}">
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
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
