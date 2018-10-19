<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2016/1/27
  Time: 19:53
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
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <script type="text/javascript" src="../static/plus/js/jquery.validate.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <style>
        label.error {
            /*position: absolute;*/
            /*margin-top: -74px;*/
            /*display: block;*/
            margin-left: 1%;
            color: red;
        }
        tr,td{
            text-align: right;
        }
    </style>

</head>

<body class="cbp-spmenu-push">
    <div class="main-content">
       <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005800050000" username="${propertystaff.staffName}" />
           <div class="forms">
               <div class="widget-shadow " data-example-id="basic-forms">
                   <div class="form-body">
                       <form class="form-horizontal" id="addseller" action="../seller/updateSeller.html" enctype="MULTIPART/FORM-DATA" method="post">
                           <input type="hidden" id="sellerId" name="sellerId" value="${seller.sellerId}">
                           <table align="center" class="table">
                               <tr><td>
                                   <label for="sellerName" class="control-label"><spring:message code="mall.sellerName" />：</label>
                                   </td>
                                   <td>
                                       <input type="text" id="sellerName" name="sellerName" value="${seller.sellerName}" class="form-control">
                                  </td>
                                   <td>
                                       <label for="sellerTel" class="control-label"><spring:message code="mall.sellerTel" />：</label>
                                       </td>
                                   <td>
                                       <input type="text" id="sellerTel" name="sellerTel" value="${seller.sellerTel}" class="form-control">
                                   </td>
                                   <td>
                                       <label for="sellerType" class="control-label"><spring:message code="mall.type" />：</label>
                                   </td>
                                   <td>
                                       <select id="sellerType" name="sellerType" class="form-control">
                                           <c:forEach items="${typeList}" var="item">
                                               <option value="${item.typeId}" <c:if test="${seller.sellerType==item.typeId}">selected</c:if>>
                                                       ${item.typeName}
                                               </option>
                                           </c:forEach>
                                       </select>
                                   </td></tr>
                               <tr><td>
                                   <label for="sellerPeople" class="control-label"><spring:message code="mall.people" />：</label>
                                   </td>
                                   <td>
                                       <input type="text" id="sellerPeople" name="sellerDirector" value="${seller.sellerDirector}" class="form-control">
                                  </td>
                                   <td>
                                       <label for="sellerPeopleTel" class="control-label"><spring:message code="mall.peopleTel" />：</label>
                                       </td>
                                   <td>
                                       <input type="text" id="sellerPeopleTel" name="sellerDirectorTel" value="${seller.sellerDirectorTel}" class="form-control">
                               </td>
                                   <td>
                                       <label for="range" class="control-label"><spring:message code="mall.range" />：</label>
                                       </td>
                                   <td>
                                       <input type="text" id="range" name="range" value="${seller.range}" class="form-control">
                                   </td></tr>
                               <tr><td>
                                   <label for="sellerAddress" class="control-label"><spring:message code="mall.sellerAddress" />：</label>
                                   </td>
                                   <td colspan="3">
                                   <input type="text" id="sellerAddress" name="sellerAddress" value="${seller.sellerAddress}" class="form-control">
                                   </td>
                                   <td rowspan="2" colspan="2">
                                       <div id="img_result" style="text-align: center"><img src="${seller.logo}" width="200" height="150"></div>
                                   </td></tr>
                               <tr><td>
                                   <label for="isTop" class="control-label"><spring:message code="mall.isTop" />：</label>
                                   </td>
                                   <td style="text-align: left">
                                   <input type="radio" id="isTop" name="isTop" <c:if test="${seller.isTop==false}">checked="checked" </c:if> value="false">否
                                   <input type="radio" name="isTop" <c:if test="${seller.isTop==true}">checked="checked" </c:if> value="true">是
                               </td>
                                   <td>
                                       <label for="logo" class="control-label"><spring:message code="mall.picture" />：</label>
                                       </td>
                                   <td>
                                       <input type="file" id="logo" name="imgFile" value="${seller.logo}" class="form-control" accept="image/*" />
                                   </td></tr>
                               <tr>
                                   <td colspan="6" style="text-align: center">
                                       <button type="button" class="btn btn-primary" onclick="history.go(-1)"><spring:message code="common_cancel" /></button>
                                       <button type="submit" class="btn btn-primary"><spring:message code="common_confirm"/></button>
                                   </td>
                               </tr>
                           </table>
                           <%--<div class="form-group  col-lg-4">--%>
                               <%--<label for="sellerName" class="col-sm-5 control-label"><spring:message code="mall.sellerName" /></label>--%>
                               <%--<div class="col-sm-7">--%>
                                   <%--<input type="text" id="sellerName" name="sellerName" value="${seller.sellerName}" class="form-control">--%>
                               <%--</div>--%>
                           <%--</div>--%>
                           <%--<div class="form-group col-lg-4">--%>
                               <%--<label for="sellerTel" class="col-sm-5 control-label"><spring:message code="mall.sellerTel" /></label>--%>
                               <%--<div class="col-sm-7">--%>
                                   <%--<input type="text" id="sellerTel" name="sellerTel" value="${seller.sellerTel}" class="form-control">--%>
                                <%--</div>--%>
                           <%--</div>--%>
                           <%--<div class="form-group col-lg-4">--%>
                                <%--<label for="sellerType" class="col-sm-5 control-label"><spring:message code="mall.type" /></label>--%>
                                <%--<div class="col-sm-7">--%>
                                   <%--<select id="sellerType" name="sellerType" class="form-control">--%>
                                       <%--<c:forEach items="${typeList}" var="item">--%>
                                           <%--<option value="${item.typeId}" <c:if test="${seller.sellerType==item.typeId}">selected</c:if>>--%>
                                           <%--${item.typeName}--%>
                                           <%--</option>--%>
                                       <%--</c:forEach>--%>
                                   <%--</select>--%>
                                <%--</div>--%>
                           <%--</div>--%>
    </div>
    </div>
    </div>
    <script type="text/javascript">
        var result = document.getElementById("img_result");
        var input = document.getElementById("logo");

        if(typeof FileReader === 'undefined'){
            result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
            input.setAttribute('disabled','disabled');
        }else{
            input.addEventListener('change',readFile,false);
        }

        function readFile() {
            result.innerHTML="";
            for (var i = 0; i < this.files.length; i++) {
                var file = this.files[i];
                if (!/image\/\w+/.test(file.type)) {
                    alert("请确保文件为图像类型");
                    return false;
                }
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function (e) {
                    result.innerHTML += '<img width="200" height="150" src="' + this.result + '" alt=""/>';
                }
            }
        }

        $(function() {
            $("#addseller").validate({
                rules: {
                    sellerName: {
                        required: true,
                        minlength: 1,
                        maxlength: 20
                    },
                    sellerTel: {
                        required: true,
                        minlength: 3,
                        maxlength: 20
                    },
                    sellerDirector: {
                        required: true,
                        minlength: 1,
                        maxlength:10
                    },
                    sellerDirectorTel:{
                        required:true,
                        minlength:3,
                        maxlength:20
                    },
                    sellerAddress:{
                        required:true,
                        minlength:1,
                        maxlength:50
                    },
                    range:{
                        required:true,
                        number:true,
                        minlength:1,
                        maxlength:99999
                    }
                },
                messages: {
                    sellerName: {
                        required: "请输入商户名！",
                        minlength: "不能少于1个字符！",
                        maxlength: "请勿超过20个字！"
                    },
                    sellerTel: {
                        required: "请输入商户电话！",
                        minlength: "不能少于3个字符！",
                        maxlength: "请勿超过20个字符！"
                    },
                    sellerDirector: {
                        required: "请输入商户联系人！",
                        minlength: "不能少于1个字符",
                        maxlength: "请勿超过10个字"
                    },
                    sellerDirectorTel: {
                        required: "请输入联系人电话",
                        minlength: "不能少于3个字符",
                        maxlength: "请勿超过20个字符"
                    },
                    sellerAddress: {
                        required: "请输入地址！",
                        minlength: "最少输入1个字符",
                        maxlength: "请勿超过50个字"
                    },
                    range: {
                        required: "请输入距离",
                        minlength: "不能少于1",
                        maxlength: "请勿超过99999",
                        number: "请输入数字"
                    }
                }
            })
        });

        $(function(){
            if($("#flag").val()=="true"){
                alert("修改成功！")
            }
        })
    </script>
                </form>
        <input type="hidden" id="flag" value="${result}">
            </div>
        </div>
    </div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
