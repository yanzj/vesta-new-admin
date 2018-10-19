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
    $(function () {
      console.log("sqq")
      $("#005700010000").addClass("active");
      $("#005700010000").parent().parent().addClass("in");
      $("#005700010000").parent().parent().parent().parent().addClass("active");
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
               crunMenu="005700010000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>

        <div class="form-body" style="min-height:680px">
          <form class="form-horizontal" id="frm" action="../staffUser/saveOrUpdateStaffUser" method="post"
                enctype="MULTIPART/FORM-DATA">
            <%--房间号--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">房间号</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${propertyPayOrder.address}</label>
              </div>
            </div>
            <%--缴费详情--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">缴费详情</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label" style="text-align: left;">
                  <c:if test="${!empty propertyPayOrder.PRemarks}">${propertyPayOrder.PRemarks}</c:if>
                  <c:if test="${empty propertyPayOrder.PRemarks}">${propertyPayOrder.RevAbstract}</c:if>
                </label>
              </div>
            </div>
            <%--单据状态--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">单据状态</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label" style="text-align: left;">
                  <c:if test="${propertyPayOrder.payOrderState eq 'NOPAY'}">未支付</c:if>
                  <c:if test="${propertyPayOrder.payOrderState eq 'HANDLE'}">处理中</c:if>
                  <c:if test="${propertyPayOrder.payOrderState eq 'SUCPAY'}">已支付</c:if>
                </label>
              </div>
            </div>
            <div class="borderSty form-group col-lg-12"></div>
            <%--业主--%>
            <%--<div class="form-group col-lg-10">
              <label class="col-sm-3 control-label">业主</label>
              <div class="col-sm-7">
                <label class="col-sm-7 control-label" style="text-align: left;">${propertyPayOrder.realName}</label>
              </div>
            </div>--%>
            <%--资源编码--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">资源编码</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${propertyPayOrder.SYResName}</label>
              </div>
            </div>
            <%--账单周期--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">账单周期</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${propertyPayOrder.RepYears}</label>
              </div>
            </div>
            <%--收费项目名称--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">收费项目名称</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${propertyPayOrder.IpItemName}</label>
              </div>
            </div>
            <%--金额--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">金额</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${propertyPayOrder.PriFailures}</label>
              </div>
            </div>
            <%--缴费人--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">缴费人</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${propertyPayOrder.createBy}</label>
              </div>
            </div>

            <%--支付单号--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">支付单号</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${propertyPayOrder.paymentId}</label>
              </div>
            </div>
            <%--支付方式--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">支付方式</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label" style="text-align: left;">
                  <c:if test="${propertyPayOrder.paymentType eq 'WECHAT_APP'}">微信</c:if>
                </label>
              </div>
            </div>
            <div class="borderSty form-group col-lg-12"></div>

            <%--缴费时间--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">缴费时间</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${propertyPayOrder.createDate}</label>
              </div>
            </div>

            <%--发票状态--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">发票状态</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label" style="text-align: left;">
                  <c:if test="${propertyPayOrder.invoiceState eq '0'}">未开票</c:if>
                  <c:if test="${propertyPayOrder.invoiceState eq '1'}">已开票</c:if>
                </label>
              </div>
            </div>
            <%--发票状态--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">发票类型</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label" style="text-align: left;">
                  <c:if test="${propertyPayOrder.invoiceType eq '1'}">普通发票</c:if>
                  <c:if test="${propertyPayOrder.invoiceType eq '2'}">增值专票</c:if>
                  <c:if test="${propertyPayOrder.invoiceType eq '3'}">普票已开</c:if>
                  <c:if test="${propertyPayOrder.invoiceType eq '4'}">专票已开</c:if>
                </label>
              </div>
            </div>
            <%--发票抬头--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">发票抬头</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${propertyPayOrder.invoiceHeader}</label>
              </div>
            </div>
            <%--发票收取方式--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">发票收取方式</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label" style="text-align: left;">
                  <c:if test="${propertyPayOrder.invoiceMail ne 'EXPRESS'}">
                    <c:if test="${propertyPayOrder.invoiceMail eq 'ONESELF'}">自取</c:if>
                    <c:if test="${propertyPayOrder.invoiceMail eq 'BUTLER'}">管家递送</c:if>
                  </c:if>
                  <c:if test="${propertyPayOrder.invoiceMail eq 'EXPRESS'}">快递   ${propertyPayOrder.expressAddress}</c:if>
                </label>
              </div>
            </div>
            <%--发票号码--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">发票号码</label>

              <div class="col-sm-5">
                <c:if test="${propertyPayOrder.invoiceState eq '0'}">
                  <input type="text" class="form-control" placeholder="" id="invoiceNum"
                         name="invoiceNum" value="${propertyPayOrder.invoiceNum}">
                </c:if>
                <c:if test="${propertyPayOrder.invoiceState eq '1'}">
                  <input type="text" class="form-control" placeholder="" id="invoiceNum"
                         name="invoiceNum" value="${propertyPayOrder.invoiceNum}" readonly>
                </c:if>
              </div>
            </div>
            <%--取消,确定--%>
            <div class="form-group  col-lg-12">
              <label class="col-sm-2 control-label" for=""></label>

              <div class="col-sm-7">
                <c:if test="${propertyPayOrder.invoiceState eq '0'}">
                  <button type="button" class="btn btn-primary"
                          onclick="updateInvoice('${propertyPayOrder.invoiceId}')">确定
                  </button>
                </c:if>
                <a href="../propertyPayment/propertyPayOrderList.html?menuId=005700010000"
                   class="btn btn-primary" for="">取消</a>
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
  //提交
  function jq_submit() {
    $("#frm").submit();
  }

  $().ready(function () {
    var validator = $("#frm").validate({
      errorElement: "span",
      rules: {
        userNameDto: {
          required: true
        },
        staffNameDto: {
          required: true
        },
        mobileDto: {
          required: true
        },
        email: {
          required: true
        }
      },
      messages: {
        userNameDto: {
          required: "请输入用户名称!"
        },
        staffNameDto: {
          required: "请输入姓名!"
        },
        mobileDto: {
          required: "请输入联系方式!"
        },
        email: {
          required: "请输入邮箱地址!"
        }
      }
    })
  });

  function updateInvoice(invoiceId) {
    $.ajax({
      type: "POST",
      url: "../propertyPayment/updateInvoice",
      cache: false,
      async: false,
      data: {
        invoiceId: invoiceId,
        invoiceNum: $("#invoiceNum").val()
      },
      dataType: "json",
      success: function (data) {
        if (data.error == 1) {
          alert("录入发票失败！");
        } else if (data.error == 0) {
          alert("录入发票成功！");
          window.location.href = "../propertyPayment/propertyPayOrderList.html?menuId=005700010000";
        }
      }
    });
  }

</script>
</body>
</html>
