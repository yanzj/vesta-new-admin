<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <link href="../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
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
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
  <script src="../static/property/js/propertyHousPay.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">
  <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
               crunMenu="000200040000" username="${propertystaff.staffName}"/>
    <div class="forms">
      <div class="widget-shadow " data-example-id="basic-forms">
        <div class="form-body">
          <h3 class="text-center">&nbsp;</h3>
          <div class="form-body" style="min-height:666px">
            <form class="form-horizontal" id="frm" action="../homePageCarousel/saveOrUpdateCarousel" method="post"
                  enctype="MULTIPART/FORM-DATA">
              <input type="hidden" id="id" name="id" value="${announcementEntity.id}">
              <c:if test="${!empty projectCode and projectCode != ''}">
                <div class="form-group col-lg-7">
                    <label for="city" class="col-sm-9 control-label"><h4>您当前正在编辑 ${projectName} 的首页轮播图 <h4></label>
                    <div class="col-sm-5">
                      <input type="hidden" id="projectCode" name="projectCode" value="${projectCode}"/>
                    </div>
                  <h3 class="text-center">&nbsp;</h3>
                  <h3 class="text-center">&nbsp;</h3>
                </div>
              </c:if>
              <c:if test="${empty projectCode or projectCode == ''}">
                <%--城市区域--%>
                <div class="form-group  col-lg-10">
                  <label for="city" class="col-sm-3 control-label"><spring:message
                          code="announcementDTO.cityName"/></label>

                  <div class="col-sm-5">
                    <select id="city" name="city" class="form-control" onchange="queryProjectNameById()">
                      <c:forEach items="${city}" var="item" >
                        <option value="${item.sid }"
                                <c:if test="${item.sid eq '0'}">selected</c:if>>${item.name }</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="col-sm-2">
                    <input type="image" name="addCity" id="addCity"
                           value="<spring:message code="announcementDTO.add"/>"
                           onclick="cityAdd();return false;"
                           src="../static/images/add.ico">
                  </div>
                </div>
                <%--城市列表--%>
                <div class="form-group col-lg-10">
                  <label class="col-sm-3 control-label" for="cityList"
                         style="min-width:115px;"><spring:message
                          code="announcementDTO.selectedCity"/></label>

                  <div class="col-sm-5">
                                  <textarea class="form-control" name="cityList" id="cityList" readonly
                                            style="width: 432px; height: 71px;" ><c:if test="${allCityInScope != ''}">${allCityInScope }</c:if><c:if test="${allCityInScope == ''}">${cityInScope }</c:if></textarea>
                    <input type="hidden" id="cityIds" name="cityIds" value="${cityIdInScope}"/>
                  </div>

                </div>
                <%--城市下项目--%>
                <div class="form-group  col-lg-10">
                  <label for="projectName" class="col-sm-3 control-label"><spring:message
                          code="HousePayBatch.projectName"/></label>

                  <div class="col-sm-5">
                    <select id="projectName" name="projectName" class="form-control">
                    </select>
                  </div>
                  <div class="col-sm-2">
                    <input type="image" name="addProject" id="addProject"
                           value="" onclick="projectAdd();return false;" src="../static/images/add.ico">
                  </div>
                </div>

                <%--项目列表--%>
                <div class="form-group col-lg-10">
                  <label class="col-sm-3 control-label" for="projectList"
                         style="min-width:115px;"><spring:message
                          code="announcementDTO.selectedProject"/></label>

                  <div class="col-sm-5">
                                  <textarea class="form-control" name="projectList" id="projectList" readonly
                                            style="width: 432px; height: 71px;"><c:if test="${allCityInScope != ''}"></c:if><c:if test="${allCityInScope == ''}">${projectInScope }</c:if></textarea>
                    <input type="hidden" id="projectIds" name="projectIds"  value="${projectIdInScope}">
                  </div>
                </div>
              </c:if>
              <%--轮播图设置--%>
              <c:forEach items="${carouselList}" var="carousel">
              <div class="form-group col-lg-6">
                <label class="col-sm-3 control-label" for="multipartFiles">上传图片</label>

                <div class="col-sm-9">
                  <input type="file" class="form-control"
                         placeholder="<spring:message code = "CommunityOverview.imgRemark"/>"
                         id="multipartFiles"
                         name="multipartFiles" accept="image/*"
                         height="160px" width="280px"
                         style="width: 350px;" onchange="check(this)">
                  <p style="color: red">
                    <span>请上传首页轮播图片，建议长宽比750×380</span>
                  </p>
                  <!-- 图片-->
                  <%--<div class="form-group col-lg-5">
                    <div class="col-sm-9" id="circleLogoUrl" name="circleLogoUrl">
                      <c:if test="${defaultConfig.configValue != null}">
                        <img src="${defaultConfig.configValue}"
                             style="width: 150px;height: 150px"/>
                      </c:if>
                    </div>
                  </div>--%>
                </div>
                <div class='clearfix'></div>
                <div id="demo_multipartFiles" style="padding-top: 5px;">
                </div>
              </div>
              <div class="form-group col-lg-6">
                <label class="col-sm-3 control-label">链接地址</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" placeholder="" id="linkUrls" name="linkUrls"
                         onchange="checkVal()"
                         value="${carousel.linkUrl}">
                </div>
                <h3 class="text-center">&nbsp;</h3>
              </div>
              </c:forEach>
<%--

              &lt;%&ndash;轮播图设置&ndash;%&gt;
              <div class="form-group col-lg-6">
                <label class="col-sm-3 control-label" for="multipartFiles">上传图片</label>

                <div class="col-sm-9">
                  <input type="file" class="form-control"
                         placeholder="<spring:message code = "CommunityOverview.imgRemark"/>"
                         id="multipartFiles"
                         name="multipartFiles" accept="image/*"
                         height="160px" width="280px"
                         style="width: 350px;" onchange="check(this)">
                  <p style="color: red">
                    <span>请上传首页轮播图片，建议长宽比750×380</span>
                  </p>
                  <!-- 图片-->
                  &lt;%&ndash;<div class="form-group col-lg-5">
                    <div class="col-sm-9" id="circleLogoUrl" name="circleLogoUrl">
                      <c:if test="${defaultConfig.configValue != null}">
                        <img src="${defaultConfig.configValue}"
                             style="width: 150px;height: 150px"/>
                      </c:if>
                    </div>
                  </div>&ndash;%&gt;
                </div>
                <div class='clearfix'></div>
                <div id="demo_multipartFiles" style="padding-top: 5px;">
                </div>
              </div>
              <div class="form-group col-lg-6">
                <label class="col-sm-3 control-label">链接地址</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" placeholder="" id="linkUrls" name="linkUrls"
                         onchange="checkVal()"
                         value="${HomePageCarouselDTO.linkUrl}">
                </div>
                <h3 class="text-center">&nbsp;</h3>
              </div>

              &lt;%&ndash;轮播图设置&ndash;%&gt;
              <div class="form-group col-lg-6">
                <label class="col-sm-3 control-label" for="multipartFiles">上传图片</label>

                <div class="col-sm-9">
                  <input type="file" class="form-control"
                         placeholder="<spring:message code = "CommunityOverview.imgRemark"/>"
                         id="multipartFiles"
                         name="multipartFiles" accept="image/*"
                         height="160px" width="280px"
                         style="width: 350px;" onchange="check(this)">
                  <p style="color: red">
                    <span>请上传首页轮播图片，建议长宽比750×380</span>
                  </p>
                  <!-- 图片-->
                  &lt;%&ndash;<div class="form-group col-lg-5">
                    <div class="col-sm-9" id="circleLogoUrl" name="circleLogoUrl">
                      <c:if test="${defaultConfig.configValue != null}">
                        <img src="${defaultConfig.configValue}"
                             style="width: 150px;height: 150px"/>
                      </c:if>
                    </div>
                  </div>&ndash;%&gt;
                </div>
                <div class='clearfix'></div>
                <div id="demo_multipartFiles" style="padding-top: 5px;">
                </div>
              </div>
              <div class="form-group col-lg-6">
                <label class="col-sm-3 control-label">链接地址</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" placeholder="" id="linkUrls" name="linkUrls"
                         onchange="checkVal()"
                         value="${HomePageCarouselDTO.linkUrl}">
                </div>
                <h3 class="text-center">&nbsp;</h3>
              </div>

              &lt;%&ndash;轮播图设置&ndash;%&gt;
              <div class="form-group col-lg-6">
                <label class="col-sm-3 control-label" for="multipartFiles">上传图片</label>

                <div class="col-sm-9">
                  <input type="file" class="form-control"
                         placeholder="<spring:message code = "CommunityOverview.imgRemark"/>"
                         id="multipartFiles"
                         name="multipartFiles" accept="image/*"
                         height="160px" width="280px"
                         style="width: 350px;" onchange="check(this)">
                  <p style="color: red">
                    <span>请上传首页轮播图片，建议长宽比750×380</span>
                  </p>
                  <!-- 图片-->
                  &lt;%&ndash;<div class="form-group col-lg-5">
                    <div class="col-sm-9" id="circleLogoUrl" name="circleLogoUrl">
                      <c:if test="${defaultConfig.configValue != null}">
                        <img src="${defaultConfig.configValue}"
                             style="width: 150px;height: 150px"/>
                      </c:if>
                    </div>
                  </div>&ndash;%&gt;
                </div>
                <div class='clearfix'></div>
                <div id="demo_multipartFiles" style="padding-top: 5px;">
                </div>
              </div>
              <div class="form-group col-lg-6">
                <label class="col-sm-3 control-label">链接地址</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" placeholder="" id="linkUrls" name="linkUrls"
                         onchange="checkVal()"
                         value="${HomePageCarouselDTO.linkUrl}">
                </div>
                <h3 class="text-center">&nbsp;</h3>
              </div>

              &lt;%&ndash;轮播图设置&ndash;%&gt;
              <div class="form-group col-lg-6">
                <label class="col-sm-3 control-label" for="multipartFiles">上传图片</label>

                <div class="col-sm-9">
                  <input type="file" class="form-control"
                         placeholder="<spring:message code = "CommunityOverview.imgRemark"/>"
                         id="multipartFiles"
                         name="multipartFiles" accept="image/*"
                         height="160px" width="280px"
                         style="width: 350px;" onchange="check(this)">
                  <p style="color: red">
                    <span>请上传首页轮播图片，建议长宽比750×380</span>
                  </p>
                  <!-- 图片-->
                  &lt;%&ndash;<div class="form-group col-lg-5">
                    <div class="col-sm-9" id="circleLogoUrl" name="circleLogoUrl">
                      <c:if test="${defaultConfig.configValue != null}">
                        <img src="${defaultConfig.configValue}"
                             style="width: 150px;height: 150px"/>
                      </c:if>
                    </div>
                  </div>&ndash;%&gt;
                </div>
                <div class='clearfix'></div>
                <div id="demo_multipartFiles" style="padding-top: 5px;">
                </div>
              </div>
              <div class="form-group col-lg-6">
                <label class="col-sm-3 control-label">链接地址</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" placeholder="" id="linkUrls" name="linkUrls"
                         onchange="checkVal()"
                         value="${HomePageCarouselDTO.linkUrl}">
                </div>
                <h3 class="text-center">&nbsp;</h3>
              </div>
--%>

              <div class="text-center form-group  col-lg-12" style="padding:0;">
                <button type="button" class="btn btn-primary" onclick="submit()"><spring:message
                        code="activityManage.activityPublish"/></button>
                <a href="../homePageCarousel/gotoCarouselList" class="btn btn-primary" for=""><spring:message
                        code="common_cancel"/></a>
              </div>

            </form>
          </div>
        </div>
      </div>
    </div>
</div>
</div>
</div>
</div>
</div>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>

<script>
  function submit(){
    $("#frm").submit();
  }
  function queryProjectNameById() {
    var projectId = $("#city").val();
    $("#planName").empty();
    $.getJSON("/announcement/queryProjectNameByCityId/" + projectId, function (data) {
      var arr = data.data;
      $("#projectName").empty();
      $("#projectName").append('<option value="0" id="projectName"><spring:message code="announcementDTO.allProject"/></option>');
      for (var k in arr) {
        $("#projectName").append('<option value=' + arr[k][0] + '>' + arr[k][1] + '</option>');
      }
    });
  }
  function cityAdd() {
    //#1.获取内容
    var projectName = $("#city").find("option:selected").text();
    var projectId = $("#city").find("option:selected").val();
    if (projectName == "全部城市") {
      //清空textarea
      $("#cityList").val("全部城市");
      $("#cityIds").val('0,');
    } else {
      if ($("#cityList").val() == "") {
        //如果textarea中没有元素
        $("#cityList").val($("#cityList").val() + projectName);
        $("#cityIds").val($("#cityIds").val() + projectId + ',');
      } else if ($("#cityList").val() != "") {
        //判断textArea中是否有select的值，如果没有则添加
        //获取textArea数组
        var strArray = $("#cityList").val().toString().split(",");
        //获取select值
        var str = $("#city").find("option:selected").text();
        //判断是否在数组中
        if (strArray.toString().indexOf(str) > -1) {
          return;
        }
        //判断是否="全部城市"
        if ($("#cityList").val() == "全部城市") {
          //清空
          $("#cityList").val("");
          $("#cityList").val($("#cityList").val() + projectName);
          $("#cityIds").val(projectId + ',');
          return;
        }
        //如果textarea中有元素，前置","
        $("#cityList").val($("#cityList").val() + ',' + projectName);
        $("#cityIds").val($("#cityIds").val() + projectId + ',');
      }
    }
  }
  function projectAdd() {
    //#1.获取内容
    var projectName = $("#projectName").find("option:selected").text();
    var projectId = $("#projectName").find("option:selected").val();
    if (projectName == "全部项目") {
      //清空textarea
      $("#projectList").val("全部项目");
      $("#projectIds").val("0,");
    } else {
      if ($("#projectList").val() == "") {
        //如果textarea中没有元素
        $("#projectList").val($("#projectList").val() + projectName);
        $("#projectIds").val($("#projectIds").val() + projectId + ',');
      } else if ($("#projectList").val() != "") {
        //判断textArea中是否有select的值，如果没有则添加
        //获取textArea数组
        var strArray = $("#projectList").val().toString().split(",");
        //获取select值
        var str = $("#projectName").find("option:selected").text();
        //判断是否在数组中
        if (strArray.toString().indexOf(str) > -1) {
          return;
        }
        //判断是否="全部项目"
        if ($("#projectList").val() == "全部项目") {
          //清空
          $("#projectList").val("");
          $("#projectList").val($("#projectList").val() + projectName);
          $("#projectIds").val(projectId + ',');
          return;
        }
        //如果textarea中有元素，前置","
        $("#projectList").val($("#projectList").val() + ',' + projectName);
        $("#projectIds").val($("#projectIds").val() + projectId + ',');
      }
    }
  }

  var inputs = $("#multipartFiles").get(0);
  var result = document.getElementById("circleLogoUrl");
  //var result = document.getElementById("demo_multipartFiles");

  if (typeof FileReader === 'undefined') {
    result.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
    inputs.setAttribute('disabled', 'disabled');
  } else {
    inputs.addEventListener('change', readFile, false);
  }

  function readFile() {

    for (var i = 0; i < this.files.length; i++) {
      var file = this.files[0];
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function (e) {
        result.innerHTML = '<img src="' + this.result + '"style="width: 750px;height: 380px" alt=""/>';
        results.innerHTML(result.innerHTML);
      }
      <!--限制图片大小-->
      /*            reader.onloadend = function(e){
       //判断图片
       var img = new Image();//构造JS的Image对象
       img.src = reader.result;//将本地图片赋给image对象

       if (img.width % 700 != 0) {
       result.innerHTML = "";
       //设置图片状态
       document.getElementById("imgStatus").value = 0;
       var obj = document.getElementById('imgStatus') ;
       obj.outerHTML=obj.outerHTML;
       //alert("对不起，图片宽度不符合，当前图片宽度为" + img.width );
       alert("对不起，图片宽度不符合，您的图片宽高比应该为700：400" );
       return;
       }
       if (img.height % 400 !=0) {
       result.innerHTML = "";
       //设置图片状态
       document.getElementById("imgStatus").value = 0;
       var obj = document.getElementById('imgStatus') ;
       obj.outerHTML=obj.outerHTML;
       //alert("对不起，图片宽度不符合，当前图片高度为" + img.height );
       alert("对不起，图片宽度不符合，您的图片宽高比应该为700：400" );
       return;
       }
       }*/
    }
  }

  var input = $("#activityimgpath").get(0);
  var results = document.getElementById("demo_activityimgpath");
  if (typeof FileReader === 'undefined') {
    results.innerHTML = "<p class='warn'>抱歉，你的浏览器不支持 FileReader</p>";
    input.setAttribute('disabled', 'disabled');
  } else {
    input.addEventListener('change', readFiles, false);
  }

  function readFiles() {
    for (var i = 0; i < this.files.length; i++) {
      var file = this.files[0];
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function (e) {
        results.innerHTML = '<img src="' + this.result + '" style="width: 750px;height: 380px"/>';
      }
    }
  }

  var imgtype = true;
  function check(fnUpload) {
    var filename = fnUpload.value;
    var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
    if (mime != ".jpg" && mime != ".png" && mime != ".jpeg" && mime != ".gif") {
      alert("上传图片类型错误");
      imgtype = false;
    } else {
      imgtype = true;
    }
  }
</script>
</body>
</html>
