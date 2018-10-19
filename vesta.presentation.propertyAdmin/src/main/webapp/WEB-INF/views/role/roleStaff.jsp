<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/6/2
  Time: 20:40
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

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001300040000" username="${propertystaff.staffName}" />


  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body" style="overflow: hidden;">
        <p>${organize.organizeName}</p>
        <table border=0 cellpadding=5 cellspacing=0 align=center>
          <tr align=center>
            <form class="form-horizontal" name="outStaff" id="outStaff" action="../user/rolePeople.html">
              <input type="hidden" id="appRoleSetId" name="appRoleSetId" value="${roleSet.appRoleSetId}">
              <td>
                <div id="stafs" style="display: none">员工列表<input type="text" name="staffName" placeholder="请输入名称"><button type="submit">搜索</button></div>
                &nbsp;</td>
              <td><label><input type="radio" name="ckFlag" value="1" checked="checked" onclick="changeView(2)">部门</label>
                <label><input type="radio" name="ckFlag" value="2" onclick="changeView(1)" <c:if test="${ckFlag=='2'}">checked="checked"</c:if>>员工</label>
              </td>
            </form>
            <form name="inStaff" id="inStaff" action="">
              <td>&nbsp;角色内成员列表：</td>
            </form>
            <td>&nbsp;</td>
            <td>常用组列表</td>
            <td></td>
            <td>角色内组列表</td>
          </tr>
          <tr align=center>
            <td style="display:block;">
              <div name="ageleft" id="ageleft" size="20" style="width:200px">
                <ul id="tree" class="ztree"></ul>
              </div>

              <select name="aleft" id="aleft" size="20" style="width:200px;display: none" ondblclick="pAdd('${roleSet.appRoleSetId}')">
                <c:forEach items="${staffOutList}" var="item">
                  <option value="${item.staffId}" id="0">${item.staffName} </option>
                </c:forEach>
              </select>
            </td>
            <td>
              <input type=button name="b1" value="添加" onclick="pAdd('${roleSet.appRoleSetId}')">
              <br><br>
              <input type=button name="b1" value="撤销" onclick="pDel('${roleSet.appRoleSetId}')">
            </td>
            <td>
              <select name="aright" id="aright" size="20" style="width:200px" ondblclick="pDel('${roleSet.appRoleSetId}')">
                <c:forEach items="${staffList}" var="items">
                  <option value="${items.agencyId}" name="staffId" id="${items.agencyType}">${items.agencyName}</option>
                </c:forEach>
              </select>
            </td>
            <td>&nbsp;</td>
            <td>
              <select name="bleft" id="bleft" size="20" style="width:200px" ondblclick="Add('${roleSet.appRoleSetId}')">
                <c:forEach items="${organizeOutList}" var="item">
                <option value="${item.organizeId}">${item.organizeName}</option>
                </c:forEach>
            </td>
            <td>
              <input type=button name="b2" value="添加" onclick="Add('${roleSet.appRoleSetId}')">
              <br><br>
              <input type=button name="b2" value="撤销" onclick="Del('${roleSet.appRoleSetId}')">
            </td>
            <td>
              <select name="bright" id="bright" size="20" style="width:200px" ondblclick="Del('${roleSet.appRoleSetId}')">
                <c:forEach items="${organizeInList}" var="items">
                  <option value="${items.organizeId}" name="staffId">${items.organizeName}</option>
                </c:forEach>
              </select>
            </td>
        </table>
        <a type="button" class="btn btn-primary domCenter" href="../user/roleManage.html" >确定</a>
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
          $(document).ready(function(){
            var a = $("input[type=radio]:checked").val();
            if(a=='2'){
              changeView();
            }
          })

          var x1=document.getElementById("aleft")
          var y1=document.getElementById("aright")
          var x=document.getElementById("bleft")
          var y=document.getElementById("bright")
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

          function pAdd(appRoleSetId){
            if($("#ageleft").is(":visible")){
              var treeObj1 = $.fn.zTree.getZTreeObj('tree');
              var node = treeObj1.getSelectedNodes();
              console.log(node);
              if(node.length > 0){
                zTreeOnDblClick(event,node.tId,node[0]);
              }else{
                alert('请选择！');
              }
            }else{
              var index=x1.selectedIndex
              var staffId= x1.options[index].value;
//              var authorityType = $(y1.options[index]).attr("id");
              if(staffId!=null){
                $.ajax({
                  type:"get",
                  async:"false",
                  dataType:"json",
                  url: "/user/saveRolePeople",
                  data: {
                    "roleSetId":appRoleSetId,
                    "staffId":staffId,
//                    "authorityType": authorityType
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
                      for(var i=0;x1[i];i++){
                        x1[i].selected==true&&y1.appendChild(x1[i])
                      }
                    }
                  }
                });
              }
            }
          }

          function pDel(appRoleSetId){
            var index=y1.selectedIndex
            var authorityId= y1.options[index].value;
            var authorityType = $(y1.options[index]).attr("id");
            if(authorityId!=null){
              $.ajax({
                type: "get",
                async: "false",
                dataType: "json",
                url: "/user/delRoleAgency",
                data: {
                  "dataId": appRoleSetId,
                  "authorityId": authorityId,
                  "authorityType": authorityType
                },
                success: function (json) {
                  <!-- 获取返回代码 -->
                  var code = json.code;
                  if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                  } else {
                    var data = json.data;
                    if($("#ageleft").is(":visible")){
                      y1.options.remove(index);
                    }else{
                      y1.options.remove(index);
//                      for(var i=0;y1[i];i++){
//                        y1[i].selected==true&&x1.appendChild(y1[i]);
//                      }
                      if(data != null && data.length > 0){
                        document.getElementById("aleft").options.length = 0;
                        for(var i = 0; i< data.length; i++){
                          $("<option value='"+data[i].staffId+"' id='0'>"+data[i].staffName+"</option>").appendTo("#aleft");
                        }
                      }

                    }
                  }
                }
              })
            }
          }

          function Add(appRoleSetId){
            var index=x.selectedIndex
            var organizeId= x.options[index].value;
            if(organizeId!=null){
              $.ajax({
                type:"get",
                async:"false",
                dataType:"json",
                url: "/user/addRoleOrganize",
                data: {
                  "dataId":appRoleSetId,
                  "authorityId":organizeId
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
          function Del(appRoleSetId){
            var index=y.selectedIndex
            var organizeId= y.options[index].value;
            if(organizeId!=null){
              $.ajax({
                type:"get",
                async:"false",
                dataType:"json",
                url: "/user/delRoleOrganize",
                data: {
                  "dataId":appRoleSetId,
                  "authorityId":organizeId
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

          function changeView(flag){
            if(flag==1){
              $("#ageleft").hide();
              $("#aleft").show();
              $("#stafs").show();
            }else{
              $("#ageleft").show();
              $("#aleft").hide();
              $("#stafs").hide();
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
