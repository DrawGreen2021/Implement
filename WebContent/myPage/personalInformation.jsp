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
	
	<title>CorpCollector : 개인 정보 관리</title>
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
					<p><a href="<c:url value='/myPage/PersonalInfoView.do'/>" style="color:#e1bf27; font-weight:bold;">개인 정보 관리</a></p>
					<p><a href="<c:url value='/myPage/FavoriteCorpView.do?page=1'/>">관심 기업</a></p>
					<p><a href="<c:url value='/myPage/RecentSearchView.do?page=1'/>">최근 검색 기업</a></p>
					<p><a href="<c:url value='/myPage/MyFeedbackView.do?page=1'/>">내가 쓴 글</a></p>
				</ul>
			</aside>
		</div>
		
		<form action="PersonalInfoWriteView.do" method="post">
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
							<td colspan="3">${requestScope.personalInfo['id'] }
								<input type="hidden" name="id" value="${requestScope.personalInfo['id'] }"></td>
						</tr>
						<!-- <tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">비밀번호/PW</td>
							<td colspan="3"></td>
						</tr> -->
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">닉네임</td>
							<td colspan="3">${requestScope.personalInfo['nickname'] }
								<input type="hidden" name="nickname" value="${requestScope.personalInfo['nickname'] }"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">이메일</td>
							<td colspan="3">${requestScope.personalInfo['email'] }
								<input type="hidden" name="email" value="${requestScope.personalInfo['email'] }"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">생년월일</td>
							<td width="350px">${requestScope.personalInfo['birth'] }
								<input type="hidden" name="birth" value="${requestScope.personalInfo['birth'] }"></td>
							<td width="100px" style="background-color:#eeedeb;">성별</td>
							<td width="200px">${requestScope.personalInfo['gender'] }
								<input type="hidden" name="gender" value="${requestScope.personalInfo['gender'] }"></td>
						</tr>
					</table>
					
				</tr>
			</table>
			<div style="float:right;">
			<input type="submit" value="수정" class="writing_btn">
			<%-- <button class="writing_btn" onclick="location.href='${pageContext.request.contextPath}/myPage/UpdatePersonalInfo.do'">수정</button> --%>
			</div>
			
		</div>
		</form>
	</div>
	<br><br><br><br><br>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>

</body>
</html>