<%--
  安全知识库管理页面
  Created by IntelliJ IDEA.
  User: yuanyn
  Date: 2017/6/7
  Time: 14:30
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
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <script>
        $(function () {
            $("#007400010000").addClass("active");
            $("#007400010000").parent().parent().addClass("in");
            $("#007400010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <style>
        label.error {
            margin-left: 1%;
            color: red;
        }
    </style>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007400010000" username="${authPropertystaff.staffName}"/>


    <div class="forms" style="overflow: hidden;margin-bottom: 20px;">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../dlectronizationGuide/initElectronizationGuide.html">
                    <%-- 文件名 --%>
                    <div class="col-lg-12">
                    <div class="form-group  col-lg-4">
                        <label for="fileName" class="col-sm-4 control-label">章节标题：</label>
                        <div class="col-sm-8">
                            <input type="text" id="fileName" name="fileName" value="${electronizationGuideDTO.fileName}"
                                   class="form-control">
                        </div>
                    </div>
                    <%-- 开始时间--%>
                    <div class="form-group  col-lg-4">
                        <label for="createTime" class="col-sm-4 control-label">创建时间：</label>
                        <div class="input-group date form_date col-sm-8" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2">
                            <input type="text" class="form-control" placeholder="请输入时间" path="createTime"
                                   id="createTime"
                                   name="createTime" value="${electronizationGuideDTO.createTime}" readonly="true"/>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    </div>
                        <div class="col-md-4 col-md-offset-4">
                            <c:if test="${function.qch40010128 eq 'Y'}">
                                <button type="submit" class="btn btn-primary">搜索</button>
                            </c:if>
                            <c:if test="${function.qch40010129 eq 'Y'}">
                                 <%--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">添加电子化指引--%>
                                 <button type="button" onclick="addFile()" class="btn btn-primary">添加电子化指引</button>
                            </c:if>
                        </div>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
                <tr>
                    <td><spring:message code="mall.serialNumber"/></td>
                    <th>章节标题</th>
                    <th>创建时间</th>
                    <th>文档大小</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${electronizationGuideDTOS}" var="list" varStatus="row">
                <tr>
                    <td><b>${(webPage.pageIndex-1)*20+row.index + 1}</b></td>
                    <td><a href="${list.path}" target="_blank">${list.fileName}</a></td>
                    <td>${list.createTime}</td>
                    <td>${list.size}</td>
                    <td>
                        <c:if test="${function.qch40010130 eq 'Y'}">
                            <a href="javascript:void(0)" onclick="updateFile('${list.id}','${list.fileName}')" class="a3">修改</a>
                        </c:if>
                        <c:if test="${function.qch40010131 eq 'Y'}">
                            <a href="javascript:void(0)" onclick="delFile('${list.id}')" class="a3">删除</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/dlectronizationGuide/initElectronizationGuide.html?pageIndex={0}&fileName=${electronizationGuideDTO.fileName}&createTime=${electronizationGuideDTO.createTime}"/>
    </div>
</div>
</div>
</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form class="modal-content" name="form1" id="form1" action="" method="post" enctype="multipart/form-data">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel2"></h4>
            </div>
            <div class="modal-body" style="overflow: hidden;">
                <div class="form-group  col-lg-12">
                    <div class="form-group  col-lg-6">
                        <label for="fileName1" class="col-sm-4 control-label"><h5>章节标题：</h5></label>
                        <div class="col-sm-8">
                            <input type="text" id="fileName1" name="fileName1" value=""
                                   class="form-control">
                            <input type="hidden" id="id1" name="id1" value="">
                        </div>
                    </div>
                </div>
                <div class="form-group  col-lg-12">
                    <div class="form-group  col-lg-6">
                        <label class="col-sm-4 control-label"><h5>上传附件：</h5></label>
                        <div class="col-sm-8">
                            <input type="file" class="form-control" name="file" id="upload">
                            <p style="color: red"><span>只能添加PDF格式文件</span></p>
                        </div>
                    </div>
                </div>
                <div class="modal-footer col-md-3 col-md-offset-3" style="border: none">
                    <a class="btn btn-primary" onclick="importWord()" id="uploadButton"></a>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script type="text/javascript">

    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
</script>
</body>
<script type="text/javascript">
    function importWord() {
        //检验导入的文件是否为PDF文件
        var filePath = document.getElementById("upload").value;
        var fileName = document.getElementById("fileName1").value;
        var id = document.getElementById("id1").value;
        if(fileName == null || fileName==''){
            alert("请输入章节标题");
            return false;
        }
        if (id =='' && (filePath == null || filePath == '')) {
            alert("请选择要上传的PDF文件");
            return false;
        } else if(id ==''){
            var fileExtend = filePath.substring(filePath.lastIndexOf('.')).toLowerCase();
            if (fileExtend == '.pdf') {
            } else {
                alert("文件格式需为'.pdf格式'");
                return false;
            }
        }
        document.getElementById("form1").action = "../dlectronizationGuide/addOrUpdateElectronizationGuide";
        document.getElementById("form1").submit();
    }

    function delFile(id) {
        if (confirm("删除后，该数据将无法恢复，是否继续？")) {
            $.ajax({
                url:"../dlectronizationGuide/deleteElectronizationGuide",
                type:"POST",
                async:false,
                data:{
                    id1:id,
                    state:'0'
                },
                error:function(){
                    alert("网络异常，可能服务器忙，请刷新重试");
                },
                success:function(data){
                    if(data.error == "0"){
                        alert("操作成功！");
                    }else if(data.error == "-1"){
                        alert("操作失败，请联系管理员！");
                    }else {
                        alert(data.error);
                    }
                }
            });
            window.location.reload();
        } else {
            return;
        }
    }

    function updateFile(id,fileName) {
        document.getElementById('id1').value=id;
        document.getElementById('fileName1').value=fileName;
        if(id==''){
        }else{
            document.getElementById('myModalLabel2').innerHTML="修改电子化指引";
            document.getElementById('uploadButton').innerHTML="修改";
        }
        $('#myModal').modal('show')
    }
    $('#myModal').on('hidden.bs.modal', function (e) {
        document.getElementById('id1').value='';
        document.getElementById('fileName1').value='';
        document.getElementById('myModalLabel2').innerHTML='';
        document.getElementById('uploadButton').innerHTML='';
    })
    function addFile(){
        document.getElementById('myModalLabel2').innerHTML="创建电子化指引";
        document.getElementById('uploadButton').innerHTML="添加";
        $('#myModal').modal('show')
    }

</script>
</html>
