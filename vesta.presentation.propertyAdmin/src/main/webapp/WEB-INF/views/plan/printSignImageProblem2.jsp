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
    <h4 align="center">${prinName}-<%=name%></h4>
</c:if>
<c:if test="${type eq '12'}">
    <h4 align="center">${prinName}-<%=name%></h4>
</c:if>
<c:if test="${type eq '13'}">
    <h4 align="center">${prinName}-<%=name%></h4>
</c:if>
<!--<span>编号</span>-->
<table class="gridtable">
    <tr class="thead">
        <td colspan="8" style="border: 0px;text-align: left">编号：${prinId}</td>
        <td colspan="8" style="border: 0px;text-align: right">日期：${strDate}</td>
    </tr>
    <tr style=" height: 20px;"></tr>
    <tr style="border: 1px solid #000;">
        <td colspan="7" style="border: 0px;text-align: left">业主：${userName}</td>
        <td colspan="9" style="border: 0px;text-align: right">房号：${address}</td>
    <tr>
    <tr id="trHeight" style="height:auto;vertical-align: top">
        <td colspan="16" >
            <div style="line-height: 25px" class="content">
                <div>情况记录：</div>
                <c:forEach items="${problems}" var="problem" varStatus="row">
                    <div class="divautohight">
                        <span class="index_left">${row.index+1}、</span>
                        <span class="position_left">【${problem.casePlace}】</span>
                        <span>${problem.caseDesc}</span>
                    </div>
                </c:forEach>
            </div>
        </td>
    </tr>
</table>
<%--</div>--%>
</body>
</html>
