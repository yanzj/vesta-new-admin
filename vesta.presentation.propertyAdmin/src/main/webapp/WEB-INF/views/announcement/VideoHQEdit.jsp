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
    <%-- wangEditor --%>
    <script type="text/javascript" charset="utf-8" src="../static/editer/wangEditor.min.js"></script>
    <style type="text/css">
        .toolbar {
            border: 1px solid #ccc;
        }
        .text {
            border: 1px solid #ccc;
            height: 500px;
        }
    </style>
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
                        <div class="col-sm-9">
                            <%--
                            <div id="editor">
                                <p>${videoHQInfo.videoSynopsis}</p>
                            </div>
                            --%>
                            <div id="divToolbar" class="toolbar"></div>
                            <div style="padding: 2px 0; color: #ccc"></div>
                            <div id="divText" class="text"> <!--可使用 min-height 实现编辑区域自动增加高度-->
                                <p>${videoHQInfo.videoSynopsis}</p>
                            </div>
                            <input type="hidden" name="videoSynopsis" id="videoSynopsis"/>
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
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    var E = window.wangEditor
//    var editor = new E('#editor')
    var editor = new E('#divToolbar', '#divText')
    // 或者 var editor = new E( document.getElementById('editor') )
    // 自定义菜单配置
    editor.customConfig.menus = [
        'head',
        'bold',
        'italic',
        'underline',
        'strikeThrough',  // 删除线
        'list',  // 列表
        'justify',  // 对齐方式
        'image',  // 插入图片
        'undo',  // 撤销
        'redo'  // 重复
    ]
    // 隐藏"网络图片"tab
    editor.customConfig.showLinkImg = false
    editor.customConfig.uploadFileName = 'upfile'
    editor.customConfig.uploadImgServer = '${baseUrl}../communityNews/uploadImage2.html?action=uploadimage'  // 上传图片到服务器
    editor.customConfig.uploadImgHooks = {
        before: function (xhr, editor, files) {
            // 图片上传之前触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件

            // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
            // return {
            //     prevent: true,
            //     msg: '放弃上传'
            // }
        },
        success: function (xhr, editor, result) {
            // 图片上传并返回结果，图片插入成功之后触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        fail: function (xhr, editor, result) {
            // 图片上传并返回结果，但图片插入错误时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        error: function (xhr, editor) {
            // 图片上传出错时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        timeout: function (xhr, editor) {
            // 图片上传超时时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
        // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
        customInsert: function (insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            if (result.state == 'SUCCESS'){
                var url = result.url;
                insertImg(url);
            }else{
                alert(result.state);
            }
            // result 必须是一个 JSON 格式字符串！！！否则报错
        }
    }
    editor.create();
</script>
<script type="text/javascript">
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
        var content = editor.txt.html();
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