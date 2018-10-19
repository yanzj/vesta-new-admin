function js_delMessage(id){
    myvalue = confirm("确定要删除吗?");
    if (myvalue == true) {
        $.ajax({
            url: "/communityArea/deleteHousePay.html",
            type: "post",
            async: "false",
            dataType: "json",
            data: {
                "housepayID": id,
            },
            success: function (json) {
                <!-- 获取返回代码 -->
                var code = json.code;
                if (code != 0) {
                    var errorMessage = json.msg;
                    alert(errorMessage);
                } else {
                    <!-- 获取返回数据 -->
                        alert("信息已被删除,请刷新...");
                    //window.location.href = "../property/PropertyAnnouncement.html?pageIndex="+pages+"&title="+title+"&project="+project+"&startTime="+startTime+"&endTime="+endTime;
                    window.location.href = "../communityArea/HousePayBatch.html";
                }
            },
        });
    }
}