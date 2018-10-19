/**
 * Created by ZhangBailiang on 2016/2/1.
 * 咨询管理
 */
$(document).ready(function() {
    <!-- 答复状态 -->
    if("" != replyStatus){
        document.getElementById("reply0").selected=true;
    }
    if("1" == replyStatus){
        document.getElementById("reply1").selected=true;
    }
    if("2" == replyStatus){
        document.getElementById("reply2").selected=true;
    }
});
<!-- 删除咨询管理信息 -->
function js_delMessage(id){
    myvalue = confirm("确定要删除吗?");
    if (myvalue == true) {
        $.ajax({
            url: "/property/removePropertyConsultById",
            type: "post",
            async: "false",
            dataType: "json",
            data: {
                "id": id,
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
                    window.location.href = "../property/PropertyConsult.html?pageIndex="+pages+"&projectId="+projectSel+"&replyStatus="+replyStatus+"&crtTimeStart="+crtTimeStart+"&crtTimeEnd="+crtTimeEnd+"&content="+content;
                }
            },
        });
    }
}
