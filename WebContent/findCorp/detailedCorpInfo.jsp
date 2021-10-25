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
	
	<title>CorpCollector : 상세 기업 정보</title>
</head>
<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div width="1200px;" style="text-align:center; margin:5% auto;">
		<div class="sidebar_div" style="float:left;">
			<aside class="sidebar">
				<ul style="list-style-type:none; ">
					<h3>기업 찾기</h3>
					<p><a href="<c:url value=''/>">인재 육성형 중소 기업</a></p>
					<p><a href="<c:url value=''/>">녹색 기업</a></p>
					<p><a href="<c:url value=''/>">사회적 기업</a></p>
					<p><a href="<c:url value=''/>">가족 친화 기업</a></p>
					<p><a href="<c:url value=''/>">청년 친화 강소 기업</a></p>
				</ul>
			</aside>
			<br>
			<aside class="rankbar" align="left;">
				<h4>가장 많이 검색한 기업</h4>
					<p>1.</p>
			</aside>
		</div>
		
		<div class="content_div">
			<table width="1000px;" style="text-align:center; float:right; background-color:gray;">
				<tr>
					<%-- 이 테이블이 없으니까 위치 조정이 안 돼서 임시로 넣음 --%>
					<table width="900px;"
						style="text-align: center; margin: 0 auto; position: relative;">
					</table>
				</tr>
				<tr>
					<article class="content_div_findCorp">
						<table cellpadding="0" cellspacing="0" border="1">
							<%-- 상세 기업 정보 출력 --%>
							<c:forEach items="${requestScope.corpInfo }" var="entry">
								<c:if test="${entry.key == '연번' }">
									<input type="hidden" id="serial_num" value="${entry.value }" />
								</c:if>
								<c:if test="${entry.key == '업체명' }">
									<input type="hidden" id="corpName" value="${entry.value }" />
								</c:if>
								<tr>
									<td>${entry.key }</td>
									<td>${entry.value }</td>
								</tr>
							</c:forEach>
						</table>
						
						<%-- 기업유형 지정 --%>
						<input type="hidden" id="corpType" value="${requestScope.corpType }">
						
						<%-- 뒤로가기 --%>
						<button onclick="backSpace()">뒤로가기</button>
						
						<%-- 관심 기업으로 등록하기 --%>
						<%-- 로그인 여부에 따라 버튼 이름 결정 --%>
						<c:choose>
							<c:when test="${not empty sessionScope.MemberDTO && requestScope.isRegistered == true}">
								<button onclick="addFavoriteCorp_detail(this)">관심기업 삭제</button>
							</c:when>
							<c:otherwise>
								<button onclick="addFavoriteCorp_detail(this)">관심기업 등록</button>
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
	<script type="text/javascript" src='<c:url value="/JavaScript/findCorp_common.js?v=<%=System.currentTimeMillis() %>"/>'></script>
</body>
</html>