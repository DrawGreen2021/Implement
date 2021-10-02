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
	
	<title>CorpCollector : 아이디 찾기</title>
</head>

<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div class="content_div_findInfo">
		<br>
		<table align="center" class="findInfo_table">
			<tr>
				<td>닉네임</td>
			</tr>
			<tr>
				<td>
					<input type = "text" id="findInfo_name">
				</td>	
			</tr>
			<tr>
				<td>이메일/e-mail</td>
			</tr>
			<tr>
				<td>
					<input type="text" id="findInfo_email" width="160px;" placeholder="이메일 주소를 입력하세요">
				</td>	
			</tr>
		</table>
		
		<!-- 인증번호 받기 -->
		<table align="center" class="findInfo_btn_table">
			<tr>
				<td>
					<input type = "button" value="인증번호 받기" class="findInfo_btn" onclick="check_number_receive()">
				</td>
			</tr>
		</table>
		
		<table align="center" class="findInfo_table">
			<tr>
				<td>인증번호 입력</td>
			</tr>
			<tr>
				<td>
					<input type = "text" id="check_number" placeholder="인증번호 6자리 숫자 입력">
				</td>
			</tr>
		</table>
		
		<!-- 아이디 찾기 -->
		<table align="center" class="findInfo_btn_table">
			<tr>
				<td>
					<input type = "button" value="ID 찾기" class="findInfo_btn" onclick="findId_chk()">
				</td>
			</tr>
		</table>
		<br>
	</div>
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	
</body>
</html>