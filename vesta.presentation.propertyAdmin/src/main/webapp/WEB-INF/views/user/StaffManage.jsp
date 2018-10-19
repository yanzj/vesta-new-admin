<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/1/25
  Time: 16:00
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
<%@ page import="com.maxrocky.vesta.utility.StringUtil" %>
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
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
    <style>
        .btnDis {
            display: none;
            /*background-color: #eee;*/
            /*border: none;*/
        }

        .btn.btnDis:hover {
            display: none;
            /*background-color: #eee;*/
            /*border: none;*/
        }
    </style>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="001300020000" username="${propertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" name="searchByName" id="searchByName"
                      action="../user/userStaffManage.html">
                    <c:choose>
                        <c:when test="${flag eq 2 && adminDTO.agencyId ne '1'}">
                            <div class="form-group  col-lg-4">
                                <label for="staffNameDto" class="col-sm-4 control-label">机构名称</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" placeholder="" id="agencyNameDto"
                                           name="agencyName" value="${adminDTO.agencyName}">
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group  col-lg-4">
                                <label for="staffNameDto" class="col-sm-4 control-label">真实姓名</label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" placeholder="" id="staffNameDto"
                                           name="admStaff" value="${adminDTO.admStaff}">
                                </div>
                            </div>
                            <%--用户名--%>
                            <div class="form-group  col-lg-4">
                                <label for="userName" class="col-sm-4 control-label"><spring:message
                                        code="staffManage.staffSelUserName"/></label>

                                <div class="col-sm-8">
                                    <input type="text" class="form-control" placeholder="" id="userName"
                                           name="admUser" value="${adminDTO.admUser}">
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <button type="button" id="sbm" class="btn btn-primary" for="propertySearch"><spring:message
                            code="propertyCompany.propertySearch"/></button>
                    <a id="addAgency" style="cursor: pointer;width: 9.5rem;" class="btn btn-primary" for="rolesetAdd">新建组织机构</a>
                    <div id="addPeople"  class="btn btn-primary btnDis">新建用户</div>
                    <a id="toP" href="../agency/addAgencyPeople.html" type="button" class="btn btn-primary">添加人员</a>
                    <button type="button" class="btn btn-primary" onclick="downloadModel()">下载数据模板</button>
                    <button type="button" class="btn btn-primary" onclick="test()">导入数据</button>
                </form>
                <form action="..../agency/ExcelAddPeople.html",name="frm" id="frm" method="post" enctype="multipart/form-data">
                    <%-- 导入excel 隐藏 --%>
                    <input type="file" id="myfile" name="myfile" style="visibility:hidden;" onchange="importExcel()">
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
        <form action="../role/listRoleRole" method="post" id="search_form">

            <input id="rolesetId" type="hidden" name="rolesetId" value="">
        </form>
        <div class="row">
            <div class="content_wrap col-md-3">
                <div class="zTreeDemoBackground left">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
            <div class="table-responsive widget-shadow col-md-9" style="float:left;margin-top:0">
                <table class="tableStyle  col-md-12">
                    <thead>
                    <!-- 点击部门 显示人员的列表 -->
                    <c:if test="${flag eq 1}">
                        <tr>
                            <th>排序号</th>
                            <th>用户名</th>
                            <th>姓名</th>
                            <th>联系方式</th>
                            <th>邮箱地址</th>
                            <th>最后修改时间</th>
                            <th>操作</th>
                        </tr>
                    </c:if>
                    <c:if test="${flag eq 2}">
                        <!-- 点击公司 显示公司的列表 -->
                        <tr>
                            <th>排序号</th>
                            <th>组织机构名称</th>
                            <th>上级部门</th>
                                <%--<th>管理员</th>--%>
                                <%--<th>备注</th>--%>
                            <th>最后修改时间</th>
                            <th>操作</th>
                        </tr>
                    </c:if>
                    </thead>
                    <tbody class="publicTbody">
                    <c:forEach items="${agencyStaff}" var="list" varStatus="as">
                        <tr>
                            <td><b>${list.orderNum}</b></td>
                            <td>${list.agencyName}</td>
                            <td>${list.parentName}</td>
                            <c:if test="${flag eq 1}">
                                <td>${list.staffMobile}</td>
                                <td>${list.memo}</td>
                                <td>${list.modifyTime}</td>
                                <td class="last">
                                    <a href="../user/staffDetail.html?staffId=${list.agencyId}&agencyId=${adminDTO.agencyId}">详情</a>
                                        <%--<a style="cursor: pointer" onclick="javascript:if(confirm('确定删除吗！'))location.href='../user/delStaff.html?staffIdDto=${list.agencyId}';else return" class="a3"><span class="span1">置为无效</span></a>--%>
                                </td>
                            </c:if>

                            <c:if test="${flag eq 2}">
                                <td>${list.modifyTime}</td>
                                <td class="last">
                                    <a class="a1" href="../agency/agencyDetail.html?agencyId=${list.agencyId}"><span
                                            class="span1">详情</span></a>
                                        <%--<a style="cursor: pointer" onclick="javascript:if(confirm('确定删除吗！'))location.href='../agency/delAgency.html?agencyId=${list.agencyId}&type=1';else return" class="a3"><span class="span1">置为无效</span></a>--%>
                                        <%--<a class="a1" href="">--%>
                                        <%--</a>--%>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <m:pager pageIndex="${webPage.pageIndex}"
                         pageSize="${webPage.pageSize}"
                         recordCount="${webPage.recordCount}"
                         submitUrl="${pageContext.request.contextPath }/user/userStaffManage.html?pageIndex={0}&admStaff=${adminDTO.admStaff}&admUser=${adminDTO.admUser}"/>
            </div>
        </div>
    </div>


</div>
</div>
</div>
</div>

<script>


    $().ready(function () {
        if (getCookie('agencyType') != '1') {
            $('#addPeople').addClass('btnDis');
            $('#addAgency').removeClass("btnDis");
            $('#addAgency').attr('href', '../agency/toAddAgency.html');
            $('#toP').hide();
        } else {
//            $("#addAgency").attr('disabled', "true");
            $('#addAgency').addClass('btnDis');
            $('#toP').show();
            $('#addPeople').removeClass('btnDis');
            $('#addPeople').click(function () {
                window.top.location.href = '/user/gotoUpdateStaff.html'
            })
        }
    })

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
                    document.getElementById("frm").action = "../agency/ExcelAddPeople.html";
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

    function downloadModel() {
        if (confirm("是否下载模板？")) {
            var href = "../agency/exportPeopleModel";
            window.location.href = href;
        } else {
            return;
        }
    }

    //JS操作cookies方法!

    //写cookies

    function setCookie(name, value, options) {
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString();
        }
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    }
    ;

    //读取cookies
    function getCookie(name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

        if (arr = document.cookie.match(reg))

            return unescape(arr[2]);
        else
            return null;
    }

    //删除cookies
    function delCookie(name, options) {
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null)
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString() + ";+path=" + path + ";domain=" + domain;
    }
    var selectedList = []
    var waitLists = {};//waitLists['id'+id]=[{name:name,id:id}]
    var setting = {
        async: {
            enable: false,
            url: "/agency/fullAgency",
            autoParam: ["id", "name=n", "level=lv"],
            otherParam: {"otherParam": "zTreeAsyncTest"},
            dataFilter: filter,
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: null
            }
        },
        callback: {
            onClick: onClick,
//      beforeAsync: beforeAsync,
            onAsyncSuccess: onAsyncSuccess,
//      onAsyncError: onAsyncError
        },
    };


    //  alert(initOpenTreeDate)
    var initState = false;
    function onAsyncSuccess(event, treeId, treeNode, msg) {
//    console.log(treeNode)
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        if (!initState) {
            if (treeNode) {
                var index = initOpenTreeDate.indexOf(treeNode.id);
                if (index == initOpenTreeDate.length - 2) {
                    initState = true;
                    setTimeout(function () {
                        zTree.selectNode(zTree.getNodesByParam('id', initOpenTreeDate[index + 1])[0])
                    }, 0)
                } else {
                    setTimeout(function () {
                        zTree.expandNode(zTree.getNodesByParam('id', initOpenTreeDate[index + 1])[0]);
                    }, 0)
                }
            } else {
                if (initOpenTreeDate.length == 1) {
                    setTimeout(function () {
                        zTree.selectNode(zTree.getNodesByParam('id', initOpenTreeDate[0])[0])
                    }, 0)
                } else {
                    var node = zTree.getNodesByParam('id', initOpenTreeDate[0])[0]
                    zTree.expandNode(node);
                }
            }
        }

    }

    function saveOpenTreeData(node) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        if (!node) return;
        if (node.parentTId) {
            var pNode = zTree.getNodeByTId(node.parentTId);
//      openTreeData.unshift(pNode.id)
//      saveOpenTreeData(pNode,openTreeData)
            setCookie('openTreeData', pNode.id, {domain: document.domain, path: '/'})
        }
    }

    //传参type id部门
    //name id type pid
    //查找子集
    //event, treeId, treeNode, clickFlag
    function onClick(event, treeId, treeNode, clickFlag) {
        var openTreeData = [];
        setCookie('agencyId', treeNode.id, {domain: document.domain, path: '/'});
        setCookie('agencyType', treeNode.agencyType, {domain: document.domain, path: '/'});
        setCookie('agencyName', treeNode.agencyName, {domain: document.domain, path: '/'});
//    console.log(treeNode)
//    openTreeData.unshift(treeNode.id)
        saveOpenTreeData(treeNode);
        window.location.href = "../user/userStaffManage.html?agencyId=" + treeNode.id + "&agencyType=" + treeNode.agencyType;
        ;
        if (treeNode.agencyType == 1) {
//      $('.company_header').css('display','table-row');
//      $('.department_header').css('display','none');
            $('.pageNumber').css('margin-top', '0%');
//    } else if(treeNode.agencyType==2){
//      //调用部门函数
////        $('.department_header').css('display','table-row');
////        $('.company_header').css('display','none');
//      $('.pageNumber').css('margin-top','0%');
//    }
        } else {
            // alert(treeNode.type);
//      $('.company_header').css('display','none');
//      $('.department_header').css('display','none');
            $('.pageNumber').css('margin-top', '20%');
            $('.publicTbody').html('');
        }
    }

    //删除
    function deleted(obj, agencyId) {
        $.ajax({
            url: "/agency/delAgency",            //目标网址
            type: "get",
            async: "false",
            dataType: "json",
            data: {
                agencyId: agencyId,
                type: "1"
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    $(obj).parent().hide();
                }
            }
        });
    }

    function delUser(staffId) {
        $.ajax({
            url: "/user/delStaff",            //目标网址
            type: "get",
            async: "false",
            dataType: "json",
            data: {
                staffIdDto: staffId
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    $("#" + staffId).parent().hide();
                }
            }
        });
    }
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) {
            return null;
        }
        var arr = [];
        for (var i = 0, h = childNodes.length; i < h; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            //arr.push  下一级的 name  id
            arr.push({name: childNodes[i].name, id: childNodes[i].id, isParent: childNodes[i].isParent})
        }
        if (parentNode) {
            waitLists['id' + parentNode.id] = arr;
        } else {
            waitLists['idRoot'] = arr;
        }
        return childNodes;
    }
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
    function strToJson(str) {
        var json = (new Function("return " + str))();

        return json;
    }
    $(document).ready(function () {
        $.ajax({
            url: "../agency/fullAgency", dataType: "json", success: function (result) {
//      result;
                $.fn.zTree.init($("#treeDemo"), setting, result);
//      var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
//      treeObj.expandAll(true);
//      var cpId=getCookie('openTreeData');
                var cId = getCookie('agencyId');
                var treeO = $.fn.zTree.getZTreeObj("treeDemo");
                var nodes = treeO.getNodeByParam('id', cId, null);
                treeO.selectNode(nodes);
                treeO.expandNode(nodes, true, false, true);
            }
        });
    });

    $("#sbm").click(function () {
//  delCookie('agencyId',{domain:document.domain,path:'/'});
//  delCookie('agencyType',{domain:document.domain,path:'/'});
//  setCookie('openTreeData','1',{domain:document.domain,path:'/'})
        $("#searchByName").submit();
    })
</script>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>