<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">  
  <div class="row">
	<h2 class="col-4" style="display:inline;">회원 리스트</h2>
  	<form class="col-8 text-right" id="searchForm" style="display:inline;">
  		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  		<input type="hidden" name="page" value="${pageMaker.cri.page }">
		<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum }">
  		<select name="searchType">
  			<option value="e" <c:if test="${pageMaker.cri.searchType eq 'e'}">selected</c:if>>이메일</option>
  			<option value="n" <c:if test="${pageMaker.cri.searchType eq 'n'}">selected</c:if>>이름</option>
  			<option value="nn" <c:if test="${pageMaker.cri.searchType eq 'nn'}">selected</c:if>>닉네임</option>
  		</select>
  		<input name="keyword" <c:if test="${!empty pageMaker.cri.keyword}">value="${pageMaker.cri.keyword}"</c:if>>
  		<button type="button" id="searchBtn">검색</button>
  	</form>
  </div>
  <br>
  <table class="table table-hover">
	<thead>
	  <tr>
		<th>이메일</th>
		<th>이름</th>
		<th>닉네임</th>
		<th>회원 권한</th>
		<th>가입일자</th>
	  </tr>
    </thead>
    <tbody>
    <!-- 값이 없을 때를 c:if로 표시할 것 -->
	    <c:forEach var="member" items="${memberList }" >
	      <tr class="members" id=${member.email }>
	        <td>${member.email }</td>
	        <td>${member.name }</td>
	        <td>${member.nickname }</td>
	        <td>
	        	<c:if test="${member.role eq 'ROLE_USER'}">일반유저</c:if>
	        	<c:if test="${member.role eq 'ROLE_SLEEP'}">휴면유저</c:if>
	        	<c:if test="${member.role eq 'ROLE_DISABLED'}">탈퇴유저</c:if>
	        	<c:if test="${member.role eq 'ROLE_ADMIN'}">관리자</c:if>
	        </td>
	        <td><fmt:formatDate value="${member.regdate }" pattern = "yyyy-MM-dd HH:mm:ss" ></fmt:formatDate></td>
		  </tr>
		</c:forEach>
	</tbody>
  </table>
</div>

<!-- pagination -->
<div class="container">
	
</div>

<%@ include file="../pagination/pagination.jsp" %>
<%@ include file="./list_js.jsp" %>
</body>
</html>