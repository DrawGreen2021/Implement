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
	
	<title>CorpCollector : 기업 데이터 분석</title>
</head>

<body>
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div width="1200px;" style="text-align:center; margin:5% auto;">
		<div class="sidebar_div" style="float:left;">
			<aside class="sidebar">
				<ul style="list-style-type:none; ">
					<h3>서비스 소개</h3>
					<p><a href="<c:url value='/information/corp_Analysis.jsp'/>" style="color:#e1bf27; font-weight:bold;">기업 데이터 분석</a></p>
					<p><a href="<c:url value='/information/corp_News.jsp'/>">기업 기사 모음</a></p>
				</ul>
			</aside>
		</div>
		
		<div class="content_div" style="text-align:left;">
			<div width="930px" height="100%">
				<h2 style="color:#180E5A;">기업 데이터 분석</h2>
				<br>
				<p class="information_text" style="font-weight:bold;">- 가족 친화 인증 기업 데이터 시각화</p>
				<div class='tableauPlaceholder' id='viz1635245619566' style='position: relative;' width="930px;" margin="5% auto;">
					<noscript><a href='#'>
					<img alt='대시보드 2 ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;____16348770981730&#47;2_1&#47;1_rss.png' style='border: none'  />
					</a></noscript>
					<object class='tableauViz'  style='display:none;' >
						<param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> 
						<param name='embed_code_version' value='3' /> 
						<param name='site_root' value='' />
						<param name='name' value='____16348770981730&#47;2_1' />
						<param name='tabs' value='no' /><param name='toolbar' value='yes' />
						<param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;____16348770981730&#47;2_1&#47;1.png' /> 
						<param name='animate_transition' value='yes' />
						<param name='display_static_image' value='yes' />
						<param name='display_spinner' value='yes' />
						<param name='display_overlay' value='yes' />
						<param name='display_count' value='yes' />
						<param name='language' value='ko-KR' />
					</object>
				</div>
				<script type='text/javascript'>
					var divElement = document.getElementById('viz1635245619566');
					var vizElement = divElement.getElementsByTagName('object')[0];                    
					if ( divElement.offsetWidth > 800 ) { 
						vizElement.style.width='1000px';vizElement.style.height='827px';} 
					else if ( divElement.offsetWidth > 500 ) { 
						vizElement.style.width='1000px';vizElement.style.height='827px';} 
					else { 
						vizElement.style.width='100%';vizElement.style.height='1227px';} 
					
					var scriptElement = document.createElement('script');                    
					scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    
					vizElement.parentNode.insertBefore(scriptElement, vizElement);                
				</script>
			
				<br><br><br><br><br><br><br><br>
				
				
				<p class="information_text" style="font-weight:bold;">- 사회적 기업 데이터 시각화</p>
				<div class='tableauPlaceholder' id='viz1635245912646' style='position: relative'>
					<noscript><a href='#'>
					<img alt='대시보드 2 ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;____16348768462840&#47;2_1&#47;1_rss.png' style='border: none' />
					</a></noscript>
					<object class='tableauViz'  style='display:none;'>
						<param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> 
						<param name='embed_code_version' value='3' /> <param name='site_root' value='' />
						<param name='name' value='____16348768462840&#47;2_1' />
						<param name='tabs' value='no' />
						<param name='toolbar' value='yes' />
						<param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;____16348768462840&#47;2_1&#47;1.png' /> 
						<param name='animate_transition' value='yes' />
						<param name='display_static_image' value='yes' />
						<param name='display_spinner' value='yes' />
						<param name='display_overlay' value='yes' />
						<param name='display_count' value='yes' />
						<param name='language' value='ko-KR' />
					</object>
				</div>                
				<script type='text/javascript'>                    
					var divElement = document.getElementById('viz1635245912646');                    
					var vizElement = divElement.getElementsByTagName('object')[0];                    
					if ( divElement.offsetWidth > 800 ) { 
						vizElement.style.width='1000px';vizElement.style.height='827px';} 
					else if ( divElement.offsetWidth > 500 ) { 
						vizElement.style.width='1000px';vizElement.style.height='827px';} 
					else { 
						vizElement.style.width='100%';vizElement.style.height='1227px';}                     
					var scriptElement = document.createElement('script');                    
					scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    
					vizElement.parentNode.insertBefore(scriptElement, vizElement);                
				</script>
			
				<br><br><br><br><br><br><br><br>
			
			
				<p class="information_text" style="font-weight:bold;">- 청년 친화 강소 기업 데이터 시각화</p>
				<div class='tableauPlaceholder' id='viz1635245975030' style='position: relative'>
					<noscript><a href='#'>
					<img alt='대시보드 2 ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;____16348763301240&#47;2_1&#47;1_rss.png' style='border: none' />
					</a></noscript>
					<object class='tableauViz'  style='display:none;'>
						<param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> 
						<param name='embed_code_version' value='3' /> 
						<param name='site_root' value='' />
						<param name='name' value='____16348763301240&#47;2_1' />
						<param name='tabs' value='no' />
						<param name='toolbar' value='yes' />
						<param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;____16348763301240&#47;2_1&#47;1.png' /> 
						<param name='animate_transition' value='yes' />
						<param name='display_static_image' value='yes' />
						<param name='display_spinner' value='yes' />
						<param name='display_overlay' value='yes' />
						<param name='display_count' value='yes' />
						<param name='language' value='ko-KR' />
					</object>
				</div>                
				<script type='text/javascript'>                   
					var divElement = document.getElementById('viz1635245975030');                    
					var vizElement = divElement.getElementsByTagName('object')[0];                    
					if ( divElement.offsetWidth > 800 ) { 
						vizElement.style.width='1000px';vizElement.style.height='827px';} 
					else if ( divElement.offsetWidth > 500 ) { 
						vizElement.style.width='1000px';vizElement.style.height='827px';} 
					else { 
						vizElement.style.width='100%';vizElement.style.height='1227px';}                     
					var scriptElement = document.createElement('script');                    
					scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    
					vizElement.parentNode.insertBefore(scriptElement, vizElement);               
				</script>
				<br><br><br><br><br><br><br><br>
			
			
				<p class="information_text" style="font-weight:bold;">- 인재 육성형 중소 기업 데이터 시각화</p>
				<div class='tableauPlaceholder' id='viz1635246026614' style='position: relative'>
					<noscript><a href='#'>
					<img alt='대시보드 1 ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;____16348760406720&#47;1_1&#47;1_rss.png' style='border: none' />
					</a></noscript>
					<object class='tableauViz'  style='display:none;'>
						<param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> 
						<param name='embed_code_version' value='3' /> 
						<param name='site_root' value='' />
						<param name='name' value='____16348760406720&#47;1_1' />
						<param name='tabs' value='no' />
						<param name='toolbar' value='yes' />
						<param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;____16348760406720&#47;1_1&#47;1.png' /> 
						<param name='animate_transition' value='yes' />
						<param name='display_static_image' value='yes' />
						<param name='display_spinner' value='yes' />
						<param name='display_overlay' value='yes' />
						<param name='display_count' value='yes' />
						<param name='language' value='ko-KR' />
					</object>
				</div>                
				<script type='text/javascript'>                    
					var divElement = document.getElementById('viz1635246026614');                   
					var vizElement = divElement.getElementsByTagName('object')[0];                    
					if ( divElement.offsetWidth > 800 ) { 
						vizElement.style.width='1000px';vizElement.style.height='827px';} 
					else if ( divElement.offsetWidth > 500 ) { 
						vizElement.style.width='1000px';vizElement.style.height='827px';} 
					else { 
						vizElement.style.width='100%';vizElement.style.height='877px';}                     
					var scriptElement = document.createElement('script');                    
					scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    
					vizElement.parentNode.insertBefore(scriptElement, vizElement);                
				</script>
				<br><br><br><br><br><br><br><br>
			
			
				<p class="information_text" style="font-weight:bold;">- 녹색 기업 데이터 시각화</p>
				<div class='tableauPlaceholder' id='viz1635246069654' style='position: relative'>
					<noscript><a href='#'>
					<img alt='대시보드 2 ' src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;____16348759454430&#47;2_1&#47;1_rss.png' style='border: none' />
					</a></noscript>
					<object class='tableauViz'  style='display:none;'>
						<param name='host_url' value='https%3A%2F%2Fpublic.tableau.com%2F' /> 
						<param name='embed_code_version' value='3' /> 
						<param name='site_root' value='' />
						<param name='name' value='____16348759454430&#47;2_1' />
						<param name='tabs' value='no' />
						<param name='toolbar' value='yes' />
						<param name='static_image' value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;__&#47;____16348759454430&#47;2_1&#47;1.png' /> 
						<param name='animate_transition' value='yes' />
						<param name='display_static_image' value='yes' />
						<param name='display_spinner' value='yes' />
						<param name='display_overlay' value='yes' />
						<param name='display_count' value='yes' />
						<param name='language' value='ko-KR' />
					</object>
				</div>                
				<script type='text/javascript'>                    
					var divElement = document.getElementById('viz1635246069654');                    
					var vizElement = divElement.getElementsByTagName('object')[0];                    
					if ( divElement.offsetWidth > 800 ) { 
						vizElement.style.width='1000px';vizElement.style.height='827px';} 
					else if ( divElement.offsetWidth > 500 ) { 
						vizElement.style.width='1000px';vizElement.style.height='827px';} 
					else { 
						vizElement.style.width='100%';vizElement.style.height='1227px';}                     
					var scriptElement = document.createElement('script');                    
					scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    
					vizElement.parentNode.insertBefore(scriptElement, vizElement);                
				</script>
				
				
				
			</div>
		</div>
	</div>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/common.js"></script>
	<script type="text/javascript" src="../JavaScript/right_Check.js"></script>
</body>
</html>