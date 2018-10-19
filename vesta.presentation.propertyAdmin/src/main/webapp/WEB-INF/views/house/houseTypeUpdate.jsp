
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            $("#001500010000").addClass("active");
            $("#001500010000").parent().parent().addClass("in");
            $("#001500010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <script src="../static/property/js/checkNullAllJsp.js"></script>
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
</head>
<script>

    function validate() {
        if(!CheckNull($("#name").val(),"户型不能为空！")){
            return false;
        }
        var name = $("#name").val();//户型
        if (name == "") {
            alert("户型不能为空");
            return false;
        }
        if (name.length > 50) {
            alert("户型不能大于50字");
            return false;
        }
        var f = document.getElementById("imgFile").value;
        if (f == "") {
            alert("请选择图片！");
            return false;
        } else {
            if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)) {
                alert("图片类型必须是.gif,jpeg,jpg,png中的一种！");
                return false;
            }
        }
        document.getElementById("frm").submit();
    }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001500010000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body" style="min-height:380px">
                    <form class="form-horizontal" id="frm" action="../housetype/saveUpdate" method="post"
                          enctype="MULTIPART/FORM-DATA">

                        <%--户型--%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="name"><spring:message
                                    code="rental.houseType"/></label>
                            <input type="hidden" id="id" name="id" value="${houseType.id}"/>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="name" name="name" value="${houseType.name}">
                            </div>
                        </div>
                        <%--描述--%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="comments"><spring:message
                                    code="rental.desciption"/></label>

                            <div class="col-sm-9">
                                <textarea class="form-control" id="comments" name="comments">${houseType.comments}</textarea>
                            </div>
                        </div>
                            <%--问题状态 --%>
                            <div class="form-group col-lg-8">
                                <label class="col-sm-3 control-label" for="comments"><spring:message
                                        code="rental.localType"/></label>
                                <div class="col-sm-9">
                                    <c:if test="${empty houseType.type}">
                                        <input type="text" class="form-control" placeholder="" id="type" name="type" disabled="disabled"
                                               value="">
                                    </c:if>
                                    <c:if test="${houseType.type eq '100000000'}">
                                        <input type="text" class="form-control" placeholder="" id="type" name="type" disabled="disabled"
                                               value="户型图">
                                    </c:if>
                                    <c:if test="${houseType.type eq '100000001'}">
                                        <input type="text" class="form-control" placeholder="" id="type" name="type" disabled="disabled"
                                               value="楼栋公共区域">
                                    </c:if>
                                    <c:if test="${houseType.type eq '100000002'}">
                                        <input type="text" class="form-control" placeholder="" id="type" name="type" disabled="disabled"
                                               value="项目公共区域">
                                    </c:if>

                                <%--<select class="form-control" placeholder="" id="type" name="type">--%>
                                        <%--<option value="" selected>请选择</option>--%>
                                        <%--<option value="100000000" <c:if test="${houseType.type eq '100000000'}">selected</c:if>>户型图--%>
                                        <%--</option>--%>
                                        <%--<option value="100000001" <c:if test="${houseType.type eq '100000001'}">selected</c:if>>楼栋公共区域--%>
                                        <%--</option>--%>
                                        <%--<option value="100000002" <c:if test="${houseType.type eq '100000002'}">selected</c:if>>项目公共区域--%>
                                        <%--</option>--%>
                                    <%--</select>--%>
                                </div>
                            </div>
                        <%--户型图片--%>
                        <div class="form-group col-lg-8">
                            <label class="col-sm-3 control-label" for="imgFile"><spring:message
                                    code="activityManage.homePageimgpath"/></label>

                            <div class="col-sm-9">
                                <input type="file" class="form-control" placeholder="" id="imgFile"
                                       name="imgFile" value="" style="width: 179px;" onchange="check(this)">

                                <div id="demo_imgFile" style="padding-top: 15px;">
                                    <c:if test="${houseType.imgUrl != null}">
                                        <img src="${houseType.imgUrl}"
                                             style="width: 150px;height: 150px"/>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="text-center form-group  col-lg-10" style="padding:0;">
                            <c:if test="${function.qch40010062 eq 'Y'}">
                                <button type="button" class="btn btn-primary" onclick="validate()"><spring:message
                                        code="common_save"/></button>
                            </c:if>
                            <button type="button" class="btn btn-primary" onclick="history.back()"><spring:message
                                    code="common_back"/></button>
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
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
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

    var inputs = $("#imgFile").get(0);
    var result = document.getElementById("demo_imgFile");
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
                result.innerHTML = '<img src="' + this.result + '" style="width:100px" alt=""/>';
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
</body>
</html>