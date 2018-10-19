<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
<html>
<head>
    <title><spring:message code="sys.tital"/></title>
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">

    <meta name="keywords" content=""/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>

    <script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
    </script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>

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

    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        $(function () {
            console.log("sqq")
            $("#005500040000").addClass("active");
            $("#005500040000").parent().parent().addClass("in");
            $("#005500040000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <style>
        .flowersList img {
            width: 20px;
        }

        .imgList img {
            width: 70px;
        }

        .form-body h3 {
            font-size: 14px;
        }

        .choice-option h3, .choice-option p {
            font-size: 14px;
        }

        .table-bordered {
            font-size: 14px;
        }
    </style>
    <STYLE type=text/css>
        input.error {
            border: 1px solid red;
        }

        label.error {
            background: url("./demo/images/unchecked.gif") no-repeat 0px 0px;
            padding-left: 16px;
            padding-bottom: 2px;
            font-weight: bold;
            color: #EA5200;
        }

        .voteImg {
            width: 121px;
            height: 71px;
        }

        label.checked {
        / / background : url("./demo/images/checked.gif") no-repeat 0 px 0 px;
        }

        /*——————MJ_______*/
        .fl {
            float: left;
        }

        .clearfix {
            *zoom: 1;
        }

        .clearfix:before, .clearfix:after {
            display: table;
            line-height: 0;
            content: "";
        }

        .clearfix:after {
            clear: both;
        }

        .choice-option {
            width: 100%;
            height: auto;
            margin-top: 30px;
        }

        .option-con {
            width: 100%;
            height: auto;
            padding-left: 24px;
        }

        .option-con .txt {
            width: 100px;
            height: 2rem;
            line-height: 2rem;
        }

        .option-con .txt h3 {
            /*margin-top: 30px;*/
        }

        .option-right {
        }

        .pic-text {
            width: 900px;
            height: 71px;
            margin-bottom: 10px;
        }

        .pic-text .pic {
            width: 121px;
            height: 71px;
            background: darkred;
            margin-right: 20px;
        }

        .pic-text .text {
            width: 568px;
            height: 45px;
            font-size: 14px;
            font-weight: 400;
            margin-top: 15px;
        }

        .jdt {
            width: 1000px;
            height: auto;
        }

        .jdt .progress {
            width: 700px;
            height: 19px;
        }

        .jdt .poll, .jdt .percent {
            width: 80px;
            text-align: right;
            height: 28px;
            line-height: 28px;
            font-size: 14px;
            font-weight: 400;
        }

        table thead tr th {
            width: 15%;
            text-align: center;
        }

        table tbody tr td {
            width: 15%;
            text-align: center;
        }

        table tbody tr th {
            width: 15%;
            text-align: center;
        }
    </STYLE>
</head>
<body class="cbp-spmenu-push">
<div class="main-content">
    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="005500040000" username="${propertystaff.staffName}"/>
    <div class="form-body">
        <h3 class="text-center">&nbsp;</h3>

        <h3>投票名称：
            <lable>${voteDetail.title}</lable>
        </h3>
        <h3 class="text-center">&nbsp;</h3>

        <h3>截止时间：
            <lable>${voteDetail.endDate}</lable>
        </h3>
        <h3 class="text-center">&nbsp;</h3>

        <h3>投票权限：
            <lable>（待定）</lable>
        </h3>
        <h3 class="text-center">&nbsp;</h3>

        <h3>投票人数：
            <lable>${count}</lable>
            人
        </h3>
    </div>
    <c:forEach items="${voteOptions}" var="voteOption" varStatus="row">
        <div class="choice-option ">
            <div class="option-con clearfix">
                <div class="txt fl"><h3>选项${row.index + 1}：</h3></div>
                <div class="option-right fl">
                    <div class="pic-text clearfix">
                        <div class="pic fl"><img class="voteImg" src="${voteOption.url}" alt=""/></div>
                        <p class="text fl">${voteOption.description}</p>
                    </div>
                    <div class="jdt clearfix">
                        <div class="progress fl">
                            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"
                                 aria-valuemax="100" style="width:${voteOption.voteNumPer}%;">
                                    ${voteOption.voteNumPer}%
                            </div>
                        </div>
                        <div class="poll fl">
                            <laber>${voteOption.voteNumber}</laber>
                            票
                        </div>
                        <div class="percent fl">${voteOption.voteNumPer}%</div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    <%--  <div class="choice-option ">
        <div class="option-con clearfix">
          <div class="txt fl"><h3>选项2：</h3></div>
          <div class="option-right fl">
            <div class="pic-text clearfix">
              <div class="pic fl"><img src="" alt=""/></div>
              <p class="text fl">本户型为三室一厅、使用面积152㎡，面北朝南本户型为三室一厅、使用面积152㎡，面北朝南</p>
            </div>
            <div class="jdt clearfix">
              <div class="progress fl">
                <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:60%;">
                  60%
                </div>
              </div>
              <div class="poll fl"><laber>7</laber>票</div>
              <div class="percent fl">33%</div>
            </div>
          </div>
        </div>
      </div>
      <div class="choice-option ">
        <div class="option-con clearfix">
          <div class="txt fl"><h3>选项3：</h3></div>
          <div class="option-right fl">
            <div class="pic-text clearfix">
              <div class="pic fl"><img src="" alt=""/></div>
              <p class="text fl">本户型为三室一厅、使用面积152㎡，面北朝南本户型为三室一厅、使用面积152㎡，面北朝南</p>
            </div>
            <div class="jdt clearfix">
              <div class="progress fl">
                <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:60%;">
                  60%
                </div>
              </div>
              <div class="poll fl"><laber>7</laber>票</div>
              <div class="percent fl">33%</div>
            </div>
          </div>
        </div>
      </div>--%>
    <table class="table table-bordered" style="margin:30px auto;">
        <thead style="background: #ccc;">
        <tr>
            <th>所属公告</th>
            <th>项目名称</th>
            <c:forEach items="${voteOptions}" var="voteOption" varStatus="row">
                <th>选项${row.index + 1}</th>
            </c:forEach>
            <th>合计</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${voteProjects}" var="voteProject" varStatus="row1">
            <tr>
                <th>${voteProject.title}</th>
                <th scope="row">${voteProject.projectName}</th>
                <c:forEach items="${voteProject.optionList}" var="voteOption" varStatus="row2">
                    <th>${voteOption}</th>
                </c:forEach>
                    <%-- <td>${voteProject.option_1}</td>
                     <td>${voteProject.option_2}</td>
                     <td>${voteProject.option_3}</td>--%>
                <td>${voteProject.voteNumSum}</td>
            </tr>

        </c:forEach>
        <%-- <tr>
          <th>所属公告</th>
          <th scope="row">亦庄金茂悦</th>
          <td>Mark</td>
          <td>Otto</td>
          <td>@mdo</td>
          <td>@mdo</td>
        </tr>--%>

        </tbody>
    </table>
    <input type="button" class="btn btn-primary" onclick="javascript:history.go(-1);" value="返回">
</div>

</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</body>
</html>
