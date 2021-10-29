<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="캡스톤_01">
	<meta name="keywords" content="HTML5, CSS, JQUERY">
	
	<link rel="stylesheet" type="text/css" href="../css/main.css?after">
	
	<title>CorpCollector : 관심 기업</title>
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
					<p><a href="<c:url value='/myPage/FavoriteCorpView.do?page=1'/>" style="color:#e1bf27; font-weight:bold;">관심 기업</a></p>
					<p><a href="<c:url value='/myPage/RecentSearchView.do?page=1'/>">최근 검색 기업</a></p>
					<p><a href="<c:url value='/myPage/MyFeedbackView.do?page=1'/>">내가 쓴 글</a></p>
				</ul>
			</aside>
		</div>
		
		<form action="DeleteFavoriteCorp.do" method="post" id="deleteForm">
		<div class="content_div">
			<table width="1000px;" style="text-align:center; float:right;">
				<tr>
					<p style="font-size:16pt; color:#21499b; font-weight:bold; margin:0 88% 0 0; ">관심 기업</p>
				</tr>
				<tr>
					<div style = "border:1px solid #21499b; margin:1% auto;"></div>
				</tr>
				<tr>
					<table class="content_div_write">

						<c:if test="${empty favCorpMap }">
							<br><br><br><br><br>
							관심 기업이 없습니다.
							<br><br><br><br><br><br><br>
						</c:if>
						<c:if test="${not empty favCorpMap['talentDevelopmentCorp']}">
							<c:forEach items="${favCorpMap['talentDevelopmentCorp'] }"
									var="favCorp" varStatus="status">
								<c:choose>
									<c:when test="${status.count == 1 }">
										<tr class="community_text" height="30px;">
										<td rowspan="${fn:length(favCorpMap['talentDevelopmentCorp']) }" 
											width="20%" style="background-color: #eeedeb;">인재 육성형 중소 기업</td>
										<td><a style="color:black; text-decoration:none;"
											href="../findCorp/DetailView.do?corpType=talentDevelopmentCorp&serial_num=${favCorp.key }">
											${favCorp.value }</a></td>
										<td width="5%"><input type="checkbox"
											name="favCorp_select_talent" value="${favCorp.key }"
											style="text-align: left;"></td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr class="community_text" height="30px;">
										<td><a style="color:black; text-decoration:none;"
											href="../findCorp/DetailView.do?corpType=talentDevelopmentCorp&serial_num=${favCorp.key }">
											${favCorp.value }</a></td>
										<td width="5%"><input type="checkbox"
											name="favCorp_select_talent" value="${favCorp.key }"
											style="text-align: left;"></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						
						<c:if test="${not empty favCorpMap['greenCorp']}">
							<c:forEach items="${favCorpMap['greenCorp'] }"
									var="favCorp" varStatus="status">
								<c:choose>
									<c:when test="${status.count == 1 }">
										<tr class="community_text" height="30px;">
										<td rowspan="${fn:length(favCorpMap['greenCorp']) }" 
											style="background-color: #eeedeb;">녹색기업</td>
										<td><a style="color:black; text-decoration:none;"
											href="../findCorp/DetailView.do?corpType=greenCorp&serial_num=${favCorp.key }">
											${favCorp.value }</a></td>
										<td width="5%"><input type="checkbox"
											name="favCorp_select_green" value="${favCorp.key }"
											style="text-align: left;"></td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr class="community_text" height="30px;">
										<td><a style="color:black; text-decoration:none;"
											href="../findCorp/DetailView.do?corpType=greenCorp&serial_num=${favCorp.key }">
											${favCorp.value }</a></td>
										<td width="5%"><input type="checkbox"
											name="favCorp_select_green" value="${favCorp.key }"
											style="text-align: left;"></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						
						<c:if test="${not empty favCorpMap['socialCorp']}">
							<c:forEach items="${favCorpMap['socialCorp'] }"
									var="favCorp" varStatus="status">
								<c:choose>
									<c:when test="${status.count == 1 }">
										<tr class="community_text" height="30px;">
										<td rowspan="${fn:length(favCorpMap['socialCorp']) }" 
											style="background-color: #eeedeb;">사회적 기업</td>
										<td><a style="color:black; text-decoration:none;"
											href="../findCorp/DetailView.do?corpType=socialCorp&serial_num=${favCorp.key }">
											${favCorp.value }</a></td>
										<td width="5%"><input type="checkbox"
											name="favCorp_select_social" value="${favCorp.key }"
											style="text-align: left;"></td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr class="community_text" height="30px;">
										<td><a style="color:black; text-decoration:none;"
											href="../findCorp/DetailView.do?corpType=socialCorp&serial_num=${favCorp.key }">
											${favCorp.value }</a></td>
										<td width="5%"><input type="checkbox"
											name="favCorp_select_social" value="${favCorp.key }"
											style="text-align: left;"></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						
						<c:if test="${not empty favCorpMap['familyFriendlyCorp']}">
							<c:forEach items="${favCorpMap['familyFriendlyCorp'] }"
									var="favCorp" varStatus="status">
								<c:choose>
									<c:when test="${status.count == 1 }">
										<tr class="community_text" height="30px;">
										<td rowspan="${fn:length(favCorpMap['familyFriendlyCorp']) }" 
											style="background-color: #eeedeb;">가족 친화 기업</td>
										<td><a style="color:black; text-decoration:none;"
											href="../findCorp/DetailView.do?corpType=familyFriendlyCorp&serial_num=${favCorp.key }">
											${favCorp.value }</a></td>
										<td width="5%"><input type="checkbox"
											name="favCorp_select_family" value="${favCorp.key }"
											style="text-align: left;"></td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr class="community_text" height="30px;">
										<td><a style="color:black; text-decoration:none;"
											href="../findCorp/DetailView.do?corpType=familyFriendlyCorp&serial_num=${favCorp.key }">
											${favCorp.value }</a></td>
										<td width="5%"><input type="checkbox"
											name="favCorp_select_family" value="${favCorp.key }"
											style="text-align: left;"></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						
						<c:if test="${not empty favCorpMap['youthFriendlyCorp']}">
							<c:forEach items="${favCorpMap['youthFriendlyCorp'] }"
									var="favCorp" varStatus="status">
								<c:choose>
									<c:when test="${status.count == 1 }">
										<tr class="community_text" height="30px;">
										<td rowspan="${fn:length(favCorpMap['youthFriendlyCorp']) }" 
											style="background-color: #eeedeb;">청년 친화 강소 기업</td>
										<td><a style="color:black; text-decoration:none;"
											href="../findCorp/DetailView.do?corpType=youthFriendlyCorp&serial_num=${favCorp.key }">
											${favCorp.value }</a></td>
										<td width="5%"><input type="checkbox"
											name="favCorp_select_youth" value="${favCorp.key }"
											style="text-align: left;"></td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr class="community_text" height="30px;">
										<td><a style="color:black; text-decoration:none;"
											href="../findCorp/DetailView.do?corpType=youthFriendlyCorp&serial_num=${favCorp.key }">
											${favCorp.value }</a></td>
										<td width="5%"><input type="checkbox"
											name="favCorp_select_youth" value="${favCorp.key }"
											style="text-align: left;"></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						
						<tr class="community_text" height="30px;">
							<td width="20%" style="background-color:#eeedeb;">인재 육성형 중소 기업</td>
							<td>
								<table> <!-- 여기에 기업이름 추가하기 -->
									<tr><td></td></tr>
								</table>
							</td>
							<td width="5%"><input type="checkbox" name="favCorp_select" value="favCorp_select" style=" text-align:left;"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td style="background-color:#eeedeb;">녹색기업</td>
							<td>
								<table> <!-- 여기에 기업이름 추가하기 -->
									<tr><td></td></tr>
								</table>
							</td>
							<td><input type="checkbox" name="favCorp_select" value="favCorp_select" style=" text-align:left;"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td style="background-color:#eeedeb;">사회적 기업</td>
							<td>
								<table> <!-- 여기에 기업이름 추가하기 -->
									<tr><td></td></tr>
								</table>
							</td>
							<td><input type="checkbox" name="favCorp_select" value="favCorp_select" style=" text-align:left;"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td style="background-color:#eeedeb;">가족 친화 기업</td>
							<td>
								<table> <!-- 여기에 기업이름 추가하기 -->
									<tr><td></td></tr>
								</table>
							</td>
							<td><input type="checkbox" name="favCorp_select" value="favCorp_select" style=" text-align:left;"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td style="background-color:#eeedeb;">청년 친화 강소 기업</td>
							<td>
								<table> <!-- 여기에 기업이름 추가하기 -->
									<tr><td></td></tr>
								</table>
							</td>
							<td><input type="checkbox" name="favCorp_select" value="favCorp_select" style=" text-align:left;"></td>
							
						</tr>

					</table>
				</tr>
			</table>
			
			<div>
				<label class="community_text" style="float:left;">
					<input type="checkbox" name="favCorp_select" value="favCorp_selectAll" onclick="selectAll(this)"> 전체 선택</label>
				<input type="button" value="삭제" class="writing_btn" style="float:right;" onclick="deleteFavCorp()">
			</div>
		</div>
		</form>
		
		<!-- 페이지 번호 div -->
		<div class="pagelist_text" style="margin: 6% auto;">

			<%-- 페이징 변수 파일 포함 --%>
			<c:import url='/importedFile/pagingVariables.jsp'></c:import>

			<c:if test="${startNum > 1}">
				<span> <a
					href='FavoriteCorpView.do?page=${startNum - pageCount}'>이전</a>
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
							href='FavoriteCorpView.do?page=${num}'>${num}</a>
					</c:if>
				</c:forEach>
			</span>

			<c:if test="${(startNum + pageCount -1) < lastPageNum }">
				<span> <a
					href='FavoriteCorpView.do?&page=${startNum + pageCount}'>다음</a>
				</span>
			</c:if>
			<c:if test="${(startNum + pageCount -1) >= lastPageNum }">
				<span onclick="alert('다음 페이지가 없습니다.');">다음</span>
			</c:if>
		</div>

	</div>

	<br><br><br><br><br>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	<script type="text/javascript" src="../JavaScript/myPage_common.js"></script>
</body>
</html>