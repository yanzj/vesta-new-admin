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
      $("#006700030000").addClass("active");
      $("#006700030000").parent().parent().addClass("in");
      $("#006700030000").parent().parent().parent().parent().addClass("active");
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
               crunMenu="006700030000" username="${propertystaff.staffName}"/>
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>

        <div class="form-body" style="min-height:680px">
          <form class="form-horizontal" id="frm" action="" method="post"
                enctype="MULTIPART/FORM-DATA">
            <%--订单号--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">订单号</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${list.tradeNo}</label>
              </div>
            </div>
            <%--商品名称--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">商品名称</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label" style="text-align: left;">

            ${list.productName}
                </label>
              </div>
            </div>
            <%--商家名称--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">商家名称</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label" style="text-align: left;">

                 ${list.productBusiness }
                </label>
              </div>
            </div>
            <div class="borderSty form-group col-lg-12"></div>

            <%--下单时间--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">下单时间</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${list.createOn}</label>
              </div>
            </div>
            <%--购买数量--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">购买数量</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${list.number}</label>
              </div>
            </div>
            <%--商品单价--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">商品单价</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${list.productIntegral}</label>
              </div>
            </div>
            <%--支付金额--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">支付金额</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${list.amount}</label>
              </div>
            </div>

              <%--业主姓名--%>
              <div class="form-group col-lg-12">
                <label class="col-sm-2 control-label">业主姓名</label>

                <div class="col-sm-7">
                  <label class="col-sm-7 control-label"
                         style="text-align: left;">${list.userName}</label>
                </div>
              </div>
              <%--业主注册电话--%>
              <div class="form-group col-lg-12">
                <label class="col-sm-2 control-label">业主电话</label>

                <div class="col-sm-7">
                  <label class="col-sm-7 control-label"
                         style="text-align: left;">${list.userPhone}</label>
                </div>
              </div>

            <%--业主信息--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">订单姓名</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${list.name}</label>
              </div>
            </div>


            <%--联系方式--%>
            <div class="form-group col-lg-12">
              <label class="col-sm-2 control-label">联系方式</label>

              <div class="col-sm-7">
                <label class="col-sm-7 control-label"
                       style="text-align: left;">${list.phone}</label>
              </div>
            </div>

              <%--收货地址--%>
              <div class="form-group col-lg-12">
                <label class="col-sm-2 control-label">收货地址</label>

                <div class="col-sm-7">
                  <label class="col-sm-7 control-label"
                         style="text-align: left;">${list.address}</label>
                </div>
              </div>
              <%--备注--%>
              <div class="form-group col-lg-12">
                <label class="col-sm-2 control-label">备注</label>

                <div class="col-sm-7">
                  <label class="col-sm-7 control-label"
                         style="text-align: left;">${list.message}</label>
                </div>
              </div>
              <%--订单状态--%>
              <div class="form-group col-lg-12">
                <label class="col-sm-2 control-label">订单状态</label>

                <div class="col-sm-7">
                  <label class="col-sm-7 control-label"
                         style="text-align: left;">
                    <c:if test="${'1' eq list.tradeStatus}">已付款</c:if>
                    <c:if test="${'2' eq list.tradeStatus}">已发货</c:if>
                  </label>
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
          window.location.href = "../propertyPayment/listList.html?menuId=006700030000";
        }
      }
    });
  }

</script>
</body>
</html>
