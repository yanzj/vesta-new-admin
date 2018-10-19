<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/24
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="000200050000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <form class="form-horizontal" action="../system/versionManage.html" method="get">
                    <div class="pull-right text-center">
                        <a href="javascript:void(0);" onclick="js_gotoAdd()" class="btn btn-primary"
                           for="rolesetAdd">增加导航菜单</a>
                    </div>
                    <div class="clearfix"></div>
                </form>
            </div>
        </div>
        <div class="table-responsive bs-example widget-shadow">
            <table width="100%" class="tableStyle">
                <thead>
                <tr>
                    <td><spring:message code="AppVersion_serialNumber"/></td>
                    <th>菜单名称</th>
                    <th>菜单url</th>
                    <th>图片地址</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${navigationMenuDTOs}" var="navigation" varStatus="vs">
                    <tr>
                        <td><b>${vs.count}</b></td>
                        <td>${navigation.navigationName}</td>
                        <td>${navigation.navigationUrl}</td>
                        <td>${navigation.imgUrl}</td>
                        <td>
                            <a href="javascript:void(0);" onclick="js_gotoEdit('${navigation.navigationId}')"
                               class="a2"><span class="span1"><spring:message code="SECTION_UPDATE"/></span></a>
                            <a href="javascript:void(0);" onclick="js_del('${navigation.navigationId}')"
                               class="a3"><span class="span1"><spring:message code="AppVersion_del"/></span></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">

    function js_gotoAdd() {
        window.location.href = "../navigationMenu/gotoAddNavigationMenu";
    }

    function js_gotoEdit(navigationId) {
        window.location.href = "../navigationMenu/gotoUpdateNavigationMenu?navigationId=" + navigationId;
    }

    function js_del(navigationId) {
        myvalue = confirm("确定要删除吗?");
        if (myvalue == true) {
            window.location.href = "../navigationMenu/navigationMenuDel?navigationId=" + navigationId;
        }
    }

</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
</body>
</html>
