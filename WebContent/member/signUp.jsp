<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="캡스톤_01">
<meta name="keywords" content="HTML5, CSS, JQUERY">

<link rel="stylesheet" type="text/css" href="../css/main.css">

<title>CorpCollector : 회원가입</title>
</head>

<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>

	<!-- 회원가입 영역 -->
	<form method="post" name="join" id="form">
		<div class="content_div_signUp">
			<br>
			<table align="center" class="signUp_table">
				<tr>
					<td colspan="3">아이디/ID</td>
				</tr>
				<tr>
					<td colspan="3"><input type="text" name="id" id="id"></td>
				</tr>
				<tr>
					<td colspan="3">아이디/ID 중복 확인</td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" id="idCheckBtn" value="아이디 중복 확인" ></td>
				</tr>
				
				<tr>
					<td colspan="3"><div id='idCheckMessage'></div>
					<input type="hidden" name="authId" id="authID" value="false"></td>
				</tr>

				<tr>
					<td colspan="3">비밀번호/PW</td>
				</tr>
				<tr>
					<td colspan="3"><input type="password" name="pw" id="pw">
					</td>
				</tr>

				<tr>
					<td colspan="3">비밀번호 확인</td>
				</tr>
				<tr>
					<td colspan="3"><input type="password" id="pw_chk">
					</td>
				</tr>

				<tr>
					<td colspan="3">닉네임</td>
				</tr>
				<tr>
					<td colspan="3"><input type="text" name="name" id="name"></td>
				</tr>

				<tr>
					<td colspan="3">생년월일</td>
				</tr>
				<tr>
					<td><input type="number" name="year" id="year" placeholder="년(4자)"
						style="width: 90px; height: 20px; margin: 0 auto;"></td>
					<td><select id="month" name="month"
						style="width: 50px; height: 26px;">
							<option value="">월</option>
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
					</select></td>
					<td><input type="number" name="day" id="day" placeholder="일"
						style="width: 60px; height: 20px; margin: 0 auto;"></td>
				</tr>

				<tr>
					<td colspan="3">성별</td>
				</tr>
				<tr>
					<td colspan="3"><select id="gender" name="gender"
						style="width: 230px; height: 26px; font-size: 10pt;">
							<option value="">성별</option>
							<option value="man">남</option>
							<option value="woman">여</option>
							<option value="none">선택 안 함</option>
					</select></td>
				</tr>

				<!-- <tr>
				<td colspan="3">휴대전화</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="text" id="signUp_mobile" placeholder="(-) 없이 숫자만 입력하세요">
				</td>	
			</tr> -->

				<tr>
					<td colspan="3">e-mail</td>
				</tr>
				<tr>
					<td colspan="3"><input type="text" id="email" name="email">
					</td>
				</tr>
				<tr>
					<td colspan="3">이메일 인증</td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" id="emailSendBtn" value="이메일 인증" >
					</td>
				</tr>
				<tr>
					<td colspan="3"><div id='emailSendMessage'></div></td>
				</tr>
				
				<tr>
					<td colspan="3">인증번호</td>
				</tr>
				<tr>
					<td colspan="3"><input type="text" id="email_auth_num" name="email_auth_num">
					</td>
				</tr>
				<tr>
					<td colspan="3">인증번호 확인</td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" id="emailCheckBtn" value="인증번호 확인" >
					</td>
				</tr>
				<tr>
					<td colspan="3"><div id='emailCheckMessage'></div>
   					<input type="hidden" name="authEmail" id="authEmail" value="false">
					</td>
				</tr>
			</table>

			<!-- 가입하기 버튼 -->
			<table align="center" class="signUp_btn_table">
				<tr>
					<td><input type="submit" value="회원가입" id="signUpBtn"></td>
					<td><input type="reset" value="취소"></td>
				</tr>
				<br>
			</table>
			<br>
		</div>
	</form>

	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>


	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/signUpCheck.js"></script>
</body>
</html>