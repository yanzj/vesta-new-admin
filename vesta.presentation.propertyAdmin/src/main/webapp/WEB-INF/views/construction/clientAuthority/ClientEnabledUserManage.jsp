<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2017/12/17
  Time: 21:10
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
    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
</head>
<style>
    .forms{
        overflow: hidden;
    }
</style>
<script>
    $(function () {
        console.log("sqq")
        $("#007300010000").addClass("active");
        $("#007300010000").parent().parent().addClass("in");
        $("#007300010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007300010000" username="${authPropertystaff.staffName}"/>
    <div style="text-align: right; margin-top: 8px; margin-right: 25px;">
        <c:if test="${function.qch40010093 eq 'Y'}">
            <a href="../authorityClient/initClientUserManage.html" class="btn btn-primary">内部用户管理</a>
        </c:if>
        <c:if test="${function.qch40010100 eq 'Y'}">
        <a href="../authorityClient/outerClientUserManage.html" class="btn btn-primary">外部用户管理</a>
        </c:if>
        <a href="../authorityClient/getClientEnabledUser.html" class="btn btn-primary">系统用户管理</a>
    </div>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" name="searchByName" id="searchByName"
                      action="../authorityClient/getClientEnabledUser.html">
                    <%--所属机构--%>
                    <div class="form-group  col-lg-4">
                        <label for="agencyNameE" class="col-sm-4 control-label">所属机构</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="agencyNameE"
                                   name="agencyNameE" value="${enabledUserDTO.agencyNameE}">
                        </div>
                    </div>
                    <%--人员姓名--%>
                    <div class="form-group  col-lg-4">
                        <label for="staffNameE" class="col-sm-4 control-label">人员</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="staffNameE"
                                   name="staffNameE" value="${enabledUserDTO.staffNameE}">
                        </div>
                    </div>
                    <%--用户名--%>
                    <div class="form-group  col-lg-4">
                        <label for="userNameE" class="col-sm-4 control-label">账号</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="userNameE"
                                   name="userNameE" value="${enabledUserDTO.userNameE}">
                        </div>
                    </div>
                    <%--用户来源--%>
                    <div class="form-group  col-lg-4">
                        <label for="sourceFromE" class="col-sm-4 control-label">用户来源</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="sourceFromE" name="sourceFromE">
                                <option value="" <c:if test="${enabledUserDTO.sourceFromE eq ''}">selected</c:if>>全部</option>
                                <option value="OA" <c:if test="${enabledUserDTO.sourceFromE eq 'OA'}">selected</c:if>>OA</option>
                                <option value="external" <c:if test="${enabledUserDTO.sourceFromE eq 'external'}">selected</c:if>>外部</option>
                            </select>
                        </div>
                    </div>
                    <%--电话--%>
                    <div class="form-group  col-lg-4">
                        <label for="mobileE" class="col-sm-4 control-label">电话</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="mobileE"
                                   name="mobileE" value="${enabledUserDTO.mobileE}">
                        </div>
                    </div>
                    <%--邮箱--%>
                    <div class="form-group  col-lg-4">
                        <label for="emailE" class="col-sm-4 control-label">邮箱</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="emailE"
                                   name="emailE" value="${enabledUserDTO.emailE}">
                        </div>
                    </div>
                    <!--集合长度(取决Excel是否可以导出)-->
                    <input type="hidden" id="size" value="${isExcel}">
                    <div style="text-align: center; margin-top: 8px;">
                        <c:if test="${function.qch40010111 eq 'Y'}">
                            <button type="submit" id="sbm" class="btn btn-primary" for="propertySearch">查询</button>
                        </c:if>
                        <c:if test="${function.qch40010112 eq 'Y'}">
                            <a href="#" onclick="isExcel()"  value="" class="btn btn-primary">导出Excel</a>
                        </c:if>
                    </div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>

    <div class="table-responsive bs-example widget-shadow">
        <div class="row">
            <div class="table-responsive bs-example widget-shadow">
                <table width="100%" class="tableStyle">
                    <thead>
                    <!-- 点击部门 显示人员的列表 -->
                    <tr>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>OA账号</th>
                        <th>系统账号</th>
                        <th>用户来源</th>
                        <th>联系方式</th>
                        <th>邮箱地址</th>
                        <th>所属机构</th>
                        <th>最后修改时间</th>
                    </tr>
                    </thead>
                    <tbody class="publicTbody">
                    <c:forEach items="${enabledUserDTOS}" var="list" varStatus="as">
                        <tr>
                            <td height="40px" style="text-align: center">
                                <b>${(webPage.pageIndex-1)*20+as.index + 1}</b>
                            </td>
                            <td>${list.staffNameE}</td>
                            <td>${list.userNameE}</td>
                            <td>${list.sysNameE}</td>
                            <td>
                                <c:if test="${list.sourceFromE eq 'external'}">外部添加</c:if>
                                <c:if test="${list.sourceFromE eq 'OA'}">OA同步</c:if>
                            </td>
                            <td>${list.mobileE}</td>
                            <td>${list.emailE}</td>
                            <td>${list.agencyNameE}</td>
                            <td>${list.modifyOnE}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <m:pager pageIndex="${webPage.pageIndex}"
                         pageSize="${webPage.pageSize}"
                         recordCount="${webPage.recordCount}"
                         submitUrl="${pageContext.request.contextPath }../authorityClient/getClientEnabledUser.html?pageIndex={0}&agencyNameE=${enabledUserDTO.agencyNameE}&staffNameE=${enabledUserDTO.staffNameE}&userNameE=${enabledUserDTO.userNameE}&sourceFromE=${enabledUserDTO.sourceFromE}&mobileE=${enabledUserDTO.mobileE}&emailE=${enabledUserDTO.emailE}"/>
            </div>
        </div>
    </div>
</div>
</div>

<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
    function isExcel(){
        var size = $("#size").val();
        if(size>0){
            var href = "../authorityClient/enabledUserExportExcel?agencyNameE=${enabledUserDTO.agencyNameE}&staffNameE=${enabledUserDTO.staffNameE}&userNameE=${enabledUserDTO.userNameE}&sourceFromE=${enabledUserDTO.sourceFromE}&mobileE=${enabledUserDTO.mobileE}&emailE=${enabledUserDTO.emailE}";
            window.location.href = href;
        }else{
            alert("没有可以导出的数据");
        }
    }
</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>

</body>
</html>
