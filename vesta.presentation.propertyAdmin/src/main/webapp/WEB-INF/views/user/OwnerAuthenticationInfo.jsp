<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%response.setHeader("cache-control","public"); %>
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
        $(function(){
            console.log("sqq")
            $("#005200060000").addClass("active");
            $("#005200060000").parent().parent().addClass("in");
            $("#005200060000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005200060000" username="${propertystaff.staffName}" />
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <input type="hidden" id="menuId" name="menuId" value="005200060000"/>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">手机号：</label>
                    <label class="control-label">${ownerAuthenticationInfo.mobile}</label>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">证件类型：</label>
                    <label class="control-label">
                        <c:if test="${'100000000' eq ownerAuthenticationInfo.idType}">身份证</c:if>
                        <c:if test="${'100000001' eq ownerAuthenticationInfo.idType}">军官证</c:if>
                        <c:if test="${'100000002' eq ownerAuthenticationInfo.idType}">警官证</c:if>
                        <c:if test="${'100000003' eq ownerAuthenticationInfo.idType}">护照</c:if>
                        <c:if test="${'100000004' eq ownerAuthenticationInfo.idType}">营业执照</c:if>
                    </label>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">证件号码：</label>
                    <label class="control-label">${ownerAuthenticationInfo.idCard}</label>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">认证时间：</label>
                    <label class="control-label">${ownerAuthenticationInfo.createOn}</label>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">来源：</label>
                    <label class="control-label">${ownerAuthenticationInfo.clientName}</label>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">处理状态：</label>
                    <label class="control-label">
                        <c:if test="${'100' eq ownerAuthenticationInfo.handleState}">未处理</c:if>
                        <c:if test="${'101' eq ownerAuthenticationInfo.handleState}">认证通过</c:if>
                        <c:if test="${'102' eq ownerAuthenticationInfo.handleState}">认证失败</c:if>
                    </label>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">操作人：</label>
                    <label class="control-label">${ownerAuthenticationInfo.modifyBy}</label>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">证件照（正面）：</label>
                    <div class="col-sm-5">
                        <img src="${ownerAuthenticationInfo.idPhotoFrontUrl}" alt="证件照（正面）" class="img-thumbnail" style="width: auto">
                    </div>
                </div>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-3 control-label">证件照（反面）：</label>
                    <div class="col-sm-5">
                        <img src="${ownerAuthenticationInfo.idPhotoBackUrl}" alt="证件照（反面）" class="img-thumbnail" style="width: auto">
                    </div>
                </div>
                <div class="text-center form-group  col-lg-12" style="padding:0;">
                    <button type="button" class="btn btn-primary" onclick="toEdit('${ownerAuthenticationInfo.id}','101')">审核通过</button>
                    <button type="button" class="btn btn-primary" onclick="toEdit('${ownerAuthenticationInfo.id}','102')">审核失败</button>
                    <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);"><spring:message code="common_cancel"/></button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    //编辑状态
    function toEdit(id,handleState){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    $.ajax({
                        url:"../user/updateOwnerAuthenticationState",
                        type:"POST",
                        cache: false,
                        async:false,
                        data:{
                            "id":id,
                            "handleState":handleState
                        },
                        dataType:"json",
                        error:function(){
                            alert("网络异常，可能服务器忙，请刷新重试！");
                        },
                        success:function(data){
                            if(data.error == 0){
                                if(data.flag == 1011){
                                    alert("业主证件号码未找到，认证失败！");
                                }else if(data.flag == 1012){
                                    alert("业主证件号码已被认证，默认通过！");
                                }else if(data.flag == 1021){
                                    alert("已经是业主，无法将认证状态设为失败！");
                                }else if(data.flag == 0){
                                    alert("操作成功！");
                                }
                            }else{
                                alert("操作失败，请联系管理员！");
                            }
//                            window.location.href = "../user/getOwnerAuthenticationList.html?menuId=005200060000";
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
