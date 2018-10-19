<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/2/20
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>

    <script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
    </script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>
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
    <script src="../static/property/js/checkNullAllJsp.js"></script>
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
    //当引入和自建选择时，改变输入框状态
    function turnType() {
        var type = $("#types").val();
        if (type == "1") {
            $("#cu_projectId").attr({"disabled": true});
        }
        if (type == "2") {
            $("#cu_projectId").attr({"disabled": false});
        }
    }
    function validate(status) {
        title = $("#title").val();//活动标题
        if (!CheckNull($("#title").val(), "活动主题不能为空！")) {
            return false;
        }
        if (title == "") {
            alert("活动主题不能为空");
            return false;
        }
        if (title.length > 30) {
            alert("活动主题不能大于三十字");
            return false;
        }
        if (!CheckNull($("#activityDate").val(), "活动日期不能为空！")) {
            return false;
        }
        activityDate = $("#activityDate").val();//活动日期
        if (activityDate == "") {
            alert("活动日期不能为空");
            return false;
        }
        if (!CheckNull($("#headCount").val(), "人数上限不能为空！")) {
            return false;
        }
        headCount = $("#headCount").val();
        if (headCount == "") {
            alert("人数上限不能为空");
            return false;
        }
        if (!CheckNull($("#headCount").val(), "总人数不能为空！")) {
            return false;
        }
        headCount = $("#headCount").val();
        if (headCount == "") {
            alert("总人数不能为空");
            return false;
        }
        if (headCount.length > 9) {
            alert("总人数不能超过9位");
            return false;
        }
        var re = /^\+?[1-9][0-9]*$/;
        if (!re.test(headCount)) {
            alert("总人数请输入正整数")
            return false;
        }
        if (!re.test($("#applyMaxNum").val())) {
            alert("报名人数上限(每户)请输入正整数");
            return false;
        }
        if (!re.test($("#applyInfoMaxNum").val())) {
            alert("报名信息最大组数请输入正整数");
            return false;
        }
        if (!CheckNull($("#address").val(), "活动地址不能为空！")) {
            return false;
        }
        address = $("#address").val();//活动地点
        if (address == "") {
            alert("活动地址不能为空");
            return false;
        }
        document.getElementById("activityDesc").value = UE.getEditor('editor').getContent();
        if (!CheckNull($("#activityDesc").val(), "活动内容不能为空！")) {
            return false;
        }
        activityDesc = $("#activityDesc").val();//活动内容
        if (activityDesc == "") {
            alert("活动内容不能为空");
            return false;
        }

        $("#pushState").val(status);

        $("#citys").val($("#cityList").val());
        $("#projects").val($("#projectList").val());
        document.getElementById("frm").submit();
    }
</script>
<script type="application/javascript">
    var i = 9999;
    $(function () {
        $('#addTextInput').click(function () {
            $('#addText').append('<div id="test' + i + '"><input type="text" class="form-control" placeholder="" name="applyInfo"/>' +
                    '<input type="button" onclick="addT()" value="添加"><input type="button" onclick="del(' + i + ')" value="删除"/></div>');
            i++;
        });
    });
    function addT() {
        $('#addText').append('<div id="testt' + i + '"><input type="text" class="form-control" placeholder="" name="applyInfo"/>' +
                '<input type="button" onclick="addT()" value="添加"><input type="button" onclick="dele(' + i + ')" value="删除"/></div>');
        i++;
    }
    function dele(o) {
        $("div").remove("#testt" + o);
    }
    function del(o) {
        $("div").remove("#test" + o);
    }
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005400030000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" value="005400030000"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body" style="min-height:1500px">
                    <form class="form-horizontal" id="frm" action="../community/updateEditActivity" method="post"
                          enctype="MULTIPART/FORM-DATA">
                        <input type="hidden" id="activityId" name="activityId" value="${activityAdminDto.activityId}"/>
                        <input type="hidden" id="citys" name="citys" value="">
                        <input type="hidden" id="projects" name="projects" value="">
                        <input type="hidden" id="content" name="content" value="">
                        <div class="form-group  col-xs-10">
                            <label for="city" class="col-sm-2 control-label">
                                <spring:message code="announcementDTO.cityName"/>
                            </label>
                            <div class="col-sm-7">
                                <select id="city" name="city" class="form-control" onchange="queryProjectNameById()">
                                    <option value="-1">--请选择城市--</option>
                                    <c:forEach items="${city}" var="item">
                                        <option value="${item.cityId }"
                                                <c:if test="${item.cityId eq '0'}">selected</c:if>>${item.cityName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-2">
                                <input type="image" name="addCity" id="addCity"
                                       value="<spring:message code="announcementDTO.add"/>"
                                       onclick="cityAdd();return false;"
                                       src="../static/images/add.ico">
                            </div>
                        </div>
                        <%--城市列表--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-2 control-label" for="cityList"
                                   style="min-width:115px;">
                                <spring:message code="announcementDTO.selectedCity"/>
                            </label>
                            <div class="col-sm-7">
                                <textarea class="form-control" name="cityList" id="cityList" readonly
                                          style=" height: 71px;"><c:if
                                        test="${cityName != ''}">${cityName }</c:if><c:if
                                        test="${cityName == ''}">${cityName }</c:if></textarea>
                                <%--<textarea class="form-control" name="cityList" id="cityList" readonly--%>
                                <%--style="width: 432px; height: 71px;" ><c:if test="${allCityInScope != ''}">${allCityInScope }</c:if><c:if test="${allCityInScope == ''}">${cityInScope }</c:if></textarea>--%>
                                <input type="hidden" id="cityIds" name="cityIds" value="${cityIdInScope}"/>
                            </div>
                        </div>
                        <%--城市下项目--%>
                        <div class="form-group  col-xs-10">
                            <label for="projectName" class="col-sm-2 control-label">
                                <spring:message code="HousePayBatch.projectName"/>
                            </label>
                            <div class="col-sm-7">
                                <select id="projectName" name="projectName" class="form-control">
                                </select>
                            </div>
                            <div class="col-sm-2">
                                <input type="image" name="addProject" id="addProject"
                                       value="" onclick="projectAdd();return false;" src="../static/images/add.ico">
                            </div>
                        </div>
                        <%--项目列表--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-2 control-label" for="projectList"
                                   style="min-width:115px;">
                                <spring:message code="announcementDTO.selectedProject"/>
                            </label>
                            <div class="col-sm-7">
                                <textarea class="form-control" name="projectList" id="projectList" readonly
                                          style="height: 71px;">${projectNameList}</textarea>
                                <input type="hidden" id="projectIds" name="projectIds" value="${projectIds}">
                            </div>
                        </div>
                        <%--报名类型--%>
                        <div class="form-group  col-xs-10">
                            <label for="types" class="col-sm-2 control-label">
                                <spring:message code="activityManage.activityTypes"/>
                            </label>
                            <div class="col-sm-7">
                                <select class="form-control" placeholder="" id="types" name="types"
                                        onchange="turnType()">
                                    <%--<option value="0" ><spring:message code="activityManage.activityTypes_all"/></option>--%>
                                    <option value="2" <c:if test="${activityAdminDto.types eq '2'}">selected</c:if>>
                                        仅业主可报名
                                    </option>
                                    <option value="1" <c:if test="${activityAdminDto.types eq '1'}">selected</c:if>>
                                        所有注册用户可报名
                                    </option>
                                </select>
                            </div>
                        </div>
                        <%--活动类型--%>
                        <div class="form-group  col-xs-10">
                            <label for="activityType" class="col-sm-2 control-label">活动类型</label>
                            <div class="col-sm-7">
                                <select class="form-control" placeholder="" id="activityType" name="activityType">
                                    <option value="1"
                                            <c:if test="${activityAdminDto.activityType eq '1'}">selected</c:if>>普通活动
                                    </option>
                                    <option value="2"
                                            <c:if test="${activityAdminDto.activityType eq '2'}">selected</c:if>>营销活动
                                    </option>
                                    <option value="3"
                                            <c:if test="${activityAdminDto.activityType eq '3'}">selected</c:if>>商业活动
                                    </option>
                                    <option value="4"
                                            <c:if test="${activityAdminDto.activityType eq '4'}">selected</c:if>>总部活动
                                    </option>
                                </select>
                            </div>
                        </div>
                        <%--主题类型--%>
                        <div class="form-group  col-xs-10">
                            <label for="themeType" class="col-sm-2 control-label">主题类型</label>
                            <div class="col-sm-7">
                                <select class="form-control" placeholder="" id="themeType" name="themeType">
                                    <option value="0" <c:if test="${empty activityAdminDto.themeType or activityAdminDto.themeType eq '0'}">selected</c:if>>--请选择--</option>
                                    <option value="1" <c:if test="${activityAdminDto.themeType eq '1'}">selected</c:if>>家兴业茂</option>
                                    <option value="2" <c:if test="${activityAdminDto.themeType eq '2'}">selected</c:if>>穆如清风</option>
                                    <option value="3" <c:if test="${activityAdminDto.themeType eq '3'}">selected</c:if>>优居计划</option>
                                    <option value="4" <c:if test="${activityAdminDto.themeType eq '4'}">selected</c:if>>金彩有你</option>
                                </select>
                            </div>
                        </div>
                        <%--活动主题--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-2 control-label" for="title"><spring:message
                                    code="activityManage.activityTitle"/></label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" placeholder="" id="title" name="title"
                                       value="${activityAdminDto.title}">
                            </div>
                        </div>
                        <%--活动日期--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-2 control-label" for="activityDate"><spring:message
                                    code="activityManage.activityDate"/></label>

                            <div class="input-group date form_date col-sm-7" data-date=""
                                 data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="" id="activityDate"
                                       value="${activityAdminDto.activityDate}"
                                       name="activityDate" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <%--活动结束日期--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-2 control-label" for="activityEndDate">至</label>
                            <div class="input-group date form_date col-sm-7" data-date=""
                                 data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="" id="activityEndDate"
                                       value="${activityAdminDto.activityEndDate}"
                                       name="activityEndDate" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <%--报名开始时间--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-2 control-label" for="applyStartTime">报名时间</label>

                            <div class="input-group date form_date col-sm-7" data-date=""
                                 data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="" id="applyStartTime"
                                       value="${activityAdminDto.applyStartTime}"
                                       name="applyStartTime" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <%--报名结束时间--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-2 control-label" for="applyEndTime">至</label>
                            <div class="input-group date form_date col-sm-7" data-date=""
                                 data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="" id="applyEndTime"
                                       value="${activityAdminDto.applyEndTime}"
                                       name="applyEndTime" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <%--报名人数上限--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-2 control-label" for="headCount"><spring:message
                                    code="activityManage.activityHeadCountApply"/></label>

                            <div class="col-sm-7">
                                <input type="text" class="form-control" placeholder="" id="headCount" name="headCount"
                                       value="${activityAdminDto.headCount}">
                            </div>
                        </div>
                        <%--每户报名人数上限--%>
                        <div class="form-group col-xs-10">
                            <label class="col-sm-2 control-label" for="headCount">报名人数上限(每户)</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" placeholder="" id="applyMaxNum"
                                       name="applyMaxNum" value="${activityAdminDto.applyMaxNum}">
                            </div>
                        </div>
                        <%--是否使用黑名单--%>
                        <div class="form-group col-xs-10">
                            <label for="isBlacklist" class="col-sm-2 control-label">是否使用黑名单</label>
                            <div class="col-sm-7" onclick="isBlacklistChange()">
                                <select class="form-control" placeholder="" id="isBlacklist" name="isBlacklist">
                                    <option value="0"
                                            <c:if test="${activityAdminDto.isBlacklist eq '0'}">selected</c:if>>否
                                    </option>
                                    <option value="1"
                                            <c:if test="${activityAdminDto.isBlacklist eq '1'}">selected</c:if>>是
                                    </option>
                                </select>
                            </div>
                        </div>
                        <%--黑名单列表--%>
                        <c:if test="${activityAdminDto.isBlacklist eq '0'}">
                        <div class="form-group col-xs-10 blacklistId" style="display:none;"></c:if>
                            <c:if test="${activityAdminDto.isBlacklist eq '1'}">
                            <div class="form-group col-xs-10 blacklistId"></c:if>
                                <label for="blacklistId" class="col-sm-2 control-label">选择黑名单</label>
                                <div class="col-sm-7">
                                    <select id="blacklistId" name="blacklistId" class="form-control">
                                        <option value="">请选择</option>
                                        <c:forEach items="${blacklistList}" var="blacklist">
                                            <option value="${blacklist.id}"
                                                    <c:if test="${activityAdminDto.blacklistId eq blacklist.id}">selected</c:if>>${blacklist.listName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <%--是否作为宣传位--%>
                            <div class="form-group  col-xs-10">
                                <label for="isBanner" class="col-sm-2 control-label">是否作为宣传位</label>
                                <div class="col-sm-7">
                                    <select class="form-control" placeholder="" id="isBanner" name="isBanner">
                                        <option value="0"
                                                <c:if test="${activityAdminDto.isBanner eq '0'}">selected</c:if>>否
                                        </option>
                                        <option value="1"
                                                <c:if test="${activityAdminDto.isBanner eq '1'}">selected</c:if>>是
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <%--是否有外链--%>
                            <div class="form-group  col-xs-10">
                                <label for="isLink" class="col-sm-2 control-label">是否有外链</label>
                                <div class="col-sm-7" onclick="isLinkChange()">
                                    <select class="form-control" placeholder="" id="isLink" name="isLink">
                                        <option value="0"
                                                <c:if test="${activityAdminDto.isLink eq '0'}">selected</c:if>>否
                                        </option>
                                        <option value="1"
                                                <c:if test="${activityAdminDto.isLink eq '1'}">selected</c:if>>是
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <%--外链地址--%>
                            <c:if test="${empty activityAdminDto.isLink or activityAdminDto.isLink eq '0'}">
                            <div class="form-group col-xs-10 linkSrc" style="display:none;"></c:if>
                                <c:if test="${activityAdminDto.isLink eq '1'}">
                                <div class="form-group col-xs-10 linkSrc"></c:if>
                                    <label class="col-sm-2 control-label" for="linkSrc">外链地址</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" id="linkSrc"
                                               name="linkSrc" value="${activityAdminDto.linkSrc}">
                                    </div>
                                </div>
                                <%--咨询热线--%>
                                <div class="form-group col-xs-10">
                                    <label class="col-sm-2 control-label" for="hotline"><spring:message
                                            code="activityManage.activityHotLine"/></label>

                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" id="hotline"
                                               name="hotline"
                                               value="${activityAdminDto.hotline}">
                                    </div>
                                </div>
                                <%--活动地点--%>
                                <div class="form-group col-xs-10">
                                    <label class="col-sm-2 control-label" for="address"><spring:message
                                            code="activityManage.activityProject"/></label>

                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" id="address"
                                               name="address"
                                               value="${activityAdminDto.address}">
                                    </div>
                                </div>
                                <%--报名信息--%>
                                <div class="form-group col-xs-10">
                                    <label class="col-sm-2 control-label">报名信息</label>
                                    <div class="col-sm-7" id="addText">
                                        <div>
                                            <input type="button" id="addTextInput" name="addTextInput" value="添加">
                                        </div>
                                        <c:forEach items="${activityAdminDto.applyInfo2}" var="apply">
                                            <div id="test${apply.number}">
                                                <input type="text" class="form-control" placeholder="" name="applyInfo"
                                                       value="${apply.name}"/>
                                                <input type="button" onclick="del(${apply.number})" value="删除"/>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <%--报名信息最大组数--%>
                                <div class="form-group col-xs-10">
                                    <label class="col-sm-2 control-label" for="applyInfoMaxNum">报名信息最大组数</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" id="applyInfoMaxNum"
                                               name="applyInfoMaxNum" value="${activityAdminDto.applyInfoMaxNum}">
                                    </div>
                                </div>
                                <div class="form-group col-xs-10" style="z-index:0">
                                    <label class="col-sm-2 control-label"><spring:message
                                            code="activityManage.activityDesc"/></label>
                                    <div class="col-sm-10">
                                        <script id="editor" type="text/plain"
                                                style="width:100%;height:200px;overflow-y:auto;">${content}</script>
                                    </div>
                                </div>
                                <input type="hidden" id="activityDesc" name="activityDesc" value="">
                                <div class='clearfix'></div>
                                <%--主页图片--%>
                                <div class="form-group col-xs-10">
                                    <label class="col-sm-2 control-label" for="homePageimgpath"><spring:message
                                            code="activityManage.homePageimgpath"/></label>
                                    <div class="col-sm-7">
                                        <input type="file" class="form-control"
                                               placeholder="<spring:message code = "CommunityOverview.imgRemark"/>"
                                               id="homePageimgpath"
                                               name="homePageimgpath" accept="image/*"
                                               height="160px"  onchange="check(this)">
                                        <p style="color: red">建议图片宽高比750*420</p>
                                        <!-- 图片ͼ-->
                                        <div class="form-group col-lg-5">
                                            <div class="col-sm-7" id="circleLogoUrl" name="circleLogoUrl">
                                                <c:if test="${communityImageSrc != null and communityImageSrc != ''}">
                                                    <img src="${communityImageSrc}"
                                                         style="width: 100px;height: 56.17px"/>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <div class='clearfix'></div>
                                    <div id="demo_homePageimgpath" style="padding-top: 15px;"></div>
                                </div>
                                <%--是否配置报名时间段--%>
                                <div class="form-group  col-xs-10">
                                    <label for="isTimeRange" class="col-sm-2 control-label">是否选择时间段</label>
                                    <div class="col-sm-7" onclick="applyTypeChange()">
                                        <select class="form-control" placeholder="" id="isTimeRange" name="isTimeRange">
                                            <option value="0"
                                                    <c:if test="${empty activityAdminDto.isTimeRange or activityAdminDto.isTimeRange eq '0'}">selected</c:if>>
                                                否
                                            </option>
                                            <option value="1"
                                                    <c:if test="${activityAdminDto.isTimeRange eq '1'}">selected</c:if>>
                                                是
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div id="timeRangeMaxUser"
                                     <c:if test="${empty activityAdminDto.isTimeRange or activityAdminDto.isTimeRange eq '0'}">style="display:none;"</c:if>>
                                    <%-- 日期 --%>
                                    <div class="form-group col-xs-10">
                                        <label class="col-sm-2 control-label" for="reservationDate">请选择日期</label>
                                        <div class="col-sm-7">
                                            <div class="input-group date form_datetime" data-date=""
                                                 data-date-format="yyyy-mm-dd"
                                                 data-link-field="dtp_input1">
                                                <input type="text" class="form-control " placeholder=""
                                                       id="reservationDate" name="reservationDate" readonly>
                                                <span class="input-group-addon"><span
                                                        class="glyphicon glyphicon-remove"></span></span>
                                                <span class="input-group-addon"><span
                                                        class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="timeRange">
                                    </div>
                                    <div class="form-group col-xs-10">
                                        <button type="button" class=" btn btn-primary" onclick="addTimeRangeMaxUser()">
                                            新增
                                        </button>
                                        <button type="button" class=" btn btn-primary" onclick="saveTimeRange()">保存
                                        </button>
                                    </div>
                                </div>
                                <input type="hidden" name="pushState" id="pushState" value="0">
                                <div class="text-center form-group  col-xs-10" style="padding:0;">
                                    <button type="button" class="btn btn-primary" onclick="validate('1')">
                                        <spring:message
                                                code="activityManage.activityPublish"/></button>
                                    <button type="button" class="btn btn-primary" onclick="validate('0')">
                                        <spring:message
                                                code="activityManage.activityUnPublish"/></button>
                                    <a href="../community/activityManage.html" class="btn btn-primary"
                                       for=""><spring:message
                                            code="common_cancel"/></a>
                                </div>

                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script type="text/javascript">
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

    var inputs = $("#homePageimgpath").get(0);
    var result = document.getElementById("circleLogoUrl");
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

    //    var input = $("#activityimgpath").get(0);
    //    var results = document.getElementById("circleLogoUrl");
    //    if (typeof FileReader === 'undefined') {
    //        results.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
    //        input.setAttribute('disabled', 'disabled');
    //    } else {
    //        input.addEventListener('change', readFiles, false);
    //    }

    //    function readFiles() {
    //
    //        for (var i = 0; i < this.files.length; i++) {
    //            var file = this.files[0];
    //            var reader = new FileReader();
    //            reader.readAsDataURL(file);
    //            reader.onload = function (e) {
    //                results.innerHTML = '<img src="' + this.result + '" style="width:100px" alt=""/>';
    //            }
    //        }
    //    }

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

</script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $(function () {
        console.log("sqq")
        $("#005400030000").addClass("active");
        $("#005400030000").parent().parent().addClass("in");
        $("#005400030000").parent().parent().parent().parent().addClass("active");
    })
    //    $('.form_datetime').datetimepicker({
    //        language: 'zh-CN',
    //        weekStart: 1,
    //        todayBtn: 1,
    //        autoclose: 1,
    //        todayHighlight: 1,
    //        startView: 2,
    //        forceParse: 0,
    //        showMeridian: 1
    //    });
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
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        linkField: "mirror_field",
        linkFormat: "yyyy-mm-dd",
        minView: 2,
        maxView: 3,
        startDate: '${fn:substring(activityAdminDto.activityDate, 0, 10)}',
        endDate: '${fn:substring(activityAdminDto.activityEndDate, 0, 10)}'
    }).on('changeDate', function () {
        $("#timeRange").empty();
        //请求时间段数据
        $.ajax({
            url: "../community/getApplyTimeRangeList",
            type: "POST",
            async: "false",
            data: {
                "activityId": $("#activityId").val(),
                "applyDate": $("#reservationDate").val(),
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (data) {
                if (data.error == 0) {
                    var reservationTimeRangeList = data.reservationTimeRangeList;
                    for (var i = 0, length = reservationTimeRangeList.length; i < length; i++) {
                        var html = '<div class="form-group col-xs-10"><div class="form-group col-xs-10"><label class="col-sm-2 control-label">请选择时间段</label>';
                        html += '<div class="col-sm-4"><div class="input-group date form_time">';
                        html += '<input type="text" class="form-control" placeholder="" value="' + reservationTimeRangeList[i].startTime + '" name="startDateList" readonly>';
                        html += '<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>';
                        html += '</div></div><div class="col-sm-4"><div class="input-group date form_time">';
                        html += '<input type="text" class="form-control" placeholder="" value="' + reservationTimeRangeList[i].endTime + '" name="endDateList" readonly>';
                        html += '<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>';
                        html += '</div></div></div><div class="form-group col-xs-4"><label class="col-sm-2 control-label" style="padding-right: 12px">交付人数</label>';
                        html += '<div class="col-sm-4"><input type="text" class="form-control maxUser" name="maxUserList" value="' + reservationTimeRangeList[i].maxUser + '"/></div></div></div>';
                        $("#timeRange").append(html);
                    }
                    $('.form_time').datetimepicker({
                        language: 'zh-CN',
                        format: 'hh:ii',
                        autoclose: true,
                        startView: 0,
                        maxView: 'minute'
                    });
                } else if (data.error == -1) {
                    alert("时间段配置数据请求失败！");
                }
            }
        });
    });
    function addTimeRangeMaxUser() {
        var html = '<div class="form-group col-xs-10"><div class="form-group col-xs-10"><label class="col-xs-3 control-label">请选择时间段</label>';
        html += '<div class="col-xs-4"><div class="input-group date form_time">';
        html += '<input type="text" class="form-control" placeholder="" value="${homeLetter.homeLetterDate}" name="startDateList" readonly>';
        html += '<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>';
        html += '</div></div><div class="col-xs-4"><div class="input-group date form_time">';
        html += '<input type="text" class="form-control" placeholder="" value="${homeLetter.homeLetterDate}" name="endDateList" readonly>';
        html += '<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span><span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>';
        html += '</div></div></div><div class="form-group col-xs-4"><label class="col-xs-3 control-label" style="padding-right: 12px">报名人数</label>';
        html += '<div class="col-xs-4"><input type="text" class="form-control maxUser" name="maxUserList"/></div></div></div>';
        $("#timeRange").append(html);

        $('.form_time').datetimepicker({
            language: 'zh-CN',
            format: 'hh:ii',
            autoclose: true,
            startView: 0,
            maxView: 'minute'
        });
    }
    //时间段保存
    function saveTimeRange() {
        var startDateList = [];
        var endDateList = [];
        var maxUserList = [];
        if ($("#isTimeRange").val() == "1") {
            var flag = 0;
            $("input[name='startDateList']").each(function (i) {
                if ($(this).val() == '') {
                    flag = 1;
                    return false;
                } else {
                    startDateList[i] = $(this).val();
                }
            });
            $("input[name='endDateList']").each(function (i) {
                if ($(this).val() == '') {
                    flag = 1;
                    return false;
                } else {
                    endDateList[i] = $(this).val();
                }
            });
            $("input[name='maxUserList']").each(function (i) {
                if ($(this).val() == '') {
                    flag = 1;
                    return false;
                } else {
                    maxUserList[i] = $(this).val();
                }
            });
            if (flag == 1) {
                alert("请完成时间段配置！");
                return false;
            }
        }
        $.ajax({
            url: "../community/saveApplyTimeRange",
            type: "POST",
            async: "false",
            traditional: true,
            data: {
                "activityId": $("#activityId").val(),
                "applyDate": $("#reservationDate").val(),
                "startTimeList": startDateList,
                "endTimeList": endDateList,
                "maxUserList": maxUserList
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (data) {
                if (data.error == 0) {
                    alert("时间段保存成功！");
                } else if (data.error == -1) {
                    alert("时间段保存失败！");
                } else {
                    alert("时间段保存异常！");
                }
            }
        });
    }
    function applyTypeChange() {
        if ($("#isTimeRange").val() == 1) {
            $("#timeRangeMaxUser").show();
        } else {
            $("#timeRangeMaxUser").hide();
        }
    }
    function isBlacklistChange() {
        if ($("#isBlacklist").val() == 1) {
            $(".blacklistId").show();
            $.ajax({
                type: "GET",
                url: "../blacklist/getBlacklistListJson",
                cache: false,
                async: false,
                dataType: "json",
                success: function (data) {
                    if (data.error == 0) {
                        var blacklistList = data.blacklistList;
                        for (var i = 0, length = blacklistList.length; i < length; i++) {
                            $("#blacklistId").append("<option value='" + blacklistList[i].id + "'>" + blacklistList[i].listName + "</option>");
                        }
                    }
                }
            });
        } else {
            $(".blacklistId").hide();
        }
    }
    function isLinkChange() {
        if ($("#isLink").val() == 1) {
            $(".linkSrc").show();
        } else {
            $(".linkSrc").hide();
        }
    }
    function queryProjectNameById() {
        var projectId = $("#city").val();
        if (projectId == '-1') {
            $("#projectName").empty();
            return;
        }
        $("#planName").empty();
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId + "/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectName").empty();
            for (var k in arr) {
                $("#projectName").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
            }
        });
    }
    function cityAdd() {
        //#1.获取内容
        var projectName = $("#city").find("option:selected").text();
        //获取隐藏域的值_Wyd_2016.06.03
        var projectId = $("#city").find("option:selected").val();
        if (projectId == '-1') {
            alert("请选择城市，并添加！");
            return;
        }
        if (projectName == "全部城市") {
            //清空textarea
            $("#cityList").val("全部城市");
            $("#cityIds").val('0,');
            //清空项目列表_Wyd_2016.08.08_数据权限
            $("#projectList").val("");
            $("#projectIds").val("");
            $("#projectName").empty();
            $("#projectName").append('<option value="0,">全部项目</option>');
        } else {
            if ($("#cityList").val() == "") {
                //如果textarea中没有元素
                $("#cityList").val($("#cityList").val() + projectName + ',');
                $("#cityIds").val($("#cityIds").val() + projectId + ',');
            } else if ($("#cityList").val() != "") {
                //判断textArea中是否有select的值，如果没有则添加
                //获取textArea数组
                var strArray = $("#cityList").val().toString().split(",");
                //获取select值
                var str = $("#city").find("option:selected").text();
                //判断是否在数组中
                if (strArray.toString().indexOf(str) > -1) {
                    return;
                }
                //判断是否="全部城市"
                if ($("#cityList").val() == "全部城市") {
                    //清空
                    $("#cityList").val("");
                    $("#cityList").val($("#cityList").val() + projectName + ',');
                    $("#cityIds").val(projectId + ',');
                    return;
                }
                //如果textarea中有元素，前置","
                $("#cityList").val($("#cityList").val() + projectName + ',');
                $("#cityIds").val($("#cityIds").val() + projectId + ',');
            }
        }
    }
    function projectAdd() {

        //#1.获取内容
        var projectName = $("#projectName").find("option:selected").text();
        var projectId = $("#projectName").find("option:selected").val();
        if (projectName == "全部项目") {
            //清空textarea
            $("#projectList").val("全部项目");
            $("#projectIds").val("0,");
        } else {
            if ($("#projectList").val() == "") {
                //如果textarea中没有元素
                $("#projectList").val($("#projectList").val() + projectName + ',');
                $("#projectIds").val($("#projectIds").val() + projectId + ',');
            } else if ($("#projectList").val() != "") {
                //判断textArea中是否有select的值，如果没有则添加
                //获取textArea数组
                var strArray = $("#projectList").val().toString().split(",");
                //获取select值
                var str = $("#projectName").find("option:selected").text();
                //判断是否在数组中
                if (strArray.toString().indexOf(str) > -1) {
                    return;
                }
                //判断是否="全部项目"
                if ($("#projectList").val() == "全部项目") {
                    //清空
                    $("#projectList").val("");
                    $("#projectList").val($("#projectList").val() + projectName + ',');
                    $("#projectIds").val(projectId + ',');
                    return;
                }
                //如果textarea中有元素，前置","
                $("#projectList").val($("#projectList").val() + projectName + ',');
                $("#projectIds").val($("#projectIds").val() + projectId + ',');
            }
        }
    }
</script>
<!--富文本控件-->
<script type="text/javascript">
    //实例化编辑器 ------
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor', {
                //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
                toolbars: [[
                    "fullscreen", "source", "|", "undo", "redo", "|",
                    "bold", "italic", "underline", "fontborder", "strikethrough", "superscript", "subscript", "removeformat", "formatmatch", "autotypeset", "blockquote", "pasteplain", "|", "forecolor", "backcolor", "insertorderedlist", "insertunorderedlist", "selectall", "cleardoc", "|",
                    "rowspacingtop", "rowspacingbottom", "lineheight", "|",
                    "customstyle", "paragraph", "fontfamily", "fontsize", "|",
                    "directionalityltr", "directionalityrtl", "indent", "|",
                    "justifyleft", "justifycenter", "justifyright", "justifyjustify", "|", "touppercase", "tolowercase", "|",
                    "link", "unlink", "anchor", "|", "imagenone", "imageleft", "imageright", "imagecenter", "|",
                    "simpleupload", "insertimage", "emotion", "scrawl", "insertvideo", "music", "attachment", "map", "gmap", "insertframe", "insertcode", "pagebreak", "template", "background", "|",
                    "horizontal", "date", "time", "spechars", "snapscreen", "wordimage", "|",
                    "inserttable", "deletetable", "insertparagraphbeforetable", "insertrow", "deleterow", "insertcol", "deletecol", "mergecells", "mergeright", "mergedown", "splittocells", "splittorows", "splittocols", "charts", "|",
                    "searchreplace", "help", "drafts"
                ]],
                //focus时自动清空初始化时的内容
                autoClearinitialContent: false,
                //关闭字数统计
                wordCount: true,
                //关闭elementPath
                elementPathEnabled: true,
                //默认的编辑区域高度
                initialFrameHeight: 200,
                maximumWords: 4000,
                //更多其他参数，请参考ueditor.config.js中的配置项

                initialContent: '${activityAdminDto.activityDesc}'

            }
    );


    function isFocus(e) {
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e) {
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }

    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        <%--arr.push("${merDesc}123");--%>
        UE.getEditor('editor').setContent('${merDesc}', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData() {
        alert(UE.getEditor('editor').execCommand("getlocaldata"));
    }

    function clearLocalData() {
        UE.getEditor('editor').execCommand("clearlocaldata");
        alert("已清空草稿箱")
    }

    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function (action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
            return '${baseUrl}../communityNews/uploadImage.html?action=uploadimage';
            // return '${baseUrl}../ueditor/fileupload/upload.html';
        } else if (action == 'uploadvideo') {
            return '${baseUrl}../communityNews/uploadImage.html?action=uploadimage';
            // return '${baseUrl}../ueditor/fileupload/upload.html';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }

</script>
</body>
</html>