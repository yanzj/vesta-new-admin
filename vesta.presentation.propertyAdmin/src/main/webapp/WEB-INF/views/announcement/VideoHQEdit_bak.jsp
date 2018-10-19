<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            $("#006900030000").addClass("active");
            $("#006900030000").parent().parent().addClass("in");
            $("#006900030000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="006900030000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="006900030000"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../video/saveOrUpdateVideoHQInfo.html" method="post" enctype="MULTIPART/FORM-DATA">
                    <input type="hidden" id="id" name="id" value="${videoHQInfo.id}">
                    <%-- 视频名称 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="videoName">视频名称</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="videoName" name="videoName" value="${videoHQInfo.videoName}">
                        </div>
                    </div>
                    <%-- 视频ID --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="videoId">视频ID</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="videoId" name="videoId" value="${videoHQInfo.videoId}">
                        </div>
                    </div>
                    <%-- 视频链接 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="videoLinkSrc">视频链接</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="videoLinkSrc" name="videoLinkSrc" value="${videoHQInfo.videoLinkSrc}">
                        </div>
                    </div>
                    <%-- 视频导图 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">视频导图</label>
                        <div class="col-sm-5">
                            <input id="videoMapImgUpload" name="videoMapImgFile" type="file" multiple/>
                            <input type="hidden" id="videoMapImgUrl" name="videoMapImgUrl" value="${videoHQInfo.videoMapImgUrl}"/>
                        </div>
                    </div>
                    <%-- 视频导图原图 --%>
                    <div id="videoMapImg" class="form-group col-lg-7" style="display: none">
                        <label class="col-sm-3 control-label">视频导图原图</label>
                        <div class="col-sm-5">
                            <img src="${videoHQInfo.videoMapImgUrl}" alt="视频导图原图" class="img-thumbnail" style="width: auto">
                        </div>
                    </div>
                    <%-- 视频介绍 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">视频介绍</label>
                        <div class="col-sm-5">
                            <script id="editor" type="text/plain"></script>
                            <input type="hidden" id="videoSynopsis" name="videoSynopsis"/>
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
    //实例化编辑器
    var videoSynopsis = '${videoHQInfo.videoSynopsis}';
    var ue = UE.getEditor( 'editor', {
        toolbars: [[
            'fullscreen', 'source', '|',,'undo', 'redo' , '|',
            'bold', 'forecolor' , 'removeformat', 'autotypeset', 'pasteplain' , '|', '|',
            'justifyleft', 'justifycenter' , '|',
            'link', 'unlink' ,  '|',
            'simpleupload','insertimage', '|',
            'wordimage', '|' ,
            'inserttable', 'insertrow' , 'deleterow', 'insertcol', 'deletecol' , 'mergecells', 'splittocells', '|' , 'mybtn1','mydialog1','scrawl'
        ]],
        autoHeightEnabled: true,
        autoFloatEnabled: true,
        initialFrameWidth: 1000,
        initialFrameHeight:500,
        initialContent: videoSynopsis
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
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
            return '${baseUrl}../communityNews/uploadImage.html?action=uploadimage';
        } else if (action == 'uploadvideo') {
            return '${baseUrl}../communityNews/uploadImage.html?action=uploadimage';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }


    //页面加载时执行
    $(function(){
        initFileInput("videoMapImgUpload", "");
        if ($("#videoMapImgUrl").val() != ""){
            $("#videoMapImg").show();
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

    //通过城市获取项目列表
    function queryProjectNameById() {
        var projectId = $("#scopeId").val();
        if(projectId == '-1'){
            $("#projectCode").empty();
            return ;
        }
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectCode").empty();
            $("#projectCode").append('<option value="">请选择</option>');
            for (var k in arr) {
                if(arr[k][0] != '0'){
                    $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
                }
            }
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
    //发布
    function toSave(status){
        if ($("#videoName").val() == "") {
            alert("请输入视频名称！");
            return;
        }
        var title = $("#videoName").val();
        if (title.length > 100) {
            alert("视频名称超过最大长度！");
            return;
        }
        if ($("#videoLinkSrc").val() == "") {
            alert("请输入视频链接！");
            return;
        }
        var content = UE.getEditor('editor').getContent();
        $("#videoSynopsis").val(content);
        $("#releaseStatus").val(status);
        $("#fromAdd").submit();
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>