<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
  </style>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="005700020000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">

      <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>

        <div class="form-body" style="min-height:680px">
          <form class="form-horizontal" id="frm" action="../propertyPayment/updateInvoice" method="post"
                enctype="MULTIPART/FORM-DATA">
            <input type="hidden" id="error" value="${error}"/>
            <input type="hidden" id="staffIdDto" name="staffIdDto" value="${staffUser.staffIdDto}"/>
            <%--支付订单--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label">支付订单</label>
              <div class="col-sm-7">
                <%--<input type="text" class="form-control" placeholder="" value="${info.payInvoice.paymentId}" disabled>--%>
                <label class="control-label" style="text-align: left;">${info.payInvoice.paymentId}</label>
              </div>
            </div>
            <%--缴费人--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label">缴费人</label>
              <div class="col-sm-7">
                <%--<input type="text" class="form-control" placeholder="" value="${info.payment.createBy}" disabled>--%>
                <label class="control-label" style="text-align: left;">${info.payment.createBy}</label>
              </div>
            </div>
            <%--缴费时间--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label">缴费时间</label>
              <div class="col-sm-7">
                <%--<input type="text" class="form-control" placeholder="" value="${info.payment.createDate}" disabled>--%>
                <label class="control-label" style="text-align: left;">${info.payment.createDate}</label>
              </div>
            </div>
            <%--缴费明细--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label">缴费明细</label>
              <div class="col-sm-7">
                <c:forEach items="${info.payOrderLogList}" var="payOrderLog" >
                  <label class="control-label" style="text-align: left;">${payOrderLog.revAbstract}</label><br/>
                </c:forEach>
              </div>
            </div>
            <%--发票状态--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label">发票状态</label>
              <div class="col-sm-7">
                <%--
                <input type="text" class="form-control" placeholder="" value="<c:if test="${info.payInvoice.invoiceState eq '0'}">未开票</c:if><c:if test="${info.payInvoice.invoiceState eq '1'}">已开票</c:if>" disabled>
                --%>
                <label class="control-label" style="text-align: left;"><c:if test="${info.payInvoice.invoiceState eq '0'}">未开票</c:if><c:if test="${info.payInvoice.invoiceState eq '1'}">已开票</c:if></label>
              </div>
            </div>
            <%--发票类型--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label">发票类型</label>
              <div class="col-sm-7">
                <%--<input type="text" class="form-control" placeholder="" value="<c:if test="${info.payInvoice.invoiceType eq '1'}">普通发票</c:if>" disabled>--%>
                  <label class="control-label" style="text-align: left;"><c:if test="${info.payInvoice.invoiceType eq '1'}">普通发票</c:if></label>
              </div>
            </div>
            <%--发票抬头--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label">发票抬头</label>
              <div class="col-sm-7">
                <%--<input type="text" class="form-control" placeholder="" value="${info.payInvoice.invoiceHeader}" disabled/>--%>
                <label class="control-label" style="text-align: left;">${info.payInvoice.invoiceHeader}</label>
              </div>
            </div>
            <%--发票收取方式--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label">发票收取方式</label>
              <div class="col-sm-7">
                <label class="control-label" style="text-align: left;">
                <c:if test="${info.payInvoice.invoiceMail eq 'ONESELF'}">自取</c:if>
                <c:if test="${info.payInvoice.invoiceMail eq 'BUTLER'}">管家递送</c:if>
                <c:if test="${info.payInvoice.invoiceMail eq 'EXPRESS'}">快递</c:if>
                </label>
              </div>
            </div>
            <%--快递地址--%>
            <c:if test="${info.payInvoice.invoiceMail eq 'EXPRESS'}">
              <div class="form-group col-lg-10">
                <label class="col-sm-3 control-label">快递地址</label>
                <div class="col-sm-7">
                  <%--<input type="text" class="form-control" placeholder="" value="${info.payInvoice.expressAddress}" disabled>--%>
                  <label class="control-label" style="text-align: left;">${info.payInvoice.expressAddress}</label>
                </div>
              </div>
            </c:if>
            <%--发票号码--%>
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label">发票号码</label>
              <div class="col-sm-5">
                <input type="text" class="form-control" placeholder="" id="invoiceNum" name="invoiceNum" value="${info.payInvoice.invoiceNum}">
              </div>
            </div>
            <input type="hidden" name="invoiceId" id="invoiceId" value="${info.payInvoice.invoiceId}">
            <%--密码--%>
            <%--
            <div class="form-group col-lg-10">
              <label class="col-sm-3 control-label" for="passwordDto">密码 </label>
              <div class="col-sm-7">
                <c:if test="${!empty staffUser.passwordDto and staffUser.passwordDto ne ''}">
                  <input type="text" class="form-control" placeholder="" id="passwordDto" name="passwordDto" value="******" readonly>
                </c:if>
                <c:if test="${empty staffUser.passwordDto}">
                  <input type="text" class="form-control" placeholder="" id="passwordDto" name="passwordDto" value="">
                </c:if>
                <p style="color: red">
                  <span>您可以选择不输入密码，系统将设置默认密码为：123456</span>
                </p>
              </div>
              <br/>
            </div>
            --%>
            <%--取消,确定--%>
            <div class="form-group  col-lg-8">
              <label class="col-sm-3 control-label"></label>
              <div class="col-sm-7">
                <button type="button" class="btn btn-primary" onclick="jq_submit()">确定</button>
                <a href="" onclick="javaScript:history.go(-1)" class="btn btn-primary" for="">取消</a>
              </div>
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
<script>
  $(function(){

  });
  function jq_submit(){
    $.ajax({
      type: "POST",
      url: "../propertyPayment/updateInvoice",
      cache: false,
      async:false,
      data: {
        invoiceId:$("#invoiceId").val(),
        invoiceNum:$("#invoiceNum").val()
      },
      dataType:"json",
      success: function (data) {
        if (data.error == 1) {
          alert("录入发票失败！");
        }else if(data.error == 0) {
          alert("录入发票成功！");
          window.location.href = "../propertyPayment/propertyPayInvoiceList.html?menuId=005700010000";
        }
      }
    });
  }
</script>

</body>
</html>
