<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="캡스톤_01">
<meta name="keywords" content="HTML5, CSS, JQUERY">

<link rel="stylesheet" type="text/css" href="../css/main.css?ver=<%=System.currentTimeMillis() %>">

<title>CorpCollector : 회원가입</title>
</head>

<body>
	<div id="container">
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>

	<!-- 회원가입 영역 -->
	<form method="post" name="join" id="form">
		<div class="content_div_signUp">
			<br>
			<table align="center" class="signUp_table">
				<tr height="40px;">
					<td colspan="3">아이디/ID</td>
				</tr>
				<tr>
					<td colspan="3"><input type="text" name="id" id="id"></td>
				</tr>
				<tr height="40px;">
					<td colspan="3">아이디/ID 중복 확인</td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" id="idCheckBtn" value="아이디 중복 확인" style="height:28px;" class="signUpPage_btn">
					<input type="hidden" name="authId" id="authID" value="false"></td>
				</tr>
				<tr height="40px;">
					<td colspan="3">비밀번호/PW</td>
				</tr>
				<tr>
					<td colspan="3"><input type="password" name="pw" id="pw">
					</td>
				</tr>

				<tr height="40px;">
					<td colspan="3">비밀번호 확인</td>
				</tr>
				<tr>
					<td colspan="3"><input type="password" id="pw_chk">
					</td>
				</tr>

				<tr height="40px;">
					<td colspan="3">닉네임</td>
				</tr>
				<tr>
					<td colspan="3"><input type="text" name="name" id="name"></td>
				</tr>

				<tr height="40px;">
					<td colspan="3">생년월일</td>
				</tr>
				<tr>
					<td><input type="number" name="year" id="year" placeholder="년(4자)"
						style="width: 100px; height: 20px; margin: 0 auto;"></td>
					<td><select id="month" name="month"
						style="width: 54px; height: 26px;">
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

				<tr height="40px;">
					<td colspan="3">성별</td>
				</tr>
				<tr>
					<td colspan="3"><select id="gender" name="gender"
						style="width: 232px; height: 26px; font-size: 10pt;">
							<option value="">성별</option>
							<option value="man">남</option>
							<option value="woman">여</option>
							<option value="none">선택 안 함</option>
					</select></td>
				</tr>

				<tr height="40px;">
					<td colspan="3">e-mail</td>
				</tr>
				<tr>
					<td colspan="3"><input type="text" id="email" name="email">
					</td>
				</tr>
				<tr height="40px;">
					<td colspan="3">이메일 인증</td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" id="emailSendBtn" value="이메일 인증" style="height:28px;" class="signUpPage_btn">
					</td>
				</tr>
				<tr>
					<td colspan="3" class="emailNotice" >
						<p style="width: 220px;">* 이메일이 도착하지 않았다면<br> 스팸 메일함을 확인해주세요.</p>
					</td>
				</tr>
				<tr height="40px;">
					<td colspan="3">인증코드</td>
				</tr>
				<tr>
					<td colspan="3"><input type="text" id="email_auth_code" name="email_auth_code">
					</td>
				</tr>
				<tr height="40px;">
					<td colspan="3">인증코드 확인</td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" id="emailCheckBtn" value="인증번호 확인" style="height:28px;" class="signUpPage_btn">
					<input type="hidden" name="authEmail" id="authEmail" value="false">
					</td>
				</tr>
			</table>

			<!-- 가입하기 버튼 -->
			<table align="center" class="signUp_btn_table">
				<tr>
					<td><input type="submit" value="회원가입" id="signUpBtn" class="signUp_btn"></td>
					<td><input type="reset" value="취소" style="background-color:#eeedeb;"class="signUp_btn"></td>
				</tr>
				<br>
			</table>
			<br>
		</div>
	</form>

	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	</div>

	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="<c:url value='/JavaScript/signUp_check.js?ver=<%=System.currentTimeMillis() %>'/>"></script>
</body>
</html>