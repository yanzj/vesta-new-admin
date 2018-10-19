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

<style>


    .outCircle {
        width: 65rem;
        height: 30rem;
        border: 1px solid #50b9d8;
        margin-left: 6rem;
        position: relative;
    }

    .outCircle .titleN {
        position: absolute;
        top: 0;
        left: 45%;
        font-size: 2.3rem;
    }

    .outCircle .circlePic {
        width: 60.5rem;
        height: 19.5rem;
        margin-left: 1.2rem;
        margin-top: 3rem;
    }

    .outCircle .cirList {
        width: 100%;
        height: 2rem;
    }

    .outCircle .cirList .listS {
        border-bottom: 1px solid #ccc;
        width: 95%;
        margin-left: 5%;
    }

    .Histogram {
        width: 100%;
        position: absolute;
        top: 2.9rem;
        height: 19.5rem;
    }

    .Histogram .hisPic {
        width: 3.6rem;
        height: 1rem;
        background: #5cc3e2;
        /* display: inline-block; */
        margin-left: 5.2rem;
        position: absolute;
        bottom: 0;
        left: 0;
    }

    .Histogram .hisPic:nth-child(1) {
        position: absolute;
        bottom: 0;
        left: 0;
    }

    .Histogram .hisPic:nth-child(2) {
        position: absolute;
        bottom: 0;
        left: 10%;
    }

    .Histogram .hisPic:nth-child(3) {
        position: absolute;
        bottom: 0;
        left: 20%;
    }

    .Histogram .hisPic:nth-child(4) {
        position: absolute;
        bottom: 0;
        left: 30%;
    }

    .Histogram .hisPic:nth-child(5) {
        position: absolute;
        bottom: 0;
        left: 40%;
    }

    .Histogram .hisPic:nth-child(6) {
        position: absolute;
        bottom: 0;
        left: 50%;
    }

    .Histogram .hisPic:nth-child(7) {
        position: absolute;
        bottom: 0;
        left: 60%;
    }

    .Histogram .hisPic:nth-child(8) {
        position: absolute;
        bottom: 0;
        left: 70%;
    }

    .Histogram .hisPic:nth-child(9) {
        position: absolute;
        bottom: 0;
        left: 80%;
    }

    .Histogram .hisPic:nth-child(10) {
        position: absolute;
        bottom: 0;
        left: 90%;
    }

    .bottomName {
        /*margin-left: .3rem;*/
        margin-top: 4.35rem;
        position: relative;
    }

    .bottomName .name {
        width: 13%;
        /*display: inline-block;*/
        transform: rotate(-44deg);
        text-align: right;
        bottom: -.5rem;
    }

    .bottomName .name:nth-child(1) {
        position: absolute;
        bottom: -.5rem;
        left: 0;
    }

    .bottomName .name:nth-child(2) {
        position: absolute;
        bottom: -.5rem;
        left: 10%;
    }

    .bottomName .name:nth-child(3) {
        position: absolute;
        bottom: -.5rem;
        left: 20%;
    }

    .bottomName .name:nth-child(4) {
        position: absolute;
        bottom: -.5rem;
        left: 30%;
    }

    .bottomName .name:nth-child(5) {
        position: absolute;
        bottom: -.5rem;
        left: 40%;
    }

    .bottomName .name:nth-child(6) {
        position: absolute;
        bottom: -.5rem;
        left: 50%;
    }

    .bottomName .name:nth-child(7) {
        position: absolute;
        bottom: -.5rem;
        left: 60%;
    }

    .bottomName .name:nth-child(8) {
        position: absolute;
        bottom: -.5rem;
        left: 70%;
    }
    .span-v{
        position: absolute;
        top: -1.4rem;
        left: 1.1rem;
    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#006700070000").addClass("active");
        $("#006700070000").parent().parent().addClass("in");
        $("#006700070000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006700070000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../userIntegralRule/getRule.html">
                    <input type="hidden" id="menuId" name="menuId" value="006700070000"/>
                    <%-- 姓名 --%>
                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>
                        <div class="col-sm-8">
                            <select id="scopeId" name="cityId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq q.cityId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group  col-xs-4">
                        <label for="projectCode" class="col-sm-4 control-label">项目</label>
                        <div class="col-sm-8">
                            <select id="projectCode" name="projectNum" class="form-control">
                                <c:forEach items="${projectList}" var="item" >
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq q.projectNum}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
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
                    </div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>




    <div class='outCircle'>
        <span class='titleN'>数量</span>
        <div class='circlePic'>
            <div class='cirList'><span>1800</span>
                <div class='listS'></div>
            </div>
            <div class='cirList'><span>1600</span>
                <div class='listS'></div>
            </div>
            <div class='cirList'><span>1400</span>
                <div class='listS'></div>
            </div>
            <div class='cirList'><span>1200</span>
                <div class='listS'></div>
            </div>
            <div class='cirList'><span>1000</span>
                <div class='listS'></div>
            </div>
            <div class='cirList'><span>800</span>
                <div class='listS'></div>
            </div>
            <div class='cirList'><span>600</span>
                <div class='listS'></div>
            </div>
            <div class='cirList'><span>400</span>
                <div class='listS'></div>
            </div>
            <div class='cirList'><span>200</span>
                <div class='listS'></div>
            </div>
            <div class='cirList'><span>0</span>
                <div class='listS'></div>
            </div>
        </div>
        <div class='Histogram'>
        </div>
        <div class='bottomName'>

            <c:forEach items="${list}" var="product" varStatus="row">
                <div class='name'>${product.modelName}</div>
            </c:forEach>
        </div>
    </div>

<%--    <table width="100%" class="tableStyle">
        <tbody>
        <c:forEach items="${list}" var="product" varStatus="row">
            <tr>
                <td>${product.modelName}</td>
                <td>${product.number}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>--%>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>

    //通过城市获取项目列表
    function queryProjectNameById() {
        $("#projectCode").find("option").remove();
        /* -------------------- */
        var projectId = $("#scopeId").val();
        if(projectId == '-1'){
            $("#projectCode").empty();
            return ;
        }
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectCode").empty();
            $("#projectCode").append('<option value="">请选择</option>');
            for (var k in arr) {
                if(arr[k][0] != '0'){
                    $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
                }
            }
        });
    }
</script>

<script type="text/javascript">
/*    ! function(desW) {
        var winW = document.documentElement.clientWidth;
        document.documentElement.style.fontSize = winW / desW * 100 + "px";
    }(1920);*/
    //表高度y轴数值
    var sheetY = 100;
    //条目数据数组
   // var listData = [{ 'num': 20 }, { 'num': 50 }, { 'num': 10 }, { 'num': 80 }, { 'num': 45 }, { 'num': 15 }, { 'num': 80 }]
        <c:forEach items="${list}" var="product" varStatus="row">
            var value = ${product.number}
             var str = "<div class='hisPic'><span class='span-v'>"+value+"</span></div>"
            $('.Histogram').append(str)
            $('.hisPic:eq(' + ${row.index} + ')').css('height', (value / 2000)*100 + '%')
        </c:forEach>


/*
for (var i = 0; i < listData.length; i++) {
        var str = "<div class='hisPic'></div>"
        $('.Histogram').append(str)
        $('.hisPic:eq(' + i + ')').css('height', listData[i].num + '%')
    };*/
</script>
</body>
</html>
