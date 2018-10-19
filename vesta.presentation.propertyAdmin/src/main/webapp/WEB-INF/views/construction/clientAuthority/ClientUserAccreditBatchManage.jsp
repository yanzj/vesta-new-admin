<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2017/12/21
  Time: 11:00
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
            $("#007300050000").addClass("active");
            $("#007300050000").parent().parent().addClass("in");
            $("#007300050000").parent().parent().parent().parent().addClass("active");
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

        .typeList .typeList_project, .typeList .typeList_department, .typeList .typeList_employees, .typeList .typeList_area{
            text-align: center;
            border: 1px solid #ccc;
            border-bottom: none;
            cursor: pointer;
            width: 60px;
            float: left;
        }
        .addPeople{
            position: absolute;margin-left: 0px;top: 6.3rem;width: 50%;display: block;padding: 0;display: none;
        }
        .addPeople2{
            position: absolute;margin-left: 0;top: 12.3rem;left: 1.2rem;width: 30%;
            display: none;
        }
        .submitBtn{
            text-align: center;
            margin-top: 8px;
            margin-bottom: 20px;
        }
    </style>
    <%-- zTree --%>
    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exhide-3.5.js"></script>
    <!--进度条-->
    <script src="../static/js/jquery.step.min.js"></script>
    <link href="../static/css/jquery.step.css" type="text/css" rel="stylesheet">
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007300050000" username="${authPropertystaff.staffName}"/>
</div>
<div class="modal-dialog">
    <div class="table-responsive">
        <div class="modal-body"     style="height: auto; min-height: 650px;">
            <div id="step"></div>
            <div class="container" style="width: 420px; float: left">
                <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                    <div class="col-md-12 typeList" style="padding-left: 0">
                        <div class="typeList_area" style="color: #fff;background: #0D165E">项目层级</div>
                        <div class="typeList_project">角色</div>
                    </div>

                </div>
                <div class="row ">
                    <div class="content_wrap2 col-md-6" style="margin:0;padding: 0;">
                        <div class="zTreeDemoBackground2 left" style="width:190px;height: 200px;overflow: scroll;">
                            <ul id="treeDemo2" class="ztree"></ul>
                        </div>
                        <div class="people_list2"></div>
                        <div class="group_list2"></div>
                    </div>
                    <div class="col-md-5 addPeople2" >
                        <input type="text" class="serach_project_key" placeholder="关键字添加角色" value="">
                        <button type="submit" class="serach_project">搜索</button>
                    </div>
                    <div class="col-md-6 department2 waitSelect">
                        <h5>待选列表</h5>
                        <table class="table">
                            <tbody>
                            <tr>
                                <select id="left2"   size="5" style="width:150px;height: 180px;overflow: scroll;">
                                </select>
                            </tr>
                            </tbody>
                        </table>
                        <div class="btnB"></div>
                    </div>
                </div>
            </div>
            <div class="containerSelect1" style="width: 430px;float: right;position: relative;">
                <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                    <div class="col-md-4 typeList" style="padding-left: 0">
                        <div class="typeList_department" style="color: #fff;background: #0D165E">部门</div>
                        <div class="typeList_employees">员工</div>
                    </div>

                </div>
                <div class="row ">
                    <div class="content_wrap col-md-6" style="padding: 0;">
                        <div class="zTreeDemoBackground left" style="width:190px;height: 200px;overflow: scroll;">
                            <ul id="treeDemo" class="ztree"></ul>
                        </div>
                        <div class="people_list"></div>
                        <div class="group_list"></div>
                    </div>
                    <div class="col-md-5 addPeople" >
                        <input type="text" class="serach_people_key" placeholder="关键字添加员工" value="">
                        <button type="submit" class="serach_people">搜索</button>
                    </div>
                    <div class="col-md-6 department waitSelect">
                        <h5>待选列表</h5>
                        <table class="table">
                            <tbody>
                            <tr>
                                <select id="left" multiple="multiple"  size="5" style="width: 150px;height: 180px;overflow: scroll;">
                                </select>
                            </tr>
                            </tbody>
                        </table>
                        <div class="btnB"></div>
                    </div>
                </div>
            </div>
            <div class=" col-md-12 moveBtn" style="margin-top: 3.1%;text-align: center;">
                <button id="btnR" class="  col-md-2 btn btn-primary" style="float: none;">授权</button>
            </div>
            <form class="submitForm" id="form2" name="form2" method="post">
                <div class="form-group col-lg-12" style="margin-top: 10px;max-height: 150px;overflow: scroll;height: 150px;">
                    <table class="tableStyle  col-md-12" id="tab1">
                        <thead>
                        <tr>
                            <th>项目层级</th>
                            <th>角色</th>
                            <th>人员</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody class="publicTbody" id="tb">
                        </tbody>
                    </table>
                </div>
            </form>
            <div  class=" col-md-12 " style= "text-align: center">
                <button type="button" onclick="subFrom()" id="sbm2"  for="propertySearch"  class="  col-md-2 btn btn-primary" style= " float: none;">保存</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">

    var $step = $("#step");
    var $index = $("#index");
    $step.step({
        index: 0,
        time: 500,
        title: ["开始","选择角色", "选择用户", "授权", "保存"]
    });
    $index.text($step.getIndex()+1);
    $("#left2").on("click", function() {
        $step.toStep(1);
        $index.text($step.getIndex()+1);
    });

    $("#left").on("click", function() {
        $step.toStep(2);
        $index.text($step.getIndex()+1);
    });


    //从后台拿到右边的数据
    var selectedData = [] ,selectedData2 = [];
    var sview = [],sview2 = [] ;
    $("#right option.htmlPerson").each(function (i) {
        if (this.value != "") {
            selectedData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            sview[i] = this.value;
        }
    });
    $("#right2 option.htmlPerson2").each(function (i) {
        if (this.value != "") {
            selectedData2.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 2 + '}'));
            sview2[i] = this.value;
        }
    });
    arrData1select(selectedData, '#right');
    $('#authStaffs').val(sview.join());
    arrData2select(selectedData2, '#right2');
    $('#authRoles').val(sview2.join());

    // 点击部门
    var data2 = [];
    $('.typeList_department').bind('click', function () {
        $('.group_list').css('display', 'none');
        $('.zTreeDemoBackground').css('display', 'block');
        $('#treeDemo').css('display', 'block');
        $('.department').css('margin-top', '-2.95%');
        $('.addPeople').css('display', 'none');
        $('.moveBtn').css('margin-top', '3.1%');
        $('.content_wrap .resultPeople').css('display', 'none');
        $('.typeList_department').css({'background':'#0D165E','color':'#fff'});
        $('.typeList_employees').css({'background':'#fff','color':'#0D165E'});
        $('#left').html('');
    });
    //点击人员
    $('.typeList_employees').bind('click', function () {
        $('.group_list').css('display', 'none');
        $('.addPeople').css('display', 'block');
        $('.department').css('margin-top', ' -2.95%');
        $('.zTreeDemoBackground').css('display', 'none');
        $('.moveBtn').css('margin-top', '3.1%');
        $('.typeList_employees').css({'background':'#0D165E','color':'#fff'});
        $('.typeList_department').css({'background':'#fff','color':'#0D165E'});
        $('#left').html('');
    });

    //点击项目层级
    $('.typeList_area').bind('click', function () {
        $('.group_list2').css('display', 'none');
        $('.zTreeDemoBackground2').css('display', 'block');
        $('#treeDemo2').css('display', 'block');
        $('.department2').css('margin-top', '-2.95%');
        $('.addPeople2').css('display', 'none');
        $('.moveBtn2').css('margin-top', '3.1%');
        $('.content_wrap2 .resultPeople2').css('display', 'none');
        $('.typeList_area').css({'background':'#0D165E','color':'#fff'});
        $('.typeList_project').css({'background':'#fff','color':'#0D165E'});
        $('#left2').html('');
    });
    //点击角色
    $('.typeList_project').bind('click', function () {
        $('.group_list2').css('display', 'none');
        $('.addPeople2').css('display', 'block');
        $('.department2').css('margin-top', ' -2.95%');
        $('.zTreeDemoBackground2').css('display', 'none');
        $('.moveBtn2').css('margin-top', '3.1%');
        $('.typeList_project').css({'background':'#0D165E','color':'#fff'});
        $('.typeList_area').css({'background':'#fff','color':'#0D165E'});
        $('#left2').html('');
    });
    // 查询人员
    $('.serach_people').bind('click', function () {
        var serachPeople = $('.serach_people_key').val();
        $.ajax({
            url: '/clientAccredit/getUserByName',
            data: {
                staffName: serachPeople
            },
            type: 'get',
            dataType: 'json',
            success: function (result) {
                if (result.code != 0) {
                    alert(result.msg)
                } else {
                    var data = result.data.map(function (val) {
                        return selectFilter(val);
                    })
                    console.log(data);
                    arrData1select(data, '#left');
                }
            }
        });

    });

    //查询角色
    $('.serach_project').bind('click', function () {
        var serachProject = $('.serach_project_key').val();
        $.ajax({
            url: '/clientAccredit/getRoleByNameAndProjectId',
            data: {
                roleName: serachProject
            },
            type: 'get',
            dataType: 'json',
            success: function (result) {
                if (result.code != 0) {
                    alert(result.msg)
                } else {
                    var data = result.data.map(function (val) {
                        return selectFilter1(val);
                    })
                    console.log(data);
                    arrData2select(data, '#left2');
                }
            }
        });

    });
    function selectFilter(obj) {
        return {
            agencyType: "1",
            icon: null,
            id: obj.outerStaffId,
            isParent: false,
            name: obj.outerStaffName,
            pId: null,
            type: "3"
        }
    }
    function selectFilter1(obj) {
        return {
            agencyType: "1",
            icon: null,
            id: obj.outerStaffId,
            isParent: false,
            name: obj.outerStaffName,
            pId: null,
            type: "2"
        }
    }
    //方法
    function arrData1select(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right' && arr != selectedData) {
            arr = selectedData.concat(arr)
            selectedData = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlPerson = ')" id="htmlPerson">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left') {
                continue;
            }
            ;
            if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        $(id).html('');
        $(id).append(htmlPerson);
    }
    //方法2
    function arrData2select(arr, id) {
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right2' && arr != selectedData2) {
            arr = selectedData2.concat(arr)
            selectedData2 = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlPerson2 = ')" id="htmlPerson2">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData2.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left2') {
                continue;
            }
            ;
            if (arr[i].type == '2') {
                htmlPersonNum++;
                htmlPerson2 = htmlPerson2 + '<option class="htmlPerson2" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
            }

        }
        ;
        htmlPerson2 = '<optgroup label="角色(' + htmlPersonNum + htmlPerson2;
        $(id).html('');
        $(id).append(htmlPerson2);
    }

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
    var setting2 = {
        data: {
            simpleData: {
                enable: true  //开启简单数据模式
            }
        },
        callback: {
            onClick: onClick2
        }
    };
    function select() {
        $('#btnR').bind('click', function () {
            wait1selected()
        });
        $('#btnR2').bind('click', function () {
            wait2selected();
        });
    }
    //右移方法1
    function wait1selected() {
        var arrs = [];
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            //将数组中optionArr存放的数据全部遍历出来 添加到另一个空数组中arr
            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id}
        }
        ;
//        //移除之前选中的那个数组
//        optionArr.remove();
//        arrData1select(arr, '#right');

        var optionArr2 = $('#left2 option:selected');
        var arr2 = [];
        var arr3 = [];
        for (var i = 0; i < optionArr2.length; i++) {
            //将数组中optionArr存放的数据全部遍历出来 添加到另一个空数组中arr
            var id2 = '';
            if (optionArr2[i].dataset) {
                id2 = optionArr2[i].dataset.id;
            } else {
                id2 =optionArr2[i].attributes[1].value;
            }
            var name1 = optionArr2[i].value.split(" （")[0];
            var name2 = optionArr2[i].value.split(" （")[1];
            arr2[i] = {name: name1, id: id2.split("|")[0]}
            arr3[i] = {name: name2.substring(0,name2.length  - 1), id: id2.split("|")[1]}
        }
        arrs = {staff: arr, roles: arr2 , pro: arr3}
        add_tr(arrs);
//        //移除之前选中的那个数组
//        optionArr2.remove();
//        arrData2select(arr2, '#right2');
    }
    select();

    $(document).ready(function(){
        $.ajax({
            url: "../clientAccredit/getOAAgencyMessage",
            dataType: "json",
            async: false,
            cache: false,
            success: function (result) {
                $.fn.zTree.init($("#treeDemo"), setting, result);
            }
        });
        $.ajax({
            url: "../clientAccredit/getAllAgencyManageById",
            dataType: "json",
            async: false,
            cache: false,
            success: function (date) {
                $.fn.zTree.init($("#treeDemo2"), setting2, date);
//                $.fn.zTree.init($("#treeDemo"), setting,result);
            }
        });
    });
    function onClick(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left'
        //是最后一个  找后台要下一级
        $.getJSON('/clientAccredit/getClientUserByAgencyId?id=' + treeNode.id , function (res) {
            childrenArr = res.data;
            arrData1select(childrenArr, leftId);
        })
    }
    function onClick2(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left2'
        //是最后一个  找后台要下一级
        $.getJSON('/clientAccredit/getClientRoleByAgencyId?id=' + treeNode.id , function (res) {
            childrenArr = res.data;
            arrData2select(childrenArr, leftId);

        })
    }
    //arr, obj看数组中是否含有对象obj可以理解成去重
    function contains(arr, obj) {
        var i = arr.length;
        while (i--) {
            if (arr[i] === obj) {
                return true;
            }
        }
        return false;
    }
    ;
    // 提交选择1
    $('.confirm').bind('click', function () {
        var liShow = $('#right').html();
        $('.selectStaff').html(liShow);
        $('.selectStaff').find('option').addClass('floatSelectResult');
        $('#htmlPerson').css('display', 'none');
        var tstaff = [], tagency = [];
        var i = k = 0;
        $("#right option").each(function () {
            if ($(this).attr("class") == 'htmlPerson') {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#authStaffs').val(tstaff.join());
    });
    // 提交选择2
    $('.confirm2').bind('click', function () {
        var liShow = $('#right2').html();
        $('.selectRole').html(liShow);
        $('.selectRole').find('option').addClass('floatSelectResult');
        $('#htmlPerson2').css('display', 'none');
        var tstaff = [], tagency = [];
        var i = k = 0;
        $("#right2 option").each(function () {
            if ($(this).attr("class") == 'htmlPerson2') {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#authRoles').val(tstaff.join());
    });
    var trLength = 0;
    function add_tr(arrs) {
        var proName = '';
        var proId= '';
        var rName = '';
        var rId = '';
        var uName = '';
        var uId = '';
        if(arrs.pro.length && arrs.roles.length && arrs.staff.length){
            for (var i = 0; i < arrs.pro.length; i++) {
                if(i==arrs.pro.length-1){
                    proName += arrs.pro[i].name;
                    proId += arrs.pro[i].id;
                }else{
                    proName += arrs.pro[i].name + ",";
                    proId += arrs.pro[i].id + ",";
                }
            }
            for (var i = 0; i < arrs.roles.length; i++) {
                if(i==arrs.roles.length-1){
                    rName += arrs.roles[i].name;
                    rId += arrs.roles[i].id;
                }else{
                    rName += arrs.roles[i].name + ",";
                    rId += arrs.roles[i].id + ",";
                }
            }
            for (var i = 0; i < arrs.staff.length; i++) {
                if(i==arrs.staff.length-1){
                    uName += arrs.staff[i].name;
                    uId += arrs.staff[i].id;
                }else{
                    uName += arrs.staff[i].name + ",";
                    uId += arrs.staff[i].id + ",";
                }
            }
            var trObj = document.createElement("tr");
            trObj.innerHTML = "<td>"+proName+"</td><td>"+rName+"</td><td>"+uName+"</td><td><input type='hidden' name='userProRole["+trLength+"].proId' id='proId' value='"+proId+"'>" +
                "<input type='hidden' name='userProRole["+trLength+"].rId' id='rId' value='"+rId+"'><input type='hidden' name='userProRole["+trLength+"].uId' id='uId' value='"+uId+"'><input type='button' class='btn btn-primary' value='删除' id='deleteTable' onclick='del_tr(this)'></td>";
            document.getElementById("tb").appendChild(trObj);
            trLength ++;
            $step.toStep(3);
            $index.text($step.getIndex()+1);
        }else {
            alert("请至少选择一个角色或人员！");
        }

    }
    function del_tr(obj) {
//        if($("#tab1 tr").length>2){
//            $(obj).parent().parent().remove();
//        }else {
//            alert("至少保留一行");
//        }
        $(obj).parent().parent().remove();
    }

    function subFrom(){
        if($("#tab1 tr").length<2){
            alert("没有可授权的数据！");
        }else {
//            document.getElementById("form2").submit();

            $.ajax({
                url: "../clientAccredit/saveUserRoleRelation_2",            //目标网址
                type: "post",
                async: false,
                dataType: "json",
                data: $("#form2").serialize(),
                success: function (json) {
                    <!-- 获取返回代码 -->
                    var code = json.code;
                    if (code != 0) {
                        var errorMessage = json.msg;
                        alert(errorMessage);
                    } else {
                        <!-- 获取返回数据 -->
                        var data = json.data;
                        if (data == 'ok') {
                            alert("保存成功，数据详细请到用户查询管理页面查看。");
                            window.location.href = '../clientAccredit/clientStaffAccreditBatch.html';
                        } else {
                            alert(data);
                        }
                    }
                }
            });

            $step.toStep(4);
            $index.text($step.getIndex()+1);
            $("#step").remove();
        }
    }

</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
</body>
</html>
