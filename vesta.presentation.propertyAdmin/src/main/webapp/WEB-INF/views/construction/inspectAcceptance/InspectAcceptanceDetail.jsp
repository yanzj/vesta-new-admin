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
    <script type="text/javascript" charset="utf-8" src="../../../../static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../../../../static/editer/ueditor.all.min.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="../../../../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="../../../../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- Custom CSS -->
    <link href="../../../../static/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="../../../../static/css/page/page.css" rel='stylesheet' type='text/css'/>

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
    <%--zoom start--%>
    <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>

    <%--zoom end--%>
    <script>
        $(function () {
            console.log("sqq")
            $("#002600010000").addClass("active");
            $("#002600010000").parent().parent().addClass("in");
            $("#002600010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../../../../static/js/metisMenu.min.js"></script>
    <script src="../../../../static/js/custom.js"></script>
    <link href="../../../../static/css/custom.css" rel="stylesheet">
    <link href="../../../../static/css/target.css" rel="stylesheet">
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="002600010000" username="${authPropertystaff.staffName}"/>
    <div class="newDetail__">
        <div class="nav">
            <div class="rightButton">
                <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
                <c:if test="${acceptance.state ne '合格' && acceptance.state ne '非正常关闭' && checkAuthFunction.esh40020098 eq 'Y'}">
                    <button data-toggle="modal1" class="btn btn-primary"
                            onclick="closeQuestionModel()">非正常关闭
                    </button>
                </c:if>
                <%--<c:choose>--%>
                    <%--<c:when test="${authority == false || acceptance.state eq '非正常关闭' || acceptance.state eq '合格'}">--%>
                        <%--<button class="btn btn-primary none"--%>
                                <%--onclick="closeQuestionModel()">--%>
                            <%--非正常关闭--%>
                        <%--</button>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<button class="btn btn-primary"--%>
                                <%--onclick="closeQuestionModel()">非正常关闭--%>
                        <%--</button>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
            </div>
        </div>

        <div class="notice_inner" style="padding: 0;margin:0;width: auto">
            <h2 class="keyComTitle" style="width: 90%;">${acceptance.batchName}</h2>

            <div class="container" style=" width: 90%;padding: 0;height: 100%;margin: 0;">
                <div class="col-lg-12" style="padding-left: 0; margin-left: -1.6%;">
                    <div class="repair_per col-sm-4">
                        <span class="key1">创建人：</span>
                        <span class="value_">${acceptance.creatBy}</span>
                    </div>

                    <div class="repair_per col-sm-4">
                        <span class="key1">创建时间：</span>
                        <span class="value_">
                        <fmt:formatDate type="date" value="${acceptance.creatDate}" dateStyle="default"
                                        pattern="yyyy-MM-dd HH:mm:ss"/></span>
                    </div>

                    <div class="repair_per col-sm-4">
                        <span class="key1">问题状态：</span>
                        <span class="value_">${acceptance.state}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">所属项目：</span>
                        <span class="value_">${acceptance.projectName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">检查项：</span>
                        <span class="value_">${acceptance.categoryName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">严重等级：</span>
                        <span class="levels">${acceptance.severityRating}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">检查楼栋：</span>
                        <span class="value_">${acceptance.buildingName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">楼层区间：</span>
                        <span class="levels">${acceptance.floorStar}~${acceptance.floorEnd}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">流水段：</span>
                        <span class="levels">${acceptance.serial}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">责任单位：</span>
                        <span class="value_">
                            ${acceptance.supplierName}
                        </span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">整改人：</span>
                        <span class="value_">
                            ${acceptance.assignnName}
                        </span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">第三监理：</span>
                        <span class="value_">${acceptance.supervisorName}</span>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">检查时间：</span>
                        <fmt:formatDate type="date" value="${acceptance.checkTime}" dateStyle="default"
                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                    </div>
                    <div class="repair_per col-sm-4">
                        <span class="key1">完成期限：</span>
                        <span class="value_">
                            <fmt:formatDate type="date" value="${acceptance.completeOn}" dateStyle="default"
                                            pattern="yyyy-MM-dd"/>
                        </span>
                    </div>
                    <div class="repair_per col-sm-9">
                        <span class="key1">抄送人：</span>
                        <c:forEach items="${acceptance.copyDetailsEntities}" var="item" varStatus="index">
                            <span class="value_">${item.name}
                                <c:if test="${index.last==false}">
                                    ,
                                </c:if>
                            </span>
                        </c:forEach>
                    </div>
                    <c:if test="${ acceptance.state eq '非正常关闭'}">
                        <div class="repair_per col-sm-4">
                            <span class="key1">关单时间：</span>
                            <span class="value_">${acceptance.modifyDate}</span>
                        </div>
                        <div class="repair_per col-sm-4">
                            <span class="key1">关单人：</span>
                            <span class="value_">${acceptance.modifyByName}</span>
                        </div>
                        <div class="repair_per col-sm-12">
                            <span class="key1">关单描述：</span>
                            <span class="value_">${acceptance.abnormalShutdown}</span>
                        </div>
                    </c:if>
                </div>
                <ul class="gallery">
                    <div class="col-lg-12" style="border-top: 1px dashed #ccc;margin: 30px 0;">
                        <c:forEach items="${acceptance.projectTargetDTOList}" var="item" varStatus="status">
                            <div class="col-lg-12" style="padding-left: 0;
margin-left: -30px;">
                                <div class="repair_per col-sm-6 targetHeader" style="font-size: 16px;;">
                                    <span class="key1" style="margin-top: 10px;">检查指标：</span>
                                </div>
                                <c:choose>
                                    <c:when test="${item.projectTargetChangeDTOList.size() > 0 || item.projectTargetAcceptanceDTOList.size() >0}">
                                        <div class="repair_per col-sm-6">
                                            <p class="details" id="${status.index+1}"
                                               onclick="showDiv('targetDes${status.index+1}')" style="font-size: 16px;margin-left: 20px;">详情</p>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="details none" id="${status.index+1}" onclick="" style="font-size: 16px;margin-left: 20px;">详情</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-sm-12" style="margin-left: -30px;">
                                <div class="team-member" style="margin-left: 0;">
                                    <div class="col-sm-4 target">
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
                                    <div class="col-sm-6 " style="margin-top: 35px;width: 550px;">
                                        <p class="text-mutedKey2 status">${item.isQualifiedForTarget}</p>
                                            <%--<div class="border1"></div>--%>
                                        <p class="text-mutedKey">${item.description}</p>
                                    </div>
                                    <div class="col-sm-3">
                                        <input id="des${status.index+1}" type="hidden" value="${item.targetDescripion}">

                                        <p id="${status.index+1}" style="cursor: pointer;color: #0044cc;float: right;margin-right: 30px;margin-top: 12px;"
                                           onclick="javascript:getDetail('${item.targetName}','des${status.index+1}');"
                                           data-toggle="modal">指引</p>
                                    </div>
                                </div>
                            </div>
                            <div id="targetDes${status.index+1}" class="col-sm-12 annal none">
                                <button type="button" class="close" data-dismiss="modal"
                                        onclick="showDiv('targetDes${status.index+1}')">
                                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <div class="col-sm-6">
                                    <c:forEach items="${item.projectTargetChangeDTOList}" var="changeItem"
                                               varStatus="changeState">
                                        <div class="col-lg-12">
                                            <div class="repair_per col-sm-6" style="margin-left: -16px">
                                                <span class="key1"><b>整改记录${changeState.index+1}：</b></span>
                                            </div>
                                        </div>
                                        <div class="team-member">
                                            <div class="col-sm-12">
                                                    <%--<h4>${item.targetName}</h4>--%>
                                                <div class="col-sm-2 targetImg" style="margin-left: -16px">
                                                    <c:choose>
                                                        <c:when test="${changeItem.targetImgUrl != null && changeItem.targetImgUrl != ''}">
                                                            <li>
                                                                <a href="${changeItem.targetImgUrl}">
                                                                        <%--<img src="${item.targetImgUrl}">--%>
                                                            <span class="imgSpan"
                                                                  style="background:url(${changeItem.targetImgUrl}) no-repeat center center;background-size:cover;"></span>
                                                                </a>
                                                            </li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="imgSpan"></span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="col-sm-2 des" style="width: 200px">
                                                        <%--<p class="text-muted status">${changeItem.isQualifiedForTarget}</p>--%>

                                                    <p>${changeItem.description}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="col-sm-6">
                                    <c:forEach items="${item.projectTargetAcceptanceDTOList}" var="acceptanceItem"
                                               varStatus="acceptanceState">
                                        <div class="col-lg-12">
                                            <div class="repair_per col-sm-6" style="margin-left: -16px">
                                                <span class="key1"><b>验收记录${acceptanceState.index+1}：</b></span>
                                            </div>
                                        </div>
                                        <div class="team-member">
                                                <%--<h4>${item.targetName}</h4>--%>
                                            <div class="col-sm-12">
                                                <div class="col-sm-2 targetImg" style="margin-left: -16px">
                                                    <c:choose>
                                                        <c:when test="${acceptanceItem.targetImgUrl != null && acceptanceItem.targetImgUrl !=''}">
                                                            <li>
                                                                <a href="${acceptanceItem.targetImgUrl}">
                                                                <span class="imgSpan"
                                                                      style="background:url(${acceptanceItem.targetImgUrl}) no-repeat center center;background-size:cover;"></span>
                                                                </a>
                                                            </li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="imgSpan"></span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="col-sm-2 des">
                                                        <%--<p class="text-muted status">${acceptanceItem.isQualifiedForTarget}</p>--%>

                                                    <p>${acceptanceItem.description}</p>
                                                </div>
                                            </div>
                                        </div>
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
                            onclick="closeQuestion('${acceptance.batchId}','${acceptance.projectId}')">
                        <spring:message code="project.confirm"/></button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- main content end-->
</div>
<%@ include file="../../main/foolter.jsp" %>
</div>
<%--<script type="text/javascript" src="../../../../static/js/jquery-1.9.1.min.js"></script>--%>
<script type="text/javascript" src="../../../../static/js/zoom.min.js"></script>
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
<!-- main content end-->
<script>
    function getDetail(title, tid) {
        <%--'${item.targetName}','${item.targetDescripion}'--%>
        var desc = $('#' + tid).val();
        $("#myModalLabel").html(title);
        $("#mo-content").html(desc);
        $("#myModal").modal({
            backdrop: false,
            show: true
        });
    }
    function closeQuestionModel() {
        $("#myModal1").modal({
            backdrop: false,
            show: true
        });
    }
    function closeQuestion(batchId, projectId) {
        var closes = $("#closes").val();
        if (closes != null && closes != "") {
            window.location.href = "../inspectAcceptance/executeAbnormalOffState?batchId=" + batchId + "&abnormalShutdown=" + closes;
        } else {
            alert("关单原因不能为空！");
            return false;
        }
//
//        var judge = confirm("系统提示：您确定要关闭此条记录吗？");
//        if (judge == true) {
//            window.location.href = "../inspectAcceptance/executeAbnormalOffState?batchId=" + batchId;
//        } else {
//            return;
//        }
//        $.ajax({
//            type: "GET",
//            url: "../inspectAcceptance/searchAuthorityByStaffId?projectId=" + projectId,
//            cache: false,
//            async: false,
//            dataType: "json",
//            success: function (data) {
//                if (data.data.success) {
//                    var judge = confirm("系统提示：" + data.data.success);
//                    if (judge == true) {
//                        window.location.href = "../inspectAcceptance/executeAbnormalOffState?batchId=" + batchId;
//                    } else {
//                        return;
//                    }
//                } else if (data.data.error) {
//                    var judge = confirm("系统提示：" + data.data.error);
//                    if (judge == true) {
//                        return;
//                    } else {
//                        return;
//                    }
//                } else {
//                    alert("对不起，操作失败！");
//                    return;
//                }
//            }
//        });
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