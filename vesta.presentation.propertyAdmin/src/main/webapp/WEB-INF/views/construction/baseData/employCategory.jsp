<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/11/10
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  <style>
    label.error {
      margin-left: 1%;
      color: red;
    }
  </style>

  <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
  <!-- font CSS -->
  <!-- font-awesome icons -->
  <link href="../static/css/font-awesome.css" rel="stylesheet">
  <!-- //font-awesome icons -->
  <%--<link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">--%>
  <!-- js-->
  <script src="../static/js/jquery-1.11.1.min.js"></script>
  <script src="../static/js/modernizr.custom.js"></script>
  <!--animate-->
  <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
  <script src="../static/js/wow.min.js"></script>
  <script>
    $(function(){
      console.log("sqq")
      $("#003100010000").addClass("active");
      $("#003100010000").parent().parent().addClass("in");
      $("#003100010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
  <script src="../static/js/jquery.ztree.core-3.5.js"></script>
  <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
  <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
  <script src="../static/js/jquery.ztree.exhide-3.5.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="003100010000" username="${authPropertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <form action="#" name="updateDuty">
          <input type="hidden" name="employId" id = "employId"  value="${employId}">
          <input type="hidden" name="projectId" id="projectId" value="${projectId}">
          <input type="hidden" id="domain" value="${domain}">
          <div class="row" style="height:500px; overflow:auto">
            <div class="col-md-2"><h4><b>${titleName}:</b></h4></div>
            <div class="col-md-9">
              <!-- Tab panes -->
              <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="one">
                  <input type="text" id="key" value="" class="empty" placeholder="请输入关键字" /><br/>
                  <div class="zTreeDemoBackground left">
                    <ul id="treeDemo" class="ztree" style="height: 400px"></ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <input type="hidden" name="categoryId" id="categoryId">
          <div class="row" style="text-align: center">
            <button type="button" class="btn btn-primary" onclick="addEmployCategory()">确定</button>
            <a href="../BaseData/categorySetting.html?projectId=${projectId}&employId=${employId}" type="button" class="btn btn-primary">取消</a>
          </div>
        </form>
      </div>
    </div>
  </div>

</div>
</div>
</div>
</div>
<!-- main content end-->



<%@ include file="../../main/foolter.jsp" %>
</div>

<script type="text/javascript">
  var setting = {
    async: {
      enable: false,
      url:"/BaseData/initCategory",
      autoParam:["id", "name=n", "level=lv"],
      otherParam:{"otherParam":"zTreeAsyncTest"},
    },
    data:{
      simpleData: {
        enable: true,
        idKey: "id",
        pIdKey: "pId",
        rootPId: null
      },
      key: {
        title:""
      }
    },
    check: {
      enable: true,
      chkStyle: "checkbox",         //添加checkbox多选功能 
      chkboxType: { "Y": "ps", "N": "ps" },
      nocheckInherit: true
    },
    callback: {
//      onClick: onClick,
//      beforeAsync: beforeAsync,
      onAsyncSuccess: onAsyncSuccess,
//      onAsyncError: onAsyncError
    },
  };

  function onAsyncSuccess(event, treeId, treeNode, msg){
    console.log(treeNode)
//    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    var check = treeNode.getCheckStatus();
  }
  //ztre模糊搜索节点方法
  function focusKey(e) {
    if (key.hasClass("empty")) {
      key.removeClass("empty");
    }
  }
  function blurKey(e) {
    if (key.get(0).value === "") {
      key.addClass("empty");
    }
  }
  var lastValue = "", nodeList = [], fontCss = {};
  function searchNode(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    var value = $.trim(key.get(0).value);
    var keyType = "name";
    if (key.hasClass("empty")) {
      value = "";
    }
    if (lastValue === value) return;
    lastValue = value;
    if (value === "") {
      zTree.showNodes(zTree.transformToArray(zTree.getNodes())) ;
      return;
    }
    nodeList = zTree.getNodesByParamFuzzy(keyType, value);
    /**不查询父级
           for(var x = 0 ; x<nodeList.length ; x++){
        if(nodeList[x].isParent){
          nodeList.splice(x--,1);
        }
      }  */
    nodeList = zTree.transformToArray(nodeList);
    updateNodes(true);
  }

  function updateNodes(highlight) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    var allNode = zTree.transformToArray(zTree.getNodes());
    zTree.hideNodes(allNode);
    for(var n in nodeList){
      findParent(zTree,nodeList[n]);
    }

    zTree.showNodes(nodeList);
  }

  function findParent(zTree,node){
    zTree.expandNode(node,true,false,false);
    var pNode = node.getParentNode();
    if(pNode != null){
      nodeList.push(pNode);
      findParent(zTree,pNode);
    }

  }

  function getFontCss(treeId, treeNode) {
    return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
  }
  function filter(node) {
    return !node.isParent && node.isFirstNode;
  }

  var key;
  $(document).ready(function(){
    var domain=$('#domain').val();
    var employId=$('#employId').val();
    $.ajax({url:"../BaseData/allCategory?domain="+domain+"&dutyId="+employId,dataType:"json",success:function(result){
     result;
      $.fn.zTree.init($("#treeDemo"), setting,result);
      var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
      treeObj.expandAll(true);
    }});

    key = $("#key");
    key.bind("focus", focusKey)
            .bind("blur", blurKey)
//      .bind("propertychange", searchNode)
            .bind("input", searchNode);
  });
  //方法结束

  function addEmployCategory(){
    var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
    var nodes = treeObj.getCheckedNodes(true);
    var ids='';
    var ckState='';
    for(var i=0;i<nodes.length;i++){
      if (!nodes[i].getCheckStatus().half) {
        ids += nodes[i].id + ',';
      }else{
        ckState += nodes[i].id+',';
      }
    }
    $.ajax({
      cache: true,
      type: "POST",
      url:"../BaseData/addEmployCategory",
      data: {"domain":$('#domain').val(),"employId":$('#employId').val(),"projectId":$('#projectId').val(),
        "categoryId":ids,"ckState":ckState
      },
      async: false,
      dataType: "json",
      success: function(json) {
        var code = json.code;
        if (code != 0) {
          var errorMessage = json.msg;
          alert(errorMessage);
        } else {
          <!-- 获取返回数据 -->
          var data = json.data;
          if (data == 'ok') {
            window.location.href="../BaseData/categorySetting.html?projectId=" +$('#projectId').val()+"&employId="+$('#employId').val();
            alert("保存成功");
          } else {
            alert(data);
          }
        }
      }
    });
  }
</script>
</body>
</html>
