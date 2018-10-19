<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: luxinxin
  Date: 2016/8/10
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <style type="text/css">
    .divCenter{
      margin:0 auto;
    }
    .mydiv{
      margin:0 auto;
      min-width:450px;
      min-height:780px;
    }
    table.gridtable {
      margin: 0 auto;
      font-family: verdana,arial,sans-serif;
      font-size:11px;
      border-collapse: collapse;
      width: 800px;

    }
    table.gridtable td {
      border-width: 1px;
      padding: 8px;
      border-style: solid;
      width: 80px;
    }
    /*.checkboxs{*/
      /*float: right;*/
      /*margin-right: 20px;*/
      /*margin-top: 8px;*/
    /*}*/
    .checkboxs{
      float: right;
      margin-right: 20px;
      /* margin-top: 8px;*/
    }
    .index_left{
      width: auto;
      display: inline-block;
      margin: 0 5px;
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
  </style>
</head>
<body>
<%
  int count=Integer.parseInt("" + request.getParameter("count"));
  int star=Integer.parseInt("" + request.getParameter("stra"));
%>
<div class="mydiv">
  <!--<span>编号：</span>-->
  <table class="gridtable">
    <tr id="trHeight" style="height: 800px;vertical-align: top">
      <td colspan="8" >
        <div style="line-height: 25px" id="contents">
          <div>情况记录：</div>
          <c:forEach items="${problems}" var="problem" varStatus="row" begin="<%=star%>" end="<%=count%>" step="1">
          <%--<div>&nbsp;&nbsp;&nbsp;&nbsp;--%>
              <%--${problem.caseDesc}<input class="checkboxs" name="answer" type="checkbox" disabled/><div>--%>
            <div class="divautohight">
              <span class="index_left">${row.index+1}、</span>
              <span class="position_left">【${problem.casePlace}】</span>
              <span class="thirdType_left">【${problem.thirdTypeName}】</span>
                <%--<span class="description_tight">${problem.caseDesc}<input class="checkboxs" name="answer" type="checkbox"/></span>--%>
              <span>${problem.caseDesc}<input class="checkboxs" name="answer" type="checkbox"/></span>
            </div>
              </c:forEach>
          <!--30-->
        </div>
      </td>
    </tr>
    <tr>
      <td>水表表号</td>
      <td>${houseTransferInfo.waterMeter}</td>

      <td>水表底数</td>
      <td>${houseTransferInfo.waterBase}</td>

      <td>电表表号</td>
      <td>${houseTransferInfo.watthourMeter}</td>

      <td>电表底数</td>
      <td>${houseTransferInfo.meterBase}</td>
    </tr>
    <tr>
      <td>中水表号</td>
      <td>${houseTransferInfo.mediumWaterMeter}</td>

      <td>中水表底数</td>
      <td>${houseTransferInfo.mediumWaterBase}</td>

      <td>燃气表号</td>
      <td>${houseTransferInfo.gasMeter}</td>

      <td>燃气表底数</td>
      <td>${houseTransferInfo.gasMeterBase}</td>
    </tr>
    <tr>
      <td colspan="8">
        本人已详细验收上述单位，同意收楼。除以上所列项目外，该单位及其设施设备情况良好，能正常居住使用。请贵公司就上述所列项目履行保修义务。<br/>
        <%--<h3>业主签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系电话：</h3>--%>
        <%--<h3><span style="margin:20px 50px;">业主签名：</span><span style="margin-top: 20px;margin-left: 200px;">联系电话：</span></h3>--%>
        <h3 style="margin-top:30px;margin-bottom:30px;"><span >业主签名：</span><span style="margin-top: 20px;margin-left: 230px;">联系电话：</span></h3>
        <span style="">陪同验房人：</span>
        <%--<span Style="float: right">验房时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年 &nbsp;&nbsp;   月&nbsp;&nbsp;   日</span>--%>
        <span Style="float: right">验房时间:${strDate}</span>

      </td>
    </tr>
    <tr>
      <td colspan="2">保修办复查人：</td>
      <td colspan="3">复查后业主签名：</td>
      <td colspan="3">复查日期：</td>
    </tr>
    <tr>
      <td colspan="2">钥匙留用情况</td>
      <td colspan="2">□ 留 &nbsp; 把</td>
      <td colspan="2">□ 未留  说明：</td>
      <td colspan="2">业主领回钥匙签收：</td>
    </tr>
    <tr>
      <td colspan="8" style="border: 0px">
        <b>说明</b>：<br>
        1、该建筑项目已经政府主管部门验收合格，取得竣工备案证明，可以交付使用。<br/>
        2、为使问题得到及时有效的解决，请业主留下返修钥匙，否则可能会延误保修工作。
      </td>
    </tr>
  </table>
</div>
</body>
</html>
