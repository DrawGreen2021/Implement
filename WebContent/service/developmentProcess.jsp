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
	<link rel="stylesheet" type="text/css" href="../css/popup.css?after">
	<title>CorpCollector : 개발 과정</title>
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
					<p><a href="<c:url value='/service/service_Summary.jsp'/>">서비스 개요</a></p>
					<p><a href="<c:url value='/service/DevProcessView.do'/>" style="color:#e1bf27; font-weight:bold;">개발 과정</a></p>
				</ul>
			</aside>
		</div>
		
		<div class="content_div" style="text-align:left;">
			<div style="width: 930px;">
				<h1 style="font-size:50px; color:#180E5A;">TEAM 그릴그린</h1>
				<br><br> <c:set var="colCount" value="3"></c:set>
				
				<h3>프론트 엔드 담당 : 천세륜</h3>
				<p>사이트 맵에 따른 웹사이트 구성과 배치, 제작을 수행했다.</p>
				<br>
				<c:set var="UIdesign" value="../images/thumbnails/UI images/image"></c:set>
				<table style="margin-bottom:10%;">
					<c:forEach var="i" begin="1" end="${requestScope.UIdesignImgCount / colCount }" varStatus="status">
						<tr>
							<c:forEach var="j" begin="${1 + (status.current-1) * colCount }" end="${status.current * colCount }">
								<td>
									<a onclick="openLayer('../images/UI images/image${j}.PNG')" class="thumbnail">
										<img alt="UI${j }" src="${UIdesign }${j}.PNG">
									</a>
								</td>
							</c:forEach>
						</tr>
					</c:forEach>	
				</table>
				
				<h3>백엔드 담당 : 오혜진</h3>
				<p> 웹 사이트 기능에 따른 알고리즘을 플로우 차트로 나타내고 데이터베이스를 다루며 프론트 엔드와 빅데이터 처리 사이 통합 과정을 총괄했다.</p>
				<br>
				<c:set var="flowChart" value="../images/thumbnails/flow charts/image"></c:set>
				<table  style="margin-bottom:10%;">
					<c:forEach var="i" begin="1" end="${requestScope.FlowChartImgCount / colCount }" varStatus="status">
						<tr>
							<c:forEach var="j" begin="${1 + (status.current-1) * colCount }" end="${status.current * colCount }">
								<td>
									<a onclick="openLayer('../images/flow charts/image${j}.png')" class="thumbnail">
										<img alt="flow charts${j }" src="${flowChart }${j}.png">
									</a>
								</td>
							</c:forEach>
						</tr>
					</c:forEach>	
				</table>
				
				
				<h3>빅데이터 가공 및 분석 담당 : 오규진</h3>
				<p>기업 데이터의 전처리와 데이터베이스 적재, 분석을 행했다.</p>
				<br>
				<span>
					<a onclick="openLayer('../images/database_design.png')" class="thumbnail"> 
						<img alt="데이터베이스 설계" 
						src="<c:url value='/images/thumbnails/database_design.png' />">
					</a>
				</span>

			</div>
		</div>
	</div>

	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 레이어 팝업 -->
	<div id="popup_mask"></div>
	<div id="popupDiv"
		style="display: none; border: 3px solid rgb(204, 204, 204);"
		onclick="closeLayer()">
	</div>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/thumbnailClickFunc.js?ver=<%=System.currentTimeMillis() %>"></script>

</body>
</html>