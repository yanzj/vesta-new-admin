<%--
  Created by IntelliJ IDEA.
  User: luxinxin
  Date: 2016/8/10
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  String name=request.getParameter("name");
  String status=request.getParameter("status");
%>
<%--<div class="mydiv">--%>
  <c:if test='<%=status.equals("yes")%>'>
    <center><input align="center" type="button" value="打印" onclick="pr();" class="noprint"></center>
  </c:if>
  <c:if test="${type eq '11'}">
    <h4 align="center">内部预验意见征询表-<%=name%></h4>
  </c:if>
  <c:if test="${type eq '12'}">
    <h4 align="center">工地开放日业主意见征询表-<%=name%></h4>
  </c:if>
  <c:if test="${type eq '13'}">
    <h4 align="center">客户保修意见征询表-<%=name%></h4>
  </c:if>
  <!--<span>编号</span>-->
  <table class="gridtable">
    <tr> <td colspan="8" style="border: 0px;text-align: left">编号：${rId}</td></tr>
    <tr>
      <td colspan="2">项目名称:</td>
      <td colspan="2">${prjectName}</td>
      <td colspan="2">楼栋房号:</td>
      <td colspan="2">${address}</td>
    </tr>
    <c:if test="${type eq '11'}">
    <tr>
      <td colspan="2">业主姓名:</td>
      <td colspan="2">${internal.customerName}</td>
      <td colspan="2">日期:</td>
      <td colspan="2">${internalTime}</td>
    <tr>
    </c:if>
      <c:if test="${type eq '12'}">
    <tr>
      <td colspan="2">业主姓名:</td>
      <td colspan="2">${internal.customerName}</td>
      <td colspan="2">日期:</td>
      <td colspan="2">${internalTime}</td>
    <tr>
      </c:if>
    <tr id="trHeight" style="height: 900px;vertical-align: top">
      <td colspan="8" >
        <div style="line-height: 25px" class="content">
          <div>情况记录：</div>
          <c:forEach items="${problems}" var="problem" varStatus="row" begin="0" end="<%=count%>" step="1">
          <%--<div>&nbsp;&nbsp;&nbsp;&nbsp;--%>
              <%--${problem.caseDesc}<input class="checkboxs" name="answer" type="checkbox" disabled/><div>--%>
            <div class="divautohight">
              <span class="index_left">${row.index+1}、</span>
              <span class="position_left">【${problem.casePlace}】</span>
              <span class="thirdType_left">【${problem.thirdTypeName}】</span>
                <%--<span class="description_tight">${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>--%>
              <span >${problem.caseDesc} <input class="checkboxs" name="answer" type="checkbox"/></span>

            </div>
              </c:forEach>
          <!--40-->
        </div>
      </td>
    </tr>
  </table>
<%--</div>--%>
</body>
</html>
