<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/12/7
  Time: 15:39
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
    <script src="../static/js/jquery.metadata.js"></script>
    <script>
        $(function () {
            console.log("sqq")
            $("#003100030000").addClass("active");
            $("#003100030000").parent().parent().addClass("in");
            $("#003100030000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <style>
        label, label.error {
            margin-left: 1%;
            color: red;
        }
    </style>

    <script>
        $().ready(function () {
            $("#altProjectCity").validate({
                rules: {
                    cityName: {
                        required: true,
                        minlength: 1,
                        maxlength: 20
                    }
                },
                messages: {
                    cityName: {
                        required: "请输入区域名称！",
                        minlength: "不能少于1个字符！",
                        maxlength: "请勿超过20个字！"
                    }
                }
            });
        });
    </script>

</head>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="003100030000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <form class="form-horizontal" name="form1" id="addOperation" action="../BaseData/addProjectCity.html">
                    <%--<div class="form-group  col-lg-5">--%>
                    <%--<div class="col-sm-12">--%>
                    <%--<input type="text" name="cityName" class="form-control">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <button type="button" class="btn btn-primary" for="propertySearch" data-toggle="modal"
                            data-target="#myModal" onclick="clearParm()">新增城市
                    </button>
                    <a type="button" class="btn btn-primary" href="../BaseData/projectRoleManage.html">返回</a>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="table-responsive bs-example widget-shadow">
    <table width="100%" class="tableStyle">
        <thead>
        <tr>
            <td>序号</td>
            <th>所属城市</th>
            <th>所属经营单位</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cityList}" var="list" varStatus="row">
            <tr>
                <td><b>${row.index+1}</b></td>
                <td>${list.cityName}</td>
                <td>${list.optName}</td>
                <td>
                    <a style="cursor: pointer" data-toggle="modal" data-target="#myModal"
                       onclick="initParm('${list.optId}','${list.cityId}','${list.cityName}')"><spring:message
                            code="common_update"/></a>
                    <a onClick="javascript:if (confirm('确定删除吗？')) location.href='../BaseData/delProjectCity.html?cityId=${list.cityId}';else return;"
                       style="cursor: pointer"><spring:message code="common_delete"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--<m:pager pageIndex="${webPage.pageIndex}"--%>
    <%--pageSize="${webPage.pageSize}"--%>
    <%--recordCount="${webPage.recordCount}"--%>
    <%--submitUrl="${pageContext.request.contextPath }../seller/typeList.html?pageIndex={0}"/>--%>
</div>
</diy>
</div>
</div>
<%@ include file="../../main/foolter.jsp" %>
</div>

<!-- 模态框（Modal） -->
<form class="modal-content" name="altProjectCity" id="altProjectCity"
      action="../BaseData/altProjectCity.html">
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">区域城市信息</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <input type="hidden" name="cityId" id="cityId">
                        <div class="col-md-2"><b>所属经营单位:</b></div>
                        <div class="col-md-3">
                            <select class="form-control" id="optId" name="optId" autocomplete="off">
                                <option value="0">请选择经营单位</option>
                                <c:forEach items="${operationList}" var="list">
                                    <option value="${list.key}"
                                            <c:if test="${list.key eq areaId}">selected</c:if>>${list.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2"><b>所属城市名:</b></div>
                        <div class="col-md-3"><input type="text" class="form-control" id="cityName" name="cityName" onkeyup="this.value=this.value.replace(/\s*/g,'');" >
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" onclick="return checkCityName()">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
    function initParm(optId, cityId, cityName) {
        $('#cityName').val(cityName);
        $('#cityId').val(cityId);
        if (optId == '') {
            $('#optId').val('0');
        } else {
            $('#optId').val(optId);
        }
    }
    function clearParm() {
        $('#cityName').val('');
        $('#cityId').val('');
//        $('#optId').val('0');
    }
    function checkCityName() {
        var state = '0';
        var cityName = $('#cityName').val();
        $.ajax({
            url: '/BaseData/chekProjectCity',
            data: {
                cityName: cityName
            },
            type: 'get',
            async: false,
            dataType: 'json',
            success: function (result) {
                state = result.data;
            }
        });
        if (state == '0') {
            alert("该城市已存在");
            return false;
        }
    }
</script>
</html>
