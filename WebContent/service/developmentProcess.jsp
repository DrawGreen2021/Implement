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
	
	<title>CorpCollector : 개발 과정</title>
</head>

<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div width="1200px;" style="text-align:center; margin:5% auto;">
		<div class="sidebar_div" style="float:left;">
			<aside class="sidebar">
				<ul style="list-style-type:none; ">
					<h3>서비스 소개</h3>
					<p><a href="<c:url value='/service/service_Summary.jsp'/>">서비스 개요</a></p>
					<p><a href="<c:url value='/service/developmentProcess.jsp'/>" style="color:#e1bf27; font-weight:bold;">개발 과정</a></p>
				</ul>
			</aside>
		</div>
		
		<div class="content_div" style="text-align:left;">
			<div width="930px" height="500px">
				<h1 style="font-size:50px; color:#180E5A;">TEAM 그릴그린</h1>
				<br><br>
				<h3>프론트 엔드 담당 : 천세륜</h3>
				<p>사이트 맵에 따른 웹사이트 구성과 배치, 제작을 수행했다.</p>
				<br>
				<h3>백엔드 담당 : 오혜진</h3>
				<p> 웹 사이트 기능에 따른 알고리즘을 플로우 차트로 나타내고 데이터베이스를 다루며 프론트 엔드와 빅데이터 처리 사이 통합 과정을 총괄했다.</p>
				<br>
				<h3>빅데이터 가공 및 분석 담당 : 오규진</h3>
				<p>기업 데이터의 전처리와 데이터베이스 적재, 분석을 행했다.</p>
				<br>
			</div>
		</div>
	</div>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	<script type="text/javascript" src="../JavaScript/right_Check.js"></script>
</body>
</html>