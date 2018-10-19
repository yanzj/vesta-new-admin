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
            $("#006900040000").addClass("active");
            $("#006900040000").parent().parent().addClass("in");
            $("#006900040000").parent().parent().parent().parent().addClass("active");
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
    <script type="text/javascript">
        function toSave(){
            if ($("#title").val() == "") {
                alert("请输入标题！");
                return;
            }
            var content = editor.txt.html();
            $("#content").val(content);
            $.ajax({
                type: 'POST',
                url: "../activitySurvey/saveOrUpdateActivitySurveyInfo",
                async:"false",
                data: {
                    id:$("#id").val(),
                    title:$("#title").val(),
                    content:$("#content").val()
                },
                error:function(){
                    alert("网络异常，可能服务器忙，请刷新重试");
                },
                success: function (data) {
                    if(data.error == 0){
                        alert("保存成功！");
                    }else{
                        alert("保存失败！");
                    }
                    window.location.href = "../activitySurvey/getActivitySurveyList.html?menuId=006900040000";
                }
            });
        }
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006900040000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="006900040000"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="#" method="post">
                    <input type="hidden" id="id" name="id" value="${activitySurveyInfo.id}">
                    <%-- 标题 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="title">标题</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="title" name="title" value="${activitySurveyInfo.title}">
                        </div>
                    </div>
                    <%-- 内容 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label">内容</label>
                        <div class="col-sm-9">
                            <div id="divToolbar" class="toolbar"></div>
                            <div style="padding: 2px 0; color: #ccc"></div>
                            <div id="divText" class="text"> <!--可使用 min-height 实现编辑区域自动增加高度-->
                                <p>${activitySurveyInfo.content}</p>
                            </div>
                            <input type="hidden" name="content" id="content"/>
                        </div>
                    </div>
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
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
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
</body>
</html>

