<%--
  Created by IntelliJ IDEA.
  User: lpc
  Date: 2016/6/5
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <link href="../static/css/chosen.css" rel='stylesheet' type='text/css'/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/jquerysession.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>

    <%--<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>--%>
    <script src="../static/js/chosen.jquery.min.js"></script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <%--<link rel="stylesheet" href="../static/css/jquery-ui.min.css" />
    <script src="../static/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>--%>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
    <style>
        label.error {
            margin-left: 1%;
            color: red;
        }
    </style>
    <style>
        td {
            overflow:hidden;text-overflow:ellipsis;/*display:-webkit-box;*/-webkit-box-orient:vertical;-webkit-line-clamp:2;
            /*white-space:nowrap;overflow:hidden;text-overflow: ellipsis;*/
        }
    </style>
    <style type="text/css">#dialog{display:none;}</style>

</head>
<script type="application/javascript">
    <c:if test="${category eq'3'}">
    $(function(){
        $("#007000070000").removeClass("active");
        $("#007000070000").parent().parent().addClass("in");
        $("#007000070000").addClass("active");
        $("#007000070000").parent().parent().addClass("in");
    })
    </c:if>
    <c:if test="${category eq'2'}">
    $(function(){
        $("#007100070000").removeClass("active");
        $("#007100070000").parent().parent().addClass("in");
        $("#007100070000").addClass("active");
        $("#007100070000").parent().parent().addClass("in");
    })
    </c:if>
    <c:if test="${category eq'1'}">
    $(function(){
        $("#007300070000").removeClass("active");
        $("#007300070000").parent().parent().addClass("in");
        $("#007300070000").addClass("active");
        $("#007300070000").parent().parent().addClass("in");
    })
    </c:if>
</script>
<body class="cbp-spmenu-push">
<div class="main-content">
    <c:if test="${category eq'3'}">
        <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007000070000" username="${authPropertystaff.staffName}" />
    </c:if>
    <c:if test="${category eq'2'}">
        <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007100070000" username="${authPropertystaff.staffName}" />
    </c:if>
    <c:if test="${category eq'1'}">
        <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="007300070000" username="${authPropertystaff.staffName}" />
    </c:if>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">

                <form class="form-horizontal" id="search" name="search" action="../authAgency/authFunctionPoint.html" method="get">
                    <div style="margin-left: -5%;">
                        <%-- app下拉框 --%>
                        <div class="form-group  col-lg-4">
                            <label for="category" class="col-sm-4 control-label">权限设置：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="category" name="category">
                                    <%--<option value="0">请选择</option>--%>
                                    <c:if test="${category eq '1'}">
                                        <option value="1" <c:if test="${typeMaps.category eq '1'}">selected</c:if>>客关APP</option>
                                    </c:if>
                                    <c:if test="${category eq '2'}">
                                        <option value="2" <c:if test="${typeMaps.category eq '2'}">selected</c:if>>工程APP</option>
                                    </c:if>
                                    <c:if test="${category eq '3'}">
                                        <option value="3" <c:if test="${typeMaps.category eq '3'}">selected</c:if>>安全APP</option>
                                    </c:if>
                                </select>
                            </div>
                        </div>

                        <%-- 角色--%>
                        <div class="form-group  col-lg-4">
                            <label for="authRoleId" class="col-sm-4 control-label">角色：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="authRoleId" name="authRoleId"  onchange= "changeProvince()">
                                    <c:forEach items="${typeMaps.roles}" var="role">
                                        <option value="${role.key}"  <c:if test="${role.key eq problem.authRoleId}">selected</c:if>>${role.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <%-- 角色--%>
                        <div class="form-group  col-lg-4">
                            <label for="classification" class="col-sm-4 control-label">类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="classification" name="classification"  onchange= "changeProvince()">
                                    <option value="0">请选择</option>
                                    <option value="1" <c:if test="${typeMaps.classification eq '1'}">selected</c:if>>APP</option>
                                    <option value="2" <c:if test="${typeMaps.classification eq '2'}">selected</c:if>>管理后台</option>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="clearfix"></div>
                    <%--<button type="submit" class="btn btn-primary" for="propertySearch" ><spring:message code="problem.search" /></button>--%>
                    <c:if test="${function.sth40020029 eq 'Y' || function.esh40020095 eq 'Y' || function.qch40010122 eq 'Y'}">
                        <button  class="btn btn-primary" onClick="Update();">保存</button>
                    </c:if>
                    <button  class="btn btn-primary" type="button" onClick="ctrlC();">复制授权</button>
                    <button  class="btn btn-primary" type="button" onClick="ctrlV();">粘贴授权</button>
                    <label id="appNameId"></label>
                    <label id="adminNameId" ></label>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle" style="table-layout: fixed;">
            <thead>
            <tr>
                <%--<th width="4%"><input type="checkbox" name="answer" onclick="checkAllBox(this)">序号</th>--%>
                <c:if test="${problems.classification eq '1'}">
                    <th width="14.2%">一级模块</th>
                    <th width="14.2%">二级模块</th>
                    <th width="14.2%" >三级模块</th>
                    <th width="14.2%">功能点</th>
                    <th width="14.2%">控制方式</th>
                    <th width="14.2%">功能说明</th>
                    <th width="14.2%">类型</th>
                </c:if>
                <c:if test="${problems.classification eq '2'}">
                    <th width="14.2%">顶部菜单</th>
                    <th width="14.2%">一级菜单</th>
                    <th width="14.2%" >二级菜单</th>
                    <th width="14.2%">功能点</th>
                    <th width="14.2%">控制方式</th>
                    <th width="14.2%">功能说明</th>
                    <th width="14.2%">类型</th>
                </c:if>

            </tr>
            </thead>
            <tbody>
            <input type="hidden" id="authChecked" name="authChecked" value="${problems.checked}" />
            <c:forEach items="${problems.list}" var="problem" varStatus="row">
                <tr>
                <td><input name="ids" type="checkbox" value="${problem.cid}"/>${problem.name}</td>
                <td>------------</td>
                <td>------------</td>
                <td>------------</td>
                <td></td>
                <td></td>
                <td></td>
                <c:forEach items="${problem.child}" var="problem2" varStatus="row">
                    <tr>
                    <td></td>
                    <td><input name="ids" type="checkbox" value="${problem2.cid}"/>${problem2.name}</td>
                    <td>------------</td>
                    <td>------------</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <c:forEach items="${problem2.child}" var="problem3" varStatus="row">
                        <tr>
                        <td></td>
                        <td></td>
                        <td><input name="ids" type="checkbox" value="${problem3.cid}"/>${problem3.name}</td>
                        <td>------------</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <c:forEach items="${problem3.child}" var="problem4" varStatus="row">
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><input name="ids4" type="checkbox" value="${problem4.cid}"/>${problem4.name}</td>
                                <td>
                                    <c:if test="${problem4.configurable eq '0'}">
                                        <select class="form-control" placeholder="" id="s${problem4.cid}" name="s${problem4.cid}">
                                            <option value="0" selected>请选择</option>
                                            <option value="1" <c:if test="${problem4.control eq '1'}">selected</c:if>>全部</option>
                                                <%--<option value="2" <c:if test="${problem4.control eq '2'}">selected</c:if>>有数据权限</option>--%>
                                            <option value="3" <c:if test="${problem4.control eq '3'}">selected</c:if>>自己的</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${problem4.configurable eq '1'}">
                                        <select class="form-control" placeholder="" id="s${problem4.cid}" name="s${problem4.cid}" style="display:none">
                                            <c:if test="${problem4.control eq '1'}">
                                                <option value="1" selected>全部</option>
                                            </c:if>
                                            <c:if test="${problem4.control eq '2'}">
                                                <option value="2" selected>数据权限</option>
                                            </c:if>
                                            <c:if test="${problem4.control eq '3'}">
                                                <option value="3" selected>自己的</option>
                                            </c:if>
                                        </select>
                                    </c:if>
                                </td>
                                <td>${problem4.explain}</td>
                                <td>
                                    <c:if test="${problems.classification eq '1'}">APP</c:if>
                                    <c:if test="${problems.classification eq '2'}">管理后台</c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tr>
                    </c:forEach>
                    </tr>
                </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</div>
</div>

<!-- main content end-->
<script type="text/javascript">
</script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">


    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
</script>
<%@ include file="../main/foolter.jsp" %>
</div>

<script>
    var ckeckCategory="";
    $(function() {//页面加载的时候触发
        //判断是否为同意登录链接属性平台   不是  清空session
        if(${category ne ckeckCategory}){
            $.session.remove('appValue');
            $.session.remove('svalue');
            $.session.remove('appNameValue');
            $.session.remove('adminValue');
            $.session.remove('appNameValue');
            ckeckCategory=${category};
        }
    })

    $("#category").change(function () {
//        $("#category").html("");
        var category = $("#category").val();
        $.ajax({
            url: "../authAgency/getRoleList",
            type: "get",
            async: "false",
            data: {"category": category},
            dataType: "json",
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
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
                    console.log(data);
                    var option = "";
                    if (data != null) {
                        document.getElementById("authRoleId").innerHTML = "";
                        for (var prop in data) {
                            if (!isNaN(data[prop])) {
                            } else {
                                if (data.hasOwnProperty(prop)) {
                                    if (prop == '0') {
                                        option = option + "<option value='" + prop + "'selected='selected'>" + data[prop] + "</option>";
                                    } else {
                                        option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                    }

                                }
                            }
                        }

                        $("#authRoleId").append(option);
                        var category = $(" #category ").val();
                        var authRoleId = $(" #authRoleId ").val();
                        var classification = $(" #classification ").val();
                        window.location.href = "../authAgency/authFunctionPoint.html?category="+category+"&authRoleId="+authRoleId+"&classification="+classification+"";
                    }
                }
            }
        });
    });

    function  changeProvince(){
        var category = $(" #category ").val();
        var authRoleId = $(" #authRoleId ").val();
        var classification = $(" #classification ").val();
        window.location.href = "../authAgency/authFunctionPoint.html?category="+category+"&authRoleId="+authRoleId+"&classification="+classification+"";

    }


    function ctrlC(){
        var category = $(" #category ").val();
        var authRoleId = $(" #authRoleId ").val();
        var classification = $(" #classification ").val();

        if(category==null||category==undefined||category==""||category=="0"||
            authRoleId==null||authRoleId==undefined||authRoleId==""||
            classification==null||classification==undefined||classification==""||classification=="0"){
            if(category==null||category==undefined||category==""||category=="0"){
                alert('请选择权限设置！')
            }else if(authRoleId==null||authRoleId==undefined||authRoleId==""||authRoleId=="0"){
                alert('请选择需要复制权限的角色！')
            }else if(classification==null||classification==undefined||classification==""||classification=="0"){
                alert('请选择需要复制权限的类型！')
            }
        }else{
            if(classification=="1"){
                $.session.remove('appValue');
                $.session.remove('svalue');
                var svalue='';
                var value = '';
                var id = document.getElementsByName('ids');
                for(var i = 0; i < id.length; i++){
                    if(id[i].checked)
                        value=value+id[i].value+",";
                }
                var id4 = document.getElementsByName('ids4');
                for(var i = 0; i < id4.length; i++){
                    if(id4[i].checked){
                        value=value+id4[i].value+",";
                        svalue=svalue+id4[i].value+":"+$('#s'+id4[i].value+' option:selected').val()+",";
                    }
                }
                if(value==null||value==undefined||value==""){
                    alert('无权限数据可复制！')
                }else{
                    $.session.set('appValue', value);
                    $.session.set('svalue', svalue);
                    $.session.remove('appNameValue');
                    var authRoleName = $("#authRoleId option:selected").text();
                    $.session.set('appNameValue', '(已复制：'+authRoleName+'授权app)');
                    $("#appNameId").html( $.session.get('appNameValue'));
                }

            }else if(classification=="2"){
                $.session.remove('adminValue');
                var value = '';
                var id = document.getElementsByName('ids');
                for(var i = 0; i < id.length; i++){
                    if(id[i].checked)
                        value=value+id[i].value+",";
                }
                var id4 = document.getElementsByName('ids4');
                for(var i = 0; i < id4.length; i++){
                    if(id4[i].checked)
                        value=value+id4[i].value+",";
                }
                if(value==null||value==undefined||value==""){
                    alert('无权限数据可复制！')
                }else{
                    $.session.set('adminValue', value);
                    $.session.remove('appNameValue');
                    var authRoleName = $("#authRoleId option:selected").text();
                    $.session.set('adminNameValue', '(已复制：'+authRoleName+'授权admin)');
                    $("#adminNameId").html( $.session.get('adminNameValue'));
                }
            }
        }


    }
    function ctrlV(){
        var category = $(" #category ").val();
        var authRoleId = $(" #authRoleId ").val();
        var classification = $(" #classification ").val();

        if(category==null||category==undefined||category==""||category=="0"||
            authRoleId==null||authRoleId==undefined||authRoleId==""||
            classification==null||classification==undefined||classification==""||classification=="0"){
            if(category==null||category==undefined||category==""||category=="0"){
                alert('请选择权限设置！')
            }else if(authRoleId==null||authRoleId==undefined||authRoleId==""||authRoleId=="0"){
                alert('请选择复制权限的角色！')
            }else if(classification==null||classification==undefined||classification==""||classification=="0"){
                alert('请选择复制权限的类型！')
            }
        }else{
            var value='';
            var svalue='';
            if(classification=="1"){
                var value=$.session.get('appValue');
                var svalue=$.session.get('svalue');
            }else if(classification=="2"){
                var value=$.session.get('adminValue');
            }
            if(value!=""){
                var boxObj = $("input:checkbox[name='ids']");  //获取所有的复选框
                var express = value.split(','); //去掉它们之间的分割符“，”
                for(i=0;i<boxObj.length;i++){
                    for(j=0;j<express.length;j++){
                        if(boxObj[i].value == express[j])  //如果值与修改前的值相等
                        {
                            boxObj[i].checked= true;
                            break;
                        }
                    }
                }

                var boxObj = $("input:checkbox[name='ids4']");  //获取所有的复选框
                var express = value.split(','); //去掉它们之间的分割符“，”
                for(i=0;i<boxObj.length;i++) {
                    for (j = 0; j < express.length; j++) {
                        if (boxObj[i].value == express[j])  //如果值与修改前的值相等
                        {
                            boxObj[i].checked = true;
                            break;
                        }
                    }
                }
                if(svalue!=""){
                    var svaluess = svalue.split(','); //去掉它们之间的分割符“，”
                    for(i=0;i<svaluess.length;i++){
                        var svalues=svaluess[i].split(':');
                        $('#s'+svalues[0]).val(svalues[1]);
                    }
                }
            }else{
                alert('无可复制数据！')
            }
        }
    }


    function  Update(){
        var category = $(" #category ").val();
        if(category==0 ||category=="0"){
            alert("请选择权限类型！！");
            return false;
        }
        var authRoleId = $(" #authRoleId ").val();
        if(authRoleId && typeof(authRoleId)=="undefined" && authRoleId==0 ||authRoleId=="0"){
            alert("请选择角色！！");
            return false;
        }
        var classification = $(" #classification ").val();
        if(classification && typeof(classification)=="undefined" && classification==0 ||classification=="0"){
            alert("请选择类型！！");
            return false;
        }
        var id = document.getElementsByName('ids');
        var value = '';
        for(var i = 0; i < id.length; i++){
            if(id[i].checked)
                value=value+id[i].value+",";
        }
        var id4 = document.getElementsByName('ids4');
        for(var i = 0; i < id4.length; i++){
            if(id4[i].checked)
                value=value+id4[i].value+":"+$('#s'+id4[i].value+' option:selected').val()+",";
        }
        if(value.length>0){
            $.ajax({
                url: "../authAgency/addRole",
                type: 'post',
                async: false,
                dataType: "json",
                data: {
                    "authRoleId": authRoleId,
                    "controlList":value,
                    "classification":classification,
                    "category":category
                },
                success: function (data) {
                    if (data.check == 1) {

                    }else{
                        alert("对不起，操作失败！");
                    }
                }
            });
        }else{
            alert("未设置权限！！");
            return false;
        }

    }




    $(function() {//页面加载的时候触发
        var boxObj = $("input:checkbox[name='ids']");  //获取所有的复选框
        var expresslist =  $(" #authChecked ").val(); //用el表达式获取在控制层存放的复选框的值为字符串类型
        var express = expresslist.split(','); //去掉它们之间的分割符“，”
        for(i=0;i<boxObj.length;i++){
            for(j=0;j<express.length;j++){
                if(boxObj[i].value == express[j])  //如果值与修改前的值相等
                {
                    boxObj[i].checked= true;
                    break;
                }
            }
        }

        var boxObj = $("input:checkbox[name='ids4']");  //获取所有的复选框
        var expresslist =  $(" #authChecked ").val(); //用el表达式获取在控制层存放的复选框的值为字符串类型
        var express = expresslist.split(','); //去掉它们之间的分割符“，”
        for(i=0;i<boxObj.length;i++){
            for(j=0;j<express.length;j++){
                if(boxObj[i].value == express[j])  //如果值与修改前的值相等
                {
                    boxObj[i].checked= true;
                    break;
                }
            }
        }
        var adminName =$.session.get('adminNameValue');
        if(typeof(adminName)!="undefined" && adminName!=null && adminName!=""){
            $("#adminNameId").html(adminName);
        }
//        $("#adminNameId").text(adminName);

        var appName=$.session.get('appNameValue');
        if(typeof(appName)!="undefined" && appName!=null && appName!=""){
            $("#appNameId").html(appName);
        }
//        alert(appName)
//        $("#appNameId").text(appName);
//
    })





</script>
</body>

</html>