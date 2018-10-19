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
            $("#006700020000").addClass("active");
            $("#006700020000").parent().parent().addClass("in");
            $("#006700020000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="006700020000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="006700020000"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../productInfo/saveOrUpdateproductInfo.html" method="post" enctype="MULTIPART/FORM-DATA">
                    <input type="hidden" id="productId" name="productId" value="${productInfo.productId}">
                    <%-- 所属客户端 --%>
                    <div class="form-group  col-lg-7">
                        <label for="clientId" class="col-sm-3 control-label">所属客户端</label>
                        <div class="col-sm-5">
                            <select id="clientId" name="clientId" class="form-control">
                                <c:forEach items="${clientConfigList}" var="clientConfig" >
                                    <option value="${clientConfig.id }"
                                            <c:if test="${clientConfig.id eq productInfo.clientId}">selected</c:if>>${clientConfig.clientName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 商品名称 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="productName">商品名称</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="productName" name="productName" value="${productInfo.productName}">
                        </div>
                    </div>
                    <%-- 商品编码 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="productNum">商品编码</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="productNum" name="productNum" value="${productInfo.productNum}">
                        </div>
                    </div>
                    <%-- 商品类目 --%>
                    <div class="form-group  col-lg-7">
                        <label for="productTypeId" class="col-sm-3 control-label">商品类目</label>
                        <div class="col-sm-5">
                            <select id="productTypeId" name="productTypeId" class="form-control">
                                <c:forEach items="${productTypeList}" var="productType" >
                                    <option value="${productType.productTypeId }"
                                            <c:if test="${productType.productTypeId eq productInfo.productTypeId}">selected</c:if>>${productType.productTypeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 商品价格 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="productPrice">商品应付金额</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="productPrice" name="productPrice" value="${productInfo.productPrice}" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">
                        </div>
                    </div>
                    <%-- 商品积分 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="productIntegral">商品应付积分</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="productIntegral" name="productIntegral" value="${productInfo.productIntegral}" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">
                        </div>
                    </div>
                    <%-- 所属商家 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="productBusiness">所属商家</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="productBusiness" name="productBusiness" value="${productInfo.productBusiness}">
                        </div>
                    </div>
                    <%-- 商品规格 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="productSpec">商品规格</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="productSpec" name="productSpec" value="${productInfo.productSpec}">
                        </div>
                    </div>
                    <%-- 商品库存 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="productStock">商品库存</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="productStock" name="productStock" value="${productInfo.productStock}">
                        </div>
                    </div>
                    <%-- 商品导图 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">商品导图</label>
                        <div class="col-sm-5">
                            <input id="productPicUpload" name="productPicFile" type="file" multiple/>
                            <input type="hidden" id="productPicUrl" name="productPicUrl" value="${productInfo.productPicUrl}"/>
                        </div>
                    </div>
                    <%-- 商品导图原图 --%>
                    <div id="productPic" class="form-group col-lg-7" style="display: none">
                        <label class="col-sm-3 control-label">商品导图原图</label>
                        <div class="col-sm-5">
                            <img src="${productInfo.productPicUrl}" alt="商品导图原图" class="img-thumbnail" style="width: auto">
                        </div>
                    </div>
                    <%-- 是否为卡券 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">是否为卡券</label>
                        <div class="col-sm-5">
                            <input type="radio" name="isCardCoupons" <c:if test="${productInfo.isCardCoupons eq '0'}">checked</c:if> value="0" style="float:left;margin: 9px 0 0 55px;">
                            <span style="float:left; width: 40px;text-align: center;height: 30px;line-height: 31px;">否</span>
                            <input type="radio" name="isCardCoupons" <c:if test="${productInfo.isCardCoupons eq '1'}">checked</c:if> value="1" style="float:left;margin: 9px 0 0 55px;">
                            <span style="float:left; width: 40px;text-align: center;height: 30px;line-height: 31px;">是</span>
                        </div>
                    </div>
                    <%-- 卡券设置 --%>
                    <div id="cardCoupons" <c:if test="${empty productInfo.isCardCoupons || productInfo.isCardCoupons eq '0'}">style="display:none;"</c:if>>
                        <%-- 卡券密码 --%>
             <%--           <div class="form-group col-lg-7">
                            <label class="col-sm-3 control-label" for="cardCouponsPassword">卡券密码</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" placeholder="" id="cardCouponsPassword" name="cardCouponsPassword" value="${productInfo.cardCouponsPassword}">
                            </div>
                        </div>--%>
                        <%-- 有效期限 --%>
                        <div class="form-group col-lg-7">
                            <label class="col-sm-3 control-label" for="cardCouponsTermStr">有效期限</label>
                            <div class="col-sm-5">
                                <div class="input-group date form_datetime" data-date=""
                                     data-date-format="yyyy-mm-dd"
                                     data-link-field="dtp_input1">
                                    <input type="text" class="form-control " placeholder="" id="cardCouponsTermStr" name="cardCouponsTermStr"
                                           value="${productInfo.cardCouponsTermStr}" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <%-- 卡券二维码 --%>
                   <%--     <div class="form-group col-lg-7">
                            <label class="col-sm-3 control-label">卡券二维码</label>
                            <div class="col-sm-5">
                                <input id="cardCouponsQRCodeUpload" name="cardCouponsQRCodeFile" type="file" multiple/>
                                <input type="hidden" id="cardCouponsQRCode" name="cardCouponsQRCode" value="${productInfo.cardCouponsQRCode}"/>
                            </div>
                        </div>--%>
                        <%-- 卡券二维码原图 --%>
                  <%--      <div id="QRCode" class="form-group col-lg-7" style="display: none">
                            <label class="col-sm-3 control-label">卡券二维码原图</label>
                            <div class="col-sm-5">
                                <img src="${productInfo.cardCouponsQRCode}" alt="卡券二维码原图" class="img-thumbnail" style="width: auto">
                            </div>
                        </div>--%>
                    </div>
                    <%-- 商品详情 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">商品详情</label>
                        <div class="col-sm-5">
                            <script id="editor" type="text/plain"></script>
                            <input type="hidden" id="productDetails" name="productDetails"/>
                        </div>
                    </div>
                    <input type="hidden" name="releaseStatus" id="releaseStatus"/>
                    <div class="text-center form-group  col-lg-6">
                        <button type="button" class="btn btn-primary" onclick="toSave()">保存</button>
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
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<script type="text/javascript">
    //实例化编辑器
    var productInfoContent = '${productInfo.productDetails}';
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
        initialContent: productInfoContent
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
        initFileInput("productPicUpload", "");
        if ($("#productPicUrl").val() != ""){
            $("#productPic").show();
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
</script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        linkField: "mirror_field",
        linkFormat: "yyyy-mm-dd",
        minView:2,
        maxView:3
    })
</script>
<script>
    //是否为卡券点击事件
    $("input[name='isCardCoupons']").click(function(){
        if(this.defaultValue == '0'){
            $("#cardCoupons").hide();
        }
        if(this.defaultValue == '1'){
            $("#cardCoupons").show();
        }
    });
    //保存
    function toSave(){
        var formData = new FormData();
        formData.append("productId",$("#productId").val());
        formData.append("clientId",$("#clientId").val());
        if($("#productName").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入商品名称！");
            return;
        }else{
            formData.append("productName",$("#productName").val());
        }
        if($("#productNum").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入商品编码！");
            return;
        }else{
            formData.append("productNum",$("#productNum").val());
        }
        formData.append("productTypeId",$("#productTypeId").val());
        if($("#productPrice").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入商品价格！");
            return;
        }else{
            formData.append("productPrice",$("#productPrice").val());
        }
        if($("#productIntegral").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入商品积分！");
            return;
        }else{
            formData.append("productIntegral",$("#productIntegral").val());
        }
        formData.append("productBusiness",$("#productBusiness").val());
        formData.append("productSpec",$("#productSpec").val());
        if($("#productStock").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入商品库存！");
            return;
        }else{
            if(isNaN($("#productStock").val())){
                alert("请输入正确的库存数量！");
                return;
            }else{
                formData.append("productStock",$("#productStock").val());
            }
        }
        if($("#productPicUpload").val() != ""){
            formData.append("productPicFile",$("#productPicUpload")[0].files[0]);
        }
        formData.append("productPicUrl",$("#productPicUrl").val());
        var productDetails = UE.getEditor('editor').getContent();
        $("#productDetails").val(productDetails);
        formData.append("productDetails",$("#productDetails").val());
        //卡券信息
        var isCardCoupons = $('input[name="isCardCoupons"]:checked').val();
        if(isCardCoupons == undefined){
            alert("请选择是否为卡券！");
            return;
        }
        formData.append("isCardCoupons",isCardCoupons);
        if(isCardCoupons == 1){

            if($("#cardCouponsTermStr").val().replace(/(^s*)|(s*$)/g, "").length == 0){
                alert("请选择有效期限！");
                return;
            }else{
                formData.append("cardCouponsTermStr",$("#cardCouponsTermStr").val());
            }

        }
        $.ajax({
            url:"../integralMall/saveOrUpdateProductInfo",
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
                window.location.href = "../integralMall/getProductList.html?menuId=006700020000";
            }
        });
    }
</script>
</body>
</html>