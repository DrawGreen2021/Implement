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
	
	<title>CorpCollector : 최근 검색 기업</title>
</head>

<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div width="1200px;" style="text-align:center; margin:5% auto;">
		<div class="sidebar_div" style="float:left;">
			<aside class="sidebar">
				<ul style="list-style-type:none; ">
					<h3>마이페이지</h3>
					<p><a href="<c:url value='/myPage/PersonalInfoView.do'/>">개인 정보 관리</a></p>
					<p><a href="<c:url value='/myPage/FavoriteCorpView.do?page=1'/>">관심 기업</a></p>
					<p><a href="<c:url value='/myPage/RecentSearchView.do?page=1'/>" style="color:#e1bf27; font-weight:bold;">최근 검색 기업</a></p>
					<p><a href="<c:url value='/myPage/myFeedback.jsp'/>">내가 쓴 글</a></p>
				</ul>
			</aside>
		</div>
		
		<div class="content_div">
			<table width="1000px;" style="text-align:center; float:right;">
				<tr>
					<p style="font-size:16pt; color:#21499b; font-weight:bold; margin:0 83% 0 0; ">최근 검색 기업</p>
				</tr>
				<tr>
					<div style = "border:1px solid #21499b; margin:1% auto;"></div>
				</tr>
				<tr>
					<table class="content_div_findCorpList" style="word-break: break-all;">
					
						<c:choose>
							<c:when test="${empty requestScope.corpList }">
								<br><br><br><br><br>
								최근 상세 정보를 확인한 기업이 없습니다.
								<br><br><br><br><br><br><br>
							</c:when>
							<c:otherwise>
								<tr class="community_text" style="background-color:#eeedeb; height:32px;">
									<!-- <td width="5%">번호</td> -->
									<td width="28%">업체명</td>
									<td width="37%x">소재지</td>
									<td width="13%">업종</td>
									<td width="22%">기업유형</td>
								</tr>
								<c:forEach items="${requestScope.corpList }" var="dto">
									<tr class="community_text" style="height:35px; cursor:pointer;">
										<td><a href="../findCorp/DetailView.do?corpType=${dto.engCorpType }&serial_num=${dto.serial_number }">${dto.company_name }</a></td>
										<td>${dto.location }</td>
										<td>${dto.sector }</td>
										<td>${dto.korCorpType }</td>
									</tr>
								</c:forEach>

							</c:otherwise>
						</c:choose>
							
					</table>

					<!-- 페이지 번호 div -->
					<div class="pagelist_text" style="margin: 3% auto;">

						<%-- 페이징 변수 파일 포함 --%>
						<c:import url='/importedFile/pagingVariables.jsp'></c:import>

						<c:if test="${startNum > 1}">
							<span><a
								href='RecentSearchView.do?page=${startNum - pageCount}'>이전</a>
							</span>
						</c:if>
						<c:if test="${startNum <= 1}">
							<span onclick="alert('이전 페이지가 없습니다.');">이전</span>
						</c:if>

						<%-- 페이지의 가장 끝 번호까지만 표시 --%>
						<span> <c:forEach var="num" begin="${startNum }"
								end="${lastNum }">
								<c:if test="${num <= lastPageNum }">
									<a style="color: gray;"
										href='RecentSearchView.do?page=${num}'>${num}</a>
								</c:if>
							</c:forEach>
						</span>

						<c:if test="${(startNum + pageCount -1) < lastPageNum }">
							<span> <a
								href='RecentSearchView.do?page=${startNum + pageCount}'>다음</a>
							</span>
						</c:if>
						<c:if test="${(startNum + pageCount -1) >= lastPageNum }">
							<span onclick="alert('다음 페이지가 없습니다.');">다음</span>
						</c:if>
					</div>
				</tr>
			</table>
			
			<!-- <div>
				<label class="community_text" style="float:left;"><input type="checkbox" name="favCorp_select" value="favCorp_selectAll" > 전체 선택</label>
				<button class="writing_btn" style="float:right;" onclick="">삭제</button>
			</div> -->
		</div>
	</div>
	<br><br><br><br><br>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	<script type="text/javascript" src="../JavaScript/right_Check.js"></script>
</body>
</html>