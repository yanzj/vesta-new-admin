<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

  <META HTTP-EQUIV="pragma" CONTENT="no-cache">
  <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
  <META HTTP-EQUIV="expires" CONTENT="0">

  <meta name="keywords" content=""/>
  <script type="application/x-javascript"> addEventListener("load", function () {
    setTimeout(hideURLbar, 0);
  }, false);
  function hideURLbar() {
    window.scrollTo(0, 1);
  } </script>

  <script type="text/javascript" charset="utf-8">
    window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
  </script>
  <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>

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

  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <!--animate-->
  <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
  <script src="../static/js/wow.min.js"></script>
  <script src="../static/property/js/checkNullAllJsp.js"></script>
  <script>
    new WOW().init();
    $(function () {
      console.log("sqq")
      $("#005500070000").addClass("active");
      $("#005500070000").parent().parent().addClass("in");
      $("#005500070000").parent().parent().parent().parent().addClass("active");
    })
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
  <style>
    .flowersList img {
      width: 20px;
    }

    .imgList img {
      width: 70px;
    }

    .borderSty {
      height: 1rem;
      border-bottom: 1px dotted #e2e2e2
    }
  </style>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="005500070000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>

        <div class="form-body" style="min-height:680px">
          <form class="form-horizontal" id="frm" action="" method="post"
                enctype="MULTIPART/FORM-DATA">
            <%--问卷名称--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">问卷名称：</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${quest.questName}</label>
              </div>
            </div>

              <%--有效时间--%>
              <div class="form-group col-lg-12">
                <label class="col-sm-2 control-label">有效时间：</label>

                <div class="col-sm-7">
                  <label class="col-sm-7 control-label"
                         style="text-align: left;">${quest.beginDate} &nbsp;至&nbsp; ${quest.endDate}</label>
                </div>
              </div>


              <%--参与人数--%>
              <div class="form-group col-lg-12">
                <label class="col-sm-2 control-label">参与人数：</label>

                <div class="col-sm-7">
                  <label class="col-sm-7 control-label"
                         style="text-align: left;">${quest.num}</label>
                </div>
              </div>


              <div class="form-group col-lg-12">
                <c:forEach items="${questtitle}" var="qlist" varStatus="row">
                  <label > 第${row.index + 1}题：${qlist.questionTitleName}</label>
                  <table width="100%" class="tableStyle">
                    <thead>
                    <tr>
                      <th>选项</th>
                      <th>小计</th>
                      <th>比例</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${qlist.questionTitleSelect}" var="list" varStatus="row">
                      <tr>
                        <td>${list.selectName}</td>
                        <td>${list.number}</td>
                        <td>
                          <fmt:formatNumber var="c" value="${list.number / quest.num * 100}" pattern="#,##"/>
                            ${c}%
                        </td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </c:forEach>
              </div>



              <div class="form-group col-lg-12">
                <c:forEach items="${subject}" var="su" varStatus="row">
                  <label> 问答题目${row.index + 1}：${su.subjectName}</label>
                  <table width="100%" class="tableStyle">
                    <thead>
                    <tr>
                      <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                      <th>姓名</th>
                      <th>联系方式</th>
                      <th>文本答案</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${su.userSubjectDTOS}" var="u" varStatus="row">
                      <tr>
                        <td><b>${row.index + 1}</b></td>
                        <td>${u.userSubjectId}</td>
                        <td>${u.userId}</td>
                        <td>${u.message}</td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </c:forEach>
              </div>
          </form>
        </div>
      </div>
    </div>

  </div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>

</body>
</html>
