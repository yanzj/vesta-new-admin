<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/1/25
  Time: 16:00
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
<script type="text/javascript">
    function goSubmit(id){
        document.getElementById("rolesetId").value = id;
        document.getElementById("search_form").submit();
    }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">


  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005800050000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../communityArea/HousePayBatch.html">


          <!-- 项目 -->
          <div class="form-group  col-lg-4">
            <label for="${communityAppointManageDto.communityName}" class="col-sm-3 control-label"><spring:message code="propertyCompany.propertyProject"/></label>

            <div class="col-sm-9">
              <select id="${communityAppointManageDto.communityName}" name="${communityAppointManageDto.communityName}"   class="form-control">
                <c:forEach items="${communityAppointPageMapDto.projectNameMap}" var="item">
                  <option value="${item.key }" >${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 支付批次 -->
          <div class="form-group  col-lg-4">
            <label for="${communityAppointManageDto.deliveryBatch}" class="col-sm-3 control-label"><spring:message code="HousePayBatch.payBatch"/></label>

            <div class="col-sm-9">
              <select id="${communityAppointManageDto.deliveryBatch}" name="${communityAppointManageDto.deliveryBatch}"  class="form-control">
                <c:forEach items="${communityAppointPageMapDto.payBatchMap}" var="item">
                  <option value="${item.key }" >${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 支付状态 -->
          <div class="form-group  col-lg-4">
            <label for="${communityAppointManageDto.status}" class="col-sm-3 control-label"><spring:message code="HousePayBatch.patStatus"/></label>

            <div class="col-sm-9">
              <select id="${communityAppointManageDto.status}" name="${communityAppointManageDto.status}"  class="form-control">
                <c:forEach items="${communityAppointPageMapDto.payStatusMap}" var="item">
                  <option value="${item.key }" >
                    <c:if test="${item.value == '0'}"><spring:message code="HousePayBatch.ing" /></c:if>
                    <c:if test="${item.value == '1'}"><spring:message code="HousePayBatch.complete" /></c:if>
                  </option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 集中交付开始时间 -->
          <div class="form-group  col-lg-6">
            <label for="payStaDate" class="col-sm-3 control-label"><spring:message code="propertyAnnouncement.createTimeStart"/></label>

            <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="payStaDate" name="payStaDate" value="${communityAppointManageDto.payStaDate}" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>

          <!-- 集中交付结束时间 -->
          <div class="form-group  col-lg-6">
            <label for="payEndDate" class="col-sm-3 control-label"><spring:message code="propertyAnnouncement.createTimeEnd"/></label>

            <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="payEndDate" value="${communityAppointManageDto.payEndDate}" name="payEndDate" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>

          <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="propertyCompany.propertySearch"/></button>

          <a  href="../property/NewPropertyAnnouncement?announcementId=" class="btn btn-primary" for="propertyAdd" ><spring:message code="propertyCompany.propertyAdd"/></a>

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005700030000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../communityArea/AppointManage.html">
                    <div class="form-group  col-lg-4">
                        <label for="projectIdDto" class="col-sm-4 control-label"><spring:message code="HousePayBatch.projectName"/></label>
                        <div class="col-sm-8">
                            <select name="projectIdDto" class="form-control" id="projectIdDto">
                                <c:forEach items="${projectList}" var="section">
                                    <option value="${section.id}" <c:if test="${housePayBatchQueryDto.projectIdDto eq section.id}">selected="selected"</c:if>>${section.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="batchNoDto" class="col-sm-4 control-label"><spring:message code="HousePayBatch.payBatch"/></label>
                        <div class="col-sm-8">
                            <select name="batchNoDto" class="form-control" id="batchNoDto">
                                <%--<c:forEach items="${houseSectionAdminDTOs}" var="section">--%>
                                <%--<option value="${section.sectionId}" <c:if test="${housePayBatchQueryDto.sectionIdDto eq section.sectionId}">selected="selected"</c:if>>${section.sectionName}</option>--%>
                                <%--</c:forEach>--%>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="statusDto" class="col-sm-4 control-label"><spring:message code="HousePayBatch.patStatus"/></label>
                        <div class="col-sm-8">
                            <select name="statusDto" class="form-control" id="statusDto">
                                <c:forEach items="${housePayBatchStatusDtoList}" var="section">
                                <option value="${section.id}"
                                        <c:if test="${housePayBatchQueryDto.statusDto eq section.id}">selected="selected"</c:if>>
                                ${section.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="HousePayBatch.search"/></button>
                    <a  href="#" class="btn btn-primary" for="rolesetAdd" ><spring:message code="common_add"/></a>

                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <form action="../communityArea/housePayBatch" method="post" id ="search_form">


            <input id = "activityId" type="hidden" name="rolesetId"  value="">
        </form>
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="HousePayBatchTitle.serialNum" /></td>
                <th><spring:message code="HousePayBatch.payBatch" /></th>
                <th><spring:message code="HousePayBatch.projectName" /></th>
                <th><spring:message code="HousePayBatch.payStaDate" /></th>
                <th><spring:message code="HousePayBatch.maxUser" /></th>
                <th><spring:message code="HousePayBatch.operation" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${communityAppointList}" var="community" varStatus="vs">
                <tr>
                    <td><b>${vs.count}</b></td>
                    <td>${community.deliveryBatch}</td>
                    <td>${community.communityName}</td>
                    <td>${community.payStaDate} 至 ${community.payEndDate}</td>
                    <td>${community.maxUser}</td>
                    <td class="last">
                        <a href="#" class="a2"><span class="span1"><spring:message code="common_update" /></span></a>
                        <a href="#" class="a2"><span class="span1"><spring:message code="common_delete" /></span></a>
                        <a href="#" class="a3"><span class="span1"><spring:message code="common_detail" /></span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/community/activityManage.html?pageIndex={0}"/>
    </div>

  </div>
  <div class="table-responsive bs-example widget-shadow">
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="propertyAnnouncement.serialNumber" /></td>
        <th><spring:message code="HousePayBatch.payBatch" /></th>
        <th><spring:message code="HousePayBatch.projectName" /></th>
        <th><spring:message code="HousePayBatch.payStaDate" /></th>
        <th><spring:message code="HousePayBatch.maxUser" /></th>
        <th width="174"><spring:message code="propertyCompany.operation" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${communityAppointManageDtos}" var="appointManage" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
          <td>${appointManage.deliveryBatch}</td>
          <td>${appointManage.communityName}</td>
          <td>${appointManage.payStaDate} 至 ${appointManage.payEndDate}</td>
          <td>${appointManage.maxUser}</td>
          <td class="last">
            <a href="../property/NewPropertyAnnouncement?announcementId=${announenment.announcementId}"><spring:message code="propertyServices.serviceModify" /></a>
            <a href="javascript:void(0);" onclick="js_delMessage('${announenment.announcementId}')" id="delProperty" class="a3"><spring:message code="propertyServices.serviceDelete" /></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/communityArea/HousePayBatch.html?pageIndex={0}&communityName=${communityAppointManageDto.communityName}&deliveryBatch=${communityAppointManageDto.deliveryBatch}&status=${communityAppointManageDto.status}&payStaDate=${communityAppointManageDto.payStaDate}&payEndDate=${communityAppointManageDto.payEndDate}"/>
  </div>

</div>
</div>
</div>
</div>

<%--时间搜索插件--%>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
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
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>