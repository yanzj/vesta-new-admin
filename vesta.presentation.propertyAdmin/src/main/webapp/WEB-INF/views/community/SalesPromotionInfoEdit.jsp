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
            $("#006000010000").addClass("active");
            $("#006000010000").parent().parent().addClass("in");
            $("#006000010000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="006000010000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="006000010000"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../salesPromotionInfo/saveOrUpdateSalesPromotionInfo.html" method="post" enctype="MULTIPART/FORM-DATA">
                    <input type="hidden" id="id" name="id" value="${salesPromotionInfo.id}">
                    <input type="hidden" id="clientId" name="clientId" value="3">
                    <%--城市区域--%>
                    <div class="form-group  col-lg-7">
                        <label for="city" class="col-sm-3 control-label"><spring:message code="announcementDTO.cityName"/></label>
                        <div class="col-sm-5">
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
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="cityList" style="min-width:115px;"><spring:message code="announcementDTO.selectedCity"/></label>
                        <div class="col-sm-5">
                            <textarea class="form-control" name="cityList" id="cityList" readonly
                                      style="width: 432px; height: 71px;" >${salesPromotionInfo.citys}</textarea>
                            <input type="hidden" id="cityIds" name="cityIds" value="${salesPromotionInfo.cityIds}"/>
                            <input type="hidden" id="citys" name="citys"/>
                        </div>
                    </div>
                    <%--城市下项目--%>
                    <div class="form-group  col-lg-7">
                        <label for="projectName" class="col-sm-3 control-label"><spring:message code="HousePayBatch.projectName"/></label>
                        <div class="col-sm-5">
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
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="projectList" style="min-width:115px;"><spring:message code="announcementDTO.selectedProject"/></label>
                        <div class="col-sm-5">
                                <textarea class="form-control" name="projectList" id="projectList" readonly
                                          style="width: 432px; height: 71px;">${salesPromotionInfo.projects }</textarea>
                            <input type="hidden" id="projectIds" name="projectIds"  value="${salesPromotionInfo.projectIds}">
                            <input type="hidden" id="projects" name="projects"/>
                        </div>
                    </div>
                    <%-- 促销信息 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="title">促销信息</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="title" name="title" value="${salesPromotionInfo.title}">
                        </div>
                    </div>
                    <%-- 发布时间 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="releaseDate"><spring:message code="rental.timeview"/></label>
                        <div class="input-group date form_datetime col-sm-5" data-date=""
                             data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input1">
                            <input type="text" class="form-control " placeholder="" id="releaseDate"
                                   value="${salesPromotionInfo.releaseDate}"
                                   name="releaseDate" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <%-- 促销信息标识图 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">促销信息标识图</label>
                        <div class="col-sm-5">
                            <input id="infoSignImgUpload" name="infoSignImgFile" type="file" multiple/>
                            <input type="hidden" id="infoSignImgUrl" name="infoSignImgUrl" value="${salesPromotionInfo.infoSignImgUrl}"/>
                        </div>
                    </div>
                    <%-- 促销信息标识图原图 --%>
                    <div id="infoSignImg" class="form-group col-lg-7" style="display: none">
                        <label class="col-sm-3 control-label">促销信息标识原图</label>
                        <div class="col-sm-5">
                            <img src="${salesPromotionInfo.infoSignImgUrl}" alt="促销信息标识原图" class="img-thumbnail" style="width: auto">
                        </div>
                    </div>
                    <%-- 发布客户端(暂无此需求) --%>
                    <%--
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label" for="clientId">发布客户端</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" placeholder="" id="clientId" name="clientId" value="${salesPromotionInfo.clientId}">
                        </div>
                    </div>
                    --%>
                    <%--是否有外链--%>
                    <div class="form-group  col-lg-7">
                        <label for="isLink" class="col-sm-3 control-label">是否有外链</label>
                        <div class="col-sm-5">
                            <select class="form-control" placeholder="" id="isLink" name="isLink" onchange="isLinkOnChange()">
                                <option value="0" <c:if test="${salesPromotionInfo.isLink eq 0}">selected</c:if>>否</option>
                                <option value="1" <c:if test="${salesPromotionInfo.isLink eq 1}">selected</c:if>>是</option>
                            </select>
                        </div>
                    </div>
                    <%--外链地址--%>
                    <div class="form-group col-lg-7" id="linkSrcDiv" <c:if test="${empty salesPromotionInfo.isLink || salesPromotionInfo.isLink eq 0}">style="display:none;"</c:if>>
                        <label class="col-sm-3 control-label" for="linkSrc">外链地址</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="linkSrc" name="linkSrc" value="${salesPromotionInfo.linkSrc}">
                        </div>
                    </div>
                    <%-- 是否作为宣传信息展示 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">是否作为宣传信息展示</label>
                        <div class="col-sm-5">
                            <input type="radio" name="isBanner" id="isBanner_1" value="0" <c:if test="${salesPromotionInfo.isBanner ne '1'}">checked</c:if>>否
                            <input type="radio" name="isBanner" id="isBanner_2" value="1" <c:if test="${salesPromotionInfo.isBanner eq '1'}">checked</c:if>>是
                        </div>
                    </div>
                    <%-- 促销详情 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">促销详情</label>
                        <div class="col-sm-5">
                            <script id="editor" type="text/plain"></script>
                            <input type="hidden" id="content" name="content"/>
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
    var salesPromotionInfoContent = '${salesPromotionInfo.content}';
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
        initialContent: salesPromotionInfoContent
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
        initFileInput("infoSignImgUpload", "");
        if ($("#infoSignImgUrl").val() != ""){
            $("#infoSignImg").show();
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

    //发布
    function toSave(status){
        if($("#cityList").val() == ""){
            alert("请添加城市！");
            return;
        }
        if($("#cityList").val() != "全部城市" && $.trim($("#projectList").val()) == ""){
            alert("请添加项目！");
            return;
        }
        if ($("#title").val() == "") {
            alert("请输入促销信息！");
            return;
        }
        var title = $("#title").val();
        if (title.length > 100) {
            alert("促销信息超过最大长度！");
            return;
        }
        if ($("#releaseDate").val() == "") {
            alert("请选择发布时间！");
            return;
        }
//        formData.append("infoSignImgUpload",$("#infoSignImgUpload")[0].files[0]);
//        formData.append("infoSignImgUrl",$("#infoSignImgUrl").val());
        $("#citys").val($("#cityList").val());
        $("#projects").val($("#projectList").val());
        var content = UE.getEditor('editor').getContent();
        $("#content").val(content);
        $("#releaseStatus").val(status);
        $("#fromAdd").submit();
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
            for (var k in arr) {
                $("#projectName").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
            }
        });
    }
    function isLinkOnChange(){
        if ($("#isLink").val() == "1"){
            $("#linkSrcDiv").show();
        }else{
            $("#linkSrcDiv").hide();
            $("#linkSrc").val("");
        }
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
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>