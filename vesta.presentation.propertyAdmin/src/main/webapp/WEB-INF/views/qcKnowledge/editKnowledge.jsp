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
        $(function () {
            $("#007700010000").addClass("active");
            $("#007700010000").parent().parent().addClass("in");
            $("#007700010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--下拉框可模糊筛选-->
    <link href="../static/css/chosen.css" rel='stylesheet' type='text/css'/>
    <script src="../static/js/chosen.jquery.min.js"></script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <%-- ueditor --%>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/plus/editer/ueditor.all.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <link href="../static/editer/themes/iframe.css" rel="stylesheet">
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <style type="text/css">#dialog{display:none;}</style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007700010000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../qcKnowledge/edit.html" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="id" name="id" value="${knowledge.id}">
                    <input type="hidden" name="createDate" value="${knowledge.createDate}">
                    <input type="hidden" name="userId" value="${knowledge.userId}">
                    <input type="hidden" name="userName" value="${knowledge.userName}">
                    <input type="hidden" name="visits" value="${knowledge.visits}">
                    <input type="hidden" name="state" value="${knowledge.state}">
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">知识库类型：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <input type="radio" name="knowledgeType" class="knowledgeType" onclick="shabi(1)" value="工作方法" >工作方法
                            <input type="radio" name="knowledgeType" class="knowledgeType" onclick="shabi(2)" value="工作成果" style="margin-left: 20px">工作成果
                            <input type="radio" name="knowledgeType" class="knowledgeType" onclick="shabi(3)" value="其他" style="margin-left: 20px">其他
                            <input type="text" name="knowledgeTypeOther" id = "retarded" value="${knowledge.knowledgeType}">
                        </div>
                    </div>
                    <div class="form-group col-lg-7" style="margin-top: 7px">
                        <label class="col-sm-3 control-label">内容类型：</label>
                        <div class="col-sm-5">
                            <input type="radio" name="contentType" class="contentType" onclick="check(1)" value="1">图文
                            <input type="radio" name="contentType" class="contentType" onclick="check(2)" value="2" style="margin-left: 20px">pdf
                            <input type="radio" name="contentType" class="contentType" onclick="check(3)" value="3" style="margin-left: 20px">视频
                            <input type="radio" name="contentType" class="contentType" onclick="check(4)" value="4" style="margin-left: 20px">视频-外链
                        </div>
                    </div>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">标题：</label>
                        <div class="col-sm-7" style="margin-top: 7px">
                            <input style="width: 100%" type="text" name="title" id="title" value="${knowledge.title}">
                        </div>
                    </div>
                    <div id="div_videoUrl" class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">视频链接：</label>
                        <div class="col-sm-7" style="margin-top: 7px">
                            <input style="width: 100%" type="text" name="videoUrl" id="videoUrl" value="${knowledge.videoUrl}">
                        </div>
                    </div>
                    <div id="div_videoUrl_out" class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">视频-外链：</label>
                        <div class="col-sm-7" style="margin-top: 7px">
                            <input style="width: 100%;" type="text" name="outVideoUrl" id="outVideoUrl" value="${knowledge.outVideoUrl}">
                        </div>
                    </div>
                    <div id="div_content" class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">内容：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <script id="editor" type="text/plain"></script>
                            <input type="hidden" id="content" name="content" value="${knowledge.content}"/>
                        </div>
                    </div>
                    <div id="div_fileName" class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">上传附件：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <input type="file" class="form-control" name="file" id="upload" multiple/>
                            <p style="color: red"><span>只能添加PDF格式文件</span></p>
                        </div>
                    </div>
                    <div class="text-center form-group  col-lg-6">
                        <button type="button" class="btn btn-primary" onclick="edit()">发布</button>
                        <button type="button" class="btn btn-primary" <c:if test="${empty knowledge.id}">onclick="javascript:history.go(-2);"</c:if> <c:if test="${not empty knowledge.id}">onclick="javascript:history.go(-1);"</c:if>>
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
</div>

<%@ include file="../main/foolter.jsp" %>
</div>
<script type="text/javascript">
    window.onload=function(){
        var knowledgeType = '${knowledge.knowledgeType}';
        var contentType = '${knowledge.contentType}';
        if(knowledgeType == '工作方法'){
            $("#retarded").hide();
            $("input[name='knowledgeType']").get(0).checked=true;
            $("input[name='knowledgeType']").get(1).checked=false;
            $("input[name='knowledgeType']").get(2).checked=false;
        }else if(knowledgeType == '工作成果'){
            $("#retarded").hide();
            $("input[name='knowledgeType']").get(0).checked=false;
            $("input[name='knowledgeType']").get(1).checked=true;
            $("input[name='knowledgeType']").get(2).checked=false;
        }else if(knowledgeType != '工作成果' && knowledgeType != '工作方法' && knowledgeType != '' && knowledgeType != null){
            $("#retarded").show();
            $("input[name='knowledgeType']").get(0).checked=false;
            $("input[name='knowledgeType']").get(1).checked=false;
            $("input[name='knowledgeType']").get(2).checked=true;
            $("#retarded").val(knowledgeType);
        }else{
            $("#retarded").hide();
            $("input[name='knowledgeType']").get(0).checked=true;
            $("input[name='knowledgeType']").get(1).checked=false;
            $("input[name='knowledgeType']").get(2).checked=false;
        }
        if(contentType == 1){
            $("input[name='contentType']").get(0).checked=true;
            $("input[name='contentType']").get(1).checked=false;
            $("input[name='contentType']").get(2).checked=false;
            $("input[name='contentType']").get(3).checked=false;
        }else if(contentType == 2){
            $("input[name='contentType']").get(0).checked=false;
            $("input[name='contentType']").get(1).checked=true;
            $("input[name='contentType']").get(2).checked=false;
            $("input[name='contentType']").get(3).checked=false;
        }else if(contentType == 3){
            $("input[name='contentType']").get(0).checked=false;
            $("input[name='contentType']").get(1).checked=false;
            $("input[name='contentType']").get(2).checked=true;
            $("input[name='contentType']").get(3).checked=false;
        }else if(contentType == 4){
            $("input[name='contentType']").get(0).checked=false;
            $("input[name='contentType']").get(1).checked=false;
            $("input[name='contentType']").get(2).checked=false;
            $("input[name='contentType']").get(3).checked=true;
        }else{
            $("input[name='contentType']").get(0).checked=true;
            $("input[name='contentType']").get(1).checked=false;
            $("input[name='contentType']").get(2).checked=false;
            $("input[name='contentType']").get(3).checked=false;
        }
        check(contentType);
    };
</script>
<script type="text/javascript">
    //实例化编辑器
    var content = '${knowledge.content}';
    var ue = UE.getEditor( 'editor', {
        toolbars: [[
            'source', '|',,'undo', 'redo' , '|',
            'bold', 'forecolor' , 'removeformat', 'autotypeset', 'pasteplain' , '|', '|',
            'justifyleft', 'justifycenter' , '|',
            'link', 'unlink' ,  '|',
            'simpleupload','insertimage', '|',
            'wordimage', '|' ,
            'inserttable', 'insertrow' , 'deleterow', 'insertcol', 'deletecol' , 'mergecells', 'splittocells', '|' , 'mybtn1','mydialog1','scrawl'
        ]],
        autoHeightEnabled: false,
        autoFloatEnabled: true,
        zIndex:99,
        initialFrameWidth: 1000,
        initialFrameHeight: 500,
        initialContent: content
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
            return '${baseUrl}../qcKnowledge/uploadImage.html?action=uploadimage';
        } else if (action == 'uploadvideo') {
            return '${baseUrl}../qcKnowledge/uploadImage.html?action=uploadimage';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }

    //发布
    function edit(){
        if($("#title").val() == null || $("#title").val() == ""){
            alert("请输入标题！");
            return;
        }
        if($("input[name='contentType']:checked").val() == 2){
            var filePath = document.getElementById("upload").value;
            if (filePath == null || filePath == '') {
                alert("请选择要上传的PDF文件");
                return false;
            }else{
                var fileExtend = filePath.substring(filePath.lastIndexOf('.')).toLowerCase();
                if (fileExtend == '.pdf') {
                } else {
                    alert("文件格式需为'.pdf格式'");
                    return false;
                }
            }
        }else if ($("input[name='contentType']:checked").val() == 3){
            if($("#videoUrl").val() == null || $("#videoUrl").val() == ''){
                alert("请输入视频链接");
                return false;
            }
        }else if ($("input[name='contentType']:checked").val() == 4){
            if($("#outVideoUrl").val() == null || $("#outVideoUrl").val() == ''){
                alert("请输入视频外链");
                return false;
            }
        }
        var content = UE.getEditor('editor').getContent();
        $("#content").val(content);
        $("#fromAdd").submit();
    }
    function shabi(param) {
        if(3 == param){
            $("#retarded").show();
        }else{
            $("#retarded").hide();
        }
    }
    function check(param) {
        if(1 == param){
            $("#div_videoUrl").hide();
            $("#div_fileName").hide();
            $("#div_content").show();
            $("#div_videoUrl_out").hide();
        }else if(2 == param){
            $("#div_videoUrl").hide();
            $("#div_content").hide();
            $("#div_fileName").show();
            $("#div_videoUrl_out").hide();
        }else if(3 == param){
            $("#div_fileName").hide();
            $("#div_videoUrl").show();
            $("#div_content").show();
            $("#div_videoUrl_out").hide();
        }else if(4 == param){
            $("#div_fileName").hide();
            $("#div_videoUrl").hide();
            $("#div_content").hide();
            $("#div_videoUrl_out").show();
        }else {
            $("#div_videoUrl").hide();
            $("#div_fileName").hide();
            $("#div_content").show();
            $("#div_videoUrl_out").hide();
        }
    }
</script>
<style>
    /*.feedBackImg{
        width: 100px;
    }*/
    .knowledgeDetail{
        font-size: 15px;
        margin-left: 10px;
    }
    .search-form {
        border-radius: inherit;
        height: 30px;
        padding: 0 10px;
    }
</style>
</body>

</html>