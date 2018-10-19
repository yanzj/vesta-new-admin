<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/22
  Time: 16:03
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

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000500060000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <%--<form class="form-horizontal" action="../circle/clubTopicManage.html">--%>
        <%--标题--%>
        <div class="form-title  col-lg-12">
          <%--<label class="col-sm-3 control-label"><spring:message code="clubTopic.TopicTitle" />：</label>--%>
          <div class="col-sm-12">
            <h4>${circleTopicDTO.circleTopicName}</h4>
          </div>
        </div>
        <%--发布人--%>
        <div class="form-group  col-lg-2">
          <%--<label class="col-sm-3 control-label"><spring:message code="clubTopic.TopicUser"/></label>--%>
          <div class="col-sm-12">
            【楼主】${circleTopicDTO.circleUserName}
          </div>
        </div>
            <%--发布内容--%>
            <div class="form-group  col-lg-7">
                <%--<label class="col-sm-3 control-label"><spring:message code="clubTopic.TopicContent"/></label>--%>
                <div class="col-sm-12">
                    ${circleTopicDTO.circleTopicContent}
                </div>
            </div>
        <%--发布时间--%>
        <div class="form-group  col-lg-3">
          <%--<label class="col-sm-3 control-label"><spring:message code="clubTopic.TopicTime"/></label>--%>
          <div class="col-sm-12">
              ${circleTopicDTO.circleTopicCreateOn}
          </div>
        </div>

        <%--图片--%>
        <div class="form-group  col-lg-12">
          <%--<label class="col-sm-3 control-label"></label>--%>
          <div class="col-sm-12" style="text-align: center">
            <c:forEach var="img" items="${circleTopicDTO.circleImageUrls}">
              <img src="${img}" width="120px" height="100px"/>
            </c:forEach>
              </div>
        </div>

        <%--<button type="submit" class="btn btn-default" for="propertySearch" ><spring:message code="common_select"/></button>--%>

        <a  href="../circle/bbsTopicManage.html" class="btn btn-primary" for="" ><spring:message code="common_back"/></a>

        <%--</form>--%>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">

    <table width="100%" class="tableStyle">
      <c:forEach items="${circleReplyDTOs}" var="reply">
        <tr>
          <td>
              ${reply.userName} @${reply.replayUserName}
          </td>
          <td>
            ${reply.content}
          </td>
            <td>
                ${reply.createOn}
                <a href="../circle/delBBSReply?id=${reply.id}&topicId=${circleTopicDTO.circleTopicId}" class="a3"><span class="span1"><spring:message code="common_delete" /></span></a>
            </td>
        </tr>
      </c:forEach>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/circle/showBBSReply?pageIndex={0}&circleTopicId=${circleTopicDTO.circleInfoId}"/>
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