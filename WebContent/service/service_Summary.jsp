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
	
	<title>CorpCollector : 서비스 개요</title>
</head>

<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div class="outer_block">
		<div class="sidebar_div" style="float:left;">
			<aside class="sidebar">
				<ul style="list-style-type:none; ">
					<h3>서비스 소개</h3>
					<p><a href="<c:url value='/service/service_Summary.jsp'/>" style="color:#e1bf27; font-weight:bold;">서비스 개요</a></p>
					<p><a href="<c:url value='/service/DevProcessView.do'/>">개발 과정</a></p>
				</ul>
			</aside>
		</div>
		
		<div class="content_div" style="text-align:left;">
			<div width="930px">
				<h1 style="font-size:40px; color:#180E5A;">팀 그릴그린에서 기업 정보 웹 서비스, <br>“CorpCollector”를 제작했습니다</h1>
				
				<p style="margin:10% 0 12% 0;"> CorpCollector는 단순히 기업 목록을 제공하는 서비스가 아니라, 선한 기업, 즉 사회적으로 유익한 활동을 하는 기업의 정보를 보여줍니다. 
				이러한 서비스를 기획한 배경에는 최근 소비자들의 소비 행위가 있습니다. 특정 기업들의 논란으로 인해, 소비자들의 
				불매 행위가 불거지면서 해당 기업들의 매출이 하락하는 증세를 보였습니다. 이런 양상에는 부도덕한 행위
				를 한 기업 제품보다는 다른 기업 제품을 소비하자는 의견이 있었기 때문입니다. 따라서 선한 기업 목록
				을 쉽게 접근 가능한 웹 사이트에서 제공하여 사람들의 이로운 소비 행위 및 기업의 사회 환원을 장려
				하고자 합니다.</p>
				
				<h3>서비스 소개</h3>
				<p style="margin-bottom:9%;">: 어떤 서비스를 제공하는 사이트인지 설명하는 페이지입니다. <br><br>
					서비스 개요 - 서비스 제작 배경 및 간략한 설명이 적혀있습니다. <br>
					개발 과정 - 팀 그릴그린 팀원들의 역할별 개발 과정을 보여줍니다.</p>

				<h3>기업 찾기</h3>
				<p style="margin-bottom:9%;">: 기업 정보를 검색할 수 있는 페이지입니다. <br><br>
					'기업 찾기' 클릭 시 - 통합 검색 창으로 이동합니다. <br>
					녹색 기업, 인재 육성형 중소 기업, 사회적 기업, 가족 친화 기업, 청년 친화 강소 기업 - 각 유형별 기업 목록 보기와 검색 서비스를 이용할 수 있습니다. <br>
					기업명을 클릭하면 상세 기업 정보를 볼 수 있습니다. </p>

				<h3>정보 나눔</h3>
				<p style="margin-bottom:9%;">: 기업 데이터를 분석하고 기업 찾기에 있는 각 기업 유형별 관련 뉴스를 제공하는 페이지입니다. <br><br>
					기업 데이터 분석 - 공공데이터포털에서 제공하는 기업 데이터를 분석해 결과를 시각화하여 보여줍니다. <br>
					기업 기사 모음 - 공공데이터에 나오지 않은 정보도 볼 수 있도록 유형별 기사를 수집해 제목과 링크를 모아놓았습니다.</p>
				
				<h3>커뮤니티</h3>
				<p style="margin-bottom:9%;">: 공지사항 업로드 및 사용자와 소통하기 위한 페이지입니다. <br><br>
					공지사항 - 서비스 관련 공지사항을 볼 수 있습니다. <br>
					고객후기 - CorpCollector 사용 후기, 건의 사항, 문의 등을 남길 수 있습니다.</p>
				
				<h3>마이페이지</h3>
				<p>: 회원가입을 한 사용자에게 제공되는 페이지입니다. <br><br>
					개인정보 관리 - 개인정보를 수정할 수 있습니다. 회원 탈퇴는 이 페이지에서 가능합니다. <br>
					최근 검색 기업 - 최근에 기업의 상세 정보를 조회한 기록을 볼 수 있습니다. <br>
					관심 기업 - 관심 기업으로 등록한 기업 목록을 볼 수 있습니다. <br>
					내가 쓴 글 - 고객후기 게시판에 작성한 글을 보고 관리할 수 있습니다. </p>
					
				문제가 있으면 아래 메일로 연락주세요. <br>
				관리자 이메일: drawgreen@corpcollector.or.kr
			</div>
		</div>
	</div>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
</body>
</html>