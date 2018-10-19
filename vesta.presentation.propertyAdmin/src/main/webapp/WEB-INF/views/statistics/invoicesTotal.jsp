<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/6/3
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
  <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="keywords" content=""/>
  <script type="application/x-javascript">
    addEventListener("load",
            function () {
              setTimeout(hideURLbar, 0);
            }, false);
    function hideURLbar() {
      window.scrollTo(0, 1);
    }
  </script>
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
  <script src="../static/property/js/propertyRepair.js"></script>
  <style>
    .tableStyle thead td,.tableStyle thead th{
      padding-left: 0;
      text-align: center;
    }
    .search_button{
      text-align: center;
    }
    .control_btn{
      padding: 0 0 1rem 0;
      background-color: #fbfbfb;
    }
    .control_btn button,.control_btn a{
      margin-right: 1rem;
    }
  </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005800060000" username="${propertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <form class="form-horizontal" action="../click/invoicesTotal.html" modelAttribute="invoiceSearch" method="get">
          <%--<div class="form-group  col-lg-5">
            <label class="col-sm-4 control-label" for="city"><spring:message code="propertyServices.city" />：</label>
            <div class="col-sm-8">
              &lt;%&ndash;<input type="hidden" id="cityName" name="cityName"/>&ndash;%&gt;
              <select id="city" name="city" class="form-control" onchange="chooseCity()">
                <c:forEach items="${invoiceSearch.cityMap}" var="item">
                  <option value="${item.key}" <c:if test="${item.key eq invoiceSearch.city}">selected</c:if>>${item.value}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group  col-lg-5">
            <label class="col-sm-4 control-label" for="projectName"><spring:message code="propertyCompany.propertyProject" />：</label>
            <div class="col-sm-8">
              &lt;%&ndash;<input type="hidden" id="projectId" name="projectId"/>&ndash;%&gt;
              &lt;%&ndash;<form:select items="${invoiceSearch.projectMap}" id="projectName" name="projectName" class="form-control">
              </form:select>&ndash;%&gt;
                <select id="projectName" name="projectName" class="form-control">
                  <option value="0">---请选择项目---</option>
                  <c:forEach items="${project}" var="item">
                    <option value="${item.id}" <c:if test="${item.id eq invoiceSearch.projectName}">selected</c:if>>${item.name}</option>
                  </c:forEach>
                </select>
            </div>
          </div>--%>
          <div class="form-group  col-xs-5">
            <label for="startTime" class="col-sm-4 control-label"><spring:message code="ownerManage.SelBeginTime"/></label>
            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="请输入统计开始时间" id="startTime" name="startTime" value="${invoiceSearch.startTime}" readonly="true" />
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="form-group  col-xs-5">
            <label for="endTime" class="col-sm-4 control-label"><spring:message code="HousePayBatch.to"/></label>
            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
              <input type="text" class="form-control" placeholder="请输入统计结束时间" id="endTime" name="endTime" value="${invoiceSearch.endTime}" readonly="true"/>
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
          </div>
          <div class="search_button  col-xs-12">
            <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="common_search"/></button>
            <!--集合长度(取决Excel是否可以导出)-->
            <input type="hidden" id="size" value="${isExcel}">
            <a href="javascript:void(0);"  onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>

          </div>


          <div class="clearfix"></div>
        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <div class="">
    </div>
    <table width="100%" class="tableStyle table-striped">
      <thead>
      <tr>
        <td style="width:6%;text-align:center"><spring:message code="common_sort" /></td>
        <th><spring:message code="userFeed.time" /></th>
        <th><spring:message code="clickTimes.repair" /></th>
        <th><spring:message code="clickTimes.feedBack" /></th>
        <th><spring:message code="clickTimes.vistor" /></th>
        <th><spring:message code="clickTimes.payment" /></th>
        <th><spring:message code="clickTimes.total" /></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${invoices}" var="invoices" varStatus="row">
        <tr>
          <td><b>${row.index + 1}</b></td>
          <td>${invoices.startTime}</td>
          <td>${invoices.repairNum}</td>
          <td>${invoices.feedbackNum}</td>
          <td>${invoices.visitorNum}</td>
          <td>${invoices.paymentNum}</td>
          <td>${invoices.total}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/click/invoicesTotal.html?pageIndex={0}&city=${invoiceSearch.city}&projectName=${invoiceSearch.projectName}&startTime=${invoiceSearch.startTime}&endTime=${invoiceSearch.endTime}"/>
  </div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
  var pages = "${webPage.pageIndex}";
  var city = "${invoiceSearch.city}";
  var projectName = "${invoiceSearch.projectName}";
  var startTime = "${invoiceSearch.startTime}";
  var endTime = "${invoiceSearch.endTime}";
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

  function chooseCity(){
    $("#projectId").empty();
    $("#projectName").empty();
    var citys=$("#city").val();
    var project="";
    $.ajax({
      url: "../click/projectShow",
      type: "post",
      async: "false",
      dataType: "json",
      data: {
        "city": citys,
      },
      success: function (json) {
        <!-- 获取返回代码 -->
        var code = json.code;
        if (code != 0) {
          var errorMessage = json.msg;
          alert(errorMessage);
        } else {
          <!-- 获取返回数据 -->
          var data = json.data;
          $("#projectName").append("<option id='0' value='0'>"+"---请选择项目---"+"</option>");
          for(var n=0;n<data.length;n++){
            var ids=data[n].id;
            var names=data[n].name;
            $("#projectName").append("<option id='"+ids+"' value='"+ids+"'>"+names+"</option>");
          }
          project = $("#projectName").val();
        }
      },
    });
  }

  function isExcel(){
    var size = $("#size").val();
    if(size>0){
      var href = "../click/exportExcel?type=2&pageIndex={0}&city=${invoiceSearch.city}&projectName=${invoiceSearch.projectName}&startTime=${invoiceSearch.startTime}&endTime=${invoiceSearch.endTime}";
      window.location.href = href;
    }else{
      alert("没有可以导出的数据");
    }
  }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>