/**
 * 서비스 소개 - 개발 과정: 썸네일 클릭 시 원본 이미지 띄우기
 */

var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

/* ----- 썸네일 클릭 시 원본 이미지 띄우기 ----- */

//레이어 팝업 열기
function openLayer(src){
	
	$("#popupDiv").css({
		"background-image": "url('"+src+"')",
		"background-repeat": "no-repeat",
    	"background-position": "center center",
    	"background-size": "contain",
	    "top": (($(window).height()-$("#popupDiv").outerHeight())/2+$(window).scrollTop())+"px",
	    "left": (($(window).width()-$("#popupDiv").outerWidth())/2+$(window).scrollLeft())+"px"
	});
	
	
	$("#popup_mask").css("display","block"); //팝업 뒷배경 display block
	$("#popupDiv").css("display","block"); //팝업창 display block
	//$("body").css("overflow","hidden");//body 스크롤바 없애기
}


//레이어 팝업 닫기
function closeLayer(){
	$("#popup_mask").css("display","none"); //팝업창 뒷배경 display none
    $("#popupDiv").css("display","none"); //팝업창 display none
    //$("body").css("overflow","auto");//body 스크롤바 생성
}	

