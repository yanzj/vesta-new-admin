/**
 * Created by ZhangBailiang on 2016/2/4.
 * 随手报 随手拍 删除
 */

<!-- 随手拍 -->
function js_delMessage(id){
    myvalue = confirm("确定要删除吗?");
    if (myvalue == true) {
        $.ajax({
            url: "../property/removePropertyAnywhereById",
            type: "post",
            async: "false",
            dataType: "json",
            data: {
                "anywherePicId": id,
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    if (data == "1") {
                        // 此信息删除失败!
                        alert("信息删除失败,请刷新重试...");
                    }
                    if (data == "2") {
                        // 此信息不存在!
                        alert("信息已被删除,请刷新...");
                    }
                  window.location.href = "../property/PropertyAnywherePic.html?pageIndex="+pages+"&houseId="+houseId+"&title="+title+"&makedateStart="+makedateStart+"&makedateEnd="+makedateEnd+"&userName="+userName;
                }
            },
        });
    }
}
function js_workMessage(id,state){
    if(state == 2){
        alert("此信息已经转为工单!");
        return;
    }
    myvalue = confirm("确定要转为工单吗?");
    if (myvalue == true) {
        $.ajax({
            url: "../property/turnWorkOrder",
            type: "post",
            async: "false",
            dataType: "json",
            data: {
                "anywherePicId": id,
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    if(data == "0" || data == "2" ){
                        alert("信息不存在,请刷新重试!");
                    }
                    if(data == 3){
                        alert("此信息已经转为工单,请刷新!");
                    }
                   window.location.href = "../property/PropertyAnywherePic.html?pageIndex="+pages+"&title="+title+"&houseId="+houseId+"&makedateStart="+makedateStart+"&makedateEnd="+makedateEnd+"&userName="+userName;
                }
            },
        });
    }
}
