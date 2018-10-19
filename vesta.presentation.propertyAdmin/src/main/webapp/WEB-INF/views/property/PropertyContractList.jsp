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
        height: 15.5rem;
    }
    #dialog-overlay {
        width:100%;
        height:100%;
        filter:alpha(opacity=50);
        -moz-opacity:0.5;
        -khtml-opacity: 0.5;
        opacity: 0.5;
        position:absolute;
        background:#000;
        top:0; left:0;
        z-index:3000;
        display:none;
    }
    #dialog-box {
        -webkit-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
        -moz-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
        -moz-border-radius: 5px;
        -webkit-border-radius: 5px;
        background:#eee;
        width:328px;
        position:absolute;
        z-index:5000;
        display:none;
    }
    #dialog-box .dialog-content {
        text-align:left;
        padding:10px;
        margin:13px;
        color:#666;
        font-family:arial;
        font-size:13px;
    }
    a.button {
        margin:10px auto 0 auto;
        text-align:center;
        background-color: #337ab7;
        display: block;
        width:50px;
        padding: 5px 10px 6px;
        color: #fff;
        text-decoration: none;
        font-weight: bold;
        line-height: 1;
        -moz-border-radius: 5px;
        -webkit-border-radius: 5px;
        -moz-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
        -webkit-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
        text-shadow: 0 -1px 1px rgba(0,0,0,0.25);
        border-bottom: 1px solid rgba(0,0,0,0.25);
        position: relative;
        cursor: pointer;
    }
    a.button:hover {
        background-color: #337ab7;
    }
    #dialog-box .dialog-content p {
        font-weight:700; margin:0;
    }
    #dialog-box .dialog-content ul {
        margin:10px 0 10px 20px;
        padding:0;
        height:50px;
    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#005500090000").addClass("active");
        $("#005500090000").parent().parent().addClass("in");
        $("#005500090000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005500090000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../propertyContract/getPropertyContractList.html">
                    <input type="hidden" id="menuId" name="menuId" value="005500090000"/>
                    <!-- 城市 -->
                    <div class="form-group  col-xs-4">
                        <label for="scopeId" class="col-sm-4 control-label">区域</label>
                        <div class="col-sm-8">
                            <select id="scopeId" name="scopeId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq propertyContractDTO.scopeId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group  col-xs-4">
                        <label for="projectCode" class="col-sm-3 control-label">项目</label>
                        <div class="col-sm-8">
                            <select id="projectCode" name="projectCode" class="form-control">
                                <c:forEach items="${projectList}" var="item" >
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq propertyContractDTO.projectCode}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 地块 -->
                    <div class="form-group  col-xs-4">
                        <label for="blockCode" class="col-sm-3 control-label">地块</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="blockCode" name="blockCode">
                                <c:forEach items="${areaMap}" var="item" >
                                    <option value="${item.key }"
                                            <c:if test="${item.key eq propertyContractDTO.blockCode}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 楼栋 -->
                    <div class="form-group  col-xs-4">
                        <label for="buildingId" class="col-sm-4 control-label">楼栋</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="buildingId" name="buildingNum">
                                <c:forEach items="${buildMap}" var="building">
                                    <option value="${building.key}" <c:if test="${building.key eq propertyContractDTO.buildingNum}">selected</c:if>>${building.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 单元 -->
                    <div class="form-group  col-xs-4">
                        <label for="unitId" class="col-sm-3 control-label">单元</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="unitId" name="unitCode">
                                <c:forEach items="${unitMap}" var="unit">
                                    <option value="${unit.key}" <c:if test="${unit.key eq propertyContractDTO.unitCode}">selected</c:if>>${unit.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 房间号 -->
                    <div class="form-group  col-xs-4">
                        <label for="roomName" class="col-sm-3 control-label">房间号</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="roomName" name="roomName" value="${propertyContractDTO.roomName}">
                        </div>
                    </div>
                    <!-- 业主姓名 -->
                    <div class="form-group  col-xs-4">
                        <label for="ownerName" class="col-sm-4 control-label">业主姓名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="ownerName" name="ownerName" value="${propertyContractDTO.ownerName}">
                        </div>
                    </div>
                    <!-- 合同类型 -->
                    <div class="form-group  col-xs-4">
                        <label for="propertyType" class="col-sm-3 control-label">合同类型</label>
                        <div class="col-sm-8">
                            <select id="propertyType" name="propertyType" class="form-control">
                                <option value="">请选择</option>
                                <option value="0" <c:if test="${propertyContractDTO.propertyType eq '0'}">selected</c:if>>购房合同</option>
                                <option value="1" <c:if test="${propertyContractDTO.propertyType eq '1'}">selected</c:if>>按揭合同</option>
                            </select>
                        </div>
                    </div>
                    <!-- 状态 -->
                    <div class="form-group  col-xs-4">
                        <label for="handleStatus" class="col-sm-3 control-label">办理状态</label>
                        <div class="col-sm-8">
                            <select id="handleStatus" name="handleStatus" class="form-control">
                                <option value="">请选择</option>
                                <option value="0" <c:if test="${propertyContractDTO.handleStatus eq '0'}">selected</c:if>>正常状态</option>
                                <option value="1" <c:if test="${propertyContractDTO.handleStatus eq '1'}">selected</c:if>>待提交资料</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch" id="search"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <button type="button" class="btn btn-primary" onclick="excelFile.click()" id="doUpload">批量导入</button>
                        <button type="button" class="btn btn-primary" id="downLoadTemplate">下载模板及数据</button>
                    </div>
                </form>
                <form id="uploadForm">
                    <input type="file" class="form-control" placeholder="" id="excelFile" style="visibility:hidden"
                           name="excelFile" onchange="doUpload()">
                </form>
            </div>
                <div id="dialog-overlay"></div>
                <div id="dialog-box">
                    <div class="dialog-content">
                        <div id="dialog-message"></div>
                        <a href="#" class="button"><spring:message code="common_close"/></a>
                    </div>
                </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                <th>房间编码</th>
                <th>业主姓名</th>
                <th>房间号</th>
                <th>合同类型</th>
                <th>办理进度</th>
                <th>状态</th>
                <%--
                <th>操作</th>
                --%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${propertyList}" var="property" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${property.roomNum}</td>
                    <td>${property.ownerName}</td>
                    <td>${property.roomName}</td>
                    <td>
                        <c:if test="${property.propertyType eq '0'}">购房合同</c:if>
                        <c:if test="${property.propertyType eq '1'}">按揭合同</c:if>
                    </td>
                    <td>${property.handleProgress}</td>
                    <td>
                        <c:if test="${property.handleStatus eq '0'}">正常状态</c:if>
                        <c:if test="${property.handleStatus eq '1'}">待提交资料</c:if>
                    </td>
                        <%--
                        <td class="last">
                            <a href="javascript:void(0);" onclick="toEdit('${property.propertyId}')" id="toEdit" class="a3">编辑</a>
                        </td>
                        --%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/propertyContract/getPropertyContractList.html?menuId=005500090000&pageIndex={0}&cityId=${homeLetterDTO.cityId}&projectCode=${homeLetterDTO.projectCode}&engineeringProgressTitle=${homeLetterDTO.engineeringProgressTitle}"/>
    </div>

</div>
</div>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    $(document).ready(function () {
        $('a.btn-ok, #dialog-overlay, #dialog-box').click(function () {
            $('#dialog-overlay, #dialog-box').hide();
            return false;
        });
        $(window).resize(function () {
            if (!$('#dialog-box').is(':hidden')) popup();
        });
    });
    //Popup dialog
    function popup(message) {
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();
        //var dialogTop =  (maskHeight/3) - ($('#dialog-box').height());
        var dialogTop = 200;
        //var dialogLeft = (maskWidth/2) - ($('#dialog-box').width()/2);
        var dialogLeft = 600;
        $('#dialog-overlay').css({height:maskHeight, width:maskWidth}).show();
        $('#dialog-box').css({top:dialogTop, left:dialogLeft}).show();
        if(message != ""){
            $('#dialog-message').html(message);
        }
        $("#search").attr("disabled", true);
        $("#doUpload").attr("disabled", true);
        $("#downLoadTemplate").attr("disabled", true);
    }
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
                    window.location.href = "../homeLetter/toEditEngineeringProgress.html?menuId="+$("#menuId").val()+"&engineeringProgressId="+id;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
    //批量导入
    function doUpload() {
        //检验导入的文件是否为Excel文件
        var excelPath = document.getElementById("excelFile").value;
        if (excelPath == null || excelPath == '') {
            alert("请选择要上传的Excel文件");
            return;
        } else {
            var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();
            if (fileExtend == '.xls' || fileExtend == '.xlsx') {
            } else {
                alert("文件格式需为'.xls'格式或者'.xlsx格式'");
                return;
            }
        }
        popup('提醒：批量导入数据时间较长，请耐心等待...');
        //执行异步上传
        var formData = new FormData($("#uploadForm")[0]);
        $.ajax({
            url: '../propertyContract/uploadPropertyExcel',
            type: 'POST',
            data: formData,
            async: true,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if(data.error == '0'){
                    var message = "导入完成，共成功 "+data.flag.successNum+" 条，失败 "+data.flag.errorNum+" 条。";
                    if (data.flag.errorNum > 0){
                        message += "其中 "+data.flag.errorNotes+" 导入失败，原因：未在系统中找到房产，请确认CRM系统中推送该房产数据！";
                    }
                    alert(message);
                    window.location.href = "../propertyContract/getPropertyContractList.html?menuId=005500090000";
                }else if(data.error == '-1'){
                    alert("导入失败！");
                }
            },
            error: function (data) {
                alert("网络异常,请求失败！");
            }
        });
    }
    //下载模板及数据
    $("#downLoadTemplate").click(function(){
        var projectCode = $("#projectCode").val();
        if (projectCode == null || projectCode == "" || projectCode == "0"){
            alert("请选择项目！");
            return;
        }
        var blockCode = $("#blockCode").val();
        if (blockCode == null || blockCode == "" || blockCode == "0"){
            alert("请选择地块！");
            return;
        }
        alert("导出数据量较大时，耗时较长，请耐心等待......");
        window.open("../propertyContract/downLoadExcelTemplateAndData?projectCode="+$("#projectCode").val()+"&blockCode="+$("#blockCode").val());
    });
    //通过城市获取项目列表
    function queryProjectNameById() {
        $("#projectCode").find("option").remove();
        $("#blockCode").find("option").remove();
        $("#buildingId").find("option").remove();
        $("#unitId").find("option").remove();
        $("#floorCode").find("option").remove();
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
    //通过项目获取地块列表
    $("#projectCode").change(function(){
        $("#blockCode").find("option").remove();
        $("#buildingId").find("option").remove();
        $("#unitId").find("option").remove();
        $("#floorCode").find("option").remove();
        /* -------------------- */
        var projectId = $("#projectCode").val();
        $.ajax({
            url:"../houseroomtype/getAreaListByProject",
            type:"get",
            async:"false",
            data:{ "projectId":projectId},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    if(data != null){
                        document.getElementById("blockCode").innerHTML = "";
                        for(var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }
                        }
                        $("#blockCode").append(option);
                    }
                }
            }
        });
    });
    //通过地块获取楼栋列表
    $("#blockCode").change(function(){
        $("#buildingId").find("option").remove();
        $("#unitId").find("option").remove();
        $("#floorCode").find("option").remove();
        /* -------------------- */
        var blockCode = $("#blockCode").val();
        $.ajax({
            url:"../houseroomtype/getBuildingListByArea",
            type:"get",
            async:"false",
            data:{ "areaId":blockCode},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    if(data != null){
                        document.getElementById("buildingId").innerHTML = "";
                        for(var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }
                        }
                        $("#buildingId").append(option);
                    }
                }
            }
        });
    });
    //通过楼栋获取单元列表
    $("#buildingId").change(function(){
        $("#unitId").find("option").remove();
        $("#floorCode").find("option").remove();
        /* -------------------- */
        var buildingId = $("#buildingId").val();
        $.ajax({
            url:"../houseroomtype/getUnitList",
            type:"get",
            async:"false",
            data:{ "buildingId":buildingId},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    if(data != null){
                        document.getElementById("unitId").innerHTML = "";
                        for(var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }
                        }
                        $("#unitId").append(option);
                    }
                }
            }
        });
    });
</script>
</body>
</html>