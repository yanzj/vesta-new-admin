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
            $("#005500070000").addClass("active");
            $("#005500070000").parent().parent().addClass("in");
            $("#005500070000").parent().parent().parent().parent().addClass("active");
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
                 crunMenu="005500070000" username="${propertystaff.staffName}"/>
    <input type="hidden" id="menuId" name="menuId" value="005500070000"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" action="../productInfo/saveOrUpdateproductInfo.html" method="post" enctype="MULTIPART/FORM-DATA">
                    <input type="hidden" id="questId" name="questId" value="${quest.questId}">

                    <!-- 城市 -->
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="questName">发布区域</label>
                        <div class="col-sm-6">
                            <select id="scopeId" name="cityId" class="form-control" onchange="queryProjectNameById()">
                                <option value="">请选择</option>
                                <c:forEach items="${city}" var="item" >
                                    <option value="${item.cityId }"
                                            <c:if test="${item.cityId eq quest.cityId}">selected</c:if>>${item.cityName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!-- 项目 -->
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="questName">发布范围</label>
                        <div class="col-sm-6">
                            <select id="projectCode" name="projectNum" class="form-control">
                                <c:forEach items="${projectList}" var="item" >
                                    <option value="${item[0] }"
                                            <c:if test="${item[0] eq quest.projectName}">selected</c:if>>${item[1]}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%-- 问卷标题 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="questName">问卷标题</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="questName" name="questName" value="${quest.questName}">
                        </div>
                    </div>

                    <%-- 问卷积分 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="questName">问卷积分</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="ruleNum" name="ruleNum" value="${quest.ruleNum}">
                        </div>
                    </div>

                    <%-- 起始日期 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="beginDate">开始时间</label>
                        <div class="col-sm-5">
                            <div class="input-group date form_datetime" data-date=""
                                 data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input1">
                                <input type="text" class="form-control " placeholder="" id="beginDate" name="beginDate"
                                       value="${quest.beginDate}" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>


                    <%-- 结束日期 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="endDate">结束时间</label>
                        <div class="col-sm-5">
                            <div class="input-group date form_datetime" data-date=""
                                 data-date-format="yyyy-mm-dd"
                                 data-link-field="dtp_input1">
                                <input type="text" class="form-control " placeholder="" id="endDate" name="endDate"
                                       value="${quest.endDate}" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>


                    <%--报名信息--%>
                    <div class="form-group col-lg-7"  id="addText">



                    </div>

                    <%-- 问卷标题 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="questName">增加题目</label>
                        <div class="col-sm-5">
                            <input type="button" id="addTextInput" name="addTextInput" value="增加题目">
                        </div>
                    </div>

                    <%-- 问答题目 --%>
                    <div class="form-group col-lg-7">
                        <label class="col-sm-3 control-label" for="questName">问答题目</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" placeholder="" id="wdName" name="wdName" value="${quest.questName}">
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
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
<script type="application/javascript">
    var i = 1;
    j = 1;
    var l = 1;
    $(function(){
        $('#addTextInput').click(function(){
            $('#addText').append('' +
                '<div id="seled'+i+'" name="seled'+i+'"><label class="col-sm-3 control-label" for="title">题目</label>' +
                '<div class="col-sm-7" id="addTextSele'+i+'">' +
                '<div>' +
                ' <input type="text" class="form-control" placeholder="" id="title" name="title"> 单选<input type="radio"  placeholder="" id="radios'+i+'" name="radios'+i+'" value="1" checked > 多选<input type="radio" placeholder="" id="radios'+i+'" name="radios'+i+'" value="2" >' +
                '<input type="button" id="addTextInput'+i+'" name="addTextInput'+i+'" value="增加选项" onclick="addT('+i+')"><input type="button" onclick="dels('+i+')" value="删除题目"/>' +
                '<div id="test'+i+'"><input type="text" class="form-control" placeholder="" name="sele'+i+'"/>' +
                '<input id="'+l+'" name="productPicFile'+i+'" type="file" multiple onchange="imageChcek(this)"/>' +
                '<input id="urlId'+l+'" name="urlId'+i+'" type="hidden" />' +
                '上传图片 <input type="button" onclick="del('+i+')" value="删除"/></div></div></div></div>');
            i++;
            l++;
        });
    });

    function addT(i) {
        $('#addTextSele'+i).append('<div id="testt'+j+'"><input type="text" class="form-control" placeholder="" name="sele'+i+'"/>' +
            '<input id="'+l+'" name="productPicFile'+i+'" type="file" multiple onchange="imageChcek(this)"/>' +
            '<input id="urlId'+l+'" name="urlId'+i+'" type="hidden" />' +
            '上传图片  <input type="button" onclick="dele('+j+')" value="删除"/></div>');

        j =j+1;
        l++;
    }

    function dele(o) {
        $("div").remove("#testt"+o);
    }

    function del(o) {
        $("div").remove("#test"+o);
    }

    function del(o) {
        $("div").remove("#test"+o);
    }
    function dels(o) {
        $("div").remove("#seled"+o);
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

    //通过城市获取项目列表
    function queryProjectNameById() {
        $("#projectCode").find("option").remove();
        /* -------------------- */
        var projectId = $("#scopeId").val();
        if(projectId == '-1'){
            $("#projectCode").empty();
            return ;
        }
        $.getJSON("/announcement/queryProjectNameByCityId/" + projectId +"/" + $("#menuId").val(), function (data) {
            var arr = data.data;
            $("#projectCode").empty();
            $("#projectCode").append('<option value="">请选择</option>');
            for (var k in arr) {
                if(arr[k][0] != '0'){
                    $("#projectCode").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
                }
            }
        });
    }
    var tasa = "";
    //保存
    function toSave(){

        var formData = new FormData();


        if($("#scopeId").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请选择城市！");
            return;
        }else{
            formData.append("cityId",$("#scopeId").val()); //城市
        }

        if($("#projectCode").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请选择项目！");
            return;
        }else{
            formData.append("projectNum",$("#projectCode").val()); //项目
        }

        if($("#questName").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入标题！");
            return;
        }else{
            formData.append("questName",$("#questName").val()); //问卷标题
        }
        if($("#beginDate").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入起始日期！");
            return;
        }else{
            formData.append("beginDate",$("#beginDate").val()); //起始日期
        }
        if($("#endDate").val().replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("请输入结束日期！");
            return;
        }else{
            formData.append("endDate",$("#endDate").val()); //结束日期
        }
        formData.append("ruleNum",$("#ruleNum").val()); //问卷积分
        formData.append("wdName",$("#wdName").val()); //问答题目


        var testa =[];
        obj = document.getElementsByName("title");
        var c = 1;

        for(i=0;i<obj.length;i++){
            var stu= {"name":"","radios":"","sel":[]};
            stu.name=obj[i].value;
            var radios=$('input:radio[name="radios'+c+'"]:checked').val();
            stu.radios = radios;
            var objs = document.getElementsByName("sele"+c);
            var urls = document.getElementsByName("urlId"+c);
            var test =[];
            for(j=0;j<objs.length;j++){
                var tests ={"m":"","url":""};
                tests.url = urls[j].value;
                tests.m = objs[j].value;
                test.push(tests);
            }

            stu.sel =test;
            testa.push(stu);
            c++;
        }
        formData.append("tt",JSON.stringify(testa));

        $.ajax({
            url:"../quest/addQuest",
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
                    window.location.href = "../quest/quest.html?menuId=005500070000";
                }else if(data.error == "1"){
                    alert("公众号配置模块已存在！");
                }else if(data.error == "-1"){
                    alert("保存失败，请联系管理员！");
                }

            }
        });
    }



    function imageChcek(id) {
        var formData = new FormData();
        formData.append("url",id.files[0]);
        $.ajax({
            url:"../quest/addImage",
            type:"POST",
            async:"false",
            data:formData,
            processData:false,
            contentType:false,
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(data){
               $("#urlId"+id.id).val(data.uname);
            }
        });

    }



</script>


</body>
</html>