<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/7/7
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
  <script src="../static/js/jquery.ztree.core-3.5.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001300050000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body" style="overflow: hidden;">
        <p>${roleSet.roledesc}</p>
        <table border=0 cellpadding=5 cellspacing=0 align=center>
          <tr align=center>
            <form class="form-horizontal" name="outStaff" id="outStaff" action="../role/goToAdminRole.html">
              <input type="hidden" id="setId" name="setId" value="${roleSet.setId}">
              <td>
                <div id="stafs">员工列表<input type="text" name="staffName" placeholder="请输入名称"><button type="submit">搜索</button></div></td>
              </td>
            </form>
            <form name="inStaff" id="inStaff" action="">
              <td>&nbsp;角色内成员列表：</td>
            </form>
          </tr>
          <tr align=center>
            <td style="display:block;">
              <%--<div name="ageleft" id="ageleft" size="20" style="width:200px">--%>
                <%--<ul id="tree" class="ztree"></ul>--%>
              <%--</div>--%>

              <select name="aleft" id="aleft" size="20" style="width:200px" ondblclick="Add('${roleSet.setId}')">
                <c:forEach items="${staffs}" var="item">
                  <option value="${item.staffId}">${item.staffName}</option>
                </c:forEach>
              </select>
            </td>
            <td>
              <input type=button name="b1" value="添加" onclick="Add('${roleSet.setId}')">
              <br><br>
              <input type=button name="b1" value="撤销" onclick="Del('${roleSet.setId}')">
            </td>
            <td>
              <select name="aright" id="aright" size="20" style="width:200px" ondblclick="Del('${roleSet.setId}')">
                <c:forEach items="${staffList}" var="items">
                  <option value="${items.staffId}" name="staffId">${items.staffName}</option>
                </c:forEach>
              </select>
            </td>
        </table>
        <a type="button" class="btn btn-primary" href="../role/AuthorityManage.html" style="margin-top:20px">确定</a>

        <script>
          var treeObj;
          //zTree基本设置
          var setting = {
            view: {
              showIcon:true,
              dblClickExpand: false,
              showLine: true,  //是否显示节点间的连线
              expandSpeed : 100, //设置 zTree节点展开、折叠时的动画速度或取消动画(三种默认定义："slow", "normal", "fast")或 表示动画时长的毫秒数值(如：1000)
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
            check: {
              enable: true, //节点显示复选框
              chkStyle: "checkbox"
            },
            callback: {
              onDblClick: zTreeOnDblClick
            }
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
          });

          var x=document.getElementById("aleft")
          var y=document.getElementById("aright")
          function zTreeOnDblClick(event, treeId,treeNode){
            var roleSetId = document.getElementById("appRoleSetId").value;
            if(treeNode!=null){
              $.ajax({
                type:"get",
                async:"false",
                dataType:"json",
                url: "/user/saveRoleAgency",
                data: {
                  "dataId":roleSetId,
                  "authorityId":treeNode.id,
                  "authorityType":treeNode.type
                },
                success: function(json){
                  <!-- 获取返回代码 -->
                  var code = json.code;
                  if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                  }else {
                    var opt = document.createElement ("option");
                    y1.appendChild(opt)
                    opt.text=treeNode.name;
                    opt.value= treeNode.id;
                    opt.id=treeNode.type;
//                    y.options.add(new Option(treeNode.name,treeNode.id));
                  }
                }
              });
            }
          }

          function Add(setId){
            var index=x.selectedIndex
            var staffId= x.options[index].value;
            if(staffId!=null){
              $.ajax({
                type:"get",
                async:"false",
                dataType:"json",
                url: "/role/saveAdminRoleSet",
                data: {
                  "roleSetId":setId,
                  "staffId":staffId
                },
                success: function(json){
                  <!-- 获取返回代码 -->
                  var code = json.code;
                  if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                  }else {
                    <!-- 获取返回数据 -->
//                                var data = json.data;
                    for(var i=0;x[i];i++){
                      x[i].selected==true&&y.appendChild(x[i])
                    }
                  }
                }
              });
            }
          }
          function Del(setId){
            var index=y.selectedIndex
            var staffId= y.options[index].value;
            if(staffId!=null){
              $.ajax({
                type:"get",
                async:"false",
                dataType:"json",
                url: "/role/delAdminRoleSet",
                data: {
                  "roleSetId":setId,
                  "staffId":staffId
                },
                success: function(json){
                  <!-- 获取返回代码 -->
                  var code = json.code;
                  if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                  }else {
                    <!-- 获取返回数据 -->
//                                var data = json.data;
                    for(var i=0;y[i];i++){
                      y[i].selected==true&&x.appendChild(y[i])
                    }
                  }
                }
              });
            }
          }
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
