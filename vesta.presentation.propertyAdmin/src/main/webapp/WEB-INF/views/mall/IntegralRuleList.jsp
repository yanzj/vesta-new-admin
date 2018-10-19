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
        $("#006700050000").addClass("active");
        $("#006700050000").parent().parent().addClass("in");
        $("#006700050000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006700050000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../integralRule/getIntegralRuleList.html">
                    <input type="hidden" id="menuId" name="menuId" value="006700050000"/>
                    <!-- 公众号 -->
                    <div class="form-group  col-xs-4">
                        <label for="clientName" class="col-sm-4 control-label">公众号</label>
                        <div class="col-sm-8">
                            <select id="clientName" name="clientName" class="form-control">
                                <option value="">请选择公众号</option>
                                <c:forEach items="${defaultConfig}" var="defaultConfig" >
                                    <option value="${defaultConfig.clientName }" <c:if test="${defaultConfig.clientName eq integralRuleQuerry.clientName}">selected</c:if>>${defaultConfig.clientName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%-- 模块 --%>
                    <div class="form-group  col-xs-4">
                        <label for="modelName" class="col-sm-4 control-label">模块</label>
                        <div class="col-sm-8">
                            <select id="modelName" name="modelName" class="form-control">
                                <option value="">请选择模块</option>
                                <c:forEach items="${modelIntegral}" var="modelIntegral" >
                                    <option value="${modelIntegral.modelName }" <c:if test="${modelIntegral.modelName eq integralRuleQuerry.modelName}">selected</c:if>>${modelIntegral.modelName}</option>
                                </c:forEach>
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
                <th>公众号</th>
                <th>模块</th>
                <th>积分数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${list.clientName}</td>
                    <td>${list.modelName}</td>
                    <td>${list.integralNumber}</td>

                    <td class="last">
                        <a href="javascript:void(0);" onclick="toEdit('${list.integralRuleId}')" id="toEdit" class="a3">编辑</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/integralRule/getIntegralRuleList.html?menuId=006700050000&pageIndex={0}&clientName=${integralRuleQuerry.clientName}&modelName=${integralRuleQuerry.modelName}"/>
    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    //编辑
    function toEdit(integralRuleId){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../integralRule/toEditIntegralRule.html?menuId="+$("#menuId").val()+"&integralRuleId="+integralRuleId;
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
