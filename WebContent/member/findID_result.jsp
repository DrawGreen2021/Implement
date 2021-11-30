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
	
	<link rel="stylesheet" type="text/css" href="../css/main.css?ver=<%=System.currentTimeMillis() %>">
	
	<title>CorpCollector : 아이디 찾기 결과</title>
</head>
<body>
	<div id="container">
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div class="outer_block">
	<c:set var="found_ID" value="${requestScope.found_ID }"
		scope="request"></c:set>
	<c:choose>
		<%-- 아이디 찾기 결과가 없으면 에러 메시지 표시 --%>
		<c:when test="${found_ID == null || fn:length(found_ID) == 0}">
			<div class="content_div_findInfo" style="margin:15% auto 20%;">
			<br>
				<table align="center" class="findInfo_table">
					<tr>
					<td>${requestScope.errorMsg }</td>
					</tr>
				</table>
			<br>
			</div>
		</c:when>
		
		<%-- 아이디 찾기 결과가 있으면 아이디 표시 --%>
		<c:otherwise>
			<div class="content_div_findInfo">
			<br>
				<table align="center" class="findInfo_table">
					<tr>
					<td>${requestScope.nickname }님의 아이디는 ${found_ID}입니다.</td>
					</tr>
				</table>
				
				<table align="center" class="signUp_btn_table">
				<tr>
					<td><input type="submit" value="로그인" class="signUp_btn" onclick="location.href='${pageContext.request.contextPath}/member/login_main.jsp'"></td>
					<td><input type="button" value="비밀번호 찾기" class="signUp_btn" style="background-color:#eeedeb;"
					onclick="location.href='${pageContext.request.contextPath}/member/findPW_main.jsp'"></td>
				</tr>
				<br>
				</table>
				<br>
			</div>
		</c:otherwise>
	</c:choose>
	</div>
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	</div>
	
</body>
</html>