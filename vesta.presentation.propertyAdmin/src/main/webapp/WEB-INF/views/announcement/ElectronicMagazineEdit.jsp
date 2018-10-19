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
            $("#006900020000").addClass("active");
            $("#006900020000").parent().parent().addClass("in");
            $("#006900020000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="006900020000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="006900020000"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../electronicMagazine/saveOrUpdateElectronicMagazineInfo.html" method="post" enctype="MULTIPART/FORM-DATA">
                    <input type="hidden" id="id" name="id" value="${electronicMagazineInfo.id}">
                    <%-- 所属客户端 --%>
                    <div class="form-group  col-lg-7">
                        <label for="clientId" class="col-sm-3 control-label">所属客户端</label>
                        <div class="col-sm-5">
                            <select id="clientId" name="clientId" class="form-control">
                                <c:forEach items="${clientConfigList}" var="clientConfig" >
                                    <option value="${clientConfig.id }"
                                            <c:if test="${clientConfig.id eq electronicMagazineInfo.clientId}">selected</c:if>>${clientConfig.clientName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <input type="hidden" id="clientName" name="clientName"/>
                    <%-- 杂志名称 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="magazineName">杂志名称</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="magazineName" name="magazineName" value="${electronicMagazineInfo.magazineName}">
                        </div>
                    </div>
                    <%-- 杂志导图 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">杂志导图</label>
                        <div class="col-sm-5">
                            <input id="mapImgUpload" name="mapImgFile" type="file" multiple/>
                            <input type="hidden" id="mapImgUrl" name="mapImgUrl" value="${electronicMagazineInfo.mapImgUrl}"/>
                        </div>
                    </div>
                    <%-- 杂志导图原图 --%>
                    <div id="mapImg" class="form-group col-lg-7" style="display: none">
                        <label class="col-sm-3 control-label">杂志导图原图</label>
                        <div class="col-sm-5">
                            <img src="${electronicMagazineInfo.mapImgUrl}" alt="杂志导图原图" class="img-thumbnail" style="width: auto">
                        </div>
                    </div>
                    <%--是否有外链--%>
                    <div class="form-group  col-lg-7">
                        <label for="isLink" class="col-sm-3 control-label">是否有外链</label>
                        <div class="col-sm-5">
                            <select class="form-control" placeholder="" id="isLink" name="isLink" onchange="isLinkOnChange()">
                                <option value="0" <c:if test="${electronicMagazineInfo.isLink eq 0}">selected</c:if>>否</option>
                                <option value="1" <c:if test="${electronicMagazineInfo.isLink eq 1}">selected</c:if>>是</option>
                            </select>
                        </div>
                    </div>
                    <%--外链地址--%>
                    <div class="form-group col-lg-7" id="linkSrcDiv" <c:if test="${empty electronicMagazineInfo.isLink || electronicMagazineInfo.isLink eq 0}">style="display:none;"</c:if>>
                        <label class="col-sm-3 control-label" for="linkSrc">外链地址</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="linkSrc" name="linkSrc" value="${electronicMagazineInfo.linkSrc}">
                        </div>
                    </div>
                    <%-- 封面 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">封面</label>
                        <div class="col-sm-5">
                            <input id="coverImgUpload" name="coverImgFile" type="file" multiple/>
                            <input type="hidden" id="coverImgUrl" name="coverImgUrl" value="${electronicMagazineInfo.coverImgUrl}"/>
                        </div>
                    </div>
                    <%-- 封面原图 --%>
                    <div id="coverImg" class="form-group col-lg-7" style="display: none">
                        <label class="col-sm-3 control-label">封面原图</label>
                        <div class="col-sm-5">
                            <img src="${electronicMagazineInfo.coverImgUrl}" alt="封面原图" class="img-thumbnail" style="width: auto">
                        </div>
                    </div>
                    <%-- 封底 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">封底</label>
                        <div class="col-sm-5">
                            <input id="backCoverImgUpload" name="backCoverImgFile" type="file" multiple/>
                            <input type="hidden" id="backCoverImgUrl" name="backCoverImgUrl" value="${electronicMagazineInfo.backCoverImgUrl}"/>
                        </div>
                    </div>
                    <%-- 封底原图 --%>
                    <div id="backCoverImg" class="form-group col-lg-7" style="display: none">
                        <label class="col-sm-3 control-label">封底原图</label>
                        <div class="col-sm-5">
                            <img src="${electronicMagazineInfo.backCoverImgUrl}" alt="封面原图" class="img-thumbnail" style="width: auto">
                        </div>
                    </div>
                    <%-- 目录 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">目录</label>
                        <div class="col-sm-5">
                            <input id="catalogImgUpload" name="catalogImgFile" type="file" multiple/>
                            <input type="hidden" id="catalogImgUrl" name="catalogImgUrl" value="${electronicMagazineInfo.catalogImgUrl}"/>
                        </div>
                    </div>
                    <%-- 目录原图 --%>
                    <div id="catalogImg" class="form-group col-lg-7" style="display: none">
                        <label class="col-sm-3 control-label">目录原图</label>
                        <div class="col-sm-5">
                            <img src="${electronicMagazineInfo.catalogImgUrl}" alt="目录原图" class="img-thumbnail" style="width: auto">
                        </div>
                    </div>
                    <%-- 栏目内容 --%>
                    <div class="form-group col-lg-7"  id="addText">
                    </div>
                    <input type="hidden" id="columnContentJson" name="columnContentJson" value="${electronicMagazineInfo.columnContentJson}"></input>
                    <%-- 增加栏目 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="addTextInput">增加栏目</label>
                        <div class="col-sm-5">
                            <input type="button" id="addTextInput" name="addTextInput" value="增加栏目">
                        </div>
                    </div>

                    <input type="hidden" name="releaseStatus" id="releaseStatus"/>
                    <div class="text-center form-group  col-lg-6">
                        <button type="button" class="btn btn-primary" onclick="toSave('1')">发布</button>
                        <button type="button" class="btn btn-primary" onclick="toSave('0')">暂不发布</button>
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
    //页面加载时执行
    $(function(){
        initFileInput("mapImgUpload", "");
        if ($("#mapImgUrl").val() != ""){
            $("#mapImg").show();
        }
        initFileInput("coverImgUpload", "");
        if ($("#coverImgUrl").val() != ""){
            $("#coverImg").show();
        }
        initFileInput("backCoverImgUpload", "");
        if ($("#backCoverImgUrl").val() != ""){
            $("#backCoverImg").show();
        }
        initFileInput("catalogImgUpload", "");
        if ($("#catalogImgUrl").val() != ""){
            $("#catalogImg").show();
        }
        initFileInput("columnImgUpload", "");
        if ($("#columnImgUrl").val() != ""){
            $("#columnImg").show();
        }
        initFileInput("contentImgUpload", "");
        if ($("#contentImgUrl").val() != ""){
            $("#contentImg").show();
        }
    });
    //初始化fileinput控件(第一次初始化)
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
</script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayBtn: true,
        linkField: "mirror_field",
        linkFormat: "yyyy-mm-dd hh:ii"
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
<script type="text/javascript">
    function isLinkOnChange(){
        if ($("#isLink").val() == "1"){
            $("#linkSrcDiv").show();
        }else{
            $("#linkSrcDiv").hide();
            $("#linkSrc").val("");
        }
    }
    //发布
    function toSave(status){
        if ($("#magazineName").val() == "") {
            alert("请输入杂志名称！");
            return;
        }
        var title = $("#magazineName").val();
        if (title.length > 100) {
            alert("杂志名称超过最大长度！");
            return;
        }
        $("#clientName").val($("#clientId option:selected").text());
        if ($("#clientId option:selected").text() != "请选择"){
            $("#clientName").val($("#clientId option:selected").text());
        }
        $("#releaseStatus").val(status);
        //栏目内容数据处理
        var testa =[];
        obj = document.getElementsByName("columnImgUrl");
        var c = 1;

        for(i=0;i<obj.length;i++){
            var stu= {"type":"","imgUrl":"","contentImgs":[]};
            stu.type = "栏目";
            stu.imgUrl=document.getElementById("columnImgUrl"+c).value;
            var urls = document.getElementsByName("contentImgUrl"+c);
            var test =[];
            for(j=0;j<urls.length;j++){
                var tests ={"url":""};
                tests.url = urls[j].value;
                test.push(tests);
            }

            stu.contentImgs =test;
            testa.push(stu);
            c++;
        }
        $("#columnContentJson").val(JSON.stringify(testa));

        $("#fromAdd").submit();
    }
</script>
<script type="application/javascript">
    var i = 1;
    j = 1;
    var l = 1;
    $(function(){
        $('#addTextInput').click(function(){
            $('#addText').append('' +
                '<div id="seled'+i+'" name="seled'+i+'"><label class="col-sm-3 control-label" for="title">栏目</label>' +
                '<div class="col-sm-7" id="addTextSele'+i+'">' +
                '<div>' +
                '<input id="columnImgFile'+i+'" type="file" onchange="imgUpload(this)" multiple/><input id="columnImgUrl'+i+'" name="columnImgUrl" type="hidden" />' +
                '<input type="button" id="addTextInput'+i+'" name="addTextInput'+i+'" value="增加内容" onclick="addT('+i+')"><input type="button" onclick="dels('+i+')" value="删除栏目"/>' +
                '<input id="'+l+'" name="contentImgFile'+i+'" type="file" onchange="imgUpload(this)" multiple/>'+
                '<input id="contentImgUrl'+l+'" name="contentImgUrl'+i+'" type="hidden" />' +
                '<input type="button" onclick="del('+i+')" value="删除"/></div></div></div></div>');
            initFileInput("columnImgFile"+i, "");
            initFileInput(l, "");
            i++;
            l++;
        });

    });
    function addT(i) {
        $('#addTextSele'+i).append('<div id="testt'+j+'">' +
            '<input id="'+l+'" name="contentImgFile'+i+'" type="file" onchange="imgUpload(this)" multiple/>'+
            '<input id="contentImgUrl'+l+'" name="contentImgUrl'+i+'" type="hidden" />' +
            '<input type="button" onclick="dele('+j+')" value="删除"/></div>');
        initFileInput(l, "");
        j =j+1;
        l++;
    }
    function dele(o) {
        $("div").remove("#testt"+o);
    }
    function del(o) {
        $("div").remove("#test"+o);
    }
    function del(o) {
        $("div").remove("#test"+o);
    }
    function dels(o) {
        $("div").remove("#seled"+o);
    }
    function imgUpload(th) {
        var formData = new FormData();
        formData.append("multipartFile",th.files[0]);
        $.ajax({
            url:"../electronicMagazine/imgUpload",
            type:"POST",
            async:"false",
            data:formData,
            processData:false,
            contentType:false,
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(data){
                if(th.id.indexOf("columnImg")>=0){
                    $("#columnImgUrl"+th.id.replace("columnImgFile","")).val(data.imgUrl);
                }else{
                    $("#contentImgUrl"+th.id).val(data.imgUrl);
                }
            }
        });

    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>