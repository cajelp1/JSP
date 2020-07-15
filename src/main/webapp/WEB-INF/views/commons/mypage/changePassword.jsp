<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/resources/site/css/basicForm.css">
	<title>비밀번호 변경</title>
</head>
<body>
	<div class="container">
		<form class="form" name="changeForm" id="changeForm" action="<%=request.getContextPath()%>/dsp/my/changePassword?${_csrf.parameterName}=${_csrf.token}" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<label>
				<input type="password" class="input" id="oldPassword" name="oldPassword" placeholder="현재 비밀번호를 입력하세요">
				<div class="line-box">
					<div class="line"></div>
				</div>
			</label>
			<label>
				<input type="password" class="input" id="newPassword" name="newPassword" placeholder="새로운 비밀번호를 입력하세요">
				<div class="line-box">
					<div class="line"></div>
				</div>
			</label>
			<label>
				<input type="password" class="input" id="newPassword2" name="newPassword2" placeholder="비밀번호를 한번 더 입력하세요">
				<div class="line-box">
					<div class="line"></div>
				</div>
			</label>
			<button type="button" id="btnOk" style="float: right;">확인</button>
		</form>
	</div>
	
	<script>
	function checkValidation(){
		
		//password 유효성 검사
		var oldPw = document.changeForm.oldPassword.value.trim();
		var newPw = document.changeForm.newPassword.value.trim();
		var newPw2 = document.changeForm.newPassword2.value.trim();
		
		console.log(oldPw);
		
		if( oldPw == '' || oldPw == 'undefined' || newPw == '' || newPw == 'undefined' || newPw2 == '' || newPw2 == 'undefined'){
			alert('빈 칸이 존재합니다');
			return false; m
		}
		
		// 비밀번호 정규식 쓰기
		regExp = /^[a-zA-Z0-9]{6,20}$/;
		if(!(regExp.test(newPw))){
			alert('비밀번호는 6~20자 이어야 하며\n숫자/영어를 모두 포함해야 합니다.');
			return false;
		}
		
		// 입력한 두 비밀번호가 같은지 체크
		if( newPw != newPw2){
			alert('입력한 두 password가 일치하지 않습니다.');
			return false;
		}
		return true;
	}
	
	$('#btnOk').on('click',function(){
		if(checkValidation()){
			$('#changeForm').submit();
		}
	});
		
	</script>
</body>
</html>