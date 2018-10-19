<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ page import="com.maxrocky.vesta.utility.StringUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            $("#006600010000").addClass("active");
            $("#006600010000").parent().parent().addClass("in");
            $("#006600010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>

    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
    <%--图片放大--%>
    <link rel="stylesheet" href="../../../../static/css/zoom.css" media="all"/>

    <%-- FileInput --%>
    <link href="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/css/fileinput.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/js/fileinput.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.1/js/fileinput_locale_zh.js"></script>

    <style>
        .imgList img {
            width: 90px;
            height: 90px;
            margin-right: 5px;
        }
    </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006600010000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <div class="form-marginL">
                    <form class="form-horizontal" id="frm1" action="../nursery/uploadNurseryImage" method="post" enctype="multipart/form-data">
                        <button type="button" class="btn btn-primary" onclick="downloadModel()">下载模板</button>
                        <button type="button" class="btn btn-primary" onclick="test()">导入数据</button>
                        <div class="col-sm-10" style="margin-left: 204px;margin-bottom: 5px">
                            <p style="color: red"><span>以下各类型图片上传请同时上传多张图片，不要多次上传！</span></p>
                        </div>
                        <div class="form-group col-lg-12">
                            <label class="col-sm-3 control-label" for="reviewImgFile">相关图片</label>
                            <div class="col-sm-5">
                                <input id="reviewImgFile" class="form-control" name="reviewImgFile" type="file" multiple/>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="checkSubmit()">上传</button>
                    </form>
                    <form action="../nursery/importNurseryExcel",name="frm" id="frm" method="post" enctype="multipart/form-data">
                        <%-- 导入excel 隐藏 --%>
                        <input type="file" id="myfile" name="myfile" style="visibility:hidden;" onchange="importExcel()">
                        <%
                            HttpSession sess = request.getSession();
                            String message = (String) sess.getAttribute("result");

                            if (message == "ok") {
                                sess.setAttribute("result", "");
                        %>
                        <script type="text/javascript">
                            alert("导入成功");
                        </script>
                        <%

                        }else if(!StringUtil.isEmpty(message)){
                            sess.setAttribute("result", "");
                        %>
                        <script type="text/javascript">
                            alert("<%=message %>");
                            <% }%>
                        </script>
                    </form>
                </div>
            </div>
        </div>
        <div class="table-responsive bs-example widget-shadow" style="border-top: 1px dashed #ccc;
    padding-top: 16px;">

            <table width="100%" class="tableStyle" style="table-layout: fixed;">
                <thead>
                <tr>
                    <th width="8%">序号</th>
                    <th width="15%">苗木名称</th>
                    <th width="12%">数量</th>
                    <th width="8%">高度</th>
                    <th width="9%">图片</th>
                    <th width="30%">备注</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${nurserys}" var="nurserys" varStatus="row">
                    <tr>
                        <td height="40px" style="text-align: center;"><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>
                        <td height="40px">${nurserys.nurseryName}</td>
                        <td height="40px">${nurserys.num}</td>
                        <td height="40px">${nurserys.height}</td>
                        <td height="40px">
                            <c:choose>
                                <c:when test="${nurserys.imageUrl eq '0'}">图片未上传
                                </c:when>
                                <c:otherwise>
                                    <label class="control-label imgList">
                                        <a href="${nurserys.imageUrl}" class="zoom">
                                            <img src="${nurserys.imageUrl}">
                                        </a>
                                    </label>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td height="40px">${nurserys.remark}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <m:pager pageIndex="${webPage.pageIndex}"
                     pageSize="${webPage.pageSize}"
                     recordCount="${webPage.recordCount}"
                     submitUrl="${pageContext.request.contextPath }/nursery/getNurseryList.html?pageIndex={0}"/>
        </div>
    </div>

</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script src="../../../../static/js/zoom.min.js"></script>
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
<script>
    function checkSubmit() {
        if($("#reviewImgFile").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请添加图片！");
            return;
        }
        $("#frm1").submit();
    }

    function test() {
        $("#myfile").click();
    }
    function search() {
        document.forms[0].submit();
    }
</script>
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


    function getDetail(titel, description) {
        $("#myModalLabel").html(titel);
        $("#mo-content").html(description);
        $("#myModal").modal({
            backdrop: false,
            show: true
        });
    }
    function downloadModel() {
        if (confirm("是否下载模板？")) {
            var href = "../nursery/exportNurseryModel";
            window.location.href = href;
        } else {
            return;
        }
    }
    function importExcel() {
        //检验导入的文件是否为Excel文件
        var excelPath = document.getElementById("myfile").value;
        if (excelPath == null || excelPath == '') {
            alert("请选择要上传的Excel文件");
            return;
        } else {
            var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();
            if (fileExtend == '.xls' || fileExtend == '.xlsx') {
                if (confirm("您确认要添加'"+excelPath+"'?")) {
                    document.getElementById("frm").action = "../nursery/importNurseryExcel";
                    document.getElementById("frm").submit();
                } else {
                    return;
                }
            } else {
                alert("文件格式需为'.xls'格式或者'.xlsx格式'");
                return;
            }
        }
    }
</script>
</body>
</html>