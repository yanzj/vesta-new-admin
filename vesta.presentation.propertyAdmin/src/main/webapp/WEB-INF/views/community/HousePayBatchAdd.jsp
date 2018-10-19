<%--
  Created by IntelliJ IDEA.
  User: liudongxin
  Date: 2016/2/20
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
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
    function validate(status) {
        if(!CheckNull($("#deliveryBatch").val(),"活动主题不能为空！")){
            return false;
        }
        deliveryBatch = $("#deliveryBatch").val();//批次名称
        if(deliveryBatch == ""){
            alert("活动主题不能为空");
            return false;
        }
        if (deliveryBatch.length > 20) {
            alert("活动内容不能大于二十字");
            return false;
        }
        if(!CheckNull($("#description").val(),"描述信息不能为空！")){
            return false;
        }
    description = $("#description").val();//描述
        if(description == ""){
        alert("描述信息不能为空");
        return false;
        }
        if (description.length > 200) {
            alert("描述信息不能大于二百字");
            return false;
        }
        if(!CheckNull($("#maxUser").val(),"接待人数人次上限不能为空！")){
            return false;
        }
    maxUser = $("#maxUser").val();//没时间段接待人次上线
        if(maxUser == ""){
        alert("接待人数人次上限不能为空");
        return false;
        }else{
            var patrn=/^([+]?)(\d+)$/;
            if(patrn.test(maxUser) == false){
                alert("请填写整形数字");
                return false;
            }

        }
        if(!CheckNull($("#payStaDate").val(),"活动开始日期不能为空！")){
            return false;
        }
        payStaDate = $("#payStaDate").val();//开始时间
        if (payStaDate == "") {
            alert("活动开始日期不能为空");
            return false;
        }
        if(!CheckNull($("#payEndDate").val(),"不能为空！")){
            return false;
        }
    payEndDate = $("#payEndDate").val();//结束时间
        if (payEndDate == "") {
            alert("不能为空");
            return false;
        }

    //    activityimgpath=$("#activityimgpath").val();//详情图片
    //    if(hotline==""){
    //      alert("活动详情不能为空");
    //      return false;
    //    }

    $("#status").val(status);
    //    if($("#title").val()==)

    document.getElementById("frm").submit();
    }
</script>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005800050000" username="${propertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">

            <div class="form-body">
                <h3 class="text-center">&nbsp;</h3>

                <div class="form-body" style="min-height:380px">
                    <form class="form-horizontal" id="frm" action="../communityArea/saveBatchManage"
                          method="post" enctype="MULTIPART/FORM-DATA">

                        <!-- 项目 -->
                        <div class="form-group  col-lg-6">
                            <label for="communityName" class="col-sm-3 control-label"><spring:message
                                    code="propertyCompany.propertyProject"/></label>

                            <div class="col-sm-9">
                                <select id="communityName" name="communityName" class="form-control">
                                    <c:forEach items="${communityAppointPageMapDto.projectNameMap}" var="item">
                                        <option value="${item.value }"
                                                <c:if test="${item.value eq communityAppointManageDto.communityName}">selected</c:if>>
                                                ${item.value }

                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>



                        <div class='clearfix'> </div>
                        <%--描述--%>
                        <div class="form-group col-lg-12">
                            <label class="col-sm-1 control-label" style="min-width:115px;"><spring:message
                                    code="HousePayBatch.description"/></label>

                            <div class="col-sm-8">
                                <textarea class="form-control" name="description" id="description" style="width: 763px; height: 71px;"></textarea>
                            </div>
                        </div>


                        <div class='clearfix'> </div>
                        <%--开始时间--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="payStaDate"><spring:message
                                    code="HousePayBatch.startDate"/></label>

                            <div class="input-group date form_date col-sm-8" data-date=""
                                 data-date-format="yyyy-mm-dd hh:ii:ss"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="" id="payStaDate" value=""
                                       name="payStaDate" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <%--//结束时间--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="payEndDate"><spring:message
                                    code="HousePayBatch.endDate"/></label>

                            <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input2">
                                <input type="text" class="form-control" placeholder="" id="payEndDate" value=""
                                       name="payEndDate" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>

                        <div class='clearfix'> </div>
                        <%--批次名称--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="deliveryBatch"><spring:message
                                    code="HousePayBatch.name"/></label>
                            <input type="hidden" id="id" name="id" value=""/>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="deliveryBatch"
                                       name="deliveryBatch" value="">
                            </div>
                        </div>
                        <div class='clearfix'> </div>
                        <%--报名人数上限--%>
                        <div class="form-group col-lg-6">
                            <label class="col-sm-3 control-label" for="maxUser"><spring:message
                                    code="HousePayBatch.maxUser"/></label>

                            <div class="col-sm-9" style="padding-top:7px;">
                                <input type="text" class="form-control" placeholder="" id="maxUser" name="maxUser"
                                       value="">
                            </div>
                        </div>
                        <div class='clearfix'> </div>
                        <input type="hidden" name="status" id="status">

                        <div class="text-center form-group  col-lg-12" style="padding:0;">
                            <button type="button" class="btn btn-primary" onclick="validate('1')"><spring:message
                                    code="common_confirm"/></button>
                            <a href="../communityArea/HousePayBatch.html" class="btn btn-primary" for=""><spring:message
                                    code="common_cancel"/></a>
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

    var inputs = $("#homePageimgpath").get(0);
    var result = document.getElementById("demo_homePageimgpath");
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
                results.innerHTML = '<img src="' + this.result + '" style="width:100px" alt=""/>';
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
</body>
</html>
