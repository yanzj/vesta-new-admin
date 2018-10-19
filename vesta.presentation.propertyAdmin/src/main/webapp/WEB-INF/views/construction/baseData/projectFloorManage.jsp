<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/10/21
  Time: 18:18
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
    <!-- js-->
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <script>
        new WOW().init();
        $(function () {
            $("#003100010000").addClass("active");
            $("#003100010000").parent().parent().addClass("in");
            $("#003100010000").parent().parent().parent().parent().addClass("active");
        })
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <script type="text/javascript" language="javascript" charset="utf-8">
        jQuery.validator.addMethod("integer", function (value, element) {
            var tel = /^-?[1-9]*[1-9][0-9]*$/g;  //正、负 整数
            return this.optional(element) || (tel.test(value));
        }, "请输入整数");
        $().ready(function () {
            var result = $('#result').val();
            if (result.length > 1) {
                alert(result);
            }
            ;
            $("#addFloor").validate({
                rules: {
                    floorName: {
                        required: true,
                        integer: true
                    }
                },
                messages: {
                    floorName: {
                        required: "请输入楼栋名",
                    }
                },
            });

            $("#updateFloor").validate({
                rules: {
                    floorName: {
                        required: true,
                        integer: true
                    }
                },
                messages: {
                    floorName: {
                        required: "请输入楼栋名",
                    }
                },
            })
        })
        $(document).ready(function () {
            <%--$('#addFloor').submit(function () {--%>
            <%--$.ajax({--%>
            <%--url: "../BaseData/addFloor",            //目标网址--%>
            <%--type: "post",--%>
            <%--async: "false",--%>
            <%--dataType: "json",--%>
            <%--data: $("#addFloor").serialize(),--%>
            <%--success: function (json) {--%>
            <%--<!-- 获取返回代码 -->--%>
            <%--var code = json.code;--%>
            <%--if (code != 0) {--%>
            <%--var errorMessage = json.msg;--%>
            <%--alert(errorMessage);--%>
            <%--} else {--%>
            <%--<!-- 获取返回数据 -->--%>
            <%--var data = json.data;--%>
            <%--if (data == 'ok') {--%>
            <%--var buildId = $("#buildId").val();--%>
            <%--window.location.href = '../BaseData/projectFloorManage.html?buildId='+buildId+'&projectId=${projectId}';--%>
            <%--alert("保存成功");--%>
            <%--} else {--%>
            <%--alert(data);--%>
            <%--}--%>
            <%--}--%>
            <%--}--%>
            <%--});--%>
            <%--return false; //此处必须返回false，阻止常规的form提交--%>
            <%--});--%>
            <%--$('#updateFloor').submit(function () {--%>
            <%--$.ajax({--%>
            <%--url: "../BaseData/updateFloor",            //目标网址--%>
            <%--type: "post",--%>
            <%--async: "false",--%>
            <%--dataType: "json",--%>
            <%--data: $("#updateFloor").serialize(),--%>
            <%--success: function (json) {--%>
            <%--<!-- 获取返回代码 -->--%>
            <%--var code = json.code;--%>
            <%--if (code != 0) {--%>
            <%--var errorMessage = json.msg;--%>
            <%--alert(errorMessage);--%>
            <%--} else {--%>
            <%--<!-- 获取返回数据 -->--%>
            <%--var data = json.data;--%>
            <%--if (data == 'ok') {--%>
            <%--var buildId = $("#buildId").val();--%>
            <%--window.location.href = '../BaseData/projectFloorManage.html?buildId='+buildId+'&projectId=${projectId}';--%>
            <%--alert("保存成功");--%>
            <%--} else {--%>
            <%--alert(data);--%>
            <%--}--%>
            <%--}--%>
            <%--}--%>
            <%--});--%>
            <%--return false; //此处必须返回false，阻止常规的form提交--%>
            <%--});--%>
        })
        //新增楼层
        function addFloor() {
            var f = document.getElementById("homePageimgpath").value;
            if (f == "") {
            } else {
                if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)) {
                    alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
                    return false;
                }
            }
            document.getElementById("addFloor").action = "../BaseData/addFloor";
            document.getElementById("addFloor").submit();
        }
        //批量新增楼层
        function executeInsertFloor() {
            var firstFloorArea = $("#firstFloorArea").val();
            var re = /^-?[1-9]*[1-9][0-9]*$/g;
            if (firstFloorArea == "") {
                alert("请输入起始楼层区间！")
                return;
            }
            if (!re.test(firstFloorArea)) {
                alert("请输入整数!");
                return;
            }
            var secondFloorArea = $("#secondFloorArea").val();
            var re = /^-?[1-9]*[1-9][0-9]*$/g;
            if (secondFloorArea == "") {
                alert("请输入结束楼层区间！")
                return;
            }
            if (!re.test(secondFloorArea)) {
                alert("请输入整数!");
                return;
            }
            if (parseInt(firstFloorArea)  >= parseInt(secondFloorArea)) {
                alert("起始区间不得大于截止区间");
                return;
            }
            var f = document.getElementById("homePageimgpath2").value;
            if (f == "") {
            } else {
                if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)) {
                    alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
                    return false;
                }
            }
            var buildId = $("#buildId").val();
            document.getElementById("allInsert").action = "../BaseData/executeAddFloor?buildId=" + buildId;
            document.getElementById("allInsert").submit();
        }

        //        function getHouseImage() {
        //            $.getJSON("/BaseData/getHouseImg", function (data) {
        //                var arr = data.data;
        //                document.getElementById("imgUpdate").src = arr;
        //            });
        //        }
        //修改楼层
        function modifyFloor() {
            var f = document.getElementById("homePageimgpath3").value;
            if (f == "") {
            } else {
                if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)) {
                    alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
                    return false;
                }
            }
            document.getElementById("updateFloor").action = "../BaseData/updateFloor";
            document.getElementById("updateFloor").submit();
        }
        //批量修改楼层
        function executeUpdateFloor() {
            var firstFloorAreaUpdate = $("#firstFloorAreaUpdate").val();
            var re = /^-?[1-9]*[1-9][0-9]*$/g;
            if (firstFloorAreaUpdate == "") {
                alert("请输入楼层区间！")
                return;
            }
            if (!re.test(firstFloorAreaUpdate)) {
                alert("请输入整数!");
                return;
            }
            var secondFloorAreaUpdate = $("#secondFloorAreaUpdate").val();
            var re = /^-?[1-9]*[1-9][0-9]*$/g;
            if (secondFloorAreaUpdate == "") {
                alert("请输入楼层区间！")
                return;
            }
            if (!re.test(secondFloorAreaUpdate)) {
                alert("请输入整数!");
                return;
            }
            if (parseInt(firstFloorAreaUpdate) >= parseInt(secondFloorAreaUpdate)) {
                alert("起始区间不得大于截止区间");
                return;
            }
            var f = document.getElementById("homePageimgpath4").value;
            if (f == "") {
            } else {
                if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)) {
                    alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
                    return false;
                }
            }
            document.getElementById("allUpdate").action = "../BaseData/executeUpdateFloor";
            document.getElementById("allUpdate").submit();
        }

        function chuancan(id, name) {
            $("#floorUpdateId").val(id);
            name = name.split("层");
            $("#floorUpdateName").val(name[0]);
            $.getJSON("/BaseData/getHouseImgByFloorId?floorId=" + id + "", function (data) {
                var arr = data.data;
                if (arr != null) {
                    document.getElementById("imgUpdate").src = arr;
                } else {
                    document.getElementById("imgUpdate").src = "";
                }
            });
            $("#showMyModel").click();
        }

        function projectBack() {
            window.location.href = "../BaseData/projectBuilding.html?projectId=" + "${projectId}";
        }
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="003100010000" username="${authPropertystaff.staffName}"/>


    <div class="forms">
        <input type="text" id="result" value="${result}" style="display: none">

        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <div class="form-group  col-lg-4">
                    <label class="col-sm-8 control-label">楼栋名称：${building.buildName}</label>

                    <div class="col-sm-4">
                    </div>
                </div>
                <%-- 新增楼层 --%>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#floorSave">
                    <spring:message code="project.floorSave"/>
                </button>
                <%-- 批量新增楼层 --%>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#floorExecute"
                        style="width:9.5rem;">
                    <spring:message code="project.floorExecute"/>
                </button>
                <%-- 批量修改 --%>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#floorExecuteUpdate">
                    <spring:message code="project.floorExecuteUpdate"/>
                </button>
                <%-- 返回 --%>
                <input type="button" class="btn btn-primary" onclick="javascript:projectBack();"
                       value="<spring:message code="project.back"/>">
            </div>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <form class="form-horizontal" id="oneForm" action="" method="post"
              enctype="multipart/form-data">
            <%-- 楼层id --%>
            <input type="hidden" id="floorId" name="floorId">

        </form>
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td><spring:message code="mall.serialNumber"/></td>
                <th>楼层名称</th>
                <th>创建时间</th>
                <th>修改时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${floorList}" var="list" varStatus="row">
                <tr>
                    <td><b>${row.index+1}</b></td>
                    <td>${list.floorName}</td>
                    <td>${list.createOn}</td>
                    <td>${list.modifyOn}</td>
                    <td>
                            <%-- 修改 --%>
                        <a onclick="chuancan('${list.floorId}', '${list.floorName}')"
                           style="cursor: pointer"><spring:message code="project.update"/></a>
                        <a id="showMyModel" data-toggle="modal" data-target="#floorUpdate" style="display: none"></a>

                            <%-- 删除 --%>
                        <a onClick="javascript:if (confirm('确定删除吗？')) location.href='../BaseData/deleteFloor.html?floorId=${list.floorId}&buildId=${building.buildId}';else return;"
                           style="cursor: pointer"><spring:message code="common_delete"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../BaseData/projectFloorManage.html?buildId=${building.buildId}&pageIndex={0}&projectId=${projectId}"/>
    </div>


</div>
</div>
</div>
</div>

<%-- 新增楼层模态框 --%>
<div class="modal fade" id="floorSave" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" id="addFloor" action="../BaseData/addFloor" method="post"
                  enctype="multipart/form-data">
                <input type="hidden" id="buildId" name="buildId" value="${buildId}">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel"><spring:message code="project.floorSave"/></h4>
                </div>
                <div class="modal-body">
                    <table>
                        <tr>
                            <td width="100px;">
                                <%--楼层名称--%>
                                <label class="" for="floorName"><spring:message code="project.floorName"/> ：</label>
                            </td>
                            <td>
                                <input type="text" placeholder="" id="floorName" name="floorName" value="">层
                            </td>
                        </tr>
                        <%--图片--%>
                        <tr>
                            <td width="100px;">
                                <label class="" for="homePageimgpath"><spring:message
                                        code="project.img"/></label>
                            </td>
                            <td>
                                <input type="file" class="form-control" placeholder="" id="homePageimgpath"
                                       name="homePageimgpath" value="" style="width: 179px;"
                                       onchange="check(this)">
                            </td>
                        </tr>
                        <%-- 图片展示 --%>
                        <tr>
                            <td width="100px;"></td>
                            <td>
                                <div id="demo_homePageimgpath" style="padding-top: 15px;"></div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <%-- 取消 --%>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                            code="project.cancel"/></button>
                    <%-- 确定 --%>
                    <button type="button" class="btn btn-primary" onclick="addFloor()"><spring:message
                            code="project.confirm"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 修改楼层模态框 -->
<div class="modal fade" id="floorUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel3"><spring:message code="project.update"/></h4>
            </div>
            <form name="updateFloor" id="updateFloor" action="../BaseData/updateFloor" method="post"
                  enctype="multipart/form-data">
                <div class="modal-body">
                    <table>
                        <tr>
                            <td width="100px">
                                <%--楼层名称--%>
                                <label class="" for="floorUpdateName"><spring:message code="project.floorName"/>
                                    ：</label>
                            </td>
                            <td>
                                <input type="hidden" id="floorUpdateId" name="floorId" value="">
                                <input type="hidden" id="buildId1" name="buildId" value="${buildId}">
                                <input type="hidden" id="projectId" name="projectId" value="${projectId}">
                                <input type="text" placeholder="" id="floorUpdateName"
                                       name="floorName">层
                            </td>
                        </tr>
                        <%--图片--%>
                        <tr>
                            <td width="100px">
                                <label class="" for="homePageimgpath3"><spring:message
                                        code="project.img"/></label>
                            </td>
                            <td>
                                <input type="file" class="form-control" placeholder="" id="homePageimgpath3"
                                       name="homePageimgpath" value="" style="width: 179px;"
                                       onchange="checks(this)">
                            </td>
                        </tr>
                        <%-- 图片展示 --%>
                        <tr>
                            <td width="100px;"></td>
                            <td>
                                <div id="demo_homePageimgpath3" style="padding-top: 15px;">
                                    <img src="" alt="" style="width:167px;height:120px;" id="imgUpdate"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                            code="project.cancel"/></button>
                    <button type="button" class="btn btn-primary" onclick="modifyFloor()"><spring:message code="project.confirm"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 批量新增楼层模态框 -->
<div class="modal fade" id="floorExecute" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel2"
     aria-hidden="true">
    <div class="modal-dialog">
        <form name="allInsert" id="allInsert" action="../BaseData/executeAddFloor" method="post"
              enctype="multipart/form-data">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel2"><spring:message code="project.floorExecute"/></h4>
                </div>
                <div class="modal-body">
                    <table>
                        <tr>
                            <td width="100px;">
                                <%-- 楼层区间 --%>
                                <label class="" for="floorName"><spring:message code="project.floorInterval"/> ：</label>
                            </td>
                            <td>
                                <input type="text" class="" placeholder="" id="firstFloorArea"
                                       name="firstFloorAreaUpdate" value=""> ———
                                <input type="text" class="" placeholder="" id="secondFloorArea"
                                       name="secondFloorAreaUpdate" value="">
                                <span style="color: red;">&nbsp;&nbsp;*&nbsp;必须为整数</span>
                            </td>
                        </tr>
                        <tr>
                            <%--图片--%>
                            <td width="100px;">
                                <label class="" for="homePageimgpath2"><spring:message
                                        code="project.img"/></label>
                            </td>
                            <td>
                                <input type="file" class="form-control" placeholder="" id="homePageimgpath2"
                                       name="homePageimgpathExecuteUpdate" value="" style="width: 179px;"
                                       onchange="checkExecute(this)">
                            </td>
                        </tr>
                        <tr>
                            <td width="100px;"></td>
                            <td>
                                <div id="demo_homePageimgpath2" style="padding-top: 15px;">
                                    <img src="" alt="" style="width:167px;height:120px;" id="imgExecute"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                            code="project.cancel"/></button>
                    <button type="button" class="btn btn-primary" onclick="executeInsertFloor()"><spring:message
                            code="project.confirm"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 批量修改模态框 -->
<div class="modal fade" id="floorExecuteUpdate" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel4"
     aria-hidden="true">
    <div class="modal-dialog">
        <form name="allUpdate" id="allUpdate" action="../BaseData/executeUpdateFloor" method="post"
              enctype="multipart/form-data">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel4"><spring:message code="project.floorExecuteUpdate"/></h4>
                </div>
                <input type="hidden" name="buildId" value="${buildId}">

                <div class="modal-body">
                    <table>
                        <tr>
                            <td width="100px;">
                                <%-- 楼层区间 --%>
                                <label class="" for="floorName"><spring:message code="project.floorInterval"/> ：</label>
                            </td>
                            <td>
                                <input type="text" class="" placeholder="" id="firstFloorAreaUpdate"
                                       name="firstFloorAreaUpdate" value=""> ———
                                <input type="text" class="" placeholder="" id="secondFloorAreaUpdate"
                                       name="secondFloorAreaUpdate" value="">
                                <span style="color: red;">&nbsp;&nbsp;*&nbsp;必须为整数</span>
                            </td>
                        </tr>
                        <tr>
                            <%--图片--%>
                            <td width="100px;">
                                <label class="" for="homePageimgpath4"><spring:message
                                        code="project.img"/></label>
                            </td>
                            <td>
                                <input type="file" class="form-control" placeholder="" id="homePageimgpath4"
                                       name="homePageimgpathExecuteUpdate" value="" style="width: 179px;"
                                       onchange="checkUpdate(this)">
                            </td>
                        </tr>
                        <tr>
                            <td width="100px;"></td>
                            <td>
                                <div id="demo_homePageimgpath4" style="padding-top: 15px;">
                                    <img src="" alt="" style="width:167px;height:120px;" id="imgExcuteUpdate"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                            code="project.cancel"/></button>
                    <button type="button" class="btn btn-primary" onclick="executeUpdateFloor()"><spring:message
                            code="project.confirm"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script type="text/javascript">
    var inputs = $("#homePageimgpath").get(0);
    var result = document.getElementById("demo_homePageimgpath");
    if (typeof FileReader === 'undefined') {
        result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
        inputs.setAttribute('disabled', 'disabled');
    } else {
        inputs.addEventListener('change', readFile, false);
    }

    function readFile() {

        for (var i = 0; i < this.files.length; i++) {
            var file = this.files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                result.innerHTML = '<img src="' + this.result + '" style="width:100px" alt="" id="imgSrc"/>';
            }
        }
    }

    var imgtype = true;
    function check(fnUpload) {
        var filename = fnUpload.value;
        var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
        if (mime != ".jpg" && mime != ".png" && mime != ".jpeg" && mime != ".gif") {
            alert("上传图片类型错误");
            imgtype = false;
        } else {
            imgtype = true;
        }
    }
</script>
<script type="text/javascript">
    function getPath(obj, fileQuery, transImg) {
        var imgSrc = '', imgArr = [], strSrc = '';
        if (window.navigator.userAgent.indexOf("MSIE") >= 1) { // IE浏览器判断
            if (obj.select) {
                obj.select();
                var path = document.selection.createRange().text;
                alert(path);
                obj.removeAttribute("src");
                imgSrc = fileQuery.value;
                imgArr = imgSrc.split('.');
                strSrc = imgArr[imgArr.length - 1].toLowerCase();
                if (strSrc.localeCompare('jpg') === 0 || strSrc.localeCompare('jpeg') === 0 || strSrc.localeCompare('gif') === 0 || strSrc.localeCompare('png') === 0) {
                    obj.setAttribute("src", transImg);
                    obj.style.filter =
                            "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + path + "', sizingMethod='scale');"; // IE通过滤镜的方式实现图片显示
                } else {
                    throw new Error('File type Error! please image file upload..');
                }
            } else {
                imgSrc = fileQuery.value;
                imgArr = imgSrc.split('.');
                strSrc = imgArr[imgArr.length - 1].toLowerCase();
                if (strSrc.localeCompare('jpg') === 0 || strSrc.localeCompare('jpeg') === 0 || strSrc.localeCompare('gif') === 0 || strSrc.localeCompare('png') === 0) {
                    obj.src = fileQuery.value;
                } else {
                    throw new Error('File type Error! please image file upload..');
                }

            }

        } else {
            var file = fileQuery.files[0];
            var reader = new FileReader();
            reader.onload = function (e) {
                imgSrc = fileQuery.value;
                imgArr = imgSrc.split('.');
                strSrc = imgArr[imgArr.length - 1].toLowerCase();
                if (strSrc.localeCompare('jpg') === 0 || strSrc.localeCompare('jpeg') === 0 || strSrc.localeCompare('gif') === 0 || strSrc.localeCompare('png') === 0) {
                    obj.setAttribute("src", e.target.result);
                } else {
                    throw new Error('File type Error! please image file upload..');
                }
            }
            reader.readAsDataURL(file);
        }
    }

    function checks(fnUpload) {
        var homePath = fnUpload.id;
        //以下即为完整客户端路径
        var file_img = document.getElementById("imgUpdate");
        var iptfileupload = document.getElementById(homePath);
        getPath(file_img, iptfileupload, file_img);
    }

    function checkExecute(fnUpload) {
        var homePath = fnUpload.id;
        //以下即为完整客户端路径
        var file_img = document.getElementById("imgExecute");
        var iptfileupload = document.getElementById(homePath);
        getPath(file_img, iptfileupload, file_img);
    }

    function checkUpdate(fnUpload) {
        var homePath = fnUpload.id;
        //以下即为完整客户端路径
        var file_img = document.getElementById("imgExcuteUpdate");
        var iptfileupload = document.getElementById(homePath);
        getPath(file_img, iptfileupload, file_img);
    }
</script>
</body>
</html>
