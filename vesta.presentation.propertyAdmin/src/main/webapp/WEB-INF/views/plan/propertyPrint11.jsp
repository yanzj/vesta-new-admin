<%--
  Created by IntelliJ IDEA.
  User: lpc
  Date: 2016/6/5
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
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
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <%--<link rel="stylesheet" href="../static/css/jquery-ui.min.css" />
    <script src="../static/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>--%>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
    <style>
        label.error {
            /*position: absolute;*/
            /*margin-top: -74px;*/
            /*display: block;*/
            margin-left: 1%;
            color: red;
        }
    </style>
    <style>
        td {
            overflow:hidden;text-overflow:ellipsis;/*display:-webkit-box;*/-webkit-box-orient:vertical;-webkit-line-clamp:2;
            /*white-space:nowrap;overflow:hidden;text-overflow: ellipsis;*/
        }
    </style>
    <style type="text/css">#dialog{display:none;}</style>
    <script type="application/javascript">
        $(function(){
            $("#001000030000").addClass("active");
            $("#001000030000").parent().parent().addClass("in");
//      $("#003200010000").parent().parent().parent().parent().addClass("active");
        })
        function checkAllBox(obj){
            var answer= document.getElementsByName("ids");
            if(obj.checked==true){
                for(var i=0;i<answer.length;i++){
                    answer[i].checked = true;
                }
            }else{
                for(var i=0;i<answer.length;i++){
                    answer[i].checked = false;
                }
            }
        }
        function checkData(){
            var answer= document.getElementsByName("answer");
            var flag = false;
            for(var i=0;i<income.length;i++){
                if(income[i].checked == true){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                alert("请至少选择一项");
            }
            return flag;
        }


        //打印
        function toObtain(){
            var type=11;
            //首先选择房间号
//            if ($('#roomId').val() == null || $('#roomId').val() == " ") {
//                alert("请先选择房间号");
//            }else{
                var rId= $("#roomId").val();
                var projectId=$("#projectId").val();
                var obj=document.getElementsByName('ids');
                var s='';
                var flag = false;
                for(var i=0;i<obj.length;i++){
                    if(obj[i].checked == true){
                        s+=obj[i].value+',';
                        flag = true;
                    }
                }


                s=s.substr(0, s.length-1);
                // window.location.href = "../problem/printProblemListing?caseIds="+ s+"&rId="+encodeURIComponent(rId)+"&projectId="+projectId+"&type="+type;//要新的窗口打开链接
                window.open("../problem/printProblemListing?caseIds="+ s+"&rId="+encodeURIComponent(rId)+"&projectId="+projectId+"&type="+type,"_blank");
                //window.open("http://cwhois.cnnic.cn/validatecode/validate.jsp?value="+strName+"&entity=domain&service=/whois&inputfield=value);
//            }
        }



    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001000030000" username="${authPropertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <%--<form class="form-horizontal" id="search" name="search" action="../problem/problemPrint.html" method="get">--%>
                    <%--<a  href="../problem/preAddProblemPrint?planType=11" class="btn btn-primary" for="rolesetAdd" ><spring:message code="problem.add"/></a>--%>
                    <button class="btn btn-primary" onclick="javaScript:history.go(-1)">返回</button>
                    <c:if test="${function.qch40010017 eq 'Y'}">
                        <a href="javascript:void(0);" onclick="toObtain();" value="" class="btn btn-primary" style="color:#fff">打印交房单</a>
                    </c:if>
                <%--</form>--%>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle" style="table-layout: fixed;">
            <thead>
            <tr>
                <%--<th width="4%"><input type="checkbox" name="answer" onclick="checkAllBox(this)">序号</th>--%>
                <th width="4%"><input style="margin-top:5px" type="checkbox" name="answer" onclick="checkAllBox(this)">序号</th>
                <th width="4%">超期</th>
                <th width="4%">部位</th>
                <th width="20%" >问题描述</th>
                <th width="10%">一级分类</th>
                <th width="10%">二级分类</th>
                <th width="10%">三级分类</th>
                <th width="24%">创建人</th>
                <th width="5%">状态</th>
                <th width="10%">登记时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${problems}" var="problem" varStatus="row">
                <tr>
                    <td><input name="ids" type="checkbox" value="${problem.caseId}"/><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>
                    <td>${problem.isOverdue}</td>
                    <td>${problem.casePlace}</td>
                    <td>${problem.caseDesc}</td>
                    <td>${problem.firstTypeName}</td>
                    <td>${problem.secondTyoeName}</td>
                    <td>${problem.thirdTypeName}</td>
                    <td>${problem.createByName}</td>
                    <td>
                        <c:if test="${problem.caseState eq '草稿'}">草稿</c:if>
                        <c:if test="${problem.caseState eq '处理中'}">处理中</c:if>
                        <c:if test="${problem.caseState eq '已完成'}">已完成</c:if>
                        <c:if test="${problem.caseState eq '已废弃'}">已废弃</c:if>
                        <c:if test="${problem.caseState eq '待接单'}">待接单</c:if>

                    </td>
                    <input type="hidden" id="roomId" name="roomId" value="${problem.roomId}" />
                    <input type="hidden" id="projectId" name="projectId" value="${problem.projectId}" />
                    <td><fmt:formatDate  type="date" value="${problem.createDate}" dateStyle="default" pattern="yyyy-MM-dd HH:mm:ss" /></td>

                </tr>
            </c:forEach>
            <input type="hidden" id="roomId" name="roomId" value="${typeMaps.roomId}" />
            <input type="hidden" id="projectId" name="projectId" value="${typeMaps.projectId}" />
            </tbody>
        </table>
        <%--    <m:pager pageIndex="${webPage.pageIndex}"
                     pageSize="${webPage.pageSize}"
                     recordCount="${webPage.recordCount}"
                     submitUrl="${pageContext.request.contextPath }/problem/problemPrint.html?pageIndex={0}&projectId=${problem.projectId}&buildingId=${problem.buildingId}&roomId=${problem.roomId}&caseState=${problem.caseState}&oneType=${problem.oneType}&twoType=${problem.twoType}&threeType=${problem.threeType}&proType=${problem.proType}&startDate=${problem.startDate}&endDate=${problem.endDate}"/>--%>
    </div>


</div>
</div>
</div>
</div>

<!-- main content end-->
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
    var projectId = "${problem.projectId}";
    var buildingId = "${problem.buildingId}";
    var unitId = "${problem.unitId}";
    var floorId = "${problem.floorId}";
    var roomId = "${problem.roomId}";
    var caseState = "${problem.caseState}";
    var oneType = "${problem.oneType}";
    var twoType = "${problem.twoType}";
    var threeType = "${problem.threeType}";
    var proType = "${problem.proType}";
    var startDate = "${problem.startDate}";
    var endDate = "${problem.endDate}";
</script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">

    function batchCommit(){
        if(window.confirm("确定提交选中的草稿吗？")){
            document.forms[1].submit();
        }
    }

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

</script>
</body>

</html>