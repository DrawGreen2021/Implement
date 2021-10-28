<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h4>가장 많이 검색한 기업</h4>
<c:forEach items="${requestScope.searchRank }" var="entry">
	<p>${entry.key }.${entry.value }</p>
</c:forEach>
