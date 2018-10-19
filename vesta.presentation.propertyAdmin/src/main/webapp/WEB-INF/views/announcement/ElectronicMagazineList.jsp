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
        height: 10.5rem;
    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#006900020000").addClass("active");
        $("#006900020000").parent().parent().addClass("in");
        $("#006900020000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006900020000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../electronicMagazine/getElectronicMagazineList.html">
                    <input type="hidden" id="menuId" name="menuId" value="006900020000"/>
                    <%-- 公告标题 --%>
                    <div class="form-group  col-xs-4">
                        <label for="magazineName" class="col-sm-4 control-label">期刊名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="magazineName" name="magazineName" value="${electronicMagazineDTO.magazineName}">
                        </div>
                    </div>
                    <%-- 发布状态 --%>
                    <div class="form-group  col-xs-4">
                        <label for="releaseStatus" class="col-sm-4 control-label">发布状态</label>
                        <div class="col-sm-8">
                            <select id="releaseStatus" name="releaseStatus" class="form-control">
                                <option value="">--请选择状态--</option>
                                <option value="0" <c:if test="${'0' eq electronicMagazineDTO.releaseStatus}">selected</c:if>>未发布</option>
                                <option value="1" <c:if test="${'1' eq electronicMagazineDTO.releaseStatus}">selected</c:if>>已发布</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <button type="button" class="btn btn-primary" onclick="toEdit('')" id="toAdd">新增</button>
                    </div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                <th>所属客户端</th>
                <th>期刊名称</th>
                <th>点赞数</th>
                <th>状态</th>
                <th>发布时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${electronicMagazineList}" var="electronicMagazine" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${electronicMagazine.clientName}</td>
                    <td>${electronicMagazine.magazineName}</td>
                    <td>${electronicMagazine.likeNum}</td>
                    <td>
                        <c:if test="${'0' eq electronicMagazine.releaseStatus}">未发布</c:if>
                        <c:if test="${'1' eq electronicMagazine.releaseStatus}">已发布</c:if>
                    </td>
                    <td><fmt:formatDate type="time" value="${electronicMagazine.releaseDate}" pattern="yyyy-MM-dd" /></td>
                    <td class="last">
                        <a href="javascript:void(0);" onclick="toEdit('${electronicMagazine.id}')" id="toEdit" class="a3">编辑</a>
                        <a href="javascript:void(0);" onclick="toDelete('${electronicMagazine.id}')" id="toDelete" class="a3">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/electronicMagazine/getElectronicMagazineList.html?menuId=006900020000&pageIndex={0}&magazineName=${electronicMagazineDTO.magazineName}&releaseStatus=${electronicMagazineD.releaseStatus}"/>
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
</script>
<script>
    //编辑
    function toEdit(id){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../electronicMagazine/toEditElectronicMagazineInfo.html?id="+id;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //删除
    function toDelete(id){
        if (confirm("确认要删除？")) {
            $.ajax({
                type: "GET",
                url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
                cache: false,
                async:false,
                dataType:"json",
                success: function (data) {
                    if (data.error == 1) {
                        $.ajax({
                            url:"../electronicMagazine/deleteElectronicMagazineInfo",
                            type:"POST",
                            cache:false,
                            async:false,
                            data:{
                                id:id
                            },
                            dataType:"JSON",
                            error:function(){
                                alert("网络异常，可能服务器忙，请刷新重试");
                            },
                            success:function(data){
                                if(data.error == "0"){
                                    alert("删除成功！");
                                    location.reload();
                                }else if(data.error == "-1"){
                                    alert("删除失败，请联系管理员！");
                                }
                            }
                        });
                    }else if(data.error == 0) {
                        alert("对不起，您无权限执行此操作！");
                    }else{
                        alert("对不起，操作失败！");
                    }
                }
            });
        }
    }
</script>
</body>
</html>