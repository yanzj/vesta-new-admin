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
    .search_button{
        text-align: center;
    }
    .form_b{
        height: 12.5rem;
    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#006700030000").addClass("active");
        $("#006700030000").parent().parent().addClass("in");
        $("#006700030000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006700030000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../trade/tradeList.html">
                    <input type="hidden" id="menuId" name="menuId" value="006700030000"/>
                    <%-- 订单号 --%>
                    <div class="form-group  col-xs-4">
                        <label for="tradeNo" class="col-sm-4 control-label">订单号</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="tradeNo" name="tradeNo" value="${q.tradeNo}" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;">
                        </div>
                    </div>
                    <%-- 商品名称 --%>
                    <div class="form-group  col-xs-4">
                        <label for="productName" class="col-sm-4 control-label">商品名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="productName" name="productName" value="${q.productName}" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;">
                        </div>
                    </div>
                    <%-- 订单状态 --%>
                    <div class="form-group  col-xs-4">
                        <label for="tradeStatus" class="col-sm-4 control-label">商品状态</label>
                        <div class="col-sm-8">
                            <select id="tradeStatus" name="tradeStatus" class="form-control">
                                <option value="">--请选择状态--</option>
                                <option value="1" <c:if test="${'1' eq q.tradeStatus}">selected</c:if>>已付款</option>
                                <option value="2" <c:if test="${'2' eq q.tradeStatus}">selected</c:if>>已发货</option>
                            </select>
                        </div>
                    </div>
                    <%-- 业主 --%>
                    <div class="form-group  col-xs-4">
                        <label for="name" class="col-sm-4 control-label">业主</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="name" name="name" value="${q.name}" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;">
                        </div>
                    </div>
                    <%-- 联系方式 --%>
                    <div class="form-group  col-xs-4">
                        <label for="phone" class="col-sm-4 control-label">联系方式</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="phone" name="phone" value="${q.phone}" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;" >
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="weChatAppId" class="col-sm-4 control-label">公众号</label>
                        <div class="col-sm-8">
                            <select id="weChatAppId" name="weChatAppId" class="form-control">
                                <option value="">请选择公众号</option>
                                <c:forEach items="${defaultConfig}" var="defaultConfig" >
                                    <c:if test="${defaultConfig.weChatAppId != null}">
                                        <option value="${defaultConfig.weChatAppId }" <c:if test="${defaultConfig.weChatAppId eq q.weChatAppId}">selected</c:if>>${defaultConfig.clientName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <a href="javascript:void(0);" onclick="isExcel()" type="button" class="btn btn-primary"
                           for="propertyAdd">导出Excel</a>
                    </div>
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
                <th>订单号</th>
                <th>商品名称</th>
                <th>购买数量</th>
                <th>订单时间</th>
                <th>业主</th>
                <th>联系方式</th>
                <th>支付积分</th>
                <th>支付金额</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${list.tradeNo}</td>
                    <td>${list.productName}</td>
                    <td>${list.number}</td>
                    <td>${list.createOn}</td>
                    <td>${list.name}</td>
                    <td>${list.phone}</td>
                    <td>${list.amount}</td>
                    <td>${list.payment}</td>
                    <td>
                        <c:if test="${'1' eq list.tradeStatus}">已付款</c:if>
                        <c:if test="${'2' eq list.tradeStatus}">已发货</c:if>
                    </td>

                    <td class="last">
                        <c:if test="${'1' eq list.tradeStatus}"> <a href="javascript:void(0);" onclick="updateTrade('${list.tradeId}')" id="" class="a3">发货</a>
                        </c:if><a href="javascript:void(0);" onclick="toEdit('${list.tradeId}')" id="" class="a3">详情</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/trade/tradeList.html?menuId=006700030000&pageIndex={0}&tradeNo=${q.tradeNo}&productName=${q.productName}&name=${q.name}&phone=${q.phone}&tradeStatus=${q.tradeStatus}&weChatAppId=${q.weChatAppId}"/>
    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    //编辑
    function toEdit(id){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../trade/tradeDetail.html?menuId="+$("#menuId").val()+"&tradeId="+id;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }


    function updateTrade(id){
        var formData = new FormData();



        formData.append("tradeId",id);

        $.ajax({
            url:"../trade/updateTrade",
            type:"POST",
            async:"false",
            data:formData,
            processData:false,
            contentType:false,
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(data){
                alert("发货成功！");
                window.location.href = "../trade/tradeList.html?menuId=006700030000";
            }
        });
    }




    function isExcel() {
        var size = ${webPage.pageSize};
        if (size > 0) {
            var href = "../trade/exportTradeAllExcel?menuId=006700030000&tradeNo=${q.tradeNo}&productName=${q.productName}&name=${q.name}&phone=${q.phone}&tradeStatus=${q.tradeStatus}";
            window.location.href = href;
        }else {
            alert("没有可以导出的数据！");
        }
    }
</script>
</body>
</html>
