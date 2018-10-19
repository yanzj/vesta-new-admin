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
  <script type="text/javascript">

  </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001700010000" username="${authPropertystaff.staffName}" />
  <div class="forms">
    <div class="widget-shadow " data-example-id="basic-forms">
      <div class="form-body">
        <p>${organize.organizeName}</p>
        <table border=0 cellpadding=5 cellspacing=0 align=center>
          <tr align=center>
            <form class="form-horizontal" name="outStaff" id="outStaff" action="../user/rolePeople.html">
              <input type="hidden" id="appRoleSetId" name="appRoleSetId" value="${roleSet.appRoleSetId}">
              <td>选择级别分类
                <%--<input type="text" name="staffName" placeholder="请输入名称"><button type="submit">搜索</button>&nbsp;--%>
              </td>
            </form>
            <td></td>
            <td></td>
            <form name="inStaff" id="inStaff" action="">
              <td>&nbsp;常用检查项：</td>
            </form>
            <td>&nbsp;</td>
          </tr>
          <tr align=center>
            <%--一级分类--%>
            <td>
              <select id="oneType" name="oneType" size="20" style="width:200px">
                <c:forEach items="${typeMaps.firstLevels}" var="item">
                  <c:if test="${item.key=='0000'}">

                  </c:if>
                  <c:if test="${item.key!='0000'}">
                    <option value="${item.key }" <c:if test="${item.key eq problem.oneType}">selected</c:if>>${item.value }</option>
                  </c:if>
                </c:forEach>
              </select>
            </td>
            <%--二级分类--%>
            <td>
              <select id="twoType" name="twoType" size="20" style="width:200px">
                <c:forEach items="${typeMaps.secondLevels}" var="item">
                  <option value="${item.key }" <c:if test="${item.key eq problem.twoType}">selected</c:if>>${item.value }</option>
                </c:forEach>>
              </select>
            </td>
            <%--三级分类--%>
            <td>
              <%--id="threeType" name="threeType"--%>
              <select name="aleft" id="aleft" size="20" style="width:200px" ondblclick="Add('${roleSet.appRoleSetId}')">
                <c:forEach items="${typeMaps.thirdLevels}" var="item">
                  <option value="${item.key }" <c:if test="${item.key eq problem.threeType}">selected</c:if>>${item.value }</option>
                </c:forEach>
                <%--<c:forEach items="${staffOutList}" var="item">
                  <option value="${item.staffId}">${item.staffName}</option>
                </c:forEach>--%>
              </select>
            </td>

            <td>
              <input type=button name=b1 value="添加" onclick="Add('${roleSet.appRoleSetId}')">
              <br><br>
              <input type=button name=b1 value="撤销" onclick="Del('${roleSet.appRoleSetId}')">
              <br><br>
              <input type=button name=b1 value="上移" onclick="UpItem('${roleSet.appRoleSetId}')">
              <br><br>
              <input type=button name=b1 value="下移" onclick="DownItem('${roleSet.appRoleSetId}')">
            </td>
            <td>
              <select name="bright" id="bright" size="20" style="width:200px" ondblclick="Del('${roleSet.appRoleSetId}')">
                <c:forEach items="${cUsedManagements}" var="items">
                  <option value="${items.key}" name="staffId">${items.value}</option>
                </c:forEach>
              </select>
            </td>
            <td>&nbsp;</td>
          </tr>
        </table>
        <%--<a type="button"  href="../user/roleManage.html">确定</a>--%>
        <c:if test="${function.qch40010065 eq 'Y'}">
          <input type=button value="确定" onclick="determine()">
        </c:if>
        <script>

          var x=document.getElementById("aleft");
          var y=document.getElementById("bright");
          function Add(appRoleSetId) {
            var index = x.selectedIndex;
            var staffId = x.options[index].value;
            if (staffId != null) {
              for (var i = 0; x[i]; i++) {
                x[i].selected == true && y.appendChild(x[i])
              }
            }
          }

          function Del(appRoleSetId){

            var index=y.selectedIndex
            var staffId= y.options[index].value;
            if(staffId!=null){
              $.ajax({
                type:"get",
                async:"false",
                dataType:"json",
                url: "../problem/delRolePeople",
                data: {
                  "roleSetId":appRoleSetId,
                  "valueId":staffId
                },
                success: function(json){
                  for(var i=0;y[i];i++){
                    y[i].selected==true&&x.appendChild(y[i])
                  }
                }
              });
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
                   // document.getElementById("twoType").innerHTML = "<option value='0000'>请选择二级分类</option>";
                    document.getElementById("twoType").options.length = 0;
                    $("#twoType").val("");

                    for(var prop in data) {
                      if (data.hasOwnProperty(prop)) {
                        if(prop!="0000"){
                          option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                        }
                      }
                    }
                    $("#twoType").append(option);
                  }
                }
              }
            });
          });

          $("#twoType").change(function(){
            var twoType = $("#twoType").val();
            $.ajax({
              url:"../problem/getThirdTypeListNew",
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
                   // document.getElementById("aleft").innerHTML = "<option value='0000'>请选择三级分类</option>";
                    document.getElementById("aleft").options.length = 0;
                    var secondId=data.secondId;
                    //alert(secondId);
                    for(var prop in data) {
                      if (data.hasOwnProperty(prop)) {
                        if(!isNaN(data[prop])){
                        }else{
                          if(prop!="0000") {
                            option = option + "<option secondId='" + data.secondId + "'  value='" + prop + "'>" + data[prop] + "</option>";
                          }
                        }
                      }
                    }
                    $("#aleft").append(option);
                  }
                }
              }
            });
          });
/*确定常用检查项*/
          function determine(){
            var optionNum=$("#bright option").length;
            if(optionNum==0){
            }else{
              var jsonStr="[";
              $("#bright option").each(function(){
                var txt = $(this).text();
                var val= $(this).val();
                var second_id=$(this).attr("secondid");
                if(typeof(second_id)=="undefined"){
                  second_id="undefined";
                }
                //jsonStr+='{"'+val+'"'+':'+'"'+txt+'"'+'},';
                jsonStr+='{"value":'+val+','+'"label":'+txt+','+'"second_id":'+second_id.toString()+'},';
              });
              jsonStr=jsonStr.substring(0, jsonStr.lastIndexOf(','))+']';
              //alert(jsonStr);
              $.ajax({
                type:"post",
                async:"false",
                dataType:"json",
                url: "../problem/determine",
                data: {
                  "determine":jsonStr.toString()
                },
                success:function(json){
                  alert("确认成功");
                  window.location.reload();
                }
              });
            }
          }


          function UpItem(appRoleSetId){
            if(null == $('#bright').val()){
              alert('请选择一项');
              return false;
            }
            //选中的索引,从0开始
            var optionIndex = $('#bright').get(0).selectedIndex;
            //如果选中的不在最上面,表示可以移动
            if(optionIndex > 0){
              $('#bright option:selected').insertBefore($('#bright option:selected').prev('option'));
            }else{
              alert("sorry,已经是第一项了");
              return false;
            }
          }
          function DownItem(appRoleSetId){
            if(null == $('#bright').val()){
              alert('请选择一项');
              return false;
            }
            //索引的长度,从1开始
            var optionLength = $('#bright')[0].options.length;
            //选中的索引,从0开始
            var optionIndex = $('#bright').get(0).selectedIndex;
            //如果选择的不在最下面,表示可以向下
            if(optionIndex < (optionLength-1)){
              $('#bright option:selected').insertAfter($('#bright option:selected').next('option'));
            }else{
              alert('sorry，已经是最后一项了');
              return false;
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
