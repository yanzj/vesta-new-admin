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
            $("#005500050000").addClass("active");
            $("#005500050000").parent().parent().addClass("in");
            $("#005500050000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="005500050000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="005500050000"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../butlerStyle/saveOrUpdateButlerStyle.html" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="butlerId" name="butlerId" value="${butlerStyle.butlerId}">
                    <%-- 管家名称 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label" for="butlerNum">管家名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" placeholder="" id="butlerNum" name="butlerNum" value="${butlerStyle.butlerNum}">
                        </div>
                    </div>
                    <%-- 真实姓名 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label" for="realName">真实姓名</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" placeholder="" id="realName" name="realName" value="${butlerStyle.realName}">
                        </div>
                    </div>
                    <%-- 累计评分 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label">累计评分</label>
                        <div class="col-sm-4">
                            <c:set var="butlerScore"></c:set>
                            <c:if test="${empty butlerStyle.butlerScore}">
                                <c:set var="butlerScore" value="4.0"></c:set>
                            </c:if>
                            <c:if test="${not empty butlerStyle.butlerScore}">
                                <c:set var="butlerScore" value="${butlerStyle.butlerScore}"></c:set>
                            </c:if>
                            <input type="text" class="form-control" placeholder="" id="butlerScore" name="butlerScore" value="${butlerScore}" readonly/>
                            <button type="button" class="btn btn-primary col-sm-2" onclick="resetScore('${butlerStyle.butlerId}')">重置</button>
                        </div>
                    </div>
                    <%-- 联系电话 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label" for="telephone">联系电话</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" placeholder="" id="telephone" name="telephone" value="${butlerStyle.telephone}">
                        </div>
                    </div>
                    <%-- 微信昵称 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label" for="wechatNickname">微信昵称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" placeholder="" id="wechatNickname" name="wechatNickname" value="${butlerStyle.wechatNickname}">
                        </div>
                    </div>
                    <%-- 微信二维码 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label">微信二维码上传</label>
                        <div class="col-sm-4">
                            <input id="wechatQRCodeUpload" name="wechatQRCodeFile" type="file" multiple/>
                            <input type="hidden" id="wechatQRCodeUrl" name="wechatQRCodeUrl" value="${butlerStyle.wechatQRCodeUrl}"/>
                        </div>
                    </div>
                    <%-- 微信二维码原图 --%>
                    <div id="wechatQRCodeImg" class="form-group col-lg-10" style="display: none">
                        <label class="col-sm-3 control-label">微信二维码原图</label>
                        <div class="col-sm-4">
                            <img src="${butlerStyle.wechatQRCodeUrl}" alt="微信二维码原图" class="img-thumbnail" style="width: auto">
                        </div>
                    </div>
                    <%-- 管家头像 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label">管家头像上传</label>
                        <div class="col-sm-4">
                            <input id="butlerHeadImgUpload" name="butlerHeadImgFile" type="file" multiple/>
                            <input type="hidden" id="butlerHeadImgUrl" name="butlerHeadImgUrl" value="${butlerStyle.butlerHeadImgUrl}"/>
                        </div>
                    </div>
                    <%-- 管家头像原图 --%>
                    <div id="butlerHeadImgImg" class="form-group col-lg-10" style="display: none">
                        <label class="col-sm-3 control-label">管家头像原图</label>
                        <div class="col-sm-4">
                            <img src="${butlerStyle.butlerHeadImgUrl}" alt="管家头像原图" class="img-thumbnail" style="width: auto">
                        </div>
                    </div>
                    <%-- 区域 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label">区域</label>
                        <div class="col-sm-4">
                            <select id="scopeId" name="cityId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq butlerStyle.serviceCityId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 项目 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label">项目</label>
                        <div class="col-sm-4">
                            <select id="projectCode" name="projectNum" class="form-control">
                                <c:forEach items="${projectList}" var="item" >
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq butlerStyle.serviceProjectCode}">selected</c:if>>${item[1] }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 备注 --%>
                    <div class="form-group col-lg-10">
                        <label class="col-sm-3 control-label" for="remarks">备注</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" placeholder="" id="remarks" name="remarks" value="${butlerStyle.remarks}">
                        </div>
                    </div>
                    <div class="text-center form-group  col-lg-6">
                        <button type="button" class="btn btn-primary" onclick="toSave()"><spring:message
                                code="common_save"/></button>
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
        initFileInput("butlerHeadImgUpload", "");
        initFileInput("wechatQRCodeUpload", "");
        if ($("#wechatQRCodeUrl").val() != ""){
//        initPortrait("wechatQRCodeUpload",$("#wechatQRCodeUrl").val());
            $("#wechatQRCodeImg").show();
        }
        if ($("#butlerHeadImgUrl").val() != ""){
//        initPortrait("butlerHeadImgUpload",$("#butlerHeadImgUrl").val());
            $("#butlerHeadImgImg").show();
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
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        });
    }

    /*
    //初始化图像信息
    function initPortrait(ctrlName,imgUrl){
        var control = $('#' + ctrlName);
        //重要，需要更新控件的附加参数内容，以及图片初始化显示
        control.fileinput('refresh',{
            initialPreview: [ //预览图片的设置
                '<img class="file-preview-image" src="' + imgUrl + '">',
            ],
        });
    }
    */

    function toSave(){
        var formData = new FormData();
        formData.append("butlerId",$("#butlerId").val());
        //校验butlerNum非空唯一性
        if($("#butlerNum").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入管家名称！");
            return;
        }else{
            if($("#butlerId").val() == ''){
                //新增时
                var flag = false;
                $.ajax({
                    url:"../butlerStyle/checkButlerNum",
                    type:"POST",
                    cache:false,
                    async:false,
                    data:{
                        butlerNum:$("#butlerNum").val()
                    },
                    dataType:"JSON",
                    error:function(){
                        alert("网络异常，可能服务器忙，请刷新重试");
                    },
                    success:function(data){
                        if(data.error == "0"){
                            flag = data.check;
                        }else if(data.error == "-1"){
                            alert("管家名称校验失败，请联系管理员！");
                        }
                    }
                });
                if (flag){
                    alert("管家名称已存在，请修改编号！");
                    return;
                }else{
                    formData.append("butlerNum",$("#butlerNum").val());
                }
            }else{
                //编辑时
                formData.append("butlerNum",$("#butlerNum").val());
            }
        }
        if($("#realName").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入真实姓名！");
            return;
        }else{
            formData.append("realName",$("#realName").val());
        }
        if($("#telephone").val().replace(/(^s*)|(s*$)/g, "").length != 11){
            alert("请输入正确的联系电话！");
            return;
        }else{
            formData.append("telephone",$("#telephone").val());
        }
        if($("#wechatNickname").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入微信昵称！");
            return;
        }else{
            formData.append("wechatNickname",$("#wechatNickname").val());
        }
        formData.append("wechatQRCodeFile",$("#wechatQRCodeUpload")[0].files[0]);
        formData.append("wechatQRCodeUrl",$("#wechatQRCodeUrl").val());
        formData.append("butlerHeadImgFile",$("#butlerHeadImgUpload")[0].files[0]);
        formData.append("butlerHeadImgUrl",$("#butlerHeadImgUrl").val());
        if($("#scopeId").val() == '' || $("#scopeId").val() == 0){
            alert("请选择城市！");
            return;
        }else{
            formData.append("cityId",$("#scopeId").val());
            formData.append("cityName",$("#scopeId").find("option:selected").text());
        }
        if($("#projectCode").val() == '' || $("#projectCode").val() == 0){
            alert("请选择项目！");
            return;
        }else{
            formData.append("projectNum",$("#projectCode").val());
            formData.append("projectName",$("#projectCode").find("option:selected").text());
        }
        formData.append("butlerScore",$("#butlerScore").val());
        if($("#remarks").val().replace(/(^s*)|(s*$)/g, "").length >= 101){
            alert("备注不能大于100字！");
            return;
        }else{
            formData.append("remarks",$("#remarks").val());
        }
        //校验telephone,wechatNickname非空
        $.ajax({
            url:"../butlerStyle/saveOrUpdateButlerStyle",
            type:"POST",
            async:"false",
            data:formData,
            processData:false,
            contentType:false,
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(data){
                if(data.error == "0"){
                    alert("保存成功！");
                }else if(data.error == "-1"){
                    alert("保存失败，请联系管理员！");
                }
                window.location.href = "../butlerStyle/getButlerStyleList.html?menuId=005500050000";
            }
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

    //重置评分
    function resetScore(butlerId){
        if (butlerId != '' ){
            $.ajax({
                url:"../butlerStyle/resetButlerScope",
                type:"POST",
                async:"false",
                data:{
                    butlerId:butlerId
                },
                dataType:"JSON",
                error:function(){
                    alert("网络异常，可能服务器忙，请刷新重试");
                },
                success:function(data){
                    if(data.error == "0"){
                        alert("重置成功！");
                        location.reload();
                    }else if(data.error == "-1"){
                        alert("重置失败，请联系管理员！");
                    }
                }
            });
        }
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>