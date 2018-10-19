<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%response.setHeader("cache-control", "public"); %>
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
        $(function(){
            console.log("sqq")
            $("#006700050000").addClass("active");
            $("#006700050000").parent().parent().addClass("in");
            $("#006700050000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <style>
        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 100px;
            height: 120px;
        }

        .sidebar ul li {
            border-bottom: 0;
        }
    </style>
    <%-- FileInput --%>
    <link href="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/css/fileinput.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/js/fileinput.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.1/js/fileinput_locale_zh.js"></script>
    <%-- ueditor --%>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006700050000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="006700050000"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../productInfo/saveOrUpdateproductInfo.html" method="post" enctype="MULTIPART/FORM-DATA">
                    <input type="hidden" id="integralRuleId" name="integralRuleId" value="${integralRuleDto.integralRuleId}">
                    <%-- 公众号 --%>
                    <div class="form-group  col-lg-7">
                        <label for="clientConfigId" class="col-sm-3 control-label">公众号</label>
                        <div class="col-sm-5">
                            <select id="clientConfigId" name="clientConfigId" class="form-control">
                                <option value="">请选择公众号</option>
                                <c:forEach items="${defaultConfig}" var="defaultConfig" >
                                    <option value="${defaultConfig.id }"
                                            <c:if test="${defaultConfig.id eq integralRuleDto.clientConfigId}">selected</c:if>>${defaultConfig.clientName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%-- 模块 --%>
                    <div class="form-group  col-lg-7">
                        <label for="integralRuleModelId" class="col-sm-3 control-label">模块</label>
                        <div class="col-sm-5">
                            <select id="integralRuleModelId" name="integralRuleModelId" class="form-control">
                                <option value="">请选择模块</option>
                                <c:forEach items="${modelIntegral}" var="modelIntegral" >
                                    <option value="${modelIntegral.modelId }"
                                            <c:if test="${modelIntegral.modelId eq integralRuleDto.integralRuleModelId}">selected</c:if>>${modelIntegral.modelName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%-- 积分 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="integralNumber">积分</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="integralNumber" name="integralNumber" value="${integralRuleDto.integralNumber}">
                        </div>
                    </div>






                    <div class="text-center form-group  col-lg-6">
                        <button type="button" class="btn btn-primary" onclick="toSave()">保存</button>
                        <button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);">
                            <spring:message code="common_cancel"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>

<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        linkField: "mirror_field",
        linkFormat: "yyyy-mm-dd",
        minView:2,
        maxView:3
    })
</script>
<script>

    //保存
    function toSave(){
        var formData = new FormData();

        if($("#clientConfigId").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请选择公众号！");
            return;
        }else{
            formData.append("clientConfigId",$("#clientConfigId").val());
        }

        if($("#integralRuleModelId").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输选择模块！");
            return;
        }else{
            formData.append("integralRuleModelId",$("#integralRuleModelId").val());
        }

        if($("#integralNumber").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入积分数量！");
            return;
        }else{
            formData.append("integralNumber",$("#integralNumber").val());
        }
        formData.append("integralRuleId",$("#integralRuleId").val());


       $.ajax({
            url:"../integralRule/toSaveIntegralRule",
            type:"POST",
            async:"false",
            data:formData,
            processData:false,
            contentType:false,
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(data){
                if(data.error == "0"){
                    alert("保存成功！");
                    window.location.href = "../integralRule/getIntegralRuleList.html?menuId=006700050000";
                }else if(data.error == "1"){
                    alert("公众号配置模块已存在！");
                }else if(data.error == "-1"){
                    alert("保存失败，请联系管理员！");
                }

            }
        });
    }
</script>
</body>
</html>