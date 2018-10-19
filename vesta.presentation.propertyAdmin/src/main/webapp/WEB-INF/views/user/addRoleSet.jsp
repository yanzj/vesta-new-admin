<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/5/10
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
    <script>
        $(function () {
            console.log("sqq")
            $("#001300040000").addClass("active");
            $("#001300040000").parent().parent().addClass("in");
            $("#001300040000").parent().parent().parent().parent().addClass("active");
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
    <script type="text/javascript">
        $(function () {
            $("#addAppRoleSet").validate({
                rules: {
                    appRoleSetName: {
                        required: true,
                        minlength: 1,
                        maxlength: 50
                    }
                },
                messages: {
                    appRoleSetName: {
                        required: "请输入角色名！",
                        minlength: "不能少于1个字符！",
                        maxlength: "请勿超过50个字！"
                    }
                }
            })
        })
    </script>
    <style>
        label.error {
            margin-left: 1%;
            color: red;
        }
    </style>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="001300040000" username="${propertystaff.staffName}"/>

    <div class="container1 userStaffManage">
        <form class="form-horizontal" name="addAppRoleSet" id="addAppRoleSet" action="../user/addRole.html">
            <div class="row">
                <div class="col-md-10 role_new_submit">
                    <div class="newRoleSubmit">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <%--<a href="" class="btn btn-primary" onclick="javaScript:history.go(-1)" for="">关闭</a>--%>
                        <a href="../user/roleManage.html" class="btn btn-primary" >关闭</a>
                    </div>
                </div>
            </div>
            <input type="hidden" name="appRoleSetId" value="${roleSetDTO.appRoleSetId}">

            <div class="row">
                <div class="col-md-10 role_new_submit2">
                    <table class="table table-bordered">
                        <caption class="role_table_title">角色编辑</caption>
                        <%--<input type="hidden" name="staffId" value="${userStaffDTO.cnStaffId}">--%>
                        <tbody>
                        <tr>
                            <td class="role_table_roleName">角色名称</td>
                            <td class="role_table_fillCont">
                                <input type="text" class="roleNameText" id="appRoleSetName" name="appRoleSetName"
                                       value="${roleSetDTO.appRoleSetName}">
                            </td>
                        </tr>

                        <tr>
                            <td class="role_table_roleName">关联的组织机构或人</td>
                            <td class="role_table_fillCont">
                                <input type="hidden" id="agencys" name="agencys">
                                <input type="hidden" id="staffs" name="staffs">
                                <span class="selectResult">
                                <c:forEach items="${agencyList}" var="agencys">
                                    ${agencys.agencyName};&nbsp;
                                </c:forEach>
                                    <br/>
                                 <c:forEach items="${staffList}" var="staffs">
                                     ${staffs.staffName};&nbsp;
                                 </c:forEach>
                            </span>
                                <span data-toggle="modal" data-target="#myModal" class="role_select2">选择</span>
                            </td>
                        </tr>

                        <tr style="background-color: #F2F2F2">
                            <td colspan="2">质量管理功能模块相关信息</td>
                        </tr>
                        <c:forEach var="list" items="${roleMenuList}" varStatus="row">
                            <tr>
                                <td class="role_table_roleName">${list.appRoleName}</td>
                                <td>
                                    <c:forEach var="item" items="${list.appMenuDTOList}">
                                        <input type="checkbox" value="${list.appRoleId},${item.menuId}" name="appRoleId"
                                               <c:forEach var="compar" items="${roleList}">
                                               <c:if test="${fn:substring(compar.appRoleId,0 ,fn:length(list.appRoleId) ) eq list.appRoleId && fn:substring(compar.appRoleId, fn:length(list.appRoleId),fn:length(item.menuId)+fn:length(list.appRoleId) ) eq item.menuId}">checked="checked" </c:if>
                                        </c:forEach> />
                                        ${item.menuName}&nbsp;
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
</div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row"  style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList"  style="padding-left: 0;">
                                <div class="typeList_department" style="color: #fff;background: #0D165E">部门</div>
                                <div class="typeList_employees">员工</div>
                            </div>

                        </div>
                        <!-- <div class="row"> -->
                        <!-- <div class="col-md-5 addPeople">
                         <input type="text"  class="serach_people_key"placeholder="关键字添加员工" value="">
                         <button type="submit" class="serach_people">搜索</button>
                     </div> -->
                        <!--  </div> -->
                        <div class="row ">
                            <div class="content_wrap col-md-3">
                                <div class="zTreeDemoBackground left">
                                    <ul id="treeDemo" class="ztree"></ul>
                                </div>
                                <div class="people_list"></div>
                            </div>
                            <div class="col-md-5 addPeople" style="left: 7%; display: block;">
                                <input type="text" class="serach_people_key" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people">搜索</button>
                            </div>
                            <div class="col-md-3 department waitSelect">
                                <h5>待选列表</h5>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <select id="left" multiple="multiple">
                                        </select>
                                    </tr>
                                    </tbody>
                                </table>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnR"  style="background: #0D165E">添加></button>
                                </div>

                            </div>

                            <div class="col-md-3 department ">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <select id="right" multiple="multiple">
                                                <c:forEach items="${agencyList}" var="agency">
                                                    <option class="htmlDepartment"
                                                            value="${agency.agencyId}">${agency.agencyName}</option>
                                                </c:forEach>
                                                <c:forEach items="${staffList}" var="staff">
                                                    <option class="htmlPerson"
                                                            value="${staff.staffId}">${staff.staffName}</option>
                                                </c:forEach>
                                            </select>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <div class=" col-md-9 moveBtn5" style="margin-left: 20%;">
                                    <button id="btnD"><移除</button>
                                </div>
                            </div>
                        </div>
                        <div class="btnB"></div>
                        <div class=" col-md-offset-5" style="display: block;margin-top: 30px;">
                            <button type="submit" class="confirm" class="close" data-dismiss="modal"
                                    aria-hidden="true">确认
                            </button>
                            <button class="cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

<script type="text/javascript">

    //从后台拿到右边的数据
    var selectedData = [];
    $("#right option.htmlDepartment").each(function (i) {
        if (this.value != "") {
            selectedData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'))
        }
    });
    $("#right option.htmlPerson").each(function (i) {
        if (this.value != "") {
            selectedData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'))
        }
    });
    arrData2select(selectedData, '#right');
    var temp5 = [];
    var temp6 = [];
    var j, k;
    j = k = 0;
    for (var i = 0; i < selectedData.length; i++) {
        if (selectedData[i].type == '1') {
            temp6[j] = selectedData[i].id;
            j++;
        } else {
            temp5[k] = selectedData[i].id;
            k++;
        }
    }
    $('#staffs').val(temp5.join());
    $('#agencys').val(temp6.join());
    // 点击部门
    var data2 = [];
    $('.typeList_department').bind('click', function () {
        $('.zTreeDemoBackground').css('display', 'block');
        // alert(11);
        $('#treeDemo').css('display', 'block');
        $('.department').css('margin-top', '-2.95%');
        $('.addPeople').css('display', 'none');
        $('.moveBtn').css('margin-top', '3.1%');
        $('.typeList_department').css({'background':'#0D165E','color':'#fff'});
        $('.typeList_employees').css({'background':'#fff','color':'#0D165E'});
        $('.content_wrap .resultPeople').css('display', 'none');
        $('#left').html('');
    });
    //点击人员
    $('.typeList_employees').bind('click', function () {
        $('.addPeople').css('display', 'block');
        $('.department').css('margin-top', '-2.95%');
        // $('.waitSelect').css('margin-left','4%');
        $('.zTreeDemoBackground').css('display', 'none');
        $('.moveBtn').css('margin-top', '-1.54%');
        $('.typeList_employees').css({'background':'#0D165E','color':'#fff'});
        $('.typeList_department').css({'background':'#fff','color':'#0D165E'});
        $('#left').html('');

    });
    // 查询
    $('.serach_people').bind('click', function () {
        var serachPeople = $('.serach_people_key').val();
        $.ajax({
            url: '/user/searchStaff',
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
                    arrData2select(data, '#left');
                }
            }
        });

    });
    function selectFilter(obj) {
        return {
            agencyType: "1",
            icon: null,
            id: obj.staffId,
            isParent: false,
            name: obj.staffName,
            pId: null,
            type: "3"
        }
    }
    var selectedList = []
    var waitLists = {};
    var setting = {
        async: {
            enable: true,
            url: "/agency/initAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter,
        },
        callback: {
            onClick: onClick
        }
    };
    function strToJson(str) {
        var json = (new Function("return " + str))();
        return json;
    }
    function onClick(event, treeId, treeNode, clickFlag) {
        var childrenArr;
        var leftId = '#left'
        if (!waitLists['id' + treeNode.id]) {
            //是最后一个  找后台要下一级
//            if (treeNode.isParent) {
            $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
                // console.log(res)
                childrenArr = res.data;
                arrData2select(childrenArr, leftId);
            })
//            }else{
//                arrData2select([],leftId);
//            }
        } else {
            //找下一级
            childrenArr = waitLists[('id' + treeNode.id)];
            arrData2select(childrenArr, leftId);
        }

    }

    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) {
            return null;
        }
        var arr = [];
        for (var i = 0, h = childNodes.length; i < h; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            //arr.push  下一级的 name  id
            arr.push({name: childNodes[i].name, id: childNodes[i].id, type: childNodes[i].type})
        }
        if (parentNode) {
            waitLists['id' + parentNode.id] = arr;
        } else {
            waitLists['idRoot'] = arr;
        }
        // console.log(waitLists)
        return childNodes;

    }

    //方法
    function arrData2select(arr, id) {
        //arrData2select(arr,'#right')
        //arr相当于代表的是左侧已经选中的数据 id 代表的是右侧的数据
        if (id == '#right' && arr != selectedData) {
            arr = selectedData.concat(arr)
            selectedData = arr;
        }
        ;
        var htmlPersonNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson" ">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment" ">' + '</optgroup>';
        for (var i = 0; i < arr.length; i++) {
            // 比较两个id看是否重复
            var isContains = contains(selectedData.map(function (res) {
                return res.id
            }), arr[i].id);
            if (isContains && id == '#left') {
                continue;
            }
            ;
            if (arr[i].type == '1') {
                htmlDepartmentNum++;
                htmlDepartment = htmlDepartment + '<option class="htmlDepartment" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>';
            } else {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '"">' + arr[i].name + '</option>'
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        $(id).html('');
        $(id).append(htmlDepartment).append(htmlPerson);
    }

    function select() {

        $('#btnR').bind('click', function () {
            wait2selected();
        });
        $('#btnD').bind('click', function () {
            selected2wait();
        });
    }
    //右移方法
    function wait2selected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left option:selected');
        // console.log(optionArr);
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') {
                className = '3';
            }
            //将数组中optionArr存放的数据全部遍历出来 添加到另一个空数组中arr

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}

        }
        ;
        //移除之前选中的那个数组
        optionArr.remove();
        arrData2select(arr, '#right');
    }
    //左移方法
    function selected2wait() {
        var optionArr = $('#right option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson')className = '3';

            var id = '';
            if (optionArr[i].dataset) {
                id = optionArr[i].dataset.id;
            } else {
                id =optionArr[i].attributes[1].value;
            }
            arr[i] = {name: optionArr[i].value, id: id, type: className}
        }
        ;

        optionArr.remove();
        // selectedData
        selectedData = arrSubtraction(selectedData, arr)
        $('#right #htmlPerson')[0].label = "员工(" + $('#right').find('.htmlPerson').length + ")";
        $('#right #htmlDepartment')[0].label = "部门(" + $('#right').find('.htmlDepartment').length + ")";
        var optionArrLeft = $('#left option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = true;
            if (optionArrLeft[i].className == 'htmlPerson')className = false;

            var id = '';
            if (optionArrLeft[i].dataset) {
                id = optionArrLeft[i].dataset.id;
            } else {
                id =optionArrLeft[i].attributes[1].value;
            }
            arrLeft[i] = {name: optionArrLeft[i].value, id: id, type: className}
        }
        ;
        arr = arrLeft.concat(arr)
        arrData2select(arr, '#left')
    }
    select();
    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting);
    });
    function arrSubtraction(arr1, arr2) {
        var temp = [];
        var num = 0;
        for (var i = 0; i < arr1.length; i++) {
            //arr2.map(function(res){return res.id;}数组列表id 和arr1[i].id进行对比
            var isContains = contains(arr2.map(function (res) {
                return res.id;
            }), arr1[i].id)
            //作为对比 如果不含有就把它 赋给ta
            if (!isContains) {
                temp[num] = arr1[i];
                num++;
            }
        }
        ;
        return temp;
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
        $('.selectResult').html(liShow);
        $('.selectResult').find('option').addClass('floatSelectResult');
        $('#htmlDepartment').css('display', 'none');
        $('#htmlPerson').css('display', 'none');
        var tstaff = [];
        var tagency = [];
        var i = j = 0;
        $("#right option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else {
                tstaff[j] = $(this).attr("data-id");
                j++;
            }
        });
        $('#staffs').val(tstaff.join());
        $('#agencys').val(tagency.join());
    });
</script>
</body>
</html>
