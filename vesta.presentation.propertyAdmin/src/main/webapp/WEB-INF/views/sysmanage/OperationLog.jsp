<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001000090000" username="${propertystaff.staffName}" />


  <div class="forms">

    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form  action="../operation/OperationLog.html" method="post">
          <div class="form-group  col-lg-6">
            <label for="" class="col-sm-3 control-label"><spring:message code="loginLog.username"/></label>

            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id=""
                     name="userName" >
            </div>
          </div>
          <div class="form-group  col-lg-6">
            <label for="content" class="col-sm-3 control-label"><spring:message code="operationLog.content"/></label>
            <div class="col-sm-9">
              <input type="text" class="form-control" placeholder="" id="content"
                     name="content" >
            </div>
          </div>
          <%--开始时间--%>
          <div class="form-group  col-lg-6">
            <label for="startDate" class="col-sm-3 control-label"><spring:message code="staffManage.staffSelBeginTime"/></label>
            <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <input type="text" class="form-control"  placeholder="" id="startDate"
                     name="startDate" readonly >
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>

            </div>
          </div>


          <%--结束时间--%>
          <div class="form-group  col-lg-6">
            <label for="endDate" class="col-sm-3 control-label"><spring:message code="staffManage.staffSelEndTime"/></label>

            <div  class="input-group date form_date col-sm-9"  data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" >
              <input type="text" class="form-control" placeholder="" id="endDate"
                     name="endDate" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>

          <button type="submit" id="abc" class="btn btn-primary"  ><spring:message code="operationLog.search"/></button>

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
        <th><spring:message code="loginLog.name" /></th>
        <th><spring:message code="loginLog.username" /></th>
        <th><spring:message code="operationLog.content" /></th>
        <th><spring:message code="operationLog.time" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${operationLogList}" var="operationLog" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
            <td>${operationLog.name}</td>
            <td>${operationLog.userName}</td>
            <td>${operationLog.content}</td>
          <td>${operationLog.time}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/operation/OperationLog.html?pageIndex={0}&userName=${operationLogSearchDTO.userName}&content=${operationLogSearchDTO.content}&startDate=${operationLogSearchDTO.startDate}&endDate=${operationLogSearchDTO.endDate}"/>
  </div>


</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script type="application/x-javascript">
  console.info($("#abc").get(0));
  $("#abc").click(function(){
    console.info("fuck");
    $("#123").submit();
  })
</script>
<!-- 时间控件Begin -->
<script type="text/javascript" src="../static/plus/date/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/plus/date/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
  console.info($('.form_datetime').get(0));
  $('.form_datetime').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1
  });
  $('.form_date').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
  });
  $('.form_time').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 1,
    minView: 0,
    maxView: 1,
    forceParse: 0
  });
</script>
<!-- 时间控件End -->
</body>
</html>