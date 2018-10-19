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
            $("#006100010000").addClass("active");
            $("#006100010000").parent().parent().addClass("in");
            $("#006100010000").parent().parent().parent().parent().addClass("active");
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
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006100010000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="006100010000"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../homeLetter/saveOrUpdateHomeLetterInfo.html" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="homeLetterId" name="homeLetterId" value="${homeLetter.homeLetterId}">
                    <%-- 家书批次标题 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label" for="homeLetterTitle">家书批次标题</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" placeholder="" id="homeLetterTitle" name="homeLetterTitle" value="${homeLetter.homeLetterTitle}">
                        </div>
                    </div>
                    <%-- 批次时间 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label" for="homeLetterDate">批次时间</label>
                        <div class="input-group date form_YM col-sm-4" data-date="" data-date-format="yyyy-mm"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="" id="homeLetterDate"
                                   value="${homeLetter.homeLetterDate}" name="homeLetterDate" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <!-- 客户端 -->
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label" for="clientId">所属客户端</label>
                        <div class="col-sm-4">
                            <select id="clientId" name="clientId" class="form-control">
                                <option value="">请选择</option>
                                <c:forEach items="${clientConfigList}" var="clientConfig" >
                                    <option value="${clientConfig.id }"
                                            <c:if test="${clientConfig.id eq homeLetter.clientId}">selected</c:if>>${clientConfig.clientName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 家书批次展示图 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label">家书批次展示图</label>
                        <div class="col-sm-4">
                            <input id="homeLetterSignImgUpload" name="homeLetterSignImgFile" type="file" multiple/>
                            <input type="hidden" id="homeLetterSignImgUrl" name="homeLetterSignImgUrl" value="${homeLetter.homeLetterSignImgUrl}"/>
                        </div>
                    </div>
                    <%-- 家书批次展示图原图 --%>
                    <div id="homeLetterSignImg" class="form-group col-lg-10" style="display: none">
                        <label class="col-sm-3 control-label">家书批次展示图原图</label>
                        <div class="col-sm-4">
                            <img src="${homeLetter.homeLetterSignImgUrl}" alt="微信二维码原图" class="img-thumbnail" style="width: auto">
                        </div>
                    </div>
                    <div class="text-center form-group  col-lg-6">
                        <button type="button" class="btn btn-primary" onclick="toSave()"><spring:message
                                code="common_save"/></button>
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
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $(".form_YM").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm',
        autoclose: true,
        todayBtn: true,
        startView: 'year',
        minView:'year',
        maxView:'decade'
    });
    //页面加载时执行
    $(function(){
        initFileInput("homeLetterSignImgUpload", "");
        if ($("#homeLetterSignImgUrl").val() != ""){
            $("#homeLetterSignImg").show();
        }
    });

    //初始化fileinput控件（第一次初始化）
    function initFileInput(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            //dropZoneEnabled: false,//是否显示拖拽区域
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount: 10, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        });
    }

    function toSave(){
        if($("#homeLetterTitle").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入批次标题！");
            return;
        }
        if($("#homeLetterDate").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请选择批次时间！");
            return;
        }
        if($("#clientId").val()==''){
            alert("请选择批次所属客户端！");
            return;
        }
        $("#fromAdd").submit();
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>