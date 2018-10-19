$(function(){
    var checkBox = {
        checkAll : function(that,type){
            that.toggleClass('on_check').siblings().removeClass('on_check');
            if(that.hasClass('on_check')){
                if(type == 0){
                    $('.checkAll1,.checkAll2,.z_check1,.z_check2').removeClass('on_check');
                }else{
                    $('.z_check'+(that.index()+1)).addClass('on_check').siblings().removeClass('on_check');
                }
            }else{
                $('.z_check'+(that.index()+1)).removeClass('on_check');
            }
        },
        checkGroup:function(that){
            var index = that.index()+1;
            if(that.hasClass('on_check')){
                that.siblings().removeClass('on_check');
                that.parents('.z_title').siblings('.z_cont').find('.z_check'+index).addClass('on_check').siblings().removeClass('on_check');

                checkBox.isCheckAll(that,index);
            }else{
                that.parents('.z_title').siblings('.z_cont').find('.z_check'+index).removeClass('on_check');
            }
        },
        checkOne : function(that){
            var index = that.index()+1;
            that.toggleClass('on_check');
            if(that.hasClass('on_check')){
                that.addClass('on_check').siblings().removeClass('on_check');
                checkBox.isCheckGroup(that,index);
                checkBox.isCheckAll(that,index);
            }else{
                $('.checkAll'+(that.index()+1)).removeClass('on_check').siblings('').removeClass('on_check');
                that.parents('.z_cont').siblings('.z_title').find('.z_check'+index).removeClass('on_check');
            }
        },
        isCheckAll:function(that,index){
            if($('.z_check'+index).length == $('.z_check'+index+'.on_check').length){
                $('.checkAll'+index).addClass('on_check');
            }else{
                $('.checkAll'+(that.index()+1)).removeClass('on_check').siblings().removeClass('on_check');
            }
        },
        isCheckGroup:function(that,index){
            var parentBox = that.parents('.z_cont');
            if(parentBox.find('.z_check'+index).length == parentBox.find('.z_check'+index+'.on_check').length){
                parentBox.siblings('.z_title').find('.z_check'+index).addClass('on_check').siblings().removeClass('on_check');
            }else{
                parentBox.siblings('.z_title').find('.z_check'+index).removeClass('on_check').siblings().removeClass('on_check');
            }
        }
    }

    $('.checkAll1,.checkAll2').on('click',function(){
        checkBox.checkAll($(this),1);
    });
    $('.clearAll').on('click',function(){
        checkBox.checkAll($(this),0);
    });
    $('.z_check1,.z_check2').on('click',function(){
        checkBox.checkOne($(this));
    });
    $('.checkGroup1,.checkGroup2').on('click',function(){
        checkBox.checkGroup($(this));
    });


    //提交信息
    $('#btn').on('click',function(){
        var arr = [];
        for(var i = 0; i < $('.z_check1.on_check').not('.checkGroup1').length;i++){
            var id = $('.z_check1.on_check').not('.checkGroup1').eq(i).find('input[type=checkbox]').attr('id');
            arr.push({id:id,type:1});
        }
        for(var j = 0; j < $('.z_check2.on_check').not('.checkGroup2').length;j++){
            var id = $('.z_check2.on_check').not('.checkGroup2').eq(j).find('input[type=checkbox]').attr('id');
            arr.push({id:id,type:0});
        }
        console.log(arr);
        //arr转为json
        var jsonStr = "[";
        for(var i=0; i<arr.length; i++){
            jsonStr += "{id:'"+arr[i].id+"',type:"+arr[i].type+"}";
            if(i != arr.length-1){
                jsonStr += ",";
            }
        }
        jsonStr += "]";
        //alert(jsonStr);
        /* Ajax提交数据 */
        $.ajax({
            type: "POST",
            url: "../memberAuthority/saveRoleMenu",
            data: {
                setId:$("#setId").val(),
                jsonStr:jsonStr
            },
            dataType: "json",
            success: function (data) {
                if(data.error == '0'){
                    alert("保存成功");
                    //window.history.back(-2);
                    window.location.href='../memberAuthority/roleList.html?menuId=' + $("#menuId").val();
                }else{
                    alert("保存失败");
                }
            }
        });
        /* ---------- */
    })
})

// [
//   {
//     id:101,
//     type:0
//   },
//   {
//     id:102,
//     type:1
//   }
// ]
