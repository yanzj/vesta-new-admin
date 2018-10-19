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
  <script src="../static/js/jquery-1.11.1.min.js"></script>
  <script src="../static/advert/js/addAdvert.js"></script>
  <script type="text/javascript">

    addEventListener("load", function () {
      setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
      window.scrollTo(0, 1);
    }


  </script>
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
  <script src="../static/js/modernizr.custom.js"></script>
  <!--animate-->
  <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
  <script src="../static/js/wow.min.js"></script>
  <script>
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/property/js/checkNullAllJsp.js"></script>
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020011" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <%--服务信息添加开始--%>
      <div class="form-body">
        <h3 class="text-center">新增消息 </h3>
        <div class="form-body">
          <form class="form-horizontal" id="frm" action="../message/addMessage.html" method="post" enctype="MULTIPART/FORM-DATA">
            <div class="form-group col-lg-6">
              <label  class="col-sm-3 control-label" for="typeId"><spring:message code="messageManage.type" /></label>
              <div class="col-sm-9">
                <select class="form-control" placeholder="" id="typeId" name="typeId">
                  <c:forEach items="${MessageTypeDTOList}" var="messageType">
                  <option value="${messageType.typeId}">${messageType.name}</option>
                  </c:forEach>
                  </select>
              </div>
            </div>
            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="title"><spring:message code="messageManage.title" /></label>
              <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="" id="title" name="title" value="${messageManageDTO.title}">
              </div>
            </div>

            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="department"><spring:message code="messageManage.department" /></label>
              <div class="col-sm-9">
                <c:forEach items="${projects.houseSectionMap}" var="houseSectionMap" varStatus="row">
                  <c:set var="value" value="${houseSectionMap.key}"></c:set>
                  <c:if test="${houseSectionMap.key!='0'}">
                   <td style="width: 80px;height:80px"><input id="department"  type="checkbox" name="department" value="${houseSectionMap.key}"<c:if test="${checkedMap[value] != null}">checked="checked" </c:if> value="true">${houseSectionMap.value}</td>
                    <c:if test="${row.index==5}">
                      <br/>
                  </c:if>
                  </c:if>
                </c:forEach>
              </div>
            </div>
            <div class="form-group col-lg-6">
              <label class="col-sm-3 control-label" for="content"><spring:message code="messageManage.content" /></label>
              <div class="col-sm-9">
                <textarea class="form-control" id="content" placeholder="" style="height:80px" name="content" >${messageManageDTO.content}</textarea>
              </div>
            </div>
              <div class="clearfix"></div>
              <div class="text-center">
            <button type="button" id="abc" class="btn btn-primary" onclick="validate()" ><spring:message code="propertyServices.servicePreservation"/></button>
                  </div>
            <input type="hidden" class="form-control" id="messageManageId" name="messageManageId" value="${messageManageDTO.messageManageId}">
            <input type="hidden" class="form-control" id="type" name="messageId" value="${messageManageDTO.typeId}">
          </form>
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
  var type=$('#type').val();
  $(document).ready(function(){
    if(''!=type){
      $('#typeId').val(type);
    }
  });

  function validate() {

    var typeId=$("#typeId").val();
    if(typeId=='0'){
      alert("类型不能为空");
      return;
    }
    if(!CheckNull($("#title").val(),"标题不能为空！")){
      return;
    }
    var title=$('#title').val();
    if(title==""){
      alert("标题不能为空");
      return;
    }

    var len=$("input[name='department']:checked").length;

    if(len==0){
      alert("请至少选择一个部门！");
      return;
    }
    if(!CheckNull($("#content").val(),"内容不能为空！")){
      return;
    }
    var content=$('#content').val();
    if(content==""){
      alert("内容不能为空");
      return;
    }
    if(content.length>200){
      alert("活动内容不能大于二百个字");
      return;
    }
        document.getElementById("frm").submit();

  }
</script>
</body>
</html>