<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.drawgreen.corpcollector.dao.RecentSearchCorpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//가장 많이 검색한 기업 순위 구하기
	RecentSearchCorpDAO recentSearchCorpDAO = RecentSearchCorpDAO.getInstance();
	LinkedHashMap<Integer, String> searchRank = recentSearchCorpDAO.getRecentSearchRank();
	request.setAttribute("searchRank", searchRank);
%>
<h4>가장 많이 검색한 기업</h4>
<c:forEach items="${requestScope.searchRank }" var="entry">
	<p style="text-align:left; font-size:10pt; font-family:dotum;">${entry.key }.${entry.value }</p>
</c:forEach>
