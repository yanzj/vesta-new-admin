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
        height: 10rem;
    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#006700010000").addClass("active");
        $("#006700010000").parent().parent().addClass("in");
        $("#006700010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006700010000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="">
                    <input type="hidden" id="menuId" name="menuId" value="006700010000"/>
                    <%-- 商品类目名称 --%>
                    <div class="form-group  col-xs-5">
                        <label for="productTypeName" class="col-sm-4 control-label">新增商品类目名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" placeholder="" id="productTypeName" name="productTypeName" value="">
                        </div>
                        <%--<div class="col-sm-2">--%>
                            <%--<button type="button" class="btn btn-primary" id="add">新增</button>--%>
                        <%--</div>--%>
                    </div>
                    <div class=" search_button col-xs-8">
                        <button type="button" class="btn btn-primary" id="add">新增</button>
                    </div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <form id="batchForm" class="form-horizontal" action="#">
            <table width="100%" class="tableStyle">
                <thead>
                <tr>
                    <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                    <th>商品类目名称</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${productTypeList}" var="productType" varStatus="row">
                    <tr>
                        <td><b>${row.index + 1}</b></td>
                        <td>${productType.productTypeName}</td>
                        <td class="last">
                            <a href="javascript:void(0);" onclick="toEdit('${productType.productTypeId}')" id="edit"
                               class="a3">编辑</a>
                            <c:if test="${productType.productTypeState eq 0}">
                                <a href="javascript:void(0);" onclick="toDisable('${productType.productTypeId}',1)" id="disable"
                                   class="a3">启用</a>
                            </c:if>
                            <c:if test="${productType.productTypeState eq 1}">
                                <a href="javascript:void(0);" onclick="toDisable('${productType.productTypeId}',0)" id="disable"
                                   class="a3">禁用</a>
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
                 submitUrl="${pageContext.request.contextPath }/integralMall/getProductTypeList.html?menuId=006700010000&pageIndex={0}"/>
    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<!-- 编辑客户端配置 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <h4>编辑商品类目信息</h4>
                        <input type="hidden" class="form-control" id="productTypeId_Modal" name="productTypeId_Modal"/>
                        <%--商品类目名称--%>
                        <div class="form-group  col-lg-6">
                            <label for="productTypeName_Modal" class="col-sm-3 control-label">商品类目名称</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="productTypeName_Modal" name="productTypeName_Modal"/>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <%--<button type="submit" class="confirm" class="close" data-dismiss="modal" aria-hidden="true">保存</button>--%>
                            <button type="button" class="confirm" class="close" onclick="toSave()">保存</button>
                            <button type="button" class="cancel" onclick="clearModal()">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script>
    //新增
    $("#add").bind("click",function () {
        if($("#productTypeName").val().replace(/(^\s*)|(\s*$)/g, "") == 0){
            alert("商品类别名称不能为空！");
            return;
        }
        //商品类目名称校验
        $.ajax({
            type: "POST",
            url: "../integralMall/checkProductTypeName",
            cache: false,
            async:false,
            data:{
                "productTypeName":$("#productTypeName").val()
            },
            dataType:"json",
            success: function (data) {
                if (data.error == 0) {
                    if (data.flag == 1){
                        alert("商品类目名称已经存在！");
                    }else{
                        $.ajax({
                            type: "POST",
                            url: "../integralMall/toSaveProductType",
                            cache: false,
                            async:false,
                            data:{
                                "productTypeName":$("#productTypeName").val()
                            },
                            dataType:"json",
                            success: function (data) {
                                if (data.error == 0) {
                                    alert("新增成功！");
                                    window.location.reload();
                                }else{
                                    alert("对不起，新增失败！");
                                }
                            }
                        });
                    }
                }else{
                    alert("对不起，商品类目名称校验异常，无法新增！");
                }
            }
        });
    });
    //禁用/启用
    function toDisable(id,state){
        $.ajax({
            type: "POST",
            url: "../integralMall/toSaveProductType",
            cache: false,
            async:false,
            data:{
                "productTypeId":id,
                "productTypeState":state
            },
            dataType:"json",
            success: function (data) {
                if (data.error == 0) {
                    alert("操作成功！");
                    window.location.reload();
                }else if(data.error == 1){
                    alert("对不起，商品已使用类目！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //清空并关闭Modal窗
    function clearModal(){
        $("#productTypeId_Modal").attr("value","");
        $("#productTypeName_Modal").attr("value","");
        //关闭模态窗
        $("#myModal").modal('hide');
    }
    //编辑
    function toEdit(id){
        $.ajax({
            type: "POST",
            url: "../integralMall/toEditProductType",
            cache: false,
            async:false,
            data:{
                "productTypeId":id
            },
            dataType:"json",
            success: function (data) {
                if (data.error == 0) {
                    $("#productTypeId_Modal").attr("value",data.productTypeInfo.productTypeId);
                    $("#productTypeName_Modal").attr("value",data.productTypeInfo.productTypeName);
                    $('#myModal').modal('show');
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //保存
    function toSave(){
        //商品类目名称校验
        $.ajax({
            type: "POST",
            url: "../integralMall/checkProductTypeName",
            cache: false,
            async:false,
            data:{
                "productTypeName":$("#productTypeName_Modal").val()
            },
            dataType:"json",
            success: function (data) {
                if (data.error == 0) {
                    if (data.flag == 1){
                        alert("商品类目名称已经存在！");
                    }else{
                        $.ajax({
                            type: "POST",
                            url: "../integralMall/toSaveProductType",
                            cache: false,
                            async:false,
                            data:{
                                "productTypeId":$("#productTypeId_Modal").val(),
                                "productTypeName":$("#productTypeName_Modal").val()
                            },
                            dataType:"json",
                            success: function (data) {
                                if (data.error == 0) {
                                    alert("保存成功！");
                                    window.location.reload();
                                }else{
                                    alert("对不起，保存失败！");
                                }
                            }
                        });
                    }
                }else{
                    alert("对不起，商品类目名称校验异常，无法新增！");
                }
            }
        });
    }
</script>
</body>
</html>