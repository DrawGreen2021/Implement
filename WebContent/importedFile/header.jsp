<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value='/JavaScript/logoutCheck.js'/>"/></script>
	<c:choose>
		<c:when test="${sessionScope.MemberDTO!=null }">
			<div>
			<!-- 헤더 영역-->
			<header class="header">
			
			<!-- 네비게이션(메뉴) 영역-->
			<nav class="nav">
				<a href="<c:url value='/index.jsp'/>" style="float:left;"><img src="<c:url value='/images/logo2.PNG'/>" alt="CorpCollector" class="nav_logo"></a>
				<a href="<c:url value='/myPage/personalInformation.jsp'/>" style="margin-left : 57%; float:left;">${sessionScope.MemberDTO.name}님</a> <!-- 마이페이지 -->
				<form name="passBeforeUrl">
					<input type="hidden" name="beforeUrl">
				</form>
				<a href="javascript:logoutDo()">로그아웃</a>
			</nav>
			<table style="text-align:center; width:1200px;">
				<tr>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/service/service_Summary.jsp'">서비스 소개</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/findCorp/findCorp_main.jsp'">기업 찾기</button></td>
					<td><button class="menu_btn">정보 나눔</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/community/notice.jsp'">커뮤니티</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/myPage/personalInformation.jsp'">마이페이지</button></td>
				</tr>
			</table>
			
			<!-- https://kuzuro.blogspot.com/2018/08/htmlcss.html 드롭다운 메뉴, 현재 페이지 글씨 색 변경 필요  -->
			
			</header>
			</div>	
		</c:when>
		
		<c:otherwise>
			<div>
			<!-- 헤더 영역-->
			<header class="header">
			
			<!-- 네비게이션(메뉴) 영역-->
			<nav class="nav">
				<a href="<c:url value='/index.jsp'/>" style="float:left;"><img src="<c:url value='/images/logo2.PNG'/>" alt="CorpCollector" class="nav_logo"></a>
				<a href="<c:url value='/member/login_main.jsp'/>" style="margin-left : 58%; float:left;">로그인</a>
				<a href="<c:url value='/member/signUp.jsp'/>">회원가입</a>
			</nav>
			<table style="text-align:center; width:1200px;">
				<tr>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/service/service_Summary.jsp'">서비스 소개</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/findCorp/findCorp_main.jsp'">기업 찾기</button></td>
					<td><button class="menu_btn">정보 나눔</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/community/notice.jsp'">커뮤니티</button></td>
					<td><button class="menu_btn" onclick="alert('로그인 후 이용 가능합니다')">마이페이지</button></td>
				</tr>
			</table>
			</header>
			</div>
		</c:otherwise>
	</c:choose>