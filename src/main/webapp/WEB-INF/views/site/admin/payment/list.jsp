<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div class="container">
	<div class="row">
	<h2 class="col-4" style="display:inline;">결제내역</h2>
  	<form class="col-8 text-right" id="searchForm" style="display:inline;">
  		<input type="hidden" name="page" value="${pageMaker.cri.page }">
		<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum }">
  		<select name="searchType">
  			<option value="e" <c:if test="${pageMaker.cri.searchType eq 'e'}">selected</c:if>>예약자이메일</option>
  			<option value="n" <c:if test="${pageMaker.cri.searchType eq 'n'}">selected</c:if>>예약자이름</option>
  			<option value="m" <c:if test="${pageMaker.cri.searchType eq 'm'}">selected</c:if>>사무실이름</option>
  			<option value="p" <c:if test="${pageMaker.cri.searchType eq 'p'}">selected</c:if>>결제상황</option>
  		</select>
  		<input name="keyword" <c:if test="${!empty pageMaker.cri.keyword}">value="${pageMaker.cri.keyword}"</c:if>>
  		<button type="button" id="searchBtn">검색</button>
  	</form>
  </div>
  <br>
  <table class="table table-hover">
	<thead>
	  <tr>
		<th>예약번호</th>
		<th>가격</th>
		<th>사무실이름</th>
		<th>예약자이메일</th>
		<th>예약자이름</th>
		<th>예약완료일</th>
	  </tr>
    </thead>
    <tbody>
    <!-- 값이 없을 때를 c:if로 표시할 것 -->
	    <c:forEach var="reserv" items="${reservationList }" >
		  <tr class="reserv" ${reserv.processtatus == 750 ? 'style="color:lightgray;"' : ''} id=${reserv.no }>
			<td>${reserv.no }</td>
			<td>${reserv.amount }</td>
			<td>${reserv.meetingName }</td>
			<td>${reserv.register }</td>
			<td>${reserv.registerName }</td>
			<td><fmt:formatDate value="${reserv.regdate }" pattern = "yyyy-MM-dd HH:mm:ss" ></fmt:formatDate></td>
		  </tr>
		</c:forEach>
	</tbody>
  </table>
</div>
<%@ include file="../pagination/pagination.jsp" %>
<%@ include file="list_js.jsp" %>
</body>
</html>