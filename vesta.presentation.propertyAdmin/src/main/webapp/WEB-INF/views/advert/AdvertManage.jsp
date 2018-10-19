<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <script language="javascript" type="text/javascript" src="/static/js/jquery-1.11.1.min.js"></script>
    <script type="application/x-javascript">

        addEventListener("load", function () {
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

    <!-- 时间控件CSS Begin-->
    <link href="../static/plus/date/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- 时间控件CSS End-->


    <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <script src="../static/js/jquery.SuperSlide.2.1.1.js"></script>
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
    <script>
        $(document).ready(function() {
            if("" != ${searchAdAdmin.itemId}){
                $("#itemId").val("${searchAdAdmin.itemId}");
            }
        });
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020011" username="${propertystaff.staffName}" />


    <div class="forms">

        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form  action="../advert/advertManage.html" class="form-horizontal" method="post">
                    <div class="form-group  col-lg-4">
                        <label  class="col-sm-4 control-label"><spring:message code="advertServices.project"/></label>

                        <div class="col-sm-8">
                            <select id="itemId" name="itemId"  class="form-control">
                                <c:forEach items="${adManageMaps.projectMap}" var="item">
                                    <option value="${item.key }">${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                <div class="form-group  col-lg-4">
                    <label  class="col-sm-4 control-label"><spring:message code="advertServices.advertGroup" /></label>

                    <div  class="col-sm-8">
                        <select id="adModularId" name="adModularId"  class="form-control">
                            <c:forEach items="${adManageMaps.groupMap}" var="item">
                                <option value="${item.key }"  <c:if test="${item.key eq searchAdAdmin.adModularId}">selected="selected"</c:if>>${item.value }</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                    <div class="form-group  col-lg-4">
                        <label for="keyWord" class="col-sm-4 control-label"><spring:message code="advertServices.title"/></label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="keyWord"
                                   name="keyWord" value="${searchAdAdmin.keyWord}" >
                        </div>
                    </div>
                    <%--开始时间--%>
                    <div class="form-group  col-lg-6">
                        <label for="publishStartDate" class="col-sm-3 control-label"><spring:message code="advertServices.StartDate"/></label>

                        <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                           <input type="text" class="form-control"  placeholder="" id="publishStartDate" value="${searchAdAdmin.publishStartDate}"
                                   name="publishStartDate" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        </div>
                    <div class="form-group  col-lg-6">
                        <label for="publishEndDate" class="col-sm-3 control-label"><spring:message code="advertServices.EndDate"/></label>

                        <div  class="input-group date form_date col-sm-9"  data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="publishEndDate" value="${searchAdAdmin.publishEndDate}"
                                   name="publishEndDate" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>


                    <button type="submit"  class="btn btn-primary"  ><spring:message code="propertyCompany.propertySearch"/></button>

                    <a href="../advert/addAdvert.html?advertId=" class="btn btn-primary" ><spring:message code="propertyCompany.propertyAdd"/></a>

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
                <th><spring:message code="advertServices.title" /></th>
                <th><spring:message code="advertServices.img" /></th>
                <th><spring:message code="advertServices.link" /></th>
                <th><spring:message code="advertServices.group" /></th>
                <th><spring:message code="advertServices.item" /></th>
                <th><spring:message code="advertServices.Time" /></th>
                <th><spring:message code="advertServices.push" /></th>
                <th width="174"><spring:message code="propertyCompany.operation" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${advertMessage}" var="advert" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${advert.title}</td>
                    <td>
                    <img src="${advert.imgUrl}" style="width:100px;"></td>
                    <td>${advert.advertisinglinks}</td>
                    <td>${advert.adModularId}</td>
                    <td>${advert.item}</td>
                    <td>${advert.publishDate}</td>
                    <td>${advert.publishBy}</td>
                    <td class="last">
                      <c:if test="${advert.isSort==1&&advert.count>1}">
                          <c:choose>
                              <c:when test="${row.index==0}">
                                  <a href="../advert/Advertisingsort.html?advertId=${advert.advertId}&state=down" style="margin:0;color: black"><span class="glyphicon glyphicon-arrow-down" style="color: green"><spring:message code="advertServices.down" /></span></a>
                              </c:when>
                              <c:when test="${row.index==advert.count-1}">
                                  <a href="../advert/Advertisingsort.html?advertId=${advert.advertId}&state=up" style="margin:0;color: black"><span class="glyphicon glyphicon-arrow-up" style="color:red"><spring:message code="advertServices.up" /></span></a>
                              </c:when>
                              <c:otherwise>
                                  <a href="../advert/Advertisingsort.html?advertId=${advert.advertId}&state=up" style="margin:0;color: black"><span class="glyphicon glyphicon-arrow-up"style="color:red"><spring:message code="advertServices.up" /></span></a>
                                  <a href="../advert/Advertisingsort.html?advertId=${advert.advertId}&state=down" style="margin:0;color: black"><span class="glyphicon glyphicon-arrow-down" style="color: green"><spring:message code="advertServices.down" /></span></a>
                              </c:otherwise>
                          </c:choose>


                      </c:if>
                        <a href="../advert/addAdvert.html?advertId=${advert.advertId}" class="a2"><span class="glyphicon glyphicon-edit" style="margin:0;color: black"><spring:message code="propertyServices.serviceModify" /></span></a>
                        <a href="../advert/removeAdvertById?advertId=${advert.advertId}" class="a3"><span class="glyphicon glyphicon-remove" style="margin:0;color: black"><spring:message code="propertyServices.serviceDelete" /></span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/advert/advertManage.html?pageIndex={0}&propertyProject=${activitiesSearch.propertyProject}"/>
    </div>


</div>
</div>
</div>
</div>
<!-- 时间控件Begin -->
<script type="text/javascript" src="../static/plus/date/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/plus/date/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
</script>
<!-- 时间控件End -->
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>