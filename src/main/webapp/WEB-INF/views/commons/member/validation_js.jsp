<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	function emailValidation(){
		
		//email 유효성 검사
 		var email = document.getElementById('email').value.trim();
		if( email == '' || email == 'undefined') {
			alert('email을 입력해주세요');
			return false;
		}
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if(!(regExp.test(email))){
			alert('올바르지 않은 email 형식입니다.');
			return false;
		}
		return true;
	}
	
	function nicknameValidation(){
		
		//닉네임 유효성 검사
		var regExp = /^[가-힣A-Za-z0-9]{3,10}$/;
		var nickname = document.getElementById('nickname').value.trim();
		if(nickname ==''||nickname==null){
			alert('닉네임을 입력해주세요');
			return false;
		}
		if(!(regExp.test(nickname))){
			alert('한글 또는 영어 또는 숫자 3~10자 조합으로 입력하세요');
			return false;
		}
		return true;
	}

	function validationCheck(){
		
		var form = document.getElementById('registForm');
		
		//email 유효성 검사
 		emailValidation();
		
		//password 유효성 검사
		var password = form.password.value.trim();
		var passwordCheck = form.passwordCheck.value.trim();
		if( password == '' || password == 'undefined'){
			alert('password를 입력해주세요');
			return false;
		}
		
		if( passwordCheck == '' || passwordCheck == 'undefined'){
			alert('password를 다시 입력해주세요');
			return false;
		}
		
		// 비밀번호 정규식 쓰기
		regExp = /^[a-zA-Z0-9]{6,20}$/;
		if(!(regExp.test(password))){
			alert('비밀번호는 6~20자 이어야 하며\n숫자/영어를 모두 포함해야 합니다.');
			return false;
		}
		
		// 입력한 두 비밀번호가 같은지 체크
		if( password != passwordCheck){
			alert('입력한 두 password가 일치하지 않습니다.');
			return false;
		}

		//파일 업로드 했는지 체크
		var picture = form.picture.value.trim();
		if( picture == '' || picture == null){
			alert('프로필 이미지를 업로드해주세요');
			return false;
		}
		
		//이름 유효성 체크
		regExp = /^[가-힣]{2,4}$|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;
		var name = form.name.value.trim();
		if( name == '' || name == null){
			alert('이름을 입력해주세요');
			return false;
		}

/* 		if(!(regExp.test(name))){
			alert('한글 이름은 2~5자, 영어는 4~20자로 입력하세요');
			return false;
		} */
		/*
		닉네임 체크
		*/
		nicknameValidation();
		
		var postcode = form.postCode.value.trim();
		if(postcode==''||postcode==null){
			alert('우편번호를 선택해주세요');
			return false;
		}
		
		var address = form.address.value.trim();
		if(address==''||address==null){
			alert('우편번호를 선택해주세요');
			return false;
		}
		
		var emailCheck = document.getElementById('emailCheck').value;
		if(emailCheck == 0){
			alert('Email 중복체크를 해주세요');
			return false;
		}

	return true;
	}
</script>
