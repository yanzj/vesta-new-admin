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
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../integralMall/getProductList.html">
                    <input type="hidden" id="menuId" name="menuId" value="006700020000"/>
                    <!-- 所属客户端 -->
                    <div class="form-group  col-xs-4">
                        <label for="clientId" class="col-sm-4 control-label">所属客户端</label>
                        <div class="col-sm-8">
                            <select id="clientId" name="clientId" class="form-control">
                                <option value="">请选择</option>
                                <c:forEach items="${clientConfigList}" var="clientConfig" >
                                    <option value="${clientConfig.id }"
                                            <c:if test="${clientConfig.id eq productDTO.clientId}">selected</c:if>>${clientConfig.clientName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 商品名称 --%>
                    <div class="form-group  col-xs-4">
                        <label for="productName" class="col-sm-4 control-label">商品名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="productName" name="productName" value="${productDTO.productName}">
                        </div>
                    </div>
                    <%-- 商品类目 --%>
                    <div class="form-group  col-xs-4">
                        <label for="productTypeName" class="col-sm-4 control-label">商品类目</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="productTypeName" name="productTypeName" value="${productDTO.productTypeName}">
                        </div>
                    </div>
                    <%-- 商品状态 --%>
                    <div class="form-group  col-xs-4">
                        <label for="productState" class="col-sm-4 control-label">商品状态</label>
                        <div class="col-sm-8">
                            <select id="productState" name="productState" class="form-control">
                                <option value="">--请选择状态--</option>
                                <option value="1" <c:if test="${'1' eq productDTO.productState}">selected</c:if>>已上架</option>
                                <option value="0" <c:if test="${'0' eq productDTO.productState}">selected</c:if>>已下架</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <button type="button" class="btn btn-primary" onclick="toEdit('')" id="toAdd">新增</button>
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
                <th>商品名称</th>
                <th>积分（金茂币）</th>
                <th>金额（元）</th>
                <th>商品类目</th>
                <th>库存</th>
                <th>状态</th>
                <th>所属客户端</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${productList}" var="product" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${product.productName}</td>
                    <td>${product.productIntegral}</td>
                    <td>${product.productPrice}</td>
                    <td>${product.productTypeName}</td>
                    <td>${product.productStock}</td>
                    <td>
                        <c:if test="${'1' eq product.productState}">已上架</c:if>
                        <c:if test="${'0' eq product.productState}">已下架</c:if>
                    </td>
                    <td>${product.clientName}</td>
                    <td class="last">
                        <a href="javascript:void(0);" onclick="toEdit('${product.productId}')" id="toEdit" class="a3">编辑</a>
                        <c:if test="${product.productState eq '1'}">
                            <a href="javascript:void(0);" onclick="toChangeState('${product.productId}',0)" id="toEdit" class="a3">下架</a>
                        </c:if>
                        <c:if test="${product.productState eq '0'}">
                            <a href="javascript:void(0);" onclick="toChangeState('${product.productId}',1)" id="toEdit" class="a3">上架</a>
                        </c:if>
                        <%--<a href="javascript:void(0);" onclick="toDelete('${product.productId}')" id="toDelete" class="a3">删除</a>--%>
                        <c:if test="${product.isCardCoupons eq '1'}">
                            <button type="button" class="btn btn-primary" onclick="ad('${product.productId}')">批量导入</button>

                            <a href="javascript:void(0);" onclick="getCard('${product.productId}')" id="toDelete" class="a3">查看详情</a>
                        </c:if>

                    </td>
                </tr>
            </c:forEach>
            <form id="uploadForm">
                <input type="file" class="form-control" placeholder="" id="excelFile" style="visibility:hidden"
                       name="excelFile" onchange="doUpload()">
                <input type="hidden" id="productId" name="productId"/>
            </form>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/integralMall/getProductList.html?menuId=006700020000&pageIndex={0}&clientId=${productDTO.clientId}&productName=${productDTO.productName}&productTypeName=${productDTO.productTypeName}&productState=${productDTO.productState}"/>
    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    function ad(p) {
        excelFile.click();

        document.getElementById("productId").value = p;
    }

    function doUpload(){
        var productId = document.getElementById("productId").value;
        //检验导入的文件是否为Excel文件
        var excelPath = document.getElementById("excelFile").value;
        if (excelPath == null || excelPath == '') {
            alert("请选择要上传的Excel文件");
            return;
        } else {
            var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();
            if (fileExtend == '.xls' || fileExtend == '.xlsx') {
            } else {
                alert("文件格式需为'.xls'格式或者'.xlsx格式'");
                return;
            }
        }


        //执行异步上传
        var formData = new FormData($("#uploadForm")[0]);
        $.ajax({
            url: '../integralMall/uploadExcel',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if(data.error == '0'){
                    alert("导入成功！");
                    if(data.list.length != 0){
                        alert("失败总条数："+data.list.length+"，序号为：  "+data.list+"，已存在卡卷。");
                    }
                }else if(data.error == '-1'){
                    alert("导入失败！");
                }
            },
            error: function (data) {
                alert("网络异常,请求失败！");
            }
        });
    }


    function getCard(productId){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../integralMall/getCard.html?menuId="+$("#menuId").val()+"&productId="+productId;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }

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