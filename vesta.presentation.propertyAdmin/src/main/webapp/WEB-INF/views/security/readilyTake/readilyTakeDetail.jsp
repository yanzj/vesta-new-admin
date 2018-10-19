<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    <link rel="stylesheet" href="../static/css/detailsCss.css"/>

    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--animate-->
    <%--problemDetail  css style--%>
    <link rel="stylesheet" href="../static/css/detailsCss.css"/>
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        $(function () {
            console.log("sqq")
            $("#006400010000").addClass("active");
            $("#006400010000").parent().parent().addClass("in");
            $("#006400010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <%--图片放大--%>
    <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>
    <%----%>
    <style>
        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 70px;
        }
    </style>
    <STYLE type=text/css>
        .newDetail__ .notice_test>div{
            padding: 10px 0 0;
        }
        .newDetail__ .notice_test>div span{
           font-size: 16px;
        }
        .newDetail__ .notice_test .imgList img{
            width: 90px;
            height: 90px;
            margin-right: 5px;
        }
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

        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 70px;
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

        .gallery {
            list-style: none;
            padding: 10px 0 0;
        }
        .gallery span{
            font-size: 16px;
        }
    </STYLE>
    <script type="application/javascript">
        function deleteQuestion(patId) {
            var str = $("#closes").val();
            if (str) {
                window.location.href = "../readilyTake/updateReadilyTake?patId=" + patId + "&supplementaryDescription=" + str + "&state=discard";
            } else {
                alert("作废原因不能为空！！");
                forCloseQuestion();
            }
        }
        function forQualified(patId) {
            if (window.confirm("设置合格后不能修改，您确定么？")) {
                window.location.href = "../readilyTake/updateReadilyTake?patId=" + patId + "&state=finshed";
            }
        }
        function forUnQualified(patId) {
            if (window.confirm("设置不合格后该单要重新整改，您确定么？")) {
                window.location.href = "../readilyTake/updateReadilyTake?patId=" + patId + "&state=twiceReform";
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
                <h4 class="modal-title" id="myModalLabel">废弃原因</h4>
            </div>
            <div class="modal-body" style="width: 500px;">
                <table>
                    <tr>
                        <td style="width: 200px;">
                            <%-- 废弃原因 --%>
                            <label class="" for="closes">请输入废弃原因 ：</label>
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
                        onclick="deleteQuestion('${readilyTake.patId}')">
                    <spring:message code="project.confirm"/></button>
            </div>
        </div>
    </div>
</div>
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006400010000" username="${authPropertystaff.staffName}"/>
    <div class="newDetail__">
        <div class="nav">
            <div class="rightButton">
                <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
                <td>
                    <%--从整改中转到待确认的状态--%>
                    <c:if test="${readilyTake.state ne 'finshed' && readilyTake.state ne 'discard' && readilyTake.state ne 'waiting' && role.dealProblem eq '1'}">
                        <a href="../readilyTake/rectifyReadilyTake.html?patId=${readilyTake.patId}"
                           class="btn btn-primary">整改</a>
                    </c:if>
                </td>
                <td>
                    <%--待确认的状态  合格or不合格--%>
                    <c:if test="${readilyTake.state ne 'reform' && readilyTake.state ne 'discard' && readilyTake.state ne 'twiceReform' && readilyTake.state ne 'finshed' && role.affirmProblem eq '1'}">
                        <a onClick="forQualified('${readilyTake.patId}')" target=_blank
                           class="btn btn-primary">合格</a>
                        <a onClick="forUnQualified('${readilyTake.patId}')" target=_blank
                           class="btn btn-primary">不合格</a>
                    </c:if>
                </td>
                <td>
                    <%--作废--%>
                    <c:if test="${readilyTake.state ne 'discard'  && role.deleProblem eq '1'}">
                        <a style="cursor: pointer" onClick="forCloseQuestion()"
                           target=_blank class="btn btn-primary"><spring:message code="problem.del"/></a>
                    </c:if>
                </td>
            </div>
        </div>
        <div class="notice_head" style="height: 80px;width: 90%;border-bottom: 1px dashed #ccc;">
            <h2 style="font-size: 18px;text-align: left;height: 40px;font-weight: bold;">随手拍整改单</h2>
            <span class="notice_time" style="width: 225px;"><spring:message code="problem.createdate"/>：
                <%--<fmt:formatDate type="date" value="${readilyTake.createDate}" dateStyle="default"--%>
                                <%--pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                <span>${readilyTake.createDate}</span>
            </span>

            <span class="ques_sta " style="width: 225px;"><spring:message code="problem.status"/>：
                <span>
                    <c:if test="${readilyTake.state eq 'reform'}">整改中</c:if>
                    <c:if test="${readilyTake.state eq 'twiceReform'}">二次整改</c:if>
                    <c:if test="${readilyTake.state eq 'finshed'}">已完成</c:if>
                    <c:if test="${readilyTake.state eq 'waiting'}">待确认</c:if>
                    <c:if test="${readilyTake.state eq 'discard'}">已废弃</c:if>
                </span>
            </span>
            <span class="key1 notice_time " style="width: 225px;"><spring:message code="problem.createby"/>：${readilyTake.createName}</span>

        </div>
        <div class="notice_inner notice_test" style="padding-bottom: 40px;">
            <div class="room">
                <span class="key1">项目公司：</span>
                <span class="value_">${readilyTake.projectName}</span>
            </div>
            <div class="room">
                <span class="key1">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</span>
                <span class="value_">${readilyTake.address}</span>
            </div>
            <div class="room_position">
                <span class="key1">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</span>
                <span class="value_">${readilyTake.examinationParts}</span>
            </div>
            <div class="duration">
                <span class="key1">严重等级：</span>
                <span class="value_">${readilyTake.severityLevel}</span>
            </div>

            <c:if test="${fn:length(readilyTake.description) >= 20}">
                <div class="description" style="height: 55px;">
                    <span class="key1">问题描述：</span>

                    <div class="col-sm-8">
                        <textarea id="description" name="description" class="form-control"
                                  disabled="disabled">${readilyTake.description}</textarea>
                    </div>
                </div>
            </c:if>
            <c:if test="${fn:length(readilyTake.description) < 20}">
                <div class="description">
                    <span class="key1">问题描述：</span>
                    <span class="value_">${readilyTake.description}</span>
                </div>
            </c:if>

            <div class="mun_pic">
                <span class="key1" style="width: 90px;">照&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片：</span>
                <ul class="gallery">
                    <c:forEach items="${readilyTake.imgList}" var="image">
                        <label class="control-label imgList">
                            <li>
                                <a href="${image.imageUrl}">
                                    <img src="${image.imageUrl}">
                                </a>
                            </li>
                        </label>
                    </c:forEach>
                </ul>
            </div>
            <div class="border1" style="margin-top: 15px;"></div>
            <div class="complete_dur">
                <span class="key1">整&nbsp; 改&nbsp; 人：</span>
                <span class="value_">${readilyTake.rectificationPeople}</span>

            </div>
            <div class="complete_dur">
                <span class="key1"><spring:message code="problem.realitydate"/>：</span>
                <span class="value_">${readilyTake.rectificationDate}</span>
            </div>
            <ul class="gallery">
                <div class="repaired_pic">
                    <span class="key1"><spring:message code="problem.rectifydesc"/>：</span><br/>
                    <c:forEach items="${readilyTake.rectificationDescriptionList}" var="description"  varStatus="status" >
                        <div class="border1" style="margin-top: 5px; width: 500px;"></div>
                        <span class="key1" style=" width: 160px;font-weight:bold">第${status.index+1}次整改描述：</span>
                        <span class="value_" >${description.rectificationDescription}</span><br/>
                        <span class="key1" style=" width: 160px;font-weight:bold"><spring:message code="problem.image"/>：</span>
                        <c:forEach items="${description.imageList}" var="image">
                            <label class="control-label imgList">
                                <li>
                                    <a href="${image.imageUrl}">
                                        <img src="${image.imageUrl}">
                                    </a>
                                </li>
                            </label>
                        </c:forEach><br/>
                    </c:forEach>
                </div>
            </ul>
            <c:if test="${readilyTake.state eq 'discard'}">
                <div class="border1" style="margin-top: 15px;"></div>
                <%--问题描述--%>
                <c:if test="${fn:length(readilyTake.supplementaryDescription) >= 20}">
                    <div class="forceClose" style="height: 55px;">
                        <span class="key1">废弃原因：</span>

                        <div class="col-sm-8">
                            <textarea id="forceClose" name="forceClose" class="form-control"
                                      disabled="disabled">${readilyTake.supplementaryDescription}</textarea>
                        </div>
                    </div>
                </c:if>
                <c:if test="${fn:length(readilyTake.supplementaryDescription) < 20}">
                    <div class="forceClosese">
                        <span class="key1">废弃原因：</span>
                        <span class="value_">${readilyTake.supplementaryDescription}</span>
                    </div>
                </c:if>
                <div class="forceCloseDate">
                    <span class="key1">废弃时间：</span>
                    <span class="value_">${readilyTake.modifyDate}</span>
                </div>
            </c:if>

        </div>

    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
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
    });

</script>
</body>
</html>