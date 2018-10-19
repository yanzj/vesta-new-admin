/**
 * Created by liudongxin on 2016/5/19.
 */
<!-- 便民信息删除 -->
function deleteLine(id){
    var flag = 0;   //操作级别标示
    $.ajax({
        type: "GET",
        url: "../memberAuthority/checkStaffMenuOperationLevel/"+$("#menuId").val(),
        cache: false,
        async:false,
        dataType:"json",
        success: function (data) {
            if (data.error == 1) {
                flag = 1;
            }else if(data.error == 0) {
                alert("对不起，您无权限执行此操作！");
                return ;
            }else{
                alert("对不起，操作失败！");
                return ;
            }
        }
    });
    var flag2 = 0;  //操作范围标示
    if(flag != 0){
        $.ajax({
            type: "GET",
            url: "../property/checkEdit/"+id+"/"+$("#menuId").val(),
            cache: false,
            async:false,
            dataType:"json",
            success: function (data) {
                if (data.error == 1) {
                    flag2 = 1;
                }else if(data.error == 0) {
                    alert("对不起，您的权限范围无法执行此操作！");
                    return ;
                }else{
                    alert("对不起，操作失败！");
                    return ;
                }
            }
        });
    }
    if(flag != 0 && flag2 != 0){
        myvalue = confirm("确定要删除吗?");
        if (myvalue == true) {
            $.ajax({
                url: "/property/removeLine",
                type: "post",
                async: "false",
                dataType: "json",
                data: {
                    "announcementId": id,
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
                        window.location.href = "../property/hotLineManagement.html?menuId=005500010000&pageIndex="+pages+"&queryScope="+queryScope+
                            "&project="+project+"&title="+title+"&memo="+memo+"&announcementContent="+announcementContent+
                            "&startTime="+startTime+"&endTime="+endTime;
                    }
                },
            });
        }
    }
}
