
$(function(){
	addName();
	$(".menu").hover(function(){
		$(this).addClass("hover");
	}, function(){
		$(this).removeClass("hover");
	})
	$(".menu").eq(0).click(function(){
		$(".content").eq(0).show();
		$(".content").eq(1).hide();
		putBox();
	})
	$(".menu").eq(1).click(function(){
		$(".content").eq(1).show();
		$(".content").eq(0).hide();
		getNews();
	})


})

function addName(){
var header = $("header");
	header.text("김민홍").addClass("top").css({
		"color" : "blue",
		"font-size" : "50px",
		"font-weight" : "bold",
		"text-align" : "center",
		"line-height" : "150px"
	});
}

function putBox(){
	var boxs = [];
	var speeds = [];
	$("#mbox").empty();

	for(var i=0;i<5;i++){
			var r = Math.floor(Math.random()*256);
			var g = Math.floor(Math.random()*256);
			var b = Math.floor(Math.random()*256);
			var topNum = Math.floor(Math.random()*651);
			var leftNum = Math.floor(Math.random()*651);
			boxs[i] = $("<div/>").addClass("box").css({
			"background-color" : `rgb(${r},${g},${b})`,
			"top" : `${topNum}px`,
			"left" : `${leftNum}px`
		}).appendTo($("#mbox"));
	}	

	var timer;
	$("#mbutton").find(".button").eq(0).click(function(){
		timer = setInterval(moveBox, 100);
	});
	$("#mbutton").find(".button").eq(1).click(function(){
		console.log("ddd")
		clearInterval(timer);
	});
	for(var i=0;i<5;i++){
		speeds[i] = 10;
	}
	function moveBox(){

	$(".box").each(function(){
		var speed = speeds[$(this).index()];
		var y = parseInt($(this).css("top"));
		var x = parseInt($(this).css("left"));
		if(x<0 + 50||x>700 - 50){
			speeds[$(this).index()] = -speed;
			 
		}
		if(y<0 + 50||y>700 -50){
			speeds[$(this).index()] = -speed;
		}

		$(this).css({
			"top" : `${y+speed}px`,
			"left" : `${x+speed}px`
		})
	})
	
}
}

function getNews(){
	var idx = 0;
	var req = $.ajax({
		url: "news.dat",
		dataType : "xml"
	});

	req.done(function(data){

		var items = $(data).find("item");
		showNews(idx);

		function showNews(idx){
			$("#news").empty();
			var item = items.eq(idx);
			var title = item.find("title").text();
			var imgUrl = item.find("enclosure").attr("url");
			var lk = item.find("link").text();
			var descript = item.find("description").text();

			var div1 = $("<div id=news_title/>");
			var div2 = $("<div id=img_area/>");
			var div3 = $("<div id=news_desc/>");
			var aTag = $("<a/>").attr({
				"href" : lk,
				"target" : "_blank"
			}).text(title);
			div1.append(aTag);
			div2.append($("<img/>").attr("src",imgUrl));
			div3.text(descript);
			$("#news").append(div1, div2, div3);
		}

		$("#nbutton").find(".button").eq(0).click(clickPrev);
		$("#nbutton").find(".button").eq(1).click(clickNext);
		function clickPrev(){
			if(idx == 0){
				idx = items.length-1;
			}else{
				idx--;
			}
			showNews(idx);
		}
		function clickNext(){
			if(idx == items.length-1){
				idx = 0;
			}else{
				idx++;
			}
			showNews(idx);
		}
	})


	
}

