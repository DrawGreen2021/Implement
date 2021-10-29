<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="캡스톤_01">
	<meta name="keywords" content="HTML5, CSS, JQUERY">
	
	<link rel="stylesheet" type="text/css" href="../css/main.css?after">
	
	<title>CorpCollector : 개인 정보 수정</title>
</head>

<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div width="1200px;" style="text-align:center; margin:5% auto;">
		<div class="sidebar_div" style="float:left;">
			<aside class="sidebar">
				<ul style="list-style-type:none; ">
					<h3>마이페이지</h3>
					<p><a href="<c:url value='/myPage/personalInformation.jsp'/>" style="color:#e1bf27; font-weight:bold;">개인 정보 관리</a></p>
					<p><a href="<c:url value='/myPage/favoriteCorp.jsp'/>">관심 기업</a></p>
					<p><a href="<c:url value='/myPage/recentSearch.jsp'/>">최근 검색 기업</a></p>
					<p><a href="<c:url value='/myPage/myFeedback.jsp'/>">내가 쓴 글</a></p>
				</ul>
			</aside>
		</div>
		
		<div class="content_div">
			<table width="1000px;" style="text-align:center; float:right;">
				<tr>
					<p style="font-size:16pt; color:#21499b; font-weight:bold; margin:0 83% 0 0; ">개인 정보 관리</p>
				</tr>
				<tr>
					<div style = "border:1px solid #21499b; margin:1% auto;"></div>
				</tr>
				<tr>
					<table class="content_div_write">
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">아이디/ID</td>
							<td colspan="3"><input type="text" autocomplete="off" class="write_input"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">비밀번호/PW</td>
							<td colspan="3"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">닉네임</td>
							<td colspan="3"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">이메일</td>
							<td colspan="3"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">생년월일</td>
							<td width="350px"></td>
							<td width="100px" style="background-color:#eeedeb;">성별</td>
							<td width="200px"></td>
						</tr>
					</table>
				</tr>
			</table>
			<div style="float:right;">
			<button class="writing_btn" onclick="">수정</button>
			</div>
		</div>
	</div>
	<br><br><br><br><br>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	<script type="text/javascript" src="../JavaScript/right_Check.js"></script>
</body>
</html>