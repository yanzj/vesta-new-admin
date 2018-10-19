<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/5/10
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
</head>
<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001300040000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
                <div class="form-body">
                    <form class="form-horizontal" action="../user/saveRoleMenu.html" method="post">
                        <c:forEach var="list" items="${roleMenuList}" varStatus="row">
                            <div class="form-group">
                                <%--<input type="hidden" name="appRoleId" value="${list.appRoleId}">--%>
                            <h3>${list.appRoleName}</h3>
                                <c:forEach var="item" items="${list.appMenuDTOList}">
                                    <input type="checkbox" value="${list.appRoleId},${item.menuId}" name="appRoleId" <c:forEach var="compar" items="${roleList}"><c:if test="${fn:substring(compar.appRoleId,0 ,fn:length(list.appRoleId) ) eq list.appRoleId && fn:substring(compar.appRoleId, fn:length(list.appRoleId),fn:length(item.menuId)+fn:length(list.appRoleId) ) eq item.menuId}">checked="checked" </c:if></c:forEach> />
                                    <label>${item.menuName}</label>
                                    &nbsp;
                                </c:forEach>
                                </div>
                        </c:forEach>

                        <input type="hidden" value="${appRoleSetId}" name="appRoleSetId">
                        <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_confirm"/></button>
                        <a  href="" onclick="javaScript:history.go(-1)" class="btn btn-primary" for="rolesetAdd" ><spring:message code="common_cancel"/></a>
                    </form>
                </div>
            <%--搜索条件结束--%>

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
