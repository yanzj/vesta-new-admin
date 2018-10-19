<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/3/6
  Time: 13:53
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
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
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
        label.error {
            margin-left: 1%;
            color: red;
        }
    </style>
    <script type="text/javascript">

        //-----------------↓业态下拉框↓-----------------
        function turnFormat(){
            $("#formatId").empty()
            $("#buildingId").empty();//清空
            $("#unitId").empty();//清空
            $("#roomId").empty();//清空
            var projectId = document.getElementById("projectId").value;
            $.ajax({
                url: "../user/listFormat",
                type: "post",
                async: "false",
                dataType: "json",
                data: {
                    "projectId": projectId
                },
                success: function (json) {
                    <!-- 获取返回代码 -->
                    var code = json.code;
                    if (code != 0) {
                        var errorMessage = json.msg;
                        alert(errorMessage);
                    } else {
                        <!-- 获取返回数据 -->
                        var data = json.data;
                        for(var n=0;n<data.length;n++){
                            var ids=data[n].formatId;
                            var names=data[n].formatName;
                            $("#formatId").append("<option id='"+ids+"' value='"+ids+"'>"+names+"</option>");
                        }
                    }
                }
            })
            setTimeout("turnBuild()",100);
        }

        //-----------------↓楼栋下拉框↓-----------------
        function turnBuild(){
            $("#buildingId").empty();//清空
            $("#unitId").empty();//清空
            $("#roomId").empty();//清空
            var projectId = document.getElementById("projectId").value;
            var formatId = document.getElementById("formatId").value;
            $.ajax({
                url: "../user/listBuildF",
                type: "post",
                async: "false",
                dataType: "json",
                data: {
                    "projectId": projectId,
                    "formatId":formatId
                },
                success: function (json) {
                    <!-- 获取返回代码 -->
                    var code = json.code;
                    if (code != 0) {
                        var errorMessage = json.msg;
                        alert(errorMessage);
                    } else {
                        <!-- 获取返回数据 -->
                        var data = json.data;
                        for(var n=0;n<data.length;n++){
                            var ids=data[n].buildingId;
                            var names=data[n].buildingName;
                            $("#buildingId").append("<option id='"+ids+"' value='"+ids+"'>"+names+"</option>");
                        }
                        buildId = $("#buildingId").val();
                    }
                }
            });
            setTimeout("turnUnit()",100);
//                    alert("2"+document.getElementById("buildingId").value);
//                    turnUnit()
        }

        //-----------------↓单元下拉框↓-----------------
        function turnUnit(){
            $("#unitId").empty();//清空
            $("#roomId").empty();//清空
            var buildingId = document.getElementById("buildingId").value;
//                alert("3"+"buildingId="+buildingId);
            $.ajax({
                url: "../user/listUnit",
                type: "post",
                async: "false",
                dataType: "json",
                data: {
                    "buildingId": buildingId
                },
                success: function (json) {
                    <!-- 获取返回代码 -->
                    var code = json.code;
                    if (code != 0) {
                        var errorMessage = json.msg;
                        alert(errorMessage);
                    } else {
                        <!-- 获取返回数据 -->
                        var data = json.data;
                        for(var n=0;n<data.length;n++){
                            var ids=data[n].unitId;
                            var names=data[n].unitName;
                            $("#unitId").append("<option id='"+ids+"' value='"+ids+"'>"+names+"</option>");
                        }
                    }
                }
            });

//                alert($("#unitId").val());

            setTimeout("turnRoom()",100);
        }
        //-----------------↓房间下拉框↓-----------------
        function turnRoom(){
            $("#roomId").empty();//清空
            var unitId = document.getElementById("unitId").value;
            $.ajax({
                url: "../user/listRoom",
                type: "post",
                async: "false",
                dataType: "json",
                data: {
                    "unitId": unitId
                },
                success: function (json) {
                    <!-- 获取返回代码 -->
                    var code = json.code;
                    if (code != 0) {
                        var errorMessage = json.msg;
                        alert(errorMessage);
                    } else {
                        <!-- 获取返回数据 -->
                        var data = json.data;
                        for(var n=0;n<data.length;n++){
                            var ids=data[n].roomId;
                            var names=data[n].roomName;
                            $("#roomId").append("<option id='"+ids+"' value='"+ids+"'>"+names+"</option>");
                        }
                    }
                }
            });
        }
    </script>
    <script type="text/javascript">
        function showOption(){
            //业态
            var formatId = document.getElementById("formatId").value;
            var formatName = document.getElementById("format"+formatId).textContent;
            document.getElementById("formatName").value = formatName;
            //楼号
            var buildingId = document.getElementById("buildingId").value;
            var buildingName = document.getElementById("building"+buildingId).textContent;
            document.getElementById("buildingName").value = buildingName;
//          alert("buildingName"+buildingName);
            //单元
            var unitId = document.getElementById("unitId").value;
            var unitName = document.getElementById("unit"+unitId).textContent;
            document.getElementById("unitName").value = unitName;
//          alert("unitName"+unitName);
            //房间
            var roomId = document.getElementById("roomId").value;
            var roomName = document.getElementById("room"+roomId).textContent;
            document.getElementById("roomName").value = roomName;
//          alert("roomName"+roomName);
            setTimeout("return true",300);
        }
    </script>
    <script type="text/javascript">
//        $(function(){
//            if($("#flag").val()=="true"){
//                alert("添加成功！")
//            }
//        })
        $(function() {
//            $.validator.addMethod("formatId",function(value){
//                if(value=='0'){
//                    return false;
//                }else{
//                    return true;
//                }
//            },"请选择业态");
//            $.validator.addMethod("buildingName",function(value,element,params){
//                if(value=='0'){
//                    return false;
//                }else{
//                    return true;
//                }
//            },"请选择楼号");
//            $.validator.addMethod("unitId",function(value,element,params){
//                if(value=='0'){
//                    return false;
//                }else{
//                    return true;
//                }
//            },"请选择单元");
//            $.validator.addMethod("roomName",function(value,element,params){
//                if(value=='0'){
//                    return false;
//                }else{
//                    return true;
//                }
//            },"请选择房间");
            $("#addTenant").validate({
                rules: {
                    dtoRealName:{
                        required:true,
                        minlength:1,
                        maxlength:20
                    },
                    dtoMobile:{
                        required:true,
                        minlength:11,
                        maxlength:11
                    },
                    dtoPassword:{
                        required:true,
                        minlength:6,
                        maxlength:20
                    },
                    dtoUserName:{
                        required:true,
                        minlength:1,
                        maxlength:20
                    },
                    formatId:{
                        ye:"a"
                    },
                    buildingId:{
                        bud:"b"
                    },
                    unitId:{
                        unitNum:"c"
                    },
                    roomId:{
                        roomNum:"d"
                    }
                },
                messages: {
                    dtoRealName: {
                        required: "请输入真实姓名！",
                        minlength: "不能少于1个字符！",
                        maxlength: "请勿超过20个字！"
                    },
                    dtoMobile: {
                        required: "请输入电话号码！",
                        minlength: "请输入正确格式！",
                        maxlength: "请输入正确格式！"
                    },
                    dtoPassword:{
                        required: "请输入密码！",
                        minlength: "不能少于6个字符！",
                        maxlength: "请勿超过20个字！"
                    },
                    dtoUserName:{
                        required: "请输入用户名！",
                        minlength: "不能少于1个字符！",
                        maxlength: "请勿超过20个字！"
                    }
                }
            })
        })

        function testAdd(){
          if($("#formatId").val()==0){
                alert("请选择业态。");
              return false;
            }
            if($("#buildingId").val()== "0"){
                alert("请选择楼号。");
                return false;
            }
            if($("#unitId").val()=="0"){
                alert("请选择单元号。");
                return false;
            }
            if($("#roomId").val()=="0"){
                alert("请选择房间号。");
                return false;
            }
        }

    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005500040000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../user/addTenant.html" id="addTenant" method="post">
                    <%--业态--%>
                    <div class="form-group  col-lg-6">
                        <label for="formatId" class="col-sm-3 control-label"><spring:message code="ownerManage.SelFormat"/></label>
                        <div class="col-sm-9">
                            <input type="hidden" id="formatName" name="formatName">
                            <select name="dtoFormatId" class="form-control" id="formatId" onchange="turnBuild()">
                                <%--<c:if test="${userOwnerDTO.buildingName != null}"><option value="${userOwnerDTO.buildingId}">${userOwnerDTO.buildingName}</option></c:if>--%>
                                <c:forEach items="${formatList}" var="format">
                                    <option value="${format.formatId}" id="format${format.formatId}">${format.formatName}</option>
                                </c:forEach>
                                <%--<c:if test="${building.buildingId eq userOwnerDTO.buildingId}"> selected = "selected"</c:if>              --%>
                            </select>
                        </div>
                    </div>
                    <%--楼栋--%>
                    <div class="form-group  col-lg-6">
                        <label for="buildingId" class="col-sm-3 control-label"><spring:message code="ownerManage.SelBuilding"/></label>
                        <div class="col-sm-9">
                            <input type="hidden" id="buildingName" name="buildingName">
                            <select name="dtoBuildingId" class="form-control" id="buildingId" onchange="turnUnit()">
                                <%--<c:if test="${userOwnerDTO.buildingName != null}"><option value="${userOwnerDTO.buildingId}">${userOwnerDTO.buildingName}</option></c:if>--%>
                                <c:forEach items="${buildingList}" var="building">
                                    <option value="${building.buildingId}" id="building${building.buildingId}">${building.buildingName}</option>
                                </c:forEach>
                                <%--<c:if test="${building.buildingId eq userOwnerDTO.buildingId}"> selected = "selected"</c:if>              --%>
                            </select>
                        </div>
                    </div>
                    <%--单元--%>
                    <div class="form-group  col-lg-6">
                        <label for="unitId" class="col-sm-3 control-label"><spring:message code="ownerManage.SelUnit"/></label>
                        <div class="col-sm-9">
                            <input type="hidden" name="unitName" id="unitName">
                            <select name="dtoUnitId" class="form-control" id="unitId" onchange="turnRoom()">
                                <%--<c:if test="${userOwnerDTO.unitName != null}"><option value="${userOwnerDTO.unitId}">${userOwnerDTO.unitName}</option></c:if>--%>
                                <c:forEach items="${unitList}" var="unit">
                                    <option value="${unit.unitId}" id="unit${unit.unitId}">${unit.unitName}</option>
                                </c:forEach>
                            </select>
                            <%--<c:if test="${unit.unitId eq userOwnerDTO.unitId}"> selected = "selected"</c:if>--%>
                        </div>
                    </div>
                    <%--房间--%>
                    <div class="form-group  col-lg-6">
                        <label for="roomId" class="col-sm-3 control-label"><spring:message code="ownerManage.SelRoom"/></label>
                        <div class="col-sm-9">
                            <input type="hidden" id="roomName" name="roomName">
                            <select name="dtoRoomId" class="form-control" id="roomId">
                                <%--<c:if test="${userOwnerDTO.roomName != null}"><option value="${userOwnerDTO.roomId}">${userOwnerDTO.roomName}</option></c:if>--%>
                                <c:forEach items="${roomList}" var="room">
                                    <option value="${room.roomId}" id="room${room.roomId}" >${room.roomName}</option>
                                </c:forEach>
                            </select>
                            <%--<c:if test="${room.roomId eq userOwnerDTO.roomId}"> selected = "selected"</c:if>--%>
                        </div>
                    </div>

                    <%--用户名--%>
                    <%--<div class="form-group  col-lg-6">--%>
                    <%--<label for="userName" class="col-sm-3 control-label"><spring:message code="ownerManage.SelUserName"/></label>--%>
                    <%--<div class="col-sm-9">--%>
                    <%--<input type="text" class="form-control" placeholder="" id="userName"--%>
                    <%--name="userName" value="${userOwnerDTO.userName}" >--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--业主姓名--%>
                    <div class="form-group  col-lg-6">
                        <label for="dtoRealName" class="col-sm-3 control-label text-right"><spring:message code="ownerManage.SelName"/></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="" id="dtoRealName"
                                   name="dtoRealName" value="${userTenantDTO.dtoRealName}">
                        </div>
                    </div>
                    <%--联系方式--%>
                    <div class="form-group  col-lg-6">
                        <label for="dtoMobile" class="col-sm-3 control-label text-right"><spring:message code="ownerManage.SelMobile"/></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="" id="dtoMobile"
                                   name="dtoMobile" value="${userTenantDTO.dtoMobile}">
                        </div>
                    </div>
                    <%--&lt;%&ndash;密码&ndash;%&gt;--%>
                    <%--<div class="form-group  col-lg-6">--%>
                        <%--<label for="dtoPassword" class="col-sm-3 control-label text-right"><spring:message code="ownerManage.SelPassword"/></label>--%>
                        <%--<div class="col-sm-9">--%>
                            <%--<input type="text" class="form-control" placeholder="" id="dtoPassword"--%>
                                   <%--name="dtoPassword">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                        <%--<div class="form-group col-lg-6">--%>
                            <%--<label for="dtoUserName" class="col-sm-3 control-label text-right"><spring:message code="ownerManage.SelUserName" /></label>--%>
                            <%--<div class="col-sm-9">--%>
                                <%--<input type="text" class="form-control" placeholder="" id="dtoUserName" name="dtoUserName">--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <div class="form-group col-lg-6">
                            <label for="" class="col-sm-3 control-label text-right"><spring:message code="ownerManage.Ispay" /></label>
                            <div class="col-sm-9">
                                 <input type="radio" id="dtoAccredit" name="dtoPccredit" value="YES"><spring:message code="common_yes" />&nbsp;
                                 <input type="radio" name="dtoPccredit" value="NO" checked="checked"><spring:message code="common_no" />
                            </div>
                        </div>

                        <div class="form-group col-lg-6">
                            <label for="" class="col-sm-3 control-label text-right"><spring:message code="ownerManage.TenantOrFamily" /></label>
                            <div class="col-sm-9">
                                <input type="radio" id="tenantOrFamily" name="tenantOrFamily" value="5" checked="checked"><spring:message code="ownerManage.Tenant" />&nbsp;
                                <input type="radio" name="tenantOrFamily" value="4"><spring:message code="ownerManage.Family" />
                            </div>
                        </div>

                        <input type="hidden" id="projectId" value="${projectId}">
                        <div class="clearfix"></div>
                        <div class="text-center">
                    <button type="submit" class="btn btn-primary" for="" onclick="return testAdd();"><spring:message code="common_add"/></button>
                    <a  href="" onclick="javaScript:history.go(-1)" class="btn btn-primary" for="rolesetAdd" ><spring:message code="ownerManage.GoBack"/></a><span>${userTenantDTO.tnResult}</span>
                  </div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>



</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
</div>

</body>
</html>

