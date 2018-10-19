<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <script language="javascript" type="text/javascript" src="/static/js/jquery-1.11.1.min.js"></script>
    <script type="application/x-javascript">

        addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- Bootstrap Core CSS -->
    <link href="../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- Custom CSS -->
    <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>

    <!-- 时间控件CSS Begin-->
    <link href="../static/plus/date/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- 时间控件CSS End-->


    <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <script src="../static/js/jquery.SuperSlide.2.1.1.js"></script>
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
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020012" username="${propertystaff.staffName}" />


    <div class="forms">

        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form  action="../activities/activitiesManage.html" class="form-horizontal" method="post">
                <div class="form-group  col-lg-6">
                    <label for="title" class="col-sm-3 control-label"><spring:message code="sharingActivities.title"/></label>

                    <div class="col-sm-9">
                        <input type="text" class="form-control" placeholder="" id="title"
                               name="title" >
                    </div>
                </div>
                    <div class="form-group  col-lg-6">
                        <label for="propertyProject" class="col-sm-3 control-label"><spring:message code="propertyCompany.propertyProject"/></label>

                        <div class="col-sm-9">
                            <select id="propertyProject" name="propertyProject"  class="form-control">
                                <c:forEach items="${projects.propertyProjectMap}" var="item">
                                    <option value="${item.key }">${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <%--开始时间--%>
                    <div class="form-group  col-lg-6">
                        <label for="publishStartDate" class="col-sm-3 control-label"><spring:message code="advertServices.StartDate"/></label>
                        <div class="input-group date form_datetime col-sm-9" data-date="" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd hh:ii:ss">
                           <input type="text" class="form-control"  placeholder="" id="publishStartDate"
                                   name="publishStartDate">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>

                        </div>
                        </div>
                        <div class="form-group  col-lg-6">
                        <label for="publishEndDate" class="col-sm-3 control-label"><spring:message code="advertServices.EndDate"/></label>

                        <div  class="input-group date form_datetime col-sm-9"  data-date="" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd hh:ii:ss" >
                            <input type="text" class="form-control" placeholder="" id="publishEndDate"
                                   name="publishEndDate">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>

                    <div class="clearfix"></div>
                    <button type="submit" id="abc" class="btn btn-primary"  ><spring:message code="propertyCompany.propertySearch"/></button>

                    <a href="../activities/CreateActivities.html?activitiesId=" class="btn btn-primary" ><spring:message code="propertyCompany.propertyAdd"/></a>

                </form>
            </div>
            <%--搜索条件结束--%>
        </div>

    </div>

    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="propertyCompany.serialNumber" /></td>
                <th><spring:message code="sharingActivities.title" /></th>
                <th><spring:message code="sharingActivities.content" /></th>
                <th><spring:message code="advertServices.Time" /></th>
                <th><spring:message code="advertServices.push" /></th>
                <th width="174"><spring:message code="propertyCompany.operation" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${activitiesMessage}" var="activities" varStatus="row">
                <tr>
                    <td style="width:100px"><b>${row.index + 1}</b></td>
                    <td style="width:100px">${activities.title}</td>
                    <td style="width:210px">${activities.content}</td>
                    <td style="width:100px">${activities.publishdate}</td>
                    <td style="width:100px">${activities.publishBy}</td>
                    <td class="last">
                        <c:if test="${activities.isSort==1&&activities.count>1}">
                        <c:choose>
                            <c:when test="${row.index==0}">
                                <a href="../activities/Activitiessort.html?activitiesId=${activities.activitiesId}&state=down" style="margin:0;color: black"><span class="glyphicon glyphicon-arrow-down" style="color: green"><spring:message code="advertServices.down" /></span></a>
                            </c:when>
                            <c:when test="${row.index==activities.count-1}">
                                <a href="../activities/Activitiessort.html?activitiesId=${activities.activitiesId}&state=up" style="margin:0;color: black"><span class="glyphicon glyphicon-arrow-up" style="color:red"><spring:message code="advertServices.up" /></span></a>
                            </c:when>
                            <c:otherwise>
                                <a href="../activities/Activitiessort.html?activitiesId=${activities.activitiesId}&state=up" style="margin:0;color: black"><span class="glyphicon glyphicon-arrow-up"style="color:red"><spring:message code="advertServices.up" /></span></a>
                                <a href="../activities/Activitiessort.html?activitiesId=${activities.activitiesId}&state=down" style="margin:0;color: black"><span class="glyphicon glyphicon-arrow-down" style="color: green"><spring:message code="advertServices.down" /></span></a>
                            </c:otherwise>
                        </c:choose>
                        </c:if>
                        <a href="../activities/CreateActivities.html?activitiesId=${activities.activitiesId}" class="a2"><span class="glyphicon glyphicon-edit" style="margin:0;color: black"><spring:message code="propertyServices.serviceModify" /></span></a>
                        <a href="javascript:void(0);" onclick="js_delMessage('${activities.activitiesId}')" ><span class="glyphicon glyphicon-remove" style="margin:0;color: black"><spring:message code="propertyServices.serviceDelete" /></span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../activities/activitiesManage.html?pageIndex={0}&propertyProject=${activitiesSearch.propertyProject}&publishStartDate=${activitiesSearch.publishStartDate}&publishEndDate=${activitiesSearch.publishEndDate}&title=${activitiesSearch.title}"/>
    </div>


</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script type="application/x-javascript">
    <!--  -->
    <!-- 当前页面 -->
    var pages = "${webPage.pageIndex}";
    <!-- 项目 -->
    var propertyProject = "${activitiesSearch.propertyProject}";
    <!-- 开始时间 -->
    var publishStartDate = "${activitiesSearch.publishStartDate}";
    <!-- 结束时间 -->
    var publishEndDate = "${activitiesSearch.publishEndDate}";
    <!-- 标题 -->
    var title = "${activitiesSearch.title}";




    console.info($("#abc").get(0));
    $("#abc").click(function(){
        console.info("fuck");
        $("#123").submit();
    })
</script>
<!-- 时间控件Begin -->
<script type="text/javascript" src="../static/plus/date/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/plus/date/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    console.info($('.form_datetime').get(0));
    $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });


    <!-- 删除咨询管理信息 -->
    function js_delMessage(activitiesId){
        myvalue = confirm("确定要删除吗?");
        if (myvalue == true) {
            $.ajax({
                url: "/activities/removeActivities",
                type: "post",
                async: "false",
                dataType: "json",
                data: {
                    "activitiesId":activitiesId,
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
                        if (data == "1") {
                            // 此信息删除失败!
                            alert("信息删除失败,请刷新重试...");
                        }
                        if (data == "2") {
                            // 此信息不存在!
                            alert("信息已被删除,请刷新...");
                        }
                        window.location.href = "../activities/activitiesManage.html?pageIndex="+pages+"&propertyProject="+propertyProject+"&publishStartDate="+publishStartDate+"&publishEndDate="+publishEndDate+"&title="+title;
                    }
                },
            });
        }
    }
</script>
<!-- 时间控件End -->
</body>
</html>