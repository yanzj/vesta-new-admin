$(document).ready(function(){
    if("" != type){
        $("#type").val(type);
    }
    if("" != state){
        $("#state").val(state);
    }
    if("" != firstType){
        $("#firstType").val(firstType);
    }
    if("" != secondType){
        $("#secondType").val(secondType);
    }
    if("" != buildingNum){
        $("#buildingNum").val(buildingNum);
    }
    if("" != buildingFloor){
        $("#buildingFloor").val(buildingFloor);
    }

    $("#buildingNum").change(function(){
        var buildingNum = $("#buildingNum").val();
        $.ajax({
            url:"../property/getBuildingFloor",
            type:"get",
            async:"false",
            data:{ "content":buildingNum,},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    <!-- 重新给楼层赋值 -->
                    if(data != null){
                        <!-- 清空楼层下拉框值 -->
                        document.getElementById("buildingFloor").innerHTML = "";
                        option = option + "<option value='0000'>--请选择楼层--</option>";
                        <!-- 循环添加楼层值 -->
                        for(var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }
                        }
                        // 给楼层赋值*/
                        $("#buildingFloor").append(option);
                    }
                }
            }
        });
    });


    $("#firstType").change(function(){
        var firstType = $("#firstType").val();
        $.ajax({
            url:"../property/getSecondType",
            type:"get",
            async:"false",
            data:{ "content":firstType,},
            dataType:"json",
            error:function(){
                alert("网络异常，可能服务器忙，请刷新重试");
            },
            success:function(json){
                <!-- 获取返回代码 -->
                var code = json.code;
                if(code != 0){
                    var errorMessage = json.msg;
                    alert(errorMessage);
                }else{
                    <!-- 获取返回数据 -->
                    var data = json.data;
                    var option ="";
                    <!-- 重新赋值 -->
                    if(data != null){
                        <!-- 清空下拉框值 -->
                        document.getElementById("secondType").innerHTML = "";
                        option = option + "<option value='0000'>--请选择二级分类--</option>";
                        <!-- 循环添加值 -->
                        for(var prop in data) {
                            if (data.hasOwnProperty(prop)) {
                                option = option + "<option value='"+ prop+"'>" +  data[prop] + "</option>";
                            }
                        }
                        // 给楼层赋值*/
                        $("#secondType").append(option);
                    }
                }
            }
        });
    });
});

function deleteRepair(id){
    var myvalue = confirm("确定要删除吗?");
    if (myvalue == true) {
        $.ajax({
            url: "/property/removeRepair",
            type: "post",
            async: "false",
            dataType: "json",
            data: {
                "repairId": id,
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
                    window.location.href="../property/repairManage.html?pageIndex="+pages+"&project="+project+"&buildingNum="+buildingNum+"&buildingFloor="+buildingFloor+"&firstType="+firstType+"&secondType="+secondType+"&type="+type+"&state="+state;
                }
            },
        });
    }
}