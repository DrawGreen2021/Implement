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
	
	<title>CorpCollector : 비밀번호 찾기 결과</title>
</head>
<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<div class="outer_block">
	<!-- 내용 영역 -->
	<c:set var="passwordCheck" value="${requestScope.passwordCheck }"
		scope="request"></c:set>
	<c:set var="user_id" value="${requestScope.user_id }" scope="request"></c:set>
	
	<c:choose>
		<%-- 비밀번호 찾기 결과가 없으면 에러 메시지 표시 --%>
		<c:when test="${passwordCheck == null}">
			<div class="content_div_findInfo">
			<br>
				<table align="center" class="findInfo_table">
					<tr>
					<td>${requestScope.errorMsg }</td>
					</tr>
				</table>
			<br>
			</div>
		</c:when>
		
		<%-- 비밀번호 찾기 결과가 있으면 비밀번호 재설정 화면을 띄운다. --%>
		<c:otherwise>
			<div class="content_div_findInfo">
			<br>
				<form method="post">
				<table align="center" class="findInfo_table">
					<tr>
						<td>비밀번호/PW</td>
					</tr>
					<tr>
						<td><input type="password" id="pw" name="pw"></td>
					</tr>
					<tr>
						<td>비밀번호 확인</td>
					</tr>
					<tr>
						<td><input type="password" id="pw_chk" name="pw_chk"></td>
					</tr>
				</table>
				
				<table align="center" class="findInfo_btn_table">
					<tr>
						<td><input type="submit" id="updatePWBtn" class="findInfo_btn" value="비밀번호 다시 설정하기"></td>
					</tr>
				</table>
				<br>
					<input type="hidden" name="id" value="${user_id }">
				</form>
			</div>
		</c:otherwise>
	</c:choose>
	</div>
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="<c:url value='/JavaScript/updatePW_check.js?ver=<%=System.currentTimeMillis() %>'/>"/></script>
</body>
</html>