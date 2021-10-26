<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 페이지 번호, 페이지 표시 블록의 시작&끝 번호, 페이지 가장 끝 번호, 한 번에 표시할 페이지 개수 정의 --%>
<c:set var="page" value="${(empty param.page)? 1 : param.page}"
	scope="request" />
<c:set var="startNum" value="${requestScope.blockStartNum}"
	scope="request" />
<c:set var="lastNum" value="${requestScope.blockLastNum}"
	scope="request" />
<c:set var="lastPageNum" value="${requestScope.lastPageNum }"
	scope="request" />
<c:set var="pageCount" value="${5 }" scope="request" />