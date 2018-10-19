<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <script src="../static/property/js/propertyHousPay.js"></script>
</head>

<body class="cbp-spmenu-push">
  <div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="000200040000" username="${propertystaff.staffName}"/>
    <div class="forms">
      <div class="widget-shadow " data-example-id="basic-forms">
        <%--搜索条件开始--%>
        <div class="form-body">
          <form id="form" class="form-horizontal" action="../homePageCarousel/gotoCarouselList">
            <!-- 发布城市 -->
            <div class="form-group  col-lg-4">
              <label for="cityId" class="col-sm-3 control-label">发布城市</label>
              <div class="col-sm-5">
                <select id="cityId" name="cityId" class="form-control" onchange="queryProjectNameById()">
                  <c:forEach items="${city}" var="item" >
                    <option value="${item.sid }"
                            <c:if test="${item.sid eq '0'}">selected</c:if>>${item.name }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <!-- 发布项目 -->
            <div class="form-group  col-lg-4">
              <label for="title" class="col-sm-3 control-label">发布项目</label>

              <div class="col-sm-9">
                <select id="projectName" name="projectName" class="form-control">
                </select>
              </div>
            </div>
            <%--<div class='clearfix'></div>--%>
            <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                    code="propertyCompany.propertySearch"/></button>

            <a href="../homePageCarousel/gotoCarouselEdit?projectCode=" class="btn btn-primary"
               for="payBatchAdd"><spring:message code="common_add"/></a>
          </form>
        </div>
        <%--搜索条件结束--%>
      </div>
    </div>

    <div class="table-responsive bs-example widget-shadow">
      <table width="100%" class="tableStyle">
        <thead>
        <tr>
          <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
          <th>项目编码</th>
          <th>项目名称</th>
          <th width="174"><spring:message code="billInfo.opera"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${projectList}" var="project" varStatus="row">
          <tr>
            <td><b>${row.index + 1}</b></td>
            <td>${project[0]}</td>
            <td>${project[1]}</td>
            <td class="last">
              <a href="javascript:void(0);" onclick="js_update('${project[0]}','${project[1]}')" id="update"
                 class="a3"><spring:message code="propertyServices.serviceModify"/></a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <m:pager pageIndex="${webPage.pageIndex}"
               pageSize="${webPage.pageSize}"
               recordCount="${webPage.recordCount}"
               submitUrl="${pageContext.request.contextPath }/homePageCarousel/gotoCarouselList?pageIndex={0}&releaseStatus=${announcementDTO.releaseStatus}&title=${announcementDTO.title}&content=${announcementDTO.content}&releasePerson=${announcementDTO.releasePerson}&staDate=${announcementDTO.staDate}&endDate=${announcementDTO.endDate}"/>
    </div>
  </div>
  </div>
  <!-- main content end-->
    <%@ include file="../main/foolter.jsp" %>
<script>
  function queryProjectNameById() {
    var projectId = $("#cityId").val();
    $("#planName").empty();
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId, function (data) {
      var arr = data.data;
      $("#projectName").empty();
      $("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');
      for (var k in arr) {
        $("#projectName").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
      }
    });
  }

  function js_update(projectCode,projectName){
    window.location.href = "../homePageCarousel/gotoCarouselEdit?projectCode=" + projectCode + "&projectName=" + projectName;
  }

</script>
</body>
</html>
