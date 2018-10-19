<%--
  设置区域权限
  Created by IntelliJ IDEA.
  User: Jason
  Date: 2017/6/6
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

        .col-md-offset-5 button {
            height: 2.5rem;
            line-height: 2.5rem;
            padding: 0 12px;
            color: #fff;
            background-color: #286090;
            border-color: #204d74;
        }

        .typeList {
            position: relative;
        }

        div.addPeople {
            position: absolute;
            margin-left: 0;
            top: 6.3rem;
            left: 1.2rem;
        }

        div.addPeople input {
            width: 15rem;
        }

        div.btnB {
            width: 46.4rem;
            height: 1px;
            border-top: 1px dashed #ccc;
            background-color: white;
            margin-top: 30px;
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
        $(function () {
            $("#006300010000").addClass("active");
            $("#006300010000").parent().parent().addClass("in");
            $("#006300010000").parent().parent().parent().parent().addClass("active");
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
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006300010000" username="${authPropertystaff.staffName}"/>
    <div class="container1 userStaffManage">
        <form class="form-horizontal" name="projectInfo" id="projectInfo"
              action="../securityProject/saveAreaRole.html">
            <input type="hidden" name="groupId" value="${groupId}">
            <div class="row">
                <div class="col-md-10 " style=" border-bottom: 1px dashed #ccc;">
                    <%--<span style="line-height: 30px;font-size: 15px;">编辑区域</span>--%>

                    <div class="newRoleSubmit">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <button onclick="history.go(-1)" class="btn btn-primary" for="">关闭</button>
                    </div>
                </div>
            </div>
            <div style="line-height: 30px;margin-top: 15px;">
                <span class="role_table_roleName">集团名称：</span>
                <span class="role_table_fillCont">
                    <input type="text" class="roleNameText" name="areaName" autofocus="autofocus"
                           value="${securityAllRoleDTO.name}">
                </span>
            </div>
            <div class="row">
                <div class="col-md-10 role_new_submit2">
                    <table class="table table-bordered">
                        <input type="hidden" name="areaId" value="${securityAllRoleDTO.id}">
                        <tbody>
                        <tr style="background-color: #003366;color: #fff;">
                            <td colspan="2">安全区域管理功能权限相关信息</td>
                        </tr>
                        <tr>
                            <td class="role_table_roleName"><span>HSE部门</span></td>
                            <td>
                                <input type="hidden" name="securityOfficerDep" id="viewAgencys">
                                <input type="hidden" name="securityOfficerStaff" id="viewStaffs">
                                <div class="existence">
                                          <span style="display:inline-block" class="selectResult" id="role_linkRole">
                                            <c:if test="${(securityAllRoleDTO.securityOfficerDep) != null && fn:length(securityAllRoleDTO.securityOfficerDep)>0}">
                                                <c:forEach items="${securityAllRoleDTO.securityOfficerDep}" var="dept">
                                                    ${dept.name}；
                                                </c:forEach>
                                                <br/>
                                            </c:if>
                                              <c:if test="${securityAllRoleDTO.securityOfficerStaff != null && fn:length(securityAllRoleDTO.securityOfficerStaff)>0}">
                                                  <c:forEach items="${securityAllRoleDTO.securityOfficerStaff}"
                                                             var="staff">
                                                      ${staff.name}；
                                                  </c:forEach>
                                              </c:if>
                                          </span>
                                </div>
                                <span class="role_select" data-toggle="modal" data-target="#myModal1">选择</span>
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
    $().ready(function () {
        $("#projectInfo").validate({
            rules: {
                areaName: {
                    required: true,
                    minlength: 1,
                    maxlength: 20
                }
            },
            messages: {
                areaName: {
                    required: "请输入区域名称！",
                    minlength: "不能少于1个字符！",
                    maxlength: "请勿超过20个字！"
                },
            }
        })
    })
</script>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>

<!-- 模态框1（Modal）-->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="containerSelect1">
                        <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 40px;">
                            <div class="col-md-4 typeList" style="padding-left: 0">
                                <div class="typeList_department">部门</div>
                                <div class="typeList_employees">员工</div>
                            </div>
                            <div class="col-md-5 addPeople">
                                <input type="text" class="serach_people_key" placeholder="关键字添加员工" value="">
                                <button type="submit" class="serach_people">搜索</button>
                            </div>
                        </div>
                        <div class="row ">
                            <div class="content_wrap col-md-3">
                                <div class="zTreeDemoBackground left">
                                    <ul id="treeDemo" class="ztree"></ul>
                                </div>
                                <div class="people_list"></div>
                                <div class="group_list"></div>
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
                                <div class="btnB"></div>
                                <div class=" col-md-offset-5" style="display: block;margin-top: 30px;width: 40rem;">
                                    <button type="submit" class="confirm" class="close" data-dismiss="modal"
                                            aria-hidden="true">确认
                                    </button>
                                    <button class="cancel" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                            <div class=" col-md-2 moveBtn">
                                <button id="btnR">添加</button>
                                <button id="btnD">移除</button>
                            </div>
                            <div class="col-md-3 department ">
                                <h5 class="h52">已选列表</h5>

                                <form class="submitForm" id="form1" name="form" method="post" action="">
                                    <table class="table">
                                        <tbody>
                                        <select id="right" multiple="multiple">
                                            <c:forEach items="${securityAllRoleDTO.securityOfficerDep}" var="dept">
                                                <option class="htmlDepartment"
                                                        value="${dept.id}">${dept.name}</option>
                                            </c:forEach>
                                            <c:forEach items="${securityAllRoleDTO.securityOfficerStaff}" var="staff">
                                                <option class="htmlPerson"
                                                        value="${staff.id}">${staff.name}</option>
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
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<script>
    //从后台拿到右边的数据
    var selectedData = [];
    var sview = [], aview = [];
    $("#right option.htmlDepartment").each(function (i) {
        if (this.value != "") {
            selectedData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 1 + '}'));
            aview[i] = this.value;
        }
    });
    $("#right option.htmlPerson").each(function (i) {
        if (this.value != "") {
            selectedData.push(JSON.parse('{"name":"' + this.innerHTML + '","id":"' + this.value + '","type":' + 3 + '}'));
            sview[i] = this.value;
        }
    });
    arrData1select(selectedData, '#right');
    $('#viewStaffs').val(sview.join());
    $('#viewAgencys').val(aview.join());

    // 点击部门
    var data2 = [];
    $('.typeList_department').bind('click', function () {
        $('.group_list').css('display', 'none');
        $('.zTreeDemoBackground').css('display', 'block');
        // alert(11);
        $('#treeDemo').css('display', 'block');
        $('.department').css('margin-top', '-2.95%');
        $('.addPeople').css('display', 'none');
        $('.moveBtn').css('margin-top', '3.1%');
        $('.content_wrap .resultPeople').css('display', 'none');
        $('#left').html('');
    });
    //点击人员
    $('.typeList_employees').bind('click', function () {
        $('.group_list').css('display', 'none');
        $('.addPeople').css('display', 'block');
        $('.department').css('margin-top', ' -2.95%');
        // $('.waitSelect').css('margin-left','4%');
        $('.zTreeDemoBackground').css('display', 'none');
        $('.moveBtn').css('margin-top', '-1.54%');
        $('#left').html('');
    });

    function arr2Html(arr) {
        var html = '';
        for (var i = 0; i < arr.length; i++) {
            html = html + '<li data-id="' + arr[i].id + '">' + arr[i].name + '</li>';
        }
        return html;
    }
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
                    console.log(data);
                    arrData1select(data, '#left');
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
        //是最后一个  找后台要下一级
        $.getJSON('/agency/nextAgency?id=' + treeNode.id + '&type=' + treeNode.agencyType, function (res) {
            childrenArr = res.data;
            arrData1select(childrenArr, leftId);
        })
    }
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) {
            return null;
        }
        var arr = [];
        for (var i = 0, h = childNodes.length; i < h; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            //arr.push  下一级的 name  id
            arr.push({
                name: childNodes[i].name,
                id: childNodes[i].id,
                isParent: childNodes[i].isParent,
                type: childNodes[i].type
            })
        }
        if (parentNode) {
            waitLists['id' + parentNode.id] = arr;
        } else {
            waitLists['idRoot'] = arr;
        }
        return childNodes;
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
        var htmlGroupNum = 0;
        var htmlDepartmentNum = 0;
        var htmlPerson = ')" id="htmlPerson">' + '</optgroup>';
        var htmlDepartment = ')"  id="htmlDepartment">' + '</optgroup>';
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
                htmlDepartment = htmlDepartment + '<option class="htmlDepartment" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
            } else if (arr[i].type == '3') {
                htmlPersonNum++;
                htmlPerson = htmlPerson + '<option class="htmlPerson" data-id="' + arr[i].id + '">' + arr[i].name + '</option>'
            }
        }
        ;
        htmlPerson = '<optgroup label="员工(' + htmlPersonNum + htmlPerson;
        htmlDepartment = '<optgroup label="部门(' + htmlDepartmentNum + htmlDepartment;
        $(id).html('');
        $(id).append(htmlPerson).append(htmlDepartment);
    }
    function select() {
        $('#btnR').bind('click', function () {
            wait1selected();
        });
        $('#btnD').bind('click', function () {
            selected1wait();
        });
    }
    //右移方法1
    function wait1selected() {
        //将左侧选中的数据放进一个数组中
        var optionArr = $('#left option:selected');
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
        arrData1select(arr, '#right');
    }
    //左移方法1
    function selected1wait() {
        var optionArr = $('#right option:selected');
        var arr = [];
        for (var i = 0; i < optionArr.length; i++) {
            var className = '1';
            if (optionArr[i].className == 'htmlPerson') className = '3';

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
        $('#right #htmlDepartment')[0].label = "部门(" + $('#right').find('.htmlDepartment').length + ")";
        $('#right #htmlPerson')[0].label = "员工(" + $('#right').find('.htmlPerson').length + ")";
        var optionArrLeft = $('#left option');
        var arrLeft = [];
        for (var i = 0; i < optionArrLeft.length; i++) {
            var className = '1';
            if (optionArrLeft[i].className == 'htmlPerson') className = '3';

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
        arrData1select(arr, '#left')
    }
    select();
    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting);
    });
    function arrSubtraction(arr1, arr2) {
        var temp = [];
        var num = 0;
        for (var i = 0; i < arr1.length; i++) {
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
        $('#htmlPerson').css('display', 'none');
        $('#htmlDepartment').css('display', 'none');
        var tstaff = [], tagency = [], torganize = [];
        var i = k = 0;
        $("#right option").each(function () {
            if ($(this).attr("class") == 'htmlDepartment') {
                tagency[i] = $(this).attr("data-id");
                i++;
            } else {
                tstaff[k] = $(this).attr("data-id");
                k++;
            }
        });
        $('#viewStaffs').val(tstaff.join());
        $('#viewAgencys').val(tagency.join());
    });
</script>
</body>
</html>