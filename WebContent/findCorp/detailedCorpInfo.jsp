<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="캡스톤_01">
	<meta name="keywords" content="HTML5, CSS, JQUERY">
	
	<link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css?ver1=<%=System.currentTimeMillis() %>"/>'>
	
	<title>CorpCollector : 상세 기업 정보</title>
</head>
<body>
	
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<table width="1200px;" style="text-align:center; margin:5% auto; border:0; padding:0;">
	<tr>
	<td valign="top">
	
		<div class="sidebar_div" style="float:left;">
			<aside class="sidebar">
				<ul style="list-style-type:none; ">
					<h3>기업 찾기</h3>
					<p>
						<a href="<c:url value='/findCorp/talentDevelopmentCorp.jsp'/>">인재 육성형 중소 기업</a>
					</p>
					<p>
						<a href="<c:url value='/findCorp/greenCorp.jsp'/>">녹색 기업</a>
					</p>
					<p>
						<a href="<c:url value='/findCorp/socialCorp.jsp'/>">사회적 기업</a>
					</p>
					<p>
						<a href="<c:url value='/findCorp/familyFriendlyCorp.jsp'/>">가족 친화 기업</a>
					</p>
					<p>
						<a href="<c:url value='/findCorp/youthFriendlyCorp.jsp'/>">청년 친화 강소 기업</a>
					</p>
				</ul>
			</aside>
			<br>
			<aside class="rankbar" align="left;">
				<%-- 가장 많이 검색한 기업 순위 --%>
				<c:import url="/importedFile/corpRank.jsp"></c:import>
				
			</aside>
		</div>
	</td>
	
	<td valign="top">	
		<div class="content_div_findCorp" style="width:930px; float:right;">
				<table class="table_detailedFindCorp">
					<%-- 상세 기업 정보 출력 --%>
					<c:forEach items="${requestScope.corpInfo }" var="entry">
					<c:if test="${entry.key == '연번' }">
						<input type="hidden" id="serial_num" value="${entry.value }" />
					</c:if>
					<c:if test="${entry.key == '업체명' }">
						<input type="hidden" id="corpName" value="${entry.value }" />
					</c:if>
					<tr>
						<td style="background-color:#eeedeb; height:32px;">${entry.key }</td>
						<td style="height:32px;">${entry.value }</td>
					</tr>
					</c:forEach>
				</table>
						
						
				<div style="margin:5% auto;">
				<%-- 기업유형 지정 --%>
					<input type="hidden" id="corpType" value="${requestScope.corpType }">
						
					<%-- 뒤로가기 --%>
					<button class="writing_btn" style="background-color:#E7F1FD;" onclick="backSpace()">뒤로가기</button>
						
					<%-- 관심 기업으로 등록하기 --%>
					<%-- 로그인 여부에 따라 버튼 이름 결정 --%>
					<c:choose>
						<c:when test="${not empty sessionScope.MemberDTO && requestScope.isRegistered == true}">
							<button class="findCorp_list_btn" onclick="addFavoriteCorp_detail(this)">관심기업 삭제</button>
						</c:when>
						<c:otherwise>
							<button class="findCorp_list_btn" onclick="addFavoriteCorp_detail(this)">관심기업 등록</button>
						</c:otherwise>
					</c:choose>
				</div>
		</div>
		
	</td>
	</tr>	
	</table>

	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src='<c:url value="/JavaScript/findCorp_common.js?v=<%=System.currentTimeMillis() %>"/>'></script>
</body>
</html>