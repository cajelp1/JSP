<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ include file="../include/bootstrap.jsp"%> --%>
<%-- <%@ include file="../include/main_header.jsp"%> --%>
<style>
th{
	text-align: center;
}
</style>
<body>
<div class="row">
	<div class="col-3">
		<select id="searchType">
			<option value="husker">전체</option>
			<option value="w">작성자</option>
			<option value="t">제목</option>
			<option value="wt">작성자 + 제목</option>
		</select>
	</div>
	<div class="col-3">
		<label for="middle-label" class="text-right middle">총 개</label>
	</div>
	<div class="col-6">
		<div class="input-group input-group-rounded">
			<input class="input-group-field" type="search">
			<div class="input-group-button" style="height: 39px;">
				<input type="submit" class="button secondary" value="Search" style="height: 39px;">
			</div>
		</div>
	</div>
</div>
<div class="row">
		<!-- 검색바 -->
	<div class="row" id="searchBar">
		<div class="columns medium-3"></div>
		<div class="columns medium-3">
			<a onclick="openWindow('/commons/notice/regist',720,640)" class="button" style="float: right;">등록</a> -->
			<a onclick="" class="button">알림</a>
		</div> 
	</div>
</div>
<div class="row">
  <div class="col-2">
    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
      <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">Home</a>
      <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">Profile</a>
      <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab" aria-controls="v-pills-messages" aria-selected="false">Messages</a>
      <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings" role="tab" aria-controls="v-pills-settings" aria-selected="false">Settings</a>
    </div>
  </div>
  <div class="col-8">
  <div class="table-responsive-sm">
<table class="table table-sm">
  <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">작성자</th>
      <th scope="col">등록일자</th>
      <th scope="col">조회수</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      	<c:forEach items="${list }" var="board">
			<tr>
				<th scope="row">${board.sno }</th>
				<td class="text-center"><a onclick="openWindow('detail?sno=${board.sno}',720,640)">${board.title }</a></td>
				<td class="text-center">${board.writer }</td>
				<td class="text-center"><fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd" /></td>
				<td class="text-center">${board.viewcnt }</td>
			</tr>					
		</c:forEach>
    </tr>
  </tbody>
</table>
</div>
  </div>  
</div>


		<!-- 게시판 시작 -->


		<!-- 게시판 끝 -->
	</div>
</body>
</html>
<%-- <%@ include file="../include/bootstrap_footer.jsp"%> --%>
<%@ include file="list_include_js.jsp"%>