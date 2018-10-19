<%--
  Created by IntelliJ IDEA.
  User: luxinxin
  Date: 2016/8/3
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <style type="text/css">
        .mydiv{
            margin:0 auto;
            width:210mm;
            /*height:297mm;*/
            /*float:left;*/
        }
        .divautohight{
            overflow:hidden;
        }
        table{
            width: 100%;
        }
        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            border-collapse: collapse;

        }
        table.gridtable .thead{
            /*border-bottom: 1px solid #000;*/
        }
        table.gridtable .thead td:first-child{
            padding-left: 0;
        }
        table.gridtable .thead td:last-child{
            text-align: right;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            width: 100px;
            font-size: 16px;
        }
        .checkboxs{
            float: right;
            margin-right: 20px;
            /* margin-top: 8px;*/
        }
        .index_left{
            width: auto;
            display: inline-block;
            margin-right: -15px;
        }
        .position_left{
            width: auto;
            display: inline-block;
            margin-left: -13px;
            margin: 0 5px;
        }
        .thirdType_left{
            width: auto;
            display: inline-block;
            margin: 0 5px;
            margin-left: -15px;
        }
        .description_tight{
            display: inline-block;
        }
        .myclass{
            padding-top: 100px;
        }
    </style>
    <style type="text/css" media="print">
        .noprint { display:none;}
        .PageNext{
            page-break-after: always;
        }
    </style>
    <style>
        .tdp
        {
            border-bottom: 1 solid #000000;
            border-left: 1 solid #000000;
            border-right: 0 solid #ffffff;
            border-top: 0 solid #ffffff;
        }
        .tabp
        {
            border-color: #000000 #000000 #000000 #000000;
            border-style: solid;
            border-top-width: 2px;
            border-right-width: 2px;
            border-bottom-width: 1px;
            border-left-width: 1px;
        }
        .NOPRINT {
            font-family: "宋体";
            font-size: 9pt;
        }
        .signUrlImg {
            -webkit-transform:rotate(-90deg);
            -o-transform:rotate(-90deg);
            -moz-transfomr:rotate(-90deg);
        }
        .gridtable .userNameIn td{
            border-bottom: none;
            height: 2.5rem;
            line-height: 2.5rem;
            padding: 0 8px;
        }
        .gridtable .userTips td{
            height: 2.5rem;
            line-height: 2.5rem;
            padding: 0 8px;
        }
        #trHeight{
            height: 800px;
        }
        #trHeight .content div{
            line-height: 2.5rem;
        }
    </style>
    <style type="text/css" media="print">
        .noprint { display:none;}







    </style>



    <script language="JavaScript">
        function pr(){
            window.print();
        }
    </script>
</head>
<body>
<%
    String logo= (String) request.getAttribute("logo");
%>




<div class="mydiv">
    <center><input align="center" type="button" value="打印" onclick="pr();" class="noprint"></center>
    <h2 align="center">${prinName}-第一联</h2>
    <table class="gridtable">
        <tr class="thead"  colspan="2">
            <td style="border: 0px;text-align: left">编号：${prinId}</td>
            <td style="border: 0px;text-align: right">日期：${strDate}</td>
        </tr>
        <tr class="userNameIn" style="border: 1px solid #000;border-bottom: none;"  colspan="2">
            <td  style="text-align: left;border-right: none;">业主：${userName}</td>
            <td style="text-align: right;border-left: none;">房号：${address}</td>
        </tr>
        <tr class="userTips">
            <td colspan="12" style="border-top: none;">本人已详细验收该房屋，请贵公司就下述所列项目履行保修义务。</td>
        </tr>
        <tr id="trHeight" style="vertical-align: top">
            <!--<td colspan="2" style="align-content: center;line-height: 800px">情况记录</td>-->
            <td colspan="16" >
                <div style="" class="content">

                    <div>情况记录：</div>
                    <c:if test="${!empty problems }">
                        <c:forEach items="${problems}" var="problem" varStatus="row">
                            <div class="divautohight">
                                <span class="index_left">${row.index+1}、</span>
                                <span class="position_left">【${problem.casePlace}】</span>
                                <span>${problem.caseDesc}
                                </span>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty problems}">
                        <div class="divautohight">
                            <span class="index_left">本次查验无质量问题，以下无正文。</span>
                        </div>
                    </c:if>
                </div>
            </td>
        </tr>
    </table>
    <table>
        <tr   style="border:0px;">
            <td colspan="8" align="center">
                <div style="overflow: hidden;">
                    <span style="float: left;line-height: 100px;">验房工程师：</span>
                    <span style="line-height: 100px;float: left; margin-left: 200px;">业主确认：</span>
                    <c:if test="${signUrl ne ''}">
                        <img class="signUrlImg" height="100px" width="100px"  style="float: left; transform: rotate(-90deg);"  src="${signUrl}">
                    </c:if>
                </div>
            </td>
        </tr>
    </table>
</div>
<div class="PageNext"></div>
<div class="mydiv">
    <h2 align="center">${prinName}-第二联</h2>
    <table class="gridtable">
        <tr class="thead"  colspan="2">
            <td style="border: 0px;text-align: left">编号：${prinId}</td>
            <td style="border: 0px;text-align: right">日期：${strDate}</td>
        </tr>
        <tr class="userNameIn" style="border: 1px solid #000;border-bottom: none;"  colspan="2">
            <td  style="text-align: left;border-right: none;">业主：${userName}</td>
            <td style="text-align: right;border-left: none;">房号：${address}</td>
        </tr>
        <tr class="userTips">
            <td colspan="12" style="border-top: none;">本人已详细验收该房屋，请贵公司就下述所列项目履行保修义务。</td>
        </tr>
        <tr id="trHeight" style="vertical-align: top">
            <!--<td colspan="2" style="align-content: center;line-height: 800px">情况记录</td>-->
            <td colspan="16" >
                <div style="" class="content">
                    <!--<div>根据《商品房买卖合同》及其补充协议，现对您购买该房屋进行验收交付，若属质量遗留瑕疵，详细记录需保修之项目，以便改善。</div>-->
                    <div>情况记录：</div>
                    <c:if test="${!empty problems }">
                        <c:forEach items="${problems}" var="problem" varStatus="row">
                            <div class="divautohight">
                                <span class="index_left">${row.index+1}、</span>
                                <span class="position_left">【${problem.casePlace}】</span>
                                <span>${problem.caseDesc}
                                </span>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty problems}">
                        <div class="divautohight">
                            <span class="index_left">本次查验无质量问题，以下无正文。</span>
                        </div>
                    </c:if>
                </div>
            </td>
        </tr>
    </table>
    <table>
        <tr   style="border:0px;">
            <td colspan="8" align="center">
                <div style="overflow: hidden;">
                    <span style="float: left;line-height: 100px;">验房工程师：</span>
                    <span style="line-height: 100px;float: left; margin-left: 200px;">业主确认：</span>
                    <c:if test="${signUrl ne ''}">
                        <img class="signUrlImg" height="100px" width="100px"  style="float: left; transform: rotate(-90deg);"  src="${signUrl}">
                    </c:if>
                </div>
            </td>
        </tr>
    </table>
</div>
<div class="PageNext"></div>
<div class="mydiv">
    <h2 align="center">${prinName}-第三联</h2>
    <table class="gridtable">
        <tr class="thead"  colspan="2">
            <td style="border: 0px;text-align: left">编号：${prinId}</td>
            <td style="border: 0px;text-align: right">日期：${strDate}</td>
        </tr>
        <tr class="userNameIn" style="border: 1px solid #000;border-bottom: none;"  colspan="2">
            <td  style="text-align: left;border-right: none;">业主：${userName}</td>
            <td style="text-align: right;border-left: none;">房号：${address}</td>
        </tr>
        <tr class="userTips">
            <td colspan="12" style="border-top: none;">本人已详细验收该房屋，请贵公司就下述所列项目履行保修义务。</td>
        </tr>
        <tr id="trHeight" style="vertical-align: top">

            <td colspan="16" >
                <div style="" class="content">
                    <!--<div>根据《商品房买卖合同》及其补充协议，现对您购买该房屋进行验收交付，若属质量遗留瑕疵，详细记录需保修之项目，以便改善。</div>-->
                    <div>情况记录：</div>
                    <c:if test="${!empty problems }">
                        <c:forEach items="${problems}" var="problem" varStatus="row">
                            <div class="divautohight">
                                <span class="index_left">${row.index+1}、</span>
                                <span class="position_left">【${problem.casePlace}】</span>
                                <span>${problem.caseDesc}
                                </span>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty problems}">
                        <div class="divautohight">
                            <span class="index_left">本次查验无质量问题，以下无正文。</span>
                        </div>
                    </c:if>
                </div>
            </td>
        </tr>
    </table>
    <table>
        <tr   style="border:0px;">
            <td colspan="8" align="center">
                <div style="overflow: hidden;">
                    <span style="float: left;line-height: 100px;">验房工程师：</span>
                    <span style="line-height: 100px;float: left; margin-left: 200px;">业主确认：</span>
                    <c:if test="${signUrl ne ''}">
                        <img class="signUrlImg" height="100px" width="100px"  style="float: left; transform: rotate(-90deg);"  src="${signUrl}">
                    </c:if>
                </div>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
