// 202311264 김민홍
window.onload = function (){
	putDate();
	changeColor();
	document.getElementById('click').onclick = putImg;
	document.getElementById('b1').onclick = calDate;
}

function putDate(){
	var date = new Date();
	var  time = document.getElementById('time');
	var str = `${date.getFullYear()}년 ${date.getMonth()+1}월 ${date.getDate()}일`;
	time.innerHTML = str;
}

function changeColor(){
	var r = Math.floor(Math.random()*256); 
	var g = Math.floor(Math.random()*256); 
	var b = Math.floor(Math.random()*256); 
	var header = document.getElementsByTagName('header');
	for(a of header){
		a.style.color = `rgb(${r}, ${g}, ${b})`;
	}
	setTimeout(changeColor, 2000);
}

function putImg(){
	var img = document.getElementById('img');

	if(!img.hasChildNodes()){
		var node = document.createElement('img')
		node.src = "img1.jpg";
		node.width = "300";
		img.appendChild(node);
	}

}


//1초는 1000밀리세컨드, 1분은 60000, 1시간은 
function calDate(){
	var date = document.getElementById('date');
	var now = new Date();
	var days;
	if(date.value == ""){
		alert("날짜를 선택해 주세요");
	}else{
	//	var input = date.value.split("-");
	//	var inputYear = input[0];
	//	var inputMonth = input[1];
//		var inputDate = input[2];
		var inputDate = new Date(date.value); //getTime은 현재 밀리세컨드 반환
		var rest = inputDate.getTime() - now.getTime();
		rest /= (1000 * 60 * 60 * 24); //초, 분, 시간, 일 로 나눠줌 
		days = Math.ceil(rest); 

		
		 
		document.getElementById('due').innerHTML = `오늘부터 ${days}일 남았습니다!`;
	}

	
}