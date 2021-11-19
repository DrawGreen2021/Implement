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
	
	<title>CorpCollector : 로그인</title>
</head>

<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!--로그인 영역-->
	<div class="content_div_login">
		<!--로그인 타이틀 글자-->
		<table align="center" height="90px;">
			<tr>
				<td>
					<div class="main_title">LOGIN</div>
				</td>
			</tr>
		</table>
		
		<!-- 아이디 입력 텍스트 박스-->
		<form method="post">
		<table align="center" class="id_table">
			<tr>
				<td>
					<input type="text" id="id" name="id" class="id_pw_input" placeholder=" 아이디 입력" onkeyup="enterKey()">
				</td>
			</tr>
		</table>
		
		<!-- 비밀번호 입력 텍스트 박스 -->
		<table align="center" class="id_table">
			<tr>
				<td>
					<input type="password" id="pw" name="pw" class="id_pw_input" placeholder=" 비밀번호 입력" onkeyup="enterKey()">
				</td>
			</tr>
		</table>
		
		<!-- 로그인 버튼 -->
		<table align="center" class="login_btn_table">
			<tr>
				<td>
					<input type="submit" value="LOGIN" class="login_btn" onclick="login_chk()">
				</td>
			</tr>
		</table>
		</form>
		
		<!-- 하위 기타 메뉴 -->
		<table align="center" class="menu_line"></table>
		
		<table align="center" class="login_sub_menu">
			<tr>
				<td>
					<a href="<c:url value='/member/signUp.jsp'/>">회원가입 &nbsp; | &nbsp;</a>
				</td>
				<td>
					<a href="<c:url value='/member/findID_main.jsp'/>">아이디 찾기 &nbsp; | &nbsp;</a>
				</td>
				<td>
					<a href="<c:url value='/member/findPW_main.jsp'/>">비밀번호 찾기</a>
				</td>
			</tr>
		</table>
		
	</div>
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="<c:url value='/JavaScript/login_check.js?ver=<%=System.currentTimeMillis() %>'/>"></script>
</body>
</html>