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
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005300010000" username="${propertystaff.staffName}" />
    <input type="hidden" id="menuId" value="005300010000"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body" style="min-height:380px">
                    <form class="form-horizontal" id="frm" action="../communityNews/updateNews" method="post"
                          enctype="MULTIPART/FORM-DATA">
                        <input type="hidden" id="id" name="id" value="${newsDetail.id}">
                        <input type="hidden" id="type" name="type" value="${newsDetail.type}">
                        <input type="hidden" id="imgStatus" name="imgStatus" value="1">

                        <input type="hidden" id="citys" name="citys" value="">
                        <input type="hidden" id="projects" name="projects" value="">
                        <%--城市区域--%>
                        <div class="form-group  col-xs-8">
                            <label for="city" class="col-sm-3 control-label"><spring:message
                                    code="announcementDTO.cityName"/></label>
                            <div class="col-sm-7">
                                <select id="city" name="city" class="form-control" onchange="queryProjectNameById()">
                                    <option value="-1">--请选择城市--</option>
                                    <c:forEach items="${city}" var="item" >
                                        <option value="${item.cityId }"
                                                <c:if test="${item.cityId eq '-1'}">selected</c:if>>${item.cityName }</option>
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
                        <div class="form-group col-xs-8">
                            <label class="col-sm-3 control-label" for="cityList"
                                   style="min-width:115px;"><spring:message
                                    code="announcementDTO.selectedCity"/></label>

                            <div class="col-sm-7">
                                <%--<textarea class="form-control" name="cityList" id="cityList" readonly
                                          style="width: 432px; height: 71px;" ><c:if test="${allCityInScope != ''}">${allCityInScope }</c:if><c:if test="${allCityInScope == ''}">${cityInScope }</c:if></textarea>--%>
                                    <textarea class="form-control" name="cityList" id="cityList" readonly
                                              style=" height: 71px;" >${cityInScope}</textarea>
                                    <input type="hidden" id="cityIds" name="cityIds" value="${cityIdInScope}"/>
                            </div>

                        </div>
                        <%--城市下项目--%>
                        <div class="form-group  col-xs-8">
                            <label for="projectName" class="col-sm-3 control-label"><spring:message
                                    code="HousePayBatch.projectName"/></label>

                            <div class="col-sm-7">
                                <select id="projectName" name="projectName" class="form-control">
                                    <option value="-1">--请选择项目--</option>
                                </select>
                            </div>
                            <div class="col-sm-2">
                                <input type="image" name="addProject" id="addProject"
                                       value="" onclick="projectAdd();return false;" src="../static/images/add.ico">
                            </div>
                        </div>

                        <%--项目列表--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-3 control-label" for="projectList"
                                   style="min-width:115px;"><spring:message
                                    code="announcementDTO.selectedProject"/></label>

                            <div class="col-sm-7">
                                <textarea class="form-control" name="projectList" id="projectList" readonly
                                          style="height: 71px;"><c:if test="${allCityInScope != ''}"></c:if><c:if test="${allCityInScope == ''}">${projectInScope }</c:if></textarea>
                                <input type="hidden" id="projectIds" name="projectIds"  value="${projectIdInScope}">
                            </div>

                        </div>

                        <%--新闻标题--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-3 control-label" for="title"><spring:message
                                    code="CommunityNews.newsInformation"/></label>

                            <div class="col-sm-7">
                                <input type="text" class="form-control" placeholder="" id="title" name="title"
                                       onchange="checkVal()"
                                       value="${newsDetail.title}">
                            </div>
                        </div>

                        <%--发布时间--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-3 control-label" for="releaseDate"><spring:message
                                    code="rental.timeview"/></label>

                            <div class="input-group date form_datetime col-sm-7" data-date=""
                                 data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input1">
                                <input type="text" class="form-control " placeholder="" id="releaseDate"
                                       value="${releaseDateFormat}"
                                       name="releaseDate" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>

                        <%--主页图片--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-3 control-label" for="homePageimgpath"><spring:message
                                    code="activityManage.homePageimgpath"/></label>

                            <div class="col-sm-7">
                                <input type="file" class="form-control" placeholder="" id="homePageimgpath"
                                       name="homePageimgpath" value="" style="" onchange="check(this)">

                                <p style="color: red">
                                    <c:if test="${newsDetail.type eq 0}">
                                        <span><spring:message code="CommunityOverview.imgType0"/></span>
                                    </c:if>
                                    <c:if test="${newsDetail.type eq 1}">
                                        <span><spring:message code="CommunityOverview.imgType1"/></span>
                                    </c:if>
                                    <c:if test="${newsDetail.type eq 2 }">
                                        <span><spring:message code="CommunityOverview.imgType2"/></span>
                                    </c:if>
                                    <c:if test="${newsDetail.type == null}">
                                        <span><spring:message code="CommunityOverview.imgType2"/></span>
                                    </c:if>
                                </p>
                                <!-- 回显图片 -->
                                <div class="form-group col-lg-5" style="position: absolute;left: 500px;top: -125px;">
                                    <!--用于判断是否修改操作-->
                                    <input type="hidden" id="isEdit" value="${isEdit}"/>
                                    <!--用于判断是否有图，有图可以不做修改-->
                                    <input type="hidden" id="isEditNewsImg" value="${newsDetail.newsImg}"/>

                                    <div class="col-sm-9" id="circleLogoUrl" name="circleLogoUrl">
                                        <c:if test="${newsDetail.newsImg!=''&& newsDetail.newsImg!=null}">
                                            <img src="${newsDetail.newsImg}" />
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <%--选择图片的图片显示div--%>
                            <div id="demo_homePageimgpath" style="padding-top: 5px;">
                            </div>

                        </div>

                        <%-- 新闻编辑--%>
                        <div class="form-group col-xs-12" style="z-index:0">
                            <label class="control-label"><spring:message code="propertyAnnouncement.text"/></label>
                            <script id="editor" type="text/plain" style="width:100%;height:400px;overflow-y:auto;"></script>
                        </div>
                        <input type="hidden" id="comment" name="comment" value="">
                        <div class='clearfix'></div>
                        <input type="hidden" name="releaseStatus" id="releaseStatus" value="">
                        <button type="button" class="btn btn-primary" onclick="checkSubmit('1')"><spring:message
                                code="activityManage.activityPublish"/></button>
                        <button type="button" class="btn btn-primary" onclick="checkSubmit('0')"><spring:message
                                code="activityManage.activityUnPublish"/></button>
                        <a href="../communityNews/NewsList.html?menuId=005300010000" class="btn btn-primary" for=""><spring:message
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
<script type="text/javascript">
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
                $("#cityList").val($("#cityList").val() + projectName);
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
                    $("#cityList").val($("#cityList").val() + projectName);
                    $("#cityIds").val(projectId + ',');
                    return;
                }
                //如果textarea中有元素，前置","
                $("#cityList").val($("#cityList").val() + ',' + projectName);
                $("#cityIds").val($("#cityIds").val() + projectId + ',');
            }
        }
    }
    function projectAdd() {

        //#1.获取内容
        var projectName = $("#projectName").find("option:selected").text();
        var projectId = $("#projectName").find("option:selected").val();
        if (projectId == '-1') {
            alert("请选择项目，并添加！");
            return;
        }
        if (projectName == "全部项目") {
            //清空textarea
            $("#projectList").val("全部项目");
            $("#projectIds").val("0,");
        } else {
            if ($("#projectList").val() == "") {
                //如果textarea中没有元素
                $("#projectList").val($("#projectList").val() + projectName);
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
                    $("#projectList").val($("#projectList").val() + projectName);
                    $("#projectIds").val(projectId + ',');
                    return;
                }
                //如果textarea中有元素，前置","
                $("#projectList").val($("#projectList").val() + ',' + projectName);
                $("#projectIds").val($("#projectIds").val() + projectId + ',');
            }
        }
    }
    function queryProjectNameById() {
        var projectId = $("#city").val();
        if(projectId == '-1'){
            $("#projectName").empty();
            $("#projectName").append('<option value="-1">--请选择项目--</option>');
            return;
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
    ;
    function checkSubmit(status) {
        if($("#cityList").val() == ""){
            alert("请添加城市！");
            return ;
        }
        if($("#cityList").val() != "全部城市" && $.trim($("#projectList").val()) == ""){
            alert("请添加项目！");
            return ;
        }
        if ($("#title").val() == "") {
            alert("请输入活动主题（新闻资讯）！");
            return;
        }
        var title = $("#title").val();
        if (title.length > 127) {
            alert("活动主题（新闻资讯）超过最大长度！");
            return;
        }
        if ($("#releaseDate").val() == "") {
            alert("请您选择日期");
            return false;
        }
        var homePageimgpath = $("#homePageimgpath").val();
        if(homePageimgpath==null||homePageimgpath==undefined||homePageimgpath==''){
            //判断是否是修改
            if($("#isEdit").val()!="y") {
                alert("请选择首页推荐图");
                return;
            }
            //判断是否是有图
            if ($("#isEditNewsImg").val()==null||$("#isEditNewsImg").val()==undefined||$("#isEditNewsImg").val()=='') {
                alert("请选择首页推荐图");
                return;
            }
        }
        var comment = UE.getEditor('editor').getContent().length;
//        if(comment>=5000){
//            alert("内容过长(请不要拖拽、粘贴图片,按照编辑器正确方式使用图片上传)!");
//            return;
//        }
        /*document.getElementById("content").value = UE.getEditor('editor').getContent();
        document.getElementById("releaseStatus").value = status;
        $("#releaseStatus").val(status);*/
        document.getElementById("comment").value = UE.getEditor('editor').getContent();
        document.getElementById("releaseStatus").value = status;
        $("#releaseStatus").val(status);
        $("#citys").val($("#cityList").val());
        $("#projects").val($("#projectList").val());
        /*alert($("#cityList").val());
        alert($("#projectList").val());*/
        if ($("#frm").validate()) {
            $("#frm").submit();
        }
//        alert(UE.getEditor('editor').getContent());
        //document.getElementById("content").value = UE.getEditor('editor').getContent();

//        $("#frm").submit();
        //var _date = $('#frm').serialize();
    }
</script>
<script>
        //动态设置回显图片样式
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
        $("#circleLogoUrl :first-child").attr({ height: hh + "px", width: ww + "px" });
</script>
<!-- 校验 -->
<script>
    function checkVal() {
        $("#frm").validate();
    }
    ;
//    function checkSubmit(status) {
//        document.getElementById("comment").value = UE.getEditor('editor').getContent();
//        document.getElementById("releaseStatus").value = status;
//        $("#releaseStatus").val(status);
//        if ($("#frm").validate()) {
//            if ($("#releaseDate").val() == "") {
//                alert("请您选择日期");
//                return false;
//            }
//            $("#frm").submit();
//        }
//        //var _date = $('#frm').serialize();
//    }
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
//        language: 'zh-CN',
//        weekStart: 1,
//        todayBtn: 1,
//        autoclose: 1,
//        todayHighlight: 1,
//        startView: 2,
//        forceParse: 0,
//        showMeridian: 1
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
<!--富文本控件-->
<script type="text/javascript">
    $(function(){
        $("#005300010000").addClass("active");
        $("#005300010000").parent().parent().addClass("in");
        $("#005300010000").parent().parent().parent().parent().addClass("active");
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
                //取消全屏滚动条，仅设置富文本滚动条
                autoHeightEnabled: false,
                //关闭字数统计
                wordCount: true,
                //关闭elementPath
                elementPathEnabled: true,
                //默认的编辑区域高度
                initialFrameHeight: 300,
                maximumWords: 1400,
                //更多其他参数，请参考ueditor.config.js中的配置项

                initialContent: '${newsDetail.comment}'

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