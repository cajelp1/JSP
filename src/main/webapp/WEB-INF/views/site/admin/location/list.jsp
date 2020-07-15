<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
	<style type="text/css">
		.overflowHidden{
			width:100%;
			padding:0 5px;
			overflow:hidden;
			text-overflow:ellipsis;
			white-space:nowrap;
		}
		tr > th, .lineCenter{
			text-align : center;
		}
	</style>

	<div class="container">
		<div class="">
		<h2 style="display:inline-block;">사무실 리스트</h2>
		<form class="float-right" id="searchForm" style="display:inline;">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	  		<input type="hidden" name="page" value="${pageMaker.cri.page }">
			<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum }">
	  		<select name="searchType">
	  			<option value="n" <c:if test="${pageMaker.cri.searchType eq 'n'}">selected</c:if>>사무실이름</option>
	  			<option value="a" <c:if test="${pageMaker.cri.searchType eq 'a'}">selected</c:if>>실제주소</option>
	  			<option value="p" <c:if test="${pageMaker.cri.searchType eq 'p'}">selected</c:if>>인원</option>
	  		</select>
	  		<input name="keyword" <c:if test="${!empty pageMaker.cri.keyword}">value="${pageMaker.cri.keyword}"</c:if>>
	  		<button type="button" id="searchBtn">검색</button>
			<button type="button" onclick="openWindow('<%=request.getContextPath()%>/dsp/admin/location/regist')">등록</button>
	  	</form>
		</div>
		<br>
		<table class="table table-hover" style="table-layout:fixed;">
			<thead>
				<tr>
					<th style="width:10%">No.</th>
					<th style="width:25%">사무실 이름</th>
					<th style="width:10%">검색주소</th>
					<th style="width:25%">실제주소</th>
					<th style="width:10%">인원</th>
					<th style="width:10%">삭제여부</th>
<!-- 					<th style="width:10%">위험여부</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="room" items="${roomJusoList }">
					<tr class="rooms" id=${room.roomNo }>
						<td class="overflowHidden lineCenter">${room.roomNo }</td>
						<td class="overflowHidden">${room.roomName }</td>
						<td class="overflowHidden">${room.city}/${room.area}</td>
						<td class="overflowHidden">${room.address }&nbsp;${room.detailAddress }</td>
						<td class="overflowHidden lineCenter">${room.capacity }</td>
						<td class="overflowHidden lineCenter">${room.deleteyn }</td>
<%-- 						<td class="overflowHidden lineCenter"> <i id="dangerSensorChek" class="fas fa-exclamation-triangle" style="color:red; font-size:x-large; display:none;" data-roomNo="${room.roomNo}"></i> </td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<%@ include file="../pagination/pagination.jsp" %>
<%@ include file="./list_js.jsp"%>
</body>