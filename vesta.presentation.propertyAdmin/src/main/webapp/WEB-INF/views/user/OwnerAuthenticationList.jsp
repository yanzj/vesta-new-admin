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
        $("#005200060000").addClass("active");
        $("#005200060000").parent().parent().addClass("in");
        $("#005200060000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005200060000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../user/getOwnerAuthenticationList.html">
                    <input type="hidden" id="menuId" name="menuId" value="005200060000"/>
                    <!-- 所属客户端 -->
                    <div class="form-group  col-xs-4">
                        <label for="clientId" class="col-sm-4 control-label">所属客户端</label>
                        <div class="col-sm-8">
                            <select id="clientId" name="appId" class="form-control">
                                <option value="">请选择</option>
                                <c:forEach items="${clientConfigList}" var="clientConfig" >
                                    <option value="${clientConfig.weChatAppId }"
                                            <c:if test="${clientConfig.weChatAppId eq ownerAuthenticationDTO.appId}">selected</c:if>>${clientConfig.clientName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 手机号码 --%>
                    <div class="form-group  col-xs-4">
                        <label for="mobile" class="col-sm-4 control-label">手机号码</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="mobile" name="mobile" value="${ownerAuthenticationDTO.mobile}">
                        </div>
                    </div>
                    <%-- 证件号码 --%>
                    <div class="form-group  col-xs-4">
                        <label for="idCard" class="col-sm-4 control-label">证件号码</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="idCard" name="idCard" value="${ownerAuthenticationDTO.idCard}">
                        </div>
                    </div>
                    <%-- 认证状态 --%>
                    <div class="form-group  col-xs-4">
                        <label for="handleState" class="col-sm-4 control-label">认证状态</label>
                        <div class="col-sm-8">
                            <select id="handleState" name="handleState" class="form-control">
                                <option value="">--请选择状态--</option>
                                <option value="100" <c:if test="${'100' eq ownerAuthenticationDTO.handleState}">selected</c:if>>未处理</option>
                                <option value="101" <c:if test="${'101' eq ownerAuthenticationDTO.handleState}">selected</c:if>>认证通过</option>
                                <option value="102" <c:if test="${'102' eq ownerAuthenticationDTO.handleState}">selected</c:if>>认证失败</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
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
                <th>手机号码</th>
                <th>证件类型</th>
                <th>证件号码</th>
                <th>认证时间</th>
                <th>来源</th>
                <th>认证状态</th>
                <%--<th>操作</th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ownerAuthenticationList}" var="ownerAuthentication" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${ownerAuthentication.mobile}</td>
                    <td>
                        <c:if test="${'100000000' eq ownerAuthentication.idType}">身份证</c:if>
                        <c:if test="${'100000001' eq ownerAuthentication.idType}">军官证</c:if>
                        <c:if test="${'100000002' eq ownerAuthentication.idType}">警官证</c:if>
                        <c:if test="${'100000003' eq ownerAuthentication.idType}">护照</c:if>
                        <c:if test="${'100000004' eq ownerAuthentication.idType}">营业执照</c:if>
                    </td>
                    <td>${ownerAuthentication.idCard}</td>
                    <td>
                        <fmt:formatDate type="time" value="${ownerAuthentication.createOn}" pattern="yyyy-MM-dd hh:mm:ss" />
                    </td>
                    <td>${ownerAuthentication.clientName}</td>
                    <td>
                        <c:if test="${'100' eq ownerAuthentication.handleState}">未处理</c:if>
                        <c:if test="${'101' eq ownerAuthentication.handleState}">认证通过</c:if>
                        <c:if test="${'102' eq ownerAuthentication.handleState}">认证失败</c:if>
                    </td>
                    <%--<td class="last">
                        <a href="javascript:void(0);" onclick="toEdit('${ownerAuthentication.id}','101')" id="toEditPass" class="a3">审核通过</a>
                        <a href="javascript:void(0);" onclick="toEdit('${ownerAuthentication.id}','102')" id="toEditFail" class="a3">审核失败</a>
                        <a href="javascript:void(0);" onclick="toDetail('${ownerAuthentication.id}')" id="toDetail" class="a3">详情</a>
                    </td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/user/getOwnerAuthenticationList.html?menuId=005200060000&pageIndex={0}&appId=${ownerAuthenticationDTO.appId}&mobile=${ownerAuthenticationDTO.mobile}&idCard=${ownerAuthenticationDTO.idCard}&handleState=${ownerAuthenticationDTO.handleState}"/>
    </div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<script>
    //详情
    function toDetail(id){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../user/getOwnerAuthenticationInfo.html?menuId="+$("#menuId").val()+"&id="+id;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
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
                                    alert("业主提交的证件号在CRM系统不存在，审核无法通过！业主需重新认证或申诉！");
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
                            window.location.href = "../user/getOwnerAuthenticationList.html?menuId=005200060000";
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