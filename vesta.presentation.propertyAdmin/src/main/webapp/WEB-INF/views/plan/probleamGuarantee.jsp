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
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <!--//Metis Menu -->
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

  <style type="text/css">#dialog{display:none;}</style>
  <style type="text/css">
    #test{
      display:block;/*内联对象需加*/
      width: 240px;
      word-break:keep-all;/* 不换行 */
      white-space:nowrap;/* 不换行 */
      overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */
      text-overflow:ellipsis;
    }
  </style>
  <script type="application/javascript">
      $(function(){
          $("#002000010000").addClass("active");
          $("#002000010000").parent().parent().addClass("in");
//          $("#002000010000").parent().parent().parent().parent().addClass("active");
      })
      function checkAllBox(obj){
          var answer= document.getElementsByName("answer");
          if(obj.checked==true){
              for(var i=0;i<answer.length;i++){
                  answer[i].checked = true;
              }
          }else{
              for(var i=0;i<answer.length;i++){
                  answer[i].checked = false;
              }
          }
      }
      function checkData(){
          var answer= document.getElementsByName("answer");
          var flag = false;
          for(var i=0;i<income.length;i++){
              if(income[i].checked == true){
                  flag = true;
                  break;
              }
          }
          if(!flag){
              alert("请至少选择一项");
          }
          return flag;
      }

      function deleteQuestion(repairId){
          if(window.confirm("确定作废吗？")){
              window.location.href = "../problemGuarantee/deleteRepair?rectifyId="+repairId;
          }
      }
  </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="002000010000" username="${authPropertystaff.staffName}" />

  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--搜索条件开始--%>
      <div class="form-body">
        <div class="form-horizontal">

          <form  name="" action="../problemGuarantee/problemGuaranteeManagement.html">
            <div style="margin-left: -5%;">
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
                    <c:forEach items="${proejcts}" var="proejct">
                      <option value="${proejct.key }"
                              <c:if test="${proejct.key eq problem.projectId}">selected</c:if>>${proejct.value }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
                <%-- 地块名称 --%>
                <div class="form-group  col-lg-4">
                  <label for="projectId" class="col-sm-4 control-label"><spring:message
                          code="problem.area"/>：</label>

                  <div class="col-sm-8">
                    <select class="form-control" placeholder="" id="area" name="area">
                      <c:forEach items="${typeMaps.areas}" var="item">
                        <option value="${item.key }"
                                <c:if test="${item.key eq problem.area}">selected</c:if>>${item.value }</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
              <%-- 楼栋 --%>
              <div class="form-group  col-lg-4">
                <label for="buildingId" class="col-sm-4 control-label"><spring:message code="problem.building"/>：</label>
                <div class="col-sm-8">
                  <select class="form-control" placeholder="" id="buildingId" name="buildingId">
                    <c:forEach items="${typeMaps.buildings}" var="item">
                      <option value="${item.key }" <c:if test="${item.key eq problem.buildingId}">selected</c:if>>${item.value }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>

              <%--单元 --%>
              <div class="form-group  col-lg-4">
                <label for="unitId" class="col-sm-4 control-label"><spring:message code="problem.unit"/>：</label>
                <div class="col-sm-8">
                  <select class="form-control" placeholder="" id="unitId" name="unitId">
                    <c:forEach items="${typeMaps.units}" var="item">
                      <option value="${item.key }" <c:if test="${item.key eq problem.unitId}">selected</c:if>>${item.value }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>

              <%-- 楼层 --%>
              <div class="form-group  col-lg-4">
                <label for="floorId" class="col-sm-4 control-label"><spring:message code="problem.floor"/>：</label>
                <div class="col-sm-8">
                  <select class="form-control" placeholder="" id="floorId" name="floorId">
                    <c:forEach items="${typeMaps.floors}" var="item">
                      <option value="${item.key }" <c:if test="${item.key eq problem.floorId}">selected</c:if>>${item.value }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>

              <%-- 房间 --%>
              <div class="form-group  col-lg-4">
                <label for="roomId" class="col-sm-4 control-label"><spring:message code="problem.room"/>：</label>
                <div class="col-sm-8">
                  <select class="form-control" placeholder="" id="roomId" name="roomId">
                    <c:forEach items="${typeMaps.rooms}" var="item">
                      <option value="${item.key }" <c:if test="${item.key eq problem.roomId}">selected</c:if>>${item.value }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>

              <%-- 状态 --%>
              <div class="form-group  col-lg-4">
                <label for="caseState" class="col-sm-4 control-label"><spring:message code="problem.status"/>：</label>
                <div class="col-sm-8">
                  <select class="form-control" placeholder="" id="caseState" name="caseState">
                    <option value="" selected>请选择</option>
                    <%--<option value="草稿" <c:if test="${problem.caseState eq '草稿'}">selected</c:if>>草稿</option>--%>
                    <option value="accepted" <c:if test="${problem.caseState eq 'accepted'}">selected</c:if>>待接单</option>
                    <option value="completed" <c:if test="${problem.caseState eq 'completed'}">selected</c:if>>已完成</option>
                    <option value="closed" <c:if test="${problem.caseState eq 'closed'}">selected</c:if>>已废弃</option>
                    <option value="processing" <c:if test="${problem.caseState eq 'processing'}">selected</c:if>>处理中</option>
                    <option value="returnVisit" <c:if test="${problem.caseState eq 'returnVisit'}">selected</c:if>>已评价</option>
                  </select>
                </div>
              </div>

              <%-- 一级分类 --%>
              <div class="form-group  col-lg-4">
                <label for="oneType" class="col-sm-4 control-label"><spring:message code="problem.fristLevel"/>：</label>
                <div class="col-sm-8">
                  <select class="form-control" placeholder="" id="oneType" name="oneType">
                    <c:forEach items="${typeMaps.firstLevels}" var="item">
                      <option value="${item.key }" <c:if test="${item.key eq problem.oneType}">selected</c:if>>${item.value }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>

              <%-- 二级分类--%>
              <div class="form-group  col-lg-4">
                <label for="twoType" class="col-sm-4 control-label"><spring:message code="problem.secondLevel"/>：</label>
                <div class="col-sm-8">
                  <select class="form-control" placeholder="" id="twoType" name="twoType">
                    <c:forEach items="${typeMaps.secondLevels}" var="item">
                      <option value="${item.key }" <c:if test="${item.key eq problem.twoType}">selected</c:if>>${item.value }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>

              <%--  三级分类 --%>
              <div class="form-group  col-lg-4">
                <label for="threeType" class="col-sm-4 control-label"><spring:message code="problem.thirdLevel"/>：</label>
                <div class="col-sm-8">
                  <select class="form-control" placeholder="" id="threeType" name="threeType">
                    <c:forEach items="${typeMaps.thirdLevels}" var="item">
                      <option value="${item.key }" <c:if test="${item.key eq problem.threeType}">selected</c:if>>${item.value }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>

              <input type="hidden" id="proType" name="proType" value="${problem.proType}" />

              <div class="form-group  col-lg-4">
                <label for="startDate" class="col-sm-4 control-label"><spring:message code="workOrders.startDate"/></label>
                <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                     data-link-field="dtp_input2">
                  <input type="text" class="form-control" placeholder="请输入开始时间" path="startDate" name="startDate" value="${problem.startDate}" readonly="true" />
                  <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
              </div>

              <div class="form-group  col-lg-4">
                <label for="endDate" class="col-sm-4 control-label"><spring:message code="workOrders.endDate"/></label>
                <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2">
                  <input type="text" class="form-control" placeholder="请输入结束时间" path="endDate" name="endDate" value="${problem.endDate}" readonly="true"/>
                  <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                  <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
              </div>
            </div>

            <%--
                                <button type="button" class="btn btn-primary" for="propertyAdd" data-toggle="modal" data-target="#myModal"><spring:message code="problem.export"/></button>
            --%>
            <div class="clearfix"></div>
            <c:if test="${function.qch40010084 eq 'Y'}">
                <button type="submit" class="btn btn-primary" for="" ><spring:message code="problem.search"/></button>
            </c:if>
            <c:if test="${function.qch40010085 eq 'Y'}">
              <a href="#" onclick="isExcel()"  value="" class="btn btn-primary" style="color:#fff">导出Excel</a>
            </c:if>
          </form>
        </div>



      </div>
      <%--搜索条件结束--%>
    </div>
  </div>
  <div class="table-responsive bs-example widget-shadow">
    <table width="100%" class="tableStyle">
      <thead>
      <tr>
        <th><input name="checkall1" type="checkbox" value="0" onClick="checkAllBox(this);"/>序号</th>
        <th width="220px">问题描述</th>
        <th>位置</th>
        <th>三级分类</th>
        <th>登记时间</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${problems}" var="problem" varStatus="row">
        <tr>
          <td><input name="answer" type="checkbox" value="a"/><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>
          <td id="test">${problem.caseDesc}</td>
          <td>${problem.address}</td>
          <td>${problem.threeType}</td>
          <td><fmt:formatDate  type="date" value="${problem.createDate}" dateStyle="default" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          <td>
            <c:if test="${problem.caseState eq 'accepted'}">待接单</c:if>
            <c:if test="${problem.caseState eq 'processing'}">处理中</c:if>
            <c:if test="${problem.caseState eq 'completed'}">已完成</c:if>
            <c:if test="${problem.caseState eq 'closed'}">已废弃</c:if>
            <c:if test="${problem.caseState eq 'returnVisit'}">已评价</c:if>
          </td>
          <td>
            <c:if test="${function.qch40010086 eq 'Y'}">
                <a href="../problemGuarantee/getProblemGuaranteeManagementDetail?rectifyId=${problem.caseId}"><spring:message code="problem.detail" /></a>
            </c:if>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <%--&startDate=${problem.startDate}

    &endDate=${problem.endDate}--%>

    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }/problemGuarantee/problemGuaranteeManagement.html?pageIndex={0}&groupId=${problem.groupId}&regionId=${problem.regionId}&cityId=${problem.cityId}&projectId=${problem.projectId}&area=${problem.area}&buildingId=${problem.buildingId}&roomId=${problem.roomId}&caseState=${problem.caseState}&oneType=${problem.oneType}&twoType=${problem.twoType}&threeType=${problem.threeType}&proType=${problem.proType}&startDate=${problem.startDate}&endDate=${problem.endDate}"/>
  </div>

</div>
</div>
</div>

<!-- main content end-->
<script type="text/javascript">
    var pages = "${webPage.pageIndex}";
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
<%@ include file="../main/foolter.jsp" %>
</div>

<script>

    function isExcel(){
        var size = ${fn:length(problems)};
        if(size>0){
            var href = "../problemGuarantee/propertyRepairExportExcels?pageIndex=${webPage.pageIndex}&groupId=${problem.groupId}&regionId=${problem.regionId}&cityId=${problem.cityId}&projectId=${problem.projectId}&area=${problem.area}&buildingId=${problem.buildingId}&roomId=${problem.roomId}&caseState=${problem.caseState}&oneType=${problem.oneType}&twoType=${problem.twoType}&threeType=${problem.threeType}&proType=${problem.proType}&startDate=${problem.startDate}&endDate=${problem.endDate}";
            window.location.href = href;
        }else{
            alert("没有可以导出的数据");
        }
    }

    $("#oneType").change(function(){
        var oneType = $("#oneType").val();
        $.ajax({
            url:"../problem/getSecondTypeList",
            type:"get",
            async:"false",
            data:{ "classifyOne":oneType},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    if(data != null){
                        document.getElementById("twoType").innerHTML = "";
                        for(var prop in data) {
                            if(!isNaN(data[prop])){
                            }else{
                                if (data.hasOwnProperty(prop)) {
                                    if(prop=='0000'){
                                        option = option + "<option value='"+ prop+"'selected='selected'>" +  data[prop] + "</option>";
                                    }else{
                                        option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                                    }

                                }
                            }
                        }

                        $("#twoType").append(option);
//            document.getElementById("twoType").innerHTML = "<option value='0000'>请选择二级分类</option>";
//            for(var prop in data) {
//              if(!isNaN(data[prop])){
//              }else{
//                if (data.hasOwnProperty(prop)) {
//                  option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
//                }
//              }
//            }
//            $("#twoType").append(option);
                    }
                }
            }
        });
    });



    $("#twoType").change(function(){
        var twoType = $("#twoType").val();
        $.ajax({
            url:"../problem/getThirdTypeList",
            type:"get",
            async:"false",
            data:{ "classifyTwo":twoType},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    if(data != null){
                        document.getElementById("threeType").innerHTML = "";
                        for(var prop in data) {
                            if(!isNaN(data[prop])){
                            }else{
                                if (data.hasOwnProperty(prop)) {
                                    if(prop=='0000'){
                                        option = option + "<option value='"+ prop+"'selected='selected'>" +  data[prop] + "</option>";
                                    }else{
                                        option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                                    }
                                }
                            }
                        }
                        $("#threeType").append(option);
//            document.getElementById("threeType").innerHTML = "<option value='0000'>请选择三级分类</option>";
//            for(var prop in data) {
//              if(!isNaN(data[prop])){
//              }else{
//                if (data.hasOwnProperty(prop)) {
//                  option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
//                }
//              }
//            }
//            $("#threeType").append(option);
                    }
                }
            }
        });
    });


//    $("#projectId").change(function(){
//        var projectId = $("#projectId").val();
//        $.ajax({
//            url:"../houseroomtype/getBuildingListByProject",
//            type:"get",
//            async:"false",
//            data:{ "projectId":projectId},
//            dataType:"json",
//            error:function(){
//                alert("网络异常，可能服务器忙，请刷新重试");
//            },
//            success:function(json){
//                <!-- 获取返回代码 -->
//                var code = json.code;
//                if(code != 0){
//                    var errorMessage = json.msg;
//                    alert(errorMessage);
//                }else{
//                    <!-- 获取返回数据 -->
//                    var data = json.data;
//                    var option ="";
//                    if(data != null){
//                        document.getElementById("buildingId").innerHTML = "";
//                        for(var prop in data) {
//                            if (data.hasOwnProperty(prop)) {
//                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
//                            }
//                        }
//                        $("#buildingId").append(option);
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
                        document.getElementById("area").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#area").append(option);
                    }
                }
            }
        });
    });



    $("#area").change(function () {
        var area = $("#area").val();
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


    $("#buildingId").change(function(){
        var buildingId = $("#buildingId").val();
        $.ajax({
            url:"../houseroomtype/getUnitList",
            type:"get",
            async:"false",
            data:{ "buildingId":buildingId},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    if(data != null){
                        document.getElementById("unitId").innerHTML = "";
                        for(var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }
                        }
                        $("#unitId").append(option);
                    }
                }
            }
        });
    });

    $("#unitId").change(function () {
        var unitId = $("#unitId").val();
        $.ajax({
            url: "../houseroomtype/getFloorListByNum",
            type: "get",
            async: "false",
            data: {"unitId": unitId},
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
                        document.getElementById("floorId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#floorId").append(option);
                    }
                }
            }
        });
    });
    $("#floorId").change(function () {
        var floor = $("#floorId").val();
        $.ajax({
            url: "../houseroomtype/getRoomListByNum",
            type: "get",
            async: "false",
            data: {"floor": floor},
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
                        document.getElementById("roomId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#roomId").append(option);
                    }
                }
            }
        });
    });

</script>
</body>

</html>