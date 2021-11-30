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
	
	<title>CorpCollector : 공지사항</title>
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
					<h3>커뮤니티</h3>
					<p><a href="<c:url value='/community/notice.jsp'/>" style="color:#e1bf27; font-weight:bold;">공지사항</a></p>
					<p><a href="<c:url value='/community/feedback.jsp'/>">고객 후기</a></p>
				</ul>
			</aside>
		</div>
		
		<c:choose>
			<c:when test="${requestScope.post != null }">
				<div class="content_div" style=" margin-bottom:19%;">
					<table width="1000px;" style="text-align: center; float: right;">
						<tr>
							<p style="font-size: 16pt; color: #21499b; font-weight: bold; margin: 0 90% 0 0;">
							공지사항</p>
						</tr>
						<tr>
							<div style="border: 1px solid #21499b; margin: 1% auto;"></div>
						</tr>
						<tr>
							<table class="content_div_write">
								<tr class="community_text" height="30px;">
									<td width="100px" style="background-color: #eeedeb; text-align:center;">제목</td>
									<td colspan="3" style="text-align:left; padding-left:15px; ">${requestScope.post['title']}</td>
								</tr>
								<tr class="community_text" height="30px;">
									<td width="100px" style="background-color: #eeedeb;">작성자</td>
									<c:choose>
										<c:when test="${requestScope.post['is_private_writer'] == true}">
											<td colspan="3" style="text-align:left; padding-left:15px; ">비공개</td>
										</c:when>
										<c:otherwise>
											<td colspan="3" style="text-align:left; padding-left:15px; ">${requestScope.post['writer_name'] }</td>
										</c:otherwise>
									</c:choose>

								</tr>
								<tr class="community_text" height="30px;">
									<td width="100px" style="background-color: #eeedeb;">등록일</td>
									<td width="350px" style="text-align:left; padding-left:15px; ">${requestScope.post['registration_date'] }</td>
									<td width="100px" style="background-color: #eeedeb;">조회수</td>
									<td width="200px" >${requestScope.post['hits'] }</td>

								</tr>
								<tr class="community_text" height="100%;">
									<td style="background-color: #eeedeb; padding:6px;">내용</td>
									<td colspan="3" style="text-align:left; padding-left:15px;"><pre style="font-family:dotum;">${requestScope.post['content'] }</pre></td>
								</tr>
							</table>

						</tr>
					</table>
					<div style="float: right;">
						<input type="hidden" id="boardName" value="공지사항">
						<button class="writing_btn" onclick="updating_Check(this)"
							value="${requestScope.post['board_number'] }">수정</button>
						<button class="writing_btn" onclick="deleting_Check(this)"
							value="${requestScope.post['board_number'] }">삭제</button>
						<button class="writing_btn" style="background-color: #E7F1FD;"
							onclick="goListPage(this)" value="notice.jsp">목록</button>
						<!-- 목록으로 돌아가기 -->
					</div>
				</div>
				
			</c:when>
			
			<c:otherwise>
				<script type="text/javascript">
					alert("비공개 글입니다.");
					history.go(-1);
				</script>
			</c:otherwise>
		</c:choose>
		</div>
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	</div>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/community_common.js?ver=<%=System.currentTimeMillis() %>"></script>
</body>
</html>