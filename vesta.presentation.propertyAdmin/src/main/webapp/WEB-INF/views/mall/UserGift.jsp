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
        height: 7rem;
    }
    .search_buttonStyle{

    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#006700040000").addClass("active");
        $("#006700040000").parent().parent().addClass("in");
        $("#006700040000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006700040000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="">
                    <input type="hidden" id="menuId" name="menuId" value="006700040000"/>
                    <%-- 商品类目名称 --%>

                    <div class="form-group  col-xs-4">
                        <div class="col-sm-8">
                            <label class="col-sm-8 control-label">积分</label>
                            <input  type="radio" name="giftType"  <c:if test="${gift.giftType eq '1'}">checked</c:if> value="1" style="float:left;margin: 9px 0 0 55px;">
                        </div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" placeholder="" id="number" name="number" value="${gift.number}">
                        </div>

                    </div>
                    <div class="form-group  col-xs-4">
                        <div class="col-sm-8">
                            <label class="col-sm-8 control-label">商品</label>
                            <input  type="radio" name="giftType"  <c:if test="${gift.giftType eq '2'}">checked</c:if> value="2" style="float:left;margin: 9px 0 0 55px;">
                        </div>
                    </div>
                    <div class="col-xs-12 search_button search_buttonStyle">
                            <button type="button" class="btn btn-primary" id="add" onclick="toSave('','${gift.userGiftId}')">保存</button>
                    </div>

                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow" style="float: left;margin-left:5%">
        <form id="batchForm" class="form-horizontal" action="#">
            <table width="100%" class="tableStyle">
                <thead>
                <tr>
                    <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                    <th>商品名称</th>
                    <th>选择</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${mall}" var="mall" varStatus="row">
                    <tr>
                        <td><b>${row.index + 1}</b></td>
                        <td>${mall.productName}</td>
                        <td class="last">
                            <c:if test="${mall.productId eq gift.productId}">
                                <a href="javascript:void(0);"  id="disable"
                                   class="a3" style="color: red;">已选用</a>
                            </c:if>
                            <c:if test="${mall.productId != gift.productId}">
                                <a href="javascript:void(0);" onclick="toSave('${mall.productId}','${gift.userGiftId}')" id="edit"
                                   class="a3">选择</a>
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
                 submitUrl="${pageContext.request.contextPath }/userGift/getUserGift.html?menuId=006700040000&pageIndex={0}"/>
    </div>
</div>
</div>

<script>


    //保存
    function toSave(productId,id){
        var formData = new FormData();
        var isCardCoupons = $('input[name="giftType"]:checked').val();
        if(isCardCoupons == undefined){
            alert("请选赠送类型！");
            return;
        }

        formData.append("giftType",isCardCoupons);
        var numb = $("#number").val()

        if(isCardCoupons == '1'){
            //积分
            if(numb == "" || numb == null){
                alert("请输入积分！");
                return;
            }
            if(productId != "" ){
                alert("请选择商品类型！");
                return;
            }

        }

        if(isCardCoupons == '2'){
            //商品
            if(productId == "" || productId == null){
                alert("请选择积分类型！");
                return;
            }

        }
        formData.append("number",numb);
        formData.append("productId",productId);
        formData.append("userGiftId",id);


        $.ajax({
            url:"../userGift/toSaveUserGift",
            type:"POST",
            async:"false",
            data:formData,
            processData:false,
            contentType:false,
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(data){
                alert("保存成功！");
                window.location.href = "../userGift/getUserGift.html?menuId=006700040000";
            }
        });
    }




</script>
</body>
</html>