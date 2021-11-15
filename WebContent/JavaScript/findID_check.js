/**
 * 아이디 찾기에 필요한 JS파일
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

window.onload=function(){
	
	$('#findIdBtn').click(function() {
		var irregular = validate();
		
		if(!irregular) {
			$("form").attr("action", "FindId.do");
		}
	});
	
}

function validate(){
	var irregular = false;
	var findInfo_name = document.getElementById("findInfo_name").value;
	var findInfo_email = document.getElementById("findInfo_email").value;
	
	var pattern_chk1 = /[`~!@#$%^&*()_+:{}\\\'\";\/?]/gi;
	var pattern_chk2 = /[0-9]/;
	var pattern_chk3 = /[a-zA-Z]/;
	var pattern_chk4 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi;
	
	//이름 체크
	if(findInfo_name.length == 0){
		alert("닉네임을 입력해주십시오");
		irregular = true;
	}
	else if(findInfo_name.search(/\s/) != -1){
		alert("닉네임에 공백은 들어갈 수 없습니다");
		irregular = true;
	}
	else if(pattern_chk1.test(findInfo_name)){
		alert("닉네임에 특수문자는 들어갈 수 없습니다");
		irregular = true;
	}
	else if(pattern_chk2.test(findInfo_name)){
		alert("닉네임에 숫자는 들어갈 수 없습니다");
		irregular = true;
	}
	
	//이메일 체크
	else if(findInfo_email.length === 0 || findInfo_email.search(/\s/) != -1 || pattern_chk4.test(findInfo_email)){
		alert("잘못된 이메일 주소입니다");
		irregular = true;
	}
	
	return irregular;
}