<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="include/bootstrap.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인 화면</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/resources/site/css/basicForm.css">
<body onkeydown="EnterLogin()">
<form id="loginForm" action="/commons/login" method="post" class="form">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<fieldset>
		<legend>로그인</legend>
		<label>
			<input type="text" class="input" id="email" name="email" placeholder="Email"
				   onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣~!#$%^&*()_+|<>?:{}\-/ ]/g, '');">
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label>
		<label>
			<input type="password" class="input" id="password" name="password" placeholder="Password" >
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label>
		<button type="button" class="btn-sm" style="float: right;" onclick="openWindow('/commons/member/agree',925,640)" >회원가입</button>
		<button type="button" class="btn-sm" style="float: right; margin-right: 20px;" onclick="login()"  onkeypress="if( event.keyCode == 13 ){login();}">로그인</button>
		<button type="button" class="btn-sm" onclick="openWindow('/commons/member/findEmail',715,570)" >Email찾기</button>
		<button type="button" class="btn-sm" onclick="openWindow('/commons/member/findPassword',815,670)" >Password찾기</button>
	</fieldset>
</form>
</body>

<script>
	var message="${msg}";
	if(message!=""){
		alert(message);
	}

	function login() {
		var email = $('#email').val();
		var password = $('#password').val();

		if (!email) {
			alert("이메일을 입력하세요")
			return false;
		}
		if (!password) {
			alert("패스워드를 입력하세요")
			return false;
		}
		$('#loginForm').submit();
	}
	function EnterLogin() {
		var keyCode = window.event.keyCode;



		if (keyCode == 13) { //엔테키 이면

			login();
		}

	} //onEnterLogin()


</script>
<%@ include file="member/commons_js.jsp" %>
</html>