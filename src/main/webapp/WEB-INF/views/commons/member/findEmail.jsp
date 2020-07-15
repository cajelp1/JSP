<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="../include/bootstrap.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>이메일 찾기</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/resources/site/css/basicForm.css">
</head>
<body>
<div class="containor">
	<form id="loginForm" action="/commons/member/findEmail" method="post" class="form">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<fieldset>
			<legend>이메일찾기</legend>
			<label>
				<input type="text" class="input" id="name" name="name" placeholder="이름을 입력하세요">
				<div class="line-box">
					<div class="line"></div>
				</div>
			</label>
			<label>
				<input type="text" class="input" id="phoneNumber" name="phoneNumber" placeholder="전화번호를 입력하세요"
				onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣa-zA-B가-힣-]/g, &#39;&#39;);"
	      		maxlength=11>
				<div class="line-box">
					<div class="line"></div>
				</div>
			</label>
			<button type="submit" style="float: right;">확인</button>
		</fieldset>
	</form>
</div>
</body>
</html>