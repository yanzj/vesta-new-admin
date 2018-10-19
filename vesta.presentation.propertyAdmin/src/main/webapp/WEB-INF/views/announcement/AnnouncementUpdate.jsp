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

    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">

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
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
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
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005500020000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" value="005500020000"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body" style="min-height:380px">
                    <form class="form-horizontal" id="frm" action="../announcement/addOrUpdatePage" method="post"
                          enctype="MULTIPART/FORM-DATA">
                        <input type="hidden" id="id" name="id" value="${announcementEntity.id}">
                        <input type="hidden" id="citys" name="citys" value="">
                        <input type="hidden" id="projects" name="projects" value="">
                        <input type="hidden" id="content" name="content" value="">
                        <%--城市区域--%>
                        <div class="form-group  col-lg-10">
                            <label for="city" class="col-sm-3 control-label"><spring:message
                                    code="announcementDTO.cityName"/></label>

                            <div class="col-sm-5">
                                <select id="city" name="city" class="form-control" onchange="queryProjectNameById()">
                                    <option value="-1">--请选择城市--</option>
                                    <c:forEach items="${city}" var="item" >
                                        <option value="${item.cityId }"
                                                <c:if test="${item.cityId eq '0'}">selected</c:if>>${item.cityName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-2">
                                <input type="image" name="addCity" id="addCity"
                                       value="<spring:message code="announcementDTO.add"/>"
                                       onclick="cityAdd();return false;"
                                       src="../static/images/add.ico">
                            </div>
                        </div>
                        <%--城市列表--%>
                        <div class="form-group col-lg-10">
                            <label class="col-sm-3 control-label" for="cityList"
                                   style="min-width:115px;"><spring:message
                                    code="announcementDTO.selectedCity"/> <font color="red">*</font></label>

                            <div class="col-sm-5">
                                <%--
                                <textarea class="form-control" name="cityList" id="cityList" readonly
                                          style="width: 432px; height: 71px;" ><c:if test="${allCityInScope != ''}">${allCityInScope }</c:if><c:if test="${allCityInScope == ''}">${cityInScope }</c:if></textarea>
                                --%>
                                <textarea class="form-control" name="cityList" id="cityList" readonly
                                          style="width: 432px; height: 71px;" >${cityInScope}</textarea>
                                <input type="hidden" id="cityIds" name="cityIds" value="${cityIdInScope}"/>
                            </div>

                        </div>
                        <%--城市下项目--%>
                        <div class="form-group  col-lg-10">
                            <label for="projectName" class="col-sm-3 control-label"><spring:message
                                    code="HousePayBatch.projectName"/></label>

                            <div class="col-sm-5">
                                <select id="projectName" name="projectName" class="form-control">
                                    <%--<option value="0"><spring:message code="announcementDTO.allProject"/></option>--%>
                                </select>
                            </div>
                            <div class="col-sm-2">
                                <input type="image" name="addProject" id="addProject"
                                       value="" onclick="projectAdd();return false;" src="../static/images/add.ico">
                            </div>
                        </div>

                        <%--项目列表--%>
                        <div class="form-group col-lg-10">
                            <label class="col-sm-3 control-label" for="projectList"
                                   style="min-width:115px;"><spring:message
                                    code="announcementDTO.selectedProject"/> <font color="red">*</font></label>

                            <div class="col-sm-5">
                                <textarea class="form-control" name="projectList" id="projectList" readonly
                                          style="width: 432px; height: 71px;">${projectInScope}</textarea>
                                <%--<c:if test="${allCityInScope != ''}"></c:if><c:if test="${allCityInScope == ''}">${projectInScope }</c:if>--%>
                                <input type="hidden" id="projectIds" name="projectIds"  value="${projectIdInScope}">
                            </div>

                        </div>

                        <%--公告标题--%>
                        <div class="form-group col-lg-10">
                            <label class="col-sm-3 control-label" for="title"><spring:message
                                    code="announcementDTO.title2"/> <font color="red">*</font></label>

                            <div class="col-sm-7">
                                <input type="text" class="form-control" placeholder="" id="title" name="title"
                                       onchange="checkVal()"
                                       value="${announcementEntity.title}">
                            </div>
                        </div>

                        <%--公告内容简介--%>
                        <div class="form-group  col-lg-10">
                            <label class="col-sm-3 control-label" for="title">公告内容简介 <font color="red">*</font></label>
                            <div class="col-sm-5">
                                <textarea type="text" class="form-control" placeholder="请输入内容"
                                          id="contentSynopsis" name="contentSynopsis">${announcementEntity.contentSynopsis}</textarea>
                            </div>
                        </div>
                        <!-- 是否投票 -->
                        <div class="form-group  col-lg-10">
                            <label for="isVote" class="col-sm-3 control-label"><spring:message code="announcementDTO.isVoit"/></label>
                            <div class="col-sm-5">
                                <select id="isVote" name="isVote" class="form-control" onchange="isVoteChange()">
                                    <c:forEach items="${isVote}" var="item">
                                        <option value="${item.id}"
                                                <c:if test="${item.id eq announcementEntity.isVote}">selected</c:if>>
                                                ${item.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%--投票ti--%>
                        <c:if test="${empty announcementEntity.isVote or announcementEntity.isVote eq '0'}"><div class="form-group col-lg-10" id="vote" style="display:none;"></c:if>
                        <c:if test="${announcementEntity.isVote eq '1'}"><div class="form-group col-lg-10" id="vote"></c:if>
                            <label for="voteId" class="col-sm-3 control-label"><spring:message code="vote.selectVote"/></label>
                            <div class="col-sm-5">
                                <select id="voteId" name="voteId" class="form-control">
                                    <option id="option_0" value="">请选择</option>
                                    <c:forEach items="${voteList}" var="item">
                                        <option value="${item.sid }"
                                                <c:if test="${item.sid eq vote.sid}">selected</c:if>>${item.name }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <%-- 公告内容--%>
                        <div class="form-group col-lg-10" style="z-index:0">
                            <label class="control-label" class="col-sm-3 control-label"><spring:message
                                    code="propertyAnnouncement.text"/></label>
                            <script id="editor" type="text/plain"
                                    style="width:100%;height:400px;overflow-y:auto;">${content}
                            </script>
                        </div>
                        <div class='clearfix'></div>

                        <input type="hidden" name="releaseStatus" id="releaseStatus" value="">
                        <button type="button" class="btn btn-primary" onclick="checkSubmit('1')"><spring:message
                                code="activityManage.activityPublish"/></button>
                        <button type="button" class="btn btn-primary" onclick="checkSubmit('0')"><spring:message
                                code="activityManage.activityUnPublish"/></button>
                        <a href="../announcement/List.html" class="btn btn-primary" for=""><spring:message
                                code="common_cancel"/></a>
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
    $(function(){
        console.log("sqq")
        $("#005500020000").addClass("active");
        $("#005500020000").parent().parent().addClass("in");
        $("#005500020000").parent().parent().parent().parent().addClass("active");
    })
    //是否投票_选择投票_联动
    function isVoteChange(){
        var val = $("#isVote").val();
        if(val == 0){
            $("#vote").hide();
//            $("#voteId").prepend('<option value="" selected></option>');
//            $("#voteId").attr("disabled","disabled");
        }else{
            $("#vote").show();
//            $("#voteId option").eq(0).remove();
//            $("#voteId").removeAttr("disabled");
        }
    }

    function checkVal() {
        $("#frm").validate();
    }
    function cityAdd() {
        //#1.获取内容
        var projectName = $("#city").find("option:selected").text();
        //获取隐藏域的值_Wyd_2016.06.03
        var projectId = $("#city").find("option:selected").val();
        if(projectId == '-1'){
            alert("请选择城市，并添加！");
            return;
        }
        if (projectName == "全部城市") {
            //清空textarea
            $("#cityList").val("全部城市");
            $("#cityIds").val('0,');
            //清空项目列表_Wyd_2016.08.08_数据权限
            $("#projectList").val("");
            $("#projectIds").val("");
            $("#projectName").empty();
            $("#projectName").append('<option value="0,">全部项目</option>');
        } else {
            if ($("#cityList").val() == "") {
                //如果textarea中没有元素
                $("#cityList").val($("#cityList").val() + projectName + ',');
                $("#cityIds").val($("#cityIds").val() + projectId + ',');
            } else if ($("#cityList").val() != "") {
                //判断textArea中是否有select的值，如果没有则添加
                //获取textArea数组
                var strArray = $("#cityList").val().toString().split(",");
                //获取select值
                var str = $("#city").find("option:selected").text();
                //判断是否在数组中
                if (strArray.toString().indexOf(str) > -1) {
                    return;
                }
                //判断是否="全部城市"
                if ($("#cityList").val() == "全部城市") {
                    //清空
                    $("#cityList").val("");
                    $("#cityList").val($("#cityList").val() + projectName + ',');
                    $("#cityIds").val(projectId + ',');
                    return;
                }
                //如果textarea中有元素，前置","
                $("#cityList").val($("#cityList").val() + projectName + ',');
                $("#cityIds").val($("#cityIds").val() + projectId + ',');
            }
        }
    }
    function projectAdd() {

        //#1.获取内容
        var projectName = $("#projectName").find("option:selected").text();
        var projectId = $("#projectName").find("option:selected").val();
        if (projectName == "全部项目") {
            //清空textarea
            $("#projectList").val("全部项目");
            $("#projectIds").val("0,");
        }
        if ($("#projectList").val() == "") {
            //如果textarea中没有元素
            $("#projectList").val($("#projectList").val() + projectName + ',');
            $("#projectIds").val($("#projectIds").val() + projectId + ',');
        } else if ($("#projectList").val() != "") {
            //判断textArea中是否有select的值，如果没有则添加
            //获取textArea数组
            var strArray = $("#projectList").val().toString().split(",");
            //获取select值
            var str = $("#projectName").find("option:selected").text();
            //判断是否在数组中
            if (strArray.toString().indexOf(str) > -1) {
                return;
            }
            //判断是否="全部项目"
            if ($("#projectList").val() == "全部项目") {
                //清空
                $("#projectList").val("");
                $("#projectList").val($("#projectList").val() + projectName + ',');
                $("#projectIds").val(projectId + ',');
                return;
            }
            //如果textarea中有元素，前置","
            $("#projectList").val($("#projectList").val() + projectName + ',');
            $("#projectIds").val($("#projectIds").val() + projectId + ',');
        }
    }
    function queryProjectNameById() {
        var projectId = $("#city").val();
        if(projectId == '-1'){
            $("#projectName").empty();
            return ;
        }
        $("#planName").empty();
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectName").empty();
            <%--$("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');--%>
            for (var k in arr) {
                $("#projectName").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
//                alert(arr[k][0]);
            }

        });
    }
    function checkSubmit(status) {

        if(!CheckNull($("#cityList").val(),"请添加城市！")){
            return false;
        }
        if($("#cityList").val() == ""){
            alert("请添加城市！");
            return ;
        }
        if($("#cityList").val() != "全部城市" ){
            if(!CheckNull($("#projectList").val(),"请添加项目！")){
                return false;
            }
        }
        if($("#cityList").val() != "全部城市" && $.trim($("#projectList").val()) == ""){
            alert("请添加项目！");
            return ;
        }
        if($("#isVote").val() == 1 && $("#voteId").val() == ""){
            alert("请选择投票！");
            return ;
        }
        document.getElementById("content").value = UE.getEditor('editor').getContent();
        document.getElementById("releaseStatus").value = status;
        $("#releaseStatus").val(status);
        $("#citys").val($("#cityList").val());
        $("#projects").val($("#projectList").val());
//        alert(UE.getEditor('editor').getContent());
        //document.getElementById("content").value = UE.getEditor('editor').getContent();

        $("#frm").submit();

        //var _date = $('#frm').serialize();
    }
    $().ready(function () {
        //
        var validator = $("#frm").validate({
            errorElement: "span",
            rules: {
                title: {
                    required: true
                },
                contentSynopsis: {
                    required: true
                }

            },
            messages: {
                title: {
                    required: "请输入公告标题!"
                },
                contentSynopsis: {
                    required: "请输入公告内容简介!"
                }
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
                maximumWords: 4000,
                //更多其他参数，请参考ueditor.config.js中的配置项

                initialContent: '${announcementEntity.content}'

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
    UE.Editor.prototype.getActionUrl = function (action) {
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
                result.innerHTML = '<img src="' + this.result + '" style="width:' + ww + 'px;height: ' + hh + 'px"/>';
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