<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    <style>
        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 70px;
        }
    </style>
</head>
<script>
    $(function () {
        $("#001500020000").addClass("active");
        $("#001500020000").parent().parent().addClass("in");
        $("#001500020000").parent().parent().parent().parent().addClass("active");
    })
    function validate() {
        var radios = $('input[name="houseType"]:checked').val();
        if (radios == null && radios == undefined) {
            alert("请选择数据");
            return false;
        }
        document.getElementById("frm").submit();
    }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="001500020000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <div class="form-horizontal">
                    <form name="" action="../houseroomtype/houseRoomLabel">
                        <input type="hidden" id="cityId1" name="cityId" value="${houseRoomTypeDTO.cityId}"/>
                        <input type="hidden" id="projectId1" name="projectId" value="${houseRoomTypeDTO.projectId}"/>
                        <input type="hidden" id="areaId1" name="areaId" value="${houseRoomTypeDTO.areaId}"/>
                        <input type="hidden" id="buildingId1" name="buildingId" value="${houseRoomTypeDTO.buildingId}"/>
                        <input type="hidden" id="unitId1" name="unitId" value="${houseRoomTypeDTO.unitId}"/>
                        <input type="hidden" id="roomId1" name="roomId" value="${houseRoomTypeDTO.roomId}"/>
                        <input type="hidden" id="roomNum1" name="roomNum" value="${houseRoomTypeDTO.roomNum}"/>
                        <input type="hidden" id="houseTypeName1" name="houseTypeName"
                               value="${houseRoomTypeDTO.houseTypeName}"/>

                        <div class="form-group  col-lg-4">
                            <label for="houseType" class="col-sm-4 control-label"><spring:message
                                    code="rental.houseType"/>：</label>

                            <div class="col-sm-8">
                                <input type="text" name="houseType" class="form-control" id="houseType"
                                       value="${houseRoomTypeDTO.houseType}">
                                <%--<select class="form-control" placeholder="" id="houseType" name="houseType">--%>
                                <%--<c:forEach items="${houseTypeList}" var="houseType">--%>
                                <%--<option value="${houseType.key}">${houseType.value}</option>--%>
                                <%--</c:forEach>--%>
                                <%--</select>--%>
                            </div>
                        </div>
                        <div class="form-group  col-lg-4">
                            <label for="houseType" class="col-sm-1 control-label"></label>

                            <div class="col-sm-8">
                                <button type="submit" class="btn btn-primary">搜索</button>
                            </div>
                        </div>
                    </form>
                    <c:if test="${function.qch40010064 eq 'Y'}">
                        <button type="button" class="btn btn-primary" onclick="validate()"><spring:message
                                code="common_save"/></button>
                    </c:if>
                    <button class="btn btn-primary" onclick="goBack()">返回</button>
                </div>
            </div>
        </div>
        <div class="table-responsive bs-example widget-shadow">
            <form class="form-horizontal" id="frm" action="../houseroomtype/houseRoomLabelSave" method="post"
                  enctype="MULTIPART/FORM-DATA">
                <input type="hidden" id="groupId" name="groupId" value="${houseRoomTypeDTO.groupId}"/>
                <input type="hidden" id="regionId" name="cityId" value="${houseRoomTypeDTO.regionId}"/>
                <input type="hidden" id="cityId" name="cityId" value="${houseRoomTypeDTO.cityId}"/>
                <input type="hidden" id="projectId" name="projectId" value="${houseRoomTypeDTO.projectId}"/>
                <input type="hidden" id="areaId" name="areaId" value="${houseRoomTypeDTO.areaId}"/>
                <input type="hidden" id="buildingId" name="buildingId" value="${houseRoomTypeDTO.buildingId}"/>
                <input type="hidden" id="unitId" name="unitId" value="${houseRoomTypeDTO.unitId}"/>
                <input type="hidden" id="roomId" name="roomId" value="${houseRoomTypeDTO.roomId}"/>
                <input type="hidden" id="roomNum" name="roomNum" value="${houseRoomTypeDTO.roomNum}"/>
                <input type="hidden" id="houseTypeName" name="houseTypeName" value="${houseRoomTypeDTO.houseTypeName}"/>
                <table width="100%" class="tableStyle">
                    <thead>
                    <tr>
                        <td><spring:message code="common_sort"/></td>
                        <th>户型</th>
                        <th>描述</th>
                        <th>修改时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${allHouseType}" var="list" varStatus="row">
                        <tr>
                            <td><input type="radio" name="houseType"
                                       value="${list.id}"><b>${row.index + 1}</b></td>
                            <td>${list.name}</td>
                            <td>${list.comments}</td>
                            <td>${list.operateDate}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
            <m:pager pageIndex="${webPage.pageIndex}"
                     pageSize="${webPage.pageSize}"
                     recordCount="${webPage.recordCount}"
                     submitUrl="${pageContext.request.contextPath }/houseroomtype/houseRoomLabel?pageIndex={0}&houseType=${houseRoomTypeDTO.houseType}&groupId=${houseRoomTypeDTO.groupId}&regionId=${houseRoomTypeDTO.regionId}&cityId=${houseRoomTypeDTO.cityId}&projectId=${houseRoomTypeDTO.projectId}&areaId=${houseRoomTypeDTO.areaId}
                     &buildingId=${houseRoomTypeDTO.buildingId}&unitId=${houseRoomTypeDTO.unitId}&roomId=${houseRoomTypeDTO.roomId}&roomNum=${houseRoomTypeDTO.roomNum}&houseTypeName=${houseRoomTypeDTO.houseTypeName}"/>
        </div>
    </div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    function checkURL(URL) {
        var str = URL;
        var Expression = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
                + "(([0-9]{1,3}.){3}[0-9]{1,3}" + "|"
                + "([0-9a-z_!~*'()-]+.)*"
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]."
                + "[a-z]{2,6})"
                + "(:[0-9]{1,4})?"
                + "((/?)|" + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        var objExp = new RegExp(Expression);
        if (objExp.test(str) == true) {
            return true;
        } else {
            return false;
        }

    }

    var inputs = $("#imgFile").get(0);
    var result = document.getElementById("demo_imgFile");
    if (typeof FileReader === 'undefined') {
        result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
        inputs.setAttribute('disabled', 'disabled');
    } else {
        inputs.addEventListener('change', readFile, false);
    }

    function readFile() {

        for (var i = 0; i < this.files.length; i++) {
            var file = this.files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                result.innerHTML = '<img src="' + this.result + '" style="width:100px" alt=""/>';
            }
        }
    }

    var imgtype = true;
    function check(fnUpload) {
        var filename = fnUpload.value;
        var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
        if (mime != ".jpg" && mime != ".png" && mime != ".jpeg" && mime != ".gif") {
            alert("上传图片类型错误");
            imgtype = false;
        } else {
            imgtype = true;
        }

    }
    function goBack() {
//    var url ="../houseroomtype/houseRoomTypeManage.html";
        var url = "../houseroomtype/houseRoomTypeManage.html?&houseType=${houseRoomTypeDTO.houseType}&groupId=${houseRoomTypeDTO.groupId}&regionId=${houseRoomTypeDTO.cityId}&regionId=${houseRoomTypeDTO.cityId}&projectId=${houseRoomTypeDTO.projectId}&areaId=${houseRoomTypeDTO.areaId}&buildingId=${houseRoomTypeDTO.buildingId}&unitId=${houseRoomTypeDTO.unitId}&roomId=${houseRoomTypeDTO.roomId}&roomNum=${houseRoomTypeDTO.roomNum}&houseTypeName=${houseRoomTypeDTO.houseTypeName}";
        url = url.replace(/#/g, "@$@");
        window.location.href = url;
    }

</script>
</body>
</html>