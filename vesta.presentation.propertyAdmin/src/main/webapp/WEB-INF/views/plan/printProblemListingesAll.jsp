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
    <c:if test="${type eq '11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第一联"/>
                <jsp:param name="status" value="yes"/>
            </jsp:include>

            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>
            <jsp:include page="printProblemListinges4.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>
            <table class="tablediv">
                <tr style="border:0px;">
                    <td colspan="8" align="center">
                        <h3 style="margin-top:30px;margin-bottom:30px;">
              <span>业主签名：
                <c:if test="${signUrl ne ''}">
                    <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                </c:if>
              </span>
                            <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                        </h3>
                    </td>
                </tr>
            </table>

        </div>
    </c:if>
    <c:if test="${type eq '13'}">
        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            <jsp:param name="name" value="第一联"/>
            <jsp:param name="status" value="yes"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>
        <jsp:include page="printProblemListinges3.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>
    </c:if>

    <%--联2--%>
    <hr align="center" width="50%" size="1" noshade class="noprint">
    <div class="PageNext"></div>
    <c:if test="${type eq '11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第二联"/>
                <jsp:param name="status" value="no"/>
            </jsp:include>

            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>
            <jsp:include page="printProblemListinges4.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>

            <table class="tablediv">
                <tr style="border:0px;">
                    <td colspan="8" align="center">
                        <h3 style="margin-top:30px;margin-bottom:30px;">
              <span>业主签名：
                <c:if test="${signUrl ne ''}">
                    <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                </c:if>
              </span>
                            <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                        </h3>
                    </td>
                </tr>
            </table>
        </div>
    </c:if>
    <c:if test="${type eq '13'}">
        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            <jsp:param name="name" value="第二联"/>
            <jsp:param name="status" value="no"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>
        <jsp:include page="printProblemListinges3.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>
    </c:if>

    <%--联3--%>
    <%--<hr align="center" width="50%" size="1" noshade class="noprint">--%>
    <%--<div class="PageNext"></div>--%>
    <c:if test="${type eq '11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第三联"/>
                <jsp:param name="status" value="no"/>
            </jsp:include>

            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>
            <jsp:include page="printProblemListinges4.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>

            <table class="tablediv">
                <tr style="border:0px;">
                    <td colspan="8" align="center">
                        <h3 style="margin-top:30px;margin-bottom:30px;">
              <span>业主签名：
                <c:if test="${signUrl ne ''}">
                    <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                </c:if>
              </span>
                            <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                        </h3>
                    </td>
                </tr>
            </table>
        </div>
    </c:if>
    <c:if test="${type eq '13'}">

        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            <jsp:param name="name" value="第三联"/>
            <jsp:param name="status" value="no"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>
        <jsp:include page="printProblemListinges3.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>

    </c:if>
</c:if>
<%--情况2/////////////大于40并且小于70////////////////////////////////////////////////////////////////////////////////--%>
<c:if test="<%=count/InitializeSize>1&&count<(InitializeSize*2-10)%>">

    <%--联1--%>
    <c:if test="${type eq '13'}">
        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="status" value="yes"/>
            <jsp:param name="name" value="第一联"/>
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>
        <jsp:include page="printProblemListinges4.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>
        <jsp:include page="printProblemListinges3.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>
    </c:if>
    <c:if test="${type eq  '11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="status" value="yes"/>
                <jsp:param name="name" value="第一联"/>
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            </jsp:include>
            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>
            <jsp:include page="printProblemListinges4.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>

            <div class="tablediv">
                <table>
                    <tr style="border:0px;">
                        <td colspan="8" align="center">
                            <h3 style="margin-top:30px;margin-bottom:30px;">
                                  <span>业主签名：
                                    <c:if test="${signUrl ne ''}">
                                        <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                    </c:if>
                                  </span>
                                <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                            </h3>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </c:if>

    <%--联2--%>
    <hr align="center" width="50%" size="1" noshade class="noprint">
    <div class="PageNext"></div>
    <c:if test="${type eq '13'}">
        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="status" value="no"/>
            <jsp:param name="name" value="第二联"/>
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>
        <jsp:include page="printProblemListinges4.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>
        <jsp:include page="printProblemListinges3.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>
    </c:if>
    <c:if test="${type eq  '11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="status" value="no"/>
                <jsp:param name="name" value="第二联"/>
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            </jsp:include>
            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>
            <jsp:include page="printProblemListinges4.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>

            <div class="tablediv">
                <table>
                    <tr style="border:0px;">
                        <td colspan="8" align="center">
                            <h3 style="margin-top:30px;margin-bottom:30px;">
                                  <span>业主签名：
                                    <c:if test="${signUrl ne ''}">
                                        <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                    </c:if>
                                  </span>
                                <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                            </h3>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </c:if>

    <%--联3--%>
    <hr align="center" width="50%" size="1" noshade class="noprint">
    <div class="PageNext"></div>
    <c:if test="${type eq '13'}">
        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="status" value="no"/>
            <jsp:param name="name" value="第三联"/>
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>
        <jsp:include page="printProblemListinges4.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>
        <jsp:include page="printProblemListinges3.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>
    </c:if>
    <c:if test="${type eq  '11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="status" value="no"/>
                <jsp:param name="name" value="第三联"/>
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            </jsp:include>
            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>
            <jsp:include page="printProblemListinges4.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>

            <div class="tablediv">
                <table>
                    <tr style="border:0px;">
                        <td colspan="8" align="center">
                            <h3 style="margin-top:30px;margin-bottom:30px;">
                                  <span>业主签名：
                                    <c:if test="${signUrl ne ''}">
                                        <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                    </c:if>
                                  </span>
                                <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                            </h3>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </c:if>

</c:if>
<%--情况3////////////////大于70且小于80//////////////////////////////////////////////////////////////////////////////--%>
<c:if test="<%=count>InitializeSize*2-10&&count<=InitializeSize*2%>">

    <%--联1--%>
    <c:if test="${type eq '13'}">
        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            <jsp:param name="name" value="第一联"/>
            <jsp:param name="status" value="yes"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>

        <jsp:include page="printProblemListinges4.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>

        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>

        <jsp:include page="printProblemListinges2.jsp"/>
    </c:if>
    <c:if test="${type eq'11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第一联"/>
                <jsp:param name="status" value="yes"/>
            </jsp:include>
            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>

            <jsp:include page="printProblemListinges4.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>

            <div class="tablediv">
                <table>
                    <tr style="border:0px;">
                        <td colspan="8" align="center">
                            <h3 style="margin-top:30px;margin-bottom:30px;">
                                  <span>业主签名：
                                    <c:if test="${signUrl ne ''}">
                                        <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                    </c:if>
                                  </span>
                                <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                            </h3>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

    </c:if>

    <%--联2--%>
    <hr align="center" width="50%" size="1" noshade class="noprint">
    <div class="PageNext"></div>
    <c:if test="${type eq '13'}">
        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            <jsp:param name="name" value="第二联"/>
            <jsp:param name="status" value="no"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>

        <jsp:include page="printProblemListinges4.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>

        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>

        <jsp:include page="printProblemListinges2.jsp"/>
    </c:if>
    <c:if test="${type eq'11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第二联"/>
                <jsp:param name="status" value="no"/>
            </jsp:include>
            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>

            <jsp:include page="printProblemListinges4.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>

            <div class="tablediv">
                <table>
                    <tr style="border:0px;">
                        <td colspan="8" align="center">
                            <h3 style="margin-top:30px;margin-bottom:30px;">
                                  <span>业主签名：
                                    <c:if test="${signUrl ne ''}">
                                        <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                    </c:if>
                                  </span>
                                <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                            </h3>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

    </c:if>

    <%--联3--%>
    <hr align="center" width="50%" size="1" noshade class="noprint">
    <div class="PageNext"></div>
    <c:if test="${type eq '13'}">
        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            <jsp:param name="name" value="第三联"/>
            <jsp:param name="status" value="no"/>
        </jsp:include>
        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>

        <jsp:include page="printProblemListinges4.jsp">
            <jsp:param name="stra" value="<%=InitializeSize%>"/>
            <jsp:param name="count" value="<%=count-1%>"/>
        </jsp:include>

        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>

        <jsp:include page="printProblemListinges2.jsp"/>
    </c:if>
    <c:if test="${type eq'11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第三联"/>
                <jsp:param name="status" value="no"/>
            </jsp:include>
            <hr align="center" width="50%" size="1" noshade class="noprint">
            <div class="PageNext"></div>

            <jsp:include page="printProblemListinges4.jsp">
                <jsp:param name="stra" value="<%=InitializeSize%>"/>
                <jsp:param name="count" value="<%=count-1%>"/>
            </jsp:include>

            <div class="tablediv">
                <table>
                    <tr style="border:0px;">
                        <td colspan="8" align="center">
                            <h3 style="margin-top:30px;margin-bottom:30px;">
                              <span>业主签名：
                                <c:if test="${signUrl ne ''}">
                                    <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                </c:if>
                              </span>
                                <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                            </h3>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

    </c:if>
</c:if>
<%--情况4/////////////大于80///////count%40==0整除////////////////count%40求余//////////////////页面4能被循环几次//////--%>
<c:if test="<%=count>InitializeSize*2%>">

    <%--联1--%>
    <c:if test="${type eq '13'}">
        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            <jsp:param name="name" value="第一联"/>
            <jsp:param name="status" value="yes"/>
        </jsp:include>

        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>
        <%--倍数 count/40 可以设置为循环的次数 ,循环的开始      ，   和结束    --%>
        <c:if test="<%=count%36==0%>">
            <c:forEach begin="1" end="<%=count/40%>" step="1" varStatus="status">
                <%--${status.index}--%>
                <jsp:include page="printProblemListinges4.jsp">
                    <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                    <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                </jsp:include>
                <hr align="center" width="50%" size="1" noshade class="noprint">
                <div class="PageNext"></div>
            </c:forEach>
            <jsp:include page="printProblemListinges2.jsp"/>
        </c:if>
        <c:if test="<%=count%36!=0%>">
            <c:forEach begin="1" end="<%=count/40%>" step="1" varStatus="status">
                <%--${status.index}--%>
                <jsp:include page="printProblemListinges4.jsp">
                    <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                    <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                </jsp:include>
                <hr align="center" width="50%" size="1" noshade class="noprint">
                <div class="PageNext"></div>
            </c:forEach>
            <c:if test="<%=count%36<=30%>">
                <jsp:include page="printProblemListinges3.jsp">
                    <jsp:param name="stra" value="<%=InitializeSize*(count/40)%>"/>
                    <jsp:param name="count" value="<%=count-1%>"/>
                </jsp:include>
            </c:if>
            <c:if test="<%=count%36>30%>">
                <jsp:include page="printProblemListinges2.jsp"/>
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${type eq '11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第一联"/>
                <jsp:param name="status" value="yes"/>
            </jsp:include>
                <%--<hr align="center" width="50%" size="1" noshade class="noprint">--%>
                <%--<div class="PageNext"></div>--%>
                <%--倍数 count/40 可以设置为循环的次数 ,循环的开始      ，   和结束    --%>
            <c:if test="<%=count%36==0%>">
                <c:forEach begin="1" end="<%=count/36%>" step="1" varStatus="status">
                    <%--${status.index}--%>
                    <hr align="center" width="50%" size="1" noshade class="noprint">
                    <div class="PageNext"></div>
                    <jsp:include page="printProblemListinges4.jsp">
                        <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                        <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                    </jsp:include>
                </c:forEach>
                <div class="tablediv">
                    <table>
                        <tr style="border:0px;">
                            <td colspan="8" align="center">
                                <h3 style="margin-top:30px;margin-bottom:30px;">
                                      <span>业主签名：
                                        <c:if test="${signUrl ne ''}">
                                            <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                        </c:if>
                                      </span>
                                    <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                                </h3>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
            <c:if test="<%=count%36!=0%>">
                <c:forEach begin="1" end="<%=count/36%>" step="1" varStatus="status">
                    <%--${status.index}--%>
                    <hr align="center" width="50%" size="1" noshade class="noprint">
                    <div class="PageNext"></div>
                    <jsp:include page="printProblemListinges4.jsp">
                        <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                        <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                    </jsp:include>
                </c:forEach>
                <%--<c:if test="<%=count%40<=30%>">--%>
                <div class="tablediv">
                    <table>
                        <tr style="border:0px;">
                            <td colspan="8" align="center">
                                <h3 style="margin-top:30px;margin-bottom:30px;">
                                          <span>业主签名：
                                            <c:if test="${signUrl ne ''}">
                                                <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                            </c:if>
                                          </span>
                                    <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                                </h3>
                            </td>
                        </tr>
                    </table>
                </div>
                <%--</c:if>--%>
            </c:if>
        </div>
    </c:if>

    <%--联2--%>
    <hr align="center" width="50%" size="1" noshade class="noprint">
    <div class="PageNext"></div>
    <c:if test="${type eq '13'}">
        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            <jsp:param name="name" value="第二联"/>
            <jsp:param name="status" value="no"/>
        </jsp:include>

        <hr align="center" width="50%" size="1" noshade class="noprint">
        <div class="PageNext"></div>
        <%--倍数 count/40 可以设置为循环的次数 ,循环的开始      ，   和结束    --%>
        <c:if test="<%=count%36==0%>">
            <c:forEach begin="1" end="<%=count/40%>" step="1" varStatus="status">
                <%--${status.index}--%>
                <jsp:include page="printProblemListinges4.jsp">
                    <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                    <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                </jsp:include>
                <hr align="center" width="50%" size="1" noshade class="noprint">
                <div class="PageNext"></div>
            </c:forEach>
            <jsp:include page="printProblemListinges2.jsp"/>
        </c:if>
        <c:if test="<%=count%36!=0%>">
            <c:forEach begin="1" end="<%=count/36%>" step="1" varStatus="status">
                <%--${status.index}--%>
                <jsp:include page="printProblemListinges4.jsp">
                    <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                    <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                </jsp:include>
                <hr align="center" width="50%" size="1" noshade class="noprint">
                <div class="PageNext"></div>
            </c:forEach>
            <c:if test="<%=count%36<=30%>">
                <jsp:include page="printProblemListinges3.jsp">
                    <jsp:param name="stra" value="<%=InitializeSize*(count/40)%>"/>
                    <jsp:param name="count" value="<%=count-1%>"/>
                </jsp:include>
            </c:if>
            <c:if test="<%=count%36>30%>">
                <jsp:include page="printProblemListinges2.jsp"/>
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${type eq '11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第二联"/>
                <jsp:param name="status" value="no"/>
            </jsp:include>
                <%--<hr align="center" width="50%" size="1" noshade class="noprint">--%>
                <%--<div class="PageNext"></div>--%>
                <%--倍数 count/40 可以设置为循环的次数 ,循环的开始      ，   和结束    --%>
            <c:if test="<%=count%36==0%>">
                <c:forEach begin="1" end="<%=count/36%>" step="1" varStatus="status">
                    <%--${status.index}--%>
                    <hr align="center" width="50%" size="1" noshade class="noprint">
                    <div class="PageNext"></div>
                    <jsp:include page="printProblemListinges4.jsp">
                        <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                        <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                    </jsp:include>
                </c:forEach>
                <div class="tablediv">
                    <table>
                        <tr style="border:0px;">
                            <td colspan="8" align="center">
                                <h3 style="margin-top:30px;margin-bottom:30px;">
                                      <span>业主签名：
                                        <c:if test="${signUrl ne ''}">
                                            <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                        </c:if>
                                      </span>
                                    <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                                </h3>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
            <c:if test="<%=count%36!=0%>">
                <c:forEach begin="1" end="<%=count/36%>" step="1" varStatus="status">
                    <%--${status.index}--%>
                    <hr align="center" width="50%" size="1" noshade class="noprint">
                    <div class="PageNext"></div>
                    <jsp:include page="printProblemListinges4.jsp">
                        <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                        <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                    </jsp:include>
                </c:forEach>
                <%--<c:if test="<%=count%40<=30%>">--%>
                <div class="tablediv">
                    <table>
                        <tr style="border:0px;">
                            <td colspan="8" align="center">
                                <h3 style="margin-top:30px;margin-bottom:30px;">
                                          <span>业主签名：
                                            <c:if test="${signUrl ne ''}">
                                                <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                            </c:if>
                                          </span>
                                    <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                                </h3>
                            </td>
                        </tr>
                    </table>
                </div>
                <%--</c:if>--%>
            </c:if>
        </div>
    </c:if>

    <%--联3--%>
    <hr align="center" width="50%" size="1" noshade class="noprint">
    <div class="PageNext"></div>
    <c:if test="${type eq '13'}">
        <jsp:include page="printProblemListinges1.jsp">
            <jsp:param name="count" value="<%=InitializeSize-1%>"/>
            <jsp:param name="name" value="第三联"/>
            <jsp:param name="status" value="no"/>
        </jsp:include>

        <%--<hr align="center" width="50%" size="1" noshade class="noprint">--%>
        <%--<div class="PageNext"></div>--%>
        <%--倍数 count/40 可以设置为循环的次数 ,循环的开始      ，   和结束    --%>
        <c:if test="<%=count%36==0%>">
            <c:forEach begin="1" end="<%=count/40%>" step="1" varStatus="status">
                <%--${status.index}--%>
                <hr align="center" width="50%" size="1" noshade class="noprint">
                <div class="PageNext"></div>
                <jsp:include page="printProblemListinges4.jsp">
                    <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                    <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                </jsp:include>
            </c:forEach>
            <jsp:include page="printProblemListinges2.jsp"/>
        </c:if>
        <c:if test="<%=count%36!=0%>">
            <c:forEach begin="1" end="<%=count/40%>" step="1" varStatus="status">
                <%--${status.index}--%>
                <hr align="center" width="50%" size="1" noshade class="noprint">
                <div class="PageNext"></div>
                <jsp:include page="printProblemListinges4.jsp">
                    <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                    <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                </jsp:include>
            </c:forEach>
            <c:if test="<%=count%36<=30%>">
                <jsp:include page="printProblemListinges3.jsp">
                    <jsp:param name="stra" value="<%=InitializeSize*(count/40)%>"/>
                    <jsp:param name="count" value="<%=count-1%>"/>
                </jsp:include>
            </c:if>
            <c:if test="<%=count%36>30%>">
                <jsp:include page="printProblemListinges2.jsp"/>
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${type eq '11' || type eq '12'}">
        <div class="mydiv">
            <jsp:include page="printProblemListinges11.jsp">
                <jsp:param name="count" value="<%=InitializeSize-1%>"/>
                <jsp:param name="name" value="第三联"/>
                <jsp:param name="status" value="no"/>
            </jsp:include>
                <%--<hr align="center" width="50%" size="1" noshade class="noprint">--%>
                <%--<div class="PageNext"></div>--%>
                <%--倍数 count/40 可以设置为循环的次数 ,循环的开始      ，   和结束    --%>
            <c:if test="<%=count%36==0%>">
                <c:forEach begin="1" end="<%=count/36%>" step="1" varStatus="status">
                    <%--${status.index}--%>
                    <hr align="center" width="50%" size="1" noshade class="noprint">
                    <div class="PageNext"></div>
                    <jsp:include page="printProblemListinges4.jsp">
                        <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                        <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                    </jsp:include>
                </c:forEach>
                <div class="tablediv">
                    <table>
                        <tr style="border:0px;">
                            <td colspan="8" align="center">
                                <h3 style="margin-top:30px;margin-bottom:30px;">
                                      <span>业主签名：
                                        <c:if test="${signUrl ne ''}">
                                            <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                        </c:if>
                                      </span>
                                    <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                                </h3>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
            <c:if test="<%=count%36!=0%>">
                <c:forEach begin="1" end="<%=count/36%>" step="1" varStatus="status">
                    <%--${status.index}--%>
                    <hr align="center" width="50%" size="1" noshade class="noprint">
                    <div class="PageNext"></div>
                    <jsp:include page="printProblemListinges4.jsp">
                        <jsp:param name="stra" value="${InitializeSize*(status.index)}"/>
                        <jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>
                    </jsp:include>
                </c:forEach>
                <%--<c:if test="<%=count%40<=30%>">--%>
                <%--<hr align="center" width="50%" size="1" noshade class="noprint">--%>
                <%--<div class="PageNext"></div>--%>
                <%--<jsp:include page="printProblemListinges4.jsp">--%>
                <%--<jsp:param name="stra" value="${InitializeSize*(status.index)}"/>--%>
                <%--<jsp:param name="count" value="${InitializeSize*(status.index+1)-1}"/>--%>
                <%--</jsp:include>--%>
                <div class="tablediv">
                    <table>
                        <tr style="border:0px;">
                            <td colspan="8" align="center">
                                <h3 style="margin-top:30px;margin-bottom:30px;">
                                      <span>业主签名：
                                        <c:if test="${signUrl ne ''}">
                                            <img class="signUrlImg" height="100px" width="100px" src="${signUrl}">
                                        </c:if>
                                      </span>
                                    <span style="margin-top: 20px;margin-left: 230px;">陪同验房人签字：</span>
                                </h3>
                            </td>
                        </tr>
                    </table>
                </div>
                <%--</c:if>--%>
            </c:if>
        </div>
    </c:if>
</c:if>
</body>
</html>
