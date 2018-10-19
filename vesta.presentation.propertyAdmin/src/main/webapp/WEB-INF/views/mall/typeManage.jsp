<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/2/2
  Time: 12:07
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
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <style>
        label,label.error {
            margin-left: 1%;
            color: red;
        }
    </style>
    <script type="text/javascript">
            function alt(typeId,typeName,id1,id2,id3,id4,id5) {
                if(!CheckNull(typeName,"请输入分类名称！")){
                    return false;
                }
                if("" == typeName){
                    $("#"+typeId+8).html("请输入分类名称！");
                    return;
                }
                if(typeName.length>10){
                    $("#"+typeId+8).html("请勿超过20个字！");
                    return;
                }
                $("#"+typeId+8).html("");
                var url = "../seller/altType";
                var data = {typeId: typeId, typeName: typeName};
                $.ajax({
                    type: "post",
                    async: "false",  //同步请求
                    url: url,
                    data: data,
                    dataType: "json",
                    timeout: 1000,
                    success: function (datas) {
                        var code = datas.code;
                        if (code != 0) {
                            var errorMessage = json.msg;
                            alert(errorMessage);
                        } else {
                            var data1 = datas.data;
                            $("#" + id1).html(data1.typeName);//要刷新的div
                        }
                    },
                    error: function () {
                        alert("修改失败，请稍后再试！");
                        return;
                    }
                });
                tog(typeId, id1, id2, id3, id4, id5)
            }
            function tog(idx,id1,id2,id3,id4,id5){
                $("#"+idx).toggle();
                $("#"+id1).toggle();
                $("#"+id2).toggle();
                $("#"+id3).toggle();
                $("#"+id4).toggle();
                $("#"+id5).toggle();
            }

            $().ready(function() {
                $("#addType").validate({
                    rules: {
                        typeName: {
                            required: true,
                            minlength: 1,
                            maxlength: 20
                        }
                    },
                    messages: {
                        typeName: {
                            required: "请输入分类名称！",
                            minlength: "不能少于1个字符！",
                            maxlength: "请勿超过20个字！"
                        }
                    }
                });
                $("altType").validate({
                    rules: {
                        typeName: {
                            required: true,
                            minlength: 1,
                            maxlength: 20
                        }
                    },
                    messages: {
                        typeName: {
                            required: "请输入分类名称！",
                            minlength: "不能少于1个字符！",
                            maxlength: "请勿超过20个字！"
                        }
                    }
                })
            });
    </script>

</head>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001000030000" username="${propertystaff.staffName}" />
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <form class="form-horizontal" name="form1" id="addType" action="../seller/addType.html">
                    <div class="form-group  col-lg-5">
                        <div class="col-sm-12">
                            <input type="text" name="typeName" class="form-control">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message code="common_add" /></button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="table-responsive bs-example widget-shadow">
    <table width="100%" class="tableStyle">
        <thead>
        <tr>
            <td><spring:message code="mall.serialNumber" /></td>
            <th><spring:message code="mall.typeName" /></th>
            <th><spring:message code="mall.operate" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="list" varStatus="row">
            <tr>
                <td><b>${row.index+1}</b></td>
                <td><span id="${list.typeId}1">${list.typeName}</span>
                    <input type="text" name="typeName" id="${list.typeId}" value="${list.typeName}" style="display: none"><label id="${list.typeId}8"></label></td>
                <td>
                    <a id="${list.typeId}2" onClick="tog('${list.typeId}','${list.typeId}'+1,'${list.typeId}'+2,'${list.typeId}'+3,'${list.typeId}'+4,'${list.typeId}'+5)"
                       style="margin:0;color: #000000;cursor: pointer"><span class="glyphicon glyphicon-edit"><spring:message code="common_update" /></span></a>
                    <a id="${list.typeId}3" onClick="javascript:if (confirm('如果删除，该分类下的商户也将全被删除，确定删除吗？')) location.href='../seller/delType.html?typeId=${list.typeId}';else return;" style="margin:0;color: #000000;cursor: pointer"><span class="glyphicon glyphicon-remove"><spring:message code="common_delete" /></span></a>
                    <a type="button" class="btn btn_default" id="${list.typeId}4" onClick="alt('${list.typeId}',$(${list.typeId}).val(),'${list.typeId}'+1,'${list.typeId}'+2,'${list.typeId}'+3,'${list.typeId}'+4,'${list.typeId}'+5)"
                       style="display: none;cursor: pointer"><spring:message code="common_confirm" /></a>
                    <a type="button" class="btn btn_default" id="${list.typeId}5" onClick="tog('${list.typeId}','${list.typeId}'+1,'${list.typeId}'+2,'${list.typeId}'+3,'${list.typeId}'+4,'${list.typeId}'+5)"
                       style="display: none;cursor: pointer"><spring:message code="common_cancel" /></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <m:pager pageIndex="${webPage.pageIndex}"
             pageSize="${webPage.pageSize}"
             recordCount="${webPage.recordCount}"
             submitUrl="${pageContext.request.contextPath }../seller/typeList.html?pageIndex={0}"/>
</div>
</diy>
</div>
</div>
<%@ include file="../main/foolter.jsp" %>
</div>
</body>
</html>
