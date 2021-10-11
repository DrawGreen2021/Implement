/**
 * 
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

window.onload=function(){
	
	$('#findPwBtn').click(function() {
		var irregular = validate();
		
		if(!irregular) {
			$("form").attr("action", "FindPw.do");
		}
		console.log("체크됨");
	});
}

function validate(){
	var irregular = false;
	var findInfo_id = document.getElementById("findInfo_id").value;
	var findInfo_email = document.getElementById("findInfo_email").value;
	
	var pattern_chk1 = /[`~!@#$%^&*()_+:{}\\\'\";\/?]/gi;
	var pattern_chk2 = /[0-9]/;
	var pattern_chk3 = /[a-zA-Z]/;
	var pattern_chk4 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi;
	
	//아이디 체크
	if(findInfo_id.length == 0){
		alert("아이디를 입력하세요");
		irregular = true;
	}
	else if(findInfo_id.search(/\s/) != -1){
		alert("아이디에 공백은 들어갈 수 없습니다");
		irregular = true;
	}
	else if(pattern_chk1.test(findInfo_id)){
		alert("아이디에 특수문자는 들어갈 수 없습니다");
		irregular = true;
	}
	
	//이메일 체크
	else if(findInfo_email.length === 0 || findInfo_email.search(/\s/) != -1 || pattern_chk4.test(findInfo_email)){
		alert("잘못된 이메일 주소입니다");
		irregular = true;
	}
	
	return irregular;
}