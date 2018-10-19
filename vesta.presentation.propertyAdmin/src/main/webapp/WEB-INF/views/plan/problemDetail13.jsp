<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <link rel="stylesheet" href="../static/css/detailsCss.css"/>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>
    <script src="../static/js/wow.min.js"></script>
    <script>
        $(function () {
            console.log("sqq")
            $("#001200010000").addClass("active");
            $("#001200010000").parent().parent().addClass("in");
            $("#001200010000").parent().parent().parent().parent().addClass("active");
        })
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

        #locationId {
            height: 450px;
            position: absolute;
            top: -20px;
            width: 500px;
            left: 558px;
            -ms-transform: scale(.5);
            -ms-transform-origin: top;
            -moz-transform: scale(.5);
            -moz-transform-origin: top;
            -webkit-transform: scale(.5);
            -webkit-transform-origin: top;
            -o-transform: scale(.5);
            -o-transform-origin: top;
            transform: scale(.5);
            transform-origin: top;
            top: 0;
        }

        #locationImg {
            width: 500px;
            /*height:300px;*/
            border: none;
        }

        .positionArea {
            position: absolute;
            z-index: 99;
            background: rgba(0, 0, 0, 0.5);
            color: #ffe400;
            font-size: 15px;
        }

        .point {
            position: absolute;
            z-index: 9999;
            width: 15px;
            height: 15px;
            border-radius: 15px;
            background-color: #ffe400;
            /*display:none;*/
        }

    </style>
    <STYLE type=text/css>
        input.error {
            border: 1px solid red;
        }

        label.error {
            background: url("./demo/images/unchecked.gif") no-repeat 0px 0px;
            padding-left: 16px;
            padding-bottom: 2px;
            font-weight: bold;
            color: #EA5200;
        }

        label.checked {
        / / background : url("./demo/images/checked.gif") no-repeat 0 px 0 px;
        }

        .newDetail__ .backTo {
            width: 100px;
            height: 40px;
            background: #53b9fc;
            border-radius: 5px;
            font-size: 14px;
            margin-top: 30px;
            margin-left: 150px;
            margin-bottom: 65px;
        }

        .gallery {
            list-style: none;
        }
    </STYLE>
    <script type="application/javascript">
        function forceCloseQuestion(rectifyId, proType) {
            var str = $("#closes").val();
            if (str) {
                window.location.href = "../problem/forceCloseQeustion?rectifyId=" + rectifyId + "&problemType=" + proType + "&forceClose=" + str;
            } else {
                alert("关闭原因不能为空！！");
                forCloseQuestion();
            }
        }
        function deleteQuestion(rectifyId, proType) {
            if (window.confirm("确定作废吗？")) {
                window.location.href = "../problem/deleteQeustion?rectifyId=" + rectifyId + "&caseType=" + proType;
            }
        }
        function closeQuestion(rectifyId, proType) {
            if (window.confirm("确定关闭吗？")) {
                window.location.href = "../problem/closeQeustion?rectifyId=" + rectifyId + "&caseType=" + proType;
            }
        }
        function redirectQuestion(rectifyId, proType) {
            if (window.confirm("确定派单吗？")) {
                window.location.href = "../problem/redirectQuestionQeustion?rectifyId=" + rectifyId + "&caseType=" + proType;
            }
        }

    </script>
    <script>
        function forCloseQuestion() {
            $(function () {
                $('#myModal').modal({
                    keyboard: true
                })
            });
        }

    </script>
</head>

<body class="cbp-spmenu-push">

<!-- 模态框（Modal） -->
<div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 800px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel"><spring:message
                        code="problem.forceClosese"/></h4>
            </div>
            <div class="modal-body" style="width: 500px;">
                <table>
                    <tr>
                        <td style="width: 200px;">
                            <%-- 楼栋名称 --%>
                            <label class="" for="closes"><spring:message
                                    code="problem.closess"/> ：</label>
                        </td>
                        <td style="width: 300px;">
                            <input type="text" class="form-control col-sm-12" placeholder="" id="closes"
                                   name="closes" value="">
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <%-- 取消 --%>
                <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message
                        code="project.cancel"/></button>
                <%-- 确定 --%>
                <button type="button" class="btn btn-primary"
                        onclick="forceCloseQuestion('${problem.caseId}','${problem.planType}')">
                    <spring:message code="project.confirm"/></button>
            </div>
        </div>
    </div>
</div>

<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="001200010000" username="${authPropertystaff.staffName}"/>

    <div class="newDetail__">
        <div class="nav">
            <div class="rightButton">
                <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
                <td>
                    <c:if test="${function.qch40010042 eq 'Y' && problem.caseState ne '强制关闭' && problem.caseState ne '已完成'&&problem.caseState ne '草稿'&&problem.caseState ne '已废弃' &&problemRole.closeProblem eq '1'}">
                        <%--<a style="cursor: pointer" onClick="closeQuestion('${problem.caseId}','${problem.planType}')" target=_blank><spring:message code="problem.close" /></a>--%>
                        <a href="../problem/closeQeustionNew?rectifyId=${problem.caseId}" target=_blank
                           class="btn btn-primary"><spring:message code="problem.close"/></a>
                    </c:if>
                </td>
              <%--  <td>
                    <c:if test="${problem.caseState ne '强制关闭' && problem.caseState ne '已完成'&&problem.caseState ne '已废弃'&&problemRole.redirectProblem eq '1'}">
                        &lt;%&ndash;<a style="cursor: pointer" onClick="redirectQuestion('${problem.caseId}','${problem.planType}')" target=_blank><spring:message code="problem.redirect" /></a>&ndash;%&gt;
                        <a href="../problem/redirectQuestionQeustion?rectifyId=${problem.caseId}&caseType=${problem.planType}"
                           target=_blank class="btn btn-primary"><spring:message code="problem.redirect"/></a>
                    </c:if>
                </td>--%>
                <td>
                    <c:if test="${function.qch40010043 eq 'Y' && problem.caseState ne '强制关闭' && problem.caseState ne '已完成' && problem.caseState ne '已废弃'&&problemRole.dealProblem eq '1'}">
                        <a href="../problem/preToModifyProblem?rectifyId=${problem.caseId}" target=_blank
                           class="btn btn-primary"><spring:message code="problem.edit"/></a>
                    </c:if>
                </td>
                <td>
                    <c:if test="${function.qch40010044 eq 'Y' && problem.caseState ne '强制关闭' && problem.caseState ne '已废弃'&&problemRole.deleProblem eq '1'}">
                        <a style="cursor: pointer" onClick="deleteQuestion('${problem.caseId}','${problem.planType}')"
                           target=_blank class="btn btn-primary"><spring:message code="problem.del"/></a>
                    </c:if>
                </td>
                <td>
                    <c:if test="${function.qch40010045 eq 'Y' && problem.caseState ne '强制关闭' && problem.caseState ne '草稿' && problem.caseState ne '已完成'&&problem.caseState ne '已废弃' &&problemRole.closeProblem eq '1'}">

                        <a style="cursor: pointer" onClick="forCloseQuestion()" target=_blank
                           class="btn btn-primary"><spring:message code="problem.forceClose"/></a>
                        <%--<a style="cursor: pointer" onClick="forceCloseQuestion('${problem.caseId}','${problem.planType}')" target=_blank class="btn btn-primary"><spring:message code="problem.forceClose" /></a>--%>
                    </c:if>
                </td>
            </div>

        </div>
        <div class="notice_head" style="height: 130px;width: 90%;border-bottom: 1px dashed #ccc;">
            <h2 style="font-size: 18px;text-align: left;height: 40px;font-weight: bold;">正式交房整改单</h2>
            <span class="" style="min-width: 225px;"><spring:message code="problem.createdate"/>：
                <fmt:formatDate type="date" value="${problem.createDate}" dateStyle="default"
                                pattern="yyyy-MM-dd HH:mm:ss"/>
            <%--${problem.createDate}--%>
            </span>

            <span class="ques_sta" style="min-width: 225px;"><spring:message code="problem.status"/>：<span>${problem.caseState}</span></span>

            <c:choose>
                <c:when test="${problem.createBy != null && problem.createBy != '' && problem.createCode != null && problem.createCode != ''}">
                    <span class="record_num" style="min-width: 40%;"><spring:message
                            code="problem.createby"/>：${problem.createBy}(${problem.createCode})</span>
                </c:when>
                <c:otherwise>
                    <span class="record_num" style="min-width: 40%;"><spring:message code="problem.createby"/>：${problem.createByName}</span>
                </c:otherwise>
            </c:choose>
            <span class="phone" style="min-width: 225px;"><spring:message code="problem.repairphone"/>：<span>${problem.createPhone}</span></span>
            <%--problem.senUserName = 派单人--%>
          <%--  <span class="notice_time" style="min-width: 225px;"><spring:message code="problem.senUserName"/>：${problem.senUserName}</span>--%>
            <%--problem.senDate = 派单人时间--%>
           <%-- <span class="ques_sta" style="min-width: 225px;"><spring:message code="problem.senDate"/>：<fmt:formatDate type="date"
                                                                                                                  value="${problem.sendDate}"
                                                                                                                  dateStyle="default"
                                                                                                                  pattern="yyyy-MM-dd HH:mm:ss"/>--%>
            </span>
            <%--problem.dealPeopleName = 处理人--%>
            <%--<div class="repair_per">--%>
            <%--<span class="key1"><spring:message code="problem.dealPeopleName" />：</span>--%>
            <%--<span class="value_">${problem.dealPeopleName}</span>--%>
            <%--</div>--%>
            <%--problem.upcloseName = 填报人--%>
            <span class="" style="min-width: 225px;"><spring:message code="problem.upcloseName"/>：${problem.upcloseName}</span>
            <%--problem.upcloseDate = 填报时间--%>
            <span class="ques_sta" style="min-width: 225px;"><spring:message code="problem.upcloseDate"/>： <fmt:formatDate type="date"
                                                                                                                       value="${problem.updateDate}"
                                                                                                                       dateStyle="default"
                                                                                                                       pattern="yyyy-MM-dd HH:mm:ss"/>
            </span>
        </div>
        <div class="notice_inner" style="padding-bottom: 40px;">
            <%--problem.rectifyId = 整改单ID--%>
            <div class="repair_per" style="margin-left: 0;">
                <span class="key1"><spring:message code="problem.rectifyId"/>：</span>
                <span class="value_">${problem.caseId}</span>
            </div>
            <%--problem.rectifyId = 批次ID--%>
            <div class="repair_per" style="margin-left: 0;">
                <span class="key1"><spring:message code="problem.setId"/>：</span>
                <span class="value_">${problem.setId}</span>
            </div>
            <div class="room">
                <span class="key1">地址：</span>
                <span class="value_">${problem.address}</span>
            </div>
            <div class="room_position">
                <span class="key1"><spring:message code="problem.location"/>：</span>
                <span class="value_">${problem.casePlace}</span>
            </div>
            <div class="common_class">
                <span class="key1">常用分类：</span>
                <span class="levels"></span>
            </div>
            <div class="class1">
                <span class="key1"><spring:message code="problem.fristLevel"/>：</span>
                <span class="levels">${problem.firstTypeName}</span>
            </div>
            <div class="class2">
                <span class="key1"><spring:message code="problem.secondLevel"/>：</span>
                <span class="levels">${problem.secondTyoeName}</span>
            </div>
            <div class="class3">
                <span class="key1"><spring:message code="problem.thirdLevel"/>：</span>
                <span class="levels">${problem.thirdTypeName}</span>
            </div>
            <div class="duration">
                <span class="key1"><spring:message code="problem.limit"/>：</span>
                <span class="value_">
                    <fmt:formatDate type="date" value="${problem.limitTime}" dateStyle="default" pattern="yyyy-MM-dd"/>
                <%--${problem.limitTime}--%>
                </span>

            </div>
            <div class="company1">
                <span class="key1"><spring:message code="problem.supplier"/>：</span>
                <span class="value_">${problem.contractor}</span>

            </div>

          <%--  <div class="description">
                <span class="key1"><spring:message code="problem.problemtype"/>：</span>
                <span class="value_">${problem.caseType}</span>
            </div>--%>
            <c:if test="${fn:length(problem.caseDesc) >= 20}">
                <div class="description" style="height: 55px;">
                    <span class="key1">问题描述：</span>

                    <div class="col-sm-8">
                        <textarea id="description" name="description" class="form-control"
                                  disabled="disabled">${problem.caseDesc}</textarea>
                    </div>
                </div>
            </c:if>
            <c:if test="${fn:length(problem.caseDesc) < 20}">
                <div class="description">
                    <span class="key1">问题描述：</span>
                    <span class="value_">${problem.caseDesc}</span>
                </div>
            </c:if>
            <ul class="gallery">
                <div class="mun_pic">
                    <span class="key1" style="width: 83px;"><spring:message code="problem.image"/>:</span>
                    <c:forEach items="${problem.questionImageList}" var="image">
                        <label class="control-label imgList">
                            <li>
                                <a href="${image.imageUrl}">
                                    <img src="${image.imageUrl}">
                                </a>
                            </li>
                        </label>
                    </c:forEach>
                </div>
            </ul>

            <div class="border1" style="margin-top: 15px;"></div>
            <div class="repair_per">
                <span class="key1"><spring:message code="problem.repairmanager"/>：</span>
                <%--<span class="value_">${problem.modifyBy}   ${problem.repairManager}</span>--%>
                <span class="value_">${problem.repairManager}</span>
            </div>
            <div class="receive_dur">
                <span class="key1">接单时间：</span>
                <span class="value_">
                    <fmt:formatDate type="date" value="${problem.dutyTaskDate}" dateStyle="default"
                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                </span>
            </div>
            <div class="complete_dur">
                <span class="key1"><spring:message code="problem.realitydate"/>：</span>
                <span class="value_">
                    <fmt:formatDate type="date" value="${problem.realityDate}" dateStyle="default"
                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                <%--${problem.realityDate}--%>
                </span>
            </div>
            <div class="repair_process">
                <span class="key1"><spring:message code="problem.rectifydesc"/>：</span>
                <span class="value_">${problem.comments}</span>
            </div>
            <%--problem.createByName = 创建人--%>
            <%--<div class="repair_per">--%>
            <%--<span class="key1"><spring:message code="problem.createByName" />：</span>--%>
            <%--<span class="value_">${problem.createByName}</span>--%>
            <%--</div>--%>
            <ul class="gallery">
                <div class="repaired_pic">
                    <span class="key1"  style=" width: 83px;"><spring:message code="problem.image"/>:</span>
                    <c:forEach items="${problem.rectifyImageList}" var="image">
                        <label class="control-label imgList">
                            <li>
                                <a href="${image.imageUrl}">
                                    <img src="${image.imageUrl}">
                                </a>
                            </li>
                        </label>
                    </c:forEach>
                </div>
            </ul>
            <%-- <div class="right_image">
                 <img src="" alt="">
             </div>--%>
            <div id="locationId">
                <img id="locationImg"/>

                <div class="point"></div>
            </div>
            <c:if test="${problem.caseState eq '强制关闭'}">
                <div class="border1" style="margin-top: 15px;"></div>
                <c:if test="${fn:length(problem.forceClose) >= 20}">
                    <div class="forceClose" style="height: 55px;">
                        <span class="key1"><spring:message code="problem.forceClosese"/>：</span>

                        <div class="col-sm-8">
                            <textarea id="forceClose" name="forceClose" class="form-control"
                                      disabled="disabled">${problem.forceClose}</textarea>
                        </div>
                    </div>
                </c:if>
                <c:if test="${fn:length(problem.forceClose) < 20}">
                    <div class="forceClosese">
                        <span class="key1"><spring:message code="problem.forceClosese"/>：</span>
                        <span class="value_">${problem.forceClose}</span>
                    </div>
                </c:if>
                <div class="forceCloseName">
                    <span class="key1"><spring:message code="problem.forceCloseName"/>：</span>
                    <span class="value_">${problem.forceCloseName}</span>
                </div>
                <div class="forceCloseDate">
                    <span class="key1">关单时间：</span>
                    <span class="value_">
                        <fmt:formatDate type="date" value="${problem.forceCloseDate}" dateStyle="default"
                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                    </span>
                </div>
            </c:if>
        </div>

    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>

</div>
<script src="../../../../static/js/zoom.min.js"></script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script>
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
<!-- main content end-->
<%--<%@ include file="../main/foolter.jsp" %>--%>
<!-- 校验 -->

<script>

    $(function () {

        //简要描述
        var caseType = '${problem.caseType}';
        if (caseType != null && caseType != 'null' && caseType != '') {
            var typeArr = caseType.split(",");
            $.each(typeArr, function (i, item) {
                $("input[name='problemtype'][value=" + item + "]").attr("checked", "true");
            });
        }

        //户型图片的原位标注
        var houseTypeId = '${problem.houseTypeId}';
        var imgUrl = '${problem.houseTypeUrl}';
        $("#locationImg").attr("src", imgUrl);


        var xCoordinates = '${problem.xCoordinates}';
        var yCoordinates = '${problem.yCoordinates}';
        $(".point").css({
            "left": xCoordinates + "%",
            top: yCoordinates + "%"
        });

        $.ajax({
            type: "GET",
            url: "../housetype/getHouseTypeLabelByTypeId",
            cache: false,
            async: false,
            data: "id=" + houseTypeId,
            success: function (data) {
                if (data.code == '0') {
//          alert(data.data.length);
                    var objList = data.data;
                    if (objList != null && objList.length > 0) {
                        $("#locationId").css({"display": "block"});
                        var areaArry = [];
                        for (var i = 0; i < objList.length; i++) {
                            areaArry[i] = {};
                            $("#locationId").append("<div class='positionArea'></div>");
                            areaArry[i].name = objList[i].name;
                            areaArry[i].id = objList[i].locationId;
                            areaArry[i].left = parseFloat(objList[i].xNum1);
                            areaArry[i].top = parseFloat(objList[i].yNum1);
                            areaArry[i].width = parseFloat(objList[i].xNum2) - parseFloat(objList[i].xNum1);
                            areaArry[i].height = parseFloat(objList[i].yNum2) - parseFloat(objList[i].yNum1);

                        }
                        for (var j = 0; j < $(".positionArea").length; j++) {
                            var left = objList[j].xNum1 + "%",
                                    top = objList[j].yNum1 + "%",
                                    width = (parseFloat(objList[j].xNum2) - parseFloat(objList[j].xNum1)) + "%",
                                    height = (parseFloat(objList[j].yNum2) - parseFloat(objList[j].yNum1)) + "%";
                            var area = document.getElementsByClassName("positionArea");
                            area[j].style.width = width;
                            area[j].style.height = height;
                            area[j].style.left = left;
                            area[j].style.top = top;
                            area[j].innerHTML = objList[j].name;
                        }
                        var pointL = xCoordinates;
                        var pointT = yCoordinates;
                        for (var i = 0; i < areaArry.length; i++) {
                            var leftD = pointL - areaArry[i].left,
                                    topD = pointT - areaArry[i].top;
                            if (leftD >= 0 && leftD < areaArry[i].width && topD >= 0 && topD < areaArry[i].height) {
                                $("#locationName").val(areaArry[i].name);
                            }
                        }
                        /* $(".positionArea").click(function(){
                         var click=window.event;
                         var bowerWidth=document.body.clientWidth;
                         var leftPoint=(click.pageX-7-bowerWidth*0.6)*100/500+"%";
                         var topPoint=(click.pageY-7-433)*100/$("#locationImg").height()+"%";
                         $(".point").css({
                         "left":leftPoint,
                         "top":topPoint
                         });
                         var pointL = parseFloat(leftPoint);
                         var pointT = parseFloat(topPoint);
                         for (var i = 0; i < areaArry.length; i++) {
                         var leftD = pointL - areaArry[i].left,
                         topD = pointT - areaArry[i].top;
                         if (leftD >= 0 && leftD < areaArry[i].width && topD >= 0 && topD < areaArry[i].height) {
                         $("#roomLocation").val(areaArry[i].id);
                         $("#locationName").val(areaArry[i].name);
                         }
                         }


                         //    原位标注X、Y、ID值传递

                         $("#xCoordinates").val(leftPoint.substring(0,leftPoint.length-1));
                         $("#yCoordinates").val(topPoint.substring(0,topPoint.length-1));

                         })*/
                    }
                } else {
                    alert("对不起，操作失败");
                }
            }
        });
    });
    function checkSubmit(caseState) {

        var a = "";
        var problemtypes = $("input[name='problemtype']:checked");
        if (problemtypes != null && problemtypes != "") {
            for (var i = 0; i < problemtypes.length; i++) {
                if (i == 0) {
                    a += problemtypes[i].value;
                } else {
                    a = a + "," + problemtypes[i].value;
                }
            }
        }
        $("#caseType").val(a);
        $("#caseState").val(caseState);
        $("#frm").submit();
    }

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

    var fileCount = 1;
    var inputs = $("#f1").get(0);
    var result = document.getElementById("demo_imgFile");
    if (typeof FileReader === 'undefined') {
        result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
        inputs.setAttribute('disabled', 'disabled');
    } else {
        inputs.addEventListener('change', readFile, false);
    }

    function readFile() {
        var beforAdd = fileCount;
        fileCount++;
        for (var i = 0; i < this.files.length; i++) {
            var file = this.files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                result.innerHTML += '<img id="i' + beforAdd + '" onclick="delImg(' + beforAdd + ',"1")" src="' + this.result + '" style="width:100px" alt=""/>';
            }
            file = '<input type="file" id="f' + fileCount + '" name="imgFile" onchange="check(this)" class="form-control" style="width: 300px;">';
            $("#addfileId").append(file);
            var f = $("#f" + fileCount).get(0);
            f.addEventListener('change', readFile, false);
        }
    }
    function delImg(obj, type) {
        if (type == '1') {
            $("#f" + obj).remove();
            $("#i" + obj).remove();
        } else {
            $("#im" + obj).remove();
            $("#ih" + obj).remove();
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

    <c:if test="${problem.caseState ne '草稿' && problem.caseState ne '待接单'}">
    var reviewfileCount = 1;
    var reviewinputs = $("#reviewf1").get(0);
    //    alert(reviewinputs);
    var reviewresult = document.getElementById("reviewDemo_imgFile");
    if (typeof FileReader === 'undefined') {
        reviewresult.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
        reviewinputs.setAttribute('disabled', 'disabled');
    } else {
        reviewinputs.addEventListener('change', readReviewFile, false);
    }

    function readReviewFile() {
        var beforAdd = reviewfileCount;
        reviewfileCount++;
        for (var i = 0; i < this.files.length; i++) {
            var file = this.files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                reviewresult.innerHTML += '<img id="reviewi' + beforAdd + '" onclick="delreviewImg(' + beforAdd + ',"1")" src="' + this.result + '" style="width:100px" alt=""/>';
            }
            file = '<input type="file" id="reviewf' + reviewfileCount + '" name="reviewImgFile" onchange="check(this)" class="form-control" style="width: 300px;">';
            $("#reviewfileId").append(file);
            var f = $("#reviewf" + reviewfileCount).get(0);
            f.addEventListener('change', readReviewFile, false);
        }
    }
    function delreviewImg(obj, type) {
        if (type == "1") {
            $("#reviewf" + obj).remove();
            $("#reviewi" + obj).remove();
        } else {
            $("#reviewim" + obj).remove();
            $("#reviewih" + obj).remove();
        }
    }
    </c:if>

    $("#classifyOne").change(function () {
        var classifyOne = $("#classifyOne").val();
        $.ajax({
            url: "../problem/getSecondTypeList",
            type: "get",
            async: "false",
            data: {"classifyOne": classifyOne},
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
                        document.getElementById("classifyTwo").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#classifyTwo").append(option);
                    }
                }
            }
        });
    });


    $("#classifyTwo").change(function () {
        var classifyTwo = $("#classifyTwo").val();
        $.ajax({
            url: "../problem/getThirdTypeList",
            type: "get",
            async: "false",
            data: {"classifyTwo": classifyTwo},
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
                        document.getElementById("classifyThree").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#classifyThree").append(option);
                    }
                }
            }
        });
    });

    $("#classifyThree").change(function () {
        var classifyThree = $("#classifyThree").val();
        $.ajax({
            url: "../problem/getProblmetype",
            type: "get",
            async: "false",
            data: {"classifyThree": classifyThree},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("problemtypediv").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<input type='checkbox' name='problemtype' id='problemtype' value='" + prop + "'>" + data[prop];
                            }
                        }
                        $("#problemtypediv").append(option);
                    }
                }
            }
        });

        var projectNum = $("#projectNum").val();
        if (projectNum != "" && classifyThree != "") {
            $.ajax({
                url: "../problem/getSupplier",
                type: "get",
                async: "false",
                data: {"classifyThree": classifyThree, "projectNum": projectNum},
                dataType: "json",
                error: function () {
                    alert("网络异常，可能服务器忙，请刷新重试");
                },
                success: function (json) {
                    <!-- 获取返回代码 -->
                    var code = json.code;
                    if (code != 0) {
                        var errorMessage = json.msg;
                    } else {
                        <!-- 获取返回数据 -->
                        var data = json.data;
                        var option = "";
                        if (data != null) {
                            document.getElementById("supplier").innerHTML = "";
                            for (var prop in data) {
                                if (data.hasOwnProperty(prop)) {
                                    option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                }
                            }
                            $("#supplier").append(option);
                        }
                    }
                }
            });
        }
    });


    $("#projectNum").change(function () {
        var projectNum = $("#projectNum").val();
        $("#locationId").css({"display": "none"});
        if ($("#locationId div").hasClass("positionArea")) {
            $(".positionArea").remove();
        }
        ;
        $(".point").css({"display": "none"});
        $.ajax({
            url: "../houseroomtype/getBuildingListByProject",
            type: "get",
            async: "false",
            data: {"projectId": projectNum},
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
                        document.getElementById("buildingNum").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#buildingNum").append(option);
                    }
                }
            }
        });
    });


    $("#buildingNum").change(function () {
        var buildingNum = $("#buildingNum").val();
        $("#locationId").css({"display": "none"});
        if ($("#locationId div").hasClass("positionArea")) {
            $(".positionArea").remove();
        }
        ;
        $(".point").css({"display": "none"});
        $.ajax({
            url: "../houseroomtype/getUnitList",
            type: "get",
            async: "false",
            data: {"buildingId": buildingNum},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("unitNum").innerHTML = "";
                        for (var prop in data) {
                            if (data[prop] == '无单元') {
                                option = "<option value=''>请选择单元</option>";
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            } else {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#unitNum").append(option);
                    }
                }
            }
        });
    });

    $("#unitNum").change(function () {

        var unitNum = $("#unitNum").val();
        $("#locationId").css({"display": "none"});
        if ($("#locationId div").hasClass("positionArea")) {
            $(".positionArea").remove();
        }
        ;
        $(".point").css({"display": "none"});

        $.ajax({
            url: "../houseroomtype/getFloorList",
            type: "get",
            async: "false",
            data: {"unitId": unitNum},
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
                        document.getElementById("floorNum").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#floorNum").append(option);
                    }
                }
            }
        });
    });

    $("#floorNum").change(function () {
        var floor = $("#floorNum").val();
        $("#locationId").css({"display": "none"});
        if ($("#locationId div").hasClass("positionArea")) {
            $(".positionArea").remove();
        }
        ;
        $(".point").css({"display": "none"});
        $.ajax({
            url: "../houseroomtype/getRoomList",
            type: "get",
            async: "false",
            data: {"floor": floor},
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
                        document.getElementById("roomNum").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#roomNum").append(option);
                    }
                }
            }
        });
    });
    $("#roomNum").change(function () {
        var roomNum = $("#roomNum").val();
        $("#locationId").css({"display": "none"});
        if ($("#locationId div").hasClass("positionArea")) {
            $(".positionArea").remove();
        }
        ;
        $(".point").css({"display": "none"});
        var imgUrl = "";
        var houseTypeId = "";
        $.ajax({
            type: "get",
            url: "../problem/getHouseType",
            async: false,
            data: {"roomNum": roomNum},
            dataType: "json",
            success: function (data) {
                if (data.code == '0') {
                    var obj = data.data;
                    if (obj != null) {
                        imgUrl = obj.imgUrl;
                        $("#locationImg").attr("src", imgUrl);
                        houseTypeId = obj.id;
                    } else {
                        alert("该房间尚未设置户型");
                        return;
                    }
                } else {
                    alert("对不起，操作失败");
                    return;
                }
            }
        });

        //户型图片的原位标注
        $.ajax({
            type: "GET",
            url: "../housetype/getHouseTypeLabelByTypeId",
            cache: false,
            async: false,
            data: "id=" + houseTypeId,
            success: function (data) {
                if (data.code == '0') {
                    var objList = data.data;
                    if (objList != null && objList.length > 0) {
                        $("#locationId").css({"display": "block"});
                        var areaArry = [];
                        for (var i = 0; i < objList.length; i++) {
                            areaArry[i] = {};
                            $("#locationId").append("<div class='positionArea'></div>");
                            areaArry[i].name = objList[i].name;
                            areaArry[i].id = objList[i].locationId;
                            areaArry[i].left = parseFloat(objList[i].xNum1);
                            areaArry[i].top = parseFloat(objList[i].yNum1);
                            areaArry[i].width = parseFloat(objList[i].xNum2) - parseFloat(objList[i].xNum1);
                            areaArry[i].height = parseFloat(objList[i].yNum2) - parseFloat(objList[i].yNum1);

                        }
                        for (var j = 0; j < $(".positionArea").length; j++) {
                            var left = objList[j].xNum1 + "%",
                                    top = objList[j].yNum1 + "%",
                                    width = (parseFloat(objList[j].xNum2) - parseFloat(objList[j].xNum1)) + "%",
                                    height = (parseFloat(objList[j].yNum2) - parseFloat(objList[j].yNum1)) + "%";
                            var area = document.getElementsByClassName("positionArea");
                            area[j].style.width = width;
                            area[j].style.height = height;
                            area[j].style.left = left;
                            area[j].style.top = top;
                            area[j].innerHTML = objList[j].name;
                        }
//                        $("#locationImg").click(function(){
//                            alert("请在阴影区域点击新增问题！");
//                        })
                        $(".positionArea").click(function () {
                            var click = window.event;
                            var bowerWidth = document.body.clientWidth;
                            var scroll = $("#locationImg").offset().top;
                            var leftPoint = (click.pageX - 7 - bowerWidth * 0.6) * 100 / 500 + "%";
                            var topPoint = (click.pageY - 7 - scroll) * 100 / $("#locationImg").height() + "%";
                            $(".point").css({
                                "left": leftPoint,
                                "top": topPoint,
                                "display": "block"
                            });
                            var pointL = parseFloat(leftPoint);
                            var pointT = parseFloat(topPoint);
                            for (var i = 0; i < areaArry.length; i++) {
                                var leftD = pointL - areaArry[i].left,
                                        topD = pointT - areaArry[i].top;
                                if (leftD >= 0 && leftD < areaArry[i].width && topD >= 0 && topD < areaArry[i].height) {
                                    $("#roomLocation").val(areaArry[i].id);
                                    $("#locationName").val(areaArry[i].name);
                                }
                                ;
                            }


                            //    原位标注X、Y、ID值传递

                            $("#xCoordinates").val(leftPoint.substring(0, leftPoint.length - 1));
                            $("#yCoordinates").val(topPoint.substring(0, topPoint.length - 1));

                        })

                    }
                } else {
                    alert("对不起，操作失败");
                }
            }

        });

    });
</script>
</body>
</html>