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
        window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL
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
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005300020000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body" style="min-height:380px">
                    <form class="form-horizontal" id="form" action="../overview/detailAdd" method="post"
                          enctype="MULTIPART/FORM-DATA">
                        <%--图片--%>
                        <div name="imgDivs" class="form-group col-lg-7">
                            <div name="imageDiv" class="imageDiv" style="padding-top: 5px;">
                                <input type="file" name="files" class="form-control" accept="image/*"/>
                                <div name="imgShowDiv" class="imgShowDiv" style="text-align: center">
                                    <img style="margin-left: 20px" src="" width="200" height="150">
                                </div>

                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <div>
                            <input id="addImg" name="addImg" value="添加更多背景图" type="button"/>
                        </div>

                        <button type="button" class="btn btn-primary" onclick="validate('1')"><spring:message
                                code="activityManage.activityPublish"/></button>
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
<script>
    function validate(status) {
        $("#form").submit();
    }
    ;
</script>
<!--图片上传控件-->
<script>
    $().ready(function () {
        //#1.给imageDiv绑定图片回显
        //input元素
        var inputF = $("div[name='imgDivs']").children('.imageDiv').children('.form-control');
        //添加input监听事件
        inputF.get(0).addEventListener('change', readFile, false);
        function readFile(){
            //#1.当前元素
            //var input = $(this).children('.form-control').get(0);
            var result = $(this).siblings(".imgShowDiv").get(0);
            //result.innerHTML = "";
            for (var i = 0; i < this.files.length; i++) {
                var file = this.files[0];
                if (!/image\/\w+/.test(file.type)) {
                    alert("请确保文件为图像类型");
                    return false;
                }
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function (e) {
                    //result.innerHTML += '<img width="200" height="150" src="' + this.result + '" alt=""/>';
                    $(result).children("img").attr("src",this.result);
                    //$(result).show();
                }
            }
        }

        //绑定dom添加实现
        $("#addImg").click(function () {
            //获取最后一个名称为name="imageDiv" 的div，进行追加
            var imgDiv = $("div[name='imgDivs']").children('.imageDiv').last();
            //清空图片，清空文本属性
            var temp = imgDiv.clone(true,false);
            //清空img
            temp.children('.imgShowDiv').children("img").attr("src","");
            var inputF = temp.children('.form-control');
            //重置inputF
            inputF.remove();
            temp.children('.imgShowDiv').before('<input type="file" name="files" class="form-control" accept="image/*"/>');
            //刪除按鈕
            temp.children('.imgShowDiv').before('<input type="button" name="deleteImgDiv" class="deleteImgDiv" value="-" />');
            temp.children('.deleteImgDiv');
            //綁定刪除事件
            $(".deleteImgDiv").click(function () {
                //獲取當前div的父類，刪除
                $(this).parent(".imageDiv").empty();
            });
            //重定位inputF
            inputF = temp.children('.form-control');
            //添加input监听事件
            inputF.get(0).addEventListener('change', readFile, false);
            //綁定刪除方法
            //temp.children('.imgShowDiv').hide();
            imgDiv.after(temp);
        });

    });
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


</script>

</div>

</body>
</html>