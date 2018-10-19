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
  <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"> </script>
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

</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020003" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms" style="overflow: hidden;">
      <div class="form-body">
        <h3 class="text-center">新增价格 </h3>
        <div class="form-body">

          <div class="form-group col-lg-6">
            <label class="col-sm-3 control-label" style="font-size: 16px;" for="projectId"><spring:message code="propertyReport.projectId" /></label>
            <div class="col-sm-9">
              <input type="hidden" id="priceSettingId" name="priceSettingId" value="${priceSetting.priceSettingId}"/>
              <input type="hidden" id="projectId" name="projectId" value="${priceSetting.projectId}"/>
              <input type="text" class="form-control" placeholder="" id="projectName" name="projectName" value="${priceSetting.projectName}" readonly>
            </div>
          </div>

          <div class="form-group col-lg-12" style="z-index:0">
            <script  id="editor" type="text/plain" style="width:100%;height:400px;overflow-y:auto;" >${priceSetting.priceSettingCount}</script>
          </div>
        </div>

          <div class="clearfix"></div>
          <div class="text-center">
        <input type="hidden" class="form-control" id="servicesId" name="servicesId">
        <button type="button" onclick="getContent();" class="btn btn-primary"><spring:message code="propertyServices.servicePreservation" /></button>
         </div>
      </div>
    </div>
  </div>
</div>
</div>
</div>
</div>
</div>
<script type="text/javascript">
  //实例化编辑器 ------
  //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
  var ue = UE.getEditor('editor');


  function isFocus(e){
    alert(UE.getEditor('editor').isFocus());
    UE.dom.domUtils.preventDefault(e)
  }
  function setblur(e){
    UE.getEditor('editor').blur();
    UE.dom.domUtils.preventDefault(e)
  }
  function insertHtml() {
    var value = prompt('插入html代码', '');
    UE.getEditor('editor').execCommand('insertHtml', value)
  }
  function createEditor() {
    enableBtn();
    UE.getEditor('editor');
  }
  function getAllHtml() {
    alert(UE.getEditor('editor').getAllHtml())
  }
  function getContent() {

    var NowNum = document.getElementById("projectName").value.length;
    if(NowNum > 30){
      alert("项目最多字符不超过20！");
      return;
    }

    if(UE.getEditor('editor').getContent().length == 0){
      alert("请填写内容");
      return;
    }
    myvalue = confirm("确定要修改信息吗?");
    if (myvalue == true) {
      $.ajax({
        url: "/property/saveOrUpdatePriceSetting",
        type: "POST",
        async: "false",
        dataType: "json",
        data: {
          "priceSettingId": $("#priceSettingId").val(),
          "projectId": $("#projectId").val(),
          "projectName": $("#projectName").val(),
          "PriceSettingCount": UE.getEditor('editor').getContent(),
        },
        success: function (json) {
          <!-- 获取返回代码 -->
          var code = json.code;
          if (code == 0) {
            window.parent.location.href = "/property/PropertyPriceSetting.html";
          }
        }
      });
    }
  }

  function getPlainTxt() {
    var arr = [];
    arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
    arr.push("内容为：");
    arr.push(UE.getEditor('editor').getPlainTxt());
    alert(arr.join('\n'))
  }
  function setContent(isAppendTo) {
    var arr = [];
    <%--arr.push("${merDesc}123");--%>
    UE.getEditor('editor').setContent('${merDesc}', isAppendTo);
    alert(arr.join("\n"));
  }
  function setDisabled() {
    UE.getEditor('editor').setDisabled('fullscreen');
    disableBtn("enable");
  }

  function setEnabled() {
    UE.getEditor('editor').setEnabled();
    enableBtn();
  }

  function getText() {
    //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
    var range = UE.getEditor('editor').selection.getRange();
    range.select();
    var txt = UE.getEditor('editor').selection.getText();
    alert(txt)
  }

  function getContentTxt() {
    var arr = [];
    arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
    arr.push("编辑器的纯文本内容为：");
    arr.push(UE.getEditor('editor').getContentTxt());
    alert(arr.join("\n"));
  }
  function hasContent() {
    var arr = [];
    arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
    arr.push("判断结果为：");
    arr.push(UE.getEditor('editor').hasContents());
    alert(arr.join("\n"));
  }
  function setFocus() {
    UE.getEditor('editor').focus();
  }
  function deleteEditor() {
    disableBtn();
    UE.getEditor('editor').destroy();
  }
  function disableBtn(str) {
    var div = document.getElementById('btns');
    var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
    for (var i = 0, btn; btn = btns[i++];) {
      if (btn.id == str) {
        UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
      } else {
        btn.setAttribute("disabled", "true");
      }
    }
  }
  function enableBtn() {
    var div = document.getElementById('btns');
    var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
    for (var i = 0, btn; btn = btns[i++];) {
      UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
    }
  }

  function getLocalData () {
    alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
  }

  function clearLocalData () {
    UE.getEditor('editor').execCommand( "clearlocaldata" );
    alert("已清空草稿箱")
  }

  UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
  UE.Editor.prototype.getActionUrl = function(action) {
    if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
      return '${baseUrl}../property/uploadImage.html?action=uploadimage';
      // return '${baseUrl}../ueditor/fileupload/upload.html';
    } else if (action == 'uploadvideo') {
      return '${baseUrl}../property/uploadImage.html?action=uploadimage';
      // return '${baseUrl}../ueditor/fileupload/upload.html';
    } else {
      return this._bkGetActionUrl.call(this, action);
    }
  }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>