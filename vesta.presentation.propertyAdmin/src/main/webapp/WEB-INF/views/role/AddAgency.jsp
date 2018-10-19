<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/1/28
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
  <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
  <!-- js-->
  <script src="../static/js/jquery-1.11.1.min.js"></script>
  <script src="../static/js/modernizr.custom.js"></script>
  <!--animate-->
  <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
  <script src="../static/js/wow.min.js"></script>
  <script>
    $(function(){
      console.log("sqq")
      $("#001300020000").addClass("active");
      $("#001300020000").parent().parent().addClass("in");
      $("#001300020000").parent().parent().parent().parent().addClass("active");
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
</head>

<body class="cbp-spmenu-push">
<div>

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001300020000" username="${propertystaff.staffName}" />
  <div class="container1 userStaffManage">
    <form name="addAgency" id="addAgency">
      <div class="row">
        <div class="col-md-10 role_new_submit">
          <div class="newRoleSubmit">
            <button type="submit" class="btn btn-primary">保存</button>
            <a  href="/user/userStaffManage.html" class="btn btn-primary" for="" >关闭</a>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-10 role_new_submit2">
          <table class="table table-bordered">
            <caption class="role_table_title">组织机构信息</caption>
            <input type="hidden" name="agencyId" value="${agency.agencyId}">
            <tbody>
            <tr>
              <td class="role_table_roleName">组织机构名称</td>
              <td class="role_table_fillCont">
                <input type="text" class="roleNameText" id="agencyName" name="agencyName" value="${agency.agencyName}">
              </td>
            </tr>
            <tr>
              <td class="role_table_titleable_roleName">组织机构类型</td>
              <td class="role_table_fillCont">
                <input type="radio" name="agencyType" value="2"<c:if test="${agency.agencyType eq 2}">checked="checked"</c:if>>公司&nbsp;
                <input type="radio" name="agencyType" value="1"<c:if test="${agency.agencyType eq 1}">checked="checked"</c:if>>部门
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">上级部门（单选）</td>
              <td class="role_table_fillCont" id="aa">
                <input type="hidden" name="parentId" id="parentId" value="${pAgency.agencyId}">
                <span class="role_select" data-toggle="modal" data-target="#myModal3" >选择</span>
                <span class="role_select_result" id="role_originOnly">${pAgency.agencyName}</span>
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">排序</td>
              <td >
                <input type="text"  class="roleNameText" id="orderNum" name="orderNum" value="${agency.orderNum}">
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">外部合作单位</td>
              <td class="role_table_fillCont">
                <input type="checkbox" value="1" id="outEmploy" name="outEmploy" <c:if test="${agency.outEmploy eq 1}">checked="checked"</c:if>>（选中表示为是）
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">供应商</td>
              <td class="role_table_fillCont">
                <select class="form-control" id="supplier" name="supplier">
                  <option value="">请选择</option>
                  <c:forEach items="${supplierList}" var="list">
                    <option value="${list.supplierId}" <c:if test="${agency.supplierId eq list.supplierId}">selected="selected"</c:if>>${list.supplierName}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">是否有效</td>
              <td class="role_table_fillCont">
                <input type="checkbox" id="status" name="status" value="1" <c:if test="${agency.status eq 1}">checked="checked"</c:if>>（选中表示为是）
              </td>
            </tr>

            <tr>
              <td class="role_table_roleName">备注</td>
              <td>
                <input type="text"  class="roleNameText" id="memo" name="memo" value="${agency.memo}">
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">最后修改时间</td>
              <td>
                <span>${agency.modifyTime}</span>
              </td>
            </tr>
            <tr style="background-color: #F2F2F2"><td colspan="2">质量管理功能模块相关信息</td></tr>
            <tr>
              <td class="role_table_roleName">关联的角色</td>
              <td class="roleNameText">
                <input type="hidden" name="appRoleSets" id="appRoleSet" value="<c:forEach items="${roleDTO}" var="rl">,${rl.appSetId}</c:forEach>">
                <!-- <span class="existence" ></span> -->
                <div class="existence">
                  <span style="display:inline-block" id="role_linkRole">
                    <c:forEach items="${roleDTO}" var="roleList">
                      ${roleList.appSetName}
                    </c:forEach>
                  </span>
                </div>
                <span class="role_select" data-toggle="modal" data-target="#myModal" >选择</span>
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">关联的项目（请到项目管理页分配）</td>
              <td class="roleNameText">
                <div class="existence2">
                  <span style="display:inline-block" id="role_project">
                    <c:forEach items="${projectDTO}" var="roleList">
                      ${projectList.name}
                    </c:forEach>
                  </span>
                </div>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </form>
  </div>
</div>
</div>
</div>
<script type="text/javascript">
  $.validator.setDefaults({
    submitHandler: function() {
      if($('#role_originOnly').is(":empty")){
//        var ele=$('#aa')[0]
//        var aaa=document.createElement('label')
//        aaa.innerHTML="请选择上级部门！"
//        aaa.style.color="red"
//        ele.appendChild(aaa)
        alert("请选择上级部门");
        return false;
      }
      $.ajax({
        url: "../agency/addAgency",            //目标网址
        type: "post",
        async: "false",
        dataType: "json",
        data: $("#addAgency").serialize(),
        success: function (json) {
          <!-- 获取返回代码 -->
          var code = json.code;
          if (code != 0) {
            var errorMessage = json.msg;
            alert(errorMessage);
          } else {
            <!-- 获取返回数据 -->
            var data = json.data;
            if(data == 'ok'){
              window.location.href = '../user/userStaffManage.html';
              alert("保存成功");
            }
          }
        }
      });
    }
  });

  $().ready(function() {
    $("#addAgency").validate({
      rules: {
        agencyName: {
          required: true,
          minlength: 1,
          maxlength: 25
        },
        agencyType: {
          required: true,
        }
      },
      messages: {
        agencyName: {
          required: "请输入机构名称！",
          minlength: "不能少于1个字符！",
          maxlength: "请勿超过25个字！"
        },
        agencyType: {
          required: "请选择类型！"
        }
      },
    })

  })
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

<!-- 模态框（Modal） one-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
        <div class="container">
          <div class="containerSelect1">
            <div class="row">
              <div class="col-md-2  typeList">
                <div class="typeList_department_role">角色</div>
              </div>
            </div>
            <div class="row ">
              <ul class="content_wrap col-md-2" style="text-align:center;list-style:none">
              </ul>
              <div class="col-md-3 department ">
                <h5>待选列表</h5>
                <table class="table">
                  <tbody>
                  <tr>
                    <select id="left" multiple="multiple"  >
                    </select>
                  </tr>
                  </tbody>
                </table>
                <div class=" col-md-offset-5">
                  <button type="submit" class="confirm" class="close" data-dismiss="modal" aria-hidden="true">确认</button>
                  <button class="cancel" data-dismiss="modal">取消</button>
                </div>
              </div>
              <div class=" col-md-2 moveBtn">
                <button id="btnR">添加></button>
                <button id="btnD"><移除</button>
              </div>
              <div class="col-md-3 department ">
                <h5 class="h52">已选列表</h5>
                <form class="submitForm" name="form" method="post" action="">
                  <table class="table">
                    <tbody>
                    <tr>
                      <select id="right" multiple="multiple">
                        <!-- <p></p> -->
                        <c:forEach items="${roleDTO}" var="roleList">
                          <option class="optionClass" data-optionid="${roleList.appSetId}" value="${roleList.appSetId}">${roleList.appSetName}</option>
                        </c:forEach>
                      </select>
                    </tr>
                    </tbody>
                  </table>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- 模态框（Modal）3 three-->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
        <div class="container">
          <div class="containerSelect1">
            <div class="row">
              <div class="col-md-2  typeList">
                <div class="typeList_department_project">组织机构</div>
              </div>
            </div>
            <div class="row ">
              <!-- <ul class="content_wrap2 col-md-2" style="text-align:center;list-style:none">
              </ul> -->
              <div class="content_wrap3 col-md-3">
                <div class="zTreeDemoBackground left">
                  <ul id="treeDemo" class="ztree"></ul>
                </div>
              </div>
              <div class="col-md-3 department ">
                <h5>待选列表</h5>
                <table class="table">
                  <tbody>
                  <tr>
                    <select id="left3" multiple="multiple">
                    </select>
                  </tr>
                  </tbody>
                </table>
                <div class=" col-md-offset-5">
                  <button type="button" class="confirm3" class="close" data-dismiss="modal" aria-hidden="true">确认</button>
                  <button class="cancel" data-dismiss="modal">取消</button>
                </div>
              </div>
              <div class=" col-md-2 moveBtn">
                <button id="btnR3">添加></button>
                <button id="btnD3"><移除</button>
              </div>
              <div class="col-md-3 department ">
                <h5 class="h52">已选列表</h5>
                <form class="submitForm" id="form" name="form" method="post" action="">
                  <table class="table">
                    <tbody>
                    <tr>
                      <select id="right3" multiple="multiple">
                        <option class="htmlPerson" value="${pAgency.agencyId}">${pAgency.agencyName}</option>
                      </select>
                    </tr>
                    </tbody>
                  </table>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  //从后台接受的数据
  $().ready(function() {
    $.ajax({
      url: "../user/appRoleSets",            //角色
      type: "get",
      async: "false",
      dataType: "json",
      success: function (json) {
        <!-- 获取返回代码 -->
        var code = json.code;
        if (code != 0) {
          var errorMessage = json.msg;
          alert(errorMessage);
        } else {
          <!-- 获取返回数据 -->
          var data = json.data;
          //模态框1
          var h='';//h一个随意的字符串
          var x='';//x一个随意的字符串
          for(var a=0;a<data.length;a++){
            h+='<li data_id='+data[a].appRoleSetId+' >'+data[a].appRoleSetName+'</li>';
          }
          $('.content_wrap').html(h);
          $('.content_wrap>li').map(function(){
            // 当前的li
            var lf = $(this);
            //var left = $(this).html();

            if($('#right>option').length!=0){
              $('#right>option').map(function(){
                if(lf.html() != $(this).html()){
                  lf.off().one('click',function(){
                    // console.log('因为'+lf.html()+'右面没有选中')
                    var x='';
                    var selectId;
                    var selectName;
                    selectId=$(this).attr('data_id');
                    selectName=$(this).html();
                    x = $('#left')[0].innerHTML;
                    if (!x) x='';
                    x+='<option class="optionClass" data-optionId='+selectId+' >'+selectName+'</option>';
                    $('#left').html(x);
//                    $('#btnR').bind('click',function(){
//                      $('#left option:selected').appendTo('#right');
//                    });
//                    $('#btnD').bind('click',function(){
//                      var kk=$('#right option:selected').appendTo('#left');
//                      // console.log(kk.data_optionId);
//                    });
                  })
                }else{
                  lf.off().one('click',function(){
                    console.log('因为我是'+lf.html()+'已经选中了')
                  })
                }
              })
            }else{
              lf.off().one('click',function(){
                // console.log('因为'+lf.html()+'右面没有选中')
                var x='';
                var selectId;
                var selectName;
                selectId=$(this).attr('data_id');
                selectName=$(this).html();
                x = $('#left')[0].innerHTML;
                if (!x) x='';
                x+='<option class="optionClass" data-optionId='+selectId+' >'+selectName+'</option>';
                $('#left').html(x);
              })
            }

          })
          $('#btnR').bind('click',function(){
            $('#left option:selected').appendTo('#right');
          });
          $('#btnD').bind('click',function(){
            var kk=$('#right option:selected').appendTo('#left');
            // console.log(kk.data_optionId);
          });
          // 模态框1确认
          $('.confirm').bind('click',function(){
            var liShow=$('#right').html();
            var result=$('.existence').html(liShow);
            result.find('option').addClass('optionClassPage1');
            var temp="";
            $('.optionClassPage1').map(function(i){
              temp+=$(this).attr('data-optionid')+",";
            })
            $('#appRoleSet').val(","+temp.substr(0,temp.length-1));
          });
        }
      }
    })
  })


  //模态框3开始
  //从后台拿到右边的数据
  var a="";
  var selectedData=[];
  $("#right3 option").each(function(i){
    if(this.value!=""){
      a+='{"name":"'+this.innerHTML+'","id":"'+ this.value+'"},';
    }
  });
  if(a!=""){
    a=JSON.parse(a.substr(0, a.length-1));
    selectedData=[a];
  }
  arrData2select(selectedData,'#right3');
  var temp3=[];
  for (var i = 0;i<selectedData.length; i++) {
    temp3[i] = selectedData[i].id;
  }
  $('#parentId').val(temp3.join());

  var selectedList=[]
  var waitLists={};
  var setting = {
    async: {
      enable: true,
      url:"/agency/initAgency",
      autoParam:["id", "name=n", "level=lv"],
      otherParam:{"otherParam":"zTreeAsyncTest"},
      dataFilter: filter,
    },
    callback: {
      onClick: onClick
    }
  };
  function strToJson(str){
    var json = (new Function("return " + str))();
    return json;
  }
  function onClick(event, treeId, treeNode, clickFlag) {
    var childrenArr;
    var leftId='#left3'
//    if (!waitLists['id'+treeNode.id]) {
    //是最后一个  找后台要下一级
//      if (treeNode.isParent) {
//        $.getJSON('/agency/nextAgency?id='+treeNode.id,function(res){
//          // console.log(res)
//          childrenArr=res.data;
//          arrData2select(childrenArr,leftId);
//        })
//      }else{
    childrenArr=[{"id":treeNode.id,"name":treeNode.name}]
    arrselect(childrenArr,leftId);
//      }
//    }else{
//      //找下一级
//      childrenArr=waitLists[('id'+treeNode.id)];
//      arrData2select(childrenArr,leftId);
//    }

  }

  function filter(treeId, parentNode, childNodes) {
    if (!childNodes) {
      return null;
    }
    var arr=[];
    for (var i=0, h=childNodes.length; i<h; i++) {
      childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
      //arr.push  下一级的 name  id
      arr.push({name:childNodes[i].name,id:childNodes[i].id,isParent:childNodes[i].isParent})
    }
    if (parentNode) {
      waitLists['id'+parentNode.id]=arr;
    }else{
      waitLists['idRoot']=arr;
    }
    return childNodes;

  }
  function arrselect(arr,id) {
    //arrData2select(arr,'#right')
    //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
    if ( id=='#right3'&&arr!=selectedData) {
      arr=selectedData.concat(arr)
      selectedData = arr;
    };
    var htmlGroup='</optgroup>';
    for (var i = 0;i<arr.length; i++) {
      // 比较两个id看是否重复
      var isContains=contains(selectedData.map(function(res){return res.id}),arr[i].id) ;
      if (isContains && id=='#left3') {
        continue;
      };
      htmlGroup=htmlGroup+'<option class="htmlGroup" data-id="'+arr[i].id+'"">'+arr[i].name+'</option>'
    };
    $(id).html('');
    $(id).append(htmlGroup);
  }
  //方法
  function arrData2select(arr,id) {
    //arrData2select(arr,'#right')
    //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
    if ( id=='#right3'&&arr!=selectedData) {
      arr=selectedData.concat(arr)
      selectedData = arr;
    };
    var htmlGroup='</optgroup>';
    for (var i = 0;i<arr.length; i++) {
      // 比较两个id看是否重复
      var isContains=contains(selectedData.map(function(res){return res.id}),arr[i].id) ;
      if (isContains && id=='#left3') {
        continue;
      };
      htmlGroup=htmlGroup+'<option class="htmlGroup" data-id="'+arr[i].id+'"">'+arr[i].name+'</option>'
    };
    $(id).html('');
    $(id).append(htmlGroup);
  }
  function select(){
    $('#btnR3').bind('click',function(){
      wait2selected();
    });
    $('#btnD3').bind('click',function(){
      selected2wait();
    });
  }
  //右移方法
  function wait2selected(){
    //将左侧选中的数据放进一个数组中
    var optionArr=$('#left3 option:selected');
    // console.log(optionArr);
    var arr=[];

    for (var i = 0;i<optionArr.length; i++) {

      arr[i]={name:optionArr[i].value,id:optionArr[i].dataset.id};
//      alert(arr[i]);
    };
    //移除之前选中的那个数组
    optionArr.remove();
    arrData2select(arr,'#right3');
  }
  //左移方法
  function selected2wait(){
    var optionArr=$('#right3 option:selected');
    var arr=[];
    for (var i = 0;i<optionArr.length; i++) {
      arr[i]={name:optionArr[i].value,id:optionArr[i].dataset.id}
    };

    optionArr.remove();
    // selectedData
    selectedData=arrSubtraction(selectedData,arr);
    var optionArrLeft=$('#left3 option');
    var arrLeft=[];
    for (var i = 0;i<optionArrLeft.length; i++) {
      if (optionArrLeft[i])
        arrLeft[i]={name:optionArrLeft[i].value,id:optionArrLeft[i].dataset.id}
    };
    arr=arrLeft.concat(arr)
    arrData2select(arr,'#left3')
  }
  select();
  $(document).ready(function(){
    $.fn.zTree.init($("#treeDemo"), setting);
  });
  function arrSubtraction(arr1,arr2){
    var temp = [];
    var num=0;
    for (var i = 0;i<arr1.length; i++) {
      //arr2.map(function(res){return res.id;}数组列表id 和arr1[i].id进行对比
      var isContains=contains(arr2.map(function(res){return res.id;}), arr1[i].id)
      //作为对比 如果不含有就把它 赋给ta
      if (!isContains){
        temp[num]=arr1[i];num++;
      }
    };
    return temp;
  }
  //arr, obj看数组中是否含有对象obj可以理解成去重 left3
  function contains(arr, obj) {
    var i = arr.length;
    while (i--) {
      if (arr[i] === obj) {
        return true;
      }
    }
    return false;
  };

  // 提交选择
  $('.confirm3').bind('click',function(){
    // alert(111);
    var liShow=$('#right3').html();
    $('.role_select_result').html(liShow);
    $('.role_select_result').find('option').addClass('floatSelectResult');
    $('#htmlGroup').css('display','none');
    $('#htmlPerson').css('display','none');
    var tagency=[];
    var i=0;
    $("#right3 option").each(function () {
        tagency[i] = $(this).attr("data-id");
        i++;
    });
    $('#parentId').val(tagency.join());
  });

</script>
</body>
</html>