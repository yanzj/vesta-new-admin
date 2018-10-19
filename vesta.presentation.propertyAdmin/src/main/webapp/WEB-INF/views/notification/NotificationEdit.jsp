<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
    </script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>

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

    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
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
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <style>
        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 70px;
        }
    </style>
    <STYLE type=text/css>
        input.error {
            border: 1px solid red;
        }
        label.error {
            background: url("./demo/images/unchecked.gif") no-repeat 0px 0px;
            padding-left: 16px;
            padding-bottom: 2px;
            font-weight: bold;
            color: #EA5200;
        }
        label.checked {
        / / background : url("./demo/images/checked.gif") no-repeat 0 px 0 px;
        }
    </STYLE>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020000" username="${propertystaff.staffName}" />
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body" style="min-height:380px">
                    <form class="form-horizontal" id="frm" action="../notification/addNotificationDetail" method="post"
                          enctype="MULTIPART/FORM-DATA">
                        <input id = "notificationStatus" type="hidden" name="notificationStatus"  value="${systemNotification.notificationStatus}">
                        <input id = "notificationId" type="hidden" name="notificationId"  value="${systemNotification.notificationId}">
                        <%--公告类型--%>
                        <div class="form-group col-xs-6">
                            <label class="col-sm-4 control-label" for="notificationType"><spring:message
                                    code="systemnotification.type"/></label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="notificationType" name="notificationType">
                                    <option value="10001" <c:if test="${systemNotification.notificationType eq '10001'}">selected</c:if>><spring:message code="systemnotification.message"/></option>
                                    <option value="10000" <c:if test="${systemNotification.notificationType eq '10000'}">selected</c:if>><spring:message code="systemnotification.other"/></option>
                                </select>
                            </div>
                        </div>

                        <%--置顶状态---%>
                        <div class="form-group col-xs-6">
                            <label class="col-sm-4 control-label" for="notificationTopStatus"><spring:message
                                    code="systemnotification.topState"/></label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="notificationTopStatus" name="notificationTopStatus">
                                    <option value="10001" <c:if test="${systemNotification.notificationTopStatus eq '10001'}">selected</c:if>><spring:message code="systemnotification.top"/></option>
                                    <option value="10000" <c:if test="${systemNotification.notificationTopStatus eq '10000'}">selected</c:if>><spring:message code="systemnotification.ntop"/></option>
                                </select>
                            </div>
                        </div>

                        <%--公告标题--%>
                        <div class="form-group col-xs-6">
                            <label class="col-sm-4 control-label" for="notificationTitle"><spring:message
                                    code="systemnotification.title"/></label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="notificationTitle" name="notificationTitle"
                                       value="${systemNotification.notificationTitle}">
                            </div>
                        </div>

                        <div class="form-group col-xs-6">
                        </div>

                        <%-- 公告内容--%>
                        <div class="form-group col-lg-12" style="z-index:0">
                            <label class="control-label col-sm-2"><spring:message code="systemnotification.content"/></label>
                            <div class="col-sm-10">
                            <script id="editor" type="text/plain"
                                    style="width:100%;height:400px;overflow-y:auto;">${systemNotification.notificationContent}
                            </script>
                            </div>
                        </div>

                        <input type="hidden" id="comment" name="notificationContent" value="">

                        <div class='clearfix'></div>

                        <div class="form-group col-xs-12" style="z-index:0">
                            <input type="hidden" name="releaseStatus" id="releaseStatus" value="">
                            <div class="form-group col-sm-4" style="z-index:0">
                            </div>
                            <div class="form-group col-sm-4" style="z-index:0">


                                <c:if test="${empty systemNotification.notificationId}">
                                    <button type="button" class="btn btn-primary" onclick="checkSubmit('10001')"><spring:message
                                        code="activityManage.activityPublish"/></button>
                                    <button type="button" class="btn btn-primary" onclick="checkSubmit('10000')"><spring:message
                                            code="activityManage.activityUnPublish"/></button>
                                </c:if>

                                <c:if test="${not empty systemNotification.notificationId}">
                                    <button type="button" class="btn btn-primary" onclick="checkSubmit('${systemNotification.notificationStatus}')"><spring:message
                                        code="common_confirm"/></button>
                                    <button type="button" class="btn btn-primary" onclick="window.location.href = '../notification/systemMessage.html'"><spring:message code="common_cancel"/></button>
                                </c:if>
                                <c:if test="${empty systemNotification.notificationId}">
                                    <button type="button" class="btn btn-primary" onclick="window.location.href = '../notification/systemMessage.html'"><spring:message code="common_cancel"/></button>
                                </c:if>
                            </div>
                            <div class="form-group col-xs-4" style="z-index:0">
                            </div>
                        </div>
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
<!-- 校验 -->
<script>
    function checkVal() {
        $("#frm").validate();
    }
    ;
    function checkSubmit(id) {
        document.getElementById("comment").value = UE.getEditor('editor').getContent();
        document.getElementById("notificationStatus").value = id;
        if(!CheckNull($("#notificationTitle").val(),"标题不能为空！")){
            return false;
        }

        if($("#notificationTitle").val()==""){
            alert("标题不能为空！");
            return false;
        }

        if ($("#frm").validate()) {
            if ($("#releaseDate").val() == "") {
                alert("请您选择日期");
                return false;
            }
            $("#frm").submit();
        }
    }
    $().ready(function () {
        //
        var validator = $("#frm").validate({
            errorElement: "span",
            rules: {
                title: {
                    required: true
                },
                releaseDate: {
                    required: true
                }
            },
            messages: {
                title: {
                    required: "请输入活动主题",
                },
                releaseDate: {
                    required: "请选择开始时间",
                },
            }
        })
    });
</script>
<!--日期控件-->
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
<!--富文本控件-->
<script type="text/javascript">
    $(function(){
        console.log("sqq")
        $("#000100020000").addClass("active");
        $("#000100020000").parent().parent().addClass("in");
        $("#000100020000").parent().parent().parent().parent().addClass("active");
    })
    //实例化编辑器 ------
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor', {
                //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
                toolbars: [[
                    "fullscreen", "source", "|", "undo", "redo", "|",
                    "bold", "italic", "underline", "fontborder", "strikethrough", "superscript", "subscript", "removeformat", "formatmatch", "autotypeset", "blockquote", "pasteplain", "|", "forecolor", "backcolor", "insertorderedlist", "insertunorderedlist", "selectall", "cleardoc", "|",
                    "rowspacingtop", "rowspacingbottom", "lineheight", "|",
                    "customstyle", "paragraph", "fontfamily", "fontsize", "|",
                    "directionalityltr", "directionalityrtl", "indent", "|",
                    "justifyleft", "justifycenter", "justifyright", "justifyjustify", "|", "touppercase", "tolowercase", "|",
                    "link", "unlink", "anchor", "|", "imagenone", "imageleft", "imageright", "imagecenter", "|",
                    "simpleupload", "insertimage", "emotion", "scrawl", "insertvideo", "music", "attachment", "map", "gmap", "insertframe", "insertcode", "pagebreak", "template", "background", "|",
                    "horizontal", "date", "time", "spechars", "snapscreen", "wordimage", "|",
                    "inserttable", "deletetable", "insertparagraphbeforetable", "insertrow", "deleterow", "insertcol", "deletecol", "mergecells", "mergeright", "mergedown", "splittocells", "splittorows", "splittocols", "charts", "|",
                    "searchreplace", "help", "drafts"
                ]],
                //focus时自动清空初始化时的内容
                autoClearinitialContent: false,
                //关闭字数统计
                wordCount: true,
                //关闭elementPath
                elementPathEnabled: true,
                //默认的编辑区域高度
                initialFrameHeight: 300,
                maximumWords: 1400,
                //更多其他参数，请参考ueditor.config.js中的配置项

                initialContent: '${systemNotificationDTO.notificationContent}'

            }
    );


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
            // return '${baseUrl}../ueditor/fileupload/upload.html';
        } else if (action == 'uploadvideo') {
            return '${baseUrl}../communityNews/uploadImage.html?action=uploadimage';
            // return '${baseUrl}../ueditor/fileupload/upload.html';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }

</script>
<!--图片上传控件-->
<script>
    function checkURL(URL) {
        var str = URL;
        var Expression = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
                + "(([0-9]{1,3}.){3}[0-9]{1,3}" + "|"
                + "([0-9a-z_!~*'()-]+.)*"
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]."
                + "[a-z]{2,6})"
                + "(:[0-9]{1,4})?"
                + "((/?)|" + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        var objExp = new RegExp(Expression);
        if (objExp.test(str) == true) {
            return true;
        } else {
            return false;
        }

    }

    var inputs = $("#homePageimgpath").get(0);
    var result = document.getElementById("circleLogoUrl");
    //var result = document.getElementById("demo_homePageimgpath");

    if (typeof FileReader === 'undefined') {
        result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
        inputs.setAttribute('disabled', 'disabled');
    } else {
        inputs.addEventListener('change', readFile, false);
    }

    function readFile() {

        for (var i = 0; i < this.files.length; i++) {
            var file = this.files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                var img = new Image();//构造JS的Image对象
                img.src = reader.result;//将本地图片赋给image对象
                var type = $("#type").val();
                var hh;
                var ww;
                if (type.toString() == "2") {
                    hh = 173;
                    ww = 260;
                } else if (type.toString() == "1") {
                    hh = 180;
                    ww = 350;
                } else if (type.toString() == "0") {
                    //hh = 215;
                    //ww = 720;
                    hh = 180;
                    ww = 350;
                } else {
                    hh = 173;
                    ww = 260;
                }
                result.innerHTML = '<img src="' + this.result + '" style="width:'+ww+'px;height: '+hh+'px"/>';
                //results.innerHTML(result.innerHTML);
            }
        }
    }

    var input = $("#activityimgpath").get(0);
    var results = document.getElementById("demo_activityimgpath");
    if (typeof FileReader === 'undefined') {
        results.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
        input.setAttribute('disabled', 'disabled');
    } else {
        input.addEventListener('change', readFiles, false);
    }

    function readFiles() {

        for (var i = 0; i < this.files.length; i++) {
            var file = this.files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                results.innerHTML = '<img src="' + this.result + '" style="width: 290px;height: 180px"/>';
            }
        }
    }

    var imgtype = true;
    function check(fnUpload) {
        var filename = fnUpload.value;
        var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
        if (mime != ".jpg" && mime != ".png" && mime != ".jpeg" && mime != ".gif") {
            alert("上传图片类型错误");
            imgtype = false;
        } else {
            imgtype = true;
        }
    }
</script>


</div>

</body>
</html>