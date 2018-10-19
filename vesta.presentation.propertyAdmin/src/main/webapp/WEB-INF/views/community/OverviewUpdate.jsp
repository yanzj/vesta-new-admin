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
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <script>
        $(function () {
            console.log("sqq")
            $("#005300020000").addClass("active");
            $("#005300020000").parent().parent().addClass("in");
            $("#005300020000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <%-- FileInput --%>
    <link href="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/css/fileinput.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.9/js/fileinput.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.3.1/js/fileinput_locale_zh.js"></script>
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
                    <form class="form-horizontal" id="form" action="../overview/addOrUpdatePage" method="post"
                          enctype="MULTIPART/FORM-DATA">
                        <input type="hidden" id="menuId" name="menuId" value="005300020000"/>
                        <input type="hidden" id="id" name="id" value="${communityOverview.id}">
                        <input type="hidden" id="releaseStatus" name="releaseStatus"
                               value="${communityOverview.releaseStatus}">
                        <input type="hidden" id="imgStatus" name="imgStatus" value="1">
                        <%--新增区域项目--%>
                        <%--城市区域--%>
                        <div class="form-group  col-xs-8">
                            <label for="city" class="col-sm-2 control-label"><spring:message
                                    code="announcementDTO.cityName"/></label>
                            <div class="col-sm-7">
                                <select id="city" name="city" class="form-control" onchange="queryProjectNameById()">
                                    <option value="-1">--请选择城市--</option>
                                    <c:forEach items="${city}" var="item">
                                        <option value="${item.cityId }"
                                                <c:if test="${item.cityId eq '-1'}">selected</c:if>>${item.cityName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-1">
                                <input type="image" name="addCity" id="addCity"
                                       value="<spring:message code="announcementDTO.add"/>"
                                       onclick="cityAdd();return false;"
                                       src="../static/images/add.ico">
                            </div>
                        </div>
                        <%--城市列表--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="cityList"
                                   style="min-width:115px;"><spring:message
                                    code="announcementDTO.selectedCity"/> <font color="red">*</font></label>

                            <div class="col-sm-7">
                                <textarea class="form-control" name="cityList" id="cityList" readonly
                                          style=" height: 71px;"><c:if
                                        test="${allCityInScope != ''}">${allCityInScope }</c:if><c:if
                                        test="${allCityInScope == ''}">${cityInScope }</c:if></textarea>
                                <input type="hidden" id="cityIds" name="cityIds" value="${cityIdInScope}"/>
                            </div>
                        </div>
                        <%--城市下项目--%>
                        <div class="form-group  col-xs-8">
                            <label for="projectName" class="col-sm-2 control-label"><spring:message
                                    code="HousePayBatch.projectName"/></label>
                            <div class="col-sm-7">
                                <select id="projectName" name="projectName" class="form-control">
                                    <option value="-1">--请选择项目--</option>
                                </select>
                            </div>
                            <div class="col-sm-1">
                                <input type="image" name="addProject" id="addProject"
                                       value="" onclick="projectAdd();return false;" src="../static/images/add.ico">
                            </div>
                        </div>
                        <%--项目列表--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="projectList"
                                   style="min-width:115px;"><spring:message
                                    code="announcementDTO.selectedProject"/> <font color="red">*</font></label>
                            <div class="col-sm-7">
                                <textarea class="form-control" name="projectList" id="projectList" readonly
                                          style=" height: 71px;">${projectNameList}</textarea>
                                <input type="hidden" id="projectIds" name="projectIds" value="${projectIds}">
                            </div>
                        </div>
                        <%--区域城市_20170522证实区域城市字段无用--%>
                        <%--
                        <div class="form-group col-lg-9">
                            <label class="col-sm-2 control-label" for="city"><spring:message
                                    code="CommunityOverview.cityArea"/> <font color="red">*</font></label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="cityScope" name="cityScope"
                                       onchange="checkVal()"
                                       value="${communityOverview.name}">
                            </div>
                        </div>
                        --%>
                        <%--项目名称--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="name"><spring:message
                                    code="HousePayBatch.projectName"/> <color="red">*</></label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" placeholder="" id="name" name="name"
                                       onchange="checkVal()"
                                       value="${communityOverview.name}">
                            </div>
                        </div>
                        <%--项目地址--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="address">项目地址</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" placeholder="" id="address" name="address" value="${communityOverview.address}">
                            </div>
                        </div>
                        <%--项目介绍--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="introduction">项目介绍</label>
                            <div class="col-sm-7">
                                <textarea class="form-control" id="introduction" name="introduction" value="${communityOverview.introduction}">${communityOverview.introduction}</textarea>
                            </div>
                        </div>
                        <%--周边配套--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="periphery">周边配套</label>
                            <div class="col-sm-7">
                                <textarea class="form-control" id="periphery" name="periphery" value="${communityOverview.periphery}">${communityOverview.periphery}</textarea>
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%--均价--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="priceAverage"><spring:message
                                    code="CommunityOverview.averagePrice"/></label>
                            <div class="col-sm-7">
                                <input type="text" id="priceAverage" class="form-control"
                                       placeholder="请输入均价"
                                       name="priceAverage"
                                       value="${communityOverview.priceAverage}">
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%--标签--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="types"><spring:message
                                    code="CommunityOverview.tag"/></label>
                            <div class="col-sm-7">
                                <input type="text" id="types" name="types" class="form-control"
                                       placeholder="<spring:message code = "CommunityOverview.tagRemark"/>"
                                       value="${communityOverview.types}">
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%--优惠信息--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="favorable"><spring:message
                                    code="CommunityOverview.favorableDetail"/></label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" placeholder="" id="favorable" name="favorable"
                                       value="${communityOverview.favorable}">
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%--电话--%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="phone"><spring:message
                                    code="CommunityOverview.phone"/></label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" placeholder="" id="phone" name="phone"
                                       value="${communityOverview.phone}">
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%-- 发布时间 --%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="orderDate"><spring:message
                                    code="CommunityOverview.orderDate"/> <font color="red">*</font></label>
                            <div class="input-group date form_date col-sm-7" data-date=""
                                 data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input1">
                                <input type="text" class="form-control" placeholder="" id="orderDate"
                                       value="${orderDate}"
                                       name="orderDate" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%-- 首页推荐图 --%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label">首页推荐图</label>
                            <div class="col-sm-7">
                                <input id="homePageimgUpload" name="homePageimgFile" type="file" multiple/>
                                <p style="color: red">
                                    <span>您将上传首页推荐图，建议宽高比为700x400！</span>
                                </p>
                                <input type="hidden" id="homePageimgUrl" name="homePageimgUrl" value="${communityOverview.panoramaImg}"/>
                            </div>
                        </div>
                        <%-- 首页推荐图原图 --%>
                        <div id="homePageimg" class="form-group col-xs-8">
                            <label class="col-sm-2 control-label">首页推荐图原图</label>
                            <div class="col-sm-4">
                                <img src="${communityOverview.panoramaImg}" alt="首页推荐图原图" class="img-thumbnail" style="width: auto">
                            </div>
                        </div>
                        <%-- H5链接 --%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label" for="url">H5页面链接地址</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" placeholder="" id="url" name="url"
                                       value="${communityOverview.url}">
                            </div>
                        </div>
                        <div class='clearfix'></div>
                        <%--编辑楼盘详情页_20170522尝试替代该功能--%>
                        <%--
                        <div class="form-group col-lg-7">
                            <label class="col-sm-2 control-label"><spring:message
                                    code="CommunityOverview.goEditNote"/></label>
                            <div class="col-sm-9">
                                <button type="button" class="btn btn-primary" onclick="gotoEdit()"><spring:message
                                        code="CommunityOverview.goedit"/></button>
                            </div>
                        </div>
                        --%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label"></label>
                            <div class="col-sm-7">
                                <p style="color: red">
                                    <span>以下各类型图片上传请同时上传多张图片，不要多次上传！</span>
                                </p>
                            </div>
                        </div>
                        <%-- 户型图 --%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label">户型图</label>
                            <div class="col-sm-7">
                                <input id="floorPlanUpload" name="floorPlanFile" type="file" multiple/>
                                <input type="hidden" id="floorPlanUrl" name="floorPlanUrl" value="${butlerStyle.wechatQRCodeUrl}"/>
                            </div>
                        </div>
                        <%-- 总平面图 --%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label">总平面图</label>
                            <div class="col-sm-7">
                                <input id="masterPlanUpload" name="masterPlanFile" type="file" multiple/>
                                <input type="hidden" id="masterPlanUrl" name="masterPlanUrl" value="${butlerStyle.wechatQRCodeUrl}"/>
                            </div>
                        </div>
                        <%-- 室内设计图 --%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label">室内</label>
                            <div class="col-sm-7">
                                <input id="interiorDesignUpload" name="interiorDesignFile" type="file" multiple/>
                                <input type="hidden" id="interiorDesignUrl" name="interiorDesignUrl" value="${butlerStyle.wechatQRCodeUrl}"/>
                            </div>
                        </div>
                        <%-- 园林设计图 --%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label">园林</label>
                            <div class="col-sm-7">
                                <input id="gardenDesignUpload" name="gardenDesignFile" type="file" multiple/>
                                <input type="hidden" id="gardenDesignUrl" name="gardenDesignUrl" value="${butlerStyle.wechatQRCodeUrl}"/>
                            </div>
                        </div>
                        <%-- 配套设施 --%>
                        <div class="form-group col-xs-8">
                            <label class="col-sm-2 control-label">配套</label>
                            <div class="col-sm-7">
                                <input id="supportingFacilitiesUpload" name="supportingFacilitiesFile" type="file" multiple/>
                                <input type="hidden" id="supportingFacilitiesUrl" name="supportingFacilitiesUrl" value="${butlerStyle.wechatQRCodeUrl}"/>
                            </div>
                        </div>

                        <div class='clearfix'></div>
                        <input type="hidden" name="releaseStatus" id="releaseStatus" value="">
                        <button type="button" class="btn btn-primary" onclick="validate('1')"><spring:message
                                code="activityManage.activityPublish"/></button>
                        <button type="button" class="btn btn-primary" onclick="validate('0')"><spring:message
                                code="activityManage.activityUnPublish"/></button>
                        <a href="../overview/List.html?menuId=005300020000" class="btn btn-primary" for=""><spring:message
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
<!--日期控件-->
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">
    $(function () {
        if ($("#name").val() == null || $("#name").val() == "") {
            $("#cityList").val("");
            $("#cityList").html("");
            $("#cityIds").val("");
            $("#projectList").val("");
            $("#projectList").html("");
            $("#projectIds").val("");
            $("#orderDate").val("");
        }
    });
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
<%--fileinput图片上传控件--%>
<script>
    $(function(){
        initFileInput1("floorPlanUpload", "");
        initFileInput1("masterPlanUpload", "");
        initFileInput1("interiorDesignUpload", "");
        initFileInput1("gardenDesignUpload", "");
        initFileInput1("supportingFacilitiesUpload", "");
        initFileInput2("homePageimgUpload", "");
        if ($("#homePageimgUrl").val() != ""){
            $("#homePageimg").show();
        }
    });
    //初始化fileinput控件（第一次初始化）
    function initFileInput1(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png', 'jpeg'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showCaption: true,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            maxFileCount: 10, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        });
    }
    function initFileInput2(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png', 'jpeg'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showCaption: true,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        });
    }
</script>
<script>
    function validate(status) {
        //校验
        if (!CheckNull($("#cityList").val(), "请添加城市!")) {
            return false;
        }
        if ($("#cityList").val() != "全部城市") {
            if (!CheckNull($("#projectList").val(), "请添加项目!")) {
                return false;
            }
        }
        if (!CheckNull($("#name").val(), "请添加项目名称！")) {
            return false;
        }
        if (!CheckNull($("#orderDate").val(), "请选择项目开盘时间!")) {
            return false;
        }
        var types = $("#types").val();
        $("#types").val(types.replace(/\，/g, ","));
        $("#releaseStatus").val(status);
        if ($("#form").validate()) {
            $("#form").submit();
        }
    }
    function checkVal() {
        $("#form").validate();
    }
    $().ready(function () {
        var validator = $("#form").validate({
            errorElement: "span",
            rules: {
                name: {
                    required: true
                },
                cityScope: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "请输入项目名称!"
                },
                cityScope: {
                    required: "请输入区域城市!"
                }
            }
        })
    });
</script>
<script>
    function queryProjectNameById() {
        var projectId = $("#city").val();
        if (projectId == '-1') {
            $("#projectName").empty();
            $("#projectName").append('<option value="-1">--请选择项目--</option>');
            return;
            return;
        }
        $("#planName").empty();
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId + "/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectName").empty();
            <%--$("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');--%>
            for (var k in arr) {
                $("#projectName").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
//                alert(arr[k][0]);
            }
        });
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
        if (projectId == '-1') {
            alert("请选择项目，并添加！");
            return;
        }
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

    function gotoEdit() {
        var url = $("#url").val();
        if (url != "") {
            alert("H5页面链接地址与编辑楼盘详情页只能二选一！");
            return;
        } else {
            document.getElementById("form").action = "../overview/editOverviewDetail";
            document.getElementById("form").submit();
        }
    }
</script>
</body>
</html>