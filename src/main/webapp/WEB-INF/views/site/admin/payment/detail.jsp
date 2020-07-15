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
	<div class="border mt-4 p-3">
		<div class="row">
			<div class="col-4">예약번호 : </div>
			<div class="col-8">${reserv.no }</div>
		</div>
		<div class="row">
			<div class="col-4">결제금액 : </div>
			<div class="col-8">${reserv.amount }&nbsp;원</div>
		</div>
	</div>
	<br>
	<div class="border p-3">
		<div class="row">
			<div class="col-4">사무실 번호 : </div>
			<div class="col-8">${reserv.meetingNo }</div>
		</div>
		<div class="row">
			<div class="col-4">사무실 이름 : </div>
			<div class="col-8">${reserv.meetingName }</div>
		</div>
	</div>
	<br>
	<div class="border p-3">
		<div class="row">
			<div class="col-4">예약날짜 : </div>
			<div class="col-8">
				<fmt:formatDate value="${reserv.startDate }" pattern="yy-MM-dd HH:mm:ss"/>&nbsp;부터<br>
				<fmt:formatDate value="${reserv.endDate }" pattern="yy-MM-dd HH:mm:ss"/>&nbsp;까지<br>
			</div>
		</div>
		<div class="row">
			<div class="col-4">예약완료일 : </div>
			<div class="col-8"><fmt:formatDate value="${reserv.regdate }" pattern="yy-MM-dd HH:mm:ss"/></div>
		</div>
	</div>
	<br>
	<div class="border p-3">
		<div class="row">
			<div class="col-4">예약자이메일 : </div>
			<div class="col-8">${reserv.register }</div>
		</div>
		<div class="row">
			<div class="col-4">예약자이름 : </div>
			<div class="col-8">${reserv.registerName }</div>
		</div>
		<div class="row">
			<div class="col-4">예약자닉네임 : </div>
			<div class="col-8">${reserv.registerNickname }</div>
		</div>
	</div>
	<br>
	<div class="border p-3">
		<div class="row">
			<div class="col-4">예약상태 : </div>
			<div class="col-8">${reserv.processtatus }&nbsp;/&nbsp;
				${reserv.processtatus == 750 ? '결제취소' : '예약완료'}
			</div>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col">
			<c:if test="${reserv.processtatus != 750}">
				<button type="button" class="btn btn-secondary" id="cancel">결제취소</button>
			</c:if>
		</div>
	</div>
</div>
<%@ include file="detail_js.jsp" %>
</body>
</html>