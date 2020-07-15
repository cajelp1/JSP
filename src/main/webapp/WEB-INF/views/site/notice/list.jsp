<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<title>공지사항</title>
</head>
<body>
<c:set var="pageMaker" value="${pageMaker }" />
<div class="noitce list">
	<div class="container">
	<!-- top bar -->
	<nav class="navbar navbar-light bg-light">
		<span class="navbar-brand mb-0 h1">공지사항</span>		
		<span class="navbar-brand mb-0 h1">총 ${pageMaker.totalCount }개</span>
		<select class="custom-select" name="searchType" id="inputGroupSelect04" aria-label="Example select with button addon">
		    <option value="tc" ${pageMaker.cri.searchType eq 'tc' ? 'selected' : '' }>==전체==</option>
		    <option value="t" ${pageMaker.cri.searchType eq 't' ? 'selected' : '' }>제목</option>
		    <option value="c" ${pageMaker.cri.searchType eq 'c' ? 'selected' : '' }>내용</option>
<!-- 		    <option value="tc">제목+내용</option> -->
		</select>
		<form class="form-inline">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input class="form-control mr-sm-2" id="keyword" name="keyword" type="text" value="${pageMaker.cri.keyword }" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="searchList_go(1);">Search</button>
		</form>
		<c:if test="${sessionScope.loginUser.role eq 'ROLE_ADMIN'}"> 
		<button type="button" onclick="openWindow('/dsp/notice/regist',720,640)">등록</button>
		</c:if>
	</nav>
	<!-- list -->
	<table class="table table-hover">
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">작성일</th>
			<th scope="col">조회수</th>
	    </tr>
	</thead>
	<tbody>
		<c:forEach items="${curr_list }" var="board">
			<tr>
				<td>${board.sno }</td>
				<td onclick="openWindow('detail?sno=${board.sno}',720,640)">${board.title }<i class="fas fa-star" style="color: #1E90FF;float: right;"></i></td>
				<td><fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd" /></td>
				<td>${board.viewcnt }</td>
			</tr>
		</c:forEach>
		<c:forEach items="${boardList }" var="board">
			<c:if test="${board.deleteyn eq 'n' }">
			<tr>
				<td>${board.sno }</td>
				<td onclick="openWindow('detail?sno=${board.sno}',720,640)">${board.title }</td>
				<td><fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd" /></td>
				<td>${board.viewcnt }</td>
			</tr>
			</c:if>
			<c:if test="${board.deleteyn eq 'y' }">
			<tr>
				<td>${board.sno}</td>
				<td colspan="3">이 게시글은 삭제된 글입니다.</td>
			</tr>
			</c:if>
		</c:forEach>
	</tbody>
	</table>
	</div>
</div>
<%@include file="/WEB-INF/views/commons/include/pagination.jsp" %>
<script>
	
	$('#searchBtn').on('click',function(e){
		/* 
		if($('input[name="keyword"]').val() !=""){
			if($('select[name="searchType"]').val()==""){
				alert("검색구분은 필수입니다.");
				$('input[name="searchType"]').focus();
				return;
			}			
		} */
		
		var jobForm=$('#jobForm');
		jobForm.find("[name='page']").val(1);
		jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
		jobForm.find("[name='keyword']").val($('input[name="keyword"]').val());
		
		/* alert(jobForm.serialize()); */
		
		jobForm.attr("action","list").attr("method","get");
		jobForm.submit();
	});
	
	
</script>
</body>
</html>