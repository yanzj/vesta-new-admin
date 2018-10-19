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
    .form-horizontal .form-group {
        float: left;
    }

    .form_b {
        height: 16rem;
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

    .btstyle {
        width: 9rem;
    }
</style>
<script type="text/javascript">
    function isExcel() {
        var size = $("#size").val();
        if (size > 0) {
            window.location.href = "../staffUser/exportExcel?scopeId=${userPropertystaffDTO.scopeId}&projectCode=${userPropertystaffDTO.projectCode}&userNameDto=${userPropertystaffDTO.userNameDto}&mobileDto=${userPropertystaffDTO.mobileDto}&staffNameDto=${userPropertystaffDTO.staffNameDto}&roledesc=${userPropertystaffDTO.roledesc}&beginTimeDto=${userPropertystaffDTO.beginTimeDto}&endTimeDto=${userPropertystaffDTO.endTimeDto}&pageIndex=${webPage.pageIndex}&pageSize=${webPage.pageSize}&recordCount=${webPage.recordCount}&submitUrl=${pageContext.request.contextPath }/staffUser/staffuserlist?pageIndex={0}";
        } else {
            return;
        }
    }
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="002300020000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" value="002300020000"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../staffUser/staffUserList.html">
                    <!-- 区域 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>

                        <div class="col-sm-8">
                            <%--
                            <input type="text" class="form-control" placeholder="" id="scope"
                                   name="scope" value="${userPropertystaffDTO.scope}">
                            --%>
                            <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">--请选择城市--</option>
                                <c:forEach items="${city}" var="item">
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq userPropertystaffDTO.scopeId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group  col-xs-4">
                        <label for="projectCode" class="col-sm-3 control-label">项目</label>

                        <div class="col-sm-8">
                            <%--
                            <input type="text" class="form-control" placeholder="" id="projectName"
                                   name="projectName" value="${userPropertystaffDTO.projectName}">
                            --%>
                            <select id="projectCode" name="projectCode" class="form-control">
                                <c:forEach items="${projectList}" var="project">
                                    <option value="${project[0]}"
                                            <c:if test="${project[0] eq userPropertystaffDTO.projectCode}">selected</c:if>>${project[1]}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 账号名 -->
                    <div class="form-group  col-xs-4">
                        <label for="userNameDto" class="col-sm-4 control-label">账号名</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="userNameDto"
                                   name="userNameDto" value="${userPropertystaffDTO.userNameDto}">
                        </div>
                    </div>
                    <!-- 联系方式 -->
                    <div class="form-group  col-xs-4">
                        <label for="mobileDto" class="col-sm-4 control-label">手机号码</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="mobileDto"
                                   name="mobileDto" value="${userPropertystaffDTO.mobileDto}">
                        </div>
                    </div>
                    <!-- 所属公司 -->
                    <%--<div class="form-group  col-lg-4">
                      <label for="company" class="col-sm-3 control-label">所属公司：</label>
                      <div class="col-sm-8">
                        <input type="text" class="form-control" placeholder="" id="company"
                               name="company" value="${userPropertystaffDTO.company}">
                      </div>
                    </div>--%>
                    <!-- 姓名 -->
                    <div class="form-group  col-xs-4">
                        <label for="userNameDto" class="col-sm-3 control-label">姓名</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="staffNameDto"
                                   name="staffNameDto" value="${userPropertystaffDTO.staffNameDto}">
                        </div>
                    </div>
                    <!-- 角色 -->
                    <div class="form-group  col-xs-4">
                        <label for="roledesc" class="col-sm-4 control-label">角色</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="roledesc"
                                   name="roledesc" value="${userPropertystaffDTO.roledesc}">
                        </div>
                    </div>
                    <!-- 创建时间查询_开始日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="beginTimeDto" class="col-sm-4 control-label">创建时间</label>

                        <div class="input-group date form_date col-sm-8" data-date=""
                             data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="beginTimeDto"
                                   value="${userPropertystaffDTO.beginTimeDto}" name="beginTimeDto" readonly/>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <!-- 创建时间查询_结束日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="endTimeDto" class="col-sm-3 control-label"><spring:message
                                code="HousePayBatch.to"/></label>

                        <div class="input-group date form_date col-sm-8" data-date=""
                             data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="endTimeDto"
                                   value="${userPropertystaffDTO.endTimeDto}" name="endTimeDto" readonly/>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span
                      class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <div class="form-group  col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <%--<a href="../staffUser/toEditStaffUser.html" class="btn btn-primary" for="payBatchAdd"><spring:message code="common_add"/></a>--%>
                        <a href="javascript:void(0);" onclick="toAdd()" class="btn btn-primary"
                           for="payBatchAdd"><spring:message code="common_add"/></a>
                        <button type="button" class="btn btn-primary btstyle" onclick="batchSetRole()">批量设置角色</button>
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
        <form id="batchForm" class="form-horizontal" action="../staffUser/toBatchSetRole">
            <table width="100%" class="tableStyle">
                <thead>
                <tr>
                    <td width="52px">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" id="checkAll" value="">
                            </label>
                        </div>
                    </td>
                    <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                    <th>姓名</th>
                    <th>账号名</th>
                    <th>手机号码</th>
                    <th>公司</th>
                    <th>区域</th>
                    <th>项目</th>
                    <th>角色</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${staffUserList}" var="staffUser" varStatus="row">
                    <tr>
                        <td>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" id="staffUserIdList" class="staffUserIdList"
                                           name="staffUserIdList" value="${staffUser.staffId}"/>
                                </label>
                            </div>
                        </td>
                        <td><b>${row.index + 1}</b></td>
                        <td>${staffUser.staffName}</td>
                        <td>${staffUser.userName}</td>
                        <td>${staffUser.mobile}</td>
                        <td>${staffUser.company}</td>
                        <td>${staffUser.scope}</td>
                        <td>${staffUser.project}</td>
                        <td>${staffUser.roleDesc}</td>
                        <td class="last">
                            <a href="javascript:void(0);" onclick="resetPassWord('${staffUser.staffId}')" id="detail"
                               class="a3">密码重置</a>
                            <a href="javascript:void(0);" onclick="editStaffUser('${staffUser.staffId}')" id="update"
                               class="a3">编辑</a>
                            <a href="javascript:void(0);" onclick="delStaffUser('${staffUser.staffId}')" id="del"
                               class="a3">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/staffUser/staffUserList.html?pageIndex={0}&userNameDto=${userPropertystaffDTO.userNameDto}&mobileDto=${userPropertystaffDTO.mobileDto}&company=${userPropertystaffDTO.company}&roledesc=${userPropertystaffDTO.roledesc}&beginTimeDto=${userPropertystaffDTO.beginTimeDto}&endTimeDto=${userPropertystaffDTO.endTimeDto}&staffNameDto=${userPropertystaffDTO.staffNameDto}"/>
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
</script>
<script>
    $(function () {
        //全选
        $("#checkAll").click(function () {
            var isCheck = $(this).is(':checked');
            var arr = $(".staffUserIdList");
            for (var i = 0; i < arr.length; i++) {
                if (isCheck) {
                    //已选中
                    arr[i].checked = true;
                } else {
                    //未选中
                    arr[i].checked = false;
                }
            }
        });
    });
    function queryProjectNameById() {
        var projectId = $("#scopeId").val();
        if (projectId == '-1') {
            $("#projectCode").empty();
            return;
        }
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId, function (data) {
            var arr = data.data;
            $("#projectCode").empty();
            <%--$("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');--%>
            for (var k in arr) {
                $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
//                alert(arr[k][0]);
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
                    window.location.href = "../staffUser/toEditStaffUser.html";
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //重置密码
    function resetPassWord(staffIdDto) {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    myvalue = confirm("确定要重置该用户密码吗?");
                    if (myvalue == true) {
                        $.ajax({
                            type: "POST",
                            url: "../staffUser/resetPassWord",
                            data: {
                                staffIdDto: staffIdDto
                            },
                            dataType: "json",
                            success: function (data) {
                                if (data.error == '0') {
                                    alert("密码重置成功！密码：123456");
                                    $("#form").submit();
                                } else {
                                    alert("密码重置失败！");
                                }
                            }
                        });
                    }
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //编辑
    function editStaffUser(staffIdDto) {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../staffUser/toEditStaffUser.html?staffIdDto=" + staffIdDto;
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //删除
    function delStaffUser(staffIdDto) {
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/" + $("#menuId").val(),
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    myvalue = confirm("确定要删除吗?");
                    if (myvalue == true) {
                        $.ajax({
                            type: "POST",
                            url: "../staffUser/delStaffUser",
                            data: {
                                staffIdDto: staffIdDto
                            },
                            dataType: "json",
                            success: function (data) {
                                if (data.error == '0') {
                                    alert("删除成功！");
                                    $("#form").submit();
                                } else {
                                    alert("对不起，操作失败！");
                                }
                            }
                        });
                    }
                } else if (data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                } else {
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //批量设置角色
    function batchSetRole() {
        var isCheck = $("#checkAll").is(':checked');
        if (isCheck) {
            var scopeId = $("#scopeId").val();
            var projectCode = $("#projectCode").val();
            if (projectCode == null) {
                projectCode = "";
            }
            var userNameDto = $("#userNameDto").val();
            var mobileDto = $("#mobileDto").val();
            var staffNameDto = $("#staffNameDto").val();
            var roledesc = $("#roledesc").val();
            var beginTimeDto = $("#beginTimeDto").val();
            var endTimeDto = $("#endTimeDto").val();
            window.location.href = "../staffUser/toBatchSetRole?scopeId=" + scopeId + "&projectCode=" + projectCode +
                    "&userNameDto=" + userNameDto + "&mobileDto=" + mobileDto + "&staffNameDto=" + staffNameDto + "&roledesc=" + roledesc +
                    "&beginTimeDto=" + beginTimeDto + "&endTimeDto=" + endTimeDto + "&batchRoleState=all";
        } else {
            $("#batchForm").submit();
        }
    }

</script>
</body>
</html>