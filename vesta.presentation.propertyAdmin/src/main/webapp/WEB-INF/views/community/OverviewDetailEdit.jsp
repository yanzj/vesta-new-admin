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
    <style>
        #body {
            padding: 0;
            margin: 0;
        }

        .infoCont {
            background: #e2e2e2;
            border: 1px solid #999;
            position: relative;
            margin-top: 20px;
        }

        .del {
            height: 22px;
            width: 22px;
            position: absolute;
            right: -14px;
            top: -10px;
            border-radius: 15px;
            background: url(../static/images/deletePic.png) no-repeat;
            background-size: 83%;
        }

        .col_left {
            width: 16%;
        }

        .demoClass1 {
            position: relative;
        }

        .backPic {
            position: absolute;
            top: 10%;
            right: -26%;
            width: 16%;
            height: 84%;
            border: 1px solid #000000;
        }
    </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005300020000" username="${propertystaff.staffName}"/>
    <div class="forms">

        <div class="form-body" style="min-height:380px">
            <%--<form class="form-horizontal" id="form" action="../vote/addOrUpdateVote.html" method="post"
                  enctype="MULTIPART/FORM-DATA">--%>
            <form class="form-horizontal" id="form" action="../overview/saveOrUpdateOverviewDetail" method="post"
                  enctype="MULTIPART/FORM-DATA">
                <input type="hidden" id="id" name="id" value="${communityOverview.id}">
                <input type="hidden" id="cityIds" name="cityIds" value="${communityOverview.cityIds}">
                <input type="hidden" id="projectIds" name="projectIds" value="${communityOverview.projectIds}">
                <input type="hidden" id="cityScope" name="cityScope" value="${communityOverview.cityScope}">
                <input type="hidden" id="name" name="name" value="${communityOverview.name}">
                <input type="hidden" id="priceAverage" name="priceAverage" value="${communityOverview.priceAverage}">
                <input type="hidden" id="types" name="types" value="${communityOverview.types}">
                <input type="hidden" id="favorable" name="favorable" value="${communityOverview.favorable}">
                <input type="hidden" id="phone" name="phone" value="${communityOverview.phone}">
                <input type="hidden" id="orderDate" name="orderDate" value="${communityOverview.orderDate}">
                <input type="hidden" id="panoramaImg" name="panoramaImg" value="${communityOverview.panoramaImg}">
                <input type="hidden" id="releaseStatus" name="releaseStatus" value="">
                <input type="hidden" id="imgStatus" name="imgStatus" value="${communityOverview.imgStatus}">
                <input type="hidden" id="cityList" name="cityList" value="${communityOverview.cityList}">
                <input type="hidden" id="projectList" name="projectList" value="${communityOverview.projectList}">
                <input type="hidden" id="url" name="url" value="${communityOverview.url}">

                <div class="wrap">
                    <div class="infoCont row">
                        <span class="del"></span>

                        <div class="col-lg-8 demoClass1">
                            <%--标题--%>
                            <div class="form-group col-lg-6">
                                <label class="col-sm-4 control-label" for="titles">标题</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" placeholder="" id="titles" name="titles">
                                </div>
                            </div>
                            <%--背景图片--%>
                            <%--<div class="form-group col-lg-6">
                              <label class="col-sm-4 control-label" for="homePageimgpath">背景图片</label>
                              <div class="col-sm-8">
                                <input type="file" class="form-control"
                                       placeholder="<spring:message code = "CommunityOverview.imgRemark"/>"
                                       id="homePageimgpath"
                                       name="homePageimgpaths" accept="image/*"
                                       height="160px" width="280px"
                                       onchange="check(this)" >
                                <p style="color: red">
                                  <span><spring:message code="CommunityOverview.imgRemark"/></span>
                                </p>
                                <!-- 图片ͼ-->
                                <div class="form-group col-lg-5">
                                  <div class="col-sm-9" id="circleLogoUrl" name="circleLogoUrl">
                                    <c:if test="${communityOverview.panoramaImg != null}">
                                      <img src="${communityOverview.panoramaImg}"
                                           style="width: 280px;height: 160px"/>
                                    </c:if>
                                  </div>
                                </div>
                              </div>
                            </div>--%>
                            <div class="form-group  col-lg-6">
                                <label class="col-sm-3 control-label" for="homePageimgpath-1">背景图片：</label>

                                <div class="col-sm-5">
                                    <input type="file" class="form-control" placeholder="" id="homePageimgpath-1"
                                           name="homePageimgpaths" style="width: 179px;" onchange="check()"/>
                                    <span style="color: red">建议图片宽高比750*420</span>
                                </div>
                            </div>
                            <%--图片--%>
                            <div class="backPic" style="width:167px;height:120px;">
                                <div id="imgPath-1" style="padding-top: 0px;">
                                    <img src="" alt="" style="width:167px;height:120px;" id="img-1"/>
                                </div>

                            </div>
                            <%--描述--%>
                            <div class="form-group col-lg-12">
                                <label class="col-sm-2 control-label col_left" for="describe">描述</label>

                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" style="width: 99%" id="describe"
                                              name="describes"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <%--预览--%>
                        </div>
                    </div>


                </div>
                <div>
                    <button type="button" id="addBg">添加更多背景图</button>
                </div>
                <%--<div class="xxk" style="width:auto; height:auto; position:relative; padding-bottom:20px; float:left; margin-bottom: 20px;">
                  <div class="form-group col-lg-7" style="width: 100%; height: 50px;">
                    <label class="col-sm-2 control-label" for="multipartFiles" style="width: 100px;text-align: right;">标题1 :</label>

                    <div class="col-sm-8" style="height: 50px;">
                      <input type="text" id="descriptions" name="descriptions" class="form-control" style="float:left; width:240px;"/>
                      <input type="file" class="form-control" placeholder="" id="multipartFiles" name="multipartFiles" accept="image/*" height="400px" width="700px" style="width:240px; float:left; width:240px; margin-left:30px;" onchange="check(this)">
                      <p style="position: absolute;color: red;bottom: -5px;right:500px;}">
                        <span><spring:message code="vote.imgRemark"/></span>
                      </p>
                      <div class="col-sm-7">
                        <textarea type="text" class="form-control" placeholder="请输入内容"
                                  id="contentSynopsis" name="contentSynopsis">${announcementEntity.contentSynopsis}</textarea>
                      </div>
                    </div>
                  </div>--%>
                <%--
                            <div class="form-group col-lg-7" style="width: 100%; height: 50px;">
                              <label class="col-sm-2 control-label" for="multipartFiles" style="width: 100px;text-align: right;">标题2 :</label>

                              <div class="col-sm-8" style="height: 50px;">
                                <input type="text" id="descriptions" name="descriptions" class="form-control" style="float:left; width:240px;"/>
                                <input type="file" class="form-control" placeholder="" id="multipartFiles" name="multipartFiles" accept="image/*" height="400px" width="700px" style="width:240px; float:left; width:240px; margin-left:30px;" onchange="check(this)">
                                <p style="position: absolute;color: red;bottom: -5px;right:500px;}">
                                  <span><spring:message code="vote.imgRemark"/></span>
                                </p>
                              </div>
                            </div>
                  --%>
                <%--<a class="addxxk" style=" position:absolute; bottom:0px; left:32px; width:82px;">增加标题</a>
                <a class="removexxk" style="position:absolute; bottom:50px; left:640px; width:82px; z-index:100;">删除标题</a>
              </div>--%>
                <!--mj-->

                <div class='clearfix'></div>

                <input type="hidden" name="releaseStatus" id="releaseStatus" value="">
                <input type="hidden" name="communityId" id="communityId" value="">
                <button type="button" class="btn btn-primary" onclick="validate('1')">发布</button>
                <button type="button" class="btn btn-primary" onclick="validate('0')">未发布</button>
                <a href="../overview/List.html" class="btn btn-primary" for=""><spring:message
                        code="common_cancel"/></a>

            </form>
        </div>
    </div>


</div>

</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>

<script>
    function validate(status) {
        $("#releaseStatus").val(status);
        $("#form").submit();
    }
</script>
<script>
    var i = 0;
    $(function () {
        $('#addBg').on('click', function () {
            var aDiv = document.getElementsByName('homePageimgpaths');
            for (var m = 0; m < aDiv.length; m++) {
                if (aDiv[m].value == "") {
                    alert("请添加背景图片！");
                    return;
                }
            }
            //var info = '<div class="infoCont"><span class="del">删除</span><dl><dd>内容</dd><dt>图片</dt></dl></div>';
            var info = '<div class="infoCont row">' +
                    '<span class="del"></span>' +
                    '<div class="col-lg-8 demoClass1">' +
                    '<div class="form-group col-lg-6">' +
                    '<label class="col-sm-4 control-label" for="titles">标题</label>' +
                    '<div class="col-sm-8">' +
                    '<input type="text" class="form-control" placeholder="" id="titles' + i + '" name="titles">' +
                    '</div>' +
                    '</div>' +
                    '<div class="form-group  col-lg-6">' +
                    '<label class="col-sm-3 control-label" for="homePageimgpath' + i + '">背景图片：</label>' +
                    '<div class="col-sm-5">' +
                    '<input type="file" class="form-control" placeholder="" id="homePageimgpath' + i + '" name="homePageimgpaths" style="width: 179px;" onchange="check()"/>' +
                    '<span style="color: red">建议图片宽高比750*420</span>' +
                    '</div>' +
                    '</div>' +
                    '<div class="backPic" style="width:167px;height:120px;">' +
                    '<div id="imgPath' + i + '" name="imgPath" style="padding-top: 0px;"><img src="" alt="" style="width:167px;height:120px;" id="img' + i + '" /></div>' +
                    '</div>' +
                    '<div class="form-group col-lg-12">' +
                    '<label class="col-sm-2 control-label col_left" for="describe' + i + '">描述</label>' +
                    '<div class="col-sm-10">' +
                    '<textarea class="form-control" rows="3" style="width: 99%" name="describes" id="describe' + i + '"></textarea>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="col-md-4">' +
                    '</div>' +
                    '</div>';
            $('.wrap').append(info);

            i = i + 1;
        });

        $('body').on('click', '.del', function () {
            if (confirm("确定删除吗？")) {
                $(this).parents('div.infoCont').remove();
            }else {
                return;
            }
        });

    })

    function getPath(obj, fileQuery, transImg) {

        var imgSrc = '', imgArr = [], strSrc = '';

        if (window.navigator.userAgent.indexOf("MSIE") >= 1) { // IE浏览器判断
            if (obj.select) {
                obj.select();
                var path = document.selection.createRange().text;
                alert(path);
                obj.removeAttribute("src");
                imgSrc = fileQuery.value;
                imgArr = imgSrc.split('.');
                strSrc = imgArr[imgArr.length - 1].toLowerCase();
                if (strSrc.localeCompare('jpg') === 0 || strSrc.localeCompare('jpeg') === 0 || strSrc.localeCompare('gif') === 0 || strSrc.localeCompare('png') === 0) {
                    obj.setAttribute("src", transImg);
                    obj.style.filter =
                            "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + path + "', sizingMethod='scale');"; // IE通过滤镜的方式实现图片显示
                } else {
                    //try{
                    throw new Error('File type Error! please image file upload..');
                    //}catch(e){
                    // alert('name: ' + e.name + 'message: ' + e.message) ;
                    //}
                }
            } else {
                // alert(fileQuery.value) ;
                imgSrc = fileQuery.value;
                imgArr = imgSrc.split('.');
                strSrc = imgArr[imgArr.length - 1].toLowerCase();
                if (strSrc.localeCompare('jpg') === 0 || strSrc.localeCompare('jpeg') === 0 || strSrc.localeCompare('gif') === 0 || strSrc.localeCompare('png') === 0) {
                    obj.src = fileQuery.value;
                } else {
                    //try{
                    throw new Error('File type Error! please image file upload..');
                    //}catch(e){
                    // alert('name: ' + e.name + 'message: ' + e.message) ;
                    //}
                }

            }

        } else {
            var file = fileQuery.files[0];
            var reader = new FileReader();
            reader.onload = function (e) {

                imgSrc = fileQuery.value;
                imgArr = imgSrc.split('.');
                strSrc = imgArr[imgArr.length - 1].toLowerCase();
                if (strSrc.localeCompare('jpg') === 0 || strSrc.localeCompare('jpeg') === 0 || strSrc.localeCompare('gif') === 0 || strSrc.localeCompare('png') === 0) {
                    obj.setAttribute("src", e.target.result);
                } else {
                    //try{
                    throw new Error('File type Error! please image file upload..');
                    //}catch(e){
                    // alert('name: ' + e.name + 'message: ' + e.message) ;
                    //}
                }

                // alert(e.target.result);
            }
            reader.readAsDataURL(file);
        }
    }

    function check() {
        var number = i - 1;
        var imgPathId = 'img' + number;
        var homePath = 'homePageimgpath' + number;
        //以下即为完整客户端路径
        var file_img = document.getElementById(imgPathId);
        var iptfileupload = document.getElementById(homePath);
        getPath(file_img, iptfileupload, file_img);
    }
    /*  if (typeof FileReader === 'undefined') {
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
     result.innerHTML = '<img src="' + this.result + '" style="width:167px;height:120px;" alt=""/>';
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
     }*/
</script>

</div>

</body>
</html>