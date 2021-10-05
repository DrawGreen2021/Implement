<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
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
					<tr>
						<td>
							<input type="text" id="findInfo_email" width="600px;" placeholder=" 검색어를 입력하세요"> <!-- 검색바 css,js 수정-->
						</td>	
					</tr>
					<tr>
						<td>
							<button id="greenCom">녹색 기업</button>
						</td>
					</tr>
				</table>
			</tr>
			
		</table>
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		</article>
	</div>
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="JavaScript/common.js"></script>
	
</body>
</html>