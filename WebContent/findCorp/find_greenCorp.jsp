<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.URLEncoder"%>
<%
	application.setAttribute("greenCorp", "녹색기업");
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="캡스톤_01">
<meta name="keywords" content="HTML5, CSS, JQUERY">
<link rel="shortcut icon" href="#">
<link rel="stylesheet" type="text/css" href="../css/main.css">

<title>CorpCollector : 기업 찾기(녹색 기업)</title>
</head>
<body>

	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>

	<!-- 내용 영역 -->

	<form action="FindGreenCorp.do" method="get" name="findGreenCorp"
		id="findGreenCorp">
		<input type="hidden" name="page" value="1"> 
		<input type="text" name="keyword" id="keyword" 
			value="${(param.keyword==undefined)?'':param.keyword}">
		<input type="submit" value="검색" id="search">
	</form>
	

	<!-- 기업 리스트 출력 -->
	<c:choose>
		<%-- 기업 리스트가 null이면 검색 결과가 없다고 표시 --%>
		<c:when test="${requestScope.GreeenCorpList == 'noResult' }">
			검색 결과가 없습니다.
		</c:when>
		
		<%-- 기업 리스트가 존재하면 출력해주는 테이블 생성 --%>
		<c:when test="${not empty requestScope.GreeenCorpList }">

			<table width="1200" cellpadding="0" cellspacing="0" border="1">

				<tr>
					<td></td>
					<td>업체명</td>
					<td>소재지</td>
					<td>업종</td>
					<td>사이트주소</td>
				</tr>
				<c:forEach items="${requestScope.GreeenCorpList }" var="dto">
					<tr>
						<td>☆</td>
						<td><a>${dto.company_name }</a></td>
						<td>${dto.location }</td>
						<td>${dto.sector }</td>
						<c:choose>
							<c:when test="${dto.site eq '없음'}">
								<td>${dto.site }</td>
							</c:when>
							<c:otherwise>
								<td><a href="http://${dto.site }" target="_blank">${dto.site }</a></td>
							</c:otherwise>
						</c:choose>

					</tr>

				</c:forEach>
			</table>

			<%-- 페이지 번호, 페이지 표시 블록의 시작&끝 번호, 페이지 가장 끝 번호, 한 번에 표시할 페이지 개수 정의 --%>
			<c:set var="page" value="${(empty param.page)? 1 : param.page}"
				scope="request" />
			<c:set var="startNum" value="${requestScope.blockStartNum}"
				scope="request" />
			<c:set var="lastNum" value="${requestScope.blockLastNum}"
				scope="request" />
			<c:set var="lastPageNum" value="${requestScope.lastPageNum }"
				scope="request" />
			<c:set var="pageCount" value="${5 }" scope="request" />

			<c:if test="${startNum > 1}">
				<span><a
					href='FindGreenCorp.do?page=${startNum - pageCount}&keyword=${param.keyword}'>이전</a>
				</span>
			</c:if>
			<c:if test="${startNum <= 1}">
				<span onclick="alert('이전 페이지가 없습니다.');">이전</span>
			</c:if>

			<%-- 페이지의 가장 끝 번호까지만 표시 --%>
			<span> <c:forEach var="num" begin="${startNum }" end="${lastNum }">
					<c:if test="${num <= lastPageNum }">
						<a
							href='FindGreenCorp.do?page=${num}&keyword=${param.keyword }'>${num}</a>
					</c:if>
				</c:forEach>
			</span>

			<c:if test="${(startNum + pageCount -1) < lastPageNum }">
				<span> <a
					href='FindGreenCorp.do?page=${startNum + pageCount}&keyword=${param.keyword}'>다음</a>
				</span>
			</c:if>
			<c:if test="${(startNum + pageCount -1) >= lastPageNum }">
				<span onclick="alert('다음 페이지가 없습니다.');">다음</span>
			</c:if>
			
		</c:when>
		
		<%-- 처음에 기업 리스트의 값이 아무것도 없으면 findGreenCorp.do 액션 수행 --%>
		<c:otherwise>
			<script>
				document.getElementById('findGreenCorp').submit();
			</script>
		</c:otherwise>

	</c:choose>
	
	<%-- 검색 후 초기 화면으로 되돌아가기 --%>
	<button onclick="resetKeyword()">전체 목록보기</button>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>

	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript"
		src="<c:url value='/JavaScript/findCorpLoad.js?v=<%=System.currentTimeMillis() %>'/>"></script> 
</body>
</html>