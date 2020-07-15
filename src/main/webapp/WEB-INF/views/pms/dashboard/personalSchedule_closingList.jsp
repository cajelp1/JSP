<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath() %>/resources/pms/css/hover-menu.css">
<meta charset="UTF-8">
<title>마감임박업무List</title>
</head>
<body>
	<div class="card-body p-0" style="display: block;">
		<table class="table table-striped projects">
			<thead class="table-header">
				<tr>
					<th style="width: 1%">#</th>
					<th style="width: 20%">참여자 이름</th>
					<th style="width: 30%">참여자 Email</th>
					<th style="width: 14%" class="text-center">전화번호</th>
					<th style="width: 25%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="schedule" items="${schedule }">
					<tr>
						<td>#</td>
						<td><p>${schedule.memberId }</p></td>
						<td class="member"><p id="email">${schedule.memberId }</p></td>
						<td class="project-state"><span class="badge badge-success">${schedule.processPercent	}</span>
						</td>
						<td class="project-state">
							<div class="dropdown">
								<button class="dropbtn">
									<i class="fas fa-ellipsis-h" style="color: white;"></i>
								</button>
								<div class="dropdown-content">
									<a>빨리 해라 Shake it 아</a>
									<%-- 											    <a onclick="OpenWindow('detail?id=${member.email }');">참여자 조회</a> --%>
									<%-- 											    <a onclick="removeMember('${member.name}','${member.email }');">내보내기</a> --%>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>