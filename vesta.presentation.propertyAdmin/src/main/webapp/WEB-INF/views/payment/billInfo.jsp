<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/2/3
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
</head>
<script type="text/javascript">
    function goSubmit(id){
        document.getElementById("rolesetId").value = id;
        document.getElementById("search_form").submit();
    }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020013" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <a href="/billInfo/exportExcel"  value="" class="btn btn-primary" style="color:#fff" a>导出Excel</a>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <%--<form action="../community/activityManage" method="post" id ="search_form">

            <input id = "activityId" type="hidden" name="rolesetId"  value="">
        </form>--%>
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td style="word-wrap:break-word; word-break:break-all;" width="%9"><spring:message code="billInfo.billInfoId" /></td>
                <th style="word-wrap:break-word; word-break:break-all;"  width="%9"><spring:message code="billInfo.payerName" /></th>
                <th style="word-wrap:break-word; word-break:break-all;"  width="%9"><spring:message code="billInfo.payerPhone" /></th>
                <th style="word-wrap:break-word; word-break:break-all;"  width="%9"><spring:message code="billInfo.houseInfo" /></th>
                <th style="word-wrap:break-word; word-break:break-all;"  width="%9"><spring:message code="billInfo.payTime" /></th>
                <th style="word-wrap:break-word; word-break:break-all;"  width="%9"><spring:message code="billInfo.billSendWay" /></th>
                <th style="word-wrap:break-word; word-break:break-all;"  width="%9"><spring:message code="billInfo.billTitle" /></th>
                <th style="word-wrap:break-word; word-break:break-all;"  width="%9"><spring:message code="billInfo.processStatus" /></th>
                <th style="word-wrap:break-word; word-break:break-all;"  width="%9"><spring:message code="billInfo.processUser" /></th>
                <th style="word-wrap:break-word; word-break:break-all;"  width="%9"><spring:message code="billInfo.processTime" /></th>
                <th style="word-wrap:break-word; word-break:break-all;"  width="%9"><spring:message code="billInfo.opera" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${billInfoList}" var="billInfo"  varStatus="status">
                <tr>
                    <td style="word-wrap:break-word; word-break:break-all;" >${ status.index + 1}</td>
                    <td style="word-wrap:break-word; word-break:break-all;" >${billInfo.payerName}</td>
                    <td style="word-wrap:break-word; word-break:break-all;" >${billInfo.payerPhone}</td>
                    <td style="word-wrap:break-word; word-break:break-all;" >${billInfo.houseInfo}</td>
                    <td style="word-wrap:break-word; word-break:break-all;" >${billInfo.payTime}</td>
                    <td style="word-wrap:break-word; word-break:break-all;" >${billInfo.billSendWay}</td>
                    <td style="word-wrap:break-word; word-break:break-all;" >${billInfo.billTitle}</td>
                    <td style="word-wrap:break-word; word-break:break-all;" >${billInfo.processStatus}</td>
                    <td style="word-wrap:break-word; word-break:break-all;" >${billInfo.processUser}</td>
                    <td style="word-wrap:break-word; word-break:break-all;" >${billInfo.processTime}</td>

                    <td style="word-wrap:break-word; word-break:break-all;" >
                        <c:if test="${billInfo.processStatusValue eq 'proccess_did'}">
                            已处理
                        </c:if>
                        <c:if test="${billInfo.processStatusValue eq 'proccess_not'}">
                            <input type="button" data="${billInfo.billInfoId}"  value="处理" class="btn btn-primary operaButton" style="color:#fff" >
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/billInfo/getBillInfoList?pageIndex={0}"/>
    </div>


</div>
</div>
</div>
</div>

<%--时间搜索插件--%>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
$(".operaButton").click(function(){

    var data = $(this).attr("data");
    $.ajax({
        "type":"get",
        "url":"/billInfo/updateBillInfoStatus",
        "data":{
            "billInfoId":data
        },
        "success":function(msg){
           if(msg.code == 0){
               window.location.reload();
           }else{
               alert(msg.msg);
           }

        }
    });

});

</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>