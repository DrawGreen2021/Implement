<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="캡스톤_01">
	<meta name="keywords" content="HTML5, CSS, JQUERY">

	<link rel="stylesheet" type="text/css" href="css/main.css?ver=<%=System.currentTimeMillis() %>">
	
	<title>CorpCollector</title>
</head>

<body>
	<div id="container">
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!--콘텐츠 및 섹션 영역-->
	<div class="index_div" style="text-align: center;">
		<div style="margin:0 auto;">
		<div style="float: left; margin: 0 20px 0 130px;">
				<a href="<c:url value='/index.jsp'/>"><img src="images/logo1.PNG" alt="CorpCollector" class="logo1_img"/></a>
		</div>
		
		<div class="search_div">
			<form action="FindCorp.do" method="get" id="findCorpForm">
				<div style="float: left; margin-top: 2px;">
					<input type="hidden" id="corpType" name="corpType"
						value="interCorp"> 
					<input type="hidden" name="page"
						value="1"> 
					<input class="search_bar" type="text" style="margin: 10px;"
						id="search_keyword" name="keyword" width="600px;" height="53px"
						autocomplete="off" placeholder=" 검색어를 입력하세요">
					<!-- 검색바 css,js 수정-->
				</div>
				<div style="float: left; margin-top: 10px;">
					<button class="search_btn" type="submit">
						<img src="images/search_logo.PNG" alt="search">
					</button>
				</div>
			</form>
		</div>
		</div>
		<%-- <table width="800px;" align="center;" style="margin:0 auto;">
			<tr>
				<td>
					<a href="<c:url value='/index.jsp'/>"><img src="images/logo1.PNG" alt="CorpCollector" class="logo1_img"/></a>
				</td>
			
				<form action="FindCorp.do" method="get" id="findCorpForm">

					<td>
						<input type="hidden" id="corpType" name="corpType" value="intercorp">
						<input type="hidden" name="page" value="1">
						<input class="search_bar" type="text" id="search_keyword" name="keyword" width="600px;" autocomplete="off" placeholder=" 검색어를 입력하세요"> <!-- 검색바 css,js 수정-->
					</td>	
					<td>
						<button class = "search_btn" type="submit">
						<img src="images/search_logo.PNG" alt="search" width="58px;">
						</button>
					</td>
				</form>

			</tr>
		</table> --%>
		<br>
		<div class="horizontal_rule" "></div>	
		<br>
		<div class="index_keyword" style="padding-top: 15px;">
			<button id="greenCorp" value="greenCorp" onclick="setType(this)">녹색 기업</button>
			<button id="talentDevelopmentCorp" value="talentDevelopmentCorp" onclick="setType(this)">인재 육성형 중소 기업</button>
			<button id="socialCorp" value="socialCorp" onclick="setType(this)">사회적 기업</button>
			<button id="familyFriendlyCorp" value="familyFriendlyCorp" onclick="setType(this)">가족 친화 인증 기업</button>
			<button id="youthFriendlyCorp" value="youthFriendlyCorp" onclick="setType(this)">청년 친화 강소 기업</button>
		</div>
	</div>
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	</div>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="JavaScript/common.js?ver=<%=System.currentTimeMillis() %>"></script>
	
</body>
</html>