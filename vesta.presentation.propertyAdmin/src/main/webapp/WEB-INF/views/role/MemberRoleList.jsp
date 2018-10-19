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
<style>
    .form-group {
        padding-right: 0;
    }

    .col-xs-1, .col-sm-1, .col-md-1, .col-lg-1, .col-xs-2, .col-sm-2, .col-md-2, .col-lg-2, .col-xs-3, .col-sm-3, .col-md-3, .col-lg-3, .col-xs-4, .col-sm-4, .col-md-4, .col-lg-4, .col-xs-5, .col-sm-5, .col-md-5, .col-lg-5, .col-xs-6, .col-sm-6, .col-md-6, .col-lg-6, .col-xs-7, .col-sm-7, .col-md-7, .col-lg-7, .col-xs-8, .col-sm-8, .col-md-8, .col-lg-8, .col-xs-9, .col-sm-9, .col-md-9, .col-lg-9, .col-xs-10, .col-sm-10, .col-md-10, .col-lg-10, .col-xs-11, .col-sm-11, .col-md-11, .col-lg-11, .col-xs-12, .col-sm-12, .col-md-12, .col-lg-12{
        padding-right: 0;
    }
    .input-group[class*="col-"]{
        padding-right: 0;
    }
    .form_b{
        height: 15rem;
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
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="002300010000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../memberAuthority/roleList.html">
                    <input type="hidden" id="menuId" name="menuId" value="002300010000"/>
                    <!-- 数据查看范围 -->
                    <div class="col-sm-12 form-group">数据查看范围</div>
                    <div class="form-group  col-xs-4">
                        <label for="remarks" class="col-sm-3 control-label">区域</label>

                        <div class="col-sm-8">
                            <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">--请选择城市--</option>
                                <c:forEach items="${city}" var="item">
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq roleRolesetDTO.scopeId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group  col-xs-4">
                        <label for="remarks" class="col-sm-3 control-label">项目</label>

                        <div class="col-sm-8">
                            <select id="projectCode" name="projectCode" class="form-control">
                                <c:forEach items="${projectList}" var="project">
                                    <option value="${project[0]}"
                                            <c:if test="${project[0] eq roleRolesetDTO.projectCode}">selected</c:if>>${project[1]}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--<div class="col-sm-8">
                      <input type="text" class="form-control" placeholder="" id="projectScope"
                             name="projectScope" value="${roleRolesetDTO.projectScope}">
                    </div>--%>

                    <!-- 角色名称 -->
                    <div class="form-group  col-xs-4">
                        <label for="roledesc" class="col-sm-3 control-label">角色名称</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="roledesc"
                                   name="roledesc" value="${roleRolesetDTO.roledesc}">
                        </div>
                    </div>
                    <!-- 角色备注 -->
                    <div class="form-group  col-xs-4">
                        <label for="remarks" class="col-sm-3 control-label">备注</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="remarks"
                                   name="remarks" value="${roleRolesetDTO.remarks}">
                        </div>
                    </div>
                    <!-- 创建时间查询_开始日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="staDate" class="col-sm-3 control-label">创建时间</label>

                        <div class="input-group date form_date col-sm-8" data-date=""
                             data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="staDate"
                                   value="${roleRolesetDTO.staDate}" name="staDate" readonly/>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <!-- 创建时间查询_结束日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="endDate" class="col-sm-3 control-label"><spring:message
                                code="HousePayBatch.to"/></label>

                        <div class="input-group date form_date col-sm-8" data-date=""
                             data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="endDate"
                                   value="${roleRolesetDTO.endDate}" name="endDate" readonly/>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="form-group  col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <%--<a href="../memberAuthority/toCreateRole.html" class="btn btn-primary" for="payBatchAdd"><spring:message code="common_add"/></a>--%>
                        <a href="javascript:void(0);" onclick="toAdd()" class="btn btn-primary"
                           for="rolesetAdd"><spring:message code="common_add"/></a>
                        <!--集合长度(取决Excel是否可以导出)-->
                        <input type="hidden" id="size" value="${isExcel}">
                        <a href="javascript:void(0);" onclick="isExcel()" value="" class="btn btn-primary"
                           style="color:#fff">导出Excel</a>
                    </div>
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
                <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                <th>角色名称</th>
                <th>备注</th>
                <th>数据查看范围</th>
                <th>分类</th>
                <th>创建时间</th>
                <th>管理操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${roleRolesetDTOs}" var="roleRolesetDTO" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${roleRolesetDTO.roledesc}</td>
                    <td>${roleRolesetDTO.remarks}</td>
                    <td>${roleRolesetDTO.scopeName}</td>
                    <td>
                        <c:if test="${roleRolesetDTO.setType eq '1'}">金茂会员</c:if>
                        <c:if test="${roleRolesetDTO.setType eq '2'}">金茂质检</c:if>
                        <c:if test="${roleRolesetDTO.setType eq '3'}">公共</c:if>
                    </td>
                    <td>${roleRolesetDTO.makeDate}</td>
                    <td class="last">
                        <a href="javascript:void(0);"
                           onclick="js_roleMemberList('${roleRolesetDTO.setId}','${roleRolesetDTO.roledesc}')"
                           id="detail"
                           class="a3">成员管理</a>
                        <a href="javascript:void(0);" onclick="js_update('${roleRolesetDTO.setId}')" id="update"
                           class="a3">编辑</a>
                        <a href="javascript:void(0);" onclick="js_del('${roleRolesetDTO.setId}')" id="del"
                           class="a3">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/memberAuthority/roleList.html?pageIndex={0}&roledesc=${roleRolesetDTO.roledesc}&remarks=${roleRolesetDTO.remarks}&projectScope=${roleRolesetDTO.projectScope}&staDate=${roleRolesetDTO.staDate}&endDate=${roleRolesetDTO.endDate}&scopeId=${roleRolesetDTO.scopeId}&projectCode=${roleRolesetDTO.projectCode}&menuId=002300010000"/>
    </div>

</div>


</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>

<script type="text/javascript">
    <%--var pages = "${webPage.pageIndex}";--%>
    <%--var roledesc = "${roleRolesetDTO.roledesc}";--%>
    <%--var remarks = ${roleRolesetDTO.remarks};--%>
    <%--var projectScope = "${roleRolesetDTO.projectScope}";--%>
    <%--var staDate = "${roleRolesetDTO.staDate}";--%>
    <%--var endDate = "${roleRolesetDTO.endDate}";--%>
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
<script>
    /*function queryProjectNameById() {
     var projectId = $("#scopeId").val();
     if(projectId == '-1'){
     $("#projectCode").empty();
     return ;
     }
     $.getJSON("/announcement/queryProjectNameByCityId/" + projectId, function (data) {
     var arr = data.data;
     $("#projectCode").empty();
    <%--$("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');--%>
     for (var k in arr) {
     if(arr[k][0] != '0'){
     $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
     }
     }

     });
     }*/
    function queryProjectNameById() {
        var projectId = $("#scopeId").val();
        if (projectId == '-1') {
            $("#projectCode").empty();
            return;
        }
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId + "/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectCode").empty();
            for (var k in arr) {
                if (arr[k][0] != '0') {
                    $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
                }
            }
        });
    }

    function toAdd() {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../memberAuthority/toCreateRole.html?menuId=" + $("#menuId").val();
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    function js_del(id) {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    $.ajax({
                        type: "GET",
                        url: "../memberAuthority/checkEdit/" + id + "/" + $("#menuId").val(),
                        cache: false,
                        async: false,
                        dataType: "json",
                        success: function (data) {
                            if (data.error == 1) {
                                myvalue = confirm("确定要删除吗?");
                                if (myvalue == true) {
                                    $.ajax({
                                        type: "POST",
                                        url: "../memberAuthority/deleteRole",
                                        data: {
                                            setId: id
                                        },
                                        dataType: "json",
                                        success: function (data) {
                                            if (data.error == '0') {
                                                alert("删除成功");
                                                $("#form").submit();
                                            } else if (data.error == '2') {
                                                alert("该角色下有绑定成员,请先将该角色下成员重新分配角色再执行删除!");
                                            } else {
                                                alert("对不起，操作失败");
                                            }
                                        }
                                    });
                                }
                            } else if (data.error == 0) {
                                alert("对不起，您的权限范围无法执行此操作！");
                                return;
                            } else {
                                alert("对不起，操作失败！");
                                return;
                            }
                        }
                    });

                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    function js_update(id) {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    $.ajax({
                        type: "GET",
                        url: "../memberAuthority/checkEdit/" + id + "/" + $("#menuId").val(),
                        cache: false,
                        async: false,
                        dataType: "json",
                        success: function (data) {
                            if (data.error == 1) {
                                window.location.href = "../memberAuthority/toEditRoleSet?setId=" + id + "&menuId=" + $("#menuId").val();
                            } else if (data.error == 0) {
                                alert("对不起，您的权限范围无法执行此操作！");
                                return;
                            } else {
                                alert("对不起，操作失败！");
                                return;
                            }
                        }
                    });
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    function js_roleMemberList(id, roleName) {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    $.ajax({
                        type: "GET",
                        url: "../memberAuthority/checkEdit/" + id + "/" + $("#menuId").val(),
                        cache: false,
                        async: false,
                        dataType: "json",
                        success: function (data) {
                            if (data.error == 1) {
                                window.location.href = "../memberAuthority/roleMemberList?roleSetId=" + id + "&roleName=" + roleName;
                            } else if (data.error == 0) {
                                alert("对不起，您的权限范围无法执行此操作！");
                                return;
                            } else {
                                alert("对不起，操作失败！");
                                return;
                            }
                        }
                    });
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }

    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
            document.getElementById("form").action = "../memberAuthority/exportExcel";
            document.getElementById("form").submit();
        }else {
            alert("没有可以导出的数据");
        }
    }
</script>
</body>
</html>