<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	application.setAttribute("greenCorp", "녹색기업");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="캡스톤_01">
<meta name="keywords" content="HTML5, CSS, JQUERY">
<link rel="shortcut icon" href="#">
<link rel="stylesheet" type="text/css" href="../css/main.css">

<title>CorpCollector : 기업 찾기(녹색 기업)</title>
</head>
<body>
	<input type="hidden" name="TOKEN_KEY"
	value="<%=request.getAttribute("TOKEN_KEY")%>" />
	
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>

	<!-- 내용 영역 -->
	<form action="FindGreenCorp.do" method="get" name="findGreenCorp"
		id="findGreenCorp">
		<input type="text" name="keyword"
			value="${(param.keyword == null)?'':param.keyword}"> 
		<input type="button" value="검색" id="search" onclick="fncSubmit()"> 
		<select name="field">
			<option ${(param.field == "업체명")? "selected" : ""} value="업체명">업체명</option>
			<option ${(param.field == "소재지")? "selected" : ""} value="소재지">소재지</option>
			<option ${(param.field == "업종")? "selected" : ""} value="업종">업종</option>
		</select>
	</form>

	<!-- 녹색 기업 테이블 -->
	<div>
		<table width="1200" cellpadding="0" cellspacing="0" border="1">
			<tr>
				<td>연번</td>
				<td>업체명</td>
				<td>소재지</td>
				<td>업종</td>
				<td>사이트주소</td>
			</tr>

			<!-- 기업 리스트 출력 -->
			<c:choose>
				<c:when test="${not empty sessionScope.GreeenCorpList }">
					<c:forEach items="${sessionScope.GreeenCorpList }" var="dto">
						<tr>
							<td>${dto.serial_number }</td>
							<td><a>${dto.company_name }</a></td>
							<td>${dto.location }</td>
							<td>${dto.sector }</td>
							<c:choose>
								<c:when test="${dto.site eq '없음'}">
									<td>${dto.site }</td>
								</c:when>
								<c:otherwise>
									<td><a href="http://${dto.site }" target="_blank">${dto.site }</a></td>
								</c:otherwise>
							</c:choose>
							
						</tr>

					</c:forEach>
				</c:when>
				
				<c:otherwise>
				<script>
					document.getElementById('findGreenCorp').submit();
				</script> 
				</c:otherwise>
			</c:choose>

		</table>
	</div>

	<div>
		<c:set var="page" value="${(empty param.page)? 1 : param.page}" scope="session" />
		<c:set var="startNum" value="${sessionScope.blockStartNum}" scope="session"/>
		<c:set var="lastNum" value="${sessionScope.blockLastNum}" scope="session"/>
		<c:set var="lastPageNum" value="${sessionScope.lastPageNum }" scope="session"/>
		<c:set var="pageCount" value="${sessionScope.pageCount }" scope="session"/>

		<c:if test="${startNum > 1}">
			<span><a href="FindGreenCorp.do?page=${startNum - pageCount}&field=${pram.field }&keword=${param.keyword}&corpType=${param.corpType}">이전</a> </span>
		</c:if>
		<c:if test="${startNum <= 1}">
			<span onclick="alert('이전 페이지가 없습니다.');">이전</span>
		</c:if>

		<span> 
			<c:forEach var="num" begin="${startNum }" end="${lastNum }">
				<c:if test="${num <= lastPageNum }">
                	<a href="FindGreenCorp.do?page=${num}&field=${pram.field }&keword=${param.keyword}&corpType=${param.corpType}" >${num}</a>
                </c:if>
			</c:forEach>
		</span>

		<c:if test="${(startNum + pageCount -1) < lastPageNum }">
			<span>
				<a href="FindGreenCorp.do?page=${startNum + pageCount}&field=${pram.field }&keword=${param.keyword}&corpType=${param.corpType}">다음</a>
			</span>
		</c:if>
		<c:if test="${(startNum + pageCount -1) >= lastPageNum }">
			<span onclick="alert('다음 페이지가 없습니다.');">다음</span>
		</c:if>
	</div>


	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>

	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript"
		src="<c:url value='/JavaScript/findCorpLoad.js'/>"></script> 
</body>
</html>