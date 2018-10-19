<%--
  Created by IntelliJ IDEA.
  User: WeiYangDong
  Date: 2017/8/23
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>抽奖</title>
    <meta charset="utf-8">
    <style>
        * {
            padding: 0;
            margin: 0;
        }
        body {
            background: url('../static/img/luckDraw/bg.png') no-repeat;
            background-size: cover;
        }
        .headTitle {
            font-size: 0.75rem;
            color: #fff;
            margin: 0 auto;
            width: 8.5rem;
            text-align: center;
            margin-top: 1rem;
        }
        .luckDrawLe {
            font-size: 0.5rem;
            color: #fff;
            margin: 0 auto;
            width: 3.5rem;
            text-align: center;
            margin-top: .4rem;
        }
        .container {
            width: 7rem;
            position: fixed;
            top: 3.5rem;
            left: 6.2rem;
            text-align: center;
        }
        .result-box {
            background: url(../static/img/luckDraw/luckDrawEnd.png);
            text-align: center;
            height: 1.7rem;
            line-height: 1.7rem;
            width: 7rem;
            font-size: 0.6rem;
            color: #fff;
            background-size: contain;
        }
        .resultConduct {
            background: url(../static/img/luckDraw/luckDrawPro.png);
            text-align: center;
            height: 1.7rem;
            line-height: 1.7rem;
            width: 7rem;
            font-size: 0.6rem;
            color: #fff;
            background-size: contain;
        }
        button {
            width: 2.5rem;
            height: .8rem;
            line-height: .8rem;
            margin-top: 1.4rem;
            border: none;
            color: #fff;
            font-size: .3rem;
            border-radius: .1rem;
        }

        button:focus {
            outline: none;
        }
        .start {
            background-color: #f3f12f;
            color: #000;
            font-weight: 600;
        }
        .end {
            background-color: #a09c9c;
            color: #fff;
            font-weight: 600;
        }
        .stepEnd {
            background: url(../static/img/luckDraw/LuckContinue.png) center no-repeat;
            background-size: contain;
            height: .8rem;
        }
        .allEnd {
            background-color: #a09c9c;
            color: #fff;
        }
    </style>
</head>
<body>
<div class='headTitle'>金茂北京业主抽奖活动</div>
<div class='luckDrawLe'>三等奖</div>
<div class="container">
    <div class="result-box">
        *********** ***
    </div>
    <button class="start" onClick="start()">开始抽奖</button>
</div>
<script src="../static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
    ! function(desW) {
        var winW = document.documentElement.clientWidth;
        document.documentElement.style.fontSize = winW / desW * 100 + "px";
    }(1920);
    var data = ${activitySignList};
    var p1Num = ${offlineActivity.onePrizeNumber}; //一等奖数量
    var p2Num = ${offlineActivity.twoPrizeNumber}; //二等奖数量
    var p3Num = ${offlineActivity.threePrizeNumber}; //三等奖数量
    var p1Winner = new Array(); //一等奖中奖者
    var p2Winner = new Array(); //二等奖中奖者
    var p3Winner = new Array(); //三等奖中奖者
    var btn = true; //按钮状态未开始还是结束
    var key = 0; //中奖下标
    var time = 0; //定时器
    var startClick = 3; //从几等奖开始
    function runTime() {
        clearInterval(time);
        $(".result-box").addClass('resultConduct');
        time = setInterval('trunNum()', 10);
    }
    var allZJ = p3Num + p2Num + p1Num; //所有中奖者数量
    //点击按钮
    function start() {
        if (allZJ > data.length) {
            alert('签到人数不足，无法开启抽奖！')
        } else {
            if (btn) {
                if ((p1Winner.length >= p1Num) && (p2Winner.length >= p2Num) && (p3Winner.length >= p3Num)) {
                    $("button").hide();
                } else if (startClick == 3 && p3Winner.length >= p3Num) {
                    startClick = 2
                    $("button").removeClass('stepEnd').removeClass("end").addClass("start").text("开始抽奖")
                    awardLevel('二等奖');
                    $(".result-box").text('*********** ***');

                } else if (startClick == 2 && p2Winner.length >= p2Num) {
                    startClick = 1
                    awardLevel('一等奖');
                    $("button").removeClass('stepEnd').removeClass("end").addClass("start").text("开始抽奖")
                    $(".result-box").text('*********** ***');
                } else if (startClick == 1 && p1Winner.length >= p1Num) {
                } else {
                    btn = false;
                    $("button").removeClass("start").addClass("end").text("结束抽奖");
                    startTrun();
                }
            } else {
                btn = true;
                if (p3Winner.length == p3Num - 1) {
                    $("button").text("").addClass('stepEnd')
                } else if (p3Winner.length == p3Num && p2Winner.length == p2Num - 1) {
                    $("button").text("").addClass('stepEnd')
                } else if (p3Winner.length == p3Num && p2Winner.length == p2Num && p1Winner.length == p1Num - 1) {
                    $("button").removeClass("start").addClass("end").text("所有抽奖已结束！");
                } else {
                    $("button").removeClass("end").addClass("start").text("开始抽奖");
                }
                endTrun();
            }
        }
    }

    function trunNum() {
        key = Math.floor(Math.random() * (data.length - 1));
        var tel = data[key].toString().substr(0, 3) + '****' + data[key].toString().substr(7);
        $(".result-box").text(tel);
    }

    //开始转动数字
    function startTrun() {
        runTime();
    }

    //停止转动数字
    function endTrun() {
        $(".result-box").removeClass('resultConduct');
        var prizeInfo = "";
        if (startClick == 3) {
            prizeInfo = "三等奖";
            p3Winner.push(data[key]);
            allZJ --;
        } else if (startClick == 2) {
            prizeInfo = "二等奖";
            p2Winner.push(data[key]);
            allZJ --;
        } else {
            prizeInfo = "一等奖";
            p1Winner.push(data[key]);
            allZJ --;
        }
        $.ajax({
            type: "GET",
            url: "../offlineActivity/activityLuckDraw",
            cache: false,
            async:false,
            data:{
                "ownerName":data[key],
                "prizeInfo":prizeInfo,
                "activityId":getUrlParam('activityId')
            },
            dataType:"json",
            success: function (data) {
//                if (data.error == 0) {
//                }else{
//                    alert("对不起，操作失败！");
//                }
            }
        });
        data.splice(key, 1);
        clearInterval(time);
    }
    //控制上部几等奖的显示
    function awardLevel(level) {
        $('.luckDrawLe').text(level)
    }
//    (function ($) {
//        $.getUrlParam = function (name) {
//            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
//            var r = window.location.search.substr(1).match(reg);
//            if (r != null) return unescape(r[2]); return null;
//        }
//    })(jQuery);
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
</script>
</body>
</html>
