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
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            overflow:hidden;text-overflow:ellipsis;/*display:-webkit-box;*/-webkit-box-orient:vertical;-webkit-line-clamp:2;
            /*white-space:nowrap;overflow:hidden;text-overflow: ellipsis;*/
        }
    </style>
    <style type="text/css">#dialog{display:none;}</style>
    <script type="application/javascript">
        $(function(){
            $("#001100020000").addClass("active");
            $("#001100020000").parent().parent().addClass("in");
//      $("#003200010000").parent().parent().parent().parent().addClass("active");
        })
        function checkAllBox(obj){
            var answer= document.getElementsByName("ids");
            if(obj.checked==true){
                for(var i=0;i<answer.length;i++){
                    answer[i].checked = true;
                }
            }else{
                for(var i=0;i<answer.length;i++){
                    answer[i].checked = false;
                }
            }
        }
        function checkData(){
            var answer= document.getElementsByName("answer");
            var flag = false;
            for(var i=0;i<income.length;i++){
                if(income[i].checked == true){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                alert("请至少选择一项");
            }
            return flag;
        }

        function deleteQuestion(rectifyId,proType){
            if(window.confirm("确定作废吗？")){
                window.location.href = "../problem/deleteQeustion?rectifyId="+rectifyId+"&problemType="+proType;
            }

            /*$("#dialog").dialog({
                resizable: false,
                height: 240,
                width: 500,
                modal: true,
                buttons: {
                    "确定": function () {
                        window.location.href = "../problem/editProblemManagement?rectifyId="+rectifyId;
                    },
                    "取消": function () {
                        $(this).dialog("close");
                    }
                }
            });*/
        }

        //打印
        function toObtain(){
            //首先选择房间号
            var type=12;
            if ($('#roomId').val() == null || $('#roomId').val() == " ") {
                alert("请先选择房间号");
            }else{
                var rId= $("#roomId").val();
                var projectId=$("#projectId").val();
                var obj=document.getElementsByName('ids');
                var s='';
                var flag = false;
                for(var i=0;i<obj.length;i++){
                    if(obj[i].checked == true){
                        s+=obj[i].value+',';
                        flag = true;
                    }
                }
//                if(!flag){
//                    alert("请至少选择一项");
//                }else{
//                    s=s.substr(0, s.length-1);
//                    window.location.href = "../problem/printProblemListing?caseIds="+ s+"&rId="+encodeURIComponent(rId)+"&projectId="+projectId+"&type="+type;//要新的窗口打开链接
//                }
               s=s.substr(0, s.length-1);
//                window.location.href = "../problem/printProblemListing?caseIds="+ s+"&rId="+encodeURIComponent(rId)+"&projectId="+projectId+"&type="+type;//要新的窗口打开链接
                window.open("../problem/printProblemListing?caseIds="+ s+"&rId="+encodeURIComponent(rId)+"&projectId="+projectId+"&type="+type,"_blank");
            }
        }

        $(function(){
            $("#search").on("submit",function(ev){
                if($('#roomId').val() == null || $('#roomId').val() == " "){
                    //如果验证不通过，一定要阻止html的默认事件，防止错误提交
                    alert("请先选择房间号");
                    ev.preventDefault();
                }else{
                    //如果验证通过，则进一步做其它处理，比如弹出对话框等，并最终返回true或false
                    //alert(roomId);
                    return true;
                }
            })
        });
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001100020000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" id="search" name="search" action="../problem/problemPrint.html" method="get">

                    <%-- 项目名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="projectId" class="col-sm-4 control-label"><spring:message code="problem.project"/>：</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="projectId" name="projectId">
                                <c:forEach items="${typeMaps.projects}" var="item">
                                    <option value="${item.key }" <c:if test="${item.key eq problem.projectId}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%-- 楼栋 --%>
                    <div class="form-group  col-lg-4">
                        <label for="buildingId" class="col-sm-4 control-label"><spring:message code="problem.building"/>：</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="buildingId" name="buildingId">
                                <c:forEach items="${typeMaps.buildings}" var="item">
                                    <option value="${item.key }" <c:if test="${item.key eq problem.buildingId}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%--单元 --%>
                    <div class="form-group  col-lg-4">
                        <label for="unitId" class="col-sm-4 control-label"><spring:message code="problem.unit"/>：</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="unitId" name="unitId">
                                <c:forEach items="${typeMaps.units}" var="item">
                                    <option value="${item.key }" <c:if test="${item.key eq problem.unitId}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%-- 楼层 --%>
                    <div class="form-group  col-lg-4">
                        <label for="floorId" class="col-sm-4 control-label"><spring:message code="problem.floor"/>：</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="floorId" name="floorId">
                                <c:forEach items="${typeMaps.floors}" var="item">
                                    <option value="${item.key }" <c:if test="${item.key eq problem.floorId}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%-- 房间 --%>
                    <div class="form-group  col-lg-4">
                        <label for="roomId" class="col-sm-4 control-label"><spring:message code="problem.room"/>：</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="roomId" name="roomId">
                                <c:forEach items="${typeMaps.rooms}" var="item">
                                    <option value="${item.key }" <c:if test="${item.key eq problem.roomId}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%-- 状态 --%>
                    <div class="form-group  col-lg-4">
                        <label for="caseState" class="col-sm-4 control-label"><spring:message code="problem.status"/>：</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="caseState" name="caseState">
                                <option value="" selected>请选择</option>
                                <option value="草稿" <c:if test="${problem.caseState eq '草稿'}">selected</c:if>>草稿</option>
                                <option value="待接单" <c:if test="${problem.caseState eq '待接单'}">selected</c:if>>待接单</option>
                                <option value="已完成" <c:if test="${problem.caseState eq '已完成'}">selected</c:if>>已完成</option>
                                <option value="已废弃" <c:if test="${problem.caseState eq '已废弃'}">selected</c:if>>已废弃</option>
                                <option value="处理中" <c:if test="${problem.caseState eq '处理中'}">selected</c:if>>处理中</option>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" id="proType" name="proType" value="${problem.proType}" />
                    <div class="form-group  col-lg-4">
                        <label for="startDate" class="col-sm-4 control-label"><spring:message code="workOrders.startDate"/></label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="请输入开始时间" path="startDate" name="startDate" value="${problem.startDate}" readonly="true" />
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <div class="form-group  col-lg-4">
                        <label for="endDate" class="col-sm-4 control-label"><spring:message code="workOrders.endDate"/></label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="请输入结束时间" path="endDate" name="endDate" value="${problem.endDate}" readonly="true"/>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="problem.search"/></button>
                    <%--<a  href="../problem/preAddProblemPrint?planType=12" class="btn btn-primary" for="rolesetAdd" ><spring:message code="problem.add"/></a>--%>
                    <a href="javascript:void(0);" value="" onclick="toObtain();" class="btn btn-primary" style="color:#fff">打印交房单</a>
                    <a href="#" onclick="isExcel()"  value="" class="btn btn-primary" style="color:#fff">导出Excel</a>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
            <table width="100%" class="tableStyle" style="table-layout: fixed;">
                <thead>
                <tr>
                    <%--<th width="4%"><input type="checkbox" name="answer" onclick="checkAllBox(this)">序号</th>--%>
                    <th width="4%"><input type="checkbox" name="answer" onclick="checkAllBox(this)">序号</th>
                    <th width="4%">超期</th>
                    <th width="20%" >问题描述</th>
                    <th width="24%">位置</th>
                    <th width="10%">三级分类</th>
                    <th width="10%">登记时间</th>
                    <th width="5%">状态</th>
                    <%--<th width="12%">操作</th>--%>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${problems}" var="problem" varStatus="row">
                    <tr>
                        <td><input name="ids" type="checkbox" value="${problem.caseId}"/><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>
                        <td>${problem.isOverdue}</td>
                        <td>
                            <%--<a href="../problem/getProblemManagementDetail?caseId=${problem.caseId}">--%>
                            ${problem.caseDesc}
                            <%--</a>--%>
                        </td>
                        <td>${problem.address}</td>
                        <td>${problem.thirdTypeName}</td>
                        <td><fmt:formatDate  type="date" value="${problem.createDate}" dateStyle="default" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <%--<td>${problem.createDate}</td>--%>
                        <td>
                            <c:if test="${problem.caseState eq '草稿'}">草稿</c:if>
                            <c:if test="${problem.caseState eq '处理中'}">处理中</c:if>
                            <c:if test="${problem.caseState eq '已完成'}">已完成</c:if>
                            <c:if test="${problem.caseState eq '已废弃'}">已废弃</c:if>
                            <c:if test="${problem.caseState eq '待接单'}">待接单</c:if>

                        </td>
                        <%--<td>--%>
                            <%--<a href="../problem/getProblemManagementDetail?rectifyId=${problem.caseId}"><spring:message code="problem.detail" /></a>--%>
                        <%--</td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
<%--        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/problem/problemPrint.html?pageIndex={0}&projectId=${problem.projectId}&buildingId=${problem.buildingId}&roomId=${problem.roomId}&caseState=${problem.caseState}&oneType=${problem.oneType}&twoType=${problem.twoType}&threeType=${problem.threeType}&proType=${problem.proType}&startDate=${problem.startDate}&endDate=${problem.endDate}"/>--%>
    </div>

</div>
</div>
</div>
</div>

<!-- main content end-->
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
    var projectId = "${problem.projectId}";
    var buildingId = "${problem.buildingId}";
    var unitId = "${problem.unitId}";
    var floorId = "${problem.floorId}";
    var roomId = "${problem.roomId}";
    var caseState = "${problem.caseState}";
    var oneType = "${problem.oneType}";
    var twoType = "${problem.twoType}";
    var threeType = "${problem.threeType}";
    var proType = "${problem.proType}";
    var startDate = "${problem.startDate}";
    var endDate = "${problem.endDate}";
</script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">

    function batchCommit(){
        if(window.confirm("确定提交选中的草稿吗？")){
            document.forms[1].submit();
        }
    }

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

    function isExcel(){
        var size = ${fn:length(problems)};
        if(size>0){
            var href = "../problem/exportExcel?pageIndex=${webPage.pageIndex}&projectId=${problem.projectId}&buildingId=${problem.buildingId}&roomId=${problem.roomId}&caseState=${problem.caseState}&oneType=${problem.oneType}&twoType=${problem.twoType}&threeType=${problem.threeType}&proType=${problem.proType}&startDate=${problem.startDate}&endDate=${problem.endDate}";
            window.location.href = href;
        }else{
            alert("没有可以导出的数据");
        }
    }

    $("#oneType").change(function(){
        var oneType = $("#oneType").val();
        $.ajax({
            url:"../problem/getSecondTypeList",
            type:"get",
            async:"false",
            data:{ "classifyOne":oneType},
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
                    console.log(data);
                    var option ="";
                    if(data != null){
                        document.getElementById("twoType").innerHTML = "";
                        for(var prop in data) {
                            if(!isNaN(data[prop])){
                            }else{
                                if (data.hasOwnProperty(prop)) {
                                    option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                                }
                            }
                        }

                        $("#twoType").append(option);
                    }
                }
            }
        });
    });



    $("#twoType").change(function(){
        var twoType = $("#twoType").val();
        $.ajax({
            url:"../problem/getThirdTypeList",
            type:"get",
            async:"false",
            data:{ "classifyTwo":twoType},
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
                        document.getElementById("threeType").innerHTML = "";
                        for(var prop in data) {
                            if(!isNaN(data[prop])){
                            }else{
                                if (data.hasOwnProperty(prop)) {
                                    option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                                }
                            }
                        }
                        $("#threeType").append(option);
                    }
                }
            }
        });
    });

    $("#threeType").change(function(){
        var threeType = $("#threeType").val();
        var projectId = $("#projectId").val();
        if(projectId != "" && projectId != "0000"){
            $.ajax({
                url:"../problem/getSupplierList",
                type:"get",
                async:"false",
                data:{ "classifyThree":threeType,"projectNum":projectId},
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
                            document.getElementById("supplier").innerHTML = "";
                            for(var prop in data) {
                                if(!isNaN(data[prop])){
                                }else{
                                    if (data.hasOwnProperty(prop)) {
                                        option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                                    }
                                }
                            }
                            $("#supplier").append(option);
                        }
                    }
                }
            });
        }
    });


    $("#projectId").change(function(){
        var projectId = $("#projectId").val();
        $.ajax({
            url:"../houseroomtype/getBuildingListByProject",
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

    $("#buildingId").change(function(){
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
                            if(data[prop]=='无单元'){
                                option="<option value=''>请选择单元</option>";
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }else{
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }
                        }
                        $("#unitId").append(option);
                    }
                }
            }
        });
    });

    $("#unitId").change(function(){
        var unitId = $("#unitId").val();
        $.ajax({
            url:"../houseroomtype/getFloorListByNum",
            type:"get",
            async:"false",
            data:{ "unitId":unitId},
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
                        document.getElementById("floorId").innerHTML = "";
                        for(var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }
                        }
                        $("#floorId").append(option);
                    }
                }
            }
        });
    });

    $("#floorId").change(function(){
        var floor = $("#floorId").val();
        $.ajax({
            url:"../houseroomtype/getRoomListByNum",
            type:"get",
            async:"false",
            data:{ "floor":floor},
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
                        document.getElementById("roomId").innerHTML = "";
                        for(var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }
                        }
                        $("#roomId").append(option);
                    }
                }
            }
        });
    });

</script>
</body>

</html>