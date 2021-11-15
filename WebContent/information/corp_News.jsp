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
					<h3>정보 나눔</h3>
					<p><a href="<c:url value='/information/corp_Analysis.jsp'/>">기업 데이터 분석</a></p>
					<p><a href="<c:url value='/information/CorpNewsView.do?page=1'/>" style="color:#e1bf27; font-weight:bold;">기업 기사 모음</a></p>
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
					<table class="content_div_community" style="word-break: break-all; font-weight:10pt;">
						<tr class="community_text" style="background-color: #eeedeb; height: 32px; font-weight:10pt;">
							<td width="15%">키워드</td>
							<td width="45%">제목</td>
							<td width="10%">출처</td>
							<td width="30%">사이트 주소</td>
						</tr>
						
						
						<!-- 뉴스 정보 리스트 -->
						<c:forEach items="${requestScope.newsList }" var="dto">
						<tr class="community_text" style="height:50px; cursor:pointer; font-weight:10pt;">
							<td>${dto.subTitle }</td>
							<td>${dto.title }</td>
							<td>${dto.source }</td>
							<td><a href="${dto.link }" target="_blank"
							style="color:black; text-decoration:none; font-size:10pt;">${dto.link }</a></td>
						</tr>
						</c:forEach>
								
					</table>
			</table>

			<!-- 페이지 번호 div -->
			<div class="pagelist_text" style="margin: 3% auto;">

				<%-- 페이징 변수 파일 포함 --%>
				<c:import url='/importedFile/pagingVariables.jsp'></c:import>
				<c:if test="${startNum > 1}">
					<span><a
						href='CorpNewsView.do?page=${startNum - pageCount}'>이전</a>
					</span>
				</c:if>
				<c:if test="${startNum <= 1}">
					<span onclick="alert('이전 페이지가 없습니다.');">이전</span>
				</c:if>

				<%-- 페이지의 가장 끝 번호까지만 표시 --%>
				<span> <c:forEach var="num" begin="${startNum }"
						end="${lastNum }">
						<c:if test="${num <= lastPageNum }">
							<c:choose>
								<c:when test="${num == param.page }">
									<a style="color: yellow;"
									href='CorpNewsView.do?page=${num}'>${num}</a>
								</c:when>
								<c:otherwise>
									<a style="color: gray;"
									href='CorpNewsView.do?page=${num}'>${num}</a>
								</c:otherwise>
							</c:choose>
						</c:if>
					</c:forEach>
				</span>

				<c:if test="${(startNum + pageCount -1) < lastPageNum }">
					<span> <a
						href='CorpNewsView.do?page=${startNum + pageCount}'>다음</a>
					</span>
				</c:if>
				<c:if test="${(startNum + pageCount -1) >= lastPageNum }">
					<span onclick="alert('다음 페이지가 없습니다.');">다음</span>
				</c:if>
			</div>

		</div>
	</div>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
</body>
</html>