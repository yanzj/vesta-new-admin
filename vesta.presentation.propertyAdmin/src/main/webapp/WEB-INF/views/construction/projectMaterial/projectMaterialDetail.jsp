<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

  <script type="text/javascript" charset="utf-8">
    window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
  </script>
  <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>

  <!-- Bootstrap Core CSS -->
  <link href="../../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
  <link href="../../../../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
  <!-- Custom CSS -->
  <link href="../../../../static/css/style.css" rel='stylesheet' type='text/css'/>
  <link href="../../../../static/css/page/page.css" rel='stylesheet' type='text/css'/>
  <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>
  <!-- font CSS -->
  <!-- font-awesome icons -->
  <link href="../../../../static/css/font-awesome.css" rel="stylesheet">
  <!-- //font-awesome icons -->
  <!-- js-->
  <script src="../../../../static/js/jquery-1.11.1.min.js"></script>
  <script src="../../../../static/js/modernizr.custom.js"></script>

  <script type="text/javascript" src="../../../../static/plus/js/jquery.validate.js"></script>
  <link rel="stylesheet" href="../../../../static/css/detailsCss.css"/>
  <!--animate-->
  <link href="../../../../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
  <script src="../../../../static/js/wow.min.js"></script>
  <script>
    $(function () {
      console.log("sqq")
      $("#002900010000").addClass("active");
      $("#002900010000").parent().parent().addClass("in");
      $("#002900010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../../../../static/js/metisMenu.min.js"></script>
  <script src="../../../../static/js/custom.js"></script>
  <link href="../../../../static/css/custom.css" rel="stylesheet">
  <link href="../../../../static/css/target.css" rel="stylesheet">
  <!--//Metis Menu -->
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="002900010000" username="${authPropertystaff.staffName}"/>
  <div class="newDetail__">
    <div class="nav">
      <div class="rightButton">
        <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
        <td>
          <c:if test="${material.state ne '已退场' &&material.state ne '非正常关闭' && checkAuthFunction.esh40020099 eq 'Y'}">
            <a style="cursor: pointer" onClick="forCloseQuestion()" target=_blank class="btn btn-primary"><spring:message code="inspection.close" /></a>
          </c:if>
        </td>
      </div>
    </div>
    <div class="notice_inner" style="padding: 0;margin:0;width: auto">
      <h2 class="keyComTitle">${material.batchName}</h2>

      <div class="container" style="width: 1026px;padding: 0;">
        <div class="col-lg-12"style=" padding: 0 0 15px;">
          <div class="repair_per col-sm-4">
            <span class="key1">创建人：</span>
            <span class="value_">${material.createName}</span>
          </div>
          <div class="repair_per col-sm-4">
            <span class="key1">创建时间：</span>
            <span class="value_">${material.createOn}</span>
          </div>
          <div class="repair_per col-sm-4">
            <span class="key1">问题状态：</span>
            <span class="value_">${material.state}</span>
          </div>
          <div class="repair_per col-sm-4">
            <span class="key1">所属项目：</span>
            <span class="value_">${material.projectName}</span>
          </div>
          <div class="repair_per col-sm-4">
            <span class="key1">检查项：</span>
            <span class="value_">${material.classifyTwoName}</span>
          </div>
          <div class="repair_per col-sm-4">
            <span class="key1">进场时间：</span>
            <span class="levels">${material.approachTime}</span>
          </div>
          <div class="repair_per col-sm-4">
            <span class="key1">进场批量：</span>
            <span class="levels">${material.approachNumber}</span>
          </div>
          <div class="repair_per col-sm-4">
            <span class="key1">甲方负责人：</span>
            <span class="value_">${material.firstPartyName}</span>
          </div>
          <div class="repair_per col-sm-4">
            <span class="key1">第三方监理：</span>
            <span class="value_">${material.supervisorName}</span>
          </div>
          <div class="repair_per col-sm-4">
            <span class="key1">责任单位：</span>
            <span class="value_">${material.supplierName}</span>
          </div>
          <div class="repair_per col-sm-4">
            <span class="key1">材料负责人：</span>
            <span class="value_">${material.assignName}</span>
          </div>
          <div class="repair_per col-sm-4">
            <span class="key1">使用部位：</span>
            <span class="value_">${material.usedPart}</span>
          </div>
          <c:if test="${material.state eq '非正常关闭'}">
            <div class="repair_per col-sm-4">
              <span class="key1">关单人：</span>
              <span class="value_">${material.shutDownBy}</span>
            </div>
            <div class="repair_per col-sm-4">
              <span class="key1">关单时间：</span>
              <span class="value_">${material.shutDownOn}</span>
            </div>
            <c:if test="${fn:length(material.shutDown) >= 20}" >
              <div class="repair_per col-sm-4" style="height: 55px;">
                <span class="key1" >关单描述：</span>
                <div class="col-sm-8">
                  <textarea id="shutDown" name="shutDown" class="form-control" disabled="disabled" style="resize: none;">${material.shutDown}</textarea>
                </div>
              </div>
            </c:if>
            <c:if test="${fn:length(material.shutDown) < 20}">
              <div class="repair_per col-sm-4">
                <span class="key1">关单描述：</span>
                <span class="value_">${material.shutDown}</span>
              </div>
            </c:if>
          </c:if>

          <ul class="gallery">
            <div class="col-lg-12" style="width: 1005px;border-top: 1px dashed #ccc;margin-top: 20px;">
              <c:forEach items="${material.detailsList}" var="item" varStatus="status">
                <div class="col-lg-12">
                  <div class="repair_per col-sm-6 targetHeader" style="margin-left: -60px;font-size: 16px;">
                    <span class="key1 span" style="margin-top: 10px;">检查指标：</span>
                  </div>
                </div>
                <div class="col-sm-12">
                  <div class="team-member">
                    <div class="col-sm-4 target" style="margin-left: -70px">
                      <h4  class="keyPress" style=" width: 800px;">${item.targetName}</h4>
                      <c:choose>
                        <c:when test="${item.imageUrl != null && item.imageUrl !=''}">
                          <li style="margin-top: -10px !important;">
                            <a href="${item.imageUrl}">
                                                        <span class="imgSpan"
                                                              style="background:url(${item.imageUrl}) no-repeat center center;background-size:cover;"></span>
                            </a>
                          </li>
                        </c:when>
                        <c:otherwise>
                          <span class="imgSpan"></span>
                        </c:otherwise>
                      </c:choose>
                    </div>
                    <div class="col-sm-6 "style="margin-top: 35px;width: 550px;">
                      <p class="text-mutedKey2 status">${item.isQualified}</p>

                      <p class="text-mutedKey">${item.description}</p>
                    </div>
                      <%--</c:if>--%>
                    <div class="col-sm-2">
                      <input id="des${status.index+1}" type="hidden"
                             value="${item.guide}">

                      <p id="${status.index+1}" style="cursor: pointer;color: #0044cc"
                         onclick="javascript:getDetail('${item.targetName}','des${status.index+1}');"
                         data-toggle="modal">指引</p>
                    </div>
                  </div>
                </div>
              </c:forEach>
            </div>
          </ul>

          <c:if test="${material.description ne ''}">
            <div class="repair_per col-sm-12">
              <span class="key1">退场时间：</span>
              <span class="value_">${material.outTime}</span>
            </div>
            <c:if test="${fn:length(material.description) >= 20}" >
              <div class="repair_per col-sm-12" style="height: 55px;">
                <span class="key1" >退场描述：</span>
                <div class="col-sm-8">
                  <textarea id="description" name="description" class="form-control" disabled="disabled" style="resize: none;">${material.description}</textarea>
                </div>
              </div>
            </c:if>
            <c:if test="${fn:length(material.description) < 20}">
              <div class="repair_per col-sm-12">
                <span class="key1">退场描述：</span>
                <span class="value_">${material.description}</span>
              </div>
            </c:if>
            <div  class="repair_per col-sm-12">
              <ul class="gallery" >
                <span class="key1">照片：</span>
                <li>
                  <c:forEach items="${material.imageList}" var="image">
                    <a href="${image.imageUrl}">
                      <label class="control-label imgList"><img src="${image.imageUrl}"  height="100" width="200"></label>
                    </a>
                  </c:forEach>
                </li>
              </ul>
            </div>
          </c:if>
        </div>
      </div>
    </div>
    <div class="clearfix"></div>
  </div>
  <%--指引模态框--%>
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
       aria-hidden="true" style="top: 70px">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span
                  aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title" id="myModalLabel">Modal title</h4>
        </div>
        <div class="modal-body" id="mo-content">

        </div>
      </div>
    </div>
  </div>
  <%--<!-- 非正常关闭模态框（Modal） -->--%>
  <div class="modal fade " id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
       aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">
            <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
          </button>
          <h4 class="modal-title" id="myModalLabel1">
            <spring:message code="problem.forceClosese"/></h4>
        </div>
        <div class="modal-body">
          <table>
            <tr>
              <td>
                <label class="" for="closes">
                  <spring:message code="problem.closess"/> ：
                </label>
              </td>
              <td style="width: 88%;">
                <input type="text" class="form-control" placeholder="" id="closes" name="closes"
                       value="">
              </td>
            </tr>
          </table>
        </div>
        <div class="modal-footer">
          <%-- 取消 --%>
          <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message
                  code="project.cancel"/></button>
          <%-- 确定 --%>
          <button type="button" class="btn btn-primary"
                  onclick="forceCloseQuestion('${material.materialId}')">
            <spring:message code="project.confirm"/></button>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="../../../../static/js/zoom.min.js"></script>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script type="text/javascript" src="../../../../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../../../../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script>
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
<script>
  function getDetail(titel, tid) {
    var desc = $('#' + tid).val();
    $("#myModalLabel").html(titel);
    $("#mo-content").html(desc);
    $("#myModal").modal({
      backdrop: false,
      show: true
    });
  }
  function closeQuestionModel() {
//        $("#myModalLabel").html(titel);
//        $("#mo-content").html(description);
    $("#myModal1").modal({
      backdrop: false,
      show: true
    });
  }

  function showDiv(targetid) {
    var target = document.getElementById(targetid);
    if (target.style.display == "block") {
      target.style.display = "none";
    } else {
      target.style.display = "block";
    }
  }

  function forceCloseQuestion(materialId){
    var str = $("#closes").val();
    if(str){
      window.location.href = "../ProjectMaterial/shutDown?materialId="+materialId+"&shutDown="+str;
    }else{
      alert("关闭原因不能为空！！");
      forCloseQuestion();
    }
  }

  function forCloseQuestion(){
    $(function () { $('#myModal1').modal({
      keyboard: true
    })});
  }


</script>
</body>
</html>