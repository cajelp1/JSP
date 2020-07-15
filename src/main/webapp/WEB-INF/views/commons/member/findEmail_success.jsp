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
<script>
	if (window.opener && !window.opener.closed) {
		alert("회원님의 이메일은 ${email} 입니다.")
		window.close();
	}
</script>
<body>
<div class="containor">
	<form class="form">
		<fieldset>
			<legend></legend>
			<label>
				<p>회원님의 이메일은 ${email}입니다.</p>
				<div class="line-box">
					<div class="line"></div>
				</div>
			</label>
			<button type="button" onclick="location.href='${pageContext.request.contextPath}/dsp/main'">메인으로 가기</button>
			<button type="button" onclick="location.href='${pageContext.request.contextPath}/commons/login'">로그인으로 가기</button>
		</fieldset>
	</form>
</div>
</body>
</html>