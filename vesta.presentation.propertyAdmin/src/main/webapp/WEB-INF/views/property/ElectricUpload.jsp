<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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

  <script>
    //-----------------↓楼栋下拉框↓-----------------
    function turnElecBuild(){
      $("#building").empty();//清空

      var formatId = document.getElementById("formatId").value;
      $.ajax({
        url: "../property/showBuilding",
        type: "post",
        async: "false",
        dataType: "json",
        data: {
          "formatSelId": formatId,
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
            for(var n=0;n<data.length;n++){
              var ids=data[n].buildingId;
              var names=data[n].buildingName;
              $("#building").append("<option id='"+ids+"' value='"+ids+"'>"+names+"</option>");
            }
          }
        },
      });
//                    alert("2"+document.getElementById("buildingId").value);
//                    turnUnit()
    }
    function js_uploadMessage(){
      if("0" == $("#building").val()){
        alert("请选择楼号！");
        return false;
      }
      if("0" == $("#formatId").val()){
        alert("请选择业态！");
        return false;
      }
    }

  </script>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020006" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--服务信息添加开始--%>
      <div class="form-body">
        <h3 class="text-center">电量模板下载 </h3>
        <div class="form-body">
          <form class="form-horizontal" action="../property/exportHouseExcel">

            <div class="form-group">
              <label class="col-sm-5 control-label" for="building"><spring:message code="ownerManage.SelFormat" /></label>
              <div class="col-sm-4">
                <select id="formatId" name="formatId"  class="form-control" onchange="turnElecBuild()">
                  <c:forEach items="${formatSelDTOs}" var="item">
                    <option value="${item.formatId }" >${item.formatName }</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-5 control-label" for="building"><spring:message code="ownerManage.SelBuilding" /></label>
              <div class="col-sm-4">
                <select id="building" name="building"  class="form-control">
                  <c:forEach items="${buildingSel}" var="item">
                    <option value="${item.buildingId }" >${item.buildingName }</option>
                  </c:forEach>
                </select>
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-5 control-label" >&nbsp;</label>
              <div class="col-sm-3">
                <label id="errorMsg" style="color: red;"></label>
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-6">
                <input type="submit" class="btn btn-primary" onclick="return js_uploadMessage();" value="<spring:message code="EleManage_Download"/>"/>
                <a  href="../property/electricManage.html" class="btn btn-primary" for="" ><spring:message code="common_back"/></a>
              </div>
            </div>
          </form>
        </div>
      </div>
      <%--服务信息添加结束--%>
    </div>
  </div>
</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>