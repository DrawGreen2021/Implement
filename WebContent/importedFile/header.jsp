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
				<a href="<c:url value='/index.jsp'/>" style="margin-left : 10px; float:left;"><img src="../images/logo2.PNG" alt="CorpCollector"></a>
				<a href="." style="margin-left : 80%; float:left;">${sessionScope.MemberDTO.name}님</a>
				<form name="passBeforeUrl">
					<input type="hidden" name="beforeUrl">
				</form>
				<a href="javascript:logoutDo()">로그아웃</a>
			</nav>
			<table width=1200px; style="text-align:center;">
				<tr>
					<td><button class="menu_btn">서비스 개요</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/findCorp/findCorp_main.jsp'">기업 찾기</button></td>
					<td><button class="menu_btn">정보 나눔</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/community/notice.jsp'">커뮤니티</button></td>
					<td><button class="menu_btn">마이페이지</button></td>
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
				<a href="<c:url value='/index.jsp'/>" style="margin-left : 10px; float:left;" ><img src="/images/logo2.png" alt="CorpCollector"></a>
				<a href="<c:url value='/member/login_main.jsp'/>" style="margin-left : 80%; float:left;">로그인</a>
				<a href="<c:url value='/member/signUp.jsp'/>">회원가입</a>
			</nav>
			<table width=1200px; style="text-align:center;">
				<tr>
					<td><button class="menu_btn">서비스 개요</button></td>
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