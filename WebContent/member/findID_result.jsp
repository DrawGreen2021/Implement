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
	
	<title>CorpCollector : 아이디 찾기 결과</title>
</head>
<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<c:set var="finded_ID" value="${requestScope.found_ID }"
		scope="request"></c:set>
	<c:choose>
		<c:when test="${found_ID == null || fn:length(found_ID) == 0}">
			<div>${requestScope.errorMsg }</div>
		</c:when>
		
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
					<td><input type="button" value="비밀번호 찾기" class="signUp_btn" onclick="location.href='${pageContext.request.contextPath}/member/findPW_main.jsp'"></td>
				</tr>
				<br>
				</table>
				<br>
			</div>
		</c:otherwise>
	</c:choose>

	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
</body>
</html>