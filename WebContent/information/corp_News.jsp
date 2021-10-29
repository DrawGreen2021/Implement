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
	
	<title>CorpCollector : 기업 기사 모음</title>
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
					<p><a href="<c:url value='/information/corp_Analysis.jsp'/>">기업 데이터 분석</a></p>
					<p><a href="<c:url value='/information/corp_News.jsp'/>" style="color:#e1bf27; font-weight:bold;">기업 기사 모음</a></p>
				</ul>
			</aside>
		</div>
		
		<div class="content_div">
			<table width="1000px;" style="text-align: center; float: right;">
				<tr>
					<p style="font-size: 16pt; color: #21499b; font-weight: bold; margin: 0 83% 0 0;">기업 기사 모음</p>
				</tr>
				<tr>
					<div style="border: 1px solid #21499b; margin: 1% auto;"></div>
				</tr>
				<tr>
					<table class="content_div_community">
						<tr class="community_text" style="background-color: #eeedeb; height: 30px; font-size:11pt;">
							<td width="120px">키워드</td>
							<td width="500px">제목</td>
							<td width="150px">출처</td>
							<td width="150px">사이트 주소</td>
						</tr>
						
						
					<!-- 뉴스 정보 리스트 들어갈 수정할 파트(지금은 복붙해둔 상태) -->
						<c:forEach items="${requestScope.postList }" var="dto">
						<tr class="community_text" style="cursor: pointer;">
							<td>${dto.board_number }</td>
							<td><a
								href="PostView.do?board_number=${dto.board_number }&boardName=${param.boardName}">${dto.title }</a></td>
							<c:choose>
							<c:when test="${dto.is_private_writer == true}">
								<td>비공개</td>
							</c:when>
							<c:otherwise>
								<td>${dto.writer_name }</td>
							</c:otherwise>
							</c:choose>

							<td>${dto.registration_date }</td>
							<td>${dto.hits }</td>
						</tr>
						</c:forEach>
								
					</table>
			</table>
			
		</div>
	</div>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	<script type="text/javascript" src="../JavaScript/right_Check.js"></script>
</body>
</html>