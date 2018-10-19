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
    <script type="application/x-javascript">
        window.addEventListener("load", function () {
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
        #locationId{
            position: absolute;
            top:433px;
            left:60%;
        }
        #locationImg{
            width:500px;
        }
        .positionArea{
            position:absolute;
            z-index: 99;
            background:rgba(0,0,0,0.5);
            color: #ffe400;
            font-size:15px;
        }
        .point{
            position:absolute;
            z-index:9999;
            width:15px;
            height:15px;
            border-radius:15px;
            background-color:#ffe400;
            /*display:none;*/
        }
        .col-lg-8 {
            width: 65.3%;
        }
        .col-sm-3 {
            width: 16.8%;
        }
    </style>

    <%-- FileInput --%>
    <link href="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/css/fileinput.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/js/fileinput.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.1/js/fileinput_locale_zh.js"></script>

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

    <%-- FileInput --%>
    <link href="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/css/fileinput.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/js/fileinput.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.1/js/fileinput_locale_zh.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006400010000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body">
                    <form class="form-horizontal" id="frm" action="../readilyTake/updateRectifyReadilyTake" method="post"
                          enctype="MULTIPART/FORM-DATA">

                        <%--所属项目--%>
                        <div class="form-group col-lg-12">
                            <label class="col-sm-2 control-label" for="projectName"><spring:message
                                    code="problem.project"/></label>
                            <div class="col-sm-4">
                                <span><input type="text" name="projectName" id="projectName" value="${readilyTake.projectName}" style="width: 100%" disabled="disabled"></span>
                                <input type="hidden" name="patId" id="patId" value="${readilyTake.patId}">
                                <input type="hidden" name="state" id="state" value="waiting">
                            </div>
                        </div>

                        <%--地址--%>
                        <div class="form-group col-lg-12">
                            <label class="col-sm-2 control-label" for="address"><spring:message
                                    code="workOrders.address"/></label>

                            <div class="col-sm-4">
                                <span><input type="text" name="address" id="address" value="${readilyTake.address}" style="width: 100%" disabled="disabled"></span>
                            </div>
                        </div>

                        <%--部位--%>
                        <div class="form-group col-lg-12">
                            <label class="col-sm-2  control-label" for="examinationParts"><spring:message code="problem.location"/></label>

                            <div class="col-sm-4">
                                <span><input type="text" name="examinationParts" id="examinationParts" value="${readilyTake.examinationParts}" style="width: 100%" disabled="disabled"></span>
                            </div>
                        </div>

                        <%--严重等级--%>
                        <div class="form-group col-lg-12">
                            <label class="col-sm-2 control-label" for="severityLevel">严重等级</label>

                            <div class="col-sm-4">
                                <span><input type="text" name="severityLevel" id="severityLevel" value="${readilyTake.severityLevel}" style="width: 100%" disabled="disabled"></span>
                            </div>
                        </div>
                        <%--问题描述--%>
                        <div class="form-group col-lg-12">
                            <label class="col-sm-2 control-label" for="description"><spring:message
                                    code="problem.bewrite"/></label>

                            <div class="col-sm-4">
                                <textarea class="form-control" id="description" name="description" readonly>${readilyTake.description}</textarea>
                            </div>
                        </div>

                        <%--整改负责人--%>
                        <div class="form-group col-lg-12">
                            <label class="col-sm-2 control-label" for="rectificationPeople"><spring:message code="problem.repairmanager"/></label>

                            <div class="col-sm-4">
                                <span><input type="text" name="rectificationPeople" id="rectificationPeople" value="${readilyTake.rectificationPeople}" style="width: 100%" disabled="disabled"></span>
                            </div>
                        </div>
                        <%--整改描述--%>
                        <div class="form-group col-lg-12">
                            <label class="col-sm-2 control-label" for="descriptions"><spring:message
                                    code="problem.rectifyDesc"/></label>

                            <div class="col-sm-4">
                                <textarea class="form-control" id="descriptions" name="descriptions"></textarea>
                            </div>
                        </div>
                            <div class="col-sm-10" style="margin-left: 204px;margin-bottom: 5px">
                            <p style="color: red"><span>以下各类型图片上传请同时上传多张图片，不要多次上传！</span></p>
                            </div>
                        <%--整改图片--%>
                        <div class="form-group col-lg-12">
                            <label class="col-sm-3 control-label" for="reviewImgFile"><spring:message code="problem.image"/></label>

                            <%--<div class="col-sm-4">--%>
                                <%--<div id="reviewfileId">--%>
                                    <%--<input type="file" id="reviewImgFile" name="reviewImgFile" class="form-control"  onchange="check(this)">--%>
                                <%--</div>--%>
                                <%--<div id="reviewDemo_imgFile" style="padding-top: 15px;">--%>
                                    <%--<c:forEach items="${rectify.imageList}" var="item" varStatus="vs">--%>
                                        <%--<img id="reviewim${vs.index}" onclick="delreviewImg('${vs.index}','0')" src="${item.imageUrl}" style="width:100px" alt=""/>--%>
                                        <%--<input id="reviewih${vs.index}" type="hidden" name="reviewImage" value="${item.imageId}">--%>
                                    <%--</c:forEach>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                                <div class="col-sm-5">
                                    <input id="reviewImgFile" class="form-control" name="reviewImgFile" type="file" multiple/>
                                    <input type="hidden" id="imageUrl" name="imageUrl" value="${imageList.imageUrl}"/>
                                </div>
                        </div>
                    </div>
                <div class="text-right form-group  col-lg-5" style="padding:0;">
                    <button type="button" class="btn btn-primary" onclick="checkSubmit()"><spring:message code="problem.submit"/></button>
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
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script>
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
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
<!-- 校验 -->

<script>

    $(function(){
        initFileInput("reviewImgFile", "");
    });

    //初始化fileinput控件（第一次初始化）
    function initFileInput(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png', 'bmp'],//接收的文件后缀
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

    function checkSubmit() {

        if($("#descriptions").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入整改描述！");
            return;
        }
        $("#frm").submit();
    }

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

    var fileCount = 1;
    var inputs = $("#f1").get(0);
    var result = document.getElementById("demo_imgFile");
    if (typeof FileReader === 'undefined') {
        result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
        inputs.setAttribute('disabled', 'disabled');
    } else {
        inputs.addEventListener('change', readFile, false);
    }

    function readFile() {
        var beforAdd = fileCount;
        fileCount++;
        for (var i = 0; i < this.files.length; i++) {
            var file = this.files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                result.innerHTML += '<img id="i'+beforAdd+'" onclick="delImg('+beforAdd+',"1")" src="' + this.result + '" style="width:100px" alt=""/>';
            }
            file = '<input type="file" id="f'+fileCount+'" name="imgFile" onchange="check(this)" class="form-control" style="width: 300px;">';
            $("#addfileId").append(file);
            var f = $("#f"+fileCount).get(0);
            f.addEventListener('change', readFile, false);
        }
    }
    function delImg(obj,type){
        if(type=='1'){
            $("#f"+obj).remove();
            $("#i"+obj).remove();
        }else{
            $("#im"+obj).remove();
            $("#ih"+obj).remove();
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

    <c:if test="${problem.rectifyState ne '草稿' && problem.rectifyState ne '待接单'}">
    var reviewfileCount = 1;
    var reviewinputs = $("#reviewf1").get(0);
    var reviewresult = document.getElementById("reviewDemo_imgFile");
    if (typeof FileReader === 'undefined') {
        reviewresult.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
        reviewinputs.setAttribute('disabled', 'disabled');
    } else {
        reviewinputs.addEventListener('change', readReviewFile, false);
    }

    function readReviewFile() {
        var beforAdd = reviewfileCount;
        reviewfileCount++;
        for (var i = 0; i < this.files.length; i++) {
            var file = this.files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                reviewresult.innerHTML += '<img id="reviewi'+beforAdd+'" onclick="delreviewImg('+beforAdd+',"1")" src="' + this.result + '" style="width:100px" alt=""/>';
            }
            file = '<input type="file" id="reviewf'+reviewfileCount+'" name="reviewImgFile" onchange="check(this)" class="form-control" style="width: 300px;">';
            $("#reviewfileId").append(file);
            var f = $("#reviewf"+reviewfileCount).get(0);
            f.addEventListener('change', readReviewFile, false);
        }
    }
    function delreviewImg(obj,type){
        if(type=="1"){
            $("#reviewf"+obj).remove();
            $("#reviewi"+obj).remove();
        }else{
            $("#reviewim"+obj).remove();
            $("#reviewih"+obj).remove();
        }
    }
    </c:if>

    $("#classifyOne").change(function(){
        var classifyOne = $("#classifyOne").val();
        $.ajax({
            url:"../problem/getSecondTypeList",
            type:"get",
            async:"false",
            data:{ "classifyOne":classifyOne},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    if(data != null){
                        document.getElementById("classifyTwo").innerHTML = "";
                        for(var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }
                        }
                        $("#classifyTwo").append(option);
                    }
                }
            }
        });
    });



    $("#classifyTwo").change(function(){
        var classifyTwo = $("#classifyTwo").val();
        $.ajax({
            url:"../problem/getThirdTypeList",
            type:"get",
            async:"false",
            data:{ "classifyTwo":classifyTwo},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    if(data != null){
                        document.getElementById("classifyThree").innerHTML = "";
                        for(var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }
                        }
                        $("#classifyThree").append(option);
                    }
                }
            }
        });
    });

    $("#classifyThree").change(function(){
        var classifyThree = $("#classifyThree").val();
        $.ajax({
            url:"../problem/getProblmetype",
            type:"get",
            async:"false",
            data:{ "classifyThree":classifyThree},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    if(data != null){
                        document.getElementById("problemtypediv").innerHTML = "";
                        for(var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<input type='checkbox' name='problemtype' id='problemtype' value='"+prop+"'>"+data[prop];
                            }
                        }
                        $("#problemtypediv").append(option);
                    }
                }
            }
        });
    });
</script>
</div>

</body>
</html>