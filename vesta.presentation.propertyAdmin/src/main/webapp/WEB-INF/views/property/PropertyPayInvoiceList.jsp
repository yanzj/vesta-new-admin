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
<style type="text/css">
  .form_b{
    height: 12.5rem;
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

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="005700020000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body form_b">
        <form id="form" class="form-horizontal" action="../propertyPayment/propertyPayInvoiceList.html">
          <input type="hidden" id="menuId" name="menuId" value="005700020000"/>
          <!-- 城市 -->
          <div class="form-group  col-xs-4">
            <label for="scopeId" class="col-sm-4 control-label">区域</label>
            <div class="col-sm-8">
              <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                <option value="">请选择</option>
                <c:forEach items="${city}" var="item" >
                  <option value="${item.cityId }"
                          <c:if test="${item.cityId eq propertyPayOrderDTO.scopeId}">selected</c:if>>${item.cityName }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 项目 -->
          <div class="form-group  col-xs-4">
            <label for="projectCode" class="col-sm-4 control-label">项目</label>
            <div class="col-sm-8">
              <select id="projectCode" name="projectCode" class="form-control">
                <c:forEach items="${projectList}" var="item" >
                  <option value="${item[0] }"
                          <c:if test="${item[0] eq propertyPayOrderDTO.projectCode}">selected</c:if>>${item[1] }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <!-- 房间号 -->
          <div class="form-group  col-xs-4">
            <label for="address" class="col-sm-4 control-label">房间号</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="address"
                     name="address" value="${propertyPayOrderDTO.address}">
            </div>
          </div>
          <!-- 发票抬头 -->
          <div class="form-group  col-xs-4">
            <label for="invoiceHeader" class="col-sm-4 control-label">发票抬头</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" placeholder="" id="invoiceHeader"
                     name="invoiceHeader" value="${propertyPayOrderDTO.invoiceHeader}">
            </div>
          </div>
          <!-- 票据状态 -->
          <div class="form-group  col-xs-4">
            <label for="invoiceState" class="col-sm-4 control-label">票据状态</label>
            <div class="col-sm-8">
              <select id="invoiceState" name="invoiceState" class="form-control">
                <option value="">--请选择--</option>
                <option value="0" <c:if test="${propertyPayOrderDTO.invoiceState eq '0'}">selected</c:if>>未开票</option>
                <option value="1" <c:if test="${propertyPayOrderDTO.invoiceState eq '1'}">selected</c:if>>已开票</option>
              </select>
            </div>
          </div>
          <div class="form-group  col-xs-12 search_button">
            <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                    code="propertyCompany.propertySearch"/></button>
          </div>

        </form>
      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <form id="batchForm" class="form-horizontal" action="../staffUser/toBatchSetRole">
      <table width="100%" class="tableStyle">
        <thead>
        <tr>
          <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
          <th>房间号</th>
          <th>收费项目名称</th>
          <th>发票类型</th>
          <th>金额</th>
          <th>发票抬头</th>
          <th>缴费人</th>
          <th>缴费时间</th>
          <th>开票状态</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${payInvoiceList}" var="payInvoice" varStatus="row">
          <tr>
            <td><b>${row.index + 1}</b></td>
            <td>${payInvoice.address}</td>
            <td>${payInvoice.IpItemName}</td>
            <td><c:if test="${payInvoice.invoiceType eq '1'}">普通发票</c:if></td>
            <td>${payInvoice.totalFee}</td>
            <td>${payInvoice.invoiceHeader}</td>
            <td>${payInvoice.createBy}</td>
            <td>${payInvoice.createDate}</td>
            <td>
              <c:if test="${payInvoice.invoiceState eq '0'}">未开票</c:if>
              <c:if test="${payInvoice.invoiceState eq '1'}">已开票</c:if>
            </td>
            <td class="last">
              <c:if test="${payInvoice.invoiceState eq '0'}">
                <a href="javascript:void(0);" onclick="toDetails('${payInvoice.invoiceId}')" id="detail"
                   class="a3">开票</a>
              </c:if>
              <c:if test="${payInvoice.invoiceState eq '1'}">
                <a href="javascript:void(0);" onclick="toDetails('${payInvoice.invoiceId}')" id="detail"
                   class="a3">详情</a>
              </c:if>

            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </form>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/propertyPayment/propertyPayInvoiceList.html?menuId=005700020000&pageIndex={0}&scopeId=${propertyPayOrderDTO.scopeId}&projectCode=${propertyPayOrderDTO.projectCode}&address=${propertyPayOrderDTO.address}&cstName=${propertyPayOrderDTO.cstName}&invoiceState=${propertyPayOrderDTO.invoiceState}"/>
  </div>

</div>


<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
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
<script>
  //通过城市获取项目列表
  function queryProjectNameById() {
    var projectId = $("#scopeId").val();
    if(projectId == '-1'){
      $("#projectCode").empty();
      return ;
    }
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
      var arr = data.data;
      $("#projectCode").empty();
      $("#projectCode").append('<option value="">请选择</option>');
      for (var k in arr) {
        if(arr[k][0] != '0'){
          $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
        }
      }
    });
  }
  //详情
  function toDetails(invoiceId){
    $.ajax({
      type: "GET",
      url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
      cache: false,
      async:false,
      dataType:"json",
      success: function (data) {
        if (data.error == 1) {
          window.location.href = "../propertyPayment/propertyPayInvoiceInfo.html?invoiceId="+invoiceId;
        }else if(data.error == 0) {
          alert("对不起，您无权限执行此操作！");
        }else{
          alert("对不起，操作失败！");
        }
      }
    });
  }
</script>
</body>
</html>