/**
 * Created by ZhangBailiang on 2016/1/21.
 * 区域 项目 公司 下来框选中
 */
$(document).ready(function() {
    if("" != companyNameSel){
        $("#companyName").val(companyNameSel);
    }
    if("" != propertyProjectSel){
        $("#propertyProject").val(propertyProjectSel);
    }
    if("" != propertyAreaSel){
        $("#propertyArea").val(propertyAreaSel);
    }
});
/**
 * 物业公司 删除 更新状态
 * 启用(type 0)
 * 禁用(type 1)
 * 删除(type 2)
 * @param id
 * @param type
 */
function js_delORupdateMessage(id,type){
    if(type == "0"){  myvalue = confirm("确定要禁用吗?"); }
    if(type == "1"){  myvalue = confirm("确定要启用吗?");  }
    if(type == "2"){  myvalue = confirm("确定要删除吗?");  }

    if (myvalue == true) {
        $.ajax({
            url: "/property/removeORupdatePropertyCompany",
            type: "post",
            async: "false",
            dataType: "json",
            data: {
                "companyId": id,
                "propertyType":type,
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
                    if (data == "0") {
                        // 此信息不存在!
                        alert("信息已被删除,请刷新...");
                    }
               window.location.href = "../property/PropertyCompany.html?pageIndex="+pages+"&propertyArea="+projectAdminSel+"&companyName="+companyNameSel+"&propertyProject="+propertyProjectSel+"&projectAdmin="+projectAdminSel;
                }
            },
        });
    }
}
/**
 * 根据管理员 查询对应姓名及联系方式
 */
function js_admin(){
    var userName = $("#projectAdmin").val();
    $("#userNameMsg").val("");
    $("#adminMsg").html("");
    $.ajax({
        url: "../user/getWandaStaff",
        type: "post",
        async: "false",
        dataType: "json",
        data: {
            "userNameDto": userName,
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
                if("NoMatch" != data.staffName){
                    $("#name").val(data.staffName);
                    $("#phnoe").val(data.mobile);
                    $("#userNameMsg").val(userName);
                }else{
                    $("#adminMsg").html("没有匹配信息!");
                }
            }
        },
    });
}

function js_openProjectCompany(){
    if("0" == $("#propertyArea").val()){
        $("#propertyAreaMsg").html("请选择区域!");
        return
    }else{
        $("#propertyAreaMsg").html("");
    }
    if("0" == $("#companyName").val()){
        $("#companyNameMsg").html("请选择公司!");
        return
    }else{
        $("#companyNameMsg").html("");
    }
    if("0" == $("#propertyProject").val()){
        $("#propertyProjectMsg").html("请选择项目!");
        return
    }else{
        $("#propertyProjectMsg").html("");
    }
    if("0" == $("#projectAdmin").val()){
        $("#adminMsg").html("请输入管理员!");
        return;
    }else{
        $("#adminMsg").html("");
    }
    if("" == $("#name").val()){
        $("#nameMsg").html("姓名不可为空!");
        return;
    }else{
        $("#nameMsg").html("");
    }
    if(""== $("#phnoe").val()){
        $("#nameMsg").html("联系方式不可为空!");
        return;
    }else{
        $("#nameMsg").html("");
    }
    if($("#userNameMsg").val() != $("#projectAdmin").val()){
        $("#adminMsg").html("没有匹配信息!");
        return;
    }else{
        $("#projectAdmin").val();
    }

    $.ajax({
        url: "../property/whetherAreAdmin",
        type: "post",
        async: "false",
        dataType: "json",
        data: {
            "projectAdmin": $("#projectAdmin").val(),
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
                if(data == "1"){
                    $("#adminMsg").html("此管理员已存在!");
                    return;
                }else{
                    $("#propertyCompany").submit();
                }
            }
        },
    });
}


$(document).ready(function(){
    $("#propertyArea").change(function(){
        var areaSel = $("#propertyArea").val();

        $.ajax({
            url:"../property/propertyDropBox",
            type:"get",
            async:"false",
            data:{ "companyName":areaSel,},
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
                    var option2 ="";
                    <!-- 重新给公司赋值 -->
                    if(data != null){
                        <!-- 清空公司下拉框值 -->
                        document.getElementById("companyName").innerHTML = "";
                        <!-- 清空项目下拉框值 -->
                        document.getElementById("propertyProject").innerHTML = "";
                        <!-- 重新给公司赋默认请选择 -->
                        option = option + "<option value='0' name='companyName' >--请选择公司--</option>";
                        <!-- 重新给项目赋默认请选择 -->
                        option2 = option2 + "<option value='0' name='propertyProject' >--请选择项目--</option>";
                        <!-- 循环添加公司值 -->
                        for(var i = 0; i <= data.length-1; i++) {
                            option = option + "<option value='"+ data[i].id+"' name='companyName' >" +  data[i].name + "</option>";
                        }
                        // 给公司赋值*/
                        $("#companyName").append(option);
                        // 给项目赋值
                        $("#propertyProject").append(option2);
                    }
                }
            }
        });
    });
});

function dropBox(){
    var propertySel = $("#companyName").val();

    $.ajax({
        url:"../property/propertyDropBox",
        type:"get",
        async:"false",
        data:{ "pojectName":propertySel,},
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
                var option2 ="";
                <!-- 重新给项目赋值 -->
                if(data != null){
                    <!-- 清空项目下拉框值 -->
                    document.getElementById("propertyProject").innerHTML = "";
                    <!-- 重新给项目赋默认请选择 -->
                    option2 = option2 + "<option value='0' name='propertyProject' >--请选择项目--</option>";
                    <!-- 循环添加公司值 -->
                    for(var i = 0; i <= data.length-1; i++) {
                        option2 = option2 + "<option value='"+ data[i].id+"' name='propertyProject' >" +  data[i].name + "</option>";
                    }
                    // 给项目赋值
                    $("#propertyProject").append(option2);
                                    }
            }
        }
    });
}