<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/2/25
  Time: 10:51
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

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005400020000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <form class="form-horizontal" action="">

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label"><spring:message code="rental.houseAddress" /></label>

                        <div class="col-sm-9">
                            <label class="control-label">${rental.address}</label>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label"><spring:message code="rental.houseType"/></label>

                        <div class="col-sm-9">
                            <label class="control-label">${rental.houseType}</label>
                        </div>
                    </div>


                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label"><spring:message code="rental.houseArea"/></label>

                        <div class="col-sm-9">
                            <label class="control-label">${rental.area}</label>
                        </div>


                    </div>
                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label"><spring:message code="rental.wantMoney"/></label>

                        <div class="col-sm-9">
                            <c:choose>
                                <c:when test="${rental.maxMoney==''}">
                                    <label class="control-label">${rental.minMoney}</label>
                                </c:when>
                                <c:otherwise>
                                    <label class="control-label">${rental.minMoney}-${rental.maxMoney}</label>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label"><spring:message code="rental.talkPrice"/></label>

                        <div class="col-sm-9">
                            <c:choose>
                            <c:when test="${rental.talkPrice}">
                                <label class="control-label">是</label>
                            </c:when>
                            <c:otherwise>
                                <label class="control-label">否</label>
                            </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label"><spring:message code="rental.ower"/></label>

                        <div class="col-sm-9">
                            <label class="control-label">${rental.userName}</label>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label"><spring:message code="rental.mobilephone"/></label>

                        <div class="col-sm-9">
                            <label class="control-label">${rental.mobile}</label>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label"><spring:message code="rental.facilities"/></label>

                        <div class="col-sm-9">
                            <label class="control-label">
                            <c:forEach items="${rental.facilitiesList}" var="list">
                                <c:if test="${list.name==1}"><label>双人床</label></c:if>
                                <c:if test="${list.name==2}"><label>沙发</label></c:if>
                                <c:if test="${list.name==3}"><label>家具</label></c:if>
                                <c:if test="${list.name==4}"><label>暖气</label></c:if>
                                <c:if test="${list.name==5}"><label>空调</label></c:if>
                                <c:if test="${list.name==6}"><label>洗碗机</label></c:if>
                                <c:if test="${list.name==7}"><label>微波炉</label></c:if>
                                <c:if test="${list.name==8}"><label>洗衣机</label></c:if>
                                <c:if test="${list.name==9}"><label>花洒</label></c:if>
                                <c:if test="${list.name==10}"><label>浴池</label></c:if>
                                <c:if test="${list.name==11}"><label>电视机</label></c:if>
                                <c:if test="${list.name==12}"><label>车库</label></c:if>
                            </c:forEach>
                                </label>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label"><spring:message code="rental.photo"/></label>

                        <div class="col-sm-9">
                            <c:forEach items="${rental.imageList}" var="list">
                                <img src="${list.url}" width="120px" height="100px">
                            </c:forEach>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="text-center">
                    <a href="../rental/rentalList.html" type="button" class="btn btn-primary" for="propertySearch" ><spring:message code="common_confirm"/></a>
                   </div>
                </form>
            </div>
        </div>
    </div>


</div>
</div>
</div>
</div>

<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
