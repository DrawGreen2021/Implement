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

/* ----- 회원가입 ----- */
function signUp_chk() {
	var signUp_id = document.getElementById("signUp_id").value;
	var signUp_pw = document.getElementById("signUp_pw").value;
	var signUp_pw_chk = document.getElementById("signUp_pw_chk").value;
	var signUp_name = document.getElementById("signUp_name").value;
	var birth_year = document.getElementById("birth_year").value;
	var birth_month = document.getElementById("birth_month").value;
	var birth_day = document.getElementById("birth_day").value;
	var signUp_gender = document.getElementById("signUp_gender").value;
	/* var signUp_mobile = document.getElementById("signUp_mobile").value; */
	var signUp_email = document.getElementById("signUp_email").value;
	
	var pattern_chk1 = /[`~!@#$%^&*|\\\'\";:\/?]/gi;	//아이디 특수문자 체크
	var pattern_chk2 = /[0-9]/;
	var pattern_chk3 = /[a-zA-Z]/;
	var pattern_chk4 = /[~!@#$%^&*()_+:{}]/;
	
	/* 아이디 유효성 체크 */
	if(signUp_id.length == 0){
		alert("아이디를 입력하세요");
		return;	//exit
	}
	else if(signUp_id.search(/\s/) != -1){
		alert("아이디에 공백은 들어갈 수 없습니다");
		return;	//exit
	}
	else if(pattern_chk1.test(signUp_id)){
		alert("아이디에 특수문자는 들어갈 수 없습니다");
		return;	//exit
	}
	
	/* 비밀번호 유효성 체크 */
	else if(!pattern_chk2.test(signUp_pw) || !pattern_chk3.test(signUp_pw) || !pattern_chk4.test(signUp_pw) || signUp_pw.length <8){
		alert("비밀번호는 8자리 이상 문자, 숫자 ,특수 문자로 구성하여야 합니다");
		return;	//exit
	}
	
	/* 비밀번호 재확인 체크 */
	else if(signUp_pw != signUp_pw_chk){
		alert("입력하신 비밀번호가 다릅니다");
		return;	//exit
	}
	
	/* 이름 체크 */
	else if(signUp_name.length == 0){
		alert("이름을 입력해 주세요");
		return;
	}
	else if(signUp_name.search(/\s/) != -1){
		alert("이름에 공백은 들어갈 수 없습니다");
		return;
	}
	else if(pattern_chk1.test(signUp_name)){
		alert("이름에 특수문자는 들어갈 수 없습니다");
		return;
	}
	
	/* 생년월일 확인 */
	else if(birth_year.length != 4){
		alert("년도는 4자리 숫자이어야 합니다");
		return;
	}
	else if(birth_month.length == 0){
		alert("month를 선택하세요");
		return;
	}
	else if(birth_day.length != 2){
		alert("day는 2자리 숫자이어야 합니다");
		return;
	}
	
	/* 성별 체크 */
	else if(signUp_gender.length == 0){
		alert("성별을 선택하세요");
		return;
	}
	
	/* 휴대전화 체크 */
	else if(signUp_mobile.length == 0 || pattern_chk1.test(signUp_mobile) || !pattern_chk2.test(signUp_mobile) || pattern_chk3.test(signUp_mobile) || signUp_mobile.search(/\s/) != -1){
		alert("잘못된 전화번호입니다");
		return;
	}
	
	/*이메일 체크 */
	else if(signUp_email.length == 0){
		alert("e-mail을 입력하세요");
		return;
	}
	
	/* 유효성 체크 통과시 가입 진행 */
	else{
		if(confirm("입력하신 정보로 가입하시겠습니까?") == true){
			alert("가입 되었습니다. 메인 페이지에서 다시 로그인 해 주세요");
			location.href="../index.html";
		}
	}
}

/* ----- 아이디, 비밀번호 찾기 ----- */
//인증번호 받기 - 아이디
function check_number_receive(){
	var findInfo_name = document.getElementById("findInfo_name").value;
	var findInfo_email = document.getElementById("findInfo_email").value;
	
	var pattern_chk1 = /[`~!@#$%^&*()_+:{}\\\'\";\/?]/gi;
	var pattern_chk2 = /[0-9]/;
	var pattern_chk3 = /[a-zA-Z]/;
	var pattern_chk4 = /[`~!#$%^&*()_+:{}\\\'\";\/?]/gi; //@제외
	var pattern_chk5 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi;
	
	//이름 체크
	if(findInfo_name.length == 0){
		alert("닉네임을 입력해주십시오");
		return;
	}
	else if(findInfo_name.search(/\s/) != -1){
		alert("닉네임에 공백은 들어갈 수 없습니다");
		return;
	}
	else if(pattern_chk1.test(findInfo_name)){
		alert("닉네임에 특수문자는 들어갈 수 없습니다");
		return;
	}
	else if(pattern_chk2.test(findInfo_name)){
		alert("닉네임에 숫자는 들어갈 수 없습니다");
		return;	//exit
	}
	
	//이메일 체크. @ 필수로 넣는 조건 추가
	else if(findInfo_email.length == 0 || findInfo_email.search(/\s/) != -1 || pattern_chk4.test(findInfo_email) || pattern_chk5.test(findInfo_email)){
		alert("잘못된 이메일 주소입니다");
		return;
	}
	
	//유효성 체크 통과시 인증번호 발송
	else{
		alert("인증번호가 발송되었습니다");
	}
}

//아이디 찾기
function findId_chk(){
	var findInfo_name = document.getElementById("findInfo_name").value;
	var findInfo_email = document.getElementById("findInfo_email").value;
	var check_number = document.getElementById("check_number").value;
	
	var pattern_chk1 = /[`~!@#$%^&*()_+:{}\\\'\";\/?]/gi;
	var pattern_chk2 = /[0-9]/;
	var pattern_chk3 = /[a-zA-Z]/;
	var pattern_chk4 = /[`~!#$%^&*()_+:{}\\\'\";\/?]/gi; //@제외
	var pattern_chk5 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi;
	
	
	//이름 체크
	if(findInfo_name.length == 0){
		alert("닉네임을 입력해주십시오");
		return;
	}
	else if(findInfo_name.search(/\s/) != -1){
		alert("닉네임에 공백은 들어갈 수 없습니다");
		return;
	}
	else if(pattern_chk1.test(findInfo_name)){
		alert("닉네임에 특수문자는 들어갈 수 없습니다");
		return;
	}
	else if(pattern_chk2.test(findInfo_name)){
		alert("닉네임에 숫자는 들어갈 수 없습니다");
		return;	//exit
	}
	
	//이메일 체크. @ 필수로 넣는 조건 추가
	else if(findInfo_email.length == 0 || findInfo_email.search(/\s/) != -1 || pattern_chk4.test(findInfo_email) || pattern_chk5.test(findInfo_email)){
		alert("잘못된 이메일 주소입니다");
		return;
	}
	
	//인증번호 체크
	else if(check_number.length != 6 || !pattern_chk2.test(check_number) || check_number.search(/\s/) != -1 || pattern_chk1.test(check_number) || pattern_chk3.test(check_number) || pattern_chk5.test(check_number)){
		alert("인증번호가 다릅니다");
		return;
	}
	
	//유효성 체크 통과시 아이디 찾기 진행
	else{
		alert("입력하신 정보의 회원정보가 존재하지 않습니다");
	}
}

//인증번호 받기 - 비밀번호
function check_number_receive_pw(){
	var findInfo_id = document.getElementById("findInfo_id").value;
	var findInfo_name = document.getElementById("findInfo_name").value;
	var findInfo_email = document.getElementById("findInfo_email").value;
	
	var pattern_chk1 = /[`~!@#$%^&*()_+:{}\\\'\";\/?]/gi;
	var pattern_chk2 = /[0-9]/;
	var pattern_chk3 = /[a-zA-Z]/;
	var pattern_chk4 = /[`~!#$%^&*()_+:{}\\\'\";\/?]/gi; //@제외
	var pattern_chk5 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi;
	
	//아이디 체크
	if(findInfo_id.length == 0){
		alert("아이디를 입력하세요");
		return;
	}
	else if(findInfo_id.search(/\s/) != -1){
		alert("아이디에 공백은 들어갈 수 없습니다");
		return;
	}
	else if(pattern_chk1.test(findInfo_id)){
		alert("아이디에 특수문자는 들어갈 수 없습니다");
		return;
	}
	
	//이름 체크
	if(findInfo_name.length == 0){
		alert("이름을 입력해주십시오");
		return;
	}
	else if(findInfo_name.search(/\s/) != -1){
		alert("이름에 공백은 들어갈 수 없습니다");
		return;
	}
	else if(pattern_chk1.test(findInfo_name)){
		alert("이름에 특수문자는 들어갈 수 없습니다");
		return;
	}
	else if(pattern_chk2.test(findInfo_name)){
		alert("이름에 숫자는 들어갈 수 없습니다");
		return;	//exit
	}
	
	//이메일 체크. @ 필수로 넣는 조건 추가
	else if(findInfo_email.length == 0 || findInfo_email.search(/\s/) != -1 || pattern_chk4.test(findInfo_email) || pattern_chk5.test(findInfo_email)){
		alert("잘못된 이메일 주소입니다");
		return;
	}
	
	//유효성 체크 통과시 인증번호 발송
	else{
		alert("인증번호가 발송되었습니다");
	}
	
}


//비밀번호 찾기
function findPw_chk(){
	var findInfo_id = document.getElementById("findInfo_id").value;
	var findInfo_name = document.getElementById("findInfo_name").value;
	var findInfo_email = document.getElementById("findInfo_email").value;
	var check_number = document.getElementById("check_number").value;
	
	var pattern_chk1 = /[`~!@#$%^&*()_+:{}\\\'\";\/?]/gi;
	var pattern_chk2 = /[0-9]/;
	var pattern_chk3 = /[a-zA-Z]/;
	var pattern_chk4 = /[`~!#$%^&*()_+:{}\\\'\";\/?]/gi; //@제외
	var pattern_chk5 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi;
	
	//아이디 체크
	if(findInfo_id.length == 0){
		alert("아이디를 입력하세요");
		return;
	}
	else if(findInfo_id.search(/\s/) != -1){
		alert("아이디에 공백은 들어갈 수 없습니다");
		return;
	}
	else if(pattern_chk1.test(findInfo_id)){
		alert("아이디에 특수문자는 들어갈 수 없습니다");
		return;
	}
	
	//이름 체크
	if(findInfo_name.length == 0){
		alert("닉네임을 입력해주십시오");
		return;
	}
	else if(findInfo_name.search(/\s/) != -1){
		alert("닉네임에 공백은 들어갈 수 없습니다");
		return;
	}
	else if(pattern_chk1.test(findInfo_name)){
		alert("닉네임에 특수문자는 들어갈 수 없습니다");
		return;
	}
	else if(pattern_chk2.test(findInfo_name)){
		alert("닉네임에 숫자는 들어갈 수 없습니다");
		return;	//exit
	}
	
	//이메일 체크. @ 필수로 넣는 조건 추가
	else if(findInfo_email.length == 0 || findInfo_email.search(/\s/) != -1 || pattern_chk4.test(findInfo_email) || pattern_chk5.test(findInfo_email)){
		alert("잘못된 이메일 주소입니다");
		return;
	}
	
	//인증번호 체크
	else if(check_number.length != 6 || !pattern_chk2.test(check_number) || check_number.search(/\s/) != -1 || pattern_chk1.test(check_number) || pattern_chk3.test(check_number) || pattern_chk5.test(check_number)){
		alert("인증번호가 다릅니다");
		return;
	}
	
	//유효성 체크 통과시 아이디 찾기 진행
	else{
		alert("입력하신 이메일로 임시 비밀번호를 발송하였습니다");
		location.href = "../index.html";
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
	location.href = "index.html";
}