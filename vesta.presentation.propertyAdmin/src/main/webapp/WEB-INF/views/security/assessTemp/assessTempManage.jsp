<%--
考核模板管理
  Created by IntelliJ IDEA.
  User: Jason
  Date: 2017/6/15
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <!-- Custom CSS -->
    <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>
    <style>
        label.error {
            margin-left: 1%;
            color: red;
        }
        .form-body{
            height: 66px;
        }
    </style>

    <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <%--<link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">--%>
    <!-- js-->
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        $(function () {
            $("#006300020000").addClass("active");
            $("#006300020000").parent().parent().addClass("in");
            $("#006300020000").parent().parent().parent().parent().addClass("active");
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
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="006300020000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="/assess/assessTempManage.html">
                    <div class="form-group  col-lg-4">
                        <label for="domain" class="col-sm-5 control-label">所属模块：</label>
                        <div class="col-sm-7">
                            <select name="domain" id="domain" class="form-control">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${assessTemp.domain eq '1'}">selected</c:if> >区域 </option>
                                <option value="2" <c:if test="${assessTemp.domain eq '2'}">selected</c:if>>项目公司</option>
                                <option value="3" <c:if test="${assessTemp.domain eq '3'}">selected</c:if>>监理</option>
                                <option value="4" <c:if test="${assessTemp.domain eq '4'}">selected</c:if>>施工单位</option>
                            </select>
                        </div>
                    </div>
                    <c:if test="${function.sth40020046 eq 'Y'}">
                        <button type="submit" class="btn btn-primary">搜索</button>
                    </c:if>
                    <c:if test="${function.sth40020047 eq 'Y'}">
                        <button type="button" class="btn btn-primary" onclick="downloadModel()">下载模板</button>
                    </c:if>
                    <c:if test="${function.sth40020048 eq 'Y'}">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">导入模板
                        </button>
                    </c:if>
                    <c:if test="${function.sth40020049 eq 'Y'}">
                        <button type="button" class="btn btn-primary" onclick="exportExcel()">导出Excel</button>
                    </c:if>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td width="3%"><spring:message code="mall.serialNumber"/></td>
                <th width="5%">所属模块</th>
                <th width="5%">考核项目</th>
                <th width="3%">考核分数</th>
                <th width="3%">排序号</th>
                <th width="15%">考核内容</th>
                <th width="3%">级别</th>
                <th width="15%">扣分原则</th>
                <%--<th>操作</th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${assessTempList}" var="list" varStatus="row">
                <tr>
                    <td height="40px" style="text-align: center"><b>${(webPage.pageIndex-1)*20+row.index+1}</b></td>
                    <td height="40px" class="ellipsis message-title" title="${list.domain}">${list.domain}</td>
                    <td height="40px" class="ellipsis message-title" title="${list.assessmentName}">${list.assessmentName}</td>
                    <td height="40px" class="ellipsis message-title" title="${list.assessmentScore}">${list.assessmentScore}分</td>
                    <td height="40px" class="ellipsis message-title" title="${list.contentNumber}">${list.contentNumber}</td>
                    <td height="40px" class="ellipsis message-title" title="${list.assessmentContent}">${list.assessmentContent}</td>
                    <td height="40px" class="ellipsis message-title" title="${list.grade}">${list.grade}</td>
                    <td height="40px" class="ellipsis message-title" title="${list.deductionPrinciple}">${list.deductionPrinciple}</td>
                    <%--<td>--%>
                        <%--<a href="">设置</a>--%>
                        <%--<a style="cursor: pointer" onclick="delcfm()" href="" class="a3"><span class="span1">删除</span></a>--%>
                    <%--</td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }../assess/assessTempManage.html?pageIndex={0}&domain=${assessTemp.domain}"/>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <form class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span
                            class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel2">添加集团</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group  col-lg-12">
                        <label class="col-sm-2 control-label">所属模块</label>
                        <div class="col-sm-6">
                            <select name="domain" id="domains" class="form-control">
                                <option value="1">区域</option>
                                <option value="2">项目公司</option>
                                <option value="3">监理</option>
                                <option value="4">施工单位</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer" style="border: none">
                        <button onclick="myfile.click()" type="button" class="btn btn-primary">导入</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </form>
            <%-- 导入excel 隐藏 --%>
            <form action="../assess/importAssessTempExcel" ,name="frm" id="frm" method="post"
                  enctype="multipart/form-data">
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

                } else if (!StringUtil.isEmpty(message)) {
                    sess.setAttribute("result", "");
                %>
                <script type="text/javascript">
                    alert("<%=message %>");
                    <% }%>
                </script>
            </form>
            <div class="row" style="height: 10px"></div>
        </div>
    </div>

</div>
</div>
</div>
</div>
<!-- main content end-->


<%@ include file="../../main/foolter.jsp" %>
</div>

<script>
    function importExcel() {
        //检验导入的文件是否为Excel文件
        var excelPath = document.getElementById("myfile").value;
        if (excelPath == null || excelPath == '') {
            alert("请选择要上传的Excel文件");
            return;
        } else {
            var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();
            if (fileExtend == '.xls' || fileExtend == '.xlsx') {
                if (confirm("确认要导入'" + excelPath + "'？")) {
                    document.getElementById("frm").action = "../assess/importAssessTempExcel?domain=" + $('#domains option:selected').val();
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
    function exportExcel() {
        var href = "../assess/exportAssessmentTempExcel?domain=" + $('#domain option:selected').val();
        window.location.href = href;
    }
    function downloadModel() {
//        if (confirm("是否下载当前模块模板？")) {
            var href = "../assess/exportModel";
            window.location.href = href;
//        } else {
//            return;
//        }
    }
</script>
</body>
</html>

