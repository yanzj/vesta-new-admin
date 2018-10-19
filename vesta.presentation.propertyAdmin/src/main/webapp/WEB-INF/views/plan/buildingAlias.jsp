<%--
  Created by IntelliJ IDEA.
  User: lpc
  Date: 2016/6/5
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
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
        label.error {
            /*position: absolute;*/
            /*margin-top: -74px;*/
            /*display: block;*/
            margin-left: 1%;
            color: red;
        }
    </style>
    <style>
        td {
            white-space: nowrap;
            padding-right: 15px;
            overflow: hidden;
            text-overflow: ellipsis; /*display:-webkit-box;*/
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 2;

            /*white-space:nowrap;overflow:hidden;text-overflow: ellipsis;*/
        }

    </style>
    <style type="text/css">#dialog {
        display: none;
    }</style>
    <script type="application/javascript">

        function checkAllBox(obj) {
            var answer = document.getElementsByName("answer");
            if (obj.checked == true) {
                for (var i = 0; i < answer.length; i++) {
                    answer[i].checked = true;
                }
            } else {
                for (var i = 0; i < answer.length; i++) {
                    answer[i].checked = false;
                }
            }
        }
        function checkData() {
            var answer = document.getElementsByName("answer");
            var flag = false;
            for (var i = 0; i < income.length; i++) {
                if (income[i].checked == true) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                alert("请至少选择一项");
            }
            return flag;
        }

        function deleteQuestion(rectifyId, proType) {
            if (window.confirm("确定作废吗？")) {
                window.location.href = "../problem/deleteQeustion?rectifyId=" + rectifyId + "&problemType=" + proType;
            }

            /*$("#dialog").dialog({
             resizable: false,
             height: 240,
             width: 500,
             modal: true,
             buttons: {
             "确定": function () {
             window.location.href = "../problem/editProblemManagement?rectifyId="+rectifyId;
             },
             "取消": function () {
             $(this).dialog("close");
             }
             }
             });*/
        }
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="002100020000" username="${authPropertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" id="frm" name="search" action="../problem/buildingAlias.html"
                      method="get">

                    <%-- 集团名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="groupId" class="col-sm-4 control-label"><spring:message
                                code="problem.group"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="groupId" name="groupId">
                                <c:forEach items="${groups}" var="group">
                                    <option value="${group.key }"
                                            <c:if test="${group.key eq problem.groupId}">selected</c:if>>${group.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 区域名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="regionId" class="col-sm-4 control-label"><spring:message
                                code="problem.region"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="regionId" name="regionId">
                                <c:forEach items="${regions}" var="region">
                                    <option value="${region.key }"
                                            <c:if test="${region.key eq problem.regionId}">selected</c:if>>${region.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 城市名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="cityId" class="col-sm-4 control-label"><spring:message
                                code="problem.city"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="cityId" name="cityId">
                                <c:forEach items="${citys}" var="city">
                                    <option value="${city.key }"
                                            <c:if test="${city.key eq problem.cityId}">selected</c:if>>${city.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%-- 项目名称 --%>
                    <div class="form-group  col-lg-4">
                        <label for="projectId" class="col-sm-4 control-label"><spring:message
                                code="problem.project"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="projectId" name="projectId">
                                <c:forEach items="${projects}" var="item">
                                    <option value="${item.key }"
                                            <c:if test="${item.key eq problem.projectId}">selected</c:if>>${item.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                        <%-- 地块名称 --%>
                        <div class="form-group  col-lg-4">
                            <label for="areaId" class="col-sm-4 control-label"><spring:message
                                    code="problem.area"/>：</label>

                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="areaId" name="areaId">
                                    <c:forEach items="${areas}" var="area">
                                        <option value="${area.key }"
                                                <c:if test="${area.key eq problem.areaId}">selected</c:if>>${area.value }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    <%-- 楼栋 --%>
                    <div class="form-group  col-lg-4">
                        <label for="buildingId" class="col-sm-4 control-label"><spring:message code="problem.building"/>：</label>

                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="buildingId" name="buildingId">
                                <c:forEach items="${buildings}" var="build">
                                    <option value="${build.key }"
                                            <c:if test="${build.key eq problem.buildingId}">selected</c:if>>${build.value }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%-- 楼栋别名 --%>
                    <div class="form-group  col-lg-4">
                        <label for="buildingAlias" class="col-sm-4 control-label"><spring:message
                                code="alias.building"/>：</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" value="${problem.buildingAlias}" placeholder=""
                                   id="buildingAlias" name="buildingAlias">
                        </div>
                    </div>

                    <%--<input type="hidden" id="proType" name="proType" value="${problem.proType}" />--%>

                    <%--<div class="form-group  col-lg-6">--%>
                    <%--<label for="startDate" class="col-sm-4 control-label"><spring:message code="workOrders.startDate"/></label>--%>
                    <%--<div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"--%>
                    <%--data-link-field="dtp_input2">--%>
                    <%--<input type="text" class="form-control" placeholder="请输入开始时间" path="startDate" name="startDate" value="${problem.startDate}" readonly="true" />--%>
                    <%--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>--%>
                    <%--<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group  col-lg-6">--%>
                    <%--<label for="endDate" class="col-sm-4 control-label"><spring:message code="workOrders.endDate"/></label>--%>
                    <%--<div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">--%>
                    <%--<input type="text" class="form-control" placeholder="请输入结束时间" path="endDate" name="endDate" value="${problem.endDate}" readonly="true"/>--%>
                    <%--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>--%>
                    <%--<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <div class="clearfix"></div>
                    <button type="button" id="" onclick="checkSubmit()" class="btn btn-primary" for="propertySearch">
                        <spring:message code="problem.search"/></button>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <form action="../problem/batchCommit">
            <%--<button type="button" class="btn btn-primary" for="propertySearch" onclick="batchCommit()">批量提交草稿</button>--%>
            <table width="100%" class="tableStyle" style="table-layout: fixed;">
                <thead>
                <tr>
                    <th width="7%">序号</th>
                    <th width="25%">项目名称</th>
                    <th width="10%">预售楼号</th>
                    <th width="10%">备案楼号</th>
                    <th width="25%">位置</th>
                    <th width="18%">楼栋别名</th>
                    <%--<th width="10%">登记时间</th>--%>
                    <%--<th width="5%">状态</th>--%>
                    <th width="15%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${buildingList}" var="building" varStatus="row">
                    <tr>
                        <td height="40px">${(webPage.pageIndex-1)*20+row.index + 1}</td>
                        <td height="40px">${problem.projectName}</td>
                        <td height="40px">${building.buildingSale}</td>
                        <td height="40px">${building.buildingRecord}</td>
                        <td height="40px">${building.street}</td>
                        <td height="40px" id="${building.buildingNum}">${building.name}</td>
                        <td height="40px">
                            <c:if test="${function.qch40010072 eq 'Y'}">
                                <a href="javascript:void(0)" onclick="edit(this)"><spring:message code="alias.edit"/></a>
                            </c:if>
                            <c:if test="${function.qch40010074 eq 'Y'}">
                                <a href="javascript:void(0)" onclick="forbid(this)"><spring:message
                                        code="alias.forbid"/></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
        <%--&startDate=${problem.startDate}

        &endDate=${problem.endDate}--%>

        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/problem/buildingAlias.html?pageIndex={0}&groupId=${problem.groupId}&regionId=${problem.regionId}&cityId=${problem.cityId}&projectId=${problem.projectId}&areaId=${problem.areaId}&buildingId=${problem.buildingId}&buildingAlias=${problem.buildingAlias}"/>
        <%--&projectId=${problem.projectId}&buildingId=${problem.buildingId}&roomId=${problem.roomId}&caseState=${problem.caseState}&oneType=${problem.oneType}&twoType=${problem.twoType}&threeType=${problem.threeType}&proType=${problem.proType}&startDate=${problem.startDate}&endDate=${problem.endDate}"/>--%>
    </div>

</div>
</div>
</div>
</div>

<!-- main content end-->
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
    var projectId = "${problem.projectId}";
    var buildingId = "${problem.buildingId}";
    <%--var unitId = "${problem.unitId}";--%>
    <%--var floorId = "${problem.floorId}";--%>
    <%--var roomId = "${problem.roomId}";--%>
    <%--var caseState = "${problem.caseState}";--%>
    <%--var oneType = "${problem.oneType}";--%>
    <%--var twoType = "${problem.twoType}";--%>
    <%--var threeType = "${problem.threeType}";--%>
    <%--var proType = "${problem.proType}";--%>
    <%--var startDate = "${problem.startDate}";--%>
    <%--var endDate = "${problem.endDate}";--%>
</script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">

    function batchCommit() {
        if (window.confirm("确定提交选中的草稿吗？")) {
            document.forms[1].submit();
        }
    }

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
<%@ include file="../main/foolter.jsp" %>
</div>

<script>


    //  $("#oneType").change(function(){
    //    var oneType = $("#oneType").val();
    //    $.ajax({
    //      url:"../problem/getSecondTypeList",
    //      type:"get",
    //      async:"false",
    //      data:{ "classifyOne":oneType},
    //      dataType:"json",
    //      error:function(){
    //        alert("网络异常，可能服务器忙，请刷新重试");
    //      },
    //      success:function(json){
    //
    <!-- 获取返回代码 -->
    //        var code = json.code;
    //        if(code != 0){
    //          var errorMessage = json.msg;
    //          alert(errorMessage);
    //        }else{
    //
    <!-- 获取返回数据 -->
    //          var data = json.data;
    //          console.log(data);
    //          var option ="";
    //          if(data != null){
    //            document.getElementById("twoType").innerHTML = "";
    //            for(var prop in data) {
    //              if(!isNaN(data[prop])){
    //              }else{
    //                if (data.hasOwnProperty(prop)) {
    //                  if(prop=='0000'){
    //                    option = option + "<option value='"+ prop+"'selected='selected'>" +  data[prop] + "</option>";
    //                  }else{
    //                    option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
    //                  }
    //
    //                }
    //              }
    //            }
    //
    //            $("#twoType").append(option);
    //          }
    //        }
    //      }
    //    });
    //  });
    //
    //
    //
    //  $("#twoType").change(function(){
    //    var twoType = $("#twoType").val();
    //    $.ajax({
    //      url:"../problem/getThirdTypeList",
    //      type:"get",
    //      async:"false",
    //      data:{ "classifyTwo":twoType},
    //      dataType:"json",
    //      error:function(){
    //        alert("网络异常，可能服务器忙，请刷新重试");
    //      },
    //      success:function(json){
    //
    <!-- 获取返回代码 -->
    //        var code = json.code;
    //        if(code != 0){
    //          var errorMessage = json.msg;
    //          alert(errorMessage);
    //        }else{
    //
    <!-- 获取返回数据 -->
    //          var data = json.data;
    //          var option ="";
    //          if(data != null){
    //            document.getElementById("threeType").innerHTML = "";
    //            for(var prop in data) {
    //              if(!isNaN(data[prop])){
    //              }else{
    //                if (data.hasOwnProperty(prop)) {
    //                  if(prop=='0000'){
    //                    option = option + "<option value='"+ prop+"'selected='selected'>" +  data[prop] + "</option>";
    //                  }else{
    //                    option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
    //                  }
    //                }
    //              }
    //            }
    //            $("#threeType").append(option);
    //          }
    //        }
    //      }
    //    });
    //  });
    //
    //  $("#threeType").change(function(){
    //    var threeType = $("#threeType").val();
    //    var projectId = $("#projectId").val();
    //    if(projectId != "" && projectId != "0000"){
    //      $.ajax({
    //        url:"../problem/getSupplierList",
    //        type:"get",
    //        async:"false",
    //        data:{ "classifyThree":threeType,"projectNum":projectId},
    //        dataType:"json",
    //        error:function(){
    //          alert("网络异常，可能服务器忙，请刷新重试");
    //        },
    //        success:function(json){
    //
    <!-- 获取返回代码 -->
    //          var code = json.code;
    //          if(code != 0){
    //            var errorMessage = json.msg;
    //            alert(errorMessage);
    //          }else{
    //
    <!-- 获取返回数据 -->
    //            var data = json.data;
    //            var option ="";
    //            if(data != null){
    //              document.getElementById("supplier").innerHTML = "";
    //              for(var prop in data) {
    //                if(!isNaN(data[prop])){
    //                }else{
    //                  if (data.hasOwnProperty(prop)) {
    //                    option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
    //                  }
    //                }
    //              }
    //              $("#supplier").append(option);
    //            }
    //          }
    //        }
    //      });
    //    }
    //  });

// 项目直接筛选楼栋
//    $("#projectId").change(function () {
//        var projectId = $("#projectId").val();
//        $("#buildingAlias").val("");
//        $.ajax({
//            url: "../problem/getBuildingListByProject",
//            type: "get",
//            async: "false",
//            data: {"projectId": projectId},
//            dataType: "json",
//            error: function () {
//                alert("网络异常，可能服务器忙，请刷新重试");
//            },
//            success: function (json) {
//                <!-- 获取返回代码 -->
//                var code = json.code;
//                if (code != 0) {
//                    var errorMessage = json.msg;
//                    alert(errorMessage);
//                } else {
//                    <!-- 获取返回数据 -->
//                    var data = json.data;
//                    var option = "";
//                    if (data != null) {
//                        document.getElementById("buildingId").innerHTML = "";
//                        $("#buildingId").append("<option value=''>请选择</option>");
//                        $.each(data, function (index, value) {
////              if (data.hasOwnProperty(prop)) {
//                            option = option + "<option value='" + value.buildingNum + "'>" + value.name + "</option>";
////              }
//                        });
//                        $("#buildingId").append(option);
//                    } else {
//                        document.getElementById("buildingId").innerHTML = "";
//                        $("#buildingId").append("<option value=''>请选择</option>");
//                    }
//                }
//            }
//        });
//    });
    //根据集团id查询区域信息
    $("#groupId").change(function () {
        var groupId = $("#groupId").val();
        $.ajax({
            url: "../problem/getQCAuthAgency",
            type: "get",
            async: "false",
            data: {"type": '100000001',
                "groupId":groupId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("regionId").innerHTML = "";
                        document.getElementById("cityId").innerHTML = "";
                        document.getElementById("projectId").innerHTML = "";
                        document.getElementById("areaId").innerHTML = "";
                        document.getElementById("buildingId").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }

                                }
                            }
                        }
                        $("#regionId").append(option);
                    }
                }
            }
        });
    });
    //根据区域id查询城市信息
    $("#regionId").change(function () {
        var regionId = $("#regionId").val();
        $.ajax({
            url: "../problem/getQCAuthAgency",
            type: "get",
            async: "false",
            data: {"type": '100000003',
                "regionId":regionId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("cityId").innerHTML = "";
                        document.getElementById("projectId").innerHTML = "";
                        document.getElementById("areaId").innerHTML = "";
                        document.getElementById("buildingId").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }

                                }
                            }
                        }
                        $("#cityId").append(option);
                    }
                }
            }
        });
    });


    //根据城市Id获取项目信息
    $("#cityId").change(function () {
        var cityId = $("#cityId").val();
        $.ajax({
            url: "../problem/getProjectMun",
            type: "get",
            async: "false",
            data: {"type": '100000002',
                "cityId":cityId,
            },
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("projectId").innerHTML = "";
                        document.getElementById("areaId").innerHTML = "";
                        document.getElementById("buildingId").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }
                                }
                            }
                        }
                        $("#projectId").append(option);
                    }
                }
            }
        });
    });

    $("#projectId").change(function () {
        var projectId = $("#projectId").val();
        $.ajax({
            url: "../houseroomtype/getAreaListByProject",
            type: "get",
            async: "false",
            data: {"projectId": projectId},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    var option = "";
                    if (data != null) {
                        document.getElementById("areaId").innerHTML = "";
                        document.getElementById("buildingId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#areaId").append(option);
                    }
                }
            }
        });
    });


    $("#areaId").change(function () {
        var area = $("#areaId").val();
        $.ajax({
            url: "../houseroomtype/getBuildingListByArea",
            type: "get",
            async: "false",
            data: {"areaId": area},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    var option = "";
                    if (data != null) {
                        document.getElementById("buildingId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#buildingId").append(option);
                    }
                }
            }
        });
    });

    //  $("#buildingId").change(function(){
    //    var buildingId = $("#buildingId").val();
    //    $.ajax({
    //      url:"../houseroomtype/getUnitList",
    //      type:"get",
    //      async:"false",
    //      data:{ "buildingId":buildingId},
    //      dataType:"json",
    //      error:function(){
    //        alert("网络异常，可能服务器忙，请刷新重试");
    //      },
    //      success:function(json){
    //
    <!-- 获取返回代码 -->
    //        var code = json.code;
    //        if(code != 0){
    //          var errorMessage = json.msg;
    //          alert(errorMessage);
    //        }else{
    //
    <!-- 获取返回数据 -->
    //          var data = json.data;
    //          var option ="";
    //          if(data != null){
    //            document.getElementById("unitId").innerHTML = "";
    //            for(var prop in data) {
    //              if(data[prop]=='无单元'){
    //                option="<option value=''>请选择单元</option>";
    //                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
    //              }else{
    //                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
    //              }
    //            }
    //            $("#unitId").append(option);
    //          }
    //        }
    //      }
    //    });
    //  });


    //模态框修改楼栋别名：别名不超过五十字
    function edit(obj) {
//    var t=$(obj).parent().prev().attr("id");
        var buildingNum = $(obj).parents("tr").children('td').eq(5).attr("id");
        var buildingAlias = $("#" + buildingNum).text();
        if (buildingAlias != null) {
            $("#updateName").val(buildingAlias);
        }
        $("#updateHiddenId").val(buildingNum);
        $("#myModal").modal("show");
    }
    function forbid(obj) {
        var buildingNum = $(obj).parents("tr").children('td').eq(5).attr("id");
        var buildingAlias = "";
        $.ajax({
            url: "../problem/updateBuildingAlias",
            type: "post",
            async: "false",
            data: {"buildingNum": buildingNum, "buildingAlias": buildingAlias},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (result) {
                if (result.code != 0) {
                    alert("禁用成功");
                    var data = result.data;
                    var buildingAlias = data.buildingAlias;
                    var buildingNum = data.buildingNum;
//          $("#myModal").modal("hide");
                    $("#" + buildingNum).text(buildingAlias);
                } else {
                    alert("禁用成功");
                    var data = result.data;
                    var buildingAlias = data.buildingAlias;
                    var buildingNum = data.buildingNum;
//          $("#myModal").modal("hide");
                    $("#" + buildingNum).text(buildingAlias);
                }
            }
        });
    }

    function save() {
//    alert("更新别名");
        var build_Alias = $("#updateName").val();
        if (build_Alias == null || build_Alias == "") {
            alert("别名不能为空！");
            return false;
        }
        build_Alias = $.trim(build_Alias);
        var build_Value = $("#updateHiddenId").val();
        if (build_Alias.length > 50) {
            alert("您输入的别名超过了50个字符！");
            return false;
        } else {
            $.ajax({
                url: "../problem/updateBuildingAlias",
                type: "post",
                async: "false",
                data: {"buildingAlias": build_Alias, "buildingNum": build_Value},
                dataType: "json",
                error: function () {
                    alert("网络异常，可能服务器忙，请刷新重试");
                },
                success: function (result) {
                    if (result.code != 0) {
                        alert(result.msg);
                        var data = result.data;
                        var buildingAlias = data.buildingAlias;
                        var buildingNum = data.buildingNum;
//          $("#myModal").modal("hide");
                        $("#" + buildingNum).text(buildingAlias);
                        $("#myModal").modal("hide");
                    } else {
                        var data = result.data;
                        var buildingAlias = data.buildingAlias;
                        var buildingNum = data.buildingNum;
                        $("#myModal").modal("hide");
                        $("#" + buildingNum).text(buildingAlias);
                    }
                }
            });

        }
    }

    function checkSubmit() {
        if ($("#groupId").val() == "") {
            alert("请选择集团");
            return false;
        }
        if ($("#regionId").val() == "") {
            alert("请选择区域");
            return false;
        }
        if ($("#cityId").val() == "") {
            alert("请选择城市");
            return false;
        }
        if ($("#projectId").val() == "") {
            alert("请选择项目");
            return false;
        }
        $("#frm").submit();
    }


</script>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="content" style="background: #fff;padding: 24px 30px;">
            <form class="form-horizontal" id="from" action="#" method="post">
                <div class="form-group" style="margin-left: 0;">
                    <label for="updateName" class="control-label">楼栋别名：</label>
                    <input type="text" class="form-control" placeholder="请填写别名，不超过50字符" id="updateName"
                           name="updateName" value="">
                    <input id="updateHiddenId" name='updateHiddenId' type="hidden" value="" }>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <c:if test="${function.qch40010073 eq 'Y'}">
                       <button type="button" onclick="return save()" class="btn btn-primary">保存</button>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

</html>