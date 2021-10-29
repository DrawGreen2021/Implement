<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="캡스톤_01">
	<meta name="keywords" content="HTML5, CSS, JQUERY">
	
	<link rel="stylesheet" type="text/css" href="../css/main.css?after">
	
	<title>CorpCollector : 서비스 개요</title>
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
					<p><a href="<c:url value='/service/service_Summary.jsp'/>" style="color:#e1bf27; font-weight:bold;">서비스 개요</a></p>
					<p><a href="<c:url value='/service/developmentProcess.jsp'/>">개발 과정</a></p>
				</ul>
			</aside>
		</div>
		
		<div class="content_div" style="text-align:left;">
			<div width="930px" height="500px">
				<h1 style="font-size:40px; color:#180E5A;">팀 그릴그린에서 기업정보 웹 서비스, <br>“CorpCollector”을 제작한다</h1>
				<br><br>
				<p>이는 단순히 기업 목록을 제공하는 서비스가 아니라, 선한 기업, 즉 사회적으로 유익한 활동을 하는 기업의 정보를 보여준다. 이러한 서
				비스를 기획한 배경에는 최근 소비자들의 소비 행위에 있다. 특정 기업들의 논란으로 인해, 소비자들의 
				불매 행위가 불거지면서 해당 기업들의 매출이 하락하는 증세를 보인다. 이런 양상에는 부도덕한 행위
				를 한 기업 제품보다는 다른 기업 제품을 소비하자는 의견이 있었기 때문이다. 따라서 선한 기업 목록
				을 쉽게 접근 가능한 웹 사이트에서 제공하여 사람들의 이로운 소비 행위 및 기업의 사회 환원을 장려
				하고자 한다.</p>
				<br>
				<p>사이트는 크게 ‘서비스 소개’, ‘기업 찾기’, ‘정보 나눔’, ‘커뮤니티’, ‘마이페이지’, ‘로그인’ 6개로 분
				류할 수 있다. ‘서비스 소개’ 부터 ‘마이페이지’ 까지의 상위 카테고리에는 각각의 하위 카테고리가 달
				려있다. ‘서비스 소개’는 어떤 서비스를 제공하는 사이트인지 설명하는 페이지이고 ‘기업 찾기’는 기업 
				정보를 검색할 수 있는 페이지, ‘정보 나눔’은 기업 데이터를 분석하고 뉴스를 제공하는 페이지, ‘커뮤니
				티’는 사용자와 소통하기 위한 페이지, ‘마이페이지’는 회원가입을 한 사용자에게 제공되는 페이지이다. </p>
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