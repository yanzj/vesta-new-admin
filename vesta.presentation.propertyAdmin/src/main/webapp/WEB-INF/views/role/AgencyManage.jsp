<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/6/21
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ page import="java.util.List" %>
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

  <!-- ZTree树形插件 -->
  <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
  <script src="../static/js/jquery.ztree.core-3.5.js"></script>
  <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
  <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
  <!--//Metis Menu -->
  <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
  <!--//Metis Menu -->
  <style>
    label.error {
      margin-left: 1%;
      color: red;
    }
  </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001300010000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body" style="overflow: hidden">
        <form class="form-horizontal" name="addAgencyRoot" id="addAgencyRoot" action="../agency/addRootAgency.html">
          <div class="form-group  col-lg-7">
            <label for="agencyName" class="col-sm-5 control-label">根级组织名称：</label>
            <div class="col-sm-7">
              <input type="text" class="form-control" placeholder=""  id="agencyName" name="agencyName">
            </div>
          </div>
          <button type="submit" class="btn btn-primary" for="propertySearch" >添加</button>
        </form>
        <div class="clearfix"></div>
        <table border=0 cellpadding=5 cellspacing=0 align=center>
          <tr align=center>
            <td>公司组织</td>
            <%--<form class="form-horizontal" name="outStaff" id="outStaff" action="../organize/goAddStaffToOrganize.html">--%>
            <%--<input type="hidden" id="organizeId" name="organizeId" value="${organize.organizeId}">--%>
            <%--<td>组织架构<input type="text" name="staffName" placeholder="请输入名称"><button type="submit">搜索</button>&nbsp;</td>--%>
            <%--</form>--%>
            <td>成员列表</td>
            <td>&nbsp;</td>
          </tr>
          <tr align=center>
            <td style="display: block">
              <div name="aleft" id="aleft" size="20" style="width:350px">
                <ul id="tree" class="ztree"></ul>
              </div>
            </td>
            <td>
              <select name="bright" id="bright" size="20" style="width:200px">
                <%--<c:forEach items="${agencyList}" var="items">--%>
                <%--<option value="${items.agencyId}" name="staffId" id="${items.agencyType}">${items.agencyName}</option>--%>
                <%--</c:forEach>--%>
              </select>
            </td>
            <td>&nbsp;</td>
          </tr>
          <tr><td colspan=4 align=right></td></tr>
        </table>
        <button type="button" class="btn btn-primary" onclick="aPeople()" style="float: right">添加员工</button>

        <script>
          var treeObj;
          //zTree基本设置
          var setting = {
            view: {
              showIcon:true,
              dblClickExpand: true,
              showLine: true,  //是否显示节点间的连线
              expandSpeed : 100, //设置 zTree节点展开、折叠时的动画速度或取消动画(三种默认定义："slow", "normal", "fast")或 表示动画时长的毫秒数值(如：1000)
              addHoverDom: addHoverDom,
              removeHoverDom: removeHoverDom,
            },
            edit: {
              enable: true,
              editNameSelectAll: true,
            },
            async :{
              enable:false//需要采用异步方式获取子节点数据,默认false
            },
            data: {
              simpleData: {
                enable: true,  //数据是否采用简单 Array 格式，默认false
                idKey:"id",
                pIdKey:"pId",
                rootPId:null
              }
            },
//            check: {
//              enable: true, //节点显示复选框
//              chkStyle: "checkbox"
//            },
            callback: {
              beforeDrag: beforeDrag,
              beforeEditName: beforeEditName,
              beforeRemove: beforeRemove,
              beforeRename: beforeRename,
              onRemove: onRemove,
              onRename: onRename,
              onClick: zTreeOnClick
            }
          };

          var log, className = "dark";
          function beforeDrag(treeId, treeNodes) {
            return false;
          }
          function beforeEditName(treeId, treeNode) {
            className = (className === "dark" ? "":"dark");
            showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
            var zTree = $.fn.zTree.getZTreeObj("tree");
            zTree.selectNode(treeNode);
            return confirm("确认编辑 " + treeNode.name + "？");
          }
          function beforeRemove(treeId, treeNode) {
            className = (className === "dark" ? "":"dark");
            showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
            var zTree = $.fn.zTree.getZTreeObj("tree");
            zTree.selectNode(treeNode);
            var del =  confirm("确认删除' " + treeNode.name + "' 吗?");
            if(del){
              $.ajax({
                type:"get",
                async:"false",
                dataType:"json",
                url: "/agency/delAgency",
                data: {
                  "id":treeNode.id,
                  "pId":treeNode.pId,
                  "type":treeNode.type
                },
                success: function(json){
                  <!-- 获取返回代码 -->
                  var code = json.code;
                  if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                    return false;
                  }else {
                    return true;
                  }
                }
              });
            }else{
              return false;
            }
//            return confirm("确认删除 node '" + treeNode.name + "' it?");
//            alert("删除1");
          }
          function onRemove(e, treeId, treeNode) {
            showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
          }
          function beforeRename(treeId, treeNode, newName, isCancel) {
            className = (className === "dark" ? "":"dark");
            showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
            if (newName.length == 0) {
              alert("不能为空");
              var zTree = $.fn.zTree.getZTreeObj("tree");
              setTimeout(function(){zTree.editName(treeNode)}, 10);
              return false;
            }
            $.ajax({
              type:"get",
              async:"false",
              dataType:"json",
              url: "/agency/editAgency",
              data: {
                "agencyId":treeNode.id,
                "agencyName":newName,
                "agencyType":treeNode.type
              },
              success: function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                  return false;
                  var errorMessage = json.msg;
                  alert(errorMessage);
                }else {
                  return true;
                }
              }
            });
          }
          function onRename(e, treeId, treeNode, isCancel) {
            showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
          }
          function showLog(str) {
            if (!log) log = $("#log");
            log.append("<li class='"+className+"'>"+str+"</li>");
            if(log.children("li").length > 8) {
              log.get(0).removeChild(log.children("li")[0]);
            }
          }
          function getTime() {
            var now= new Date(),
                    h=now.getHours(),
                    m=now.getMinutes(),
                    s=now.getSeconds(),
                    ms=now.getMilliseconds();
            return (h+":"+m+":"+s+ " " +ms);
          }

          var newCount = 1;
          function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                    + "' title='add node' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_"+treeNode.tId);
            if (btn) btn.bind("click", function(){
              $.ajax({
                type:"get",
                async:"false",
                dataType:"json",
                url: "/agency/addAgency",
                data: {
                  "parentId":treeNode.id,
                  "agencyType":treeNode.type,
                  "agencyName":"new node"+(newCount++),
                },
                success: function(json){
                  <!-- 获取返回代码 -->
                  var code = json.code;
                  if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                    return false;
                  }else {
                    var result = json.data;
                    var zTree = $.fn.zTree.getZTreeObj("tree");
                    zTree.addNodes(treeNode, {id:result.agencyId, pId:treeNode.id, name:result.agencyName});
                    return true;
                  }
                }
              });
            });
          };
          function removeHoverDom(treeId, treeNode) {
            $("#addBtn_"+treeNode.tId).unbind().remove();
          };

          function zTreeOnClick(event, treeId, treeNode) {
            var y=document.getElementById("bright");
            $.ajax({
              type:"get",
              async:"false",
              dataType:"json",
              url: "/agency/agencyPeople",
              data: {
                "agencyId":treeNode.id,
              },
              success: function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                  var errorMessage = json.msg;
                  alert(errorMessage);
                }else {
                  var result = json.data;
                  y.options.length=0;
                  for(var o in result){
                    y.options.add(new Option(result[o].staffName,result[o].staffId));
                  }
                }
              }
            });
          };

          $(function(){
            $.ajax({
              async :"false",
              type:"post",
              dataType:"json",
              contentType: "application/json; charset=utf-8",
              url: "/project/allAgency",//请求的action路径
              error: function () {//请求失败处理函数
                alert('请求失败');
              },
              success:function(data){ //请求成功后处理函数。
                // treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes
                treeObj = $.fn.zTree.init($("#tree"),setting, data);
              }
            });
          })

          function aPeople(){
            var treeObj1 = $.fn.zTree.getZTreeObj('tree');
            var node = treeObj1.getSelectedNodes();
            console.log(node);
            if(node.length > 0){
              var a = document.createElement('a');
              a.id = 'aa';
              a.href = '/agency/addAgencyPeople.html?agencyId='+node[0].id+'&&agencyType='+node[0].type;
              a.display = 'none';
              document.body.appendChild(a);
              document.getElementById('aa').click();
//              $.ajax({
//                async :"false",
//                type:"get",
//                dataType:"text",
//                url: "/agency/addAgencyPeople.html",//请求的action路径
//                data:{
//                  "agencyId":node[0].id,
//                  "agencyType":node[0].type
//                },
//                error: function () {//请求失败处理函数
//                  alert('请求失败');
//                },
//                success:function(data){ //请求成功后处理函数。
////                  $("html").html(data);
////                  $(document.body).html(data);
//                }
//              });
            }else{
              alert('请选择组织架构节点！');
            }
          }

          $(function() {
            $("#addAgencyRoot").validate({
              rules: {
                agencyName: {
                  required: true,
                  minlength: 1,
                  maxlength: 30
                }
              },
              messages: {
                agencyName: {
                  required: "请输入名称！",
                  minlength: "不能少于1个字符！",
                  maxlength: "请勿超过30个字！"
                }
              }
            })
          });
        </script>
      </div>
    </div>
  </div>


</div>
</div>
</div>
</div>

<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>

