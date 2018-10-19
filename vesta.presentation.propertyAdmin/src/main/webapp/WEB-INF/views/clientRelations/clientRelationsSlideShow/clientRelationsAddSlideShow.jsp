<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        $(function () {
            console.log("sqq")
            $("#007600010000").addClass("active");
            $("#007600010000").parent().parent().addClass("in");
            $("#007600010000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="007600010000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="formAdd" action="../qcSlideShow/saveOrUpdateSlideShow"
                      method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" id="slideShowId" name="slideShowId" value="${slideShowDTO.slideShowId}">
                    <%-- 轮播图类型 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">轮播图类型:</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <label>
                                <input type="radio" name="slideShowType"
                                       <c:if test="${slideShowDTO.slideShowType eq '1'}">checked</c:if>
                                       value="1">图文</label>
                            <label style="margin-left:20px;">
                                <input type="radio" name="slideShowType"
                                       <c:if test="${slideShowDTO.slideShowType eq '2'}">checked</c:if>
                                       value="2">视频</label>
                            <label style="margin-left:20px;">
                                <input type="radio" name="slideShowType"
                                       <c:if test="${slideShowDTO.slideShowType eq '3'}">checked</c:if>
                                       value="3">链接</label>
                        </div>
                    </div>
                    <%-- 轮播图标题 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="slideShowTitle">标题:</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="slideShowTitle"
                                   name="slideShowTitle" value="${slideShowDTO.slideShowTitle}">
                        </div>
                    </div>
                    <%-- 轮播图展示图 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">轮播图展示图:</label>
                        <div class="col-sm-5">
                            <input id="slideShowImgUpload" name="slideShowImgFile" type="file" multiple/>
                            <p style="color: red"><span>建议上传尺寸750×530</span></p>
                            <input type="hidden" id="slideShowImgUrl" name="slideShowImgUrl"
                                   value="${slideShowDTO.slideShowImgUrl}"/>
                        </div>
                    </div>
                    <%-- 轮播图展示图修改时显示以前上传的图 --%>
                    <div id="slideShowImg" class="form-group col-lg-7" style="display: none">
                        <label class="col-sm-3 control-label">原轮播图展示图:</label>
                        <div class="col-sm-5">
                            <img src="${slideShowDTO.slideShowImgUrl}" alt="原轮播图展示图" class="img-thumbnail"
                                 style="width: auto">
                        </div>
                    </div>
                    <%-- 轮播图详情 --%>
                    <div id="content" class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">轮播图详情:</label>
                        <div class="col-sm-5">
                            <script id="editor" type="text/plain"></script>
                            <input type="hidden" id="slideShowContent" name="slideShowContent"/>
                        </div>
                    </div>
                    <%-- 视频链接 --%>
                    <div id="url" class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="videoUrl">视频链接:</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="videoUrl" name="videoUrl"
                                   value="${slideShowDTO.videoUrl}">
                        </div>
                    </div>
                    <%-- H5链接 --%>
                    <div id="H5Url" class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="outUrl">链接:</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="outUrl" name="outUrl"
                                   value="${slideShowDTO.outUrl}">
                        </div>
                    </div>
                    <%-- 上架时间 --%>
                    <div id="isSlideShow" class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">上架时间:</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <label>
                                <input type="radio" name="isSlideShow"
                                       <c:if test="${slideShowDTO.isSlideShow eq '0'}">checked</c:if>
                                       value="0">暂不上架</label>
                            <c:if test="${slideShowDTO.isShow eq 'Y'}">
                                <label style="margin-left:20px;">
                                    <input type="radio" name="isSlideShow"
                                           <c:if test="${slideShowDTO.isSlideShow eq '1'}">checked</c:if>
                                           value="1">立即上架</label>
                                <label style="margin-left:20px;">
                                    <input type="radio" name="isSlideShow"
                                           <c:if test="${slideShowDTO.isSlideShow eq '3'}">checked</c:if>
                                           value="3">定时上架</label>
                            </c:if>
                        </div>
                    </div>
                    <%-- 发布日期 --%>
                    <div id="releaseTime" class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="sReleaseDate">上架日期:</label>
                        <div class="col-sm-5">
                            <r class="input-group date form_datetime" data-date=""
                               data-date-format="yyyy-mm-dd hh:ii"
                               data-link-field="dtp_input2">
                                <input type="text" class="form-control " placeholder="请输入时间" path="sReleaseDate"
                                       id="sReleaseDate"
                                       name="sReleaseDate"
                                       value="<fmt:formatDate type="time" value="${slideShowDTO.releaseDate}"
                                                      pattern="yyyy-MM-dd hh:mm"/>" readonly="true"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span></r>
                        </div>
                    </div>
            </div>
            <div class="text-center form-group  col-lg-6">
                <button type="button" class="btn btn-primary" onclick="toSave()">确定</button>
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
<script type="text/javascript">
    //页面加载时执行
    $(function () {
        initFileInput("slideShowImgUpload", "");
        if ($("#slideShowImgUrl").val() != "") {
            $("#slideShowImg").show();
            $("#slideShowImg").show();
        }
        if ($("input[name='slideShowType']:checked").val() == "2") {
            $("#url").show();
            $("#content").hide();
            $("#H5Url").hide();
        } else if ($("input[name='slideShowType']:checked").val() == "3"){
            $("#H5Url").show();
            $("#url").hide();
            $("#content").hide();
        }else{
            $("#url").hide();
            $("#H5Url").hide();
            $("#content").show();
        }
        if ($("input[name='isSlideShow']:checked").val() == "3") {
            $("#releaseTime").show();
        } else {
            $("#releaseTime").hide();
        }
        if ($("input[name='isSlideShow']:checked").val() == "1") {
            $("#isSlideShow").hide();
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
            validateInitialCount: true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        });
    }
</script>
<script type="text/javascript">
    //实例化编辑器
    var slideShowContent = '${slideShowDTO.slideShowContent}';
    var ue = UE.getEditor('editor', {
        toolbars: [[
            'source', '|', , 'undo', 'redo', '|',
            'bold', 'forecolor', 'removeformat', 'autotypeset', 'pasteplain', '|', '|',
            'justifyleft', 'justifycenter', '|',
            'link', 'unlink', '|',
            'simpleupload', 'insertimage', '|',
            'wordimage', '|',
            'inserttable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'splittocells', '|', 'mybtn1', 'mydialog1', 'scrawl'
        ]],
        autoHeightEnabled: false,
        autoFloatEnabled: true,
        zIndex: 99,
        initialFrameWidth: 1000,
        initialFrameHeight: 500,
        initialContent: slideShowContent
    });
    function isFocus(e) {
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e) {
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        <%--arr.push("${merDesc}123");--%>
        UE.getEditor('editor').setContent('${merDesc}', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }
    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }
    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }
    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }
    function getLocalData() {
        alert(UE.getEditor('editor').execCommand("getlocaldata"));
    }
    function clearLocalData() {
        UE.getEditor('editor').execCommand("clearlocaldata");
        alert("已清空草稿箱")
    }
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function (action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
            return '${baseUrl}../qcSlideShow/uploadImage.html?action=uploadimage';
        } else if (action == 'uploadvideo') {
            return '${baseUrl}../qcSlideShow/uploadImage.html?action=uploadimage';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }

    //发布
    function toSave() {
        var content = UE.getEditor('editor').getContent();
        $("#slideShowContent").val(content);
        if ($("input[name='slideShowType']:checked").val() == null) {
            alert("请选择轮播图类型！");
            return;
        }
        if ($("#slideShowTitle").val().replace(/(^\s*)|(\s*$)/g, "").length == 0) {
            alert("请输入轮播图标题！");
            return;
        }
        if ($("#slideShowImgUpload").val().replace(/(^\s*)|(\s*$)/g, "").length == 0) {
            alert("请选择轮播图！");
            return;
        }
        if ($("input[name='slideShowType']:checked").val() == "1" && $("#slideShowContent").val().replace(/(^\s*)|(\s*$)/g, "").length == 0) {
            alert("请输入轮播图内容！");
            return;
        }
        if ($("input[name='slideShowType']:checked").val() == "2" && $("#videoUrl").val().replace(/(^\s*)|(\s*$)/g, "").length == 0) {
            alert("请输入视频链接！");
            return;
        }
        if ($("input[name='slideShowType']:checked").val() == "3" && $("#outUrl").val().replace(/(^\s*)|(\s*$)/g, "").length == 0) {
            alert("请输入链接！");
            return;
        }
        if ($("input[name='isSlideShow']:checked").val() == null) {
            alert("请选择上架时间！");
            return;
        }
        if ($("input[name='isSlideShow']:checked").val() == "3" && $("#sReleaseDate").val().replace(/(^\s*)|(\s*$)/g, "").length == 0) {
            alert("请选择定时上架时间！");
            return;
        }
        $("#formAdd").submit();
    }

</script>
<script>
    //轮播图类型点击事件
    $("input[name='slideShowType']").click(function () {
        if (this.defaultValue == '1') {
            $("#content").show();
            $("#url").hide();
            $("#H5Url").hide();
        }
        if (this.defaultValue == '2') {
            $("#content").hide();
            $("#url").show();
            $("#H5Url").hide();
        }
        if (this.defaultValue == '3') {
            $("#content").hide();
            $("#url").hide();
            $("#H5Url").show();
        }
    });
    //上架时间点击事件
    $("input[name='isSlideShow']").click(function () {
        if (this.defaultValue == '0') {
            $("#releaseTime").hide();
        }
        if (this.defaultValue == '1') {
            $("#releaseTime").hide();
        }
        if (this.defaultValue == '3') {
            $("#releaseTime").show();
        }
    });
</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
</body>
</html>