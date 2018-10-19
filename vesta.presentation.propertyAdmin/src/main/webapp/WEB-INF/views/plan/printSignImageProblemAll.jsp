<%--
  Created by IntelliJ IDEA.
  User: luxinxin
  Date: 2016/8/10
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title></title>
    <style type="text/css">
        .divCenter {
            margin: 0 auto;
        }

        .mydiv {
            margin: 0 auto;
            min-width: 450px;
            min-height: 780px;
        }

        table.gridtable {
            margin: 0 auto;
            font-family: verdana, arial, sans-serif;
            font-size: 11px;
            border-collapse: collapse;
            width: 800px;

        }

        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            width: 80px;
        }

        .checkboxs {
            float: right;
            margin-right: 20px;
            margin-top: 8px;
        }


    </style>
    <style type="text/css" media="print">
        .noprint {
            display: none;
        }

        .PageNext {
            page-break-after: always;
        }
    </style>
    <style>
        .tdp {
            border-bottom: 1 solid #000000;
            border-left: 1 solid #000000;
            border-right: 0 solid #ffffff;
            border-top: 0 solid #ffffff;
        }

        .tabp {
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

        .tablediv {
            margin: 0 auto;
            width: 210mm;
            height: 60mm;
            /*float:left;*/
        }
    </style>
    <script language="JavaScript">
        //打印
        function pr() {
            window.print();
        }
    </script>
</head>
<%
    // int count=Integer.parseInt("" + request.getAttribute("count"));//总行数
    // int InitializeSize=Integer.parseInt("" + request.getAttribute("InitializeSize"));//---初始化一页40
    int count = Integer.parseInt("" + request.getAttribute("count"));//总行数
    int InitializeSize = 36;//---初始化一页40
%>
<%--情况1////////小于40//////////////////////////////////////////////////////////////////////////////////////////////--%>

<c:if test="<%=count/InitializeSize<=1%>">
    <%--联1--%>
    <c:if test="${type eq '13'}">
        <div class="mydiv">
            <jsp:include page="printSignImageProblem2.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第一联"/>
                <jsp:param name="status" value="yes"/>
            </jsp:include>

            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>
            <jsp:include page="printSignImageProblem1.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>
            <table class="tablediv">
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
    </c:if>

    <%--联2--%>
    <hr align="center" width="50%" size="1" noshade class="noprint">
    <div class="PageNext"></div>
    <c:if test="${type eq '13'}">
        <div class="mydiv">
            <jsp:include page="printSignImageProblem2.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第二联"/>
                <jsp:param name="status" value="no"/>
            </jsp:include>

            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>
            <jsp:include page="printSignImageProblem1.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>

            <table class="tablediv">
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
    </c:if>

    <%--联3--%>
    <%--<hr align="center" width="50%" size="1" noshade class="noprint">--%>
    <%--<div class="PageNext"></div>--%>
    <c:if test="${type eq '13'}">
        <div class="mydiv">
            <jsp:include page="printSignImageProblem2.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第三联"/>
                <jsp:param name="status" value="no"/>
            </jsp:include>

            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>
            <jsp:include page="printSignImageProblem1.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>

            <table class="tablediv">
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
    </c:if>
</c:if>

</html>
