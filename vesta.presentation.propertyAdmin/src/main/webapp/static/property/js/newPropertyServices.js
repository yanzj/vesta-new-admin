/**
 * Created by ZhangBailiang on 2016/1/20.
 */

<!-- 服务信息修改 -->
$(document).ready(function() {
    var url=window.location.search;
    if(url.indexOf("?")!=-1) {
        var str = url.substr(4);
        $.ajax({
            url: "/property/queryPropertyServicesById",
            type: "post",
            async: "false",
            dataType: "json",
            data: {
                "servicesId": str,
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
                    if(data.servicesId == "无"){
                        alert("此信息不存在,请刷新重试...");
                        return;
                    }else{
                        $("#servicesId").val(data.servicesId);
                        $("#serviceName").val(data.serviceName);
                        $("#servicesPhone").val(data.servicesPhone);
                        $("#serviceType").val(data.serviceType);
                    }
                }
            },
        });
    }

  });
