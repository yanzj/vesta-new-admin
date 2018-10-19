<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        $(function () {
            $("#007900010000").addClass("active");
            $("#007900010000").parent().parent().addClass("in");
            $("#007900010000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <%--<link rel="stylesheet" href="../static/css/jquery-ui.min.css" />
    <script src="../static/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>--%>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
    <script src="../static/js/echarts.js"></script>
    <style>

        .out {
            border-top: 45.67px #5c92c1 solid;
            width: 0px;
            height: 0px;
            border-left: 133px #06315d solid;
            position: relative;
        }

        b {
            font-style: normal;
            display: block;
            position: absolute;
            top: -20px;
            left: -110px;
        }

        em {
            font-style: normal;
            display: block;
            position: absolute;
            top: -38px;
            left: -75px;
            min-width: 85px;
        }

        .measureColorDiv {
            overflow: hidden;
            margin-top: 8px;
        }

        .measureColorSpan {
            display: block;
            overflow: hidden;
            float: left;
            margin-right:10px
        }
    </style>
    <%-- zTree --%>
    <link rel="stylesheet" href="../static/css/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../static/js/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" src="../static/js/jquery.ztree.core-3.5.js"></script>
    <script src="../static/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exedit-3.5.js"></script>
    <script src="../static/js/jquery.ztree.exhide-3.5.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="007900010000" username="${authPropertystaff.staffName}"/>
    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <div class="form-body">
                <div class="form-marginL">
                    <form class="form-horizontal" action="../measure/measureList.html" method="get">
                        <%--区域--%>
                        <div class="form-group  col-lg-4">
                            <label for="regionId" class="col-sm-4 control-label">区域:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="regionId" name="regionId">
                                    <c:forEach items="${agencyEntities}" var="agency">
                                        <c:if test="${agency.level eq '2'}">
                                            <option value="${agency.agencyId}"
                                                    <c:if test="${agency.agencyId eq measureDTO.regionId}">selected</c:if>>${agency.agencyName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="hidden" id="regionId_hidden">
                        </div>
                        <%--城市--%>
                        <div class="form-group  col-lg-4">
                            <label for="cityId" class="col-sm-4 control-label">城市:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="cityId" name="cityId">
                                </select>
                            </div>
                            <input type="hidden" id="cityId_hidden">
                        </div>
                        <%--项目--%>
                        <div class="form-group  col-lg-4">
                            <label for="projectId" class="col-sm-4 control-label">项目:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="projectId" name="projectId">
                                </select>
                            </div>
                            <input type="hidden" id="projectId_hidden" value="${measureDTO.projectId}">
                        </div>

                        <%--楼栋--%>
                        <div class="form-group  col-lg-4">
                            <label for="buildingId" class="col-sm-4 control-label">楼栋:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="buildingId" name="buildingId">
                                </select>
                            </div>
                            <input type="hidden" id="buildingId_hidden" value="${measureDTO.buildingId}">
                        </div>
                        <%--楼层--%>
                        <div class="form-group  col-lg-4">
                            <label for="floorId" class="col-sm-4 control-label">楼层:</label>
                            <div class="col-sm-8">
                                <select class="form-control" placeholder="" id="floorId" name="floorId">
                                </select>
                            </div>
                            <input type="hidden" id="floorId_hidden" value="${measureDTO.floorId}">
                        </div>
                        <input type="hidden" name="projectName" id="projectName" value="${measureDTO.projectName}">
                        <input type="hidden" name="buildingName" id="buildingName" value="${measureDTO.buildingName}">
                        <input type="hidden" name="floorName" id="floorName" value="${measureDTO.floorName}">
                        <div class="clearfix"></div>
                        <div style="text-align: center; margin-top: 8px;">
                            <c:if test="${function.esh40020103 eq 'Y'}">
                                <button type="button" class="btn btn-primary" onclick="search()" for="propertySearch">
                                    <spring:message code="propertyCompany.propertySearch"/></button>
                            </c:if>
                            <c:if test="${function.esh40020106 eq 'Y'}">
                                <button type="button" data-toggle="modal" class="btn btn-primary" data-target="#myModal"
                                        onclick="addMeasure()" for="propertySearch">添加实测实量
                                </button>
                            </c:if>
                            <c:if test="${function.esh40020104 eq 'Y'}">
                                <button type="button" class="btn btn-primary" onclick="downloadModel()"
                                        for="propertySearch">下载模板
                                </button>
                            </c:if>
                            <c:if test="${function.esh40020107 eq 'Y'}">
                                <button type="button" data-toggle="modal" class="btn btn-primary"
                                        data-target="#qrCodeModal" onclick="getQrCodeState()" for="propertySearch">二维码配置
                                </button>
                            </c:if>
                            <c:if test="${function.esh40020108 eq 'Y'}">
                                <a href="javascript:void(0);" onclick="isExcel()" type="button" class="btn btn-primary"
                                   for="propertySearch">导出Excel</a>
                            </c:if>
                            <c:if test="${function.esh40020105 eq 'Y'}">
                                <button type="button" class="btn btn-primary" for="propertySearch" data-toggle="modal"
                                        data-target="#myModal2">导出全数据
                                </button>
                            </c:if>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <input type="hidden" id="size" value="${isExcel}"/>
        <div id="data_name" class="form-group  col-lg-7" hidden></div>
        <div id="data_color" class="form-group  col-lg-7" hidden></div>
        <!--柱状图-->
        <div id="data_table" class="table-responsive bs-example widget-shadow"
             style="padding-top: 16px;padding-left: 20px">
        </div>
        <!--列表-->
        <div class="table-responsive bs-example widget-shadow" style="padding-top: 16px;" id="table_div">
            <table width="100%" class="tableStyle" style="table-layout: fixed;" id="list_table">
                <thead>
                <tr>
                    <td style="border: none;padding: 0;width: 133px">
                        <div class="out">
                            <b><c:if test="${measureDataDTO.type eq '1'}">项目</c:if>
                                <c:if test="${measureDataDTO.type eq '2'}">楼栋</c:if>
                                <c:if test="${measureDataDTO.type eq '3'}">楼层</c:if></b>
                            <em>检查分项</em>
                        </div>
                    </td>
                    <td>
                        <c:if test="${measureDataDTO.type eq '1'}">项目总合格率</c:if>
                        <c:if test="${measureDataDTO.type eq '2'}">楼栋总合格率</c:if>
                        <c:if test="${measureDataDTO.type eq '3'}">楼层总合格率</c:if></td>
                    <td>混凝土结构工程</td>
                    <td>砌筑工程</td>
                    <td>抹灰工程</td>
                    <td>设备安装工程</td>
                    <td>涂饰工程</td>
                    <td>饰面砖、石材</td>
                    <td>室内门</td>
                    <td>门窗工程</td>
                    <td>木地板安装</td>
                    <td>防开裂</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td height="40px" style="text-align: center"><c:if
                            test="${measureDataDTO.type eq '1'}">${measureDTO.projectName}</c:if>
                        <c:if test="${measureDataDTO.type eq '2'}">${measureDTO.projectName}-${measureDTO.buildingName}</c:if>
                        <c:if test="${measureDataDTO.type eq '3'}">${measureDTO.projectName}-${measureDTO.buildingName}-${measureDTO.floorName}</c:if></td>
                    <td height="40px">${measureDataDTO.totalPercent}</td>
                    <td height="40px">${measureDataDTO.firstPercent}</td>
                    <td height="40px">${measureDataDTO.secondPercent}</td>
                    <td height="40px">${measureDataDTO.thirdPercent}</td>
                    <td height="40px">${measureDataDTO.fourthPercent}</td>
                    <td height="40px">${measureDataDTO.fifthPercent}</td>
                    <td height="40px">${measureDataDTO.sixth}</td>
                    <td height="40px">${measureDataDTO.seventhPercent}</td>
                    <td height="40px">${measureDataDTO.eighth}</td>
                    <td height="40px">${measureDataDTO.ninth}</td>
                    <td height="40px">${measureDataDTO.tenth}</td>
                </tr>
                </tbody>
            </table>
            <br>
            <br>
            <table width="100%" class="tableStyle" id="detail_table" style="table-layout: fixed;">
                <thead>
                <tr>
                    <td width="12%">序号</td>
                    <td width="8%">项目名称</td>
                    <td width="8%">楼栋</td>
                    <td width="8%">楼层</td>
                    <td width="8%">标段实测合格率</td>
                    <td width="8%">变更时间</td>
                    <td width="8%">操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${measureDetailDTOS}" var="measureDetail" varStatus="row">
                    <tr>
                        <td height="40px">${row.index+1}</td>
                        <td height="40px">${measureDetail.projectName}</td>
                        <td height="40px">${measureDetail.buildingName}</td>
                        <td height="40px">${measureDetail.floorName}</td>
                        <td height="40px">${measureDetail.qualificationRate}</td>
                        <td height="40px">${measureDetail.createDate}</td>
                        <td height="40px">
                            <c:if test="${function.esh40020109 eq 'Y'}">
                                <a href="../measure/measureDetail.html?measureId=${measureDetail.measureId}&projectId=${measureDetail.projectId}&buildingId=${measureDetail.buildingId}&floorId=${measureDetail.floorId}&type=1">详情</a>
                            </c:if>
                            <c:if test="${function.esh40020110 eq 'Y'}">
                                <a href="../measure/measureDetail.html?measureId=${measureDetail.measureId}&projectId=${measureDetail.projectId}&buildingId=${measureDetail.buildingId}&floorId=${measureDetail.floorId}&type=2">查看二维码</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <m:pager pageIndex="${webPage.pageIndex}"
                     pageSize="${webPage.pageSize}"
                     recordCount="${webPage.recordCount}"
                     submitUrl="${pageContext.request.contextPath }/measure/measureList.html?pageIndex={0}&regionId=${measureDTO.regionId}&cityId=${measureDTO.cityId}&projectId=${measureDTO.projectId}&buildingId=${measureDTO.buildingId}&floorId=${measureDTO.floorId}"/>
        </div>
    </div>
</div>
</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px;margin: 30px auto;min-height: 500px;height: 500px;">
        <div class="modal-content" style="height: 500px">
            <div class="modal-body" style="height: 500px">
                <br class="modal-body">
                <label style="font-size: 20px">添加实测实量</label>
                <hr style="height:1px;border:none;border-top:1px solid #555555;"/>
                <%--区域--%>
                <div class="form-group  col-lg-12">
                    <label for="regionId_model" class="col-sm-2 control-label">所属区域:</label>
                    <div class="col-sm-6">
                        <select class="form-control" placeholder="" id="regionId_model" name="regionId_model">
                            <c:forEach items="${agencyEntities}" var="agency">
                                <c:if test="${agency.level eq '2' && agency.agencyId ne '1'}">
                                    <option value="${agency.agencyId}"
                                            <c:if test="${agency.agencyId eq measureDTO.regionId}">selected</c:if>>${agency.agencyName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-4"><span id="regionId_model_error" style="color:red" class="err"></span></div>
                </div>
                <%--城市--%>
                <div class="form-group  col-lg-12">
                    <label for="cityId_model" class="col-sm-2 control-label">所属城市:</label>
                    <div class="col-sm-6">
                        <select class="form-control" placeholder="" id="cityId_model" name="cityId_model">
                        </select>
                    </div>
                    <div class="col-sm-4"><span id="cityId_model_error" style="color:red" class="err"></span></div>
                </div>
                <%--项目--%>
                <div class="form-group  col-lg-12">
                    <label for="projectId_model" class="col-sm-2 control-label">所属项目名称:</label>
                    <div class="col-sm-6">
                        <select class="form-control" placeholder="" id="projectId_model" name="projectId_model">
                        </select>
                    </div>
                    <div class="col-sm-4"><span id="projectId_model_error" style="color:red" class="err"></span></div>
                </div>
                <%--楼栋--%>
                <div class="form-group  col-lg-12">
                    <label for="buildingId_model" class="col-sm-2 control-label">楼栋:</label>
                    <div class="col-sm-6">
                        <select class="form-control" placeholder="" id="buildingId_model" name="buildingId_model">
                            <c:forEach items="${builds}" var="build">
                                <option value="${build.key}"
                                        <c:if test="${build.key eq measureDTO.buildingId}">selected</c:if>>${build.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-4"><span id="buildingId_model_error" style="color:red" class="err"></span></div>
                </div>
                <%--楼层--%>
                <div class="form-group  col-lg-12">
                    <label for="floorId_model" class="col-sm-2 control-label">楼层:</label>
                    <div class="col-sm-6">
                        <select class="form-control" placeholder="" id="floorId_model" name="floorId_model">
                            <c:forEach items="${floors}" var="floor">
                                <option value="${floor.key}"
                                        <c:if test="${floor.key eq measureDTO.floorId}">selected</c:if>>${floor.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-4"><span id="floorId_model_error" style="color:red" class="err"></span></div>
                </div>
                <%--检查分项--%>
                <div class="form-group  col-lg-12">
                    <label for="inspectionPhase_model" class="col-sm-2 control-label">检查分项:</label>
                    <div class="col-sm-6">
                        <select class="form-control" placeholder="" id="inspectionPhase_model"
                                name="inspectionPhase_model">
                            <c:forEach items="${inspectionPhases}" var="inspectionPhase">
                                <option value="${inspectionPhase.key}"
                                        <c:if test="${inspectionPhase.key eq measureDTO.inspectionPhaseId}">selected</c:if>>${inspectionPhase.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-4"><span id="inspectionPhase_model_error" style="color:red" class="err"></span>
                    </div>
                </div>
                <%--是否涉及单元--%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-2 control-label">是否涉及单元:</label>
                    <div class="col-sm-6" style="text-align: left">
                        <input type="checkbox" id="isUnit"/> <label>涉及</label>
                    </div>
                    <div class="col-sm-4"><span id="isUnit_error" style="color:red" class="err"></span></div>
                </div>
                <%--单元--%>
                <div class="form-group  col-lg-12" hidden id="unitId_model_div">
                    <label for="unitId_model" class="col-sm-2 control-label">单元:</label>
                    <div class="col-sm-6">
                        <select class="form-control" placeholder="" id="unitId_model" name="unitId_model">
                        </select>
                        <input type="text" id="unitId_model_null" hidden
                               style="text-align:left; float:left; width: 80%" placeholder="单元只能输入数字且最多能输入两位数字" maxlength="2">
                        <input type="button" id="unitId_model_null_a" onclick="cancel()" hidden value="取消">
                    </div>
                    <div class="col-sm-4"><span id="unitId_model_error" style="color:red" class="err"></span></div>
                </div>
                <%--上传实测实量--%>
                <div class="form-group  col-lg-12">
                    <label class="col-sm-2 control-label">上传实测实量:</label>
                    <div class="col-sm-6">
                        <input type="file" class="form-control" placeholder="" id="measureDetail"
                               name="measureDetail" value="" style="width: 100%;">
                    </div>
                    <div class="col-sm-4"><span id="measureDetail_error" style="color:red" class="err"></span></div>
                </div>
                <div class="form-group  col-lg-12">
                    <div class="col-sm-2 col-md-offset-4">
                        <button type="button" class="btn btn-primary" onclick="editMeasure()">确认</button>
                    </div>
                    <div class="col-sm-4">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="qrCodeModal" tabindex="-1" role="dialog"
     aria-labelledby="qrCodeModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;margin: 30px auto;min-height: 200px;height: 200px;">
        <div class="modal-content" style="height: 200px">
            <div class="modal-body" style="height: 200px">
                <br class="modal-body">
                <label style="font-size: 20px">二维码配置</label>
                <hr style="height:1px;border:none;border-top:1px solid #555555;"/>
                <!--二维码状态-->
                <div class="form-group  col-lg-12" id="isOpenQrCode_model_div">
                    <label for="isOpenQrCode_model" class="col-sm-4 control-label">二维码公开状态:</label>
                    <div class="col-sm-4" id="isOpenQrCode_model">
                        <input type="radio" name="isOpenQrCode" value="0" checked id="open">公开
                        <input type="radio" name="isOpenQrCode" value="1" style="margin-left: 20px" id="noOpen">不公开
                    </div>
                    <div class="col-sm-4"><span id="isOpenQrCode_model_error"></span></div>
                    <input type="hidden" id="isOpenQrCode" name="isOpenQrCode"/>
                </div>
                <div class="form-group  col-lg-12">
                    <div class="col-sm-2 col-md-offset-4">
                        <button type="button" class="btn btn-primary" onclick="qrCodeState()">确认</button>
                    </div>
                    <div class="col-sm-4">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- 模态框2（Modal）--%>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="    width: 800px;margin: 30px auto;min-height: 500px;height: 500px;">
        <div class="modal-content" style="  height: 500px;">
            <div class="modal-body" style="height: 500px;">
                <br class="modal-body">
                <label style="font-size: 20px">导出全数据</label>
                <hr style="height:1px;border:none;border-top:1px solid #555555;"/>
                <label style="font-size: 15px">请选择您要下载的区域、城市或项目</label>
                <div class="containerSelect1" style="overflow-y: scroll;  height: 330px;">
                    <div class="row ">
                        <div class="content_wrap2 col-md-6 ">
                            <div class="zTreeDemoBackground left">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group  col-lg-12">
                    <div class="col-sm-2 col-md-offset-4">
                        <button type="button" class="btn btn-primary" onclick="sub()">确认</button>
                    </div>
                    <div class="col-sm-4">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 模态框3（Modal）-->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px;margin: 30px auto;min-height: 500px;height: 500px;">
        <div class="modal-content" style="  height: 150px;">
            <div class="modal-body" style="height: 150px;  text-align:center;">
                <br class="modal-body">
                <label style="font-size: 20px">数据正在导出，请稍等</label>
                <hr>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="remove()">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- main content end-->
<%@ include file="../../main/foolter.jsp" %>
</div>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script>

    // zTree 的参数配置,深入使用请参考 API 文档(setting 配置详解)
    var setting = {
        check: {
            enable: true,
            chkboxType: {"Y": "s", "N": "s"}
        },
        data: {
            simpleData: {
                enable: true  //简单数据格式
            }
        }
    };
    var code;
    function showCode(str) {
        if (!code) code = $("#code");
        code.empty();
        code.append("<li>" + str + "</li>");
    }

    $(document).ready(function () {
        $.ajax({
            url: "../measure/getAllAgencyById",
            dataType: "json",
            success: function (result) {
                $.fn.zTree.init($("#treeDemo"), setting, result);
                var zt = $.fn.zTree.getZTreeObj("treeDemo");
                var nodes = zt.getNodes();
                var json=zt.transformToArray(nodes);
                for (var i = 0; i < json.length; i++) {
                    var node = json[i];
                    if('100000000' == node.agencyType){
                        node.nocheck = true; //如果是集团  则不显示checkbox
                        zt.updateNode(node);
                        break;
                    }
                }
            }
        });
        showCode('setting.check.chkboxType = { "Y" : "s", "N" : "s" };');
    });


    $("#regionId").change(function () {
        var regionId = $("#regionId").val();
        regionChange(regionId);
    });
    function regionChange(param) {
        $("#buildingId").val('');
        $("#floorId").val('');
        $("#cityId").val('');
        $("#projectId").val('');
        var regionId = param;
        var agency = new Array();
        agency = ${agencyEntities};
        var measureDTO = '${measureDTO}';
        var option = "";
        document.getElementById("cityId").innerHTML = "";
        if (regionId == '') {
            option = option + "<option value='' selected='selected'>" + "请选择" + "</option>";
        } else {
            if ($.trim(agency) && agency.length > 0) {
                for (var i = 0; i < agency.length; i++) {
                    if (agency[i].level == 3 && agency[i].parentId == regionId || ((agency[i].agencyId == '' || agency[i].agencyId == '1') && agency[i].level == 3)) {
                        if ($.isEmptyObject(measureDTO)) {
                            option = option + "<option value='' selected='selected'>" + "请选择" + "</option>";
                        } else {
                            if (measureDTO.cityId == agency[i].agencyId) {
                                option = option + "<option value='" + agency[i].agencyId + "'selected='selected'>" + agency[i].agencyName + "</option>";
                            } else {
                                option = option + "<option value='" + agency[i].agencyId + "'>" + agency[i].agencyName + "</option>";
                            }
                        }
                    } else if (regionId == '1') {//全部
                        if (agency[i].level == 3) {
                            if ($.isEmptyObject(measureDTO)) {
                                option = option + "<option value='' selected='selected'>" + "请选择" + "</option>";
                            } else {
                                if (measureDTO.cityId == agency[i].agencyId) {
                                    option = option + "<option value='" + agency[i].agencyId + "'selected='selected'>" + agency[i].agencyName + "</option>";
                                } else {
                                    option = option + "<option value='" + agency[i].agencyId + "'>" + agency[i].agencyName + "</option>";
                                }
                            }
                        }
                    }
                }
            }
        }
        $("#cityId").append(option);
    }

    $("#cityId").change(function () {
        var regionId = $("#regionId").val();
        var cityId = $("#cityId").val();
        cityChange(regionId, cityId);
    });
    function cityChange(param1, param2) {
        $("#buildingId").val('');
        $("#floorId").val('');
        $("#projectId").val('');
        var regionId = param1;
        var cityId = param2;
        var cityIds = new Array();
        var agency = new Array();
        agency = ${agencyEntities};
        var measureDTO = '${measureDTO}';
        var option = "";
        document.getElementById("projectId").innerHTML = "";
        if (cityId == '') {
            option = option + "<option value='' selected='selected'>" + "请选择" + "</option>";
        } else {
            if ($.trim(agency) && agency.length > 0) {
                if (cityId == '1') {
                    if (regionId == '1') {
                        for (var i = 0; i < agency.length; i++) {
                            if (agency[i].level == 4) {
                                if ($.isEmptyObject(measureDTO)) {
                                    option = option + "<option value='' selected='selected'>" + "请选择" + "</option>";
                                } else {
                                    if (measureDTO.projectId == agency[i].agencyId) {
                                        option = option + "<option value='" + agency[i].agencyId + "'selected='selected'>" + agency[i].agencyName + "</option>";
                                    } else {
                                        option = option + "<option value='" + agency[i].agencyId + "'>" + agency[i].agencyName + "</option>";
                                    }
                                }
                            }
                        }
                    } else {
                        for (var i = 0; i < agency.length; i++) {
                            if (agency[i].level == 3 && agency[i].parentId == regionId) {
                                cityIds.push(agency[i].agencyId);
                            }
                        }
                        if (cityIds != null && cityIds != undefined && cityIds.length > 0) {
                            option = option + "<option value=''>" + "请选择" + "</option>";
                            for (var j = 0; j < cityIds.length; j++) {
                                for (var i = 0; i < agency.length; i++) {
                                    if (agency[i].level == 4 && agency[i].parentId == cityIds[j]) {
                                        if ($.isEmptyObject(measureDTO)) {
                                            option = option + "<option value='' selected='selected'>" + "请选择" + "</option>";
                                        } else {
                                            if (measureDTO.projectId == agency[i].agencyId) {
                                                option = option + "<option value='" + agency[i].agencyId + "'selected='selected'>" + agency[i].agencyName + "</option>";
                                            } else {
                                                option = option + "<option value='" + agency[i].agencyId + "'>" + agency[i].agencyName + "</option>";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (var i = 0; i < agency.length; i++) {
                        if (agency[i].level == 4 && agency[i].parentId == cityId || (agency[i].agencyId == '' && agency[i].level == 4)) {
                            if ($.isEmptyObject(measureDTO)) {
                                option = option + "<option value='' selected='selected'>" + "请选择" + "</option>";
                            } else {
                                if (measureDTO.projectId == agency[i].agencyId) {
                                    option = option + "<option value='" + agency[i].agencyId + "'selected='selected'>" + agency[i].agencyName + "</option>";
                                } else {
                                    option = option + "<option value='" + agency[i].agencyId + "'>" + agency[i].agencyName + "</option>";
                                }
                            }
                        }
                    }
                }
            }
        }
        $("#projectId").append(option);
    }

    $("#projectId").change(function () {
        var projectId = $("#projectId").val();
        projectChange(projectId, '');
    });
    function projectChange(param, param1) {
        $("#buildingId").val('');
        $("#floorId").val('');
        var projectId = param;
        $.ajax({
            url: "../measure/getBuilds",
            type: "get",
            async: "false",
            data: {"projectId": projectId},
            dataType: "json",
            traditional: true,
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("buildingId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                if (param1 == prop) {
                                    option = option + "<option value='" + prop + "' selected='selected'>" + data[prop] + "</option>";
                                } else {
                                    option = option + "<option value='" + prop + "' >" + data[prop] + "</option>";
                                }
                            }
                        }
                        $("#buildingId").append(option);
                    }
                }
            }
        });
    }

    $("#buildingId").change(function () {
        $("#floorId").val('');
        var buildingId = $("#buildingId").val();
        buildingChange(buildingId, '')
    });
    function buildingChange(param, param1) {
        var buildingId = param;
        $.ajax({
            url: "../measure/getFloors",
            type: "get",
            async: "false",
            data: {"buildingId": buildingId},
            dataType: "json",
            traditional: true,
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("floorId").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                if (prop == param1) {
                                    option = option + "<option value='" + prop + "' selected='selected'>" + data[prop] + "</option>";
                                } else {
                                    option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                }
                            }
                        }
                        $("#floorId").append(option);
                    }
                }
            }
        });
    }

</script>
<script>
    $("#regionId_model").change(function () {
        $("#buildingId_model").val('');
        $("#floorId_model").val('');
        var regionId = $("#regionId_model").val();
        var agency = new Array();
        agency = ${agencyEntities};
        var measureDTO = '${measureDTO}';
        var option = "";
        document.getElementById("cityId_model").innerHTML = "";
        if (regionId == '') {
            option = option + "<option value='' selected='selected'>" + "请选择" + "</option>";
        } else {
            if ($.trim(agency) && agency.length > 0) {
                for (var i = 0; i < agency.length; i++) {
                    if (agency[i].level == 3 && agency[i].parentId == regionId || (agency[i].level == 3 && agency[i].agencyId == '')) {
                        if ($.isEmptyObject(measureDTO)) {
                            option = option + "<option value='' selected='selected'>" + "请选择" + "</option>";
                        } else {
                            if (measureDTO.cityId == agency[i].agencyId) {
                                option = option + "<option value='" + agency[i].agencyId + "'selected='selected'>" + agency[i].agencyName + "</option>";
                            } else {
                                option = option + "<option value='" + agency[i].agencyId + "'>" + agency[i].agencyName + "</option>";
                            }
                        }
                    }
                }
            }
        }
        $("#cityId_model").append(option);
    });

    $("#cityId_model").change(function () {
        var regionId = $("#regionId_model").val();
        var cityId = $("#cityId_model").val();
        var cityIds = new Array();
        var agency = new Array();
        agency = ${agencyEntities};
        var measureDTO = '${measureDTO}';
        var option = "";
        document.getElementById("projectId_model").innerHTML = "";
        if (cityId == '') {
            option = option + "<option value='' selected='selected'>" + "请选择" + "</option>";
        } else {
            if ($.trim(agency) && agency.length > 0) {
                for (var i = 0; i < agency.length; i++) {
                    if (agency[i].level == 4 && agency[i].agencyId != '' && agency[i].parentId == cityId || (agency[i].level == 4 && agency[i].agencyId == '')) {
                        if ($.isEmptyObject(measureDTO)) {
                            option = option + "<option value='' selected='selected'>" + "请选择" + "</option>";
                        } else {
                            if (measureDTO.projectId == agency[i].agencyId) {
                                option = option + "<option value='" + agency[i].agencyId + "'selected='selected'>" + agency[i].agencyName + "</option>";
                            } else {
                                option = option + "<option value='" + agency[i].agencyId + "'>" + agency[i].agencyName + "</option>";
                            }
                        }
                    }
                }
            }
        }
        $("#projectId_model").append(option);
    });

    $("#projectId_model").change(function () {
        var projectId = $("#projectId_model").val();
        $.ajax({
            url: "../measure/getBuilds",
            type: "get",
            async: "false",
            data: {"projectId": projectId},
            dataType: "json",
            traditional: true,
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("buildingId_model").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#buildingId_model").append(option);
                    }
                }
            }
        });
    });

    $("#buildingId_model").change(function () {
        var buildingId = $("#buildingId_model").val();
        $.ajax({
            url: "../measure/getFloors",
            type: "get",
            async: "false",
            data: {"buildingId": buildingId},
            dataType: "json",
            traditional: true,
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option = "";
                    if (data != null) {
                        document.getElementById("floorId_model").innerHTML = "";
                        for (var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                            }
                        }
                        $("#floorId_model").append(option);
                    }
                }
            }
        });
    });

    $("#isUnit").change(function () {
        isUnit();
    });
    function isUnit() {
        if ($("#isUnit").is(':checked')) {
            $("#unitId_model_div").show();
            $("#unitId_model").show();
            $("#unitId_model_null").hide();
            $("#unitId_model_null_a").hide();
            var buildingId = $("#buildingId_model").val();
            var floorId = $("#floorId_model").val();
            $.ajax({
                url: "../measure/getUnits",
                type: "get",
                async: "false",
                data: {
                    "buildingId": buildingId,
                    "floorId": floorId
                },
                dataType: "json",
                traditional: true,
                error: function () {
                    alert("网络异常，可能服务器忙，请刷新重试");
                },
                success: function (json) {
                    <!-- 获取返回代码 -->
                    var code = json.code;
                    if (code != 0) {
                        var errorMessage = json.msg;
                        alert(errorMessage);
                    } else {
                        <!-- 获取返回数据 -->
                        var data = json.data;
                        var option = "";
                        if (data != null) {
                            document.getElementById("unitId_model").innerHTML = "";
                            for (var prop in data) {
                                if (data.hasOwnProperty(prop)) {
                                    option = option + "<option value='" + prop + "'>" + data[prop] + "</option>";
                                }
                            }
                            $("#unitId_model").append(option);
                        }
                    }
                }
            });
        } else {
            $("#unitId_model_div").hide();
        }
    }

    $("#unitId_model").change(function () {
        var unitId = $("#unitId_model").val();
        if (unitId == 999) {
            $("#unitId_model").hide();
            $("#unitId_model_null").show();
            $("#unitId_model_null_a").show();
        }
    });

    $("#inspectionPhase_model").change(function () {
        inspectionPhaseModelChange();
    });
    function inspectionPhaseModelChange() {
        var projectId = $("#projectId_model").val();
        var buildingId = $("#buildingId_model").val();
        var floorId = $("#floorId_model").val();
        var inspectionPhaseId = $("#inspectionPhase_model").val();
        if (projectId == "" || projectId == null || projectId == undefined) {
            $("#projectId_model_error").html("请选择项目");
            return;
        }
        if (buildingId == "" || buildingId == null || buildingId == undefined) {
            $("#buildingId_model_error").html("请选择楼栋");
            return;
        }
        if (floorId == "" || floorId == null || floorId == undefined) {
            $("#floorId_model_error").html("请选择楼层");
            return;
        }
        if (inspectionPhaseId == "" || inspectionPhaseId == null || inspectionPhaseId == undefined) {
            $("#inspectionPhase_model_error").html("请选择检查分项");
            return;
        }
        $.ajax({
            url: "../measure/getIsUnit",
            type: "get",
            async: "false",
            data: {
                "projectId": projectId,
                "buildingId": buildingId,
                "floorId": floorId,
                "inspectionPhaseId": inspectionPhaseId
            },
            dataType: "json",
            traditional: true,
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    if ('true' == data) {
                        document.getElementById("isUnit").checked = true;
                        $("#isUnit").attr("disabled", "disabled");
                        isUnit();
                    } else {
                        $("#isUnit").removeAttr("disabled");
                        document.getElementById("isUnit").checked = false;
                        isUnit();
                    }
                }
            }
        });
    }

    function cancel() {
        $("#unitId_model").show();
        $("#unitId_model").val('0');
        $("#unitId_model_null").hide();
        $("#unitId_model_null_a").hide();
    }
    function qrCode(buildingId, floorId) {
        $.ajax({
            url: '../measure/getQrCodeByBuildingIdAndFloorId?buildingId=' + buildingId + '&floorId=' + floorId,
            type: 'GET',
            cache: false,
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.data == "") {
                    alert("该楼层下未上传二维码");
                } else {
                    alert(data.data);
                }
            }
        })
    }
</script>
<script type="text/javascript">
    var myChart;
    function editMeasure() {
        document.getElementById('regionId_model_error').innerHTML = "";
        document.getElementById('cityId_model_error').innerHTML = "";
        document.getElementById('projectId_model_error').innerHTML = "";
        document.getElementById('buildingId_model_error').innerHTML = "";
        document.getElementById('floorId_model_error').innerHTML = "";
        document.getElementById('measureDetail_error').innerHTML = "";
        document.getElementById('inspectionPhase_model_error').innerHTML = "";
        var regionId = $("#regionId_model").val();
        var regionName = $("#regionId_model").find("option:selected").text();
        var cityId = $("#cityId_model").val();
        var cityName = $("#cityId_model").find("option:selected").text();
        var projectId = $("#projectId_model").val();
        var projectName = $("#projectId_model").find("option:selected").text();
        var buildingId = $("#buildingId_model").val();
        var buildingName = $("#buildingId_model").find("option:selected").text();
        var floorId = $("#floorId_model").val();
        var floorName = $("#floorId_model").find("option:selected").text();
        var isOpenQrCode = $("#isOpenQrCode").val();
        if (isOpenQrCode == '' || isOpenQrCode == null || isOpenQrCode == undefined) {
            isOpenQrCode = $("input[name='isOpenQrCode']:checked").val();
        }
        var inspectionPhaseId = $("#inspectionPhase_model").val();
        var inspectionPhaseName = $("#inspectionPhase_model").find("option:selected").text();
        var unitId = '';
        var unitName = '';
        if ($("#isUnit").is(':checked')) {
            var unitId_model = $("#unitId_model").val();
            if (unitId_model == 999) {
                unitName = $("#unitId_model_null").val();
                var numRegex =  /^[0-9]+.?[0-9]*$/;
                if (numRegex.test(unitName)) {

                }else{
                    alert("单元只能输入数字")
                    return;
                }
            } else if (unitId_model == 0) {
                alert("该检查分项已存在单元，请填写单元信息");
                return;
            } else {
                unitId = $("#unitId_model").val();
                unitName = $("#unitId_model").find("option:selected").text();
            }
        }
        var measureDetail = document.getElementById('measureDetail').files[0];
        if (regionId == "" || regionId == null || regionId == undefined) {
            $("#regionId_model_error").html("请选择区域");
            return;
        }
        if (cityId == "" || cityId == null || cityId == undefined) {
            $("#cityId_model_error").html("请选择城市");
            return;
        }
        if (projectId == "" || projectId == null || projectId == undefined) {
            $("#projectId_model_error").html("请选择项目");
            return;
        }
        if (buildingId == "" || buildingId == null || buildingId == undefined) {
            $("#buildingId_model_error").html("请选择楼栋");
            return;
        }
        if (floorId == "" || floorId == null || floorId == undefined) {
            $("#floorId_model_error").html("请选择楼层");
            return;
        }
        if (inspectionPhaseId == "" || inspectionPhaseId == null || inspectionPhaseId == undefined) {
            $("#inspectionPhase_model_error").html("请选择检查分项");
            return;
        }
        if (measureDetail == "" || measureDetail == null || measureDetail == undefined) {
            $("#measureDetail_error").html("导入文件不能为空");
            return;
        } else {
            var filePath = document.getElementById("measureDetail").value;
            var fileExtend = filePath.substring(filePath.lastIndexOf('.')).toLowerCase();
            if (fileExtend == '.xlsx') {
            } else {
                alert("请添加Excle文件'");
                return false;
            }
        }
        var fd = new FormData();
        fd.append('regionId', regionId);
        fd.append('regionName', regionName);
        fd.append('cityId', cityId);
        fd.append('cityName', cityName);
        fd.append('projectId', projectId);
        fd.append('projectName', projectName);
        fd.append('buildingId', buildingId);
        fd.append('buildingName', buildingName);
        fd.append('floorId', floorId);
        fd.append('floorName', floorName);
        fd.append('unitId', unitId);
        fd.append('unitName', unitName);
        fd.append('measureDetail', measureDetail);
        fd.append('isOpenQrCode', isOpenQrCode);
        fd.append('inspectionPhaseId', inspectionPhaseId);
        fd.append('inspectionPhaseName', inspectionPhaseName);
        $.ajax({
            url: '../measure/editMeasure.html',
            type: 'POST',
            data: fd,
            processData: false,  //tell jQuery not to process the data
            contentType: false,  //tell jQuery not to set contentType
            //这儿的三个参数其实就是XMLHttpRequest里面带的信息。
            success: function (data) {
                var data1 = eval('(' + data + ')');
                if (data1[0].success == '0') {
                    if (data1[0].msg != null && data1[0].msg != undefined && data1[0].msg != '') {
                        alert(data1[0].msg)
                        $("#myModal").modal('hide')
                    } else {
                        alert("添加成功")
                        $("#myModal").modal('hide')
                    }
                } else {
                    alert(data1[0].error)
                }
            }
        })
    }
    function search() {
        var regionId = $("#regionId").val();
        var regionName = $("#regionId").find("option:selected").text();
        var cityId = $("#cityId").val();
        var cityName = $("#cityId").find("option:selected").text();
        var projectId = $("#projectId").val();
        var buildingId = $("#buildingId").val();
        var floorId = $("#floorId").val();
        var projectName = $("#projectId").find("option:selected").text();
        var buildingName = $("#buildingId").find("option:selected").text();
        var floorName = $("#floorId").find("option:selected").text();
        $("#regionId_hidden").val(regionId)
        $("#cityId_hidden").val(cityId)
        $("#projectId_hidden").val(projectId)
        $("#buildingId_hidden").val(buildingId)
        $("#floorId_hidden").val(floorId)
        var agency = new Array();
        agency = ${agencyEntities};
        if (projectId == null || projectId == '' || projectId == undefined) {
            $("#data_table").show();
            $("#data_name").show();
            $("#data_color").show();
            $("#list_table").hide();
            $("#detail_table").hide();
            $("#table_div").hide();
            var fd = new FormData();
            fd.append('regionId', regionId);
            fd.append('cityId', cityId);
            fd.append('projectId', projectId);
            fd.append('buildingId', buildingId);
            fd.append('floorId', floorId);
            fd.append('regionName', regionName);
            fd.append('cityName', cityName);
            fd.append('projectName', projectName);
            fd.append('buildingName', buildingName);
            fd.append('floorName', floorName);
            $.ajax({
                url: '../measure/measureData.html',
                type: 'POST',
                data: fd,
                processData: false,  //tell jQuery not to process the data
                contentType: false,  //tell jQuery not to set contentType
                error: function (data, type, err) {
                    alert("网络异常，可能服务器忙，请刷新重试");
                    console.log("ajax错误类型：" + type);
                    console.log(err);

                },
                success: function (json) {
                    var data = eval('(' + json + ')');//解析json
                    var dataObj = data[0];//数据
                    var name = new Array();//名称-数组
                    var mensurePercentOfPass = new Array();//合格率-数组
                    var regionIds = new Array();//区域ID
                    for (var i = 0; i < dataObj.data.length; i++) {
                        regionIds.push(dataObj.data[i].regionId);
                    }
                    document.getElementById("data_table").innerHTML = "";
                    if (data[0].success == '0') {
                        if (dataObj.data.length > 0) {

                        } else {
                            alert("暂无数据")
                            return;
                        }
                        $("#data_color").html('<div class="measureColorDiv"><span class="measureColorSpan" style="background-color:#3080E2 ;width: 20px;height: 20px;"></span><span class="measureColorSpan">优秀（95%以上)</span><br/></div>' +
                            '<div class="measureColorDiv"><span class="measureColorSpan" style="background-color:#EDA533 ;width: 20px;height: 20px;"></span><span class="measureColorSpan">需提升（90%~95%）</span><br/></div>' +
                            '<div class="measureColorDiv"><span class="measureColorSpan" style="background-color:#f66b55 ;width: 20px;height: 20px;"></span><span class="measureColorSpan">警示（90%以下）</span><br/></div>')
                        $("#size").val(dataObj.isExcel);//返回字段用来判断是否有值
                        if (regionId == '1' && (cityId == null || cityId == '' || cityId == undefined)) {
                            for (var i = 0; i < dataObj.data.length; i++) {
                                name.push(dataObj.data[i].regionName);
                                mensurePercentOfPass.push(dataObj.data[i].mensurePercentOfPass);
                            }
                            echars(name, mensurePercentOfPass, '中国金茂截至目前为止实测实量情况对比');
                            $("#data_name").html('<span style="font-size: 20px">中国金茂截至目前为止实测实量情况对比</span>')
                        } else if ((cityId == '1' || cityId == null || cityId == '' || cityId == undefined) && regionId != null && regionId != '' && regionId != undefined) {
                            for (var i = 0; i < dataObj.data.length; i++) {
                                name.push(dataObj.data[i].cityName);
                                mensurePercentOfPass.push(dataObj.data[i].mensurePercentOfPass);
                            }
                            if (regionName == '全部') {
                                echars(name, mensurePercentOfPass, '中国金茂截至目前为止实测实量情况对比');
                                $("#data_name").html('<span style="font-size: 20px">中国金茂截至目前为止实测实量情况对比</span>')
                            } else {
                                echars(name, mensurePercentOfPass, regionName + '截至目前为止实测实量情况对比');
                                $("#data_name").html('<span style="font-size: 20px">' + regionName + '截至目前为止实测实量情况对比</span>')
                            }
                        } else if (cityId != null && cityId != '' && cityId != undefined && cityId != '1') {
                            for (var i = 0; i < dataObj.data.length; i++) {
                                name.push(dataObj.data[i].projectName);
                                mensurePercentOfPass.push(dataObj.data[i].mensurePercentOfPass);
                            }
                            echars(name, mensurePercentOfPass, cityName + '截至目前为止实测实量情况对比');
                            $("#data_name").html('<span style="font-size: 20px">' + cityName + '截至目前为止实测实量情况对比</span>')
                        }
                    } else {
                        alert(data[0].error)
                        return
                    }
                }
            })
        } else {
            $("#projectName").val(projectName);
            $("#buildingName").val(buildingName);
            $("#floorName").val(floorName);
            $("#data_table").hide();
            $("#data_name").hide();
            $("#data_color").hide();
            document.forms[0].submit();
        }
    }
    function downloadModel() {
        if (confirm("是否下载模板？")) {
            var href = "../measure/getMeasureExcelModel";
            window.location.href = href;
        } else {
            return;
        }
    }
    function getQrCodeState() {
        $.ajax({
            url: "../measure/getQrCodeState",
            type: "get",
            async: "false",
            dataType: "json",
            traditional: true,
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                var data = json.data;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert('未知错误');
                } else {
                    if (data == 0) {
                        document.getElementById("open").checked = true;
                    } else {
                        document.getElementById("noOpen").checked = true;
                    }
                }
            }
        });
    }
    function addMeasure() {
        $("#regionId_model").val('');
        $("#cityId_model").val('');
        $("#projectId_model").val('');
        $("#buildingId_model").val('');
        $("#floorId_model").val('');
        $("#inspectionPhase_model").val('');
        $("#measureDetail").val('');
        document.getElementById("isUnit").checked = false;
        $("#isUnit").removeAttr("disabled");
        isUnit();
    }
    function qrCodeState() {
        var qrCodeState = $("input[name='isOpenQrCode']:checked").val();
        $.ajax({
            url: "../measure/updateQrCodeState",
            type: "get",
            async: "false",
            data: {"state": qrCodeState},
            dataType: "json",
            traditional: true,
            error: function () {
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    alert("修改成功")
                    window.location.reload()
                }
            }
        });
    }
    function echars(name, mensurePercentOfPass, title) {
        // 基于准备好的dom，初始化echarts实例
        var dom = document.getElementById('data_table');

        var resizeWorldMapContainer;
        resizeWorldMapContainer = function () {
            dom.style.width = window.innerWidth - 200 + 'px';
            dom.style.height = name.length * 50 + 100 + 'px';
//            dom.style.height = window.innerHeight+'px';
            dom.style.marginLeft = '-55px';
        };
        resizeWorldMapContainer();

        if (myChart != null && myChart != "" && myChart != undefined) {
            myChart.dispose();
        }
        myChart = echarts.init(dom);
        option = {
            /*title: {
             text: title,
             },*/
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                data: ['优秀(95%以上)', '需提升(90%-95%)', '警示(90%以下)']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0],
                axisLabel: {
                    show: true,
                    interval: 'auto',
                    formatter: '{value} %'
                },
            },
            yAxis: {
                type: 'category',
                data: name
            },
            series: [
                {
                    name: '',
                    type: 'bar',
                    data: mensurePercentOfPass,
                    barWidth: 30,
                    itemStyle: {
                        emphasis: {
                            barBorderRadius: [3, 3, 3, 3]
                        },
                        normal: {
                            color: function (params) {
                                console.log(params)
                                if (params.value >= 0 && params.value < 90) {
                                    var colorList = [
                                        ['#f66b55','#ff8673','#f66b55'],//每组三个颜色（可以设置更颜色但是对应下面的数组也需要更改）
                                        ['#f66b55','#ff8673','#f66b55'],
                                        ['#f66b55','#ff8673','#f66b55']
                                    ];
                                } else if (params.value >= 90 && params.value < 95) {
                                    var colorList = [
                                        ['#eda533','#f8c067','#eda533'],//每组三个颜色（可以设置更颜色但是对应下面的数组也需要更改）
                                        ['#eda533','#f8c067','#eda533'],
                                        ['#eda533','#f8c067','#eda533']
                                    ];
                                } else if (params.value >= 95) {
                                    var colorList = [
                                        ['#3080e2','#59a2fb','#3080e2'],//每组三个颜色（可以设置更颜色但是对应下面的数组也需要更改）
                                        ['#3080e2','#59a2fb','#3080e2'],
                                        ['#3080e2','#59a2fb','#3080e2']
                                    ];
                                }
                                var index=params.dataIndex;
                                if(params.dataIndex >= colorList.length){
                                    index=params.dataIndex-colorList.length;
                                }
                                return new echarts.graphic.LinearGradient(0, 0, 0, 1,
                                    [
                                        {offset: 0, color: colorList[0][0]},//第一个颜色的位置（对应颜色数组的长度）
                                        {offset: 0.5, color: colorList[0][1]},//第二个颜色的位置
                                        {offset: 1, color: colorList[0][2]}//第三个颜色的位置
                                    ]);
                            },
                            /*shadowColor: 'rgba(0, 0, 0, 2)',//阴影颜色
                            shadowBlur: 10,//图形阴影的模糊大小*/
                            /*shadowOffsetX: 0,
                            shadowOffsetY: 1,*/
                            barBorderRadius: 1 //柱状角成椭圆形
                        },
                    }
                }
            ],
        };
        /*if(option.series[0].data > 0 && option.series[0].data < 90){
         option.series[0].name = '警示(90%以下)';
         }else if(option.series[0].data >= 90 && option.series[0].data<= 95){
         option.series[0].name = '调整(90%-95%)';
         }else if(option.series[0].data > 95){
         option.series[0].name = '优秀(95%以上)';
         }*/

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }
</script>
<script>
    $(function () {
        initSelect();
    })
    //初始化选项框
    function initSelect() {
        var regionId = '${measureDTO.regionId}';
        var cityId = '${measureDTO.cityId}';
        var projectId = '${measureDTO.projectId}';
        var buildingId = '${measureDTO.buildingId}';
        var floorId = '${measureDTO.floorId}';
        regionChange(regionId)
        cityChange(regionId, cityId)
        projectChange(projectId, buildingId)
        buildingChange(buildingId, floorId)
        $("#regionId").val(regionId)
        $("#cityId").val(cityId)
        $("#projectId").val(projectId)
        $("#buildingId").val(buildingId)
        $("#floorId").val(floorId)
        if (projectId == '' || projectId == null || projectId == undefined) {
            $("#list_table").hide();
            $("#detail_table").hide();
            $("#table_div").hide();
        } else {
            if (floorId == '' || floorId == null || floorId == undefined) {
                $("#list_table").show();
                $("#detail_table").show();
                $("#table_div").show();
            } else {
                $("#list_table").hide();
                $("#detail_table").show();
                $("#table_div").show();
            }
        }
    }
    function isExcel() {
        var size = $("#size").val();
        var regionId = $("#regionId_hidden").val();
        var cityId = $("#cityId_hidden").val();
        var project = $("#projectId_hidden").val();
        var buildingId = $("#buildingId_hidden").val();
        var floorId = $("#floorId_hidden").val();
        var projectName = $("#projectName").val();
        var buildingName = $("#buildingName").val();
        var floorName = $("#floorName").val();
        if(project == null || project == '' || project == undefined){
            projectName = '';
        }
        if(buildingId == null || buildingId == '' || buildingId == undefined){
            buildingName = '';
        }
        if(floorId == null || floorId == '' || floorId == undefined){
            floorName = '';
        }
        debugger
        if (size > 0) {
            var href = "../measure/exportExcels?regionId=" + regionId + "&cityId=" + cityId + "&projectId=" + project + "&buildingId="+buildingId+"&floorId="+floorId+"&projectName="+projectName+"&buildingName="+buildingName+"&floorName="+floorName;
            window.location.href = href;
        } else {
            alert("没有可以导出的数据");
        }
    }
    function sub() {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        var result = '';
        if (nodes.length == 0) {
            alert("请选择您要下载的区域、城市或项目！");
            return false;
        }
        for (var i = 0; i < nodes.length; i++) {
            if (i == nodes.length) {
                result += nodes[i].id;
            } else {
                result += nodes[i].id + ',';
            }
        }
        $.ajax({
            url: "../measure/getDataByagencyId?agencyId=" + result,            //目标网址
            type: "get",
            cache: false,
            async: true,
            dataType: "json",
            success: function (data) {
                if (data.error == 1) {
                    window.location.href = "../measure/exportAllDataForExcel?agencyId=" + result;
                    $('#myModal2').modal('hide');
                    $('#myModal3').modal('show');
                    /*$(".chk").removeClass("checkbox_true_full").addClass('checkbox_false_full');
                    $(".chk").removeClass("checkbox_false_part").addClass('checkbox_false_full');*/
                } else {
                    alert(data.error);
                }
            }
        });
    }
    function remove() {
        window.location.reload()
    }
</script>
</body>
</html>