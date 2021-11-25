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
	
	<title>CorpCollector : 비밀번호 찾기</title>
</head>

<body>
	<div id="container">
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div class="outer_block">
	<form method="post">
	<div class="content_div_findInfo">
		<br>
		<table align="center" class="findInfo_table">
			<tr>
				<td>아이디</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="findInfo_id" name="id">
				</td>
			</tr>
			<tr>
				<td>이메일/e-mail</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="findInfo_email" name="email" width="160px;" placeholder="이메일 주소를 입력하세요">
				</td>	
			</tr>
		</table>
		
		<table align="center" class="findInfo_btn_table">
			<tr>
				<td>
					<input type = "submit" id="emailSendBtn" value="인증번호 받기" class="findInfo_btn">
				</td>
			</tr>
			<tr>
				<td colspan="3" class="emailNotice" >
					<p style="width: 200px;">* 이메일이 도착하지 않았다면<br> 스팸 메일함을 확인해주세요.</p>
				</td>
			</tr>
		</table>
		
		<table align="center" class="findInfo_table">
			<tr>
				<td>인증코드 입력</td>
			</tr>
			<tr>
				<td>
					<input type = "text" id="email_auth_num" name="email_auth_num" placeholder="인증번호 6자리 숫자 입력">
				</td>
			</tr>
		</table>
		
		<!-- 비밀번호 찾기 -->
		<table align="center" class="findInfo_btn_table">
			<tr>
				<td>
					<input type = "submit" id="findPwBtn" value="비밀번호 찾기" class="findInfo_btn">
				</td>
			</tr>
		</table>
		<br>
	</div>
	</form>
	</div>
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	</div>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="<c:url value='/JavaScript/findPW_check.js?ver=<%=System.currentTimeMillis() %>'/>"></script>
	
</body>
</html>