<%--
  Created by IntelliJ IDEA.
  User: lpc
  Date: 2016/6/5
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <%--<link rel="stylesheet" href="../static/css/jquery-ui.min.css" />
    <script src="../static/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>--%>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
    <style>
        label.error {
            /*position: absolute;*/
            /*margin-top: -74px;*/
            /*display: block;*/
            margin-left: 1%;
            color: red;
        }
    </style>
    <style>
        td {
            white-space: nowrap;
            padding-right: 15px;
            overflow: hidden;
            text-overflow: ellipsis; /*display:-webkit-box;*/
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 2;

            /*white-space:nowrap;overflow:hidden;text-overflow: ellipsis;*/
        }

    </style>
    <style type="text/css">#dialog {
        display: none;
    }</style>
    <script type="application/javascript">
        $(function(){
            $("#007300090000").addClass("active");
            $("#007300090000").parent().parent().addClass("in");
//      $("#003200010000").parent().parent().parent().parent().addClass("active");
        })
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007300090000" username="${authPropertystaff.staffName}"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">

                <form class="form-horizontal" name="search" action="../authAgency/authClassifyStaff.html" method="get">
                    <div style=" margin-left: -5%;">
                        <%-- 集团名称 --%>
                        <div class="form-group  col-lg-4">
                            <label for="groupId" class="col-sm-4 control-label"><spring:message
                                    code="problem.group"/>：</label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="groupId" name="groupId">
                                    <c:forEach items="${groups}" var="group">
                                        <option value="${group.key }"
                                                <c:if test="${group.key eq problem.groupId}">selected</c:if>>${group.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%-- 区域名称 --%>
                        <div class="form-group  col-lg-4">
                            <label for="regionId" class="col-sm-4 control-label"><spring:message
                                    code="problem.region"/>：</label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="regionId" name="regionId">
                                    <c:forEach items="${regions}" var="region">
                                        <option value="${region.key }"
                                                <c:if test="${region.key eq problem.regionId}">selected</c:if>>${region.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%-- 城市名称 --%>
                        <div class="form-group  col-lg-4">
                            <label for="cityId" class="col-sm-4 control-label"><spring:message
                                    code="problem.city"/>：</label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="cityId" name="cityId">
                                    <c:forEach items="${citys}" var="city">
                                        <option value="${city.key }"
                                                <c:if test="${city.key eq problem.cityId}">selected</c:if>>${city.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%-- 项目名称 --%>
                        <div class="form-group  col-lg-4">
                            <label for="projectNum" class="col-sm-4 control-label"><spring:message
                                    code="problem.project"/>：</label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="projectNum" name="projectNum">
                                    <c:forEach items="${proejcts}" var="proejct">
                                        <option value="${proejct.key }"
                                                <c:if test="${proejct.key eq problem.projectNum}">selected</c:if>>${proejct.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%--  分类 --%>
                        <div class="form-group  col-lg-4">
                            <label for="classifyId" class="col-sm-4 control-label"><spring:message
                                    code="auth.classify"/>：</label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="classifyId" name="classifyId">
                                    <option value="" selected>请选择</option>
                                    <option value="100000000" <c:if test="${problem.classifyId eq '100000000'}">selected</c:if>>销售服务类</option>
                                    <option value="100000005" <c:if test="${problem.classifyId eq '100000005'}">selected</c:if>>工程质量类</option>
                                    <option value="100000005" <c:if test="${problem.classifyId eq '100000005'}">selected</c:if>>规划设计类</option>
                                    <option value="100000003" <c:if test="${problem.classifyId eq '100000003'}">selected</c:if>>物业服务类</option>
                                    <%--<option value="100000005" <c:if test="${problem.classifyId eq '100000005'}">selected</c:if>>客户服务类</option>--%>
                                    <option value="100000005" <c:if test="${problem.classifyId eq '100000005'}">selected</c:if>>其他类</option>
                                </select>
                            </div>
                        </div>

                    <div class="clearfix">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="problem.search"/></button>
                    </div>

                    <%--<c:if test="${function.qch40010001 eq 'Y'}">--%>
                            <%--<button type="submit" class="btn btn-primary" for="propertySearch"><spring:message--%>
                                    <%--code="problem.search"/></button>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${function.qch40010002 eq 'Y'}">--%>
                        <%--<a href="../problem/preAddProblemManagement?planType=11" class="btn btn-primary"--%>
                           <%--for="rolesetAdd"><spring:message code="problem.add"/></a>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${function.qch40010003 eq 'Y'}">--%>
                        <%--<a href="#" onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>--%>
                        <%--(<input type="checkbox" id="checkImage" name="checkImage" value=""/>导出问题图片)--%>
                    <%--</c:if>--%>
                </form>


            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive  widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <%--<th width="4%"><input type="checkbox" name="answer" onclick="checkAllBox(this)">序号</th>--%>
                <th width="4%">序号</th>
                <th width="15%">员工姓名</th>
                <th width="8%" >项目名称</th>
                <th width="8%">分类名称</th>
                <th width="8%" >时间</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="problem" varStatus="row">
                <tr>
                    <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
                    <td>${problem.staffName}</td>
                    <td>${problem.projectName}</td>
                    <td>
                        <c:if test="${problem.classifyId eq '100000000'}">销售服务类</c:if>
                        <c:if test="${problem.classifyId eq '100000001'}">工程质量类</c:if>
                        <c:if test="${problem.classifyId eq '100000002'}">规划设计类</c:if>
                        <c:if test="${problem.classifyId eq '100000003'}">物业服务类</c:if>
                        <c:if test="${problem.classifyId eq '100000005'}">客户服务类</c:if>
                        <c:if test="${problem.classifyId eq '100000004'}">其他类</c:if>

                    </td>
                    <td>${problem.modifyOn}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/authAgency/authClassifyStaff.html?pageIndex={0}&groupId=${problem.groupId}&regionId=${problem.regionId} &cityId=${problem.cityId}&projectNum=${problem.projectNum}&classifyId=${problem.classifyId} "/>
    </div>

</div>
</div>
</div>
</div>

<!-- main content end-->
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";

</script>
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
</script>
<%@ include file="../main/foolter.jsp" %>
</div>

<script>



    //根据集团id查询区域信息
    $("#groupId").change(function () {
        var groupId = $("#groupId").val();
        $.ajax({
            url: "../problem/getQCAuthAgency",
            type: "get",
            async: "false",
            data: {"type": '100000001',
                "groupId":groupId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("regionId").innerHTML = "";
                        document.getElementById("cityId").innerHTML = "";
                        document.getElementById("projectNum").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }

                                }
                            }
                        }
                        $("#regionId").append(option);
                    }
                }
            }
        });
    });
    //根据区域id查询城市信息
    $("#regionId").change(function () {
        var regionId = $("#regionId").val();
        $.ajax({
            url: "../problem/getQCAuthAgency",
            type: "get",
            async: "false",
            data: {"type": '100000003',
                "regionId":regionId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("cityId").innerHTML = "";
                        document.getElementById("projectNum").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }

                                }
                            }
                        }
                        $("#cityId").append(option);
                    }
                }
            }
        });
    });

    //根据城市Id获取项目信息
    $("#cityId").change(function () {
        var cityId = $("#cityId").val();
        $.ajax({
            url: "../problem/getProjectMun",
            type: "get",
            async: "false",
            data: {"type": '100000002',
                "cityId":cityId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("projectNum").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }
                                }
                            }
                        }
                        $("#projectNum").append(option);
                    }
                }
            }
        });
    });



</script>
</body>

</html>