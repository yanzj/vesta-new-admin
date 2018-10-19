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
    <!--下拉框可模糊筛选-->
    <link href="../static/css/chosen.css" rel='stylesheet' type='text/css'/>
    <script src="../static/js/chosen.jquery.min.js"></script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <style type="text/css">#dialog{display:none;}</style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007500010000" username="${authPropertystaff.staffName}" />
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" id="search" name="search" action="../feedBack/getFeedBackList.html" method="get" style="overflow: hidden;">
                    <div class="form-group  col-lg-12">
                        <div class="form-group  col-lg-4">
                            <label for="createDate" class="col-sm-4 control-label">提交时间：</label>
                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="请输入提交时间" id="createDate" path="createDate" name="createDate" value="${feedBackDTO.createDate}" readonly="true" />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="form-group  col-lg-4">
                            <label for="createDate" class="col-sm-4 control-label">用户名：</label>
                            <input class = "search-form" type="text" value="${feedBackDTO.userId}" name="userId">
                        </div>
                        <div class="form-group  col-lg-4">
                            <label for="createDate" class="col-sm-4 control-label">联系方式：</label>
                            <input class = "search-form" type="text" value="${feedBackDTO.phone}" name="phone">
                        </div>
                    </div>
                    <c:if test="${function.qch40010132 eq 'Y'}">
                        <button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="problem.search"/></button>
                    </c:if>
                    <c:if test="${function.qch40010133 eq 'Y'}">
                        <a href="#" onclick="isExcel()"  value="" class="btn btn-primary" style="color:#fff">导出Excel</a>
                    </c:if>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle" style="table-layout: fixed;">
            <thead>
                <tr>
                    <th width="4%">序号</th>
                    <th width="8%">用户名</th>
                    <th width="20%" >联系方式</th>
                    <th width="28%">意见反馈内容</th>
                    <th width="10%">提交时间</th>
                    <th width="5%">操作</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${feedBacks}" var="feedback" varStatus="row">
                <tr>
                    <td height="40px" style="text-align: center;"><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>
                    <td>${feedback.userId}</td>
                    <td>${feedback.phone}</td>
                    <td>
                        <c:if test="${fn:length(feedback.content)>30}">${fn:substring(feedback.content, 0, 30)} ...</c:if>
                        <c:if test="${fn:length(feedback.content)<=30}">${feedback.content}</c:if>
                    </td>
                    <td>${feedback.createDate}</td>
                    <td>
                        <c:if test="${function.qch40010134 eq 'Y'}">
                            <a href="javascript:void(0);" data-toggle="modal" onclick="getDetail('${feedback.userName}','${feedback.phone}','${feedback.createDate}','${feedback.content}','${feedback.id}')" data-target="#myModal"  for="">
                                <spring:message code="problem.detail" />
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/feedBack/getFeedBackList.html?pageIndex={0}&userId=${feedBackDTO.userId}&phone=${feedBackDTO.phone}&createDate=${feedBackDTO.createDate}"/>
    </div>
</div>
</div>
</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <br class="modal-body">
                <label style="font-size: 20px">意见反馈详情</label>
                <hr style="height:1px;border:none;border-top:1px solid #555555;" />
                <div class="feedBackDetail">
                    <span>投诉人：</span><span id="span_userName"></span>
                </div></br>
                <div class="feedBackDetail">
                    <span>联系电话：</span><span id="span_phone"></span>
                </div></br>
                <div class="feedBackDetail">
                    <span>提交时间：</span><span id="span_createDate"></span>
                </div></br>
                <div class="feedBackDetail">
                    <span>反馈内容：</span><span id="span_content"></span>
                </div></br>
                <div id="imgList" class="feedBackDetail">

                </div></br>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
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
        var href = "../feedBack/printExportExcel?userId=${feedBackDTO.userId}&phone=${feedBackDTO.phone}&createDate=${feedBackDTO.createDate}";
        window.location.href = href;
    }

    function getDetail(userName,phone,createDate,content,id) {
        $.ajax({
            url: "../feedBack/getDetail",
            type: 'post',
            async: false,
            dataType: "json",
            data: {
                "userName": userName,
                "phone" :phone,
                "createDate":createDate,
                "content":content,
                "id":id
            },
            success: function (data) {
                if (data.check == 0) {
                    $("#span_userName").html(data.data.userName);
                    $("#span_phone").html(data.data.phone);
                    $("#span_createDate").html(data.data.createDate);
                    $("#span_content").html(data.data.content);
                    if(data.data.img != null){
                        var img = "<span>反馈图片：</span>";
                        for (var i=0;i<data.data.img.length;i++){
                            img+="<div class='feedBackImg'><img src='"+data.data.img[i]+"' style='width: 400px'></div></br>";
                        }
                        $("#imgList").html(img);
                    }
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }
</script>
<style>
    .feedBackImg{
        width: 100px;
    }
    .feedBackDetail{
        font-size: 15px;
        margin-left: 10px;
    }
    .search-form {
        border-radius: inherit;
        height: 30px;
        padding: 0 10px;
    }
</style>
</body>

</html>