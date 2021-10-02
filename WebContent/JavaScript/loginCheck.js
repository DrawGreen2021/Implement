/**
 * 
 */
var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

function login_chk(){
	var chk_id = document.getElementById("id").value;
	var chk_pw = document.getElementById("pw").value;
	
	//아이디를 입력 안 했을 경우
	if(chk_id == null || chk_id.length == 0 ||
			chk_pw == null || chk_pw.length == 0){
		alert("아이디와 비밀번호를 입력해주세요.");
		return false;
	} else {
		$("form").attr("action", "Login.do");
	}
}

/* 로그인 정보 입력시 enter키 눌렀을 때 */
function enterKey(){
	if(window.event.KeyCode == 13){
		login_chk();
	}
}