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
        $(function () {
            $("#001300050000").addClass("active");
            $("#001300050000").parent().parent().addClass("in");
            $("#001300050000").parent().parent().parent().parent().addClass("active");
        })
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
                 crunMenu="001300050000" username="${propertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../role/AuthorityManage.html">
                    <div class="form-group  col-lg-6">
                        <label for="roledesc" class="col-sm-3 control-label">角色名称：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" value="${setDto.roledesc}" placeholder="请输入角色名称" id="roledesc" name="roledesc">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" for=""><spring:message code="common_select"/></button>

                    <a href="../role/gotoUpdateRole" class="btn btn-primary" for="rolesetAdd"><spring:message
                            code="authotity.rolesetAdd"/></a>

                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <form action="#" method="post" id="search_form">

            <input id="rolesetId" type="hidden" name="rolesetId" value="">
        </form>
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="authotity.rolesetId"/></td>
                <th><spring:message code="authotity.rolesetName"/></th>
                <%--<th><spring:message code="authotity.rolesetIsallot" /></th>--%>
                <th><spring:message code="authotity.rolesetDateTime"/></th>
                <th><spring:message code="authotity.rolesetOperation"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${roleRolesetDTOList}" var="roleSet" varStatus="vs">
                <tr>
                    <%--<c:if test="${roleSet.roledesc ne '后端默认甲方权限' && roleSet.roledesc ne '默认工程经理权限' && roleSet.roledesc ne '默认系统管理员' && roleSet.roledesc ne '后台默认领导权限'}">--%>
                        <td><b>${(webPage.pageIndex-1)*20+vs.index+1}</b></td>
                        <td>${roleSet.roledesc}</td>
                        <%--<td>--%>
                        <%--<c:if test="${roleSet.isallot eq '1'}"><spring:message code="common_yes" /></c:if>--%>
                        <%--<c:if test="${roleSet.isallot eq '0'}"><spring:message code="common_no" /></c:if>--%>
                        <%--</td>--%>
                        <td>${roleSet.makeDate}&nbsp;&nbsp;${roleSet.makeTime}</td>
                        <td class="last">
                            <a href="../role/gotoUpdateRole?rolesetId=${roleSet.setId}" class="a2"><span
                                    class="span1">修改</span></a>
                            <a href="../role/listRoleRole.html?rolesetId=${roleSet.setId}"><span class="span1">详情</span></a>
                            <a style="cursor: pointer"
                               onclick="javascript:if(confirm('确定置为无效吗！'))location.href='../role/DelRoleRoleset?rolesetId=${roleSet.setId}';else return"
                               class="a3"><span class="span1">置为无效</span></a>
                        </td>
                    <%--</c:if>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/role/AuthorityManage.html?pageIndex={0}&roledesc=${setDto.roledesc}"/>
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