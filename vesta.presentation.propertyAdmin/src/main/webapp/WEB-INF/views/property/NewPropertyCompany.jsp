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
            <div class="form-body">
                <h3 class="text-center"><spring:message code="propertyCompany.addProject"/></h3>
                <div class="form-body">
                    <form class="form-horizontal" action="../property/addPropertyCompany.html" id="propertyCompany">
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="propertyArea"><spring:message code="propertyCompany.propertyArea" /></label>
                            <div class="col-sm-4">
                                <select id="propertyArea" name="propertyArea"  class="form-control">
                                    <c:forEach items="${propertyCompanyMap.propertyAreaMap}" var="item">
                                        <option value="${item.key }" <c:if test="${item.key eq propertyCompany.propertyArea}">selected="selected"</c:if> >${item.value }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="control-label" style="color: red;" id="propertyAreaMsg"></label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="companyName"><spring:message code="propertyCompany.companyName" /></label>
                            <div class="col-sm-4">
                                <select id="companyName" name="companyName" onchange="javascript:dropBox();"  class="form-control">
                                    <c:forEach items="${propertyCompanyMap.companyNameMap}" var="item">
                                        <option value="${item.key }"  <c:if test="${item.key eq propertyCompany.companyName}">selected="selected"</c:if> >${item.value }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="control-label" style="color: red;" id="companyNameMsg"></label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="propertyProject"><spring:message code="propertyCompany.propertyProject" /></label>
                            <div class="col-sm-4">
                                <select id="propertyProject" name="propertyProject"  class="form-control">
                                    <c:forEach items="${propertyCompanyMap.propertyProjectMap}" var="item">
                                        <option value="${item.key }" <c:if test="${item.key eq propertyCompany.propertyProject}">selected="selected"</c:if> >${item.value }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="control-label" style="color: red;" id="propertyProjectMsg"></label>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="projectAdmin"><spring:message code="propertyCompany.projectAdmin" /></label>
                            <div class="col-sm-4">
                                 <input type="hidden" id="userNameMsg"/>
                                 <input type="text" class="form-control" placeholder="" value="${propertyCompany.projectAdmin}" onblur="js_admin();" id="projectAdmin" name="projectAdmin">
                            </div>
                            <label class="control-label" style="color: red;" id="adminMsg"></label>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="roleId"><spring:message code="staffManage.staffRoleSet" /></label>
                            <div class="col-sm-4">
                                <select id="roleId" name="roleId"  class="form-control">
                                    <c:forEach var="roleSet" items="${roleRolesetDTOs}">
                                        <option value="${roleSet.setId}" <c:if test="${roleSet.setId eq propertyCompany.roleId}">selected="selected"</c:if>>${roleSet.roledesc}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="name"><spring:message code="ownerManage.Name" /></label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" placeholder="" value="${propertyCompany.name}" id="name" name="name" readonly>
                            </div>
                            <label class="control-label" style="color: red;" id="nameMsg"></label>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="projectAdmin"><spring:message code="ownerManage.Mobile" /></label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" placeholder="" value="${propertyCompany.phnoe}" id="phnoe" name="phnoe" readonly>
                            </div>
                            <label class="control-label" style="color: red;" id="phnoeMsg"></label>
                        </div>
                        <input type="hidden" value="${propertyCompany.companyId}" id="companyId" name="companyId" />
                        <div class="form-group">
                            <div class="col-sm-offset-6">
                                <input type="button" onclick="js_openProjectCompany ();" class="btn btn-primary" value="<spring:message code="propertyCompany.propertyOpen" />"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </div>
    </div>

</div>
</div>
</div>
</div>
<script type="text/javascript">
    var companyNameSel = "${propertyCompany.companyName}";
    var propertyProjectSel="${propertyCompany.propertyProject}";
    var propertyAreaSel = "${propertyCompany.propertyArea}";
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>