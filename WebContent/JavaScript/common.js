/* 공통적으로 사용되는 자바 스크립트 코드 */

/* ----- 로그인 ----- */

/* 로그인 체크 (미리 저장된 테스트 용 id, pw) */
function login_chk(){
	var chk_id = document.getElementById("id").value;
	var chk_pw = document.getElementById("pw").value;
	
	//로그인 체크 (admin/admin123, user01/user111)
	//admin 인 경우
	if(chk_id == "admin" && chk_pw == "admin123"){
		alert("admin 님 로그인 되었습니다");
		location.href="../html_login/index.html";
	}
	//user01 인 경우
	else if(chk_id == "user01" && chk_pw == "user111"){
		alert("user01 님 로그인 되었습니다");
		location.href="../html_login/index.html";
	}
	//로그인 정보가 틀릴 경우
	else{
		alert("로그인 정보가 일치하지 않습니다");
	}
}

/* 로그인 정보 입력시 enter키 눌렀을 때 */
function enterKey(){
	if(window.event.KeyCode == 13){
		login_chk();
	}
}


/* ----- 로그 아웃 ----- */
function logout_chk(){
	if(confirm("로그아웃 하시겠습니까?") == true){
		location.href = "../index.html";
	}
}

/* ----- 이미지 상세정보 이동 ----- */
//인덱스 화면 로고1
function logo1_click(){
	location.href ="/index.jsp";
}

/* ----- 기업 유형 지정 ----- */
var corpType = document.getElementById("corpType");

function setType(button) {
	corpType.value = button.value;
}

