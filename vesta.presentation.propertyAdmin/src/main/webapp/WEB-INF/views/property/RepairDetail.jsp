<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/2/20
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%response.setHeader("cache-control","public"); %>
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
    $(function(){
      console.log("sqq")
      $("#005700030000").addClass("active");
      $("#005700030000").parent().parent().addClass("in");
      $("#005700030000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <style>
    .flowersList img{
      width: 20px;
    }
    .imgList img{
      width: 100px;
      height: 120px;
    }
    .sidebar ul li{
      border-bottom: 0;
    }
  </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005700030000" username="${propertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--工单分配开始--%>
      <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
        <form:form class="form-horizontal" action="../property/repairDetail.html" modelAttribute="repairInfo" method="get">
          <input type="hidden" id="id" name="id" value="${repairs.id}" />
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.id" />：</label>
            <label class="control-label">${repairs.id}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.resource" />：</label>
            <label class="control-label">${repairs.repairWay}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.userName" />：</label>
            <label class="control-label">${repairs.userName}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.address" />：</label>
            <label class="control-label">${repairs.userAddress}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.repairState" /></label>
            <label class="control-label">${progress.status}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.repairContent" /></label>
            <label class="col-sm-9" style="padding: 8px 0px 0px;">${repairs.content}</label>
          </div>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.repairImage" /></label>
             <c:forEach items="${repairs.imageList}" var="image">
               <label class="control-label imgList"><img src="${image.imageUrl}"></label>
             </c:forEach>
          </div>

          <%--<div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.repairImaged" /></label>
            <c:forEach items="${repairs.imagedList}" var="image">
              <label class="control-label imgList"><img src="${image.imageUrl}"></label>
            </c:forEach>
          </div>--%>
          <div class="form-group  col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.repairProgress" /></label>
            <table>
              <ul class="col-lg-6 col-sm-6" style="padding-top:8px;">
                  <c:forEach items="${progress.progressList}" var="progress">
                    <li>
                      <span>${progress.taskDate}</span>
                      <span style="padding-left: 40px;">${progress.taskContent}</span>
                    </li>
                  </c:forEach>
              </ul>
            </table>
          </div>
          <div class="form-group  col-lg-12 flowersList">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.evaluate" /></label>
            <div class="col-sm-9" style="padding: 0;">
              <c:if test="${repairs.grade==0 || repairs.grade==null}">
                <label class="control-label">
                  <img src="../static/img/iconfont-favor.png">
                  <img src="../static/img/iconfont-favor.png">
                  <img src="../static/img/iconfont-favor.png">
                  <img src="../static/img/iconfont-favor.png">
                  <img src="../static/img/iconfont-favor.png">
                </label>
              </c:if>
              <c:if test="${repairs.grade==1}">
                <label class="control-label">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favor.png">
                  <img src="../static/img/iconfont-favor.png">
                  <img src="../static/img/iconfont-favor.png">
                  <img src="../static/img/iconfont-favor.png">
                </label>
                &nbsp;&nbsp;
                <label class="control-label"><spring:message code="workOrders.veryBad" /></label>
              </c:if>
              <c:if test="${repairs.grade==2}">
                <label class="control-label">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favor.png">
                  <img src="../static/img/iconfont-favor.png">
                  <img src="../static/img/iconfont-favor.png">
                </label>
                &nbsp;&nbsp;
                <label class="control-label"><spring:message code="workOrders.bad" /></label>
              </c:if>
              <c:if test="${repairs.grade==3}">
                <label class="control-label">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favor.png">
                  <img src="../static/img/iconfont-favor.png">
                </label>
                &nbsp;&nbsp;
                <label class="control-label"><spring:message code="workOrders.general" /></label>
              </c:if>
              <c:if test="${repairs.grade==4}">
                <label class="control-label">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favor.png">
                </label>
                &nbsp;&nbsp;
                <label class="control-label"><spring:message code="workOrders.good" /></label>
              </c:if>
              <c:if test="${repairs.grade==5}">
                <label class="control-label">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favorfill.png">
                  <img src="../static/img/iconfont-favorfill.png">
                </label>
                &nbsp;&nbsp;
                <label class="control-label"><spring:message code="workOrders.veryGood" /></label>
              </c:if>
            </div>
          </div>
          <%-- 调查问卷 --%>
          <c:if test="${not empty repairServiceQuestionList}">
            <div class="form-group col-lg-12 flowersList">
              <c:forEach items="${repairServiceQuestionList}" var="repairServiceQuestion">
                <div class=" col-lg-12" style="margin-left: 20.3%;">
                  <label class="col-sm-8 control-label" style="text-align: left;">${repairServiceQuestion.questionContent}：
                  <c:if test="${repairServiceQuestion.questionType eq '1'}">
                    <%-- 评分星级 --%>
                      <c:if test="${repairServiceQuestion.scoreLev==0 || repairServiceQuestion.scoreLev==null}">
                        <label class="control-label">
                          <img src="../static/img/iconfont-favor.png">
                          <img src="../static/img/iconfont-favor.png">
                          <img src="../static/img/iconfont-favor.png">
                          <img src="../static/img/iconfont-favor.png">
                          <img src="../static/img/iconfont-favor.png">
                        </label>
                      </c:if>
                      <c:if test="${repairServiceQuestion.scoreLev==1}">
                        <label class="control-label">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favor.png">
                          <img src="../static/img/iconfont-favor.png">
                          <img src="../static/img/iconfont-favor.png">
                          <img src="../static/img/iconfont-favor.png">
                        </label>
                        &nbsp;&nbsp;
                        <label class="control-label"><spring:message code="workOrders.veryBad" /></label>
                      </c:if>
                      <c:if test="${repairServiceQuestion.scoreLev==2}">
                        <label class="control-label">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favor.png">
                          <img src="../static/img/iconfont-favor.png">
                          <img src="../static/img/iconfont-favor.png">
                        </label>
                        &nbsp;&nbsp;
                        <label class="control-label"><spring:message code="workOrders.bad" /></label>
                      </c:if>
                      <c:if test="${repairServiceQuestion.scoreLev==3}">
                        <label class="control-label">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favor.png">
                          <img src="../static/img/iconfont-favor.png">
                        </label>
                        &nbsp;&nbsp;
                        <label class="control-label"><spring:message code="workOrders.general" /></label>
                      </c:if>
                      <c:if test="${repairServiceQuestion.scoreLev==4}">
                        <label class="control-label">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favor.png">
                        </label>
                        &nbsp;&nbsp;
                        <label class="control-label"><spring:message code="workOrders.good" /></label>
                      </c:if>
                      <c:if test="${repairServiceQuestion.scoreLev==5}">
                        <label class="control-label">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favorfill.png">
                          <img src="../static/img/iconfont-favorfill.png">
                        </label>
                        &nbsp;&nbsp;
                        <label class="control-label"><spring:message code="workOrders.veryGood" /></label>
                      </c:if>
                  </label>
                  </c:if>
                  <c:if test="${repairServiceQuestion.questionType eq '2'}">
                    <%-- 意见/建议 --%>
                    <label class="control-label">建议内容：${repairServiceQuestion.suggestion}</label>
                  </c:if>
                </div>
              </c:forEach>
            </div>
          </c:if>
          <%--<div class="form-group col-lg-12">
            <label class="col-sm-3 control-label"><spring:message code="workOrders.evaluateContent" /></label>
            <div class="col-sm-6" style="padding: 0;">
              <textarea class="form-control" readonly="true">${repairs.evaluateContent}</textarea>
            </div>
          </div>--%>
          <div class="text-center form-group  col-lg-12" style="padding:0;">
            <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);"><spring:message code="common_cancel"/></button>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>
