<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div width="1200px;" style="text-align: center; margin: 5% auto;">
		<div class="sidebar_div" style="float: left;">
			<aside class="sidebar">
				<ul style="list-style-type: none;">
					<h3>기업 찾기</h3>
					<p>
						<a href="<c:url value='/findCorp/talentDevelopmentCorp.jsp'/>">
						인재 육성형 중소 기업</a>
					</p>
					<p>
						<a href="<c:url value='/findCorp/greenCorp.jsp'/>">녹색 기업</a>
					</p>
					<p>
						<a href="<c:url value='/findCorp/socialCorp.jsp'/>">사회적 기업</a>
					</p>
					<p>
						<a href="<c:url value='/findCorp/familyFriendlyCorp.jsp'/>"
							style="color: #e1bf27; font-weight: bold;">가족 친화 기업</a>
					</p>
					<p>
						<a href="<c:url value='/findCorp/youthFriendlyCorp.jsp'/>">
						청년 친화 강소 기업</a>
					</p>
				</ul>
			</aside>
			<br>
			<aside class="rankbar" align="left;">
				<h4>가장 많이 검색한 기업</h4>
				<p>1.</p>
			</aside>
		</div>

		<div class="content_div">
			<table width="1000px;"
				style="text-align: center; float: right; background-color: gray;">
				<tr>
					<table width="900px;"
						style="text-align: center; margin: 0 auto; position: relative;">
						<tr>
							<form align="center" action="FindFamilyFriendlyCorp.do"
								method="get" name="findFamilyFriendlyCorp"
								id="findFamilyFriendlyCorp">
							<td><input type="hidden" name="page" value="1"> <input
								class="search_bar" type="text" id="search_keyword"
								autocomplete="off" placeholder=" 검색어를 입력하세요" name="keyword"
								value="${(param.keyword==undefined)?'':param.keyword}">
							</td>
							<td>
								<button class="search_btn" type="submit" value="" onclick="">
									<img src="<c:url value='/images/search_logo.PNG'/>"
										alt="search" width="55px;">
								</button>
							</td>
							</form>
						</tr>
					</table>
				</tr>
				<tr>
					<article class="content_div_findCorp">
						<!-- 기업 리스트 출력 -->
						<c:choose>
							<%-- 기업 리스트가 null이면 검색 결과가 없다고 표시 --%>
							<c:when test="${requestScope.FamilyFriendlyCorpList == 'noResult' }">
								검색 결과가 없습니다.
							</c:when>

							<%-- 기업 리스트가 존재하면 출력해주는 테이블 생성 --%>
							<c:when test="${not empty requestScope.FamilyFriendlyCorpList }">

								<table cellpadding="0" cellspacing="0" border="1">

									<tr>
										<td></td>
										<td>기업명</td>
										<td>분류</td>
										<td>시도</td>
									</tr>
									<c:forEach items="${requestScope.FamilyFriendlyCorpList }" var="dto">
										<tr>
											<td>☆</td>
											<td><a>${dto.company_name }</a></td>
											<td>${dto.division }</td>
											<td>${dto.city_state }</td>
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
										href='FindFamilyFriendlyCorp.do?page=${startNum - pageCount}&keyword=${param.keyword}'>이전</a>
									</span>
								</c:if>
								<c:if test="${startNum <= 1}">
									<span onclick="alert('이전 페이지가 없습니다.');">이전</span>
								</c:if>

								<%-- 페이지의 가장 끝 번호까지만 표시 --%>
								<span> <c:forEach var="num" begin="${startNum }"
										end="${lastNum }">
										<c:if test="${num <= lastPageNum }">
											<a
												href='FindFamilyFriendlyCorp.do.do?page=${num}&keyword=${param.keyword }'>${num}</a>
										</c:if>
									</c:forEach>
								</span>

								<c:if test="${(startNum + pageCount -1) < lastPageNum }">
									<span> <a
										href='FindFamilyFriendlyCorp.do.do?page=${startNum + pageCount}&keyword=${param.keyword}'>다음</a>
									</span>
								</c:if>
								<c:if test="${(startNum + pageCount -1) >= lastPageNum }">
									<span onclick="alert('다음 페이지가 없습니다.');">다음</span>
								</c:if>

							</c:when>

							<%-- 처음에 기업 리스트의 값이 아무것도 없으면 findGreenCorp.do 액션 수행 --%>
							<c:otherwise>
								<script>
									document.getElementById('findFamilyFriendlyCorp').submit();
								</script>
							</c:otherwise>

						</c:choose>

						<%-- 검색 후 초기 화면으로 되돌아가기 --%>
						<button onclick="resetKeyword()">전체 목록보기</button>

					</article>
				</tr>

			</table>
		</div>
	</div>



	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>

	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	<script type="text/javascript">
	function resetKeyword() {
		var form = document.findFamilyFriendlyCorp;
		var noKeyword = "";
		
		form.keyword.value = noKeyword;
		form.action = "FindFamilyFriendlyCorp.do";
		form.method = "get";
		form.submit();
	}
	</script>
</body>
</html>