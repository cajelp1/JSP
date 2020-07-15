<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="../include/bootstrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/site/css/regist_member.css"/>

</head>
<body>
<div class="container step">
    <div class="row">
    	<div class="col-4" id="alrnr">
	    	<div class="row step1">
    			<div class="col">
		    		<span class="tit"><i class="far fa-check-square icon"></i>STEP 01</span>
					<p class="txt">약관동의</p>
				</div>
			</div>	
    	</div>
    	<div class="col-3 active" id="alrnr">
	    	<div class="row step2">
    			<div class="col">
		    		<span class="tit"><i class="fas fa-pencil-alt icon"></i>STEP 02</span>
					<p class="txt">회원정보 입력</p>
    			</div>
			</div>	
		</div>	
    	<div class="col-1 active" id="arrow"></div>
    	<div class="col-4">
    		<div class="row step3">
    			<div class="col">
		    		<span class="tit"><i class="far fa-smile-wink icon"></i>STEP 03</span>
					<p class="txt">회원가입 완료</p>
    			</div>
    		</div>
		</div>
   	</div>
</div>
<h3>회원가입</h3>
<div class="container">
	<div class="row justify-content-md-center img-form">
		<div class="mailbox-attachments clearfix" style="text-align: center;">
			<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;"></div>
		</div>
	</div>
</div>
<div class="container">
<form role="form" class="form" action="regist?${_csrf.parameterName}=${_csrf.token}" method="post" id="registForm" enctype="multipart/form-data">
	<input type="hidden" name="picture" />
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<div class="row justify-content-md-center regist_member">
		<label for="colFormLabelSm" class="col-3 col-form-label">프로필 이미지</label>
		<div class="col-9">
			<div class="input-group input-group">
				<input id="inputFileName" class="form-control" type="text" name="inputFileName" />
				<label for="inputFile" class="btn btn-warning btn-flat input-group-addon upload-form ">파일선택</label>
				<span class="input-group-append">											
					<button type="button" class="btn btn-info btn-append upload-form" onclick="upload_go();">업로드</button>											
				</span>
			</div>
		</div>
	</div>
	<div class="form-group row regist_member">
		<label for="colFormLabelSm" class="col-3 col-form-label">Email</label>
		<div class="col-6">
			<input type="text" class="form-control" id="email" name="email" placeholder="Email">
		</div>
	    <div class="col-3">
			<button type="button" class="btn btn-info" onclick="emailCheck()">중복체크</button>
		</div>
	</div>
	<div class="form-group row regist_member">
		<label for="colFormLabelSm" class="col-3 col-form-label">Password</label>
		<div class="col-9">
			<input type="password" class="form-control" id="password" name="password" placeholder="Password">
		</div>
	</div>
	<div class="form-group row regist_member">
		<label for="colFormLabelSm" class="col-3 col-form-label">Password 확인</label>
		<div class="col-9">
			<input type="password" class="form-control" id="passwordCheck" name="passwordCheck" placeholder="Password확인">
		</div>
	</div>
	<div class="form-group row regist_member">
		<label for="colFormLabelSm" class="col-3 col-form-label">전화번호</label>
		<div class="col-9">
			<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="전화번호 '-'없이 입력하세요"
			   	maxlength="11"
			   	onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣 a-z A-Z ~!@#$%^&*()_+|<>?:{}\-/ ]/g, '');"
				value=""
			   >
		</div>
	</div>
	<div class="form-group row regist_member">
		<label for="colFormLabelSm" class="col-3 col-form-label">이름</label>
		<div class="col-9">
			<input type="text" class="form-control" id="name" name="name" placeholder="이름">
		</div>		
	</div>		
	<div class="form-group row regist_member">
		<label for="colFormLabelSm" class="col-3 col-form-label">닉네임</label>
		<div class="col-6">
			<input type="text" class="form-control" id="nickname" name="nickname" placeholder="닉네임">
		</div>
		<div class="col-3">
			<button type="button" class="btn btn-info" onclick="nickCheck()">중복체크</button>
		</div>
	</div>
	<div class="form-group row regist_member">
		<label for="colFormLabelSm" class="col-3 col-form-label">우편번호</label>
		<div class="col-6">
			<input type="text"class="form-control" id="postCode" name="postCode" placeholder="우편번호" readonly>
		</div>
		<div class="col-3">
			<button type="button" class="btn btn-info" onclick="searchAddress()">주소찾기</button>
		</div>
	</div>
	<div class="form-group row regist_member">
		<label for="colFormLabelSm" class="col-3 col-form-label">도로명주소</label>
		<div class="col-9">
			<input type="text" class="form-control" id="address" name="address" placeholder="주소" readonly>		
		</div>
	</div>
	<div class="form-group row regist_member">
		<label for="colFormLabelSm" class="col-3 col-form-label">상세주소</label>
		<div class="col-9">
			<input type="text" class="form-control" id="detailAddress" name="detailAddress" placeholder="상세주소">
		</div>
	</div>
	<div class="row">
		<button type="button" class="btn btn-info" onclick="formRegist()" value="회원가입">회원가입</button>
	</div>
</form>
</div>
	<input id="emailCheck" type="hidden" value="0"/>
	<input id="nickCheck" type="hidden" value="0"/>
	<form role="imageForm" action="/commons/member/uploadImg" method="post"	enctype="multipart/form-data" >
		<input id="inputFile" name="pictureFile" type="file" class="show-for-sr" style="display: none;">
		<input id="oldFile" type="hidden" name="oldPicture" value="" />
		<input type="hidden" name="checkUpload" value="0" />
	</form>
<script>

function nickCheck(){
	if(nicknameValidation()){
		var nickname = document.getElementById('nickname').value.trim();
		$.ajax({
			url:"${pageContext.request.contextPath}/commons/member/nickCheck",
			type:"post",
			beforeSend : function(xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
            },
			data:{
				"nickname":nickname
			},
			success:function(data){
				if(data.result){
					document.getElementById('nickCheck').value = 1;
					alert(nickname+'은/는 사용할 수 있는 닉네임입니다.');
				} else {
					alert('이미 존재하는 닉네임입니다.\n다른 닉네임을 입력해주세요.');
				}
			},
			error:function(xhr){
				alert('잠시후에 시도하세요');
			}
		})
	}
}

function emailCheck(){
	if(emailValidation()){

		var email = document.getElementById('email').value.trim();
		$.ajax({
			url:"${pageContext.request.contextPath}/commons/member/emailcheck",
			type:"post",
			beforeSend : function(xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
            },
			data:{
				"email":email
			},
			success:function(data){
				if(data.result){
					document.getElementById('emailCheck').value = 1;
					alert(email+'은/는 사용할 수 있는 email입니다.');
				} else {
					alert('이미 존재하는 Email입니다.');
				}
			},
			error:function(xhr){
				alert('잠시후에 시도하세요');
			}
		})
	}
}
// document.getElementById('alrnr').style.color= "white";
// document.getElementById('alrnr').style.backgroundColor="blue";
</script>
</body>
<%@ include file="commons_js.jsp"%>
<%@ include file="validation_js.jsp"%>