<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="캡스톤_01">
	<meta name="keywords" content="HTML5, CSS, JQUERY">
	
	<link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css?ver=<%=System.currentTimeMillis() %>"/>'>
	
	<title>CorpCollector : 기업 데이터 분석</title>
</head>

<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div class="outer_block">
		<div class="sidebar_div" style="float:left;">
			<aside class="sidebar">
				<ul style="list-style-type:none; ">
					<h3>정보 나눔</h3>
					<p><a href="<c:url value='/information/corp_Analysis.jsp'/>" style="color:#e1bf27; font-weight:bold;">기업 데이터 분석</a></p>
					<p><a href="<c:url value='/information/CorpNewsView.do?page=1'/>">기업 기사 모음</a></p>
				</ul>
			</aside>
		</div>
		
		<div class="content_div" style="text-align:left;">
			<div width="930px" height="100%">
				<h2 style="color:#180E5A;  margin-bottom:5%;">기업 데이터 분석</h2>
				
				<!--  <div width="930px;"style="margin:1% auto; text-align:center;" >
					<a href="#FFcorp" class="information_move_text">가족 친화 기업</a>
					<a href="#Scorp" class="information_move_text" >사회적 기업</a>
					<a href="#YFcorp" class="information_move_text">청년 친화 강소 기업</a>
					<a href="#TDcorp" class="information_move_text">인재 육성형 중소 기업</a>
					<a href="#Gcorp" class="information_move_text">녹색 기업</a>
				</div>
				<div style = "border:1px solid #eeedeb;" width="500px;"></div> -->
				
				
				<table width="930px;" style="margin:0 auto; border:0;" >
					<tr>
					<td><button class="information_move_button" onclick="location.href='#FFcorp'">가족 친화 기업</button></td>
					<td><button class="information_move_button" onclick="location.href='#Scorp'">사회적 기업</button></td>
					<td><button class="information_move_button" onclick="location.href='#YFcorp'">청년 친화 강소 기업</button></td>
					<td><button class="information_move_button" onclick="location.href='#TDcorp'">인재 육성형 중소 기업</button></td>
					<td><button class="information_move_button" onclick="location.href='#Gcorp'">녹색 기업</button></td>
					</tr>
				</table> 
				
				<p class="information_text" style="font-size:10pt; margin:4% 0 11% 0;" id="FFcorp">
				그래프 설명: 색상은 업종 종류를 나타냅니다. 그래프의 크기는 업종 분포에 따라 달라집니다.</p>
				
				
				
				<p class="information_text" style="font-weight:bold; margin-top:5%;">- 가족 친화 인증 기업 데이터 시각화</p>
				<div class='tableauPlaceholder' id='viz1636087981062' style='position: relative'>
					<noscript><a href='#'>
					<img alt='대시보드 2 ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;_9&#47;_930x800&#47;2_1&#47;1_rss.png' style='border: none' />
					</a></noscript>
					<object class='tableauViz'  style='display:none;'>
						<param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> 
						<param name='embed_code_version' value='3' /> 
						<param name='site_root' value='' />
						<param name='name' value='_930x800&#47;2_1' />
						<param name='tabs' value='no' />
						<param name='toolbar' value='yes' />
						<param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;_9&#47;_930x800&#47;2_1&#47;1.png' /> 
						<param name='animate_transition' value='yes' />
						<param name='display_static_image' value='yes' />
						<param name='display_spinner' value='yes' />
						<param name='display_overlay' value='yes' />
						<param name='display_count' value='yes' />
						<param name='language' value='ko-KR' />
					</object>
				</div>                
				<script type='text/javascript'>                   
					var divElement = document.getElementById('viz1636087981062');                    
					var vizElement = divElement.getElementsByTagName('object')[0];                    
					if ( divElement.offsetWidth > 800 ) { 
						vizElement.style.width='930px';vizElement.style.height='827px';} 
					else if ( divElement.offsetWidth > 500 ) {
						vizElement.style.width='930px';vizElement.style.height='827px';} 
					else { 
						vizElement.style.width='100%';vizElement.style.height='1227px';}                     
					var scriptElement = document.createElement('script');                    
					scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                   
					vizElement.parentNode.insertBefore(scriptElement, vizElement);                
				</script>
				
				<p class="information_text" style="font-size:10pt; margin:5% 0 18% 0;">
					*&nbsp;1. 색상이 업종 종류를 나타냅니다. 그래프의 크기는 업종 분포에 따라 달라집니다. <br>
					  &nbsp;&nbsp;2. 해당 카테고리의 총 선정 업체 수를 나타냅니다. <br>
					  &nbsp;&nbsp;3. 지역별 해당 업종 분포를 나타냅니다. 색상은 업종 종류를 나타냅니다. <br>
					  &nbsp;&nbsp;4. 지역별 선정 업체를 숫자로 나타냅니다.
				</p>
				<div style = "border:1px solid #eeedeb;" width="500px;"></div>
				
				
				<p class="information_text" style="font-weight:bold; margin-top:5%;" id="Scorp">- 사회적 기업 데이터 시각화</p>
				<div class='tableauPlaceholder' id='viz1636088077209' style='position: relative'>
					<noscript><a href='#'>
					<img alt='대시보드 2 ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;__930x800&#47;2_1&#47;1_rss.png' style='border: none' />
					</a></noscript>
					<object class='tableauViz'  style='display:none;'>
						<param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> 
						<param name='embed_code_version' value='3' /> 
						<param name='site_root' value='' />
						<param name='name' value='__930x800&#47;2_1' />
						<param name='tabs' value='no' />
						<param name='toolbar' value='yes' />
						<param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;__930x800&#47;2_1&#47;1.png' /> 
						<param name='animate_transition' value='yes' />
						<param name='display_static_image' value='yes' />
						<param name='display_spinner' value='yes' />
						<param name='display_overlay' value='yes' />
						<param name='display_count' value='yes' />
						<param name='language' value='ko-KR' />
					</object>
				</div>                
				<script type='text/javascript'>                    
					var divElement = document.getElementById('viz1636088077209');                   
					var vizElement = divElement.getElementsByTagName('object')[0];                    
					if ( divElement.offsetWidth > 800 ) { 
						vizElement.style.width='930px';vizElement.style.height='827px';} 
					else if ( divElement.offsetWidth > 500 ) { 
						vizElement.style.width='930px';vizElement.style.height='827px';} 
					else { 
						vizElement.style.width='100%';vizElement.style.height='1227px';}                     
					var scriptElement = document.createElement('script');                    
					scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    
					vizElement.parentNode.insertBefore(scriptElement, vizElement);                
				</script>
				
				<p class="information_text" style="font-size:10pt; margin:5% 0 18% 0;">
					*&nbsp;1. 색상이 업종 종류를 나타냅니다. 그래프의 크기는 업종 분포에 따라 달라집니다. <br>
					  &nbsp;&nbsp;2. 해당 카테고리의 총 선정 업체 수를 나타냅니다. <br>
					  &nbsp;&nbsp;3. 지역별 해당 업종 분포를 나타냅니다. 색상은 업종 종류를 나타냅니다. <br>
					  &nbsp;&nbsp;4. 지역별 선정 업체를 숫자로 나타냅니다.
				</p>
				<div style = "border:1px solid #eeedeb;" width="500px;"></div>
			
			
				<p class="information_text" style="font-weight:bold; margin-top:5%;" id="YFcorp">- 청년 친화 강소 기업 데이터 시각화</p>
				<div class='tableauPlaceholder' id='viz1636088119924' style='position: relative'>
					<noscript><a href='#'>
					<img alt='대시보드 2 ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;_9&#47;_930x800_16360223486110&#47;2_1&#47;1_rss.png' style='border: none' />
					</a></noscript>
					<object class='tableauViz'  style='display:none;'>
						<param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> 
						<param name='embed_code_version' value='3' /> 
						<param name='site_root' value='' />
						<param name='name' value='_930x800_16360223486110&#47;2_1' />
						<param name='tabs' value='no' />
						<param name='toolbar' value='yes' />
						<param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;_9&#47;_930x800_16360223486110&#47;2_1&#47;1.png' /> 
						<param name='animate_transition' value='yes' />
						<param name='display_static_image' value='yes' />
						<param name='display_spinner' value='yes' />
						<param name='display_overlay' value='yes' />
						<param name='display_count' value='yes' />
						<param name='language' value='ko-KR' />
					</object>
				</div>                
				<script type='text/javascript'>                    
					var divElement = document.getElementById('viz1636088119924');                    
					var vizElement = divElement.getElementsByTagName('object')[0];                   
					if ( divElement.offsetWidth > 800 ) { 
						vizElement.style.width='930px';vizElement.style.height='827px';} 
					else if ( divElement.offsetWidth > 500 ) { 
						vizElement.style.width='930px';vizElement.style.height='827px';} 
					else { 
						vizElement.style.width='100%';vizElement.style.height='1227px';}                     
					var scriptElement = document.createElement('script');                    
					scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    
					vizElement.parentNode.insertBefore(scriptElement, vizElement);                
				</script>
				
				<p class="information_text" style="font-size:10pt; margin:5% 0 18% 0;">
					*&nbsp;1. 색상이 업종 종류를 나타냅니다. 그래프의 크기는 업종 분포에 따라 달라집니다. <br>
					  &nbsp;&nbsp;2. 해당 카테고리의 총 선정 업체 수를 나타냅니다. <br>
					  &nbsp;&nbsp;3. 지역별 해당 업종 분포를 나타냅니다. 색상은 업종 종류를 나타냅니다. <br>
					  &nbsp;&nbsp;4. 지역별 선정 업체를 숫자로 나타냅니다.
				</p>
				<div style = "border:1px solid #eeedeb;" width="500px;"></div>
			
			
				<p class="information_text" style="font-weight:bold; margin-top:5%;" id="TDcorp">- 인재 육성형 중소 기업 데이터 시각화</p>
				<div class='tableauPlaceholder' id='viz1636088033154' style='position: relative'>
					<noscript><a href='#'>
					<img alt='대시보드 1 ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;_9&#47;_930x800_16360222688770&#47;1_1&#47;1_rss.png' style='border: none' />
					</a></noscript>
					<object class='tableauViz'  style='display:none;'>
						<param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> 
						<param name='embed_code_version' value='3' /> 
						<param name='site_root' value='' />
						<param name='name' value='_930x800_16360222688770&#47;1_1' />
						<param name='tabs' value='no' />
						<param name='toolbar' value='yes' />
						<param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;_9&#47;_930x800_16360222688770&#47;1_1&#47;1.png' /> 
						<param name='animate_transition' value='yes' />
						<param name='display_static_image' value='yes' />
						<param name='display_spinner' value='yes' />
						<param name='display_overlay' value='yes' />
						<param name='display_count' value='yes' />
						<param name='language' value='ko-KR' />
					</object>
				</div>                
				<script type='text/javascript'>                    
					var divElement = document.getElementById('viz1636088033154');                    
					var vizElement = divElement.getElementsByTagName('object')[0];                    
					if ( divElement.offsetWidth > 800 ) { 
						vizElement.style.width='930px';vizElement.style.height='827px';} 
					else if ( divElement.offsetWidth > 500 ) { 
						vizElement.style.width='930px';vizElement.style.height='827px';} 
					else {
						vizElement.style.width='100%';vizElement.style.height='877px';}                    
					var scriptElement = document.createElement('script');                    
					scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                   
					vizElement.parentNode.insertBefore(scriptElement, vizElement);                
				</script>
				
				<p class="information_text" style="font-size:10pt; margin:5% 0 18% 0;">
					*&nbsp;1. 선정 업체를 지역별로 나타냅니다. <br>
					  &nbsp;&nbsp;2. 업체별 주생산품을 숫자로 나타냅니다. <br>
					  &nbsp;&nbsp;3. 해당 카테고리의 총 선정 업체 수를 나타냅니다.
				</p>
				<div style = "border:1px solid #eeedeb;" width="500px;"></div>
			
			
				<p class="information_text" style="font-weight:bold; margin-top:5%;" id="Gcorp">- 녹색 기업 데이터 시각화</p>
				<div class='tableauPlaceholder' id='viz1636088011312' style='position: relative'>
					<noscript><a href='#'>
					<img alt='대시보드 2 ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;_9&#47;_930x800_16360222031960&#47;2_1&#47;1_rss.png' style='border: none' />
					</a></noscript>
					<object class='tableauViz'  style='display:none;'>
						<param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> 
						<param name='embed_code_version' value='3' /> 
						<param name='site_root' value='' />
						<param name='name' value='_930x800_16360222031960&#47;2_1' />
						<param name='tabs' value='no' />
						<param name='toolbar' value='yes' />
						<param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;_9&#47;_930x800_16360222031960&#47;2_1&#47;1.png' /> 
						<param name='animate_transition' value='yes' />
						<param name='display_static_image' value='yes' />
						<param name='display_spinner' value='yes' />
						<param name='display_overlay' value='yes' />
						<param name='display_count' value='yes' />
						<param name='language' value='ko-KR' />
					</object>
				</div>                
				<script type='text/javascript'>                    
					var divElement = document.getElementById('viz1636088011312');                   
					var vizElement = divElement.getElementsByTagName('object')[0];                    
					if ( divElement.offsetWidth > 800 ) {
						vizElement.style.width='930px';vizElement.style.height='827px';} 
					else if ( divElement.offsetWidth > 500 ) { 
						vizElement.style.width='930px';vizElement.style.height='827px';} 
					else { 
						vizElement.style.width='100%';vizElement.style.height='1227px';}                     
					var scriptElement = document.createElement('script');                    
					scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                   
					vizElement.parentNode.insertBefore(scriptElement, vizElement);                
				</script>
				
				<p class="information_text" style="font-size:10pt; margin:5% 0 18% 0;">
					*&nbsp;1. 색상이 업종 종류를 나타냅니다. 그래프의 크기는 업종 분포에 따라 달라집니다. <br>
					  &nbsp;&nbsp;2. 해당 카테고리의 총 선정 업체 수를 나타냅니다. <br>
					  &nbsp;&nbsp;3. 지역별 해당 업종 분포를 나타냅니다. 색상은 업종 종류를 나타냅니다. <br>
					  &nbsp;&nbsp;4. 지역별 선정 업체를 숫자로 나타냅니다.
				</p>
				<div style = "border:1px solid #eeedeb;" width="500px;"></div>
				
			</div>
		</div>
		
		
	</div>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
</body>
</html>