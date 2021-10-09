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
	
	<title>CorpCollector : 고객 후기</title>
</head>

<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div width="1200px;" style="text-align:center; margin:5% auto;">
		<div class="sidebar_div" style="float:left;">
			<aside class="sidebar">
				<ul style="list-style-type:none; ">
					<h3>커뮤니티</h3>
					<p><a href="<c:url value='/community/notice.jsp'/>">공지사항</a></p>
					<p><a href="<c:url value='/community/feedback.jsp'/>" style="color:#e1bf27; font-weight:bold;">고객 후기</a></p>
				</ul>
			</aside>
		</div>
		
		<div class="content_div">
			<table width="1000px;" style="text-align:center; float:right; background-color:gray;">
				<tr>
				<table width="900px;" style="text-align: center; margin:0 auto; position:relative;">
					<tr>
						<form align="center">
						<td>
							<input class="search_bar" type="text" id="search_keyword" autocomplete="off" placeholder=" 검색어를 입력하세요">
						</td>
						<td>
							<button class = "search_btn" type=submit value="" onclick="">
							<img src="<c:url value='/images/search_logo.PNG'/>" alt="search" width="55px;">
							</button>
						</td>
					</form>
					</tr>
				</table>
				</tr>
				<tr>
					<table class="content_div_community">
						<tr class="community_text" style="background-color:#eeedeb; height:30px;">
							<td width="80px">번호</td>
							<td width="500px">제목</td>
							<td width="150px">작성자</td>
							<td width="150px">등록일</td>
							<td width="80px">조회수</td>
						</tr>
						<tr class="community_text">
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</table>
				</tr>
			</table>
			<button class="writing_btn" style="margin:0 0 0 92%;" onclick="location.href='${pageContext.request.contextPath}/community/feedback_Write.jsp'">글쓰기</button>
			<!-- 일단 글쓰기 가능하게 설정. 이후 권한에 따라 alert로 글쓰기 막을 것 -->
		</div>
	</div>
	
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	<script type="text/javascript" src="../JavaScript/right_Check.js"></script>
</body>
</html>