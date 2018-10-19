<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    .search_button{
        text-align: center;
    }
    .form_b{
        height: 12.5rem;
    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#006700060000").addClass("active");
        $("#006700060000").parent().parent().addClass("in");
        $("#006700060000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006700060000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="">
                    <input type="hidden" id="menuId" name="menuId" value="006700060000"/>
                    <%-- 姓名 --%>
                    <div class="form-group  col-lg-4">
                        <label for="realName" class="col-sm-4 control-label">姓名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="realName" name="realName" value="${integralRuleQuerry.realName}" disabled="disabled" >
                        </div>
                    </div>
                    <%-- 手机 --%>
                    <div class="form-group  col-lg-4">
                        <label for="mobile" class="col-sm-4 control-label">手机号码</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="mobile" name="mobile" value="${integralRuleQuerry.mobile}" disabled="disabled" >
                        </div>
                    </div>
                    <%-- 项目 --%>
                    <%--<div class="form-group  col-lg-4">
                        <label for="name" class="col-sm-4 control-label">项目</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="name" name="name" value="${integralRuleQuerry.name}" disabled="disabled" >
                        </div>
                    </div>--%>


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
                <th>日期</th>
                <th>模块</th>
                <th>积分</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${list.createOn}</td>
                    <td>${list.modelName}</td>
                    <td>${list.number}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
      </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
