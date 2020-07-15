<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<body>
	<div class="container m-3">
	<table class="table">
		<thead>
			<tr>
				<th>사무실이름</th>
				<th>사유</th>
				<th>측정값</th>
				<th>등록시기</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty dangerList }">
			<tr style="text-align:center;">
				<td colspan="5">내용이 없습니다</td>
			</tr>
			</c:if>
			<c:forEach items="${dangerList}" var="danger">
			<tr>
				<td>${danger.roomName }</td>
				<td>${danger.reason }</td>
				<td>${danger.val }</td>
				<td><fmt:formatDate value="${danger.regDate}" pattern="yyyy-MM-dd / HH:mm:ss"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>