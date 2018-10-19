$(function() {
    console.log('333');
    var curNav;
    console.log('222');

    $.ajax({
        type:"get",
        async:"false",
        dataType:"json",
        cache:false,
        url: "/login/getTab",
        success: function(json){
            console.log('111'+curNav);
            curNav = json.data;
            getMenu(curNav);
            console.log('2222');
        }
    });
    console.log('111');
    function getMenu(curNav){
        $.ajax({
            type: "get",
            async: "false",
            dataType: "json",
            url: "/login/getViewSession",
            success: function (json) {
                console.log(curNav);
                //分组
                var data = json.data;
                var arr1 = [], arr2 = [], arr3 = [], arr4 = [],arr5 = [],arr6=[];
                for (var i = 0; i < data.length; i++) {
                    if (data[i].belong == 1) {
                        arr1.push(data[i])
                    } else if (data[i].belong == 2) {
                        arr2.push(data[i])
                    } else if (data[i].belong == 3) {
                        arr3.push(data[i])
                    } else if (data[i].belong == 4) {
                        arr4.push(data[i])
                    } else if (data[i].belong == 5) {
                        arr5.push(data[i])
                    }else if (data[i].belong == 6) {
                        arr6.push(data[i])
                    }
                    ;
                }
                if (curNav) {
                    if (curNav == 1) {
                        $('#cbp-spmenu-s1').addClass('showMenu').siblings('nav').removeClass('showMenu');
                        $('#vip').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                    } else if (curNav == 2) {
                        $('#cbp-spmenu-s2').addClass('showMenu').siblings('nav').removeClass('showMenu');
                        $('#quality').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                    } else if (curNav == 3) {
                        $('#cbp-spmenu-s3').addClass('showMenu').siblings('nav').removeClass('showMenu');
                        $('#inspection').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                    } else if (curNav == 4) {
                        $('#cbp-spmenu-s4').addClass('showMenu').siblings('nav').removeClass('showMenu');
                        $('#operate').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                    } else if (curNav == 5) {
                        $('#cbp-spmenu-s5').addClass('showMenu').siblings('nav').removeClass('showMenu');
                        $('#base').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                    }else if (curNav == 6) {
                        $('#cbp-spmenu-s6').addClass('showMenu').siblings('nav').removeClass('showMenu');
                        $('#security').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                    }
                } else {
                    if (arr1.length > 1) {
                        //第一个nav显示
                        $('#cbp-spmenu-s1').addClass('showMenu').siblings('nav').removeClass('showMenu');
                        $('#vip').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                    }

                    if (arr2.length > 1) {
                        //第二个nav显示
                        if (!$('#cbp-spmenu-s1').hasClass('showMenu')) {
                            $('#cbp-spmenu-s2').addClass('showMenu').siblings('nav').removeClass('showMenu');
                            $('#quality').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                        }
                    }

                    if (arr3.length > 1) {
                        //第三个nav显示
                        if (!$('#cbp-spmenu-s2').hasClass('showMenu')) {
                            $('#cbp-spmenu-s3').addClass('showMenu').siblings('nav').removeClass('showMenu');
                            $('#inspection').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                        }
                    }

                    if (arr4.length > 1) {
                        //第三个nav显示
                        if (!$('#cbp-spmenu-s3').hasClass('showMenu')) {
                            $('#cbp-spmenu-s4').addClass('showMenu').siblings('nav').removeClass('showMenu');
                            $('#operate').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                        }
                    }
                    if (arr5.length > 1) {
                        //第三个nav显示
                        if (!$('#cbp-spmenu-s4').hasClass('showMenu')) {
                            $('#cbp-spmenu-s5').addClass('showMenu').siblings('nav').removeClass('showMenu');
                            $('#base').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                        }
                    }
                    if (arr6.length > 1) {
                        //第三个nav显示
                        if (!$('#cbp-spmenu-s5').hasClass('showMenu')) {
                            $('#cbp-spmenu-s6').addClass('showMenu').siblings('nav').removeClass('showMenu');
                            $('#security').addClass('activeStyle').siblings('li').removeClass('activeStyle');
                        }
                    }
                }
            }
        });
    };


    $('#side-menu1').metisMenu();
    $('#side-menu2').metisMenu();
    $('#side-menu3').metisMenu();
    $('#side-menu4').metisMenu();
    $('#side-menu5').metisMenu();
    $('#side-menu6').metisMenu();

    $('.navList li').on('click', function () {
        var index;
        var curId = $(this).attr('id');
        // console.log(curId);
        if (curId == "vip") {
            $('#cbp-spmenu-s1').addClass('showMenu').siblings('nav').removeClass('showMenu');
            $('#vip').addClass('activeStyle').siblings('li').removeClass('activeStyle');
            index = 1;
        } else if (curId == "quality") {
            $('#cbp-spmenu-s2').addClass('showMenu').siblings('nav').removeClass('showMenu');
            $('#quality').addClass('activeStyle').siblings('li').removeClass('activeStyle');
            index = 2;
        } else if (curId == "inspection") {
            $('#cbp-spmenu-s3').addClass('showMenu').siblings('nav').removeClass('showMenu');
            $('#inspection').addClass('activeStyle').siblings('li').removeClass('activeStyle');
            index = 3;
        } else if (curId == "operate") {
            $('#cbp-spmenu-s4').addClass('showMenu').siblings('nav').removeClass('showMenu');
            $('#operate').addClass('activeStyle').siblings('li').removeClass('activeStyle');
            index = 4;
        } else if (curId == "base") {
            $('#cbp-spmenu-s5').addClass('showMenu').siblings('nav').removeClass('showMenu');
            $('#base').addClass('activeStyle').siblings('li').removeClass('activeStyle');
            index = 5;
        }else if (curId == "security") {
            $('#cbp-spmenu-s6').addClass('showMenu').siblings('nav').removeClass('showMenu');
            $('#security').addClass('activeStyle').siblings('li').removeClass('activeStyle');
            index = 6;
        }

        $.ajax({
            type: "get",
            async: "false",
            dataType: "json",
            url: "/login/saveTab",
            data: {
                "tabValue": index
            },
            success: function (json) {
            }
        });

    });
})

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {

    $(window).bind("load resize", function() {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});
