<%--
  Describe:便民信息修改
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/5/19
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            $("#005500010000").addClass("active");
            $("#005500010000").parent().parent().addClass("in");
            $("#005500010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <style>
        .imgList img {
            width: 340px;
            height: 120px;
        }
    </style>
    <script>

    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005500010000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" value="005500010000"/>

    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="froms" action="../property/updateLine" method="post"
                      enctype="MULTIPART/FORM-DATA">
                    <input type="hidden" id="announcementId" name="announcementId" value="${hotLine.announcementId}"/>
                    <input type="hidden" id="citys" name="citys" value="">
                    <input type="hidden" id="projects" name="projects" value="">
                    <input type="hidden" id="content" name="content" value="">

                    <div class="form-group  col-lg-10">
                        <label for="city" class="col-sm-3 control-label"><spring:message
                                code="announcementDTO.cityName"/></label>

                        <div class="col-sm-5">

                            <select id="city" name="city" class="form-control" onchange="queryProjectNameById()" disabled="disabled">
                                <option value="-1">--请选择城市--</option>
                                <c:forEach items="${city}" var="item">
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq hotLine.queryScope}">selected</c:if>
                                            <c:if test="${item.cityId eq '0'}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>

                        </div>
                    </div>

                    <%--城市下项目--%>
                    <div class="form-group  col-lg-10">
                        <label for="project" class="col-sm-3 control-label"><spring:message
                                code="HousePayBatch.projectName"/></label>

                        <div class="col-sm-5">
                            <select id="project" name="project" class="form-control" disabled="disabled">
                                <option value="${hotLine.project}"
                                        <c:if test="${hotLine.project}">selected</c:if>>${hotLine.projects}
                                </option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group  col-lg-10">
                        <label class="col-sm-3 control-label" for="title"><spring:message
                                code="propertyServices.sortNumber"/>：</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="请输入排序序号，值越大越靠后" id="sortNumber"
                                   name="sortNumber" value="${hotLine.sortNumber}"/>
                        </div>
                    </div>

                    <div class="form-group  col-lg-10">
                        <label class="col-sm-3 control-label" for="title"><spring:message code="propertyServices.name"/>：</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" value="${hotLine.title}" id="title"
                                   name="title"/>
                        </div>
                    </div>


                    <div class="form-group  col-lg-10">
                        <label class="col-sm-3 control-label" for="memo"><spring:message
                                code="propertyServices.servicesPhone"/>：</label>

                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" value="${hotLine.memo}" id="memo"
                                   name="memo"/>
                        </div>
                    </div>
                    <div class="form-group  col-lg-10">
                        <label class="col-sm-3 control-label" for="announcementContent"><spring:message
                                code="propertyReport.releaseContent"/>：</label>

                        <div class="col-sm-5">
                            <textarea type="text" class="form-control" placeholder="" name="announcementContent"
                                      id="announcementContent">${hotLine.announcementContent}</textarea>
                        </div>
                    </div>


                    <div class="form-group  col-lg-10">
                        <label class="col-sm-3 control-label" for="announcementContent"><spring:message
                                code="workOrders.repairImage"/></label>

                        <div class="col-sm-5">
                            <label class="control-label control-label imgList"><img src="${hotLine.imagePath}"></label>
                        </div>
                    </div>

                    <%--<div class="form-group  col-lg-6">
                      <label class="col-sm-3 control-label" for="image"><spring:message code="propertyServices.image" />：</label>
                      <div class="col-sm-6">
                        <input type="file" class="form-control" placeholder="" id="image" name="image" style="width: 179px;" onchange="uploadImg(this)"/>
                          <span style="color: red">建议图片宽高比750*420</span>
                        <div id="imgPath" style="padding-top: 15px;"></div>
                      </div>
                    </div>--%>
                    <div class="text-center form-group  col-lg-6">
                        <button type="button" class="btn btn-primary" onclick="saveLine()"><spring:message
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
    //上传图片
    var imgType = true;
    function uploadImg(fnUpload) {
        var filename = fnUpload.value;
        var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
        if (mime != ".jpg" && mime != ".png" && mime != ".jpeg" && mime != ".gif") {
            alert("上传图片类型错误");
            imgType = false;
        } else {
            imgType = true;
        }
    }

    var image = $("#image").get(0);
    var result = document.getElementById("imgPath");
    if (typeof FileReader === 'undefined') {
        result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
        image.setAttribute('disabled', 'disabled');
    } else {
        image.addEventListener('change', readFile, false);
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

    //页面值验证
    function saveLine() {
        var city = $("#city").val();
        if ("-1" == city) {
            alert("请选择城市！");
            return;
        }
        if ("0" == city) {
            alert("请选择具体城市！");
            return;
        }
        var project = $("#project").val();
        if ("0" == project) {
            alert("请选择具体项目！");
            return;
        }
        var number = $("#sortNumber").val();
        if (number == "") {
            alert("请输入排序序号！");
            return;
        }
        var re = /^[1-9]+[0-9]*]*$/;
        if (!re.test(number)) {
            alert("请输入正整数!");
            return;
        }
        if (number >= 2147483647) {
            alert("排序序号过大，请输入正确的排序序号！");
            return;
        }
        if (!CheckNull($("#title").val(), "请填写名称！")) {
            return;
        }
        var title = $("#title").val();
        if ("" == title) {
            alert("请填写名称！");
            return;
        }
//      if(!CheckNull($("#announcementContent").val(),"请填写内容！")){
//          return;
//      }
        var content = $("#announcementContent").val();
        var memo = $("#memo").val();
        if ("" != content && memo != "") {
            alert("电话和内容请填写一个！");

            return;
        }
        if ("" == content && memo == "") {
            alert("电话和内容请填写一个！");
            return;
        }
//      if ("" == content) {
//          alert("请填写内容！");
//          return;
//      }
        if (content.length > 800) {
            alert("请填写低于800字符的内容！");
            return;
        }
        var memo = $("#memo").val();
        /* if("" == memo){
         alert("请填写电话");
         return;
         }*/

        $("#citys").val($("#cityList").val());
        $("#projects").val($("#projectList").val());
        document.getElementById("froms").submit();
    }
    function cityAdd() {
        //#1.获取内容
        var projectName = $("#city").find("option:selected").text();
        //获取隐藏域的值_Wyd_2016.06.03
        var projectId = $("#city").find("option:selected").val();
        if (projectId == '-1') {
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
                $("#cityList").val($("#cityList").val() + projectName + ',');
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
                    $("#cityList").val($("#cityList").val() + projectName + ',');
                    $("#cityIds").val(projectId + ',');
                    return;
                }
                //如果textarea中有元素，前置","
                $("#cityList").val($("#cityList").val() + projectName + ',');
                $("#cityIds").val($("#cityIds").val() + projectId + ',');
            }
        }
    }
    function projectAdd() {

        //#1.获取内容
        var projectName = $("#projectName").find("option:selected").text();
        var projectId = $("#projectName").find("option:selected").val();
        if (projectName == "全部项目") {
            //清空textarea
            $("#projectList").val("全部项目");
            $("#projectIds").val("0,");
        } else {
            if ($("#projectList").val() == "") {
                //如果textarea中没有元素
                $("#projectList").val($("#projectList").val() + projectName + ',');
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
                    $("#projectList").val($("#projectList").val() + projectName + ',');
                    $("#projectIds").val(projectId + ',');
                    return;
                }
                //如果textarea中有元素，前置","
                $("#projectList").val($("#projectList").val() + projectName + ',');
                $("#projectIds").val($("#projectIds").val() + projectId + ',');
            }
        }
    }

    function queryProjectNameById() {
        var projectId = $("#city").val();
        if (projectId == '-1') {
            $("#project").empty();
            return;
        }
        $("#planName").empty();
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId + "/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#project").empty();
            <%--$("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');--%>
            for (var k in arr) {
                $("#project").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
//                alert(arr[k][0]);
            }

        });
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>
