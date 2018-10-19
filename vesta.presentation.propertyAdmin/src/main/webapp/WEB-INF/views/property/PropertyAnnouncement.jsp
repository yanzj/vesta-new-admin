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
  <script src="../static/property/js/propertyAnnouncement.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020001" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../property/PropertyAnnouncement.html">

          <div class="form-group  col-lg-6">
            <label for="title" class="col-sm-3 control-label"><spring:message code="propertyAnnouncement.title" /></label>

            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" value="${propertyAnnouncementSearch.title}" id="title" name="title">
            </div>
          </div>

          <div class="form-group  col-lg-6">
            <label for="project" class="col-sm-3 control-label"><spring:message code="propertyCompany.propertyProject"/></label>

            <div class="col-sm-9">
              <select id="project" name="project"  class="form-control">
                <c:forEach items="${propertyCompanyMap.propertyProjectMap}" var="item">
                  <option value="${item.key }" >${item.value }</option>
                </c:forEach>
              </select>
            </div>
          </div>


          <div class="form-group  col-lg-6">
            <label for="startTime" class="col-sm-3 control-label"><spring:message code="propertyAnnouncement.createTimeStart"/></label>

            <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="startTime" value="${propertyAnnouncementSearch.startTime}" name="startTime" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="form-group  col-lg-6">
            <label for="endTime" class="col-sm-3 control-label"><spring:message code="propertyAnnouncement.createTimeEnd"/></label>

            <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="endTime" value="${propertyAnnouncementSearch.endTime}" name="endTime" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>

          <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="propertyCompany.propertySearch"/></button>

          <a  href="../property/NewPropertyAnnouncement?announcementId=" class="btn btn-primary" for="propertyAdd" ><spring:message code="propertyCompany.propertyAdd"/></a>

        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="propertyAnnouncement.serialNumber" /></td>
        <th><spring:message code="propertyCompany.propertyProject" /></th>
        <th><spring:message code="propertyAnnouncement.title" /></th>
        <th><spring:message code="propertyAnnouncement.type" /></th>
        <th><spring:message code="propertyAnnouncement.operator" /></th>
        <th><spring:message code="propertyAnnouncement.operatDate" /></th>
        <th><spring:message code="propertyAnnouncement.stat" /></th>
        <th width="174"><spring:message code="propertyCompany.operation" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${propertyAnnouncement}" var="announenment" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
          <td>${announenment.project}</td>
          <td>${announenment.title}</td>
          <td>${announenment.type}</td>
          <td>${announenment.releasePerson}</td>
          <td>${announenment.createTime}</td>
          <td>
            <c:if test="${announenment.stat == '0'}"><spring:message code="propertyAnnouncement.no" /></c:if>
            <c:if test="${announenment.stat == '1'}"><spring:message code="propertyAnnouncement.yes" /></c:if>
          </td>
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
             submitUrl="${pageContext.request.contextPath }/property/PropertyAnnouncement.html?pageIndex={0}&title=${propertyAnnouncementSearch.title}&project=${propertyAnnouncementSearch.project}&startTime=${propertyAnnouncementSearch.startTime}&endTime=${propertyAnnouncementSearch.endTime}"/>
  </div>


</div>
</div>
</div>
</div>
<script type="text/javascript">
  var pages = "${webPage.pageIndex}";
  var title = "${propertyAnnouncementSearch.title}";
  var project = "${propertyAnnouncementSearch.project}";
  var startTime = "${propertyAnnouncementSearch.startTime}";
  var endTime = "${propertyAnnouncementSearch.endTime}";

</script>
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