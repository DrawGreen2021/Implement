/**
 * 
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

window.onload=function(){
	
	$('#updatePWBtn').click(function() {
		var pw = $('#pw').val();
		var pw_chk = $('#pw_chk').val();
		
		var pattern_chk_number = /[0-9]/;
		var pattern_chk_char = /[a-zA-Z]/;
		var pattern_chk_specialChar = /[~!@#$%^&*()_+:{}]/;
		
		// 비밀번호 입력 검사
		if(pw.length == 0 || pw == null) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		
		// 비밀번호 유효성 검사: 8자리 이상 문자, 숫자, 특수문자로 구성
		else if(!pattern_chk_number.test(pw) || !pattern_chk_char.test(pw) 
				|| !pattern_chk_specialChar.test(pw) || pw.length < 8) {
			alert("비밀번호는 8자리 이상 문자, 숫자, 특수문자로 구성하여야 합니다.");
			return false;
		}
		
		else if(pw != pw_chk) {
			alert("입력하신 비밀번호가 다릅니다.");
			return false;
		}
		
		else {
			if(confirm("입력하신 정보로 비밀번호를 바꾸시겠습니까?") == true){
				$("form").attr("action", "UpdatePw.do");
			} else {
				return false;
			}
		}
		
	});
}