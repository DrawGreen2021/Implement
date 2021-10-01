<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CorpCollector</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/joinCheck.js?ver=123"></script>


	<form method="post" name="join" id="form">
		아이디 <input type="text" name="id" id="id" size="10"> &nbsp;
			<input type="submit" id="idCheckBtn" value="아이디 중복 확인" > <br>
			<div id='idCheckmessage'></div>
			<input type="hidden" name="authId" id="authID" value="false">
			
		비밀번호 <input type="password" name="pw" id="pw" size="10"> <br>
			<div>비밀번호는 8자리 이상 문자, 숫자, 특수문자로 구성하여야 합니다.</div>
		비밀번호 체크 <input type="password" name="pw_chk" id="pw_chk" size="10"> <br>
		
		닉네임 <input type="text" name="name" id="name" size="10"> <br>
		
		이메일 <input type="text" name="email" id="email" size="20">
   			<input type="submit" id="emailSendBtn" value="이메일 인증" > <br>
   			<div id='emailSendmessage'></div>
   			
   		인증번호 <input type="text" name="email_auth_num" id="email_auth_num" size="10"> &nbsp;
   			<input type="submit" id="emailCheckBtn" value="인증번호 확인" > <br>
   			<div id='emailCheckmessage'></div>
   			<input type="hidden" name="authEmail" id="authEmail" value="false">
   		
   		생년월일 <input type="number" name="year" id="year" size="5">년&nbsp;
   			<input type="number" name="month" id="month" size="3">월&nbsp;
   			<input type="number" name="day" id="day" size="3">일 <br>
   			
   		성별 <input type="radio" name="gender" value="man" checked>남 &nbsp;
   			<input type="radio" name="gender" value="woman">여 &nbsp;
   			<input type="radio" name="gender" value="other">그 외<br>
   		<input type="submit" value="회원가입" id="join"> <input type="reset" value="취소">
	</form>
</body>
</html>