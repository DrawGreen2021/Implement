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
	
	<title>CorpCollector : 기업 찾기(녹색 기업)</title>
</head>
<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<c:set var="corpType" value="greenCompany" scope="request"></c:set>
	
	<form action="FindCorp.do" method="post">
		<input type="text" name="keword">
		<input type="submit" value="검색">
	</form>
	
	<!-- 녹색 기업 테이블 -->
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>업체명</td>
			<td>소재지</td>
			<td>업종</td>
			<td>비고</td>
			<td>지역구분</td>
			<td>사이트주소</td>
		</tr>
		
		<!-- 기업 리스트 출력 -->
		<c:forEach items="${requestScope. }">
		
		
		</c:forEach>
	</table>
	
	
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
</body>
</html>