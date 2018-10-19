<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/advert/js/addAdvert.js"></script>
    <script src="../static/property/js/checkNullAllJsp.js"></script>
    <script type="text/javascript">

        var addImgDivIndex = 0;
        var addImgDivIndexCnt = 0;

        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);
        function hideURLbar() {
            window.scrollTo(0, 1);
        }


    </script>
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
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="000100020012" username="${propertystaff.staffName}" />


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--服务信息添加开始--%>
            <div class="form-body">
                <h3 class="text-center">新增活动分享 </h3>
                <div class="form-body">
                    <form class="form-horizontal" id="frm" action="../activities/addActivities.html" method="post" enctype="MULTIPART/FORM-DATA">
                        <div class="form-group">
                            <label for="propertyProject" style="text-align: left;width:150px;margin-left:75px" class="col-sm-3 control-label"><spring:message code="propertyCompany.propertyProject"/></label>

                            <div class="col-sm-9" style="width: 230px;">
                                <select id="propertyProject" name="propertyProject"  class="form-control">
                                    <c:forEach items="${projects.propertyProjectMap}" var="item">
                                        <option value="${item.key }">${item.value }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="title" style="text-align: left;width:150px;margin-left:75px"><spring:message code="sharingActivities.title" /></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="title" name="title" value="${activitiesDTO.title}"style="width: 200px;">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="content" style="text-align: left;width:150px;margin-left:75px"><spring:message code="sharingActivities.content" /></label>
                            <div class="col-sm-8">
                                 <textarea class="form-control" id="content" placeholder=""  name="content" >${activitiesDTO.content}</textarea>
                            </div>
                        </div>
                        <div class="form-group" id="addImgUpload">
                            <div class="col-sm-9" style="text-align:left">
                            </div>
                        </div>
                        <div class="DisplayImg">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="imgUrl" style="text-align: left;width:150px;margin-left:75px"><spring:message code="sharingActivities.img" /></label>
                                <div class="col-sm-2">
                                    <input type="file" class="form-control" style="width:170px;" placeholder="" id="imgUrl" name="imgUrl" value="" onchange="check(this,0)">
                                </div>
                                <div class="col-sm-6" style="height: 50px;width:88px;height:50px;float: left;margin-left: 70px;margin-top:5px" id="demo_result0"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="text-center">
                        <button type="button" id="abc" class="btn btn-primary" onclick="validate()" ><spring:message code="propertyServices.servicePreservation"/></button>
                        <button type="button" id="addImgUploadBtn" class="btn btn-primary" onclick="addImgUpload()" >添加图片</button>
                        <input type="hidden" class="form-control" id="activitiesId" name="activitiesId" value="${activitiesDTO.activitiesId}">
                        <input type="hidden" class="form-control" id="projectId"  value="${activitiesDTO.propertyProject}">
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
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>
   var projectId=$('#projectId').val();
   $(document).ready(function(){
      if(''!=projectId){
          $('#propertyProject').val(projectId);
      }
   });
    var img=false;
    var imgList = [];
    var activitiesId=$('#activitiesId').val();
    console.info("activitiesId:"+activitiesId)
    if(activitiesId){
        //ajax请求后台有多少图片，以及每个图片的地址
        $.ajax({
            url: "/activities/getActivitiesImg?activitiesId="+activitiesId,
            type: "get",
            async: "false",
            dataType: "json",
            success: function (data) {
                imgList=data;
                for(var i=0;i<imgList.length;i++){
                    img=true;
                    console.info("imgList[i]"+imgList[i].imgUrl);
                    if(i==0){
                        document.getElementById("demo_result0").innerHTML+= '<img src="'+imgList[i].imgUrl+'" id="showImg"  style="width:70px;height:50px;" alt=""/>';
                        document.getElementById("demo_result0").innerHTML+='<input type="hidden" class="form-control" id="imgid" name="imageId" value="'+imgList[i].imageId+'">';
                    }else{
                        addImgUpload();
                        var imgDiv = document.getElementById("demo_result"+i);
                        imgDiv.innerHTML+= '<img src="'+imgList[i].imgUrl+'" style="width:70px;height:50px;" alt=""/>';
                        imgDiv.innerHTML+='<input type="hidden" class="form-control" id="imgid" name="imageId" value="'+imgList[i].imageId+'">';
                    }
                }
            }
        });
    }

    var imgtype=true;
    function check(that,divNum) {
        var filename = that.value;
        var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
        if (mime != ".jpg" && mime != ".png"&&mime != ".jpeg"&& mime != ".gif") {
            alert("上传图片类型错误");
            imgtype=false;
        }else{
            imgtype=true;
        }
        if(imgtype){
            for(var i=0;i<that.files.length;i++)
            {
                img=true;
                var file = that.files[0];
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function(e){
                    var imgDiv = document.getElementById("demo_result"+divNum);
                    $("#demo_result"+divNum).html("");
                    imgDiv.innerHTML+= '<img src="'+this.result+'" style="width:70px;height:50px;" alt=""/>';
                }
            }
        }
    }
    console.info("123-------------------------------------3");
    function hideTile(adModularId) {
        var adModularId = adModularId.value;

        console.info("adModularId:"+adModularId);
        if(adModularId==1){
            $("#title").css("display","none");
        }else{
            $("#title").css("display","inline");
        }
    }

    console.info("123-------------------------------------4");
    function addImgUpload(){
        if(addImgDivIndexCnt+1 != 10){
            addImgDivIndex++;
            addImgDivIndexCnt++;
            var strHtml = '<div class="addImg'+addImgDivIndex+'"><div class="form-group col-lg-3">'+
                    '<label class="col-sm-3 control-label" for="imgUrl" style="margin-left: 45px;width: 97px;"></label>'+
                    '<div class="col-sm-4">'+
                    '<input type="file" class="form-control" placeholder="" style="margin-left: 73px;width: 170px" id="imgUrl" name="imgUrl" value="" onchange="check(this,'+addImgDivIndex+')">'+
                    '</div>'+
                    '</div>'+
                    '<div class="form-group">'+
                    '<div class="col-sm-9" style="height: 50px;width:70px;height:50px;float: left;margin-left:210px;margin-top:5px" id="demo_result'+addImgDivIndex+'"></div>'+
                    '<div class="col-sm-9" style="height: 50px;width:10%;float: left;margin-top: 10px;margin-left:20px"><a href="javascript:rmDisplayImgDiv('+addImgDivIndex+')">删除</a></div></div></div>';
            var displayImgDiv = $(".DisplayImg");
            displayImgDiv.append(strHtml);
        }else{
            alert("最多只能添加10个上传工具！");
        }
    }

    function rmDisplayImgDiv(that){
        console.info(that);
        var imgDiv = $('.addImg'+that);
        console.info("删除imgDiv"+imgDiv);
        imgDiv.children().remove();
        addImgDivIndexCnt--;
    }



    function validate() {
        propertyProject=$("#propertyProject").val();
        if(propertyProject=='0'){
            alert("项目不能为空");
            return false;
        }
        title = $("#title").val();//活动标题
        if(!CheckNull($("#title").val(),"活动标题不能为空！")){
            return false;
        }
        if(title==""){
            alert("活动标题不能为空");
            return false;
        }
        if(title.length>15){
            alert("活动标题不能大于十五字");
            return false;
        }
        content=$("#content").val();//活动内容
        if(!CheckNull($("#content").val(),"活动内容不能为空！")){
            return false;
        }
        if(content==""){
            alert("活动内容不能为空");
            return false;
        }
        if(content.length>200){
            alert("活动内容不能大于二百个字");
            return false;
        }
        if(!img){
            alert("活动主页图不能为空");
            return false;
        }
        document.getElementById("frm").submit();
    }
</script>
</body>
</html>