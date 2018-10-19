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
            $("#addOrganize").validate({
                rules: {
                    organizeName: {
                        required: true,
                        minlength: 1,
                        maxlength: 20
                    }
                },
                messages: {
                    organizeName: {
                        required: "请输入组名称！",
                        minlength: "不能少于1个字符！",
                        maxlength: "请勿超过20个字！"
                    }
                }
            })
        });
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001300030000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" name="addOrganize" id="addOrganize" action="../organize/updateOrganize.html">
                    <input type="hidden" name="organizeId" value="${organize.organizeId}">
                    <div class="form-group  col-lg-4">
                        <label for="organizeName" class="col-sm-4 control-label">组名称：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" value="${organize.organizeName}" id="organizeName" name="organizeName">
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="memo" class="col-sm-4 control-label">备注：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" value="${organize.memo}" id="memo" name="memo">
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="crmId" class="col-sm-4 control-label">组CRM ID:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" value="${organize.crmId}" id="crmId" name="crmId">
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="crmName" class="col-sm-4 control-label">组CRM名：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" value="${organize.crmName}" id="crmName" name="crmName">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" for="propertySearch" >保存</button>
                    <a href="../organize/organizeList.html" type="button" class="btn btn-primary">返回</a>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
</div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>

</html>