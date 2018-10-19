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
      height:297mm;
      /*float:left;*/
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
      margin-top: 8px;
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
<div class="mydiv">
  <img style="text-align: center"  src="../static/img/jinmaoimg.png">
  <table class="gridtable" align="center" width="800px">
    <tr>
      <th colspan="6">${prjectName}项目房屋交付验收表</th>
    </tr>
    <tr>
      <td colspan="2">编号：${rId}&nbsp; </td><td colspan="4" align="right">日期：${printDate}</td>
    </tr>
    <tr>
      <td colspan="6">房屋信息:${address}</td>
    </tr>
    <tr>
      <td>业主姓名:</td>
      <td colspan="2">${userName}</td>
      <td>联系电话:</td>
      <td colspan="2">${mobile}</td>
    </tr>
    <tr>
      <td colspan="6">问题清单</td>
    </tr>
    <tr style="height: 800px;vertical-align: top">
      <td colspan="6" >
        <div style="line-height: 25px" class="content"   >
           <c:forEach items="${problems}" var="problem" varStatus="row">
             <div>&nbsp;&nbsp;&nbsp;&nbsp;
            ${problem.caseDesc}<input class="checkboxs" name="answer" type="checkbox"/><div>
           </c:forEach>
        </div>
      </td>
    </tr>

    <tr>
      <td>物业公司：</td>
      <td width="150px"></td>
      <td>建设单位：</td>
      <td width="150px"></td>
      <td>业主：</td>
      <td width="150px"></td>
    </tr>
  </table>
  <center><input align="center" type="button" value="打印" onclick="pr();" class="noprint"></center>
</div>
</body>
</html>
