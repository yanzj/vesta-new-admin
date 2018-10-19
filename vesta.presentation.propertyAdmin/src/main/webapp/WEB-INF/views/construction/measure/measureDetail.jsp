<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
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
        $(function () {
            $("#007900010000").addClass("active");
            $("#007900010000").parent().parent().addClass("in");
            $("#007900010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <%--<link rel="stylesheet" href="../static/css/jquery-ui.min.css" />
    <script src="../static/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>--%>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
<style>
    .tableUl{
        overflow: hidden;
    }
    .tableUl li{
        float: left;
        display: inline-block;
        padding: 10px;
        border-left: 1px solid #ccc;
        border-top: 1px solid #ccc;
        border-bottom: 1px solid #ccc;

    }
    .tableUl li:last-child
    {
        border-right: 1px solid #ccc;
    }
     .btnactive
    {
    color: #fff!important;
    background-color: #003159;
    }
    .tableStyle tbody td{
        min-width: 100px;
    }
</style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007900010000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body" style="overflow:hidden;font-size:13px;line-height:25px;font-family:'微软雅黑';">
                <form class="form-horizontal" id="fromAdd" method="post" enctype="multipart/form-data">
                    <div class="form-group col-lg-8">
                        <label class="col-sm-3 control-label">所属区域：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_regionName">${measureDTO.regionName}</span>
                        </div>
                    </div>
                    <div class="form-group col-lg-8" style="margin-top: 7px">
                        <label class="col-sm-3 control-label">所属城市：</label>
                        <div class="col-sm-5">
                            <span id="span_cityName">${measureDTO.cityName}</span>
                        </div>
                    </div>
                    <div class="form-group col-lg-8">
                        <label class="col-sm-3 control-label">所属项目名称：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_projectName">${measureDTO.projectName}</span>
                        </div>
                    </div>
                    <div id="div_videoUrl" class="form-group col-lg-8">
                        <label class="col-sm-3 control-label">项目总合格率：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_projectPercentOfPass">${measureDTO.projectPercentOfPass}</span>
                        </div>
                    </div>
                    <div id="div_content" class="form-group col-lg-8">
                        <label class="col-sm-3 control-label">所属楼栋：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_buildingName">${measureDTO.buildingName}</span>
                        </div>
                    </div>
                    <div id="div_fileUrl" class="form-group col-lg-8">
                        <label class="col-sm-3 control-label">楼栋总合格率：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_buildPercentOfPass">${measureDTO.buildPercentOfPass}</span>
                        </div>
                    </div>
                    <div id="div_fileSize" class="form-group col-lg-8">
                        <label class="col-sm-3 control-label">所属楼层：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_floorName">${measureDTO.floorName}</span>
                        </div>
                    </div>
                    <div id="div_visits" class="form-group col-lg-8">
                        <label class="col-sm-3 control-label">标段实测合格率：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_mensurePercentOfPass">${measureDTO.mensurePercentOfPass}</span>
                        </div>
                    </div>
                    <div id="div_" class="form-group col-lg-8">
                        <label class="col-sm-3 control-label">上传人员：</label>
                        <div class="col-sm-5" style="margin-top: 7px">
                            <span id="span_createBy">${measureDTO.createBy}</span>
                        </div>
                    </div>
                </form>
                <div id="div_button" class="form-group col-lg-8">
                    <div class="col-sm-4 rightButton" style="text-align: center">
                        <button class="btn btn-primary" onclick="javaScript:history.go(-1)">确认</button>
                    </div>
                </div>
                <div class="tableStyle col-md-12" style="    overflow-x: scroll;max-width: 1600px;">

                    <ul class="tableUl">
                        <c:forEach items="${measureInspectionPhaseDTOs}" var="measureInspectionPhaseDTO">
                            <li class="  btn-js" onclick="measureModelByInspectionPhaseAndId({inspectionPhaseId:'${measureInspectionPhaseDTO.inspectionPhaseId}',unitId:'${measureInspectionPhaseDTO.unitId}'})">
                            ${measureInspectionPhaseDTO.inspectionPhaseName}
                            <c:if test="${not empty measureInspectionPhaseDTO.unitName}">-${measureInspectionPhaseDTO.unitName}</c:if>
                        </li></c:forEach>
                    </ul>

                    <table class="tableStyle  col-md-12" border="1">
                        <thead>
                            <tr id="table_tr">

                            </tr>
                        </thead>
                        <tbody id="meaure_tbody">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script>

    function measureModelByInspectionPhaseAndId(param) {
        $(" .btn-js").click(function() {
            $(" .btn-js").removeClass("btnactive");
            $(this).addClass("btnactive").siblings().removeClass("btnactive");
        });
        var floorId = '${measureDTO.floorId}';
        var inspectionPhaseId = param.inspectionPhaseId;
        var unitId = param.unitId;
        $.ajax({
            url: "../measure/measureModelAndData",
            type: "get",
            async: "false",
            data: {"floorId": floorId,
            "inspectionPhaseId":inspectionPhaseId,
            "unitId":unitId},
            dataType: "json",
            traditional: true,
            error: function (data,type, err) {
                alert("网络异常，可能服务器忙，请刷新重试");
                console.log("ajax错误类型："+type);
                console.log(err);

            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var html = "";
                    if (data != null) {
                        var num = data[0].num;
                        document.getElementById("meaure_tbody").innerHTML = "";
                        html+="<tr><td>序号</td><td>检查分项</td><td>检查内容</td><td>评判标准</td><td>检查点数(最多)</td><td>合格率</td><td>数据</td>";
                        for(var i = 0;i< num-1;i++){
                            html+="<td></td>"
                        }
                        html+="</tr>";
                        for (var j = 0;j< data.length;j++) {
                            html = html + "<tr><td>"+(j+1)+"</td><td>"+data[j].inspectionPhaseName+"</td><td>"+data[j].inspectionContentName+"</td>" +
                                "<td>"+data[j].evaluationCriteria+"</td><td>"+data[j].checkPoints+"</td><td>"+data[j].qualificationRate+"</td>";
                            var dataArr = data[j].data;
                            var index = 0;
                            if(dataArr != null && dataArr != undefined && dataArr != ''){
                                for(var i = 0;i< dataArr.length;i++){
                                    html+="<td>"+dataArr[i]+"</td>"
                                    index++;
                                }
                            }
                            for(var i = 0;i< num-index;i++){
                                html+="<td></td>"
                            }
                            html+="</tr>"
                        }
                        $("#meaure_tbody").append(html);
                    }
                }
            }
        });
    }

    function tableInit() {
        $(".tableUl li").eq(0).addClass("btnactive").siblings().removeClass("btnactive");
        var measureInspectionPhaseDTOs = '${measureInspectionPhaseDTOs}'
        var measureInspectionPhaseDTO = '';
        if(measureInspectionPhaseDTOs != undefined && measureInspectionPhaseDTOs != null && measureInspectionPhaseDTOs != ''){
            measureInspectionPhaseDTO = measureInspectionPhaseDTOs[0];
        }
        measureModelByInspectionPhaseAndId({inspectionPhaseId:'${measureInspectionPhaseDTOs[0].inspectionPhaseId}',unitId:'${measureInspectionPhaseDTOs[0].unitId}'})
    }
    tableInit();
</script>
</body>
</html>