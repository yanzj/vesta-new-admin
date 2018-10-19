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

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020014" username="${propertystaff.staffName}" />


  <div class="forms">

    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form  action="../message/messageManage.html" method="post">
          <a href="../message/CreateMessage.html?messageManageId=" class="btn btn-primary" ><spring:message code="propertyCompany.propertyAdd"/></a>

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
        <th><spring:message code="messageManage.title" /></th>
        <th><spring:message code="messageManage.content" /></th>
        <th><spring:message code="messageManage.type" /></th>
        <th width="174"><spring:message code="propertyCompany.operation" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${messageManage}" var="message" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
          <td>${message.title}</td>
          <td>${message.content}</td>
          <td>${message.typeId}</td>
          <td class="last">
            <a href="../message/CreateMessage.html?messageManageId=${message.messageManageId}" class="a2"><span class="glyphicon glyphicon-edit" style="margin:0;color: black"><spring:message code="propertyServices.serviceModify" /></span></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/message/messageManage.html?pageIndex={0}"/>
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