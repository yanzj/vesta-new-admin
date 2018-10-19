<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);
        function hideURLbar() {
            window.scrollTo(0, 1);
        } </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="001500020000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <form class="form-horizontal" action="../houseroomtype/houseRoomTypeManage.html" method="get">
                    <%-- 集团名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="groupId" class="col-sm-4 control-label"><spring:message
                                code="problem.group"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="groupId" name="groupId">
                                <c:forEach items="${groups}" var="group">
                                    <option value="${group.key }"
                                            <c:if test="${group.key eq houseRoomTypeDTO.groupId}">selected</c:if>>${group.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 区域名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="regionId" class="col-sm-4 control-label"><spring:message
                                code="problem.region"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="regionId" name="regionId">
                                <c:forEach items="${regions}" var="region">
                                    <option value="${region.key }"
                                            <c:if test="${region.key eq houseRoomTypeDTO.regionId}">selected</c:if>>${region.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 城市名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="cityId" class="col-sm-4 control-label"><spring:message
                                code="problem.city"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="cityId" name="cityId">
                                <c:forEach items="${citys}" var="city">
                                    <option value="${city.key }"
                                            <c:if test="${city.key eq houseRoomTypeDTO.cityId}">selected</c:if>>${city.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 项目名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="projectId" class="col-sm-4 control-label"><spring:message
                                code="problem.project"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="projectId" name="projectId">
                                <c:forEach items="${projects}" var="project">
                                    <option value="${project.key }"
                                            <c:if test="${project.key eq houseRoomTypeDTO.projectId}">selected</c:if>>${project.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%--地块--%>
                    <div class="form-group  col-lg-4">
                        <label for="projectId" class="col-sm-4 control-label"><spring:message
                                code="house.area"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="areaId" name="areaId">
                                <c:forEach items="${areaList}" var="area">
                                    <option value="${area.key}"
                                            <c:if test="${area.key eq houseRoomTypeDTO.areaId}">selected</c:if>>${area.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group  col-lg-4">
                        <label for="buildingId" class="col-sm-4 control-label"><spring:message
                                code="activityManage.activityBuildName"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="buildingId" name="buildingId">
                                <c:forEach items="${buildingList}" var="building">
                                    <option value="${building.key}"
                                            <c:if test="${building.key eq houseRoomTypeDTO.buildingId}">selected</c:if>>${building.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group  col-lg-4">
                        <label for="unitId" class="col-sm-4 control-label"><spring:message
                                code="activityManage.activityUnitName"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="unitId" name="unitId">
                                <c:forEach items="${unitList}" var="unit">
                                    <option value="${unit.key}"
                                            <c:if test="${unit.key eq houseRoomTypeDTO.unitId}">selected</c:if>>${unit.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <button type="button" class="btn btn-primary" onclick="search()" for="propertySearch">
                        <spring:message code="propertyCompany.propertySearch"/></button>
                </form>
            </div>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <%--<form action="../houseroomtype/houseRoomTypeManage.html" method="post" id ="search_form">--%>
        <%--<input id = "id" type="hidden" name="id"  value="">--%>
        <%--</form>--%>
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><c:choose><c:when
                        test="${floorRoomList[0].roomName eq '区域'}">楼号</c:when><c:otherwise><spring:message
                        code="workOrders.buildingFloor"/></c:otherwise></c:choose></td>
                <c:forEach items="${floorRoomList}" var="floorRoom" varStatus="vs">
                    <td><a href="javascript:void(0);"
                           onclick="batchSet('${houseRoomTypeDTO.cityId}','${houseRoomTypeDTO.projectId}','${houseRoomTypeDTO.areaId}','${houseRoomTypeDTO.buildingId}','${houseRoomTypeDTO.unitId}','${floorRoom.roomName}','${roomType.roomId}','${roomType.roomNum}','${houseRoomTypeDTO.groupId}','${houseRoomTypeDTO.regionId}')">${floorRoom.roomName}</a>
                    </td>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${floorList}" var="floor" varStatus="vs">
                <tr>
                    <td><b><c:choose><c:when
                            test="${empty floor.roomName}">${buildSelectName}</c:when><c:otherwise>${floor.roomName}</c:otherwise></c:choose></b>
                    </td>
                    <c:forEach items="${floorRoomList}" var="floorRoom" varStatus="vs">
                        <td>
                            <c:forEach items="${roomTypeList}" var="roomType" varStatus="vs">
                                <c:if test="${floor.roomName eq roomType.floor && floorRoom.roomName eq roomType.floorName}">
                                    <a href="javascript:void(0);"
                                       onclick="oneSet('${houseRoomTypeDTO.cityId}','${houseRoomTypeDTO.projectId}','${houseRoomTypeDTO.areaId}','${houseRoomTypeDTO.buildingId}','${houseRoomTypeDTO.unitId}','${roomType.roomId}','${roomType.roomNum}','${houseRoomTypeDTO.groupId}','${houseRoomTypeDTO.regionId}')">
                                            ${roomType.roomName}</a>
                                    </br>
                                    <a href="javascript:void(0);"
                                       onclick="typeDetail('${roomType.houseType}','${houseRoomTypeDTO.cityId}','${houseRoomTypeDTO.projectId}','${houseRoomTypeDTO.areaId}','${houseRoomTypeDTO.buildingId}','${houseRoomTypeDTO.unitId},'${houseRoomTypeDTO.groupId}','${houseRoomTypeDTO.regionId}'')"
                                       class="a2">${roomType.houseTypeName}</a>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


</div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

<script>

    function oneSet(cityId, projectId, areaId,buildingId, unitId, roomId, roomNum,groupId,regionId) {
        var url = "../houseroomtype/houseRoomLabel?groupId="+groupId+"&regionId="+regionId+"&cityId=" + cityId + "&projectId=" + projectId + "&areaId=" + areaId + "&buildingId=" + buildingId + "&unitId=" + unitId + "&roomId=" + roomId + "&roomNum=" + roomNum;
        url = url.replace(/#/g, "@$@");
        window.location.href = url;
    }

    function batchSet(cityId,projectId,areaId,buildingId, unitId, roomName,roomId,roomNum,groupId,regionId) {
        var url = "../houseroomtype/houseRoomLabel?groupId="+groupId+"&regionId="+regionId+"&cityId=" + cityId + "&projectId=" + projectId +"&areaId=" + areaId + "&buildingId=" + buildingId + "&unitId=" + unitId + "&houseTypeName=" + roomName+ "&roomId=" + roomId + "&roomNum=" + roomNum;
        url = url.replace(/#/g, "@$@");
        window.location.href = url;
    }

    function typeDetail(houseType, cityId, projectId, areaId, buildingId, unitId,groupId,regionId) {
        var url = "../housetype/houseTypeDetailForRoom?id=" + houseType + "&groupId="+groupId+"&regionId="+regionId+"&cityId=" + cityId + "&projectNum=" + projectId + "&areaId=" + areaId + "&buildingNum=" + buildingId + "&unitNum=" + unitId;
        url = url.replace(/#/g, "@$@");
        window.location.href = url;
    }

    //根据集团id查询区域信息
    $("#groupId").change(function () {
        document.getElementById("regionId").value = "";
        document.getElementById("cityId").value = "";
        document.getElementById("projectId").value = "";
        document.getElementById("areaId").value = "";
        document.getElementById("buildingId").value = "";
        document.getElementById("unitId").value = "";

        var groupId = $("#groupId").val();
        $.ajax({
            url: "../problem/getQCAuthAgency",
            type: "get",
            async: "false",
            data: {"type": '100000001',
                "groupId":groupId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("regionId").innerHTML = "";
                        document.getElementById("cityId").innerHTML = "";
                        document.getElementById("projectId").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }

                                }
                            }
                        }
                        $("#regionId").append(option);
                    }
                }
            }
        });
    });
    //根据区域id查询城市信息
    $("#regionId").change(function () {
        document.getElementById("cityId").value = "";
        document.getElementById("projectId").value = "";
        document.getElementById("areaId").value = "";
        document.getElementById("buildingId").value = "";
        document.getElementById("unitId").value = "";

        var regionId = $("#regionId").val();
        $.ajax({
            url: "../problem/getQCAuthAgency",
            type: "get",
            async: "false",
            data: {"type": '100000003',
                "regionId":regionId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("cityId").innerHTML = "";
                        document.getElementById("projectId").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }

                                }
                            }
                        }
                        $("#cityId").append(option);
                    }
                }
            }
        });
    });


    //根据城市Id获取项目信息
    $("#cityId").change(function () {
        document.getElementById("projectId").value = "";
        document.getElementById("areaId").value = "";
        document.getElementById("buildingId").value = "";
        document.getElementById("unitId").value = "";

        var cityId = $("#cityId").val();
        $.ajax({
            url: "../problem/getProjectMun",
            type: "get",
            async: "false",
            data: {"type": '100000002',
                "cityId":cityId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("projectId").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }
                                }
                            }
                        }
                        $("#projectId").append(option);
                    }
                }
            }
        });
    });

    $("#projectId").change(function () {
        document.getElementById("areaId").value = "";
        document.getElementById("buildingId").value = "";
        document.getElementById("unitId").value = "";
        var projectId = $("#projectId").val();
        $.ajax({
            url: "../houseroomtype/getAreaListByProject",
            type: "get",
            async: "false",
            data: {"projectId": projectId},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    var option = "";
                    if (data != null) {
                        document.getElementById("areaId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#areaId").append(option);
                    }
                }
            }
        });
    });

    $("#areaId").change(function () {
        document.getElementById("buildingId").value = "";
        document.getElementById("unitId").value = "";
        var areaId = $("#areaId").val();
        $.ajax({
            url: "../houseroomtype/getBuildingListByArea",
            type: "get",
            async: "false",
            data: {"areaId": areaId},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    var option = "";
                    if (data != null) {
                        document.getElementById("buildingId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#buildingId").append(option);
                    }
                }
            }
        });
    });
    $("#buildingId").change(function () {
        document.getElementById("unitId").value = "";
        var buildingId = $("#buildingId").val();
        $.ajax({
            url: "../houseroomtype/getUnitList",
            type: "get",
            async: "false",
            data: {"buildingId": buildingId},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    var option = "";
                    if (data != null) {
                        document.getElementById("unitId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#unitId").append(option);
                    }
                }
            }
        });
    });

    function search() {
        var groupId = $("#groupId").val();
        if (groupId == "" || groupId == '0') {
            alert("请选择集团");
            return;
        }
        var regionId = $("#regionId").val();
        if (regionId == "" || regionId == '0') {
            alert("请选择区域");
            return;
        }
        var cityId = $("#cityId").val();
        if (cityId == "" || cityId == '0') {
            alert("请选择城市");
            return;
        }
        var projectId = $("#projectId").val();
        if (projectId == "" || projectId == '0') {
            alert("请选择项目");
            return;
        }
        var areaId = $("#areaId").val();
        if (areaId == "" || areaId == '0') {
            alert("请选择地块");
            return;
        }
        var buildingId = $("#buildingId").val();
        if (buildingId == "" || buildingId == '0') {
            alert("请选择楼栋");
            return;
        }
        var unitId = $("#unitId").val();
        if (unitId == "" || unitId == '0') {
            alert("请选择单元");
            return;
        }
        document.forms[0].submit();
    }

</script>

</body>
</html>