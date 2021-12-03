<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="캡스톤_01">
	<meta name="keywords" content="HTML5, CSS, JQUERY">
	
	<link rel="stylesheet" type="text/css" href="../css/main.css?ver=<%=System.currentTimeMillis() %>">
	
	<title>CorpCollector : 개인 정보 수정</title>
</head>

<body>
	<div id="container">
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div class="outer_block" >
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
		
		<form action="UpdatePersonalInfo.do" method="post">
		<div class="content_div" >
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
							<td width="150px" style="background-color:#eeedeb;">아이디/ID</td>
							<td style="text-align:left; padding-left:12px;"><input type="text" autocomplete="off" class="write_input" 
								name="id" id="id" value="${requestScope.personalInfo['id'] }" style="width: 580px;"></td>
							<td style="width: 200px; text-align:right;"><input type="submit" id="idCheckBtn" value="아이디 중복 확인" class="writing_btn" style="width:120px; background-color:#E7F1FD;">
								<input type="hidden" name="authId" id="authID" value="not-changed"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">비밀번호/PW</td>
							<td colspan="3" style="text-align:left; padding-left:12px;"><input type="password" name="pw" id="pw" autocomplete="off" class="write_input"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">비밀번호/PW 확인</td>
							<td colspan="3" style="text-align:left; padding-left:12px;"><input type="password" name="pw_chk" id="pw_chk" autocomplete="off" class="write_input"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">닉네임</td>
							<td colspan="3" style="text-align:left; padding-left:12px;"><input type="text" name="name" autocomplete="off" class="write_input"
								id="name" value="${requestScope.personalInfo['nickname'] }"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">이메일</td>
							<td style="text-align:left; padding-left:12px;"><input type="text" name="email" id="email" autocomplete="off" class="write_input"
								value="${requestScope.personalInfo['email'] }" style="width: 580px; margin-bottom: 2px;"><br>
								<span style="font-size: 9pt; color: gray; margin-top: 10px;">* 이메일이 도착하지 않았다면 스팸 메일함을 확인해주세요.</span></td>
							<td style="width: 150px; text-align:right;"><input type = "submit" id="emailSendBtn" value="인증번호 받기" class="writing_btn" style="width: 120px; background-color: #E7F1FD;"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">인증코드</td>
							<td style="text-align:left; padding-left:12px;"><input type="text" name="email_auth_code" autocomplete="off" class="write_input"
								style="width: 580px;" id="email_auth_code"></td>
							<td style="width: 200px; text-align:right;"><input type = "submit" id="emailCheckBtn" value="인증코드 확인" class="writing_btn" style="width: 120px; background-color: #E7F1FD;">
								<input type="hidden" name="authEmail" id="authEmail" value="false"></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">생년월일</td>
							<td colspan="3" style="text-align:left; padding-left:12px;"><input type="number" name="year" id="year"
								value="${requestScope.personalInfo['birth_year'] }"
								style="width: 100px; height: 20px; margin: 0 auto;"> 
								<select id="month" name="month" style="height:25px;">
									<option value="">월</option>
									<option value="01" ${(requestScope.personalInfo['birth_month']==1)?'selected':'' }>01</option>
									<option value="02" ${(requestScope.personalInfo['birth_month']==2)?'selected':'' }>02</option>
									<option value="03" ${(requestScope.personalInfo['birth_month']==3)?'selected':'' }>03</option>
									<option value="04" ${(requestScope.personalInfo['birth_month']==4)?'selected':'' }>04</option>
									<option value="05" ${(requestScope.personalInfo['birth_month']==5)?'selected':'' }>05</option>
									<option value="06" ${(requestScope.personalInfo['birth_month']==6)?'selected':'' }>06</option>
									<option value="07" ${(requestScope.personalInfo['birth_month']==7)?'selected':'' }>07</option>
									<option value="08" ${(requestScope.personalInfo['birth_month']==8)?'selected':'' }>08</option>
									<option value="09" ${(requestScope.personalInfo['birth_month']==9)?'selected':'' }>09</option>
									<option value="10" ${(requestScope.personalInfo['birth_month']==10)?'selected':'' }>10</option>
									<option value="11" ${(requestScope.personalInfo['birth_month']==11)?'selected':'' }>11</option>
									<option value="12" ${(requestScope.personalInfo['birth_month']==12)?'selected':'' }>12</option>
								</select>
								<c:choose>
									<c:when test="${requestScope.personalInfo['birth_day'] < 10 }">
										<input type="number" name="day" id="day" value="0${requestScope.personalInfo['birth_day'] }"
										style="width: 60px; height: 20px; margin: 0 auto;">
									</c:when>
									<c:otherwise>
										<input type="number" name="day" id="day" value="${requestScope.personalInfo['birth_day'] }"
										style="width: 60px; height: 20px; margin: 0 auto;">
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color: #eeedeb;">성별</td>
							<td colspan="3" style="text-align:left; padding-left:12px;">
								<select id="gender" name="gender" style="height:25px;">
									<option value="">성별</option>
									<option value="man" ${(requestScope.personalInfo['gender']=='man')?'selected':'' }>남</option>
									<option value="woman" ${(requestScope.personalInfo['gender']=='woman')?'selected':'' }>여</option>
									<option value="none" ${(requestScope.personalInfo['gender']=='none')?'selected':'' }>선택 안 함</option>
								</select>
							</td>
						</tr>
					</table>
				</tr>
			</table>
			<div style="float:right;">
				<input type="submit" class="writing_btn" id="updatePersonalInfoBtn" value="수정">
			</div>
		</div>
		</form>
	</div>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	</div>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="<c:url value='/JavaScript/updatePersonalInfo_check.js?ver=<%=System.currentTimeMillis() %>'/>"/></script>
</body>
</html>