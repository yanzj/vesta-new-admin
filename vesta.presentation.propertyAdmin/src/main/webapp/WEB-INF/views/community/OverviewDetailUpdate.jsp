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
    <script type="text/javascript" charset="utf-8" src="/static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/static/editer/ueditor.all.min.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="/static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- Custom CSS -->
    <link href="/static/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="/static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="/static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="/static/js/jquery-1.11.1.min.js"></script>
    <script src="/static/js/modernizr.custom.js"></script>

    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--animate-->
    <link href="/static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="/static/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="/static/js/metisMenu.min.js"></script>
    <script src="/static/js/custom.js"></script>
    <link href="/static/css/custom.css" rel="stylesheet">
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
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body" style="min-height:380px">
                    <form class="form-horizontal" id="form" action="../overview/addOrUpdatePage" method="post"
                          enctype="MULTIPART/FORM-DATA">
                        <input type="hidden" id="id" name="id" value="${communityOverview.id}">
                        <input type="hidden" id="releaseStatus" name="releaseStatus"
                               value="${communityOverview.releaseStatus}">
                        <input type="hidden" id="imgStatus" name="imgStatus" value="1">
                        <%--区域城市--%>
                        <div class="form-group col-lg-7">
                            <label class="col-sm-2 control-label" for="city"><spring:message
                                    code="CommunityOverview.cityArea"/></label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" placeholder="" id="city" name="city"
                                       value="${communityOverview.name}">
                            </div>
                        </div>
                        <%--项目名称--%>
                        <div class="form-group col-lg-7">
                            <label class="col-sm-2 control-label" for="name"><spring:message
                                    code="HousePayBatch.projectName"/></label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" placeholder="" id="name" name="name"
                                       value="${communityOverview.name}">
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%--均价--%>
                        <div class="form-group col-lg-7">
                            <label class="col-sm-2 control-label" for="priceAverage"><spring:message
                                    code="CommunityOverview.averagePrice"/></label>

                            <div class="col-sm-10">
                                <input type="text" id="priceAverage" class="form-control"
                                       placeholder="<spring:message code = "CommunityOverview.priceRemark"/>"
                                       name="priceAverage"
                                       value="${communityOverview.priceAverage}">
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%--标签--%>
                        <div class="form-group col-lg-7">
                            <label class="col-sm-2 control-label" for="types"><spring:message
                                    code="CommunityOverview.tag"/></label>

                            <div class="col-sm-10">
                                <input type="text" id="types" name="types" class="form-control"
                                       placeholder="<spring:message code = "CommunityOverview.tagRemark"/>"
                                       value="${communityOverview.types}">
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%--优惠信息--%>
                        <div class="form-group col-lg-7">
                            <label class="col-sm-2 control-label" for="favorable"><spring:message
                                    code="CommunityOverview.favorableDetail"/></label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" placeholder="" id="favorable" name="favorable"
                                       value="${communityOverview.favorable}">
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%--电话--%>
                        <div class="form-group col-lg-7">
                            <label class="col-sm-2 control-label" for="phone"><spring:message
                                    code="CommunityOverview.phone"/></label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" placeholder="" id="phone" name="phone"
                                       value="${communityOverview.phone}">
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%--图片--%>
                        <div class="form-group col-lg-7">
                            <label class="col-sm-2 control-label" for="homePageimgpath"><spring:message
                                    code="activityManage.homePageimgpath"/></label>

                            <div class="col-sm-8">
                                <input type="file" class="form-control"
                                       placeholder="<spring:message code = "CommunityOverview.imgRemark"/>"
                                       id="homePageimgpath"
                                       name="homePageimgpath" accept="image/*"
                                       height="400px" width="700px"
                                       style="width: 556px;" onchange="check(this)" >
                                <p style="color: red">
                                        <span><spring:message code="CommunityOverview.imgRemark"/></span>
                                </p>
                                <!-- 图片ͼ-->
                                <div class="form-group col-lg-5">
                                    <div class="col-sm-9" id="circleLogoUrl" name="circleLogoUrl">
                                        <c:if test="${communityOverview.panoramaImg != null}">
                                            <img src="${communityOverview.panoramaImg}"
                                                 style="width: 150px;height: 120px"/>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class='clearfix'></div>

                            <div id="demo_homePageimgpath" style="padding-top: 5px;">
                            </div>

                        </div>
                        <%--H5链接--%>
                        <div class="form-group col-lg-7">
                            <label class="col-sm-2 control-label" for="url"><spring:message
                                    code="CommunityOverview.goH5Note"/></label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" placeholder="" id="url" name="url"
                                       value="${communityOverview.url}">
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%--编辑楼盘详情页--%>
                        <div class="form-group col-lg-7">
                            <label class="col-sm-2 control-label" for=""><spring:message
                                    code="CommunityOverview.goEditNote"/></label>

                            <div class="col-sm-10">
                                <a id="" name="" class="" href="">
                                    <spring:message
                                            code="CommunityOverview.goedit"/>
                                </a>
                            </div>
                        </div>
                        <div class='clearfix'></div>

                        <input type="hidden" name="releaseStatus" id="releaseStatus" value="">
                        <button type="button" class="btn btn-primary" onclick="validate('1')"><spring:message
                                code="activityManage.activityPublish"/></button>
                        <button type="button" class="btn btn-primary" onclick="validate('0')"><spring:message
                                code="activityManage.activityUnPublish"/></button>
                        <a href="../communityNews/NewsList.html" class="btn btn-primary" for=""><spring:message
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
    $().ready(function () {
        //
        var validator = $("#form").validate({
            /*

             errorPlacement: function (error, element) {
             // Append error within linked label
             $(element)
             .closest("form")
             .find("label[for='" + element.attr("id") + "']")
             .append(error);
             },*/
            errorElement: "span",
            rules: {
                name: {
                    required: true
                },
                city: {
                    required: true
                },
                phone: {
                    required: true
                },
                priceAverage: {
                    digits: true
                }
            },
            messages: {
                name: {
                    required: " 请输入项目名称",
                },
                city: {
                    required: "请选择城市",
                },
                phone: {
                    required: " 请输入手机号码",
                },
                priceAverage: {
                    digits: "请输入正确的内容",
                }
            }
        })
    })
    function validate(status) {
        //
        $("#types").val($("#types").val().replace(/,/g, ","));
        //alert( $("#types").val())
        //var _date = $('#frm').serialize();
        $("#releaseStatus").val(status);
        if ($("#form").validate()) {
            $("#form").submit();
        }

    }
</script>

<!--图片上传控件-->
<script>
    $().ready(function () {
        //if img is null then hide it
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
                result.innerHTML = '<img src="' + this.result + '"style="width: 150px;height: 120px" alt=""/>';
                results.innerHTML(result.innerHTML);
            }
            reader.onloadend = function(e){
                //判断图片
                var img = new Image();//构造JS的Image对象
                img.src = reader.result;//将本地图片赋给image对象

                if (img.width % 700 != 0) {
                    result.innerHTML = "";
                    //设置图片状态
                    document.getElementById("imgStatus").value = 0;
                    var obj = document.getElementById('imgStatus') ;
                    obj.outerHTML=obj.outerHTML;
                    //alert("对不起，图片宽度不符合，当前图片宽度为" + img.width );
                    alert("对不起，图片宽度不符合，您的图片宽高比应该为700：400" );
                    return;
                }
                if (img.height % 400 !=0) {
                    result.innerHTML = "";
                    //设置图片状态
                    document.getElementById("imgStatus").value = 0;
                    var obj = document.getElementById('imgStatus') ;
                    obj.outerHTML=obj.outerHTML;
                    //alert("对不起，图片宽度不符合，当前图片高度为" + img.height );
                    alert("对不起，图片宽度不符合，您的图片宽高比应该为700：400" );
                    return;
                }
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
                results.innerHTML = '<img src="' + this.result + '" style="width: 500px;height: 180px"/>';
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
<!--限制图片大小-->
<script language="javascript">
    /* $("#file").change(function imgSel() {
     var img = new Image();//构造JS的Image对象
     img.src = $("#file").val();//将本地图片赋给image对象
     alert(["图片大小是:", img.width, img.height]);
     document.form.width.value = img.width;
     document.form.height.value = img.height;
     document.form.size.value = img.size;
     //document.images['image'].src = img.src;
     /!* img.onreadystatechange = function () {
     alert(1);
     if (img.readyState == "complete") {
     alert(["图片大小是:", img.width, img.height]);
     document.form.width.value = img.width;
     document.form.height.value = img.height;
     document.form.size.value = img.size;
     //document.images['image'].src = img.src;
     }
     }*!/
     });*/

</script>

</div>

</body>
</html>