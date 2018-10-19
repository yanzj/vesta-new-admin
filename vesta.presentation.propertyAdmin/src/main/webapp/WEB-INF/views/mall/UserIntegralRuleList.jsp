<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script src="../static/property/js/propertyHousPay.js"></script>
</head>
<style type="text/css">
    .search_button{
        text-align: center;
    }
    .form_b{
        height: 12.5rem;
    }
</style>
<script>
    $(function(){
        console.log("sqq")
        $("#006700060000").addClass("active");
        $("#006700060000").parent().parent().addClass("in");
        $("#006700060000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006700060000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body form_b">
                <form id="form" class="form-horizontal" action="../userIntegralRule/getUserIntegralList.html">
                    <input type="hidden" id="menuId" name="menuId" value="006700060000"/>
                    <%-- 姓名 --%>
                    <div class="form-group  col-xs-4">
                        <label for="realName" class="col-sm-4 control-label">姓名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="realName" name="realName" value="${integralRuleQuerry.realName}">
                        </div>
                    </div>
                    <%-- 手机 --%>
                    <div class="form-group  col-xs-4">
                        <label for="mobile" class="col-sm-4 control-label">手机号码</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="mobile" name="mobile" value="${integralRuleQuerry.mobile}">
                        </div>
                    </div>
                    <%-- 项目 --%>
                    <%--<div class="form-group  col-xs-4">
                        <label for="name" class="col-sm-4 control-label">项目</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="name" name="name" value="${integralRuleQuerry.name}">
                        </div>
                    </div>--%>
                    <%-- 积分 --%>
                    <div class="form-group  col-xs-4">
                        <label for="integralNumber" class="col-sm-4 control-label">积分</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="integralNumber" name="integralNumber" value="${integralRuleQuerry.integralNumber}">
                        </div>
                    </div>
                    <%-- 公众号 --%>
                    <div class="form-group  col-xs-4">
                        <label for="weChatAppId" class="col-sm-4 control-label">公众号</label>
                        <div class="col-sm-8">
                            <select id="weChatAppId" name="weChatAppId" class="form-control">
                                <option value="">请选择公众号</option>
                                <c:forEach items="${defaultConfig}" var="defaultConfig" >
                                    <c:if test="${defaultConfig.weChatAppId != null}">
                                        <option value="${defaultConfig.weChatAppId }" <c:if test="${defaultConfig.weChatAppId eq integralRuleQuerry.weChatAppId}">selected</c:if>>${defaultConfig.clientName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- U+会员卡 --%>
                    <div class="form-group  col-xs-4">
                        <label for="integralNumber" class="col-sm-4 control-label">U+会员卡</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="cardNum" name="cardNum" value="${integralRuleQuerry.cardNum}">
                        </div>
                    </div>
                    <div class="col-xs-12 search_button">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                        <button type="button" class="btn btn-primary" onclick="excelFile.click()">批量导入</button>
                    </div>
                </form>
                <form id="uploadForm">
                    <input type="file" class="form-control" placeholder="" id="excelFile" style="visibility:hidden"
                           name="excelFile" onchange="doUpload()">
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="propertyAnnouncement.serialNumber"/></td>
                <th>姓名</th>
                <th>手机号</th>
                <th>积分数</th>
                <c:if test="${isShowUPlus eq 1}"><th>U+会员卡</th></c:if>
                <th>来源</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index + 1}</b></td>
                    <td>${list.realName}</td>
                    <td>${list.mobile}</td>
                    <td>${list.integralNumber}</td>
                    <c:if test="${isShowUPlus eq 1}"><td>${list.cardNum}</td></c:if>
                    <td>${list.clientName}</td>

                    <td class="last">
                        <a href="javascript:void(0);" onclick="toEdit('${list.userId}','${list.realName}','${list.mobile}','${list.name}')" id="toEdit" class="a3">查看详情</a>
                        <%--<a href="javascript:void(0);" onclick="toEdit('${list.userId}','${list.houseProjectId}','${list.realName}','${list.mobile}','${list.name}')" id="toEdit" class="a3">查看详情</a>--%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/userIntegralRule/getUserIntegralList.html?menuId=006700060000&pageIndex={0}&realName=${integralRuleQuerry.realName}&mobile=${integralRuleQuerry.mobile}&name=${integralRuleQuerry.name}&integralNumber=${integralRuleQuerry.integralNumber}"/>
    </div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    //编辑
    function toEdit(userId,houseProjectId,realname,phone,name){
        $.ajax({
            type: "GET",
            url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../userIntegralRule/getUserIntegralDetail.html?menuId="+$("#menuId").val()+"&realName="+realname+"&mobile="+phone+"&name="+name+"&userId="+userId;
                }else if(data.error == 0) {
                    alert("对不起，您无权限执行此操作！");
                }else{
                    alert("对不起，操作失败！");
                }
            }
        });
    }


    function doUpload(){
        //检验导入的文件是否为Excel文件
        var excelPath = document.getElementById("excelFile").value;
        if (excelPath == null || excelPath == '') {
            alert("请选择要上传的Excel文件");
            return;
        } else {
            var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();
            if (fileExtend == '.xls' || fileExtend == '.xlsx') {
            } else {
                alert("文件格式需为'.xls'格式或者'.xlsx格式'");
                return;
            }
        }


        //执行异步上传
        var formData = new FormData($("#uploadForm")[0]);
        $.ajax({
            url: '../userIntegralRule/uploadExcel',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if(data.error == '0'){
                    alert("导入成功！");
                    if(data.list.length != 0){
                        alert("未查到人员失败条数："+data.list.length+"，序号为：  "+data.list+"，没有找到人员（未入会）。未查到房产总条数："+data.listd.length+"，序号为：  "+data.listd+"，没有找到房产（未认证）");
                    }

                }else if(data.error == '-1'){
                    alert("导入失败！");
                }
            },
            error: function (data) {
                alert("网络异常,请求失败！");
            }
        });
    }
</script>
</body>
</html>
