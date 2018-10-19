<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/7/19
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <style>
        .tableStyle tbody td {
            padding-left: 0px;
            text-align: center;
        }
        .myTime{
            padding-left: 6px;
            padding-right: 6px;
        }
        .form-horizontal .form-group{
            float: left;
        }
        .form_b{
            height: 15.5rem;
        }
        .search_button{
            text-align: center;
        }
        .control_btn{
            padding: 0 0 1rem 0;
            background-color: #fbfbfb;
        }
        .control_btn button,.control_btn a{
            margin-right: 1rem;
        }
    </style>
    <style type="text/css">
        #test{
            padding-left: 0px;
            text-align: center;
            /*display:block;*//*内联对象需加*/
            /*width: 450px;*/
            word-break:keep-all;/* 不换行 */
            white-space:nowrap;/* 不换行 */
            overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow:ellipsis;
        }
    </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005800030000" username="${propertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form class="form-horizontal" action="../log/userDocumentsLog.html">
                    <input type="hidden" id="menuId" name="menuId" value="005800030000"/>
                    <%--
                    <div class="form-group  col-lg-4">
                        <label for="projectId" class="col-sm-4 control-label">项目</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="projectId" name="projectId" class="">
                                <c:forEach items="${projectList}" var="list">
                                    &lt;%&ndash;<option value="${list.key}" <c:if test="${list.key eq sysLog.projectId}">selected="selected"</c:if>>${list.value}</option>&ndash;%&gt;
                                    <option value="${list.value}"
                                            <c:if test="${list.value eq systemLogDTO.projectId}">selected</c:if>>${list.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    --%>
                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>
                        <div class="col-sm-8">
                            <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq sysLog.scopeId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group  col-xs-4">
                        <label for="projectCode" class="col-sm-4 control-label">项目</label>
                        <div class="col-sm-8">
                            <select id="projectCode" name="projectCode" class="form-control">
                                <c:forEach items="${projectList}" var="item" >
                                    <option value="${item[1] }"
                                            <c:if test="${item[1] eq sysLog.projectCode}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="userType" class="col-sm-4 control-label">用户类型</label>

                        <div class="col-sm-8">
                            <select class="form-control" name="userType" id="userType">
                                <option value="0" <c:if test="${sysLog.userType eq 0}">selected="selected"</c:if>>全部
                                </option>
                                <option value="3" <c:if test="${sysLog.userType eq 3}">selected="selected"</c:if>>业主
                                </option>
                                <option value="4" <c:if test="${sysLog.userType eq 4}">selected="selected"</c:if>>关联住户
                                </option>
                                <option value="2" <c:if test="${sysLog.userType eq 2}">selected="selected"</c:if>>普通用户
                                </option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="sourceFrom" class="col-sm-4 control-label">来源</label>

                        <div class="col-sm-8">
                            <select class="form-control" name="sourceFrom" id="sourceFrom">
                                <option value="0" <c:if test="${sysLog.sourceFrom eq 0}">selected="selected"</c:if>>全部
                                </option>
                                <c:forEach items="${clientConfigList}" var="clientConfig">
                                    <option value="${clientConfig.id}" <c:if test="${sysLog.sourceFrom eq clientConfig.id}">selected="selected"</c:if>>
                                            ${clientConfig.clientName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="userMobile" class="col-sm-4 control-label">手机号码</label>

                        <div class="col-sm-8">
                            <input type="text" id="userMobile" name="userMobile" value="${sysLog.userMobile}"
                                   class="form-control">
                        </div>
                    </div>
                    <!-- 发布日期开始日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="staDate" class="col-sm-4 control-label">开始时间</label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="staDate"
                                   value="${sysLog.beginTime}" name="beginTime" readonly>
                            <span class="input-group-addon myTime"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon myTime"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <!-- 发布日期结束日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="endDate" class="col-sm-4 control-label">结束时间</label>

                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="endDate"
                                   value="${sysLog.endTime}" name="endTime" readonly>
                            <span class="input-group-addon myTime"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon myTime"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <%-- 单据类型 --%>
                    <div class="form-group  col-xs-4">
                        <label for="thisType" class="col-sm-4 control-label">单据类型</label>
                        <div class="col-sm-8">
                            <select class="form-control" name="thisType" id="thisType">
                                <option value="0">请选择</option>
                                <option value="物业报修" <c:if test="${sysLog.thisType eq '物业报修'}">selected="selected"</c:if>>物业报修</option>
                                <option value="便民信息-我要纠错" <c:if test="${sysLog.thisType eq '便民信息-我要纠错'}">selected="selected"</c:if>>便民信息-我要纠错</option>
                                <option value="物业缴费" <c:if test="${sysLog.thisType eq '物业缴费'}">selected="selected"</c:if>>物业缴费</option>
                                <option value="意见反馈" <c:if test="${sysLog.thisType eq '意见反馈'}">selected="selected"</c:if>>意见反馈</option>
                                <option value="关于app-对软件反馈" <c:if test="${sysLog.thisType eq '关于app-对软件反馈'}">selected="selected"</c:if>>关于app-对软件反馈</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="common_search"/></button>
                        <input type="hidden" id="size" value="${isExcel}"/>
                        <a href="javascript:void(0);" onclick="isExcel()" type="button" class="btn btn-primary"
                           for="propertyAdd">导出Excel</a>
                    </div>
                    <div class="clearfix"></div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>


    <div class="table-responsive bs-example widget-shadow">
        <div class="">

        </div>
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td>序号</td>
                <th>应用时间</th>
                <th>用户名</th>
                <th>手机号码</th>
                <th>单据类型</th>
                <th>单据编号</th>
                <th>来源</th>
                <th>单据内容</th>
                <th>项目</th>
                <th>房产信息</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${logList}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index+1}</b></td>
                    <td>${list.logTime}</td>
                    <td>${list.userName}</td>
                    <td>${list.userMobile}</td>
                    <td id="test" align="center">${list.thisType}</td>
                    <td>${list.docNum}</td>
                    <td>
                        <%--
                        <c:choose>
                            <c:when test="${list.sourceFrom eq 1}">APP</c:when>
                            <c:when test="${list.sourceFrom eq 2}">微信</c:when>
                        </c:choose>
                        --%>
                        <c:if test="${empty list.sourceFrom or list.sourceFrom eq ''}">
                            无法捕获
                        </c:if>
                        <c:if test="${!empty list.sourceFrom and list.sourceFrom ne ''}">
                            <c:forEach items="${clientConfigList}" var="clientConfig">
                                <c:if test="${list.sourceFrom eq clientConfig.id}">${clientConfig.clientName}</c:if>
                            </c:forEach>
                        </c:if>
                    </td>
                    <c:if test="${fn:length(list.logContent)>'13'}">
                        <td id="test">${fn:substring(list.logContent,0,13)}...</td>
                    </c:if>
                    <c:if test="${fn:length(list.logContent)<='13'}">
                        <td id="test">${list.logContent}</td>
                    </c:if>
                    <td>${list.projectName}</td>
                    <td>${list.realEstate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../log/userDocumentsLog.html?menuId=005800030000&pageIndex={0}&scopeId=${systemLogDTO.scopeId}&projectCode=${systemLogDTO.projectCode}&userType=${systemLogDTO.userType}&sourceFrom=${systemLogDTO.sourceFrom}&userMobile=${systemLogDTO.userMobile}&beginTime=${systemLogDTO.beginTime}&endTime=${systemLogDTO.endTime}"/>
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
    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
            var href = "../log/userDocumentsLogExportExcel?scopeId=${systemLogDTO.scopeId}&projectCode=${systemLogDTO.projectCode}&userType=${systemLogDTO.userType}&sourceFrom=${systemLogDTO.sourceFrom}&userMobile=${systemLogDTO.userMobile}&beginTime=${systemLogDTO.beginTime}&endTime=${systemLogDTO.endTime}&thisType=${systemLogDTO.thisType}";
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }

</script>
</body>
</html>
