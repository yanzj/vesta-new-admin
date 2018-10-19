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
  <script src="../static/property/js/propertyConsult.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020010" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../property/PropertyConsult.html">

          <div class="form-group  col-lg-4">
            <label for="project" class="col-sm-3 control-label"><spring:message code="propertyConsult.projectId" /></label>

            <div class="col-sm-9">
              <select name="project" class="form-control" id="project" >
                <c:forEach items="${projectSelDTOs}" var="project" varStatus="row">
                  <option value="${project.projectId}" id="project${row.index}"  <c:if test="${project.projectId == propertyConsult.project}">selected="selected"</c:if> >${project.projectName}</option>

                </c:forEach>
              </select>
            </div>
          </div>

          <div class="form-group  col-lg-4">
            <label for="replyStatus" class="col-sm-4 control-label"><spring:message code="propertyConsult.replyStatus"/></label>

            <div class="col-sm-8">
              <select id="replyStatus" name="replyStatus"  class="form-control">
                <option value="0" id="reply0">----------请选择状态----------</option>
                <option value="1" id="reply1"><spring:message code="propertyConsult.yesReply"/></option>
                <option value="2" id="reply2"><spring:message code="propertyConsult.noReply"/></option>
              </select>
            </div>
          </div>
            <div class="form-group  col-lg-4">
                <label for="content" class="col-sm-4 control-label"><spring:message code="propertyConsult.content"/></label>

                <div class="col-sm-8">
                    <input type="text" class="form-control" placeholder="" value="${propertyConsult.content}" id="content" name="content">
                </div>
            </div>
          <div class="clearfix"></div>
          <div class="form-group col-lg-6" >
            <label for="crtTimeStart" class="col-sm-3 control-label"><spring:message code="propertyConsult.crtTimeStart"/></label>
            <div class="input-group date form_date col-lg-9" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="crtTimeStart" value="${propertyConsult.crtTimeStart}" name="crtTimeStart" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
            </div>
          <%--</div>--%>
          <%--<div class="form-group  col-lg-5">--%>
          <div class="form-group col-lg-6" >
            <label for="crtTimeEnd" class="col-sm-3 control-label"><spring:message code="propertyConsult.crtTimeEnd"/></label>
            <div class="input-group date form_date col-lg-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="crtTimeEnd" value="${propertyConsult.crtTimeEnd}" name="crtTimeEnd" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>

          </div>
          <div class="clearfix"></div>
          <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="propertyConsult.search"/></button>

        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <th><spring:message code="propertyAnnouncement.serialNumber" /></th>
        <th><spring:message code="propertyConsult.projectId" /></th>
        <th><spring:message code="propertyConsult.crtTime" /></th>
        <th><spring:message code="propertyConsult.owner" /></th>
        <th><spring:message code="propertyConsult.mobile" /></th>
        <th><spring:message code="propertyConsult.address" /></th>
        <th><spring:message code="propertyConsult.content" /></th>
        <th><spring:message code="advertServices.state" /></th>
        <th><spring:message code="propertyConsult.replyTime" /></th>
        <th><spring:message code="propertyServices.serviceOperation" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${consultList}" var="consult" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
          <td>${consult.projectName}</td>
          <td>${consult.crtTime}</td>
          <td>${consult.userName}</td>
          <td>${consult.mobile}</td>
          <td>${consult.address}</td>
          <td>
            <c:choose>
              <c:when test="${fn:length(consult.content) > 15}">
                <c:out value="${fn:substring(consult.content, 0, 15)}......" />
              </c:when>
              <c:otherwise>
                <c:out value="${consult.content}" />
              </c:otherwise>
            </c:choose>
          </td>
          <td>
            <c:if test="${consult.replyStatus == '1'}"><spring:message code="propertyConsult.yesReply"/></c:if>
            <c:if test="${consult.replyStatus == '2'}"><spring:message code="propertyConsult.noReply"/></c:if>
          </td>
          <td>${consult.answercCrtTime}</td>
          <td>
            <c:if test="${consult.replyStatus == '1'}">
              <a href="../property/NewPropertyConsult.html?id=${consult.id}&type=1"><spring:message code="praise.review" /></a>
            </c:if>
            <c:if test="${consult.replyStatus == '2'}">
              <a href="../property/NewPropertyConsult.html?id=${consult.id}&type=2"><spring:message code="propertyConsult.reply" /></a>
            </c:if>
            <a href="javascript:void(0);" onclick="js_delMessage('${consult.id}')" ><spring:message code="propertyConsult.delete" /></a>

          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/property/PropertyConsult.html?pageIndex={0}&replyStatus=${propertyConsult.replyStatus}&project=${propertyConsult.project}&crtTimeStart=${propertyConsult.crtTimeStart}&crtTimeEnd=${propertyConsult.crtTimeEnd}&content=${propertyConsult.content}"/>
  </div>


</div>
</div>
</div>
</div>
<script type="text/javascript">
  <!--  -->
  <!-- 当前页面 -->
  var pages = "${webPage.pageIndex}";
  <!-- 答复状态 -->
  var replyStatus = "${propertyConsult.replyStatus}";
  <!-- 项目 -->
  var projectSel = "${propertyConsult.project}";
  <!-- 咨询开始时间 -->
  var crtTimeStart = "${propertyConsult.crtTimeStart}";
  <!-- 咨询结束时间 -->
  var crtTimeEnd = "${propertyConsult.crtTimeEnd}";
  <!-- 咨询内容 -->
  var content = "${propertyConsult.content}";
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