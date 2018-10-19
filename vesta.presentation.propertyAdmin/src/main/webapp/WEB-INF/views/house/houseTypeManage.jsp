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
    <style>
        * {
            margin: 0;
            padding: 0;
            list-style: none;
            -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
            -webkit-appearance: none;
            font-family: "Helvetica Neue", Helvetica, "Hiragino Sans GB", "Microsoft YaHei", Arial, sans-serif;
            font-style: normal;
            outline: none;
        }

        input, a {
            appearance: none;
            tap-highlight-color: rgba(255, 255, 255, 0);
            -webkit-appearance: none;
            -webkit-tap-highlight-color: rgba(255, 255, 255, 0);

        }

        .bar.bar-positive {
            background: #fff !important;
        }

        a {
            text-decoration: none;
        }

        a:hover, a:focus {
            text-decoration: none;
        }

        h1,
        h2,
        h3,
        h4,
        h5,
        h6 {
            font-size: 0.24rem;
            font-weight: normal;
            font-family: "Helvetica Neue", Helvetica, "Hiragino Sans GB", "Microsoft YaHei", Arial, sans-serif;

        }

        input[type="text"],
        input[type="checkBox"],
        input[type="password"],
        button {
            -webkit-appearance: none;
            appearance: none;
            outline: none;
            border-radius: 0;
        }

        .head {
            width: 559px;
            height: 40px;
            background-color: #f6f6f6;
            line-height: 40px;
            text-align: center;
            margin: 0 auto;
        }

        #picbox {
            position: relative;
            position: fixed;
            top: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            padding-bottom: 40px;
            z-index: 9999;

        }

        .relative {
            position: relative;

        }

        .content {
            background: #fff;
            left: 0;
            right: 0;
            position: fixed;
            width: 560px;
            height: 570px;
            border: 1px solid #eee;
            margin: 0 auto;

        }

        #imgbox {
            top: 40px !important;
            background: rgba(255, 255, 255, 0) !important;
        }

        #picbox img {
            max-width: 100%;
            max-height: 100%;
            display: block;
            width: 480px;
            height: 400px;
            margin: 0 auto;
        }

        #btnlist {
            text-align: center;
            margin-top: 10px;
        }

        #baocun {
            display: inline-block;
            color: #fff;
            width: 120px;
            height: 40px;
            border: none;
            border-radius: 6px;
            background-color: #06315d;
            margin-right: 10px;
        }

        #close {
            display: inline-block;
            color: #fff;
            width: 120px;
            height: 40px;
            border: none;
            border-radius: 6px;
            background-color: #d7d7d7;
            margin-left: 10px;
        }

        .hidden {
            display: none;
        }

        .del {
            position: absolute;
            background: red;
            right: 0;
            bottom: 0;
            font-size: 12px;
        }
    </style>

</head>

<body class="cbp-spmenu-push">
<script>
    var options = '';


    var mapInit = function () {
        $('#picbox').removeClass('hidden');
        var picleft = document.getElementById('pic').offsetLeft;
        var mmm = $('img')[0];
        var picWidth = mmm.clientWidth;
        var picHeight = mmm.clientHeight;
        var mmm = document.createElement('div');
        mmm.id = 'imgbox';
        mmm.style.cssText = "position:absolute;width:" + picWidth + "px;height:" + picHeight + "px;left:50%;top:0;background:rgba(255,0,0,0.3);margin-left:-" + picWidth / 2 + "px;";
        $('#picbox').append(mmm);
        for (var i = maparea.length - 1; i >= 0; i--) {
            var selectArea = document.createElement('div');
            selectArea.id = maparea[i].id;
            selectArea.innerHTML = "<select value='" + maparea[i].name + "' style='width:90%;'>" + options + "</select><button class='del'>删除</button>";
            for (var j = selectArea.getElementsByTagName("option").length - 1; j >= 0; j--) {
                if (selectArea.getElementsByTagName("option")[j].value == maparea[i].name) {
                    selectArea.getElementsByTagName("option")[j].selected = "selected"
                }
            }
            selectArea.style.cssText = "position:absolute;bottom:" + (100 - maparea[i].Y2) + "%;right:" + (100 - maparea[i].X2) + "%;top:" + maparea[i].Y1 + "%;left:" + maparea[i].X1 + "%;background:rgba(0,0,0,0.5);border:1px solid red";
            $('#imgbox').append(selectArea);
        }
        ;
        functionone();
    };


    var maparea = [];
    var mapImg = "";
    var mapID = "";

    var saveMapDate = function () {
        // wyd
        var objs = $('#imgbox').find('div');
        var sendDate = [];
        var maparea = [];
        var mapImg = "";
//    var mapID="";
        for (var i = objs.length - 1; i >= 0; i--) {
            var obj = {};
            obj.name = objs[i].childNodes[0].value;
            obj.id = objs[i].id;
            obj.x1 = parseFloat(objs[i].style.left);
            obj.x2 = 100 - parseFloat(objs[i].style.right);
            obj.y1 = parseFloat(objs[i].style.top);
            obj.y2 = 100 - parseFloat(objs[i].style.bottom);
            sendDate.push(obj);
        }
        ;
        console.log(sendDate);
        //Ajax请求保存户型批注列表数据
        $.ajax({
            type: "POST",
            url: "../housetype/saveHouseTypeLabels",
            async: false,
            cache: false,
            data: {
                id: mapID,
                objectList: sendDate
            },
            success: function (data) {
                if (data.code == '0') {
                    alert("保存成功");
                } else {
                    alert("对不起，操作失败");
                }
            }
        });

        //mapID 户型图id
        //需要对后台更新户型原味标注数据
        $('#imgbox').remove();
        $('#picbox').addClass('hidden');
        maparea = [];
    }
    var closeMapDate = function () {
        $("#imgbox div").remove();
        $('#imgbox').remove();
        maparea = [];
        $('#picbox').addClass('hidden');
    }


    var showMap = function (ids, imgUrl, type) {
        console.log(ids)
        mapID = ids;
        //执行ajax请求 得到如下数据  然后回调绘图init方法   wyd

        $.ajax({
            type: "GET",
            url: "../housetype/getHouseTypeLabels",
            cache: false,
            async: false,
            data: "id=" + ids,
            success: function (data) {
                if (data.code == '0') {
//          alert(data.data.length);
                    var objList = data.data;
                    if (objList != null && objList.length > 0) {
                        for (var i = 0; i < objList.length; i++) {
                            var obj = {};
                            obj.X1 = objList[i].xNum1;
                            obj.Y1 = objList[i].yNum1;
                            obj.X2 = objList[i].xNum2;
                            obj.Y2 = objList[i].yNum2;
                            obj.name = objList[i].name;
                            obj.id = objList[i].id;
                            maparea.push(obj);
                        }
                        console.log(maparea);
                    }
                } else {
                    alert("对不起，操作失败");
                }
            }
        });

        mapImg = imgUrl;
        //mapImg = "http://123.57.160.155:8081/images/loupan1.png";
        $("#pic")[0].src = mapImg;
//    maparea=[{X1:10,Y1:10,X2:30,Y2:30,name:"123",id:222},{X1:12,Y1:42,X2:40,Y2:88,name:"1234",id:333}];

        if (type != null && type != "") {
            typeUp(type);
        } else {
            options = '';//清空历史数据
            <c:forEach items="${roomLocations}" var="roomLocation">
            options = options + '<option value="${roomLocation.id}">' + '${roomLocation.name}' + '</option>';
            </c:forEach>
        }
        mapInit();
    }


    var typeUp = function (type) {
        //该接口为按户型类型区分location列表显示
        options = '';//清空历史数据
        $.ajax({
            type: "GET",
            url: "../housetype/getHouseLocationList",
            cache: false,
            async: false,
            data: "type=" + type,
            success: function (data) {
                if (data.code == '0') {
                    var objList = data.data;
                    if (objList != null && objList.length > 0) {
                        for (var i = 0; i < objList.length; i++) {
                            options = options + '<option value="' + objList[i].id + '">' + objList[i].name + '</option>';
                        }
                    }
                } else {
                    alert("对不起，操作失败");
                }
            }
        });
    }

</script>
<%--!!--%>

<div id="picbox" class='hidden'>
    <div class="relative">
        <div class="content">
            <div class="head">绘制部位</div>
            <img id="pic" src="" alt=""></img>
            <div id="btnlist">
                <c:if test="${function.qch40010060 eq 'Y'}">
                    <button id="baocun" onClick="saveMapDate();">保存</button>
                </c:if>
                <button id="baocun" onClick="closeMapDate();">关闭</button>
            </div>
        </div>
    </div>
</div>
<%--@@--%>


<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>"
                 crunMenu="001500010000" username="${authPropertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <form class="form-horizontal" action="../housetype/houseTypeManage.html" method="post">
                    <%--户型名称--%>
                    <div class="form-group  col-lg-4">
                        <label for="name" class="col-sm-4 control-label"><spring:message
                                code="rental.houseType"/>：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" placeholder="" id="name"
                                   name="name" value="${houseTypeDTO.name}">
                        </div>
                    </div>

                    <div class="form-group  col-lg-4">
                        <label for="projectNum" class="col-sm-4 control-label"><spring:message
                                code="activityManage.activityProjectName"/>：</label>
                        <div class="col-sm-8">
                            <select class="form-control" placeholder="" id="projectNum" name="projectNum">
                                <c:forEach items="${projects}" var="project">
                                    <option value="${project.key}"
                                            <c:if test="${project.key eq houseTypeDTO.projectNum}">selected</c:if>>${project.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <c:if test="${function.qch40010056 eq 'Y'}">
                        <button type="submit" class="btn btn-primary" for="propertySearch"><spring:message
                                code="propertyCompany.propertySearch"/></button>
                    </c:if>
                    <c:if test="${function.qch40010057 eq 'Y'}">
                        <a href="../housetype/houseTypeAdd" class="btn btn-primary" for="houseTypeAdd"><spring:message
                                code="common_add"/></a>
                    </c:if>

                </form>
            </div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <form action="../housetype/houseTypeManage.html" method="post" id="search_form">

            <input id="id" type="hidden" name="id" value="">
        </form>
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <td style="width:10%;"><spring:message code="activityManage.activitySort"/></td>
                <td style="width:30%;"><spring:message code="rental.houseType"/></td>
                <th style="width:30%;"><spring:message code="rental.desciption"/></th>
                <th style="width:15%;"><spring:message code="rental.modifyDate"/></th>
                <th style="width:15%;"><spring:message code="common_operate"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${houseTypeList}" var="houseType" varStatus="vs">
                <tr>
                    <td><b>${(webPage.pageIndex-1)*20+vs.index+1}</b></td>
                    <td><a href="../housetype/houseTypeDetail?id=${houseType.id}" class="a2">${houseType.name}</a></td>
                    <td>${houseType.comments}</td>
                    <td>${houseType.operateDate}</td>
                    <td class="last">
                            <%--<a href="../housetype/updateHouseType?id=${houseType.id}" class="a2"><span class="span1"><spring:message code="house.label" /></span></a>--%>
                        <c:if test="${function.qch40010059 eq 'Y'}">
                            <a style="cursor: pointer"
                               href="javaScript:showMap('${houseType.id}','${houseType.imgUrl}','${houseType.type}')"
                               class="a2"><span class="span1"><spring:message code="house.label"/></span></a>
                        </c:if>
                        <c:if test="${function.qch40010061 eq 'Y'}">
                            <a href="../housetype/houseTypeUpdate?id=${houseType.id}" class="a2"><span
                                    class="span1"><spring:message code="common_update"/></span></a>
                        </c:if>
                        <c:if test="${function.qch40010063 eq 'Y'}">
                            <a href="javaScript:deleteType('${houseType.id}')" class="a3"><span
                                    class="span1"><spring:message code="common_delete"/></span></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/housetype/houseTypeManage.html?projectNum=${houseTypeDTO.projectNum}&pageIndex={0}&name=${houseTypeDTO.name}"/>
    </div>


</div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>
<script>

    function deleteType(id) {
        if (window.confirm("确认删除吗？")) {
            window.location.href = '../housetype/houseTypeDelete?id=' + id;
        }
    }


    function getMousePos(event) {
        var picleft = document.getElementById('imgbox').offsetLeft;
        var pictop = document.getElementById('picbox').offsetTop;
        var mmm = $('img')[0];
        var picWidth = mmm.clientWidth;
        var picHeight = mmm.clientHeight;
        var e = event || window.event;
        var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
        var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
        var x = e.pageX || e.clientX + scrollX;
        var y = e.pageY || e.clientY + scrollY;
        return {
            'x': (x - picleft) * 100 / picWidth,
            'y': (y - pictop - 40) * 100 / picHeight
        };
    }

    for (i in document.images) document.images[i].ondragstart = imgdragstart;


    function isRepeat(obj, arr) {
        var j = arr.length;
        while (j--) {
            if (!(obj.X1 > arr[j].X2 || obj.X2 < arr[j].X1 || obj.Y1 > arr[j].Y2 || obj.Y2 < arr[j].Y1)) {
                return false;
            }
        }
        return true;
    }

    function functionone() {

        var leftTop, rightBottom, selectArea;
        var id = 1;
        $('#imgbox').mousedown(function (event) {
            event.stopPropagation();
            leftTop = getMousePos(event);
            var picleft = document.getElementById('pic').offsetLeft;
            selectArea = document.createElement('div');
            selectArea.id = "area" + id;
            selectArea.innerHTML = '<select value="请选择" style="width:90%;">' + options + '</select><button class="del">删除</button>';

            $('#imgbox').append(selectArea);
            selectArea.style.cssText = "position:absolute;top:" + leftTop.y + "%;left:" + leftTop.x + "%;background:red;";
            $('#imgbox').on("mousemove", function (event) {
                rightBottom = getMousePos(event);
                selectArea.style.cssText = "position:absolute;bottom:" + (100 - rightBottom.y) + "%;right:" + (100 - rightBottom.x) + "%;top:" + leftTop.y + "%;left:" + leftTop.x + "%;background:rgba(0,0,0,0.5);border:1px solid red";
            })

        });
        $('#imgbox').mouseup(function (event) {

            var rightBottom = getMousePos(event);
            $('#imgbox').off("mousemove");
            if (!leftTop) {
                return false;
            }
            ;
            if (leftTop.x < rightBottom.x && leftTop.y < rightBottom.y && (isRepeat({
                    X1: leftTop.x,
                    Y1: leftTop.y,
                    X2: rightBottom.x,
                    Y2: rightBottom.y
                }, maparea) || maparea.length == 0)) {
                maparea.push({
                    X1: leftTop.x,
                    Y1: leftTop.y,
                    X2: rightBottom.x,
                    Y2: rightBottom.y,
                    name: "",
                    id: "area" + id
                });
                id++;
                leftTop = null;
                rightBottom = null;
                console.log(maparea);
            } else {
                // selectArea.remove();
                $('#' + selectArea.id).remove();
            }
            $('#imgbox .del').off('mouseup');
            $('#imgbox .del').mouseup(function (event) {
                var j;
                for (var i = maparea.length - 1; i >= 0; i--) {
                    if (maparea[i].id == this.parentElement.id) {
                        j = i;
                        break;
                    }
                    ;
                }
                ;
                maparea.splice(j, 1);
                console.log(maparea);
                this.parentElement.remove()
                event.stopPropagation();
            })
            $('#imgbox div').mousedown(function (event) {
                event.stopPropagation();
            })

        });


        $('#imgbox .del').mouseup(function (event) {
            var j;
            for (var i = maparea.length - 1; i >= 0; i--) {
                if (maparea[i].id == this.parentElement.id) {
                    j = i;
                }
                ;
            }
            ;
            maparea.splice(j, 1);
            console.log(maparea);
            this.parentElement.remove()
            event.stopPropagation();
        })
        $('#imgbox div').mousedown(function (event) {
            event.stopPropagation();
        })
    }


</script>
</body>
</html>