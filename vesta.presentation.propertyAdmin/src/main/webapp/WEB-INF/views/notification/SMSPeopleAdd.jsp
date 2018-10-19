<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/2/20
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
    </script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>
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
        $(function () {
            console.log("sqq")
            $("#000100030000").addClass("active");
            $("#000100030000").parent().parent().addClass("in");
            $("#000100030000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <style>
        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 70px;
        }
    </style>
</head>
<script>
    function validate() {
        var name = document.getElementById("name");
        if (name.value == null || name.value == "") {
            alert("请输入姓名！");
            return false;
        }
        var phone = document.getElementById("phone");
        if (phone.value == null || phone.value == "") {
            alert("请输入手机号！");
            return false;
        }
        var cityList = document.getElementById("cityList");
        if (cityList.value == null || cityList.value == "") {
            alert("请选择城市范围！");
            return false;
        }
        var projectList = document.getElementById("projectList");
        if (projectList.value == null || projectList.value == "") {
            alert("请选择项目范围！");
            return false;
        }
        var modelList = document.getElementById("modelList");
        if (modelList.value == null || modelList.value == "") {
            alert("请选择提醒模块！");
            return false;
        }
        document.getElementById("frm").submit();
    }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="000100030000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body" style="min-height:1500px">
                    <form class="form-horizontal" id="frm" action="../smsMessage/smsPeopleAdd" method="post">
                        <input type="hidden" id="menuId" value="000100030000">

                        <%--姓名--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-3 control-label" for="name">姓名：</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" placeholder="" id="name" name="name"
                                       value="">
                            </div>
                        </div>
                        <%--手机号--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-3 control-label" for="phone">手机号：</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" placeholder="" id="phone" name="phone"
                                       value="">
                            </div>
                        </div>

                        <div class="form-group col-xs-10">
                            <label class="col-sm-3 control-label">数据查看范围：</label>

                            <div class="col-sm-9">
                            </div>
                        </div>

                        <%--城市区域--%>
                        <div class="form-group  col-xs-10">
                            <label for="cityNum" class="col-sm-3 control-label"><spring:message
                                    code="announcementDTO.cityName"/></label>

                            <div class="col-sm-5">
                                <select id="cityNum" name="cityNum" class="form-control"
                                        onchange="queryProjectNameById()">
                                    <option value="-1">--请选择城市--</option>
                                    <c:forEach items="${city}" var="item">
                                        <option value="${item.cityId }"
                                                <c:if test="${item.cityId eq '0'}">selected</c:if>>${item.cityName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-2">
                                <input type="image" name="addCity" id="addCity"
                                       value="<spring:message code="announcementDTO.add"/>"
                                       onclick="cityAdd();return false;"
                                       src="../static/images/add.ico">
                            </div>
                        </div>
                        <%--城市列表--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-3 control-label" for="cityList"
                                   style="min-width:115px;"><spring:message
                                    code="announcementDTO.selectedCity"/></label>
                            <div class="col-sm-5">
                                <textarea class="form-control" name="cityList" id="cityList" readonly
                                          style=" height: 71px;"><c:if
                                        test="${allCityInScope != ''}">${allCityInScope }</c:if><c:if
                                        test="${allCityInScope == ''}">${cityInScope }</c:if></textarea>
                                <input type="hidden" id="cityIds" name="cityIds" value="${cityIdInScope}"/>
                            </div>

                        </div>
                        <%--城市下项目--%>
                        <div class="form-group  col-xs-10">
                            <label for="projectNum" class="col-sm-3 control-label"><spring:message
                                    code="HousePayBatch.projectName"/></label>

                            <div class="col-sm-5">
                                <select id="projectNum" name="projectNum" class="form-control">
                                    <%--<option value="0"><spring:message code="announcementDTO.allProject"/></option>--%>
                                </select>
                            </div>
                            <div class="col-sm-2">
                                <input type="image" name="addProject" id="addProject"
                                       value="" onclick="projectAdd();return false;" src="../static/images/add.ico">
                            </div>
                        </div>

                        <%--项目列表--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-3 control-label" for="projectList"
                                   style="min-width:115px;"><spring:message
                                    code="announcementDTO.selectedProject"/></label>

                            <div class="col-sm-5">
                                <textarea class="form-control" name="projectList" id="projectList" readonly
                                          style=" height: 71px;"><c:if
                                        test="${allCityInScope != ''}"></c:if><c:if
                                        test="${allCityInScope == ''}">${projectInScope }</c:if></textarea>
                                <input type="hidden" id="projectIds" name="projectIds" value="${projectIdInScope}">
                            </div>
                        </div>
                        <%--提醒模块--%>
                        <div class="form-group  col-xs-10">
                            <label for="model" class="col-sm-3 control-label">提醒模块</label>
                            <div class="col-sm-5">
                                <select id="model" name="model" class="form-control" onchange="queryProjectNameById()">
                                    <c:forEach items="${alertsModel}" var="item">
                                        <option value="${item.key }"
                                                <c:if test="${item.key eq '0'}">selected</c:if>>${item.value }</option>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class="col-sm-2">
                                <input type="image" name="addModel" id="addModel"
                                       value="<spring:message code="announcementDTO.add"/>"
                                       onclick="modelAdd();return false;"
                                       src="../static/images/add.ico">
                            </div>
                        </div>

                        <div class="form-group col-xs-10">
                            <label class="col-sm-3 control-label" for="modelList"
                                   style="min-width:115px;">已选择的提醒模块</label>

                            <div class="col-sm-5">
                                <textarea class="form-control" name="modelList" id="modelList" readonly
                                          style="height: 71px;"></textarea>
                            </div>

                        </div>

                        <div class="text-center form-group  col-xs-12" style="padding:0;">
                            <input type="button" onclick="validate()" class="btn btn-primary" value="确定">
                            <a href="../smsMessage/smsList" class="btn btn-primary" for=""><spring:message
                                    code="common_cancel"/></a>
                        </div>

                    </form>
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
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
    function queryProjectNameById() {
        var projectId = $("#cityNum").val();
        if (projectId == '-1') {
            $("#projectNum").empty();
            return;
        }
        $("#planName").empty();
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId + "/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectNum").empty();
            <%--$("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');--%>
            for (var k in arr) {
                $("#projectNum").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
//                alert(arr[k][0]);
            }

        });
    }
    function cityAdd() {
        //#1.获取内容
        var projectName = $("#cityNum").find("option:selected").text();
        //获取隐藏域的值_Wyd_2016.06.03
        var projectId = $("#cityNum").find("option:selected").val();
        if (projectId == '-1') {
            alert("请选择城市，并添加！");
            return;
        }
        if (projectName == "全部城市") {
            //清空textarea
            $("#cityList").val("全部城市");
            $("#cityIds").val('0,');
            //清空项目列表_Wyd_2016.08.08_数据权限
            $("#projectList").val("");
            $("#projectIds").val("");
            $("#projectNum").empty();
            $("#projectNum").append('<option value="0,">全部项目</option>');
        } else {
            if ($("#cityList").val() == "") {
                //如果textarea中没有元素
                $("#cityList").val($("#cityList").val() + projectName + ',');
                $("#cityIds").val($("#cityIds").val() + projectId + ',');
            } else if ($("#cityList").val() != "") {
                //判断textArea中是否有select的值，如果没有则添加
                //获取textArea数组
                var strArray = $("#cityList").val().toString().split(",");
                //获取select值
                var str = $("#cityNum").find("option:selected").text();
                //判断是否在数组中
                if (strArray.toString().indexOf(str) > -1) {
                    return;
                }
                //判断是否="全部城市"
                if ($("#cityList").val() == "全部城市") {
                    //清空
                    $("#cityList").val("");
                    $("#cityList").val($("#cityList").val() + projectName + ',');
                    $("#cityIds").val(projectId + ',');
                    return;
                }
                //如果textarea中有元素，前置","
                $("#cityList").val($("#cityList").val() + projectName + ',');
                $("#cityIds").val($("#cityIds").val() + projectId + ',');
            }
        }
    }
    function projectAdd() {

        //#1.获取内容
        var projectName = $("#projectNum").find("option:selected").text();
        var projectId = $("#projectNum").find("option:selected").val();
        if (projectName == "全部项目") {
            //清空textarea
            $("#projectList").val("全部项目");
            $("#projectIds").val("0,");
        } else {
            if ($("#projectList").val() == "") {
                //如果textarea中没有元素
                $("#projectList").val($("#projectList").val() + projectName + ',');
                $("#projectIds").val($("#projectIds").val() + projectId + ',');
            } else if ($("#projectList").val() != "") {
                //判断textArea中是否有select的值，如果没有则添加
                //获取textArea数组
                var strArray = $("#projectList").val().toString().split(",");
                //获取select值
                var str = $("#projectNum").find("option:selected").text();
                //判断是否在数组中
                if (strArray.toString().indexOf(str) > -1) {
                    return;
                }
                //判断是否="全部项目"
                if ($("#projectList").val() == "全部项目") {
                    //清空
                    $("#projectList").val("");
                    $("#projectList").val($("#projectList").val() + projectName + ',');
                    $("#projectIds").val(projectId + ',');
                    return;
                }
                //如果textarea中有元素，前置","
                $("#projectList").val($("#projectList").val() + projectName + ',');
                $("#projectIds").val($("#projectIds").val() + projectId + ',');
            }
        }
    }
    function modelAdd() {
        //#1.获取内容
        var projectName = $("#model").find("option:selected").text();
        //获取隐藏域的值_Wyd_2016.06.03
        var projectId = $("#model").find("option:selected").val();
        if (projectName == "请选择") {
            //清空textarea
            $("#modelList").val("全部模块");
            $("#modelIds").val('0,');
        } else {
            if ($("#modelList").val() == "") {
                //如果textarea中没有元素
                $("#modelList").val($("#modelList").val() + projectName);
                $("#modelIds").val($("#modelIds").val() + projectId + ',');
            } else if ($("#modelList").val() != "") {
                //判断textArea中是否有select的值，如果没有则添加
                //获取textArea数组
                var strArray = $("#modelList").val().toString().split(",");
                //获取select值
                var str = $("#model").find("option:selected").text();
                //判断是否在数组中
                if (strArray.toString().indexOf(str) > -1) {
                    return;
                }
                //判断是否="全部城市"
                if ($("#modelList").val() == "全部模块") {
                    //清空
                    $("#modelList").val("");
                    $("#modelList").val($("#modelList").val() + projectName);
                    $("#modelIds").val(projectId + ',');
                    return;
                }
                //如果textarea中有元素，前置","
                $("#modelList").val($("#modelList").val() + ',' + projectName);
                $("#modelIds").val($("#modelIds").val() + projectId + ',');
            }
        }
    }


</script>
</body>
</html>
