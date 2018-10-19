<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/5/30
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
    <style>
        label.error {
            /*position: absolute;*/
            /*margin-top: -74px;*/
            /*display: block;*/
            margin-left: 1%;
            color: red;
        }
    </style>
    <script type="application/javascript">
        $(function() {
            $("#form1").validate({
                rules: {
                    caseName: {
                        required: true,
                        minlength: 1,
                        maxlength: 20
                    },
                    standDesc: {
                        required: true,
                        minlength: 3,
                        maxlength: 500
                    },
                    recodeDesc: {
                        required: true,
                        minlength: 1,
                        maxlength:300
                    }
                },
                messages: {
                    caseName: {
                        required: "请输入批次名称！",
                        minlength: "不能少于1个字符！",
                        maxlength: "请勿超过20个字！"
                    },
                    standDesc: {
                        required: "请输入旁站要点！",
                        minlength: "不能少于3个字符！",
                        maxlength: "请勿超过500个字符！"
                    },
                    recodeDesc: {
                        required: "请输入文字说明！",
                        minlength: "不能少于1个字符",
                        maxlength: "请勿超过300个字"
                    }
                }
            })
            $("#form2").validate({
                rules: {
                    imageUrl: {
                        required: true,
                    },
                    recodeDesc: {
                        required: true,
                        minlength: 1,
                        maxlength:300
                    }
                },
                messages: {
                    imageUrl: {
                        required: "请选择文件！"
                    },
                    recodeDesc: {
                        required: "请输入文字说明！",
                        minlength: "不能少于1个字符",
                        maxlength: "请勿超过300个字"
                    }
                }
            })
        });
    </script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005400020000" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <form class="form-horizontal" action="../stand/addStand.html" name="form1" id="form1">

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label">批次名称：</label>

                        <div class="col-sm-9">
                            <c:choose>
                                <c:when test="${flag==2}"><input type="text" class="form-control" name="caseName" value="${stand.caseName}"></c:when>
                                <c:otherwise><label class="control-label">${stand.caseName}</label></c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label">所属项目：</label>

                        <div class="col-sm-9">
                            <c:choose>
                                <c:when test="${flag==2}"><input type="text" class="form-control" name="projectId" value="${stand.projectName}"></c:when>
                                <c:otherwise><label class="control-label">${stand.projectName}</label></c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label">旁站日期：</label>

                        <div class="col-sm-9">
                            <label class="control-label">${stand.standTime}</label>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label">旁站人员：</label>

                        <div class="col-sm-9">
                            <label class="control-label">${stand.standUser}</label>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label">旁站要点：</label>

                        <div class="col-sm-9">
                            <c:choose>
                                <c:when test="${flag==2}"><input type="text" class="form-control" name="standDesc" value="${stand.standDesc}"></c:when>
                                <c:otherwise><label class="control-label">${stand.standDesc}</label></c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="form-group  col-lg-12">
                        <label class="col-sm-3 control-label">旁站记录：</label>

                        <div class="col-sm-9">
                            <c:forEach items="${stand.recodeList}" var="list">
                                <label>${list.createTime}</label>
                                <img src="${list.imageUrl}" width="120px" height="100px">
                                <c:choose>
                                    <c:when test="${flag==2}"><input type="text" class="form-control" name="recodeDesc" value="${list.recodeDesc}"></c:when>
                                    <c:otherwise><label class="control-label">${list.recodeDesc}</label></c:otherwise>
                                </c:choose>
                                <div class="clearfix"></div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="text-center">
                        <c:if test="${flag==2}"><button type="submit" class="btn btn-primary">保存</button></c:if>
                        <button type="button" class="btn btn-primary" for="propertySearch" data-toggle="modal" data-target="#myModal">增加旁站记录</button>
                        <a href="../stand/standList.html" type="button" class="btn btn-primary" for="propertySearch">返回</a>
                    </div>
                </form>
            </div>
        </div>
    </div>


</div>
</div>
</div>
</div>

<%@ include file="../main/foolter.jsp" %>
</div>

<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-lg">
        <form action="../stand/addRecode.html" name="form2" id="form2" class="form-inline">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新建旁站记录</h4>
                </div>
                <div class="modal-body">
                    <div class="highlight">
                        <div class="form-group">
                            <input type="file" name="imageUrl">
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="">添加文字描述：</label>
                            <textarea name="recodeDesc" rows="3"></textarea>
                        </div>
                    </div>
                    <input type="hidden" name="standId" value="${stand.standId}">
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

