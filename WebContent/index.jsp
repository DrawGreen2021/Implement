<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="캡스톤_01">
	<meta name="keywords" content="HTML5, CSS, JQUERY">
	
	<link rel="stylesheet" type="text/css" href="css/main.css">
	
	<title>CorpCollector</title>
</head>

<body>
	<div>
		<!-- 헤더 영역-->
		<header class="header">
			
			<!-- 네비게이션(메뉴) 영역-->
			<nav class="nav">
				<a href="index.jsp" style="margin-left : 10px;">CorpCollector</a>
				<a href="html/login_main.html" style="margin-left : 1090px;">로그인</a>
				<a href="html/signUp.html">회원가입</a>
			</nav>
		</header>
	</div>
	
	<!--콘텐츠 및 섹션 영역-->
	<div class="index_table">
		<!-- <article>
			<header>
				<h1>CorpCollector 인덱스 페이지</h1>
				<p>검색바</p>
			</header> -->
			
		<br><br><br><br><br>
		<table width="800px;" style="text-align: center;">
			<tr>
				<td>
					<img src="images/logo1.PNG" alt="CorpCollector" class="logo1_img" onclick="logo1_click()">
				</td>
			
				<table align="center" class="search_bar">
					
					<td>
						<input type="text" id="findInfo_email" width="600px;" placeholder=" 검색어를 입력하세요"> <!-- 검색바 css,js 수정-->
					</td>	
					
				</table>
			</tr>
		</table>
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		</article>
	</div>
	
	<!-- 푸터 영역-->
	<div class="footer_div">
		<footer>
			<b>
				<font color="black">Copyright 2021. TEAM_DRAWGREEN All rights reserved</font>
			</b>
		</footer>
	</div>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="JavaScript/common.js"></script>
	
</body>
</html>