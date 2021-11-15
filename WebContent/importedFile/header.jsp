<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value='/JavaScript/logoutCheck.js'/>"/></script>
	<c:choose>
		<c:when test="${sessionScope.MemberDTO!=null }">
			<div>
			<!-- 헤더 영역-->
			<header class="header">
			
			<!-- 네비게이션(메뉴) 영역-->
			<nav class="nav">
				<a href="<c:url value='/index.jsp'/>" style="float:left;"><img src="<c:url value='/images/logo2.PNG'/>" alt="CorpCollector" class="nav_logo"></a>
				<a href="<c:url value='/myPage/PersonalInfoView.do'/>" style="margin-left : 57%; float:left;">${sessionScope.MemberDTO.name}님</a> <!-- 마이페이지 -->
				<form name="passBeforeUrl">
					<input type="hidden" name="beforeUrl">
					<input type="hidden" name="URI" value="${pageContext.request.requestURI }">
					<input type="hidden" name="contextPath" value="${pageContext.request.contextPath }">
				</form>
				<a href="javascript:logoutDo()">로그아웃</a>
			</nav>
			
			<!-- 
			<table style="text-align:center; width:1200px;">
				<tr>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/service/service_Summary.jsp'">서비스 소개</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/findCorp/findCorp_main.jsp'">기업 찾기</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/information/corp_Analysis.jsp'">정보 나눔</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/community/notice.jsp'">커뮤니티</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/myPage/personalInformation.jsp'">마이페이지</button></td>
				</tr>
			</table> -->
			<!-- https://kuzuro.blogspot.com/2018/08/htmlcss.html 드롭다운 메뉴  -->
			
			<div style="width:1200px; ">
				<ul class="my_Menu">
					<li>
						<a href="<c:url value='/service/service_Summary.jsp'/>">서비스 소개</a>
						<ul class="sub_Menu">
							<li><a href="<c:url value='/service/service_Summary.jsp'/>">서비스 개요</a></li>
							<li><a href="<c:url value='/service/developmentProcess.jsp'/>">개발 과정</a></li>
						</ul>
					</li>
					<li>
						<a href="<c:url value='/findCorp/findCorp_main.jsp'/>">기업 찾기</a>
						<ul class="sub_Menu">
							<li><a href="<c:url value='/findCorp/talentDevelopmentCorp.jsp'/>">인재 육성형 중소 기업</a></li>
							<li><a href="<c:url value='/findCorp/greenCorp.jsp'/>">녹색 기업</a></li>
							<li><a href="<c:url value='/findCorp/socialCorp.jsp'/>">사회적 기업</a></li>
							<li><a href="<c:url value='/findCorp/familyFriendlyCorp.jsp'/>">가족 친화 기업</a></li>
							<li><a href="<c:url value='/findCorp/youthFriendlyCorp.jsp'/>">청년 친화 강소 기업</a></li>
						</ul>
					</li>
					<li>
						<a href="<c:url value='/information/corp_Analysis.jsp'/>">정보 나눔</a>
						<ul class="sub_Menu">
							<li><a href="<c:url value='/information/corp_Analysis.jsp'/>">기업 데이터 분석</a></li>
							<li><a href="<c:url value='/information/CorpNewsView.do?page=1'/>">기업 기사 모음</a></li>
						</ul>
					</li>
					<li>
						<a href="<c:url value='/community/notice.jsp'/>">커뮤니티</a>
						<ul class="sub_Menu">
							<li><a href="<c:url value='/community/notice.jsp'/>">공지사항</a></li>
							<li><a href="<c:url value='/community/feedback.jsp'/>">고객 후기</a></li>
						</ul>
					</li>
					<li style="float:right;">
						<a href="<c:url value='/myPage/PersonalInfoView.do'/>">마이페이지</a>
						<ul class="sub_Menu" style="float:right;">
							<li><a href="<c:url value='/myPage/PersonalInfoView.do'/>">개인 정보 관리</a></li>
							<li><a href="<c:url value='/myPage/FavoriteCorpView.do?page=1'/>">관심 기업</a></li>
							<li><a href="<c:url value='/myPage/RecentSearchView.do?page=1'/>">최근 검색 기업</a></li>
							<li><a href="<c:url value='/myPage/MyFeedbackView.do?page=1'/>">내가 쓴 글</a></li>
						</ul>
					</li>
				</ul>
			</div>
			
			</header>
			</div>	
		</c:when>
		
		
		<c:otherwise>
			<div>
			<!-- 헤더 영역-->
			<header class="header">
			
			<!-- 네비게이션(메뉴) 영역-->
			<nav class="nav">
				<a href="<c:url value='/index.jsp'/>" style="float:left;"><img src="<c:url value='/images/logo2.PNG'/>" alt="CorpCollector" class="nav_logo"></a>
				<a href="<c:url value='/member/login_main.jsp'/>" style="margin-left : 58%; float:left;">로그인</a>
				<a href="<c:url value='/member/signUp.jsp'/>">회원가입</a>
			</nav>
			
			<!-- 
			<table style="text-align:center; width:1200px;">
				<tr>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/service/service_Summary.jsp'">서비스 소개</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/findCorp/findCorp_main.jsp'">기업 찾기</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/information/corp_Analysis.jsp'">정보 나눔</button></td>
					<td><button class="menu_btn" onclick="location.href='${pageContext.request.contextPath}/community/notice.jsp'">커뮤니티</button></td>
					<td><button class="menu_btn" onclick="alert('로그인 후 이용 가능합니다')">마이페이지</button></td>
				</tr>
			</table>  -->
			
			<div style="width:1200px; ">
				<ul class="my_Menu">
					<li>
						<a href="<c:url value='/service/service_Summary.jsp'/>">서비스 소개</a>
						<ul class="sub_Menu">
							<li><a href="<c:url value='/service/service_Summary.jsp'/>">서비스 개요</a></li>
							<li><a href="<c:url value='/service/developmentProcess.jsp'/>">개발 과정</a></li>
						</ul>
					</li>
					<li>
						<a href="<c:url value='/findCorp/findCorp_main.jsp'/>">기업 찾기</a>
						<ul class="sub_Menu">
							<li><a href="<c:url value='/findCorp/talentDevelopmentCorp.jsp'/>">인재 육성형 중소 기업</a></li>
							<li><a href="<c:url value='/findCorp/greenCorp.jsp'/>">녹색 기업</a></li>
							<li><a href="<c:url value='/findCorp/socialCorp.jsp'/>">사회적 기업</a></li>
							<li><a href="<c:url value='/findCorp/familyFriendlyCorp.jsp'/>">가족 친화 기업</a></li>
							<li><a href="<c:url value='/findCorp/youthFriendlyCorp.jsp'/>">청년 친화 강소 기업</a></li>
						</ul>
					</li>
					<li>
						<a href="<c:url value='/information/corp_Analysis.jsp'/>">정보 나눔</a>
						<ul class="sub_Menu">
							<li><a href="<c:url value='/information/corp_Analysis.jsp'/>">기업 데이터 분석</a></li>
							<li><a href="<c:url value='/information/CorpNewsView.do?page=1'/>">기업 기사 모음</a></li>
						</ul>
					</li>
					<li>
						<a href="<c:url value='/community/notice.jsp'/>">커뮤니티</a>
						<ul class="sub_Menu">
							<li><a href="<c:url value='/community/notice.jsp'/>">공지사항</a></li>
							<li><a href="<c:url value='/community/feedback.jsp'/>">고객 후기</a></li>
						</ul>
					</li>
					<li>
						<a href=""  onclick="alert('로그인 후 이용 가능합니다')">마이페이지</a>
						<ul class="sub_Menu" float="right">
							<li><a href="" onclick="alert('로그인 후 이용 가능합니다')">개인 정보 관리</a></li>
							<li><a href="" onclick="alert('로그인 후 이용 가능합니다')">관심 기업</a></li>
							<li><a href="" onclick="alert('로그인 후 이용 가능합니다')">최근 검색 기업</a></li>
							<li><a href="" onclick="alert('로그인 후 이용 가능합니다')">내가 쓴 글</a></li>
						</ul>
					</li>
				</ul>
			</div>
			
			</header>
			</div>
		</c:otherwise>
	</c:choose>