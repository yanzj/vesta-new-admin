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
    table.gridtable {
      font-family: verdana,arial,sans-serif;
      font-size:11px;
      border-collapse: collapse;
    }
    table.gridtable td {
      border-width: 1px;
      padding: 8px;
      border-style: solid;
      width: 200px;
    }
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
<%--正式交房（13）走当前判断--%>
<c:if test="${type eq '13'}">
  <div class="mydiv">
    <center><input align="center" type="button" value="打印" onclick="pr();" class="noprint"></center>
    <h4 align="center">客户保修意见征询表-第一联</h4>

    <table class="gridtable">
      <tr>

        <td colspan="8" style="border: 0px;text-align: left">编号：${rId}</td>
      </tr>
      <tr>
        <td colspan="2">项目名称:</td>
        <td colspan="2">${prjectName}</td>
        <td colspan="2">楼栋房号:</td>
        <td colspan="2">${address}</td>
      </tr>
      <tr id="trHeight" style="height: 650px;vertical-align: top">
        <!--<td colspan="2" style="align-content: center;line-height: 800px">情况记录</td>-->
        <td colspan="8" >
          <div style="line-height: 25px" class="content">
            <!--<div>根据《商品房买卖合同》及其补充协议，现对您购买该房屋进行验收交付，若属质量遗留瑕疵，详细记录需保修之项目，以便改善。</div>-->
            <div>情况记录：</div>
            <c:choose>
              <c:when test="<%=logo.equals(\"hello\")%>">
                <c:forEach items="${problems}" var="problem" varStatus="row">
                  <div class="divautohight">
                    <span class="index_left">${row.index+1}、</span>
                    <span class="position_left">【${problem.casePlace}】</span>
                    <span class="thirdType_left">【${problem.thirdTypeName}】</span>
                      <%--<span class="description_tight">${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>--%>
                    <span >${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>

                  </div>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <span style="margin-left:60px;"><%=logo%></span>
              </c:otherwise>
            </c:choose>
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
          <h3 style="margin-top:30px;margin-bottom:30px;overflow: hidden;">
            <span style="line-height: 100px;float: left;">业主签名：</span>
            <c:if test="${signUrl ne ''}">
              <img class="signUrlImg" height="100px" width="100px"  style="float: left;"  src="${signUrl}">
            </c:if>
            <span style="margin-left: 230px;float: left;line-height: 100px;">联系电话：</span>
          </h3>

          <span style="">陪同验房人：</span>
            <%--<span Style="float: right">验房时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年 &nbsp;&nbsp;   月&nbsp;&nbsp;   日</span>--%>
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
    </table>
    <b>说明</b>：<br>
    1、该建筑项目已经政府主管部门验收合格，取得竣工备案证明，可以交付使用。<br/>
    2、为使问题得到及时有效的解决，请业主留下返修钥匙，否则可能会延误保修工作。
  </div>
  <div class="PageNext"></div>
  <div class="mydiv">
    <table class="gridtable">
      <h4 align="center">客户保修意见征询表-第二联</h4>
      <tr>

        <td colspan="8" style="border: 0px;text-align: left">编号：${rId}</td>
      </tr>
      <tr>
        <td colspan="2">项目名称:</td>
        <td colspan="2">${prjectName}</td>
        <td colspan="2">楼栋房号:</td>
        <td colspan="2">${address}</td>
      </tr>
      <tr id="trHeight" style="height: 700px;vertical-align: top">
        <!--<td colspan="2" style="align-content: center;line-height: 800px">情况记录</td>-->
        <td colspan="8" >
          <div style="line-height: 25px" class="content">
            <!--<div>根据《商品房买卖合同》及其补充协议，现对您购买该房屋进行验收交付，若属质量遗留瑕疵，详细记录需保修之项目，以便改善。</div>-->
            <div>情况记录：</div>
              <%--<c:forEach items="${problems}" var="problem" varStatus="row">--%>
              <%--<div>&nbsp;&nbsp;&nbsp;&nbsp;--%>
              <%--${problem.caseDesc}<input class="checkboxs" name="answer" type="checkbox"/><div>--%>
              <%--</c:forEach>--%>
            <c:choose>
              <c:when test="<%=logo.equals(\"hello\")%>">
                <c:forEach items="${problems}" var="problem" varStatus="row">
                  <div class="divautohight">
                    <span class="index_left">${row.index+1}、</span>
                    <span class="position_left">【${problem.casePlace}】</span>
                    <span class="thirdType_left">【${problem.thirdTypeName}】</span>
                      <%-- <span class="description_tight">${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>--%>
                    <span>${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>
                  </div>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <span style="margin-left:60px;"><%=logo%></span>
              </c:otherwise>
            </c:choose>
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


          <h3 style="margin-top:30px;margin-bottom:30px;overflow: hidden;">
            <span style="line-height: 100px;float: left;">业主签名：</span>
            <c:if test="${signUrl ne ''}">
              <img class="signUrlImg" height="100px" width="100px"  style="float: left;"  src="${signUrl}">
            </c:if>
            <span style="margin-left: 230px;float: left;line-height: 100px;">联系电话：</span>
          </h3>
          <span style="">陪同验房人：</span>
            <%--<span Style="float: right">验房时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年 &nbsp;&nbsp;   月&nbsp;&nbsp;   日</span>--%>
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
    </table>
    <b>说明</b>：<br>
    1、该建筑项目已经政府主管部门验收合格，取得竣工备案证明，可以交付使用。<br/>
    2、为使问题得到及时有效的解决，请业主留下返修钥匙，否则可能会延误保修工作。
  </div>
  <div class="PageNext"></div>
  <div class="mydiv">
    <table class="gridtable">
      <h4 align="center">客户保修意见征询表-第三联</h4>
      <tr>
        <td colspan="8" style="border: 0px;text-align: left">编号：${rId}</td>
      </tr>
      <tr>
        <td colspan="2">项目名称:</td>
        <td colspan="2">${prjectName}</td>
        <td colspan="2">楼栋房号:</td>
        <td colspan="2">${address}</td>
      </tr>
      <tr id="trHeight" style="height: 700px;vertical-align: top">
        <!--<td colspan="2" style="align-content: center;line-height: 800px">情况记录</td>-->
        <td colspan="8" >
          <div style="line-height: 25px" class="content">
            <!--<div>根据《商品房买卖合同》及其补充协议，现对您购买该房屋进行验收交付，若属质量遗留瑕疵，详细记录需保修之项目，以便改善。</div>-->
            <div>情况记录：</div>
              <%--<c:forEach items="${problems}" var="problem" varStatus="row">--%>
              <%--<div>&nbsp;&nbsp;&nbsp;&nbsp;--%>
              <%--${problem.caseDesc}<input class="checkboxs" name="answer" type="checkbox"/><div>--%>
              <%--</c:forEach>--%>
            <c:choose>
              <c:when test="<%=logo.equals(\"hello\")%>">
                <c:forEach items="${problems}" var="problem" varStatus="row">
                  <div class="divautohight">
                    <span class="index_left">${row.index+1}、</span>
                    <span class="position_left">【${problem.casePlace}】</span>
                    <span class="thirdType_left">【${problem.thirdTypeName}】</span>
                      <%-- <span class="description_tight">${problem.caseDesc}<input class="checkboxs" name="answer" type="checkbox"/></span>--%>
                    <span>${problem.caseDesc}<input class="checkboxs" name="answer" type="checkbox"/></span>

                  </div>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <span style="margin-left:60px;"><%=logo%></span>
              </c:otherwise>
            </c:choose>
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
            <%--<h3><span style="margin:20px 50px;">业主签名：</span><span style="margin-top: 20px;margin-left: 200px;">联系电话：</span></h3>--%>
            <%--<h3>业主签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系电话：</h3>--%>
          <h3 style="margin-top:30px;margin-bottom:30px;overflow: hidden;">
            <span style="line-height: 100px;float: left;">业主签名：</span>
            <c:if test="${signUrl ne ''}">
              <img class="signUrlImg" height="100px" width="100px"  style="float: left;"  src="${signUrl}">
            </c:if>
            <span style="margin-left: 230px;float: left;line-height: 100px;">联系电话：</span>
          </h3>


          <span style="">陪同验房人：</span>
            <%--<span Style="float: right">验房时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年 &nbsp;&nbsp;   月&nbsp;&nbsp;   日</span>--%>
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
    </table>
    <b>说明</b>：<br>
    1、该建筑项目已经政府主管部门验收合格，取得竣工备案证明，可以交付使用。<br/>
    2、为使问题得到及时有效的解决，请业主留下返修钥匙，否则可能会延误保修工作。
  </div>
</c:if>
<%--工地开放（12）走当前判断--%>
<c:if test="${type eq '12'}">

  <div class="mydiv">
    <center><input align="center" type="button" value="打印" onclick="pr();" class="noprint"></center>
    <h4 align="center">工地开放日业主意见征询表-第一联</h4>
    <table class="gridtable">
      <tr>
        <td colspan="8" style="border: 0px;text-align: left">编号：${rId}</td>
      </tr>
      <tr>
        <td colspan="2">项目名称:</td>
        <td colspan="2">${prjectName}</td>
        <td colspan="2">楼栋房号:</td>
        <td colspan="2">${address}</td>
      </tr>
      <tr>
        <td colspan="2">业主姓名:</td>
        <td colspan="2">${houseOpen.customerName}</td>
        <td colspan="2">日期:</td>
        <td colspan="2">${openTime}</td>
      <tr>
      <tr id="trHeight" style="height: 650px;vertical-align: top">
        <!--<td colspan="2" style="align-content: center;line-height: 800px">情况记录</td>-->
        <td colspan="8" >
          <div style="line-height: 25px" class="content">
            <!--<div>根据《商品房买卖合同》及其补充协议，现对您购买该房屋进行验收交付，若属质量遗留瑕疵，详细记录需保修之项目，以便改善。</div>-->
            <div>情况记录：</div>
            <c:choose>
              <c:when test="<%=logo.equals(\"hello\")%>">
                <c:forEach items="${problems}" var="problem" varStatus="row">
                  <div class="divautohight">
                    <span class="index_left">${row.index+1}、</span>
                    <span class="position_left">【${problem.casePlace}】</span>
                    <span class="thirdType_left">【${problem.thirdTypeName}】</span>
                      <%--<span class="description_tight">${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>--%>
                    <span>${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>
                  </div>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <span style="margin-left:60px;"><%=logo%></span>
              </c:otherwise>
            </c:choose>
          </div>
        </td>
      </tr>
    </table>
    <table>
      <tr   style="border:0px;">
        <td colspan="8" align="center">
            <h3 style="margin-top:30px;margin-bottom:30px;overflow: hidden;">
                <span style="line-height: 100px;float: left;">业主签名：</span>
              <c:if test="${signUrl ne ''}">
                <img class="signUrlImg" height="100px" width="100px"  style="float: left;"  src="${signUrl}">
              </c:if>
                <span style="margin-left: 230px;float: left;line-height: 100px;">陪同验房人签字：</span>
            </h3>
        </td>
      </tr>
    </table>
  </div>
  <%--<hr align="center" width="50%" size="1" noshade class="noprint">--%>
  <div class="PageNext"></div>
  <div class="mydiv">
    <h4 align="center">工地开放日业主意见征询表-第二联</h4>
    <table class="gridtable">
      <tr>
        <td colspan="8" style="border: 0px;text-align: left">编号：${rId}</td>
      </tr>
      <tr>
        <td colspan="2">项目名称:</td>
        <td colspan="2">${prjectName}</td>
        <td colspan="2">楼栋房号:</td>
        <td colspan="2">${address}</td>
      </tr>
      <tr>
        <td colspan="2">业主姓名:</td>
        <td colspan="2">${houseOpen.customerName}</td>
        <td colspan="2">日期:</td>
        <td colspan="2">${openTime}</td>
      <tr>
      <tr id="trHeight" style="height: 650px;vertical-align: top">
        <!--<td colspan="2" style="align-content: center;line-height: 800px">情况记录</td>-->
        <td colspan="8" >
          <div style="line-height: 25px" class="content">
            <!--<div>根据《商品房买卖合同》及其补充协议，现对您购买该房屋进行验收交付，若属质量遗留瑕疵，详细记录需保修之项目，以便改善。</div>-->
            <div>情况记录：</div>
            <c:choose>
              <c:when test="<%=logo.equals(\"hello\")%>">
                <c:forEach items="${problems}" var="problem" varStatus="row">
                  <div class="divautohight">
                    <span class="index_left">${row.index+1}、</span>
                    <span class="position_left">【${problem.casePlace}】</span>
                    <span class="thirdType_left">【${problem.thirdTypeName}】</span>
                      <%--<span class="description_tight">${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>--%>
                    <span>${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>
                  </div>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <span style="margin-left:60px;"><%=logo%></span>
              </c:otherwise>
            </c:choose>
          </div>
        </td>
      </tr>
    </table>
    <table>
      <tr   style="border:0px;">
        <td colspan="8" align="center">
            <h3 style="margin-top:30px;margin-bottom:30px;overflow: hidden;">
                <span style="line-height: 100px;float: left;">业主签名：</span>
              <c:if test="${signUrl ne ''}">
                <img class="signUrlImg" height="100px" width="100px"  style="float: left;"  src="${signUrl}">
              </c:if>
                <span style="margin-left: 230px;float: left;line-height: 100px;">陪同验房人签字：</span>
            </h3>
        </td>
      </tr>
    </table>
  </div>
  <%--<hr align="center" width="50%" size="1" noshade class="noprint">--%>
  <div class="PageNext"></div>
  <div class="mydiv">
    <h4 align="center">工地开放日业主意见征询表-第三联</h4>
    <table class="gridtable">
      <tr>
        <td colspan="8" style="border: 0px;text-align: left">编号：${rId}</td>
      </tr>
      <tr>
        <td colspan="2">项目名称:</td>
        <td colspan="2">${prjectName}</td>
        <td colspan="2">楼栋房号:</td>
        <td colspan="2">${address}</td>
      </tr>
      <tr>
        <td colspan="2">业主姓名:</td>
        <td colspan="2">${houseOpen.customerName}</td>
        <td colspan="2">日期:</td>
        <td colspan="2">${openTime}</td>
      <tr>
      <tr id="trHeight" style="height: 650px;vertical-align: top">
        <!--<td colspan="2" style="align-content: center;line-height: 800px">情况记录</td>-->
        <td colspan="8" >
          <div style="line-height: 25px" class="content">
            <!--<div>根据《商品房买卖合同》及其补充协议，现对您购买该房屋进行验收交付，若属质量遗留瑕疵，详细记录需保修之项目，以便改善。</div>-->
            <div>情况记录：</div>
            <c:choose>
              <c:when test="<%=logo.equals(\"hello\")%>">
                <c:forEach items="${problems}" var="problem" varStatus="row">
                  <div class="divautohight">
                    <span class="index_left">${row.index+1}、</span>
                    <span class="position_left">【${problem.casePlace}】</span>
                    <span class="thirdType_left">【${problem.thirdTypeName}】</span>
                      <%-- <span class="description_tight">${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>--%>
                    <span>${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>
                  </div>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <span style="margin-left:60px;"><%=logo%></span>
              </c:otherwise>
            </c:choose>
          </div>
        </td>
      </tr>
    </table>
    <table>
      <tr   style="border:0px;">
        <td colspan="8" align="center">
            <h3 style="margin-top:30px;margin-bottom:30px;overflow: hidden;">
                <span style="line-height: 100px;float: left;">业主签名：</span>
              <c:if test="${signUrl ne ''}">
                <img class="signUrlImg" height="100px" width="100px"  style="float: left;"  src="${signUrl}">
              </c:if>
                <span style="margin-left: 230px;float: left;line-height: 100px;">陪同验房人签字：</span>
            </h3>
        </td>
      </tr>
    </table>
  </div>
</c:if>
<%--内部预验（11）走当前判断--%>
<c:if test="${type eq '11'}">

  <div class="mydiv">
    <center><input align="center" type="button" value="打印" onclick="pr();" class="noprint"></center>
    <h4 align="center">内部预验意见征询表-第一联</h4>
    <table class="gridtable">
      <tr>
        <td colspan="8" style="border: 0px;text-align: left">编号：${rId}</td>
      </tr>
      <tr>
        <td colspan="2">项目名称:</td>
        <td colspan="2">${prjectName}</td>
        <td colspan="2">楼栋房号:</td>
        <td colspan="2">${address}</td>
      </tr>
      <tr>
        <td colspan="2">业主姓名:</td>
        <td colspan="2">${internal.customerName}</td>
        <td colspan="2">日期:</td>
        <td colspan="2">${internalTime}</td>
      <tr>
      <tr id="trHeight" style="height: 650px;vertical-align: top">
        <!--<td colspan="2" style="align-content: center;line-height: 800px">情况记录</td>-->
        <td colspan="8" >
          <div style="line-height: 25px" class="content">
            <!--<div>根据《商品房买卖合同》及其补充协议，现对您购买该房屋进行验收交付，若属质量遗留瑕疵，详细记录需保修之项目，以便改善。</div>-->
            <div>情况记录：</div>
            <c:choose>
              <c:when test="<%=logo.equals(\"hello\")%>">
                <c:forEach items="${problems}" var="problem" varStatus="row">
                  <div class="divautohight">
                    <span class="index_left">${row.index+1}、</span>
                    <span class="position_left">【${problem.casePlace}】</span>
                    <span class="thirdType_left">【${problem.thirdTypeName}】</span>
                      <%--<span class="description_tight">${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>--%>
                    <span>${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>
                  </div>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <span style="margin-left:60px;"><%=logo%></span>
              </c:otherwise>
            </c:choose>
          </div>
        </td>
      </tr>
    </table>
    <table>
      <tr   style="border:0px;">
        <td colspan="8" align="center">
          <h3 style="margin-top:30px;margin-bottom:30px;overflow: hidden;">
              <span style="line-height: 100px;float: left;">业主签名：</span>
            <c:if test="${signUrl ne ''}">
              <img class="signUrlImg" height="100px" width="100px"  style="float: left;"  src="${signUrl}">
            </c:if>
              <span style="margin-left: 230px;float: left;line-height: 100px;">陪同验房人签字：</span>
          </h3>
        </td>
      </tr>
    </table>
  </div>
  <div class="PageNext"></div>
  <div class="mydiv">
    <h4 align="center">内部预验意见征询表-第二联</h4>
    <table class="gridtable">
      <tr>
        <td colspan="8" style="border: 0px;text-align: left">编号：${rId}</td>
      </tr>
      <tr>
        <td colspan="2">项目名称:</td>
        <td colspan="2">${prjectName}</td>
        <td colspan="2">楼栋房号:</td>
        <td colspan="2">${address}</td>
      </tr>
      <tr>
        <td colspan="2">业主姓名:</td>
        <td colspan="2">${internal.customerName}</td>
        <td colspan="2">日期:</td>
        <td colspan="2">${internalTime}</td>
      <tr>
      <tr id="trHeight" style="height: 650px;vertical-align: top">
        <!--<td colspan="2" style="align-content: center;line-height: 800px">情况记录</td>-->
        <td colspan="8" >
          <div style="line-height: 25px" class="content">
            <!--<div>根据《商品房买卖合同》及其补充协议，现对您购买该房屋进行验收交付，若属质量遗留瑕疵，详细记录需保修之项目，以便改善。</div>-->
            <div>情况记录：</div>
            <c:choose>
              <c:when test="<%=logo.equals(\"hello\")%>">
                <c:forEach items="${problems}" var="problem" varStatus="row">
                  <div class="divautohight">
                    <span class="index_left">${row.index+1}、</span>
                    <span class="position_left">【${problem.casePlace}】</span>
                    <span class="thirdType_left">【${problem.thirdTypeName}】</span>
                      <%--<span class="description_tight">${problem.caseDesc}<input class="checkboxs" name="answer" type="checkbox"/></span>--%>
                    <span>${problem.caseDesc}<input class="checkboxs" name="answer" type="checkbox"/></span>
                  </div>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <span style="margin-left:60px;"><%=logo%></span>
              </c:otherwise>
            </c:choose>
          </div>
        </td>
      </tr>
    </table>
    <table>
      <tr   style="border:0px;">
        <td colspan="8" align="center">
            <h3 style="margin-top:30px;margin-bottom:30px;overflow: hidden;">
                <span style="line-height: 100px;float: left;">业主签名：</span>
              <c:if test="${signUrl ne ''}">
                <img class="signUrlImg" height="100px" width="100px"  style="float: left;"  src="${signUrl}">
              </c:if>
                <span style="margin-left: 230px;float: left;line-height: 100px;">陪同验房人签字：</span>
            </h3>
        </td>
      </tr>
    </table>
  </div>
  <div class="PageNext"></div>
  <div class="mydiv">
    <h4 align="center">内部预验意见征询表-第三联</h4>
    <table class="gridtable">
      <tr>
        <td colspan="8" style="border: 0px;text-align: left">编号：${rId}</td>
      </tr>
      <tr>
        <td colspan="2">项目名称:</td>
        <td colspan="2">${prjectName}</td>
        <td colspan="2">楼栋房号:</td>
        <td colspan="2">${address}</td>
      </tr>
      <tr>
        <td colspan="2">业主姓名:</td>
        <td colspan="2">${internal.customerName}</td>
        <td colspan="2">日期:</td>
        <td colspan="2">${internalTime}</td>
      <tr>
      <tr id="trHeight" style="height: 650px;vertical-align: top">
        <!--<td colspan="2" style="align-content: center;line-height: 800px">情况记录</td>-->
        <td colspan="8" >
          <div style="line-height: 25px" class="content">
            <!--<div>根据《商品房买卖合同》及其补充协议，现对您购买该房屋进行验收交付，若属质量遗留瑕疵，详细记录需保修之项目，以便改善。</div>-->
            <div>情况记录：</div>
            <c:choose>
              <c:when test="<%=logo.equals(\"hello\")%>">
                <c:forEach items="${problems}" var="problem" varStatus="row">
                  <div class="divautohight">
                    <span class="index_left">${row.index+1}、</span>
                    <span class="position_left">【${problem.casePlace}】</span>
                    <span class="thirdType_left">【${problem.thirdTypeName}】</span>
                      <%--<span class="description_tight">${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>--%>
                    <span>${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>
                  </div>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <span style="margin-left:60px;"><%=logo%></span>
              </c:otherwise>
            </c:choose>
          </div>
        </td>
      </tr>
    </table>
    <table>
      <tr   style="border:0px;">
        <td colspan="8" align="center">
            <h3 style="margin-top:30px;margin-bottom:30px;overflow: hidden;">
                <span style="line-height: 100px;float: left;">业主签名：</span>
              <c:if test="${signUrl ne ''}">
                <img class="signUrlImg" height="100px" width="100px"  style="float: left;"  src="${signUrl}">
              </c:if>
                <span style="margin-left: 230px;float: left;line-height: 100px;">陪同验房人签字：</span>
            </h3>
        </td>
      </tr>
    </table>
  </div>
</c:if>
</body>
</html>
