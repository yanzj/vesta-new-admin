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
        height: 9rem;
    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#000200070000").addClass("active");
        $("#000200070000").parent().parent().addClass("in");
        $("#000200070000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="000200070000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../defaultConfig/clientConfigList.html">
                    <input type="hidden" id="menuId" name="menuId" value="000200070000"/>
                    <div class="col-lg-12 search_button">
                        <button type="button" class="btn btn-primary" id="add">新增</button>
                        <button type="button" class="btn btn-primary" onclick="goBack()">返回</button>
                    </div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <form id="batchForm" class="form-horizontal" action="#">
            <table width="100%" class="tableStyle">
                <thead>
                <tr>
                    <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                    <th>客户端名称</th>
                    <th>AppId</th>
                    <th>AppSecret</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${clientConfigList}" var="clientConfig" varStatus="row">
                    <tr>
                        <td><b>${row.index + 1}</b></td>
                        <td>${clientConfig.clientName}</td>
                        <td>${clientConfig.weChatAppId}</td>
                        <td>${clientConfig.weChatAppSecret}</td>
                        <td class="last">
                            <a href="javascript:void(0);" onclick="toEdit('${clientConfig.id}')" id="edit"
                               class="a3">编辑</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/defaultConfig/clientConfigList.html?menuId=000200070000&pageIndex={0}&cityId=${userTypeProjectConfigDTO.cityId}&projectNum=${userTypeProjectConfigDTO.projectNum}"/>
    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
<!-- 编辑客户端配置 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <h4>编辑客户端配置</h4>
                        <input type="hidden" class="form-control" id="id" name="id"/>
                        <%--客户端名称--%>
                        <div class="form-group  col-lg-6">
                            <label for="clientName" class="col-sm-3 control-label">客户端名称</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="clientName" name="clientName"/>
                            </div>
                        </div>
                        <%--客户端编码--%>
                        <div class="form-group  col-lg-6">
                            <label for="weChatAppId" class="col-sm-3 control-label">AppId</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="weChatAppId" name="weChatAppId"/>
                            </div>
                        </div>
                        <%--AppSecret--%>
                        <div class="form-group  col-lg-6">
                            <label for="weChatAppId" class="col-sm-3 control-label">AppSecret</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="weChatAppSecret" name="weChatAppSecret"/>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <%--<button type="submit" class="confirm" class="close" data-dismiss="modal" aria-hidden="true">保存</button>--%>
                            <button type="button" class="confirm" class="close" onclick="toSave()">保存</button>
                            <button type="button" class="cancel" onclick="clearModal()">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
    //通过城市获取项目列表
    function queryProjectNameById() {
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
    //编辑
    function toEdit(id){
        $.ajax({
            type: "POST",
            url: "../defaultConfig/toEditClientConfig",
            cache: false,
            async:false,
            data:{
                "id":id
            },
            dataType:"json",
            success: function (data) {
                if (data.error == 0) {
                    $("#id").attr("value",data.clientConfig.id);
                    $("#clientName").attr("value",data.clientConfig.clientName);
                    $("#weChatAppId").attr("value",data.clientConfig.weChatAppId);
                    $("#weChatAppSecret").attr("value",data.clientConfig.weChatAppSecret);
                    $('#myModal').modal('show');
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //清空并关闭Modal窗
    function clearModal(){
        $("#id").attr("value","");
        $("#clientName").attr("value","");
        $("#weChatAppId").attr("value","");
        $("#weChatAppSecret").attr("value","");
        //关闭模态窗
        $("#myModal").modal('hide');
    }
    //新增
    $("#add").bind("click",function(){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    $('#myModal').modal('show');
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    });
    //保存
    function toSave(){
        $.ajax({
            type: "POST",
            url: "../defaultConfig/saveOrUpdateClientConfig",
            cache: false,
            async:false,
            data:{
                "id":$("#id").val(),
                "clientName":$("#clientName").val(),
                "weChatAppId":$("#weChatAppId").val(),
                "weChatAppSecret":$("#weChatAppSecret").val()
            },
            dataType:"json",
            success: function (data) {
                if (data.error == 0) {
                    alert("保存成功！");
                    //刷新当前页面
                    window.location.reload();
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //返回
    function goBack(){
        window.location.href = "../defaultConfig/clientShowProjectConfigList.html?menuId=000200070000";
    }
</script>
</body>
</html>