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
    <script src="../static/property/js/propertyCompany.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../property/PropertyCompany.html">
                    <div class="form-group  col-lg-4">
                        <label for="propertyArea" class="col-sm-4 control-label"><spring:message code="propertyCompany.propertyArea" /></label>

                        <div class="col-sm-8">
                            <select id="propertyArea" name="propertyArea"  class="form-control">
                                <c:forEach items="${propertyCompanyMap.propertyAreaMap}" var="item">
                                    <option value="${item.key }">${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="companyName" class="col-sm-4 control-label"><spring:message code="propertyCompany.companyName"/></label>

                        <div class="col-sm-8">
                            <select id="companyName" name="companyName"  onchange="javascript:dropBox();" class="form-control">
                                <c:forEach items="${propertyCompanyMap.companyNameMap}" var="item">
                                    <option value="${item.key }">${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="propertyProject" class="col-sm-4 control-label"><spring:message code="propertyCompany.propertyProject"/></label>

                        <div class="col-sm-8">
                            <select id="propertyProject" name="propertyProject"  class="form-control">
                                <c:forEach items="${propertyCompanyMap.propertyProjectMap}" var="item">
                                    <option value="${item.key }">${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="projectAdmin" class="col-sm-4 control-label"><spring:message code="propertyCompany.projectAdmin"/></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="projectAdmin" value="${propertyCompanySearch.projectAdmin}" name="projectAdmin">
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="propertyCompany.propertySearch"/></button>

                    <a  href="../property/NewPropertyCompany?companyId=" class="btn btn-primary" for="propertyAdd" ><spring:message code="propertyCompany.propertyAdd"/></a>

                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="propertyCompany.serialNumber" /></td>
                <th><spring:message code="propertyCompany.propertyArea" /></th>
                <th><spring:message code="propertyCompany.companyName" /></th>
                <th><spring:message code="propertyCompany.propertyProject" /></th>
                <th><spring:message code="propertyCompany.addMessageTime" /></th>
                <th><spring:message code="propertyCompany.projectAdmin" /></th>
                <th><spring:message code="propertyCompany.servicesPhone" /></th>
                <th><spring:message code="propertyCompany.servicesType" /></th>
                <th width="174"><spring:message code="propertyCompany.operation" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${propertyCompanyMessage}" var="compay" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${compay.propertyArea}</td>
                    <td>${compay.companyName}</td>
                    <td>${compay.propertyProject}</td>
                    <td>${compay.propertyMessageTime}</td>
                        <%-- <td><span class="span1">待分配</span></td>--%>
                    <td>${compay.projectAdmin}</td>
                    <td>${compay.phnoe}</td>
                    <td>${compay.propertyType}</td>
                    <td class="last">
                        <a href="../property/NewPropertyCompany?companyId=${compay.companyId}" class="a1">
                            <span class="span1"><spring:message code="propertyCompany.propertySee" /></span>
                        </a>
                        <c:if test="${compay.propertyType == '启用'}">
                            <a href="javascript:void(0);" onclick="js_delORupdateMessage('${compay.companyId}','0')" class="a2">
                                <span class="span1"><spring:message code="propertyCompany.propertyDistribute" /></span>
                            </a>
                        </c:if>
                        <c:if test="${compay.propertyType == '禁用'}">
                            <a href="javascript:void(0);" onclick="js_delORupdateMessage('${compay.companyId}','1')" class="a2">
                                <span class="span1"><spring:message code="propertyCompany.propertyEnable" /></span>
                            </a>
                        </c:if>
                        <a href="javascript:void(0);" onclick="js_delORupdateMessage('${compay.companyId}','2')" class="a3">
                            <span class="span1"><spring:message code="propertyCompany.propertyDelete" /></span>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/property/PropertyCompany.html?pageIndex={0}&propertyArea=${propertyCompanySearch.propertyArea}&companyName=${propertyCompanySearch.companyName}&propertyProject=${propertyCompanySearch.propertyProject}&projectAdmin${propertyCompanySearch.projectAdmin}"/>
    </div>


</div>
</div>
</div>
</div>
<script type="text/javascript">
    var companyNameSel = "${propertyCompanySearch.companyName}";
    var propertyProjectSel="${propertyCompanySearch.propertyProject}";
    var propertyAreaSel = "${propertyCompanySearch.propertyArea}";
    var projectAdminSel = "${propertyCompanySearch.projectAdmin}";
    var pages = "${webPage.pageIndex}";
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>