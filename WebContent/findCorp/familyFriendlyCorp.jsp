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
	
	<title>CorpCollector : 가족 친화 기업</title>
</head>
<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div width="1200px;" style="text-align:center; margin:5% auto;">
		<div class="sidebar_div" style="float:left;">
			<aside class="sidebar">
				<ul style="list-style-type:none; ">
					<h3>기업 찾기</h3>
					<p><a href="<c:url value='/findCorp/talentDevelopmentCorp.jsp'/>">인재 육성형 중소 기업</a></p>
					<p><a href="<c:url value='/findCorp/greenCorp.jsp'/>">녹색 기업</a></p>
					<p><a href="<c:url value='/findCorp/socialCorp.jsp'/>">사회적 기업</a></p>
					<p><a href="<c:url value='/findCorp/familyFriendlyCorp.jsp'/>" style="color:#e1bf27; font-weight:bold;">가족 친화 기업</a></p>
					<p><a href="<c:url value='/findCorp/youthFriendlyCorp.jsp'/>">청년 친화 강소 기업</a></p>
				</ul>
			</aside>
			<br>
			<aside class="rankbar" align="left;">
				<h4>가장 많이 검색한 기업</h4>
					<p>1.</p>
			</aside>
		</div>
		
		<div class="content_div">
			<table width="1000px;" style="text-align:center; float:right; background-color:gray;">
				<tr>
				<table width="900px;" style="text-align: center; margin:0 auto; position:relative;">
					<tr>
						<form align="center">
						<td>
							<input class="search_bar" type="text" id="search_keyword" autocomplete="off" placeholder=" 검색어를 입력하세요">
						</td>
						<td>
							<button class = "search_btn" type=submit value="" onclick="">
							<img src="<c:url value='/images/search_logo.PNG'/>" alt="search" width="55px;">
							</button>
						</td>
					</form>
					</tr>
				</table>
				</tr>
				<tr>
					<article class="content_div_findCorp">
						<p>검색결과</p>
					</article>
				</tr>
				
			</table>
		</div>
	</div>



	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	
</body>
</html>