<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CorpCollector</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script type="text/javascript" src="../js/emailSelect.js?ver=123"></script>


	<form action="JoinOk" method="post">
		아이디 <input type="text" name="id" size="10"> <button>아이디 중복확인</button><br>
		비밀번호 <input type="password" name="pw" size="10"> <br>
		비밀번호 체크 <input type="password" name="pw_chek" size="10"> <br>
		닉네임 <input type="text" name="name" size="10"> <br>
		이메일 <input type="text" name="email1" value="이메일" onfocus="this.value='';"> @
		<input type="text" name="email2" id="email2" value="" disabled >
			<select name="email" id="email" onchange="email_change()">
			<option value="0" >선택하세요</option>
			<option value="9">직접입력</option>
    		<option value="naver.com">naver.com</option>
    		<option value="gmail.com">gmail.com</option>
    		<option value="hanmail.net">hanmail.net</option>
    		<option value="kakao.com">kakao.com</option>
    		<option value="nate.com">nate.com</option> 
   			</select>
   		<button>이메일 인증</button> <br>
   		생년월일 <input type="text" name="year" size="7">년&nbsp;
   			<input type="text" name="month" size="5">월&nbsp;
   			<input type="text" name="day" size="5">일 <br>
   		성별 <input type="radio" name="gender" value="man">남 &nbsp;
   			<input type="radio" name="gender" value="woman">여 &nbsp;
   			<input type="radio" name="gender" value="other">그 외<br>
   		<input type="submit" value="회원가입"> <input type="reset" value="취소">
	</form>
</body>
</html>