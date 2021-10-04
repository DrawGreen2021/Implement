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
				<a href="<c:url value='/index.jsp'/>" style="margin-left : 10px;">CorpCollector</a>
				<a href="." style="margin-left : 1090px;">${sessionScope.MemberDTO.name}님</a>
				<form name="passBeforeUrl">
					<input type="hidden" name="beforeUrl">
				</form>
				<a href="javascript:logoutDo()">로그아웃</a>
			</nav>
			</header>
			</div>	
		</c:when>
		
		<c:otherwise>
			<div>
			<!-- 헤더 영역-->
			<header class="header">
			
			<!-- 네비게이션(메뉴) 영역-->
			<nav class="nav">
				<a href="<c:url value='/index.jsp'/>" style="margin-left : 10px;">CorpCollector</a>
				<a href="<c:url value='/member/login_main.jsp'/>" style="margin-left : 82%;">로그인</a>
				<a href="<c:url value='/member/signUp.jsp'/>">회원가입</a>
			</nav>
			</header>
			</div>
		</c:otherwise>
	</c:choose>