<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body>
<style>
#profilePic{
	width:200px;
	height:200px;
	background-repeat:no-repeat;
	background-position:center;
	background-size:cover;
}
</style>

	<div class="container mt-3 border align-center text-center" id="profilePic"
	style="background-image:url('<%=request.getContextPath()%>/commons/member/getProfile?id=${member.email }'),url('https://via.placeholder.com/200?text=Profile');"
	>
	</div>
	<div class="container m-3 p-3">
		이메일 : ${member.email }<br>
		닉네임 : ${member.nickname }<br>
		이름 : ${member.name }<br>
		번호 : ${member.phoneNumber}<br>
		주소 : ${member.postCode }&nbsp;${member.address }&nbsp;${member.detailAddress }<br>
		가입일 : <fmt:formatDate value="${member.regdate }" pattern = "yyyy-MM-dd HH:mm:ss" ></fmt:formatDate><br>
		<br>
		현재 참여중인 프로젝트 : <c:if test="${empty projectList}">없음</c:if>
		<c:forEach var="project" items="${projectList }">[${project.projectName}]&nbsp;</c:forEach>
		<br><br>
		권한 : <select>
			<option value="ROLE_USER" <c:if test="${member.role eq 'ROLE_USER'}">selected</c:if>>일반유저</option>
			<option value="ROLE_DISABLED" <c:if test="${member.role eq 'ROLE_DISABLED'}">selected</c:if>>탈퇴유저</option>
			<option value="ROLE_ADMIN" <c:if test='${member.role eq "ROLE_ADMIN"}'>selected</c:if>>관리자</option>
		</select>
	</div>
	<div class="container m-3 p-3">
		<button class="btn btn-primary" onclick="submit()">수정완료</button>
		<button class="btn btn-secondary" onclick="cancel()">닫기</button>&nbsp;
	</div>
	
	<form id="form"  method="post" action="<% request.getContextPath();%>/dsp/admin/member/modify" style="display:none;">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input name="email" id="email" value=${member.email }><br>
		<input name="role" id="role" value=${member.role }><br>
	</form>
<%@ include file="./detail_js.jsp" %>
</body>