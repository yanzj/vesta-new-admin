<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/11/15
  Time: 10:16
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
<%@ page import="com.maxrocky.vesta.utility.StringUtil" %>
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
      $("#003100020000").addClass("active");
      $("#003100020000").parent().parent().addClass("in");
      $("#003100020000").parent().parent().parent().parent().addClass("active");
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
  <style type="text/css">
    .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
  </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="003100020000" username="${authPropertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <div class="row">
          <div class="col-md-6">
            <label for="domain" class="col-sm-3 control-label">所属模块:</label>
            <div class="col-sm-7">
              <select name="domain" id="domain" class="form-control">
                <option value="1" <c:if test="${domain eq '1'}">selected</c:if> >日常巡检</option>
                <option value="2" <c:if test="${domain eq '2'}">selected</c:if>>检查验收</option>
                <option value="3" <c:if test="${domain eq '3'}">selected</c:if>>样板点评</option>
                <option value="6" <c:if test="${domain eq '6'}">selected</c:if>>关键工序</option>
                <option value="4" <c:if test="${domain eq '4'}">selected</c:if>>材料验收</option>
                <option value="5" <c:if test="${domain eq '5'}">selected</c:if>>旁站</option>
              </select>
            </div>
          </div>
          <div class="col-md-6">
            <c:if test="${function.esh40020056 eq 'Y'}">
               <button type="button" class="btn btn-primary" onclick="downloadModel()">下载模板</button>
            </c:if>
            <c:if test="${function.esh40020057 eq 'Y'}">
              <button onclick="test()" type="button" class="btn btn-primary">导入</button>
            </c:if>
            <c:if test="${function.esh40020058 eq 'Y'}">
                <button type="button" class="btn btn-primary" onclick="exportExcel()">导出</button>
            </c:if>
          </div>
        </div>
        <%-- 导入excel 隐藏 --%>
        <form action="../BaseData/importCategoryExcel",name="frm" id="frm" method="post" enctype="multipart/form-data">
          <input type="file" id="myfile" name="myfile" style="visibility:hidden;" onchange="importExcel()">
          <%
            HttpSession sess = request.getSession();
            String message = (String) sess.getAttribute("result");

            if (message == "ok") {
              sess.setAttribute("result", "");
          %>
          <script type="text/javascript">
            alert("导入成功");
          </script>
          <%

          }else if(!StringUtil.isEmpty(message)){
            sess.setAttribute("result", "");
          %>
          <script type="text/javascript">
            alert("<%=message %>");
            <% }%>
          </script>
          <%--<%--%>
          <%--sess.setAttribute("result", "");--%>
          <%--}--%>
          <%--%>--%>
        </form>
        <div class="row" style="height: 10px"></div>
        <form action="#" name="" style="background-color: ivory">
          <div class="row">
            <div class="col-md-6">
              <label>检查项</label>
            </div>
            <div class="col-md-6">
              <label>检查详情</label>
            </div>
          </div>
          <div class="row" style="min-height: 250px">
            <div class="col-md-6">
              <div class="zTreeDemoBackground left">
                <ul id="treeDemo" class="ztree"></ul>
              </div>
            </div>
            <div class="col-md-6">
              <label id="ta"></label>
            </div>
          </div>

          <div class="row" style="text-align: center">
            <%--<button type="button" class="btn btn-primary">返回</button>--%>
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

<script>
  function test() {
      $("#myfile").click();
  }
  var setting1 = {
    view: {
//      addHoverDom: addHoverDom,      //增加节点按钮
//      removeHoverDom: removeHoverDom,
      selectedMulti: false
    },
    edit: {
      enable: true,
      editNameSelectAll: true,
    },
    async: {
      enable: true,
      url:"/BaseData/categoryTree",
      autoParam:["id", "name=n", "level=lv"],
      otherParam:{"domain":1},
    },
    callback: {
      onClick:treeDetail,
      beforeDrag: beforeDrag,
      beforeEditName: beforeEditName,
      beforeRemove: beforeRemove,
      beforeRename: beforeRename,
      onRemove: onRemove,
      onRename: onRename
    }
  }
  var setting2 = {
    view: {
//      addHoverDom: addHoverDom,      //增加节点按钮
//      removeHoverDom: removeHoverDom,
      selectedMulti: false
    },
    edit: {
      enable: true,
      editNameSelectAll: true,
    },
    async: {
      enable: true,
      url:"/BaseData/categoryTree",
      autoParam:["id", "name=n", "level=lv"],
      otherParam:{"domain":2},
    },
    callback: {
      onClick:treeDetail,
      beforeDrag: beforeDrag,
      beforeEditName: beforeEditName,
      beforeRemove: beforeRemove,
      beforeRename: beforeRename,
      onRemove: onRemove,
      onRename: onRename
    }
  }
  var setting3 = {
    view: {
//      addHoverDom: addHoverDom,      //增加节点按钮
//      removeHoverDom: removeHoverDom,
      selectedMulti: false
    },
    edit: {
      enable: true,
      editNameSelectAll: true,
    },
    async: {
      enable: true,
      url:"/BaseData/categoryTree",
      autoParam:["id", "name=n", "level=lv"],
      otherParam:{"domain":3},
    },
    callback: {
      onClick:treeDetail
    }
  }
  var setting4 = {
    view: {
//      addHoverDom: addHoverDom,      //增加节点按钮
//      removeHoverDom: removeHoverDom,
      selectedMulti: false
    },
    edit: {
      enable: true,
      editNameSelectAll: true,
    },
    async: {
      enable: true,
      url:"/BaseData/categoryTree",
      autoParam:["id", "name=n", "level=lv"],
      otherParam:{"domain":4},
    },
    callback: {
      onClick:treeDetail,
      beforeDrag: beforeDrag,
      beforeEditName: beforeEditName,
      beforeRemove: beforeRemove,
      beforeRename: beforeRename,
      onRemove: onRemove,
      onRename: onRename
    }
  }
  var setting5 = {
    view: {
//      addHoverDom: addHoverDom,      //增加节点按钮
//      removeHoverDom: removeHoverDom,
      selectedMulti: false
    },
    edit: {
      enable: true,
      editNameSelectAll: true,
    },
    async: {
      enable: true,
      url:"/BaseData/categoryTree",
      autoParam:["id", "name=n", "level=lv"],
      otherParam:{"domain":5},
    },
    callback: {
      onClick:treeDetail,
      beforeDrag: beforeDrag,
      beforeEditName: beforeEditName,
      beforeRemove: beforeRemove,
      beforeRename: beforeRename,
      onRemove: onRemove,
      onRename: onRename
    }
  }
  function treeDetail(event, treeId, treeNode){      //ajax获取指标信息
    $.ajax({
      cache: true,
      type: "GET",
      url:"../BaseData/getTarget",
      data: {"categoryId":treeNode.id,"domain":$('#domain option:selected') .val()},
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
          if(data.length>0){
            $('#ta').html('');
            for(var o in data){
              $('#ta').append('<b>'+data[o].name+'</b><br/>'+data[o].description+'<br/>');
            }
          }else{
            $('#ta').html('');
          }
        }
      }
    });
  }
  function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
      var zTree = $.fn.zTree.getZTreeObj("treeDemo");
      zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
      return false;
    });
  };
  function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
  };
  function beforeDrag(treeId, treeNodes) {
    return false;
  }
  function beforeEditName(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.selectNode(treeNode);
    return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
  }
  function beforeRemove(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.selectNode(treeNode);
    return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
  }
  function onRemove(e, treeId, treeNode) {
    $.ajax({
      type: "GET",
      url:"../BaseData/delCategory",
      data: {"categoryId":treeNode.id},
      async: false,
      dataType: "json",
      success: function(json) {
        var code = json.code;
        if (code != 0) {
          var errorMessage = json.msg;
          alert(errorMessage);
          return false
        }else{
          var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
          treeObj.reAsyncChildNodes(treeNode, "refresh");
        }
      }
    });
  }
  function beforeRename(treeId, treeNode, newName, isCancel) {
    if (newName.length == 0) {
      alert("节点名称不能为空.");
      var zTree = $.fn.zTree.getZTreeObj("treeDemo");
      setTimeout(function(){zTree.editName(treeNode)}, 10);
      return false;
    }
    $.ajax({
      cache: true,
      type: "GET",
      url:"../BaseData/altCategory",
      data: {"categoryId":treeNode.id,"categoryName":newName},
      async: false,
      dataType: "json",
      success: function(json) {
        var code = json.code;
        if (code != 0) {
          var errorMessage = json.msg;
          alert(errorMessage);
          return false
        }
      }
    });
    return true;
  }
  function onRename(e, treeId, treeNode, isCancel) {
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    treeObj.reAsyncChildNodes(treeNode, "refresh");
  }

  $(document).ready(function(){
    $.fn.zTree.init($("#treeDemo"), setting1);
    var domain=$('#domain option:selected') .val();
    switch (domain){
      case '1': $.fn.zTree.init($("#treeDemo"), setting1);break;
      case '2': $.fn.zTree.init($("#treeDemo"), setting2);break;
      case '6': $.fn.zTree.init($("#treeDemo"), setting2);break;
      case '3': $.fn.zTree.init($("#treeDemo"), setting3);break;
      case '4': $.fn.zTree.init($("#treeDemo"), setting4);break;
      case '5': $.fn.zTree.init($("#treeDemo"), setting5);break;
    }
    $('#domain').change(function(){
      domain=$('#domain option:selected') .val();
      switch (domain){
        case '1': $.fn.zTree.init($("#treeDemo"), setting1);break;
        case '2': $.fn.zTree.init($("#treeDemo"), setting2);break;
        case '6': $.fn.zTree.init($("#treeDemo"), setting2);break;
        case '3': $.fn.zTree.init($("#treeDemo"), setting3);break;
        case '4': $.fn.zTree.init($("#treeDemo"), setting4);break;
        case '5': $.fn.zTree.init($("#treeDemo"), setting5);break;
      }
      $('#ta').html('');
    })
  })
  function refreshTree(){
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    treeObj.reAsyncChildNodes(null, "refresh");
  }

  function importExcel() {
    //检验导入的文件是否为Excel文件
    var excelPath = document.getElementById("myfile").value;
    if (excelPath == null || excelPath == '') {
      alert("请选择要上传的Excel文件");
      return;
    } else {
      var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();
      if (fileExtend == '.xls' || fileExtend == '.xlsx') {
        if (confirm("确认要导入'"+excelPath+"'？")) {
          document.getElementById("frm").action="../BaseData/importCategoryExcel?domain="+$('#domain option:selected').val();
          document.getElementById("frm").submit();
        } else {
          return;
        }
      } else {
        alert("文件格式需为'.xls'格式或者'.xlsx格式'");
        return;
      }
    }
  }
  function exportExcel() {
    var href = "../BaseData/exportCategory?domain=" +$('#domain option:selected').val();
    window.location.href = href;
  }
  function downloadModel() {
    if (confirm("是否下载当前模块模板？")) {
      var href = "../BaseData/exportModel?domain="+$('#domain option:selected').val();
      window.location.href = href;
    } else {
      return;
    }
  }
</script>
</body>
</html>

