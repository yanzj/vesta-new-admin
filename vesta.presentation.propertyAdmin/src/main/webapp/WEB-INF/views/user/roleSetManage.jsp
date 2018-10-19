<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/5/10
  Time: 14:22
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
                 crunMenu="001300040000" username="${propertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../user/roleManage.html" method="post">
                    <%--角色名称--%>
                    <div class="form-group  col-lg-6">
                        <label for="appRoleSetName" class="col-sm-3 control-label">角色名称：</label>

                        <div class="col-sm-9">
                            <input type="text" class="form-control" value="${setDto.appRoleSetName}"
                                   placeholder="请输入角色名称" id="appRoleSetName" name="appRoleSetName">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" for="">搜索</button>

                    <a href="../user/gotoRoleSet.html" class="btn btn-primary" for="">新建角色</a>
                    <%--<a  href="/user/userStaffManage.html" class="btn btn-primary" for="" >调整角色顺序</a>--%>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <form action="../role/listRoleRole" method="post" id="search_form">
            <input id="rolesetId" type="hidden" name="rolesetId" value="">
        </form>
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td>角色排序</td>
                <th>角色名称</th>
                <th>修改时间</th>
                <th><spring:message code="staffManage.staffOpteration"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${appRoleSetList}" var="roleList" varStatus="vs">
                <tr>
                    <%--<c:if test="${roleList.appRoleSetName ne 'APP默认甲方权限' && roleList.appRoleSetName ne 'APP默认乙方权限' && roleList.appRoleSetName ne 'APP默认监理权限' && roleList.appRoleSetName ne 'APP默认工程经理' && roleList.appRoleSetName ne 'APP默认领导权限'}">--%>
                        <td><b>${(webPage.pageIndex-1)*20+vs.index+1}</b></td>
                        <td>${roleList.appRoleSetName}</td>
                        <td>${roleList.modifyTime}</td>
                        <td class="last">
                            <a href="../user/gotoRoleSet.html?roleSetId=${roleList.appRoleSetId}" class="a2"><span
                                    class="span1"><spring:message code="common_update"/></span></a>
                            <a style="cursor: pointer"
                               onclick="javascript:if(confirm('确定置为无效吗！'))location.href='../user/delRole.html?appRoleSetId=${roleList.appRoleSetId}';else return"
                               class="a3">置为无效</a>
                            <a href="../user/roleSetDetail.html?appRoleSetId=${roleList.appRoleSetId}" class="a1"><span
                                    class="span1">详情</span></a>
                                <%--<a href="../user/rolePeople.html?appRoleSetId=${roleList.appRoleSetId}" class="a1"><span class="span1">人员分配</span></a>--%>
                        </td>
                    <%--</c:if>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/user/roleManage.html?pageIndex={0}&appRoleSetName=${setDto.appRoleSetName}"/>
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

