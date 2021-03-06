<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="캡스톤_01">
	<meta name="keywords" content="HTML5, CSS, JQUERY">
	
	<link rel="stylesheet" type="text/css" href="../css/main.css?ver1=<%=System.currentTimeMillis() %>">
	
	<title>CorpCollector : 고객 후기</title>
</head>

<body>
	<div id="container">
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div class="outer_block">
		<div class="sidebar_div" style="float:left;">
			<aside class="sidebar">
				<ul style="list-style-type:none; ">
					<h3>커뮤니티</h3>
					<p><a href="<c:url value='/community/notice.jsp'/>">공지사항</a></p>
					<p><a href="<c:url value='/community/feedback.jsp'/>" style="color:#e1bf27; font-weight:bold;">고객 후기</a></p>
				</ul>
			</aside>
		</div>
		
		<div class="content_div" style="display: grid">
			<div class="search_div" style="width: 930px;">
				<form align="center" method="get" action="SearchPost.do" id="searchPost" name="searchPost">
					<div class="bar_div">
						<input class="search_bar" type="text" id="search_keyword" name="keyword" autocomplete="off" placeholder=" 검색어를 입력하세요"
							style="margin: 10px; width: 800px; height: 53px;" value="${(param.keyword==undefined)?'':param.keyword}">
						<input type="hidden" name="page" value="1">
						<input type="hidden" name="boardName" id="boardName" value="고객후기">
					</div>
					<div class="btn_div">
						<button class = "search_btn" type="submit">
							<img src="<c:url value='/images/search_logo.PNG'/>" alt="search">
						</button>
					</div>
				</form>
			</div>
			<div class="horizontal_rule_long"></div>
			
			<article>
			<c:choose>
				<%-- 고객후기 글이 없을 경우 --%>
				<c:when test="${requestScope.postList == 'noResult' }">
					<div class="content_div_community">
						<p style="margin: 0 auto; padding: 15% 10%; color: gray; text-align: center;">고객
							후기가 없습니다.</p>
					</div>
					<button class="writing_btn" style="margin: 0 0 0 92.5%;"
						value="feedback_Write.jsp" onclick="writing_Check(this)">글쓰기</button>
				</c:when>

				<%-- 고객후기 글 목록이 있다면 리스트 출력 --%>
				<c:when test="${not empty requestScope.postList }">
					<table class="content_div_community" id="content_div_community">
						<tr class="community_text"
							style="background-color: #eeedeb; height: 30px; font-size:11pt;">
							<td width="30px"></td>
							<td width="80px">번호</td>
							<td width="500px">제목</td>
							<td width="150px">작성자</td>
							<td width="150px">등록일</td>
							<td width="80px">조회수</td>
						</tr>
						<c:forEach items="${requestScope.postList }" var="dto">
							<tr class="community_text" style="height: 35px; cursor: pointer;">
								<c:choose>
									<c:when test="${dto.is_private_writing }">
										<td>🔒</td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
								<td>${dto.board_number }</td>
								<td><a style="color: black; text-decoration: none;"
									href="PostView.do?board_number=${dto.board_number }&boardName=${param.boardName}">${dto.title }</a></td>
								<c:choose>
									<c:when test="${dto.is_private_writer == true}">
										<td>비공개</td>
									</c:when>
									<c:otherwise>
										<td>${dto.writer_name }</td>
									</c:otherwise>
								</c:choose>

								<td style="font-size: 10pt;">${dto.registration_date }</td>
								<td>${dto.hits }</td>
							</tr>
						</c:forEach>

					</table>
					
					<div style="float:right">
						<button class="writing_btn" 
							value="feedback_Write.jsp" onclick="writing_Check(this)">글쓰기</button>
						
						<%-- 검색 후 초기 화면으로 되돌아가기 --%>
						<button class="findCorp_list_btn"
							onclick="resetKeyword()">전체 목록보기</button>
					</div>

					<!-- 페이지 번호 div -->
					<div class="pagelist_text" style="margin: 10% 0 0 0;">

						<%-- 페이징 변수 파일 포함 --%>
						<c:import url='/importedFile/pagingVariables.jsp'></c:import>
						<c:if test="${startNum > 1}">
							<span><a
								href='SearchPost.do?boardName=${param.boardName }&page=${startNum - pageCount}&keyword=${param.keyword}'>이전</a>
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
											<a style="color: gray; font-weight:bold;"
												href='SearchPost.do?boardName=${param.boardName }&page=${num}&keyword=${param.keyword }'>${num}</a>
										</c:when>
										<c:otherwise>
											<a style="color: gray;"
												href='SearchPost.do?boardName=${param.boardName }&page=${num}&keyword=${param.keyword }'>${num}</a>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</span>

						<c:if test="${(startNum + pageCount -1) < lastPageNum }">
							<span> <a
								href='SearchPost.do?boardName=${param.boardName }&page=${startNum + pageCount}&keyword=${param.keyword}'>다음</a>
							</span>
						</c:if>
						<c:if test="${(startNum + pageCount -1) >= lastPageNum }">
							<span onclick="alert('다음 페이지가 없습니다.');">다음</span>
						</c:if>
					</div>

				</c:when>

				<%-- 그 외의 경우 --%>
				<c:otherwise>
					<script>
						document.getElementById('searchPost').submit();
					</script>
				</c:otherwise>
			</c:choose>
			</article>
			
		</div>
	</div>


	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	</div>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/community_common.js?ver=<%=System.currentTimeMillis() %>"></script>
</body>
</html>