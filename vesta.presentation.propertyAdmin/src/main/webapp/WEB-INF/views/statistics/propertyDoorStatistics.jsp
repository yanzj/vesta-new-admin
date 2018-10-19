<%--
  Created by IntelliJ IDEA.
  User: WeiYangDong
  Date: 2018/1/29
  Time: 16:14
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
    <%-- Highcharts-6.0.4 --%>
    <script src="../static/highcharts/code/highcharts.js"></script>
    <%-- //Highcharts-6.0.4 --%>
</head>
<style type="text/css">
    .form_b {
        height: 17rem;
    }
    .search_button {
        text-align: center;
    }
    .control_btn {
        padding: 0 0 1rem 0;
        background-color: #fbfbfb;
    }
    .control_btn button, .control_btn a {
        margin-right: 1rem;
    }
    .blank_sty .blank_input {
        border: none;
        box-shadow: none;
    }
    .form-group.last_time {
        /*margin-left: 1.5rem;*/
    }
</style>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005600040000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form class="form-horizontal" action="../property/getPropertyDoorStatistics.html">
                    <input type="hidden" id="menuId" name="menuId" value="005600040000"/>

                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>

                        <div class="col-sm-8">
                            <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item">
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq propertyDoorLogDTO.scopeId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group  col-xs-4">
                        <label for="projectCode" class="col-sm-4 control-label">项目</label>

                        <div class="col-sm-8">
                            <select id="projectCode" name="projectCode" class="form-control">
                                <c:forEach items="${projectList}" var="item">
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq propertyDoorLogDTO.projectCode}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 操作 --%>
                    <div class="form-group  col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="common_search"/></button>
                    </div>
                    <div class="clearfix"></div>

                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <div id="container" style="width: 60rem;height:40rem;"></div>
        <div id="container2" style="width: 60rem;height:40rem;"></div>
        <div id="container3" style="width: 60rem;height:40rem;"></div>
    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<script type="text/javascript">
    //通过城市获取项目列表
    function queryProjectNameById() {
        $("#projectCode").find("option").remove();
        /* -------------------- */
        var projectId = $("#scopeId").val();
        if (projectId == '-1') {
            $("#projectCode").empty();
            return;
        }
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId + "/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectCode").empty();
            $("#projectCode").append('<option value="">请选择</option>');
            for (var k in arr) {
                if (arr[k][0] != '0') {
                    $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
                }
            }
        });
    }
</script>
<script>
    // 图表配置
    var options = {
        chart: {
            type: 'bar'                          //指定图表的类型，默认是折线图（line）
        },
        title: {
            text: '楼层开门情况统计表'                 // 标题
        },
        xAxis: {
            categories: ${floorName}   // x 轴分类
        },
        yAxis: {
            title: {
                text: '数量'                // y 轴标题
            }
        },
        series: [{                              // 数据列
            name: '开门次数',                        // 数据列名
            data: ${floorStr}                     // 数据
        }]
    };
    // 图表初始化函数
    var chart = Highcharts.chart('container', options);

    Highcharts.chart('container2', {
        title: {
            text: '时间段开门情况统计表'
        },
        xAxis: {
            categories: ${timeName}
        },
        series: [{
            data: ${timeStr},
            name: '开门次数'
        }]
    });

    $(function () {
        $('#container3').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '楼栋开门情况统计表'
            },
            xAxis: {
                categories: ${buildingName}
            },
            credits: {
                enabled: true
            },
            plotOptions: {
                column: {
                    // 关于柱状图数据标签的详细配置参考：https://api.hcharts.cn/highcharts#plotOptions.column.dataLabels
                    dataLabels: {
                        enabled: true,
                        // verticalAlign: 'top', // 竖直对齐方式，默认是 center
                        inside: true
                    }
                }
            },
            series: [{
                name:'开门次数',
                data: ${buildingStr}
            }]
        });
    });



</script>

</body>
</html>
