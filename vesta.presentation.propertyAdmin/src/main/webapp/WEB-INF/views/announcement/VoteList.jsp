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
</style>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005500040000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" value="005500040000"/>
    <%--搜索--%>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form id="form" class="form-horizontal" action="../vote/voteList.html">

                    <!-- 投票标题 -->
                    <div class="form-group  col-xs-6">
                        <label for="title" class="col-sm-3 control-label"><spring:message
                                code="vote.title"/></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="title"
                                   name="title" value="${voteDTO.title}">
                        </div>
                    </div>

                    <!-- 投票状态 -->
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-3 control-label"><spring:message
                                code="vote.state"/></label>

                        <div class="col-sm-8">
                            <select id="voteStatus" name="voteStatus" class="form-control">
                                <option value="3">--请选择--</option>
                                <c:forEach items="${statusMap}" var="item">
                                    <option value="${item.id }"
                                            <c:if test="${item.id eq voteDTO.voteStatus}">selected</c:if>>
                                            ${item.name }
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- 发布日期开始日期 -->
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-3 control-label"><spring:message
                                code="vote.createDate"/></label>

                        <div class="input-group date form_date col-sm-8" data-date=""
                             data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="queryStaDate"
                                   value="${voteDTO.queryStaDate}" name="queryStaDate" readonly>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <!-- 发布日期结束日期 -->
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-3 control-label"><spring:message
                                code="HousePayBatch.to"/></label>

                        <div class="input-group date form_date col-sm-8" data-date=""
                             data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="queryEndDate"
                                   value="${voteDTO.queryEndDate}" name="queryEndDate" readonly>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="form-group  col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <button type="button" class="btn btn-primary" onclick="transferPage('005500040000')"><spring:message code="common_add"/></button>
                        <%--<a href="../vote/toVoteEdit.html" class="btn btn-primary" for="payBatchAdd"><spring:message code="common_add"/></a>--%>
                        <!--集合长度(取决Excel是否可以导出)-->
                        <input type="hidden" id="size" value="${isExcel}">
                        <a href="javascript:void(0);"  onclick="isExcel()" value="" class="btn btn-primary" style="color:#fff">导出Excel</a>

                    </div>
                    <div class='clearfix'></div>


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
                <th><spring:message code="vote.title"/></th>
                <c:if test="${voteDTO.voteStatus !='3'}">
                </c:if>
                <th>投票状态</th>
                <th><spring:message code="vote.releasePerson"/></th>
                <th><spring:message code="vote.createDate"/></th>
                <th><spring:message code="vote.voteNum"/></th>
                <th><spring:message code="billInfo.opera"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${voteDTOs}" var="voteDTO" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${voteDTO.title}</td>

                    <c:if test="${voteDTO.voteStatus=='0'}">
                        <td>未开始</td>
                    </c:if>
                    <c:if test="${voteDTO.voteStatus=='1'}">
                        <td>进行中</td>
                    </c:if>
                    <c:if test="${voteDTO.voteStatus=='2'}">
                        <td>已结束</td>
                    </c:if>

                    <td>${voteDTO.createPerson}</td>
                    <td>
                        <fmt:formatDate value="${voteDTO.createDate}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>${voteDTO.voteCount}</td>
                    <td class="last">
                        <a href="javascript:void(0);" onclick="js_detail('${voteDTO.id}')" id="detail"
                           class="a3"><spring:message code="workOrders.detail"/></a>
                        <a href="javascript:void(0);" onclick="js_del('${voteDTO.id}')" id="del"
                           class="a3"><spring:message code="propertyServices.serviceDelete"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/vote/voteList.html?pageIndex={0}&title=${voteDTO.title}&voteStatus=${voteDTO.voteStatus}&queryStaDate=${voteDTO.queryStaDate}&queryEndDate=${voteDTO.queryEndDate}"/>
    </div>

</div>
</div>
</div>
</div>

<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
    var title = "${voteDTO.title}";
    var voteStatus = "${voteDTO.voteStatus}";
    var queryStaDate = "${voteDTO.queryStaDate}";
    var queryEndDate = "${voteDTO.queryEndDate}";
</script>
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
                    window.location.href = "../vote/toVoteEdit.html";
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }

    function js_detail(id) {
        window.location.href = "../vote/getVoteDetail.html?id=" + id;
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
        if(flag != 0){
            myvalue = confirm("确定要删除吗?");
            if (myvalue == true) {
                $.ajax({
                    type: "POST",
                    url: "../vote/delVote",
                    cache: false,
                    data: "id=" + id,
                    success: function (data) {
                        if (data.code == '0') {
                            alert("删除成功");
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
            data: "id=" + id,
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
            var href = "../vote/exportExcel?pageIndex={0}&title=${voteDTO.title}&voteStatus=${voteDTO.voteStatus}&queryStaDate=${voteDTO.queryStaDate}&queryEndDate=${voteDTO.queryEndDate}";
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
