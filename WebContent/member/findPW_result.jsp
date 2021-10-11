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
	
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	
	<title>CorpCollector : 비밀번호 찾기 결과</title>
</head>
<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<c:set var="passwordCheck" value="${requestScope.passwordCheck }"
		scope="request"></c:set>
	<c:set var="user_id" value="${requestScope.user_id }" scope="request"></c:set>
	<c:choose>
		<c:when test="${passwordCheck == null}">
			<div>${requestScope.errorMsg }</div>
		</c:when>
		
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

	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="<c:url value='/JavaScript/updatePW_Check.js'/>"/></script>
</body>
</html>