<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<head>
<title>회원 정보 수정</title>
</head>
<body>
<style>
#pictureView{
	width:200px;
	height:200px;
	background-repeat:no-repeat;
	background-position:center;
	background-size:cover;
}
.upload-btn{
	height: 38px;
}
.col-3,.col-6,.col-9{
	margin-top: 10px;
}
</style>
	<h4 class="text-center">회원 정보 수정</h4>
	<div class="container">
	<!-- 회원 정보 시작 -->
		<div class="row justify-content-md-center">
			<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;
			background-image:url('<%=request.getContextPath() %>/commons/member/getProfile?id=${member.email}'),url('https://via.placeholder.com/200?text=Profile');">
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 align-self-end">
			<button type="button" class="btn btn-info" onclick="modify()">수정</button>
<!-- 			<button type="button" class="btn btn-info" onclick="remove()">탈퇴</button> -->
			</div>
		</div>
			<form role="form" class="modify" id="modifyForm" method="post" action="modify?${_csrf.parameterName}=${_csrf.token}">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="hidden" name="picture" id="picture"/>
				<div class="form-group row">
					<div class="input-group input-group">
						<input id="inputFileName" class="form-control" type="text" name="inputFileName" readonly="readonly" />
						<label for="inputFile" class="btn btn-warning btn-flat input-group-addon upload-form ">파일선택</label>
						<span class="input-group-append">											
							<button type="button" class="btn btn-info btn-append upload-form upload-btn" onclick="upload_go();">업로드</button>											
						</span>
					</div>
					<label for="colFormLabelSm" class="col-3 col-form-label col-form-label">Email</label>
					<div class="col-9">
						<input type="email" name="email" class="form-control" id="email"  value="${member.email }">
					</div>
					<label for="colFormLabelSm" class="col-3 col-form-label col-form-label">이름</label>
					<div class="col-9">
						<input type="text" name="name" class="form-control" id="name"  value="${member.name }">
					</div>
					<label for="colFormLabelSm" class="col-3 col-form-label col-form-label">닉네임</label>
					<div class="col-9">
						<input type="text" name="nickname" class="form-control" id="nickname"  value="${member.nickname }" readonly>
					</div>
					<label for="colFormLabelSm" class="col-3 col-form-label col-form-label">우편번호</label>
					<div class="col-6">
						<input type="text" name="postCode" class="form-control" id="postCode"  value="${member.postCode }" readonly>
					</div>
					<div class="col-3">
						<button type="button" class="btn btn-info" id="postCode" onclick="searchAddress()">우편번호</button>
					</div>
					<label for="colFormLabelSm" class="col-3 col-form-label col-form-label">도로명주소</label>
					<div class="col-9">
						<input type="text" name="address" class="form-control" id="address"  value="${member.address }" readonly>
					</div>
					<label for="colFormLabelSm" class="col-3 col-form-label col-form-label">상세주소</label>
					<div class="col-9">
						<input type="text" name="detailAddress" class="form-control" id="detailAddress"  value="${member.detailAddress }" >
					</div>
					<label for="colFormLabelSm" class="col-3 col-form-label col-form-label">휴대전화</label>
					<div class="col-9">
						<input type="text" name="phoneNumber" class="form-control" id="phoneNumber"  value="${member.phoneNumber }">
					</div>
				</div>
			</form>
		</div>	
	<form role="imageForm" action="/commons/member/uploadImg?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input id="inputFile" name="pictureFile" type="file" class="show-for-sr" style="display: none;">
		<input id="oldFile" type="hidden" name="oldPicture" value="" />
		<input type="hidden" name="checkUpload" value="0" />
	</form>
<%@ include file="modify_js.jsp"%>
<%@ include file="/WEB-INF/views/commons/member/commons_js.jsp"%>
</body>
</html>