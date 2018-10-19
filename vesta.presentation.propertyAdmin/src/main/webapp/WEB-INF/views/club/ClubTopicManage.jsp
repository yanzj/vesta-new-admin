<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/22
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000500040000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../circle/clubTopicManage.html">
          <%--俱乐部下拉框--%>
          <div class="form-group  col-lg-6">
            <label for="circleInfoId" class="col-sm-3 control-label"><spring:message code="clubTopic.ClubName" /></label>
            <div class="col-sm-9">
              <select name="circleInfoId" id="circleInfoId" class="form-control">
                <c:forEach items="${circleInfoAdminDTOs}" var="circle">
                  <option value="${circle.circleId}" <c:if test="${circle.circleId eq circleTopicDTO.circleInfoId}">selected="selected"</c:if>>${circle.circleName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
            <%--标题--%>
          <div class="form-group  col-lg-6">
          <label for="circleTopicName" class="col-sm-3 control-label"><spring:message code="clubTopic.TopicTitle"/></label>
          <div class="col-sm-9">
          <input type="test" class="form-control" placeholder="" id="circleTopicName" name="circleTopicName" value="${circleTopicDTO.circleTopicName}">
          </div>
          </div>
            <%--开始时间--%>

            <div class="form-group  col-lg-6">
              <label for="circleTopicName" class="col-sm-3 control-label"><spring:message code="clubTopic.TopicTime"/></label>
            <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="beginTime" name="beginTime" value="${circleTopicDTO.beginTime}" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
              </div>
            <div class="form-group  col-lg-6">
              <label for="circleTopicName" class="col-sm-3 control-label" style="text-align: center"><spring:message code="clubTopic.EndTime"/></label>
            <%--结束时间--%>
            <div class="input-group date form_date col-sm-9" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="" id="endTime" name="endTime" value="${circleTopicDTO.endTime}" readonly>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
            </div>

          <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_select"/></button>

          <%--<a  href="../club/gotoAddClub" class="btn btn-add" for="" ><spring:message code="common_add"/></a>--%>

        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">

    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <td><spring:message code="common_sort" /></td>
        <th><spring:message code="clubTopic.ClubName" /></th>
        <th><spring:message code="clubTopic.TopicTitle" /></th>
        <th><spring:message code="clubTopic.TopicContent" /></th>
        <th><spring:message code="clubTopic.TopicUser" /></th>
        <th><spring:message code="clubTopic.TopicTime" /></th>
        <th><spring:message code="clubTopic.TopicReplyNum" /></th>
        <th><spring:message code="clubTopic.TopicBestNum" /></th>
        <th><spring:message code="clubTopic.Operation" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${circleTopicDTOs}" var="clubTopic" varStatus="vs">
        <tr>
          <td><b>${vs.count}</b></td>
          <td>${clubTopic.circleInfoName}</td>
          <td>${clubTopic.circleTopicName}</td>
          <td>${clubTopic.circleTopicContent}</td>
          <td>${clubTopic.circleUserName}</td>
          <td>${clubTopic.circleTopicCreateOn}</td>
          <td>${clubTopic.circleCountReply}</td>
          <td>${clubTopic.circleCountBest}</td>
          <td class="last">
              <%--<a class = "a1" href="javascript:void(0);" onclick="goSubmit('${roleSet.setId}')"><span class="span1"><spring:message code="authotity.rolesetManage" /></span></a>--%>
            <a href="../circle/showClubReply?circleTopicId=${clubTopic.circleTopicId}" class="a2"><span class="span1"><spring:message code="clubTopic.ShowReply" /></span></a>
            <a href="../circle/delClubTopic?circleTopicId=${clubTopic.circleTopicId}" class="a3"><span class="span1"><spring:message code="common_delete" /></span></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/circle/clubTopicManage.html?pageIndex={0}"/>
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