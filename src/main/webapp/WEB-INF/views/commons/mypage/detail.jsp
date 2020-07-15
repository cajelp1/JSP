<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<head>
	<title>마이페이지</title>
</head>
<body>
<style>
.col-sm-2.col-form-label{
    margin-top: 0px;
}
.detail{
	width: 100%;
}
</style>
	<h4 class="text-center">상세보기</h4>
	<div class="container">
		<div class="row justify-content-md-center">
			<!-- 회원 정보 시작 -->
		
			<form role="detail" class="detail">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="row justify-content-md-center">
				<div class="col-md-auto">
					<img alt="" class="img-responsive center-block" src="<%=request.getContextPath() %>/commons/member/getProfile?id=${member.email}" style="height: 240px; width: auto;">
				</div>
				</div>
				<div class="form-group row" style="margin-top: 20px;">
					<label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3" value="${member.email }" readonly="readonly">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">이름</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3" value="${member.name }" readonly="readonly">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">닉네임</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3" value="${member.nickname }" readonly="readonly">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">우편번호</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3" value="${member.postCode }" readonly="readonly">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">주소</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3" value="${member.address }&nbsp;${member.detailAddress }" readonly="readonly">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label" >전화번호</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3" value="${member.phoneNumber }" readonly="readonly">
					</div>
				</div>
			</form>
			</div>
			<button type="button" class="btn btn-info" onclick="openWindow('/dsp/my/modify?email=${member.email}',720,640);">수정</button>
<!-- 			<button type="button" class="btn btn-info" onclick="go_url('removeCheck')">탈퇴</button> -->
			<button type="button" class="btn btn-info" onclick="go_url('')">마이페이지 메인</button>
		</div>
		<div class="cell small-12">
		</div>

<script>
	function go_url(url){
		location.href="<%=request.getContextPath()%>/dsp/my/"+url;
	}
</script>
</body>
</html>