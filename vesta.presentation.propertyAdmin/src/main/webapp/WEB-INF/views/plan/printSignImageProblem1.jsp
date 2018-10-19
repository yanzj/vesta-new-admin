<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: luxinxin
  Date: 2016/8/10
  Time: 16:03
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
<%
    int stra=Integer.parseInt("" + request.getParameter("stra"));
    int enda=Integer.parseInt("" + request.getParameter("count"));
%>
<body>
<div class="mydiv">
    <table class="gridtable">
        <tr id="trHeight" style="height: 900px;vertical-align: top">
            <td colspan="8" >
                <div style="line-height: 25px" class="content">
                    <div>情况记录：</div>
                    <c:forEach items="${problems}" var="problem" varStatus="row" begin="<%=stra%>" end="<%=enda%>" step="1">
                        <div class="divautohight">
                            <span class="index_left">${row.index+1}、</span>
                            <span class="position_left">【${problem.casePlace}】</span>
                            <span>${problem.caseDesc}</span>
                        </div>
                    </c:forEach>
                    <!--40-->
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
