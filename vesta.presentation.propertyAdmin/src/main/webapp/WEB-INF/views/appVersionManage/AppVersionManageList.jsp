<%--
  Created by IntelliJ IDEA.
  User: jiazefeng
  Date: 2016/2/24
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="002400010000" username="${authPropertystaff.staffName}"/>
    <input type="hidden" id="menuId" value="003200010000"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <div class="widget-shadow " data-example-id="basic-forms">
                    <%--搜索条件开始--%>
                    <div class="form-body">
                        <form class="form-horizontal" action="../qualityAPPVersionManagement/appVersionManage.html"
                              method="get">
                            <%--系统类型--%>
                            <div class="form-group  col-lg-6">
                                <label for="appVersionType" class="col-sm-4 control-label"><spring:message
                                        code="AppVersion_AppType"/>：</label>
                                <div class="col-sm-8">
                                    <select class="form-control" placeholder="" id="appEdition"
                                            name="appEdition">
                                        <option value=""><spring:message code="systemnotification.type_all"/></option>
                                        <option value="1"
                                                <c:if test="${appVersionDTO.appEdition==1}">selected="selected"</c:if>>
                                            <spring:message code="AppVersion_Deliver"/></option>
                                        <option value="2"
                                                <c:if test="${appVersionDTO.appEdition==2}">selected="selected"</c:if>>
                                            <spring:message code="AppVersion_Engineering"/></option>
                                        <option value="3"
                                                <c:if test="${appVersionDTO.appEdition==3}">selected="selected"</c:if>>
                                            <spring:message code="AppVersion_Security"/></option>
                                    </select>
                                </div>
                            </div>
                            <%--系统类型--%>
                            <div class="form-group  col-lg-6">
                                <label for="appVersionType" class="col-sm-4 control-label"><spring:message
                                        code="AppVersion_Type"/>：</label>

                                <div class="col-sm-8">
                                    <select class="form-control" placeholder="" id="appVersionType"
                                            name="appVersionType">
                                        <option value=""><spring:message code="systemnotification.type_all"/></option>
                                        <option value="1"
                                                <c:if test="${appVersionDTO.appVersionType==1}">selected="selected"</c:if>>
                                            <spring:message code="AppVersion_IOS"/></option>
                                        <option value="2"
                                                <c:if test="${appVersionDTO.appVersionType==2}">selected="selected"</c:if>>
                                            <spring:message code="AppVersion_Android"/></option>
                                    </select>
                                </div>
                            </div>
                            <%--名称--%>
                            <div class="form-group  col-lg-6">
                                <label for="appVersionName" class="col-sm-4 control-label"><spring:message
                                        code="AppVersion_Name"/>：</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" placeholder=""
                                           value="${appVersionDTO.appVersionName}" id="appVersionName"
                                           name="appVersionName"/>
                                </div>
                            </div>
                            <%--开始时间--%>
                            <div class="form-group  col-lg-6">
                                <label for="beginDate" class="col-sm-4 control-label"><spring:message
                                        code="systemnotification.createTime"/>:</label>

                                <div class="input-group date form_date col-sm-8" data-date=""
                                     data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                                    <input type="text" class="form-control" placeholder="" id="beginDate"
                                           name="beginDate" value="${appVersionDTO.beginDate}" readonly>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="form-group  col-lg-6">
                                <%--结束时间--%>
                                <label for="endDate" class="col-sm-4 control-label"><spring:message
                                        code="systemnotification.to"/>:</label>

                                <div class="input-group date form_date col-sm-8s" data-date=""
                                     data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                                    <input type="text" class="form-control" placeholder="" id="endDate" name="endDate"
                                           value="${appVersionDTO.endDate}" readonly>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="form-group  col-lg-6 pull-right">
                                <div class="col-sm-4"></div>
                                <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                        code="systemnotification.search"/></button>
                                <%--<a  href="../system/gotoAddVersion?appVersionType=${appVersionDTO.appVersionType}&userType=${appVersionDTO.userType}" class="btn btn-primary" for="rolesetAdd" ><spring:message code="AppVersion_Add"/></a>--%>
                                <c:if test="${function.qch40010078 eq 'Y'}">
                                    <a href="javascript:void(0);"
                                       onclick="js_gotoAdd('${appVersionDTO.appVersionType}')"
                                       class="btn btn-primary" for="rolesetAdd"><spring:message
                                            code="AppVersion_Add"/></a>
                                </c:if>
                                <!--集合长度(取决Excel是否可以导出)-->
                                <input type="hidden" id="size" value="${isExcel}"/>
                                <c:if test="${function.qch40010080 eq 'Y'}">
                                    <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary"
                                       style="color:#fff">导出Excel</a>
                                </c:if>
                            </div>
                            <div class="clearfix"></div>
                        </form>
                    </div>
                    <%--&lt;%&ndash;搜索条件结束&ndash;%&gt;--%>

                    <%--&lt;%&ndash;IOS&ndash;%&gt;--%>
                    <%--<a  href="../system/versionManage.html?appVersionType=1&userType=${appVersionDTO.userType}" class="btn btn-primary" for="rolesetAdd" <c:if test="${appVersionDTO.appVersionType eq '1'}">style="color: blue;font-weight:bold"</c:if>><spring:message code="AppVersion_IOS"/></a>--%>
                    <%--&lt;%&ndash;Adr&ndash;%&gt;--%>
                    <%--<a  href="../system/versionManage.html?appVersionType=2&userType=${appVersionDTO.userType}" class="btn btn-primary" for="rolesetAdd" <c:if test="${appVersionDTO.appVersionType eq '2'}">style="color: blue;font-weight:bold"</c:if>><spring:message code="AppVersion_Android"/></a>--%>
                    <%--&lt;%&ndash;发布版本&ndash;%&gt;--%>
                    <%--<a  href="../system/gotoAddVersion?appVersionType=${appVersionDTO.appVersionType}&userType=${appVersionDTO.userType}" class="btn btn-primary" for="rolesetAdd" ><spring:message code="AppVersion_Add"/></a>--%>

                </div>
                <%--搜索条件结束--%>
            </div>
        </div>
        <div class="table-responsive bs-example widget-shadow">
            <table width="100%" class="tableStyle">
                <thead>
                <tr>
                    <td><spring:message code="AppVersion_serialNumber"/></td>
                    <td><spring:message code="AppVersion_AppType"/></td>
                    <td><spring:message code="AppVersion_Type"/></td>
                    <td><spring:message code="AppVersion_Name"/></td>
                    <td><spring:message code="AppVersion_appVersion"/></td>
                    <td><spring:message code="AppVersion_size"/></td>
                    <th><spring:message code="AppVersion_createBy"/></th>
                    <th><spring:message code="AppVersion_createOn"/></th>
                    <th><spring:message code="AppVersion_operate"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${appVersionDTOs}" var="version" varStatus="vs">
                    <tr>
                        <td><b>${(webPage.pageIndex-1)*20+vs.index + 1}</b></td>
                        <td>
                            <c:if test="${version.appEdition eq '1'}"><spring:message code="AppVersion_Deliver"/></c:if>
                            <c:if test="${version.appEdition eq '2'}"><spring:message
                                    code="AppVersion_Engineering"/></c:if>
                            <c:if test="${version.appEdition eq '3'}"><spring:message
                                    code="AppVersion_Security"/></c:if>
                        </td>
                        <td>
                            <c:if test="${version.appVersionType eq '1'}"><spring:message code="AppVersion_IOS"/></c:if>
                            <c:if test="${version.appVersionType eq '2'}"><spring:message
                                    code="AppVersion_Android"/></c:if>
                        </td>
                        <td>${version.appVersionName}</td>
                        <td>${version.appVersionNum}</td>
                        <td>${version.fileSize}</td>
                        <td>${version.createBy}</td>
                        <td>${version.createOn}</td>
                        <td>
                                <%--<a href="../system/gotoUpdateVersion?appVersionId=${version.appVersionId}" class="a2"><span class="span1"><spring:message code="SECTION_UPDATE" /></span></a>--%>
                            <c:if test="${function.qch40010081 eq 'Y'}">
                                <a href="javascript:void(0);" onclick="js_gotoEdit('${version.appVersionId}')"
                                   class="a2"><span class="span1"><spring:message code="SECTION_UPDATE"/></span></a>
                            </c:if>
                                <%--<a href="../system/versionDel?appVersionId=${version.appVersionId}" class="a3"><span class="span1"><spring:message code="AppVersion_del" /></span></a>--%>
                            <c:if test="${function.qch40010083 eq 'Y'}">
                                <a href="javascript:void(0);" onclick="js_del('${version.appVersionId}')"
                                   class="a3"><span
                                        class="span1"><spring:message code="AppVersion_del"/></span></a>
                            </c:if>
                        </td>


                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <m:pager pageIndex="${webPage.pageIndex}"
                     pageSize="${webPage.pageSize}"
                     recordCount="${webPage.recordCount}"
                     submitUrl="${pageContext.request.contextPath }/qualityAPPVersionManagement/appVersionManage.html?pageIndex={0}&appVersionType=${appVersionDTO.appVersionType}&appVersionName=${appVersionDTO.appVersionName}&beginDate=${appVersionDTO.beginDate}&endDate=${appVersionDTO.endDate}"/>
        </div>
    </div>
</div>
</div>
</div>
<script type="text/javascript">
    var appVersionType = "${appVersionDTO.appVersionType}";
    var state = "${appVersionDTO.state}";
    var beginDate = "${appVersionDTO.beginDate}";
    var endDate = "${appVersionDTO.endDate}";
</script>
<%--时间搜索插件--%>
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

    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
            var href = "../qualityAPPVersionManagement/exportExcels?pageIndex=${webPage.pageIndex}&appVersionType=${appVersionDTO.appVersionType}&appVersionName=${appVersionDTO.appVersionName}&beginDate=${appVersionDTO.beginDate}&endDate=${appVersionDTO.endDate}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }

    function js_gotoAdd(appVersionType) {
        window.location.href = "../qualityAPPVersionManagement/gotoAddVersion?appVersionType=" + appVersionType;
//        $.ajax({
//            type: "GET",
//            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
//            cache: false,
//            async: false,
//            dataType: "json",
//            success: function (data) {
//                if (data.error == 1) {
//                    window.location.href = "../qualityAPPVersionManagement/gotoAddVersion?appVersionType=" + appVersionType;
//                } else if (data.error == 0) {
//                    alert("对不起，您无权限执行此操作！");
//                } else {
//                    alert("对不起，操作失败！");
//                }
//            }
//        });
    }

    function js_gotoEdit(appVersionId) {
        window.location.href = "../qualityAPPVersionManagement/gotoUpdateVersion?appVersionId=" + appVersionId;
//        var flag = 0;   //操作级别标示
//        $.ajax({
//            type: "GET",
//            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
//            cache: false,
//            async: false,
//            dataType: "json",
//            success: function (data) {
//                if (data.error == 1) {
//                    flag = 1;
//                } else if (data.error == 0) {
//                    alert("对不起，您无权限执行此操作！");
//                    return;
//                } else {
//                    alert("对不起，操作失败！");
//                    return;
//                }
//            }
//        });
//        if (flag != 0) {
//            window.location.href = "../qualityAPPVersionManagement/gotoUpdateVersion?appVersionId=" + appVersionId;
//        }
    }

    function js_del(appVersionId) {
        window.location.href = "../qualityAPPVersionManagement/versionDel?appVersionId=" + appVersionId;
//        var flag = 0;   //操作级别标示
//        $.ajax({
//            type: "GET",
//            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
//            cache: false,
//            async: false,
//            dataType: "json",
//            success: function (data) {
//                if (data.error == 1) {
//                    flag = 1;
//                } else if (data.error == 0) {
//                    alert("对不起，您无权限执行此操作！");
//                    return;
//                } else {
//                    alert("对不起，操作失败！");
//                    return;
//                }
//            }
//        });
//        if (flag != 0) {
//            myvalue = confirm("系统提示：您确定要删除此条记录吗?");
//            if (myvalue == true) {
//                window.location.href = "../qualityAPPVersionManagement/versionDel?appVersionId=" + appVersionId;
//            }
//        }
    }

</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
