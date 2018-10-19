<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/5/25
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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

<!DOCTYPE HTML>
<html>
<head>
    <title><spring:message code="sys.tital"/></title>
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- Bootstrap Core CSS -->
    <link href="../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
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
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        $(function () {
            console.log("sqq")
            $("#001300020000").addClass("active");
            $("#001300020000").parent().parent().addClass("in");
            $("#001300020000").parent().parent().parent().parent().addClass("active");
        })
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <!--//Metis Menu -->
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="001300020000" username="${propertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" name="add" id="add" action="../agency/addAgencyPeople.html">
                    <input type="hidden" name="agencyId" value="${agencyId}">
                    <input type="hidden" name="agencyType" value="1">

                    <div class="form-group  col-lg-4">
                        <label for="agencyName" class="col-sm-4 control-label">真实姓名:</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="agencyName" name="agencyName"
                                   value="${staffName}">
                        </div>
                    </div>
                    <div class="form-group  col-lg-4">
                        <label for="parentId" class="col-sm-4 control-label">用户名：</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" value="${nickName}" id="parentId"
                                   name="parentId">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" for="propertySearch">搜索</button>
                    <button onclick="history.go(-1)" type="button" class="btn btn-primary">返回</button>
                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>

    <div class="table-responsive bs-example widget-shadow">
        <form name="save" id="save" action="../agency/saveAP.html">
            <button type="button" id="confirm" class="btn btn-primary" onclick="check()">确定</button>
            <input type="hidden" name="agencyId" value="${agencyId}">
            <table width="100%" class="tableStyle">
                <thead>
                <tr>
                    <td>序号</td>
                    <th>真实姓名</th>
                    <th>用户名</th>
                    <th>联系方式</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${staffList}" var="list" varStatus="row">
                    <tr>
                        <td><input type="checkbox"  name="staffId"
                                   value="${list.staffId}"><b>${row.index+1}</b></td>
                        <td>${list.staffName}</td>
                        <td>${list.nikeName}</td>
                        <td>${list.mobile}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </form>
        <%--<m:pager pageIndex="${webPage.pageIndex}"--%>
        <%--pageSize="${webPage.pageSize}"--%>
        <%--recordCount="${webPage.recordCount}"--%>
        <%--submitUrl="${pageContext.request.contextPath }../agency/addAgencyPeople.html?pageIndex={0}"/>--%>
    </div>

</div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
    function check() {
        var staff =document.getElementsByName("staffId");
        var checkbox=false;
        for(var i=0;i<staff.length;i++)
        {
            if(staff[i].checked)
            {
                checkbox=true;
            }
        }
//        checkbox= document.getElementsById("staffId").checked;
//        var checkbox = document.all.staffId;
//        for (var i = 0; i < checkbox.length; i++) {
//            if (checkbox[i].checked) {
//                flag = true;
//                break;
//            }
//        }
        if(checkbox){
            if (confirm("您确认要添加?")) {
                document.save.submit();
            } else {
//                $("input:checkbox[name='staffId']:checked").each(function() { // 遍历name=test的多选框
//                    console.log($(this).val());   // 每一个被选中项的值
//                });
                return;
            }
        }else {
            alert("没有人员被选中");
        }
    }
</script>

</body>

</html>