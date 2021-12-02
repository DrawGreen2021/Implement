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
	
	<title>CorpCollector : 고객 후기 작성</title>
</head>

<body>
	<div id="container">
	<!-- 헤더 파일 포함 -->
	<c:import url='/importedFile/header.jsp'></c:import>
	
	<!-- 내용 영역 -->
	<div class="outer_block">
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
			<form id="postForm" method="post">
			<table width="1000px;" style="text-align:center; float:right;">
				<tr>
					<p style="font-size:16pt; color:#21499b; font-weight:bold; margin:0 82% 0 0; ">고객 후기 글쓰기</p>
				</tr>
				<tr>
					<div style = "border:1px solid #21499b; margin:1% auto;"></div>
				</tr>
				<tr>
					<table class="content_div_write">
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">제목</td>
							<td style="text-align:left; padding-left:12px;"><input type="text" name="title" autocomplete="off" class="write_input" maxlength="30" 
								value="${requestScope.post==null?'':requestScope.post['title'] }"></td>
							<td width="150px" style="text-align:left;"><label><input type="checkbox" name="private_Writing" value="private_Writing" style=" text-align:left;" 
								${(requestScope.post==null || requestScope.post['is_private_writing']==false)?'':'checked' }> 글 비공개</label></td>
						</tr>
						<tr class="community_text" height="30px;">
							<td width="100px" style="background-color:#eeedeb;">작성자</td>
							<td style="text-align:left; padding-left:12px;"><input type="text" name="writer" value="${sessionScope.MemberDTO.name }" autocomplete="off" class="write_input" readonly>
								<input type="hidden" name="writer_id" value="${sessionScope.MemberDTO.id }"></td>
							<td width="150px" style="text-align:left;"><label><input type="checkbox" name="private_Writer" value="private_Writer" style="text-align:left;"
								${(requestScope.post==null || requestScope.post['is_private_writer']==false)?'':'checked' }> 작성자 비공개</label></td>
						</tr>
						<tr class="community_text" height="300px">
							<td style="background-color:#eeedeb;">내용</td>
							<td colspan="2" class="write_input">
							<textarea name="content" style="resize:none;" width="100%">${requestScope.post==null?'':requestScope.post['content'] }</textarea></td>
						</tr>
					</table>
				</tr>
			</table>
			<div style="float:right;">
			<c:choose>
				<c:when test="${requestScope.post != null }">
					<button class="writing_btn" onclick="updatePost()">수정</button>
				</c:when>
				<c:otherwise>
					<button class="writing_btn" onclick="writePost()">글쓰기</button>
				</c:otherwise>
			</c:choose>

			<button class="writing_btn" style="background-color:#E7F1FD;" 
				value="feedback.jsp" id="cancel">취소</button> <!-- 취소 선택시 뒤로가기 -->
			<input type="hidden" name="board_number" value="${requestScope.post==null?'':requestScope.post['board_number'] }">
			<input type="hidden" name="boardName" value="고객후기">
			</div>
			</form>
		</div>
	</div>
	
	
	
	<!-- 푸터 파일 포함 -->
	<c:import url='/importedFile/footer.html'></c:import>
	</div>
	
	<!-- 자바 스크립트 파일 외부 참조 -->
	<script type="text/javascript" src="../JavaScript/community_common.js?ver=<%=System.currentTimeMillis() %>"></script>
</body>
</html>