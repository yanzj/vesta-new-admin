<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2017/12/19
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ page import="com.maxrocky.vesta.utility.StringUtil" %>
<%response.setHeader("cache-control", "public"); %>
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
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        $(function(){
            console.log("sqq")
            $("#007100010000").addClass("active");
            $("#007100010000").parent().parent().addClass("in");
            $("#007100010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <style>
        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 100px;
            height: 120px;
        }

        .sidebar ul li {
            border-bottom: 0;
        }
        .forms{
            overflow: hidden;
        }
    </style>
    <%-- zTree --%>
    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007100010000" username="${authPropertystaff.staffName}"/>

    <div style="text-align: right; margin-top: 8px; margin-right: 25px;">
        <c:if test="${function.esh40020062 eq 'Y'}">
            <a href="../authorityForProject/initProjectUserManage.html" class="btn btn-primary">内部用户管理</a>
        </c:if>
        <a href="../authorityForProject/outerProjectUserManage.html" class="btn btn-primary">外部用户管理</a>
        <c:if test="${function.esh40020079 eq 'Y'}">
            <a href="../authorityForProject/getProjectEnabledUser.html" class="btn btn-primary">系统用户管理</a>
        </c:if>
    </div>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" name="searchByName" id="searchByName"
                      action="../authorityForProject/outerProjectUserManage.html">
                    <%--所属机构--%>
                    <div class="form-group  col-lg-4">
                        <label for="agencyNameO" class="col-sm-4 control-label">所属机构</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="agencyNameO"
                                   name="agencyNameO" value="${outerUserDTO.agencyNameO}">
                        </div>
                    </div>
                        <%--人员姓名--%>
                        <div class="form-group  col-lg-4">
                            <label for="staffNameO" class="col-sm-4 control-label">人员</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="staffNameO"
                                       name="staffNameO" value="${outerUserDTO.staffNameO}">
                            </div>
                        </div>
                        <%--系统账号--%>
                        <div class="form-group  col-lg-4">
                            <label for="userNameO" class="col-sm-4 control-label">系统账号</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="userNameO"
                                       name="sysNameO" value="${outerUserDTO.sysNameO}">
                            </div>
                        </div>
                        <%--启用状态--%>
                        <div class="form-group  col-lg-4">
                            <label for="stateO" class="col-sm-4 control-label">启用状态</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="stateO" name="stateO">
                                    <option value="" selected>请选择</option>
                                    <option value="1" <c:if test="${outerUserDTO.stateO eq '1'}">selected</c:if>>启用</option>
                                    <option value="0" <c:if test="${outerUserDTO.stateO eq '0'}">selected</c:if>>停用</option>
                                </select>
                            </div>
                        </div>
                        <%--电话--%>
                        <div class="form-group  col-lg-4">
                            <label for="mobileO" class="col-sm-4 control-label">手机号码</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="mobileO"
                                       name="mobileO" value="${outerUserDTO.mobileO}">
                            </div>
                        </div>
                        <%--邮箱--%>
                        <div class="form-group  col-lg-4">
                            <label for="emailO" class="col-sm-4 control-label">邮箱</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="" id="emailO"
                                       name="emailO" value="${outerUserDTO.emailO}">
                            </div>
                        </div>
                        <!--集合长度(取决Excel是否可以导出)-->
                        <input type="hidden" id="size" value="${isExcel}">
                        <input type="hidden" id="hasUser" value="${agencyStateDTO.delIs}">
                        <div style="text-align: center; margin-top: 8px;">
                            <c:if test="${function.esh40020070 eq 'Y'}">
                                <button type="submit" id="sbm" class="btn btn-primary" for="propertySearch">查询</button>
                            </c:if>
                            <c:if test="${function.esh40020071 eq 'Y'}">
                                <a href="../authorityForProject/toEditOuterAgency.html?agencyId=${outerUserDTO.agencyIdO}" class="btn btn-primary" >添加组织机构</a>
                            </c:if>
                            <c:if test="${function.esh40020085 eq 'Y'}">
                                <a href="javascript:void(0)" onclick="delAgency('${agencyStateDTO.delAgencyName}','${agencyStateDTO.delAgencyId}')" class="btn btn-primary" >删除组织机构</a>
                            </c:if>
                            <c:if test="${function.esh40020072 eq 'Y'}">
                                <a href="../authorityForProject/toEditOuterUser.html" class="btn btn-primary" >添加用户</a>
                            </c:if>
                            <c:if test="${function.esh40020082 eq 'Y'}">
                                <a href="javascript:void(0)" onclick="downloadModel()" class="btn btn-primary">下载用户模板</a>
                            </c:if>
                            <c:if test="${function.esh40020083 eq 'Y'}">
                                <button type="button" class="btn btn-primary" onclick="test()">导入数据</button>
                            </c:if>
                            <c:if test="${function.esh40020084 eq 'Y'}">
                                <a href="#" onclick="isExcel()"  value="" class="btn btn-primary">导出Excel</a>
                            </c:if>
                            <c:if test="${function.esh40020073 eq 'Y'}">
                                <a href="javascript:void(0)" onclick="batchStartStaff()" class="btn btn-primary">批量启用</a>
                            </c:if>
                            <c:if test="${function.esh40020074 eq 'Y'}">
                                <a href="javascript:void(0)" onclick="batchStopStaff()" class="btn btn-primary">批量停用</a>
                            </c:if>
                            <c:if test="${function.esh40020075 eq 'Y'}">
                                <a href="javascript:void(0)" onclick="batchDele()" class="btn btn-primary">批量删除</a>
                            </c:if>
                        </div>
                </form>
                <form action="../authorityForProject/excelAddOuterPeople",name="frm" id="frm" method="post" enctype="multipart/form-data">
                    <%-- 导入excel 隐藏 --%>
                    <input type="file" id="myfile" name="myfile" style="visibility:hidden;" onchange="importExcel()">
                        <%--项目下是否有人员--%>
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
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>

    <div class="table-responsive bs-example widget-shadow">
        <div class="row">
            <div class="content_wrap col-md-3" style="height: 400px; overflow: scroll">
                <div class="zTreeDemoBackground left" style="border-top:1px solid  #ccc; ;">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
                <div class="people_list"></div>
            </div>
            <div class="table-responsive widget-shadow col-md-9" style="float:left;margin-top:0">
                <table class="tableStyle  col-md-12">
                    <thead>
                    <!-- 点击部门 显示人员的列表 -->
                    <tr>
                        <th><input name="checkall1" type="checkbox" value="0" onClick="checkAllBox(this);"/>序号</th>
                        <th>姓名</th>
                        <th>系统账号</th>
                        <th>手机号码</th>
                        <th>邮箱地址</th>
                        <th>所属机构</th>
                        <th>状态</th>
                        <th>最后修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="publicTbody">
                    <c:forEach items="${outerUserDTOs}" var="list" varStatus="as">
                        <tr>
                            <td height="40px" style="text-align: center">
                                <input type="checkbox" name="staffIdO"
                                       value="${list.staffIdO}${list.stateO}"><b>${(webPage.pageIndex-1)*20+as.index+1}</b>
                            </td>
                            <td>${list.staffNameO}</td>
                            <td>${list.sysNameO}</td>
                            <td>${list.mobileO}</td>
                            <td>${list.emailO}</td>
                            <td>${list.agencyNameO}</td>
                            <td>
                                <c:if test="${list.stateO eq '0'}">未启用
                                </c:if>
                                <c:if test="${list.stateO eq '1'}">已启用
                                </c:if>
                            </td>
                            <td>${list.modifyOnO}</td>
                            <td>
                                <c:if test="${function.esh40020076 eq 'Y'}">
                                <a href="javascript:void(0);" onclick="toEdit('${list.staffIdO}')" id="toEdit"
                                   class="a3">编辑</a>
                                </c:if>
                                <c:if test="${function.esh40020077 eq 'Y'}">
                                    <a href="javascript:void(0);" onclick="toHandleStaff('${list.staffIdO}','${list.stateO}')" id="toHandle"
                                       class="a3">
                                        <c:if test="${list.stateO eq '0'}">启用
                                        </c:if>
                                        <c:if test="${list.stateO eq '1'}">停用
                                        </c:if>
                                    </a>
                                </c:if>
                                <c:if test="${function.esh40020078 eq 'Y'}">
                                    <a href="javascript:void(0);" onclick="toDelete('${list.staffIdO}','${list.stateO}')" id="toDelete"
                                       class="a3">删除</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <m:pager pageIndex="${webPage.pageIndex}"
                         pageSize="${webPage.pageSize}"
                         recordCount="${webPage.recordCount}"
                         submitUrl="${pageContext.request.contextPath }../authorityForProject/outerProjectUserManage.html?pageIndex={0}&agencyNameO=${outerUserDTO.agencyNameO}&staffNameO=${outerUserDTO.staffNameO}&sysNameO=${outerUserDTO.sysNameO}&stateO=${outerUserDTO.stateO}&mobileO=${outerUserDTO.mobileO}&emailO=${outerUserDTO.emailO}&agencyIdO=${outerUserDTO.agencyIdO}"/>
            </div>
        </div>
    </div>

</div>
</div>
</div>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    // zTree 的参数配置,深入使用请参考 API 文档(setting 配置详解)
    var setting = {
        data: {
            simpleData: {
                enable: true  //开启简单数据模式
            }
        },
        callback: {
            onClick: onClick
        }
    };
    var zNodes =[
        {id:"1",pId:"0",name:"爷节点1",value:"GZ-NSJMW-X1",isParent:"true",open:"true"},
        {id:"11",pId:"1",name:"父节点1",value:"GZ-NSJMW-X1-T1",isParent:"true",open:"true"},
        {id:"111",pId:"11",name:"孙节点1",value:"GZ-NSJMW-X1-T1-#",isParent:"true",open:"true"},
        {id:"111",pId:"11",name:"孙节点1",value:"024b1dea-be7e-4256-a60d-3dbecda0c3c1",isParent:"true"},
        {id:"112",pId:"11",name:"孙节点1",value:"0510bba1-ce77-42d4-a489-cdf1b5df2a0b",isParent:"true"},
        {id:"113",pId:"11",name:"孙节点1",value:"053b7d4e-bb23-4407-9070-6c5a8c04dba8",isParent:"true"},
        {id:"114",pId:"11",name:"孙节点1",value:"0667422c-2b1c-4fd4-ad92-50096d772dd2",isParent:"true"},
        {id:"115",pId:"11",name:"孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"true"},
        {id:"116",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"117",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"118",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"119",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"120",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"121",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"122",pId:"11",name:"孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"true"},
        {id:"1111",pId:"111",name:"孙孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"false"},
        {id:"1112",pId:"111",name:"孙孙节点1",value:"0510bba1-ce77-42d4-a489-cdf1b5df2a0b",isParent:"false"},
        {id:"1113",pId:"111",name:"孙孙节点1",value:"053b7d4e-bb23-4407-9070-6c5a8c04dba8",isParent:"false"},
        {id:"1114",pId:"111",name:"孙孙节点1",value:"0667422c-2b1c-4fd4-ad92-50096d772dd2",isParent:"false"},
        {id:"1115",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        {id:"1116",pId:"111",name:"孙孙节点1",value:"0b218827-0aec-4469-a519-a091b2b9cceb",isParent:"false"},
        {id:"1117",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        {id:"1118",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        {id:"1119",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        {id:"1120",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"},
        {id:"1121",pId:"111",name:"孙孙节点1",value:"06de9b50-3777-46b8-9ce0-db2e328028c3",isParent:"false"}
    ];
    var code;
    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>"+str+"</li>");
    }

    $(document).ready(function(){
        $.ajax({
            url: "../authorityForProject/getProjectOuterAgency",
            dataType: "json",
            success: function (result) {
                $.fn.zTree.init($("#treeDemo"), setting, result);
                var cId = "${outerUserDTO.agencyIdO}";
                var treeO = $.fn.zTree.getZTreeObj("treeDemo");
                var nodes = treeO.getNodeByParam('id', cId, null);
                treeO.selectNode(nodes);
                treeO.expandNode(nodes, true, false, true);
            }
        });
//        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        showCode();
    });

    function onClick(event, treeId, treeNode, clickFlag) {
        window.location.href = "../authorityForProject/outerProjectUserManage.html?agencyIdO=" + treeNode.id ;
        }

    function checkAllBox(obj){
        var answer= document.getElementsByName("staffIdO");
        if(obj.checked==true){
            for(var i=0;i<answer.length;i++){
                answer[i].checked = true;
            }
        }else{
            for(var i=0;i<answer.length;i++){
                answer[i].checked = false;
            }
        }
    }

    function batchDele() {
        var staffId = document.getElementsByName("staffIdO");
        var checkbox = false;
        var staffIds = '';
        for (var i = 0; i < staffId.length; i++) {
            if (staffId[i].checked) {
                checkbox = true;
                if(staffId[i].value.substring(staffId[i].value.length-1) == "1"){
                    alert("操作失败，无法删除已启用人员！");
                    return false;
                }
                if(i==staffId.length-1){
                    staffIds += staffId[i].value.substring(0,staffId[i].value.length-1);
                }else{
                    staffIds += staffId[i].value.substring(0,staffId[i].value.length-1) + ",";
                }
            }
        }
        if (checkbox) {
            if (confirm("您确认要批量删除已选用户?")) {
                $.ajax({
                    url:"../authorityForProject/batchDeleteProjectUser",
                    type:"POST",
                    async:false,
                    data:{
                        staffIds:staffIds
                    },
                    error:function(){
                        alert("网络异常，可能服务器忙，请刷新重试");
                    },
                    success:function(data){
                        if(data.error == "0"){
                            alert("操作成功！");
                        }else if(data.error == "-1"){
                            alert("操作失败，请联系管理员！");
                        }
                    }
                });
                window.location.reload();
//                window.location.href = "../authorityForProject/outerProjectUserManage.html?agencyIdO=1442cfe2f277454c860b082c9bdf47bc";
            } else {
                return;
            }
        } else {
            alert("请选择要删除的用户");
            return;
        }
    }


    function downloadModel() {
        if (confirm("是否下载模板？")) {
            var href = "../authorityForProject/exportOuterPeopleModel";
            window.location.href = href;
        } else {
            return;
        }
    }

    //删除组织机构
    function delAgency(name,id) {
        var hasUser = $("#hasUser").val();
        if(hasUser != "0"){
            if(name.length > 0 ){
                if (confirm("确定要删除组织机构：\""+name+"\"吗?")) {
                    $.ajax({
                        type: "GET",
                        url: "../authorityForProject/delProjectAgencyById?agencyId="+id,
                        cache: false,
                        async: false,
                        dataType: "json",
                        success: function (json) {
                            var data = json.data;
                            if (data == 'ok') {
                                alert("删除成功！");
                                window.location.href = "../authorityForProject/outerProjectUserManage.html";
                            } else {
                                alert(data);
                            }
                        }
                    });
                }
            }else {
                alert("请选择要删除的组织机构！");
            }
        }else{
            alert("对不起，该组织机构下有人员存在，无法删除！");
        }
    }

    //删除
    function toDelete(id,state) {
        if(state == "0"){
            if (confirm("确定要删除该账号吗?")) {
                $.ajax({
                    type: "GET",
                    url: "../authorityForProject/toDeleteProjectUser?staffIdO=" + id,
                    cache: false,
                    async: false,
                    dataType: "json",
                    success: function (data) {
                        if (data.error == 0) {
                            alert("删除成功！");
                        } else {
                            alert("删除失败！");
                        }
                    }
                });
                window.location.reload();
//                window.location.href = "../authorityForProject/outerProjectUserManage.html?agencyIdO=1442cfe2f277454c860b082c9bdf47bc";
            } else {
                alert("已取消！");
            }
        }else {
            alert("操作失败，无法删除已启用人员！");
        }
    }

    //批量启用
    function batchStartStaff() {
        var staffId = document.getElementsByName("staffIdO");
        var checkbox = false;
        var staffIds = '';
        for (var i = 0; i < staffId.length; i++) {
            if (staffId[i].checked) {
                checkbox = true;
                if(i==staffId.length-1){
                    staffIds += staffId[i].value.substring(0,staffId[i].value.length-1);
                }else{
                    staffIds += staffId[i].value.substring(0,staffId[i].value.length-1) + ",";
                }
            }
        }
        if (checkbox) {
            if (confirm("您确认要批量启用已选用户?")) {
                $.ajax({
                    url:"../authorityForProject/batchHandleProjectOuterStaff",
                    type:"POST",
                    async:false,
                    data:{
                        state:'1',
                        staffIds:staffIds
                    },
                    error:function(){
                        alert("网络异常，可能服务器忙，请刷新重试");
                    },
                    success:function(data){
                        if(data.error == "0"){
                            alert("操作成功！");
                        }else if(data.error == "-1"){
                            alert("操作失败，请联系管理员！");
                        }
                    }
                });
                window.location.reload();
//                window.location.href = "../authorityForProject/outerProjectUserManage.html?agencyIdO=1442cfe2f277454c860b082c9bdf47bc";
            } else {
                return;
            }
        } else {
            alert("请选择要启用的用户");
            return;
        }
    }

    //批量停用
    function batchStopStaff() {
        var staffId = document.getElementsByName("staffIdO");
        var checkbox = false;
        var staffIds = '';
        for (var i = 0; i < staffId.length; i++) {
            if (staffId[i].checked) {
                checkbox = true;
                if (i == staffId.length - 1) {
                    staffIds += staffId[i].value.substring(0,staffId[i].value.length-1);
                } else {
                    staffIds += staffId[i].value.substring(0,staffId[i].value.length-1) + ",";
                }
            }
        }
        if (checkbox) {
            if (confirm("您确认要批量停用已选用户?")) {
                $.ajax({
                    url: "../authorityForProject/batchHandleProjectOuterStaff",
                    type: "POST",
                    async: false,
                    data: {
                        state: '0',
//                        staffIds:'12315193219321',
                        staffIds: staffIds
                    },
                    error: function () {
                        alert("网络异常，可能服务器忙，请刷新重试");
                    },
                    success: function (data) {
                        if (data.error == "0") {
                            alert("操作成功！");
                        } else if (data.error == "-1") {
                            alert("操作失败，请联系管理员！");
                        }
                    }
                });
                window.location.reload();
//                window.location.href = "../authorityForProject/outerProjectUserManage.html?agencyIdO=1442cfe2f277454c860b082c9bdf47bc";
            } else {
                return;
            }
        } else {
            alert("请选择要停用的用户");
            return;
        }
    }
    
    function toEdit(staffId) {
        window.location.href = "../authorityForProject/toEditOuterUser.html?staffIdO="+staffId ;
    }

    function toHandleStaff(staffId,state) {
        if(state == "1"){
            if (confirm("您确认要停用该用户?")) {
                $.ajax({
                    url: "../authorityForProject/batchHandleProjectOuterStaff",
                    type: "POST",
                    async: false,
                    data: {
                        state: '0',
                        staffIds: staffId
                    },
                    error: function () {
                        alert("网络异常，可能服务器忙，请刷新重试");
                    },
                    success: function (data) {
                        if (data.error == "0") {
                            alert("操作成功！");
                        } else if (data.error == "-1") {
                            alert("操作失败，请联系管理员！");
                        }
                    }
                });
                window.location.reload();
//                window.location.href = "../authorityForProject/outerProjectUserManage.html?agencyIdO=1442cfe2f277454c860b082c9bdf47bc";
            } else {
                return;
            }
        }else {
            if (confirm("您确认要启用该用户?")) {
                $.ajax({
                    url: "../authorityForProject/batchHandleProjectOuterStaff",
                    type: "POST",
                    async: false,
                    data: {
                        state: '1',
                        staffIds: staffId
                    },
                    error: function () {
                        alert("网络异常，可能服务器忙，请刷新重试");
                    },
                    success: function (data) {
                        if (data.error == "0") {
                            alert("操作成功！");
                        } else if (data.error == "-1") {
                            alert("操作失败，请联系管理员！");
                        }
                    }
                });
                window.location.reload();
//                window.location.href = "../authorityForProject/outerProjectUserManage.html?agencyIdO=1442cfe2f277454c860b082c9bdf47bc";
            } else {
                return;
            }
        }
    }

    function test() {
        $("#myfile").click();
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
                if (confirm("您确认要添加'"+excelPath+"'?")) {
                    document.getElementById("frm").action = "../authorityForProject/excelAddOuterPeople";
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

    function isExcel(){
        var size = $("#size").val();
        if(size>0){
            var href = "../authorityForProject/outerUserExportExcel?agencyNameO=${outerUserDTO.agencyNameO}&staffNameO=${outerUserDTO.staffNameO}&sysNameO=${outerUserDTO.sysNameO}&stateO=${outerUserDTO.stateO}&mobileO=${outerUserDTO.mobileO}&emailO=${outerUserDTO.emailO}&agencyIdO=${outerUserDTO.agencyIdO}";
            window.location.href = href;
        }else{
            alert("没有可以导出的数据");
        }
    }

</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
</body>
</html>