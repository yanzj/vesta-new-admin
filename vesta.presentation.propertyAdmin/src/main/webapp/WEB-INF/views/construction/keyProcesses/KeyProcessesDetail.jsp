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
    <link href="../../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="../../../../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- Custom CSS -->
    <link href="../../../../static/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="../../../../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../../../../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="../../../../static/js/jquery-1.11.1.min.js"></script>
    <script src="../../../../static/js/modernizr.custom.js"></script>

    <script type="text/javascript" src="../../../../static/plus/js/jquery.validate.js"></script>
    <link rel="stylesheet" href="../../../../static/css/detailsCss.css"/>
    <!--animate-->
    <link href="../../../../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../../../../static/js/wow.min.js"></script>
    <script>
        $(function () {
            $("#002800010000").addClass("active");
            $("#002800010000").parent().parent().addClass("in");
            $("#002800010000").parent().parent().parent().parent().addClass("active");
        });
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../../../../static/js/metisMenu.min.js"></script>
    <script src="../../../../static/js/custom.js"></script>
    <link href="../../../../static/css/custom.css" rel="stylesheet">
    <link href="../../../../static/css/target.css" rel="stylesheet">
    <!--//Metis Menu -->
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="002800010000" username="${authPropertystaff.staffName}"/>
    <div class="newDetail__">
        <div class="nav">
            <div class="rightButton">
                <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
                <c:if test="${keyProcesses.state ne '合格' && keyProcesses.state ne '非正常关闭' && checkAuthFunction.esh40020100 eq 'Y'}">
                    <button data-toggle="modal1" class="btn btn-primary"
                            onclick="closeQuestionModel()">非正常关闭
                    </button>
                </c:if>
                <%--<c:choose>--%>
                    <%--<c:when test="${authority == false || keyProcesses.state eq '非正常关闭' || keyProcesses.state eq '合格'}">--%>
                        <%--<button class="btn btn-primary"--%>
                                <%--onclick="closeQuestion('${keyProcesses.processId}','${keyProcesses.projectId}')"--%>
                                <%--style="display: none">非正常关闭--%>
                        <%--</button>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<button data-toggle="modal1" class="btn btn-primary"--%>
                                <%--onclick="closeQuestionModel()">非正常关闭--%>
                        <%--</button>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
            </div>
        </div>
        <div class="notice_inner" style="padding: 0;margin:0;width: auto">
            <h2 class="keyComTitle" style="width: 90%;">${keyProcesses.processName}</h2>

            <div class="container"  style=" width: 90%;padding: 0;height: 100%;margin: 0;">
                <div class="col-lg-12" style="padding-left: 0;margin-left: -30px;">
                    <div class="repair_per col-sm-4">
                        <span class="key1">创建人：</span>
                        <span class="value_">${keyProcesses.createBy}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">创建时间：</span>
                        <span class="value_">${keyProcesses.createDate}</span>
                        <%--<fmt:formatDate type="date" value="${keyProcesses.createDate}" dateStyle="default"--%>
                        <%--pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">问题状态：</span>
                        <span class="value_">${keyProcesses.state}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">所属项目：</span>
                        <span class="value_">${keyProcesses.projectName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">检查项：</span>
                        <span class="value_">${keyProcesses.fourSortName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">严重等级：</span>
                        <span class="levels">${keyProcesses.severityRating}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">检查楼栋：</span>
                        <span class="value_">${keyProcesses.buildingName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">楼层区间：</span>
                        <span class="levels">${keyProcesses.floorStar}~${keyProcesses.floorEnd}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">流水段：</span>
                        <span class="levels">${keyProcesses.serial}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">检查时间：</span>
                        <span class="levels">${keyProcesses.examinationDate}</span>
                        <%--<fmt:formatDate type="date" value="${keyProcesses.examinationDate}" dateStyle="default"--%>
                        <%--pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">甲方负责人：</span>
                        <span class="value_">${keyProcesses.partyPrincipalName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">第三方监理：</span>
                        <span class="value_">${keyProcesses.supervisorName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">责任单位：</span>
                        <span class="value_">
                            ${keyProcesses.supplierName}
                        </span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">整改人：</span>
                        <span class="value_">
                            ${keyProcesses.assignName}
                        </span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">完成期限：</span>
                        <span class="value_">
                            ${keyProcesses.completeOn}
                            <%--<fmt:formatDate type="date" value="${keyProcesses.completeOn}" dateStyle="default"--%>
                                            <%--pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                        </span>
                    </div>
                    <div class="repair_per col-sm-9">
                        <span class="key1">抄送人：</span>
                        <%--<span class="value_" id="copyName"></span>--%>
                        <c:forEach items="${keyProcesses.copyDTOList}" var="item" varStatus="index">
                            <span class="value_">
                            ${item.name}
                                <c:if test="${index.last==false}">
                                    ,
                                </c:if>
                            </span>
                        </c:forEach>
                    </div>
                    <c:if test="${keyProcesses.state eq '非正常关闭'}">
                        <div class="repair_per col-sm-4">
                            <span class="key1">关单时间：</span>
                            <span class="value_">${keyProcesses.modifyDate}</span>
                        </div>

                        <div class="repair_per col-sm-4">
                            <span class="key1">关单人：</span>
                            <span class="value_">${keyProcesses.modifyName}</span>
                        </div>
                        <div class="repair_per col-sm-12">
                            <span class="key1">关单描述：</span>
                            <span class="value_">${keyProcesses.abnormalShutdown}</span>
                        </div>
                    </c:if>
                </div>
                <div></div>
                <ul class="gallery">
                    <div class="col-lg-12" style="border-top: 1px dashed #ccc;margin: 30px 0;padding-left: 0;">
                        <c:forEach items="${keyProcesses.targetDTOList}" var="item" varStatus="status">
                            <div class="col-lg-12" style="padding-left: 0;">
                                <div class="repair_per col-sm-6 targetHeader" style="padding-left: -60px;font-size: 16px;">
                                    <span class="key1 span" style="margin-top: 10px;">检查指标：</span>
                                </div>
                                <c:choose>
                                    <c:when test="${item.targetDTOByPartyBAnnalList.size() > 0 || item.targetDTOBySupervisionAnnalList.size() >0 || item.targetDTOByPartyAAnnalList.size() >0}">
                                        <div class="repair_per col-sm-6">
                                            <p class="details" id="${status.index+1}"
                                               onclick="showDiv('targetDes${status.index+1}')" style="font-size: 16px;">详情</p>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="details none" id="${status.index+1}" onclick="" style="font-size: 16px;">详情</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-sm-12">
                                <div class="team-member">
                                    <div class="col-sm-4 target" style="margin-left: -70px">
                                        <h4 class="keyPress" style=" width: 1000px;">${item.targetName}</h4>
                                        <c:choose>
                                            <c:when test="${item.targetImgUrl != null && item.targetImgUrl !=''}">
                                                <li style="margin-top: -10px !important;">
                                                    <a href="${item.targetImgUrl}">
                                                        <span class="imgSpan"
                                                              style="background:url(${item.targetImgUrl}) no-repeat center center;background-size:cover;"></span>
                                                    </a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="imgSpan"></span>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                        <%--<c:if test="${item.qualifiedState!=null&&item.qualifiedState!='' || item.description != null && item.description != ''}">--%>
                                    <div class="col-sm-6 " style="margin-top: 35px">
                                        <p class="text-mutedKey2 status">${item.qualifiedState}</p>

                                        <p class="text-mutedKey">${item.description}</p>
                                    </div>
                                        <%--</c:if>--%>
                                    <div class="col-sm-2" style="float: right;margin-right: 15px;margin-top: 10px;
}">
                                        <input id="des${status.index+1}" type="hidden"
                                               value="${item.targetDescription}">

                                        <p id="${status.index+1}" style="cursor: pointer;color: #0044cc"
                                           onclick="javascript:getDetail('${item.targetName}','des${status.index+1}');"
                                           data-toggle="modal">指引</p>
                                            <%--<p id="${status.index+1}" style="cursor: pointer;color: #0044cc"--%>
                                            <%--onclick="getDetail('${item.targetName}','${item.targetDescription}')"--%>
                                            <%--data-toggle="modal">指引</p>--%>
                                    </div>
                                </div>
                            </div>
                            <div id="targetDes${status.index+1}" class="col-sm-12 annal none"
                                 style="margin-bottom: 15px">
                                <button type="button" class="close" data-dismiss="modal"
                                        onclick="showDiv('targetDes${status.index+1}')">
                                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <div class="col-sm-4 annalDes" style="width: 255px">
                                    <c:forEach items="${item.targetDTOByPartyBAnnalList}" var="partyBAnnalItem"
                                               varStatus="partyBAnnalState">
                                        <div class="col-lg-12">
                                            <div class="repair_per col-sm-6 header">
                                                <span class="key1 span"><b>第${partyBAnnalItem.qualifiedState}次整改-乙方：</b></span>
                                            </div>
                                        </div>
                                        <div class="team-member detail">
                                            <div class="col-sm-12 detailsList">
                                                <div class="col-sm-2 targetImg">
                                                    <c:choose>
                                                        <c:when test="${partyBAnnalItem.targetImgUrl != null && partyBAnnalItem.targetImgUrl != ''}">
                                                            <li >
                                                                <a href="${partyBAnnalItem.targetImgUrl}">
                                                                        <%--<img src="${item.targetImgUrl}">--%>
                                                            <span class="imgSpan"
                                                                  style="background:url(${partyBAnnalItem.targetImgUrl}) no-repeat center center;background-size:cover;"></span>
                                                                </a>
                                                            </li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="imgSpan"></span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="col-sm-2 des description" style="width: 120px">
                                                    <p>${partyBAnnalItem.description}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="col-sm-8 annalDes" style="margin-left: 20px">
                                    <c:forEach items="${item.targetDTOBySupervisionAnnalList}"
                                               var="supervisionAnnalItem"
                                               varStatus="supervisionAnnalState">
                                        <div class="col-sm-4" style="width: 270px">
                                            <div class="col-lg-12">
                                                <div class="repair_per col-sm-6 header">
                                                    <span class="key1 span"><b>第${supervisionAnnalItem.qualifiedState}次审核-监理：</b></span>
                                                </div>
                                            </div>
                                            <div class="team-member detail">
                                                <div class="col-sm-12 detailsList">
                                                    <div class="col-sm-2 targetImg">
                                                        <c:choose>
                                                            <c:when test="${supervisionAnnalItem.targetImgUrl != null && supervisionAnnalItem.targetImgUrl != ''}">
                                                                <li>
                                                                    <a href="${supervisionAnnalItem.targetImgUrl}">
                                                                            <%--<img src="${item.targetImgUrl}">--%>
                                                            <span class="imgSpan"
                                                                  style="background:url(${supervisionAnnalItem.targetImgUrl}) no-repeat center center;background-size:cover;"></span>
                                                                    </a>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="imgSpan"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                    <div class="col-sm-2 des description" style="width: 120px">
                                                        <p>${supervisionAnnalItem.description}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <c:forEach items="${item.targetDTOByPartyAAnnalList}"
                                                   var="partyAAnnalItem"
                                                   varStatus="partyAAnnalState">
                                            <c:choose>
                                                <c:when test="${supervisionAnnalItem.qualifiedState eq partyAAnnalItem.qualifiedState}">
                                                    <div class="col-sm-4" style="margin-right: -1px;width: 325px;">
                                                        <div class="col-lg-12">
                                                            <div class="repair_per col-sm-6 header">
                                                                <span class="key1 span"><b>第${partyAAnnalItem.qualifiedState}次审核-甲方：</b></span>
                                                            </div>
                                                            <div class="team-member detail">
                                                                <div class="col-sm-12 detailsList" style="width: 335px">
                                                                    <div class="col-sm-2 targetImg">
                                                                        <c:choose>
                                                                            <c:when test="${partyAAnnalItem.targetImgUrl != null && partyAAnnalItem.targetImgUrl != ''}">
                                                                                <li>
                                                                                    <a href="${partyAAnnalItem.targetImgUrl}">
                                                            <span class="imgSpan"
                                                                  style="background:url(${partyAAnnalItem.targetImgUrl}) no-repeat center center;background-size:cover;"></span>
                                                                                    </a>
                                                                                </li>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <span class="imgSpan"></span>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </div>
                                                                    <div class="col-sm-2 des description"
                                                                         style="width: 120px">
                                                                            <%--<p class="text-muted status">${annalState.isQualifiedForTarget}</p>--%>
                                                                        <p>${partyAAnnalItem.description}</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="col-sm-4" style="margin-right: -1px;width: 330px;">
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </ul>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
    <%--指引模态框--%>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="top: 70px">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                </div>
                <div class="modal-body" id="mo-content">

                </div>
            </div>
        </div>
    </div>
    <!-- 非正常关闭模态框（Modal） -->
    <div class="modal fade " id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel1">
                        <spring:message code="problem.forceClosese"/></h4>
                </div>
                <div class="modal-body">
                    <table>
                        <tr>
                            <td>
                                <label class="" for="closes">
                                    <spring:message code="problem.closess"/> ：
                                </label>
                            </td>
                            <td style="width: 88%;">
                                <input type="text" class="form-control" placeholder="" id="closes" name="closes"
                                       value="">
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
                            onclick="closeQuestion('${keyProcesses.processId}','${keyProcesses.projectId}')">
                        <spring:message code="project.confirm"/></button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../../../static/js/zoom.min.js"></script>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script type="text/javascript" src="../../../../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../../../../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
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
<script>
    function getDetail(titel, tid) {
        var desc = $('#' + tid).val();
        $("#myModalLabel").html(titel);
        $("#mo-content").html(desc);
        $("#myModal").modal({
            backdrop: false,
            show: true
        });
    }
    function closeQuestionModel() {
//        $("#myModalLabel").html(titel);
//        $("#mo-content").html(description);
        $("#myModal1").modal({
            backdrop: false,
            show: true
        });
    }
    function closeQuestion(processId, projectId) {
        var closes = $("#closes").val();
        if (closes != null && closes != "") {
            window.location.href = "../projectKeyProcesses/executeAbnormalOffState?processId=" + processId + "&abnormalShutdown=" + closes;
        } else {
            alert("关单原因不能为空！");
            return false;
        }
//        var judge = confirm("系统提示：您确定要关闭此条记录吗？");
//        if (judge == true) {
//            if (closes != null && closes != "") {
//                window.location.href = "../projectKeyProcesses/executeAbnormalOffState?processId=" + processId + "&abnormalShutdown=" + closes;
//            } else {
//                alert("关单原因不能为空！");
//                return false;
//            }
//        } else {
//            return false;
//        }
//            $.ajax({
//                type: "GET",
//                url: "../projectKeyProcesses/searchAuthorityByStaffId?projectId="+projectId,
//                cache: false,
//                async: false,
//                dataType: "json",
//                success: function (data) {
//                    if (data.data.success) {
//                        var judge = confirm("系统提示：" + data.data.success);
//                        if (judge == true) {
//                            window.location.href = "../projectKeyProcesses/executeAbnormalOffState?processId=" + processId;
//                        } else {
//                            return;
//                        }
//                    } else if (data.data.error) {
//                        var judge = confirm("系统提示：" + data.data.error);
//                        if (judge == true) {
//                            return;
//                        } else {
//                            return;
//                        }
//                    } else {
//                        alert("对不起，操作失败！");
//                        return;
//                    }
//                }
//            });
    }
    function showDiv(targetid) {
        var target = document.getElementById(targetid);
        if (target.style.display == "block") {
            target.style.display = "none";
        } else {
            target.style.display = "block";
        }
    }
</script>
</body>
</html>