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
        $("#006700020000").addClass("active");
        $("#006700020000").parent().parent().addClass("in");
        $("#006700020000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006700020000" username="${propertystaff.staffName}"/>
    <div class="forms">

    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <th>编码</th>
                <th>密码</th>
                <th>状态</th>
                <th>入库时间</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="l" varStatus="row">
                <tr>
                    <td>${l.code}</td>
                    <td>${l.pwd}</td>

                    <td>
                        <c:if test="${l.userId == null}">未出售</c:if>
                        <c:if test="${l.userId != null}"><span style="color: red">已出售</span></c:if>
                    </td>
                    <td>${l.createOn}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/integralMall/getCard.html?menuId=006700020000&pageIndex={0}&productId=${q.productId}"/>
    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    //编辑
    function toEdit(productId){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../integralMall/toEditProductInfo.html?menuId="+$("#menuId").val()+"&productId="+productId;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //删除
    function toDelete(productId){
        if (confirm("确认要删除？")) {
            $.ajax({
                type: "GET",
                url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
                cache: false,
                async:false,
                dataType:"json",
                success: function (data) {
                    if (data.error == 1) {
                        $.ajax({
                            url:"../integralMall/deleteProduct",
                            type:"POST",
                            cache:false,
                            async:false,
                            data:{
                                "productId":productId
                            },
                            dataType:"JSON",
                            error:function(){
                                alert("网络异常，可能服务器忙，请刷新重试");
                            },
                            success:function(data){
                                if(data.error == "0"){
                                    if(data.flag == 1){
                                        alert("删除成功！");
                                    }else if(data.flag == 2){
                                        alert("删除失败，礼物已使用该商品！");
                                    }else{
                                        alert("删除异常！");
                                    }
                                    location.reload();
                                }else if(data.error == "-1"){
                                    alert("删除失败，请联系管理员！");
                                }
                            }
                        });
                    }else if(data.error == 0) {
                        alert("对不起，您无权限执行此操作！");
                    }else{
                        alert("对不起，操作失败！");
                    }
                }
            });
        }
    }
    //状态变更
    function toChangeState(productId,state){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    $.ajax({
                        url:"../integralMall/updateProductState",
                        type:"POST",
                        cache: false,
                        async:false,
                        data:{
                            "productId":productId,
                            "productState":state
                        },
                        dataType:"json",
                        error:function(){
                            alert("网络异常，可能服务器忙，请刷新重试");
                        },
                        success:function(data){
                            if(data.error == "0"){
                                if(data.flag == 1){
                                    alert("操作成功！");
                                }else if(data.flag == 2){
                                    alert("状态变更失败，礼物已使用该商品！");
                                }else{
                                    alert("状态变更异常！");
                                }
                            }else if(data.error == "-1"){
                                alert("操作失败，请联系管理员！");
                            }
                            window.location.href = "../integralMall/getProductList.html?menuId=006700020000";
                        }
                    });
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