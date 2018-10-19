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
    .control_btn{
        padding: 0 0 1rem 0;
        background-color: #fbfbfb;
    }
    .control_btn button,.control_btn a{
        margin-right: 1rem;
    }
    .form_b{
        height: 14rem;
    }
    .tableStyle .replyNum{
        width: 5rem;
    }
    .tableStyle .likeNum{
        width: 5rem;
    }
</style>
<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005500020000" username="${propertystaff.staffName}"/>

    <input type="hidden" id="menuId" value="005500020000"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../announcement/List.html">
                    <!-- 发布日期开始日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="staDate" class="col-sm-3 control-label"><spring:message
                                code="announcementDTO.createDate"/></label>

                        <div class="input-group date form_date col-sm-8" data-date=""
                             data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="staDate"
                                   value="${announcementDTO.staDate}" name="staDate" readonly>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <!-- 发布日期结束日期 -->
                    <div class="form-group  col-xs-4">
                        <label for="endDate" class="col-sm-3 control-label"><spring:message
                                code="HousePayBatch.to"/></label>

                        <div class="input-group date form_date col-sm-8" data-date=""
                             data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="endDate"
                                   value="${announcementDTO.endDate}" name="endDate" readonly>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <!-- 发布状态 -->
                    <div class="form-group  col-xs-4">
                        <label for="releaseStatus" class="col-sm-3 control-label"><spring:message
                                code="advertServices.state"/></label>

                        <div class="col-sm-8">
                            <select id="releaseStatus" name="releaseStatus" class="form-control">
                                <c:forEach items="${statusMap}" var="item">
                                    <option value="${item.id }"
                                            <c:if test="${item.id eq announcementDTO.releaseStatus}">selected</c:if>>
                                            ${item.name }
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 关键词 -->
                    <div class="form-group  col-xs-4">
                        <label for="title" class="col-sm-3 control-label">关键词</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="title"
                                   name="title" value="${announcementDTO.title}">
                        </div>
                    </div>
                    <div class='clearfix'></div>
                    <div class="form-group  col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <button type="button" class="btn btn-primary" onclick="transferPage('005500020000')"><spring:message code="common_add"/></button>
                        <%--<a href="../announcement/transferPage" class="btn btn-primary" for="payBatchAdd"><spring:message code="common_add"/></a>--%>
                        <!--集合长度(取决Excel是否可以导出)-->
                        <input type="hidden" id="size" value="${isExcel}">
                        <a href="javascript:void(0);"  onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>

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
                <th><spring:message code="announcementDTO.title"/></th>
                <th><spring:message code="announcementDTO.releasePerson"/></th>
                <th><spring:message code="announcementDTO.city"/></th>
                <th><spring:message code="propertyCompany.propertyProject"/></th>
                <th><spring:message code="announcementDTO.createDate"/></th>
                <th><spring:message code="advertServices.state"/></th>
                <th class="replyNum"><spring:message code="announcementDTO.replyNum"/></th>
                <th class="likeNum"><spring:message code="announcementDTO.likeNum"/></th>
                <th width="174"><spring:message code="billInfo.opera"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${announcementDTOs}" var="announcementDTOs" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${announcementDTOs.title}</td>
                    <td>${announcementDTOs.releasePerson}</td>
                    <td>${announcementDTOs.citys}</td>
                    <td>${announcementDTOs.projects}</td>
                    <td>
                        <fmt:formatDate value="${announcementDTOs.createDate}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <c:if test="${announcementDTOs.releaseStatus eq 0 }"><spring:message
                                code="activityManage.activityStatus_5"/></c:if>
                        <c:if test="${announcementDTOs.releaseStatus eq 1 }"><spring:message
                                code="activityManage.activityStatus_6"/></c:if>
                    </td>
                    <td>${announcementDTOs.replyNum}</td>
                    <td>${announcementDTOs.likeNum}</td>
                    <td class="last">
                        <a href="javascript:void(0);" onclick="js_detail('${announcementDTOs.id}')" id="detail"
                           class="a3">评论<spring:message code="workOrders.detail"/></a>
                        <a href="javascript:void(0);" onclick="js_update('${announcementDTOs.id}')" id="update"
                           class="a3">公告<spring:message code="propertyServices.serviceModify"/></a>
                        <a href="javascript:void(0);" onclick="js_del('${announcementDTOs.id}')" id="del"
                           class="a3"><spring:message code="propertyServices.serviceDelete"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/announcement/List.html?pageIndex={0}&releaseStatus=${announcementDTO.releaseStatus}&title=${announcementDTO.title}&content=${announcementDTO.content}&releasePerson=${announcementDTO.releasePerson}&staDate=${announcementDTO.staDate}&endDate=${announcementDTO.endDate}"/>
    </div>

</div>
</div>
</div>
</div>
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
    var title = "${announcementDTO.title}";
    var content = "${announcementDTO.content}";
    var releasePerson = ${announcementDTO.releasePerson};
    var releaseStatus = "${announcementDTO.releaseStatus}";
    var staDate = "${announcementDTO.staDate}";
    var endDate = "${announcementDTO.endDate}";

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

    function transferPage(menuId){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+menuId,
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../announcement/transferPage?menuId="+menuId;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }

    function js_detail(id) {
        console.log(id+"sqq");
        window.location.href = "../replyDetail/BBSreplyList.html?topicId=" + id;
    }
    function js_update(id) {
        var flag = 0;   //操作级别标示
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    flag = 1;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                    return ;
                }else{
                    alert("对不起，操作失败！");
                    return ;
                }
            }
        });
        var flag2 = 0;  //操作范围标示
        if(flag != 0) {
            $.ajax({
                type: "GET",
                url: "../announcement/checkEdit/" + id + "/" + $("#menuId").val(),
                cache: false,
                async: false,
                dataType: "json",
                success: function (data) {
                    if (data.error == 1) {
                        flag2 = 1;
                    } else if (data.error == 0) {
                        alert("对不起，您的权限范围无法执行此操作！");
                        return;
                    } else {
                        alert("对不起，操作失败！");
                        return;
                    }
                }
            });
        }
        if(flag != 0 && flag2 != 0){
            window.location.href = "../announcement/transferPage?id=" + id + "&menuId="+$("#menuId").val();
        }
    }
    function js_del(id) {
        var flag = 0;   //操作级别标示
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    flag = 1;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                    return ;
                }else{
                    alert("对不起，操作失败！");
                    return ;
                }
            }
        });
        var flag2 = 0;  //操作范围标示
        if(flag != 0){
            $.ajax({
                type: "GET",
                url: "../announcement/checkEdit/"+id+"/"+$("#menuId").val(),
                cache: false,
                async:false,
                dataType:"json",
                success: function (data) {
                    if (data.error == 1) {
                        flag2 = 1;
                    }else if(data.error == 0) {
                        alert("对不起，您的权限范围无法执行此操作！");
                        return ;
                    }else{
                        alert("对不起，操作失败！");
                        return ;
                    }
                }
            });
        }
        if(flag != 0 && flag2 != 0){
            myvalue = confirm("确定要删除吗?");
            if (myvalue == true) {
                $.ajax({
                    type: "POST",
                    url: "../announcement/deleteAnnouncementVote",
                    cache: false,
                    data: "id=" + id + "&&" + "select=cancelStatus" + "&&" + "flag=" + 0,
                    success: function (data) {
                        if (data.error == '0') {
                            alert("删除成功");
                            //                        alert(data.error);
                            $("#form").submit();
                        } else {
                            alert("对不起，操作失败");
                        }
                    }
                });
            }
        }
    }
    function js_release(id) {
        $.ajax({
            type: "POST",
            url: "../overview/updateStatus",
            cache: false,
            data: "id=" + id + "&&" + "select=releaseStatus" + "&&" + "flag=" + 1,
            success: function (data) {
                if (data.code == '0') {
                    $("#form").submit();
                } else {
                    alert("对不起，操作失败");
                }
            }
        });

    }
    function js_unRelease(id) {
        $.ajax({
            type: "POST",
            url: "../overview/updateStatus",
            cache: false,
            data: "id=" + id + "&&" + "select=releaseStatus" + "&&" + "flag=" + 0,
            success: function (data) {
                if (data.code == '0') {
                    $("#form").submit();
                } else {
                    alert("对不起，操作失败");
                }
            }
        });
    }

    function isExcel(){
        var size = $("#size").val();
        if(size>0){
            var href = "../announcement/exportExcel?pageIndex={0}&releaseStatus=${announcementDTO.releaseStatus}&title=${announcementDTO.title}&content=${announcementDTO.content}&releasePerson=${announcementDTO.releasePerson}&staDate=${announcementDTO.staDate}&endDate=${announcementDTO.endDate}";
            window.location.href = href;
        }else{
            alert("没有可以导出的数据");
        }
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>