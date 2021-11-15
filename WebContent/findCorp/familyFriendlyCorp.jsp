<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="캡스톤_01">
<meta name="keywords" content="HTML5, CSS, JQUERY">

<link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css?after"/>'>

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
				<%-- 가장 많이 검색한 기업 순위 --%>
				<c:import url="/importedFile/corpRank.jsp"></c:import>
			</aside>
		</div>

		<div class="content_div">
			<table width="1000px;"
				style="text-align: center; float: right; background-color: gray;">
				<tr>
					<table width="900px;"
						style="text-align: center; margin: 0 auto; position: relative;">
						<tr>
							<form align="center" action="FindCorp.do"
								method="get" name="findCorp" id="findCorp">
							<td><input type="hidden" name="corpType" id="corpType" value="familyFriendlyCorp">
								<input type="hidden" name="page" value="1"> 
								<input class="search_bar" type="text" id="search_keyword"
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
							<c:when test="${requestScope.corpList == 'noResult' }">
								<div class="content_div_findCorpList" style="height:350px;">
									<p style="margin:0 auto; padding:14% 10%; color:gray;">검색 결과가 없습니다.</p>
								</div>
								<button class="findCorp_list_btn" style="margin:0 0 0 89%;" onclick="resetKeyword()">전체 목록보기</button>
							</c:when>

							<%-- 기업 리스트가 존재하면 출력해주는 테이블 생성 --%>
							<c:when test="${not empty requestScope.corpList }">

								<table class="content_div_findCorpList" style="word-break: break-all;">
									<tr class="community_text" style="background-color:#eeedeb; height:32px;">
										<td width="3%"> </td>
										<td width="50%">기업명</td>
										<td width="20%x">분류</td>
										<td width="27%">시도</td>
									</tr>
									<c:forEach items="${requestScope.corpList }" var="dto" varStatus="status">
										<tr class="community_text" style="height:35px; cursor:pointer;">
											<c:choose>
												<c:when
													test="${dto.serial_number eq favoriteNums[status.index] 
															&& requestScope.favoriteNums != null && not empty sessionScope.MemberDTO}">
													<td style="text-align:center;"><button value="${dto.serial_number }"
															onclick="addFavoriteCorp(this)" class="favoriteCorp_btn">★</button></td>
												</c:when>
												<c:otherwise>
													<td style="text-align:center;"><button value="${dto.serial_number }"
															onclick="addFavoriteCorp(this)" class="favoriteCorp_btn">☆</button></td>
												</c:otherwise>
											</c:choose>
											<td><a id="corpName${dto.serial_number }" style="color:black; text-decoration:none;"
											href='DetailView.do?corpType=${param.corpType }&serial_num=${dto.serial_number }'>
												${dto.company_name }</a></td>
											<td>${dto.division }</td>
											<td>${dto.city_state }</td>
										</tr>

									</c:forEach>
								</table>
								
								
								<%-- 검색 후 초기 화면으로 되돌아가기 --%>
								<button class="findCorp_list_btn" style="margin:0 0 0 89%;" onclick="resetKeyword()">전체 목록보기</button>
								
								
								<!-- 페이지 번호 div -->
								<div class="pagelist_text" style="margin:3% auto;">
								
								<%-- 페이징 변수 파일 포함 --%>
								<c:import url='/importedFile/pagingVariables.jsp'></c:import>

								<c:if test="${startNum > 1}">
									<span><a
										href='FindCorp.do?corpType=${param.corpType }&page=${startNum - pageCount}&keyword=${param.keyword}'>이전</a>
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
												<%-- 현재 페이지는 회색이 아닌 다른 컬러로 표시 --%>
												<c:when test="${num == param.page }">
													<a style="color:yellow;" href='FindCorp.do?corpType=${param.corpType }&page=${num}&keyword=${param.keyword }'>${num}</a>
												</c:when>
												<c:otherwise>
													<a style="color:gray;" href='FindCorp.do?corpType=${param.corpType }&page=${num}&keyword=${param.keyword }'>${num}</a>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach>
								</span>

								<c:if test="${(startNum + pageCount -1) < lastPageNum }">
									<span> <a
										href='FindCorp.do?corpType=${param.corpType }&page=${startNum + pageCount}&keyword=${param.keyword}'>다음</a>
									</span>
								</c:if>
								<c:if test="${(startNum + pageCount -1) >= lastPageNum }">
									<span onclick="alert('다음 페이지가 없습니다.');">다음</span>
								</c:if>
								</div>
							</c:when>

							<%-- 처음에 기업 리스트의 값이 아무것도 없으면 findCorp.do 액션 수행 --%>
							<c:otherwise>
								<script>
									document.getElementById('findCorp').submit();
								</script>
							</c:otherwise>

						</c:choose>
						
					</article>
				</tr>

			</table>
		</div>
	</div>



	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>

	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	<script type="text/javascript" src='<c:url value="/JavaScript/findCorp_common.js"/>'></script>
</body>
</html>