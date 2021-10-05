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
	
	<title>CorpCollector : 기업 찾기</title>
</head>
<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div width="1200px;" style="text-align:center; margin:5% auto; float:left;">
	<aside class="sidebar">
		<ul>
			<li><a href="<c:url value=''/>">인재 육성형 중소 기업</a>
			<li><a href="<c:url value=''/>">녹색 기업</a>
			<li><a href="<c:url value=''/>">사회적 기업</a>
			<li><a href="<c:url value=''/>">가족 친화 기업</a>
			<li><a href="<c:url value=''/>">청년 친화 강소 기업</a>
		</ul>
	</aside>
	<div width="1000px;" style="text-align:center;">
		<form align="center">
			
			<input class="search_bar" type="text" id="search_keyword" autocomplete="off" placeholder=" 검색어를 입력하세요">
			
			<button class = "search_btn" type=submit value="" onclick="location='/findCorp/findCorp_main.jsp'">
			<img src="<c:url value='/images/search_logo.png'/>" alt="search" width="55px;">
			</button>
		</form>
		
		<article class="content_div_findCorp">
			
		
		</article>
	</div>
	</div>



	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	
</body>
</html>