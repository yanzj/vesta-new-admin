
//左右键翻页代码:规则:page+1/page-1.html
document.onkeydown = chang_page

function chang_page() {
	var evt = getEvent();
	var arr = /(\d+)-?(\d*).html/g.exec(window.location.pathname);
	var numarr = arr[0].split(".");
	var countdesc = parseInt(numarr[0]) - 1 > 0 ? parseInt(numarr[0]) - 1 : 1;
	var countasc = parseInt(numarr[0]) + 1;
	//	var countasc = parseInt(numarr[0]) + 1 < 16 ? parseInt(numarr[0]) + 1 : 15;
	if (evt.keyCode == 37 || evt.keyCode == 33) location.href = window.location.pathname.replace(arr[0], countdesc + '.html');
	if (evt.keyCode == 39 || evt.keyCode == 34) location.href = window.location.pathname.replace(arr[0], countasc + '.html');
}

function getEvent() {
	if (document.all) return window.event; //如果是ie
	func = getEvent.caller;
	while (func != null) {
		var arg0 = func.arguments[0];
		if (arg0) {
			if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
				return arg0;
			}
		}
		func = func.caller;
	}
	return null;
}


// jquery开始
$(document).ready(function() {

//	$(".tableStyle tr:nth-child(even) td").css("background", "#f5f5f5");
//	$(".tableStyle02 tr:nth-child(even) td").css("background", "#f5f5f5");
//
	$(".selS1").click(function(){
		$(".navUl").show();
	})
	$(".js2 a").click(function() {
		$(".current2").removeClass("current2");
		$(this).addClass("current2")
	})
	$(".listUl li ul a").click(function() {
		$(".current2").removeClass("current2");
		$(this).addClass("current2")
	})
	$(".js1").click(function() {
		var status = $(this).next(".listUlChild").css("display");
		//	alert(status);
		//		if (status == "none") {
		$(".listUlChild").slideUp();
		$(".down").removeClass("down")
		$(this).next(".listUlChild").slideDown();
		$(this).children("i").addClass("down");
		//	} else {
		//		$(this).next(".listUlChild").slideUp();
		//		$(this).children("i").removeClass("down");
		//	}
	})
});

