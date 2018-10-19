/**
 * Created by ZhangBailiang on 2016/1/20.
 * 物业服务信息
 */
<!-- 服务信息删除 -->
function js_delMessage(id){
  myvalue = confirm("确定要删除吗?");
  if (myvalue == true) {
      $.ajax({
          url: "/property/removePropertyServicesById",
          type: "post",
          async: "false",
          dataType: "json",
          data: {
              "servicesId": id,
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
                  window.location.href = "../property/PropertyServices.html?pageIndex="+pages+"&serviceType="+serviceType+"&serviceName="+serviceName+"&servicesPhone="+servicesPhone;
              }
          },
      });
  }
}

